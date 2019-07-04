/**
* @Title: ExpRoomUpdateServlet.java
* @Package com.sean.servlet
* @Description: TODO��ʵ�����޸�ģ���漰����Servlet������
* @author �ŷ���
* @date 2019.1.24
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

import com.sean.model.ExpRoom;


@WebServlet("/expRoom/update")
public class ExpRoomUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
		
		ExpRoomDao expRoomDao = new ExpRoomDao();
		
		ExpRoom mark=expRoomDao.getByEId(id);
		request.setAttribute("expRoom", mark);
		request.getRequestDispatcher("/Administrator/expRoom_update.jsp").forward(request, response);

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDStr = request.getParameter("erid");
		int id = Integer.parseInt(IDStr);
		
		String eraddress=request.getParameter("eraddress");
		String ertelnum=request.getParameter("ertelnum");
		//String erstate=request.getParameter("erstate");
		String superordinate=request.getParameter("superordinate");
		
		ExpRoom expRoom=new ExpRoom();
		
		expRoom.setEraddress(eraddress);
		expRoom.setErtelnum(ertelnum);
		expRoom.setSuperordinate(superordinate);
		//expRoom.setErstate(erstate);
		expRoom.setErid(id);
		ExpRoomDao expRoomDao = new ExpRoomDao();
		int mark=expRoomDao.update(expRoom);
		//������ʾ��Ϣ��ҳ�� 
		if(mark>0){
			request.setAttribute("info", "�޸ĳɹ�������");
		}else{
			request.setAttribute("info", "�޸�ʧ�ܣ�����");
		}
		//ת����ҳ��(�ض���)student_info��ʾ��Ϣ���ɹ�����ʧ��
		request.getRequestDispatcher("/Administrator/expRoom_info.jsp").forward(request, response);
	}

}
