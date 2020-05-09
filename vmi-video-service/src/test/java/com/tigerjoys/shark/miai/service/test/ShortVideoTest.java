package com.tigerjoys.shark.miai.service.test;

import java.util.List;

import org.elasticsearch.action.ActionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.service.IShortVideoService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class ShortVideoTest extends BaseTestConfig {
	
	@Autowired
	private IShortVideoService shortVideoService;

	@Test
	public void testGetAnchorData() throws Exception {
		/*
		StringBuffer stampBuffer = new StringBuffer("");
		List<AnchorOnlineEntity>  anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		System.err.println(anchors.size());
		anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		System.err.println(anchors.size());
		anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		System.err.println(anchors.size());
		anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		System.err.println(anchors.size());
		anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		System.err.println(anchors.size());
		anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		System.err.println(anchors.size());
		anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		System.err.println(anchors.size());
		anchors = shortVideoService.getTabAnchors(3+"", true, stampBuffer, 1);
		*/
	}
	
	@Test
	public void testShortVideoData() throws Exception {
		ActionResult request = shortVideoService.anchorNewSlideList(160697009626480896L,1,5,null,null);
		System.out.println("XXXXXX="+JsonHelper.toJson(request));
		
	}
	
	
	
	
}
