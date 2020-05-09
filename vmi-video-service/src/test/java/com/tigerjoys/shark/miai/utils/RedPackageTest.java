package com.tigerjoys.shark.miai.utils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.shark.miai.common.util.RedPackageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedPackageTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void testWeight(){
		for(int i=0;i<30;i++){
			int array[]=RedPackageUtil.shuffle(6);
			System.out.println(Arrays.toString(array));
			String res=RedPackageUtil.screeningArray(array, 1);
			System.out.println(res);
		}
	}
	
	@Test
	public void testRandom(){
		for(int i=0;i<300;i++){
			double u = ThreadLocalRandom.current().nextInt(20);
			System.out.println(u);
		}
		
	  System.out.println(System.currentTimeMillis());
	}
	
	
	@Test
	public void testDouble() throws Exception {
		Double aa=98.7845;
		long ff=aa.longValue();
		System.out.println(ff);
		double ee=aa-aa.longValue();
		System.out.println(ee);
		
		double a=1235;
		double b=111;
		Double c=1000/(101*1.0);
		DecimalFormat df=new DecimalFormat("##0.00");
		 String num=df.format(c);
		 Double bb=Double.parseDouble(num);
		 Double xiaoshu=bb-bb.longValue();
		System.out.println(xiaoshu);
		Double ss=34/Math.pow(10, 3);
		System.out.println(ss);;
		System.out.println(0.455625+0.7845);
		
		System.out.println(4875/10000.0);
		
		Random rand=new Random();
		System.out.println(rand.nextInt(1)+1);
	}
	
	
	
}
