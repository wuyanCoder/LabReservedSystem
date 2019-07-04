/**
* @Title: ExpRoomSelectServlet.java
* @Package com.sean.servlet
* @Description: TODO（实验室列表显示模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.1.24
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sean.dao.ExpRoomDao;
import com.sean.dao.StudentDao;
import com.sean.model.ExpRoom;
import com.sean.model.Student;


@WebServlet("/expRoom/select")
public class ExpRoomSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		ExpRoomDao expRoomDao = new ExpRoomDao();
		
		ArrayList<ExpRoom> mark=expRoomDao.getExpRoomList();
		request.setAttribute("list", mark);
		
		request.getRequestDispatcher("/Administrator/expRoom_list.jsp").forward(request, response);
	}

}
