package com.haha.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haha.dao.jdbc.UserDaoImpl;
import com.haha.entity.User;
import com.haha.globle.Constants;

/**
 * Servlet implementation class UserlistAction
 */
public class UserListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private int pageCount = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 用户提交的手机或邮箱
 		String condition = request.getParameter("name");
		// 用户的个人密码
		String password = request.getParameter("password");
		// 用户输入的验证码
		String code = request.getParameter("code");
		//
		int pageNow = 1;
		String page = request.getParameter("pageNow");
		try {
			pageNow=Integer.valueOf(page);
		}catch(Exception e) {
			pageNow =1;
		}
		userDao = new UserDaoImpl();
		// 通过用户名查询用户
		
		List<User> users = userDao.getUserList(pageNow);
		pageCount = userDao.getUserCount() / 5 +1;
		request.setAttribute("persons", users);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);
//		System.out.println(users.size());
		request.getRequestDispatcher("/WEB-INF/pages/contactsList.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
