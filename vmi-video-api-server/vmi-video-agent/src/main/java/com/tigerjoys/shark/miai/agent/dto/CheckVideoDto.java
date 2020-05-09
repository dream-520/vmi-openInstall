package com.tigerjoys.shark.miai.agent.dto;

public class CheckVideoDto {

	//色情监控开关
	private int pornographicOn;
	//不确定值
	private double pornographicIndeterminacy;
	//确定值
	private double pornographicDetermine;
	//控制用户
	private int pornographicUser;
	//控住主播
	private int pornographicAnchor;
	//警告类型
	private int pornographicWarn;
	//警告次数
	private int pornographicWarnCount;
	//警告间隔
	private int pornographicWarnTime;
	//控制区域
	private String pornographicArea;
	
	//用户控制区域
	private String pornographicAreaUser;
	//用户警告类型
	private int pornographicWarnUser;
	
	//警告提示语
	private String pornographicWarnText;
	//切断违规方提示语
	private String pornographicWarnStopUText;
	//切断对方提示语
	private String pornographicWarnStopTText;
	
	private int lowOn;
	private double lowIndeterminacy;
	private double lowDetermine;
	private int lowUser;
	private int lowAnchor;
	private int lowWarn;
	private int lowWarnCount;
	private int lowWarnTime;
	private String lowArea;
	private String lowWarnText;
	private String lowWarnStopUText;
	private String lowWarnStopTText;
	
	private String lowAreaUser;
	private int lowWarnUser;
	
	public int getPornographicOn() {
		return pornographicOn;
	}
	
	public void setPornographicOn(int pornographicOn) {
		this.pornographicOn = pornographicOn;
	}
	
	public double getPornographicIndeterminacy() {
		return pornographicIndeterminacy;
	}
	
	public void setPornographicIndeterminacy(double pornographicIndeterminacy) {
		this.pornographicIndeterminacy = pornographicIndeterminacy;
	}
	
	public double getPornographicDetermine() {
		return pornographicDetermine;
	}
	
	public void setPornographicDetermine(double pornographicDetermine) {
		this.pornographicDetermine = pornographicDetermine;
	}
	
	public int getPornographicUser() {
		return pornographicUser;
	}
	
	public void setPornographicUser(int pornographicUser) {
		this.pornographicUser = pornographicUser;
	}
	
	public int getPornographicAnchor() {
		return pornographicAnchor;
	}
	
	public void setPornographicAnchor(int pornographicAnchor) {
		this.pornographicAnchor = pornographicAnchor;
	}
	
	public int getPornographicWarn() {
		return pornographicWarn;
	}
	
	public void setPornographicWarn(int pornographicWarn) {
		this.pornographicWarn = pornographicWarn;
	}
	
	public int getPornographicWarnCount() {
		return pornographicWarnCount;
	}
	
	public void setPornographicWarnCount(int pornographicWarnCount) {
		this.pornographicWarnCount = pornographicWarnCount;
	}
	
	public int getPornographicWarnTime() {
		return pornographicWarnTime;
	}
	
	public void setPornographicWarnTime(int pornographicWarnTime) {
		this.pornographicWarnTime = pornographicWarnTime;
	}
	
	public String getPornographicArea() {
		return pornographicArea;
	}
	
	public void setPornographicArea(String pornographicArea) {
		this.pornographicArea = pornographicArea;
	}
	
	public String getPornographicWarnText() {
		return pornographicWarnText;
	}
	
	public void setPornographicWarnText(String pornographicWarnText) {
		this.pornographicWarnText = pornographicWarnText;
	}
	
	public String getPornographicWarnStopUText() {
		return pornographicWarnStopUText;
	}
	
	public void setPornographicWarnStopUText(String pornographicWarnStopUText) {
		this.pornographicWarnStopUText = pornographicWarnStopUText;
	}
	
	public String getPornographicWarnStopTText() {
		return pornographicWarnStopTText;
	}
	
	public void setPornographicWarnStopTText(String pornographicWarnStopTText) {
		this.pornographicWarnStopTText = pornographicWarnStopTText;
	}
	
	public int getLowOn() {
		return lowOn;
	}
	
	public void setLowOn(int lowOn) {
		this.lowOn = lowOn;
	}
	
	public double getLowIndeterminacy() {
		return lowIndeterminacy;
	}
	
	public void setLowIndeterminacy(double lowIndeterminacy) {
		this.lowIndeterminacy = lowIndeterminacy;
	}
	
	public double getLowDetermine() {
		return lowDetermine;
	}
	
	public void setLowDetermine(double lowDetermine) {
		this.lowDetermine = lowDetermine;
	}
	
	public int getLowUser() {
		return lowUser;
	}
	
	public void setLowUser(int lowUser) {
		this.lowUser = lowUser;
	}
	
	public int getLowAnchor() {
		return lowAnchor;
	}
	
	public void setLowAnchor(int lowAnchor) {
		this.lowAnchor = lowAnchor;
	}
	
	public int getLowWarn() {
		return lowWarn;
	}
	
	public void setLowWarn(int lowWarn) {
		this.lowWarn = lowWarn;
	}
	
	public int getLowWarnCount() {
		return lowWarnCount;
	}
	
	public void setLowWarnCount(int lowWarnCount) {
		this.lowWarnCount = lowWarnCount;
	}
	
	public int getLowWarnTime() {
		return lowWarnTime;
	}
	
	public void setLowWarnTime(int lowWarnTime) {
		this.lowWarnTime = lowWarnTime;
	}
	
	public String getLowArea() {
		return lowArea;
	}
	
	public void setLowArea(String lowArea) {
		this.lowArea = lowArea;
	}
	
	public String getLowWarnText() {
		return lowWarnText;
	}
	
	public void setLowWarnText(String lowWarnText) {
		this.lowWarnText = lowWarnText;
	}
	
	public String getLowWarnStopUText() {
		return lowWarnStopUText;
	}
	
	public void setLowWarnStopUText(String lowWarnStopUText) {
		this.lowWarnStopUText = lowWarnStopUText;
	}
	
	public String getLowWarnStopTText() {
		return lowWarnStopTText;
	}
	
	public void setLowWarnStopTText(String lowWarnStopTText) {
		this.lowWarnStopTText = lowWarnStopTText;
	}

	public String getPornographicAreaUser() {
		return pornographicAreaUser;
	}

	public void setPornographicAreaUser(String pornographicAreaUser) {
		this.pornographicAreaUser = pornographicAreaUser;
	}

	public int getPornographicWarnUser() {
		return pornographicWarnUser;
	}

	public void setPornographicWarnUser(int pornographicWarnUser) {
		this.pornographicWarnUser = pornographicWarnUser;
	}

	public String getLowAreaUser() {
		return lowAreaUser;
	}

	public void setLowAreaUser(String lowAreaUser) {
		this.lowAreaUser = lowAreaUser;
	}

	public int getLowWarnUser() {
		return lowWarnUser;
	}

	public void setLowWarnUser(int lowWarnUser) {
		this.lowWarnUser = lowWarnUser;
	}
	
}
