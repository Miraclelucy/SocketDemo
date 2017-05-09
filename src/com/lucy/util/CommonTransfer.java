package com.lucy.util;

import java.io.Serializable;

public class CommonTransfer  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2549534616267890131L;
	private String cmd; //命令 register login  uploadFile 
	private Object data; //发送的数据
	private boolean flag; //是否操作成功
	private String result;//返回的结果
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
