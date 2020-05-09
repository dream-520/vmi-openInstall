package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.DiscoveryAnchorDto;
import com.tigerjoys.shark.miai.dto.service.FateRouletteDto;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IFateWheelLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.FateWheelLogEntity;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * IOS发现H5页面
 * 
 * @author yangjunming
 *
 */
@Controller
//@TestEncrypt("5KdAVyLCYWMpEV/67SjhMbv69yCD3xE0Jep4y5zNC+mqFGREZlehEWlDMO2HnM1Lp12sEbst2smBpZhfnGtVnlSn0uenvm64WZeGG4G8FvLTyFG9KdXstSsA0sklsqM5JlDg8l3TFJ8ifQAWEggn+9gyYPqUvRapzpDNkoVS5ESKOvYe1D2f9+VLBljm1WeA6W7Exwa56FANQ3ckgMYFnpWBIrsYghm1y+wM6ZJMojdSDpVLL1nPcxSqMmYVk5JP8Z7OLvktbj6ZmUnUKGKePFXLMI/7OKHNp35YYMopTDc4zoXjWWEzHNbryZMKbfGkpNzXabUT8XAyeZnhHiZo1wJhfzeBI1AsSSG+8YkC5W8Xoi/HdDErBnRTPQl44EOxg4DpdlMrjZSFHHF+/Btrs+Ag1ptGtJrhIZHbQJjLDqgIVBofzyOVGDUyaev4ymwqFBd0c6zGFSYSHDIOZ2MENwCgUW68qvFgqo3HUvhDlI4=")
@RequestMapping(value = "/web/discovery", produces = Produce.TEXT_HTML)
public class WebDisCoveryController {

	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IFateWheelLogContract fateWheelLogContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	/**
	 * 密聊发现页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/movies", produces = Produce.TEXT_HTML)
	public String shareShow(Model model) throws Exception {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		/*
		//返回对应的圆形头像对应的h5
		if(Tools.isNotNull(header) && header.getVersioncode() != 62) {
			model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
			return "discovery/userList";
		}
		*/
		
		//返回对应的姻缘对对碰的数据
		if(Tools.isNotNull(header) && header.getVersioncode() == 62) {
			model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("state", -9));
			pageModel.addQuery(Restrictions.eq("flag", 1));
			pageModel.addQuery(Restrictions.lt("id", 235));
			List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
			List<FateRouletteDto> datas = null;
			if(Tools.isNotNull(anchors) && anchors.size() > 0) {
				Collections.shuffle(anchors);
				FateRouletteDto data = null;
				datas = new ArrayList<>();
				for(int i=1; i <= 7 && i < anchors.size(); i++) {
					AnchorOnlineEntity anchor = anchors.get(i);
					data = new FateRouletteDto();
					data.setIndex(i);
					data.setUserid(anchor.getUserid()+"");
					data.setNickName(anchor.getNickname());
					data.setPhoto(Const.getCdn(anchor.getPhoto()));
					datas.add(data);
				}
			}
			model.addAttribute("wheels", datas);
			return "discovery/fate";
		}
		
		return "discovery/scenerySource";
	}
	
	/*
	 * 发现也展示主播列表数据
	 */
	@RequestMapping(value="/anchors", produces=Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult getAnchors() throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("state", -9));
		pageModel.addQuery(Restrictions.eq("flag", 1));
		List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
		List<DiscoveryAnchorDto> datas = new ArrayList<>();
		if(Tools.isNotNull(anchors) && anchors.size() > 0) {
			Collections.shuffle(anchors);
			DiscoveryAnchorDto data = null;
			for(int i=0; i < 20 && i < anchors.size(); i++) {
				AnchorOnlineEntity anchor = anchors.get(i);
				data = new DiscoveryAnchorDto();
				data.setNickName(anchor.getNickname());
				data.setUserid(anchor.getUserid()+"");
				data.setPhoto(ServiceHelper.getUserSmallPhoto(anchor.getPhoto()));
				datas.add(data);
			}
		}
		return ActionResult.success(datas);
	}
	
	@Login
	@NoSign
	@RequestMapping(value = "/fate/commit", method = RequestMethod.POST, produces=Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult roulette(@RequestBody String body) throws Exception {
		JSONArray array = JsonHelper.toJsonArray(body);
		//用于存储获取到前端的机器人id值
		List<Long> robotIds = new ArrayList<>();
		if(Tools.isNotNull(array)) {
			for (Object item : array) {
				robotIds.add(Long.valueOf((String)item));
			}
		}
		long logId = IdGenerater.generateId();
	    UserBO user = (UserBO)RequestUtils.getCurrent().getUser();
	    PageModel pageModel = PageModel.getPageModel();
	    pageModel.addQuery(Restrictions.ge("create_time", Tools.getDateTime(Tools.getDayTime())));
	    pageModel.addQuery(Restrictions.lt("create_time", Tools.getDateTime(Tools.getDayTime(-1))));
	    pageModel.addQuery(Restrictions.eq("user_id", user.getUserid()));
	    long count = fateWheelLogContract.count(pageModel);
	    //检测是否超过了指定的次数
	    if(count >= 3) {
	    	return ActionResult.fail(21010, "今天的次数用完了，请明天再试吧");
	    }
	    //进入此处说明已经扣款成功 或者是第一次进行抽奖处理
	    //随机获取对应的抽奖位置
	    int index = randomWheel();
	    long robotId = 0;
	    //是否需要发送消息标记位
	    boolean send = false;
	    FateWheelLogEntity entity = new FateWheelLogEntity();
	    entity.setId(logId);
	    entity.setCreate_time(new Date());
	    entity.setUser_id(user.getUserid());
	    if(index == 8) {
	    	entity.setRobot_id(0L);
	    	entity.setDescription("抽中了--谢谢参与");
	    } else {
	    	robotId = robotIds.get(index-1);
	    	entity.setRobot_id(robotId);
	    	entity.setDescription("抽中了--联系方式");
		}
	    entity.setWheel_id(index);
	    fateWheelLogContract.insert(entity);
	    Map<String, Object> data = new HashMap<>();
	    data.put("index", index);
	    //设置对应的奖励
	    if(index == 8) {
	    	data.put("award", "众多美女等着你哦~");
	    	data.put("des", "感谢参与，不要灰心");
	    } else {
	    	UserBO robot = userAgent.findById(robotId);
	    	if(Tools.isNotNull(robot)) {
	    		//使用字符串格式
	    		data.put("userid", ""+robot.getUserid());
	    		data.put("nickName", robot.getNickname());
	    		data.put("photo", Const.getCdn(robot.getPhoto()));
	    		data.put("des", "恭喜你，和她有缘分哦");
	    		//data.put("award", "她的联系方式为:"+robot.getContactText());
	    	}
		}
	    return ActionResult.success(data);
	}
	
	private int randomWheel() {
		Random random = new Random();
		int select = random.nextInt(1000);
		if(select < 100) {
			return 1;
		} else if(select < 200){
			return 8;
		} else if(select < 300){
			return 3;
		} else if(select < 400){
			return 4;
		} else if(select < 500){
			return 8;
		} else if(select < 600){
			return 6;
		} else if(select < 700){
			return 7;
		} else if(select < 800){
			return 8;
		} else if(select < 900){
			return 2;
		} else {
			return 5;
		}
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
}
