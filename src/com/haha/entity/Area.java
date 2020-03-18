package com.haha.entity;

public class Area {
	private int  _id  ;
	private String  name  ;
	private String  area_id ;
	private String  city_id  ;
	public Area() {
		super();
	}
	public Area(int _id, String name, String area_id, String city_id) {
		super();
		this._id = _id;
		this.name = name;
		this.area_id = area_id;
		this.city_id = city_id;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
}
