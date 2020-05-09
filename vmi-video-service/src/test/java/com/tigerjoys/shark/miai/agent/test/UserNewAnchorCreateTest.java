package com.tigerjoys.shark.miai.agent.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.shark.miai.common.cloud.storage.ICloudStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserExtensionAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserExtensionBO;
import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppLabelContract;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AppLabelEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.shark.miai.service.IVChatVideoYXService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;
import com.tigerjoys.shark.miai.utils.FileUploadResult;
import com.tigerjoys.shark.miai.utils.Helper;

public class UserNewAnchorCreateTest extends BaseTestConfig {

	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserExtensionAgent userExtensionAgent;
	
	@Autowired
	private IVChatVideoYXService vChatVideoYXService;
	
	@Autowired
	private IAnchorIntimateRankingsContract anchorIntimateRankingsContract;
	
	@Autowired
	private IAppLabelContract appLabelContract;
	
	@Autowired
	private IShortVideoContract shortVideoContract;
	
	@Autowired
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Autowired
	@Qualifier("upYunCloudStorage")
	private ICloudStorage upYunCloudStorage;
	
	//@Test
	public void createAnchorInfoTest() throws Exception{
		List<AnchorDto> dtos = new ArrayList<>();
		
		AnchorDto dto1 = new AnchorDto();
		dto1.setUserid(65418691671490304L);
		dto1.setPrice(8);
		dto1.setStar(3);
		dto1.setOnline(3);
		dto1.setDisturb(0);
		dto1.setState(1);
		dto1.setFlag(1);
		dto1.setNickname("任莹樱");
		dto1.setPhoto("{\"result\":true,\"data\":{\"id\":280732,\"nickname\":\"\\u4efb\\u83b9\\u6a31\",\"identity\":\"\\u5199\\u771f\\u6a21\\u7279 \\u5185\\u8863\\u6a21\\u7279\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":7448,\"focused\":0,\"avatar\":{\"id\":1906311,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1531443173000_t4bntfxibxcb1u8yxbii\"},\"photos\":[{\"id\":1906311,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1531443173000_t4bntfxibxcb1u8yxbii\"},{\"id\":1787169,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1510164999000_287vsygaancexs1u8q0j\"},{\"id\":1811799,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1513881232000_tw432qyr2ngggcnnudpn\"},{\"id\":1787166,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1510164999000_d9vttdyz87juco6d05wj\"},{\"id\":1841784,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1520323898000_4xwj1ddaqmc7j0x15s4r\"},{\"id\":1807151,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1513166254000_reh91f9kms1n64fhmuv4\"},{\"id\":1902253,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1530739787000_3abat4ateu0cva6fvric\"},{\"id\":1906310,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/280732_1531443052000_l6qsexfgtfqi2o4hnakn\"}],\"figureTags\":[{\"id\":5,\"type\":1,\"name\":\"\\u59a9\\u5a9a\\u6027\\u611f\",\"color\":\"ff7a7a\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":11,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdwx.qlogo.cn\\/mmopen\\/vi_32\\/Q0j4TwGTfTIdmxUuslFdVibmkdNiap04zoiagnhYcWYx8iclXRnib96wBE8Awl8u6Q8ksvGG4EibEAVa8rg6y2bniaBKA\\/132\"},\"nickname\":\"Zwz\",\"contribution\":7968,\"userId\":968718,\"rank\":1,\"level\":8},{\"avatar\":{\"id\":1926464,\"width\":720,\"height\":779,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/652883_1535227767000_nlpz4itbdu0szhjmmua6\"},\"nickname\":\"Eason\",\"contribution\":7680,\"userId\":652883,\"rank\":2,\"level\":10},{\"avatar\":{\"id\":1888894,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/100634_1528656583000_ond4bsz5ie3trsuhqyee\"},\"nickname\":\"\\u51c0\\u5316\",\"contribution\":5152,\"userId\":100634,\"rank\":3,\"level\":9}],\"isOneFree\":0,\"intimacyCount\":312655,\"giftCount\":636,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto1.setSignature("请文明观球");
		dto1.setIntro("写真模特 内衣模特");
		dto1.setStature("163 cm");
		dto1.setWeight("53 kg");
		dto1.setZodiac("双子座");
		dto1.setLabel("妩媚性感 完美身材");
		dto1.setCityId("289");
		dto1.setDial_num(158);
		dto1.setRecv_num(148);
		dto1.setEvaluation_num(142);
		dtos.add(dto1);
		
		AnchorDto dto2 = new AnchorDto();
		dto2.setUserid(65418693063999232L);
		dto2.setPrice(8);
		dto2.setStar(3);
		dto2.setOnline(3);
		dto2.setDisturb(0);
		dto2.setState(1);
		dto2.setFlag(1);
		dto2.setNickname("静静lucky");
		dto2.setPhoto("{\"result\":true,\"data\":{\"id\":2062232,\"nickname\":\"\\u9759\\u9759lucky\",\"identity\":\"\\u77e5\\u540d\\u6a21\\u7279 \\u6f14\\u5458 \\u2006\\u7f51\\u7ea2\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":138,\"focused\":0,\"avatar\":{\"id\":2078008,\"width\":750,\"height\":750,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/NC7z6SrGQK.jpg\"},\"photos\":[{\"id\":2078008,\"width\":750,\"height\":750,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/NC7z6SrGQK.jpg\"},{\"id\":2078018,\"width\":750,\"height\":750,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/FSNQChQkmQ.jpg\"},{\"id\":2059849,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2062232_1540098747000_ywl7925vacx4jtve7a48\"},{\"id\":2078012,\"width\":750,\"height\":750,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/XQsp7EG77E.jpg\"},{\"id\":2078013,\"width\":750,\"height\":750,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/SFPsQZhYNY.jpg\"},{\"id\":2078015,\"width\":750,\"height\":750,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/Et7jAeiMPw.jpg\"},{\"id\":2054650,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2062232_1540016324000_o5zry3bc30vgb3xfpqa7\"},{\"id\":2059850,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2062232_1540098747000_2h6lkmb6noxb6mt4ur5h\"}],\"figureTags\":[{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":2060991,\"width\":959,\"height\":821,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1999428_1540115441_96419105.jpg\"},\"nickname\":\"Fxxk\",\"contribution\":1378,\"userId\":1999428,\"rank\":1,\"level\":6},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"Mo\",\"contribution\":1193,\"userId\":2092469,\"rank\":2,\"level\":4},{\"avatar\":{\"id\":1995660,\"width\":900,\"height\":900,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/588397_1538808780472_0tjpqxda0848jzvbmntt\"},\"nickname\":\"\\u683c\\u683c\\u5deb\",\"contribution\":280,\"userId\":588397,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":6303,\"giftCount\":17,\"gifts\":[{\"id\":110,\"width\":116,\"height\":136,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/GFC2RpreXi.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto2.setSignature("你敢来撩吗");
		dto2.setIntro("知名模特 演员 网红");
		dto2.setStature("175 cm");
		dto2.setWeight("40 kg");
		dto2.setZodiac("巨蟹座");
		dto2.setLabel("清纯美女 完美身材");
		dto2.setCityId("131");
		dto2.setDial_num(58);
		dto2.setRecv_num(45);
		dto2.setEvaluation_num(36);
		dtos.add(dto2);
		
		AnchorDto dto3 = new AnchorDto();
		dto3.setUserid(65418693535858432L);
		dto3.setPrice(5);
		dto3.setStar(3);
		dto3.setOnline(2);
		dto3.setDisturb(0);
		dto3.setState(1);
		dto3.setFlag(1);
		dto3.setNickname("小小");
		dto3.setPhoto("{\"result\":true,\"data\":{\"id\":2129449,\"nickname\":\"\\u5c0f\\u5c0f\",\"identity\":\"\\u4e3b\\u64ad \\u6a21\\u7279\",\"vcoinPerMinute\":5,\"level\":\"3\",\"fans\":186,\"focused\":0,\"avatar\":{\"id\":2118648,\"width\":500,\"height\":503,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2129449_1541066559_12841418\"},\"photos\":[{\"id\":2118648,\"width\":500,\"height\":503,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2129449_1541066559_12841418\"},{\"id\":2118641,\"width\":500,\"height\":502,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2129449_1541066465_88430982\"},{\"id\":2118645,\"width\":500,\"height\":502,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2129449_1541066518_37497847\"},{\"id\":2118649,\"width\":500,\"height\":502,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2129449_1541066574_89228054\"},{\"id\":2118650,\"width\":500,\"height\":502,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2129449_1541066621_49473548\"}],\"figureTags\":[{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":2130344,\"width\":959,\"height\":615,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2091504_1541243064_61927090.jpg\"},\"nickname\":\"\\u9633\\u9633\",\"contribution\":637,\"userId\":2091504,\"rank\":1,\"level\":4},{\"avatar\":{\"id\":1860909,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/921525_1523811829000_4aiagvoj5jmx51sdwdbg\"},\"nickname\":\"\\u5c0f\\u674e\\u98de\\u5200\",\"contribution\":390,\"userId\":921525,\"rank\":2,\"level\":5},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u5ce5\\u54e5\",\"contribution\":299,\"userId\":34786,\"rank\":3,\"level\":6}],\"isOneFree\":0,\"intimacyCount\":3730,\"giftCount\":17,\"gifts\":[{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"},{\"id\":19,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/XmFStD3D4J.png\"},{\"id\":5,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/EFTezHAMKk1.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto3.setSignature("我可不可以做你的宝贝");
		dto3.setIntro("主播 模特");
		dto3.setStature("165 cm");
		dto3.setWeight("48 kg");
		dto3.setZodiac("射手座");
		dto3.setCityId("75");
		dto3.setDial_num(55);
		dto3.setRecv_num(42);
		dto3.setEvaluation_num(34);
		dtos.add(dto3);
		
		AnchorDto dto4 = new AnchorDto();
		dto4.setUserid(65418693967871744L);
		dto4.setPrice(8);
		dto4.setStar(3);
		dto4.setOnline(2);
		dto4.setDisturb(0);
		dto4.setState(1);
		dto4.setFlag(1);
		dto4.setNickname("Sherry");
		dto4.setPhoto("{\"result\":true,\"data\":{\"id\":1252330,\"nickname\":\"Sherry\",\"identity\":\"\\u6a21\\u7279\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":1693,\"focused\":0,\"avatar\":{\"id\":1886527,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1528330411000_yqemlk54pt66ve7k4rpc\"},\"photos\":[{\"id\":1886527,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1528330411000_yqemlk54pt66ve7k4rpc\"},{\"id\":2083932,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1540476851000_h18k2fugkdzf528zerro\"},{\"id\":1890699,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1528890850000_zxahs47ifg5k9oahe4m8\"},{\"id\":1886580,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1528341298000_3xly11xa5h13t0pzarhb\"},{\"id\":2045322,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1539867587000_cgpjxhqysmfu32nydert\"},{\"id\":1913345,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1532588951000_1and40u9n56vr9zd907b\"},{\"id\":2139551,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1541347928000_d21dfru5n92d9yi0fv8y\"},{\"id\":1934878,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1252330_1537172171000_of3y7w6g13yqg1jv4znm\"}],\"figureTags\":[{\"id\":6,\"type\":1,\"name\":\"\\u6e29\\u67d4\\u53ef\\u7231\",\"color\":\"82da35\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":10,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"Dami\",\"contribution\":4601,\"userId\":317257,\"rank\":1,\"level\":10},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u8fd9\\u4e2a\\u4eba\\u5f88\\u61d2\\u4ec0\\u4e48\\u90fd\\u6ca1\\u7559\\u4e0b\",\"contribution\":4257,\"userId\":1442851,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u83e0\\u841d\",\"contribution\":3804,\"userId\":237920,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":84115,\"giftCount\":158,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto4.setSignature("我喜欢慢慢深入的，你喜欢吗？");
		dto4.setIntro("模特");
		dto4.setStature("165 cm");
		dto4.setWeight("46 kg");
		dto4.setZodiac("白羊座");
		dto4.setCityId("257");
		dto4.setDial_num(186);
		dto4.setRecv_num(180);
		dto4.setEvaluation_num(165);
		dtos.add(dto4);
		
		AnchorDto dto5 = new AnchorDto();
		dto5.setUserid(65418694332776192L);
		dto5.setPrice(5);
		dto5.setStar(3);
		dto5.setOnline(1);
		dto5.setDisturb(1);
		dto5.setState(1);
		dto5.setFlag(1);
		dto5.setNickname("郑雪莉");
		dto5.setPhoto("{\"result\":true,\"data\":{\"id\":2130794,\"nickname\":\"\\u90d1\\u96ea\\u8389\",\"identity\":\"\\u5341\\u516b\\u7ebf\\u5c0f\\u6a21\\u7279\\u4e00\\u679a\",\"vcoinPerMinute\":5,\"level\":\"3\",\"fans\":65,\"focused\":0,\"avatar\":{\"id\":2119856,\"width\":500,\"height\":480,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2130794_1541081339_36546519\"},\"photos\":[{\"id\":2119856,\"width\":500,\"height\":480,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2130794_1541081339_36546519\"},{\"id\":2119861,\"width\":500,\"height\":502,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2130794_1541081363_83309546\"}],\"figureTags\":[{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":12,\"type\":1,\"name\":\"\\u957f\\u53d1\\u98d8\\u9038\",\"color\":\"19e8d0\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":5,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u6c99\\u573a\\u70b9\\u5175\\u53ef\\u559c\\u53ef\\u8d3a\",\"contribution\":125,\"userId\":1831478,\"rank\":1,\"level\":8},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"AJ kevin\",\"contribution\":90,\"userId\":544838,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdqq.qlogo.cn\\/qqapp\\/101490836\\/C7DA4796C24252D1A146EF69F0FBE750\\/100\"},\"nickname\":\"\\u9221\\u79cb\",\"contribution\":70,\"userId\":2129381,\"rank\":3,\"level\":3}],\"isOneFree\":0,\"intimacyCount\":460,\"giftCount\":0,\"gifts\":[],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto5.setSignature("不倾国倾城只亲你");
		dto5.setIntro("十八线小模特一枚");
		dto5.setStature("174 cm");
		dto5.setWeight("50 kg");
		dto5.setZodiac("天秤座");
		dto5.setCityId("180");
		dto5.setDial_num(56);
		dto5.setRecv_num(48);
		dto5.setEvaluation_num(46);
		dtos.add(dto5);
		
		AnchorDto dto6 = new AnchorDto();
		dto6.setUserid(65418694731235072L);
		dto6.setPrice(8);
		dto6.setStar(3);
		dto6.setOnline(1);
		dto6.setDisturb(1);
		dto6.setState(1);
		dto6.setFlag(1);
		dto6.setNickname("抹茶酱");
		dto6.setPhoto("{\"result\":true,\"data\":{\"id\":1573824,\"nickname\":\"\\u62b9\\u8336\\u9171\",\"identity\":\"\\u7535\\u7ade\\u4e3b\\u64ad\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":1982,\"focused\":0,\"avatar\":{\"id\":1919668,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1573824_1533646325000_lchfalyb1k4l8e1sv658\"},\"photos\":[{\"id\":1919668,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1573824_1533646325000_lchfalyb1k4l8e1sv658\"},{\"id\":2076787,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1573824_1540369535000_k0mdb7py9wwbp82oeqry\"},{\"id\":1929591,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1573824_1536032931000_817frqi7ie7z94swnmen\"},{\"id\":2076789,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1573824_1540369535000_fqdseyewcc0wkzv92nbk\"},{\"id\":2002647,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1573824_1538970081000_ltc9mhjku4g0uku8gtch\"},{\"id\":2076786,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1573824_1540369535000_88k1o2qdaocgotn41lzx\"}],\"figureTags\":[{\"id\":23,\"type\":1,\"name\":\"\\u840c\\u59b9\\u7eb8\",\"color\":\"ffc456\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":17,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":24,\"type\":1,\"name\":\"\\u4e8c\\u6b21\\u5143\",\"color\":\"cb7ff7\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":16,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u2133int\\u0fd0\\ud83d\\udc93\",\"contribution\":4006,\"userId\":1698422,\"rank\":1,\"level\":6},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u8709\\u8763\",\"contribution\":3288,\"userId\":1637107,\"rank\":2,\"level\":7},{\"avatar\":{\"id\":2042542,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2368_1539807794000_f6obhal1cdhjzejuzjci\"},\"nickname\":\"Caeras\",\"contribution\":2512,\"userId\":2368,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":65880,\"giftCount\":108,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto6.setSignature("没有你的绝地我不想求生");
		dto6.setIntro("电竞主播");
		dto6.setStature("158 cm");
		dto6.setWeight("50 kg");
		dto6.setZodiac("双子座");
		dto6.setCityId("224");
		dto6.setDial_num(188);
		dto6.setRecv_num(180);
		dto6.setEvaluation_num(148);
		dtos.add(dto6);
		
		AnchorDto dto7 = new AnchorDto();
		dto7.setUserid(65418695278591744L);
		dto7.setPrice(8);
		dto7.setStar(3);
		dto7.setOnline(1);
		dto7.setDisturb(1);
		dto7.setState(1);
		dto7.setFlag(1);
		dto7.setNickname("小G娜");
		dto7.setPhoto("{\"result\":true,\"data\":{\"id\":1436637,\"nickname\":\"\\u5c0fG\\u5a1c\",\"identity\":\"\\u4e3b\\u64ad\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":535,\"focused\":0,\"avatar\":{\"id\":2023309,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1436637_1539434988000_9jrc1f527tdytydv76wf\"},\"photos\":[{\"id\":2023309,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1436637_1539434988000_9jrc1f527tdytydv76wf\"}],\"figureTags\":[{\"id\":6,\"type\":1,\"name\":\"\\u6e29\\u67d4\\u53ef\\u7231\",\"color\":\"82da35\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":10,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1923222,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1289738_1534490163000_3dgx9ki6dxs1wongpu52\"},\"nickname\":\"\\u7ec5\\u58eb\\u5b9d\\u5b9d\",\"contribution\":670,\"userId\":1289738,\"rank\":1,\"level\":8},{\"avatar\":{\"id\":2048380,\"width\":959,\"height\":906,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2007764_1539915568_20031433.jpg\"},\"nickname\":\"lijzi\",\"contribution\":659,\"userId\":2007764,\"rank\":2,\"level\":4},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdqq.qlogo.cn\\/qqapp\\/101490836\\/128934FFA699B528661053738537E7E8\\/100\"},\"nickname\":\"\\u53ef\\u4ee5\\u52a0Wx\\u5417\\uff1f\",\"contribution\":641,\"userId\":2089114,\"rank\":3,\"level\":6}],\"isOneFree\":0,\"intimacyCount\":\"26433\",\"giftCount\":70,\"gifts\":[{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto7.setSignature("约起来 不看打字");
		dto7.setIntro("主播");
		dto7.setStature("162 cm");
		dto7.setWeight("50 kg");
		dto7.setZodiac("天蝎座");
		dto7.setCityId("131");
		dto7.setDial_num(123);
		dto7.setRecv_num(120);
		dto7.setEvaluation_num(111);
		dtos.add(dto7);
		
		AnchorDto dto8 = new AnchorDto();
		dto8.setUserid(65418695616233216L);
		dto8.setPrice(8);
		dto8.setStar(3);
		dto8.setOnline(0);
		dto8.setDisturb(0);
		dto8.setState(1);
		dto8.setFlag(1);
		dto8.setNickname("凤九儿");
		dto8.setPhoto("{\"result\":true,\"data\":{\"id\":591838,\"nickname\":\"\\u51e4\\u4e5d\\u513f\",\"identity\":\"\\u77e5\\u540d\\u4e3b\\u64ad \\u5e73\\u9762\\u6a21\\u7279 \\u5b66\\u751f\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":3471,\"focused\":0,\"avatar\":{\"id\":2070947,\"width\":749,\"height\":749,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/rFSdSrWetC.jpg\"},\"photos\":[{\"id\":2070947,\"width\":749,\"height\":749,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/rFSdSrWetC.jpg\"},{\"id\":2070931,\"width\":749,\"height\":749,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/asB2GkhAB6.jpg\"},{\"id\":2070935,\"width\":750,\"height\":750,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/Xeyzm36Cry.jpg\"},{\"id\":1905122,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/591838_1531237446000_wu18oz1hgh37e5ns4jn3\"},{\"id\":1834657,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/591838_1518406128000_kek5tli62fe1km1w2l7g\"},{\"id\":1970162,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/591838_1538210851000_x9nsuu0dhylzbrw29fiw\"},{\"id\":2070937,\"width\":750,\"height\":750,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/QrRszSSywK.jpg\"}],\"figureTags\":[{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":9,\"type\":1,\"name\":\"\\u58f0\\u97f3\\u8ff7\\u4eba\",\"color\":\"add721\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":8,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1853867,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/3949_1522672488000_fuejaq6c6mrvq793zo4m\"},\"nickname\":\"\\u604b\\u4e0a\\u4e0b\\u96e8\\u5929\",\"contribution\":4553,\"userId\":3949,\"rank\":1,\"level\":7},{\"avatar\":{\"id\":1921033,\"width\":900,\"height\":900,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/611071_1533920998906_s4fhekj7fgtnhmtcadjs\"},\"nickname\":\"\\u542c\\u98ce\\u8005\",\"contribution\":4352,\"userId\":611071,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdwx.qlogo.cn\\/mmopen\\/vi_32\\/91dBJEibUDqQwicIev0rLkUzhYhcDINQpSYGaoKtQwLSiaECxmYOeVic8YdhYSTYseQeRMMdGLLyWLFc65tDzLiakyw\\/132\"},\"nickname\":\"\\u54c7\\u5494\\u5494\",\"contribution\":3518,\"userId\":871212,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":\"175172\",\"giftCount\":131,\"gifts\":[{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto8.setSignature("新人长腿女神求守护");
		dto8.setIntro("知名模特 平面模特 学生");
		dto8.setStature("168 cm");
		dto8.setWeight("44 kg");
		dto8.setZodiac("双子座");
		dto8.setCityId("131");
		dto8.setDial_num(258);
		dto8.setRecv_num(246);
		dto8.setEvaluation_num(202);
		dtos.add(dto8);
		
		AnchorDto dto9 = new AnchorDto();
		dto9.setUserid(65418696167784192L);
		dto9.setPrice(8);
		dto9.setStar(3);
		dto9.setOnline(0);
		dto9.setDisturb(0);
		dto9.setState(1);
		dto9.setFlag(1);
		dto9.setNickname("林baby");
		dto9.setPhoto("{\"result\":true,\"data\":{\"id\":1467534,\"nickname\":\"\\u6797baby\",\"identity\":\"\\u6a21\\u7279\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":748,\"focused\":0,\"avatar\":{\"id\":1907676,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1467534_1531705187000_3j875ffdr3r9bjewnr1f\"},\"photos\":[{\"id\":1907676,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1467534_1531705187000_3j875ffdr3r9bjewnr1f\"},{\"id\":1907673,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1467534_1531705187000_vt47x1fv01dcr9rkmfgc\"},{\"id\":1907674,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1467534_1531705187000_ua0eakiz5ko16w2cnlrb\"},{\"id\":1907672,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1467534_1531705187000_xkt1vbryygpp2ef89ri6\"}],\"figureTags\":[{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u6c6a\\u6d77\\u6d9b\",\"contribution\":425,\"userId\":1642951,\"rank\":1,\"level\":4},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"deepblue2016\",\"contribution\":144,\"userId\":768357,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdqq.qlogo.cn\\/qqapp\\/101490836\\/5BC8C6B29C02FB94B86FDDFEED248550\\/100\"},\"nickname\":\"\\u98de\\u4e91\",\"contribution\":77,\"userId\":1960731,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":1188,\"giftCount\":14,\"gifts\":[{\"id\":19,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/XmFStD3D4J.png\"},{\"id\":5,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/EFTezHAMKk1.png\"},{\"id\":18,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/nMQ2DaGDWK2.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto9.setSignature("绿色主播");
		dto9.setIntro("模特");
		dto9.setStature("173 cm");
		dto9.setWeight("50 kg");
		dto9.setZodiac("射手座");
		dto9.setCityId("131");
		dto9.setDial_num(28);
		dto9.setRecv_num(24);
		dto9.setEvaluation_num(20);
		dtos.add(dto9);
		
		AnchorDto dto10 = new AnchorDto();
		dto10.setUserid(65418696643837696L);
		dto10.setPrice(8);
		dto10.setStar(3);
		dto10.setOnline(0);
		dto10.setDisturb(0);
		dto10.setState(1);
		dto10.setFlag(1);
		dto10.setNickname("喵");
		dto10.setPhoto("{\"result\":true,\"data\":{\"id\":129624,\"nickname\":\"\\u55b5\",\"identity\":\"\\u6f14\\u5458\\uff0c\\u767d\\u9886\",\"vcoinPerMinute\":8,\"level\":\"3\",\"fans\":165,\"focused\":0,\"avatar\":{\"id\":1353823,\"width\":720,\"height\":755,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/129624_1505544780000_gkpa0dpqumnfbttc3ub5\"},\"photos\":[{\"id\":1353823,\"width\":720,\"height\":755,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/129624_1505544780000_gkpa0dpqumnfbttc3ub5\"},{\"id\":1287128,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/129624_1505397000000_2sps5rolaklki7eoorhd\"},{\"id\":1288764,\"width\":720,\"height\":886,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/129624_1505399179000_03a77hxpl2bf9lordryn\"},{\"id\":1321021,\"width\":720,\"height\":824,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/129624_1505462512000_dyd3illich3gdv61juhk\"},{\"id\":1321029,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/129624_1505462551000_uwu9kh74sv4a5eymkx53\"},{\"id\":1321032,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/129624_1505462566000_g1rmu7vapye25xwcl1ja\"}],\"figureTags\":[{\"id\":14,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u841d\\u8389\",\"color\":\"e691f5\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":12,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":19,\"type\":1,\"name\":\"\\u5927\\u957f\\u817f\",\"color\":\"fc79bd\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":20,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[],\"isOneFree\":0,\"intimacyCount\":0,\"giftCount\":0,\"gifts\":[],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto10.setSignature("百变女王 总有你喜欢的一款");
		dto10.setIntro("演员 白领");
		dto10.setStature("168 cm");
		dto10.setWeight("50 kg");
		dto10.setZodiac("射手座");
		dto10.setCityId("121");
		dto10.setDial_num(15);
		dto10.setRecv_num(10);
		dto10.setEvaluation_num(8);
		dtos.add(dto10);
		
		AnchorDto dto11 = new AnchorDto();
		dto11.setUserid(65418697029713664L);
		dto11.setPrice(14);
		dto11.setStar(4);
		dto11.setOnline(3);
		dto11.setDisturb(0);
		dto11.setState(1);
		dto11.setFlag(1);
		dto11.setNickname("泡芙");
		dto11.setPhoto("{\"result\":true,\"data\":{\"id\":327249,\"nickname\":\"\\u6ce1\\u8299\",\"identity\":\"\\u5b66\\u751f \\u6a21\\u7279\",\"vcoinPerMinute\":14,\"level\":\"4\",\"fans\":4282,\"focused\":0,\"avatar\":{\"id\":2137117,\"width\":720,\"height\":721,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1541328566000_6uy9gm5pby3ha1n55i34\"},\"photos\":[{\"id\":2137117,\"width\":720,\"height\":721,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1541328566000_6uy9gm5pby3ha1n55i34\"},{\"id\":1938720,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1537366953000_3iv82v54qxzekhqlro4c\"},{\"id\":2080002,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1540402732000_73manbbxzm7bgawohg9x\"},{\"id\":2137116,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1541328567000_9do8hdvjy985mvrdkpw4\"},{\"id\":2081900,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1540448121000_dlemfmgl5pvx6zao0qjg\"},{\"id\":2133869,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1541274983000_oe6iyjtjmaf67imkr1mc\"},{\"id\":2137461,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1541333195000_nliub9iyvmtsewb7heje\"},{\"id\":2140329,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/327249_1541357249000_0i9bmfdzr8kezl0ydkr1\"}],\"figureTags\":[{\"id\":10,\"type\":1,\"name\":\"\\u6c14\\u8d28\\u7f8e\\u5973\",\"color\":\"48df36\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":7,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":13,\"type\":1,\"name\":\"\\u90bb\\u5bb6\\u5c0f\\u59b9\",\"color\":\"64c3fa\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":4,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u7ea6*\",\"contribution\":38628,\"userId\":222565,\"rank\":1,\"level\":12},{\"avatar\":{\"id\":1828652,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/558423_1516818946000_ppzb6rcli0yliyz9t0ic\"},\"nickname\":\"Moneymonster\",\"contribution\":29856,\"userId\":558423,\"rank\":2,\"level\":11},{\"avatar\":{\"id\":1849596,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/789305_1521822692000_auancjfyjay3tuac0ipz\"},\"nickname\":\"Z\\u5148\\u68ee\",\"contribution\":23168,\"userId\":789305,\"rank\":3,\"level\":10}],\"isOneFree\":0,\"intimacyCount\":581049,\"giftCount\":592,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec..\",\"inducementActTime\":5,\"honor\":[]}}");
		dto11.setSignature("学院逃课小公主一枚");
		dto11.setIntro("学生 模特");
		dto11.setStature("168 cm");
		dto11.setWeight("46 kg");
		dto11.setZodiac("巨蟹座");
		dto11.setCityId("131");
		dto11.setDial_num(526);
		dto11.setRecv_num(500);
		dto11.setEvaluation_num(420);
		dtos.add(dto11);
		
		AnchorDto dto12 = new AnchorDto();
		dto12.setUserid(65418697558195968L);
		dto12.setPrice(12);
		dto12.setStar(4);
		dto12.setOnline(3);
		dto12.setDisturb(0);
		dto12.setState(1);
		dto12.setFlag(1);
		dto12.setNickname("李小鹿");
		dto12.setPhoto("{\"result\":true,\"data\":{\"id\":1232175,\"nickname\":\"\\u674e\\u5c0f\\u9e7f\",\"identity\":\"\\u5fc3\\u7406\\u54a8\\u8be2\\u5e08\",\"vcoinPerMinute\":12,\"level\":\"4\",\"fans\":3171,\"focused\":0,\"avatar\":{\"id\":2082087,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540450942000_j533f4nvtn74wcaochw5\"},\"photos\":[{\"id\":2082087,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540450942000_j533f4nvtn74wcaochw5\"},{\"id\":2082086,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540450942000_ft1dv9x3h3gkjzng4o98\"},{\"id\":2056235,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540039876000_fleee9inn6cmz6yhve7r\"},{\"id\":2056459,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540041938000_1c1kroxi0v4bk0wcuiji\"},{\"id\":2082088,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540450942000_z0qx1i8f8na94111xpjb\"},{\"id\":1921992,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1534172343000_1etbyjl2e9o1al3k6b9b\"},{\"id\":2082089,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540450942000_uvhmwmaqyapdpc4z2ymj\"},{\"id\":2087610,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1232175_1540546541000_zyljiuyyh8aah6swhrsm\"}],\"figureTags\":[{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":20,\"type\":1,\"name\":\"\\u60c5\\u611f\\u4e13\\u5bb6\",\"color\":\"ff78a0\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":1,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u9047\\u89c1\",\"contribution\":9084,\"userId\":1540095,\"rank\":1,\"level\":9},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"Wild man\",\"contribution\":6646,\"userId\":1378193,\"rank\":2,\"level\":7},{\"avatar\":{\"id\":2001651,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/647772_1538931348000_qieftlb1e77qt6no4bsb\"},\"nickname\":\"\\uff0c\",\"contribution\":4157,\"userId\":647772,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":204844,\"giftCount\":352,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec..\",\"inducementActTime\":5,\"honor\":[]}}");
		dto12.setSignature("可以直接打过来");
		dto12.setIntro("心理咨询师");
		dto12.setStature("165 cm");
		dto12.setWeight("44 kg");
		dto12.setZodiac("白羊座");
		dto12.setCityId("315");
		dto12.setDial_num(268);
		dto12.setRecv_num(260);
		dto12.setEvaluation_num(202);
		dtos.add(dto12);
		
		AnchorDto dto13 = new AnchorDto();
		dto13.setUserid(65418698199924480L);
		dto13.setPrice(16);
		dto13.setStar(4);
		dto13.setOnline(2);
		dto13.setDisturb(0);
		dto13.setState(1);
		dto13.setFlag(1);
		dto13.setNickname("琉璃心");
		dto13.setPhoto("{\"result\":true,\"data\":{\"id\":230347,\"nickname\":\"\\u7409\\u7483\\u5fc3\",\"identity\":\"\\u5e73\\u9762\\u6a21\\u7279\",\"vcoinPerMinute\":16,\"level\":\"4\",\"fans\":6710,\"focused\":0,\"avatar\":{\"id\":2107314,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/230347_1540876353000_ku801dhpsc636261q1ag\"},\"photos\":[{\"id\":2107314,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/230347_1540876353000_ku801dhpsc636261q1ag\"},{\"id\":2104362,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/230347_1540822076000_pdpftx881lahblwa9rer\"},{\"id\":2104360,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/230347_1540822076000_pv10whihnef2ep3du5wa\"},{\"id\":2102038,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/230347_1540781685000_56ncmfql30gv0ml3xehj\"}],\"figureTags\":[{\"id\":5,\"type\":1,\"name\":\"\\u59a9\\u5a9a\\u6027\\u611f\",\"color\":\"ff7a7a\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":11,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":26,\"type\":1,\"name\":\"\\u5b85\\u7537\\u5973\\u795e\",\"color\":\"ff6e64\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":3,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1935787,\"width\":900,\"height\":900,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1823266_1537274103628_3clod43kzuv0mvrnfit1\"},\"nickname\":\"\\u5927\\u53d4\",\"contribution\":7806,\"userId\":1823266,\"rank\":1,\"level\":7},{\"avatar\":{\"id\":1908110,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1438969_1531758874000_de5f7kj82yvu0tcs590t\"},\"nickname\":\"SEE\",\"contribution\":7179,\"userId\":1438969,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u7a46\\u5148\\u751f\",\"contribution\":4694,\"userId\":1753176,\"rank\":3,\"level\":11}],\"isOneFree\":0,\"intimacyCount\":385567,\"giftCount\":1203,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto13.setSignature("以色事他人");
		dto13.setIntro("平面模特");
		dto13.setStature("167 cm");
		dto13.setWeight("49 kg");
		dto13.setZodiac("处女座");
		dto13.setCityId("131");
		dto13.setDial_num(538);
		dto13.setRecv_num(500);
		dto13.setEvaluation_num(420);
		dtos.add(dto13);
		
		AnchorDto dto14 = new AnchorDto();
		dto14.setUserid(65418698587897600L);
		dto14.setPrice(14);
		dto14.setStar(4);
		dto14.setOnline(2);
		dto14.setDisturb(0);
		dto14.setState(1);
		dto14.setFlag(1);
		dto14.setNickname("萌小金金");
		dto14.setPhoto("{\"result\":true,\"data\":{\"id\":251468,\"nickname\":\"\\u840c\\u5c0f\\u91d1\\u91d1\",\"identity\":\"\\u767d\\u9886\",\"vcoinPerMinute\":14,\"level\":\"4\",\"fans\":6068,\"focused\":0,\"avatar\":{\"id\":2021853,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1539410699000_8nt9inejlx95urwbbysa\"},\"photos\":[{\"id\":2021853,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1539410699000_8nt9inejlx95urwbbysa\"},{\"id\":2028423,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1539531702000_aw6huujqwai3wctdz2t2\"},{\"id\":2065693,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1540195397000_wh42lpdzokid5xoiml0n\"},{\"id\":2065691,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1540195397000_4uasrjwpyyny15nhb1lb\"},{\"id\":2086765,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1540532223000_qj23dnoucbycxf2gx2rp\"},{\"id\":2065694,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1540195397000_mtr7kqbu8ohoethqejw0\"},{\"id\":2121451,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/251468_1541095228000_hckka2nvf4gwp0za5t7c\"}],\"figureTags\":[{\"id\":5,\"type\":1,\"name\":\"\\u59a9\\u5a9a\\u6027\\u611f\",\"color\":\"ff7a7a\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":11,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":15,\"type\":1,\"name\":\"\\u65f6\\u5c1a\\u5fa1\\u59d0\",\"color\":\"58d2ff\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":13,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1954086,\"width\":900,\"height\":900,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/902902_1537726415443_r4zk5t44cm2rkunk8gd8\"},\"nickname\":\"\\u8d70\\u8def\\u5e26\\u98ce\",\"contribution\":15875,\"userId\":902902,\"rank\":1,\"level\":10},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u72fc*\\u7537\\u4eba\",\"contribution\":7861,\"userId\":168387,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\ud83d\\udd06\\ud83d\\udd05\",\"contribution\":4207,\"userId\":1275626,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":441923,\"giftCount\":774,\"gifts\":[{\"id\":11,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/8w48JEwwMB1.png\"},{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto14.setSignature("我的口红是草莓味");
		dto14.setIntro("白领");
		dto14.setStature("170 cm");
		dto14.setWeight("58 kg");
		dto14.setZodiac("天秤座");
		dto14.setCityId("131");
		dto14.setDial_num(628);
		dto14.setRecv_num(600);
		dto14.setEvaluation_num(460);
		dtos.add(dto14);
		
		AnchorDto dto15 = new AnchorDto();
		dto15.setUserid(65418699246403328L);
		dto15.setPrice(10);
		dto15.setStar(4);
		dto15.setOnline(1);
		dto15.setDisturb(1);
		dto15.setState(1);
		dto15.setFlag(1);
		dto15.setNickname("倪清清");
		dto15.setPhoto("{\"result\":true,\"data\":{\"id\":342019,\"nickname\":\"\\u502a\\u6e05\\u6e05\",\"identity\":\"\\u77e5\\u540d\\u6a21\\u7279 \\u8fd0\\u52a8\\u5458\",\"vcoinPerMinute\":10,\"level\":\"4\",\"fans\":9787,\"focused\":0,\"avatar\":{\"id\":1904937,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1531219908000_pn5y7icwxkdiba4vx614\"},\"photos\":[{\"id\":1904937,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1531219908000_pn5y7icwxkdiba4vx614\"},{\"id\":2017952,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1539335536000_e7sqvd6x977x44zolqcj\"},{\"id\":1888443,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1528593778000_am9q468d82jd1uio21qo\"},{\"id\":1888543,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1528611745000_xildwxfsvo9zor1cyn3x\"},{\"id\":1922476,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1534300858000_5tv2zytpwaz0okvonuje\"},{\"id\":1894462,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1529465343000_1fwduoh0j13yo2px93uw\"},{\"id\":1920217,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1533743511000_progzl354v5blqb9cmet\"},{\"id\":1931168,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/342019_1536374947000_yfipy5i1n4dcetj9k82d\"}],\"figureTags\":[{\"id\":10,\"type\":1,\"name\":\"\\u6c14\\u8d28\\u7f8e\\u5973\",\"color\":\"48df36\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":7,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":19,\"type\":1,\"name\":\"\\u5927\\u957f\\u817f\",\"color\":\"fc79bd\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":20,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u517b\\u4e50\\u591a\",\"contribution\":8180,\"userId\":1537550,\"rank\":1,\"level\":8},{\"avatar\":{\"id\":2063748,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/358698_1540146833000_nxjumc89cywyq2dj8xrx\"},\"nickname\":\"\\u9a6c\\u4e0a\\u5c31\\u6ce8\\u9500\",\"contribution\":7027,\"userId\":358698,\"rank\":2,\"level\":11},{\"avatar\":{\"id\":1833422,\"width\":900,\"height\":900,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/568817_1517972289796_0eirniz46f4swwrv84dt\"},\"nickname\":\"Penny\",\"contribution\":6477,\"userId\":568817,\"rank\":3,\"level\":6}],\"isOneFree\":0,\"intimacyCount\":540669,\"giftCount\":595,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[]}}");
		dto15.setSignature("大长腿 马甲线");
		dto15.setIntro("知名模特 运动员");
		dto15.setStature("172 cm");
		dto15.setWeight("50 kg");
		dto15.setZodiac("巨蟹座");
		dto15.setCityId("289");
		dto15.setDial_num(1128);
		dto15.setRecv_num(1080);
		dto15.setEvaluation_num(802);
		dtos.add(dto15);
		
		AnchorDto dto16 = new AnchorDto();
		dto16.setUserid(65418699674222336L);
		dto16.setPrice(10);
		dto16.setStar(4);
		dto16.setOnline(1);
		dto16.setDisturb(1);
		dto16.setState(1);
		dto16.setFlag(1);
		dto16.setNickname("陈艺甜");
		dto16.setPhoto("{\"result\":true,\"data\":{\"id\":1095236,\"nickname\":\"\\u9648\\u827a\\u751c\",\"identity\":\"\\u6a21\\u7279\",\"vcoinPerMinute\":10,\"level\":\"4\",\"fans\":4331,\"focused\":0,\"avatar\":{\"id\":1927776,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548425000_uwuok5p4uonusq2yhc2h\"},\"photos\":[{\"id\":1927776,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548425000_uwuok5p4uonusq2yhc2h\"},{\"id\":1927769,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548042000_aunru4tzu9w5tei7spet\"},{\"id\":1927764,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548042000_56t5fb4xs0yz9lrg7mnj\"},{\"id\":1927767,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548042000_5v2xxnjd0env7cocen80\"},{\"id\":1927766,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548042000_g7x2i585pptc5wp7w1e2\"},{\"id\":1927770,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548042000_sf9zgm283m19oezojh6j\"},{\"id\":1927771,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548042000_fu2ly0pnb0tpqvra64m8\"},{\"id\":1927765,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1095236_1535548042000_c47sarxvduf8h21t9qpl\"}],\"figureTags\":[{\"id\":5,\"type\":1,\"name\":\"\\u59a9\\u5a9a\\u6027\\u611f\",\"color\":\"ff7a7a\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":11,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":16,\"type\":1,\"name\":\"\\u5236\\u670d\\u8bf1\\u60d1\",\"color\":\"46daed\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":14,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1944294,\"width\":900,\"height\":900,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1876487_1537512074580_u6rv4obd22loeahk9qnl\"},\"nickname\":\"\\u770b\\u5230\\u6211\\u2026\\u2026\\u56de\\u590d\",\"contribution\":7680,\"userId\":1876487,\"rank\":1,\"level\":9},{\"avatar\":{\"id\":2127981,\"width\":900,\"height\":900,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/2013133_1541205642594_5uu9pit4ucytm3l6lw8h\"},\"nickname\":\"\\u6e38\\u5ba2\\u5230\\u6b64\\u4e00\\u6e38\",\"contribution\":6960,\"userId\":2013133,\"rank\":2,\"level\":6},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdwx.qlogo.cn\\/mmopen\\/vi_32\\/Q0j4TwGTfTKjhq3Pnsz4H5P9lsR1ZiamGo4bUzDGIGuI83IjiaCegKnQdYT1SfzSygRPEsdH6icfQdpIx6Z5Fu2LQ\\/132\"},\"nickname\":\"\\u6bb5\\u5fd7\\u806a\",\"contribution\":4529,\"userId\":1938383,\"rank\":3,\"level\":6}],\"isOneFree\":0,\"intimacyCount\":357508,\"giftCount\":2148,\"gifts\":[{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto16.setSignature("私密私密一对一 直接弹视频");
		dto16.setIntro("模特");
		dto16.setStature("165 cm");
		dto16.setWeight("50 kg");
		dto16.setZodiac("双子座");
		dto16.setCityId("131");
		dto16.setDial_num(512);
		dto16.setRecv_num(500);
		dto16.setEvaluation_num(402);
		dtos.add(dto16);
		
		AnchorDto dto17 = new AnchorDto();
		dto17.setUserid(65418700051709696L);
		dto17.setPrice(18);
		dto17.setStar(4);
		dto17.setOnline(1);
		dto17.setDisturb(1);
		dto17.setState(1);
		dto17.setFlag(1);
		dto17.setNickname("绝对仙儿");
		dto17.setPhoto("{\"result\":true,\"data\":{\"id\":249768,\"nickname\":\"\\u7edd\\u5bf9\\u4ed9\\u513f\",\"identity\":\"\\u5e73\\u9762\\u6a21\\u7279\",\"vcoinPerMinute\":17,\"level\":\"4\",\"fans\":5598,\"focused\":0,\"avatar\":{\"id\":1896394,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1529762032000_megj6rolhqbcc0fsld5l\"},\"photos\":[{\"id\":1896394,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1529762032000_megj6rolhqbcc0fsld5l\"},{\"id\":1834767,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1518445133000_5jy8r2e1v58ukhehpocf\"},{\"id\":1886288,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1528292097000_bmixxrymogx0csfrygxv\"},{\"id\":1831086,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1517294147000_6dg4nsq6az7mfi1xlb1o\"},{\"id\":1851219,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1522173448000_0y22xt0clyy4oxus8i6q\"},{\"id\":2102664,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1540795206000_wd9hckqwpjyy4gymlkul\"},{\"id\":2102708,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1540795801000_u1rqpwa2z06my4oam18d\"},{\"id\":2102709,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/249768_1540795801000_77g61fiqv9bm4qyvn8l9\"}],\"figureTags\":[{\"id\":5,\"type\":1,\"name\":\"\\u59a9\\u5a9a\\u6027\\u611f\",\"color\":\"ff7a7a\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":11,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"lyn\\u7684\\u884c\\u60c5\",\"contribution\":24651,\"userId\":463925,\"rank\":1,\"level\":10},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"joker\",\"contribution\":11973,\"userId\":282876,\"rank\":2,\"level\":11},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u7ed9\\u59d0\\u59d0\\u6696\\u88ab\\u7a9d\",\"contribution\":11159,\"userId\":272855,\"rank\":3,\"level\":10}],\"isOneFree\":0,\"intimacyCount\":\"457670\",\"giftCount\":370,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto17.setSignature("一秒让你爱上我");
		dto17.setIntro("平面模特");
		dto17.setStature("168 cm");
		dto17.setWeight("46 kg");
		dto17.setZodiac("双子座");
		dto17.setCityId("332");
		dto17.setDial_num(508);
		dto17.setRecv_num(502);
		dto17.setEvaluation_num(404);
		dtos.add(dto17);
		
		AnchorDto dto18 = new AnchorDto();
		dto18.setUserid(65418698910859008L);
		dto18.setPrice(12);
		dto18.setStar(4);
		dto18.setOnline(0);
		dto18.setDisturb(0);
		dto18.setState(1);
		dto18.setFlag(1);
		dto18.setNickname("陈遇白");
		dto18.setPhoto("{\"result\":true,\"data\":{\"id\":1397867,\"nickname\":\"\\u9648\\u9047\\u767d\",\"identity\":\"\\u672c\\u4eba\\u6bd4\\u7167\\u7247\\u597d\\u770b\",\"vcoinPerMinute\":12,\"level\":\"4\",\"fans\":2325,\"focused\":0,\"avatar\":{\"id\":1913438,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1397867_1532594355000_64h34cgbgc8ns7rrbfmc\"},\"photos\":[{\"id\":1913438,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1397867_1532594355000_64h34cgbgc8ns7rrbfmc\"},{\"id\":1913421,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1397867_1532593621000_dqs6sr2ke9h1cwf9fehp\"},{\"id\":1925347,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1397867_1534955071000_qijabgys0v0aicko5opa\"},{\"id\":1916084,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1397867_1532967995000_nvxybtcz4sazoyfcw79w\"},{\"id\":2007250,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1397867_1539087431000_tzrjull8tymqjxov0rmz\"}],\"figureTags\":[{\"id\":10,\"type\":1,\"name\":\"\\u6c14\\u8d28\\u7f8e\\u5973\",\"color\":\"48df36\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":7,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":12,\"type\":1,\"name\":\"\\u957f\\u53d1\\u98d8\\u9038\",\"color\":\"19e8d0\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":5,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u592a\\u9633\",\"contribution\":7505,\"userId\":169132,\"rank\":1,\"level\":9},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u8282\\u8282\\u9ad8\",\"contribution\":4778,\"userId\":1341869,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u6c99\\u5b50\",\"contribution\":3400,\"userId\":1404994,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":132358,\"giftCount\":208,\"gifts\":[{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto18.setSignature("散似满天星");
		dto18.setIntro("本人比照片好看");
		dto18.setStature("166 cm");
		dto18.setWeight("48 kg");
		dto18.setZodiac("天蝎座");
		dto18.setCityId("179");
		dto18.setDial_num(268);
		dto18.setRecv_num(248);
		dto18.setEvaluation_num(220);
		dtos.add(dto18);
		
		AnchorDto dto19 = new AnchorDto();
		dto19.setUserid(65418700729089792L);
		dto19.setPrice(16);
		dto19.setStar(4);
		dto19.setOnline(0);
		dto19.setDisturb(0);
		dto19.setState(1);
		dto19.setFlag(1);
		dto19.setNickname("尚心");
		dto19.setPhoto("{\"result\":true,\"data\":{\"id\":663173,\"nickname\":\"\\u5c1a\\u5fc3\",\"identity\":\"\\u5e73\\u9762\\u6a21\\u7279 \\u8f66\\u6a21\",\"vcoinPerMinute\":16,\"level\":\"4\",\"fans\":4877,\"focused\":0,\"avatar\":{\"id\":2124729,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1541162002000_snf577321vra1t6egazw\"},\"photos\":[{\"id\":2124729,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1541162002000_snf577321vra1t6egazw\"},{\"id\":2124727,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1541162002000_toipknx3w9rn3z1ftmtf\"},{\"id\":2103820,\"width\":900,\"height\":900,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1540816184995_yho59ux36j8nsl9yn9ux\"},{\"id\":2124728,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1541162002000_ssiqdlrw66k0bwpb4roi\"},{\"id\":1919576,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1533631261000_qbzr19d0ijtp7s6uudd8\"},{\"id\":2124730,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1541162002000_ojm41mfr705v9mix7pj4\"},{\"id\":2137837,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/663173_1541336329000_bsizj1wi31j4j66iy3br\"}],\"figureTags\":[{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1974626,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1685367_1538331680000_ps6qoc2msv6hcivyt7cf\"},\"nickname\":\"\\u5c1a\\u5fc3\\u60a6\\u76ee\",\"contribution\":128486,\"userId\":1685367,\"rank\":1,\"level\":13},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdwx.qlogo.cn\\/mmopen\\/vi_32\\/lWfF1X3ty2apY07gRiaAibv9RCESDwwQia4uwKwia7iaUuxTsTgdGYSq401bvf8FZxE62gJLHwkxyicPicrEu1ialibs4og\\/132\"},\"nickname\":\"\\u533f\\u540d\",\"contribution\":24255,\"userId\":1423781,\"rank\":2,\"level\":13},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u82e5\\u5f64\\u7684\\u5907\\u80ce\",\"contribution\":8694,\"userId\":143978,\"rank\":3,\"level\":11}],\"isOneFree\":0,\"intimacyCount\":469188,\"giftCount\":555,\"gifts\":[{\"id\":11,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/8w48JEwwMB1.png\"},{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto19.setSignature("想做你的床畔画和心上人");
		dto19.setIntro("平面模特 车模");
		dto19.setStature("172 cm");
		dto19.setWeight("55 kg");
		dto19.setZodiac("双子座");
		dto19.setCityId("55");
		dto19.setDial_num(301);
		dto19.setRecv_num(298);
		dto19.setEvaluation_num(282);
		dtos.add(dto19);
		
		AnchorDto dto20 = new AnchorDto();
		dto20.setUserid(65418701123354368L);
		dto20.setPrice(16);
		dto20.setStar(4);
		dto20.setOnline(0);
		dto20.setDisturb(0);
		dto20.setState(1);
		dto20.setFlag(1);
		dto20.setNickname("于惠茹");
		dto20.setPhoto("{\"result\":true,\"data\":{\"id\":70,\"nickname\":\"\\u4e8e\\u60e0\\u8339\",\"identity\":\"\\u77e5\\u540d\\u7f51\\u7ea2\\u535a\\u4e3b \\u5e73\\u9762\\u6a21\\u7279\",\"vcoinPerMinute\":16,\"level\":\"4\",\"fans\":420,\"focused\":0,\"avatar\":{\"id\":1807018,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/70_1513150024000_1toihh0h1p3cb28n6u7j\"},\"photos\":[{\"id\":1807018,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/70_1513150024000_1toihh0h1p3cb28n6u7j\"},{\"id\":1765723,\"width\":750,\"height\":750,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/6aKPpsCnic.jpg\"},{\"id\":1765724,\"width\":750,\"height\":750,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/Nmnh76zFCC.jpg\"},{\"id\":1765725,\"width\":750,\"height\":750,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/KCtm8W7Fty.jpg\"},{\"id\":1765726,\"width\":750,\"height\":750,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/KtW48HwRj5.jpg\"},{\"id\":1765727,\"width\":750,\"height\":750,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/m3wdZeHBSF.jpg\"},{\"id\":1765728,\"width\":750,\"height\":750,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/c5swEYpYWT.jpg\"}],\"figureTags\":[{\"id\":7,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u7f8e\\u5973\",\"color\":\"57bdfe\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":2,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":9,\"type\":1,\"name\":\"\\u58f0\\u97f3\\u8ff7\\u4eba\",\"color\":\"add721\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":8,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"tao\",\"contribution\":3526,\"userId\":88785,\"rank\":1,\"level\":8},{\"avatar\":{\"id\":1220963,\"width\":900,\"height\":900,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/120978_1505173772922_z220uw9e9gxthceinowz\"},\"nickname\":\"\\u5c0f\\u5965\\u7279\\u66fc\",\"contribution\":537,\"userId\":120978,\"rank\":2,\"level\":9},{\"avatar\":{\"id\":1903118,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/90223_1530887018000_qhkypk3nq2khkpwh2ylz\"},\"nickname\":\"\\u9e23\",\"contribution\":496,\"userId\":90223,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":6433,\"giftCount\":1,\"gifts\":[{\"id\":3,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/xmMZDeGB83.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto20.setSignature("你的小女友上线啦 快呼我");
		dto20.setIntro("知名网红博主 平面模特");
		dto20.setStature("164 cm");
		dto20.setWeight("46 kg");
		dto20.setZodiac("处女座");
		dto20.setCityId("131");
		dto20.setDial_num(18);
		dto20.setRecv_num(16);
		dto20.setEvaluation_num(14);
		dtos.add(dto20);
		
		AnchorDto dto21 = new AnchorDto();
		dto21.setUserid(65418701460995840L);
		dto21.setPrice(25);
		dto21.setStar(5);
		dto21.setOnline(3);
		dto21.setDisturb(0);
		dto21.setState(1);
		dto21.setFlag(1);
		dto21.setNickname("缘分");
		dto21.setPhoto("{\"result\":true,\"data\":{\"id\":164714,\"nickname\":\"\\u7f18\\u5206\",\"identity\":\"\\u77e5\\u540d\\u4e3b\\u64ad \\u5e73\\u9762\\u6a21\\u7279\",\"vcoinPerMinute\":25,\"level\":\"5\",\"fans\":26706,\"focused\":0,\"avatar\":{\"id\":1934821,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/C3Ajm6nHD7.jpg\"},\"photos\":[{\"id\":1934821,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/C3Ajm6nHD7.jpg\"},{\"id\":1850940,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/164714_1522129860000_416b7ozrfd56spz3g0kk\"},{\"id\":1875304,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/164714_1526614236000_hi3yyun53mzdsj82wmh8\"},{\"id\":1914168,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/164714_1532692599000_cu91e855svh9crsm4p4s\"},{\"id\":1914261,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/164714_1532698706000_o3yycdujzsvkoygah5bz\"},{\"id\":1918975,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/164714_1533516600000_edsh6ll5j21cri1lqq20\"},{\"id\":2112742,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/164714_1540965965000_fhsv99ztx3vhye9jsztn\"},{\"id\":2112821,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/164714_1540966915000_1h1r202u43m2yl40ps44\"}],\"figureTags\":[{\"id\":5,\"type\":1,\"name\":\"\\u59a9\\u5a9a\\u6027\\u611f\",\"color\":\"ff7a7a\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":11,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":16,\"type\":1,\"name\":\"\\u5236\\u670d\\u8bf1\\u60d1\",\"color\":\"46daed\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":14,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u6e9c\\u8fbe\",\"contribution\":131875,\"userId\":1485105,\"rank\":1,\"level\":13},{\"avatar\":{\"id\":1831299,\"width\":900,\"height\":900,\"sn\":140,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/523231_1517327374512_suszklh264ktkd2lskqj\"},\"nickname\":\"\\u53ea\\u7231\\u7f18\\u5206\",\"contribution\":93369,\"userId\":523231,\"rank\":2,\"level\":14},{\"avatar\":{\"id\":1901366,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/586827_1530591247000_r6987bkfcgmgbi17yf3i\"},\"nickname\":\"\\u966a\\u4f34\\u4f60\\u4e00\\u751f\",\"contribution\":62575,\"userId\":586827,\"rank\":3,\"level\":10}],\"isOneFree\":0,\"intimacyCount\":\"1380336\",\"giftCount\":4883,\"gifts\":[{\"id\":11,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/8w48JEwwMB1.png\"},{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[{\"acId\":7,\"rank\":3,\"num\":1,\"date\":1536571105,\"avatar\":{\"id\":74,\"width\":184,\"height\":184,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/rbtkTfCSzN.png\"}},{\"acId\":0,\"rank\":1,\"num\":1,\"date\":0,\"avatar\":{\"id\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/banner\\/weekstart1.png\",\"height\":0,\"width\":0}},{\"acId\":0,\"rank\":2,\"num\":1,\"date\":0,\"avatar\":{\"id\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/banner\\/weekstart2.png\",\"height\":0,\"width\":0}}]}}");
		dto21.setSignature("行走的荷尔蒙");
		dto21.setIntro("知名主播 平面模特");
		dto21.setStature("165 cm");
		dto21.setWeight("49 kg");
		dto21.setZodiac("金牛座");
		dto21.setCityId("289");
		dto21.setDial_num(1201);
		dto21.setRecv_num(1198);
		dto21.setEvaluation_num(802);
		dtos.add(dto21);
		
		AnchorDto dto22 = new AnchorDto();
		dto22.setUserid(65418701876231936L);
		dto22.setPrice(18);
		dto22.setStar(5);
		dto22.setOnline(3);
		dto22.setDisturb(0);
		dto22.setState(1);
		dto22.setFlag(1);
		dto22.setNickname("艾米");
		dto22.setPhoto("{\"result\":true,\"data\":{\"id\":66071,\"nickname\":\"\\u827e\\u7c73\",\"identity\":\"\\u5e73\\u9762\\u6a21\\u7279 \\u4e3b\\u64ad\",\"vcoinPerMinute\":18,\"level\":\"5\",\"fans\":19378,\"focused\":0,\"avatar\":{\"id\":1784894,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1509732432000_0ugp91xnr8vubrz7hjbt\"},\"photos\":[{\"id\":1784894,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1509732432000_0ugp91xnr8vubrz7hjbt\"},{\"id\":1904555,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1531155562000_eyw74mmpguske3kqcbtz\"},{\"id\":1904038,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1531074178000_8zdxr7g4b7dym2ynvxje\"},{\"id\":1888002,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1528527678000_iwu7pbga65a6bfokphlz\"},{\"id\":1888000,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1528527678000_75rqrxl3elikli1doul2\"},{\"id\":1888001,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1528527678000_62kjxo1xs9299k4v23ya\"},{\"id\":1887999,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/66071_1528527678000_w8347731gakiqr5qsjpb\"}],\"figureTags\":[{\"id\":16,\"type\":1,\"name\":\"\\u5236\\u670d\\u8bf1\\u60d1\",\"color\":\"46daed\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":14,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":26,\"type\":1,\"name\":\"\\u5b85\\u7537\\u5973\\u795e\",\"color\":\"ff6e64\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":3,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdqq.qlogo.cn\\/qqapp\\/101396968\\/E150C38B8BF2D77D7F3ACA50D7CBA18C\\/100\"},\"nickname\":\"\\u6155\\u5bb9\\u5929\\u590d2\",\"contribution\":100242,\"userId\":824737,\"rank\":1,\"level\":12},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"XS\",\"contribution\":23759,\"userId\":48895,\"rank\":2,\"level\":12},{\"avatar\":{\"id\":1910230,\"width\":900,\"height\":900,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1080856_1532100839614_i61mic5lfxc9g0ru1n44\"},\"nickname\":\"\\u8fdb\\u51fb\\u7684\\u5965\\u7279\\u66fc\",\"contribution\":22973,\"userId\":1080856,\"rank\":3,\"level\":9}],\"isOneFree\":0,\"intimacyCount\":1259457,\"giftCount\":2384,\"gifts\":[{\"id\":11,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/8w48JEwwMB1.png\"},{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto22.setSignature("撩拨你的心");
		dto22.setIntro("平面模特 主播");
		dto22.setStature("164 cm");
		dto22.setWeight("46 kg");
		dto22.setZodiac("双鱼座");
		dto22.setCityId("257");
		dto22.setDial_num(1921);
		dto22.setRecv_num(1900);
		dto22.setEvaluation_num(1268);
		dtos.add(dto22);
		
		AnchorDto dto23 = new AnchorDto();
		dto23.setUserid(65418702280982272L);
		dto23.setPrice(29);
		dto23.setStar(5);
		dto23.setOnline(2);
		dto23.setDisturb(0);
		dto23.setState(1);
		dto23.setFlag(1);
		dto23.setNickname("达丽娅");
		dto23.setPhoto("{\"result\":true,\"data\":{\"id\":492100,\"nickname\":\"\\u8fbe\\u4e3d\\u5a05\",\"identity\":\"\\u4e4c\\u514b\\u5170\\u4eba\\uff0c\\u82f1\\u8bed\\u8001\\u5e08\",\"vcoinPerMinute\":29,\"level\":\"5\",\"fans\":6059,\"focused\":0,\"avatar\":{\"id\":2136784,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1541322439000_faiv74ekfuim1jhexurm\"},\"photos\":[{\"id\":2136784,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1541322439000_faiv74ekfuim1jhexurm\"},{\"id\":1921583,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1534076614000_2jw08vflqttfwelr8sfc\"},{\"id\":1911576,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1532334175000_ptf9vw3hkyqpxqjnkhid\"},{\"id\":1917775,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1533281164000_deb45ler2dhy51lnj4ei\"},{\"id\":1870461,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1525716044000_qjh9ywcdkhkl46i8lmle\"},{\"id\":1921595,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1534079108000_9jd1ltsequjqbk9o8v05\"},{\"id\":2129650,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1541231514000_najimxro9kuuyzrlmtsb\"},{\"id\":1908580,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/492100_1531834612000_b3eqgj02hx5nkbapf7yi\"}],\"figureTags\":[{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":20,\"type\":1,\"name\":\"\\u60c5\\u611f\\u4e13\\u5bb6\",\"color\":\"ff78a0\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":1,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u5212\\u8239\\u4e0d\\u7528\\u6868\",\"contribution\":5053,\"userId\":1595762,\"rank\":1,\"level\":15},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u5357\\u57ce\\u5c0f\\u516b\\u4e49\",\"contribution\":3320,\"userId\":100914,\"rank\":2,\"level\":8},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/tvax3.sinaimg.cn\\/default\\/images\\/default_avatar_male_50.gif\"},\"nickname\":\"\\u7528\\u62375553378318\",\"contribution\":2187,\"userId\":1069361,\"rank\":3,\"level\":5}],\"isOneFree\":0,\"intimacyCount\":\"260744\",\"giftCount\":168,\"gifts\":[{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto23.setSignature("乌克兰人 会说中文");
		dto23.setIntro("乌克兰人 英语老师");
		dto23.setStature("178 cm");
		dto23.setWeight("57 kg");
		dto23.setZodiac("狮子座");
		dto23.setCityId("332");
		dto23.setDial_num(268);
		dto23.setRecv_num(260);
		dto23.setEvaluation_num(188);
		dtos.add(dto23);
		
		AnchorDto dto24 = new AnchorDto();
		dto24.setUserid(65418702671052544L);
		dto24.setPrice(29);
		dto24.setStar(5);
		dto24.setOnline(2);
		dto24.setDisturb(0);
		dto24.setState(1);
		dto24.setFlag(1);
		dto24.setNickname("苏可可");
		dto24.setPhoto("{\"result\":true,\"data\":{\"id\":67357,\"nickname\":\"\\u82cf\\u53ef\\u53ef\",\"identity\":\"\\u77e5\\u540d\\u5199\\u771f\\u6a21\\u7279\",\"vcoinPerMinute\":25,\"level\":\"5\",\"fans\":2539,\"focused\":0,\"avatar\":{\"id\":1841644,\"width\":1000,\"height\":1000,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/Wn45KjpfWM.jpg\"},\"photos\":[{\"id\":1841644,\"width\":1000,\"height\":1000,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/Wn45KjpfWM.jpg\"},{\"id\":1810348,\"width\":922,\"height\":1000,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/r5jCNQehTP.jpg\"},{\"id\":1841645,\"width\":1000,\"height\":1000,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/DyYKNCrZmz.jpg\"},{\"id\":1810355,\"width\":922,\"height\":1000,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/7CA2AER3YX.jpg\"},{\"id\":1810486,\"width\":1420,\"height\":1420,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/56ic3D2r42.jpg\"},{\"id\":1815586,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/67357_1514570547000_28z02d5eaxbrq27loxsv\"}],\"figureTags\":[{\"id\":6,\"type\":1,\"name\":\"\\u6e29\\u67d4\\u53ef\\u7231\",\"color\":\"82da35\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":10,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":23,\"type\":1,\"name\":\"\\u840c\\u59b9\\u7eb8\",\"color\":\"ffc456\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":17,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1777344,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/225877_1508385072000_2yp6u34gx5l648vectbu\"},\"nickname\":\"chodomatte\",\"contribution\":1314,\"userId\":225877,\"rank\":1,\"level\":4},{\"avatar\":{\"id\":1808924,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/401638_1513436350000_h2uil837ykbhsvrcigxg\"},\"nickname\":\"\\u5f20\\u5f3a\",\"contribution\":1130,\"userId\":401638,\"rank\":2,\"level\":6},{\"avatar\":{\"id\":1896527,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/825631_1529785604000_7vh75g1i2q85zrjpb5kt\"},\"nickname\":\"\\u6700\\u7231\\u5c0f\\u8089\\u8089\",\"contribution\":1013,\"userId\":825631,\"rank\":3,\"level\":7}],\"isOneFree\":0,\"intimacyCount\":20762,\"giftCount\":9,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto24.setSignature("乌克兰人 会说中文");
		dto24.setIntro("乌克兰人 英语老师");
		dto24.setStature("178 cm");
		dto24.setWeight("57 kg");
		dto24.setZodiac("狮子座");
		dto24.setCityId("131");
		dto24.setDial_num(268);
		dto24.setRecv_num(260);
		dto24.setEvaluation_num(188);
		dtos.add(dto24);
		
		AnchorDto dto25 = new AnchorDto();
		dto25.setUserid(65418703048539904L);
		dto25.setPrice(19);
		dto25.setStar(5);
		dto25.setOnline(1);
		dto25.setDisturb(1);
		dto25.setState(1);
		dto25.setFlag(1);
		dto25.setNickname("我是你阿紫");
		dto25.setPhoto("{\"result\":true,\"data\":{\"id\":295878,\"nickname\":\"\\u6211\\u662f\\u4f60\\u963f\\u7d2b\",\"identity\":\"\\u6dd8\\u5973\\u90ce \\u6a21\\u7279 \\u9ebb\\u8c46\",\"vcoinPerMinute\":19,\"level\":\"5\",\"fans\":5751,\"focused\":0,\"avatar\":{\"id\":2119651,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/295878_1541079593000_lqh8cpjoy25gbi19p4eh\"},\"photos\":[{\"id\":2119651,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/295878_1541079593000_lqh8cpjoy25gbi19p4eh\"},{\"id\":1876217,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/295878_1526763616000_48m0ykcwqu1ubcqj1uqu\"},{\"id\":1936040,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/295878_1537278726000_s801uuyubrj7dspzitfh\"},{\"id\":1946597,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/295878_1537555561000_lp9jgxpodfyq8m24s3id\"},{\"id\":1921293,\"width\":750,\"height\":750,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/HpCTGW5QWT.jpg\"},{\"id\":1966440,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/295878_1538067134000_mtpg9s4jeqvsmj03nu83\"}],\"figureTags\":[{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":26,\"type\":1,\"name\":\"\\u5b85\\u7537\\u5973\\u795e\",\"color\":\"ff6e64\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":3,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1971736,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1188_1538257085000_l57saa1srlmi6equ5a6i\"},\"nickname\":\"Max\\u3030\\ufe0f\",\"contribution\":10092,\"userId\":1188,\"rank\":1,\"level\":10},{\"avatar\":{\"id\":1827723,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/7208_1516696218000_ov82ohfgmblyen1irtni\"},\"nickname\":\"zz\",\"contribution\":9509,\"userId\":7208,\"rank\":2,\"level\":9},{\"avatar\":{\"id\":1,\"width\":0,\"height\":0,\"url\":\"http:\\/\\/thirdqq.qlogo.cn\\/qqapp\\/101396968\\/8B50B30A62D2671A6C6B53B5AC7ABF58\\/100\"},\"nickname\":\"\\u539d\\u8fb9\\u5934\\u5c3e\",\"contribution\":9389,\"userId\":942317,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":399419,\"giftCount\":535,\"gifts\":[{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto25.setSignature("可以了解一下");
		dto25.setIntro("淘女郎 模特 麻豆");
		dto25.setStature("168 cm");
		dto25.setWeight("50 kg");
		dto25.setZodiac("天秤座");
		dto25.setCityId("289");
		dto25.setDial_num(368);
		dto25.setRecv_num(360);
		dto25.setEvaluation_num(340);
		dtos.add(dto25);
		
		AnchorDto dto26 = new AnchorDto();
		dto26.setUserid(65418703377792768L);
		dto26.setPrice(39);
		dto26.setStar(5);
		dto26.setOnline(1);
		dto26.setDisturb(1);
		dto26.setState(1);
		dto26.setFlag(1);
		dto26.setNickname("梦心月");
		dto26.setPhoto("{\"result\":true,\"data\":{\"id\":63,\"nickname\":\"\\u68a6\\u5fc3\\u6708\",\"identity\":\"\\u77e5\\u540d\\u7f51\\u7ea2\\u535a\\u4e3b \\u5199\\u771f\\u6a21\\u7279\",\"vcoinPerMinute\":39,\"level\":\"5\",\"fans\":4873,\"focused\":0,\"avatar\":{\"id\":1859031,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/63_1523519485000_qs4my2xmjra65rubh9r8\"},\"photos\":[{\"id\":1859031,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/63_1523519485000_qs4my2xmjra65rubh9r8\"},{\"id\":1946800,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/63_1537568651000_6x4s7et4fqkvexbrucyj\"},{\"id\":1946973,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/63_1537575699000_tultrbqqi4dq4r15djzb\"},{\"id\":1946975,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/63_1537575699000_1dt1n91m8mvg7bvw3cbw\"},{\"id\":1946972,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/63_1537575699000_o5791wucya6og5eyxvzg\"},{\"id\":1946974,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/63_1537575699000_45jnh0vkb8msqb9s3utl\"}],\"figureTags\":[{\"id\":8,\"type\":1,\"name\":\"\\u4e1d\\u889c\\u7f8e\\u817f\",\"color\":\"21e7b9\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":9,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":10,\"type\":1,\"name\":\"\\u6c14\\u8d28\\u7f8e\\u5973\",\"color\":\"48df36\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":7,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1918087,\"width\":900,\"height\":900,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1480362_1533351103950_ene7amqsrd9zbwlsh48f\"},\"nickname\":\"\\u8fde\\u5929\\u4e4b\\u5cf0\",\"contribution\":1170,\"userId\":1480362,\"rank\":1,\"level\":8},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u897f\\u83ab\\u00b7\\u8840\\u6c57\\u6cea\",\"contribution\":708,\"userId\":539180,\"rank\":2,\"level\":3},{\"avatar\":{\"id\":1806044,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/351_1512988435000_5ax7cgem6s51evx45whv\"},\"nickname\":\"\\u9f3b\\u7c91\\u7c91\",\"contribution\":546,\"userId\":351,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":9709,\"giftCount\":6,\"gifts\":[{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"},{\"id\":6,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/mRa4siWNJy2.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto26.setSignature("我给你祝福 你给我保护");
		dto26.setIntro("知名网红博主 写真模特");
		dto26.setStature("175 cm");
		dto26.setWeight("50 kg");
		dto26.setZodiac("射手座");
		dto26.setCityId("131");
		dto26.setDial_num(12);
		dto26.setRecv_num(10);
		dto26.setEvaluation_num(8);
		dtos.add(dto26);
		
		AnchorDto dto27 = new AnchorDto();
		dto27.setUserid(65418703755280128L);
		dto27.setPrice(39);
		dto27.setStar(5);
		dto27.setOnline(0);
		dto27.setDisturb(0);
		dto27.setState(1);
		dto27.setFlag(1);
		dto27.setNickname("李丽莎");
		dto27.setPhoto("{\"result\":true,\"data\":{\"id\":33518,\"nickname\":\"\\u674e\\u4e3d\\u838e\",\"identity\":\"\\u77e5\\u540d\\u5199\\u771f\\u6a21\\u7279 \\u7f51\\u7ea2\\u535a\\u4e3b\",\"vcoinPerMinute\":39,\"level\":\"5\",\"fans\":11128,\"focused\":0,\"avatar\":{\"id\":1785451,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1509872093000_bvcodteuos7q3cx8uwkx\"},\"photos\":[{\"id\":1785451,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1509872093000_bvcodteuos7q3cx8uwkx\"},{\"id\":1788865,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1510507609000_ufr9tn8roscc5bxw4zym\"},{\"id\":1776711,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1508255519000_x6u4ko65tip8337yoy9d\"},{\"id\":1780880,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1509024959000_w6jz56hhc2c0drc5aj6t\"},{\"id\":1872550,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1526123600000_8xwwud6xf982d5z3t1f1\"},{\"id\":1872551,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1526123601000_wi4nb6i1up3nop2jo5j5\"},{\"id\":1780881,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1509024959000_vvgwtyc2yzuifhlr9w11\"},{\"id\":1872570,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/33518_1526125947000_lcc910vy0hk42flkaprk\"}],\"figureTags\":[{\"id\":16,\"type\":1,\"name\":\"\\u5236\\u670d\\u8bf1\\u60d1\",\"color\":\"46daed\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":14,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":22,\"type\":1,\"name\":\"\\u5973\\u6c49\\u5b50\",\"color\":\"ff968e\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":18,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1899435,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/517765_1530246887000_ll2eiwvq8qhfivyqbxx0\"},\"nickname\":\"lucky hao\",\"contribution\":9992,\"userId\":517765,\"rank\":1,\"level\":7},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"King\",\"contribution\":7084,\"userId\":157267,\"rank\":2,\"level\":12},{\"avatar\":{\"id\":1779976,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/245761_1508863643000_506p0vmfrx02tgbc8vzg\"},\"nickname\":\"City\",\"contribution\":4875,\"userId\":245761,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":236471,\"giftCount\":194,\"gifts\":[{\"id\":11,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/8w48JEwwMB1.png\"},{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto27.setSignature("傻白甜的莎莎 等你来玩");
		dto27.setIntro("知名写真模特 网红博主");
		dto27.setStature("172 cm");
		dto27.setWeight("45 kg");
		dto27.setZodiac("射手座");
		dto27.setCityId("131");
		dto27.setDial_num(118);
		dto27.setRecv_num(108);
		dto27.setEvaluation_num(88);
		dtos.add(dto27);
		
		AnchorDto dto28 = new AnchorDto();
		dto28.setUserid(65418704120184576L);
		dto28.setPrice(39);
		dto28.setStar(5);
		dto28.setOnline(0);
		dto28.setDisturb(0);
		dto28.setState(1);
		dto28.setFlag(1);
		dto28.setNickname("朱可儿");
		dto28.setPhoto("{\"result\":true,\"data\":{\"id\":46474,\"nickname\":\"\\u6731\\u53ef\\u513f\",\"identity\":\"2016\\u8d85\\u7ea7\\u5973\\u58f0\\u9009\\u624b \\u5b8c\\u7f8e\\u5047\\u671f\\u9009\\u624b\",\"vcoinPerMinute\":39,\"level\":\"5\",\"fans\":16770,\"focused\":0,\"avatar\":{\"id\":1765743,\"width\":750,\"height\":750,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/WjfNsTFh6f.jpg\"},\"photos\":[{\"id\":1765743,\"width\":750,\"height\":750,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/WjfNsTFh6f.jpg\"},{\"id\":1908180,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/46474_1531772691000_8t6i1n5wgu9qyia0zd9g\"},{\"id\":1917978,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/46474_1533310674000_u5dhbq1e0mrj99bss0lv\"},{\"id\":1907125,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/46474_1531579604000_gvlis2tgb8yv6ydyeml5\"},{\"id\":1908179,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/46474_1531772691000_ju2ign29dhntbttyko9c\"},{\"id\":1854656,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/46474_1522814815000_638qr575qdlj5g8yb987\"},{\"id\":1917977,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/46474_1533310674000_tzau3p4l4sra53oiujiu\"}],\"figureTags\":[{\"id\":10,\"type\":1,\"name\":\"\\u6c14\\u8d28\\u7f8e\\u5973\",\"color\":\"48df36\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":7,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":14,\"type\":1,\"name\":\"\\u6e05\\u7eaf\\u841d\\u8389\",\"color\":\"e691f5\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":12,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":2042599,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/359236_1539810297000_mzooacdce5ambi0i9wy2\"},\"nickname\":\"lll\",\"contribution\":3060,\"userId\":359236,\"rank\":1,\"level\":12},{\"avatar\":{\"id\":1871441,\"width\":900,\"height\":900,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/403442_1525896145644_amere6td0fw66xyqk7sw\"},\"nickname\":\"yyy\",\"contribution\":3003,\"userId\":403442,\"rank\":2,\"level\":12},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"guhguh\",\"contribution\":2988,\"userId\":58135,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":41481,\"giftCount\":28,\"gifts\":[{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"},{\"id\":7,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/jn2SyxHxXh.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto28.setSignature("这是很长快乐的一生");
		dto28.setIntro("2016超级女声选手 完美假期选手");
		dto28.setStature("165 cm");
		dto28.setWeight("46 kg");
		dto28.setZodiac("天秤座");
		dto28.setCityId("289");
		dto28.setDial_num(18);
		dto28.setRecv_num(16);
		dto28.setEvaluation_num(12);
		dtos.add(dto28);
		
		AnchorDto dto29 = new AnchorDto();
		dto29.setUserid(65418704462020352L);
		dto29.setPrice(49);
		dto29.setStar(5);
		dto29.setOnline(0);
		dto29.setDisturb(0);
		dto29.setState(1);
		dto29.setFlag(1);
		dto29.setNickname("孟晓艺dana");
		dto29.setPhoto("{\"result\":true,\"data\":{\"id\":86491,\"nickname\":\"\\u5b5f\\u6653\\u827adana\",\"identity\":\"\\u751c\\u7f8e\\u7cfb\\u5b85\\u7537\\u5973\\u795e\",\"vcoinPerMinute\":49,\"level\":\"5\",\"fans\":58587,\"focused\":0,\"avatar\":{\"id\":2139492,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/M5AYKAsGSx.jpg\"},\"photos\":[{\"id\":2139492,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/M5AYKAsGSx.jpg\"},{\"id\":1802662,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/86491_1512460223000_3l4jmyn1ed85d57s5j9t\"},{\"id\":1926001,\"width\":1125,\"height\":1124,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/wYBtja7jmB.jpg\"},{\"id\":1929677,\"width\":750,\"height\":750,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/RZbnXSe6CK.jpg\"},{\"id\":1929661,\"width\":750,\"height\":750,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/EziHdcpGEx.jpg\"},{\"id\":1926667,\"width\":750,\"height\":750,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/Wc6sZ32ebX.jpg\"},{\"id\":1926668,\"width\":750,\"height\":750,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/zDbQNtpGpH.jpg\"},{\"id\":1926669,\"width\":750,\"height\":750,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/QtArDGBBZK.jpg\"}],\"figureTags\":[{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":26,\"type\":1,\"name\":\"\\u5b85\\u7537\\u5973\\u795e\",\"color\":\"ff6e64\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":3,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":2034912,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1194_1539672492000_5wvyn8ay1b58pzni4q41\"},\"nickname\":\"\\u5b5f\\u6653\\u827a\\u7537\\u53cb\",\"contribution\":132083,\"userId\":1194,\"rank\":1,\"level\":13},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"\\u5212\\u8239\\u4e0d\\u7528\\u6868\",\"contribution\":103042,\"userId\":1595762,\"rank\":2,\"level\":15},{\"avatar\":{\"id\":1939680,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/166153_1537394286000_fvr1cralv9kmtwxujmkh\"},\"nickname\":\"\\u5b88\\u62a4\\u6653\\u827a\",\"contribution\":35845,\"userId\":166153,\"rank\":3,\"level\":9}],\"isOneFree\":0,\"intimacyCount\":1248423,\"giftCount\":2094,\"gifts\":[{\"id\":11,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/8w48JEwwMB1.png\"},{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":9,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/Rts4YWs3PH1.png\"}],\"inducement\":\"\\u770b\\u6211\\u90a3\\u4e48\\u4e45\\uff0c\\u559c\\u6b22\\u6211\\u5417\\uff1f\\u8981\\u4e0d\\u6211\\u4eec...\",\"inducementActTime\":5,\"honor\":[{\"acId\":12,\"rank\":1,\"num\":1,\"date\":1537157203,\"avatar\":{\"id\":114,\"width\":140,\"height\":90,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/ecBmMSmGPA.png\"}},{\"acId\":0,\"rank\":1,\"num\":5,\"date\":0,\"avatar\":{\"id\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/banner\\/weekstart1.png\",\"height\":0,\"width\":0}}]}}");
		dto29.setSignature("全国第一网红 能撩你一下吗");
		dto29.setIntro("甜美系宅男女神");
		dto29.setStature("173 cm");
		dto29.setWeight("45 kg");
		dto29.setZodiac("处女座");
		dto29.setCityId("248");
		dto29.setDial_num(246);
		dto29.setRecv_num(240);
		dto29.setEvaluation_num(224);
		dtos.add(dto29);
		
		AnchorDto dto30 = new AnchorDto();
		dto30.setUserid(65418704805953280L);
		dto30.setPrice(18);
		dto30.setStar(5);
		dto30.setOnline(0);
		dto30.setDisturb(0);
		dto30.setState(1);
		dto30.setFlag(1);
		dto30.setNickname("若涵");
		dto30.setPhoto("{\"result\":true,\"data\":{\"id\":1017388,\"nickname\":\"\\u82e5\\u6db5\",\"identity\":\"\\u7f51\\u7ea2 \\u6a21\\u7279\",\"vcoinPerMinute\":18,\"level\":\"5\",\"fans\":8229,\"focused\":1,\"avatar\":{\"id\":1920539,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1533817035000_5jcfhr8skkt9zh55zizj\"},\"photos\":[{\"id\":1920539,\"width\":720,\"height\":720,\"sn\":0,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1533817035000_5jcfhr8skkt9zh55zizj\"},{\"id\":1916439,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1533041858000_1kc9dpp6z2y5cjsrd5o5\"},{\"id\":1928499,\"width\":720,\"height\":720,\"sn\":2,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1535723049000_g9wvrvaw42ybw2p5hcfy\"},{\"id\":1890450,\"width\":720,\"height\":720,\"sn\":3,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1528869958000_o3fehw7g3146abn5g26i\"},{\"id\":1868113,\"width\":720,\"height\":720,\"sn\":4,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1525246734000_rq7pbb93849ml6o435js\"},{\"id\":1868118,\"width\":720,\"height\":720,\"sn\":5,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1525246735000_e57dzv6ur2ybep9h4zsg\"},{\"id\":1868114,\"width\":720,\"height\":720,\"sn\":6,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1525246734000_sqkcdm39iwpwvp7sjhul\"},{\"id\":1907080,\"width\":720,\"height\":720,\"sn\":7,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/1017388_1531573192000_30an1by7tdg8qicfkqwl\"}],\"figureTags\":[{\"id\":10,\"type\":1,\"name\":\"\\u6c14\\u8d28\\u7f8e\\u5973\",\"color\":\"48df36\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":7,\"is_love\":1,\"meiliao_color\":\"ff2f79\"},{\"id\":11,\"type\":1,\"name\":\"\\u5b8c\\u7f8e\\u8eab\\u6750\",\"color\":\"ff8baf\",\"cdate\":\"2017-09-07 13:54:33\",\"isuse\":1,\"sn\":6,\"is_love\":1,\"meiliao_color\":\"ff2f79\"}],\"intimacy\":[{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"y\\u00eau\\u82e5\\u6db5\",\"contribution\":62037,\"userId\":1144459,\"rank\":1,\"level\":10},{\"avatar\":{\"id\":1,\"width\":512,\"height\":512,\"sn\":-1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/default_avatar.jpg\"},\"nickname\":\"Lee\",\"contribution\":20517,\"userId\":283308,\"rank\":2,\"level\":11},{\"avatar\":{\"id\":1836253,\"width\":720,\"height\":720,\"sn\":1,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/image\\/641309_1519120915000_2uxvbvdzgr696g6ddood\"},\"nickname\":\"\\u53fc\\u7a3b\\u660e\\u56db\",\"contribution\":11393,\"userId\":641309,\"rank\":3,\"level\":8}],\"isOneFree\":0,\"intimacyCount\":824842,\"giftCount\":1768,\"gifts\":[{\"id\":11,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/8w48JEwwMB1.png\"},{\"id\":10,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/J4zk8pbxRm1.png\"},{\"id\":8,\"width\":165,\"height\":165,\"url\":\"http:\\/\\/cdn.vliao1.xyz\\/prod\\/resource\\/f5naYEC4kz1.png\"}],\"inducement\":\"\",\"inducementActTime\":5,\"honor\":[]}}");
		dto30.setSignature("我的气质等你来驾驭");
		dto30.setIntro("网红 模特");
		dto30.setStature("165 cm");
		dto30.setWeight("43 kg");
		dto30.setZodiac("处女座");
		dto30.setCityId("132");
		dto30.setDial_num(626);
		dto30.setRecv_num(620);
		dto30.setEvaluation_num(588);
		dtos.add(dto30);
		

		//处理生成基础数据

		for (AnchorDto dto : dtos) {
			//创建对应的主播表中的信息
			AnchorOnlineEntity anchor = new AnchorOnlineEntity();
			anchor.setUserid(dto.getUserid());
			anchor.setStar(dto.getStar());
			anchor.setPrice(dto.getPrice());
			anchor.setOnline(dto.getOnline());
			anchor.setState(1);
			anchor.setFlag(1);
			anchor.setDisturb(dto.getDisturb());
			anchor.setNickname(dto.getNickname());
			anchor.setSignature(dto.getSignature());
			anchor.setIntro(dto.getIntro());
			anchor.setStature(dto.getStature());
			anchor.setWeight(dto.getWeight());
			anchor.setZodiac(dto.getZodiac());
			anchor.setCityid(Tools.parseLong(dto.getCityId()));
			
			//时间相关信息
			anchor.setCreate_time(new Date());
			anchor.setUpdate_time(new Date());
			anchor.setAudit_time(new Date());
			
			anchor.setDial_num(dto.getDial_num());
			anchor.setRecv_num(dto.getRecv_num());
			
			anchorOnlineContract.insert(anchor);
			Thread.sleep(6*1000);
		}



		//处理生成对应的头像数据
		for (AnchorDto dto : dtos) {
			JSONObject json = JsonHelper.toJsonObject(dto.getPhoto());
			JSONObject data = json.getJSONObject("data");
			JSONObject avatar = data.getJSONObject("avatar");
			String url = avatar.getString("url");
			if(Tools.isNotNull(url)) {
				String fileExt = "jpg";
				String picture = getFileName("user/photo", fileExt);
				boolean issuccess =  HttpUtils.downloadFile(url, picture);
				if(issuccess) {
					PageModel pageModel = PageModel.getPageModel();
					pageModel.addQuery(Restrictions.eq("userid", dto.getUserid()));
					Map<String, Object> updateStatement = new HashMap<>();
					updateStatement.put("photo", picture);
					anchorOnlineContract.updateByCondition(updateStatement, pageModel);
				}				
			}
			Thread.sleep(4000);
		}

		
		//处理下载相册数据
		for (AnchorDto dto : dtos) {
			JSONObject json = JsonHelper.toJsonObject(dto.getPhoto());
			JSONObject data = json.getJSONObject("data");
			if(Tools.isNotNull(data)) {
				JSONArray photos = data.getJSONArray("photos");
				if(Tools.isNotNull(photos) && photos.size() > 0) {
					for(int i = 0; i < photos.size(); i++) {
						JSONObject photo = photos.getJSONObject(i);
						if(Tools.isNotNull(photo)) {
							//启动下载对应的图片处理
							String url = photo.getString("url");
							String fileExt = "jpg";
							String picture = getFileName("user/photos", fileExt);
							boolean issuccess =  HttpUtils.downloadFile(url, picture);
							if(issuccess) {
								UserPhotoResourceEntity t = new UserPhotoResourceEntity();
								t.setCreate_time(new Date());
								t.setUserid(dto.getUserid());
								t.setPath(picture);
								t.setState(1);
								t.setUpdate_time(new Date());
								userPhotoResourceContract.insert(t);
								//下载完一个等待3s
								Thread.sleep(3000);
							}
						}
					}
				}
			}
		}
		
		//处理拨打电话和评论相关的处理
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addLimitField(2, 200);
		pageModel.addQuery(Restrictions.lt("fr", 3));
		pageModel.addQuery(Restrictions.eq("sex", 1));
		pageModel.addQuery(Restrictions.lt("create_time", "2018-09-14 00:00:00"));
		pageModel.desc("create_time");
		List<UserEntity> users = userContract.load(pageModel);
		
		PageModel label = PageModel.getPageModel();
		label.addLimitField(2, 200);
		label.addQuery(Restrictions.eq("type", 2));
		label.addQuery(Restrictions.le("id", 36));
		List<AppLabelEntity> labels = appLabelContract.load(label);
		System.err.println(labels.size());
		if(Tools.isNotNull(users) && users.size() > 0 && Tools.isNotNull(labels) && labels.size() > 0) {
			for (AnchorDto dto : dtos) {
				for(int i=0; i < dto.getEvaluation_num();i++ ) {
					int index = getRandomNumber(0, users.size()-1);
					UserEntity user = users.get(index);
					//设置评论
					//获取对应的随机评论信息
					List<Long> evaluationIdList = new ArrayList<>();
					int num = getRandomNumber(1, 2);
					if(num == 1) {
						int labid = getRandomNumber(0, labels.size()-1);
						evaluationIdList.add(labels.get(labid).getId());
					} else {
						int labid = getRandomNumber(0, labels.size()-8);
						for(int j=0; j<num; j++) {
							if(j==0) {
								evaluationIdList.add(labels.get(labid).getId());
							} else {
								labid = labid+getRandomNumber(1, 7);
								evaluationIdList.add(labels.get(labid).getId());
							}
						}
					}
					
					vChatVideoYXService.anchorEvaluation(user.getId(), dto.getUserid(),2, evaluationIdList,"");
					//设置亲密榜数据
					int money = getRandomNumber(10, 2500);
					anchorIntimateRankingsContract.addIncome(dto.getUserid(), user.getId(), money);
				}
			}
		}

		
		//生成对应的视频数据

		for (AnchorDto dto : dtos) {
			//获取对应的目录
			File base = new File("E:\\data\\"+dto.getUserid());
			if(base.isDirectory()) {
				File[] files = base.listFiles();
				if(Tools.isNotNull(files) && files.length > 0) {
					for (File file : files) {
						String save = getFileName("user/video", "mp4");
						System.err.println(save);
						boolean isUpload = upYunCloudStorage.writeFile(save, file, true);
						if(isUpload) {
							//上传成功 进行截图操作处理
							String savePath = getFileName("user/video/snap", "jpg");
							boolean isSnapshot = upYunCloudStorage.mediaSnapshot(save, savePath, "00:00:00");
							if(isSnapshot) {
								File uploadPicture = new File("E:\\data1\\" + savePath);
								boolean isUploadPicture =  upYunCloudStorage.readFile(savePath, uploadPicture);
								if(isUploadPicture) {
									FileUploadResult result = Helper.uploadFile(new FileInputStream(file), save);
									if(result.getCode() == 0) {
										//将对应的视频插入到短视频列表中
										ShortVideoEntity t = new ShortVideoEntity();
										t.setUserid(dto.getUserid());
										t.setVideo_path(save);
										t.setVideo_photo(savePath);
										t.setStatus(1);
										t.setVideo_praise(getRandomNumber(300, 800));
										t.setWatch_num(getRandomNumber(900, 1500));
										t.setCreate_time(new Date());
										t.setUpdate_time(new Date());
										shortVideoContract.insert(t);
									}
								}
							}
						}
					}
				}
			}
		}

		
		/*
		for (AnchorDto dto : dtos) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", dto.getUserid()));
			Map<String, Object> updateStatement = new HashMap<>();
			updateStatement.put("evaluation_num", dto.getEvaluation_num());
			anchorOnlineContract.updateByCondition(updateStatement, pageModel);
		}
		*/
	}
	
	//@Test
	public void createApplyAnchorInfoTest() throws Exception{
		List<AnchorDto> dtos = new ArrayList<>();

		AnchorDto dto1 = new AnchorDto();
		dto1.setUserid(65418712749965056L);
		dto1.setStar(3);
		dto1.setPrice(8);
		dto1.setState(-9);
		dto1.setOnline(3);
		dto1.setDisturb(0);
		dto1.setCityId("131");
		dtos.add(dto1);
		
		AnchorDto dto2 = new AnchorDto();
		dto2.setUserid(65418713056149248L);
		dto2.setStar(3);
		dto2.setPrice(6);
		dto2.setState(-9);
		dto2.setOnline(3);
		dto2.setDisturb(0);
		dto2.setCityId("131");
		dtos.add(dto2);
		
		AnchorDto dto3 = new AnchorDto();
		dto3.setUserid(65418713444122368L);
		dto3.setStar(3);
		dto3.setPrice(8);
		dto3.setState(-9);
		dto3.setOnline(2);
		dto3.setDisturb(0);
		dto3.setCityId("289");
		dtos.add(dto3);
		
		AnchorDto dto4 = new AnchorDto();
		dto4.setUserid(65418713834192640L);
		dto4.setStar(3);
		dto4.setPrice(10);
		dto4.setState(-9);
		dto4.setOnline(1);
		dto4.setDisturb(1);
		dto4.setCityId("131");
		dtos.add(dto4);
		
		AnchorDto dto5 = new AnchorDto();
		dto5.setUserid(65418714222165760L);
		dto5.setStar(3);
		dto5.setPrice(8);
		dto5.setState(-9);
		dto5.setOnline(1);
		dto5.setDisturb(1);
		dto5.setCityId("179");
		dtos.add(dto5);
		
		AnchorDto dto6 = new AnchorDto();
		dto6.setUserid(65418714612236032L);
		dto6.setStar(3);
		dto6.setPrice(5);
		dto6.setState(-9);
		dto6.setOnline(0);
		dto6.setDisturb(0);
		dto6.setCityId("131");
		dtos.add(dto6);
		
		AnchorDto dto7 = new AnchorDto();
		dto7.setUserid(65418715012792064L);
		dto7.setStar(4);
		dto7.setPrice(16);
		dto7.setState(-9);
		dto7.setOnline(3);
		dto7.setDisturb(0);
		dto7.setCityId("131");
		dtos.add(dto7);
		
		AnchorDto dto8 = new AnchorDto();
		dto8.setUserid(65418715644034816L);
		dto8.setStar(4);
		dto8.setPrice(14);
		dto8.setState(-9);
		dto8.setOnline(2);
		dto8.setDisturb(0);
		dto8.setCityId("332");
		dtos.add(dto8);
		
		AnchorDto dto9 = new AnchorDto();
		dto9.setUserid(65418716067659520L);
		dto9.setStar(4);
		dto9.setPrice(12);
		dto9.setState(-9);
		dto9.setOnline(1);
		dto9.setDisturb(1);
		dto9.setCityId("332");
		dtos.add(dto9);
		
		AnchorDto dto10 = new AnchorDto();
		dto10.setUserid(65418716405300992L);
		dto10.setStar(4);
		dto10.setPrice(18);
		dto10.setState(-9);
		dto10.setOnline(1);
		dto10.setDisturb(1);
		dto10.setCityId("289");
		dtos.add(dto10);
		
		AnchorDto dto11 = new AnchorDto();
		dto11.setUserid(65418716772302592L);
		dto11.setStar(4);
		dto11.setPrice(12);
		dto11.setState(-9);
		dto11.setOnline(0);
		dto11.setDisturb(0);
		dto11.setCityId("131");
		dtos.add(dto11);
		
		AnchorDto dto12 = new AnchorDto();
		dto12.setUserid(65418717112041216L);
		dto12.setStar(4);
		dto12.setPrice(16);
		dto12.setState(-9);
		dto12.setOnline(0);
		dto12.setDisturb(0);
		dto12.setCityId("289");
		dtos.add(dto12);
		
		AnchorDto dto13 = new AnchorDto();
		dto13.setUserid(65418717500014336L);
		dto13.setStar(5);
		dto13.setPrice(22);
		dto13.setState(-9);
		dto13.setOnline(3);
		dto13.setDisturb(0);
		dto13.setCityId("75");
		dtos.add(dto13);
		
		AnchorDto dto14 = new AnchorDto();
		dto14.setUserid(65418717902667520L);
		dto14.setStar(5);
		dto14.setPrice(26);
		dto14.setState(-9);
		dto14.setOnline(3);
		dto14.setDisturb(0);
		dto14.setCityId("224");
		dtos.add(dto14);
		
		AnchorDto dto15 = new AnchorDto();
		dto15.setUserid(65418718194171648L);
		dto15.setStar(5);
		dto15.setPrice(24);
		dto15.setState(-9);
		dto15.setOnline(2);
		dto15.setDisturb(0);
		dto15.setCityId("257");
		dtos.add(dto15);
		
		AnchorDto dto16 = new AnchorDto();
		dto16.setUserid(65418718835900160L);
		dto16.setStar(5);
		dto16.setPrice(22);
		dto16.setState(-9);
		dto16.setOnline(1);
		dto16.setDisturb(1);
		dto16.setCityId("131");
		dtos.add(dto16);
		
		AnchorDto dto17 = new AnchorDto();
		dto17.setUserid(65418719110627072L);
		dto17.setStar(5);
		dto17.setPrice(24);
		dto17.setState(-9);
		dto17.setOnline(1);
		dto17.setDisturb(1);
		dto17.setCityId("75");
		dtos.add(dto17);
		
		AnchorDto dto18 = new AnchorDto();
		dto18.setUserid(65418719477628672L);
		dto18.setStar(5);
		dto18.setPrice(26);
		dto18.setState(-9);
		dto18.setOnline(0);
		dto18.setDisturb(0);
		dto18.setCityId("179");
		dtos.add(dto18);
		
		AnchorDto dto19 = new AnchorDto();
		dto19.setUserid(65418719867698944L);
		dto19.setStar(5);
		dto19.setPrice(28);
		dto19.setState(-9);
		dto19.setOnline(0);
		dto19.setDisturb(0);
		dto19.setCityId("131");
		dtos.add(dto19);
		
		PageModel label = PageModel.getPageModel();
		label.addQuery(Restrictions.eq("type", 1));
		List<AppLabelEntity> labels = appLabelContract.load(label);
		
		for (AnchorDto dto : dtos) {
			//处理基本数据
			UserBO user = userAgent.findById(dto.getUserid());
			//处理扩展数据
			UserExtensionBO extension = userExtensionAgent.findByUserId(dto.getUserid());
			if(Tools.isNotNull(user) && Tools.isNotNull(extension)) {
				AnchorOnlineEntity anchor = new AnchorOnlineEntity();
				anchor.setUserid(dto.getUserid());
				anchor.setStar(dto.getStar());
				anchor.setPrice(dto.getPrice());
				anchor.setOnline(dto.getOnline());
				anchor.setState(-9);
				anchor.setFlag(1);
				anchor.setDisturb(dto.getDisturb());
				
				anchor.setNickname(user.getNickname());
				anchor.setPhoto(user.getPhoto());
				anchor.setSignature(user.getSignature());
				anchor.setStature(extension.getStature()+" cm");
				anchor.setWeight(extension.getWeight()+ " kg");
				anchor.setZodiac(extension.getZodiac());
				anchor.setCityid(Tools.parseLong(dto.getCityId()));
				
				//创建对应的自评标签
				JSONArray evaluationIdList = new JSONArray();
				if(Tools.isNotNull(labels)) {
					int num = getRandomNumber(1, 2);
					if(num == 1) {
						int labid = getRandomNumber(0, labels.size()-1);
						AppLabelEntity l = labels.get(labid);
						JSONObject object = new JSONObject();
						object.put("color", l.getColor());
						object.put("id", l.getId());
						object.put("desc", l.getName());
						evaluationIdList.add(object);
					} else {
						int labid = getRandomNumber(0, labels.size()-10);
						for(int j=0; j<num; j++) {
							if(j==0) {
								AppLabelEntity l = labels.get(labid);
								JSONObject object = new JSONObject();
								object.put("color", l.getColor());
								object.put("id", l.getId());
								object.put("desc", l.getName());
								evaluationIdList.add(object);
							} else {
								labid = labid+getRandomNumber(1, 9);
								AppLabelEntity l = labels.get(labid);
								JSONObject object = new JSONObject();
								object.put("color", l.getColor());
								object.put("id", l.getId());
								object.put("desc", l.getName());
								evaluationIdList.add(object);
							}
						}
					}
				}
				
				if(Tools.isNotNull(evaluationIdList) && evaluationIdList.size() > 0) {
					anchor.setLabel(evaluationIdList.toJSONString());
				}
				//时间相关信息
				anchor.setCreate_time(new Date());
				anchor.setUpdate_time(new Date());
				anchor.setAudit_time(new Date());
				anchorOnlineContract.insert(anchor);
				
				Thread.sleep(3000);
			}
		}
	}
	
	//@Test
	public void createSingelAnchorInfoTest() throws Exception{
		PageModel label = PageModel.getPageModel();
		label.addQuery(Restrictions.eq("type", 1));
		List<AppLabelEntity> labels = appLabelContract.load(label);
		
		AnchorDto dto = new AnchorDto();
		dto.setUserid(87152674235023616L);
		dto.setStar(3);
		dto.setPrice(8);
		dto.setState(1);
		dto.setOnline(0);
		dto.setDisturb(0);
		dto.setCityId("131");
		
		//处理基本数据
		UserBO user = userAgent.findById(dto .getUserid());
		//处理扩展数据
		UserExtensionBO extension = userExtensionAgent.findByUserId(dto.getUserid());
		if(Tools.isNotNull(user) && Tools.isNotNull(extension)) {
			AnchorOnlineEntity anchor = new AnchorOnlineEntity();
			anchor.setUserid(dto.getUserid());
			anchor.setStar(dto.getStar());
			anchor.setPrice(dto.getPrice());
			anchor.setOnline(dto.getOnline());
			anchor.setState(-9);
			anchor.setFlag(1);
			anchor.setDisturb(dto.getDisturb());
			
			anchor.setNickname(user.getNickname());
			anchor.setPhoto(user.getPhoto());
			anchor.setSignature(user.getSignature());
			anchor.setStature(extension.getStature()+" cm");
			anchor.setWeight(extension.getWeight()+ " kg");
			anchor.setZodiac(extension.getZodiac());
			anchor.setCityid(Tools.parseLong(dto.getCityId()));
			
			//创建对应的自评标签
			JSONArray evaluationIdList = new JSONArray();
			if(Tools.isNotNull(labels)) {
				int num = getRandomNumber(1, 2);
				if(num == 1) {
					int labid = getRandomNumber(0, labels.size()-1);
					AppLabelEntity l = labels.get(labid);
					JSONObject object = new JSONObject();
					object.put("color", l.getColor());
					object.put("id", l.getId());
					object.put("desc", l.getName());
					evaluationIdList.add(object);
				} else {
					int labid = getRandomNumber(0, labels.size()-10);
					for(int j=0; j<num; j++) {
						if(j==0) {
							AppLabelEntity l = labels.get(labid);
							JSONObject object = new JSONObject();
							object.put("color", l.getColor());
							object.put("id", l.getId());
							object.put("desc", l.getName());
							evaluationIdList.add(object);
						} else {
							labid = labid+getRandomNumber(1, 9);
							AppLabelEntity l = labels.get(labid);
							JSONObject object = new JSONObject();
							object.put("color", l.getColor());
							object.put("id", l.getId());
							object.put("desc", l.getName());
							evaluationIdList.add(object);
						}
					}
				}
			}
			
			if(Tools.isNotNull(evaluationIdList) && evaluationIdList.size() > 0) {
				anchor.setLabel(evaluationIdList.toJSONString());
			}
			//时间相关信息
			anchor.setCreate_time(new Date());
			anchor.setUpdate_time(new Date());
			anchor.setAudit_time(new Date());
			anchorOnlineContract.insert(anchor);
			
		}
	}
	
	//@Test
	public void createAnchorInfoUplodTest() throws Exception{
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("id", 1111111111111L));
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
	
	/**
	 * 手动处理视频资源上传到云存储
	 *  注意:操作时需要操作配置文件改成对应的线上又拍云的账号
	 * @throws Exception
	 */
	@Test
	public void uploadAnchorVideoTest() throws Exception{
		//获取对应的目录
		File dir = new File("F:\\upload\\user\\video\\2018\\11\\14");
		String base="/upload/user/video/2018/11/14/";
		if(dir.isDirectory()) {
			File[] files = dir.listFiles();
			if(Tools.isNotNull(files) && files.length > 0) {
				for (File file : files) {
					//String save = getFileName("user/video", "mp4");
					String save = base+file.getName();
					System.err.println(save);
					boolean isUpload = upYunCloudStorage.writeFile(save, file, true);
					if(isUpload) {
						System.err.println("上传成功");
					} else {
						System.err.println("上传失败:"+file.getName());
					}
				}
			}
		}
	}
	
	/**
	 * 手动处理视频资源上传到云存储
	 *  注意:操作时需要操作配置文件改成对应的线上又拍云的账号
	 * @throws Exception
	 */
	@Test
	public void anchorVideoTest() throws Exception{
		File base = new File("E:\\data");
		if(base.isDirectory()) {
			File[] files = base.listFiles();
			if(Tools.isNotNull(files) && files.length > 0) {
				//获取对应的用户目录
				for (File file : files) {
					String userid = file.getName();
					File dirFile = new File("E:\\data\\"+userid);
					if(dirFile.isDirectory()) {
						File[] fs = dirFile.listFiles();
						if(Tools.isNotNull(fs) && fs.length > 0) {
							for (File f : fs) {
								String save = getFileName("user/video", "mp4");
								System.err.println(save);
								boolean isUpload = upYunCloudStorage.writeFile(save, f, true);
								if(isUpload) {
									//上传成功 进行截图操作处理
									String savePath = getFileName("user/video/snap", "jpg");
									boolean isSnapshot = upYunCloudStorage.mediaSnapshot(save, savePath, "00:00:00");
									if(isSnapshot) {
										File uploadPicture = new File("E:\\data1\\" + savePath);
										boolean isUploadPicture =  upYunCloudStorage.readFile(savePath, uploadPicture);
										if(isUploadPicture) {
											FileUploadResult result = Helper.uploadFile(new FileInputStream(f), save);
											if(result.getCode() == 0) {
												//将对应的视频插入到短视频列表中
												ShortVideoEntity t = new ShortVideoEntity();
												t.setUserid(Long.parseLong(userid));
												t.setVideo_path(save);
												t.setVideo_photo(savePath);
												t.setStatus(-2);
												t.setVideo_praise(getRandomNumber(20, 80));
												t.setWatch_num(getRandomNumber(100, 300));
												t.setCreate_time(new Date());
												t.setUpdate_time(new Date());
												shortVideoContract.insert(t);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//@Test
	public void updatePhotoTest() throws Exception{
		anchorOnlineContract.updateAnchorInfo(85157397089288448L, null, "/upload/user/2018/11/14/1542185337909_2341.png");
	}
	
	
	public static String getFileName(String directory, String fileExt) {
		String filePath = Helper.getUploadFilePath(directory)+Helper.getUploadFileName(fileExt);
		return filePath;
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
}
