/**
* @Title: StudentSelectServlet.java
* @Package com.sean.servlet
* @Description: TODO（学生列表显示模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.2.7
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

import com.sean.dao.StudentDao;

import com.sean.model.Student;


@WebServlet("/student/select")
public class StudentSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		StudentDao studentDao = new StudentDao();
		
		ArrayList<Student> mark=studentDao.getStudentList();
		request.setAttribute("list", mark);
		
		request.getRequestDispatcher("/Administrator/student_list.jsp").forward(request, response);
	}

}
