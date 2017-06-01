package com.rong.android.shareaccount.pojo;

/**
 * Created by rong on 2017/5/23.
 */

public class ResponseBody<T> {

	private String reason;
	private int error_code;
	private T result;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
