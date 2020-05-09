package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserExtensionAgent;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserFullBO;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserCreatDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserExtensionModifyDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserModifyDto;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 根据需求生成小机器人的测试代码
 * @author shiming
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class RobotUserCreateTset extends BaseTestConfig {
	
	private static List<Long> robotUserIds;
	
	static {
		robotUserIds = new ArrayList<>();
		robotUserIds.add(1468566924886272l);
		robotUserIds.add(1468567986045184l);
		robotUserIds.add(1468568688591104l);
		robotUserIds.add(1468569846219008l);
		robotUserIds.add(1468570989166848l);
		robotUserIds.add(1468572180349184l);
		robotUserIds.add(1468573285548288l);
		robotUserIds.add(1468574459953408l);
		robotUserIds.add(1468575617581312l);
		robotUserIds.add(1468576810860800l);
		robotUserIds.add(1468577987363072l);
		robotUserIds.add(1468578614411520l);
		robotUserIds.add(1468579335831808l);
		robotUserIds.add(1468580518625536l);
		robotUserIds.add(1468581697224960l);
		robotUserIds.add(1468582842269952l);
		robotUserIds.add(1468584058618112l);
		robotUserIds.add(1468585113485568l);
		robotUserIds.add(1468586296279296l);
		robotUserIds.add(1468587451810048l);
		robotUserIds.add(1468588598952192l);
		robotUserIds.add(1468589823688960l);
		robotUserIds.add(1468591010676992l);
		robotUserIds.add(1468592195567872l);
		robotUserIds.add(1468592931668224l);
		robotUserIds.add(1468594219319552l);
		robotUserIds.add(1468595332907264l);
		robotUserIds.add(1468596452786432l);
		robotUserIds.add(1468597497168128l);
		robotUserIds.add(1468598608658688l);
		robotUserIds.add(1468599776772352l);
		robotUserIds.add(1468600840028416l);
		robotUserIds.add(1468601953616128l);
		robotUserIds.add(1468603134312704l);
		robotUserIds.add(1468604266774784l);
		robotUserIds.add(1468605342613760l);
		robotUserIds.add(1468606475075840l);
		robotUserIds.add(1468607599149312l);
		robotUserIds.add(1468608777748736l);
		robotUserIds.add(1468609939570944l);
		robotUserIds.add(1468611042672896l);
		robotUserIds.add(1468611793453312l);
		robotUserIds.add(1468612829446400l);
		robotUserIds.add(1468613653627136l);
		robotUserIds.add(1468614968541440l);
		robotUserIds.add(1468616092614912l);
		robotUserIds.add(1468617248145664l);
		robotUserIds.add(1468618420453632l);
		robotUserIds.add(1468619454349568l);
		robotUserIds.add(1468620519702784l);
		robotUserIds.add(1468621689913600l);
		robotUserIds.add(1468622767849728l);
		robotUserIds.add(1468623803842816l);
		robotUserIds.add(1468624927916288l);
		robotUserIds.add(1468626184110336l);
		robotUserIds.add(1468627366904064l);
		robotUserIds.add(1468628551794944l);
		robotUserIds.add(1468629619245312l);
		robotUserIds.add(1468630644752640l);
		robotUserIds.add(1468631722688768l);
		robotUserIds.add(1468632760779008l);
		robotUserIds.add(1468633436061952l);
		robotUserIds.add(1468634537066752l);
		robotUserIds.add(1468635621294336l);
		robotUserIds.add(1468636747464960l);
		robotUserIds.add(1468637896704256l);
		robotUserIds.add(1468638997709056l);
		robotUserIds.add(1468640086130944l);
		robotUserIds.add(1468641283604736l);
		robotUserIds.add(1468642334277888l);
		robotUserIds.add(1468643487711488l);
		robotUserIds.add(1468644599202048l);
		robotUserIds.add(1468645712789760l);
		robotUserIds.add(1468646836863232l);
		robotUserIds.add(1468647927382272l);
		robotUserIds.add(1468649038872832l);
		robotUserIds.add(1468650194403584l);
		robotUserIds.add(1468651345740032l);
		robotUserIds.add(1468652530630912l);
		robotUserIds.add(1468653767950592l);
		robotUserIds.add(1468654831206656l);
		robotUserIds.add(1468656034971904l);
		robotUserIds.add(1468657129685248l);
		robotUserIds.add(1468658272633088l);
		robotUserIds.add(1468659440746752l);
		robotUserIds.add(1468660508197120l);
		robotUserIds.add(1468661233811712l);
		robotUserIds.add(1468662276096256l);
		robotUserIds.add(1468663406461184l);
		robotUserIds.add(1468664534728960l);
		robotUserIds.add(1468665675579648l);
		robotUserIds.add(1468666745127168l);
		robotUserIds.add(1468667988738304l);
		robotUserIds.add(1468668705964288l);
		robotUserIds.add(1468669779706112l);
		robotUserIds.add(1468671031705856l);
		robotUserIds.add(1468672220791040l);
		robotUserIds.add(1468673470693632l);
		robotUserIds.add(1468674624127232l);
		robotUserIds.add(1468675762880768l);
		robotUserIds.add(1468676853399808l);
		robotUserIds.add(1468678013124864l);
		robotUserIds.add(1468679126712576l);
		robotUserIds.add(1468680290631936l);
		robotUserIds.add(1468681376956672l);
		robotUserIds.add(1468682601693440l);
		robotUserIds.add(1468683897733376l);
		robotUserIds.add(1468685002932480l);
		robotUserIds.add(1468686085062912l);
		robotUserIds.add(1468687202844928l);
		robotUserIds.add(1468688278683904l);
		robotUserIds.add(1468689432117504l);
		robotUserIds.add(1468690531025152l);
		robotUserIds.add(1468691594281216l);
		robotUserIds.add(1468692668023040l);
		robotUserIds.add(1468693815165184l);
		robotUserIds.add(1468694825992448l);
		robotUserIds.add(1468695866179840l);
		robotUserIds.add(1468696587600128l);
		robotUserIds.add(1468697665536256l);
		robotUserIds.add(1468698709917952l);
		robotUserIds.add(1468699884323072l);
		robotUserIds.add(1468701037756672l);
		robotUserIds.add(1468702149247232l);
		robotUserIds.add(1468703267029248l);
		robotUserIds.add(1468704424657152l);
		robotUserIds.add(1468705565507840l);
		robotUserIds.add(1468706611986688l);
		robotUserIds.add(1468707788488960l);
		robotUserIds.add(1468708851745024l);
		robotUserIds.add(1468709954846976l);
		robotUserIds.add(1468711114572032l);
		robotUserIds.add(1468712261714176l);
		robotUserIds.add(1468713379496192l);
		robotUserIds.add(1468714593747200l);
		robotUserIds.add(1468715696849152l);
		robotUserIds.add(1468716818825472l);
		robotUserIds.add(1468717955481856l);
		robotUserIds.add(1468719039709440l);
		robotUserIds.add(1468720262349056l);
		robotUserIds.add(1468721296244992l);
		robotUserIds.add(1468722497913088l);
		robotUserIds.add(1468723680706816l);
		robotUserIds.add(1468724848820480l);
		robotUserIds.add(1468725861744896l);
		robotUserIds.add(1468726891446528l);
		robotUserIds.add(1468727965188352l);
		robotUserIds.add(1468729041027328l);
		robotUserIds.add(1468730190266624l);
		robotUserIds.add(1468731264008448l);
		robotUserIds.add(1468732341944576l);
		robotUserIds.add(1468733428269312l);
		robotUserIds.add(1468734510399744l);
		robotUserIds.add(1468735699484928l);
		robotUserIds.add(1468736785809664l);
		robotUserIds.add(1468738088141056l);
		robotUserIds.add(1468739216408832l);
		robotUserIds.add(1468740325802240l);
		robotUserIds.add(1468741349212416l);
		robotUserIds.add(1468742427148544l);
		robotUserIds.add(1468743572193536l);
		robotUserIds.add(1468744719335680l);
		robotUserIds.add(1468745421881600l);
		robotUserIds.add(1468746439000320l);
		robotUserIds.add(1468747646959872l);
		robotUserIds.add(1468748288688384l);
		robotUserIds.add(1468749343555840l);
		robotUserIds.add(1468750480212224l);
		robotUserIds.add(1468751642034432l);
		robotUserIds.add(1468752866771200l);
		robotUserIds.add(1468753938415872l);
		robotUserIds.add(1468755070877952l);
		robotUserIds.add(1468756211728640l);
		robotUserIds.add(1468757216264448l);
		robotUserIds.add(1468758357115136l);
		robotUserIds.add(1468759521034496l);
		robotUserIds.add(1468760601067776l);
		robotUserIds.add(1468761744015616l);
		robotUserIds.add(1468762817757440l);
		robotUserIds.add(1468764080242944l);
		robotUserIds.add(1468766651351296l);
		robotUserIds.add(1468767949488384l);
		robotUserIds.add(1468769163739392l);
		robotUserIds.add(1468770226995456l);
		robotUserIds.add(1468771443343616l);
		robotUserIds.add(1468772544348416l);
		robotUserIds.add(1468773716656384l);
		robotUserIds.add(1469153162756352l);
		robotUserIds.add(1469156780343552l);
		robotUserIds.add(1469157696798976l);
		robotUserIds.add(1469158820872448l);
		robotUserIds.add(1469160035123456l);
		robotUserIds.add(1469160687337728l);
		robotUserIds.add(1469161501032704l);
		robotUserIds.add(1469162639786240l);
		robotUserIds.add(1469163801608448l);
		robotUserIds.add(1469165068288256l);
		robotUserIds.add(1469165754056960l);
		robotUserIds.add(1469166955725056l);
		robotUserIds.add(1469168218210560l);
		robotUserIds.add(1469169390518528l);
		robotUserIds.add(1469170506203392l);
		robotUserIds.add(1469171586236672l);
		robotUserIds.add(1469172758544640l);
		robotUserIds.add(1469173471576320l);
		robotUserIds.add(1469174666952960l);
		robotUserIds.add(1469175795220736l);
		robotUserIds.add(1469177013666048l);
		robotUserIds.add(1469178120962304l);
		robotUserIds.add(1469179192606976l);
		robotUserIds.add(1469179930804480l);
		robotUserIds.add(1469180970991872l);
		robotUserIds.add(1469182181048576l);
		robotUserIds.add(1469183384813824l);
		robotUserIds.add(1469184234160384l);
		robotUserIds.add(1469185326776576l);
		robotUserIds.add(1469186392129792l);
		robotUserIds.add(1469187545563392l);
		robotUserIds.add(1469188325703936l);
		robotUserIds.add(1469189349114112l);
		robotUserIds.add(1469189971968256l);
		robotUserIds.add(1469191102333184l);
		robotUserIds.add(1469192377401600l);
		robotUserIds.add(1469193411297536l);
		robotUserIds.add(1469194560536832l);
		robotUserIds.add(1469195577655552l);
		robotUserIds.add(1469196697534720l);
		robotUserIds.add(1469197836288256l);
		robotUserIds.add(1469198970847488l);
		robotUserIds.add(1469200036200704l);
		robotUserIds.add(1469201170759936l);
		robotUserIds.add(1469202340970752l);
		robotUserIds.add(1469203462947072l);
		robotUserIds.add(1469204670906624l);
		robotUserIds.add(1469205828534528l);
		robotUserIds.add(1469207017619712l);
		robotUserIds.add(1469208194121984l);
		robotUserIds.add(1469209358041344l);
		robotUserIds.add(1469210142376192l);
		robotUserIds.add(1469211190952192l);
		robotUserIds.add(1469212287762688l);
		robotUserIds.add(1469213353115904l);
		robotUserIds.add(1469214464606464l);
		robotUserIds.add(1469215494308096l);
		robotUserIds.add(1469216635158784l);
		robotUserIds.add(1469217719386368l);
		robotUserIds.add(1469218893791488l);
		robotUserIds.add(1469220267426048l);
		robotUserIds.add(1469221313904896l);
		robotUserIds.add(1469222475727104l);
		robotUserIds.add(1469223566246144l);
		robotUserIds.add(1469224604336384l);
		robotUserIds.add(1469225669689600l);
		robotUserIds.add(1469226808443136l);
		robotUserIds.add(1469227924128000l);
		robotUserIds.add(1469229006258432l);
		robotUserIds.add(1469230023377152l);
		robotUserIds.add(1469231132770560l);
		robotUserIds.add(1469232252649728l);
		robotUserIds.add(1469233324294400l);
		robotUserIds.add(1469234490310912l);
		robotUserIds.add(1469235561955584l);
		robotUserIds.add(1469236583268608l);
		robotUserIds.add(1469237766062336l);
		robotUserIds.add(1469238850289920l);
		robotUserIds.add(1469240012112128l);
		robotUserIds.add(1469241020842240l);
		robotUserIds.add(1469242136527104l);
		robotUserIds.add(1469243162034432l);
		robotUserIds.add(1469244284010752l);
		robotUserIds.add(1469245420667136l);
		robotUserIds.add(1469246496506112l);
		robotUserIds.add(1469247652036864l);
		robotUserIds.add(1469248801276160l);
		robotUserIds.add(1469249879212288l);
		robotUserIds.add(1469250973925632l);
		robotUserIds.add(1469252125262080l);
		robotUserIds.add(1469253224169728l);
		robotUserIds.add(1469254333563136l);
		robotUserIds.add(1469255421985024l);
		robotUserIds.add(1469256491532544l);
		robotUserIds.add(1469257603023104l);
		robotUserIds.add(1469258316054784l);
		robotUserIds.add(1469259467391232l);
		robotUserIds.add(1469260551618816l);
		robotUserIds.add(1469261642137856l);
		robotUserIds.add(1469262770405632l);
		robotUserIds.add(1469263969976576l);
		robotUserIds.add(1469265039524096l);
		robotUserIds.add(1469266293620992l);
		robotUserIds.add(1469268036354304l);
		robotUserIds.add(1469271112876288l);
		robotUserIds.add(1469272262115584l);
		robotUserIds.add(1469273302302976l);
		robotUserIds.add(1469273887408384l);
		robotUserIds.add(1469275179254016l);
		robotUserIds.add(1469276318007552l);
		robotUserIds.add(1469277404332288l);
		robotUserIds.add(1469278549377280l);
		robotUserIds.add(1469279692325120l);
		robotUserIds.add(1469280877216000l);
		robotUserIds.add(1469282015969536l);
		robotUserIds.add(1469283131654400l);
		robotUserIds.add(1469284245242112l);
		robotUserIds.add(1469285084102912l);
		robotUserIds.add(1469286206079232l);
		robotUserIds.add(1469287225295104l);
		robotUserIds.add(1469288408088832l);
		robotUserIds.add(1469289418916096l);
		robotUserIds.add(1469290591224064l);
		robotUserIds.add(1469291669160192l);
		robotUserIds.add(1469292759679232l);
		robotUserIds.add(1469293825032448l);
		robotUserIds.add(1469294980563200l);
		robotUserIds.add(1469296150774016l);
		robotUserIds.add(1469297302110464l);
		robotUserIds.add(1469298375852288l);
		robotUserIds.add(1469299455885568l);
		robotUserIds.add(1469300493975808l);
		robotUserIds.add(1469301553037568l);
		robotUserIds.add(1469302154920192l);
		robotUserIds.add(1469303251730688l);
		robotUserIds.add(1469303968956672l);
		robotUserIds.add(1469304992366848l);
		robotUserIds.add(1469306173063424l);
		robotUserIds.add(1469307223736576l);
		robotUserIds.add(1469307995488512l);
		robotUserIds.add(1469309077618944l);
		robotUserIds.add(1469310168137984l);
		robotUserIds.add(1469310864392448l);
		robotUserIds.add(1469311527092480l);
		robotUserIds.add(1469312529531136l);
		robotUserIds.add(1469313739587840l);
		robotUserIds.add(1469314976907520l);
		robotUserIds.add(1469316052746496l);
		robotUserIds.add(1469317189402880l);
		robotUserIds.add(1469318302990592l);
		robotUserIds.add(1469319393509632l);
		robotUserIds.add(1469320540651776l);
		robotUserIds.add(1469321647948032l);
		robotUserIds.add(1469322805575936l);
		robotUserIds.add(1469323896094976l);
		robotUserIds.add(1469324980322560l);
		robotUserIds.add(1469326039384320l);
		robotUserIds.add(1469327129903360l);
		robotUserIds.add(1469328283336960l);
		robotUserIds.add(1469329369661696l);
		robotUserIds.add(1469330487443712l);
		robotUserIds.add(1469331789775104l);
		robotUserIds.add(1469332951597312l);
		robotUserIds.add(1469334054699264l);
		robotUserIds.add(1469335161995520l);
		robotUserIds.add(1469336277680384l);
		robotUserIds.add(1469337374490880l);
		robotUserIds.add(1469338506952960l);
		robotUserIds.add(1469339570209024l);
		robotUserIds.add(1469340732031232l);
		robotUserIds.add(1469341805773056l);
		robotUserIds.add(1469342952915200l);
		robotUserIds.add(1469344087474432l);
		robotUserIds.add(1469345180090624l);
		robotUserIds.add(1469346325135616l);
		robotUserIds.add(1469347359031552l);
		robotUserIds.add(1469348371955968l);
		robotUserIds.add(1469349552652544l);
		robotUserIds.add(1469350685114624l);
		robotUserIds.add(1469351832256768l);
		robotUserIds.add(1469352914387200l);
		robotUserIds.add(1469353556115712l);
		robotUserIds.add(1469354736812288l);
		robotUserIds.add(1469355776999680l);
		robotUserIds.add(1469356861227264l);
		robotUserIds.add(1469357981106432l);
		robotUserIds.add(1469358664777984l);
		robotUserIds.add(1469359264563456l);
		robotUserIds.add(1469360380248320l);
		robotUserIds.add(1469361500127488l);
		robotUserIds.add(1469362554994944l);
		robotUserIds.add(1469363721011456l);
		robotUserIds.add(1469364524220672l);
		robotUserIds.add(1469365581185280l);
		robotUserIds.add(1469366740910336l);
		robotUserIds.add(1469367881761024l);
		robotUserIds.add(1469368965988608l);
		robotUserIds.add(1469370113130752l);
		robotUserIds.add(1469371262370048l);
		robotUserIds.add(1469372348694784l);
		robotUserIds.add(1469373378396416l);
		robotUserIds.add(1469374420680960l);
		robotUserIds.add(1469375481839872l);
		robotUserIds.add(1469376628982016l);
		robotUserIds.add(1469377709015296l);
		robotUserIds.add(1469378751299840l);
		robotUserIds.add(1469380005396736l);
		robotUserIds.add(1469381192384768l);
		robotUserIds.add(1469382175949056l);
		robotUserIds.add(1469383211942144l);
		robotUserIds.add(1469384310849792l);
		robotUserIds.add(1469385353134336l);
		robotUserIds.add(1469386460430592l);
		robotUserIds.add(1469387653710080l);
		robotUserIds.add(1469388790366464l);
		robotUserIds.add(1469389799096576l);
		robotUserIds.add(1469390898004224l);
		robotUserIds.add(1469391967551744l);
		robotUserIds.add(1469392999350528l);
		robotUserIds.add(1469394056315136l);
		robotUserIds.add(1469395482378496l);
		robotUserIds.add(1469396537245952l);
		robotUserIds.add(1469397690679552l);
		robotUserIds.add(1469398783295744l);
		robotUserIds.add(1469399951409408l);
		robotUserIds.add(1469401117425920l);
		robotUserIds.add(1469402231013632l);
		robotUserIds.add(1469403252326656l);
		robotUserIds.add(1469404307194112l);
		robotUserIds.add(1469405359964416l);
		robotUserIds.add(1469406416929024l);
		robotUserIds.add(1469407469699328l);
		robotUserIds.add(1469408484720896l);
		robotUserIds.add(1469409518616832l);
		robotUserIds.add(1469410680439040l);
		robotUserIds.add(1469411355721984l);
		robotUserIds.add(1469412427366656l);
		robotUserIds.add(1469413543051520l);
		robotUserIds.add(1469414688096512l);
		robotUserIds.add(1469415839432960l);
		robotUserIds.add(1469416925757696l);
		robotUserIds.add(1469418047734016l);
		robotUserIds.add(1469421216530688l);
		robotUserIds.add(1469422353187072l);
		robotUserIds.add(1469423475163392l);
		robotUserIds.add(1469424603431168l);
		robotUserIds.add(1469425603772672l);
		robotUserIds.add(1469426742526208l);
		robotUserIds.add(1469427507986688l);
		robotUserIds.add(1469428535591168l);
		robotUserIds.add(1469429663858944l);
		robotUserIds.add(1469430664200448l);
		robotUserIds.add(1469431668736256l);
		robotUserIds.add(1469432767643904l);
		robotUserIds.add(1469433927368960l);
		robotUserIds.add(1469435061928192l);
		robotUserIds.add(1469435867234560l);
		robotUserIds.add(1469436949364992l);
		robotUserIds.add(1469437997940992l);
		robotUserIds.add(1469439050711296l);
		robotUserIds.add(1469440095092992l);
		robotUserIds.add(1469441152057600l);
		robotUserIds.add(1469442255159552l);
		robotUserIds.add(1469443316318464l);
		robotUserIds.add(1469444490723584l);
		robotUserIds.add(1469445572854016l);
		robotUserIds.add(1469446665470208l);
		robotUserIds.add(1469447846166784l);
		robotUserIds.add(1469448991211776l);
		robotUserIds.add(1469450169811200l);
		robotUserIds.add(1469451243553024l);
		robotUserIds.add(1469451933516032l);
		robotUserIds.add(1469452480872704l);
		robotUserIds.add(1469453481214208l);
		robotUserIds.add(1469454596899072l);
		robotUserIds.add(1469455284764928l);
		robotUserIds.add(1469456379478272l);
		robotUserIds.add(1469456989749504l);
		robotUserIds.add(1469457717461248l);
		robotUserIds.add(1469458797494528l);
		robotUserIds.add(1469459833487616l);
		robotUserIds.add(1469460921909504l);
		robotUserIds.add(1469461976776960l);
		robotUserIds.add(1469463025352960l);
		robotUserIds.add(1469464067637504l);
		robotUserIds.add(1469465122504960l);
		robotUserIds.add(1469466328367360l);
		robotUserIds.add(1469467454537984l);
		robotUserIds.add(1469468480045312l);
		robotUserIds.add(1469469633478912l);
		robotUserIds.add(1469470669472000l);
		robotUserIds.add(1469471776768256l);
		robotUserIds.add(1469472804372736l);
		robotUserIds.add(1469473896988928l);
		robotUserIds.add(1469475088171264l);
		robotUserIds.add(1469476182884608l);
		robotUserIds.add(1469477346803968l);
		robotUserIds.add(1469478118555904l);
		robotUserIds.add(1469479278280960l);
		robotUserIds.add(1469481176203520l);
		robotUserIds.add(1469482356900096l);
		robotUserIds.add(1469483520819456l);
		robotUserIds.add(1469484575686912l);
		robotUserIds.add(1469485609582848l);
		robotUserIds.add(1469486804959488l);
		robotUserIds.add(1469487905964288l);
		robotUserIds.add(1469489055203584l);
		robotUserIds.add(1469490204442880l);
		robotUserIds.add(1469491320127744l);
		robotUserIds.add(1469492347732224l);
		robotUserIds.add(1469493392113920l);
		robotUserIds.add(1469494499410176l);
		robotUserIds.add(1469495604609280l);
		robotUserIds.add(1469496586076416l);
		robotUserIds.add(1469497626263808l);
		robotUserIds.add(1469499494826240l);
		robotUserIds.add(1469500560179456l);
		robotUserIds.add(1469501787013376l);
		robotUserIds.add(1469502443421952l);
		robotUserIds.add(1469503573786880l);
		robotUserIds.add(1469504731414784l);
		robotUserIds.add(1469506008580352l);
		robotUserIds.add(1469507124265216l);
		robotUserIds.add(1469508200104192l);
		robotUserIds.add(1469508927815936l);
		robotUserIds.add(1469509982683392l);
		robotUserIds.add(1469510974636288l);
		robotUserIds.add(1469512084029696l);
		robotUserIds.add(1469513170354432l);
		robotUserIds.add(1469514307010816l);
		robotUserIds.add(1469515376558336l);
		robotUserIds.add(1469516087492864l);
		robotUserIds.add(1469517096222976l);
		robotUserIds.add(1469518224490752l);
		robotUserIds.add(1469519333884160l);
		robotUserIds.add(1469520453763328l);
		robotUserIds.add(1469522041307392l);
		robotUserIds.add(1469523066814720l);
		robotUserIds.add(1469524107002112l);
		robotUserIds.add(1469525170258176l);
		robotUserIds.add(1469526319497472l);
		robotUserIds.add(1469527468736768l);
		robotUserIds.add(1469528605393152l);
		robotUserIds.add(1469529677037824l);
		robotUserIds.add(1469530773848320l);
		robotUserIds.add(1469531933573376l);
		robotUserIds.add(1469533099589888l);
		robotUserIds.add(1469534303355136l);
		robotUserIds.add(1469535542771968l);
		robotUserIds.add(1469536591347968l);
		robotUserIds.add(1469537799307520l);
		robotUserIds.add(1469538933866752l);
		robotUserIds.add(1469539959374080l);
		robotUserIds.add(1469541043601664l);
		robotUserIds.add(1469542251561216l);
		robotUserIds.add(1469543314817280l);
		robotUserIds.add(1469544426307840l);
		robotUserIds.add(1469545546187008l);
		robotUserIds.add(1469546594763008l);
		robotUserIds.add(1469547676893440l);
		robotUserIds.add(1469548729663744l);
		robotUserIds.add(1469549753073920l);
		robotUserIds.add(1469550841495808l);
		robotUserIds.add(1469551906849024l);
		robotUserIds.add(1469553053991168l);
		robotUserIds.add(1469554127732992l);
		robotUserIds.add(1469555138560256l);
		robotUserIds.add(1469556245856512l);
		robotUserIds.add(1469557351055616l);
		robotUserIds.add(1469558485614848l);
		robotUserIds.add(1469559540482304l);
		robotUserIds.add(1469560603738368l);
		robotUserIds.add(1469561757171968l);
		robotUserIds.add(1469562830913792l);
		robotUserIds.add(1469563856421120l);
		robotUserIds.add(1469565028729088l);
		robotUserIds.add(1469566234591488l);
		robotUserIds.add(1469567333499136l);
		robotUserIds.add(1469568449184000l);
		robotUserIds.add(1469569541800192l);
		robotUserIds.add(1469570164654336l);
		robotUserIds.add(1469571112567040l);
		robotUserIds.add(1469572245029120l);
		robotUserIds.add(1469572920312064l);
		robotUserIds.add(1469574065357056l);
		robotUserIds.add(1469575118127360l);
		robotUserIds.add(1469576298823936l);
		robotUserIds.add(1469577464840448l);
		robotUserIds.add(1469578477764864l);
		robotUserIds.add(1469579675238656l);
		robotUserIds.add(1469580698648832l);
		robotUserIds.add(1469581738836224l);
		robotUserIds.add(1469582808383744l);
		robotUserIds.add(1469583984886016l);
		robotUserIds.add(1469585071210752l);
		robotUserIds.add(1469586197381376l);
		robotUserIds.add(1469587275317504l);
		robotUserIds.add(1469588409876736l);
		robotUserIds.add(1469589498298624l);
		robotUserIds.add(1469590261661952l);
		robotUserIds.add(1469591285072128l);
		robotUserIds.add(1469592476254464l);
		robotUserIds.add(1469593547899136l);
		robotUserIds.add(1469594613252352l);
		robotUserIds.add(1469595737325824l);
		robotUserIds.add(1469596880273664l);
		robotUserIds.add(1469597968695552l);
		robotUserIds.add(1469599136809216l);
		robotUserIds.add(1469600344768768l);
		robotUserIds.add(1469601395441920l);
		robotUserIds.add(1469602567749888l);
		robotUserIds.add(1469603190604032l);
		robotUserIds.add(1469604239180032l);
		robotUserIds.add(1469605375836416l);
		robotUserIds.add(1469606386663680l);
		robotUserIds.add(1469607487668480l);
		robotUserIds.add(1469608611741952l);
		robotUserIds.add(1469609748398336l);
		robotUserIds.add(1469610824237312l);
		robotUserIds.add(1469611937825024l);
		robotUserIds.add(1469613076578560l);
		robotUserIds.add(1469614202749184l);
		robotUserIds.add(1469615320531200l);
		robotUserIds.add(1469616482353408l);
		robotUserIds.add(1469617631592704l);
		robotUserIds.add(1469618694848768l);
		robotUserIds.add(1469619898614016l);
		robotUserIds.add(1469621035270400l);
		robotUserIds.add(1469622136275200l);
		robotUserIds.add(1469623260348672l);
		robotUserIds.add(1469624315216128l);
		robotUserIds.add(1469625460261120l);
		robotUserIds.add(1469626452214016l);
		robotUserIds.add(1469627530150144l);
		robotUserIds.add(1469628580823296l);
		robotUserIds.add(1469629631496448l);
		robotUserIds.add(1469630732501248l);
		robotUserIds.add(1469631441338624l);
		robotUserIds.add(1469632104038656l);
		robotUserIds.add(1469632817070336l);
		robotUserIds.add(1469633439924480l);
		robotUserIds.add(1469634060681472l);
		robotUserIds.add(1469635050537216l);
		robotUserIds.add(1469636084433152l);
		robotUserIds.add(1469637225283840l);
		robotUserIds.add(1469637848137984l);
		robotUserIds.add(1469638596821248l);
		robotUserIds.add(1469639722991872l);
		robotUserIds.add(1469640897396992l);
		robotUserIds.add(1469641958555904l);
		robotUserIds.add(1469643124572416l);
		robotUserIds.add(1469644206702848l);
		robotUserIds.add(1469645347553536l);
		robotUserIds.add(1469646033322240l);
		robotUserIds.add(1469647553757440l);
		robotUserIds.add(1469648648470784l);
		robotUserIds.add(1469649382473984l);
		robotUserIds.add(1469650061951232l);
		robotUserIds.add(1469651177636096l);
		robotUserIds.add(1469652247183616l);
		robotUserIds.add(1469653352382720l);
		robotUserIds.add(1469654520496384l);
		robotUserIds.add(1469655640375552l);
		robotUserIds.add(1469656630231296l);
		robotUserIds.add(1469657794150656l);
		robotUserIds.add(1469658437976320l);
		robotUserIds.add(1469659620770048l);
		robotUserIds.add(1469660757426432l);
		robotUserIds.add(1469661923442944l);
		robotUserIds.add(1469663020253440l);
		robotUserIds.add(1469664142229760l);
		robotUserIds.add(1469665270497536l);
		robotUserIds.add(1469666346336512l);
		robotUserIds.add(1469667290054912l);
		robotUserIds.add(1469667925491968l);
		robotUserIds.add(1469668978262272l);
		robotUserIds.add(1469670075072768l);
		robotUserIds.add(1469671115260160l);
		robotUserIds.add(1469672216264960l);
		robotUserIds.add(1469672906227968l);
		robotUserIds.add(1469674044981504l);
		robotUserIds.add(1469675122917632l);
		robotUserIds.add(1469676209242368l);
		robotUserIds.add(1469677341704448l);
		robotUserIds.add(1469678493040896l);
		robotUserIds.add(1469679621308672l);
		robotUserIds.add(1469680711827712l);
		robotUserIds.add(1469681873649920l);
		robotUserIds.add(1469682892865792l);
		robotUserIds.add(1469684019036416l);
		robotUserIds.add(1469685029863680l);
		robotUserIds.add(1469686281863424l);
		robotUserIds.add(1469687288496384l);
		robotUserIds.add(1469688473387264l);
		robotUserIds.add(1469689521963264l);
		robotUserIds.add(1469690566344960l);
		robotUserIds.add(1469691612823808l);
		robotUserIds.add(1469692692857088l);
		robotUserIds.add(1469693703684352l);
		robotUserIds.add(1469694764843264l);
		robotUserIds.add(1469695853265152l);
		robotUserIds.add(1469696885063936l);
		robotUserIds.add(1469698044788992l);
		robotUserIds.add(1469699120627968l);
		robotUserIds.add(1469700246798592l);
		robotUserIds.add(1469701316346112l);
		robotUserIds.add(1469702398476544l);
		robotUserIds.add(1469703512064256l);
		robotUserIds.add(1469704638234880l);
		robotUserIds.add(1469705785377024l);
		robotUserIds.add(1469706894770432l);
		robotUserIds.add(1469707618287872l);
		robotUserIds.add(1469708637503744l);
		robotUserIds.add(1469709757382912l);
		robotUserIds.add(1469710940176640l);
		robotUserIds.add(1469711990849792l);
		robotUserIds.add(1469713125409024l);
		robotUserIds.add(1469714222219520l);
		robotUserIds.add(1469715245629696l);
		robotUserIds.add(1469716436812032l);
		robotUserIds.add(1469717479096576l);
		robotUserIds.add(1469718504603904l);
		robotUserIds.add(1469719683203328l);
		robotUserIds.add(1469720329126144l);
		robotUserIds.add(1469721375604992l);
		robotUserIds.add(1469722514358528l);
		robotUserIds.add(1469723588100352l);
		robotUserIds.add(1469724718465280l);
		robotUserIds.add(1469725802692864l);
		robotUserIds.add(1469726832394496l);
		robotUserIds.add(1469727931302144l);
		robotUserIds.add(1469729116193024l);
		robotUserIds.add(1469730261238016l);
		robotUserIds.add(1469731431448832l);
		robotUserIds.add(1469732521967872l);
		robotUserIds.add(1469733589418240l);
		robotUserIds.add(1469734765920512l);
		robotUserIds.add(1469735822885120l);
		robotUserIds.add(1469736921792768l);
		robotUserIds.add(1469738075226368l);
		robotUserIds.add(1469739142676736l);
		robotUserIds.add(1469740266750208l);
		robotUserIds.add(1469741361463552l);
		robotUserIds.add(1469741967540480l);
		robotUserIds.add(1469743030796544l);
		robotUserIds.add(1469744129704192l);
		robotUserIds.add(1469745174085888l);
		robotUserIds.add(1469746153455872l);
		robotUserIds.add(1469747157991680l);
		robotUserIds.add(1469748336591104l);
		robotUserIds.add(1469749380972800l);
		robotUserIds.add(1469750530212096l);
		robotUserIds.add(1469751689937152l);
		robotUserIds.add(1469752826593536l);
		robotUserIds.add(1469753931792640l);
		robotUserIds.add(1469755030700288l);
		robotUserIds.add(1469756165259520l);
		robotUserIds.add(1469757215932672l);
		robotUserIds.add(1469758340006144l);
		robotUserIds.add(1469759413747968l);
		robotUserIds.add(1469760596541696l);
		robotUserIds.add(1469761649312000l);
		robotUserIds.add(1469762752413952l);
		robotUserIds.add(1469763926819072l);
		robotUserIds.add(1469765080252672l);
		robotUserIds.add(1469765709398272l);
		robotUserIds.add(1469766688768256l);
		robotUserIds.add(1469767844299008l);
		robotUserIds.add(1469768911749376l);
		robotUserIds.add(1469769951936768l);
		robotUserIds.add(1469771027775744l);
		robotUserIds.add(1469772093128960l);
		robotUserIds.add(1469773871513856l);
		robotUserIds.add(1469774588739840l);
		robotUserIds.add(1469775293382912l);
		robotUserIds.add(1469776333570304l);
		robotUserIds.add(1469777487003904l);
		robotUserIds.add(1469778539774208l);
		robotUserIds.add(1469779733053696l);
		robotUserIds.add(1469780861321472l);
		robotUserIds.add(1469782025240832l);
		robotUserIds.add(1469783182868736l);
		robotUserIds.add(1469784332108032l);
		robotUserIds.add(1469785506513152l);
		robotUserIds.add(1469786555089152l);
		robotUserIds.add(1469787639316736l);
		robotUserIds.add(1469788606103808l);
		robotUserIds.add(1469789707108608l);
		robotUserIds.add(1469790772461824l);
		robotUserIds.add(1469792137707776l);
		robotUserIds.add(1469793177895168l);
		robotUserIds.add(1469794285191424l);
		robotUserIds.add(1469795293921536l);
		robotUserIds.add(1469795971301632l);
		robotUserIds.add(1469797116346624l);
		robotUserIds.add(1469798175408384l);
		robotUserIds.add(1469799223984384l);
		robotUserIds.add(1469800257880320l);
		robotUserIds.add(1469801472131328l);
		robotUserIds.add(1469802497638656l);
		robotUserIds.add(1469803554603264l);
		robotUserIds.add(1469804705939712l);
		robotUserIds.add(1469805823721728l);
		robotUserIds.add(1469806895366400l);
		robotUserIds.add(1469807952331008l);
		robotUserIds.add(1469809124638976l);
		robotUserIds.add(1469810292752640l);
		robotUserIds.add(1469811406340352l);
		robotUserIds.add(1469812534608128l);
		robotUserIds.add(1469813224571136l);
		robotUserIds.add(1469813893562624l);
		robotUserIds.add(1469814539485440l);
		robotUserIds.add(1469815239934208l);
		robotUserIds.add(1469815860691200l);
		robotUserIds.add(1469816628248832l);
		robotUserIds.add(1469817746030848l);
		robotUserIds.add(1469818830258432l);
		robotUserIds.add(1469819467792640l);
		robotUserIds.add(1469820447162624l);
		robotUserIds.add(1469821023879424l);
		robotUserIds.add(1469821646733568l);
		robotUserIds.add(1469822695309568l);
		robotUserIds.add(1469823769051392l);
		robotUserIds.add(1469824341573888l);
		robotUserIds.add(1469825023148288l);
		robotUserIds.add(1469826040267008l);
		robotUserIds.add(1469826753298688l);
		robotUserIds.add(1469827485204736l);
		robotUserIds.add(1469828590403840l);
		robotUserIds.add(1469829651562752l);
		robotUserIds.add(1469830700138752l);
		robotUserIds.add(1469831656440064l);
		robotUserIds.add(1469832795193600l);
		robotUserIds.add(1469833889906944l);
		robotUserIds.add(1469834946871552l);
		robotUserIds.add(1469836081430784l);
		robotUserIds.add(1469837138395392l);
		robotUserIds.add(1469837692043520l);
		robotUserIds.add(1469838413463808l);
		robotUserIds.add(1469839587868928l);
		robotUserIds.add(1469840638542080l);
		robotUserIds.add(1469841703895296l);
		robotUserIds.add(1469842859426048l);
		robotUserIds.add(1469843882836224l);
		robotUserIds.add(1469844979646720l);
		robotUserIds.add(1469846059680000l);
		robotUserIds.add(1469847141810432l);
		robotUserIds.add(1469848314118400l);
		robotUserIds.add(1469848930681088l);
		robotUserIds.add(1469850092503296l);
		robotUserIds.add(1469851191410944l);
		robotUserIds.add(1469852355330304l);
		robotUserIds.add(1469853385031936l);
		robotUserIds.add(1469854450385152l);
		robotUserIds.add(1469855517835520l);
		robotUserIds.add(1469856576897280l);
		robotUserIds.add(1469857621278976l);
		robotUserIds.add(1469858655174912l);
		robotUserIds.add(1469859697459456l);
		robotUserIds.add(1469860850893056l);
		robotUserIds.add(1469861956092160l);
		robotUserIds.add(1469863015153920l);
		robotUserIds.add(1469864105672960l);
		robotUserIds.add(1469865166831872l);
		robotUserIds.add(1469866127327488l);
		robotUserIds.add(1469867083628800l);
		robotUserIds.add(1469868172050688l);
		robotUserIds.add(1469869252083968l);
		robotUserIds.add(1469869931561216l);
		robotUserIds.add(1469871093383424l);
		robotUserIds.add(1469871751889152l);
		robotUserIds.add(1469872775299328l);
		robotUserIds.add(1469873819681024l);
		robotUserIds.add(1469874509644032l);
		robotUserIds.add(1469875663077632l);
		robotUserIds.add(1469876715847936l);
		robotUserIds.add(1469877397422336l);
		robotUserIds.add(1469878133522688l);
		robotUserIds.add(1469879182098688l);
		robotUserIds.add(1469880278909184l);
		robotUserIds.add(1469881296027904l);
		robotUserIds.add(1469882445267200l);
		robotUserIds.add(1469883514814720l);
		robotUserIds.add(1469884246720768l);
		robotUserIds.add(1469885351919872l);
		robotUserIds.add(1469886616502528l);
		robotUserIds.add(1469887646204160l);
		robotUserIds.add(1469888734626048l);
		robotUserIds.add(1469889760133376l);
		robotUserIds.add(1469890783543552l);
		robotUserIds.add(1469891832119552l);
		robotUserIds.add(1469892918444288l);
		robotUserIds.add(1469893541298432l);
		robotUserIds.add(1469894206095616l);
		robotUserIds.add(1469895344849152l);
		robotUserIds.add(1469896466825472l);
		robotUserIds.add(1469897582510336l);
		robotUserIds.add(1469898268279040l);
		robotUserIds.add(1469899876794624l);
		robotUserIds.add(1469900533203200l);
		robotUserIds.add(1469901135085824l);
		robotUserIds.add(1469901783105792l);
		robotUserIds.add(1469902429028608l);
		robotUserIds.add(1469903473410304l);
		robotUserIds.add(1469904169664768l);
		robotUserIds.add(1469904769450240l);
		robotUserIds.add(1469905933369600l);
		robotUserIds.add(1469906593972480l);
		robotUserIds.add(1469907221020928l);
		robotUserIds.add(1469907829195008l);
		robotUserIds.add(1469908554809600l);
		robotUserIds.add(1469909110554880l);
		robotUserIds.add(1469910190588160l);
		robotUserIds.add(1469911310467328l);
		robotUserIds.add(1469912291934464l);
		robotUserIds.add(1469913363579136l);
		robotUserIds.add(1469914449903872l);
		robotUserIds.add(1469915112603904l);
		robotUserIds.add(1469915748040960l);
		robotUserIds.add(1469916802908416l);
		robotUserIds.add(1469917843095808l);
		robotUserIds.add(1469918946197760l);
		robotUserIds.add(1469919963316480l);
		robotUserIds.add(1469921171276032l);
		robotUserIds.add(1469922310029568l);
		robotUserIds.add(1469923497017600l);
		robotUserIds.add(1469924503650560l);
		robotUserIds.add(1469925311054080l);
		robotUserIds.add(1469926414156032l);
		robotUserIds.add(1469927603241216l);
		robotUserIds.add(1469928309981440l);
		robotUserIds.add(1469929360654592l);
		robotUserIds.add(1469930428104960l);
		robotUserIds.add(1469931524915456l);
		robotUserIds.add(1469932139380992l);
		robotUserIds.add(1469933280231680l);
		robotUserIds.add(1469934435762432l);
		robotUserIds.add(1469935522087168l);
		robotUserIds.add(1469936235118848l);
		robotUserIds.add(1469936874750208l);
		robotUserIds.add(1469937545838848l);
		robotUserIds.add(1469938581831936l);
		robotUserIds.add(1469939668156672l);
		robotUserIds.add(1469940712538368l);
		robotUserIds.add(1469941266186496l);
		robotUserIds.add(1469942386065664l);
		robotUserIds.add(1469943388504320l);
		robotUserIds.add(1469944455954688l);
		robotUserIds.add(1469945525502208l);
		robotUserIds.add(1469946693615872l);
		robotUserIds.add(1469947775746304l);
		robotUserIds.add(1469948434252032l);
		robotUserIds.add(1469949484925184l);
		robotUserIds.add(1469950590124288l);
		robotUserIds.add(1469951649186048l);
		robotUserIds.add(1469952722927872l);
		robotUserIds.add(1469953863778560l);
		robotUserIds.add(1469954962686208l);
		robotUserIds.add(1469956051108096l);
		robotUserIds.add(1469957164695808l);
		robotUserIds.add(1469958238437632l);
		robotUserIds.add(1469959335248128l);
		robotUserIds.add(1469960352366848l);
		robotUserIds.add(1469961463857408l);
		robotUserIds.add(1469962577445120l);
		robotUserIds.add(1469963550523648l);
		robotUserIds.add(1469964718637312l);
		robotUserIds.add(1469965802864896l);
		robotUserIds.add(1469966362804480l);
		robotUserIds.add(1469967426060544l);
		robotUserIds.add(1469968569008384l);
		robotUserIds.add(1469969649041664l);
		robotUserIds.add(1469970752143616l);
		robotUserIds.add(1469971878314240l);
		robotUserIds.add(1469972941570304l);
		robotUserIds.add(1469974063546624l);
		robotUserIds.add(1469975139385600l);
		robotUserIds.add(1469976198447360l);
		robotUserIds.add(1469977247023360l);
		robotUserIds.add(1469977970540800l);
		robotUserIds.add(1469978985562368l);
		robotUserIds.add(1469980086567168l);
		robotUserIds.add(1469981105783040l);
		robotUserIds.add(1469982160650496l);
		robotUserIds.add(1469983307792640l);
		robotUserIds.add(1469984377340160l);
		robotUserIds.add(1469985388167424l);
		robotUserIds.add(1469986474492160l);
		robotUserIds.add(1469987611148544l);
		robotUserIds.add(1469988638753024l);
		robotUserIds.add(1469989660066048l);
		robotUserIds.add(1469990700253440l);
		robotUserIds.add(1469991738343680l);
		robotUserIds.add(1469992854028544l);
		robotUserIds.add(1469993902604544l);
		robotUserIds.add(1469994963763456l);
		robotUserIds.add(1469996081545472l);
		robotUserIds.add(1469997230784768l);
		robotUserIds.add(1469998350663936l);
		
	}
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserExtensionAgent userExtensionAgent;
	
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IAppAreaService appAreaService;
	
	@Test
	public void testCreateData() throws Exception {
		List robots = new ArrayList();
		for(int i = robotUserIds.size() - 1; i > 0; i--) {
			UserBO user = userAgent.findById(robotUserIds.get(i));
			if(Tools.isNotNull(user)) {
//				OldRobot robot = new OldRobot();
//				robot.setNickname(user.getNickname());
//				robot.setUserid(user.getUserid());
//				robot.setClientid(user.getClientid());
//				robot.setPhoto(user.getPhoto());
//				robot.setSignature(user.getSignature());
//				robots.add(new OldRobot(user.getUserid(), user.getNickname(), user.getPhoto(), user.getSignature(), user.getClientid()));
				if(user.getSex() == 2) {
					logger.info("robots.add(new OldRobot(" +user.getUserid() +"L, " + "\""+ user.getNickname() + "\"" +", " + "\""+ user.getPhoto() + "\"" +", " + "\""+ user.getSignature() + "\"" +", " + "\""+ user.getClientid() + "\"" +"));");
				}
			}
//			logger.info("robot.setNickname("+ "\""+ user.getNickname() + "\"" +")");
//			logger.info("robot.setUserid("+ user.getUserid() + ")");
//			logger.info("robot.setClientid("+ "\""+ user.getClientid() + "\"" +")");
//			logger.info("robot.setPhoto("+ "\""+ user.getPhoto() + "\"" +")");
//			logger.info("robot.setSignature("+ "\""+ user.getSignature() + "\"" +")");
//			logger.info(" "+ user.getNickname() +" "+ user.getUserid() + " " +user.getClientid() + " "+user.getPhoto() +" "+user.getSignature());
//			System.out.println("name: "+ user.getNickname() +"  Userid: "+ user.getUserid() + " Clientid:" +user.getClientid() + " Photo:"+user.getPhoto() +" Signature:"+user.getSignature());
		}
		
	}
	
	@Test
	public void testCreatePath() throws Exception {
		List<String> paths = new ArrayList<>();
		for(int i = robotUserIds.size() - 1; i > 0; i--) {
			UserBO user = userAgent.findById(robotUserIds.get(i));
			if(Tools.isNotNull(user)) {
				if(user.getSex() == 2) {
					logger.info("."+user.getPhoto());
					paths.add("."+user.getPhoto());
				}
			}
		}
		StringBuilder str = new StringBuilder();
		for (String path : paths) {
			str.append(path+" ");
		}
		logger.info(str.toString());
		
	}
	
//	@Test
	public void testCreateRobot1() throws Exception {
		/**
		 * 模拟测试生成一个小机器人数据
		 *   首先根据小机器人id找到对应的机器人的数据
		 *   配置生成对应新机器人的数据
		 *   进行新机器人数据的插入处理
		 *   根据需求来更新新机器人的扩展信息
		 */
		
		/**
		 * 首先对一条机器人数据的生成处理
		 */
		UserBO oldRobot = userAgent.findById(1468558397866240l);
		//进行数据填充处理
		UserCreatDto creat = new UserCreatDto();
		creat.setPhoto(oldRobot.getPhoto());
		//设置出生年月     需要配置
//		creat.setBirthday(Tools.getDate(oldRobot.getBirthday()));
//		creat.setBirthday(oldRobot.getBirthday());
		creat.setFr(9);
		//设置Openid值      需要配置
		creat.setOpenid(oldRobot.getUserid()+"");
		
		//配置城市信息
		creat.setCountryid(oldRobot.getCountryid());
		creat.setProvinceid(oldRobot.getProvinceid());
		creat.setCityid(oldRobot.getCityid());
		
		creat.setSex(2);
		creat.setSignature(oldRobot.getSignature());
		creat.setClientid(oldRobot.getClientid());
		creat.setPlatform(1);
//		creat.setAppVersion(RequestUtils.getCurrent().getHeader().getVersion());
//		creat.setVersionCode(RequestUtils.getCurrent().getHeader().getVersioncode());
		creat.setLng(0D);
		creat.setLat(0D);
		UserFullBO newRobot = userAgent.createUser(creat, null);
		//这个地方需要把原先的机器人的数据干掉   这样才能保证新的机器人的名称不出现重复
		userContract.deleteById(oldRobot.getUserid());
		
		//这里需要进一步更新用户的名称
		UserModifyDto dto = new UserModifyDto();
		dto.setUserid(newRobot.getUserid());
		dto.setNickname(oldRobot.getNickname());
		userAgent.updateUser(dto);
		
		//下面进行更新用户扩展表中的信息处理
		UserExtensionModifyDto dtoUserExtension = new UserExtensionModifyDto();
		//这个是类别  0-6
		/**
		 * 	default_null(0,""),
			income1(1,"3000元以上/月"),
			income2(2,"5000元以上/月"),
			income3(3,"10000元以上/月"),
			income4(4,"20000元以上/月"),
			income5(5,"50万元以上/年"),
			secrecy(6,"保密"),
		 */
//		dtoUserExtension.setIncome(income);
//		dtoUserExtension.setUserid(newRobot.getUserid());
		//这个是汉字输入
		/**
			<item>职业经理人</item>
        	<item>私营企业主</item>
        	<item>中层管理者</item>
        	<item>空乘</item>
        	<item>公司员工</item>
        	<item>军人</item>
        	<item>医生</item>
        	<item>教师</item>
        	<item>工程师</item>
        	<item>护士</item>
        	<item>高校学生</item>
        	<item>艺人</item>
        	<item>机关工作</item>
        	<item>互联网从业者</item>
        	<item>警察</item>
		 */
//		dtoUserExtension.setJob(job);
		
		/**
		 * 	default_null(0,""),
			find_friends(1,"寻找知己玩伴"),
			find_love(2,"找寻恋爱对象"),
			only_married(3,"以结婚为目的"),
			find_king(4,"两情相悦"),
			fastsex(5,"饮食男女"),
			find_playmate(6,"找玩伴"),
		 */
//		dtoUserExtension.setMakeFriend(makeFriend);
		//感情   是个数字
		/**
		 *  default_null(0,""),
			single(1,"单身"),
			love(2,"恋爱"),
			married(3,"已婚"),
			separation(4,"分居"),
			divorced(5,"离异"),
		 */
//		dtoUserExtension.setMarriage(marriage);
		//个人信息对感情的看法
		/**
		 * 	default_null(0,""),
			conservative(1,"保守"),
			conservative_asexual(2,"保守且无经历"),
			let_it_be(3,"顺其自然"),
			resonance_between_two_lovers(4,"两情相悦"),
			open(5,"开放"),
			crazy(6,"疯狂"),
		 */
//		dtoUserExtension.setSexOpinion(sexOpinion);
		//个人信息对另一半的要求
		/**
		 * 	default_null(0,""),
			longleg(1,"长腿"),
			chubby(2,"微胖"),
			can_swim(3,"会游泳"),
			bingle(4,"短发"),
			shape(5,"身材好"),
			big_breasts(6,"小鲜肉"),
			oxeye(7,"大眼睛"),
			good_work(8,"成熟"),
			risibility(9,"无要求"),
		 */
//		dtoUserExtension.setSpouseOpinion(spouseOpinion);
		//身高     是个数字
//		dtoUserExtension.setStature(stature);
		//体重     是个数字
//		dtoUserExtension.setWeight(weight);
		//星座     是汉字输入  根据年龄来获取
//		dtoUserExtension.setZodiac(zodiac);
		//特点   是字符串 需要以,分割开来
		/**
        <item>摄影</item>
        <item>游泳</item>
        <item>羽毛球</item>
        <item>烹饪美食</item>
        <item>英语</item>
        <item>驾驶</item>
        <item>弹钢琴</item>
        <item>户外探险</item>
        <item>美甲</item>
        <item>修电脑</item>
        <item>化妆</item>
        <item>手机贴膜</item>
        <item>期货</item>
        <item>造型设计</item>
        <item>涂鸦</item>
        <item>高尔夫球</item>
        <item>舞蹈</item>
        <item>魔术</item>
        <item>健身</item>
        <item>K歌</item>
        <item>钓鱼</item>
        <item>PS照片</item>
        <item>读书</item>
        <item>日语</item>
        <item>绘画</item>
        <item>篮球</item>
        <item>王者荣耀</item>
		 */
//		dtoUserExtension.setTraitPoint(traitPoint);
//		userExtensionAgent.updateUserExtension(dtoUserExtension);
	}
	
	@Test
	public void testCreateRobot() throws Exception {
		List list = new ArrayList();
		list.add("摄影");
		list.add("游泳");
		list.add("羽毛球");
		list.add("烹饪美食");
		list.add("英语");
		list.add("驾驶");
		list.add("弹钢琴");
		list.add("户外探险");
		list.add("美甲");
		list.add("修电脑");
		list.add("化妆");
		list.add("手机贴膜");
		list.add("期货");
		list.add("造型设计");
		list.add("涂鸦");
		list.add("高尔夫球");
		list.add("舞蹈");
		list.add("魔术");
		list.add("健身");
		list.add("K歌");
		list.add("钓鱼");
		list.add("PS照片");
		list.add("读书");
		list.add("日语");
		list.add("绘画");
		list.add("篮球");
		list.add("王者荣耀");
		
		//首先配置需要生成机器人的配置对象
		List userExtensions = new ArrayList();
		RobotUserExtension userExtension = new RobotUserExtension();
		userExtension.ageStart = 18;
		userExtension.ageEnd = 25;
		userExtension.job = "空乘";
		userExtension.income = 3;
		userExtension.statureStart = 175;
		userExtension.statureEnd = 185;
		userExtension.weightStart = 50;
		userExtension.weightEnd = 55;
		userExtension.marriage.add(1);
		userExtension.marriage.add(2);
		userExtension.marriage.add(3);
		userExtension.count = 1;
		userExtensions.add(userExtension);
		
		UserBO oldRobot = userAgent.findById(1468565647720704l);
		//进行数据填充处理
		UserCreatDto creat = new UserCreatDto();
		creat.setPhoto(oldRobot.getPhoto());
		//设置出生年月     需要配置
		int date = getRandomNumber(userExtension.ageStart, userExtension.ageEnd);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017-date);
		cal.set(Calendar.MONTH, getRandomNumber(1,11));
		cal.set(Calendar.DAY_OF_MONTH, getRandomNumber(1,28));
		Date birthday = cal.getTime();
		
//		creat.setBirthday(Tools.getDate(oldRobot.getBirthday()));
		creat.setBirthday(oldRobot.getBirthday());
		creat.setFr(9);
		//设置Openid值      需要配置
		creat.setOpenid(oldRobot.getUserid()+"");

		//配置城市信息
		creat.setCountryid(3592);
		creat.setProvinceid(3593);
		creat.setCityid(4146);
		
		creat.setSex(2);
		creat.setSignature(oldRobot.getSignature());
		creat.setClientid(oldRobot.getClientid());
		creat.setPlatform(1);
//		creat.setAppVersion(RequestUtils.getCurrent().getHeader().getVersion());
//		creat.setVersionCode(RequestUtils.getCurrent().getHeader().getVersioncode());
		creat.setLng(0D);
		creat.setLat(0D);
		UserFullBO newRobot = userAgent.createUser(creat, null);
		//这个地方需要把原先的机器人的数据干掉   这样才能保证新的机器人的名称不出现重复
		userContract.deleteById(oldRobot.getUserid());
		
		//这里需要进一步更新用户的名称
		UserModifyDto dto = new UserModifyDto();
		dto.setUserid(newRobot.getUserid());
		dto.setNickname(oldRobot.getNickname());
		dto.setBirthday(birthday);
		userAgent.updateUser(dto);
		
		//下面进行更新用户扩展表中的信息处理
		UserExtensionModifyDto dtoUserExtension = new UserExtensionModifyDto();
		//这个是类别  0-6
		dtoUserExtension.setIncome(userExtension.income);
		//用户id值
		dtoUserExtension.setUserid(newRobot.getUserid());
		//这个是汉字输入
		dtoUserExtension.setJob(userExtension.job);
		//交友目地
		dtoUserExtension.setMakeFriend(getRandomNumber(1, 6));
		if(userExtension.marriage.size() > 1) {
			//感情   是个数字
			dtoUserExtension.setMarriage((Integer) (createRandomList(userExtension.marriage, 1).get(0)));
		} else {
			//感情   是个数字
			dtoUserExtension.setMarriage((Integer) userExtension.marriage.get(0));
		}
		//个人信息对感情的看法  对性
		dtoUserExtension.setSexOpinion(getRandomNumber(1, 6));
		//个人信息对另一半的要求
		dtoUserExtension.setSpouseOpinion(getRandomNumber(1, 9));
		//身高     是个数字
		dtoUserExtension.setStature(getRandomNumber(userExtension.statureStart, userExtension.statureEnd));
		//体重     是个数字
		dtoUserExtension.setWeight(getRandomNumber(userExtension.weightStart, userExtension.weightEnd));
		//星座     是汉字输入  根据年龄来获取
		dtoUserExtension.setZodiac(Tools.getConstellation(birthday));
		//特点   是字符串 需要以,分割开来
		dtoUserExtension.setTraitPoint(Tools.joinString(createRandomList(list, 3)));
		userExtensionAgent.updateUserExtension(dtoUserExtension);
		
	}
	
	
	@Test
	public void testUpdateRobot() throws Exception {
		//设置出生年月     需要配置
		int date = getRandomNumber(18, 25);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017-date);
		cal.set(Calendar.MONTH, getRandomNumber(1,11));
		cal.set(Calendar.DAY_OF_MONTH, getRandomNumber(1,28));
		Date birthday = cal.getTime();
		System.out.println("===========" + Tools.getDate(birthday));
		//这里需要进一步更新用户的名称
		UserModifyDto dto = new UserModifyDto();
		dto.setUserid(27193605080809728L);
		dto.setBirthday(birthday);
		userAgent.updateUser(dto);
	}
	
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
	public List createRandomList(List list, int n) {
        Map map = new HashMap();
        List listNew = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random =getRandomNumber(0, list.size()-1);
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    System.out.println(random + "===========" + list.get(random));
                    listNew.add(list.get(random));
                }
            }
            return listNew;
        }
    }
	
	@Test
	public void createCity() {
		AreaDto cityArea = appAreaService.getCityByName("北京");
		if (Tools.isNotNull(cityArea)) {
			AreaDto[] areas = appAreaService.getAreas(cityArea.getId());
			System.out.println("setCountryid" + "===========" + areas[0].getId());
			System.out.println("setProvinceid" + "===========" + areas[1].getId());
			System.out.println("setCityid" + "===========" + areas[2].getId());
			/**
			 * setCountryid===========3592
			   setProvinceid===========3593
			   setCityid===========4146
			 */
		}
	}
	
//	@Test
	public void testRandomList() {
		List list = new ArrayList();
		list.add("摄影");
		list.add("游泳");
		list.add("羽毛球");
		list.add("烹饪美食");
		list.add("英语");
		list.add("驾驶");
		list.add("弹钢琴");
		list.add("户外探险");
		list.add("美甲");
		list.add("修电脑");
		list.add("化妆");
		list.add("手机贴膜");
		list.add("期货");
		list.add("造型设计");
		list.add("涂鸦");
		list.add("高尔夫球");
		list.add("舞蹈");
		list.add("魔术");
		list.add("健身");
		list.add("K歌");
		list.add("钓鱼");
		list.add("PS照片");
		list.add("读书");
		list.add("日语");
		list.add("绘画");
		list.add("篮球");
		list.add("王者荣耀");
		List listnew = createRandomList(list, 3);
		System.out.println("===========" + listnew.size());
		String str = Tools.joinString(listnew);
		System.out.println("===========" + str);
	}
	
//	@Test
	public void testRandomListSingle() {
		RobotUserExtension userExtension = new RobotUserExtension();
		userExtension.ageStart = 18;
		userExtension.ageEnd = 25;
		userExtension.job = "空乘";
		userExtension.income = 3;
		userExtension.statureStart = 175;
		userExtension.statureEnd = 185;
		userExtension.weightStart = 50;
		userExtension.weightEnd = 55;
		userExtension.marriage.add(1);
		userExtension.marriage.add(2);
		userExtension.marriage.add(3);
		List listnew = createRandomList(userExtension.marriage, 1);
		System.out.println("===========" + listnew.size());
		String str = Tools.joinString(listnew);
		System.out.println("===========" + str);
	}
	
//	@Test
	public void testAge() {
		RobotUserExtension userExtension = new RobotUserExtension();
		userExtension.ageStart = 18;
		userExtension.ageEnd = 25;
		userExtension.job = "空乘";
		userExtension.income = 3;
		userExtension.statureStart = 175;
		userExtension.statureEnd = 185;
		userExtension.weightStart = 50;
		userExtension.weightEnd = 55;
		userExtension.marriage.add(1);
		userExtension.marriage.add(2);
		userExtension.marriage.add(3);
		//设置出生年月     需要配置
		int date = getRandomNumber(userExtension.ageStart, userExtension.ageEnd);
		System.out.println("===========" + date);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017-date);
		cal.set(Calendar.MONTH, getRandomNumber(1,11));
		cal.set(Calendar.DAY_OF_MONTH, getRandomNumber(1,28));
		Date birthday = cal.getTime();
		System.out.println("===========" + Tools.getDate(birthday));
		String str = Tools.getConstellation(birthday);
		System.out.println("===========" + str);
	}
	
}
