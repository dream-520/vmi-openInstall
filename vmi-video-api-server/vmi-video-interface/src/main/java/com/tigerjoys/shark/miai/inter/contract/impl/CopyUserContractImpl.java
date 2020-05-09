package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ICopyUserContract;
import com.tigerjoys.shark.miai.inter.entity.CopyUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.CopyUserMapper;

/**
 * 数据库中  用户基础表[t_copy_user]表 接口实现类
 * @author lipeng
 * @Date 2019-12-17 15:58:26
 *
 */
@Repository
public class CopyUserContractImpl extends AbstractBaseContract<CopyUserEntity , CopyUserMapper> implements ICopyUserContract {
	
}
