package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserVideoAuthAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserVideoAuthBO;
import com.tigerjoys.shark.miai.inter.contract.IUserVideoAuthContract;
import com.tigerjoys.shark.miai.inter.entity.UserVideoAuthEntity;

import net.rubyeye.xmemcached.utils.ByteUtils;

/**
 * 用户视频认证代理服务实现类
 * @author chengang
 *
 */
@Service
public class UserVideoAuthAgentImpl implements IUserVideoAuthAgent {
	
	/**
	 * 空对象
	 */
	private static final UserVideoAuthBO EMPTY_DTO;
	
	static {
		EMPTY_DTO = new UserVideoAuthBO();
		EMPTY_DTO.setId(0L);
		EMPTY_DTO.setUserid(0L);
	}
	
	@Autowired
	private IUserVideoAuthContract userVideoAuthContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_CACHE)
	private CacheRedis userCacheRedis;
	
	@Override
	public UserVideoAuthBO findById(long id) throws Exception {
		if(id <= 0) return null;
		
		return transferUserVideoAuth(userVideoAuthContract.findById(id));
	}

	@Override
	public UserVideoAuthBO findByUserId(long userid) throws Exception {
		if(userid <= 0) return null;
		
		String key = AgentRedisCacheConst.USER_VIDEO_AUTH_CACHE_KEY+userid;
		UserVideoAuthBO dto = (UserVideoAuthBO)userCacheRedis.getObject(key);
		if(dto != null) {
			if(dto.getUserid() == 0) {
				return null;
			} else {
				return dto;
			}
		}
		
		dto = transferUserVideoAuth(userVideoAuthContract.findByProperty("userid", userid));
		if(dto == null) {
			dto = EMPTY_DTO;
		}
		
		userCacheRedis.setObject(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);
		
		return dto.getUserid()==0?null:dto;
	}

	@Override
	public Map<Long, UserVideoAuthBO> findByUserId(List<Long> userIds) throws Exception {
		if(Tools.isNull(userIds)) return null;
		
		Map<Long , UserVideoAuthBO> dataMap = new HashMap<>();
		List<Object> returnList = userCacheRedis.pipelinedAndReturnAll(pipeline -> {
			for(Long userId : userIds) {
				pipeline.get(ByteUtils.getBytes(AgentRedisCacheConst.USER_VIDEO_AUTH_CACHE_KEY+userId));
			}
		});
		
		Set<Long> emptyDataSet = new HashSet<>();
		//批量返回的数据不为空，则将数据转换为对象存储到dataMap中
		if(Tools.isNotNull(returnList)) {
			for(int i=0,size=userIds.size();i<size;i++) {
				byte[] b = (byte[])returnList.get(i);
				if(b != null) {
					UserVideoAuthBO uf = (UserVideoAuthBO)userCacheRedis.decode(b);
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
			List<UserVideoAuthEntity> list = userVideoAuthContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				List<UserVideoAuthBO> boList = new ArrayList<>(list.size());
				for(UserVideoAuthEntity ug : list) {
					UserVideoAuthBO bo = transferUserVideoAuth(ug);
					
					dataMap.put(bo.getUserid(), bo);
					boList.add(bo);
					
					//将为查找的数据删除掉
					emptyDataSet.remove(bo.getUserid());
				}
				
				//顺便将数据放入到缓存中
				userCacheRedis.pipelined(pipeline -> {
					boList.forEach(e -> {
						String cacheKey = AgentRedisCacheConst.USER_VIDEO_AUTH_CACHE_KEY+e.getUserid();
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
						String cacheKey = AgentRedisCacheConst.USER_VIDEO_AUTH_CACHE_KEY+userId;
						pipeline.set(ByteUtils.getBytes(cacheKey), empty);
						pipeline.expire(cacheKey, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE);
					});
				});
			}
		}
		return dataMap;
	}

	@Override
	public UserVideoAuthBO findByUserIdRefreshCache(long userid) throws Exception {
		if(userid <= 0) return null;
		
		UserVideoAuthBO dto = transferUserVideoAuth(userVideoAuthContract.findByProperty("userid", userid));
		if(dto == null) {
			dto = EMPTY_DTO;
		}
		
		//获得数据并且刷新缓存
		userCacheRedis.setObject(AgentRedisCacheConst.USER_VIDEO_AUTH_CACHE_KEY+userid, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);
		
		return dto.getUserid()==0?null:dto;
	}

	@Override
	public void insert(UserVideoAuthBO auth) throws Exception {
		if(auth == null || Tools.longValue(auth.getUserid()) <= 0) {
			throw new NullPointerException("auth is null or userid < 0");
		}
		
		UserVideoAuthEntity entity = new UserVideoAuthEntity();
		entity.setCreate_time(auth.getCreateTime());
		entity.setMemo(auth.getMemo());
		entity.setStatus(auth.getStatus());
		entity.setUpdate_time(new Date());
		entity.setUserid(auth.getUserid());
		entity.setVideo_auth(auth.getVideoAuth());
		entity.setVideo_auth_pic(auth.getVideoAuthPic());
		userVideoAuthContract.insert(entity);
		
		//清理掉缓存
		clearUserCache(auth.getUserid());
	}

	@Override
	public void update(UserVideoAuthBO auth) throws Exception {
		if(auth.getId() == null || Tools.longValue(auth.getUserid()) <= 0) {
			throw new NullPointerException("id and userid can not null");
		}
		
		try {
			UserVideoAuthEntity entity = new UserVideoAuthEntity();
			entity.setId(auth.getId());
			entity.setCreate_time(auth.getCreateTime());
			entity.setMemo(auth.getMemo());
			entity.setStatus(auth.getStatus());
			entity.setUpdate_time(new Date());
			entity.setUserid(auth.getUserid());
			entity.setVideo_auth(auth.getVideoAuth());
			entity.setVideo_auth_pic(auth.getVideoAuthPic());
			userVideoAuthContract.update(entity);
		} finally {
			clearUserCache(auth.getUserid());
		}
	}

	@Override
	public long count(PageModel pageModel) throws Exception {
		return userVideoAuthContract.count(pageModel);
	}

	@Override
	public List<UserVideoAuthBO> load(PageModel pageModel) throws Exception {
		List<UserVideoAuthEntity> list = userVideoAuthContract.load(pageModel);
		if(Tools.isNull(list)) {
			return null;
		}
		
		return list.stream().map(this::transferUserVideoAuth).collect(Collectors.toList());
	}
	
	/**
	 * 清除用户视频认证的缓存的基本信息
	 * @param userid - 用户ID
	 */
	public void clearUserCache(long userid){
		userCacheRedis.del(AgentRedisCacheConst.USER_VIDEO_AUTH_CACHE_KEY+userid);
	}
	
	/**
	 * 将数据库用户视频认证对象转换为UserVideoAuthEntity对象
	 * @param auth - UserVideoAuthBO
	 * @return UserVideoAuthBO
	 */
	private UserVideoAuthBO transferUserVideoAuth(UserVideoAuthEntity auth) {
		if(auth == null) return null;
		
		UserVideoAuthBO dto = new UserVideoAuthBO();
		dto.setCreateTime(auth.getCreate_time());
		dto.setId(auth.getId());
		dto.setMemo(auth.getMemo());
		dto.setStatus(auth.getStatus());
		dto.setUserid(auth.getUserid());
		dto.setVideoAuth(auth.getVideo_auth());
		dto.setVideoAuthPic(auth.getVideo_auth_pic());
		
		return dto;
	}

	@Override
	public boolean ifAuthVideo(long userid) throws Exception {
		UserVideoAuthBO bo = findByUserId(userid);
		if (bo == null) return false;
		if (bo.getStatus()==1) {
			return true;
		}
		return false;
	}

}
