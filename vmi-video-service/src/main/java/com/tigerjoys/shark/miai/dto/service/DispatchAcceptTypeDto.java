package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 达人可以接受的派单类型dto
 * 
 * @author shiming
 *
 */
public class DispatchAcceptTypeDto implements Serializable {

	private static final long serialVersionUID = -2972763911331597310L;

	/**
	 * id
	 */
	private long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 是否被选中
	 */
	private boolean select;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsSelect() {
		return select;
	}

	public void setIsSelect(boolean select) {
		this.select = select;
	}
	
}
