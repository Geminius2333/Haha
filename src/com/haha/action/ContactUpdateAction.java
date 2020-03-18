package com.haha.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haha.dao.jdbc.ContactsDaoImpl;
import com.haha.entity.Contact;
import com.haha.entity.User;

/**
 * Servlet implementation class ContactUpdateAction
 */
@WebServlet("/contactupdate.jhtml")
public class ContactUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactsDaoImpl contactDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int user_id;
		int id;
		try {
			User user = (User) session.getAttribute("User");
			user_id = user.getId();
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e){
			return;
		}
		contactDao = new ContactsDaoImpl();
		String name = request.getParameter("name");
		String str_sex = request.getParameter("sex");
		String str_phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String str_youbian = request.getParameter("youbian");
		String str_qq = request.getParameter("qq");
		String work_location = request.getParameter("work_location");
		String address = request.getParameter("address");
		int sex = 0,phone = 0,youbian = 0,qq = 0;
//		System.out.println(name+" "+sex+" "+phone+" "+qq);
		try {
		    sex  = Integer.parseInt(str_sex);
		    phone = Integer.parseInt(str_phone);
		    youbian = Integer.parseInt(str_youbian);
		    qq = Integer.parseInt(str_qq);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		Contact contact = new Contact(user_id,name,sex,phone,email,youbian,qq,work_location,address);
		
		contactDao.update(id,user_id, contact);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
