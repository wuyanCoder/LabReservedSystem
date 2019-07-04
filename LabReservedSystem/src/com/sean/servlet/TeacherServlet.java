/**
* @Title: TeacherServlet.java
* @Package com.sean.servlet
* @Description: TODO（教师模块涉及到的Servlet操作）
* @author wsl，sean
* @date 2018.9.20
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sean.dao.ExpCourseDao;
import com.sean.dao.TeacherDao;
import com.sean.model.ExpApplication;
import com.sean.model.ExpCourse;
import com.sean.model.Student;
import com.sean.model.Teacher;
import com.sean.tools.ConnDB;
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDao teacherDao = null;
	public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
       teacherDao=new TeacherDao();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			login(request, response); 
		} 
		else if ("exit".equals(action)) {
			exit(request, response); 
		} 
		else if("show".equals(action)){
			show(request,response);
		}
		else if("change_password".equals(action)){
			change_password(request, response);
		}
		else if("ask_applied".equals(action)){
			ask_applied(request, response);
		}
		else if("check_student_application".equals(action)){
			check_student_application(request, response);
		}
		else if("consent_application".equals(action)){
			consent_application(request, response);
		}
		else if("deny_application".equals(action)){
			deny_application(request, response);
		}
		else if("check_expcourse".equals(action)){
			check_expcourse(request, response);
		}
		else if("edit_grade".equals(action)){
			edit_grade(request, response);
		}	
	}
	/**
	 * 功能：用户登陆
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Teacher teacher=new Teacher(); 
		teacher.setTeacherNumber(request.getParameter("teacherNumber")) ;// 获取并设置用户名
		teacher.setPassword(request.getParameter("password")); // 获取并设置密码
		int r=teacherDao.login(teacher);
		if (r > 0) {// 当用户登录成功时
			HttpSession session = request.getSession();
		//	session.setAttribute("teacherNumber", teacher.getTeacherNumber());// 保存用户名
		//	session.setAttribute("sid", r);// 保存用户ID
			session.setAttribute("sessionKey", r);// 保存sessionKey
			session.setAttribute("teacher", teacher);//直接保存用户实例			
			session.setAttribute("last_ip", teacher.getLastip());
			session.setAttribute("last_date", teacher.getLastdate());
			teacherDao.ip_date(teacher.getID(),request);			
			request.setAttribute("returnValue", "登录成功！");// 保存提示信息		
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// 重定向页面
		} else {// 当用户登录不成功时
			request.setAttribute("returnValue", "您输入的用户名或密码错误，请重新输入！");
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// 重定向页面
		}
	}
	/**
	 * 功能：用户退出
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();// 获取HttpSession的对象
		session.invalidate();// 销毁session
		request.getRequestDispatcher("/index.jsp").forward(request,
				response);// 重定向页面
	}
	private void change_password(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();// 获取HttpSession的对象
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}
		int tid=(int)session.getAttribute("sessionKey");
		String old_password=request.getParameter("old_password");
		String new_password=request.getParameter("new_password");
		if(teacherDao.changePassword(tid,old_password,new_password)==1){
			session.invalidate();// 销毁session
			request.setAttribute("returnValue", "修改成功！");// 保存提示信息
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// 重定向页面			
		}
		else{
			request.setAttribute("returnValue", "原密码错误，修改失败！");// 保存提示信息
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// 重定向页面
		}	
	}	
	
	/**
	 * 功能：显示教师信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Teacher teacher=new Teacher();
		String teacherNumber= request.getParameter("teacherNumber");// 获取HttpSession的对象		
		teacher=teacherDao.show(teacherNumber);
		session.setAttribute("teacher", teacher);//直接保存用户实例
		String note="";
		System.out.print("teacher");
		if(teacher!=null){
			note+="<div class='cl pd-20' style=' background-color:#5bacb6'>";
			note+="<img class='avatar size-XL l' src='../static/h-ui/images/ucnter/avatar-default.jpg'>";
			note+="<dl style='margin-left:80px; color:#fff'>";
			note+="<dt><span class='f-18'>"+teacher.getName()+"</span>	</dt>	</dl> 	</div>";			
			note+="<div class='pd-20'><table class='table'><tbody>";		
			note+="<tr><th class='text-r' width='80'>学号：</th><td>"+teacher.getTeacherNumber()+"	</td></tr>";				
			note+="<tr><th class='text-r'>性别：</th><td>"+teacher.getSex()+"</td></tr>";		
			note+="<tr><th class='text-r'>电话：</th><td>"+teacher.getPhoneNumber()+"</td></tr>";
			note+="<tr><th class='text-r'>院系：</th><td>"+teacher.getDepartment()+"</td></tr>";	
			note+="</tbody></table></div>";
		}
		else{
			note+="暂无此人信息";
		}
		request.setAttribute("returnValue", note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面
	}
	/**
	 * 功能：查询已申请课程
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ask_applied(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String cur_page=request.getParameter("cur_page");
		int page=Integer.parseInt(cur_page);
		HttpSession session = request.getSession();// 获取HttpSession的对象
		int course_count=0;
		String mes="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}
		
		int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
		ConnDB  conn = new ConnDB();
		String sql="SELECT CID,TITLE,ERADDRESS,to_char(EXPTIME,'yyyy-mm-dd hh:mm:ss')  AS NEWDATE,EXPSTATUS FROM EXPCOURSE,EXPROOM "
				+ "WHERE  EXPCOURSE.ERID=EXPROOM.ERID AND TID="+tid;
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next()){
				mes+="<tr class='text-c'>";
				mes+="<td>"+rs.getInt("CID")+"</td>";
				mes+="<td class='text-l'><u style='cursor:pointer' class='text-primar'>"+rs.getString("TITLE")+"</u></td>";
				mes+="<td>"+rs.getString("ERADDRESS")+"</td>";
				mes+="<td>"+rs.getString("NEWDATE")+"</td>";
				switch(rs.getInt("EXPSTATUS")){
				case 1:mes+="<td class='td-status'><span class='label label-success radius'>同意开设</span></td>";break;
				case 0:mes+="<td class='td-status'><span class='label label-warning radius'>申请中</span></td>";break;
				case -1:mes+="<td class='td-status'><span class='label label-failed radius'>未能开设</span></td>";break;
				default:break;
				}
				mes+=	"</tr>";
				course_count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
		}
		mes+=course_count+"";
		request.setAttribute("returnValue",mes);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面			
	}	
	/**
	 * 功能：查询学生申请情况
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void check_student_application(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cur_page=request.getParameter("cur_page");
		int page=Integer.parseInt(cur_page);
		HttpSession session = request.getSession();// 获取HttpSession的对象
		int course_count=0;
		String mes="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}
		int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
		ConnDB  conn = new ConnDB();
		
		String sql="SELECT TITLE,TNAME,SNUMBER,SNAME,to_char(EATIME,'yyyy-mm-dd hh:mm:ss')  AS NEWTIME,EASTATE,EXPCOURSE.CID AS COURSEID,STUDENT.SID AS STUDENTID FROM EXPAPPLICATION,TEACHER,STUDENT,EXPCOURSE "
				+ "WHERE TEACHER.TID="+tid
				+ " AND EXPAPPLICATION.CID=EXPCOURSE.CID AND EXPAPPLICATION.SID=STUDENT.SID AND EXPCOURSE.TID=TEACHER.TID";
		System.out.println(sql);
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next()){		
		
				mes+="<tr class='text-c'>";
				mes+="<td><input type='checkbox' value='"+rs.getInt("STUDENTID")+":"+rs.getInt("COURSEID")+"' name='selected' ></td>";			
				mes+="<td class='text-l'><u style='cursor:pointer' class='text-primar'>"+rs.getString("TITLE")+"</u></td>";
				mes+="<td>"+rs.getString("TNAME")+"</td>";
				mes+="<td>"+rs.getString("SNUMBER")+"</td>";
				mes+="<td>"+rs.getString("SNAME")+"</td>";
				mes+="<td>"+rs.getString("NEWTIME")+"</td>";
				switch(rs.getInt("EASTATE")){
				case 1:mes+="<td class='td-status'><span class='label label-success radius'>审核通过</span></td>";break;
				case 0:mes+="<td class='td-status'><span class='label label-warning radius'>审核中</span></td>";break;
				case -1:mes+="<td class='td-status'><span class='label label-failed radius'>审核失败</span></td>";break;
				default:break;
				}
				mes+=	"</tr>";
				course_count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
		}
		mes+=course_count+"";
		request.setAttribute("returnValue",mes);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面			
	}		
	/**
	 * 功能：同意学生申请
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void consent_application(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String note="";
		ConnDB conn=new ConnDB(); 
		String sc=request.getParameter("sc");
		String sc_val[] = sc.split(",");// 截取字符串，获得各个checkBox的值
		int success_count=0;
		for(int i=1;i<sc_val.length;i++){
			boolean flag=true;
			int sid,cid;	
			String params[]=sc_val[i].split(":");
			sid=Integer.parseInt(params[0]);
			cid=Integer.parseInt(params[1]);		
			String sql="SELECT * FROM EXPAPPLICATION WHERE SID="+sid+" AND CID="+cid;
			ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
			try {
				while(rs.next())	{
					if(Integer.parseInt(rs.getString("EASTATE"))==1){
						note+="操作中有已经同意的申请，无须再次操作\n";
						flag=false;
						}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			if(flag==false) continue;
			sql="UPDATE EXPAPPLICATION SET EASTATE=1 WHERE SID="+sid+" AND CID="+cid;

			if(conn.executeUpdate(sql)==1)
					success_count++;				
		}
		note+="本次共审核同意了"+(sc_val.length-1)+"节课程，成功"+success_count+"节，失败"+(sc_val.length-1-success_count)+"节课程。";
		request.setAttribute("returnValue",note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面			
	}	
	/**
	 * 功能：拒绝学生申请
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deny_application(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String note="";
		ConnDB conn=new ConnDB(); 
		String sc=request.getParameter("sc");
		String sc_val[] = sc.split(",");// 截取字符串，获得各个checkBox的值
		int success_count=0;
		for(int i=1;i<sc_val.length;i++){
			boolean flag=true;
			int sid,cid;	
			String params[]=sc_val[i].split(":");
			sid=Integer.parseInt(params[0]);
			cid=Integer.parseInt(params[1]);				
			String sql="SELECT * FROM EXPAPPLICATION WHERE SID="+sid+" AND CID="+cid;
			ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
			try {
				while(rs.next())	{
					if(Integer.parseInt(rs.getString("EASTATE"))==-1){
						note+="操作中有已经拒绝的申请，无须再次操作\n";
						flag=false;
						}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag==false) continue;
			sql="UPDATE EXPAPPLICATION SET EASTATE=-1 WHERE SID="+sid+" AND CID="+cid;

			if(conn.executeUpdate(sql)==1)
					success_count++;				
		}
		note+="本次共审核拒绝了"+(sc_val.length-1)+"节课程，成功"+success_count+"节，失败"+(sc_val.length-1-success_count)+"节课程。";
		request.setAttribute("returnValue",note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面			
	}	
	/**
	 * 功能：查看学生实验课情况
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void check_expcourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String note="";
		ConnDB conn=new ConnDB(); 
		HttpSession session = request.getSession();// 获取HttpSession的对象
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}
		int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
		//System.out.println(tid);
		String sql="SELECT EXPCOURSE.CID AS COURSEID,TITLE,SNAME,SNUMBER,CONTENT,EARESULT,STUDENT.SID FROM EXPCOURSE,STUDENT,EXPAPPLICATION WHERE TID="+tid
						+ " AND EXPCOURSE.CID=EXPAPPLICATION.CID AND EXPAPPLICATION.SID=STUDENT.SID";
		//System.out.println(sql);
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next())	{
					note+="<tr class='text-c'>";
					note+="<td>"+rs.getInt("COURSEID")+"</td>";
					note+="<td><a onclick='' >"+rs.getString("TITLE")+"</a></td>";
					note+="<td>"+rs.getString("SNAME")+"</td>";
					note+="<td>"+rs.getString("SNUMBER")+"</td>";
					note+="<td>"+rs.getString("CONTENT")+"</td>";
					//System.out.println(rs.getString("EARESULT"));
					if(Integer.parseInt(rs.getString("EARESULT"))==-1)
					note+="<td>--分</td>";
					else note+="<td>"+rs.getString("EARESULT")+"分</td>";
				//	note+="<td><a title='编辑' href='javascript:;' onclick='edit('score.jsp','360','460')' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a></td></tr>";
					note+="<td><a  href='javascript:;' onclick='edit(&quot;score.jsp?cid="+rs.getInt("COURSEID")+"&sid="+rs.getInt("SID")+"&quot;,&quot;360&quot;,&quot;460&quot;)' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a></td>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
		}
		request.setAttribute("returnValue",note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面		
	}
	/**
	 * 功能：修改学生实验成绩及评分
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void edit_grade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		String cid = request.getParameter("cid");
		String score = request.getParameter("score");
		String leadership = request.getParameter("leadership");
		String teamwork = request.getParameter("teamwork");
		String apprehension = request.getParameter("apprehension");
		String practice = request.getParameter("practice");
		String analysis = request.getParameter("analysis");
		String innovation = request.getParameter("innovation");
		String note = "";
		ConnDB conn=new ConnDB(); 
		HttpSession session = request.getSession();// 获取HttpSession的对象
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return ;
		}
		String sql="UPDATE EXPAPPLICATION SET earesult="+score+",leadership="+leadership+", "
				+ "teamwork="+teamwork+",apprehension="+apprehension+",practice="+practice+","
						+ "analysis="+analysis+",innovation="+innovation+" WHERE SID="+sid+" AND CID="+cid;
		//System.out.println(sql);
		if(conn.executeUpdate(sql)==1)
		{
			note="实验评分成功";
		}
		else
		{
			note="实验评分成功";
		}
		request.setAttribute("returnValue",note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面		
	}		
}
