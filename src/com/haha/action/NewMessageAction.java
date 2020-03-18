package com.haha.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.support.SessionAttributeStore;

import com.haha.dao.MessageDao;
import com.haha.dao.jdbc.MessageDaoImpl;
import com.haha.dao.jdbc.UserDaoImpl;
import com.haha.entity.Message;
import com.haha.entity.User;

/**
 * Servlet implementation class PicUpdate
 */
@WebServlet("/newmessage.jhtml")
public class NewMessageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "upfile/";
	private UserDaoImpl userDao;
	private MessageDao messageDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMessageAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        /****** 初始化部分 ******/
        //设置编码格式，前端后台统一是utf-8
        request.setCharacterEncoding("utf-8");

        /****** 文件路径部分 用的是项目发布的相对路径******/
        // Servlet上下文对象
        ServletContext servletContext = this.getServletConfig().getServletContext();
        // tomcat的项目路径，记住要加斜杠

        /****** 读写部分  ******/
        FileItemFactory factory = new DiskFileItemFactory();
		 
        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 开始解析请求信息
        List items = null;
        try {
            items = upload.parseRequest(request);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }

        // 对所有请求信息进行判断
        Iterator iter = items.iterator();
        System.out.println(iter.hasNext());
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            // 信息为普通的格式
            if (item.isFormField()) {
                String fieldName = item.getFieldName();              
                String value = item.getString();
                request.setAttribute(fieldName, value);
            }
            // 信息为文件格式
            else {
            	
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间
                
                String temp = item.getName();
                int index1 = temp.indexOf(".");
                String fileName = date+temp.substring(index1);
                System.out.println(fileName);
                String realPath = servletContext.getRealPath(UPLOAD_DIR);//保存的路径
                System.out.println(realPath);
                String filePath = realPath+fileName;//合起来就是完整的文件路径了
                System.out.println(filePath);
                File realPathFile = new File(realPath);
                if (!realPathFile.exists()) {
                    realPathFile.mkdirs();
                }
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
                request.setAttribute("realFileName", fileName);
                File file = new File(realPath, fileName);
                try {
                    item.write(file);
                    System.out.println("成功");
                    SaveMessage(request,response,fileName);
            		
                }
                catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/Haha/newMessage.jhtml");
                }
            }
        }		       
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void SaveMessage(HttpServletRequest request, HttpServletResponse response,String fileName) throws ServletException, IOException {
		MessageDao msdao = new MessageDaoImpl();
		String reciever =(String)request.getAttribute("reciever");
		String content = (String)request.getAttribute("content");
		System.out.println(content + reciever);
		Message msg = new Message();
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("User");
		msg.setSender(user.getUsername());
		msg.setContent(content);
		msg.setReciever(reciever);
		msg.setUpfile(fileName);
		msdao.save(msg);
		request.getRequestDispatcher("/messagelist.jhtml").forward(request, response);
	}
}
