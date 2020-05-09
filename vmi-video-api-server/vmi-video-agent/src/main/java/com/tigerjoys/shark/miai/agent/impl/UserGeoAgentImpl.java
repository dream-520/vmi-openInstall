package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.geohash.GeoHash;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserGeoAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserGeoBO;
import com.tigerjoys.shark.miai.inter.contract.IUserGeoContract;
import com.tigerjoys.shark.miai.inter.entity.UserGeoEntity;

import net.rubyeye.xmemcached.utils.ByteUtils;

/**
 * 用户位置信息代理接口实现类
 * @author chengang
 *
 */
@Service
public class UserGeoAgentImpl implements IUserGeoAgent {
	
	/**
	 * 空DTO
	 */
	private final static UserGeoBO EMPTY_DTO;
	
	static {
		EMPTY_DTO = new UserGeoBO();
		EMPTY_DTO.setUserid(0L);
		EMPTY_DTO.setGeoCode("");
		EMPTY_DTO.setLat(0d);
		EMPTY_DTO.setLng(0d);
	}
	
	@Autowired
	private IUserGeoContract userGeoContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_CACHE)
	private CacheRedis userCacheRedis;
	
	@Override
	public UserGeoBO findByUserId(long userid) throws Exception {
		if(userid <= 0) return null;
		
		String key = AgentRedisCacheConst.USER_GEO_CACHE_KEY+userid;
		UserGeoBO dto = (UserGeoBO)userCacheRedis.getObject(key);
		if(dto != null) {
			if(dto.getUserid() == 0) {
				return null;
			} else {
				return dto;
			}
		}
		
		dto = transferUserGeo(getUserGeoEntity(userid));
		if(dto == null) {
			dto = EMPTY_DTO;
		}
		
		userCacheRedis.setObject(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);
		
		return dto.getUserid()==0?null:dto;
	}

	@Override
	public Map<Long, UserGeoBO> findById(List<Long> userIds) throws Exception {
		if(Tools.isNull(userIds)) return null;
		
		Map<Long , UserGeoBO> dataMap = new HashMap<>();
		List<Object> returnList = userCacheRedis.pipelinedAndReturnAll(pipeline -> {
			for(Long userId : userIds) {
				pipeline.get(ByteUtils.getBytes(AgentRedisCacheConst.USER_GEO_CACHE_KEY+userId));
			}
		});
		
		Set<Long> emptyDataSet = new HashSet<>();
		//批量返回的数据不为空，则将数据转换为对象存储到dataMap中
		if(Tools.isNotNull(returnList)) {
			for(int i=0,size=userIds.size();i<size;i++) {
				byte[] b = (byte[])returnList.get(i);
				if(b != null) {
					UserGeoBO uf = (UserGeoBO)userCacheRedis.decode(b);
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
			List<UserGeoEntity> list = userGeoContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				List<UserGeoBO> boList = new ArrayList<>(list.size());
				for(UserGeoEntity ug : list) {
					UserGeoBO bo = transferUserGeo(ug);
					
					dataMap.put(bo.getUserid(), bo);
					boList.add(bo);
					
					//将为查找的数据删除掉
					emptyDataSet.remove(bo.getUserid());
				}
				
				//顺便将数据放入到缓存中
				userCacheRedis.pipelined(pipeline -> {
					boList.forEach(e -> {
						String cacheKey = AgentRedisCacheConst.USER_GEO_CACHE_KEY+e.getUserid();
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
						String cacheKey = AgentRedisCacheConst.USER_GEO_CACHE_KEY+userId;
						pipeline.set(ByteUtils.getBytes(cacheKey), empty);
						pipeline.expire(cacheKey, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE);
					});
				});
			}
		}
		return dataMap;
	}

	@Override
	public UserGeoBO findByUserIdRefreshCache(long userid) throws Exception {
		if(userid <= 0) return null;
		
		UserGeoBO dto = transferUserGeo(getUserGeoEntity(userid));
		if(dto == null) {
			dto = EMPTY_DTO;
		}
		
		userCacheRedis.setObject(AgentRedisCacheConst.USER_GEO_CACHE_KEY+userid, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);
		
		return dto.getUserid()==0?null:dto;
	}

	@Override
	public void modifyUserGeo(long userid, double lng, double lat , String city , Integer cityCode) throws Exception {
		if(userid <= 0) return;
		
		UserGeoBO dto = findByUserId(userid);
		try {
			String geohash = GeoHash.withCharacterPrecision(lat, lng , 9).toBase32();
			
			if(dto == null || dto.getUserid() == 0) {
				UserGeoEntity ug = new UserGeoEntity();
				ug.setAlt(0d);
				ug.setDirection(0d);
				ug.setGeo_code(geohash);
				ug.setLat(lat);
				ug.setLng(lng);
				ug.setSpeed(0d);
				ug.setUpdate_time(new Date());
				ug.setUserid(userid);
				ug.setCity(city);
				ug.setCity_code(cityCode);
				userGeoContract.insert(ug);
			} else {
				UserGeoEntity temp = new UserGeoEntity();
				temp.setId(dto.getId());
				temp.setUpdate_time(new Date());
				temp.setGeo_code(geohash);
				temp.setLat(lat);
				temp.setLng(lng);
				temp.setCity(city);
				temp.setCity_code(cityCode);
				userGeoContract.update(temp);
			}
		} finally {
			clearUserCache(userid);
		}
	}
	
	/**
	 * 清除用户的缓存的基本信息
	 * @param userid - 用户ID
	 */
	private void clearUserCache(long userid){
		userCacheRedis.del(AgentRedisCacheConst.USER_GEO_CACHE_KEY+userid);
	}
	
	/**
	 * 根据用户ID获得用户的定位信息
	 * @param userId - 用户ID
	 * @return UserGeoEntity
	 * @throws Exception
	 */
	private UserGeoEntity getUserGeoEntity(long userId) throws Exception {
		return userGeoContract.findByProperty("userid", userId);
	}
	
	/**
	 * 将数据库用户GEO对象转换为UserGeoBO对象
	 * @param userGeo - UserGeoEntity
	 * @return UserGeoBO
	 */
	private UserGeoBO transferUserGeo(UserGeoEntity userGeo) {
		if(userGeo == null) return null;
		UserGeoBO dto = new UserGeoBO();
		dto.setId(userGeo.getId());
		dto.setGeoCode(userGeo.getGeo_code());
		dto.setLat(userGeo.getLat());
		dto.setLng(userGeo.getLng());
		dto.setUserid(userGeo.getUserid());
		return dto;
	}

}
