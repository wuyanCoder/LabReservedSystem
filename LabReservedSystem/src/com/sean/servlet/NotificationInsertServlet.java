/**
* @Title: NotificationInsertServlet.java
* @Package com.sean.servlet
* @Description: TODO（公告增加模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.1.26
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sean.dao.NotificationDao;
import com.sean.model.Administrator;
import com.sean.model.Notification;

@WebServlet("/notification/insert")
public class NotificationInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//String publictime=request.getParameter("publictime");
		
		Notification notification = new Notification();
		notification.setTitle(title);
		notification.setContent(content);
		//notification.setPublictime(publictime);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr =formatter.format(new Date());
		System.out.println(formatStr);//2017-九月-15 13:17:08:355
		
		notification.setPublictime(formatStr);

		Administrator a = (Administrator) request.getSession().getAttribute("administrator");
		String aname = a.getName();
		notification.setPublisher(aname);
		
		
		System.out.println(notification.toString());//测试到这里是否出现bug
		
		NotificationDao notificationDao = new NotificationDao();
		
		int mark=notificationDao.insert(notification);
		//返回提示信息到页面
		if(mark>0){
			request.setAttribute("info", "添加成功！！！");
		}else{
			request.setAttribute("info", "添加失败！！！");
		}
		
		request.getRequestDispatcher("/Administrator/notification_info.jsp").forward(request, response);
	}

}
