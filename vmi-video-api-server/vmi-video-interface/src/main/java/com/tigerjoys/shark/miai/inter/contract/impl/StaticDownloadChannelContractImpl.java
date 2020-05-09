package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IStaticDownloadChannelContract;
import com.tigerjoys.shark.miai.inter.entity.StaticDownloadChannelEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.StaticDownloadChannelMapper;

/**
 * 数据库中  遍历日志获取到的渠道信息[t_static_download_channel]表 接口实现类
 * @author yangjunming
 * @Date 2018-05-09 10:53:59
 *
 */
@Repository
public class StaticDownloadChannelContractImpl extends AbstractBaseContract<StaticDownloadChannelEntity , StaticDownloadChannelMapper> implements IStaticDownloadChannelContract {
	
}
