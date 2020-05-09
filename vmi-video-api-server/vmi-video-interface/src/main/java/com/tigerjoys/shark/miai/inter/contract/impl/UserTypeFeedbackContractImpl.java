package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserTypeFeedbackContract;
import com.tigerjoys.shark.miai.inter.entity.UserTypeFeedbackEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserTypeFeedbackMapper;

/**
 * 数据库中  用户分类反馈表[t_user_type_feedback]表 接口实现类
 * @author lipeng
 * @Date 2018-01-29 16:31:32
 *
 */
@Repository
public class UserTypeFeedbackContractImpl extends AbstractBaseContract<UserTypeFeedbackEntity , UserTypeFeedbackMapper> implements IUserTypeFeedbackContract {
	
}
