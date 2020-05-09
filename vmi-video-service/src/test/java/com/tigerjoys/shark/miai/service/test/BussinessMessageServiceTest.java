package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.enums.BussinessMessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IMessageRedDotAgent;
import com.tigerjoys.shark.miai.dto.task.BusinessMessageViewDto;
import com.tigerjoys.shark.miai.inter.contract.IBussinessMessageContract;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


/**
 * 业务小红点测试代码(暂时没有执行)
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class BussinessMessageServiceTest extends BaseTestConfig {
	
	@Autowired
	private IBussinessMessageContract bussinessMessageContract;
	
	@Autowired
	private IMessageRedDotAgent messageRedDotAgent;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	/**
	 * 测试插入业务消息记录
	 * @throws Exception 
	 */
	@Test
	public void testAddBussinessMessage() throws Exception {
		long userId = 10022588L;
		BussinessMessageEntity bussinessMessage = new BussinessMessageEntity();
		//你的红包被偷了,赶紧去看看
		//你的红包树被好友施加魔法了,赶紧来看看
		//你的用户等级已经升级,赶紧来看看
		bussinessMessage.setContent("你的红包树被好友施加魔法了,赶紧来看看");
		bussinessMessage.setCreate_time(new Date());
		//你的红包被偷了,赶紧去看看
		//你的红包树被好友施加魔法了
		//你的用户等级已经升级
		bussinessMessage.setIntro("你的红包树被好友施加魔法了");
		//红包被偷
		//被施加魔法
		//用户等级升级
		bussinessMessage.setTitle("被施加魔法");
		bussinessMessage.setUserid(userId);
		bussinessMessageContract.insert(bussinessMessage);
	}
	
	@Test
	public void testAddBussinessMessageWithRedis() throws Exception {
		long userId = 13051798829L;
		messageRedDotAgent.addBussinessMessage(userId, BussinessMessageTypeEnum.apply_sucess_free,"");
	}
	
	@Test
	public void testAddRedisHash() throws Exception {
//		long userId = 10022588l;
		long userId = 10022609L;
		cacheRedis.hincrBy(CommonConst.business_message, String.valueOf(userId), 1);
	}
	
	@Test
	public void getBussinessMessages() throws Exception {
		PageModel pageModel = PageModel.getPageModel(1, 10);
		pageModel.setPageSize(11);
		pageModel.addQuery(Restrictions.eq("userid", 10022588L));
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
				if(++i >= 10) {
					break;
				}
			}
		}
		
		ActionResult result = ActionResult.success(dtoList , "" , (list!=null&&list.size()>10)?true:false);
		logger.info("json of result:{}", JsonHelper.toJson(result));
	}
	
	/**
	 * 组装业务消息
	 * @param bussinessMessage - BussinessMessageEntity
	 * @return BusinessMessageViewDto
	 * @throws Exception
	 */
	private BusinessMessageViewDto compositeMessageView(BussinessMessageEntity bussinessMessage) throws Exception {
		BusinessMessageViewDto dto = new BusinessMessageViewDto();
		dto.setMessage(bussinessMessage.getId());
//		dto.setCreateDate(Tools.getDateTime(template.getPublish_time()));
//		dto.setCreateDate(bussinessMessage.getCreate_time());
		dto.setTitle(bussinessMessage.getTitle());
		dto.setIntro(bussinessMessage.getIntro());
		
		return dto;
	}
	
}
