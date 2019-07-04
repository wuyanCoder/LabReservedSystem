/**
* @Title: NotificationDeleteServlet.java
* @Package com.sean.servlet
* @Description: TODO（公告删除模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.1.26
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sean.dao.NotificationDao;



@WebServlet("/notification/delete")
public class NotificationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDStr = request.getParameter("nid");

		int nid = Integer.parseInt(IDStr);
		NotificationDao notificationDao = new NotificationDao();
	
		int mark=notificationDao.delete(nid);
	
		if(mark>0){
			request.setAttribute("info", "删除成功！！！");
		}else{
			request.setAttribute("info", "删除失败！！！");
		}
		request.getRequestDispatcher("/Notification/notification_info.jsp").forward(request, response);
		//request.getRequestDispatcher("showMessage.jsp").forward(request, response);
	}

}
