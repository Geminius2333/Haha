package com.haha.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haha.dao.jdbc.MessageDaoImpl;
import com.haha.entity.Message;
import com.haha.entity.User;

/**
 * Servlet implementation class MessageListAction
 */
@WebServlet("/messagelist.jhtml")
public class MessageListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageDaoImpl messageDao;
	private int pageCount =0;
    private String username = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		username = request.getParameter("username");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if(user == null)
			return;
		username = user.getUsername();
		System.out.println(username);
		int pageNow = 1;
		String page = request.getParameter("pageNow");
		try {
			pageNow=Integer.valueOf(page);
		}catch(Exception e) {
			pageNow =1;
		}
		messageDao = new MessageDaoImpl();	
		List<Message> messages = messageDao.getMessageList(pageNow,username);
		pageCount = messageDao.getMessageCount(username) / 5 +1;
		request.setAttribute("messages", messages);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);
//		System.out.println(messages.size());
		request.getRequestDispatcher("/WEB-INF/pages/messageList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
