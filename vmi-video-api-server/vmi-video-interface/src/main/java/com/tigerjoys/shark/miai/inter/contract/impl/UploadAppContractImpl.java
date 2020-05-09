package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.LRUCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IUploadAppContract;
import com.tigerjoys.shark.miai.inter.entity.UploadAppEntity;
import com.tigerjoys.shark.miai.inter.mapper.UploadAppMapper;

/**
 * 数据库中  App上传信息表[t_upload_app]表 接口实现类
 * @author lipeng
 * @Date 2018-04-26 15:28:04
 *
 */
@Repository
public class UploadAppContractImpl extends LRUCacheContract<UploadAppEntity , UploadAppMapper> implements IUploadAppContract {
	
}
