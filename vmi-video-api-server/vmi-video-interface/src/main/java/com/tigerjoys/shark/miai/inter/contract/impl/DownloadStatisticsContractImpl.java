package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IDownloadStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.DownloadStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.DownloadStatisticsMapper;

/**
 * 数据库中  [t_download_statistics]表 接口实现类
 * @author yangjunming
 * @Date 2018-05-03 17:55:00
 *
 */
@Repository
public class DownloadStatisticsContractImpl extends AbstractBaseContract<DownloadStatisticsEntity , DownloadStatisticsMapper> implements IDownloadStatisticsContract {
	
}
