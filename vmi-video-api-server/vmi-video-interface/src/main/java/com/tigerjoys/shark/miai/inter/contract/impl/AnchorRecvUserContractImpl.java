package com.tigerjoys.shark.miai.inter.contract.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorRecvUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorRecvUserEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.mapper.AnchorRecvUserMapper;

/**
 * 数据库中  主播接听用户[t_anchor_recv_user]表 接口实现类
 * @author shiming
 * @Date 2019-07-05 16:00:33
 *
 */
@Repository
public class AnchorRecvUserContractImpl extends AbstractBaseContract<AnchorRecvUserEntity , AnchorRecvUserMapper> implements IAnchorRecvUserContract {

	@Override
	public boolean checkDisturb(long anchorId, long userid) throws Exception {
		PageModel pgModel = PageModel.getLimitModel(0, 1);
		pgModel.addQuery(Restrictions.eq("anchorId", anchorId));
		pgModel.addQuery(Restrictions.eq("userid",userid));
		pgModel.addQuery(Restrictions.eq("disturb",1));
		List<AnchorRecvUserEntity> entity = mapper.load(pgModel);
		if(Tools.isNotNull(entity)){
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean checkInvisibility(long anchorId, long userid) throws Exception {
		PageModel pgModel = PageModel.getLimitModel(0, 1);
		pgModel.addQuery(Restrictions.eq("anchorId", anchorId));
		pgModel.addQuery(Restrictions.eq("userid",userid));
		pgModel.addQuery(Restrictions.eq("invisibility",1));
		List<AnchorRecvUserEntity> entity = mapper.load(pgModel);
		if(Tools.isNotNull(entity)){
			return true;
		}
		return false;
	}
	
	@Override
	public List<AnchorRecvUserEntity> getRecvUserByType(long anchorId, int type) throws Exception {
		String fieldName = "";
		PageModel pgModel = PageModel.getPageModel();
		pgModel.addQuery(Restrictions.eq("anchorId", anchorId));
		if(type ==1){
			fieldName = "disturb";
		}else{
			fieldName = "invisibility";
		}
		pgModel.addQuery(Restrictions.eq(fieldName,1));
		return  mapper.load(pgModel);
		
	}
}
