package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IShareVipCardContract;
import com.tigerjoys.shark.miai.inter.entity.ShareVipCardEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ShareVipCardMapper;

/**
 * 数据库中  [t_share_vip_card]表 接口实现类
 * @author yangjunming
 * @Date 2018-05-25 10:26:31
 *
 */
@Repository
public class ShareVipCardContractImpl extends AbstractBaseContract<ShareVipCardEntity , ShareVipCardMapper> implements IShareVipCardContract {

	@Override
	public int usedCard(String mobile) throws Exception {
		return mapper.updateByStatement("status=1", "mobile="+mobile+" and status=0 ");
	}

	@Override
	public boolean queryCardStatus(String mobile) throws Exception {
		ShareVipCardEntity entity = mapper.findByProperty("mobile", mobile);
		if(Tools.isNotNull(entity)){
			if(entity.getStatus() == 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
