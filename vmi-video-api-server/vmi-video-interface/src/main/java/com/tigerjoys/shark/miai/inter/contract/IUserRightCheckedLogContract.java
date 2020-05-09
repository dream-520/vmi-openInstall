package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserRightCheckedLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户付费查看用户信息记录[t_user_right_checked_log]表 接口类
 * @author mouzhanpeng
 * @Date 2018-06-05 15:08:29
 *
 */
public interface IUserRightCheckedLogContract extends BaseContract<UserRightCheckedLogEntity> {

  /**
   * 根据用户ID加锁查询获得对象
   * @param userId
   * @param otherId
   * @param type
   * @return
   */
  public UserRightCheckedLogEntity lockByUserId(long userId, long otherId, int type);
}
