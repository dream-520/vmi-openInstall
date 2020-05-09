package com.tigerjoys.shark.miai.agent.exception;

/**
 * 付费约异常类
 * @author chengang
 *
 */
public class PaidAppointAccountException extends AbstractAccountException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7106236664018286254L;

	public PaidAppointAccountException(int code) {
		super(code);
	}
	
	public PaidAppointAccountException(int code , String message) {
		super(code , message);
	}

}
