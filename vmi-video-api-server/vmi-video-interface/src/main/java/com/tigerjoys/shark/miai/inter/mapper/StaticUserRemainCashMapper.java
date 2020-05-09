package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.ProducerResult;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StaticUserRemainCashEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  红包现金账户统计[t_static_user_remain_cash]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-05-21 19:00:29
 *
 */
@Producer(entityType=StaticUserRemainCashEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StaticUserRemainCashMapper extends BaseMapper<StaticUserRemainCashEntity> {
    
	/**
	 * 根据主键获得对象
	 * @param id - long id
	 * @param type - int 类型 1 累计现金  2 剩余现金
	 * return StaticUserRemainCashEntity
	 * @throw Exception
	 *
	*/
	@Select("SELECT * FROM t_static_user_remain_cash WHERE id = #{id,jdbcType=BIGINT} AND type = #{type,jdbcType=TINYINT}")
	@ProducerResult
	public StaticUserRemainCashEntity getStaticUserRemainCash(@Param("id")long id, @Param("type")int type);
	
}