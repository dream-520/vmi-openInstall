package com.tigerjoys.shark.miai.agent.dto.result;

import java.io.Serializable;

import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;

/**
 * 积分返回信息
 * ClassName: PointResultDto <br/> 
 * date: 2017年5月10日 下午5:22:00 <br/> 
 * 
 * @author lipeng 
 * @version @param <T> 
 * @since JDK 1.8.0
 */
public class PointResultDto<T> implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 6637282740072602083L;
	
	/**
	 * 返回码,0成功，其他失败
	 */
	private int code;
	
	/**
	 * 返回信息
	 */
	private String msg;
	
	
	/**
	 * 返回数据
	 */
	private  T data;
	
	/**
	 * 返回失败信息
	 * @param code - 错误码
	 * @return PassportResultDto<T>
	 * @see com.tigerjoys.orca.inter.passport.enums.PassportErrEnum
	 */
	public static <T extends Object> PointResultDto<T> fail(int code) {
		return failAndData(code, null);
	}
	
	/**
	 * 返回失败信息并且存放data数据
	 * @param code - 错误码
	 * @param data - 错误数据返回
	 * @return PassportResultDto<T>
	 * @see com.tigerjoys.orca.inter.passport.enums.PassportErrEnum
	 */
	public static <T extends Object> PointResultDto<T> failAndData(int code , T data) {
		String msg = AgentErrorCodeEnum.getDescByCode(code);
		if(msg == null) {
			code = AgentErrorCodeEnum.unkonwn.getCode();
			msg = AgentErrorCodeEnum.unkonwn.getDesc();
		}
		return fail(code, msg, data);
	}
	
	/**
	 * 返回失败信息
	 * @param code - 错误码
	 * @param msg - 错误信息
	 * @return PassportResultDto<T>
	 * @see com.tigerjoys.orca.inter.passport.enums.PassportErrEnum
	 */
	public static <T extends Object> PointResultDto<T> fail(int code , String msg) {
		return fail(code, msg, null);
	}
	
	/**
	 * 返回失败信息
	 * @param code - 错误码
	 * @param msg - 错误信息
	 * @param data - 错误数据返回
	 * @return PassportResultDto<T>
	 * @see com.tigerjoys.orca.inter.passport.enums.PassportErrEnum
	 */
	public static <T extends Object> PointResultDto<T> fail(int code , String msg , T data) {
		PointResultDto<T> dto = new PointResultDto<>();
		dto.setCode(code);
		dto.setMsg(msg);
		dto.setData(data);
		return dto;
	}
	
	
	/**
	 * 返回成功信息
	 * @return PassportResultDto<T>
	 */
	public static <T> PointResultDto<T> success() {
		return success(null);
	}
	
	/**
	 * 返回成功信息
	 * @param data - 返回数据
	 * @return PassportResultDto<T>
	 */
	public static <T> PointResultDto<T> success(T data) {
		PointResultDto<T> dto = new PointResultDto<>();
		dto.setCode(AgentErrorCodeEnum.success.getCode());
		dto.setMsg(AgentErrorCodeEnum.success.getDesc());
		dto.setData(data);
		return dto;
	}
	
	
	/**
	 * 返回成功信息
	 * @param data - 返回数据
	 * @return PassportResultDto<T>
	 */
	public static <T> PointResultDto<T> success(String msg , T data) {
		PointResultDto<T> dto = new PointResultDto<>();
		dto.setCode(AgentErrorCodeEnum.success.getCode());
		dto.setMsg(msg);
		dto.setData(data);
		return dto;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
