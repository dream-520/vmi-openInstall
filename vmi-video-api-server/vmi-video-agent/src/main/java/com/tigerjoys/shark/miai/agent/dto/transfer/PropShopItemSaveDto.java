package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;

/**
 * 道具商城的道具添加或者删除的DTO对象
 * @author chengang
 *
 */
public class PropShopItemSaveDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5629914097545955725L;
	
	/**
	 * 商城映射ID，如果调用的为修改方法，则必不为空
	 */
	private Long shop_propid;
	
	/**
	 * 道具ID
	 */
	private Long propid;
	
	/**
	 * 购买红包树等级限制,0不限制
	 */
	private Integer tree_level;
	
	/**
	 * 购买用户等级限制,0不限制
	 */
	private Integer user_level;
	
	/**
	 * 购买：钻
	 */
	private Integer diamond;
	
	/**
	 * 购买：金币
	 */
	private Integer gold;
	
	/**
	 * 支付方式,0钻+金币,1钻或金币
	 */
	private Integer pay_type;
	
	/**
	 * 道具类型,0默认,1特殊
	 */
	private Integer item_type;
	
	/**
	 * 优先级，排序用
	 */
	private Integer priority;
	
	/**
	 * 状态，0下架,1上架
	 */
	private Integer status;
	
	/**
	 * 添加人/修改人
	 */
	private long adminId;

	public Long getShop_propid() {
		return shop_propid;
	}

	public void setShop_propid(Long shop_propid) {
		this.shop_propid = shop_propid;
	}

	public Long getPropid() {
		return propid;
	}

	public void setPropid(Long propid) {
		this.propid = propid;
	}

	public Integer getTree_level() {
		return tree_level;
	}

	public void setTree_level(Integer tree_level) {
		this.tree_level = tree_level;
	}

	public Integer getUser_level() {
		return user_level;
	}

	public void setUser_level(Integer user_level) {
		this.user_level = user_level;
	}

	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public Integer getItem_type() {
		return item_type;
	}

	public void setItem_type(Integer item_type) {
		this.item_type = item_type;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

}
