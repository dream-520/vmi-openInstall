package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserRightCheckedLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserRightCheckedLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserRightCheckedLogMapper;

/**
 * 数据库中  用户付费查看用户信息记录[t_user_right_checked_log]表 接口实现类
 * @author mouzhanpeng
 * @Date 2018-06-05 15:08:29
 *
 */
@Repository
public class UserRightCheckedLogContractImpl extends AbstractBaseContract<UserRightCheckedLogEntity , UserRightCheckedLogMapper> implements IUserRightCheckedLogContract {

  public UserRightCheckedLogEntity lockByUserId(long userId, long otherId, int type){
    return mapper.lockByUserId(userId,otherId,type);
  }
}
