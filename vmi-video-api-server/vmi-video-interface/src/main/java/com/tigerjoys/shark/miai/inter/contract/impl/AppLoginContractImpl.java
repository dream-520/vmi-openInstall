package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppLoginContract;
import com.tigerjoys.shark.miai.inter.entity.AppLoginEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppLoginMapper;

/**
 * 数据库中  第三方登录表[t_app_login]表 接口实现类
 * @author lipeng
 * @Date 2017-05-06 10:28:58
 *
 */
@Repository
public class AppLoginContractImpl extends AbstractBaseContract<AppLoginEntity , AppLoginMapper> implements IAppLoginContract {
	
}
