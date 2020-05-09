package com.tigerjoys.shark.miai.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserExtensionAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserExtensionBO;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 主播用户数据生成
 * @author shiming
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class AnchorCreateDataTest extends BaseTestConfig {

	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserExtensionAgent userExtensionAgent;
	
	@Test
	public void testCreateAnchorData() throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
		if(Tools.isNotNull(anchors)) {
			for (AnchorOnlineEntity anchor : anchors) {
				//处理基本数据
				UserBO user = userAgent.findById(anchor.getUserid());
				//处理扩展数据
				UserExtensionBO extension = userExtensionAgent.findByUserId(anchor.getUserid());
				
				anchor.setNickname(user.getNickname());
				anchor.setCityid(user.getCityid());
				anchor.setPhoto(user.getPhoto());
				anchor.setSignature(user.getSignature());
				
				anchor.setStature(extension.getStature()+" cm");
				anchor.setWeight(extension.getWeight()+ " kg");
				anchor.setZodiac(extension.getZodiac());
				anchorOnlineContract.update(anchor);
			}
		}
	}
}
