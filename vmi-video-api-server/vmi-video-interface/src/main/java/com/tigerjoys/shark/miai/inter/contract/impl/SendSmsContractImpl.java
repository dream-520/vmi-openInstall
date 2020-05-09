package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.ISendSmsContract;
import com.tigerjoys.shark.miai.inter.entity.SendSmsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.SendSmsMapper;

/**
 * 数据库中  短信发送记录表[t_send_sms]表 接口实现类
 * @author lipeng
 * @Date 2017-08-18 10:29:55
 *
 */
@Repository
public class SendSmsContractImpl extends AbstractBaseContract<SendSmsEntity , SendSmsMapper> implements ISendSmsContract {
	
}
