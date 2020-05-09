package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import org.apache.ibatis.annotations.Transient;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户扩展信息表[t_user_extension] 表对应的实体类
 * @author lipeng
 * @Date 2017-08-24 16:35:06
 *
 */
@Table(name="t_user_extension")
public class UserExtensionEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 星座
	 */
	@Column(name="zodiac",nullable=false,jdbcType=JdbcType.CHAR,comment="星座")
	private String zodiac;
	
	/**
	 * 感情状态
	 */
	@Column(name="marriage",nullable=false,jdbcType=JdbcType.TINYINT,comment="感情状态")
	private Integer marriage;
	
	/**
	 * 职位
	 */
	@Column(name="job",nullable=false,jdbcType=JdbcType.VARCHAR,comment="职位")
	private String job;
	
	/**
	 * 收入
	 */
	@Column(name="income",nullable=false,jdbcType=JdbcType.TINYINT,comment="收入")
	private Integer income;
	
	/**
	 * 身高(CM)
	 */
	@Column(name="stature",nullable=false,jdbcType=JdbcType.SMALLINT,comment="身高(CM)")
	private Integer stature;
	
	/**
	 * 体重(KG)
	 */
	@Column(name="weight",nullable=false,jdbcType=JdbcType.SMALLINT,comment="体重(KG)")
	private Integer weight;
	
	/**
	 * 对性的看法
	 */
	@Column(name="sex_opinion",nullable=false,jdbcType=JdbcType.TINYINT,comment="对性的看法")
	private Integer sex_opinion;
	
	/**
	 * 对另一半的看法
	 */
	@Column(name="spouse_opinion",nullable=false,jdbcType=JdbcType.TINYINT,comment="对另一半的看法")
	private Integer spouse_opinion;
	
	/**
	 * 交友目地
	 */
	@Column(name="make_friend",nullable=false,jdbcType=JdbcType.TINYINT,comment="交友目地")
	private Integer make_friend;
	
	/**
	 * 特点
	 */
	@Column(name="trait_point",nullable=true,jdbcType=JdbcType.VARCHAR,comment="特点")
	private String trait_point;
	
	/**
	 * 扩展JSON字段
	 */
	@Column(name="extra",nullable=true,jdbcType=JdbcType.VARCHAR,comment="扩展JSON字段")
	private String extra;
	
	/**
	 * 扩展参数
	 */
	@Transient
	private JSONObject paramJson;
	
	/**
	 * 是否已经解析过扩展参数
	 */
	@Transient
	private boolean isGetParamMap = false;
	
	/**
	 * 获取域 Paramap 单元参数的map对象，key是单元参数的id.<br>
	 * <b>注意：在更新完单元参数后，请务必调用commit才回覆盖数据库的映射字段，并最终保存到数据库中<b>
	 * @return Paramap
	 */
	public JSONObject getParamJson() {
		if (paramJson == null) {
			paramJson = JsonHelper.toJsonObject(extra);
			if(paramJson == JsonHelper.EMPTY_JSON) {
				paramJson = new JSONObject();
			}
		}
		isGetParamMap = true;
		return paramJson;
	}
	
	/**
	 * 将外部的JSONObject 替换本对象的单元参数，本单元没有的参数将为新增。<br>
	 * <b>注意：在更新完单元参数后，请务必调用commit才回覆盖数据库的映射字段，并最终保存到数据库中<b>
	 * @param paramJson - JSONObject
	 */
	public void setParamJson(JSONObject paramJson) {
		if(paramJson == null || paramJson.isEmpty()) {
			return;
		}
		
		//替换单元参数的值
		getParamJson().putAll(paramJson);
	}
	
	/**
	 * 添加单元参数<br>
	 * <b>注意：在更新完单元参数后，请务必调用commit才回覆盖数据库的映射字段，并最终保存到数据库中<b>
	 * @param key - 单元参数键
	 * @param value - 单元参数值
	 */
	public void addParamJson(String key , Object value) {
		getParamJson().put(key, value);
	}

	/**
	 * 将单元参数的对象转换成json string，并存储到相关域
	 */
	public void commit() {
		if (isGetParamMap) {
			if(paramJson != null && !paramJson.isEmpty()) {
				this.extra = paramJson.toJSONString();
			} else {
				this.extra = "{}";
			}
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	
	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}
	
	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getSex_opinion() {
		return sex_opinion;
	}

	public void setSex_opinion(Integer sex_opinion) {
		this.sex_opinion = sex_opinion;
	}
	
	public Integer getSpouse_opinion() {
		return spouse_opinion;
	}

	public void setSpouse_opinion(Integer spouse_opinion) {
		this.spouse_opinion = spouse_opinion;
	}
	
	public Integer getMake_friend() {
		return make_friend;
	}

	public void setMake_friend(Integer make_friend) {
		this.make_friend = make_friend;
	}
	
	public String getTrait_point() {
		return trait_point;
	}

	public void setTrait_point(String trait_point) {
		this.trait_point = trait_point;
	}
	
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
}