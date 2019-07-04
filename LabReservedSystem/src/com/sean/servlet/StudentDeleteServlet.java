/**
* @Title: StudentDeleteServlet.java
* @Package com.sean.servlet
* @Description: TODO（学生删除模块涉及到的Servlet操作）
* @author 张芳菲
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
		
		
		//然后在servlet层调用service逻辑处理层
		StudentDao studentDao = new StudentDao();
		//调用service逻辑处理层的插入方法,返回布尔类型
		int mark=studentDao.delete(id);
		//返回提示信息到页面
		if(mark>0){
			request.setAttribute("info", "删除成功！！！");
		}else{
			request.setAttribute("info", "删除失败！！！");
		}
		//转发到页面(重定向)student_info.jsp提示信息，成功或者失败
		request.getRequestDispatcher("/Administrator/student_info.jsp").forward(request, response);
	}

}
