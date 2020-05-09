package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_anchor_online]表 接口类
 * @author shiming
 * @Date 2018-09-13 16:06:53
 *
 */
public interface IAnchorOnlineContract extends BaseContract<AnchorOnlineEntity> {
	
	//更新主播的在线状态信息
	public int updateAnchorInfo(long userid, String nickname, String photo) throws Exception;
	
	//更新主播的在线状态信息
	public int updateState(long userid, int state,boolean updateTimeFlag ) throws Exception;
	
	//获取主播在线状态信息
	public int onlineState(long userid) throws Exception;
	
	//增加用户拔打本大V总计
	public int addDialNum(long userid,int avType) throws Exception;
	
	//增加大V接听总计
	public int addRecvNum(long userid,int avType) throws Exception;
	
	//增加用户评论大V总计
	public int addEvaluationNum(long userid,int avType) throws Exception;
	
	//关注主播列表数据
	public List<AnchorOnlineEntity> attentionAnchors(String userid, String update_time) throws Exception;
	
	//关注主播列表数据
	public List<AnchorOnlineEntity> newAttentionAnchors(String userid, int start, String update_time) throws Exception;
	
	//关注主播列表数据
	public List<AnchorOnlineEntity> newAttentionAnchorsTwo(String userid, int start, String update_time) throws Exception;
	
	/**
	 * 根据用户ID 获取主播信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public long getAnchorOnlineId(long userid) throws Exception;
	
	/**
	 * 根据用户ID 获取主播信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public AnchorOnlineEntity getAnchorOnlineEntity(long userid) throws Exception;
}
