package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserVchatExperienceIncomeLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserVchatExperienceIncomeLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserVchatExperienceIncomeLogMapper;

/**
 * 数据库中  用户音视频体验收益[t_user_vchat_experience_income_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-09-10 12:12:46
 *
 */
@Repository
public class UserVchatExperienceIncomeLogContractImpl extends AbstractBaseContract<UserVchatExperienceIncomeLogEntity , UserVchatExperienceIncomeLogMapper> implements IUserVchatExperienceIncomeLogContract {
	
}
