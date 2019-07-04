/**
* @Title: TeacherUpdateServlet.java
* @Package com.sean.servlet
* @Description: TODO（教师修改模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.2.13
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

@WebServlet("/tea/update")
public class TeacherUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
		TeacherDao teacherDao = new TeacherDao();
		
		Teacher mark=teacherDao.getById(id);
		request.setAttribute("tea", mark);
		request.getRequestDispatcher("/Administrator/tea_update.jsp").forward(request, response);

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
		String teacherNumber = request.getParameter("teacherNumber");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String phoneNumber = request.getParameter("phoneNumber");
		String department = request.getParameter("department");
		Teacher tea = new Teacher();
		tea.setTeacherNumber(teacherNumber);
		tea.setPassword(password);
		tea.setName(name);
		tea.setSex(sex);
		tea.setPhoneNumber(phoneNumber);
		tea.setDepartment(department);
		tea.setID(id);
		
		
		TeacherDao teacherDao = new TeacherDao();
		
		int mark=teacherDao.update(tea);
		System.out.println("================333333"+mark);
	
		if(mark>0){
			request.setAttribute("info", "修改成功！！！");
		}else{
			request.setAttribute("info", "修改失败！！！");
		}
		
		request.getRequestDispatcher("/Administrator/tea_info.jsp").forward(request, response);
	}

}
