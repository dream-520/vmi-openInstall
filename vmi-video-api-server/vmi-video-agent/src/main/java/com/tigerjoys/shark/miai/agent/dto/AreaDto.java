package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.tigerjoys.shark.miai.inter.entity.AppAreaEntity;

/**
 * 城市区域DTO
 * @author chengang
 *
 */
public class AreaDto implements Serializable , Comparable<AreaDto> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4228167159333499517L;
	private Long id;//ID
	private String name;//名称
	private String citycode;//city code
	private String spell;//拼音
	private String initial;//首字母
	private Integer hot;//是否热门城市,0非,1是
	private Integer depth;
	private Long pid;//父ID
	private Double lng;//经度
	private Double lat;//纬度
	private Integer baiduCode;//百度城市编号
	private Boolean iszhi;//是否是直辖市或者特别行政区
	private Integer level;//城市等级
	
	public static AreaDto parseDto(AppAreaEntity area) {
		AreaDto dto = new AreaDto();
		dto.citycode = area.getEname();
		dto.hot = area.getHot();
		dto.id = area.getId();
		dto.initial = area.getInitial();
		dto.name = area.getName();
		dto.pid = area.getPid();
		dto.depth = area.getDepth();
		dto.spell = area.getSpell();
		dto.lng = area.getLng();
		dto.lat = area.getLat();
		dto.baiduCode = area.getBaidu_code();
		dto.iszhi = area.getIszhi()==1;
		dto.level = area.getLevel();
		
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getBaiduCode() {
		return baiduCode;
	}

	public void setBaiduCode(Integer baiduCode) {
		this.baiduCode = baiduCode;
	}

	public Boolean getIszhi() {
		return iszhi;
	}

	public void setIszhi(Boolean iszhi) {
		this.iszhi = iszhi;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public int compareTo(AreaDto o) {
		char c1 = this.initial.charAt(0) , c2 = o.initial.charAt(0);
		if(c1 > c2) {
			return 1;
		} else if(c1 < c2) {
			return -1;
		} else {
			if(this.id > o.id) {
				return 1;
			} else if(this.id < o.id) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
