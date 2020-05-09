package com.tigerjoys.shark.miai.inter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  约定场地[t_appoint_site]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-12-13 16:14:35
 *
 */
@Producer(entityType=AppointSiteEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppointSiteMapper extends BaseMapper<AppointSiteEntity> {
	
	/**
	 * 随机取出pageSize条记录
	 * @param pageSize - 订单ID
	 * @return AppointSiteEntity
	 */
	@Select("SELECT * FROM t_appoint_site WHERE id >= ((SELECT MAX(id) FROM t_appoint_site)-(SELECT MIN(id) FROM t_appoint_site)) * RAND() + (SELECT MIN(id) FROM t_appoint_site)  LIMIT #{pageSize}")
	public abstract List<AppointSiteEntity> loadRandomList(int pageSize);
}