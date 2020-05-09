package com.tigerjoys.shark.miai.service.test;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class NewAnchorTest extends BaseTestConfig {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Test
	public void testCreateNewAnchor() throws Exception {
		long id = 3000;
		String man = "{\"RECORDS\":[{\"id\":79326886492176640,\"nickname\":\"Jason\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413454740_3875.jpg\"},{\"id\":79326886919995648,\"nickname\":\"狼图腾\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413493254_9457.jpg\"},{\"id\":79326887358300416,\"nickname\":\"雨中漫步\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413507094_6376.jpg\"},{\"id\":79326887825965312,\"nickname\":\"a.小明\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413513272_3865.jpg\"},{\"id\":79326888195064064,\"nickname\":\"吉米\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413530227_2203.jpg\"},{\"id\":79326888503345408,\"nickname\":\"祖\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413541047_4732.jpg\"},{\"id\":79326888945844480,\"nickname\":\"国际米兰\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413547690_3666.jpg\"},{\"id\":79326889333817600,\"nickname\":\"赵小貝\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413559973_1844.jpg\"},{\"id\":79326889660973312,\"nickname\":\"似水年华\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413575697_7.jpg\"},{\"id\":79326890034266368,\"nickname\":\"溟\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413578404_9287.jpg\"},{\"id\":79326890336256256,\"nickname\":\"Robin赵\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413582347_8627.jpg\"},{\"id\":79326890720035072,\"nickname\":\"帅猛男\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413587715_9087.jpg\"},{\"id\":79326891122688256,\"nickname\":\"阿东\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413608218_7560.jpg\"},{\"id\":79326891483398400,\"nickname\":\"独上西楼\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413626025_3103.jpg\"},{\"id\":79326891854594304,\"nickname\":\"爱飞的钢铁侠\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413641184_3218.jpg\"},{\"id\":79326892217401600,\"nickname\":\"右手看云\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413658987_2105.png\"},{\"id\":79326892739592448,\"nickname\":\"나는苏다更春期\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413673769_9225.jpg\"},{\"id\":79326893127565568,\"nickname\":\"hellodhq\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413694200_5977.jpg\"},{\"id\":79326893391806720,\"nickname\":\"sunny\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413706557_1912.jpg\"},{\"id\":79326893723156736,\"nickname\":\"暖心\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413716112_1793.jpg\"},{\"id\":79326894041923840,\"nickname\":\"四夕刚\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413727492_8599.jpg\"},{\"id\":79326894404731136,\"nickname\":\"Antonychen\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413745845_346.jpg\"},{\"id\":79326894761246976,\"nickname\":\"Ricardo??\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413772426_3432.jpg\"},{\"id\":79326895086305536,\"nickname\":\"原点\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413780812_328.jpg\"},{\"id\":79326895411364096,\"nickname\":\"包容百川又如何\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413788211_7838.jpg\"},{\"id\":79326895767879936,\"nickname\":\"找个平凡爱\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413797147_7761.jpg\"},{\"id\":79326896042606848,\"nickname\":\"中国--唐\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413818172_6350.jpg\"},{\"id\":79326896319430912,\"nickname\":\"爱法牛的男人\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413824066_45.jpg\"},{\"id\":79326896644489472,\"nickname\":\"风依然\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413849438_1072.jpg\"},{\"id\":79326896982130944,\"nickname\":\"木子\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413863579_8373.jpg\"},{\"id\":79326897273635072,\"nickname\":\"TheApocalypse\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413872800_5933.jpg\"},{\"id\":79326897575624960,\"nickname\":\"酒\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413876401_1194.jpg\"},{\"id\":79326897848254720,\"nickname\":\"Neil\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413885068_1019.jpg\"},{\"id\":79326898217353472,\"nickname\":\"小鸟\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413898173_9699.jpg\"},{\"id\":79326898708087040,\"nickname\":\"冬冬哥\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413909270_2063.jpg\"},{\"id\":79326899056214272,\"nickname\":\"锄禾\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413925076_6132.jpg\"},{\"id\":79326899291095296,\"nickname\":\"申威\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413934836_6183.jpg\"},{\"id\":79326899624542464,\"nickname\":\"大叔\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413958021_9286.jpg\"},{\"id\":79326900079624448,\"nickname\":\"\\/ty太阳之巅\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413976217_1223.jpg\"},{\"id\":79326900366934272,\"nickname\":\"金金\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534413989442_4370.jpg\"},{\"id\":79326900777976064,\"nickname\":\"大枣\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414001304_4847.jpg\"},{\"id\":79326901082063104,\"nickname\":\"FelixChu\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414007944_8258.jpg\"},{\"id\":79326901516173568,\"nickname\":\"一壶老酒\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414012979_1758.jpg\"},{\"id\":79326901790900480,\"nickname\":\"向来痴\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414030673_2210.jpg\"},{\"id\":79326902101278976,\"nickname\":\"孤独求败\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414045107_5078.jpg\"},{\"id\":79326902485057792,\"nickname\":\"PJLADD\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414075753_6541.jpg\"},{\"id\":79326902870933760,\"nickname\":\"伍歌®\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414083003_4767.jpg\"},{\"id\":79326903198089472,\"nickname\":\"A?史莱克\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414107311_9094.jpg\"},{\"id\":79326903609131264,\"nickname\":\"油焖小软脚虾\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414117472_6893.jpg\"},{\"id\":79326903997104384,\"nickname\":\"风之子\",\"photo\":\"\\/upload\\/user\\/2018\\/08\\/16\\/1534414135687_8225.jpg\"}]}";
		String woman = "{\"RECORDS\":[{\"id\":65418855465352960,\"nickname\":\"闫晓婷\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758800684_7072.jpg\"},{\"id\":65418855071088384,\"nickname\":\"?贝贝贝\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758792226_6387.jpg\"},{\"id\":65418854177701632,\"nickname\":\"Nina\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758784993_1816.jpg\"},{\"id\":65418853858934528,\"nickname\":\"你好，不好。\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758779800_2718.jpg\"},{\"id\":65418853412241152,\"nickname\":\"沙皮狗淡淡的忧伤\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758766276_1091.jpg\"},{\"id\":65418853095571200,\"nickname\":\"a-文静\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758755036_5751.jpg\"},{\"id\":65418852678237952,\"nickname\":\"火柴\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758751626_8616.jpg\"},{\"id\":65418852357373696,\"nickname\":\"伪爱^ω^鎖伈\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758748613_9999.jpg\"},{\"id\":65418851958914816,\"nickname\":\"?坚果\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758738450_5078.jpg\"},{\"id\":65418851581427456,\"nickname\":\"兔兔\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758723144_3168.jpg\"},{\"id\":65418851224911616,\"nickname\":\"清风\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758719630_8494.jpg\"},{\"id\":65418850797092608,\"nickname\":\"特立独行的猫\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758694478_5313.jpg\"},{\"id\":65418850438479616,\"nickname\":\"明惠\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758689117_5892.jpg\"},{\"id\":65418850029534976,\"nickname\":\"小姜同学\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758688453_2705.jpg\"},{\"id\":65418849662533376,\"nickname\":\"ABAB\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758685385_3542.jpg\"},{\"id\":65418849345863424,\"nickname\":\"默默＆\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758677984_8046.jpg\"},{\"id\":65418848941113088,\"nickname\":\"?幸福宝贝儿?\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758667531_9271.jpg\"},{\"id\":65418848588791552,\"nickname\":\"小魔女吴栩辰\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758661364_1737.jpg\"},{\"id\":65418848263732992,\"nickname\":\"只如初见\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758655391_646.jpg\"},{\"id\":65418847833816832,\"nickname\":\"毛毛虫\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758649380_4819.jpg\"},{\"id\":65418847418580736,\"nickname\":\"百灵\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758646425_7043.jpg\"},{\"id\":65418846867029760,\"nickname\":\"画个圈圈......\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758642396_2920.jpg\"},{\"id\":65418846548262656,\"nickname\":\"小幸运\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758638385_1131.jpg\"},{\"id\":65418846141415168,\"nickname\":\"冰\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758634770_5616.jpg\"},{\"id\":65418845770219264,\"nickname\":\"兜宝儿\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758630303_2102.jpg\"},{\"id\":65418845474520832,\"nickname\":\"^O^辉宝宝^O^\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758623557_9357.jpg\"},{\"id\":65418845099130624,\"nickname\":\"栀子花开\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758621050_3768.jpg\"},{\"id\":65418844736323328,\"nickname\":\"多啦\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758611717_6424.jpg\"},{\"id\":65418844298018560,\"nickname\":\"辛辛\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758604792_7076.jpg\"},{\"id\":65418843910045440,\"nickname\":\"卖饼干的小女孩\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758597471_9569.jpg\"},{\"id\":65418843511586560,\"nickname\":\"纪红\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758594558_3542.jpg\"},{\"id\":65418843173945088,\"nickname\":\"Lina\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758577462_2285.jpg\"},{\"id\":65418842783874816,\"nickname\":\"飘逗\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758567342_7621.jpg\"},{\"id\":65418842406387456,\"nickname\":\"viva\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758553333_238.jpg\"},{\"id\":65418842014220032,\"nickname\":\"Miss不明白\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758549645_7063.jpg\"},{\"id\":65418841609469696,\"nickname\":\"诺\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758546083_3766.jpg\"},{\"id\":65418841093570304,\"nickname\":\"L•Y\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758536736_783.jpg\"},{\"id\":65418840607031040,\"nickname\":\"小贰月\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758526180_6860.jpg\"},{\"id\":65418840292458240,\"nickname\":\"嘎玛\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758522663_6823.jpg\"},{\"id\":65418839977885440,\"nickname\":\"晓伽碧玉\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758514923_5907.jpg\"},{\"id\":65418839631855360,\"nickname\":\"Fiona\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758505520_3066.jpg\"},{\"id\":65418839329865472,\"nickname\":\"幸福树\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758498586_4612.jpg\"},{\"id\":65418839025778432,\"nickname\":\"期待\\/break爱\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758494927_8738.jpg\"},{\"id\":65418838738468608,\"nickname\":\"?爱吃菜小老?\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758490658_4792.jpg\"},{\"id\":65418838335815424,\"nickname\":\"吃吃糊涂虫\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758487620_8777.jpg\"},{\"id\":65418837924773632,\"nickname\":\"BemyQueen\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758476130_2140.jpg\"},{\"id\":65418837543091968,\"nickname\":\"Elaine\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758472454_2343.jpg\"},{\"id\":65418837123661568,\"nickname\":\"乐乐\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758458611_3103.jpg\"},{\"id\":65418836723105536,\"nickname\":\"带刺玫瑰\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758450723_1316.jpg\"},{\"id\":65418836362395392,\"nickname\":\"小萌子\",\"photo\":\"\\/upload\\/user\\/2018\\/05\\/31\\/1527758447519_1187.jpg\"}]}";
		JSONObject jsonMan = JsonHelper.toJsonObject(man);
		if(Tools.isNotNull(jsonMan)) {
			JSONArray array = jsonMan.getJSONArray("RECORDS");
			if(Tools.isNotNull(array)) {
				for(int i=0; i< array.size(); i++) {
					JSONObject object = array.getJSONObject(i);
					if(Tools.isNotNull(object)) {
						long userid = object.getLongValue("id");
						String nickname = object.getString("nickname");
						String photo = object.getString("photo");
						if(userid > 0 && Tools.isNotNull(nickname) && Tools.isNotNull(photo)) {
							AnchorOnlineEntity anchor = new AnchorOnlineEntity();
							anchor.setId(id);
							anchor.setUserid(userid);
							anchor.setStar(getRandomNumber(3,5));
							anchor.setPrice(0);
							anchor.setOnline(getRandomNumber(0, 2));
							anchor.setState(-9);
							anchor.setFlag(3);
							anchor.setDisturb(0);
							anchor.setNickname(nickname);
							anchor.setSignature(null);
							anchor.setIntro(null);
							anchor.setStature(getRandomNumber(160, 175)+" cm");
							anchor.setWeight(getRandomNumber(45, 55)+" kg");
							int index = getRandomNumber(0, 11);
							anchor.setZodiac(Tools.constellationArr[index]);
							anchor.setCityid(131L);
							anchor.setPhoto(photo);
							//时间相关信息
							anchor.setCreate_time(new Date());
							anchor.setUpdate_time(new Date());
							anchor.setAudit_time(new Date());
							
							anchorOnlineContract.insert(anchor);
							id++;
						}
					}
				}
			}
		}
		
		JSONObject jsonWoman = JsonHelper.toJsonObject(woman);
		if(Tools.isNotNull(jsonWoman)) {
			JSONArray array = jsonWoman.getJSONArray("RECORDS");
			if(Tools.isNotNull(array)) {
				for(int i=0; i< array.size(); i++) {
					JSONObject object = array.getJSONObject(i);
					if(Tools.isNotNull(object)) {
						long userid = object.getLongValue("id");
						String nickname = object.getString("nickname");
						String photo = object.getString("photo");
						if(userid > 0 && Tools.isNotNull(nickname) && Tools.isNotNull(photo)) {
							AnchorOnlineEntity anchor = new AnchorOnlineEntity();
							anchor.setId(id);
							anchor.setUserid(userid);
							anchor.setStar(getRandomNumber(3,5));
							anchor.setPrice(0);
							anchor.setOnline(getRandomNumber(0, 2));
							anchor.setState(-9);
							anchor.setFlag(3);
							anchor.setDisturb(0);
							anchor.setNickname(nickname);
							anchor.setSignature(null);
							anchor.setIntro(null);
							anchor.setStature(getRandomNumber(169, 185)+" cm");
							anchor.setWeight(getRandomNumber(60, 75)+" kg");
							int index = getRandomNumber(0, 11);
							anchor.setZodiac(Tools.constellationArr[index]);
							anchor.setCityid(131L);
							anchor.setPhoto(photo);
							//时间相关信息
							anchor.setCreate_time(new Date());
							anchor.setUpdate_time(new Date());
							anchor.setAudit_time(new Date());
							
							anchorOnlineContract.insert(anchor);
							id++;
						}
					}
				}
			}
		}
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
}
