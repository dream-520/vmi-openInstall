package com.tigerjoys.shark.miai;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tigerjoys.nbs.common.beans.Produce;

/**
 * 注册相关的接口测试
 * @author chengang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class SecurityTest {
	
	private final static String NO_LOGIN_HEADER = "QHrejHRTQfaOSg6Z4tRYLOyOuR0ejLU8YdV1psYKKxCBtJCO/Ug9OSHcprzx na0C+zoPj0PxKfQIahxICAieJweeyMwpO8dcbeq8WBUeufCdKZ/4w1/3dqpE sqXLRuAgpUY1AihrZdRl/IFGDdxpSFfZTuX5O7jq5OXDtQ2OU/qYsCUwZynt CnQxW98da7sv1g4RAyiWXP90dLdE2psQ7S4T/id+3B+3k74c+hBWfS3OdexN T7wu62UO5P70GDAahVhtaVIr+v9SCRcUsXyj12aDcv9I5hRyF1SPlX1kMKAc jXiSqz6MYeiVtMsaSAxrzfZAu5nxD3Yc68a8+7EL1g==";
	
	private final static String LOGIN_HEADER = "QHrejHRTQfaOSg6Z4tRYLOyOuR0ejLU8YdV1psYKKxCBtJCO/Ug9OSHcprzx na0C+zoPj0PxKfQIahxICAieJweeyMwpO8dcbeq8WBUeufCdKZ/4w1/3dqpE sqXLRuAgpUY1AihrZdRl/IFGDdxpSFfZTuX5O7jq5OXDtQ2OU/qYsCUwZynt CnQxW98da7sv1g4RAyiWXP90dLdE2psQ7S4T/id+3B+3k74c+hBWfS3OdexN T7wu62UO5P70GDAayhix0Mg36UI9F17Wdu0korq+sSSElQ4keLwfulNjAEuU oPNuY4hokRop31yXWf3gL0BY3tV5VufjCIfaRY1dSg==";
	
	private MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext wac;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		//此处创建一个假的mock对象替换到spring中的真实对象，防止操作数据库
	}
	
	@Test
	public void testUserinfoController() throws Exception {
		MockHttpServletRequestBuilder builder = post("/api/security/userinfo");
		builder = builder.contentType(Produce.TEXT_ENCODE);
		builder = addNoLoginHeader(builder);
		
		//模拟
		mockMvc.perform(builder)
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	/**
	 * 添加未登录
	 * @param builder - MockHttpServletRequestBuilder
	 * @return MockHttpServletRequestBuilder
	 */
	private MockHttpServletRequestBuilder addNoLoginHeader(MockHttpServletRequestBuilder builder){
		return builder.header("header-encrypt-code", NO_LOGIN_HEADER);
	}
	
	/**
	 * 添加已登录
	 * @param builder - MockHttpServletRequestBuilder
	 * @return MockHttpServletRequestBuilder
	 */
	private MockHttpServletRequestBuilder addLoginHeader(MockHttpServletRequestBuilder builder){
		return builder.header("header-encrypt-code", LOGIN_HEADER);
	}

}
