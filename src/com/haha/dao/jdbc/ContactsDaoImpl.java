package com.haha.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haha.dao.ContactDao;
import com.haha.entity.Contact;
import com.mysql.jdbc.Statement;


public class ContactsDaoImpl extends JDBCBase implements ContactDao {

	@Override
	public Contact getContactByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(int user_id,Contact contact) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConnection();
		Statement st = null;
		
		String name = contact.getName();
		int sex = contact.getSex();
		int phone = contact.getPhone();
		String email = contact.getEmail();
		int youbian = contact.getYoubian();
		int qq = contact.getQq();
		String work_location = contact.getWork_location();
		String address = contact.getAddress();
		
		String sql = String.format("insert into contact(user_id,name,sex,phone,email,youbian,qq,work_location,address) values (%d,'%s',%d,%d,'%s',%d,%d,'%s','%s')",
				user_id,name,sex,phone,email,youbian,qq,work_location,address);
		System.out.println(sql);
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("插入消息成功！！！");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(null,st, conn);
		}
//		System.out.println(pageCount);
	}

	@Override
	public void update(int id,int user_id,Contact contact) {
		Connection conn = JDBCUtil.getConnection();
		Statement st = null;
		
		String name = contact.getName();
		int sex = contact.getSex();
		int phone = contact.getPhone();
		String email = contact.getEmail();
		int youbian = contact.getYoubian();
		int qq = contact.getQq();
		String work_location = contact.getWork_location();
		String address = contact.getAddress();
		
		String sql = String.format("update contact set name='%s',sex=%d,phone=%d,email='%s',youbian=%d,qq=%d,work_location='%s',address='%s' where id=%d and user_id=%d",
				name,sex,phone,email,youbian,qq,work_location,address,id,user_id);
		System.out.println(sql);
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("更新消息成功！！！");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(null,st, conn);
		}
	}

	@Override
	public Contact getContactById(int id,int user_id) {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql = String.format("select * from contact where id=%d and user_id=%d",id,user_id);
		System.out.println(sql);
		try {
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				contact = packContact(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return contact;
	}

	@Override
	public int getContactCount(int user_id) {
		// TODO Auto-generated method stub
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) pageCount FROM contact where user_id="+user_id;
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

	@Override
	public List<Contact> getContactList(int pageNow,int user_id,String name) {
		// TODO Auto-generated method stub
		List<Contact> contacts = new ArrayList<Contact>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		int start = (pageNow-1)*5;
		String sql = "select * from contact where user_id="+user_id+" and name like '%"+name+"%' and id limit "+start+",5";
		System.out.println(sql);
		try {
			Contact contact = new Contact();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				contact = packContact(rs);
				contacts.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return contacts;
	}

	@Override
	public List<Contact> getContactList(int pageNow,int user_id) {
		// TODO Auto-generated method stub
		List<Contact> contacts = new ArrayList<Contact>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		int start = (pageNow-1)*5;
		String sql = "SELECT * FROM contact where user_id = "+user_id+" and id limit "+start+",5";
//		System.out.println(sql);
		try {
			Contact contact = new Contact();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				contact = packContact(rs);
				contacts.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return contacts;
	}
	
	public static Contact packContact(ResultSet rs) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getInt("id"));
		contact.setUser_id(rs.getInt("user_id"));
		contact.setName(rs.getString("name"));
		contact.setSex(rs.getInt("sex"));
		contact.setPhone(rs.getInt("phone"));
		contact.setEmail(rs.getString("email"));
		contact.setYoubian(rs.getInt("youbian"));
		contact.setQq(rs.getInt("qq"));
		contact.setWork_location(rs.getString("work_location"));
		contact.setAddress(rs.getString("address"));
		return contact;
	}

	@Override
	public void delete(int id, int user_id) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConnection();
		Statement st = null;
		
		String sql = String.format("delete from contact where id=%d and user_id=%d",id,user_id);
		System.out.println(sql);
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("删除联系人成功！！！");		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(null,st, conn);
		}
		
	}

	@Override
	public int getContactSearchCount(int user_id, String name) {
		// TODO Auto-generated method stub
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql ="SELECT count(*) pageCount FROM contact where user_id="+user_id+" and name like '%"+name+"%'";
		System.out.println(sql);
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

	@Override
	public List<Contact> getContactListWithoutId(int id,int user_id) {
		// TODO Auto-generated method stub
		List<Contact> contacts = new ArrayList<Contact>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql = "SELECT * FROM contact where user_id = "+user_id+" and id<>"+id;
		System.out.println(sql);
		try {
			Contact contact = new Contact();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				contact = packContact(rs);
				contacts.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return contacts;
	}

}
