package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserLuckyDrawLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserLuckyDrawLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserLuckyDrawLogMapper;

/**
 * 数据库中  用户参与幸运抽奖日志表[t_user_lucky_draw_log]表 接口实现类
 * @author lipeng
 * @Date 2018-01-29 16:31:32
 *
 */
@Repository
public class UserLuckyDrawLogContractImpl extends AbstractBaseContract<UserLuckyDrawLogEntity , UserLuckyDrawLogMapper> implements IUserLuckyDrawLogContract {
	
}
