package org.shark.miai.common.dto;

import java.io.Serializable;

/**
 * ID Value映射对象
 * @author chengang
 *
 */
public class IdNameBooleanDto implements Serializable , Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2143831540857405100L;
	
	/**
	 * ID
	 */
	private long id;
	
	/**
	 * value
	 */
	private String name;
	
	/**
	 * 是否选中
	 */
	private boolean check;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 数值
	 */
	private String value;
	
	public IdNameBooleanDto(long id , String name) {
		this(id , name , false , null , null);
	}
	
	public IdNameBooleanDto(long id , String name , boolean check) {
		this(id , name , check , null , null);
	}
	
	public IdNameBooleanDto(long id , String name , boolean check , String icon) {
		this(id , name , check , icon , null);
	}
	
	public IdNameBooleanDto(long id , String name , String value) {
		this(id , name , false , null , value);
	}
	
	public IdNameBooleanDto(long id , String name , String value , boolean check) {
		this(id , name , check , null , value);
	}
	
	public IdNameBooleanDto(long id , String name , boolean check , String icon , String value) {
		this.id = id;
		this.name = name;
		this.check = check;
		this.icon = icon;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isCheck() {
		return check;
	}

	public String getIcon() {
		return icon;
	}

	public String getValue() {
		return value;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		IdNameBooleanDto o = (IdNameBooleanDto)obj;
		
		if(this.id == o.id) {
			if(this.name != null && this.name.equals(o.name)) {
				if(this.value != null && this.value.equals(o.value)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
