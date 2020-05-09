package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorImageContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorImageEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorImageMapper;

/**
 * 数据库中  主播个人主页形象图片[t_anchor_image]表 接口实现类
 * @author shiming
 * @Date 2019-07-03 19:24:27
 *
 */
@Repository
public class AnchorImageContractImpl extends AbstractBaseContract<AnchorImageEntity , AnchorImageMapper> implements IAnchorImageContract {
	
}
