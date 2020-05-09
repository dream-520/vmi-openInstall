package org.shark.miai.common.cloud.storage;

/**
 * 高斯模糊等级
 * @author chengang
 *
 */
public enum GaussianBlurLevel {
	
	LOW(100),
	MIDDLE(150),
	HIGH(200),
	SUPER(250)
	;
	
	private final int radius;
	
	GaussianBlurLevel(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

}
