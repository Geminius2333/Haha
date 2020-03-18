package com.haha.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haha.dao.UserDao;
import com.haha.entity.User;
import com.haha.globle.Constants;
import com.mysql.jdbc.Statement;

public class UserDaoImpl extends JDBCBase implements UserDao {

	@Override
	public User getUserByCondition(String condition) {
		// TODO Auto-generated method stub
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM user u WHERE u.username = ?";
		try {
			ps = con.prepareStatement(sql);
			Object[] param = {condition};
			rs = query(ps, param);
			if(rs.next()){
				user = packUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return user;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConnection();
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		String phone = user.getPhone();
		String youbian = user.getYoubian();
		String pic = user.getPic();
		String province = user.getProvince();
		String city = user.getCity();
		String area = user.getArea();
		String sql = String.format("insert into user(username,password,email,phone,youbian,pic,province,city,area) values('%s','%s','%s','%s','%s','%s','%s','%s','%s')", 
				username,password,email,phone,youbian,pic,province,city,area);
		System.out.println(sql);
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("插入成功！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null,st, conn);
		}

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConnection();
		int id = user.getId();
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		String phone = user.getPhone();
		String youbian = user.getYoubian();
		String pic = user.getPic();
		//UPDATE user SET `username`='1', `password`='1', `email`='1', `phone`='1', `youbian`='1', `pic`='1' WHERE `id`='17';

		String sql = "update user set username='"+username+"',password='"+password+"',email='"+email+"',phone='"+phone+
				"',youbian='"+youbian+"',pic='"+pic+"' where id="+id;
//		System.out.println(sql);
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("更新成功！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null,st, conn);
		}
		
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql = "SELECT * FROM user where 1=1";
		try {
			User user = new User();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				user = packUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return users;		
	}

	@Override
	public List<User> getUserList(int pageNow) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		int start = (pageNow-1)*5;
		String sql = "SELECT * FROM user where 1=1 and id limit "+start+",5";
		try {
			User user = new User();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				user = packUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return users;		
	}
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM user u WHERE u.id = "+id+";";
		try {
			ps = con.prepareStatement(sql);
			rs = query(ps,null);
			if(rs.next()){
				user = packUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return user;
	}
	
	public static User packUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setPassword(rs.getString(Constants.KEY_PASSWORD));
		user.setPhone(rs.getString(Constants.KEY_PHONE));
		user.setUsername(rs.getString(Constants.KEY_USERNAME));
		user.setEmail(rs.getString(Constants.KEY_EMAIL));
		user.setPic(rs.getString(Constants.KEY_PIC));
		user.setYoubian(rs.getString(Constants.KEY_YOUBIAN));
		return user;
	}

	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) pageCount FROM user u";
		int pageCount = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = query(ps,null);
			if(rs.next()){
				pageCount = rs.getInt("pageCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
//		System.out.println(pageCount);
		return pageCount;
	}


}
