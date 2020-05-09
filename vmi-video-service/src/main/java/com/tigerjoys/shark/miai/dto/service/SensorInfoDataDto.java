package com.tigerjoys.shark.miai.dto.service;

/**
 * 传感器数据
 * Created by yangyi on 2019/7/12.
 */

public class SensorInfoDataDto {
  private int type;
  private String name;
  private String vendor; //类似厂商
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getVendor() {
	return vendor;
}
public void setVendor(String vendor) {
	this.vendor = vendor;
}
	  
	  

}
