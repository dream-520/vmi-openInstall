package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorRecvUserAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.AnchorRecvUserEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorRecvUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorRecvUserEntity;

@Service
public class AnchorRecvUserAgentImpl implements IAnchorRecvUserAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAnchorRecvUserContract anchorRecvUserContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Override
	public void checkAnchorRecvUser(long userid, long otherid, int type) {
		try {
			UserBO bo1 = userAgent.findById(userid);
			UserBO bo2 = userAgent.findById(otherid);
			if(Tools.isNotNull(bo1) && Tools.isNotNull(bo2)) {
				//分析对应的主播和用户
				UserBO user = null;
				UserBO waiter = null;
				if(bo1.isWaiter()) {
					waiter = bo1;
				} else {
					user = bo1;
				}
				if(bo2.isWaiter()) {
					waiter = bo2;
				} else {
					user = bo2;
				}
				
				//进行入库分析处理
				if(Tools.isNotNull(user) && Tools.isNotNull(waiter)) {
					PageModel pageModel = PageModel.getPageModel();
					pageModel.addQuery(Restrictions.eq("anchorId", waiter.getUserid()));
					pageModel.addQuery(Restrictions.eq("userid", user.getUserid()));
					List<AnchorRecvUserEntity> list = anchorRecvUserContract.load(pageModel);
					if(Tools.isNotNull(list) && list.size() > 0) {
						AnchorRecvUserEntity recv = list.get(0);
						if(Tools.isNotNull(recv)) {
							Map<String, Object> updateStatement = new HashMap<String, Object>();
							if(type == AnchorRecvUserEnum.msg.getCode() && recv.getMsg() != 1) {
								updateStatement.put("msg", 1);
							} else if(type == AnchorRecvUserEnum.gift.getCode() && recv.getGift() != 1){
								updateStatement.put("gift", 1);
							}
							if(updateStatement.size() > 0)
								anchorRecvUserContract.updateById(updateStatement , recv.getId());
						}
					} else {
						AnchorRecvUserEntity entity = new AnchorRecvUserEntity();
						entity.setAnchorId(waiter.getUserid());
						entity.setUserid(user.getUserid());
						entity.setCreate_time(new Date());
						if(type == AnchorRecvUserEnum.msg.getCode()) {
							entity.setMsg(1);
						} else if(type == AnchorRecvUserEnum.gift.getCode()){
							entity.setGift(1);
						}
						anchorRecvUserContract.insert(entity);
					}
				}
			}
		} catch (Exception e) {
			
		}
	}

	@Override
	public boolean checkInvisibility(long userid, long anchorid) {
		boolean invisibility = false;
		try {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("anchorId", anchorid));
			List<AnchorRecvUserEntity> list = anchorRecvUserContract.load(pageModel);
			if(Tools.isNotNull(list) && list.size() > 0) {
				AnchorRecvUserEntity recv = list.get(0);
				if(Tools.isNotNull(recv) && recv.getInvisibility() > 0)
					invisibility = true;
			}
		} catch (Exception e) {
			
		}
		return invisibility;
	}

	@Override
	public List<Long> userInvisibilityAnchor(long anchorid) {
		List<Long> userids = null;
		try {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("anchorId", anchorid));
			pageModel.addQuery(Restrictions.eq("invisibility", 1));
			List<AnchorRecvUserEntity> list = anchorRecvUserContract.load(pageModel);
			if(Tools.isNotNull(list) && list.size() > 0) {
				userids = new ArrayList<Long>();
				for (AnchorRecvUserEntity recv : list) {
					userids.add(recv.getUserid());
				}
			}
		} catch (Exception e) {
			
		}
		return userids;
	}

}
