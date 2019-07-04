/**
* @Title: StudentInsertServlet.java
* @Package com.sean.servlet
* @Description: TODO（学生增加模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.2.4
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
import com.sean.model.Student;


@WebServlet("/student/insert")
public class StudentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String studentNumber = request.getParameter("studentNumber");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String department = request.getParameter("department");
		String s_class = request.getParameter("s_class");
		String telnum=request.getParameter("telnum");
		
		Student student=new Student();
		student.setStudentNumber(studentNumber);
		student.setPassword(password);
		student.setName(name);
		student.setSex(sex);
		student.setDepartment(department);
		student.setS_class(s_class);
		student.setTelnum(telnum);
		
		
		
		System.out.println(student);
		StudentDao studentDao = new StudentDao();
	
		int mark=studentDao.insert(student);
		
		if(mark>0){
			request.setAttribute("info", "添加成功！！！");
		}else{
			request.setAttribute("info", "添加失败！！！");
		}
		
		request.getRequestDispatcher("/Administrator/student_info.jsp").forward(request, response);
	}

}
