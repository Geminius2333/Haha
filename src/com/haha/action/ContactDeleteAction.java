package com.haha.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haha.dao.ContactDao;
import com.haha.dao.jdbc.ContactsDaoImpl;
import com.haha.entity.User;

/**
 * Servlet implementation class ContactDeleteAction
 */
@WebServlet("/contactdelete.jhtml")
public class ContactDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ContactDao contactDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactDeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=0;
		int user_id = 0;
		HttpSession session = request.getSession();
		try {
			id = Integer.parseInt(request.getParameter("id"));
			User user = (User)session.getAttribute("User");
			user_id = user.getId();
		}catch(Exception e) {
			return;
		}
		contactDao = new ContactsDaoImpl();
		System.out.println("id:"+id+"user_id:"+user_id);
		contactDao.delete(id, user_id);
		request.getRequestDispatcher("/contactslist.jhtml").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
