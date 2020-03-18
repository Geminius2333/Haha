package com.haha.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.haha.dao.UserDao;
import com.haha.dao.jdbc.UserDaoImpl;
import com.haha.entity.User;
import com.haha.globle.Constants;

/**
 * Servlet implementation class RegisterAction
 */
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 登录失败跳转的url
	private static String ERROR_URL="/register.jsp";
	// 登陆成功跳转的url
	private static String OK_URL="/login.jsp";
	private UserDao userdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAction() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 用户提交的手机或邮箱
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
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		test(username,password,confirm_password,email,phone,youbian);
		// 如果用户名、密码或者验证码为空则跳转回登录页面
		if (username == null || password == null || confirm_password == null) {
			request.getRequestDispatcher(ERROR_URL).forward(request, response);
			return;
		}
		
		//判断密码
		if(!password.equals(confirm_password)) {
			request.getRequestDispatcher(ERROR_URL).forward(request, response);
			return;
		}
		//用户存在判断
		userdao = new UserDaoImpl();
		User user_temp = userdao.getUserByCondition(username);

		if(user_temp != null) {
			request.setAttribute(Constants.ERROR, "用户已存在！");
			forword(request, response, true, null);
		}
		//更新数据库
		User user = new User(username,password,email,phone,youbian,"",province,city,area);
		userdao.save(user);
		// 注册成功，进行跳转
		forword(request, response, false, null);
	}

	private void test(String username, String password, String confirm_password, String email, String phone,
			String youbian) {
		// TODO Auto-generated method stub
//		String str = " ";
		System.out.println(username);
		System.out.println(password);
		System.out.println(confirm_password);
		System.out.println(email);
		System.out.println(phone);
		System.out.println(youbian);
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
			response.sendRedirect(this.getServletContext().getContextPath() + okUrl);
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
}
