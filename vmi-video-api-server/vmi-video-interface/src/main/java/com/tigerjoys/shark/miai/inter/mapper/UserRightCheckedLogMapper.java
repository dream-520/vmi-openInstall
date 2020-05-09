package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserRightCheckedLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 数据库  用户付费查看用户信息记录[t_user_right_checked_log]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2018-06-05 15:08:29
 *
 */
@Producer(entityType=UserRightCheckedLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserRightCheckedLogMapper extends BaseMapper<UserRightCheckedLogEntity> {

  /**
   * 根据用户ID加锁查询获得对象
   * @param userId - Long
   * @return UserRightCheckedLogEntity
   */
  @Select("select * from t_user_right_checked_log where user_id = #{userId} and other_id = #{otherId} and type = #{type} for update")
  public UserRightCheckedLogEntity lockByUserId(@Param("userId")long userId, @Param("otherId")long otherId, @Param("type")int type);
}