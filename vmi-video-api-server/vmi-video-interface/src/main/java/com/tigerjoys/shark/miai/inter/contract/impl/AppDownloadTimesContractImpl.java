package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IAppDownloadTimesContract;
import com.tigerjoys.shark.miai.inter.entity.AppDownloadTimesEntity;
import com.tigerjoys.shark.miai.inter.mapper.AppDownloadTimesMapper;

/**
 * 数据库中  [t_app_download_times]表 接口实现类
 * @author yangjunming
 * @Date 2019-12-11 16:57:51
 *
 */
@Repository
public class AppDownloadTimesContractImpl extends AbstractBaseContract<AppDownloadTimesEntity , AppDownloadTimesMapper> implements IAppDownloadTimesContract {

}
