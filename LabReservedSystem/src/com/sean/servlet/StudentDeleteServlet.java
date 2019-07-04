/**
* @Title: StudentDeleteServlet.java
* @Package com.sean.servlet
* @Description: TODO��ѧ��ɾ��ģ���漰����Servlet������
* @author �ŷ���
* @date 2019.2.3
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sean.dao.StudentDao;


@WebServlet("/student/delete")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
		
		
		//Ȼ����servlet�����service�߼������
		StudentDao studentDao = new StudentDao();
		//����service�߼������Ĳ��뷽��,���ز�������
		int mark=studentDao.delete(id);
		//������ʾ��Ϣ��ҳ��
		if(mark>0){
			request.setAttribute("info", "ɾ���ɹ�������");
		}else{
			request.setAttribute("info", "ɾ��ʧ�ܣ�����");
		}
		//ת����ҳ��(�ض���)student_info.jsp��ʾ��Ϣ���ɹ�����ʧ��
		request.getRequestDispatcher("/Administrator/student_info.jsp").forward(request, response);
	}

}
