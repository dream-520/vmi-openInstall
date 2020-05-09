package com.tigerjoys.shark.miai;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.shark.miai.Bootstrap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Bootstrap.class) 
public class BaseSpringBootTests {
	 @Before
	    public void init() {
	        System.out.println("开始测试-----------------");
	    }
	 
	    @After
	    public void after() {
	        System.out.println("测试结束-----------------");
	    }


}
