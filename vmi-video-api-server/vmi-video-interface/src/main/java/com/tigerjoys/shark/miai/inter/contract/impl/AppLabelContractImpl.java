package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppLabelContract;
import com.tigerjoys.shark.miai.inter.entity.AppLabelEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppLabelMapper;

/**
 * 数据库中  app使用的标签库(形象标签和用户评论标签)[t_app_label]表 接口实现类
 * @author shiming
 * @Date 2018-10-29 11:27:03
 *
 */
@Repository
public class AppLabelContractImpl extends AbstractBaseContract<AppLabelEntity , AppLabelMapper> implements IAppLabelContract {
	
}
