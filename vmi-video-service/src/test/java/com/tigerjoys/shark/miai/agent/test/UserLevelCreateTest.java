package com.tigerjoys.shark.miai.agent.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.inter.contract.IUserLevelContract;
import com.tigerjoys.shark.miai.inter.entity.UserLevelEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class UserLevelCreateTest extends BaseTestConfig {

	@Autowired
	private IUserLevelContract userLevelContract;
	
	@Test
	public void createUserLevelTest() throws Exception {
		int money = 0;
		for(int i=2; i<=100;i++) {
			if(i<21) {
				//1-20 100
				money = money + 100;
			} else if(i<51){
				//21-50 500
				money = money + 500;
			} else if(i<71) {
				//51-70 1000
				money = money + 1000;
			} else if(i<81) {
				//71-80 2000
				money = money + 2000;
			} else if(i<91) {
				//81-90 3000
				money = money + 3000;
			} else {
				//91-100 5000
				money = money + 5000;
			}
			UserLevelEntity t = new UserLevelEntity();
			t.setCreate_time(new Date());
			t.setUpdate_time(new Date());
			t.setLevel(i);
			t.setDiamond(money);
			userLevelContract.insert(t);
		}
	}
	
}
