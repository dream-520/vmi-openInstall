package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.shark.miai.common.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.dto.task.BusinessMessageViewDto;
import com.tigerjoys.shark.miai.inter.contract.IBussinessMessageContract;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;
import com.tigerjoys.shark.miai.service.IBussinessMessageService;

/**
 * 业务消息服务类
 * @author liuman
 *
 */
@Service
public class BusinessServiceImpl implements IBussinessMessageService {
	
	@Autowired
	private IBussinessMessageContract bussinessMessageContract;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Override
	public ActionResult messageList(long userid, String stamp, int page, int pagesize) throws Exception {
		PageModel pageModel = PageModel.getPageModel(page, pagesize);
		pageModel.setPageSize(pagesize+1);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.desc("create_time");
		
		List<BussinessMessageEntity> list = bussinessMessageContract.load(pageModel);
		List<BusinessMessageViewDto> dtoList = new ArrayList<BusinessMessageViewDto>();
		
		if(Tools.isNotNull(list)) {
			int i = 0;
			for(BussinessMessageEntity news : list) {
				BusinessMessageViewDto dto = this.compositeMessageView(news);
				if(dto != null) {
					dtoList.add(dto);
				}
				if(++i >= pagesize) {
					break;
				}
			}
		}
		
		//点击小红点的时候,要将redis中的数据清空
		//cacheRedis.del(Const.business_message);
//		cacheRedis.hincrBy(Const.business_message, String.valueOf(userid), -1);
		cacheRedis.hset(CommonConst.business_message, String.valueOf(userid), "0");
		
		return ActionResult.success(dtoList , "" , (list!=null&&list.size()>pagesize)?true:false);
	}
	
	/**
	 * 组装业务消息
	 * @param bussinessMessage - BussinessMessageEntity
	 * @return BusinessMessageViewDto
	 * @throws Exception
	 */
	private BusinessMessageViewDto compositeMessageView(BussinessMessageEntity bussinessMessage) throws Exception {
		BusinessMessageViewDto dto = new BusinessMessageViewDto();
		dto.setAndroidAppPage(bussinessMessage.getAndroidAppPage());
		dto.setCreateDate(this.getBussinessMessageTime(bussinessMessage.getCreate_time()));
		dto.setCreateTime(bussinessMessage.getCreate_time().getTime());
		dto.setHitType(bussinessMessage.getHitType());
		dto.setIntro(bussinessMessage.getIntro());
		dto.setContent(bussinessMessage.getContent());
		dto.setIosAppPage(bussinessMessage.getIosAppPage());
		dto.setMessage(bussinessMessage.getId());
		dto.setOpentype(bussinessMessage.getOpentype());
		dto.setOpenurl(bussinessMessage.getOpenurl());
		dto.setParam(bussinessMessage.getParam());
		dto.setTitle(bussinessMessage.getTitle());
		
		return dto;
	}
	
	/**
	 * 获得业务消息发生时间的业务字符串格式
	 * @param publishDate 系统消息发布时间
	 * @return
	 */
	private String getBussinessMessageTime(Date createDate) {
		Date currentDate = new Date();
		String createTime = "";
		String currentDateStr = Tools.getFormatDate(currentDate, "yyyy-MM-dd");
		String createDateStr = Tools.getFormatDate(createDate, "yyyy-MM-dd");
		//不是同一天
		if (!currentDateStr.equals(createDateStr)) {
			createTime = Tools.getFormatDate(createDate, "yyyy-MM-dd");
		} else {
			createTime = Tools.getFormatDate(createDate, "HH:mm");
		}

		
		return createTime;
	}
	
}
