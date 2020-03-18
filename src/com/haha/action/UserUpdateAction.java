package com.haha.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haha.dao.UserDao;
import com.haha.dao.jdbc.UserDaoImpl;
import com.haha.entity.User;
import com.haha.globle.Constants;

/**
 * Servlet implementation class UserUpdateAction
 */
@WebServlet("/userupdate.jhtml")
public class UserUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userdao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("name");
		// 用户的个人密码
		String password = request.getParameter("password");
		// 用户输入的确认密码
		String confirm_password = request.getParameter("confirm_password");
		//用户输入的邮箱
		String email = request.getParameter("email");
		//用户输入的电话
		String phone = request.getParameter("phone");
		//用户输入的邮编
		String youbian = request.getParameter("youbian");
		
//		test(username,password,confirm_password,email,phone,youbian);
		// 如果用户名、密码或者验证码为空则跳转回登录页面
		//更新数据库
		User user = (User)request.getSession().getAttribute("User");
		if(user == null)
			return;
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		user.setPhone(phone);
		user.setYoubian(youbian);
		userdao = new UserDaoImpl();
		userdao.update(user);
		// 注册成功，进行跳转
		request.getRequestDispatcher("/WEB-INF/pages/userDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
