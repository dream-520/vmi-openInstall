package com.tigerjoys.shark.miai.agent.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.shark.miai.common.util.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IPayUserAgent;
import com.tigerjoys.shark.miai.inter.contract.IPayUserRecordContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserLoginLogContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.PayUserRecordEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;
import com.tigerjoys.shark.miai.inter.entity.UserLoginLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;

/**
 * 全局广播服务实现类
 * @author lipeng
 *
 */
@Service
public class PayUserAgentImpl implements IPayUserAgent {

	@Autowired
	private IPayUserRecordContract payUserRecordContract;
	
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IUserLoginLogContract userLoginLogContract;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 统计库session
	 */
	@Autowired
	@Qualifier("sqlSession")
    private SqlSession sqlSession;
	
	@Override
	public void updatePay(UserPayActionEntity entity) throws Exception {
		PayUserRecordEntity  record =  payUserRecordContract.findByProperty("userid", entity.getUser_id());
		if (record!=null) {
			PayUserRecordEntity pay = new PayUserRecordEntity();
			pay.setId(record.getId());
			pay.setLast_pay_time(entity.getCreate_time());
			pay.setPay_times(record.getPay_times()+1);
			pay.setPay_money(record.getPay_money()+entity.getMoney());
			payUserRecordContract.update(pay);
		}else{
			Date currDate = new Date();
			UserEntity user = userContract.findById(entity.getUser_id());
			if (user.getCreate_time().getTime()>Tools.getDateTime("2018-09-01 00:00:00").getTime()) {
				if (!idList.contains(entity.getUser_id())) {
					PayUserRecordEntity pay = new PayUserRecordEntity();
					pay.setCreate_time(currDate);
					pay.setUpdate_time(currDate);
					pay.setUserid(entity.getUser_id());
					pay.setReg_time(user.getCreate_time());
					pay.setUser_level(user.getDegreeid());
					pay.setFirst_pay_time(entity.getCreate_time());
					pay.setLast_pay_time(entity.getCreate_time());
					if (Tools.isNotNull(getLastChatTime(entity.getUser_id()))) {
						pay.setLast_chat_time(getLastChatTime(entity.getUser_id()));
						pay.setChat_days(1);
					}else{
						pay.setChat_days(0);
					}
					if (Tools.isNotNull(getlastLoginTime(entity.getUser_id()))) {
						pay.setLast_login_time(getlastLoginTime(entity.getUser_id()));
					}
					pay.setPay_times(1);
					pay.setPay_money(entity.getMoney());
					pay.setVip(0);
					pay.setChannel(entity.getApp_channel());
					payUserRecordContract.insert(pay);
				}
			}
		}
	}

	/**
	 * 获得最后登录时间
	 * @param user_id
	 * @return
	 * @throws Exception 
	 */
	private Date getlastLoginTime(Long user_id) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addLimitField(0, 1);
		pageModel.addQuery(Restrictions.eq("user_id", user_id));
		pageModel.desc("create_time");
		List<UserLoginLogEntity> loginList = userLoginLogContract.load(pageModel);
		if (Tools.isNotNull(loginList)) {
			return loginList.get(0).getCreate_time();
		}
		return null;
	}

	/**
	 * 获得最后聊天时间
	 * @param user_id
	 * @return
	 * @throws Exception 
	 */
	private Date getLastChatTime(Long user_id) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addLimitField(0, 1);
		pageModel.addQuery(Restrictions.gt("vchat_duration", 0));
		pageModel.addQuery(Restrictions.eq("av_type", 2));
		pageModel.addQuery(Restrictions.eq("userid", user_id));
		pageModel.desc("create_time");
		List<VchatRoomEntity> chatList  = vchatRoomContract.load(pageModel);
		if (Tools.isNotNull(chatList)) {
			return chatList.get(0).getCreate_time();
		}
		return null;
	}

	@Override
	public void updateChat(VchatRoomEntity entity) throws Exception {
		PayUserRecordEntity  record =  payUserRecordContract.findByProperty("userid", entity.getUserid());
		if (record!=null) {
			PayUserRecordEntity pay = new PayUserRecordEntity();
			pay.setId(record.getId());
			pay.setLast_chat_time(entity.getCreate_time());
			pay.setChat_days(getVchatDays(entity.getUserid()));
			payUserRecordContract.update(pay);
		}
	}

	/**
	 * 获得聊天天数
	 * @param channel
	 * @param start
	 * @param end
	 * @return
	 */
	private int getVchatDays(long userid) {
		Connection conn = DBHelper.getConnection(sqlSession);
		PreparedStatement st = null;
		ResultSet rs = null;
		int count=1;
		try {
			String sql = "SELECT COUNT(DISTINCT DATE(create_time)) as count FROM t_vchat_room  where vchat_duration>0 AND userid = "+ userid;
			logger.info(sql);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()){
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DBHelper.closeDBA(rs, st, conn);
		}
		return count;
	}
	
	public static List<Long> idList = new ArrayList<Long>(); 
	static{
		idList.add(130114190751891712L);
		idList.add(32470659676307712L);
		idList.add(145896550965510400L);
		idList.add(132100703287050496L);
		idList.add(142626186703470848L);
		idList.add(32396088795267328L);
		idList.add(91682454307406080L);
		idList.add(78111993137004800L);
	}
	
	
}
