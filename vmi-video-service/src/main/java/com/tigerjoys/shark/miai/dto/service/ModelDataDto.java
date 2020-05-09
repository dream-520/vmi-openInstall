package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;
import java.util.List;

/**
 * 收集数据
 * Created by yangjunming on 2019/7/12.
 */
public class ModelDataDto implements Serializable {
	  
	    //设备参数
	    private String buildDevice;

	    //手机厂商
	    private String buildProduct;

	    //版本
	    private String buildModel;

	    //显示屏参数
	    private String buildDisplay;

	    //android系统定制商
	    private String buildBrand;

	    //主板名称
	    private String buildBoard;

	    //硬件名
	    private String buildFingerprint;

	    //硬件厂商
	    private String buildManufacturer;

	    //硬件序列号
	    private String buildSerial;

	    private String mac;

	    /**
	     *  5.0.1 // release版本
	     */
	    private String buildVersionRelease;

	    /**
	     *  LRX22F // 设备列表编号
	     */
	    private String buildId;

	    /**
	     *  eng.huaweiromtest.20180919.182709 // 系统源代码控制值
	     */
	    private String buildVersionIncremental;

	    /**
	     *   设备版本类型 主要为”user” 或”eng”
	     */
	    private String buildType;
	   /**
	     *  设备标签。如release-keys 或测试的 test-keys
	     */
	    private String buildTags;

	    /**
	     * 系统版本值
	     */
	    private String buildVersionSdk;
	    /**
	     * 设备硬件名称,一般和基板名称一样
	     */
	    private String buildHardware;
	    /**
	     * 设备主机地址
	     */
	    private String buildHost;
	    /**
	     * 设备用户名
	     */
	    private String buildUser;
	    /**
	     * 固件开发版本代号
	     */
	    private String buildVersionCodename;
	    /**
	     * 主板引导程序
	     */
	    private String buildBootloader;
	    /**
	     * Build时间
	     */
	    private long buildTime;
	    /**
	     * 系统的API级别
	     */
	    private String buildVersionSdkInt;
	    /**
	     * cpu指令集1
	     */
	    private String buildCpu_abi;
	    /**
	     * cpu指令集2
	     */
	    private String buildCpu_abi2;
	    /**
	     * 无线电基带版本
	     */
	    private String buildRadioVersion;
	/**
	     * 无线电
	     */
	    private String buildRadio;

	    private String androidId;

	    /**
	     * sim
	     */
	    //国际移动设备标识,是手机的识别id
	    private String simImei1;
	    private String simImei2;
	    private String simMeid;

	    //国际移动用户识别码
	    private String simImsi;
	    //集成电路卡识别码
	    private String simIccid;
	    //运营商
	    private String simOperator;
	    //设备的软件版本号
	    private String simSoftwareVersion;

	    //mccmnc
	    private String networkOperator;

	    //mccmnc 名字
	    private String networkOperatorName;

	    //sim运营商 名字
	    private String simOperatorName;

	    //sim国家代码
	    private String simCountryIso;

	    private int simState;



	    //network国家代码
	    private String networkCountryIso;

	    //
	    private String phoneType;

	    //
	    private String networkType;

	    /**
	     * 屏幕分辨率
	     */
	    private List<String> displayList;

	    /**
	     * cpu数据
	     */
	    private List<String> cpuData;

	    /**
	     * mem数据
	     */
	    private List<String> memData;

	    /**
	     * stat数据
	     */
	    private List<String> statData;

	    private String bluetooth;

	    private String bluetoothName;

	    private String webViewUa;

	    /**
	     * 系统内核版本
	     * Linux version 3.18.24-perf-g82fe70f (compiler@compiler02301) (gcc version 4.8 (GCC) ) #1 SMP PREEMPT Wed Dec 13 15:02:48 CST 2017
	     */
	    private String sysVersion;

	    /**
	     * 声卡信息
	     * 0 [msm8952sndcardm]: msm8952-snd-car - msm8952-snd-card-mtp
	     *                      msm8952-snd-card-mtp
	     */
	    private List<String> asoundCards;

	    /**
	     * 电池信息
	     * {level=34, scale=100, status=2, health=2, present=true, plugged=2, voltage=3815, temperature=288}
	     */
	    private String batteryInfo;

	    /**
	     * 传感器收集
	     */
	    private List<SensorInfoDataDto> sensorList;

	    /**
	     * 定位数据
	     */
	    private List<String> locationList;

	    /**
	     * rom增加df磁盘空间查看指令的修改，需要在rom.cfg中增加字段，修改如下:
	     * df = /,1.4G,9.5M,1.4G,4096$/dev,1.4G,136.0K,1.4G,4096$/mnt/runtime/default/emulated: Permission denied // df命令的每一行，中间用$分隔，每行中的内容用,分隔
	     */
	    private List<String> diskSpaceData;

	    /**
	     * cpu核数
	     */
	    private String cpuPresent;
	    /**
	     * cpu最大工作频率（千赫兹）
	     */
	    private String cpuInfoMaxFreq;
	    /**
	     * cpu最小工作频率（千赫兹）
	     */
	    private String cpuInfoMinFreq;

	    /**
	     * 基带信息 msm
	     */
	    private String roBaseBand;
	    /**
	     * 基带信息 msm
	     */
	    private String roBootBaseBand;

	    /**
	     * bootloader的标号 hhz11b
	     */
	    private String roBootBootloader;
	    /**
	     * 忽略 基本采不到 oriseb
	     */
	    private String roBootHardwareDisplay;
	    /**
	     * 一般是qcom
	     */
	    private String roBootHardware;
	    /**
	     * 与Build.SERIAL一致即可  a7dbe033
	     */
	    private String roBootSerialNo;
	    /**
	     * build的描述，类似finger
	     * msm8937_32-user 6.0.1 MMB29M eng.compiler.20171213.145748 release-keys
	     */
	    private String roBuildDescription;

	    /**
	     * 手机分辨率 - 密度
	     */
	    private float displayMetricsDensity;
	    /**
	     * 手机分辨率 - 缩放密度
	     */
	    private float displayMetricsScaledDensity;

	    /**
	     * 手机分辨率 - Dpi
	     */
	    private int displayMetricsDpi;

	    /**
	     * 手机分辨率 - xdpi
	     */
	    private float displayMetricsXdpi;

	    /**
	     * 手机分辨率 - ydpi
	     */
	    private float displayMetricsYdpi;

	    private List<String> appList; //应用列表
	    private List<String> curAppList; //应用列表
		public String getBuildDevice() {
			return buildDevice;
		}
		public void setBuildDevice(String buildDevice) {
			this.buildDevice = buildDevice;
		}
		public String getBuildProduct() {
			return buildProduct;
		}
		public void setBuildProduct(String buildProduct) {
			this.buildProduct = buildProduct;
		}
		public String getBuildModel() {
			return buildModel;
		}
		public void setBuildModel(String buildModel) {
			this.buildModel = buildModel;
		}
		public String getBuildDisplay() {
			return buildDisplay;
		}
		public void setBuildDisplay(String buildDisplay) {
			this.buildDisplay = buildDisplay;
		}
		public String getBuildBrand() {
			return buildBrand;
		}
		public void setBuildBrand(String buildBrand) {
			this.buildBrand = buildBrand;
		}
		public String getBuildBoard() {
			return buildBoard;
		}
		public void setBuildBoard(String buildBoard) {
			this.buildBoard = buildBoard;
		}
		public String getBuildFingerprint() {
			return buildFingerprint;
		}
		public void setBuildFingerprint(String buildFingerprint) {
			this.buildFingerprint = buildFingerprint;
		}
		public String getBuildManufacturer() {
			return buildManufacturer;
		}
		public void setBuildManufacturer(String buildManufacturer) {
			this.buildManufacturer = buildManufacturer;
		}
		public String getBuildSerial() {
			return buildSerial;
		}
		public void setBuildSerial(String buildSerial) {
			this.buildSerial = buildSerial;
		}
		public String getMac() {
			return mac;
		}
		public void setMac(String mac) {
			this.mac = mac;
		}
		public String getBuildVersionRelease() {
			return buildVersionRelease;
		}
		public void setBuildVersionRelease(String buildVersionRelease) {
			this.buildVersionRelease = buildVersionRelease;
		}
		public String getBuildId() {
			return buildId;
		}
		public void setBuildId(String buildId) {
			this.buildId = buildId;
		}
		public String getBuildVersionIncremental() {
			return buildVersionIncremental;
		}
		public void setBuildVersionIncremental(String buildVersionIncremental) {
			this.buildVersionIncremental = buildVersionIncremental;
		}
		public String getBuildType() {
			return buildType;
		}
		public void setBuildType(String buildType) {
			this.buildType = buildType;
		}
		public String getBuildTags() {
			return buildTags;
		}
		public void setBuildTags(String buildTags) {
			this.buildTags = buildTags;
		}
		public String getBuildVersionSdk() {
			return buildVersionSdk;
		}
		public void setBuildVersionSdk(String buildVersionSdk) {
			this.buildVersionSdk = buildVersionSdk;
		}
		public String getBuildHardware() {
			return buildHardware;
		}
		public void setBuildHardware(String buildHardware) {
			this.buildHardware = buildHardware;
		}
		public String getBuildHost() {
			return buildHost;
		}
		public void setBuildHost(String buildHost) {
			this.buildHost = buildHost;
		}
		public String getBuildUser() {
			return buildUser;
		}
		public void setBuildUser(String buildUser) {
			this.buildUser = buildUser;
		}
		public String getBuildVersionCodename() {
			return buildVersionCodename;
		}
		public void setBuildVersionCodename(String buildVersionCodename) {
			this.buildVersionCodename = buildVersionCodename;
		}
		public String getBuildBootloader() {
			return buildBootloader;
		}
		public void setBuildBootloader(String buildBootloader) {
			this.buildBootloader = buildBootloader;
		}
		public long getBuildTime() {
			return buildTime;
		}
		public void setBuildTime(long buildTime) {
			this.buildTime = buildTime;
		}
		public String getBuildVersionSdkInt() {
			return buildVersionSdkInt;
		}
		public void setBuildVersionSdkInt(String buildVersionSdkInt) {
			this.buildVersionSdkInt = buildVersionSdkInt;
		}
		public String getBuildCpu_abi() {
			return buildCpu_abi;
		}
		public void setBuildCpu_abi(String buildCpu_abi) {
			this.buildCpu_abi = buildCpu_abi;
		}
		public String getBuildCpu_abi2() {
			return buildCpu_abi2;
		}
		public void setBuildCpu_abi2(String buildCpu_abi2) {
			this.buildCpu_abi2 = buildCpu_abi2;
		}
		public String getBuildRadioVersion() {
			return buildRadioVersion;
		}
		public void setBuildRadioVersion(String buildRadioVersion) {
			this.buildRadioVersion = buildRadioVersion;
		}
		public String getBuildRadio() {
			return buildRadio;
		}
		public void setBuildRadio(String buildRadio) {
			this.buildRadio = buildRadio;
		}
		public String getAndroidId() {
			return androidId;
		}
		public void setAndroidId(String androidId) {
			this.androidId = androidId;
		}
		public String getSimImei1() {
			return simImei1;
		}
		public void setSimImei1(String simImei1) {
			this.simImei1 = simImei1;
		}
		public String getSimImei2() {
			return simImei2;
		}
		public void setSimImei2(String simImei2) {
			this.simImei2 = simImei2;
		}
		public String getSimMeid() {
			return simMeid;
		}
		public void setSimMeid(String simMeid) {
			this.simMeid = simMeid;
		}
		public String getSimImsi() {
			return simImsi;
		}
		public void setSimImsi(String simImsi) {
			this.simImsi = simImsi;
		}
		public String getSimIccid() {
			return simIccid;
		}
		public void setSimIccid(String simIccid) {
			this.simIccid = simIccid;
		}
		public String getSimOperator() {
			return simOperator;
		}
		public void setSimOperator(String simOperator) {
			this.simOperator = simOperator;
		}
		public String getSimSoftwareVersion() {
			return simSoftwareVersion;
		}
		public void setSimSoftwareVersion(String simSoftwareVersion) {
			this.simSoftwareVersion = simSoftwareVersion;
		}
		public String getNetworkOperator() {
			return networkOperator;
		}
		public void setNetworkOperator(String networkOperator) {
			this.networkOperator = networkOperator;
		}
		public String getNetworkOperatorName() {
			return networkOperatorName;
		}
		public void setNetworkOperatorName(String networkOperatorName) {
			this.networkOperatorName = networkOperatorName;
		}
		public String getSimOperatorName() {
			return simOperatorName;
		}
		public void setSimOperatorName(String simOperatorName) {
			this.simOperatorName = simOperatorName;
		}
		public String getSimCountryIso() {
			return simCountryIso;
		}
		public void setSimCountryIso(String simCountryIso) {
			this.simCountryIso = simCountryIso;
		}
		public int getSimState() {
			return simState;
		}
		public void setSimState(int simState) {
			this.simState = simState;
		}
		public String getNetworkCountryIso() {
			return networkCountryIso;
		}
		public void setNetworkCountryIso(String networkCountryIso) {
			this.networkCountryIso = networkCountryIso;
		}
		public String getPhoneType() {
			return phoneType;
		}
		public void setPhoneType(String phoneType) {
			this.phoneType = phoneType;
		}
		public String getNetworkType() {
			return networkType;
		}
		public void setNetworkType(String networkType) {
			this.networkType = networkType;
		}
		public List<String> getDisplayList() {
			return displayList;
		}
		public void setDisplayList(List<String> displayList) {
			this.displayList = displayList;
		}
		public List<String> getCpuData() {
			return cpuData;
		}
		public void setCpuData(List<String> cpuData) {
			this.cpuData = cpuData;
		}
		public List<String> getMemData() {
			return memData;
		}
		public void setMemData(List<String> memData) {
			this.memData = memData;
		}
		public List<String> getStatData() {
			return statData;
		}
		public void setStatData(List<String> statData) {
			this.statData = statData;
		}
		public String getBluetooth() {
			return bluetooth;
		}
		public void setBluetooth(String bluetooth) {
			this.bluetooth = bluetooth;
		}
		public String getBluetoothName() {
			return bluetoothName;
		}
		public void setBluetoothName(String bluetoothName) {
			this.bluetoothName = bluetoothName;
		}
		public String getWebViewUa() {
			return webViewUa;
		}
		public void setWebViewUa(String webViewUa) {
			this.webViewUa = webViewUa;
		}
		public String getSysVersion() {
			return sysVersion;
		}
		public void setSysVersion(String sysVersion) {
			this.sysVersion = sysVersion;
		}
		public List<String> getAsoundCards() {
			return asoundCards;
		}
		public void setAsoundCards(List<String> asoundCards) {
			this.asoundCards = asoundCards;
		}
		public String getBatteryInfo() {
			return batteryInfo;
		}
		public void setBatteryInfo(String batteryInfo) {
			this.batteryInfo = batteryInfo;
		}
		public List<SensorInfoDataDto> getSensorList() {
			return sensorList;
		}
		public void setSensorList(List<SensorInfoDataDto> sensorList) {
			this.sensorList = sensorList;
		}
		public List<String> getLocationList() {
			return locationList;
		}
		public void setLocationList(List<String> locationList) {
			this.locationList = locationList;
		}
		public List<String> getDiskSpaceData() {
			return diskSpaceData;
		}
		public void setDiskSpaceData(List<String> diskSpaceData) {
			this.diskSpaceData = diskSpaceData;
		}
		public String getCpuPresent() {
			return cpuPresent;
		}
		public void setCpuPresent(String cpuPresent) {
			this.cpuPresent = cpuPresent;
		}
		public String getCpuInfoMaxFreq() {
			return cpuInfoMaxFreq;
		}
		public void setCpuInfoMaxFreq(String cpuInfoMaxFreq) {
			this.cpuInfoMaxFreq = cpuInfoMaxFreq;
		}
		public String getCpuInfoMinFreq() {
			return cpuInfoMinFreq;
		}
		public void setCpuInfoMinFreq(String cpuInfoMinFreq) {
			this.cpuInfoMinFreq = cpuInfoMinFreq;
		}
		public String getRoBaseBand() {
			return roBaseBand;
		}
		public void setRoBaseBand(String roBaseBand) {
			this.roBaseBand = roBaseBand;
		}
		public String getRoBootBaseBand() {
			return roBootBaseBand;
		}
		public void setRoBootBaseBand(String roBootBaseBand) {
			this.roBootBaseBand = roBootBaseBand;
		}
		public String getRoBootBootloader() {
			return roBootBootloader;
		}
		public void setRoBootBootloader(String roBootBootloader) {
			this.roBootBootloader = roBootBootloader;
		}
		public String getRoBootHardwareDisplay() {
			return roBootHardwareDisplay;
		}
		public void setRoBootHardwareDisplay(String roBootHardwareDisplay) {
			this.roBootHardwareDisplay = roBootHardwareDisplay;
		}
		public String getRoBootHardware() {
			return roBootHardware;
		}
		public void setRoBootHardware(String roBootHardware) {
			this.roBootHardware = roBootHardware;
		}
		public String getRoBootSerialNo() {
			return roBootSerialNo;
		}
		public void setRoBootSerialNo(String roBootSerialNo) {
			this.roBootSerialNo = roBootSerialNo;
		}
		public String getRoBuildDescription() {
			return roBuildDescription;
		}
		public void setRoBuildDescription(String roBuildDescription) {
			this.roBuildDescription = roBuildDescription;
		}
		public float getDisplayMetricsDensity() {
			return displayMetricsDensity;
		}
		public void setDisplayMetricsDensity(float displayMetricsDensity) {
			this.displayMetricsDensity = displayMetricsDensity;
		}
		public float getDisplayMetricsScaledDensity() {
			return displayMetricsScaledDensity;
		}
		public void setDisplayMetricsScaledDensity(float displayMetricsScaledDensity) {
			this.displayMetricsScaledDensity = displayMetricsScaledDensity;
		}
		public int getDisplayMetricsDpi() {
			return displayMetricsDpi;
		}
		public void setDisplayMetricsDpi(int displayMetricsDpi) {
			this.displayMetricsDpi = displayMetricsDpi;
		}
		public float getDisplayMetricsXdpi() {
			return displayMetricsXdpi;
		}
		public void setDisplayMetricsXdpi(float displayMetricsXdpi) {
			this.displayMetricsXdpi = displayMetricsXdpi;
		}
		public float getDisplayMetricsYdpi() {
			return displayMetricsYdpi;
		}
		public void setDisplayMetricsYdpi(float displayMetricsYdpi) {
			this.displayMetricsYdpi = displayMetricsYdpi;
		}
		public List<String> getAppList() {
			return appList;
		}
		public void setAppList(List<String> appList) {
			this.appList = appList;
		}
		public List<String> getCurAppList() {
			return curAppList;
		}
		public void setCurAppList(List<String> curAppList) {
			this.curAppList = curAppList;
		}
	    
	    
	    
}
