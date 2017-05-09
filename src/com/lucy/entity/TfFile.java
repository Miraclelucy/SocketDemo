package com.lucy.entity;

import java.io.Serializable;

public class TfFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fid;
	private String filename;
	private byte[] filebyte;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getFilebyte() {
		return filebyte;
	}
	public void setFilebyte(byte[] filebyte) {
		this.filebyte = filebyte;
	}
}
