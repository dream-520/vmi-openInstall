package com.tigerjoys.shark.miai.agent.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.shark.miai.common.enums.AppNameEnum;
import org.shark.miai.common.enums.InviteCodeEnum;
import org.shark.miai.common.enums.ProxyInletEnum;
import org.shark.miai.common.enums.ProxyTransStatusEnum;
import org.shark.miai.common.enums.UserInviteProxyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.ICouponAgent;
import com.tigerjoys.shark.miai.agent.IProxyAgent;
import com.tigerjoys.shark.miai.agent.ITaskAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.ProxyInletDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserInviteCpsDto;
import com.tigerjoys.shark.miai.agent.dto.UserInviteOfParentDto;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IProxyContract;
import com.tigerjoys.shark.miai.inter.contract.IProxyTransContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteCpsIncomeLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteCpsStatisticsContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteMappingContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTalentVipStatisticsContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTaskContract;
import com.tigerjoys.shark.miai.inter.entity.ProxyEntity;
import com.tigerjoys.shark.miai.inter.entity.ProxyTransEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteCpsIncomeLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteCpsStatisticsEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTalentVipStatisticsEntity;

@Service
public class ProxyAgentImpl implements IProxyAgent {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IProxyContract proxyContract;

	@Autowired
	private IProxyTransContract proxyTransContract;
	
	@Autowired
	private IUserTaskContract userTaskContract;
	
	@Autowired
	private IUserPointAgent userPointAgent;

	@Autowired
	private ICouponAgent couponAgent;

	@Autowired
	private IUserInviteContract userInviteContract;

	@Autowired
	private IUserTalentVipStatisticsContract userTalentVipStatisticsContract;

	@Autowired
	private IUserInviteMappingContract userInviteMappingContract;
	
	@Autowired
	private IUserInviteCpsIncomeLogContract userInviteCpsIncomeLogContract;
	
	@Autowired
	private IUserInviteCpsStatisticsContract userInviteCpsStatisticsContract;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	
	@Autowired
	private ITaskAgent taskAgent;
	
	@Autowired
	private IUserAgent userAgent;

	@Override
	public ProxyEntity findById(long id) throws Exception {
		return proxyContract.findById(id);
	}

	@Override
	public ProxyEntity findByUserId(long userId) throws Exception {
		return proxyContract.findByProperty("userid", userId);
	}

	@Override
	public void insert(ProxyEntity t) throws Exception {
		proxyContract.insert(t);

	}

	@Override
	public void insertAll(List<ProxyEntity> list) throws Exception {
		proxyContract.insertAll(list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addIncome(long userid, long inComeid, long orderid, Integer amount, Map<String, Object> map,
			long percent) throws Exception {
		if (amount <= 0) {
			throw new IllegalAccessError();
		}
		UserInviteEntity userInvite = userInviteContract.findByProperty("userid", inComeid);
		if (Tools.isNull(userInvite)) {
			throw new IllegalAccessError();
		}
		if( userInvite.getProxy() == UserInviteProxyEnum.common.getCode()){
			return 0;
		}
		ProxyTransEntity entity = new ProxyTransEntity();
		entity.setUserid(userid);
		entity.setIncomeid(inComeid);
		entity.setOrderid(orderid);
		entity.setTrade_amount(amount);
		entity.setMappingid(userInvite.getMapping());
		Integer times = (Integer) map.get("times");
		Integer typeid = (Integer) map.get("typeid");
		if (times != null) {
			entity.setTimes(times);
		} else {
			times = 1;
			entity.setTimes(times);
		}
		if (typeid != null) {
			entity.setTypeid(typeid);
		} else {
			entity.setTypeid(0);
		}
		Integer dividedAmount = new BigDecimal(amount).multiply(new BigDecimal(percent)).divide(new BigDecimal(100))
				.intValue();
		entity.setDivided_amount(dividedAmount);
		entity.setStatus(ProxyTransStatusEnum.income.getCode());//
		entity.setCreate_time(new Date());
		proxyTransContract.insert(entity);

		userInviteMappingContract.addIncome(userInvite.getMapping(), entity.getDivided_amount());

		UserTalentVipStatisticsEntity statistics = userTalentVipStatisticsContract.findByProperty("userid", userid);
		if (Tools.isNotNull(statistics)) {
			userTalentVipStatisticsContract.addTradeAmount(userid, dividedAmount);
		} else {
			statistics = new UserTalentVipStatisticsEntity();
			statistics.setUserid(userid);
			statistics.setTrade_num(1);
			statistics.setTrade_amount(dividedAmount);
			statistics.setCreate_time(new Date());
			userTalentVipStatisticsContract.insert(statistics);
		}
		return proxyContract.addIncome(userid, amount, times, dividedAmount);
	}

	@Override
	public int delete(long id) throws Exception {
		return proxyContract.deleteProxy(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult addInvitation(long userid, String inviteCode) throws Exception {
		Long inviteId = 0l;
		Long mappingId = 0l;
		String packageName = null;
		try {
			UserInviteMappingEntity mapping = userInviteMappingContract.findByProperty("invitecode",
					Long.parseLong(inviteCode));
			if (Tools.isNull(mapping)) {
				return ActionResult.fail(InviteCodeEnum.noExist.getCode(), InviteCodeEnum.noExist.getDesc());
			}
			inviteId = mapping.getUserid();
			mappingId = mapping.getId();
			packageName = mapping.getPackage_name();
		} catch (Exception e) {
			return ActionResult.fail(InviteCodeEnum.illegal.getCode(), InviteCodeEnum.illegal.getDesc());
		}
		if(userid == inviteId){
			return ActionResult.fail(InviteCodeEnum.self_invite_self.getCode(), InviteCodeEnum.self_invite_self.getDesc());
		}
		

		UserInviteEntity inviteUser = userInviteContract.findByProperty("userid", userid);
		if (Tools.isNotNull(inviteUser)) {
			return ActionResult.fail(InviteCodeEnum.Used.getCode(), InviteCodeEnum.Used.getDesc());
		}
		UserInviteEntity entity = new UserInviteEntity();
		entity.setUserid(userid);
		entity.setInvitation(inviteId);
		entity.setMapping(mappingId);
		entity.setCreate_time(new Date());
		entity.setProxy(UserInviteProxyEnum.common.getCode());
		entity.setPackage_name(packageName);
		userInviteContract.insert(entity);
		/*
		UserInviteCpsStatisticsEntity cpsStatis = userInviteCpsStatisticsContract.findByProperty("userid", inviteId);
		updateCpsInviteNum(Tools.isNull(cpsStatis)?null:cpsStatis.getId(), inviteId, level, packageName);
		*/
		try {
			updateCpsStatisticsNum(getUserOfParents(userid));
		} catch (Exception e) {
			LOGGER.info("添加失败userid:"+userid,e);
		}
		
		userInviteMappingContract.addinviteNum(mappingId);
		//送积分
		userPointAgent.changePointAccount(inviteId, UserPointAccountLogTypeEnum.invite_friends.getCode(), 1, String.valueOf(entity.getId()), UserPointAccountLogTypeEnum.invite_friends.getDesc());
		
		return ActionResult.success();
	}
	


	@Override
	public int addProxyTalentVip(long userid) throws Exception {
		UserInviteEntity userInvite = userInviteContract.findByProperty("userid", userid);
		if (userInvite != null && userInvite.getProxy() != UserInviteProxyEnum.common.getCode()) {
			proxyContract.addProxyTalentVip(userInvite.getInvitation());
			userInviteMappingContract.addTalentVipNum(userInvite.getMapping());
			return 1;
		}
		return 0;
	}

	@Override
	public ProxyInletDto queryInletByUserId(long userId) throws Exception {
		UserInviteEntity userInvite = userInviteContract.findByProperty("userid", userId);
		ProxyInletDto inlet = new ProxyInletDto();
		inlet.setUserid(userId);
		if (Tools.isNull(userInvite) || userInvite.getProxy() == UserInviteProxyEnum.common.getCode()) {
			inlet.setProxy(false);
			inlet.setProxyInlet(ProxyInletEnum.offline.getCode());
			return inlet;
		}
		ProxyEntity proxy = proxyContract.findById(userInvite.getInvitation());
		if (Tools.isNull(proxy)) {
			inlet.setProxy(false);
			inlet.setProxyInlet(ProxyInletEnum.offline.getCode());
			return inlet;
		} else {
			inlet.setProxy(true);
			inlet.setProxyInlet(proxy.getInlet());
		}
		return inlet;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void addCpsIncome(long userId,long amount) throws Exception{
		UserInviteOfParentDto parent = getUserOfParents(userId);
		if(!parent.isParentFlag()){
			return ;
		}
		if(!AppNameEnum.andriod_com_tjhj_miliao.getPackageName().equalsIgnoreCase(parent.getPackageName())){
			return ;
		}
		UserInviteCpsIncomeLogEntity log = new UserInviteCpsIncomeLogEntity();
		log.setUserid(userId);
		log.setPackage_name(parent.getPackageName());
		log.setParent1(parent.getParent1());
		log.setParent2(parent.getParent2());
		log.setParent3(parent.getParent3());
		if(parent.getParent1()>0){
			log.setParent1_income(getAmount(1,amount));
		}
		if(parent.getParent2()>0){
			log.setParent2_income(getAmount(2,amount));
		}
		if(parent.getParent3()>0){
			log.setParent3_income(getAmount(3,amount));
		}
		log.setAmount(amount);
		log.setCreate_time(new Date());
		userInviteCpsIncomeLogContract.insert(log);
		updateCpsStatisticsIncome(log);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int updateCpsStatisticsIncome(UserInviteCpsIncomeLogEntity log) throws Exception{
		List<Long> idList = new ArrayList<Long>();
		if(log.getParent1()>0){
			idList.add(log.getParent1());
		}
		if(log.getParent2()>0){
			idList.add(log.getParent2());
		}
		if(log.getParent3()>0){
			idList.add(log.getParent3());
		}
		if(Tools.isNull(idList)){
			return 0;
		}
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.in("userid", idList));
		List<UserInviteCpsStatisticsEntity> cpsStatisList = userInviteCpsStatisticsContract.load(pageModel);
		Map<Long,Long> userMap = new HashMap<>();
		if(Tools.isNotNull(cpsStatisList)){
			cpsStatisList.forEach(v->{
				userMap.put(v.getUserid(), v.getId());
			});
		}
		Long parentid = null;
		if(log.getParent1()>0){
			parentid = userMap.get(log.getParent1());
			updateCpsIncome(parentid,log.getParent1(),1,log.getParent1_income(),log.getPackage_name(),log.getId());
		}
		if(log.getParent2()>0){
			parentid = userMap.get(log.getParent2());
			updateCpsIncome(parentid,log.getParent2(),2,log.getParent2_income(),log.getPackage_name(),log.getId());
		}
		if(log.getParent3()>0){
			parentid = userMap.get(log.getParent3());
			updateCpsIncome(parentid,log.getParent3(),3,log.getParent3_income(),log.getPackage_name(),log.getId());
		}
		return 0;
	}
	
	
	
	@Transactional(rollbackFor = Exception.class)
	public int updateCpsStatisticsNum(UserInviteOfParentDto parent) throws Exception{
		List<Long> idList = new ArrayList<Long>();
		if(parent.getParent1()>0){
			idList.add(parent.getParent1());
		}
		if(parent.getParent2()>0){
			idList.add(parent.getParent2());
		}
		if(parent.getParent3()>0){
			idList.add(parent.getParent3());
		}
		if(Tools.isNull(idList)){
			return 0;
		}
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.in("userid", idList));
		List<UserInviteCpsStatisticsEntity> cpsStatisList = userInviteCpsStatisticsContract.load(pageModel);
		Map<Long,Long> userMap = new HashMap<>();
		if(Tools.isNotNull(cpsStatisList)){
			cpsStatisList.forEach(v->{
				userMap.put(v.getUserid(), v.getId());
			});
		}
		Long parentid = null;
		if(parent.getParent1()>0){
			parentid = userMap.get(parent.getParent1());
			updateCpsInviteNum(parentid,parent.getParent1(),1,parent.getPackageName());
		}
		if(parent.getParent2()>0){
			parentid = userMap.get(parent.getParent2());
			updateCpsInviteNum(parentid,parent.getParent2(),2,parent.getPackageName());
		}
		if(parent.getParent3()>0){
			parentid = userMap.get(parent.getParent3());
			updateCpsInviteNum(parentid,parent.getParent3(),3,parent.getPackageName());
		}
		return 0;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public int updateCpsIncome(Long id,long userId,int level,long amount,String packageName,long logId) throws Exception{
		if(Tools.isNull(id)){
			statisticsInit(userId,level,0,amount,packageName);
		}else{
			String updateSql = "";
			if(level == 1){
				updateSql = "update_time=now(),a_income=a_income+"+amount;
			}else if(level == 2){
				updateSql = "update_time=now(),b_income=b_income+"+amount;
			}else if(level == 3){
				updateSql = "update_time=now(),c_income=c_income+"+amount;
			}
			userInviteCpsStatisticsContract.updateByStatement(updateSql, "id="+id);
		}
		userIncomeAgent.changeIncomeAccount(userId, amount, 1, UserIncomeAccountLogTypeEnum.user_inivite_cps_back_Income, "level"+level+"_"+String.valueOf(logId),
				UserIncomeAccountLogTypeEnum.user_inivite_cps_back_Income.getDesc());
		return 1;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int updateCpsInviteNum(Long id,long userId,int level,String packageName) throws Exception{
		if(Tools.isNull(id)){
			statisticsInit(userId,level,1,0,packageName);
		}else{
			String updateSql = "";
			if(level == 1){
				updateSql = "update_time=now(),a_num=a_num+1";
			}else if(level == 2){
				updateSql = "update_time=now(),b_num=b_num+1";
			}else if(level == 3){
				updateSql = "update_time=now(),c_num=c_num+1";
			}
			userInviteCpsStatisticsContract.updateByStatement(updateSql, "id="+id);
		}
		return 1;
	}
	
	public long getAmount(int level,long amount){
		if(amount == 0){
			return 0L;
		}
		long newAmount = 0L;
		if(level == 1){
			newAmount = Math.round(amount*0.25);
		}else if(level == 2){
			newAmount = Math.round(amount*0.05);
		}else if(level == 3){
			newAmount = Math.round(amount*0.03);
		}
		return newAmount;
	}
	
	
	private void statisticsInit(long userId,int level,int userNum,long amount,String packageName) throws Exception {
		Date current = new Date(); 
		UserInviteCpsStatisticsEntity statistics = new UserInviteCpsStatisticsEntity();
		statistics.setUserid(userId);
		statistics.setPackage_name(packageName);
		if(level ==1){
			statistics.setA_num(userNum);
			statistics.setA_income(amount);
		}else if(level ==2){
			statistics.setB_num(userNum);
			statistics.setB_income(amount);
		}else if(level ==3){
			statistics.setB_num(userNum);
			statistics.setB_income(amount);
		}
		statistics.setCreate_time(current);
		statistics.setUpdate_time(current);
		userInviteCpsStatisticsContract.insert(statistics);
	}
	
	public UserInviteOfParentDto getUserOfParents(long userId) throws Exception{
		UserInviteOfParentDto dto = new UserInviteOfParentDto(); 
		dto.setUserid(userId);
		dto.setParentFlag(false);
		
		long parentId = getUserParent(userId);
		if(parentId>0){
			dto.setParentFlag(true);
			dto.setPackageName(getUserParentPackageName(parentId));
			dto.setParent1(parentId);
			parentId = getUserParent(parentId);
		}
		if(parentId > 0){
			dto.setParent2(parentId);
			parentId = getUserParent(parentId);
		}
		if(parentId > 0){
			dto.setParent3(parentId);
		}
		return dto;
	}
	
	
	public long getUserParent(long userId) throws Exception{
		UserInviteEntity userInvite = userInviteContract.findByProperty("userid", userId);
		if(Tools.isNotNull(userInvite)){
			return userInvite.getInvitation();
		}else{
			return 0L;
		}
	}

	public String getUserParentPackageName(long userId) throws Exception{
		UserInviteMappingEntity entity = userInviteMappingContract.getFirstUserInviteMapping(userId);
		if(Tools.isNotNull(entity)){
			return entity.getPackage_name();
		}else{
			return null;
		}
		
	}
	
	public List<UserInviteCpsDto> getUserInviteCpsIncomeList(long userId,int level) throws Exception{
		List<UserInviteCpsDto> dtoList = new ArrayList<>();
		PageModel pageModel = PageModel.getPageModel(0,200);
		pageModel.addQuery(Restrictions.eq("parent"+level,userId));
		pageModel.desc("id");
		List<UserInviteCpsIncomeLogEntity> list = userInviteCpsIncomeLogContract.load(pageModel);
		if(Tools.isNotNull(list)){
			List<Long> idList = list.stream().map(UserInviteCpsIncomeLogEntity::getUserid).collect(Collectors.toList());
			Map<Long, UserBO> userMap = userAgent.findById(idList);
			for(UserInviteCpsIncomeLogEntity re:list){
				UserBO userbo = userMap.get(re.getUserid());
				UserInviteCpsDto dto = new UserInviteCpsDto();
				dto.setId(re.getId());
				dto.setNikeName(Tools.isNotNull(userbo)?userbo.getNickname():"");
				dto.setIcon(Tools.isNotNull(userbo) && Tools.isNotNull(userbo.getPhoto()) ? Const.getCdn(userbo.getPhoto()):Const.getDefaultUserFace());
				long amount = 0;
				if(level == 1){
					amount = re.getParent1_income();
				}else if(level == 2){
					amount = re.getParent2_income();
				}else if(level == 3){
					amount = re.getParent3_income();
				}
				dto.setIncome(Tools.formatDouble2Percent(amount)+"元");
				dto.setCreateDate(Tools.getFormatDate(re.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	

}
