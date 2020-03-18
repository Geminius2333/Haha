package com.haha.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haha.dao.AddressDao;
import com.haha.entity.Area;
import com.haha.entity.City;
import com.haha.entity.Province;

public class AddressDaoImpl extends JDBCBase implements AddressDao {

	@Override
	public List<Province> getProvinces() {
		// TODO Auto-generated method stub
		List<Province> provinces = new ArrayList<Province>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql = "SELECT * FROM province where 1=1";
		try {
			Province province = new Province();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				province = packProvince(rs);
				provinces.add(province);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		System.out.println(provinces.size());
		return provinces;
	}

	@Override
	public List<City> getCities(String id) {
		// TODO Auto-generated method stub
		List<City> citys = new ArrayList<City>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql = "SELECT * FROM city where province_id="+id;
		try {
			City city = new City();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				city = packCity(rs);
				citys.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		System.out.println(citys.size());
		return citys;
	}

	@Override
	public List<Area> getAreas(String id) {
		// TODO Auto-generated method stub
		List<Area> areas = new ArrayList<Area>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql = "SELECT * FROM area where city_id="+id;
		try {
			Area area = new Area();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				area = packArea(rs);
				areas.add(area);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		System.out.println(areas.size());
		return areas;
	}
	
	public static Province packProvince(ResultSet rs) throws SQLException {
		Province province = new Province();
		province.set_id(rs.getInt("_id"));
		province.setName(rs.getString("name"));
		province.setProvince_id(rs.getString("province_id"));
		return province;
	}
	
	public static City packCity(ResultSet rs) throws SQLException{
		City city = new City();
		city.set_id(rs.getInt("_id"));
		city.setName(rs.getString("name"));
		city.setProvince_id(rs.getString("province_id"));
		city.setCity_id(rs.getString("city_id"));
		return city;
	}
	
	public static Area packArea(ResultSet rs) throws SQLException{
		Area area = new Area();
		area.set_id(rs.getInt("_id"));
		area.setName(rs.getString("name"));
		area.setCity_id(rs.getString("city_id"));
		area.setArea_id(rs.getString("area_id"));
		return area;
	}
}


