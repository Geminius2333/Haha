package com.haha.entity;

public class User implements java.io.Serializable  {
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String youbian;
	private String pic;
	private String province;
	private String city;
	private String area;
	
	public User() {
		super();
	}
	public User(String username, String password, String email, String phone, String youbian, String pic,String province,String city,String area) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.youbian = youbian;
		this.pic = pic;
		this.province = province;
		this.city = city;
		this.area = area;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getYoubian() {
		return youbian;
	}
	public void setYoubian(String youbian) {
		this.youbian = youbian;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
