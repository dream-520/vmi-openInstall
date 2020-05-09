package com.tigerjoys.shark.miai.agent.dto.result;

import java.io.Serializable;

import com.tigerjoys.nbs.common.IErrorCodeEnum;

/**
 * 代理包通用返回对象
 * 
 * @author chengang
 *
 */
public class AgentResult implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6530288282605462431L;

	// 返回码
	private int code;
	// 返回描述
	private String codemsg;
	// 返回的数据
	private Object data;
	// 分页戳
	private Object stamp;
	// 是否有下一页
	private Boolean nextPage;

	/**
	 * 返回AgentResult
	 * 
	 * @param code
	 *            - 返回码
	 * @param codemsg
	 *            - 返回描述
	 * @param data
	 *            - 返回的数据
	 * @param stamp
	 *            - 分页戳
	 * @return AgentResult
	 */
	public static AgentResult getResult(int code, String codemsg, Object data, Object stamp) {
		AgentResult result = new AgentResult();
		result.code = code;
		result.codemsg = codemsg;
		result.data = data;
		result.stamp = stamp;

		return result;
	}

	/**
	 * 返回成功
	 * @return AgentResult
	 */
	public static AgentResult success() {
		return success(null, null, null);
	}

	/**
	 * 返回成功
	 * @param data - 返回的数据
	 * @return AgentResult
	 */
	public static AgentResult success(Object data) {
		return success(data, null, null);
	}

	/**
	 * 返回成功
	 * @param data - 返回的数据
	 * @param successMsg - 自定义文案
	 * @return AgentResult
	 */
	public static AgentResult success(Object data, String successMsg) {
		return success(data, successMsg, null, null);
	}

	/**
	 * 返回成功
	 * @param data - 返回的数据
	 * @param stamp - 分页戳
	 * @param nextPage - 是否有下一页
	 * @return AgentResult
	 */
	public static AgentResult success(Object data, Object stamp, Boolean nextPage) {
		AgentResult result = getResult(0, "成功", data, stamp);
		result.setNextPage(nextPage);

		return result;
	}

	/**
	 * 返回成功
	 * @param data - 返回的数据
	 * @param successMsg - 自定义文案
	 * @param stamp - 分页戳
	 * @param nextPage - 是否有下一页
	 * @return AgentResult
	 */
	public static AgentResult success(Object data, String successMsg, Object stamp, Boolean nextPage) {
		AgentResult result = getResult(0, successMsg, data, stamp);
		result.setNextPage(nextPage);

		return result;
	}

	/**
	 * 返回默认的错误失败
	 * @return - AgentResult
	 */
	public static AgentResult fail() {
		return getResult(1, "系统异常", null, null);
	}

	/**
	 * 返回错误信息
	 * @param errorCode - 错误枚举
	 * @return AgentResult
	 */
	public static AgentResult fail(IErrorCodeEnum errorCode) {
		return getResult(errorCode.getCode(), errorCode.getDesc(), null, null);
	}

	/**
	 * 返回失败
	 * @param code - 返回码
	 * @param codemsg - 返回描述
	 * @return AgentResult
	 */
	public static AgentResult fail(int code, String codemsg) {
		return getResult(code, codemsg, null, null);
	}

	/**
	 * 返回失败
	 * @param code - 返回码
	 * @param codemsg - 返回描述
	 * @param data - 返回的数据
	 * @return AgentResult
	 */
	public static AgentResult fail(int code, String codemsg, Object data) {
		return getResult(code, codemsg, data, null);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodemsg() {
		return codemsg;
	}

	public void setCodemsg(String codemsg) {
		this.codemsg = codemsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getStamp() {
		return stamp;
	}

	public void setStamp(Object stamp) {
		this.stamp = stamp;
	}

	public Boolean getNextPage() {
		return nextPage;
	}

	public void setNextPage(Boolean nextPage) {
		this.nextPage = nextPage;
	}

}
