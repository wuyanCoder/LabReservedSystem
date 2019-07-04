/**
* @Title: TeacherDeleteServlet.java
* @Package com.sean.servlet
* @Description: TODO（教师删除模块涉及到的Servlet操作）
* @author 张芳菲
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
			request.setAttribute("info", "删除成功！！！");
		}else{
			request.setAttribute("info", "删除失败！！！");
		}
		//转发到页面(重定向)book_info.jsp提示信息，成功或者失败
		request.getRequestDispatcher("/Administrator/tea_info.jsp").forward(request, response);
	}

}
