package com.tigerjoys.shark.miai.agent;

import java.util.List;

import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsFakeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftStatisticsFakeEntity;

/**
 * 聊天礼物服务代理接口
 * @author lipeng
 *
 */
public interface IUserGiftAgent {
	
	/**
	 * 添加全局广播信息
	 * @param userid
	 * @param anchorid
	 * @param money
	 * @param giftId
	 * @throws Exception
	 */
	public void insertOrUpdate(Long userid, Long anchorid, Integer diamond)throws Exception;

	/**
	 * 创建礼物贡献榜假数据
	 * @param anchorid
	 * @param gift_contribution
	 * @param gift_count
	 * @return
	 * @throws Exception 
	 */
	public List<UserChatGiftStatisticsFakeEntity> creatGiftFakeDate(long anchorid, Integer gift_contribution, Integer gift_count) throws Exception;

	/**
	 * 创建主播个人主页亲密榜假数据
	 * @param anchorid
	 * @param total_money
	 * @return
	 * @throws Exception
	 */
	public List<AnchorIntimateRankingsFakeEntity> creatIntimateRankingsFakeDate(long anchorid, Integer total_money) throws Exception;


}
