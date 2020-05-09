package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IMeetstrangerNoticeContract;
import com.tigerjoys.shark.miai.inter.entity.MeetstrangerNoticeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.MeetstrangerNoticeMapper;

/**
 * 数据库中  遇见文字公告表[t_meetstranger_notice]表 接口实现类
 * @author liuman
 * @Date 2017-12-19 16:30:43
 *
 */
@Repository
public class MeetstrangerNoticeContractImpl extends AbstractBaseContract<MeetstrangerNoticeEntity , MeetstrangerNoticeMapper> implements IMeetstrangerNoticeContract {
	
}
