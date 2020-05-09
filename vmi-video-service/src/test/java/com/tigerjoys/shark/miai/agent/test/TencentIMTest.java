package com.tigerjoys.shark.miai.agent.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.shark.miai.common.util.DBHelper;
import org.shark.miai.common.util.QRCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.ITencentIMAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.shark.miai.service.IVChatVideoService;
import com.tigerjoys.shark.miai.token.TokenService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class TencentIMTest extends BaseTestConfig{
private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ITencentIMAgent tencentIMAgent;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Autowired
	private IVChatVideoService vChatVideoService;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void testCreateUser(){
		Map<String, String> contentType = new HashMap<>();
		contentType.put("Identifier", "Web_trtc_01");
		contentType.put("Nick", "test123");
		contentType.put("FaceUrl", "http://www.qq.com");
		try {
		JSONObject json= 	tencentIMAgent.createUser(JsonHelper.toJson(contentType));
		System.out.println(json.toJSONString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testToken() {
		TokenService.instance().addTokenToRedis("aaaaaa", "123456");

	}
	@Test
	public void testRoomId() {
		long orderId = IdGenerater.generateId();
		System.out.println(orderId);
		
		System.out.println(System.currentTimeMillis());
	}
	
	
	@Test
	public void testVChatRoomId() throws Exception{
		
		VchatRoomEntity entity = new VchatRoomEntity();
		//entity.setId(2L);
		entity.setOrderId(155657L);
		entity.setCreate_time(new Date());
		vchatRoomContract.insert(entity);
	}
	
	private long orderId =12345L;
	@Test
	public void testDialing() throws Exception {
		ActionResult reslut= vChatVideoService.dialing(36775801156337920L, 67244811045896448L);
		System.err.println("拨打:"+JsonHelper.toJson(reslut));
	}
	
	@Test
	public void testAnswer() throws Exception {
		ActionResult reslut= vChatVideoService.communicateRes(67244811045896448L, orderId, 36775801156337920L, VChatVideoStatusEnum.answer.getCode());
		System.err.println("接听:"+JsonHelper.toJson(reslut));
	}
	
	@Test
	public void testEnterRoom() throws Exception {
		ActionResult reslut= vChatVideoService.communicateRes(67244811045896448L, orderId, 36775801156337920L, VChatVideoStatusEnum.enter_room.getCode());
		System.err.println("进入房间:"+JsonHelper.toJson(reslut));
		reslut= vChatVideoService.communicateRes(36775801156337920L, orderId, 36775801156337920L, VChatVideoStatusEnum.enter_room.getCode());
		System.err.println("进入房间:"+JsonHelper.toJson(reslut));
	}
	
	@Test
	public void testExitRoom() throws Exception {
		ActionResult reslut= vChatVideoService.communicateRes(67244811045896448L, orderId, 36775801156337920L, VChatVideoStatusEnum.enter_room.getCode());
		System.err.println("进入房间:"+JsonHelper.toJson(reslut));
	}
	
	
	@Test
	public void testSystem() throws Exception {
		String payFlag = cacheRedis.hget(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX+"123456", "payFlag");
		System.err.println(payFlag);
		boolean falg = cacheRedis.exists(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX+"123456");
		System.err.println(falg);
	}
	
	
	@Test
	public void testQRCode() throws Exception {
		String qrCode = QRCodeUtil.getBase64Str("http://192.168.20.31:20100/vmi-video-service/web/shareDownload/101584");
		System.err.println(qrCode);
	}
	
	
	@Test
	public void testMD5() throws Exception {
		//String ss = millisToStringShort(new Date().getTime()-Tools.getDateTime("2019-06-28 16:03:45", "yyyy-MM-dd HH:mm:ss").getTime());
		String ss = millisToStringShort(5000);
		System.out.println(ss);
	}
	
	
	public static String millisToStringShort(long millis) {
		StringBuilder strBuilder = new StringBuilder();
		long temp = millis;
		long hper = 60 * 60 * 1000;
		long mper = 60 * 1000;
		long sper = 1000;
		if (temp / hper > 0) {
			if((temp / hper)<10){
				strBuilder.append("0");
			}
			strBuilder.append(temp / hper).append(":");
		}
		temp = temp % hper;

		if (temp / mper > 0) {
			if((temp / mper)<10){
				strBuilder.append("0");
			}
			strBuilder.append(temp / mper).append(":");
		}else{
			strBuilder.append("00").append(":");
		}
		temp = temp % mper;
		if (temp / sper > 0) {
			if((temp / sper)<10){
				strBuilder.append("0");
			}
			strBuilder.append(temp / sper).append("");
		}
		return strBuilder.toString();
	}
	
	
	
	@Test
	public void testanchorAARecvUserTimer() throws Exception {
		ArrayList<String> list = new ArrayList<>();
		int days =84;
			for(int i=days;i>=0;i--){
				String day = Tools.getFormatDate(new Date(Tools.getDayTime(i)),"yyyy-MM-dd");
				String sql = "UPDATE t_vchat_room r SET user_flag=1 WHERE DATE(r.create_time)='"+day+"' AND  userid IN (SELECT id FROM t_user  u WHERE DATE(u.create_time)='"+day+"');";
				System.out.println("----------"+day);
				//list.add(getSQLResultDouble(sql));
				list.add(sql);
				
			}
			
			for(String re:list){
				System.out.println(re);
			}
	}
	
	
	
	
	
	
	
	
	private String getSQLResultDouble(String sql) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		double sum = 0;
		String str ="";
		try {
			connection = DBHelper.getConnection(sqlSession);
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) {
				String date = result.getString(1);
				int  rows = result.getInt(2);
				str = date+"  "+rows;
				
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		} finally {
			DBHelper.closeDBA(result, statement, connection);
		}
		return str;
	}
	
	

	
}
