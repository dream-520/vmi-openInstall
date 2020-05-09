package org.shark.miai.common.cloud.storage;

/**
 * 抽象的云存储
 * @author chengang
 *
 */
public abstract class AbstractCloudStorage implements ICloudStorage {
	
	/**
     * 路径的分割符
     */
	protected final String SEPARATOR = "/";
	
	/**
	 * 空间名称
	 */
	protected final String bucketName;
	
	/**
	 * 操作员名称
	 */
	protected final String userName;
	
	/**
	 * 密码
	 */
	protected final String password;
	
	/**
	 * 传入参数构造云存储对象
	 * @param bucketName - 空间名称
	 * @param userName - 操作员名称
	 * @param password - 密码
	 */
	public AbstractCloudStorage(String bucketName, String userName, String password) {
		this.bucketName = bucketName;
		this.userName = userName;
		this.password = password;
	}

}
