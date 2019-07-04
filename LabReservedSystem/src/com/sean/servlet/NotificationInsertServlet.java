/**
* @Title: NotificationInsertServlet.java
* @Package com.sean.servlet
* @Description: TODO����������ģ���漰����Servlet������
* @author �ŷ���
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
		System.out.println(formatStr);//2017-����-15 13:17:08:355
		
		notification.setPublictime(formatStr);

		Administrator a = (Administrator) request.getSession().getAttribute("administrator");
		String aname = a.getName();
		notification.setPublisher(aname);
		
		
		System.out.println(notification.toString());//���Ե������Ƿ����bug
		
		NotificationDao notificationDao = new NotificationDao();
		
		int mark=notificationDao.insert(notification);
		//������ʾ��Ϣ��ҳ��
		if(mark>0){
			request.setAttribute("info", "��ӳɹ�������");
		}else{
			request.setAttribute("info", "���ʧ�ܣ�����");
		}
		
		request.getRequestDispatcher("/Administrator/notification_info.jsp").forward(request, response);
	}

}
