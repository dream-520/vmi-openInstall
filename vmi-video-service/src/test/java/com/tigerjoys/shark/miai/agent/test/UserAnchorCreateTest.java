package com.tigerjoys.shark.miai.agent.test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDynamicContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.contract.IVUserInfoContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.shark.miai.inter.entity.VUserInfoEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

import redis.clients.jedis.Tuple;

/**
 * 机器人主播信息生成
 * @author shiming
 *
 */
public class UserAnchorCreateTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IVUserInfoContract vUserInfoContract;
	
	@Autowired
	private IUserDynamicContract userDynamicContract;
	
	@Autowired
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserOnlineStateAgent userOnlineStateAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis anchorOnlineCacheRedis;
	
	/**
	 * 测试生成数据
	 * @throws Exception
	 */
	@Test
	public void createAnchorInfoTest() throws Exception{
		PageModel pageModel = PageModel.getLimitModel(0, 30);
		pageModel.addQuery(Restrictions.eq("fr", 11));
		List<UserEntity> users = userContract.load(pageModel);
		if(Tools.isNotNull(users)) {
			for (UserEntity user : users) {
				AnchorOnlineEntity anchor = new AnchorOnlineEntity();
				anchor.setUserid(user.getId());
				int star = getRandomNumber(3,5);
				anchor.setStar(star);
				int price = getPrice(star);
				anchor.setPrice(price);
				anchor.setOnline(3);
				anchor.setFlag(1);
				anchor.setState(1);
				anchor.setCreate_time(new Date());
				anchor.setAudit_time(new Date());
				anchor.setUpdate_time(new Date());
				anchorOnlineContract.insert(anchor);
				Thread.sleep(1000*3);
			}
		}
	}
	
	@Test
	public void createNewAnchorInfoTest() throws Exception{
		PageModel page = PageModel.getLimitModel(0, 50);
		List<VUserInfoEntity> infos = vUserInfoContract.load(page);
		if(Tools.isNotNull(infos) && infos.size() > 0) {
			PageModel pageModel = PageModel.getLimitModel(0, 50);
			pageModel.addQuery(Restrictions.eq("fr", 11));
			List<UserEntity> users = userContract.load(pageModel);
			if(Tools.isNotNull(users)) {
				for(int i=0; i<50; i++) {
					VUserInfoEntity info = infos.get(i);
					UserEntity user = users.get(i);
					
					//首选更改用户表里边的用户头像信息
					user.setPhoto(info.getPhoto());
					user.setWaiter(1);
					userContract.update(user);
					
					//然后将原始用户的相册数据和动态数据清除
					PageModel condition = PageModel.getPageModel();
					condition.addQuery(Restrictions.in("userid", user.getId()));
					Map<String, Object> updateStatement = new HashMap<>();
					updateStatement.put("state", -9);
					userPhotoResourceContract.updateByCondition(updateStatement, condition);
					
					condition.clearAll();
					condition.addQuery(Restrictions.in("userid", user.getId()));
					updateStatement.clear();
					updateStatement.put("state", -9);
					userDynamicContract.updateByCondition(updateStatement, condition);
					
					//添加新的相册数据
					List<String> paths = Arrays.asList(Tools.split(info.getPhotos()));
					if(Tools.isNotNull(paths) && paths.size() > 0){
						for (String path : paths) {
							UserPhotoResourceEntity t = new UserPhotoResourceEntity();
							t.setPath(path);
							t.setState(1);
							t.setUserid(user.getId());
							t.setCreate_time(new Date());
							t.setUpdate_time(new Date());
							userPhotoResourceContract.insert(t);
						}
					}
					
					//最后生成对应的主播信息
					AnchorOnlineEntity anchor = new AnchorOnlineEntity();
					anchor.setUserid(user.getId());
					int star = getRandomNumber(3,5);
					anchor.setStar(star);
					int price = getPrice(star);
					anchor.setPrice(price);
					
					//控制状态信息
					if(i < 4) {
						anchor.setOnline(3);
					} else if(i < 13) {
						anchor.setOnline(2);
					} else if(i < 23) {
						anchor.setOnline(1);
					} else {
						anchor.setOnline(0);
					}
					anchor.setFlag(1);
					anchor.setState(1);
					anchor.setCreate_time(new Date());
					anchor.setAudit_time(new Date());
					anchor.setUpdate_time(new Date());
					anchorOnlineContract.insert(anchor);
				}
			}
		}
	}
	
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
	private int getPrice(int star) {
		//3  5、8
		//4  10、11、12、14、15、16
		//5  18、19、20、24、25、28、29、30、35
		if(star == 3) {
			int index = getRandomNumber(1, 2);
			if(index == 1)
				return 5;
			return 8;
		} else if(star == 4) {
			int index = getRandomNumber(1, 6);
			if(index == 1) {
				return 10;
			} else if(index == 2){
				return 11;
			} else if(index == 3){
				return 12;
			} else if(index == 4){
				return 14;
			} else if(index == 5){
				return 15;
			} 
			return 16;
		} else {
			int index = getRandomNumber(1, 9);
			if(index == 1) {
				return 18;
			} else if(index == 2){
				return 19;
			} else if(index == 3){
				return 20;
			} else if(index == 4){
				return 24;
			} else if(index == 5){
				return 25;
			} else if(index == 6){
				return 28;
			} else if(index == 7){
				return 29;
			} else if(index == 8){
				return 30;
			}
			return 35;
		}
	}
	
	@Test
	public void taPersonTest() throws Exception{
		UserBO userSelf = userAgent.findById(66494830169096448L);
		UserBO user = userAgent.findById(66138512797270272L);
		int balanceEnough;
		//主播像普通人 发起视频 不收费      
		if (userSelf.isWaiter() && !user.isWaiter()) {
			//获得普通用户钻石余额
			long balance = userDiamondAgent.getDiamondBalance(user.getUserid());
			//拿到自己的价格
			AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userSelf.getUserid());
			//vo.setAudioBalanceEnough(1);
			if (Tools.isNotNull(anchor)) {
				balanceEnough = anchor.getPrice() * 3 <= balance?1:-1;
			}else{
				balanceEnough = -1;
			}
		}else{
			balanceEnough = -5;
		}
		System.err.println("BalanceEnough:"+balanceEnough);
	}
	
	@Test
	public void onlineStateTest() throws Exception {
		int state = userOnlineStateAgent.userOnlineState(11111111L);
		System.err.println(state);
	}

	@Test
	public void onlineStateListTest() throws Exception {
		String key = "anchor_online_state_1540202220003";
		int index = 1;
		Set<Tuple> returnSet = anchorOnlineCacheRedis.zrangeByScoreWithScores(key, index, index+20, 0, 21);
		if(Tools.isNotNull(returnSet)) {
			System.err.println(returnSet.size());
			for (Tuple tuple : returnSet) {
				System.err.println(tuple.getElement());
			}
		}
	}
	
}
