package com.tigerjoys.shark.miai.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.inter.contract.IAppControllContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserBlackRoomContract;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.entity.UserBlackRoomEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;

@Service
public class ChannelCheckServiceImpl implements IChannelCheckService {

	//北京 3593  广东省 3696
	private static long province = 3696;
	
	@Autowired
	private IAppAreaService appAreaService;
	
	@Autowired
	private IAppControllContract appControllContract;
	
	@Autowired
	private IUserBlackRoomContract userBlackRoomContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Override
	public boolean checkChannel() {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		if(Tools.isNotNull(header)) {
			int versioncode = header.getVersioncode();
			String channel = header.getChannel();
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("channel", channel));
			pageModel.addQuery(Restrictions.eq("version", versioncode));
			pageModel.addQuery(Restrictions.eq("state", 1));
			try {
				long count = appControllContract.count(pageModel);
				if(count > 0)
					return true;
				
				//特殊处理一个对应的提审账号 设备id值 3a2341225fe26723066047360366a13f    183.197.40.238地址
				long userid = header.getUserid();
				if(userid > 0) {
					/*
					if(userid == 149690510617936128L)
						return true;
					*/
					
					/* 对华为渠道 用户是北京 天津 广东省的进行屏蔽数据
					if(header.getChannel().equals("Huawei_yoyo3")) {
						//处理特殊的区域 北京 天津 广东省
						UserBO bo = userAgent.findById(userid);
						if(Tools.isNotNull(bo)) {
							if(bo.getProvinceid() == 3696 || bo.getProvinceid() == 3958 || bo.getProvinceid() == 3593)
								return true;
						}
					}
					*/
					
					//小黑屋中的用户显示提审数据
					UserBlackRoomEntity black = userBlackRoomContract.findByProperty("userid", userid);
					if(Tools.isNotNull(black))
						return true;
					
					if(Const.is_test) {
						/*
						if(userid == 32441318881952000L)
							return true;
						UserBO user = userAgent.findById(userid);
						if(Tools.isNotNull(user) && user.getClientid().equals("630b5c8d48b05faeb5cae967872c64f4"))
							return true;
						HttpServletRequest request = RequestUtils.getCurrent().getRequest();
						if(Tools.isNotNull(request)) {
							String ip = Tools.getIP(request);
							if(Tools.isNotNull(ip) && ip.equals("192.168.30.60"))
								return true;
						}
						*/
					} else {
						if(userid == 147006931763003648L)
							return true;
						UserBO user = userAgent.findById(userid);
						if(Tools.isNotNull(user) && user.getClientid().equals("3a2341225fe26723066047360366a13f"))
							return true;
						HttpServletRequest request = RequestUtils.getCurrent().getRequest();
						if(Tools.isNotNull(request)) {
							String ip = Tools.getIP(request);
							if(Tools.isNotNull(ip) && ip.equals("183.197.40.238"))
								return true;
						}
					}
				}
			} catch (Exception e) {
				
			}
		}
		return false;
	}

	@Override
	public boolean checkChannelReport() {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		//获取对应的渠道
		if(Tools.isNotNull(header)) {
			int versioncode = header.getVersioncode();
			String channel = header.getChannel();
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("channel", channel));
			pageModel.addQuery(Restrictions.eq("version", versioncode));
			pageModel.addQuery(Restrictions.eq("state", 1));
			try {
				long count = appControllContract.count(pageModel);
				if(count > 0)
					return true;
			} catch (Exception e) {
				
			}
		}
		return false;
	}

	@Override
	public boolean checkShowFakeData() {
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		if(Tools.isNotNull(header)) {
			long userid = header.getUserid();
			try {
				//对应ios 密友的渠道显示真实数据
				if(header.getChannel().equals("ios_miyou") || header.getChannel().equals("User_Share_a")) {
					return false;
				}
				//对于北京 天津 lv3以下用户返回测试数据和屏蔽所有
				UserBO bo = userAgent.findById(userid);
				if(Tools.isNotNull(bo)) {
					if(bo.getCityid() == 4146 || bo.getCityid() == 4149) {
						if(bo.getDegreeid() <= 3)
							return true;
					}
				}
				/*
				long diamond = userDiamondAgent.getDiamondDeposit(userid);
				if(diamond <= 300)
					return true;
				*/
			} catch (Exception e) {
				
			}
		}
		return false;
	}

	@Override
	public boolean checkSendMessage() {
		boolean send = true;
		try {
			SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_ROBOT_COFIG);
			JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
			if(Tools.isNotNull(ctrl)) {
				int message = ctrl.getIntValue("message");
				if (message > 0) {
					send = false;
				}
			}
		} catch (Exception e) {
			
		}
		return send;
	}

	@Override
	public boolean checkShowDail() {
		boolean send = true;
		try {
			SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_ROBOT_COFIG);
			JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
			if(Tools.isNotNull(ctrl)) {
				int dail = ctrl.getIntValue("dail");
				if (dail > 0) {
					send = false;
				}
			}
		} catch (Exception e) {
			
		}
		return send;
	}

	@Override
	public boolean checkSendFlower() {
		boolean send = false;
		try {
			SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_ROBOT_COFIG);
			JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
			if(Tools.isNotNull(ctrl)) {
				int flower = ctrl.getIntValue("flower");
				if (flower > 0) {
					send = true;
				}
			}
		} catch (Exception e) {
			
		}
		return send;
	}

}
