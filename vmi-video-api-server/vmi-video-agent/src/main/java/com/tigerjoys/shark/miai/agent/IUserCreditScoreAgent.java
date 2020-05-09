package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Map;

import com.tigerjoys.shark.miai.agent.dto.CreditRecordBO;
import com.tigerjoys.shark.miai.agent.dto.CreditScoreConfigureBO;

/**
 * 用户信用分服务代理接口
 * @author liuman
 *
 */
public interface IUserCreditScoreAgent {
	
	//功能如下：
	//后台信用分可购买列表、信用分扣除、信用分增加、信用记录列表、用户信用分账户余额
	
	/**
	 * 根据ID获得该用户的后台购买信用分列表
	 * @param userid - 用户ID
	 * @return List<CreditScoreConfigureBO>
	 * @throws Exception
	 */
	public List<CreditScoreConfigureBO> findPurchaseCreditScoreList(long userid) throws Exception;
	
	/**
	 * 根据ID获得该用户的信用使用记录列表
	 * @param userid - 用户ID
	 * @return List<CreditRecordBO>
	 * @throws Exception
	 */
	public List<CreditRecordBO> findCreditScoreRecordList(long userid) throws Exception;
	
	/**
	 * 根据ID获得该用户的信用使用记录列表
	 * @param userid - 用户ID
	 * @param recordId - 上一次查询分页数据的最后一条id
	 * @param pagesize - 分页数
	 * @return List<CreditRecordBO>
	 * @throws Exception
	 */
	public List<CreditRecordBO> findCreditScoreRecordListByPaging(long userid,long recordId , int pagesize) throws Exception;
	
	/**
	 * 
	 * @param userId: 用户id
	 * @param isAdd: ture-增加信用分;false-扣除信用分
	 * @param changeScore: 变化的信用分数 常量可以维护在CreditConst类中
	 * @param transactionFlow 一条交易流水标识,用于存储日志表
	 * @return 扣除成功则返回用户信用分账户余额和状态;失败则返回失败状态和文案
	 * @param type 信用账户变更的类型,要写一个枚举类
	 * @param memo 信用账户变更的描述,要写一个枚举类 UserCreditRecordEnum
	 * @throws Exception
	 */
	public Map<String,Object> changeCreditScore(Long userId,boolean isAdd, long changeScore,String transactionFlow,int type, String memo) throws Exception;
	
	/**
	 * 查封/解封用户信用账户
	 * @param userid - 用户ID
	 * @param status - 状态，1正常,0查封
	 * @param memo - 备注
	 * @throws Exception
	 */
	public void changeUserCreditStatus(long userid , int status , String memo) throws Exception;
	
	/**
	 * 拿到用户信用账户的余额
	 * @param userid - 用户ID
	 * @throws Exception
	 */
	public long getUserCreditBalance(long userid) throws Exception;
	
	/**
	 * 初始化用户的信用分账户信息,用户注册时使用
	 * @param userid
	 * @param type:普通用户 or 服务者 ； 使用枚举类-UserTypeEnum
	 * @throws Exception
	 */
	public void initUserCreaditAccount(long userid,int type) throws Exception;
}
