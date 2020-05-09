package com.tigerjoys.shark.miai.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.tigerjoys.nbs.common.utils.Tools;

public class ShowDateStringUtil {
	
	/**
	 * 偷红包列表展示时间
	 * @param date - 时间
	 * @return String
	 */
	public static String getShowTime(Date d2){
		
		String create_time_s = "";
		Date d1 = new Date();
		try
		{
		    long diff = d1.getTime() - d2.getTime();
		    long ss = diff/1000;
		    long minutes = diff / (1000 * 60);
		    long hour = diff / (1000 * 60 * 60);
		    long day = diff / (1000 * 60 * 60 * 24);
	   	  /*
	   	   * 如果一分钟之内显示刚刚
		   * 如果是一个小时内，显示“XX分钟前”
		   * 如果是大于一个小时在今天之内，显示“XX小时XX分钟前
		   * 如果是小于今天，显示“XX天XX小时前” 
		   */
		    if(ss<60){
		    	create_time_s = "刚刚";
		    }
		    else if(minutes<=60){
		    	create_time_s = minutes + "分钟前" ;
		    }else if(hour<=24){
		    	create_time_s = hour + "小时" + minutes%60 + "分钟前" ;
		    }else{
		    	create_time_s = day + "天" + hour%24 + "小时前" ;
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return create_time_s;
	}
	
	public static int getAgeForCompareDate(Date brithday){
		if(Tools.isNull(brithday)){
			System.err.print("userBO对象里面的brithday是nulll====");
			return 0;
			
		}
		LocalDate date = brithday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		Period periodToNextJavaRelease = Period.between(date,today); 
		return periodToNextJavaRelease.getYears();
	}
	
	public static void main(String[] args) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d2 = dateFormat.parse("2017-04-27 14:41:20");
		System.out.println(ShowDateStringUtil.getShowTime(d2));
		System.out.println(Tools.getCurrnetMonday());
	}
}

