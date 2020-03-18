package com.haha.entity;

public class Contact implements java.io.Serializable {
	private int id;
	private int user_id;
	private String name;
	private int sex;
	private int phone;
	private String email;
	private int youbian;
	private int qq;
	private String work_location;
	private String address;
	public Contact(int user_id, String name, int sex, int phone, String email, int youbian, int qq,
			String work_location, String address) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.youbian = youbian;
		this.qq = qq;
		this.work_location = work_location;
		this.address = address;
	}
	public Contact() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getYoubian() {
		return youbian;
	}
	public void setYoubian(int youbian) {
		this.youbian = youbian;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
		this.qq = qq;
	}
	public String getWork_location() {
		return work_location;
	}
	public void setWork_location(String work_location) {
		this.work_location = work_location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
		
}
