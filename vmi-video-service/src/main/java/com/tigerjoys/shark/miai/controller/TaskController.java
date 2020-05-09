package com.tigerjoys.shark.miai.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.context.UserDetails;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.ITaskAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.TaskCategoryEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.ITaskContract;
import com.tigerjoys.shark.miai.inter.contract.ITaskUserCompleteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeWithdrawalContract;
import com.tigerjoys.shark.miai.inter.entity.TaskEntity;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeWithdrawalEntity;

/**
 * @author mouzhanpeng at [2017年11月27日 上午9:54:51]
 * @since JDK 1.8.0
 */
@Controller
@RequestMapping("/api/task")
public class TaskController extends BaseController {

	@Autowired
	private ITaskUserCompleteContract userCompleteContract;

	@Autowired
	private ITaskContract taskContract;

	@Autowired
	private ITaskAgent taskAgent;

	@Autowired
	private IUserIncomeWithdrawalContract userIncomeWithdrawalContract;

	/**
	 * 用户任务首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Login
	@NoSign
	@RequestMapping(value = "/home/list", produces = Produce.TEXT_HTML)
	public String taskHome(Model model) throws Exception {
		UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addProjection(Projections.sum("award").as("total"));
		pageModel.addQuery(Restrictions.eq("user_id", user.getUserid()));
		pageModel.addQuery(Restrictions.eq("completed", 1));
		List<Map<String, Object>> list = userCompleteContract.loadGroupBy(pageModel);
		Map<String, Object> temp = null;
		// 任务总奖励
		model.addAttribute("total", Tools.isNotNull(temp = list.get(0)) ? Tools.formatDouble2PercentToString(Tools.longValue(temp.get("total"))) : "0.00");
		String date = Tools.getDateTime(Tools.getDayTime());
		pageModel.addQuery(Restrictions.ge("create_time", date));
		list = userCompleteContract.loadGroupBy(pageModel);
		// 任务日奖励
		model.addAttribute("daily", Tools.isNotNull(temp = list.get(0)) ? Tools.formatDouble2PercentToString(Tools.longValue(temp.get("total"))) : "0.00");
		model.addAttribute("withdrawal", taskAgent.depositWithdrawalAlone(user.getUserid()) > 0 ? 1 : 0);

		pageModel.clearAll();
		pageModel.addProjection(Projections.count("1").as("times"));
		pageModel.addProjection(Projections.groupProperty("task_id").as("task"));
		pageModel.addQuery(Restrictions.eq("user_id", user.getUserid()));
		pageModel.addQuery(Restrictions.or(Restrictions.ge("create_time", date), Restrictions.in("code", TaskCategoryEnum.INFORMATION.getCode(), TaskCategoryEnum.REGISTER.getCode())));
		// 任务完成度
		list = userCompleteContract.loadGroupBy(pageModel);
		Map<Long, Integer> container = new HashMap<>();
		list.stream().forEach(one -> {
			if(null != one){
				container.put(Tools.parseLong(one.get("task")), Tools.parseInt(one.get("times").toString()));
			}
		});
		pageModel.clearAll();
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.addQuery(Restrictions.eq("level", user.getWaiterLevelId()));
		pageModel.asc("start");
		pageModel.asc("end");
		List<TaskEntity> tasks = taskContract.load(pageModel);
		List<Map<String, Object>> ts = new ArrayList<>();
		int count = 0, amount = 0;
		if (Tools.isNotNull(tasks)) {
			for (TaskEntity task : tasks) {
				Map<String, Object> data = new HashMap<>();
				data.put("title", ++amount + "、" + task.getTitle());
				data.put("desc", task.getDescription());
				data.put("duration", task.getStart() + "~" + task.getEnd());
				data.put("times", task.getDay_times());
				Integer rate = container.get(task.getId());
				data.put("rate", null == rate ? rate = 0 : rate);
				data.put("code", task.getCode());
				if (TaskCategoryEnum.TALENT.getCode() == task.getCode()) {
					data.put("award", task.getAward());
				} else {
					data.put("award", Tools.formatDouble2PercentToString(task.getAward()));
				}
				// 0-未完成，1-已完成，2-去完成，3-时间未到
				int status = 0;
				if (task.getDay_times() <= rate.intValue()) {
					status = 1;
					// 进度条
					count++;
				}
				LocalTime now = LocalTime.now();
				if (now.isAfter(task.getStart().toLocalTime()) && now.isBefore(task.getEnd().toLocalTime())) {
					if (task.getDay_times() > rate) {
						status = 2;
					}
				} else if (now.isBefore(task.getStart().toLocalTime())) {
					if (task.getDay_times() > rate) {
						status = 3;
					}
				}
				data.put("status", status);
				ts.add(data);
			}
		}
		model.addAttribute("count", count);
		model.addAttribute("amount", amount);
		model.addAttribute("progress", (int) (1.0 * count / amount *100));
		model.addAttribute("tasks", ts);
		return "task/list";
	}

	/**
	 * 提现收益申请页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Login
	@NoSign
	@RequestMapping(value = "/withdrawal/page", produces = Produce.TEXT_HTML)
	public String taskWithdrawalPage(Model model) throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("type", AccountType.TASK.getCode()));
		//pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.addQuery(Restrictions.eq("user_id", context.getUserid()));
		pageModel.addQuery(Restrictions.lt("create_time",Tools.getDateTime()));
		pageModel.addQuery(Restrictions.gt("create_time",Tools.getDateTime(System.currentTimeMillis() - Tools.DAY_MILLIS * Const.TASK_WITHDRAWAL_DATE_LIMIT)));
		model.addAttribute("allow", 0 < userIncomeWithdrawalContract.count(pageModel) ? 0 : 1);
		model.addAttribute("balance", Tools.formatDouble2PercentToString(taskAgent.balanceWithdrawalAlone(context.getUserid())));
		model.addAttribute("limit", Const.TASK_WITHDRAWAL_LIMIT);
		model.addAttribute("date", Const.TASK_WITHDRAWAL_DATE_LIMIT);
		model.addAttribute("header", context.getHeaderEncrypt());
		return "task/withdrawal";
	}

	/**
	 * 提现申请
	 * 
	 * @return ActionResult
	 */
	@UserClientService("withdrawal")
	@Login
	@NoSign
	@RequestMapping(value = "withdrawal/apply", produces = Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult taskWithdrawalApply(@RequestBody String body) {
		try {
			UserDetails user = RequestUtils.getCurrent().getUser();
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("type", AccountType.TASK.getCode()));
			//pageModel.addQuery(Restrictions.eq("status", 1));
			pageModel.addQuery(Restrictions.eq("user_id", user.getUserid()));
			pageModel.addQuery(Restrictions.lt("create_time",Tools.getDateTime()));
			pageModel.addQuery(Restrictions.gt("create_time",Tools.getDateTime(System.currentTimeMillis() - Tools.DAY_MILLIS * Const.TASK_WITHDRAWAL_DATE_LIMIT)));
			// 提现间隔7天
			if(0 < userIncomeWithdrawalContract.count(pageModel)){
				return ActionResult.fail(ErrorCodeEnum.task_withdrawal_date_limit);
			}
			JSONObject json = JsonHelper.toJsonObject(body);
			String name = json.getString("name");
			String account = json.getString("account");
			if (Tools.isNull(name) || Tools.isNull(account)) {
				return ActionResult.fail(ErrorCodeEnum.parameter_isnull);
			}
			float money = json.getFloatValue("money");
			if (Const.TASK_WITHDRAWAL_LIMIT > money) {// 提现限额
				return ActionResult.fail(ErrorCodeEnum.task_withdrawal_limit);
			}
			IncomeResultDto<Long> result = taskAgent.withdrawalMoney((int) (money * 100), user.getUserid(), user.getNickname(), name, account);
			if (0 == result.getCode()) {
				Map<String, Object> data = new HashMap<>();
				data.put("balance", Tools.formatDouble2PercentToString(result.getData()));
				return ActionResult.success(data);
			} else {
				return ActionResult.fail(AgentErrorCodeEnum.getByCode(result.getCode()));
			}
		} catch (Exception e) {
			logger.error("申请提现出错", e);
			return ActionResult.fail();
		}
	}

	/**
	 * 提现记录
	 * 
	 * @return ActionResult
	 * @throws Exception 
	 */
	@UserClientService("withdrawal")
	@Login
	@NoSign
	@RequestMapping(value = "withdrawal/list", produces = Produce.TEXT_HTML)
	public String taskWithdrawalLog(Model model) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("user_id", userId));
		pageModel.addQuery(Restrictions.eq("type", AccountType.TASK.getCode()));
		pageModel.desc("id");
		List<UserIncomeWithdrawalEntity> list = userIncomeWithdrawalContract.load(pageModel);
		if (Tools.isNotNull(list)) {
			model.addAttribute("logs", list.stream().map(entity -> {
				Map<String, Object> data = new HashMap<>();
				data.put("title", Tools.formatDouble2PercentToString(entity.getApply_money()) + "元");
				data.put("description",	"扣除" + JsonHelper.toObject(entity.getVacuate(), VacuateConfigDto.class).getTaxRatio() + "%税,实得" + Tools.formatDouble2PercentToString(entity.getApply_money() - entity.getTax()) + "元");
				data.put("date", Tools.getDateTime(entity.getCreate_time()));
				data.put("status", entity.getStatus());
				return data;
			}).collect(Collectors.toList()));
		}
		return "task/withdrawaled";
	}

	/**
	 * 提现记录数据
	 * 
	 * @return ActionResult
	 */
	@UserClientService("withdrawal")
	@Login
	@NoSign
	@RequestMapping(value = "withdrawal/list/ajax", produces = Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult taskWithdrawalLogData() {
		try {
			long userId = RequestUtils.getCurrent().getUserid();
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("user_id", userId));
			pageModel.addQuery(Restrictions.eq("type", AccountType.TASK.getCode()));
			pageModel.desc("id");
			List<UserIncomeWithdrawalEntity> list = userIncomeWithdrawalContract.load(pageModel);
			if (Tools.isNotNull(list)) {
				return ActionResult.success(list.stream().map(entity -> {
					Map<String, Object> data = new HashMap<>();
					data.put("title", Tools.formatDouble2PercentToString(entity.getApply_money()) + "元");
					data.put("description",	"扣除" + JsonHelper.toObject(entity.getVacuate(), VacuateConfigDto.class).getTaxRatio() + "%税,实得" + Tools.formatDouble2PercentToString(entity.getApply_money() - entity.getTax()) + "元");
					data.put("date", Tools.getDateTime(entity.getCreate_time()));
					data.put("status", entity.getStatus());
					return data;
				}).collect(Collectors.toList()));
			}
			return ActionResult.success();
		} catch (Exception e) {
			logger.error("获取提现记录列表出错", e);
			return ActionResult.fail();
		}
	}
}
