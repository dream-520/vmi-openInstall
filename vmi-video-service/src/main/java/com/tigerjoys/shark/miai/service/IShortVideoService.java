package com.tigerjoys.shark.miai.service;

import org.springframework.web.multipart.MultipartFile;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 短视频相关接口
 * @author yangjunming
 *
 */
public interface IShortVideoService {
	
	/**
	 * 短视频首页
	 * @param stamp      时间戳
	 * @return
	 * @throws Exception
	 */
	public ActionResult videoHome(String stamp) throws Exception;
	
	/**
	 * 主播短视频列表
	 * @param stamp      时间戳
	 * @return
	 * @throws Exception
	 */
	public ActionResult anchorVideoList(long userId,String stamp) throws Exception;
	
	/**
	 * 短视频上传页
	 * @param video
	 * @return
	 * @throws Exception
	 */
	public ActionResult videoUpload(long userId,MultipartFile video) throws Exception;

	/**
	 * 短视频详情页
	 * @param stamp      时间戳
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public ActionResult videoDesc(Long videoId,long userid) throws Exception;
	
	/**
	 * 短视频删除
	 * @param stamp      时间戳
	 * @return
	 * @throws Exception
	 */
	public ActionResult videoDelete(Long videoId) throws Exception;
	
	/**
	 * 短视频点赞
	 * @param videoId
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public ActionResult videoPraise(Long videoId,long userid) throws Exception;
	
	/**
	 * 简版接口
	 * @param userid   当前用户
	 * @param tag    0 首页 1 视频  2 个人主页 
	 * @param type   类型
	 * @param stamp   分页戳
	 * @param anchorId  主播ID
	 * @return
	 * @throws Exception
	 */
	public ActionResult anchorSlideList(Long userid,Integer tag,Integer type,String stamp,Long anchorId) throws Exception;
	
	/**
	 * V密
	 * @param userid   当前用户
	 * @param tag    0 客户端首页 1 视聊  2 个人主页 3主播端首页 
	 * @param type   类型
	 * @param stamp   分页戳
	 * @param anchorId  主播ID
	 * @return
	 * @throws Exception
	 */
	public ActionResult anchorNewSlideList(Long userid,Integer tag,Integer type,String stamp,Long anchorId) throws Exception;


}
