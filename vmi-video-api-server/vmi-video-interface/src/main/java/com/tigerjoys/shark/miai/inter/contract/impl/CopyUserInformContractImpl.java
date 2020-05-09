package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICopyUserInformContract;
import com.tigerjoys.shark.miai.inter.entity.CopyUserInformEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CopyUserInformMapper;

/**
 * 数据库中  用户举报表[t_copy_user_inform]表 接口实现类
 * @author shiming
 * @Date 2019-12-18 15:45:36
 *
 */
@Repository
public class CopyUserInformContractImpl extends AbstractBaseContract<CopyUserInformEntity , CopyUserInformMapper> implements ICopyUserInformContract {
	
}
