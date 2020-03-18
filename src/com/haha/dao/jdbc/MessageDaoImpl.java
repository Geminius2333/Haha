package com.haha.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haha.dao.MessageDao;
import com.haha.entity.Message;
import com.haha.globle.Constants;
import com.mysql.jdbc.Statement;

public class MessageDaoImpl extends JDBCBase implements MessageDao {

	@Override
	public Message getMessageByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Message message) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConnection();
		String sender = message.getSender();
		String content = message.getContent();
		String reciever = message.getReciever();
		int isread = message.getIsread();
		String upfile = message.getUpfile();
		String sql = String.format("insert into message(sender,reciever,content,isread,upfile) values('%s','%s','%s',%d,'%s')", 
				sender,reciever,content,isread,upfile);
		System.out.println(sql);
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("插入消息成功！！！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null,st, conn);
		}
	}

	@Override
	public void update(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public Message getMessageById(int id) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql = String.format("select * from message where id=%d",id);
		System.out.println(sql);
		try {
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				msg = packMessage(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return msg;
	}

	@Override
	public int getMessageCount(String username) {
		// TODO Auto-generated method stub
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) pageCount FROM message where reciever='"+username+"'";
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
	public List<Message> getMessageList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessageList(int pageNow,String username) {
		// TODO Auto-generated method stub
		List<Message> messages = new ArrayList<Message>();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;	
		int start = (pageNow-1)*5;
		String sql = "SELECT * FROM message where reciever='"+username+"' and id limit "+start+",5";
		System.out.println(sql);
		try {
			Message message = new Message();
			ps = con.prepareStatement(sql);
			rs = query(ps, null);
			while(rs.next()){
				message = packMessage(rs);
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, con);
		}
		return messages;	
	}
	
	public static Message packMessage(ResultSet rs) throws SQLException {
		Message message =new Message();
		message.setId(rs.getInt("id"));
		message.setSender(rs.getString(Constants.KEY_SENDER));
		message.setReciever(rs.getString(Constants.KEY_RECIEVER));
		message.setContent(rs.getString(Constants.KEY_CONTENT));
		message.setIsread(rs.getInt(Constants.KEY_ISREAD));
		message.setUpfile(rs.getString("upfile"));
		return message;
	}

	@Override
	public void updateIsread(int id) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConnection();
		String sql = String.format("update message set isread=1 where id="+id);
		System.out.println(sql);
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("已读");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null,st, conn);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConnection();
		String sql = String.format("delete from message where id="+id);
		System.out.println(sql);
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			int num = st.executeUpdate(sql);
			if(num>0)
				   System.out.println("删除");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null,st, conn);
		}
	}
}
