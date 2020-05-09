package com.tigerjoys.shark.miai.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IUserBalanceAccountAgent;
import com.tigerjoys.shark.miai.agent.dto.PayAccessDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserBalanceAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserSVIPLevelEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IPaySvipFlowContract;
import com.tigerjoys.shark.miai.inter.entity.PaySvipFlowEntity;
import com.tigerjoys.shark.miai.service.IUserSvipFlowService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 用户高级VIP支付处理等操作接口实现类
 * @author chengang
 *
 */
@Service
public class UserSvipFlowServiceImpl implements IUserSvipFlowService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPaySvipFlowContract paySvipFlowContract;
	
	@Autowired
	private IUserBalanceAccountAgent userBalanceAccountAgent;
	
	@Autowired
	private IPayAgent payAgent;

	@Override
	public ActionResult createPaySvipFlow(int svip , int payType) throws Exception {
		UserSVIPLevelEnum svipLevel = UserSVIPLevelEnum.getByCode(svip);
		if(svipLevel == null || svipLevel == UserSVIPLevelEnum.NORMAL_LEVEL) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		if(payType != PayChannelEnum.ali.getCode() && payType != PayChannelEnum.wx.getCode()) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode() , "仅支持支付宝或微信支付");
		}
		
		UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
		Date currDate = new Date();
		
		PaySvipFlowEntity flow = new PaySvipFlowEntity();
		flow.setCreate_time(currDate);
		flow.setLevel(svip);
		flow.setMoney(svipLevel.getConditionAmount());
		flow.setStatus(0);
		flow.setUpdate_time(currDate);
		flow.setUserid(user.getUserid());
		paySvipFlowContract.insert(flow);
		
		RequestHeader header = RequestUtils.getCurrent().getHeader();

		PayAccessDto access = new PayAccessDto();
		PayChannelEnum channel = PayChannelEnum.getByCode(payType);
		access.setUser_id(user.getUserid());
		access.setNickname(user.getNickname());
		access.setMobile(Tools.formatString(user.getMobile()));
		access.setOrder_id(flow.getId());
		String fm = Tools.formatDouble2PercentToString(flow.getMoney());
		access.setSubject("["+svipLevel.getName()+"：" + fm + "元]");
		access.setDescription(svipLevel.getName()+"：" + fm + "元");
		access.setMoney(flow.getMoney().longValue());
		access.setPay_channel(channel);
		access.setType(PayTypeEnum.pay_paid_appoint_tip);
		access.setApp_type(header.getOs_type());
		access.setApp_channel(header.getChannel());
		access.setApp_version(header.getVersion());
		access.setPackage_name(header.getPackageName());
		return ServiceHelper.dealPayData(payAgent.preparePay(access), channel);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public ActionResult paySuccessSvip(long flowId, long money) throws Exception {
		PaySvipFlowEntity svipFlow = paySvipFlowContract.findById(flowId);
		if (svipFlow == null) {
			logger.error("高级会员购买完成支付回调,购买信息流水没有找到,svipFlow=" + flowId);
			return ActionResult.fail(ErrorCodeEnum.db_not_found.getCode() , "没有找到支付流水");
		}
		if (svipFlow.getStatus() != 0) {
			logger.error("高级会员购买已经支付完毕,购买信息流水状态异常,svipFlow=" + flowId+",userid="+svipFlow.getUserid());
			return ActionResult.fail(ErrorCodeEnum.db_not_found.getCode() , "支付流水信息已完成支付");
		}
		if(svipFlow.getMoney().longValue() != money) {
			logger.error("高级会员购买已经支付完毕,购买信息金额不匹配，原金额:"+svipFlow.getMoney()+",支付金额:"+money+",svipFlow=" + flowId+",userid="+svipFlow.getId());
			return ActionResult.fail(ErrorCodeEnum.db_not_found.getCode() , "支付金额不匹配");
		}
		
		//修改支付成功状态为成功即可
		PaySvipFlowEntity temp = new PaySvipFlowEntity();
		temp.setId(svipFlow.getId());
		temp.setStatus(1);
		temp.setUpdate_time(new Date());
		paySvipFlowContract.update(temp);
		
		UserSVIPLevelEnum svipLevel = UserSVIPLevelEnum.getByCode(svipFlow.getLevel());
		//调用充值
		AgentResult result = userBalanceAccountAgent.changeAccount(svipFlow.getUserid(), svipFlow.getMoney().longValue(), UserBalanceAccountLogTypeEnum.pay_svip, String.valueOf(svipFlow.getId()), "开通"+svipLevel.getName());
		if(result.getCode() != ErrorCodeEnum.success.getCode()) {
			logger.error("高级会员购买，用户余额充值失败，svipFlow="+flowId+",userId="+svipFlow.getUserid()+",code="+result.getCode()+",msg="+result.getCodemsg());
		}
		
		return ActionResult.getResult(result.getCode(), result.getCodemsg(), result.getData(), result.getStamp());
	}

}
