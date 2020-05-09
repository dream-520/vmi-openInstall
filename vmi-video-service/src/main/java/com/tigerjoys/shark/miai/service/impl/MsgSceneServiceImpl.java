package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.ScenceMessageTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorRobotSendGiftContract;
import com.tigerjoys.shark.miai.inter.contract.IAppDailScenceContract;
import com.tigerjoys.shark.miai.inter.contract.IAppMsgSceneDetailContract;
import com.tigerjoys.shark.miai.inter.contract.IAppMsgSceneUserContract;
import com.tigerjoys.shark.miai.inter.contract.IAppStartSendMsgContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorRobotSendGiftEntity;
import com.tigerjoys.shark.miai.inter.entity.AppDailScenceEntity;
import com.tigerjoys.shark.miai.inter.entity.AppMsgSceneDetailEntity;
import com.tigerjoys.shark.miai.inter.entity.AppMsgSceneUserEntity;
import com.tigerjoys.shark.miai.inter.entity.AppStartSendMsgEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.service.IMsgSceneService;

/**
 * 用户与假主播的场景对话消息
 * @author shiming
 */
@Service
public class MsgSceneServiceImpl implements IMsgSceneService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static Map<Integer, List<String>> scenes = new HashMap<Integer, List<String>>();
	
	//场景对话id
	List<Integer> msgScene = new ArrayList<>();
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;

	@Autowired
	private IRedFlowerAgent redFlowerAgent;
	
	@Autowired
	private IAppMsgSceneDetailContract appMsgSceneDetailContract;
	
	@Autowired
	private IAppMsgSceneUserContract appMsgSceneUserContract;
	
	@Autowired
	private IAppStartSendMsgContract appStartSendMsgContract;
	
	@Autowired
	private IAnchorRobotSendGiftContract anchorRobotSendGiftContract;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private IUserChatGiftContract userChatGiftContract;
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IAppDailScenceContract appDailScenceContract;
	
	@PostConstruct
	public void init() {
		List<String> scene01 = new ArrayList<>();
		scene01.add("哈喽！我是北方人，你可以看看我资料，了解下，觉得合适可以认识一下?");
		scene01.add("你做什么的呀！");
		scene01.add("挺不错的工作呀");
		scene01.add("明人不说暗话，我喜欢你。");
		scene01.add("你一般业余时间干嘛呢？");
		scene01.add("视频聊天来一发吗？");		
		scenes.put(1, scene01);
		
		List<String> scene02 = new ArrayList<>();
		scene02.add("在吗？认识一下");
		scene02.add("哥哥我可爱吗？");
		scene02.add("来找我视频吧");
		scene02.add("在不，小哥哥在干嘛呀");
		scene02.add("想找个人认真谈一场恋爱");
		scene02.add("视频聊天不");
		scenes.put(2, scene02);
		
		List<String> scene03 = new ArrayList<>();
		scene03.add("你好，可以认识一下吗？");
		scene03.add("哥哥看表演不？");
		scene03.add("视频吗？聊你想聊，看你想看的");
		scene03.add("你晚上有安排吗？");
		scene03.add("小哥哥，需要妹子陪吗？");
		scenes.put(3, scene03);
		
		List<String> scene04 = new ArrayList<>();
		scene04.add("你认为多久亲热一次算正常呀？");
		scene04.add("那也太久了吧");
		scene04.add("喜欢暖男，你是我的暖男吗？");
		scene04.add("小哥哥是哪里人呀？");
		scene04.add("亲爱的来个视频吧");
		scenes.put(4, scene04);
		
		List<String> scene05 = new ArrayList<>();
		scene05.add("帅锅，在干嘛那？");
		scene05.add("我喜欢旅行，喜欢认识新的朋友，你去过哪些地方呢？");
		scene05.add("我工作挺稳定的，收入也还可以，希望找个懂我的人。");
		scene05.add("我是你喜欢的类型吗？");
		scene05.add("小哥哥，我想和你视频聊天。");
		scenes.put(5, scene05);
		
		List<String> scene06 = new ArrayList<>();
		scene06.add("嗨，感觉你不像没女朋友的呢，是来寻找刺激的吗？");
		scene06.add("你什么时候用这个软件的呀？");
		scene06.add("我也刚刚用没多久额");
		scene06.add("你喜欢什么样的女生呢");
		scenes.put(6, scene06);
		
		List<String> scene07 = new ArrayList<>();
		scene07.add("有没有时间聊一聊？");
		scene07.add("好无聊啊，可以开视频陪我聊聊天吗？");
		scene07.add("玩视频吗？妹妹可以帮小哥哥解决寂寞，不聊天");
		scene07.add("视频可以聊你想聊的，看你想看的");
		scenes.put(7, scene07);
		
		List<String> scene08 = new ArrayList<>();
		scene08.add("嗨，在干什么呢？");
		scene08.add("我是双子座女孩，你喜欢双子座女孩吗？");
		scene08.add("哈哈……你这太快了吧。");
		scene08.add("不过我觉得我挺喜欢你的个性的，直爽，你是什么星座啊？");
		scenes.put(8, scene08);
		
		List<String> scene09 = new ArrayList<>();
		scene09.add("好无聊呀~");
		scene09.add("有人在吗？");
		scene09.add("你喜欢什么样的女孩呀？");
		scene09.add("我是温柔型的女生，你喜欢吗？");
		scene09.add("嗨，交个朋友好吗？");
		scenes.put(9, scene09);
		
		List<String> scene10 = new ArrayList<>();
		scene10.add("你好");
		scene10.add("哪里人呀");
		scene10.add("你是干什么的，我是做护士的？");
		scene10.add("希望有个机会大家能彼此了解一下");
		scenes.put(10, scene10);
		
		List<String> scene11 = new ArrayList<>();
		scene11.add("在吗？");
		scene11.add("能和你聊一会天吗？");
		scene11.add("你相信一见钟情吗？");
		scene11.add("那可以视频聊天吗？");
		scenes.put(11, scene11);
		
		List<String> scene12 = new ArrayList<>();
		scene12.add("在吗？");
		scene12.add("我喜欢暖男，你是我的暖男吗");
		scene12.add("今天心情不好，你能陪我聊会儿天吗");
		scene12.add("感觉你人还不错");
		scene12.add("我们视频聊天吧");
		scenes.put(12, scene12);
		
		List<String> scene13 = new ArrayList<>();
		scene13.add("你好？");
		scene13.add("晚上有什么安排吗？");
		scene13.add("可以陪我聊会儿天吗？");
		scene13.add("好无聊啊");
		scene13.add("我们可以视频的");
		scenes.put(13, scene13);
		
		List<String> scene14 = new ArrayList<>();
		scene14.add("在吗？你多大了，家里催结婚吗？");
		scene14.add("我喜欢温柔的大叔，你是吗？");
		scene14.add("年纪大会照顾人，我喜欢年纪大的");
		scene14.add("可以视频聊天吗？");
		scenes.put(14, scene14);
		
		List<String> scene15 = new ArrayList<>();
		scene15.add("想聊聊吗？一个人不寂寞吗？");
		scene15.add("你在干嘛啊");
		scene15.add("你喜欢我这种类型的吗？");
		scene15.add("哈哈……你好直接啊。");
		scene15.add("要不然视频聊个天？");
		scenes.put(15, scene15);
		
		List<String> scene16 = new ArrayList<>();
		scene16.add("在吗？");
		scene16.add("我是狮子座女孩，你喜欢狮子座女孩吗？");
		scene16.add("好烦躁啊，心情好差");
		scene16.add("工作不开心");
		scene16.add("你可以陪我聊会儿天吗？");
		scenes.put(16, scene16);
		
		List<String> scene17 = new ArrayList<>();
		scene17.add("看你挺好的，咱们聊聊吧~");
		scene17.add("你多大了，我今年24岁，你呢？");
		scene17.add("小哥哥");
		scene17.add("我想你陪我聊会儿天可以吗");
		scene17.add("我们视频吧");
		scenes.put(17, scene17);
		
		List<String> scene18 = new ArrayList<>();
		scene18.add("Hi，你喜欢的动物吗？");
		scene18.add("家里面养狗吗？");
		scene18.add("我很喜欢宠物额");
		scene18.add("养宠物都比较有爱心");
		scene18.add("我觉得你还挺好的");
		scene18.add("我们可以视频一下吗");
		scenes.put(18, scene18);
		
		List<String> scene19 = new ArrayList<>();
		scene19.add("平时在家里都做些什么呢?");
		scene19.add("我比较喜欢宅在家里");
		scene19.add("我这种宅女你会喜欢吗？");
		scene19.add("你是宅男吗？");
		scene19.add("我都不怎么爱出去");
		scene19.add("要不然视频聊个天？");
		scenes.put(19, scene19);
		
		List<String> scene20 = new ArrayList<>();
		scene20.add("你好，可以认识一下你吗？");
		scene20.add("你喜欢看什么电影？");
		scene20.add("我喜欢看恐怖片，你愿意陪我看吗？");
		scene20.add("哈哈，你是不是怕了");
		scene20.add("要不然视频聊个天？");
		scenes.put(20, scene20);
		
		List<String> scene21 = new ArrayList<>();
		scene21.add("晚上有安排吗？");
		scene21.add("我好无聊，想有个人陪");
		scene21.add("你喜欢我这种类型的吗？");
		scene21.add("可以陪我聊会儿天吗");
		scene21.add("要不然视频聊个天？");
		scenes.put(21, scene21);
		
		List<String> scene22 = new ArrayList<>();
		scene22.add("好寂寞呀");
		scene22.add("可以陪我聊聊吗？");
		scene22.add("我是天秤女孩，你喜欢天秤座女孩吗？");
		scene22.add("哈哈……你这太快了吧。");
		scene22.add("不过我觉得我挺喜欢你的个性的，直爽，你是什么星座啊？");
		scene22.add("你喜欢我这种类型的吗？");
		scene22.add("要不然视频聊个天？");
		scenes.put(22, scene22);
		
		List<String> scene23 = new ArrayList<>();
		scene23.add("今晚就我一个人哦~");
		scene23.add("聊点刺激的~");
		scene23.add("你想聊什么都可以");
		scene23.add("聊你想聊的，看你像看的");
		scene23.add("可以视频的");
		scenes.put(23, scene23);
		
		List<String> scene24 = new ArrayList<>();
		scene24.add("想不想聊些刺激的话题呢？");
		scene24.add("哈哈，聊什么都可以啊");
		scene24.add("你玩这个软件多久了");
		scene24.add("我也刚玩，不太会");
		scene24.add("我们可以视频聊天");
		scenes.put(24, scene24);
		
		List<String> scene25 = new ArrayList<>();
		scene25.add("你什么时候用这个软件的呀？");
		scene25.add("我也刚下载啊");
		scene25.add("你是找女朋友啊，还是交朋友？");
		scene25.add("你喜欢什么样的女生呢？");
		scene25.add("要不然视频聊个天？");
		scenes.put(25, scene25);
		
		List<String> scene26 = new ArrayList<>();
		scene26.add("你会在这个上面约吗？");
		scene26.add("我不是什么人都约的");
		scene26.add("哈哈……你这太快了吧。");
		scene26.add("要不然先视频聊聊，看下有没有眼缘？");
		scenes.put(26, scene26);
		
		List<String> scene27 = new ArrayList<>();
		scene27.add("你好啊，");
		scene27.add("咱俩聊一下呗");
		scene27.add("我是双子座女孩，你喜欢双子座女孩吗？");
		scene27.add("不过我觉得我挺喜欢你的个性的，直爽，你是什么星座啊？");
		scene27.add("你喜欢我这种类型的吗？");
		scene27.add("要不然视频聊个天？");
		scenes.put(27, scene27);
		
		List<String> scene28 = new ArrayList<>();
		scene28.add("你喜欢什么类型的女生啊？");
		scene28.add("漂亮的，温柔的？御姐？");
		scene28.add("你喜欢我这种类型的吗？");
		scene28.add("我觉得自己还可以，应该不会让你失望");
		scene28.add("要不然视频聊个天？");
		scenes.put(28, scene28);
		
		List<String> scene29 = new ArrayList<>();
		scene29.add("聊吗？");
		scene29.add("我喜欢运动，喜欢健身，你喜欢运动吗");
		scene29.add("可以一起去健身");
		scene29.add("你会喜欢我这种类型的吗？");
		scene29.add("要不然视频聊个天？");
		scenes.put(29, scene29);
		
		List<String> scene30 = new ArrayList<>();
		scene30.add("你在吗？");
		scene30.add("我这个刚下载，还不太会用");
		scene30.add("你能陪我来聊会儿天马");
		scene30.add("我喜欢暖男，喜欢温柔的，脾气好的，你是我喜欢的类型吗");
		scene30.add("我可以和视频聊天吗");
		scenes.put(30, scene30);
		
		List<String> scene31 = new ArrayList<>();
		scene31.add("你多大呀，我25岁");
		scene31.add("你做什么的？");
		scene31.add("小哥哥，我这人有点笨笨的，你喜欢我这样的吗？");
		scene31.add("我对你印象还不错");
		scene31.add("要不然视频聊个天？");
		scenes.put(31, scene31);
		
		List<String> scene32 = new ArrayList<>();
		scene32.add("我们交个朋友吧");
		scene32.add("我喜欢你的眼睛");
		scene32.add("我觉得你的眼睛好好看");
		scene32.add("你陪我聊会儿天吧");
		scene32.add("要不我们视频怎么样");
		scenes.put(32, scene32);
		
		List<String> scene33 = new ArrayList<>();
		scene33.add("你好，在吗？");
		scene33.add("想和你聊一下");
		scene33.add("我是来找男朋友的，你呢？");
		scene33.add("希望遇到合适的");
		scene33.add("视频聊一下吧？");
		scenes.put(33, scene33);
		
		List<String> scene34 = new ArrayList<>();
		scene34.add("小哥哥，今天陪我聊会吧");
		scene34.add("聊什么都行，今天有点烦");
		scene34.add("你给我讲笑话吧");
		scene34.add("我喜欢听黄色段子");
		scenes.put(34, scene34);
		
		List<String> scene35 = new ArrayList<>();
		scene35.add("平时都玩什么软件呢？");
		scene35.add("我也会玩，玩的比较少");
		scene35.add("我这个刚下载，还不太会用");
		scene35.add("你能陪我来聊会儿天马");
		scene35.add("要不我们视频吧");
		scenes.put(35, scene35);
		
		List<String> scene36 = new ArrayList<>();
		scene36.add("Hi，小哥哥你好");
		scene36.add("你是做什么工作的啊");
		scene36.add("噢噢，好厉害啊");
		scene36.add("我还在上学，准备找实习，你能我一些建议吗？");
		scene36.add("你在这上面是找女朋友吗？");
		scene36.add("你喜欢什么样的女生啊？");
		scene36.add("我能和你视频聊天吗？");
		scenes.put(36, scene36);
		
		List<String> scene37 = new ArrayList<>();
		scene37.add("最近挺烦的，能够讲个笑话给我听吗？");
		scene37.add("我喜欢听带点颜色的笑话");
		scene37.add("喜欢听污的段子");
		scene37.add("哈哈");
		scene37.add("可以陪我视频聊会儿天吗？");
		scenes.put(37, scene37);
		
		List<String> scene38 = new ArrayList<>();
		scene38.add("你一般几点睡觉？");
		scene38.add("要是无聊可以找我聊天的。");
		scene38.add("我一般睡的比较晚");
		scene38.add("想在这上面看看有没有合适的男生");
		scene38.add("你喜欢什么类型的？");
		scene38.add("视频聊一下？");
		scenes.put(38, scene38);
		
		List<String> scene39 = new ArrayList<>();
		scene39.add("Hi ,在干嘛啊");
		scene39.add("小哥哥多大了啊？");
		scene39.add("我喜欢年龄比我大的");
		scene39.add("年纪大的会疼人，会照顾我");
		scene39.add("我比较粘人");
		scene39.add("能视频聊下吗？");
		scenes.put(39, scene39);
		
		List<String> scene40 = new ArrayList<>();
		scene40.add("好无聊呀");
		scene40.add("在干嘛啊");
		scene40.add("你喜欢什么样的女孩啊？");
		scene40.add("能陪我聊聊天吗？");
		scenes.put(40, scene40);
		
		List<String> scene41 = new ArrayList<>();
		scene41.add("嗨，小哥哥你好");
		scene41.add("你平时都去哪儿玩？");
		scene41.add("我比较宅，一般都在家里");
		scene41.add("我可以跟你视频吗");
		scenes.put(41, scene41);
		
		List<String> scene42 = new ArrayList<>();
		scene42.add("好呀，你是哪个城市的，咱们认识一下。");
		scene42.add("我在北京，你在哪里？");
		scene42.add("噢噢");
		scene42.add("你是找女朋友嘛？");
		scene42.add("你喜欢什么样的女孩啊");
		scene42.add("可以跟你视频聊天吗");
		scenes.put(42, scene42);
		
		List<String> scene43 = new ArrayList<>();
		scene43.add("在吗？我一个人好寂寞~");
		scene43.add("你相信一见钟情吗？");
		scene43.add("想聊点刺激的");
		scene43.add("你知道吗？爱一个人是具体的，具体到18cm");
		scene43.add("我的两个头，只有你能摸。");
		scene43.add("我想让你陪我睡觉，然后我可以尽情的放声大叫。");
		scenes.put(43, scene43);
		
		List<String> scene44 = new ArrayList<>();
		scene44.add("天秤女一枚，你对天秤女有好感吗？");
		scene44.add("我相信缘分，你相信缘分吗？");
		scene44.add("所以啊，我们在这里能聊天也是一种缘分");
		scene44.add("你业余时间都喜欢做什么呢？");
		scene44.add("可以视频聊吗？");
		scenes.put(44, scene44);
		
		List<String> scene45 = new ArrayList<>();
		scene45.add("有没有人说过你眼睛很好看");
		scene45.add("但我觉得你的眼睛没有我的眼睛好看");
		scene45.add("因为我眼睛里有你啊");
		scene45.add("要不要一起视频啊");
		scene45.add("我想和你聊天呀");
		scenes.put(45, scene45);
		
		List<String> scene46 = new ArrayList<>();
		scene46.add("你好，帅哥？");
		scene46.add("小哥哥，能给我讲个笑话吗？");
		scene46.add("小哥哥，我们可以视频聊天吗？");
		scene46.add("你为什么不找我聊天啊，小哥哥，我对你很有眼缘的呀");
		scenes.put(46, scene46);
		
		List<String> scene47 = new ArrayList<>();
		scene47.add("每天都这样，无聊死了。");
		scene47.add("想找个人认真谈一场恋爱，很高兴认识你");
		scene47.add("你喜欢什么样的女生呢？有哪些条件？");
		scene47.add("我喜欢成熟的，有责任感的，孝顺的男生，你是这样的吗？");
		scene47.add("如果聊的来的话，可以见面。");
		scene47.add("可以先视频聊一下吗？");
		scenes.put(47, scene47);
		
		List<String> scene48 = new ArrayList<>();
		scene48.add("hi，帅哥你好？。");
		scene48.add("谢谢夸奖。");
		scene48.add("哈哈，好直接");
		scene48.add("真的嘛？");
		scene48.add("");
		scene48.add("我能跟你视频聊一下嘛");
		scenes.put(48, scene48);
		
		List<String> scene49 = new ArrayList<>();
		scene49.add("我是双子座女孩，你喜欢双子座女孩吗？");
		scene49.add("哈哈……你这太快了吧。");
		scene49.add("不过我觉得我挺喜欢你的个性的，直爽，你是什么星座啊？");
		scene49.add("你喜欢我这种类型的吗？");
		scene49.add("要不然视频聊个天？");
		scenes.put(49, scene49);
		
		List<String> scene50 = new ArrayList<>();
		scene50.add("我是学生，今年快毕业了，你呢?");
		scene50.add("你做什么工作的呀");
		scene50.add("好厉害呀");
		scene50.add("找了一个UI设计实习。");
		scene50.add("是的。......");
		scene50.add("想不想视频聊天啊，来视频吧");
		scenes.put(50, scene50);
		
		//初始化50个聊天场景的id值
		for(int i=1; i<=50; i++) {
			msgScene.add(i);
		}
	}
	
	@Override
	public void defineMsgScene(long userid) {
		try {
			UserBO bo = userAgent.findById(userid);
			//非主播 
			if(Tools.isNotNull(bo) && !bo.isWaiter()) {
				long flowers = redFlowerAgent.getRedFlowerBalance(userid);
				//检测对应用户的小红花数量
				if(flowers <= 5) {
					//首先确定已经使用过的虚拟主播id
					PageModel pageModel = PageModel.getPageModel();
					pageModel.addQuery(Restrictions.eq("from_userid", userid));
					//当前场景消息没有发送完成
					//pageModel.addQuery(Restrictions.eq("state", 0));
					List<AppMsgSceneDetailEntity> mScenes = appMsgSceneDetailContract.load(pageModel);
					List<Long> vUserid = null;
					if(Tools.isNotNull(mScenes)) {
						vUserid = new ArrayList<>();
						for (AppMsgSceneDetailEntity scene : mScenes) {
							vUserid.add(scene.getTo_userid());
						}
					}
					
					//然后确认两个可以使用的虚拟主播id值
					int scene1 = 0;
					int scene2 = 0;
					//获取虚拟主播id值
					long robotId1 = 0;
					long robotId2 = 0;
					pageModel.clearAll();
					pageModel.addQuery(Restrictions.eq("state", 1));
					if(Tools.isNotNull(vUserid) && vUserid.size() > 0)
						pageModel.addQuery(Restrictions.notin("userid", vUserid));
					pageModel.addQuery(Restrictions.eq("sex", 2));
					pageModel.addQuery(Restrictions.sql("flag=4 order by rand() limit 2"));
					List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
					logger.error("场景消息提示   获取到随机主播数量:"+anchors.size());
					if(Tools.isNotNull(anchors) && anchors.size() == 2) {
						//获取虚拟主播id值
						robotId1 = anchors.get(0).getUserid();
						robotId2 = anchors.get(1).getUserid();
						//确定使用的两个场景消息
						List<Integer> copy = null;
						AppMsgSceneUserEntity userSceneMsg = appMsgSceneUserContract.lockByUserId(userid);
						if(Tools.isNotNull(userSceneMsg)) {
							int sendFlag = 1;
							//检测是否需要发送消息状态值
							if(sendFlag == 1) {
								String indexs = userSceneMsg.getScene();
								//获取数据库中尚未发送的消息id信息
								if(Tools.isNotNull(indexs) && indexs.length() > 0) {
									//获取对应的随机消息的索引位置
									copy = Tools.splitToIntegerList(indexs);
								}
								//处理剩余消息索引数量不足的问题
								if(Tools.isNull(copy) || copy.size() < 2) {
									copy = new ArrayList<>(msgScene);
								}
								int index = getRandomNumber(0,copy.size()-1);
								scene1 = copy.get(index);
								copy.remove(index);
								index = getRandomNumber(0, copy.size() - 1);
								scene2 = copy.get(index);
								copy.remove(index);
								userSceneMsg.setVtime(new Date());
								userSceneMsg.setScene(Tools.joinString(copy));
								appMsgSceneUserContract.update(userSceneMsg);
							}
						} else {
							copy = new ArrayList<>(msgScene);
							int index = getRandomNumber(0, copy.size() - 1);
							scene1 = copy.get(index);
							copy.remove(index);
							index = getRandomNumber(0, copy.size() - 1);
							scene2 = copy.get(index);
							copy.remove(index);
							//首次需要创建对应的用户场景记录信息
							AppMsgSceneUserEntity t = new AppMsgSceneUserEntity();
							t.setCount(2);
							t.setScene(Tools.joinString(copy));
							t.setUserid(userid);
							t.setVtime(new Date());
							t.setCreate_time(new Date());
							appMsgSceneUserContract.insert(t);
							logger.info("确定的场景1:"+ scene1 + " 确定的场景2:"+scene2);
						}
					}
					
					logger.error("场景消息提示   获取到的场景索引为1:"+scene1+" 2:"+scene2);
					logger.error("场景消息提示   获取到的虚拟主播为1:"+robotId1+" 2:"+robotId2);
					if(scene1 > 0) {
						List<String> scenesM = scenes.get(scene1);
						if(Tools.isNotNull(scenesM) && scenesM.size() > 0) {
							//创建对应的场景对话详情
							AppMsgSceneDetailEntity t = new AppMsgSceneDetailEntity();
							t.setFrom_userid(userid);
							t.setTo_userid(robotId1);
							t.setScene(scene1);
							t.setItem(1);
							t.setState(0);
							t.setSend_time(new Date());
							t.setCreate_time(new Date());
							appMsgSceneDetailContract.insert(t);
							//进行设定需要发送的消息
							long time = new Date().getTime();
							AppStartSendMsgEntity entity = new AppStartSendMsgEntity();
							entity.setFromId(robotId1);
							entity.setToId(userid);
							entity.setMsg(scenesM.get(0));
							entity.setSend_time(time + getRandomNumber(1, 4)*1000);
							appStartSendMsgContract.insert(entity);
						}
					}
					
					if(scene2 > 0) {
						List<String> scenesM = scenes.get(scene2);
						if(Tools.isNotNull(scenesM) && scenesM.size() > 0) {
							//创建对应的场景对话详情
							AppMsgSceneDetailEntity t = new AppMsgSceneDetailEntity();
							t.setFrom_userid(userid);
							t.setTo_userid(robotId2);
							t.setScene(scene2);
							t.setItem(1);
							t.setState(0);
							t.setSend_time(new Date());
							t.setCreate_time(new Date());
							appMsgSceneDetailContract.insert(t);
							//进行设定需要发送的消息
							long time = new Date().getTime();
							AppStartSendMsgEntity entity = new AppStartSendMsgEntity();
							entity.setFromId(robotId2);
							entity.setToId(userid);
							entity.setMsg(scenesM.get(0));
							entity.setSend_time(time + getRandomNumber(5, 10)*1000);
							appStartSendMsgContract.insert(entity);
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}

	@Override
	public void sendMsgScene(long userid, long otherid) {
		ExecutorServiceHelper.executor.execute(new MsgSceneThread(userid, otherid));
	}

	private class MsgSceneThread implements Runnable {

		private long userid;
		
		private long otherid;
		
		public MsgSceneThread(long userid, long otherid) {
			this.userid = userid;
			this.otherid = otherid;
		}
		
		@Override
		public void run() {
			try {
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("from_userid", userid));
				pageModel.addQuery(Restrictions.eq("to_userid", otherid));
				//搜索已经发送过首条场景消息
				pageModel.addQuery(Restrictions.ne("item", 0));
				//当前场景消息没有发送完成
				pageModel.addQuery(Restrictions.eq("state", 0));
				List<AppMsgSceneDetailEntity> list = appMsgSceneDetailContract.load(pageModel);
				logger.error("场景消息提示  获取到对应场景消息数量:"+list.size());
				if(Tools.isNotNull(list) && list.size() > 0) {
					//进入此处说明有对应的场景消息
					AppMsgSceneDetailEntity entity = list.get(0);
					//取出当前需要发送的消息索引位置
					int item = entity.getItem();
					int sceneId = entity.getScene();
					//获取对应的场景消息
					List<String> scene = scenes.get(sceneId);
					logger.error("场景消息提示  场景值:"+sceneId);
					if(Tools.isNotNull(scene) && scene.size() > 0) {
						if(item > scene.size() -1) {
							//设置本次的场景消息已经完成
							entity.setState(1);
							appMsgSceneDetailContract.update(entity);
						} else {
							String message = scene.get(item);
							logger.error("场景消息提示 已经进入发送场景消息逻辑:"+message);
							if(Tools.isNotNull(message)) {
								PageModel msgPageModel = PageModel.getPageModel();
								msgPageModel.addPageField(0, 1);
								msgPageModel.addQuery(Restrictions.eq("fromId", userid));
								msgPageModel.addQuery(Restrictions.eq("toId", otherid));
								msgPageModel.desc("send_time");
								List<AppStartSendMsgEntity> msgs = appStartSendMsgContract.load(msgPageModel);
								long time = new Date().getTime();
								if(Tools.isNotNull(msgs) && msgs.size() > 0) {
									time = msgs.get(0).getSend_time();
								}
								int sce = getRandomNumber(6,9);
								long sendTime = time + sce * 1000;
								AppStartSendMsgEntity t = new AppStartSendMsgEntity();
								t.setFromId(otherid);
								t.setToId(userid);
								t.setMsg(message);
								t.setSend_time(sendTime);
								appStartSendMsgContract.insert(t);
							}
							
							//检测场景消息是否已经发送到第三条了  触发进行送礼物操作处理
							if(item == 3) {
								//触发发送礼物操作处理
								checkSendGift(userid, otherid);
							}
							
							//设置下一条需要发送的场景消息索引值
							item = item + 1;
							entity.setItem(item);
							//进一步检测对应的条目是否已经发送完成
							if(item == scene.size())
								entity.setState(1);
							appMsgSceneDetailContract.update(entity);
						}
					}
				}
			} catch (Exception e) {
				
			}
		}
		
		public void checkSendGift(long userid, long otherid) {
			try {
				//检测今天是否已经进行发送礼物操作处理
				String date = Tools.getDate();
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("userid", userid));
				pageModel.addQuery(Restrictions.eq("send_date", date));
				long count = anchorRobotSendGiftContract.count(pageModel);
				if(count == 0) {
					//触发发送消息和礼物的处理
					//2 花  7 摸摸哒
					//晚1s不进行发送
					Thread.sleep(1000);
					int rand = getRandomNumber(0, 10);
					int index = getRandomNumber(0, 9);
					if(rand >= 5) {
						//送花
						UserChatGiftEntity gift = userChatGiftContract.findById(7);
						if(Tools.isNotNull(gift)) {
							neteaseAgent.sendGiftPic(otherid, userid, gift.getName(), gift.getIcon(), gift.getId(),true);
						}
						if(index <=3) {
							neteaseAgent.pushOneMessage(otherid, userid, "给你送个小红花吧，和你聊挺好的", true);
						} else if(index <=6){
							neteaseAgent.pushOneMessage(otherid, userid, "送个花给你，哈哈", true);
						}
					} else {
						//送摸摸哒
						UserChatGiftEntity gift = userChatGiftContract.findById(2);
						if(Tools.isNotNull(gift)) {
							neteaseAgent.sendGiftPic(otherid, userid, gift.getName(), gift.getIcon(), gift.getId(),true);
						}
						if(index <= 3) {
							neteaseAgent.pushOneMessage(otherid, userid, "给你送个么么哒吧", true);
						} else if(index <= 6) {
							neteaseAgent.pushOneMessage(otherid, userid, "送个吻给你，喜欢吗", true);
						}
					}
					AnchorRobotSendGiftEntity t = new AnchorRobotSendGiftEntity();
					t.setUserid(userid);
					t.setSend_date(Tools.getDate(date));
					t.setCreate_time(new Date());
					anchorRobotSendGiftContract.insert(t );
				}
			} catch (Exception e) {
				
			}
		}

	}

	@Override
	public void checkAutoInMsgScene(long userid, long anchorid) {
		try {
			UserBO bo = userAgent.findById(userid);
			//非主播 
			if(Tools.isNotNull(bo) && !bo.isWaiter()) {
				long flowers = redFlowerAgent.getRedFlowerBalance(userid);
				//检测对应用户的小红花数量
				if(flowers <= 5) {
					AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", anchorid);
					//对应的主播是否假主播
					if(Tools.isNotNull(anchor) && anchor.getFlag().intValue() > 0  && anchor.getFlag().intValue() < 100) {
						ExecutorServiceHelper.executor.execute(new UserAutoMsgSceneThread(userid, anchorid));
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	private class UserAutoMsgSceneThread implements Runnable {

		private long userid;
		
		private long otherid;
		
		public UserAutoMsgSceneThread(long userid, long otherid) {
			this.userid = userid;
			this.otherid = otherid;
		}
		
		@Override
		public void run() {
			try {
				//检测两者之间是否有过场景对话  没有场景对话触发一个场景对话
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("from_userid", userid));
				pageModel.addQuery(Restrictions.eq("to_userid", otherid));
				List<AppMsgSceneDetailEntity> list = appMsgSceneDetailContract.load(pageModel);
				logger.error("场景消息提示  获取到对应场景消息数量:"+list.size());
				if(Tools.isNull(list) || list.size() == 0) {
					int scene = -1;
					List<Integer> copy = null;
					AppMsgSceneUserEntity userSceneMsg = appMsgSceneUserContract.lockByUserId(userid);
					if(Tools.isNotNull(userSceneMsg)) {
						String indexs = userSceneMsg.getScene();
						//获取数据库中尚未发送的消息id信息
						if(Tools.isNotNull(indexs) && indexs.length() > 0) {
							//获取对应的随机消息的索引位置
							copy = Tools.splitToIntegerList(indexs);
						}
						//处理剩余消息索引数量不足的问题
						if(Tools.isNull(copy) || copy.size() < 2) {
							copy = new ArrayList<>(msgScene);
						}
						int index = getRandomNumber(0,copy.size()-1);
						scene = copy.get(index);
						copy.remove(index);
						userSceneMsg.setVtime(new Date());
						userSceneMsg.setScene(Tools.joinString(copy));
						appMsgSceneUserContract.update(userSceneMsg);
						logger.info("22确定的新场景为:"+ scene);
					} else {
						copy = new ArrayList<>(msgScene);
						int index = getRandomNumber(0, copy.size() - 1);
						scene = copy.get(index);
						copy.remove(index);
						//首次需要创建对应的用户场景记录信息
						AppMsgSceneUserEntity t = new AppMsgSceneUserEntity();
						t.setCount(1);
						t.setScene(Tools.joinString(copy));
						t.setUserid(userid);
						t.setVtime(new Date());
						t.setCreate_time(new Date());
						appMsgSceneUserContract.insert(t);
						logger.info("11确定的新场景为:"+ scene);
					}
					
					if(scene >= 0) {
						List<String> scenesM = scenes.get(scene);
						if(Tools.isNotNull(scenesM) && scenesM.size() > 0) {
							//创建对应的场景对话详情
							AppMsgSceneDetailEntity t = new AppMsgSceneDetailEntity();
							t.setFrom_userid(userid);
							t.setTo_userid(otherid);
							t.setScene(scene);
							t.setItem(1);
							t.setState(0);
							t.setSend_time(new Date());
							t.setCreate_time(new Date());
							appMsgSceneDetailContract.insert(t);
							//进行设定需要发送的消息
							long time = new Date().getTime();
							AppStartSendMsgEntity entity = new AppStartSendMsgEntity();
							entity.setFromId(otherid);
							entity.setToId(userid);
							entity.setMsg(scenesM.get(0));
							entity.setSend_time(time + getRandomNumber(1, 2)*1000);
							appStartSendMsgContract.insert(entity);
						}
					}
				}
			} catch (Exception e) {
				
			}
		}
		
	}

	@Override
	public void sendNewMsgScene(long userid, long otherid) {
		try {
				long flower = redFlowerAgent.getRedFlowerDeposit(userid);
				long diamond = userDiamondAgent.getDiamondDeposit(userid);
				if(flower < 5 && diamond < 200) {
					PageModel pageModel = PageModel.getPageModel();
					pageModel.addQuery(Restrictions.eq("userid", userid));
					List<AppDailScenceEntity> list = appDailScenceContract.load(pageModel);
					if(Tools.isNull(list) || list.size() < 15) {
						Map<Integer, Integer> scences = new HashMap<>();
						boolean send = true;
						if(Tools.isNotNull(list)) {
							for (AppDailScenceEntity item : list) {
								if(item.getAnchorid() == otherid) {
									send = false;
									break;
								}
								scences.put(item.getScence(), item.getScence());
							}
						}
						if(send) {
							List<Integer> sc = new ArrayList<>();
							for(int i = 0; i < 15; i++) {
								if(!Tools.isNotNull(scences.get(i))) {
									sc.add(i);
								}
							}
							if(Tools.isNotNull(sc) && sc.size() > 0) {
								Collections.shuffle(sc);
								//获取随机到的场景
								int scence = sc.get(0);
								
								//尝试进行插入数据操作处理
								AppDailScenceEntity t = new AppDailScenceEntity();
								t.setUserid(userid);
								t.setAnchorid(otherid);
								t.setCreate_time(new Date());
								t.setScence(scence);
								
								//插入-1 标识还没有触发发送消息的操作处理
								t.setScence_index(-1);
								t.setScence_type(ScenceMessageTypeEnum.text.getCode());
								t.setState(0);
								t.setSend_time(new Date());
								appDailScenceContract.insert(t);
							} 
						}
					}
				}
		} catch (Exception e) {
			
		}
	}
	
}
