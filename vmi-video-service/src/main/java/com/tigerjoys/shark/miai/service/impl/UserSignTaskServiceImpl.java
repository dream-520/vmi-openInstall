package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shark.miai.common.enums.UserTaskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.IUserTariffeAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.PointResultDto;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserTariffeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.dto.index.GotoDataItem;
import com.tigerjoys.shark.miai.dto.service.TaskItemDto;
import com.tigerjoys.shark.miai.dto.service.UserSignTaskVO;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserPointAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserSignControlContract;
import com.tigerjoys.shark.miai.inter.contract.IUserSignLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserSignRechargeContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTariffeAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTaskContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTaskLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserPointAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserSignControlEntity;
import com.tigerjoys.shark.miai.inter.entity.UserSignLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserSignRechargeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTaskEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTaskLogEntity;
import com.tigerjoys.shark.miai.service.IUserSignTaskService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * App升级服务实现类
 * @author lipeng
 *
 */
@Service
public class UserSignTaskServiceImpl implements IUserSignTaskService {
	
	@Autowired
	private IUserPointAgent userPointAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserPointAccountContract userPointAccountContract;
	
	@Autowired
	private IUserTariffeAccountContract userTariffeAccountContract;
	
	@Autowired
	private IUserSignLogContract userSignLogContract;
	
	@Autowired
	private IUserSignControlContract userSignControlContract;
	
	@Autowired
	private IUserTaskContract userTaskContract;
	
	@Autowired
	private IUserTaskLogContract userTaskLogContract;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IUserTariffeAgent userTariffeAgent;
	
	@Autowired
	private IUserSignRechargeContract userSignRechargeContract;
	
	@Override
	public ActionResult initSign(long userid) throws Exception {
		if(userid <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), ErrorCodeEnum.parameter_error.getDesc());
		}
		UserBO user = userAgent.findById(userid);
		
		long totalIntegral = 0;
		long currentIntegral = 0;
		int status = 0;		
		int signDays = 0;
		int signVipDays = 0;
		UserSignTaskVO vo = new UserSignTaskVO();
		UserPointAccountEntity point = userPointAccountContract.findByProperty("userid", userid);
		if (point!=null) {
			totalIntegral=point.getDeposit();
			currentIntegral=point.getBalance();
		}
		vo.setTotalIntegral(String.valueOf(totalIntegral));
		vo.setCurrentIntegral(String.valueOf(currentIntegral));
		vo.setIntegral(getPointList());
		//查看今天签没签到
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("sign_time", Tools.getDate()));
		List<UserSignLogEntity> signDay = userSignLogContract.load(pageModel);
		if (Tools.isNotNull(signDay)) {
			status = 1;
			signDays = signDay.get(0).getDay();
			signVipDays = signDay.get(0).getDays();
		}else{
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("sign_time", Tools.getYesterday()));
			List<UserSignLogEntity> signYesterday = userSignLogContract.load(pageModel);
			if (Tools.isNotNull(signYesterday)) {
				signDays = signYesterday.get(0).getDay()<7?signYesterday.get(0).getDay():0;
				signVipDays = signYesterday.get(0).getDays();
			}
		}
		vo.setStatus(status);
		vo.setSignDays(signDays);
		List<TaskItemDto> dtoList = new ArrayList<TaskItemDto>();
		pageModel.clearAll();
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<UserTaskEntity> tastList =  userTaskContract.load(pageModel);
		Map<Long ,UserTaskLogEntity> taskLogMap = getTaskLogMap(userid);
		for (UserTaskEntity task : tastList) {
			TaskItemDto dto = new TaskItemDto();
			UserTaskLogEntity log = taskLogMap.get(task.getId());
			int count = 0;
			if (log!=null) {
				count = log.getCount() < task.getCount() ? log.getCount() : task.getCount();
			}
			String parameters = task.getParameters();
			dto.setTitle(task.getTitle());
			dto.setContent("可获得"+task.getPoint()+"积分，已完成"+count+"/"+task.getCount()+"次");
			dto.setStatus(count < task.getCount()?0:1);
			dto.setAndroidPage(UserTaskEnum.getAndroidPageByCode(parameters));
			dto.setIosPage(UserTaskEnum.getIosPageByCode(parameters));
			dto.setParame(task.getParame());
			dtoList.add(dto);
		}
		vo.setTaskData(dtoList);
		
		vo.setSignTip("连续签到"+signVipDays+"天");
		long balance = userTariffeAgent.getTariffeBalance(userid);
		if (balance>0) {
			List<GotoDataItem> list = new ArrayList<>();
			GotoDataItem vipItem = new GotoDataItem();
			vipItem.setAndroidPage("WebSingleSaveFragment");
			vipItem.setIosPage("8");
			if (Tools.isNotNull(user.getMobile())) {
				vipItem.setParame("{\"strValue2\":\"领取话费\",\"strValue1\":\""+Const.WEB_SITE+"/api/web/getTelephoneCharge\"}");
			}else{
				vipItem.setParame("{\"strValue2\":\"绑定手机号\",\"strValue1\":\""+Const.WEB_SITE+"/api/userSafe/gotoMobilePage\"}");
			}
			vipItem.setImageurl(ServiceHelper.getCdnPhoto("/upload/userSign/phoneBill.png"));
			list.add(vipItem);
			vo.setBanners(list);
		}
		return ActionResult.success(vo);
	}

	
	
	
	
	
	/**
	 * 根据任务ID获得用户任务完成情况
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	private Map<Long, UserTaskLogEntity> getTaskLogMap(long userid) throws Exception {
		Map<Long ,UserTaskLogEntity> taskLogMap =  new HashMap<>();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		List<UserTaskLogEntity> signList = userTaskLogContract.load(pageModel);
		if (Tools.isNotNull(signList)) {
			for (UserTaskLogEntity entity : signList) {
				taskLogMap.put(entity.getTaskid(), entity);
			}
		}
		return taskLogMap;
	}

	/**
	 * 获得签到积分列表
	 * @return
	 * @throws Exception 
	 */
	private List<String> getPointList() throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.asc("day");
		List<UserSignControlEntity> signList = userSignControlContract.load(pageModel);
		List<String> list = new ArrayList<>();
		for (UserSignControlEntity sign : signList) {
			list.add("+"+sign.getPoint());
		}
		return list;
	}
	
	@Override
	public ActionResult signing(long userid) throws Exception {
		if(userid <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), ErrorCodeEnum.parameter_error.getDesc());
		}
		//查看今天签没签到
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("sign_time", Tools.getDate()));
		List<UserSignLogEntity> signDay = userSignLogContract.load(pageModel);
		if (Tools.isNotNull(signDay)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), ErrorCodeEnum.parameter_error.getDesc());
		}
		pageModel.clearAll();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("sign_time", Tools.getYesterday()));
		List<UserSignLogEntity> signYesterday = userSignLogContract.load(pageModel);
		int day = 0;
		int days = 0;
		if (Tools.isNotNull(signYesterday)) {
			day = signYesterday.get(0).getDay();
			days = signYesterday.get(0).getDays();
		}
		int signPoint = 0;
		Date currDate = new Date();
		UserSignLogEntity sign = new UserSignLogEntity();
		sign.setUserid(userid);
		sign.setCreate_time(currDate);
		sign.setSign_time(Tools.getDate());
		sign.setMonth(Tools.getMonth());
		sign.setDay(day<7?day+1:1);
		sign.setSign_status(0);
		if (days+1==20) {
			try {
				UserTariffeAccountEntity userTariffeAccount = userTariffeAccountContract.findByProperty("userid", userid);
				if (userTariffeAccount!=null && userTariffeAccount.getStatus() == 1) {
					UserSignRechargeEntity recharge = new UserSignRechargeEntity();
					recharge.setCreate_time(currDate);
					recharge.setUserid(userid);
					recharge.setStatus(0);
					userSignRechargeContract.insert(recharge);
					userTariffeAgent.changeTariffeAccount(userid, 2000, UserTariffeAccountLogTypeEnum.tariffe_recharge.getCode(), 0, recharge.getId()+"" , UserTariffeAccountLogTypeEnum.tariffe_recharge.getDesc());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (days==20) {
			UserTariffeAccountEntity userTariffeAccount = userTariffeAccountContract.findByProperty("userid", userid);
			if (userTariffeAccount!=null && userTariffeAccount.getStatus() == 1) {
				sign.setDays(1);
			}else{
				sign.setDays(days+1);
			}
		}else{
			sign.setDays(days+1);
		}
		try {
			//送签到的积分
			userSignLogContract.insert(sign);
			UserSignControlEntity signControl = userSignControlContract.findByProperty("day", sign.getDay());
			signPoint = signControl.getPoint();
			userPointAgent.changePointAccount(userid, signPoint, UserPointAccountLogTypeEnum.user_sign.getCode(), 1, String.valueOf(sign.getId()), UserPointAccountLogTypeEnum.user_sign.getDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> dataMap = new HashMap<>();
		UserPointAccountEntity point = userPointAccountContract.findByProperty("userid", userid);
		if (point!=null) {
			dataMap.put("totalIntegral", point.getDeposit());
			dataMap.put("currentIntegral", point.getBalance());
		}else{
			dataMap.put("totalIntegral", 0);
			dataMap.put("currentIntegral", 0);
		}
		dataMap.put("content", "恭喜您获得"+signPoint+"积分\n明天要继续来哟");
		dataMap.put("signTip", "连续签到"+sign.getDays()+"天");
		dataMap.put("signDays", sign.getDay());
		return ActionResult.success(dataMap);
	}

	@Override
	public ActionResult pointExchanging(long point, int type) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		if(userid < 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), ErrorCodeEnum.parameter_error.getDesc());
		}
		if(point > userPointAgent.getPointBalance(userid)) {
			return ActionResult.fail(ErrorCodeEnum.point_exchang_more_balance.getCode(), ErrorCodeEnum.point_exchang_more_balance.getDesc());
		}
		if (point <= 0 || point % 100 != 0) {
			return ActionResult.fail(ErrorCodeEnum.point_exchang_rule_false.getCode(), ErrorCodeEnum.point_exchang_rule_false.getDesc());
		}
		PointResultDto<Long> result = null;
		if (type==1) {//钻
			//扣积分
			result = userPointAgent.changePointAccount(userid, point, UserPointAccountLogTypeEnum.point_transform_diamond.getCode(), 0, String.valueOf(IdGenerater.generateId()), UserPointAccountLogTypeEnum.point_transform_diamond.getDesc());
			//加钻
			userDiamondAgent.changeDiamondAccount(userid, point/100*20, (long)point, UserDiamondAccountLogTypeEnum.point_transform_diamond.getCode(), 1, null, String.valueOf(IdGenerater.generateId()), UserDiamondAccountLogTypeEnum.point_transform_diamond.getDesc());
		}else if(type==2){//收益
			//扣积分
			result = userPointAgent.changePointAccount(userid, point, UserPointAccountLogTypeEnum.point_transform_income.getCode(), 0, String.valueOf(IdGenerater.generateId()), UserPointAccountLogTypeEnum.point_transform_income.getDesc());
			//加收益
			userIncomeAgent.changeIncomeAccount(userid, point, 1, UserIncomeAccountLogTypeEnum.point_transform_income, String.valueOf(IdGenerater.generateId()), UserIncomeAccountLogTypeEnum.point_transform_income.getDesc());
		}
		return ActionResult.success(result.getData(),"兑换成功");
	}
}
