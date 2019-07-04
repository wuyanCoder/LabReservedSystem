/**
* @Title: ExpRoomDeleteServlet.java
* @Package com.sean.servlet
* @Description: TODO��ʵ����ɾ��ģ���漰����Servlet������
* @author �ŷ���
* @date 2019.1.20
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sean.dao.ExpRoomDao;
import com.sean.dao.StudentDao;


@WebServlet("/expRoom/delete")
public class ExpRoomDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
	
		ExpRoomDao expRoomDao = new ExpRoomDao();
		
		int mark=expRoomDao.delete(id);
	
		if(mark>0){
			request.setAttribute("info", "ɾ���ɹ�������");
		}else{
			request.setAttribute("info", "ɾ��ʧ�ܣ�����");
		}
		
		request.getRequestDispatcher("/Administrator/expRoom_info.jsp").forward(request, response);
	}

}
