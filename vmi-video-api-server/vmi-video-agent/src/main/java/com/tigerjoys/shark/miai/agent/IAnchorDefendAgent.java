package com.tigerjoys.shark.miai.agent;

import java.util.List;

import com.tigerjoys.shark.miai.agent.dto.AnchorDefendItemDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendPayDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendTopDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendUserItemDto;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;

/**
 * 处理守护相关的接口
 * @author shiming
 *
 */
public interface IAnchorDefendAgent {

	/**
	 * 购买或者延期守护时进行相关操作
	 * @param orderid
	 */
	public void buyAnchorDefend(long orderid);
	
	/**
	 * 检测对应的用户是否是对应主播的守护   暂时在个人主页使用
	 * @param userid
	 * @param anchorid
	 * @return
	 */
	public boolean userIsAnchorDefend(long userid, long anchorid);
	
	/**
	 * 获取主播前四个守护数据    暂时在个人主页上守护团上使用
	 * @param anchorid
	 * @return
	 */
	public List<AnchorDefendTopDto> anchorTop4Defend(long anchorid);
	
	
	/**
	 * 获取给定用户对给定主播的守护列表   暂时在购买守护页面使用
	 * @param userid
	 * @param anchorid
	 */
	public List<AnchorDefendPayDto> expiryAnchorDefend(long userid, long anchorid);
	
	/**
	 * 获取给定用户所有的守护
	 * @param userid
	 */
	public List<AnchorDefendUserItemDto> getUserAnchorDefend(long userid);
	
	/**
	 * 获取给定主播的所有守护
	 * @param anchorid
	 */
	public List<AnchorDefendItemDto> getAnchorDefends(long anchorid);
	
	/**
	 * 获取当前用户对主播的守护实体
	 * @param userid
	 * @param anchorid
	 * @return
	 */
	public GuardVipCategoryEntity getCurrentAnchorDefendByUser(long userid, long anchorid);
	
}
