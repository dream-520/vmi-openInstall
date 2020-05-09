package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.test.AnchorDto;
import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsContract;
import com.tigerjoys.shark.miai.inter.contract.IAppLabelContract;
import com.tigerjoys.shark.miai.inter.contract.IGlobalBroadcastVipContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.entity.AppLabelEntity;
import com.tigerjoys.shark.miai.inter.entity.GlobalBroadcastVipEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;
import com.tigerjoys.shark.miai.service.IVChatVideoYXService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class CreateAnchorEvaluation extends BaseTestConfig {

	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IVChatVideoYXService vChatVideoYXService;
	
	@Autowired
	private IAnchorIntimateRankingsContract anchorIntimateRankingsContract;
	
	@Autowired
	private IAppLabelContract appLabelContract;
	
	@Autowired
	private IGlobalBroadcastVipContract globalBroadcastVipContract;

	@Test
	public void testCreateAnchorEvaluation() throws Exception {
		List<Long> vUserids = new ArrayList<>();
		/*
		vUserids.add(65418691671490304L);
		vUserids.add(65418693063999232L);
		vUserids.add(65418693535858432L);
		vUserids.add(65418693967871744L);
		vUserids.add(65418697029713664L);
		vUserids.add(65418697558195968L);
		vUserids.add(65418698199924480L);
		vUserids.add(65418701460995840L);
		vUserids.add(65418701876231936L);
		vUserids.add(65418702280982272L);
		*/
		vUserids.add(150024574795317504L);
		vUserids.add(150028220176072960L);
		vUserids.add(150028437881422080L);
		vUserids.add(150028655632908544L);
		vUserids.add(150028852995883264L);
		vUserids.add(150029075723911424L);
		vUserids.add(150029335798022400L);
		vUserids.add(150029601786102016L);
		vUserids.add(150029854773936384L);
		vUserids.add(150030168933597440L);
		vUserids.add(150030485280588032L);
		vUserids.add(150030812692152576L);
		vUserids.add(150031225357140224L);
		vUserids.add(150031527628046592L);
		vUserids.add(150031973136531712L);
		vUserids.add(150032306824872192L);
		vUserids.add(150032561993744640L);
		vUserids.add(150032829567271168L);
		vUserids.add(150033214887493888L);
		vUserids.add(150033704545222912L);
		vUserids.add(150034090777706752L);
		vUserids.add(150034342645661952L);
		vUserids.add(150034633661153536L);
		vUserids.add(150034906343342336L);
		vUserids.add(150035547167981824L);
		vUserids.add(150035816628945152L);
		vUserids.add(150036373680750848L);
		vUserids.add(150036891345944832L);
		vUserids.add(150037273644171520L);
		vUserids.add(150037648153575680L);
		vUserids.add(150038579551207680L);
		vUserids.add(150039041356660992L);
		vUserids.add(150039359609962752L);
		vUserids.add(150039598194557184L);
		vUserids.add(150039910194151680L);
		vUserids.add(150040318547394816L);
		
		List<Long> users = new ArrayList<>();
		users.add(83532314035945728L);
		users.add(83550355100598528L);
		users.add(83584446617420032L);
		users.add(83594041654051072L);
		users.add(83560851753206016L);
		users.add(83581384001650944L);
		users.add(83661749292695808L);
		users.add(83585853032562944L);
		users.add(83652788436926720L);
		users.add(83526063237431552L);
		users.add(83700376714412288L);
		users.add(83675607373775104L);
		users.add(83553201344807168L);
		users.add(83624486051578112L);
		users.add(83686697090482432L);
		users.add(83637409016774912L);
		users.add(83574064941170944L);
		users.add(83699468125405440L);
		users.add(83540371210043648L);
		users.add(83604030638588160L);
		users.add(83663079172276480L);
		users.add(83584734053073152L);
		users.add(83573122504130816L);
		users.add(83696345185059072L);
		users.add(83593471880921344L);
		users.add(83602853322621184L);
		users.add(83545830004424960L);
		users.add(83573511720861952L);
		users.add(83587252778762496L);
		users.add(83556731373814016L);
		users.add(83649863323025664L);
		users.add(83531681794949376L);
		users.add(83597927150321920L);
		users.add(83703137537425664L);
		users.add(83527997052748032L);
		users.add(83550776363909376L);
		users.add(83583715489415424L);
		users.add(83577236776616192L);
		users.add(83606961488199936L);
		users.add(83577951603458304L);
		users.add(83575644729966848L);
		users.add(83675546902397184L);
		users.add(83564675832283392L);
		users.add(83703780373233920L);
		users.add(83710185511649536L);
		users.add(83671874424340736L);
		users.add(83587939036102912L);
		users.add(83704608460964096L);
		users.add(83605879821238528L);
		users.add(83570600532050176L);
		users.add(83556293587042560L);
		users.add(83529327842492672L);
		users.add(83631074082619648L);
		users.add(83600195987767552L);
		users.add(83684000461291776L);
		users.add(83693993224896768L);
		users.add(83642000353591552L);
		users.add(83563698586714368L);
		users.add(83613969387946240L);
		users.add(83526919779320064L);
		users.add(83589185782481152L);
		users.add(83528954733986048L);
		users.add(83705170736775424L);
		users.add(83637216085082368L);
		users.add(83688058515423488L);
		users.add(83643523678667008L);
		users.add(83654894849949952L);
		users.add(83690452045332736L);
		users.add(83707513939230976L);
		users.add(83577090017919232L);
		users.add(83659398259474688L);
		users.add(83585195118231808L);
		users.add(83528485049532672L);
		users.add(83578941153018112L);
		users.add(83593852895691008L);
		users.add(83582849460011264L);
		users.add(83584021325480192L);
		users.add(83554753197441280L);
		users.add(83709273344901376L);
		users.add(83669385258008832L);
		users.add(83568513173750016L);
		users.add(83653640958574848L);
		users.add(83653783103537408L);
		users.add(83605727760941312L);
		users.add(83540305609031936L);
		users.add(83643415744545024L);
		users.add(83528908506464512L);
		users.add(83604280038195456L);
		users.add(83624553365963008L);
		users.add(83595015229604096L);
		users.add(83545826019836160L);
		users.add(83693345811005696L);
		users.add(83652887063888128L);
		users.add(83594791692075264L);
		users.add(83588707577299200L);
		users.add(83703132036595968L);
		users.add(83611393342898432L);
		users.add(83530511089205504L);
		users.add(83563715030483200L);
		users.add(83682730239394048L);
		users.add(83591436181766400L);
		users.add(83548960471449856L);
		users.add(83693732915904768L);
		users.add(83532238051934464L);
		users.add(83557867117936896L);
		users.add(83555303485931776L);
		users.add(83584433482957056L);
		users.add(83582726837436672L);
		users.add(83537911531766016L);
		users.add(83576794134937856L);
		users.add(83577952291324160L);
		users.add(83695759674900736L);
		users.add(83686189208502528L);
		users.add(83594233017073920L);
		users.add(83579143576420608L);
		users.add(83587944853602560L);
		users.add(83539950888354048L);
		users.add(83533161056764160L);
		users.add(83582528002261248L);
		users.add(83547813589352704L);
		users.add(83553757727621376L);
		users.add(83533160482144512L);
		users.add(83647871947178240L);
		users.add(83528392435106048L);
		users.add(83648149880635648L);
		users.add(83572485903155456L);
		users.add(83589145041109248L);
		users.add(83529538736292096L);
		users.add(83709671432585472L);
		users.add(83704472053809408L);
		users.add(83569550605484288L);
		users.add(83542759438352640L);
		users.add(83651365368299776L);
		users.add(83605953424982272L);
		users.add(83566247551238400L);
		users.add(83580931088122112L);
		users.add(83576356266377472L);
		users.add(83706750145986816L);
		users.add(83675988940095744L);
		users.add(83653449314533632L);
		users.add(83563680479904000L);
		users.add(83548600052809984L);
		users.add(83591594749526272L);
		users.add(83664515754950912L);
		users.add(83541858067742976L);
		users.add(83559859806601472L);
		users.add(83601241176867072L);
		users.add(83595626287268096L);
		users.add(83666213296734464L);
		users.add(83526759833731328L);
		users.add(83672197253628160L);
		users.add(83708389527453952L);
		users.add(83706289133256960L);
		users.add(83678749687611648L);
		users.add(83577432736596224L);
		users.add(83556174437351680L);
		users.add(83593484375752960L);
		users.add(83699795184648448L);
		users.add(83543521832796416L);
		users.add(83694497849999616L);
		users.add(83612676315480320L);
		users.add(83626427351630080L);
		users.add(83571225867124992L);
		users.add(83663904839893248L);
		users.add(83592670882103552L);
		users.add(83528647058718976L);
		users.add(83560431678980352L);
		users.add(83604693739176192L);
		users.add(83550466071396608L);
		users.add(83656886280323328L);
		users.add(83685075755008256L);
		users.add(83689977487098112L);
		users.add(83584103768719616L);
		users.add(83576952419582208L);
		users.add(83694024919154944L);
		users.add(83693281134838016L);
		users.add(83536882176164096L);
		users.add(83595670652518656L);
		users.add(83697304560795904L);
		users.add(83652897063108864L);
		users.add(83671114282238208L);
		users.add(83690362238992640L);
		users.add(83552248375869696L);
		users.add(83668365966639360L);
		users.add(83536944400761088L);
		users.add(83563339403297024L);
		users.add(83525795720528128L);
		users.add(83647589041373440L);
		users.add(83603496703688960L);
		users.add(83653351585153280L);
		users.add(83656552610857216L);
		users.add(83690375388135680L);
		users.add(83681925824315648L);
		users.add(83600264923250944L);
		users.add(83582707518472448L);
		users.add(83530530938749184L);
		users.add(83526415904997632L);
		users.add(83537850601111808L);
		users.add(83558502607421696L);
		
		//处理拨打电话和评论相关的处理
		/*
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addLimitField(2, 300);
		pageModel.addQuery(Restrictions.lt("fr", 3));
		pageModel.addQuery(Restrictions.eq("sex", 1));
		pageModel.desc("create_time");
		List<UserEntity> users = userContract.load(pageModel);
		*/
		
		PageModel label = PageModel.getPageModel();
		label.addQuery(Restrictions.eq("type", 2));
		label.addQuery(Restrictions.le("state", 1));
		List<AppLabelEntity> labels = appLabelContract.load(label);
		System.err.println(labels.size());
		if(Tools.isNotNull(users) && users.size() > 0 && Tools.isNotNull(labels) && labels.size() > 0) {
			for (long vUserid : vUserids) {
				for(int i = 0; i < getRandomNumber(36, 50); i++) {
					int index = getRandomNumber(0, users.size()-1);
					long user = users.get(index);
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
					
					vChatVideoYXService.anchorEvaluation(user, vUserid, 0, evaluationIdList,"");
					//设置亲密榜数据
					int money = getRandomNumber(10, 2500);
					anchorIntimateRankingsContract.addIncome(vUserid, user, money);
				}
			}
		}

	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
	@Test
	public void testCreateUserVipHint() throws Exception {
		List<Long> users = new ArrayList<>();
		users.add(83532314035945728L);
		users.add(83550355100598528L);
		users.add(83584446617420032L);
		users.add(83594041654051072L);
		users.add(83560851753206016L);
		users.add(83581384001650944L);
		users.add(83661749292695808L);
		users.add(83585853032562944L);
		users.add(83652788436926720L);
		users.add(83526063237431552L);
		users.add(83700376714412288L);
		users.add(83675607373775104L);
		users.add(83553201344807168L);
		users.add(83624486051578112L);
		users.add(83686697090482432L);
		users.add(83637409016774912L);
		users.add(83574064941170944L);
		users.add(83699468125405440L);
		users.add(83540371210043648L);
		users.add(83604030638588160L);
		users.add(83663079172276480L);
		users.add(83584734053073152L);
		users.add(83573122504130816L);
		users.add(83696345185059072L);
		users.add(83593471880921344L);
		users.add(83602853322621184L);
		users.add(83545830004424960L);
		users.add(83573511720861952L);
		users.add(83587252778762496L);
		users.add(83556731373814016L);
		users.add(83649863323025664L);
		users.add(83531681794949376L);
		users.add(83597927150321920L);
		users.add(83703137537425664L);
		users.add(83527997052748032L);
		users.add(83550776363909376L);
		users.add(83583715489415424L);
		users.add(83577236776616192L);
		users.add(83606961488199936L);
		users.add(83577951603458304L);
		users.add(83575644729966848L);
		users.add(83675546902397184L);
		users.add(83564675832283392L);
		users.add(83703780373233920L);
		users.add(83710185511649536L);
		users.add(83671874424340736L);
		users.add(83587939036102912L);
		users.add(83704608460964096L);
		users.add(83605879821238528L);
		users.add(83570600532050176L);
		users.add(83556293587042560L);
		users.add(83529327842492672L);
		users.add(83631074082619648L);
		users.add(83600195987767552L);
		users.add(83684000461291776L);
		users.add(83693993224896768L);
		users.add(83642000353591552L);
		users.add(83563698586714368L);
		users.add(83613969387946240L);
		users.add(83526919779320064L);
		users.add(83589185782481152L);
		users.add(83528954733986048L);
		users.add(83705170736775424L);
		users.add(83637216085082368L);
		users.add(83688058515423488L);
		users.add(83643523678667008L);
		users.add(83654894849949952L);
		users.add(83690452045332736L);
		users.add(83707513939230976L);
		users.add(83577090017919232L);
		users.add(83659398259474688L);
		users.add(83585195118231808L);
		users.add(83528485049532672L);
		users.add(83578941153018112L);
		users.add(83593852895691008L);
		users.add(83582849460011264L);
		users.add(83584021325480192L);
		users.add(83554753197441280L);
		users.add(83709273344901376L);
		users.add(83669385258008832L);
		users.add(83568513173750016L);
		users.add(83653640958574848L);
		users.add(83653783103537408L);
		users.add(83605727760941312L);
		users.add(83540305609031936L);
		users.add(83643415744545024L);
		users.add(83528908506464512L);
		users.add(83604280038195456L);
		users.add(83624553365963008L);
		users.add(83595015229604096L);
		users.add(83545826019836160L);
		users.add(83693345811005696L);
		users.add(83652887063888128L);
		users.add(83594791692075264L);
		users.add(83588707577299200L);
		users.add(83703132036595968L);
		users.add(83611393342898432L);
		users.add(83530511089205504L);
		users.add(83563715030483200L);
		users.add(83682730239394048L);
		users.add(83591436181766400L);
		users.add(83548960471449856L);
		users.add(83693732915904768L);
		users.add(83532238051934464L);
		users.add(83557867117936896L);
		users.add(83555303485931776L);
		users.add(83584433482957056L);
		users.add(83582726837436672L);
		users.add(83537911531766016L);
		users.add(83576794134937856L);
		users.add(83577952291324160L);
		users.add(83695759674900736L);
		users.add(83686189208502528L);
		users.add(83594233017073920L);
		users.add(83579143576420608L);
		users.add(83587944853602560L);
		users.add(83539950888354048L);
		users.add(83533161056764160L);
		users.add(83582528002261248L);
		users.add(83547813589352704L);
		users.add(83553757727621376L);
		users.add(83533160482144512L);
		users.add(83647871947178240L);
		users.add(83528392435106048L);
		users.add(83648149880635648L);
		users.add(83572485903155456L);
		users.add(83589145041109248L);
		users.add(83529538736292096L);
		users.add(83709671432585472L);
		users.add(83704472053809408L);
		users.add(83569550605484288L);
		users.add(83542759438352640L);
		users.add(83651365368299776L);
		users.add(83605953424982272L);
		users.add(83566247551238400L);
		users.add(83580931088122112L);
		users.add(83576356266377472L);
		users.add(83706750145986816L);
		users.add(83675988940095744L);
		users.add(83653449314533632L);
		users.add(83563680479904000L);
		users.add(83548600052809984L);
		users.add(83591594749526272L);
		users.add(83664515754950912L);
		users.add(83541858067742976L);
		users.add(83559859806601472L);
		users.add(83601241176867072L);
		users.add(83595626287268096L);
		users.add(83666213296734464L);
		users.add(83526759833731328L);
		users.add(83672197253628160L);
		users.add(83708389527453952L);
		users.add(83706289133256960L);
		users.add(83678749687611648L);
		users.add(83577432736596224L);
		users.add(83556174437351680L);
		users.add(83593484375752960L);
		users.add(83699795184648448L);
		users.add(83543521832796416L);
		users.add(83694497849999616L);
		users.add(83612676315480320L);
		users.add(83626427351630080L);
		users.add(83571225867124992L);
		users.add(83663904839893248L);
		users.add(83592670882103552L);
		users.add(83528647058718976L);
		users.add(83560431678980352L);
		users.add(83604693739176192L);
		users.add(83550466071396608L);
		users.add(83656886280323328L);
		users.add(83685075755008256L);
		users.add(83689977487098112L);
		users.add(83584103768719616L);
		users.add(83576952419582208L);
		users.add(83694024919154944L);
		users.add(83693281134838016L);
		users.add(83536882176164096L);
		users.add(83595670652518656L);
		users.add(83697304560795904L);
		users.add(83652897063108864L);
		users.add(83671114282238208L);
		users.add(83690362238992640L);
		users.add(83552248375869696L);
		users.add(83668365966639360L);
		users.add(83536944400761088L);
		users.add(83563339403297024L);
		users.add(83525795720528128L);
		users.add(83647589041373440L);
		users.add(83603496703688960L);
		users.add(83653351585153280L);
		users.add(83656552610857216L);
		users.add(83690375388135680L);
		users.add(83681925824315648L);
		users.add(83600264923250944L);
		users.add(83582707518472448L);
		users.add(83530530938749184L);
		users.add(83526415904997632L);
		users.add(83537850601111808L);
		users.add(83558502607421696L);
		
		List<Integer> moblie = new ArrayList<>();
		moblie.add(133);
		moblie.add(149);
		moblie.add(153);
		moblie.add(173);
		moblie.add(177);
		moblie.add(180);
		moblie.add(181);
		moblie.add(189);
		moblie.add(199);
		moblie.add(130);
		moblie.add(131);
		moblie.add(132);
		moblie.add(145);
		moblie.add(155);
		moblie.add(156);
		moblie.add(166);
		moblie.add(171);
		moblie.add(175);
		moblie.add(176);
		moblie.add(185);
		moblie.add(186);
		moblie.add(166);
		moblie.add(135);
		moblie.add(136);
		moblie.add(137);
		moblie.add(138);
		moblie.add(139);
		moblie.add(147);
		moblie.add(150);
		moblie.add(151);
		moblie.add(152);
		moblie.add(157);
		moblie.add(158);
		moblie.add(159);
		moblie.add(172);
		moblie.add(178);
		moblie.add(182);
		moblie.add(183);
		moblie.add(184);
		moblie.add(187);
		moblie.add(188);
		moblie.add(198);
		//添加对应的记录信息
		for (Long userid : users) {
			UserBO bo = userAgent.findById(userid);
			if(Tools.isNotNull(bo)) {
				int index = getRandomNumber(0, moblie.size()-1);
				
				int head = moblie.get(index);
				int tail = getRandomNumber(1500, 9500);
				String m = "" + head + "****"+tail;
				
				GlobalBroadcastVipEntity t1 = new GlobalBroadcastVipEntity();
				t1.setUserid(userid);
				t1.setNickname(bo.getNickname());
				t1.setContent(m+ " 完成签到并领取了话费");
				t1.setCreate_time(new Date());
				globalBroadcastVipContract.insert(t1);
				GlobalBroadcastVipEntity t2 = new GlobalBroadcastVipEntity();
				t2.setUserid(userid);
				t2.setNickname(bo.getNickname());
				t2.setContent(m+ " 充值了VIP");
				t2.setCreate_time(new Date());
				globalBroadcastVipContract.insert(t2);
			}
		}

	}
}
