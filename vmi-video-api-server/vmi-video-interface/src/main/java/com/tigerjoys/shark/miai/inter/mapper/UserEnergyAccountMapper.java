package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserEnergyAccountEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 数据库  用户能量账户[t_user_energy_account]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-08-17 14:31:33
 *
 */
@Producer(entityType=UserEnergyAccountEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserEnergyAccountMapper extends BaseMapper<UserEnergyAccountEntity> {

  /**
   * 根据用户ID加锁查询获得对象
   * @param userId - Long
   * @return UserEnergyAccountEntity
   */
  @Select("select * from t_user_energy_account where user_id = #{userId} for update")
  public abstract UserEnergyAccountEntity lockByUserId(long userId);
}