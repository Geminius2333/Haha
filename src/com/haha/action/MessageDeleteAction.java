package com.haha.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haha.dao.MessageDao;
import com.haha.dao.jdbc.ContactsDaoImpl;
import com.haha.dao.jdbc.MessageDaoImpl;
import com.haha.entity.User;

/**
 * Servlet implementation class MessageDeleteAction
 */
@WebServlet("/messagedelete.jhtml")
public class MessageDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MessageDao messageDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(Exception e) {
			return;
		}
		messageDao = new MessageDaoImpl();
		messageDao.delete(id);
		request.getRequestDispatcher("/messagelist.jhtml").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
