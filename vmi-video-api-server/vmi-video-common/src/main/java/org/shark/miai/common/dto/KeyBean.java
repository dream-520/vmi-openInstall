package org.shark.miai.common.dto;

public class KeyBean {
	
	/**
	 * key
	 */
	private String key;
	
	/**
	 * value
	 */
	private String value;
	
	public KeyBean(){
		
	}
	
	public KeyBean(String key , String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
