package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.List;

public class RobotUserExtension {
	
	//职位
	public String job;
	
	//收入
	public int income;
	
	//生成人数
	public int count;
	
	//随机年龄的起始点
	public int ageStart;
	
	//随机年龄的结束点
	public int ageEnd;
	
	//随机体重的起始点
	public int weightStart;
		
	//随机体重的结束点
	public int weightEnd;
		
	//随机身高的起始点
	public int statureStart;
		
	//随机身高的结束点
	public int statureEnd;
	
	//感情状态   可能有多个需要随机取一个
	public List marriage = new ArrayList();

}
