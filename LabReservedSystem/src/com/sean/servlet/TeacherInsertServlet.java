/**
* @Title: TeacherInsertServlet.java
* @Package com.sean.servlet
* @Description: TODO（教师增加模块涉及到的Servlet操作）
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

@WebServlet("/tea/insert")
public class TeacherInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		System.out.println(tea);
		
		TeacherDao teacherDao = new TeacherDao();
		
		int mark=teacherDao.insert(tea);
		System.out.println("================"+mark);
		
		if(mark>0){
			request.setAttribute("info", "添加成功！！！");
		}else{
			request.setAttribute("info", "添加失败！！！");
		}
		
		request.getRequestDispatcher("/Administrator/tea_info.jsp").forward(request, response);
	}

}
