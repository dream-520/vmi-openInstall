package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.TalentVacuateEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  达人分成比例表[t_talent_vacuate]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-11-15 15:01:09
 *
 */
@Producer(entityType=TalentVacuateEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface TalentVacuateMapper extends BaseMapper<TalentVacuateEntity> {
    
}