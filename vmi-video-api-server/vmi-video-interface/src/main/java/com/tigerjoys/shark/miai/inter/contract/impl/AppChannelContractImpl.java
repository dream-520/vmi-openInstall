package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppChannelContract;
import com.tigerjoys.shark.miai.inter.entity.AppChannelEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppChannelMapper;

/**
 * 数据库中  渠道信息表[t_app_channel]表 接口实现类
 * @author lipeng
 * @Date 2017-05-17 16:04:19
 *
 */
@Repository
public class AppChannelContractImpl extends AbstractBaseContract<AppChannelEntity , AppChannelMapper> implements IAppChannelContract {
	
}
