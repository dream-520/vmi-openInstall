package com.tigerjoys.shark.miai.agent.exception;

/**
 * 抽象的账户异常类
 * @author chengang
 *
 */
public abstract class AbstractAccountException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4342701913774380475L;
	
	/**
	 * 返回码
	 */
	private final int code;
	
	public AbstractAccountException(int code) {
		super();
		this.code = code;
	}
	
	public AbstractAccountException(int code , String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
