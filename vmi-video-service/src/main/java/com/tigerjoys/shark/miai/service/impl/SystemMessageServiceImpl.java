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
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.task.MessageViewDto;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IMessageTemplateContract;
import com.tigerjoys.shark.miai.inter.contract.IMessageUserReadContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;
import com.tigerjoys.shark.miai.inter.entity.MessageUserReadEntity;
import com.tigerjoys.shark.miai.service.ISystemMessageService;

@Service
public class SystemMessageServiceImpl implements ISystemMessageService {
	
	@Autowired
	private IMessageTemplateContract messageTemplateContract;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IMessageUserReadContract messageUserReadContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;

	@Override
	public ActionResult messageList(long userid, String stamp, int page, int pagesize,boolean isAddReadCount,String packageName) throws Exception {
		UserBO user = userAgent.findById(userid);
		PageModel pageModel = PageModel.getPageModel(page, pagesize);
		pageModel.setPageSize(pagesize+1);
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.addQuery(Restrictions.eq("type", 0));
		pageModel.addQuery(Restrictions.eq("packagename", packageName));
		//在系统消息发布时间之后注册的用户,之前的系统消息不应该在系统消息列表中展示
		pageModel.addQuery(Restrictions.gt("publish_time", user.getCreateTime()));
		pageModel.addQuery(Restrictions.eq("send", 1));
		pageModel.addQuery(Restrictions.le("publish_time", new Date(Tools.getMinuteTime(5))));//发布的时间不能超过当前的时间
		pageModel.desc("publish_time");
		
		List<MessageTemplateEntity> list = messageTemplateContract.load(pageModel);
		//发布消息个数
		String publishCount = cacheRedis.get(CommonConst.sys_message + packageName);
		String readCount = cacheRedis.hget(CommonConst.sys_user_message + packageName, String.valueOf(userid));
		if (Tools.isNotNull(publishCount)) { 
			if (isAddReadCount && (Tools.parseInt(publishCount) > Tools.parseInt(readCount))) {
				//后台系统消息要将加入该消息更新的redis值(hash结构) 系统小红点在亮起后,点击进入系统消息列表时删除
				cacheRedis.hincrBy(CommonConst.sys_user_message + packageName, String.valueOf(userid), 1);
			}
		}

		List<MessageViewDto> dtoList = new ArrayList<MessageViewDto>();
		
		if(Tools.isNotNull(list)) {
			int i = 0;
			for(MessageTemplateEntity news : list) {
				MessageViewDto dto = this.compositeMessageView(news);
				if(dto != null) {
					dtoList.add(dto);
				}
				if(++i >= pagesize) { 
					break;
				}
			}
		}
		
		return ActionResult.success(dtoList , "" , (list!=null&&list.size()>pagesize)?true:false);
	}
	
	@Override
	public ActionResult messageListNew(long userid, String stamp, int page, int pagesize, boolean isAddReadCount) throws Exception {
		pagesize = 20;
		UserBO user = userAgent.findById(userid);
		if(Tools.isNotNull(user)) {
			//首先处理小红点业务
			if(isAddReadCount) {
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.in("userid", "10010", userid));
				List<MessageUserReadEntity> userReads = messageUserReadContract.load(pageModel);
				MessageUserReadEntity total = null;
				MessageUserReadEntity userM = null;
				if(Tools.isNotNull(userReads) && userReads.size() > 0) {
					//分析对应的用户和特殊总数值
					for (MessageUserReadEntity userRead : userReads) {
						if(userRead.getUserid().longValue() == 10010) {
							total = userRead;
						} else if(userRead.getUserid().longValue() == userid) {
							userM = userRead;
						}
					}
					
					if(Tools.isNotNull(total) && Tools.isNotNull(userM)) {
						//使用最新的小红点数量进行替换当前小红点数量
						if(userM.getAll_num().intValue() != total.getAll_num().intValue() || userM.getUser_num().intValue() != total.getUser_num().intValue() || userM.getAnchor_num().intValue() != total.getAnchor_num().intValue())
						userM.setAll_num(total.getAll_num());
						userM.setAnchor_num(total.getAnchor_num());
						userM.setUser_num(total.getUser_num());
						messageUserReadContract.update(userM);
					}
					
				}
			}
			
			//确定第一个检索条件 发布时间
			String publish_time = "";
			if(Tools.isNotNull(stamp) && stamp.length() > 0) {
				publish_time = stamp;
			} else {
				publish_time = Tools.getDateTime(Tools.getMinuteTime(5));
			}
			List<MessageTemplateEntity> list = null;
			//根据对应的用户身份来确认对应的取消息的方式 以及创建时间
			if(user.isWaiter()) {
				//主播  取主播审核通过之后的消息
				AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", user.getUserid());
				if(Tools.isNotNull(anchor)) {
					String create = Tools.getDateTime(anchor.getAudit_time());
					list = messageTemplateContract.getAnchorMessages(publish_time, create);
				}
			} else {
				//用户  取用户创建时间的消息
				String create = Tools.getDateTime(user.getCreateTime());
				list = messageTemplateContract.getUserMessages(publish_time, create);
			}
			
			List<MessageViewDto> dtoList = new ArrayList<MessageViewDto>();
			String newStamp = null;
			if(Tools.isNotNull(list)) {
				int i = 0;
				for(MessageTemplateEntity news : list) {
					MessageViewDto dto = this.compositeMessageView(news);
					if(dto != null) {
						dtoList.add(dto);
					}
					if(++i >= 20) { 
						
						break;
					}
				}
				//确定戳
				if(list.size() > 20) {
					newStamp = Tools.getDateTime(list.get(list.size() - 1).getPublish_time());
				}
			}
			
			//确定是否进行分页
			boolean nextPage = false;
			if(Tools.isNotNull(list) && list.size() > 0 && list.size() > pagesize)
				nextPage = true;
			return ActionResult.success(dtoList, newStamp, nextPage);
		} 
		return ActionResult.success();
	}
	
	/**
	 * 组装系统消息
	 * @param template - MessageTemplateEntity
	 * @return MessageViewDto
	 * @throws Exception
	 */
	private MessageViewDto compositeMessageView(MessageTemplateEntity template) throws Exception {
		MessageViewDto dto = new MessageViewDto();
		dto.setMessage(template.getId());
		dto.setCreateDate(this.getPublishTime(template.getPublish_time()));
		dto.setCreateTime(template.getPublish_time().getTime());
		dto.setTitle(template.getTitle());
		dto.setOpenurl(template.getOpenurl());
		dto.setOpentype(template.getOpentype());
		dto.setType(template.getType());
		dto.setIntro(template.getIntro());
		dto.setOpenurl(Const.getSysMessUrl(template.getId()));
		return dto;
	}
	
	/**
	 * 获得系统消息发布时间的业务字符串格式
	 * @param publishDate 系统消息发布时间
	 * @return
	 */
	private String getPublishTime(Date publishDate) {
		Date currentDate = new Date();
		String publishTime = "";
		String currentDateStr = Tools.getFormatDate(currentDate, "yyyy-MM-dd");
		String commentDateStr = Tools.getFormatDate(publishDate, "yyyy-MM-dd");
		//不是同一天
		if (!currentDateStr.equals(commentDateStr)) {
			publishTime = Tools.getFormatDate(publishDate, "yyyy-MM-dd");
		} else {
			publishTime = Tools.getFormatDate(publishDate, "HH:mm");
		}
		return publishTime;
	}

}
