package com.tigerjoys.shark.miai.agent.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VChatCallPopTCPDto;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.enums.VChatPopupStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.agent.impl.NeteaseAgentImpl;
import com.tigerjoys.shark.miai.agent.impl.UserAgentImpl;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * @author mouzhanpeng at [2017年9月21日 下午1:53:27]
 * @since JDK 1.8.0
 */
public class NeteaseAgentTest {

	private INeteaseAgent neteaseAgent = spy(NeteaseAgentImpl.class);
	private IUserAgent userAgent = spy(UserAgentImpl.class);

	//@Test
	public void createUser() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("accid", "adminVmi");
		params.put("name", "adminVmiName");
		params.put("icon",
				"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506578844&di=6ce7ae3acce7dd07b15c07b1075c0958&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201406%2F29%2F20140629180728_ktRJ2.jpeg");
		params.put("token", "adminVmiToken");
		JSONObject json = neteaseAgent.createUser(params);
		System.out.println(json.toString());
		//assertEquals(200, neteaseAgent.createUser(params).getIntValue("code"));
	}

	//@Test
	public void updateUser() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("32392030751621376","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/27/1456535120998_2592.jpg");
		map.put("32392031173148928","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/01/1454286255202_8967.jpg");
		map.put("32392031412224256","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/01/1454269052677_578.jpg");
		map.put("32392031707922688","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/01/1454262225241_7589.jpg");
		map.put("32392031928123648","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/31/1454178238395_2466.jpg");
		map.put("32392032288833792","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116222742_3942.jpg");
		map.put("32392032576143616","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/30/1454143149478_2732.jpg");
		map.put("32392032792150272","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/30/1454132600466_5196.jpg");
		map.put("32392033043808512","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116170454_6664.jpg");
		map.put("32392033322729728","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116130196_2662.jpg");
		map.put("32392033517764864","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116052019_912.jpg");
		map.put("32392033737965824","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/29/1454076771177_9879.jpg");
		map.put("32392033945583872","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/29/1454054803472_3303.jpg");
		map.put("32392034167881984","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115997435_9759.jpg");
		map.put("32392034390180096","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115959817_9220.jpg");
		map.put("32392034675392768","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115915735_7013.jpg");
		map.put("32392034897690880","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/29/1454023703237_1049.jpg");
		map.put("32392035164029184","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/28/1453983771264_2810.jpg");
		map.put("32392035426173184","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/28/1453975726435_4857.jpg");
		map.put("32392035625402624","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/28/1453975680933_4197.jpg");
		map.put("32392035868672256","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/27/1453887047689_4701.jpg");
		map.put("32392036078387456","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/27/1453882216240_1481.jpg");
		map.put("32392036327948544","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/27/1453881560243_4293.jpg");
		map.put("32392036585898240","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115852281_5520.jpg");
		map.put("32392036843847936","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115809207_5585.jpg");
		map.put("32392037049368832","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115772305_1885.jpg");
		map.put("32392037231821056","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115714363_8894.jpg");
		map.put("32392037428953344","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115650315_3527.jpg");
		map.put("32392037674320128","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115609118_2597.jpg");
		map.put("32392037896618240","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/06/1454747101036_8062.jpg");
		map.put("32392038173442304","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115556666_5110.jpg");
		map.put("32392038431392000","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/26/1453769653434_1146.jpg");
		map.put("32392038657884416","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/25/1453732299074_7918.jpg");
		map.put("32392038899056896","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115512209_1924.jpg");
		map.put("32392039079411968","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/12/13/1481587690252_1639.jpg");
		map.put("32392039331070208","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/25/1453691561088_7414.jpg");
		map.put("32392039509328128","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/27/1453848102937_5912.jpg");
		map.put("32392039740014848","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115444334_8527.jpg");
		map.put("32392040014741760","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115374008_8898.jpg");
		map.put("32392040304148736","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115326316_1916.jpg");
		map.put("32392040587264256","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/13/1455326994391_7767.jpg");
		map.put("32392040811659520","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116264558_2437.jpg");
		map.put("32392041057026304","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114596231_2309.jpg");
		map.put("32392041279324416","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114659399_6831.jpg");
		map.put("32392041476456704","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114728624_9737.jpg");
		map.put("32392041681977600","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114808062_2972.jpg");
		map.put("32392041902178560","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114863092_7471.jpg");
		map.put("32392042227237120","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114950285_184.jpg");
		map.put("32392042483089664","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/19/1453195128474_1653.jpg");
		map.put("32392042751525120","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/19/1453162875413_5407.jpg");
		map.put("32392043009474816","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/18/1453117682333_7057.jpg");
		map.put("32392043273715968","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/18/1453106614372_7926.jpg");
		map.put("32392043483431168","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/18/1453079968511_357.jpg");
		map.put("32392043732992256","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116312625_2527.jpg");
		map.put("32392044014010624","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116363075_2165.jpg");
		map.put("32392044263571712","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/17/1453023209530_3793.jpg");
		map.put("32392044496355584","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116444561_685.jpg");
		map.put("32392044708167936","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115007631_7247.jpg");
		map.put("32392044951437568","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116412248_5320.jpg");
		map.put("32392045173735680","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/16/1452947327851_4225.jpg");
		map.put("32392045444268288","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/15/1452861811671_4214.jpg");
		map.put("32392045677052160","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116483516_9965.jpg");
		map.put("32392045888864512","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115059245_6704.jpg");
		map.put("32392046069219584","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/03/28/1459135770526_2831.jpg");
		map.put("32392046339752192","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/15/1452848637318_5478.jpg");
		map.put("32392046536884480","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512116522120_6133.jpg");
		map.put("32392046698365184","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/15/1452844453100_9187.jpg");
		map.put("32392046945829120","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/15/1452844216745_7040.jpg");
		map.put("32392047218458880","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/15/1452841814329_4504.jpg");
		map.put("32392047501574400","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/15/1452833392247_7059.jpg");
		map.put("32392047759524096","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/15/1452821066564_8497.jpg");
		map.put("32392047967142144","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/19/1455867113554_4477.jpg");
		map.put("32392048141205760","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/14/1452758725851_3174.jpg");
		map.put("32392048386572544","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/14/1452730368074_9379.jpg");
		map.put("32392048613064960","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/13/1452690459277_8629.jpg");
		map.put("32392048812294400","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/13/1452684422059_3768.jpg");
		map.put("32392049080729856","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/13/1452661661079_4108.jpg");
		map.put("32392049286250752","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/13/1452650214927_9635.jpg");
		map.put("32392049556783360","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114504834_9431.jpg");
		map.put("32392049802150144","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/13/1452617727477_4671.jpg");
		map.put("32392050026545408","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/12/1452601979487_7163.jpg");
		map.put("32392050238357760","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/19/1453191253653_8027.jpg");
		map.put("32392050414518528","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/16/1452927646466_7553.jpg");
		map.put("32392050582290688","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/12/1452584702395_7920.jpg");
		map.put("32392050863309056","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/11/09/1478640252837_5975.jpg");
		map.put("32392051062538496","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/16/1452908302924_8811.jpg");
		map.put("32392051259670784","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/12/1452564685373_6057.jpg");
		map.put("32392051442123008","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/11/1452507752877_4187.jpg");
		map.put("32392051725238528","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/11/1452506119508_6516.jpg");
		map.put("32392051983188224","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/11/1452482290412_4254.jpg");
		map.put("32392052257915136","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/11/1452459275540_5427.jpg");
		map.put("32392052467630336","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/11/1452441675922_7408.jpg");
		map.put("32392052683636992","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/10/1452435148671_9814.jpg");
		map.put("32392052920615168","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/10/1452436958194_2871.jpg");
		map.put("32392053199536384","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/10/1452415264694_3649.jpg");
		map.put("32392053384085760","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/10/1452359672501_2095.jpg");
		map.put("32392053583315200","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/09/1452343683092_8300.jpg");
		map.put("32392053799321856","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/09/1452334062428_2741.jpg");
		map.put("32392054040494336","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/09/1452332150496_4899.jpg");
		map.put("32392054225043712","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/09/1452324089584_7461.jpg");
		map.put("32392054462021888","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/09/1452322166036_5416.jpg");
		map.put("32392054965338368","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/09/1452313498551_4877.jpg");
		map.put("32392055204413696","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/09/1452269566158_151.jpg");
		map.put("32392055510597888","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/08/1452247529791_9138.jpg");
		map.put("32392055701438720","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/08/1452245651177_9790.jpg");
		map.put("32392055898571008","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/07/1452169239293_1813.jpg");
		map.put("32392056152326400","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/07/1452167558962_4441.jpg");
		map.put("32392056368333056","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/12/1452613188426_3504.jpg");
		map.put("32392056582242560","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/07/1452142784061_7611.jpg");
		map.put("32392056794054912","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/07/1452119616790_7568.jpg");
		map.put("32392057016353024","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/06/1452083611820_2296.jpg");
		map.put("32392057215582464","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/06/1452083033132_307.jpg");
		map.put("32392057463046400","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115158033_395.jpg");
		map.put("32392057748259072","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/06/1452088864965_1484.jpg");
		map.put("32392057987334400","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/06/1452069685976_1376.jpg");
		map.put("32392058262061312","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/06/1452059851763_701.jpg");
		map.put("32392058526302464","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/06/1452048236311_8878.jpg");
		map.put("32392058748600576","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/05/1452005648368_8642.jpg");
		map.put("32392058939441408","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/05/1452000014539_9567.jpg");
		map.put("32392059147059456","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512115248556_3100.jpg");
		map.put("32392059367260416","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/05/1451929555179_8672.jpg");
		map.put("32392059574878464","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/04/1451915562746_808.jpg");
		map.put("32392059765719296","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/04/1451905466246_3978.jpg");
		map.put("32392059960754432","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/04/1451898144714_7337.jpg");
		map.put("32392060139012352","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/04/1451890815068_2797.jpg");
		map.put("32392060405350656","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/07/1454797190999_3532.jpg");
		map.put("32392060615065856","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/10/1452386853796_9989.jpg");
		map.put("32392060812198144","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/03/1451780551094_7374.jpg");
		map.put("32392061047079168","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/03/1451778726731_7862.jpg");
		map.put("32392061256794368","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/09/06/1473120132473_9336.jpg");
		map.put("32392061466509568","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/02/1451744483968_8409.jpg");
		map.put("32392061644767488","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/10/24/1477311357000_894.jpg");
		map.put("32392061852385536","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/02/1451722344974_7895.jpg");
		map.put("32392062066295040","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/02/1451708044483_3641.jpg");
		map.put("32392062317953280","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/02/1451703390073_65.jpg");
		map.put("32392062527668480","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/02/1451694894216_2687.jpg");
		map.put("32392062758355200","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/01/1451657693806_310.jpg");
		map.put("32392062949196032","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114426207_9600.jpg");
		map.put("32392063184077056","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/01/1451624592344_7373.jpg");
		map.put("32392063444123904","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/02/04/1454518128383_2190.jpg");
		map.put("32392063658033408","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/01/01/1451612437094_9608.jpg");
		map.put("32392063909691648","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114124043_6538.jpg");
		map.put("32392064184418560","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/31/1451566619546_4286.jpg");
		map.put("32392064408813824","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/31/1451563816050_8855.jpg");
		map.put("32392064626917632","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512119858987_9735.jpg");
		map.put("32392064849215744","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114266213_335.jpg");
		map.put("32392065054736640","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/30/1451477711037_5738.jpg");
		map.put("32392065283326208","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/30/1451471763372_817.jpg");
		map.put("32392065495138560","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512114339161_9366.jpg");
		map.put("32392065734213888","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512112750406_7796.jpg");
		map.put("32392065939734784","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512112655890_5204.jpg");
		map.put("32392066201878784","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/29/1451393533532_209.jpg");
		map.put("32392066438856960","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/29/1451381070843_6078.jpg");
		map.put("32392066638086400","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/28/1451293874708_8594.jpg");
		map.put("32392066854093056","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512112533392_3157.jpg");
		map.put("32392067065905408","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/28/1451268747169_5599.jpg");
		map.put("32392067292397824","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/27/1451203979627_900.jpg");
		map.put("32392067493724416","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/27/1451199582418_9229.jpg");
		map.put("32392067709731072","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/27/1451180456933_7379.jpg");
		map.put("32392067980263680","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/27/1451179642554_9303.jpg");
		map.put("32392068217241856","http://cdn.yoyo.liaomeivideo.com/upload/user/2016/03/15/1458051964035_2254.jpg");
		map.put("32392068454220032","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512112489752_9445.jpg");
		map.put("32392068684906752","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512112351388_9539.jpg");
		map.put("32392068873650432","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/25/1451008891283_3095.jpg");
		map.put("32392069072879872","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/25/1451008266748_7583.jpg");
		map.put("32392069311955200","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/25/1450974168491_2295.jpg");
		map.put("32392069590876416","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/24/1450972028376_7193.jpg");
		map.put("32392069790105856","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512112143840_1239.jpg");
		map.put("32392069987238144","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/23/1450857384775_3325.jpg");
		map.put("32392070337462528","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/23/1450854450868_42.jpg");
		map.put("32392070587023616","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512112053137_6794.jpg");
		map.put("32392070752698624","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111966080_6300.jpg");
		map.put("32392070998065408","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111832569_906.jpg");
		map.put("32392071285375232","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111726036_2384.jpg");
		map.put("32392071495090432","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111611031_2496.jpg");
		map.put("32392071744651520","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111468625_9103.jpg");
		map.put("32392072017281280","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/28/1451297305347_8527.jpg");
		map.put("32392072235385088","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/21/1450707937097_2571.jpg");
		map.put("32392072503820544","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111205472_1413.jpg");
		map.put("32392072690467072","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111135762_9028.jpg");
		map.put("32392072858239232","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512111022457_7354.jpg");
		map.put("32392073074245888","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512110767502_5542.jpg");
		map.put("32392073281863936","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512110769546_1924.jpg");
		map.put("32392073567076608","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512110405616_1744.jpg");
		map.put("32392073768403200","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512110311669_3037.jpg");
		map.put("32392073978118400","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/20/1450577444191_5548.jpg");
		map.put("32392074192027904","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/19/1450538065290_2320.jpg");
		map.put("32392074401743104","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/19/1450535111840_3958.jpg");
		map.put("32392074588389632","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512110035165_7850.jpg");
		map.put("32392074823270656","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512109946715_6160.jpg");
		map.put("32392075118969088","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512109736706_2429.jpg");
		map.put("32392075288838400","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512109638671_9088.jpg");
		map.put("32392075597119744","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512109488837_5548.jpg");
		map.put("32392075823612160","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512109403400_6684.jpg");
		map.put("32392076043813120","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512109340317_2737.jpg");
		map.put("32392076207390976","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512109260395_4643.jpg");
		map.put("32392076448563456","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512108836423_1057.jpg");
		map.put("32392076614238464","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/20/1450542063661_7637.jpg");
		map.put("32392076891062528","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512108720950_6520.jpg");
		map.put("32392077067223296","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/17/1450355199028_8617.jpg");
		map.put("32392077312590080","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/21/1450713195023_2837.jpg");
		map.put("32392077618774272","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/18/1450418382158_4074.jpg");
		map.put("32392077790740736","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/17/1450282946763_7498.jpg");
		map.put("32392078004650240","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512108248450_2610.jpg");
		map.put("32392078264697088","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512108604770_9954.jpg");
		map.put("32392078424080640","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512108425873_8291.jpg");
		map.put("32392078698807552","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512106708403_5214.jpg");
		map.put("32392078935785728","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512106485559_1939.jpg");
		map.put("32392079103557888","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512106359036_211.jpg");
		map.put("32392079277621504","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512106245141_3398.jpg");
		map.put("32392079481045248","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/14/1450066829963_8124.jpg");
		map.put("32392079728509184","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512106131327_482.jpg");
		map.put("32392079902572800","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512105968733_237.jpg");
		map.put("32392080116482304","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512105724505_2677.jpg");
		map.put("32392080309420288","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/13/1449970134058_8498.jpg");
		map.put("32392080554787072","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512105532054_4753.jpg");
		map.put("32392080802251008","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/12/1449886033218_5791.jpg");
		map.put("32392081014063360","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/12/1449885515535_6012.jpg");
		map.put("32392081261527296","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/11/1449840676212_9959.jpg");
		map.put("32392081473339648","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512105419627_6259.jpg");
		map.put("32392081647403264","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512105133080_6643.jpg");
		map.put("32392081873895680","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/11/1449789515670_7164.jpg");
		map.put("32392082119262464","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/11/1449770609468_5183.jpg");
		map.put("32392082345754880","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512105007683_5227.jpg");
		map.put("32392082591121664","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512104893181_8302.jpg");
		map.put("32392082754699520","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512456236821_9852.png");
		map.put("32392082932957440","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512456155740_9867.png");
		map.put("32392083130089728","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512456034864_1942.png");
		map.put("32392083348193536","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512455956964_4001.png");
		map.put("32392083591463168","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512104613725_996.jpg");
		map.put("32392083752943872","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512104487202_6701.jpg");
		map.put("32392083920716032","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512455706744_7330.png");
		map.put("32392084176568576","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512455566426_3680.png");
		map.put("32392084438712576","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512455485166_5609.png");
		map.put("32392084646330624","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512094928537_8559.jpg");
		map.put("32392084830880000","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/01/1512094661228_8557.jpg");
		map.put("32392088431689984","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512455309500_6482.png");
		map.put("32392088599462144","http://cdn.yoyo.liaomeivideo.com/upload/user/2015/12/06/1449370865605_3248.jpg");
		map.put("32392088821760256","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512455159138_977.png");
		map.put("32392089069224192","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/05/1512454951644_175.png");
		map.put("33670661562040576","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512703555958_6571.png");
		map.put("33672030511563008","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512704208756_127.png");
		map.put("33672810983457024","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512704580921_5185.png");
		map.put("33674086095913216","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512705188252_2107.png");
		map.put("33674636914983168","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512705451560_5529.png");
		map.put("33675003320992000","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512705626281_7860.png");
		map.put("33688221038805248","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512711929019_2837.png");
		map.put("33688660553629952","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512712136765_3133.png");
		map.put("33689047207641344","http://cdn.yoyo.liaomeivideo.com/upload/user/2017/12/08/1512712323002_406.png");
		for (Map.Entry<String, String> one : map.entrySet()){
		Map<String, String> params = new HashMap<>();
		params.put("accid", one.getKey());
		//params.put("name", "roc.moon");
		params.put("icon", one.getValue());
		//params.put("sign", "dixrocmoon dfs s");
		//params.put("email", "mzp12345@sina.com");
		//params.put("birth", null);
		//params.put("mobile", "15210573691");
		//params.put("gender", "1");
		assertEquals(200, neteaseAgent.updateUser(params).getIntValue("code"));
		}
	}

	//@Test
	public void queryHistory() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("from", "66494830169096448");
		params.put("to", "32392035164029184");
		params.put("begintime", "15000000000");
		params.put("endtime", System.currentTimeMillis() + "");
		params.put("limit", "100");
		assertEquals(200, neteaseAgent.queryHistory(params).getIntValue("code"));
	}
	
	//@Test
	public void sendMsg() throws Exception{
		neteaseAgent.pushOneMessage(66494830169096448L, 32392035164029184L, "HELL ppRRR", true);
	}
	
	//@Test
	public void badList() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("accid", String.valueOf(66455210620420352L));
		neteaseAgent.badRelationList(map);
	}
	
	//@Test
	public void gift() throws Exception {
		neteaseAgent.sendGiftPic(66494830169096448L,36775801156337920L, "baidu", "http://pic.58pic.com/58pic/13/66/58/20258PICpDh_1024.png", 2,true);
	}
	
	@Test
	public void query() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("accids", "[\"134831734503047424\",\"32392035164029184\"]");
		neteaseAgent.acquireData("https://api.netease.im/nimserver/user/getUinfos.action", map);
	}
	
	
	
	//@Test
	public void sendAttachMsg() throws Exception{
		//neteaseAgent.pushOneAttachMessage(32392035164029184L, 67244811045896448L, "HELL ppRRR", true);
		HashMap<String, String> hsmp = new HashMap<>();
		hsmp.put("msg", "邀请你加入视频聊天");
		neteaseAgent.pushOneAttachMessage(32392035164029184L, 36775801156337920L, JsonHelper.toJson(hsmp),"XX邀请你加入视频聊天");
		//neteaseAgent.pushOneAttachMessage(32392035164029184L, 85520618574905600L, "HELL ppRRR");
	}
	
	//@Test
	public void sendDiamond() throws Exception {
		//neteaseAgent.pushOneMessage(65418719477628672L, 147006931763003648L, "奖励金520钻已到账", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 147006931763003648L, "奖励金100钻已到账", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 148264212982989056L, "感谢您对净化平台环境做出的贡献，特赠送200余额", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 148424918476849408L, "根据您的举报，已查实视播用户 想做你的怀中小猫   违反平台规定，已对 想做你的怀中小猫   做出了永久封号处理。感谢用户对净化平台环境做出的贡献，并赠送100余额", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 148978672399876352L, "根据您的举报，已查实视播用户 小奶牛  违反平台规定，已对 小奶牛   做出了永久封号处理。感谢用户对净化平台环境做出的贡献，并赠送100余额和9钻体验金", true);
		
		neteaseAgent.pushOneMessage(66494830169096448L, 66494830169096448L, "根据您的举报，已查实视播用户 喜欢深入  违反平台规定，已对 喜欢深入   做出了警告处理。感谢你对净化平台环境做出的贡献，并赠送58余额", true);
	}
	
	//@Test
	public void sendAudio() throws Exception {
		//neteaseAgent.pushOneMessage(65418719477628672L, 147006931763003648L, "奖励金520钻已到账", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 147006931763003648L, "奖励金100钻已到账", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 148264212982989056L, "感谢您对净化平台环境做出的贡献，特赠送200余额", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 148424918476849408L, "根据您的举报，已查实视播用户 想做你的怀中小猫   违反平台规定，已对 想做你的怀中小猫   做出了永久封号处理。感谢用户对净化平台环境做出的贡献，并赠送100余额", true);
		//neteaseAgent.pushOneMessage(65418719477628672L, 148978672399876352L, "根据您的举报，已查实视播用户 小奶牛  违反平台规定，已对 小奶牛   做出了永久封号处理。感谢用户对净化平台环境做出的贡献，并赠送100余额和9钻体验金", true);
		
		neteaseAgent.sendAudioMsg(85157149189144832L, 85157149189144832L,166364079475327232L,"https://upaiyun.test.yoyo.liaomeivideo.com/upload/audio/check/2019/12/10/1575976708496_5143.aac", 3, false);
	}
	
	
	
	
}
