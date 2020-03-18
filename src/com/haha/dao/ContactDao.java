package com.haha.dao;

import java.util.List;

import com.haha.entity.Contact;

public interface ContactDao {
	/**
	 * 通过邮箱或者电话号码,用户名查询Contact
	 * 
	 * @param condition
	 *            查询关键字
	 * @return 返回Contact对象
	 */
	Contact getContactByCondition(String condition);
	
	/**
	 * 将用户对象存储到数据库
	 * 注册时只需要填写手机号或者邮箱地址
	 * 密码是默认的123456
	 * 所以只需插入这三个字段
	 * @param Contact
	 */
	void save(int user_id,Contact contact);

	/**
	 * 更新或保存用户对象
	 * @param Contact
	 */
	void update(int id,int user_id,Contact contact);
	void delete(int id,int user_id);
	/**
	 * 通过用户id查找用户对象
	 * @param id 用户id
	 * @return 用户对象
	 */
	Contact getContactById(int id,int user_Id);
	int getContactCount(int user_id);
	int getContactSearchCount(int user_id,String name);
	List<Contact> getContactList(int pageNow,int user_id,String name);
	List<Contact> getContactList(int pageNow,int user_id);
	List<Contact> getContactListWithoutId(int id,int user_id);
}
