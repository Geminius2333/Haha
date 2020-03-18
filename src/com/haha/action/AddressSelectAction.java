package com.haha.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haha.dao.AddressDao;
import com.haha.dao.jdbc.AddressDaoImpl;
import com.haha.entity.Area;
import com.haha.entity.City;
import com.haha.entity.Province;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddressSelectAction
 */
@WebServlet("/AddressSelectAction")
public class AddressSelectAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filename;
	private AddressDao addressDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressSelectAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		addressDao = new AddressDaoImpl();
        if ("provinces".equals(request.getParameter("type"))){
            List<Province> all = addressDao.getProvinces();

            JSONObject json = new JSONObject();
            for (Province p : all){
                json.put(p.getName(), p.getProvince_id());
            }
            System.out.println(json.toString());
            response.getWriter().write(json.toString());
        }

        if ("cities".equals(request.getParameter("type"))){
            String id = request.getParameter("id");
            List<City> all = addressDao.getCities(id);
            JSONObject json = new JSONObject();
            for (City c : all){
                json.put(c.getName(), c.getCity_id());
            }
            System.out.println(json.toString());
            response.getWriter().write(json.toString());
        }

        if ("areas".equals(request.getParameter("type"))){
            String id = request.getParameter("id");
            List<Area> all = addressDao.getAreas(id);
            JSONObject json = new JSONObject();
            for (Area c : all){
                json.put(c.getName(), c.getArea_id());
            }
            System.out.println(json.toString());
            response.getWriter().write(json.toString());
        }

    }
		    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
