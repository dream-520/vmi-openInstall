package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.SelectProducer;

import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;
import com.tigerjoys.shark.miai.inter.provider.BussinessMessageSqlProvider;

/**
 * 数据库  业务消息记录表[t_bussiness_message]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-05-18 11:38:44
 *
 */
@Producer(entityType=BussinessMessageEntity.class,providerType=BussinessMessageSqlProvider.class)
@Mapper
public interface BussinessMessageMapper extends BaseMapper<BussinessMessageEntity> {

	/**
	 * 利用union all方法获得所有业务消息类型的最新消息记录
	 * @param types 业务消息类型
	 * @param userId 用户id
	 * @return
	 */
	@SelectProducer
	public abstract List<BussinessMessageEntity> loadByUseridAndType(@Param("types") List<Integer> types,@Param("userId") long userId);
}