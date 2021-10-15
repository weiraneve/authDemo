package com.weiran.basic.myexception;

/**
 * Token 异常
 */
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;
	private String msg;

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

	public TokenException(String arg0) {
		super(arg0);
	}
	
	public TokenException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
}
