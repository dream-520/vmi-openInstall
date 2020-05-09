package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ITopHeadInfoContract;
import com.tigerjoys.shark.miai.inter.entity.TopHeadInfoEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.TopHeadInfoMapper;

/**
 * 数据库中  [t_top_head_info]表 接口实现类
 * @author yangjunming
 * @Date 2018-03-09 18:19:37
 *
 */
@Repository
public class TopHeadInfoContractImpl extends AbstractBaseContract<TopHeadInfoEntity , TopHeadInfoMapper> implements ITopHeadInfoContract {

	@Override
	public void addTopHeadInfo(String name, String info) throws Exception {
		TopHeadInfoEntity entity = new TopHeadInfoEntity();
		entity.setName(Tools.isNull(name)?"":name);
		entity.setInfo(info);
		entity.setStatus(1);
		entity.setTop_type(1);
		entity.setCreate_time(new Date());
		mapper.insert(entity);
	}
	

}
