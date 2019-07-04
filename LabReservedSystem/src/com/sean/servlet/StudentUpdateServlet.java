/**
* @Title: StudentUpdateServlet.java
* @Package com.sean.servlet
* @Description: TODO（学生修改模块涉及到的Servlet操作）
* @author 张芳菲
* @date 2019.2.9
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
import com.sean.dao.TeacherDao;
import com.sean.model.Student;
import com.sean.model.Teacher;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
		StudentDao studentDao = new StudentDao();
		
		Student mark=studentDao.getById(id);
		request.setAttribute("student", mark);
		request.getRequestDispatcher("/Administrator/student_update.jsp").forward(request, response);

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDStr = request.getParameter("ID");
		int id = Integer.parseInt(IDStr);
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
		student.setID(id);
		
		StudentDao studentDao = new StudentDao();
		int mark=studentDao.update(student);
		//返回提示信息到页面
		if(mark>0){
			request.setAttribute("info", "修改成功！！！");
		}else{
			request.setAttribute("info", "修改失败！！！");
		}
		//转发到页面(重定向)student_info提示信息，成功或者失败
		request.getRequestDispatcher("/Administrator/student_info.jsp").forward(request, response);
	}

}
