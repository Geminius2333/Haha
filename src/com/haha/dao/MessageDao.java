package com.haha.dao;

import java.util.List;

import com.haha.entity.Message;

public interface MessageDao {

	Message getMessageByCondition(String condition);
	
	void save(Message message);

	/**
	 * 更新或保存用户对象
	 * @param Message
	 */
	void update(Message message);

	/**
	 * 通过用户id查找用户对象
	 * @param id 用户id
	 * @return 用户对象
	 */
	void delete(int id);
	Message getMessageById(int id);
	int getMessageCount(String username);
	List<Message> getMessageList();
	void updateIsread(int id);
	List<Message> getMessageList(int pageNow,String username);
}
