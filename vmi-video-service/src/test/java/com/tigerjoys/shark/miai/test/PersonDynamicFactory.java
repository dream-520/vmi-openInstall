package com.tigerjoys.shark.miai.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tigerjoys.shark.miai.inter.entity.UserDynamicContentEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;

public class PersonDynamicFactory {

	public static List<UserPhotoResourceEntity> TopPhotoLE10(){
		List<UserPhotoResourceEntity> url = new ArrayList<>();
		//生成一个1到9的随机数字
		Random random = new Random();
		int size = random.nextInt(9) + 1;
		for(int i = 0; i < size; i++) {
			UserPhotoResourceEntity entity = new UserPhotoResourceEntity();
			entity.setPath("/upload/user/2017/06/27/"+i+".png");
			url.add(entity);
		}
		return url;
	}
	
	
	public static List<UserPhotoResourceEntity> TopPhotoEQ10(){
		List<UserPhotoResourceEntity> url = new ArrayList<>();
		//生成一个1到9的随机数字
//		Random random = new Random();
		int size = 10;
		for(int i = 0; i < size; i++) {
			UserPhotoResourceEntity entity = new UserPhotoResourceEntity();
			entity.setPath("/upload/user/2017/06/27/"+i+".png");
			url.add(entity);
		}
		return url;
	}
	
	public static List<UserPhotoResourceEntity> TopPhotoGE10() {
		List<UserPhotoResourceEntity> url = new ArrayList<>();
		//生成一个1到9的随机数字
		Random random = new Random();
		int size = random.nextInt(200) + 10;
		for(int i = 0; i < size; i++) {
			UserPhotoResourceEntity entity = new UserPhotoResourceEntity();
			entity.setPath("/upload/user/2017/06/27/"+i+".png");
			url.add(entity);
		}
		return url;
	}
	
	//生成的id范围值在0 - 3 之间   所有第二个函数在取id值的时候需要注意了
	public static List<UserDynamicEntity> TopDynamicConditionUserDynamicEntity1() {
		List<UserDynamicEntity> url = new ArrayList<>();
		//生成一个1到9的随机数字
		Random random = new Random();
		int size = random.nextInt(3) + 1;
		for(int i = 0; i < size; i++) {
			UserDynamicEntity entity = new UserDynamicEntity();
			entity.setId((long) i);
			if(i==0){
				entity.setType(1);
			} else {
				entity.setType(2);
			}
			url.add(entity);
		}
		return url;
	}
	
	public static UserDynamicContentEntity TopDynamicCondition0() {
		UserDynamicContentEntity entity = new UserDynamicContentEntity();
		entity.setPath("/upload/user/2017/06/27/0.png");
		return entity;	
	}
	
	public static UserDynamicContentEntity TopDynamicCondition1() {
		UserDynamicContentEntity entity = new UserDynamicContentEntity();
		entity.setPath("/upload/user/2017/06/27/1.png,/upload/user/2017/06/27/live.mp4");
		return entity;	
	}
	
	public static UserDynamicContentEntity TopDynamicCondition2() {
		UserDynamicContentEntity entity = new UserDynamicContentEntity();
		entity.setPath("/upload/user/2017/06/27/2.png,/upload/user/2017/06/27/love.mp4");
		return entity;	
	}
	
	public static UserDynamicContentEntity TopDynamicCondition3() {
		UserDynamicContentEntity entity = new UserDynamicContentEntity();
		entity.setPath("/upload/user/2017/06/27/3.png,/upload/user/2017/06/27/love.mp4");
		return entity;	
	}
	
	//生成的id范围值在4 - 7 之间   所有第二个函数在取id值的时候需要注意了
	public static List<UserDynamicEntity> TopDynamicConditionUserDynamicEntity2() {
		List<UserDynamicEntity> url = new ArrayList<>();
		//生成一个1到9的随机数字
		Random random = new Random();
		int size = random.nextInt(4) + 4;
		for(int i = 4; i < size; i++) {
			UserDynamicEntity entity = new UserDynamicEntity();
			entity.setId((long) i);
			entity.setType(random.nextInt(1)+1);
			if(i==4){
				entity.setType(1);
			}
			url.add(entity);
		}
		return url;
	}
	
	public static UserDynamicContentEntity TopDynamicCondition4() {
		UserDynamicContentEntity entity = new UserDynamicContentEntity();
		entity.setPath("/upload/user/2017/06/27/2.png,/upload/user/2017/06/27/3.png,/upload/user/2017/06/27/4.png,/upload/user/2017/06/27/5.png,/upload/user/2017/06/27/6.png,");
		return entity;	
	}
	
}
