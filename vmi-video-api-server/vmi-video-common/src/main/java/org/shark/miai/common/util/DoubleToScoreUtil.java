package org.shark.miai.common.util;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class DoubleToScoreUtil {

	public static Score doubleToScore(double m){
		if(m==0){
			return null;
		}
		String[] array=new String[2];
		array=(""+m).split("\\.");
		int a = Integer.parseInt(array[0]);//获取整数部分
		int b = Integer.parseInt(array[1]);//获取小数部分
		int length = array[1].length();
		int fenZi = (int) (a * Math.pow(10, length) + b);
		int fenMu = (int) Math.pow(10, length);
		int MaxYueShu = gcd(fenZi, fenMu);
		fenZi=fenZi/MaxYueShu;
		fenMu=fenMu/MaxYueShu;
		Double unitDouble=fenMu/(fenZi*1.0);
		DecimalFormat df=new DecimalFormat("##0.000");
		 String num=df.format(unitDouble);
		 array=num.split("\\.");
		 length = array[1].length();
		int zhengShu = Integer.parseInt(array[0]);//获取整数部分
		int xiaoshuInt = Integer.parseInt(array[1]);//获取小数部分
		double xiaoshu=xiaoshuInt/Math.pow(10, length);
		if(xiaoshu>=0.1){
			zhengShu=zhengShu+1;
		}
		System.out.println(fenZi+"/"+fenMu+" "+zhengShu+" "+xiaoshu);
		return new Score(fenZi,fenMu,zhengShu,xiaoshu);
	}
	
	
	
    private static int gcd(int a, int b) {
        BigInteger b1 = new BigInteger(String.valueOf(a));
        BigInteger b2 = new BigInteger(String.valueOf(b));
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }
	
  public static void main(String[] args) {
	  DoubleToScoreUtil.doubleToScore(0);
}
}



