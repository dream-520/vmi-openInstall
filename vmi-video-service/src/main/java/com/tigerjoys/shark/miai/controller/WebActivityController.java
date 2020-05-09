package com.tigerjoys.shark.miai.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.shark.miai.common.util.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.DoubleElevenActivityDto;
import com.tigerjoys.shark.miai.utils.ServiceHelper;


@Controller
@FilterHeader
@RequestMapping(value = "/web/activity")
public class WebActivityController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis activityRedis;
	
	@RequestMapping(value="/anniversary",produces=Produce.TEXT_HTML)
	public String getAnniversary() throws Exception{
		return "activity/anniversary/FoundingPage";
	}
	
	/**
	 * 浓情中秋
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/festival",produces=Produce.TEXT_HTML)
	public String getFestival() throws Exception{
		return "activity/goddess/festival";
	}
	
	/**
	 * 浓情中秋
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/chargeSendGift",produces=Produce.TEXT_HTML)
	public String getChargeSendGift() throws Exception{
		return "activity/goddess/firstChange";
	}
	
	/**
	 * 冲刺11.11
	 * @return - String
	 * @throws Exception
	 */
	@FilterHeader
	@RequestMapping(value = "/doubleEleven", produces = Produce.TEXT_HTML)
	public String doubleEleven(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("vchatList", getVchatList());
		model.addAttribute("giftList", getGiftList());
		return "activity/doubleEleven";
	}

	/**
	 * 统计库session
	 */
	@Autowired
	@Qualifier("sqlSession")
    private SqlSession sqlSession;
	
	@SuppressWarnings("unchecked")
	public List<DoubleElevenActivityDto> getVchatList() throws Exception {
		String key = AgentRedisCacheConst.DOUBLE_ELEVEN_VCHAT;
		List<DoubleElevenActivityDto> vchatList1 = (List<DoubleElevenActivityDto>) activityRedis.getObject(key);
		if (vchatList1 != null) {
			return vchatList1;
		}
		List<DoubleElevenActivityDto> vchatList = new ArrayList<DoubleElevenActivityDto>();
		Connection conn = DBHelper.getConnection(sqlSession);
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT anchorid , SUM(vchat_duration) as vchatCount FROM t_vchat_room where create_time>='2019-11-03 00:00:00' and create_time<'2019-11-12 00:00:00' and free_vchart_falg IN(0,2) GROUP BY anchorid ORDER BY vchatCount DESC LIMIT 10 ";
			logger.info(sql);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			int number = 1;
			while(rs.next()){
				long anchorid = rs.getLong("anchorid");
				UserBO user = userAgent.findById(anchorid);
				if (user!=null) {
					DoubleElevenActivityDto dto = new DoubleElevenActivityDto();
					dto.setNumber(number++);
					dto.setUserid(user.getUserid());
					dto.setPhoto(ServiceHelper.getUserSmallPhoto(user.getPhoto()));
					dto.setNickname(user.getNickname());
					dto.setCount(rs.getInt("vchatCount")*9);
					vchatList.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DBHelper.closeDBA(rs, st, conn);
		}
		activityRedis.setObject(key, 1800, vchatList);
		return vchatList;
	}

	@SuppressWarnings("unchecked")
	public List<DoubleElevenActivityDto> getGiftList() throws Exception {
		String key = AgentRedisCacheConst.DOUBLE_ELEVEN_GIFT;
		List<DoubleElevenActivityDto> giftList1 = (List<DoubleElevenActivityDto>) activityRedis.getObject(key);
		if (giftList1 != null) {
			return giftList1;
		}
		List<DoubleElevenActivityDto> giftList = new ArrayList<DoubleElevenActivityDto>();
		Connection conn = DBHelper.getConnection(sqlSession);
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT other_id , SUM(diamond) as giftCount from t_user_chat_gift_log where create_time>='2019-11-03 00:00:00' and create_time<'2019-11-12 00:00:00' GROUP BY other_id ORDER BY giftCount DESC LIMIT 10 ";
			logger.info(sql);
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			int number = 1;
			while(rs.next()){
				long anchorid = rs.getLong("other_id");
				UserBO user = userAgent.findById(anchorid);
				if (user!=null) {
					DoubleElevenActivityDto dto = new DoubleElevenActivityDto();
					dto.setNumber(number++);
					dto.setUserid(user.getUserid());
					dto.setPhoto(ServiceHelper.getUserSmallPhoto(user.getPhoto()));
					dto.setNickname(user.getNickname());
					dto.setCount(rs.getInt("giftCount")*9);
					giftList.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			DBHelper.closeDBA(rs, st, conn);
		}
		activityRedis.setObject(key, 1800, giftList);
		return giftList;
	}
	
	
}
