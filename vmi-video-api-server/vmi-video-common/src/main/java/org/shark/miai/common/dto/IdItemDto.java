package org.shark.miai.common.dto;

import java.io.Serializable;

/**
 * 技能一级条目 DTO
 * @author shiming
 *
 */
public class IdItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8494183036523016718L;

	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 图片
	 */
	private String picUrl;
	
	/**
	 * 大图片
	 */
	private String bigPicUrl;
	
	public static IdItemDto preDto(int id,String name,String ico,String bigPicUrl){
		IdItemDto dto = new IdItemDto();
		dto.setId(id);
		dto.setName(name);
		dto.setPicUrl(ico);
		dto.setBigPicUrl(bigPicUrl);
		return dto;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getBigPicUrl() {
		return bigPicUrl;
	}

	public void setBigPicUrl(String bigPicUrl) {
		this.bigPicUrl = bigPicUrl;
	}


	
	
}
