package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserFriendAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserFriendBO;
import com.tigerjoys.shark.miai.inter.contract.IUserFriendsContract;
import com.tigerjoys.shark.miai.inter.entity.UserFriendsEntity;

import net.rubyeye.xmemcached.utils.ByteUtils;

/**
 * 用户好友服务代理实现类
 * 将好友信息存储在redis的hash中
 * 在登录的时候加载用户的好友信息
 * 所有的操作都操作缓存，并且同时持久化到数据库中
 * 使用hash存储好友的关系信息
 * 使用zset存储序列[按照添加时间排序]
 * @author chengang
 *
 */
@Service
public class UserFriendAgentImpl implements IUserFriendAgent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserFriendAgentImpl.class);
	
	/**
	 * 好友缓存的默认秒数，3小时
	 */
	public static final int FRIEND_CACHE_EXPIRE = 3600*3;
	
	@Autowired
	private IUserFriendsContract userFriendsContract;
	

	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_FRIEND_LIST_CACHE)
	private CacheRedis userFriendCacheRedis;
	
	@Override
	public UserFriendBO findUserFriendWithoutCache(long userid, long friendid) throws Exception {
		if(userid <= 0 || friendid <= 0 || userid == friendid) return null;
		
		UserFriendsEntity uf = getUserFriendEntity(userid, friendid);
		if(uf == null) {
			return null;
		}
		
		return transferUserFriend(uf);
	}

	@Override
	public UserFriendBO findUserFriend(long userid, long friendid) throws Exception {
		if(userid <= 0 || friendid <= 0 || userid == friendid) return null;
		
		String cacheKey = AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+userid;
		
		UserFriendBO bo = (UserFriendBO)userFriendCacheRedis.hgetObject(cacheKey, String.valueOf(friendid));
		if(bo != null) {
			if(bo.getId() == 0) return null;
			return bo;
		}
		
		//从数据库中获取
		UserFriendsEntity uf = getUserFriendEntity(userid, friendid);
		if(uf != null) {
			bo = transferUserFriend(uf);
		}
		if(bo == null) {
			bo = new UserFriendBO();
			bo.setId(0);
		}
		
		final UserFriendBO temp = bo;//不能更改
		//存入缓存
		userFriendCacheRedis.transaction(tx -> {
			tx.hset(ByteUtils.getBytes(cacheKey), ByteUtils.getBytes(String.valueOf(friendid)), userFriendCacheRedis.encode(temp));
			tx.expire(cacheKey, FRIEND_CACHE_EXPIRE);
		});
		return bo.getId()==0?null:bo;
	}
	
	
	
	@Override
	public boolean isAttention(long userid, long friendid) throws Exception {
		UserFriendBO uf = findUserFriend(userid, friendid);
		if (Tools.isNotNull(uf)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long , UserFriendBO> findUserFriendByFriendIdList(long userid , List<Long> friendidList) throws Exception {
		if(userid <= 0 || Tools.isNull(friendidList)) return null;
		//缓存key
		String cacheKey = AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+userid;
		
		Map<Long , UserFriendBO> dataMap = new HashMap<>();
		//首先从缓存中获取，如果缓存中没有，则从数据库中获取
		List<byte[]> fieldList = friendidList.stream().map(f -> ByteUtils.getBytes(String.valueOf(f))).collect(Collectors.toList());
		List<Object> returnList = userFriendCacheRedis.pipelinedAndReturnAll(pipeline -> {
			pipeline.hmget(ByteUtils.getBytes(cacheKey), fieldList.toArray(new byte[0][0]));
		});
		
		Set<Long> emptyDataSet = new HashSet<>();
		//批量返回的数据不为空，则将数据转换为对象存储到dataMap中
		if(Tools.isNotNull(returnList)) {
			List<byte[]> returnDataList = (List<byte[]>)returnList.get(0);
			for(int i=0,size=friendidList.size();i<size;i++) {
				byte[] b = returnDataList.get(i);
				if(b != null) {
					UserFriendBO uf = (UserFriendBO)userFriendCacheRedis.decode(b);
					//=0是因为缓存缘故
					if(uf.getId() != 0) dataMap.put(friendidList.get(i), uf);
				} else {
					emptyDataSet.add(friendidList.get(i));
				}
			}
		}
		
		//如果emptyDataSet不为空，则代表还有数据不在缓存中，则需要批量从数据库中获取，并且将其放入到缓存中
		if(Tools.isNotNull(emptyDataSet)) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.in("friendid", emptyDataSet));
			
			List<UserFriendsEntity> list = userFriendsContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				List<UserFriendBO> boList = new ArrayList<>(list.size());
				for(UserFriendsEntity uf : list) {
					boList.add(transferUserFriend(uf));
				}
				
				//顺便将数据放入到缓存中
				Map<byte[] , byte[]> cacheMap = new HashMap<>();
				boList.forEach(e -> {
					cacheMap.put(ByteUtils.getBytes(String.valueOf(e.getFriendid())), userFriendCacheRedis.encode(e));
					dataMap.put(e.getFriendid(), e);
				});
				userFriendCacheRedis.pipelined(pipeline -> {
					pipeline.hmset(ByteUtils.getBytes(cacheKey), cacheMap);
					pipeline.expire(cacheKey, FRIEND_CACHE_EXPIRE);
				});
			}
		}
		
		return dataMap;
	}
	
	@Override
	public Map<Long , UserFriendBO> findFriendByUserIdList(long friendid , List<Long> useridList) throws Exception {
		if(friendid <= 0 || Tools.isNull(useridList)) return null;
		
		String fid = String.valueOf(friendid);
		Map<Long , UserFriendBO> dataMap = new HashMap<>();
		//首先从缓存中获取，如果缓存中没有，则从数据库中获取
		List<Object> returnList = userFriendCacheRedis.pipelinedAndReturnAll(pipeline -> {
			useridList.forEach(userid -> {
				pipeline.hget(ByteUtils.getBytes(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+userid), ByteUtils.getBytes(fid));
			});
		});
		
		Set<Long> emptyDataSet = new HashSet<>();
		//批量返回的数据不为空，则将数据转换为对象存储到dataMap中
		if(Tools.isNotNull(returnList)) {
			for(int i=0,size=useridList.size();i<size;i++) {
				byte[] b = (byte[])returnList.get(i);
				if(b != null) {
					UserFriendBO uf = (UserFriendBO)userFriendCacheRedis.decode(b);
					if(uf.getId() != 0) dataMap.put(useridList.get(i), uf);
				} else {
					emptyDataSet.add(useridList.get(i));
				}
			}
		}
		
		//如果emptyDataSet不为空，则代表还有数据不在缓存中，则需要批量从数据库中获取，并且将其放入到缓存中
		if(Tools.isNotNull(emptyDataSet)) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("friendid", friendid));
			pageModel.addQuery(Restrictions.in("userid", emptyDataSet));
			
			List<UserFriendsEntity> list = userFriendsContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				List<UserFriendBO> boList = new ArrayList<>(list.size());
				for(UserFriendsEntity uf : list) {
					boList.add(transferUserFriend(uf));
				}
				
				//顺便将数据放入到缓存中
				userFriendCacheRedis.pipelined(pipeline -> {
					boList.forEach(e -> {
						String cacheKey = AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+e.getUserid();
						pipeline.hset(ByteUtils.getBytes(cacheKey), ByteUtils.getBytes(fid) , userFriendCacheRedis.encode(e));
						pipeline.expire(cacheKey, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE);
						
						dataMap.put(e.getUserid(), e);
					});
				});
			}
		}
		
		return dataMap;
	}

	@Override
	public List<UserFriendBO> findUserFriendList(long userid, long lastUserFrId, int pagesize) throws Exception {
		if(userid <= 0) return null;
		
		PageModel pageModel = PageModel.getLimitModel(0, pagesize);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		if(lastUserFrId > 0) {
			pageModel.addQuery(Restrictions.lt("id", lastUserFrId));
		}
		pageModel.desc("id");
		
		List<Long> friendidList = userFriendsContract.loadFriendIds(pageModel);
		if(Tools.isNull(friendidList)) {
			return null;
		}
		
		LOGGER.info("findUserFriendList "+friendidList.toString());
		
		return getFriendDataList(userid, friendidList);
	}
	
	@Override
	public long findUserFriendCount(long userid) throws Exception {
		String count = userFriendCacheRedis.get(AgentRedisCacheConst.USER_FRIEND_LEN_CACHE_KEY+userid);
		if(count == null) {
			//此处从数据库中获取再放入到缓存中
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			
			count = String.valueOf(userFriendsContract.count(pageModel));
			
			userFriendCacheRedis.setex(AgentRedisCacheConst.USER_FRIEND_LEN_CACHE_KEY+userid, FRIEND_CACHE_EXPIRE , count);
		}
		
		return Tools.parseLong(count);
	}
	
	@Override
	public List<UserFriendBO> findUserFriendPowderList(long userid , long lastUserFrId , int pagesize) throws Exception {
		if(userid <= 0) return null;
		
		PageModel pageModel = PageModel.getLimitModel(0, pagesize);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("powder", 1));//好友
		if(lastUserFrId > 0) {
			pageModel.addQuery(Restrictions.lt("id", lastUserFrId));
		}
		pageModel.desc("id");
		
		List<Long> friendidList = userFriendsContract.loadFriendIds(pageModel);
		if(Tools.isNull(friendidList)) {
			return null;
		}
		
		LOGGER.info("findUserFriendPowderList "+friendidList.toString());
		
		return getFriendDataList(userid, friendidList);
	}
	
	@Override
	public long findUserFriendPowderCount(long userid) throws Exception {
		String count = userFriendCacheRedis.get(AgentRedisCacheConst.USER_FRIEND_POWDER_LEN_CACHE_KEY+userid);
		if(count == null) {
			//此处从数据库中获取再放入到缓存中
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("powder", 1));
			
			count = String.valueOf(userFriendsContract.count(pageModel));
			
			userFriendCacheRedis.setex(AgentRedisCacheConst.USER_FRIEND_POWDER_LEN_CACHE_KEY+userid, FRIEND_CACHE_EXPIRE , count);
		}
		
		return Tools.parseLong(count);
	}
	
	@Override
	public List<UserFriendBO> findFriendUserList(long friendid , long lastUserFrId , int pagesize) throws Exception {
		if(friendid <= 0) return null;
		
		PageModel pageModel = PageModel.getLimitModel(0, pagesize);
		pageModel.addQuery(Restrictions.eq("friendid", friendid));
		if(lastUserFrId > 0) {
			pageModel.addQuery(Restrictions.lt("id", lastUserFrId));
		}
		pageModel.desc("id");
		
		List<Long> useridList = userFriendsContract.loadUserIds(pageModel);
		if(Tools.isNull(useridList)) {
			return null;
		}
		
		LOGGER.info("findFriendUserList "+useridList.toString());
		
		return getUserDataList(friendid, useridList);
	}
	
	@Override
	public long findFriendUserCount(long friendid) throws Exception {
		String count = userFriendCacheRedis.get(AgentRedisCacheConst.FRIEND_USER_LEN_CACHE_KEY+friendid);
		if(count == null) {
			//此处从数据库中获取再放入到缓存中
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("friendid", friendid));
			
			count = String.valueOf(userFriendsContract.count(pageModel));
			
			userFriendCacheRedis.setex(AgentRedisCacheConst.FRIEND_USER_LEN_CACHE_KEY+friendid, FRIEND_CACHE_EXPIRE , count);
		}
		
		return Tools.parseLong(count);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean addFriend(long userid, long friendid) throws Exception {
		if(userid <= 0 || friendid <= 0 || userid == friendid) return false;
		
		//一条SQL搞定
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.or(
			Restrictions.and(Restrictions.eq("userid", userid) , Restrictions.eq("friendid", friendid)),
			Restrictions.and(Restrictions.eq("userid", friendid) , Restrictions.eq("friendid", userid))
		));
		
		List<UserFriendsEntity> list = userFriendsContract.load(pageModel);
		//当用户没有添加过好友，并且对方也没有添加自己，则直接加对方为好友
		if(Tools.isNull(list)) {
			UserFriendsEntity uf = new UserFriendsEntity();
			uf.setCreate_time(new Date());
			uf.setFriendid(friendid);
			uf.setUserid(userid);
			uf.setPowder(0);
			userFriendsContract.insert(uf);
			
			//加入到redis缓存中
			addUserFriendToCache(uf , null);
			//TODO 关注的时候要发送推送消息
//			pushAgent.pushUserHasBeenAttended(userid, friendid);
			return true;
		}
		
		UserFriendsEntity userFriend = null , friendUser = null;
		for(UserFriendsEntity uf : list) {
			if(uf.getUserid() == userid) {
				userFriend = uf;
			} else {
				friendUser = uf;
			}
		}
		
		//此处判断用户是否已经添加过此好友了
		if(userFriend != null) {
			return true;
		}
		
		//此处需要判断用户是否是互粉状态
		boolean isEachPowder = false;
		if(friendUser != null) {
			isEachPowder = true;
		}
		
		if(userFriend == null) {
			userFriend = new UserFriendsEntity();
			userFriend.setCreate_time(new Date());
			userFriend.setFriendid(friendid);
			userFriend.setUserid(userid);
			userFriend.setPowder(isEachPowder?1:0);
			userFriendsContract.insert(userFriend);
		}
		
		//此处判断是否需要将对方的状态也设置为互粉
		if(isEachPowder) {
			friendUser.setPowder(1);
			userFriendsContract.update(friendUser);
		} else {
			friendUser = null;//不要刷新对方用户的缓存
		}
		
		//TODO 关注的时候要发送推送消息
		//pushAgent.pushUserHasBeenAttended(userid, friendid);
		//刷新缓存
		addUserFriendToCache(userFriend , friendUser);
		
		return true;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean removeFriend(long userid, long friendid) throws Exception {
		if(userid <= 0 || friendid <= 0) return false;
		
		UserFriendsEntity uf = getUserFriendEntity(userid, friendid);
		
		if(uf == null) return true;
		
		boolean isEachPowder = (uf.getPowder()==1);//是否互粉

		userFriendsContract.delete(uf);
		
		//查看一下对方的数据，并且将互粉的删除掉。
		UserFriendsEntity fu = null;
		if(isEachPowder) {
			fu = getUserFriendEntity(friendid , userid);
			if(fu != null) {
				//判断互粉，进行删除状态
				fu.setPowder(0);
				userFriendsContract.update(fu);
			} else {
				fu = null;//不要刷新对方用户的缓存
			}
		}
		
		//清空缓存数据
		removeUserFriendToCache(uf , fu , isEachPowder);
		
		return true;
	}
	
	/**
	 * 将对象从redis缓存中删除
	 * @param uf - UserFriendsEntity
	 * @param fu - UserFriendsEntity
	 * @param isEachPowder - 是否是互粉
	 */
	private void removeUserFriendToCache(UserFriendsEntity uf , UserFriendsEntity fu , boolean isEachPowder) {
		String userid = String.valueOf(uf.getUserid()) , friendid = String.valueOf(uf.getFriendid());
		
		UserFriendBO friendUserBo = transferUserFriend(fu);//反向数据
		
		//缓存中删除
		userFriendCacheRedis.transaction(tx -> {
			//将用户从我的关注列表中删除，并且删除元数据
			tx.hdel(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+userid, friendid);
			if(isEachPowder) {//互粉被去掉了
				//将好友对方的元数据更新一下
				if(friendUserBo != null) {
					tx.hset(ByteUtils.getBytes(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+friendid), ByteUtils.getBytes(userid), userFriendCacheRedis.encode(friendUserBo));
					tx.expire(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+friendid, FRIEND_CACHE_EXPIRE);
				}
				
				tx.del(
					//删除用户的好友数量/关注数量缓存
					AgentRedisCacheConst.USER_FRIEND_LEN_CACHE_KEY+userid , 
					AgentRedisCacheConst.USER_FRIEND_POWDER_LEN_CACHE_KEY+userid , 
					//清理一下对方的好友数量/粉丝数量
					AgentRedisCacheConst.USER_FRIEND_POWDER_LEN_CACHE_KEY+friendid ,
					AgentRedisCacheConst.FRIEND_USER_LEN_CACHE_KEY+friendid
				);
			} else {
				tx.del(
					//删除用户的关注数量缓存
					AgentRedisCacheConst.USER_FRIEND_LEN_CACHE_KEY+userid ,
					//清空对方的粉丝数量缓存
					AgentRedisCacheConst.FRIEND_USER_LEN_CACHE_KEY+friendid
				);
			}
		});
	}
	
	/**
	 * 将对象加入到Redis缓存中
	 * @param uf - UserFriendsEntity
	 * @param fu - UserFriendsEntity
	 * @throws Exception 
	 */
	private void addUserFriendToCache(UserFriendsEntity uf , UserFriendsEntity fu) throws Exception {
		//将数据存储
		UserFriendBO bo = transferUserFriend(uf);
		
		String userid = String.valueOf(uf.getUserid()) , friendid = String.valueOf(uf.getFriendid());
		
		UserFriendBO friendUserBo = transferUserFriend(fu);//反向数据
		
		userFriendCacheRedis.transaction(tx -> {
			//将好友加入到元数据
			tx.hset(ByteUtils.getBytes(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+userid), ByteUtils.getBytes(friendid), userFriendCacheRedis.encode(bo));
			tx.expire(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+userid, FRIEND_CACHE_EXPIRE);
			
			//查看是否是互粉，是互粉的话，加入到列表中
			if(uf.getPowder() == 1) {
				//将好友对方的元数据更新一下
				if(friendUserBo != null) {
					tx.hset(ByteUtils.getBytes(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+friendid), ByteUtils.getBytes(userid), userFriendCacheRedis.encode(friendUserBo));
					tx.expire(AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY+friendid, FRIEND_CACHE_EXPIRE);
				}
				
				tx.del(
					//删除我的好友/我的关注长度
					AgentRedisCacheConst.USER_FRIEND_LEN_CACHE_KEY+userid ,
					AgentRedisCacheConst.USER_FRIEND_POWDER_LEN_CACHE_KEY+userid ,
					//删除对方的好友长度缓存/粉丝长度
					AgentRedisCacheConst.USER_FRIEND_POWDER_LEN_CACHE_KEY+friendid,
					AgentRedisCacheConst.FRIEND_USER_LEN_CACHE_KEY + friendid
				);
			} else {
				tx.del(
					//删除我的关注长度
					AgentRedisCacheConst.USER_FRIEND_LEN_CACHE_KEY+userid,
					//删除对方的粉丝长度
					AgentRedisCacheConst.FRIEND_USER_LEN_CACHE_KEY+friendid
				);
			}
		});
	}
	
	@Override
	public boolean clearUserFriendCache(long userid) throws Exception {
		userFriendCacheRedis.del(
			AgentRedisCacheConst.FRIEND_USER_LEN_CACHE_KEY+userid , 
			AgentRedisCacheConst.USER_FRIEND_POWDER_LEN_CACHE_KEY+userid , 
			AgentRedisCacheConst.USER_FRIEND_LEN_CACHE_KEY + userid,
			AgentRedisCacheConst.USER_FRIEND_HASH_CACHE_KEY + userid
		);
		return false;
	}
	
	/**
	 * 根据用户ID和好友ID获得好友关系对象
	 * @param userid - 用户ID
	 * @param friendid - 好友ID
	 * @return UserFriendsEntity
	 * @throws Exception
	 */
	private UserFriendsEntity getUserFriendEntity(long userid, long friendid) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("friendid", friendid));
		
		List<UserFriendsEntity> list = userFriendsContract.load(pageModel);
		
		if(Tools.isNull(list)) return null;
		return list.get(0);
	}
	
	/**
	 * 根据用户ID获得用户的粉丝关系信息
	 * @param friendid - 用户ID
	 * @param useridList - 用户ID集合
	 * @return List<UserFriendBO>
	 * @throws Exception  
	 */
	private List<UserFriendBO> getUserDataList(long friendid , List<Long> useridList) throws Exception {
		if(Tools.isNull(useridList)) return null;
		
		Map<Long, UserFriendBO> resultMap = findFriendByUserIdList(friendid, useridList);
		if(Tools.isNull(resultMap)) return null;
		
		List<UserFriendBO> dataList = new ArrayList<>(useridList.size());
		useridList.forEach(id -> {
			UserFriendBO v = resultMap.get(id);
			if(v != null) dataList.add(v);
		});
		
		return dataList;
	}
	
	/**
	 * 根据用户ID获得用户的好友关系信息
	 * @param userid - 用户ID
	 * @param friendidList - 好友ID集合
	 * @return List<UserFriendBO>
	 * @throws Exception  
	 */
	private List<UserFriendBO> getFriendDataList(long userid , List<Long> friendidList) throws Exception {
		if(Tools.isNull(friendidList)) return null;
		
		Map<Long, UserFriendBO> resultMap = findUserFriendByFriendIdList(userid, friendidList);
		if(Tools.isNull(resultMap)) return null;
		
		List<UserFriendBO> dataList = new ArrayList<>(friendidList.size());
		friendidList.forEach(id -> {
			UserFriendBO v = resultMap.get(id);
			if(v != null) dataList.add(v);
		});
		
		return dataList;
	}
	
	/**
	 * 将数据库对象转换为UserFriendBO对象
	 * @param userFriend - UserFriendsEntity
	 * @return UserFriendBO
	 */
	private UserFriendBO transferUserFriend(UserFriendsEntity userFriend) {
		if(userFriend == null) return null;
		
		UserFriendBO uf = new UserFriendBO();
		uf.setCreate_time(userFriend.getCreate_time());
		uf.setFriendid(userFriend.getFriendid());
		uf.setId(userFriend.getId());
		uf.setUserid(userFriend.getUserid());
		uf.setEachFans(userFriend.getPowder());
		
		return uf;
	}

	/**
	 * 删除与该用户所有的关系
	 */
	@Override
	public boolean removeAllFriend(long userid) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		userFriendsContract.deleteByCondition(pageModel);
		pageModel.clearAll();
		pageModel.addQuery(Restrictions.eq("friendid",userid));
		userFriendsContract.deleteByCondition(pageModel);
		clearUserFriendCache(userid);
		return true;
	}

	
	
	
}
