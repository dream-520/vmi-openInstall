package org.shark.miai.common.cloud.upyun;

public enum UpYunCloudVideoExtEnum {

	mp4("MP4"),
	flv("FLV"),
	m3u8("M3U8"),
	;
	
	private String value;
	
	private UpYunCloudVideoExtEnum(String name){
		this.value = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
	
}
