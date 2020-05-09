/**
 * 
 */
package com.tigerjoys.shark.miai.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.enums.NeteaseErrorEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;

/** 
 * ClassName: GenericTokenTest <br/> 
 * date: 2017年5月16日 下午3:27:27 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class GenericTokenTest {
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Test
	public void batchGenricToken() throws Exception{
		List<UserEntity> list = userContract.load(PageModel.getPageModel());
		for(UserEntity user:list){
			if(!user.getPhoto().startsWith("/upload")) {
				continue;
			}
			Map<String,String> params = new HashMap<>();
			params.put("accid", String.valueOf(user.getId()));
			params.put("icon", Const.getCdn(user.getPhoto()));
			JSONObject data = neteaseAgent.updateUser(params);
			int code = data.getIntValue("code");
			if (NeteaseErrorEnum.success.getCode() == code) {
			} else {
				System.err.println(data);
			}
		}
	}
}
