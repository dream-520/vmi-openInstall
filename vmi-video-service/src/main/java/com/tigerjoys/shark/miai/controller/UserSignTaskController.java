package com.tigerjoys.shark.miai.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.shark.miai.common.enums.UserTypeEnum;
import org.shark.miai.common.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.es.service.IEsUserOnlineRecordService;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IDeviceContract;
import com.tigerjoys.shark.miai.inter.contract.IUserSignLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTariffeAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserSignLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountEntity;
import com.tigerjoys.shark.miai.service.IUserSignTaskService;

/**
 * 用户签到任务功能接口
 * 
 * @author lipeng
 *
 */
@Controller
//@TestEncrypt("5KdAVyLCYWMpEV/67SjhMbv69yCD3xE0Jep4y5zNC+mqFGREZlehEWlDMO2HnM1Lp12sEbst2smBpZhfnGtVnh2MWnFTaAeYWs45M8TofdFClScZDqsrZDepZddIsnn8WXyXrRbrygUCNsLpl2pFIMI7Eq3cFaBhqkrxVcwElSzEXaYNzzjVGd81bSQj2H00f2C1ggXPiyLkLIGqac3aegpq5NE+HBtgw23BjN/vkq+fFnn3GOt1NydSpuhzX4tE9JT8IQID8VQj4zmUUm5mGHU6qnYHWOTsqZDk4+2mOsXRjzWV7pYLASo7RuNCHmRMFRjqDnBOwPnQksFzhPcYw05SYZpjgi5XW13O8fjs0L2sp0OzlPSJ5pbn+jgIoXL/2AmDR3Jrc5D7SU6TJ8QIprhj+OEtfqPL5GtTzTD9lALO7LMxan8gEGCkMsajWmqrnx1HEwf6N+LnDk83uN+YnunC0U+z3X574mvyQqcUAtg=")
@RequestMapping(value = "/api")
public class UserSignTaskController extends BaseController {

	@Autowired
	private IUserSignTaskService userSignTaskService;
	
	@Autowired
	private IUserPointAgent userPointAgent;
	
	@Autowired
	private IDeviceContract deviceContract;
	
	@Autowired
	private IEsUserOnlineRecordService esUserOnlineRecordService;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserSignLogContract userSignLogContract;
	
	@Autowired
	private IUserTariffeAccountContract userTariffeAccountContract;
	
	/**
	 * 统计库session
	 */
	@Autowired
	@Qualifier("sqlSession")
    private SqlSession sqlSession;

	/**
	 * 签到任务初始化
	 * @return
	 * @throws Exception
	 */
	@UserClientService("signTask")
	@RequestMapping(value="/signTask/init")
	@ResponseBody
	public ActionResult signInit() throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		return userSignTaskService.initSign(userid);
	}
	
	/**
	 * 今天签到
	 * 
	 * @throws Exception
	 */
	@UserClientService("signTask")
	@RequestMapping(value = "/signTask/signing")
	@ResponseBody
	//@NoSign
	public ActionResult signing(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		return userSignTaskService.signing(userid);
	}
	
	/**
	 * 积分兑换
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("point")
	@NoSign
	@RequestMapping(value = "/point/exchange", produces = Produce.TEXT_HTML)
	public String pointExchange(HttpServletRequest request, Model model) throws Exception {
		// 验证请求头
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		model.addAttribute("point", userPointAgent.getPointBalance(RequestUtils.getCurrent().getUserid()));
		return "wallet/exchange";
	}
	
	/**
	 * 积分兑换
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("point")
	@NoSign
	@RequestMapping(value = "/point/exchanging",produces = Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult pointExchanging(HttpServletRequest request, HttpServletResponse response ,@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		long point = json.getLongValue("point");
		int type = json.getIntValue("type");
		return userSignTaskService.pointExchanging(point,type);
	}
	
	/**
	 * 跳转到冲话费页面
	 * @return
	 * @throws Exception
	 */
	@UserClientService("telephoneCharge")
	@NoSign
	@RequestMapping(value="/web/getTelephoneCharge", produces = Produce.TEXT_HTML)
	public String getTelephoneCharge(HttpServletRequest request, Model model) throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		// 验证请求头
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		UserTariffeAccountEntity userTariffeAccount = userTariffeAccountContract.findByProperty("userid", userid);
		if (userTariffeAccount.getStatus()==0) {
			//第一次打开 修改签到规则
			//查看今天签没签到
			PageModel pageModel = new PageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("sign_time", Tools.getDate()));
			List<UserSignLogEntity> signDay = userSignLogContract.load(pageModel);
			if (Tools.isNotNull(signDay)) {
				UserSignLogEntity log = signDay.get(0);
				UserSignLogEntity temp = new UserSignLogEntity();
				temp.setId(log.getId());
				temp.setDays(1);
				userSignLogContract.update(temp);
			}else{
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.eq("sign_time", Tools.getYesterday()));
				List<UserSignLogEntity> signYesterday = userSignLogContract.load(pageModel);
				if (Tools.isNotNull(signYesterday)) {
					UserSignLogEntity log = signYesterday.get(0);
					UserSignLogEntity temp = new UserSignLogEntity();
					temp.setId(log.getId());
					temp.setDays(0);
					userSignLogContract.update(temp);
				}
			}
			UserTariffeAccountEntity userTariffeAccountTemp = new UserTariffeAccountEntity();
			userTariffeAccountTemp.setId(userTariffeAccount.getId());
			userTariffeAccountTemp.setStatus(1);
			userTariffeAccountContract.update(userTariffeAccountTemp);
			model.addAttribute("status", 0);
		}else{
			int days = 1;
			PageModel pageModel = new PageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("sign_time", Tools.getDate()));
			List<UserSignLogEntity> signDay = userSignLogContract.load(pageModel);
			if (Tools.isNotNull(signDay)) {
				days = signDay.get(0).getDays();
			}else{
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.eq("sign_time", Tools.getYesterday()));
				List<UserSignLogEntity> signYesterday = userSignLogContract.load(pageModel);
				if (Tools.isNotNull(signYesterday)) {
					days = signYesterday.get(0).getDays();
				}
			}
			model.addAttribute("days", days);
			model.addAttribute("needDays", 20-days);
			model.addAttribute("consume", userTariffeAccount.getConsume()/100);
			model.addAttribute("balance", userTariffeAccount.getBalance()/100);
			model.addAttribute("status", 1);
		}
		return "integral/telExpense";
	}
	
	
	
	/**
	 * H5-付费统计
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("statistics")
	@NoSign
	@RequestMapping(value = "/statistics/pay", produces = Produce.TEXT_HTML)
	public String statisticsPay(HttpServletRequest request, Model model) throws Exception {
		// 验证请求头
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		model.addAttribute("act", getAct());//总激活数
		model.addAttribute("actUser", getActUser());//总活跃
		model.addAttribute("onlineAnchors", getOnlineAnchors());//在线主播
		model.addAttribute("chatAnchors", getChatAnchors());//在聊主播
		//总的
		String dateTime = Tools.getDateTime(Tools.getDayTime());
		Map<String, Integer> dataUserPay = getUserPayMap(dateTime);
		model.addAttribute("payTimes", dataUserPay.get("payTimes"));//付费次数
		model.addAttribute("payUsers", dataUserPay.get("payUsers"));//付费人数
		model.addAttribute("payMoney", dataUserPay.get("payMoney")/100.0);//总流水
		//新增总的
		/*Map<String, Integer> dataNewUserPay = getNewUserPayMap(dateTime);
		if (dataUserPay != null) {
			model.addAttribute("newPayTimes", dataNewUserPay.get("newPayTimes"));//新增付费次数
			model.addAttribute("newPayUsers", dataNewUserPay.get("newPayUsers"));//新增付费人数
			model.addAttribute("newPayMoney", dataNewUserPay.get("newPayMoney"));//新增总流水
		}*/
		
		//华为总的
		Map<String, Integer> dataHuaweiUserPay = getUserPayMap("Huawei_yoyo3",dateTime);
		model.addAttribute("huaweiPayTimes", dataHuaweiUserPay.get("payTimes"));//华为付费次数
		model.addAttribute("huaweiPayUsers", dataHuaweiUserPay.get("payUsers"));//华为付费人数
		model.addAttribute("huaweiPayMoney", dataHuaweiUserPay.get("payMoney")/100);//华为总流水
		//新增华为总的
		Map<String, Integer> dataNewHuaweiUserPay = getNewUserPayMap("Huawei_yoyo3",dateTime);
		model.addAttribute("newHuaweiPayTimes", dataNewHuaweiUserPay.get("newPayTimes"));//新增华为付费次数
		model.addAttribute("newHuaweiPayUsers", dataNewHuaweiUserPay.get("newPayUsers"));//新增华为付费人数
		model.addAttribute("newHuaweiPayMoney", dataNewHuaweiUserPay.get("newPayMoney")/100);//新增华为总流水
		//华为总的
		Map<String, Integer> dataVivoUserPay = getUserPayMap("Vivo_AP_DM_YO",dateTime);
		model.addAttribute("vivoPayTimes", dataVivoUserPay.get("payTimes"));//vivo付费次数
		model.addAttribute("vivoPayUsers", dataVivoUserPay.get("payUsers"));//vivo付费人数
		model.addAttribute("vivoPayMoney", dataVivoUserPay.get("payMoney")/100);//vivo总流水
		//新增华为总的
		Map<String, Integer> dataNewVivoUserPay = getNewUserPayMap("Vivo_AP_DM_YO",dateTime);
		model.addAttribute("newVivoPayTimes", dataNewVivoUserPay.get("newPayTimes"));//新增vivo付费次数
		model.addAttribute("newVivoPayUsers", dataNewVivoUserPay.get("newPayUsers"));//新增vivo付费人数
		model.addAttribute("newVivoPayMoney", dataNewVivoUserPay.get("newPayMoney")/100);//新增vivo总流水
		return "statistics/reportForm";
	}
	
	/**
	 * 获得主播在聊数
	 * @throws Exception 
	 */
	private long getChatAnchors() throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("state", 1));
		pageModel.addQuery(Restrictions.eq("flag", 0));
		pageModel.addQuery(Restrictions.eq("online", 2));//在聊
		return anchorOnlineContract.count(pageModel);
	}
	
	/**
	 * 获得主播在线数
	 * @throws Exception 
	 */
	private long getOnlineAnchors() throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("state", 1));
		pageModel.addQuery(Restrictions.in("flag", 0));
		pageModel.addQuery(Restrictions.eq("online", 3));//在线
		return anchorOnlineContract.count(pageModel);
	}

	/**
	 * 获得主播在线数
	 * @throws Exception 
	 */
	private long getAct() throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.ge("create_time", Tools.getDateTime(Tools.getDayTime())));
		return deviceContract.count(pageModel);
	}
	
	/**
	 * 获得用户活跃数
	 * @throws Exception 
	 */
	private long getActUser() throws Exception {
	return esUserOnlineRecordService.queryUserOnlineNum(new Date(Tools.getDayTime()), new Date(System.currentTimeMillis()), UserTypeEnum.ordinary);
	}
	
	/**
	 * 获得总付费流水数据
	 * @param dateTime
	 * @return
	 */
	private Map<String, Integer> getUserPayMap(String dateTime) {
		Connection conn = DBHelper.getConnection(sqlSession);
		PreparedStatement st = null;
		ResultSet rs = null;
		Map<String, Integer> data = new HashMap<>();
		try {
			String sql = "SELECT COUNT(1) as payTimes , COUNT(DISTINCT user_id) as payUsers, SUM(money) as payMoney FROM t_user_pay_action where `status` = 1 and create_time >= '"+dateTime+"'";
			logger.info(sql);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				data.put("payTimes", rs.getInt("payTimes"));
				data.put("payUsers", rs.getInt("payUsers"));
				data.put("payMoney", rs.getInt("payMoney"));
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DBHelper.closeDBA(rs, st, conn);
		}
		return data;
	}

	/**
	 * 获得新的总付费流水数据
	 * @param dateTime
	 * @return
	 */
	/*private Map<String, Integer> getNewUserPayMap(String dateTime) {
		Connection conn = DBHelper.getConnection(sqlSession);
		PreparedStatement st = null;
		ResultSet rs = null;
		Map<String, Integer> data = new HashMap<>();
		try {
			String sql = "SELECT count(1) as newPayTimes, COUNT(DISTINCT user_id) as newPayUsers, SUM(money) as newPayMoney FROM t_user_pay_action a,t_user_reg_log b where a.user_id = b.userid and TO_DAYS(a.create_time)=TO_DAYS(b.create_time) and a.`status` = 1 and a.create_time >= '"+dateTime+"'";
			logger.info(sql);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				data.put("newPayMoney", rs.getInt("newPayTimes"));
				data.put("newPayMoney", rs.getInt("newPayUsers"));
				data.put("newPayMoney", rs.getInt("newPayMoney"));
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DBHelper.closeDBA(rs, st, conn);
		}
		return data;
	}*/
	
	/**
	 * 获得总付费流水数据
	 * @param dateTime
	 * @return
	 */
	private Map<String, Integer> getUserPayMap(String channel,String dateTime) {
		Connection conn = DBHelper.getConnection(sqlSession);
		PreparedStatement st = null;
		ResultSet rs = null;
		Map<String, Integer> data = new HashMap<>();
		try {
			String sql = "SELECT COUNT(1) as payTimes , COUNT(DISTINCT user_id) as payUsers, SUM(money) as payMoney FROM t_user_pay_action where `status` = 1 and app_channel = '"+channel+"' and create_time >= '"+dateTime+"'";
			logger.info(sql);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				data.put("payTimes", rs.getInt("payTimes"));
				data.put("payUsers", rs.getInt("payUsers"));
				data.put("payMoney", rs.getInt("payMoney"));
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DBHelper.closeDBA(rs, st, conn);
		}
		return data;
	}
	
	/**
	 * 获得新的总付费流水数据
	 * @param dateTime
	 * @return
	 */
	private Map<String, Integer> getNewUserPayMap(String channel,String dateTime) {
		Connection conn = DBHelper.getConnection(sqlSession);
		PreparedStatement st = null;
		ResultSet rs = null;
		Map<String, Integer> data = new HashMap<>();
		try {
			String sql = "SELECT count(1) as newPayTimes, COUNT(DISTINCT user_id) as newPayUsers, SUM(money) as newPayMoney FROM t_user_pay_action a,t_user_reg_log b where a.user_id = b.userid and TO_DAYS(a.create_time)=TO_DAYS(b.create_time) and a.`status` = 1 and a.app_channel = '"+channel+"' and a.create_time >= '"+dateTime+"'";
			logger.info(sql);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				data.put("newPayTimes", rs.getInt("newPayTimes"));
				data.put("newPayUsers", rs.getInt("newPayUsers"));
				data.put("newPayMoney", rs.getInt("newPayMoney"));
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DBHelper.closeDBA(rs, st, conn);
		}
		return data;
	}
}
