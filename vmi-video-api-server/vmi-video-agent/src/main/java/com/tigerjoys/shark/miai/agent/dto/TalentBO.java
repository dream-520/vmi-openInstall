package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 达人信息DTO
 * @author chengang
 *
 */
public class TalentBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -583667510071413703L;
	
	/**
	 * 达人信息ID
	 */
	private long appointId;
	
	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 排序修改时间
	 */
	private long modifyTime;
	
	/**
	 * 服务类型,多选格式:,1,2,
	 */
	private String types;
	
	/**
	 * 所在国家ID
	 */
	private long countryid;
	
	/**
	 * 所在省份ID
	 */
	private long provinceid;
	
	/**
	 * 所在城市ID
	 */
	private long cityid;
	
	/**
	 * 状态
	 * @see PaidAppointStatusEnum
	 */
	private int status;
	
	/**
	 * 下架类型
	 * @see PaidAppointOfflineTypeEnum
	 */
	private int offlineType;
	
	/**
	 * 是否线上达人
	 */
	private boolean inlet;
	
	/**
	 * 申请时间
	 */
	private Date applyTime;
	
	/**
	 * 审核时间
	 */
	private Date auditTime;
	
	/**
	 * 审核不通过原因
	 */
	private String auditMemo;
	
	/**
	 * 是否接收派单
	 */
	private boolean accept;
	
	public TalentBO() {
		
	}
	
	public TalentBO(long userid) {
		this.userid = userid;
	}

	public long getAppointId() {
		return appointId;
	}

	public void setAppointId(long appointId) {
		this.appointId = appointId;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public long getCountryid() {
		return countryid;
	}

	public void setCountryid(long countryid) {
		this.countryid = countryid;
	}

	public long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(long provinceid) {
		this.provinceid = provinceid;
	}

	public long getCityid() {
		return cityid;
	}

	public void setCityid(long cityid) {
		this.cityid = cityid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOfflineType() {
		return offlineType;
	}

	public void setOfflineType(int offlineType) {
		this.offlineType = offlineType;
	}

	public boolean isInlet() {
		return inlet;
	}

	public void setInlet(boolean inlet) {
		this.inlet = inlet;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditMemo() {
		return auditMemo;
	}

	public void setAuditMemo(String auditMemo) {
		this.auditMemo = auditMemo;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

}
