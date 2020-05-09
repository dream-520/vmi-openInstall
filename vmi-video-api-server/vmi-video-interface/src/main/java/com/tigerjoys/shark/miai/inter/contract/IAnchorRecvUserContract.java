package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AnchorRecvUserEntity;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  主播接听用户[t_anchor_recv_user]表 接口类
 * @author shiming
 * @Date 2019-07-05 16:00:33
 *
 */
public interface IAnchorRecvUserContract extends BaseContract<AnchorRecvUserEntity> {
	
	/**
	 * 核查是否设置勿扰
	 * @return
	 * @throws Exception
	 */
	public boolean checkDisturb(long anchorId,long userid) throws Exception;
	
	/**
	 * 核查是否隐身
	 * @param anchorId
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean checkInvisibility(long anchorId, long userid) throws Exception;
	
	/**
	 * 查询主播对应的 勿扰和 隐身 列表
	 * @param anchorId
	 * @param type   1  勿扰  2 隐身 
	 * @return
	 * @throws Exception
	 */
	public List<AnchorRecvUserEntity> getRecvUserByType(long anchorId, int type) throws Exception;
}
