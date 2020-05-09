package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorAudioContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorAudioEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorAudioMapper;

/**
 * 数据库中  主播音频列表数据[t_anchor_audio]表 接口实现类
 * @author shiming
 * @Date 2019-08-06 19:46:56
 *
 */
@Repository
public class AnchorAudioContractImpl extends AbstractBaseContract<AnchorAudioEntity , AnchorAudioMapper> implements IAnchorAudioContract {
	
}
