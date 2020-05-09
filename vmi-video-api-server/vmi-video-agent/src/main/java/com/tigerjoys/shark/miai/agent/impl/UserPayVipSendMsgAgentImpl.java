package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserPayVipSendMsgAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppPayVipUserAnchorContract;
import com.tigerjoys.shark.miai.inter.contract.IAppPayVipUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AppPayVipUserAnchorEntity;
import com.tigerjoys.shark.miai.inter.entity.AppPayVipUserEntity;

@Service
public class UserPayVipSendMsgAgentImpl implements IUserPayVipSendMsgAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IAppPayVipUserAnchorContract appPayVipUserAnchorContract; 
	 
	@Autowired
	private IAppPayVipUserContract appPayVipUserContract;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	List<String> msg = null;
	
	@PostConstruct
	public void init() {
		msg = new ArrayList<String>();
		msg.add("我们私下里聊吧，加我联系方式");
		msg.add("小哥哥，你加我微信呀");
		msg.add("你加我联系方式，咱们可以在微信里边聊，有刺激的");
		msg.add("怎么不加我联系方式？");
	}
	
	@Override
	public void userFirstPayVip(long userid) throws Exception {
		logger.info("检测业务逻辑是否进入:"+userid);
		//检测是否需要触发对应的发送联系方式的任务
		UserBO bo = userAgent.findById(userid);
		//确定首次购买vip
		if(Tools.isNotNull(bo) && bo.getVip() == 0) {
			logger.info("本用户不是vip: 首次购买vip业务:"+userid);
			//进行主播的选取
			long anchorid = 0;
			//优先选择在线的真主播
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("state", 1));
			pageModel.addQuery(Restrictions.in("online", 3));
			pageModel.addQuery(Restrictions.eq("contact_on", 1));
			pageModel.addQuery(Restrictions.isNotNull("contact_text"));
			pageModel.addQuery(Restrictions.sql("flag=0 order by rand() limit 1"));
			List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
			if(Tools.isNotNull(anchors) && anchors.size() > 0) {
				anchorid = anchors.get(0).getUserid();
			} else {
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("state", 1));
				pageModel.addQuery(Restrictions.eq("contact_on", 1));
				pageModel.addQuery(Restrictions.isNotNull("contact_text"));
				pageModel.addQuery(Restrictions.sql("flag=4 order by rand() limit 1"));
				anchors = anchorOnlineContract.load(pageModel);
				if(Tools.isNotNull(anchors) && anchors.size() > 0) {
					anchorid = anchors.get(0).getUserid();
				}
			}
			if(anchorid > 0) {
				logger.info("选取对应的主播进行发送诱导消息业务");
				//确定发送消息的索引
				List<Integer> ids = new ArrayList<>();
				ids.add(0);
				ids.add(1);
				ids.add(2);
				ids.add(3);
				Collections.shuffle(ids);
				int index = ids.get(0);

				String sendMsg = msg.get(index);
				if(Tools.isNotNull(sendMsg)) {
					//发送对应的联系方式消息
					neteaseAgent.pushOneMessage(anchorid, userid, sendMsg, true);
					
					//记录发送消息的统计信息
					AppPayVipUserEntity info = new AppPayVipUserEntity();
					info.setUserid(userid);
					info.setState(0);
					info.setCreate_time(new Date());
					info.setEnd_time(new Date());
					appPayVipUserContract.insert(info);
					
					//记录存储发送记录
					AppPayVipUserAnchorEntity sta = new AppPayVipUserAnchorEntity();
					sta.setUserid(userid);
					sta.setAnchorid(anchorid);
					sta.setMsg_index(index);
					sta.setCreate_time(new Date());
					appPayVipUserAnchorContract.insert(sta);
				}
			}
		} else {
			logger.info("不满足条件"+bo.getVip());
		}
	}

	@Override
	public void userFirstPayVipSendOther() throws Exception {
		//查询处当前对应的待发送联系方式的用户
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("state", 0));
		List<AppPayVipUserEntity> list = appPayVipUserContract.load(pageModel);
		for (AppPayVipUserEntity item : list) {
			//查询对应的用户已经接受联系方式的情况
			long userid = item.getUserid();
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			List<AppPayVipUserAnchorEntity> infos = appPayVipUserAnchorContract.load(pageModel);
			//记录对应的主播和已经使用过的索引值
			List<Integer> ids = new ArrayList<>();
			ids.add(0);
			ids.add(1);
			ids.add(2);
			ids.add(3);
			List<Long> anchorids = new ArrayList<>();
			for (AppPayVipUserAnchorEntity info : infos) {
				ids.remove(info.getMsg_index());
				anchorids.add(info.getAnchorid());
			}
			
			//进行选择对应的主播
			long anchorid = 0;
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.eq("state", 1));
			pageModel.addQuery(Restrictions.in("online", 3));
			pageModel.addQuery(Restrictions.eq("contact_on", 1));
			pageModel.addQuery(Restrictions.isNotNull("contact_text"));
			if(Tools.isNotNull(anchorids)) {
				pageModel.addQuery(Restrictions.notin("userid", anchorids));
			}
			pageModel.addQuery(Restrictions.sql("flag=0 order by rand() limit 1"));
			List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
			if(Tools.isNotNull(anchors) && anchors.size() > 0) {
				anchorid = anchors.get(0).getUserid();
			} else {
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("state", 1));
				pageModel.addQuery(Restrictions.eq("contact_on", 1));
				pageModel.addQuery(Restrictions.isNotNull("contact_text"));
				if(Tools.isNotNull(anchorids)) {
					pageModel.addQuery(Restrictions.notin("userid", anchorids));
				}
				pageModel.addQuery(Restrictions.sql("flag=4 order by rand() limit 1"));
				anchors = anchorOnlineContract.load(pageModel);
				if(Tools.isNotNull(anchors) && anchors.size() > 0) {
					anchorid = anchors.get(0).getUserid();
				}
			}
			
			if(anchorid > 0) {
				//确定发送消息的索引
				Collections.shuffle(ids);
				int index = ids.get(0);
				String sendMsg = msg.get(index);
				if(Tools.isNotNull(sendMsg)) {
					//发送对应的联系方式消息
					neteaseAgent.pushOneMessage(anchorid, userid, sendMsg, true);
					//记录存储发送记录
					AppPayVipUserAnchorEntity sta = new AppPayVipUserAnchorEntity();
					sta.setUserid(userid);
					sta.setAnchorid(anchorid);
					sta.setMsg_index(index);
					sta.setCreate_time(new Date());
					appPayVipUserAnchorContract.insert(sta);
				}
			}
			
			//处理发送满足三条消息进制终止发送的业务
			if((Tools.isNotNull(infos) && infos.size() >= 2) || anchorid == 0) {
				item.setState(1);
				item.setEnd_time(new Date());
				appPayVipUserContract.update(item);
			}
		}
	}

}
