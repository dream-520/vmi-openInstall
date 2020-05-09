package com.tigerjoys.shark.miai.dto.service;

import java.io.Serializable;

/**
 * 处理转盘信息的dto
 * @author yangjunming
 *
 */
public class TurntableCheckDto implements Serializable {

	private long id;
	
	private String name;
	
	private boolean checked;

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


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
