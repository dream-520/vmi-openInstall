package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserExtensionAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserExtensionBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserExtensionModifyDto;
import com.tigerjoys.shark.miai.inter.contract.IUserExtensionContract;
import com.tigerjoys.shark.miai.inter.entity.UserExtensionEntity;

import net.rubyeye.xmemcached.utils.ByteUtils;

/**
 * 用户扩展信息代理接口实现类
 * @author chengang
 *
 */
@Service
public class UserExtensionAgentImpl implements IUserExtensionAgent {
	
	private static final UserExtensionBO EMPTY_DTO;
	
	static {
		EMPTY_DTO = new UserExtensionBO();
		EMPTY_DTO.setUserid(0);
		EMPTY_DTO.setId(0);
	}
	
	@Autowired
	private IUserExtensionContract userExtensionContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_EXTENSION_CACHE)
	private CacheRedis userCacheRedis;

	@Override
	public UserExtensionBO findByUserId(long userid) throws Exception {
		if(userid <= 0) return null;
		
		String key = AgentRedisCacheConst.USER_EXTENSION_CACHE_KEY+userid;
		UserExtensionBO dto = (UserExtensionBO)userCacheRedis.getObject(key);
		if(dto != null) {
			if(dto.getUserid() == 0) {
				return null;
			} else {
				return dto;
			}
		}
		
		dto = transferUserExtension(getUserExtensionEntity(userid));
		if(dto == null) {
			dto = EMPTY_DTO;
		}
		
		userCacheRedis.setObject(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);
		
		return dto.getUserid()==0?null:dto;
	}

	@Override
	public Map<Long, UserExtensionBO> findById(List<Long> userIds) throws Exception {
		if(Tools.isNull(userIds)) return null;
		
		Map<Long , UserExtensionBO> dataMap = new HashMap<>();
		List<Object> returnList = userCacheRedis.pipelinedAndReturnAll(pipeline -> {
			for(Long userId : userIds) {
				pipeline.get(ByteUtils.getBytes(AgentRedisCacheConst.USER_EXTENSION_CACHE_KEY+userId));
			}
		});
		
		Set<Long> emptyDataSet = new HashSet<>();
		//批量返回的数据不为空，则将数据转换为对象存储到dataMap中
		if(Tools.isNotNull(returnList)) {
			for(int i=0,size=userIds.size();i<size;i++) {
				byte[] b = (byte[])returnList.get(i);
				if(b != null) {
					UserExtensionBO uf = (UserExtensionBO)userCacheRedis.decode(b);
					if(uf.getUserid() != 0) dataMap.put(userIds.get(i), uf);
				} else {
					emptyDataSet.add(userIds.get(i));
				}
			}
		}
		
		//如果emptyDataSet不为空，则代表还有数据不在缓存中，则需要批量从数据库中获取，并且将其放入到缓存中
		if(Tools.isNotNull(emptyDataSet)) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.in("userid", emptyDataSet));
			List<UserExtensionEntity> list = userExtensionContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				List<UserExtensionBO> boList = new ArrayList<>(list.size());
				for(UserExtensionEntity ug : list) {
					UserExtensionBO bo = transferUserExtension(ug);
					
					dataMap.put(bo.getUserid(), bo);
					boList.add(bo);
					
					//将为查找的数据删除掉
					emptyDataSet.remove(bo.getUserid());
				}
				
				//顺便将数据放入到缓存中
				userCacheRedis.pipelined(pipeline -> {
					boList.forEach(e -> {
						String cacheKey = AgentRedisCacheConst.USER_EXTENSION_CACHE_KEY+e.getUserid();
						pipeline.set(ByteUtils.getBytes(cacheKey), userCacheRedis.encode(e));
						pipeline.expire(cacheKey, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE);
					});
				});
			}
			
			//如果emptyDataSet还有空的，则创建空对象存放到redis中
			if(Tools.isNotNull(emptyDataSet)) {
				byte[] empty = userCacheRedis.encode(EMPTY_DTO);
				
				userCacheRedis.pipelined(pipeline -> {
					emptyDataSet.forEach(userId -> {
						String cacheKey = AgentRedisCacheConst.USER_EXTENSION_CACHE_KEY+userId;
						pipeline.set(ByteUtils.getBytes(cacheKey), empty);
						pipeline.expire(cacheKey, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE);
					});
				});
			}
		}
		return dataMap;
	}

	@Override
	public UserExtensionBO findByUserIdRefreshCache(long userid) throws Exception {
		if(userid <= 0) return null;
		
		UserExtensionBO dto = transferUserExtension(getUserExtensionEntity(userid));
		if(dto == null) {
			dto = EMPTY_DTO;
		}
		
		userCacheRedis.setObject(AgentRedisCacheConst.USER_EXTENSION_CACHE_KEY+userid, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);
		
		return dto.getUserid()==0?null:dto;
	}
	
	/**
	 * 根据用户ID获得用户的扩展信息
	 * @param userId - 用户ID
	 * @return UserExtensionEntity
	 * @throws Exception
	 */
	private UserExtensionEntity getUserExtensionEntity(long userId) throws Exception {
		return userExtensionContract.findByProperty("userid", userId);
	}
	
	/**
	 * 将数据库用户扩展对象转换为UserExtensionBO对象
	 * @param ext - UserExtensionEntity
	 * @return UserExtensionBO
	 */
	private UserExtensionBO transferUserExtension(UserExtensionEntity ext) {
		if(ext == null) return null;
		
		/*String[] traitPointArray = null;
		
		String traitPoint = ext.getTrait_point();
		if(traitPoint != null && traitPoint.length() > 0) {
			traitPointArray = Tools.split(traitPoint);
		}*/
		
		UserExtensionBO dto = new UserExtensionBO();
		dto.setId(ext.getId());
		dto.setIncome(ext.getIncome());
		dto.setJob(ext.getJob());
		dto.setMakeFriend(ext.getMake_friend());
		dto.setMarriage(ext.getMarriage());
		dto.setSexOpinion(ext.getSex_opinion());
		dto.setSpouseOpinion(ext.getSpouse_opinion());
		dto.setStature(ext.getStature());
		//dto.setTraitPoint(traitPointArray);
		dto.setTraitPoint(ext.getTrait_point());
		dto.setUserid(ext.getUserid());
		dto.setWeight(ext.getWeight());
		dto.setZodiac(ext.getZodiac());
		//扩展参数
		dto.setExtraJson(ext.getParamJson());
		
		return dto;
	}

	@Override
	public void updateUserExtension(UserExtensionModifyDto dto) throws Exception {
		if(dto.getUserid() <= 0) {
			throw new IllegalArgumentException("parameter userid is error");
		}
		
		UserExtensionBO ext = findByUserId(dto.getUserid());
		if(ext == null) {
			throw new NullPointerException("userid is not found!");
		}
		
		try {
			UserExtensionEntity temp = new UserExtensionEntity();
			temp.setZodiac(dto.getZodiac());
			temp.setUserid(dto.getUserid());
			temp.setMarriage(dto.getMarriage());
			temp.setJob(dto.getJob());
			temp.setIncome(dto.getIncome());
			temp.setStature(dto.getStature());
			temp.setWeight(dto.getWeight());
			temp.setSex_opinion(dto.getSexOpinion());
			temp.setSpouse_opinion(dto.getSpouseOpinion());
			temp.setMake_friend(dto.getMakeFriend());
			temp.setTrait_point(dto.getTraitPoint());
			
			//添加额外的参数
			if(!dto.isEmptyParamJson()) {
				temp.setParamJson(ext.getExtraJson());
				
				JSONObject paramJson = dto.getParamJson();
				for(Map.Entry<String, Object> me : paramJson.entrySet()) {
					temp.addParamJson(me.getKey(), me.getValue());
				}
				
				temp.commit();
			}
			
			userExtensionContract.updateEntityByProperty(temp, "userid", dto.getUserid());
		} finally {
			clearUserCache(dto.getUserid());
		}
	}
	
	/**
	 * 清除用户扩展信心的缓存的基本信息
	 * @param userid - 用户ID
	 */
	public void clearUserCache(long userid){
		userCacheRedis.del(AgentRedisCacheConst.USER_EXTENSION_CACHE_KEY+userid);
	}

}
