package com.tigerjoys.shark.miai.agent;

import java.util.List;

import org.javatuples.Pair;

import com.tigerjoys.shark.miai.inter.entity.CouponEntity;

/**
 * 付费约代金券
 * @author yangjunming
 *
 */
public interface ICouponAgent {
	
	/**
	 * 根据ID查找代金券对象
	 * @param id - ID
	 * @return PaidAppointCouponEntity
	 * @throws Exception
	 */
	public CouponEntity findById(long id) throws Exception;
	
	/**
	 * 根据用户ID查找可用代金券列表对象
	 * @param userId - 用户ID
	 * @param page - 当前页
	 * @param pageSize - 每页记录数
	 * @return PaidAppointCouponEntity
	 * @throws Exception
	 */
	public List<CouponEntity> findByUserId(long userId) throws Exception;
	
	/**
	 * 根据用户ID查找满多少可用代金券列表对象
	 * @param userId - 用户ID
	 * @param type - 代金券类型   参考CouponTypeEnum类
	 * @param lastAmount - 满多少可用
	 * @return PaidAppointCouponEntity
	 * @throws Exception
	 */
	public List<CouponEntity> findByUserId(long userId,int type, long lastAmount) throws Exception;
	

	/**
	 * 根据用户ID查找满多少可用代金券列表对象
	 * @param userId - 用户ID
	 * @param type - 代金券类型   参考CouponTypeEnum类
	 * @param couponId - 代金券Id
	 * @param amount - 满多少可用
	 * @return Pair<Boolean , CouponEntity> 第一个参数检查优惠券是否可以，第二个参数返回优惠券实体
	 * @throws Exception
	 */
	public Pair<Boolean , CouponEntity> checkCoupon(long userId, long couponId,int type,long amount) throws Exception;
	
	/**
	 * 新增代金券
	 * @param t - PaidAppointCouponEntity
	 * @throws Exception
	 */
	public void insert(CouponEntity t) throws Exception;
	
	/**
	 * 批量新增代金券
	 * @param list - List<CouponEntity>
	 * @throws Exception
	 */
	public void insertAll(List<CouponEntity> list) throws Exception;
	
	/**
	 * 使用代金券
	 * @param id
	 * @param orderId
	 * @return  0  使用失败  1为使用成功
	 * @throws Exception
	 */
	public int useCoupon(long id,long orderId) throws Exception;
	
	/**
	 * 恢复代金券为可用状态
	 * @param id - 代金券ID
	 * @return int
	 * @throws Exception
	 */
	public void recoverCoupon(long id) throws Exception;
	
	/**
	 * 删除代金券
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteCoupon(long id) throws Exception;
	
	/**
	 * 添加用户代金券
	 * @param userId	用户代金券
	 * @param groupid		代金券来源组 参考 CouponGroupEnum
	 * @return
	 * @throws Exception
	 */
	public void addUserCoupon(long userId, int groupid) throws Exception;
	
	
	/**
	 * 查询代金券状态
	 * @param userId	用户id
	 * @param groupid	代金券来源组 参考 CouponGroupEnum
	 * @param status	状态 参考  CouponStatusEnum
	 * @return
	 * @throws Exception
	 */
	public long queryUserCouponNum(long userId, Integer groupid, Integer status) throws Exception ;
}
