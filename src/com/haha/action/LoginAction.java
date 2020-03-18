package com.haha.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.haha.entity.User;
import com.haha.globle.Constants;
import com.haha.dao.UserDao;
import com.haha.dao.jdbc.UserDaoImpl;

/**
 * Servlet implementation class LoginAction
 */

public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 登录失败跳转的url
	private static String ERROR_URL="/login.jsp";
	// 登陆成功跳转的url
	private static String OK_URL="/WEB-INF/pages/welcome.jsp";
	private UserDao userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public LoginAction() {
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

		// 如果用户名、密码或者验证码为空则跳转回登录页面
		if (condition == null || password == null || code == null || code.equals("")) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		// session域中拿到当前正确的验证码
		String right_code = (String) request.getSession().getAttribute(Constants.CHECK_NUMBER_NAME);

		// 对验证码的正确性进行验证
		if (!checkCode(right_code, code)) {
			request.setAttribute(Constants.ERROR, "验证码不正确");
			forword(request, response, true, null);
//			System.out.println("验证码不正确");
			return;
		}
		userDao = new UserDaoImpl();
		// 通过用户名查询用户
		User user = userDao.getUserByCondition(condition);
		// 验证用户名和密码是否正确
		if (user == null || !user.getPassword().equals(password)) {
			request.setAttribute(Constants.ERROR, "用户名或密码错误！");
			forword(request, response, true, user);
//			System.out.println("用户名或密码错误");
			return;
		}
		ArrayList<User> users = (ArrayList<User>) userDao.getUserList();
//		request.getSession().setAttribute("persons",users);
		// 登陆成功，进行跳转
		forword(request, response, false, user);
//		System.out.println("登录成功！");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void forword(HttpServletRequest request, HttpServletResponse response, boolean isError, User user)
			throws ServletException, IOException {

		// 获取session中的正确跳转后的URL 如果跳转前有存储页面链接，则跳转到登陆前的页面 如果没有，则跳转到首页
		String okUrl = getUrl(Constants.OK_URL, request);
		// 暂未实现错误链接跳转 后续可以作为不同错误跳转页面的伏笔
		String errUrl = getUrl(Constants.ERR_URL, request);

		if (okUrl == null)
			okUrl = "/" + OK_URL;

		if (errUrl == null)
			errUrl = ERROR_URL;

		if (isError)
			request.getRequestDispatcher(errUrl).forward(request, response);
		else
			/**
			 * 跳转的首页需要通过action获取数据后才能跳转，本来需要用到服务端跳转
			 * 这里只学到重定向，故需要手动添加项目前缀
			 */
			request.getSession().setAttribute("User",user);
			request.getRequestDispatcher(okUrl).forward(request, response);
	}

	/**
	 * 尝试从请求中获取对应的URL值
	 * 
	 * @param key
	 *            存储URL的键值
	 * @param request
	 *            请求对象
	 * @return 获取到的URL地址
	 */
	private String getUrl(String key, HttpServletRequest request) {

		String url = request.getParameter(key);

		if (url != null && !"".equals(url.trim())) {
			request.setAttribute(key, url);
			return url;
		}

		url = (String) request.getAttribute(key);
		if (url != null && !"".equals(url.trim()))
			return url;

		url = (String) request.getSession().getAttribute(key);
		if (url != null && !"".equals(url.trim())) {
			request.getSession().removeAttribute(key);
			return url;
		}

		return url;
	}
	public boolean checkCode(String right_code, String code) {
		if (right_code == null || "".equals(right_code))
			return false;
		right_code = right_code.toUpperCase();
		code = code.toUpperCase();

		if (right_code.equals(code))
			return true;
		else
			return false;

	}
}
