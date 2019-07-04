/**
* @Title: ExpRoomInsertServlet.java
* @Package com.sean.servlet
* @Description: TODO（实验室增加模块涉及到的Servlet操作）
* @author 张芳菲
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



@WebServlet("/expRoom/insert")
public class ExpRoomInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String eraddress=request.getParameter("eraddress");
		
		String ertelnum=request.getParameter("ertelnum");
		
		//String erstate=request.getParameter("erstate");
		String superordinate=request.getParameter("superordinate");
		
		ExpRoom expRoom=new ExpRoom();
		
		expRoom.setEraddress(eraddress);
		expRoom.setErtelnum(ertelnum);
		expRoom.setSuperordinate(superordinate);
		//expRoom.setErstate(erstate);
		System.out.println(expRoom);
		ExpRoomDao expRoomDao = new ExpRoomDao();
	
		int mark=expRoomDao.insert(expRoom);
		System.out.print(mark);
		if(mark>0){
			request.setAttribute("info", "添加成功！！！");
		}else{
			request.setAttribute("info", "添加失败！！！");
		}
		
		request.getRequestDispatcher("/Administrator/expRoom_info.jsp").forward(request, response);
	}

}
