package com.haha.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haha.dao.ContactDao;
import com.haha.dao.jdbc.ContactsDaoImpl;
import com.haha.entity.Contact;
import com.haha.entity.User;

/**
 * Servlet implementation class ContactSearchAction
 */
@WebServlet("/contactsearch.jhtml")
public class ContactSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ContactDao contactDao;
	private int pageCount = 0;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactSearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id = 0;
		HttpSession session = request.getSession();
		try {
			User user = (User) session.getAttribute("User");
			user_id = user.getId();
		}catch(Exception e){
			return;
		}
		int pageNow = 1;
		String page = request.getParameter("pageSearchNow");
		try {
			pageNow=Integer.valueOf(page);
		}catch(Exception e) {
			pageNow =1;
		}
		contactDao = new ContactsDaoImpl();
		// 通过用户名查询用户
		String name ="" ;
		try {
			name = request.getParameter("name");
		}catch(Exception e) {
			name = (String) session.getAttribute("searchName");
		}
		session.setAttribute("searchName", name);
		System.out.println("request,name:"+name);
		List<Contact> contacts = contactDao.getContactList(pageNow,user_id,name);
		pageCount = contactDao.getContactSearchCount(user_id, name) / 5 +1;
		request.getSession().setAttribute("searchs", contacts);
		request.setAttribute("pageSearchNow", pageNow);
		request.setAttribute("pageSearchCount", pageCount);
		System.out.println(contacts.size());
		request.getRequestDispatcher("/WEB-INF/pages/contactSearchList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
