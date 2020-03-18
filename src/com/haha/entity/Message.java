package com.haha.entity;

public class Message {
	private int id;
	private String sender;
	private String reciever;
	private String content;
	private int isread;
	private String upfile;
	public Message() {
		super();
	}
	public Message(String sender, String reciever, String content, int isread,String upfile) {
		super();
		this.sender = sender;
		this.reciever = reciever;
		this.content = content;
		this.isread = isread;
		this.upfile = upfile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsread() {
		return isread;
	}
	public String getIsreadResult() {
		if(isread == 0)
			return "未读";
		else {
			return "已读";
		}
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public String getUpfile() {
		return upfile;
	}
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	
}
