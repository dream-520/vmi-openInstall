package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.SequenceEntity;

/**
 * 数据库  ID生成器表[b_sequence]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2017-04-14 10:41:53
 *
 */
@Producer(entityType=SequenceEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface SequenceMapper extends BaseMapper<SequenceEntity> {

	/**
	 * 锁定指定的行
	 * @param tablename - 表名称
	 * @return SequenceEntity
	 */
	@Select("select * from b_sequence where entityname = #{entityname} for update")
	public abstract SequenceEntity lockByClassName(String entityname);
    
}