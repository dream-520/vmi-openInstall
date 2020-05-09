package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shark.miai.common.enums.IndexActivityAreaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.page.SortBean;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VChatCallPopTCPDto;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.AnchorOnlineStateEnum;
import com.tigerjoys.shark.miai.agent.enums.AnchorStateEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatPopupStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.dto.service.AnchorContactDto;
import com.tigerjoys.shark.miai.dto.service.BtnData;
import com.tigerjoys.shark.miai.dto.service.DisturbItem;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPage;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPageNew;
import com.tigerjoys.shark.miai.dto.service.UserDisturbDto;
import com.tigerjoys.shark.miai.enums.DlgAndGoPageEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.enums.StaticWebUrlEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorContactLookContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorRecvUserContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorUserGreetContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorContactLookEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorRecvUserEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorUserGreetEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.service.IMsgSceneService;
import com.tigerjoys.shark.miai.service.IVChatTextYXService;

/**
 * 新大v版设置界面的配置
 * @author shiming
 */
@Controller
@RequestMapping(value = "/api")
public class VliaoSettingController extends BaseController {

	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserInviteContract userInviteContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserPayActionContract userPayActionContract;
	
	@Autowired
	private IAnchorRecvUserContract anchorRecvUserContract;
	
	@Autowired
	private IAnchorContactLookContract anchorContactLookContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IAnchorUserGreetContract anchorUserGreetContract;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private IMsgSceneService msgSceneService;
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Autowired
	private IVChatTextYXService vChatTextYXService;
	
	/**
	 * 首页的打招呼操作
	 */
	@RequestMapping(value = "/anchor/greet", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult userGreetAnchor(@RequestBody(required=false) String body) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		long otherId = json.getLongValue("userId");
		if(userid <= 0 || otherId <= 0)
			return ActionResult.fail();
		try {
			//插入打招呼的用户数据
			AnchorUserGreetEntity t = new AnchorUserGreetEntity();
			t.setAnchorid(otherId);
			t.setUserid(userid);
			t.setCreate_time(new Date());
			anchorUserGreetContract.insert(t);
			
			String content = null;
			try {
				SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.APP_TOPPAGE_MSG_COFIG);
				List<String> msgs = JsonHelper.toList(config.getValue(), String.class);
				if(Tools.isNotNull(msgs)) {
					Collections.shuffle(msgs);
					content = msgs.get(0);
				}
			} catch (Exception e) {
				
			}
			if(Tools.isNull(content)) {
				//插入数据成功 触发发送对应的消息业务处理
				List<String> msgs = new ArrayList<>();
				msgs.add("你好呀，很高兴认识你！");
				msgs.add("嗨美女，我对你感兴趣哦，有空聊聊吗？");
				Collections.shuffle(msgs);
				content = msgs.get(0);
			}
			boolean check = channelCheckService.checkChannel();
			ExecutorServiceHelper.executor.execute(new SleepSendMessageThread(userid, otherId, content, check));
		} catch (Exception e) {
			
		}
		return ActionResult.success();
	}
	
	@RequestMapping(value = "/anchor/get/contact", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getAnchorContact() throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		AnchorContactDto dto = new AnchorContactDto();
		dto.setDescription("打开后，联系方式展示在个人主页中，用户付费后即可查看，大V可获得和视频通话分成比例一样的分成");
		if(userid > 0) {
			AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userid);
			if(Tools.isNotNull(anchor)) {
				dto.setSwitchTag(anchor.getContact_on());
				if(Tools.isNotNull(anchor.getContact_text()) && anchor.getContact_text().length() > 0) {
					dto.setContact(anchor.getContact_text());
				} else {
					dto.setContact(null);
				}
			}
		}
		return ActionResult.success(dto);
	}
	
	@RequestMapping(value = "/anchor/modify/contact", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult modifyAnchorContact(@RequestBody(required=false) String body) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		int switchTag = json.getIntValue("switchTag");
		String contact = json.getString("contact");
		if(userid > 0) {
			Map<String, Object> updateStatement = new HashMap<>();
			if(Tools.isNotNull(contact) && contact.length() > 0) {
				updateStatement.put("contact_text", contact);
			}
			updateStatement.put("contact_on", switchTag);
			anchorOnlineContract.updateByProperty(updateStatement, "userid", userid);
		}
		return ActionResult.success();
	}
	
	@RequestMapping(value = "/user/page/contact", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getPersonpageContact(@RequestBody(required=false) String body) throws Exception {
		int status = 0;
		long userid = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		long otherId = json.getLongValue("otherId");
		if(userid <= 0 || otherId <= 0)
			return ActionResult.fail();
		//首先检测对应的用户是否买过了本主播的联系方式
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("anchorid", otherId));
		long count = anchorContactLookContract.count(pageModel);
		if(count == 0) {
			//然后检测对应的主播的联系方式开关是否开启
			AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", otherId);
			if(Tools.isNotNull(anchor)) {
				if(anchor.getContact_on() == 1 && Tools.isNotNull(anchor.getContact_text()) && anchor.getContact_text().length() > 0) {
					status = 1;
				}
			}
		} else {
			status = 1;
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		return ActionResult.success(result);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(value = "/user/page/contact/pay", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult payPersonpageContact(@RequestBody(required=false) String body) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		long otherId = json.getLongValue("otherId");
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", otherId);
		if(userid <= 0 || otherId <= 0 || Tools.isNull(anchor))
			return ActionResult.fail();
		//首先检测对应的用户是否买过了本主播的联系方式
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("anchorid", otherId));
		long count = anchorContactLookContract.count(pageModel);
		int look = 0;
		int price = 0;
		if(count == 0) {
			//首先进行vip检测操作处理
			UserBO user = userAgent.findById(userid);
			if (vChatTextYXService.checkShowVIPFragment(user)) {
				return CheckFailVIPReturnDesc();
			}
			//进行扣费操作处理
			DiamondResultDto<Long> result = DiamondResultDto.fail(AgentErrorCodeEnum.error.getCode());
			price = anchor.getContact_price();
			//计算主播的收益
			float ratio = anchor.getRatio();
			int star3Price = 0;
			int star4Price = 0;
			int star5Price = 0;
			try {
				SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.APP_ROBOT_COFIG);
				JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
				if(Tools.isNotNull(ctrl)) {
					star3Price = ctrl.getIntValue("star3Price");
					star4Price = ctrl.getIntValue("star4Price");
					star5Price = ctrl.getIntValue("star5Price");
				}
			} catch (Exception e) {
				
			}
			if(price == 0) {
				if(anchor.getStar() == 3) {
					if(star3Price > 0) {
						price = star3Price;
					} else {
						price = 2000;
					}
				} else if(anchor.getStar() == 4) {
					if(star4Price > 0) {
						price = star4Price;
					} else {
						price = 4000;
					}
				} else if(anchor.getStar() == 5) {
					if(star5Price > 0) {
						price = star5Price;
					} else {
						price = 6000;
					}
				}
			}
			try {
				SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.APP_ROBOT_COFIG);
				JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
				if(Tools.isNotNull(ctrl)) {
					//获取对应的分成和假主播价格
					if(anchor.getFlag() == 4) {
						int anchorPrice = ctrl.getIntValue("anchorPrice");
						if (anchorPrice > 0) {
							price = anchorPrice;
						}
					}
					int anchorIncome = ctrl.getIntValue("anchorIncome");
					if(anchorIncome > 0) {
						ratio = anchorIncome;
					}
				}
			} catch (Exception e) {
				
			}
			
			long logid = IdGenerater.generateId();
			if(AgentErrorCodeEnum.success.getCode() == (result = userDiamondAgent.changeDiamondAccount(userid, price, null, UserDiamondAccountLogTypeEnum.user_look_anchor_contact.getCode(),0, null, String.valueOf(logid), UserDiamondAccountLogTypeEnum.user_look_anchor_contact.getDesc())).getCode()) {
				//使用默认的分成比例
				if(ratio <= 0)
					ratio = 40;
				int amount = Math.round(price * (ratio / 20));
				userIncomeAgent.changeIncomeAccount(otherId, amount, 1, UserIncomeAccountLogTypeEnum.user_look_anchor_contact, String.valueOf(logid), UserIncomeAccountLogTypeEnum.user_look_anchor_contact.getDesc());
				look = 1;
				AnchorContactLookEntity t = new AnchorContactLookEntity();
				t.setUserid(userid);
				t.setDiamond(price);
				t.setAnchorid(otherId);
				t.setCreate_time(new Date());
				anchorContactLookContract.insert(t);
			} 
		} else {
			look = 1;
		}
		DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
		if(look == 1) {
			//可以进行查看联系方式弹窗
			BtnData chargebtn = new BtnData();
			chargebtn.setBtnName("确定");
			dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
			dlgAndGoPage.setHintInfo(anchor.getNickname()+"联系方式\n"+anchor.getContact_text());
		} else if(look == 0) {
			//费用不足 充值弹窗
			boolean ios = false;
			RequestHeader header = RequestUtils.getCurrent().getHeader();
			if(Tools.isNotNull(header) && header.getOs_type() == 2)
				ios = true;
			if(ios) {
				BtnData cancel = new BtnData();
				cancel.setBtnName("取消");
				
				BtnData chargebtn = new BtnData();
				chargebtn.setBtnName("充值");
				chargebtn.setAndroidPageTag(DlgAndGoPageEnum.iosChargeDiamond.getAndroidPage());
				dlgAndGoPage.setBtnDataList(Arrays.asList(cancel, chargebtn));
				//处理根据真假主播来显示不同的描述方式
				if(anchor.getFlag() == 0) {
					dlgAndGoPage.setHintInfo("查看"+anchor.getNickname()+"联系方式\n 此为认证大V \n 您的钻不足,需要"+price+"钻");
				} else {
					dlgAndGoPage.setHintInfo("查看"+anchor.getNickname()+"联系方式\n 此为未认证用户 \n 您的钻不足,需要"+price+"钻");
				}
			} else {
				BtnData chargebtn = new BtnData();
				chargebtn.setBtnName("充值");
				chargebtn.setAndroidPageTag(DlgAndGoPageEnum.webSingle.getAndroidPage());
				chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+"/api/income/zuanList","我的钱包"));
				dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
				//处理根据真假主播来显示不同的描述方式
				if(anchor.getFlag() == 0) {
					dlgAndGoPage.setHintInfo("查看"+anchor.getNickname()+"联系方式\n 此为认证大V \n 您的钻不足,需要"+price+"钻");
				} else {
					dlgAndGoPage.setHintInfo("查看"+anchor.getNickname()+"联系方式\n 此为未认证用户 \n 您的钻不足,需要"+price+"钻");
				}
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("dlgAndGoPage", dlgAndGoPage);
		return ActionResult.success(result);
	}
	
	public ActionResult CheckFailVIPReturnDesc() {
		Map<String, Object> data = new HashMap<>();
		DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
		String	hintInfo = "还没有开通VIP特色服务功能哦\n开通后可以查看\n更获得100元话费";
		int os_type = RequestUtils.getCurrent().getHeader().getOs_type();
		BtnData chargebtn = new BtnData();
		chargebtn.setBtnName("去开通");
		BtnData cancelbtn = new BtnData();
		cancelbtn.setBtnName("取消");
		if( os_type == 1){
			chargebtn.setAndroidPageTag(IndexActivityAreaEnum.webSingle.getAndroidPage());
			chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+StaticWebUrlEnum.USER_VIP_SERVICE_H5.getPath(),"VIP特色服务"));
			dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
		} else {
			chargebtn.setAndroidPageTag(IndexActivityAreaEnum.vipMemeberPage.getAndroidPage());
			chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(0));
			dlgAndGoPage.setBtnDataList(Arrays.asList(cancelbtn,chargebtn));
		}
		dlgAndGoPage.setHintInfo(hintInfo);
		
		data.put("dlgAndGoPage", dlgAndGoPage);
		data.put("state", 0);

		data.put("iosNeedRecharge", 0);
		data.put("iosMessage", hintInfo);
		return ActionResult.success(data);
	}
	
	@RequestMapping(value = "/setting/param", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getSettingParam() throws Exception {
		Map<String,Object> param = new HashMap<>();
		SysConfigEntity config = sysConfigContract.findByProperty("name", Const.SETTING_CONTACT_INFO);
		String contactField = "";
		String contactValue = "";
		if(Tools.isNotNull(config)) {
			JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
			contactField = (String) ctrl.get("contactField");
			contactValue = (String) ctrl.get("contactValue");
		}
		UserBO user = userAgent.findById(RequestUtils.getCurrent().getUserid());
		if (Tools.isNotNull(userInviteContract.findByProperty("userid", RequestUtils.getCurrent().getUserid()))){
			param.put("showInviteStatus", 0);
		} else {
			Date currdate = new Date();
			Calendar ca = Calendar.getInstance();
			ca.setTime(user.getCreateTime());
			ca.add(Calendar.DATE, 3);
			Date date = ca.getTime();
			param.put("showInviteStatus", currdate.before(date)?1:0);
		}
		
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", user.getUserid());
		if(Tools.isNotNull(anchor) && (anchor.getState() != AnchorStateEnum.apply.getCode() && anchor.getState() != AnchorStateEnum.reject.getCode())) {
			int state = anchor.getDisturb();
			param.put("anchor", 1);
			if(state == 1) {
				param.put("disturb", 1);
			} else {
				param.put("disturb", 0);
			}
		} else {
			//不是服务者
			param.put("anchor", 0);
			param.put("disturb", 0);
		}
		
		if(Tools.isNotNull(contactField) && contactField.length() > 0 && Tools.isNotNull(contactValue) && contactValue.length() > 0) {
			param.put("contact", contactField+":"+contactValue);
		}
		return ActionResult.success(param);
	}
	
	@RequestMapping(value = "/setting/disturb", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult disturb(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long userid = RequestUtils.getCurrent().getUserid();
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userid);
		if(Tools.isNotNull(json) && Tools.isNotNull(anchor)) {
			int disturb = json.getIntValue("disturb");
			if(disturb == 0) {
				anchor.setDisturb(0);
				anchor.setOnline(AnchorOnlineStateEnum.online.getCode());
				anchor.setUpdate_time(new Date());
				anchorOnlineContract.update(anchor);
			} else if(disturb == 1) {
				anchor.setDisturb(1);
				anchor.setOnline(AnchorOnlineStateEnum.quiet.getCode());
				anchor.setUpdate_time(new Date());
				anchorOnlineContract.update(anchor);
			}
			return ActionResult.success();
		} else {
			return ActionResult.fail();
		}
	}
	
	@RequestMapping(value = "/user/disturb/page", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getUserDisturb() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		UserDisturbDto dto = new UserDisturbDto();
		int condition = 0;
		String content = "此功能仅对累计充值1000元及其以上的高级用户使用";
		int status = 0;
		List<DisturbItem> data = null;
		if(userId > 0) {
			UserBO user = userAgent.findById(userId);
			if(Tools.isNotNull(user)) {
				long total = 0;
				//首先检测对应的用户是否满足充值金额
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("user_id", userId));
				pageModel.addQuery(Restrictions.eq("status", 1));
				pageModel.addProjection(Projections.sum("money").as("total"));
				List<Map<String, Object>> maps = userPayActionContract.loadGroupBy(pageModel);
				Map<String, Object> map = maps.get(0);
				if(Tools.isNotNull(map)) {
					total += Tools.parseLong(map.get("total"));
				}
				//检测是否满足配置的1000元充值金额
				if(total/100 >= 1000) {
					condition = 1;
					status = user.getDisturb();
					//if(status == 1) {
						//查询与该用户聊过的主播以及对应的勿扰状态
						pageModel.clearAll();
						pageModel.addQuery(Restrictions.eq("userid", userId));
						pageModel.addSort("create_time", SortBean.ORDER_DESC);
						List<AnchorRecvUserEntity> list = anchorRecvUserContract.load(pageModel);
						if(Tools.isNotNull(list) && list.size() > 0) {
							//首先拼接所有的主播id 查询出对应的主播信息
							List<Long> userids = new ArrayList<Long>();
							for (AnchorRecvUserEntity anchorRecvUserEntity : list) {
								userids.add(anchorRecvUserEntity.getAnchorId());
							}
							pageModel.clearAll();
							pageModel.addQuery(Restrictions.in("userid", userids));
							List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
							Map<Long, AnchorOnlineEntity> anchorMap = new HashMap<>();
							if(Tools.isNotNull(anchors)) {
								for (AnchorOnlineEntity anchor : anchors) {
									anchorMap.put(anchor.getUserid(), anchor);
								}
							}
							data = new ArrayList<>();
							for (AnchorRecvUserEntity anchorRecvUserEntity : list) {
								//首先根据对应的主播id找到对应的主播信息  然后进行数据拼接操作处理
								AnchorOnlineEntity anchor = anchorMap.get(anchorRecvUserEntity.getAnchorId());
								if(Tools.isNotNull(anchor)) {
									DisturbItem item = new DisturbItem();
									item.setNickName(anchor.getNickname());
									item.setPhoto(Const.getCdn(anchor.getPhoto()));
									item.setUserId(anchor.getUserid());
									item.setStatus(anchorRecvUserEntity.getDisturb());
									data.add(item);
								}
							}
						}
					//}
				}
			}
		}
		dto.setCondition(condition);
		dto.setContent(content);
		dto.setData(data);
		dto.setStatus(status);
		return ActionResult.success(dto);
	}
	
	@RequestMapping(value = "/setting/user/disturb", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult setUserDisturb(@RequestBody(required=true) String body) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		if(userid > 0) {
			//获取对应的传入参数   验证参数是否传入有误
			JSONObject obj = JsonHelper.toJsonObject(body);
			if(Tools.isNotNull(obj)) {
				String type = obj.getString("type");
				String anchorId = obj.getString("userId");
				String status = obj.getString("status");
				//logger.info("测试参数数据 type:"+type +" anchorId:"+anchorId+" status:" +status);
				if(Tools.isNotNull(type) && (type.equals("0") || type.equals("1")) && Tools.isNotNull(status) && (status.equals("0") || status.equals("1"))) {
					if(type.equals("0")) {
						//设置对所有用户的勿扰模式
						userAgent.updateUserDisturb(userid, Integer.parseInt(status));
						String hint = "操作成功";
						if(status.equals("0")) {
							hint = "关闭全局勿扰成功";
						} else {
							hint = "开启全局勿扰成功";
						}
						return ActionResult.success(hint);
					} else {
						//设置对单个主播的勿扰模式
						if(Tools.isNotNull(anchorId)) {
							PageModel pageModel = PageModel.getPageModel();
							pageModel.addQuery(Restrictions.eq("anchorId", anchorId));
							pageModel.addQuery(Restrictions.eq("userid", userid));
							Map<String, Object> updateStatement = new HashMap<>();
							updateStatement.put("disturb", status);
							anchorRecvUserContract.updateByCondition(updateStatement , pageModel);
							return ActionResult.success();
						}
					}
				}
			}
		} 
		return ActionResult.fail(ErrorCodeEnum.parameter_error);
	}
	
	
	@RequestMapping(value = "/user/disturb/new/page", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult getUserNewDisturb() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		List<UserDisturbDto> dtos = new ArrayList<>();
		//配置两个Tab页的数据
		UserDisturbDto disturb = new UserDisturbDto();
		disturb.setContent("此功能仅对VIP的高级用户使用");
		UserDisturbDto invisibility = new UserDisturbDto();
		invisibility.setContent("此功能仅对VIP的高级用户使用");
		dtos.add(disturb);
		dtos.add(invisibility);
		UserBO user = userAgent.findById(userId);
		if(Tools.isNotNull(user)) {
			int condition = user.vipValue();
			if(condition > 0) {
				//设置满足对应的条件
				disturb.setCondition(1);
				invisibility.setCondition(1);
				
				//单独配置对应的全局勿扰参数
				disturb.setStatus(user.getDisturb());
				//单独配置隐身页的描述
				invisibility.setDes("对选择的大V不再向对方显示相关信息，对方也拨通不了你");
				//查询需要配置的列表数据
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("userid", userId));
				pageModel.addSort("create_time", SortBean.ORDER_DESC);
				List<AnchorRecvUserEntity> list = anchorRecvUserContract.load(pageModel);
				if(Tools.isNotNull(list) && list.size() > 0) {
					//首先拼接所有的主播id 查询出对应的主播信息
					List<Long> userids = new ArrayList<Long>();
					for (AnchorRecvUserEntity anchorRecvUserEntity : list) {
						userids.add(anchorRecvUserEntity.getAnchorId());
					}
					pageModel.clearAll();
					pageModel.addQuery(Restrictions.in("userid", userids));
					List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
					Map<Long, AnchorOnlineEntity> anchorMap = new HashMap<>();
					if(Tools.isNotNull(anchors)) {
						for (AnchorOnlineEntity anchor : anchors) {
							anchorMap.put(anchor.getUserid(), anchor);
						}
					}
					List<DisturbItem> disturbs = new ArrayList<>();
					List<DisturbItem> invisibilities = new ArrayList<>();
					for (AnchorRecvUserEntity recv : list) {
						//首先根据对应的主播id找到对应的主播信息  然后进行数据拼接操作处理
						AnchorOnlineEntity anchor = anchorMap.get(recv.getAnchorId());
						if(Tools.isNotNull(anchor)) {
							DisturbItem item1 = new DisturbItem();
							item1.setNickName(anchor.getNickname());
							item1.setPhoto(Const.getCdn(anchor.getPhoto()));
							item1.setUserId(anchor.getUserid());
							item1.setStatus(recv.getDisturb());
							disturbs.add(item1);
							
							DisturbItem item2 = new DisturbItem();
							item2.setNickName(anchor.getNickname());
							item2.setPhoto(Const.getCdn(anchor.getPhoto()));
							item2.setUserId(anchor.getUserid());
							item2.setStatus(recv.getInvisibility());
							invisibilities.add(item2);
						}
					}
					disturb.setData(disturbs);
					invisibility.setData(invisibilities);
				}
			}
		}
		return ActionResult.success(dtos);
	}
	
	@RequestMapping(value = "/setting/user/new/disturb", method=RequestMethod.POST)
	@ResponseBody
	public ActionResult setUserNewDisturb(@RequestBody(required=true) String body) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		if(userid > 0) {
			//获取对应的传入参数   验证参数是否传入有误
			JSONObject obj = JsonHelper.toJsonObject(body);
			if(Tools.isNotNull(obj)) {
				String type = obj.getString("type");
				String anchorId = obj.getString("userId");
				String status = obj.getString("status");
				//logger.info("测试参数数据 type:"+type +" anchorId:"+anchorId+" status:" +status);
				if(Tools.isNotNull(type) && (type.equals("0") || type.equals("1") || type.equals("2")) && Tools.isNotNull(status) && (status.equals("0") || status.equals("1"))) {
					if(type.equals("0")) {
						//设置对所有用户的勿扰模式
						userAgent.updateUserDisturb(userid, Integer.parseInt(status));
						String hint = "操作成功";
						if(status.equals("0")) {
							hint = "关闭全局勿扰成功";
						} else {
							hint = "开启全局勿扰成功";
						}
						return ActionResult.success(hint);
					} else if(type.equals("1")){
						//设置对单个主播的勿扰模式
						if(Tools.isNotNull(anchorId)) {
							PageModel pageModel = PageModel.getPageModel();
							pageModel.addQuery(Restrictions.eq("anchorId", anchorId));
							pageModel.addQuery(Restrictions.eq("userid", userid));
							Map<String, Object> updateStatement = new HashMap<>();
							updateStatement.put("disturb", status);
							anchorRecvUserContract.updateByCondition(updateStatement , pageModel);
							return ActionResult.success();
						}
					} else if(type.equals("2")){
						//设置对单个主播的隐身模式
						if(Tools.isNotNull(anchorId)) {
							PageModel pageModel = PageModel.getPageModel();
							pageModel.addQuery(Restrictions.eq("anchorId", anchorId));
							pageModel.addQuery(Restrictions.eq("userid", userid));
							Map<String, Object> updateStatement = new HashMap<>();
							updateStatement.put("invisibility", status);
							anchorRecvUserContract.updateByCondition(updateStatement , pageModel);
							return ActionResult.success();
						}
					}
				}
			}
		} 
		return ActionResult.fail(ErrorCodeEnum.parameter_error);
	}
	
	private class SleepSendMessageThread implements Runnable {
		
		private long userid;
		private long anchorid;
		private String msg;
		private boolean check;
		
		public SleepSendMessageThread(long userid, long anchorid, String msg, boolean check) {
			this.userid = userid;
			this.anchorid = anchorid;
			this.msg = msg;
			this.check = check;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(500);
				neteaseAgent.pushOneMessage(userid, anchorid, msg, true);
				if(!check)
					msgSceneService.sendNewMsgScene(userid, anchorid);
			} catch (Exception e) {
				
			}
		}
	}
	
}
