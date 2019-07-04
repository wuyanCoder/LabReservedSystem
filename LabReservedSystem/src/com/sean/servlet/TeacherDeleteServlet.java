/**
* @Title: TeacherDeleteServlet.java
* @Package com.sean.servlet
* @Description: TODO����ʦɾ��ģ���漰����Servlet������
* @author �ŷ���
* @date 2019.2.10
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sean.dao.TeacherDao;
import com.sean.model.Teacher;

@WebServlet("/tea/delete")
public class TeacherDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
		
		TeacherDao teacherDao = new TeacherDao();
	
		int mark=teacherDao.delete(id);
		
		if(mark>0){
			request.setAttribute("info", "ɾ���ɹ�������");
		}else{
			request.setAttribute("info", "ɾ��ʧ�ܣ�����");
		}
		//ת����ҳ��(�ض���)book_info.jsp��ʾ��Ϣ���ɹ�����ʧ��
		request.getRequestDispatcher("/Administrator/tea_info.jsp").forward(request, response);
	}

}
