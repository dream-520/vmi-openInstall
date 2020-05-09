package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserGiftAgent;
import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsFakeContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftLogFakeContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftStatisticsContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftStatisticsFakeContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsFakeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftLogFakeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftStatisticsEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftStatisticsFakeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;

/**
 * 全局广播服务实现类
 * @author lipeng
 *
 */
@Service
public class UserGiftAgentImpl implements IUserGiftAgent {
	
	@Autowired
	private IUserChatGiftStatisticsContract userChatGiftStatisticsContract;
	
	@Autowired
	private IUserChatGiftStatisticsFakeContract userChatGiftStatisticsFakeContract;
	
	@Autowired
	private IAnchorIntimateRankingsFakeContract anchorIntimateRankingsFakeContract;
	
	@Autowired
	private IUserChatGiftLogFakeContract userChatGiftLogFakeContract;
	
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IUserChatGiftContract userChatGiftContract;

	private final Random random = new Random();
	
	
	@Override
	public void insertOrUpdate(Long userid, Long anchorid, Integer diamond) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
		List<UserChatGiftStatisticsEntity> list = userChatGiftStatisticsContract.load(pageModel);
		if (Tools.isNotNull(list)) {
			UserChatGiftStatisticsEntity entity = list.get(0);
			entity.setId(entity.getId());
			entity.setGift_count(entity.getGift_count()+1);
			entity.setGift_contribution(entity.getGift_contribution()+diamond);
			userChatGiftStatisticsContract.update(entity);
		}else{
			UserChatGiftStatisticsEntity entity = new UserChatGiftStatisticsEntity();
			Date currDate =  new Date();
			entity.setCreate_time(currDate);
			entity.setUpdate_time(currDate);
			entity.setUserid(userid);
			entity.setAnchorid(anchorid);
			entity.setGift_contribution(diamond);
			entity.setGift_count(1);
			userChatGiftStatisticsContract.insert(entity);
		}
	}

	@Override
	public List<UserChatGiftStatisticsFakeEntity> creatGiftFakeDate(long anchorid, Integer gift_contribution, Integer gift_count) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.lt("create_time","2019-01-01"));
		pageModel.addQuery(Restrictions.sql("status = 1 order by rand() limit 8"));
		List<UserEntity> userList = userContract.load(pageModel);
		Date currDate = new Date();
		int gift_contributionSum = gift_contribution;
		int gift_countTemp = gift_count;
		for (UserEntity user : userList) {
			int gift_contributionTemp = gift_contributionSum + 2000+random.nextInt(3000);
			while (gift_contributionSum < gift_contributionTemp) {
				UserChatGiftEntity giftEntity = getFakeGlobalBroadcastGiftContent();
				UserChatGiftLogFakeEntity logFakeEntity = new UserChatGiftLogFakeEntity();
				logFakeEntity.setCreate_time(currDate);
				logFakeEntity.setGift_id(giftEntity.getId());
				logFakeEntity.setDiamond(giftEntity.getDiamond());
				logFakeEntity.setOther_id(anchorid);
				logFakeEntity.setUser_id(user.getId());
				userChatGiftLogFakeContract.insert(logFakeEntity);
				gift_contributionSum += giftEntity.getDiamond();
				gift_countTemp ++ ;
			}
			UserChatGiftStatisticsFakeEntity  statisticsFakeEntity = new UserChatGiftStatisticsFakeEntity();
			statisticsFakeEntity.setCreate_time(currDate);
			statisticsFakeEntity.setUpdate_time(currDate);
			statisticsFakeEntity.setUserid(user.getId());
			statisticsFakeEntity.setAnchorid(anchorid);
			statisticsFakeEntity.setGift_contribution(gift_contributionSum);
			statisticsFakeEntity.setGift_count(gift_countTemp);
			userChatGiftStatisticsFakeContract.insert(statisticsFakeEntity);
		}
		pageModel.clearAll();
		pageModel.desc("gift_contribution");
		pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
		List<UserChatGiftStatisticsFakeEntity> fakeList = userChatGiftStatisticsFakeContract.load(pageModel);
		if (Tools.isNotNull(fakeList)) {
			return fakeList;
		}else{
			return null;
		}
	}
	
	/**
	 * 随机生成假数据礼物内容
	 * @return
	 * @throws Exception 
	 */
	private UserChatGiftEntity getFakeGlobalBroadcastGiftContent() throws Exception {
		int select = random.nextInt(100);
		String gift = "玫瑰花";
		if (80>=select && select>70) {
			gift = "黄瓜";
		}else if(70 >= select && select > 60) {
			gift = "香蕉";
		}else if(60 >= select && select > 50) {
			gift = "套套";
		}else if(50 >= select && select > 35) {
			gift = "钻戒";
		}else if(35 >= select && select > 20) {
			gift = "跑车";
		}else if(20 >= select && select > 10) {
			gift = "520";
		}else if(10 >= select && select > 5) {
			gift = "游艇";
		}else if(5 >= select) {
			gift = "火箭";
		}
		UserChatGiftEntity entity = userChatGiftContract.findByProperty("name", gift);
		if (entity!=null){
			return entity;
		}else{
			return userChatGiftContract.findById(7);
		}
	}
	
	@Override
	public List<AnchorIntimateRankingsFakeEntity> creatIntimateRankingsFakeDate(long anchorid, Integer total_money) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.lt("create_time","2019-01-01"));
		pageModel.addQuery(Restrictions.sql("status = 1 order by rand() limit 8"));
		List<UserEntity> userList = userContract.load(pageModel);
		Date currDate = new Date();
		for (UserEntity user : userList) {
			total_money += 2000+random.nextInt(3000);
			AnchorIntimateRankingsFakeEntity  fakeEntity = new AnchorIntimateRankingsFakeEntity();
			fakeEntity.setCreate_time(currDate);
			fakeEntity.setUpdate_time(currDate);
			fakeEntity.setUserid(user.getId());
			fakeEntity.setAnchor_userid(anchorid);
			fakeEntity.setTotal_money(total_money);
			anchorIntimateRankingsFakeContract.insert(fakeEntity);
		}
		pageModel.clearAll();
		pageModel.addQuery(Restrictions.eq("anchor_userid", anchorid));
		pageModel.desc("total_money");
		List<AnchorIntimateRankingsFakeEntity> fakeList = anchorIntimateRankingsFakeContract.load(pageModel);
		if (Tools.isNotNull(fakeList)) {
			return fakeList;
		}else{
			return null;
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 8; i++) {
			System.out.println(i);
		}
	}

}
