package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStaticDownloadVisitContract;
import com.tigerjoys.shark.miai.inter.entity.StaticDownloadVisitEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StaticDownloadVisitMapper;

/**
 * 数据库中  渠道连接访问量统计表[t_static_download_visit]表 接口实现类
 * @author yangjunming
 * @Date 2018-05-09 10:53:59
 *
 */
@Repository
public class StaticDownloadVisitContractImpl extends AbstractBaseContract<StaticDownloadVisitEntity , StaticDownloadVisitMapper> implements IStaticDownloadVisitContract {
	
}
