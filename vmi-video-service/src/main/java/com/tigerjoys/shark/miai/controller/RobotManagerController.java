package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.DistanceUtils;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserFullBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserCreatDto;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;

//@Controller
//@RequestMapping(value="/api/robot")
public class RobotManagerController extends BaseController {

	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@FilterHeader
	@RequestMapping(value="/createUser")
	@Transactional(rollbackFor=Exception.class)
	public @ResponseBody ActionResult createRobotManUser() throws Exception {
		//加载当前已经使用的userid值
		PageModel pageModel = PageModel.getPageModel();
		List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
		if(Tools.isNotNull(anchors)) {
			List<Long> userids = new ArrayList<>();
			for (AnchorOnlineEntity anchor : anchors) {
				userids.add(anchor.getUserid());
			}
			pageModel.clearAll();
			pageModel.addLimitField(100, 40);
			pageModel.addQuery(Restrictions.eq("fr", 11));
			pageModel.addQuery(Restrictions.notin("id", userids));
			List<UserEntity> users = userContract.load(pageModel);
			if(Tools.isNotNull(users) && users.size() > 30) {
				for (int i = 1; i <= 20; i++) {
					//进行批量创建用户操作处理
					UserEntity entity = users.get(i);
					long uid = IdGenerater.generateId();
					UserCreatDto creat = new UserCreatDto();
					creat.setOpenid(uid+"");
					creat.setNickname(entity.getNickname());
					creat.setPhoto("/upload/user/2019/01/17/"+i+".jpeg");
					creat.setFr(13);
					//配置城市信息
					creat.setCountryid(3592);
					creat.setProvinceid(3593);
					creat.setCityid(4146);
					creat.setSex(2);
					creat.setSignature(entity.getSignature());
					//设置设备id值
					creat.setClientid(MD5.encode(uid+""));
					creat.setPlatform(1);
					double[] randomZuoBiao = DistanceUtils.getRandomDistance(116.403981, 39.914935, 20000);
					creat.setLng(randomZuoBiao[0]);
					creat.setLat(randomZuoBiao[1]);
					creat.setPackageName("com.ydwx.yoyo");
					UserFullBO user = userAgent.createUser(creat, null);
					
					//设置用户为达人的状态变化
					userAgent.updateWaiter(user.getUserid(), 1);
					
					//然后创建对应的主播数据
					AnchorOnlineEntity anchor = new AnchorOnlineEntity();
					anchor.setUserid(user.getUserid());
					anchor.setStar(getRandomNumber(3,5));
					anchor.setPrice(0);
					anchor.setOnline(getRandomNumber(0, 3));
					anchor.setState(-9);
					anchor.setFlag(1);
					anchor.setDisturb(0);
					anchor.setNickname(entity.getNickname());
					anchor.setSignature(entity.getSignature());
					anchor.setIntro(null);
					anchor.setStature(getRandomNumber(160, 175)+" cm");
					anchor.setWeight(getRandomNumber(45, 55)+" kg");
					int index = getRandomNumber(0, 11);
					anchor.setZodiac(Tools.constellationArr[index]);
					anchor.setCityid(131L);
					anchor.setPhoto("/upload/user/2019/01/17/"+i+".jpeg");
					
					//时间相关信息
					anchor.setCreate_time(new Date());
					anchor.setUpdate_time(new Date());
					anchor.setAudit_time(new Date());
					
					anchorOnlineContract.insert(anchor);
					Thread.sleep(1*1000);
				}
			}
		}
		return ActionResult.success();
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
}
