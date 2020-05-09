package com.tigerjoys.shark.miai.service;

import java.util.List;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.AnchorToUserEvaluationDto;

public interface IVChatVideoYXService {
	
	/**
	 * 获对视频通话对方信息
	 * @param userId    调用方
	 * @param toUserId  被叫方ID
	 * @param sponsor   是否发起方 1-是 0-否
	 * @param avType    1 为音频  0 ，2 为视频
	 * @return
	 * @throws Exception
	 */
	public ActionResult getDialing(long userId, Long toUserId,Integer sponsor,int avType) throws Exception;

	
	/**
	 * 新接口
	 * 视频通话前检查，看是否可以拨打 
	 * @param userId    调用方
	 * @param toUserId  被叫方ID
	 * @param type    1为 提示2分钟框  0 为不提示框
	 * @param avType    1 为音频  0 ，2 为视频
	 * @return
	 * @throws Exception
	 */
	public ActionResult dialingNewCheck(long userId, Long toUserId,long type,int avType) throws Exception;
	
	
	
	/**
	 * 主播视频通话结束后评价列表
	 * @param serialNum    订单号
	 * @return
	 * @throws Exception
	 */
	public ActionResult anchorEvaluationList(long serialNum) throws Exception;
	
	/**
	 * 用户的视频通话结束后评价
	 * @param otherId    对方ID 
	 * @param typeList  评价列表
	 * @return
	 * @throws Exception
	 */
	public ActionResult annchorToUserEvaluation(long userId,Long otherId,long serialNum,List<Long> typeList) throws Exception;
	
	
	/**
	 * 主播对用户评论列表
	 * @return
	 * @throws Exception
	 */
	public List<AnchorToUserEvaluationDto> annchorToUserEvaluationList() throws Exception;
	
	/**
	 * 用户对主播视频通话结束后评价
	 * @param userId
	 * @param anchorId
	 * @param serialNum   订单号
	 * @param evaluationIdList
	 * @return
	 * @throws Exception
	 */
	public ActionResult anchorEvaluation(long userId,Long anchorId,long serialNum,List<Long> evaluationIdList,String evaluationText) throws Exception;
	/**
	 * 新接口
	 * 扣费和结束通知 
	 * @param userId   本用户
	 * @param otherId  被叫方ID
	 * @param avType    1 为音频  0 ，2 为视频
	 * @param sponsor  是否发起方  1-是 0-否  
	 * @param serialNum  订单号
	 * @param state     1-正常通话，0-结束
	 * @param wyChatId  网易ID
	 * @return
	 * @throws Exception
	 */
	public ActionResult payOrderNew(long userId,Long otherId,int avType,int sponsor,Long serialNum,int state, Long wyChatId) throws Exception;
	
	/**
	 * 
	 * @param userid
	 * @throws Exception
	 */
	public ActionResult exitRoom(long oldUserId,Long oldOtherId,int avType) throws Exception;
	
	
	/**
	 * 用户主动退出
	 * @param otherId    对方ID 
	 * @param serialNum  订单号
	 * @return
	 * @throws Exception
	 */
	public ActionResult userExit(long userId,Long otherId,long serialNum,int avType) throws Exception;
	
	
	/**
	 * 我的通话
	 * @param userId
	 * @param anchorId
	 * @param evaluationIdList
	 * @return
	 * @throws Exception
	 */
	public ActionResult getMyPhone(long userId,String stamp) throws Exception;
	
	
	/**
	 * 根据用户ID，获的主用户ID，主播有子账号的情况
	 * @param userId
	 * @param anchorId
	 * @param evaluationIdList
	 * @return
	 * @throws Exception
	 */
	public Long getRealUserId(long userId) throws Exception;
	
	/**
	 * 设置用户播打标记
	 * @param userId
	 * @throws Exception
	 */
	public void setDialingFalg(long userId) throws Exception;
	
	/**
	 * 获取用户是否记录播打,true 为  记录播打  false 为不记录
	 * @param userId
	 * @throws Exception
	 */
	public boolean getDialingFalg(long userId) throws Exception;
	
	/**
	 * 关闭蒙层
	 * @param userId
	 */
	public void closeObscurationClient(long userId);
	
}
