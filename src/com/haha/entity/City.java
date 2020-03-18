package com.haha.entity;

public class City {
	private int   _id;           
	private String   city_id  ;    
	private String  name   ;       
	private String  province_id   ;
	public City() {
		super();
	}
	public City(int _id, String city_id, String name, String province_id) {
		super();
		this._id = _id;
		this.city_id = city_id;
		this.name = name;
		this.province_id = province_id;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	
}
