package org.shark.miai.common.dto;

import java.io.Serializable;

/**
 * ID和名称 DTO
 * @author chengang
 *
 */
public class IdNameDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4072935745812866805L;
	
	/**
	 * ID
	 */
	private final long id;
	
	/**
	 * 名称
	 */
	private final String name;
	
	/**
	 * icon地址
	 */
	private final String icon;
	
	/**
	 * 值
	 */
	private final String value;
	
	public IdNameDto(long id , String name) {
		this(id , name , null , null);
	}
	
	public IdNameDto(long id , String name , String value) {
		this(id , name , null , value);
	}
	
	public IdNameDto(long id , String name , String icon , String value) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	public String getValue() {
		return value;
	}

}
