package org.shark.miai.common.dto;

import java.io.Serializable;

/**
 * 技能一级条目 DTO
 * @author shiming
 *
 */
public class IdItemAllDto implements Serializable {

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
	 * 对应本条目对应的父类id  这样就可以直接通过条目获取对应的父类id值了
	 */
	private int parentId;
	
	
	public static IdItemAllDto preDto(int id,String name,int parentId){
		IdItemAllDto dto = new IdItemAllDto();
		dto.setId(id);
		dto.setName(name);
		dto.setParentId(parentId);
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
