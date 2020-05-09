package org.shark.miai.common.util;

import java.util.Random;

public class RedPackageUtil {
	/**
	 * 生成数组顺序打乱
	 * @param nums
	 */
	public static int[] shuffle(int number){
		int []nums=new int[number];
		for(int i=0;i<nums.length;i++){
			nums[i]=i+100001;
		}
		  Random rnd = new Random();
		    for (int i = nums.length - 1; i > 0; i--) {
		        int j = rnd.nextInt(i + 1);
		        //swap index i, j
		        int t = nums[i];
		        nums[i] = nums[j];
		        nums[j] = t;
		    }
		 return nums;
	}
	
	/**
	 * 生成数组顺序打乱
	 * @param nums
	 */
	public static int[] shuffle(int start,int end){
		int []nums=new int[end-start+1];
		for(int i=0;i<nums.length;i++){
			nums[i]=i+start;
		}
		  Random rnd = new Random();
		    for (int i = nums.length - 1; i > 0; i--) {
		        int j = rnd.nextInt(i + 1);
		        //swap index i, j
		        int t = nums[i];
		        nums[i] = nums[j];
		        nums[j] = t;
		    }
		 return nums;
	}
	
	public static String screeningArray(int []nums,int max){
		    String res="";
		    for (int i =0; i <nums.length ; i++) {
		       if(nums[i]<=max){
		    	    res=res+(i+1)+",";
		       }
		    }
		    return res;
	}
	
}
