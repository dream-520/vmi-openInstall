package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.mapper.AnchorOnlineMapper;

/**
 * 数据库中  [t_anchor_online]表 接口实现类
 * @author shiming
 * @Date 2018-09-13 16:06:53
 *
 */
@Repository
public class AnchorOnlineContractImpl extends AbstractBaseContract<AnchorOnlineEntity , AnchorOnlineMapper> implements IAnchorOnlineContract {

	@Override
	public int updateAnchorInfo(long userid, String nickname, String photo) throws Exception {
		Map<String, Object> updateStatement = new HashMap();
		if(Tools.isNotNull(nickname) && nickname.length() > 0) {
			updateStatement.put("nickname", nickname);
		}
		if(Tools.isNotNull(photo) && photo.length() > 0) {
			updateStatement.put("photo", photo);
		}
		int count = 0;
		if(updateStatement.size() > 0) {
			updateStatement.put("update_time", new Date());
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			count = mapper.updateByCondition(updateStatement, pageModel);
		}
		return count;
	}
	
	
	@Override
	public int updateState(long userid, int state ,boolean updateTimeFlag) throws Exception {
		String updateTime = "" ;
		if(updateTimeFlag){
			updateTime =",update_time=now()";
		}
		return mapper.updateByStatement("online="+state+updateTime, "id="+getAnchorOnlineId(userid));
	}

	@Override
	public int onlineState(long userid) throws Exception {
		AnchorOnlineEntity anchor = mapper.findByProperty("userid", userid);
		if(Tools.isNotNull(anchor)) {
			return anchor.getOnline();
		}
		return 0;
	}

	@Override
	public int addDialNum(long userid,int avType) throws Exception {
		if(avType == 1){
			return mapper.updateByStatement("audio_dial_num=audio_dial_num+1", "id="+getAnchorOnlineId(userid));
		}else{
			return mapper.updateByStatement("dial_num=dial_num+1", "id="+getAnchorOnlineId(userid));
		}
		
	}

	@Override
	public int addRecvNum(long userid,int avType) throws Exception {
		if(avType == 1){
			return mapper.updateByStatement("audio_recv_num=audio_recv_num+1", "id="+getAnchorOnlineId(userid));
		}else{
			return mapper.updateByStatement("recv_num=recv_num+1", "id="+getAnchorOnlineId(userid));
		}
		
	}

	@Override
	public int addEvaluationNum(long userid,int avType) throws Exception {
		if(avType == 1){
			return mapper.updateByStatement("audio_evaluation_num=audio_evaluation_num+1", "id="+getAnchorOnlineId(userid));
		}else{
			return mapper.updateByStatement("evaluation_num=evaluation_num+1", "id="+getAnchorOnlineId(userid));
		}
		
	}

	
	public long getAnchorOnlineId(long userid) throws Exception{
		long id=0;
		AnchorOnlineEntity entity =mapper.findByProperty("userid", userid);
		if(Tools.isNotNull(entity)){
			id = entity.getId();
		}
		return id;
	}

	
	
	@Override
	public AnchorOnlineEntity getAnchorOnlineEntity(long userid) throws Exception {
		AnchorOnlineEntity entity =mapper.findByProperty("userid", userid);
		return entity;
	}


	@Override
	public List<AnchorOnlineEntity> attentionAnchors(String userid, String update_time) throws Exception {
		return mapper.attentionAnchors(userid, update_time);
	}


	@Override
	public List<AnchorOnlineEntity> newAttentionAnchors(String userid, int start, String update_time) throws Exception {
		return mapper.newAttentionAnchors(userid, start, update_time);
	}


	@Override
	public List<AnchorOnlineEntity> newAttentionAnchorsTwo(String userid, int start, String update_time) throws Exception {
		return mapper.newAttentionAnchorsTwo(userid, start, update_time);
	}
	
}
