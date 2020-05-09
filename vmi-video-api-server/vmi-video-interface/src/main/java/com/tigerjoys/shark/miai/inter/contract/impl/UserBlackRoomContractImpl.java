package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserBlackRoomContract;
import com.tigerjoys.shark.miai.inter.entity.UserBlackRoomEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserBlackRoomMapper;

/**
 * 数据库中  用户小黑屋处理表[t_user_black_room]表 接口实现类
 * @author shiming
 * @Date 2019-11-12 11:07:36
 *
 */
@Repository
public class UserBlackRoomContractImpl extends AbstractBaseContract<UserBlackRoomEntity , UserBlackRoomMapper> implements IUserBlackRoomContract {
	
}
