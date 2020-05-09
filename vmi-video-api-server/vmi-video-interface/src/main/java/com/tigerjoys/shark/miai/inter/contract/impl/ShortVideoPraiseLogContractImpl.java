package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoPraiseLogContract;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoPraiseLogEntity;
import com.tigerjoys.shark.miai.inter.mapper.ShortVideoPraiseLogMapper;

/**
 * 数据库中  [t_short_video_praise_log]表 接口实现类
 * @author yangjunming
 * @Date 2018-10-29 17:51:26
 *
 */
@Repository
public class ShortVideoPraiseLogContractImpl extends AbstractBaseContract<ShortVideoPraiseLogEntity , ShortVideoPraiseLogMapper> implements IShortVideoPraiseLogContract {

	@Override
	public int updatePraiseLogStatus(Long videoId, long userid) throws Exception {
		ShortVideoPraiseLogEntity praiseLog =  getShortVideoPraiseLog(videoId,userid);
		int rows =0;
		if(Tools.isNotNull(praiseLog)){
			rows = mapper.updateByStatement("STATUS=(case status when 0 then 1 when 1 then 0 end)", "id="+praiseLog.getId());
		}else{
			ShortVideoPraiseLogEntity entity = new ShortVideoPraiseLogEntity();
			entity.setVideo_id(videoId);
			entity.setUserid(userid);
			entity.setStatus(1);
			entity.setCreate_time(new Date());
			mapper.insert(entity);
			rows = 1;
		}
		return rows;
	}
	
	public ShortVideoPraiseLogEntity getShortVideoPraiseLog(Long videoId, long userid){
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("video_id", videoId));
		pageModel.addQuery(Restrictions.eq("userid", userid));
		List<ShortVideoPraiseLogEntity> list = mapper.load(pageModel);
		if(Tools.isNotNull(list)){
			return list.get(0);
		}
		return null;
	}
	
	
}
