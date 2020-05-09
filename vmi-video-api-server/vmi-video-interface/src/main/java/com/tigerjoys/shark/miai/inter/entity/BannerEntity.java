package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  banner推荐表[t_banner] 表对应的实体类
 * @author chengang
 * @Date 2017-05-17 11:09:55
 *
 */
@Table(name="t_banner")
public class BannerEntity extends BaseEntity implements Serializable {

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
	 * 创建人ID
	 */
	@Column(name="create_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="创建人ID")
	private Long create_adminid;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 修改人ID
	 */
	@Column(name="update_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="修改人ID")
	private Long update_adminid;
	
	/**
	 * 标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标题")
	private String title;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String memo;
	
	/**
	 * 分组ID
	 */
	@Column(name="groupid",nullable=false,jdbcType=JdbcType.BIGINT,comment="分组ID")
	private Long groupid;
	
	/**
	 * 图片地址
	 */
	@Column(name="imageurl",nullable=false,jdbcType=JdbcType.VARCHAR,comment="图片地址")
	private String imageurl;
	
	/**
	 * 打开链接
	 */
	@Column(name="openurl",nullable=false,jdbcType=JdbcType.VARCHAR,comment="打开链接")
	private String openurl;
	
	/**
	 * 参数
	 */
	@Column(name="parameters",nullable=true,jdbcType=JdbcType.VARCHAR,comment="参数")
	private String parameters;
	
	/**
	 * 开始时间
	 */
	@Column(name="starttime",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="开始时间")
	private Date starttime;
	
	/**
	 * 结束时间
	 */
	@Column(name="endtime",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="结束时间")
	private Date endtime;
	
	/**
	 * 优先级
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="优先级")
	private Integer priority;
	
	/**
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="0打开网页链接,1打开应用链接,2打开浏览器")
	private Integer type;
	
	/**
	 * 点击量
	 */
	@Column(name="click",nullable=false,jdbcType=JdbcType.BIGINT,comment="点击量")
	private Long click;
	
	/**
	 * 1正常,0暂停,-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常,0暂停,-9删除")
	private Integer status;
	
	/**
	 * 是否为活动 0.否 1.是
	 */
	@Column(name="activity",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否为活动 0.否 1.是")
	private Integer activity;
	
	/**
	 * app内页的参数,json
	 */
	@Column(name="parame",nullable=true,jdbcType=JdbcType.VARCHAR,comment="app内页的参数,json")
	private String parame;
	
	/**
	 * banner显示范围  0 全部 1 用户 2 主播
	 */
	@Column(name="scope",nullable=true,jdbcType=JdbcType.TINYINT,comment="banner显示范围  0 全部 1 用户 2 主播")
	private Integer scope;
	
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
	
	public Long getCreate_adminid() {
		return create_adminid;
	}

	public void setCreate_adminid(Long create_adminid) {
		this.create_adminid = create_adminid;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getUpdate_adminid() {
		return update_adminid;
	}

	public void setUpdate_adminid(Long update_adminid) {
		this.update_adminid = update_adminid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}
	
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public String getOpenurl() {
		return openurl;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}
	
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getClick() {
		return click;
	}

	public void setClick(Long click) {
		this.click = click;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}
	
	public String getParame() {
		return parame;
	}

	public void setParame(String parame) {
		this.parame = parame;
	}
	
	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}
}