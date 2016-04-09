package com.zhk.core.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Author {
	String authorInfo ="huxuquan@netviom.com";
	String authorName = "huxu";
	String version = "1.0.0.1";
	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	String note="此页面由Velocity模版生成";
	public String getAuthorInfo() {
		return authorInfo;
	}
	public void setAuthorInfo(String authorInfo) {
		this.authorInfo = authorInfo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
