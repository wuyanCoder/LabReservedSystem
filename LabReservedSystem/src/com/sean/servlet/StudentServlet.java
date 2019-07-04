/**
* @Title: StudebtServlet.java
* @Package com.sean.servlet
* @Description: TODO(学生功能Servlet)
* @author sean
* @date 2018.9.1
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sean.dao.StudentDao;
import com.sean.model.ExpApplication;
import com.sean.model.Notification;
import com.sean.model.Student;
import com.sean.tools.ConnDB;
import com.sean.tools.DateTest;
import com.sean.tools.GetIPv6Address;
import com.sean.tools.MyPagination;
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = null;
    private ArrayList<ExpApplication> expApplications=null;
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
       studentDao=new StudentDao();
       expApplications=new ArrayList<ExpApplication>();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			login(request, response); 
		} 
		else if ("exit".equals(action)) {
			exit(request, response); 
		} 
		else if("change_password".equals(action)){
			change_password(request, response);
		}
		else if("ask_applied".equals(action)){
			ask_applied(request, response);
		}
		else if("show".equals(action)){
			show(request,response);
		}
		else if("get_appliedcoursename".equals(action)){
			get_appliedcoursename(request,response);
		}
//		else if("match".equals(action)){
//			match(request,response);
//		}		
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
		Student student=new Student();
		student.setStudentNumber(request.getParameter("studentNumber")) ;// 获取并设置用户名
		student.setPassword(request.getParameter("password")); // 获取并设置密码
		int r=studentDao.login(student);
		if (r > 0) {// 当用户登录成功时
			HttpSession session = request.getSession();
		//	session.setAttribute("studentNumber", student.getStudentNumber());// 保存用户名
			//session.setAttribute("sid", r);// 保存用户ID
			session.setAttribute("sessionKey", r);// 保存sessionKey
			session.setAttribute("student", student);//直接保存用户实例
			session.setAttribute("last_ip", student.getLastip());
			session.setAttribute("last_date", student.getLastdate());
			studentDao.ip_date(student.getID(),request);
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
	/**
	 * 功能：修改密码 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void change_password(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();// 获取HttpSession的对象

		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}	
		int sid=(int)session.getAttribute("sessionKey");
		String old_password=request.getParameter("old_password");
		String new_password=request.getParameter("new_password");
		if(studentDao.changePassword(sid,old_password,new_password)==1){
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
	 * 功能：查询已预约课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ask_applied(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cur_page=request.getParameter("cur_page");
		int page=Integer.parseInt(cur_page);
		HttpSession session = request.getSession();// 获取HttpSession的对象

		String mes="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}	
		int sid=(int)session.getAttribute("sessionKey");		
		expApplications=studentDao.get_applied(sid);
		ConnDB  conn = new ConnDB();
		for(ExpApplication ea:expApplications){		
			String sql = "SELECT cid,title,superordinate,tname,to_char(exptime,'yyyy-mm-dd hh24:mi:ss') as mytime  FROM expcourse,teacher,exproom WHERE  expcourse.cid = '"+ea.getCid()+"' AND teacher.tid = expcourse.tid AND exproom.erid = expcourse.erid";			
			ResultSet rs = conn.executeQuery(sql);// 执行SQL语句			
			try {
				while(rs.next()){
					mes+="<tr class='text-c'>";
					mes+="<td>"+rs.getInt("CID")+"</td>";
					mes+="<td class='text-l'><u style='cursor:pointer' class='text-primar'>"+rs.getString("TITLE")+"</u></td>";
					mes+="<td>"+rs.getString("SUPERORDINATE")+"</td>";
					mes+="<td>"+rs.getString("TNAME")+"</td>";
					mes+="<td>"+rs.getString("mytime")+"</td>";
					switch(ea.getEaState()){
					case 1:mes+="<td class='td-status'><span class='label label-success radius'>审核通过</span></td>";break;
					case 0:mes+="<td class='td-status'><span class='label label-warning radius'>审核中</span></td>";break;
					case -1:mes+="<td class='td-status'><span class='label label-failed radius'>审核失败</span></td>";break;
					default:break;
						}
					mes+=	"</tr>";					
					}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn.close();// 关闭数据库连接
			}
		}
		mes+=expApplications.size()+"";
		request.setAttribute("returnValue",mes);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面			
	}
	/**
	 * 功能：显示学生信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Student student=new Student();
		String studentNumber= request.getParameter("studentNumber");// 获取HttpSession的对象		
		student=studentDao.show(studentNumber);
		session.setAttribute("student", student);//直接保存用户实例
		String note="";
		if(student!=null){
			note+="<div class='cl pd-20' style=' background-color:#5bacb6'>";
			note+="<img class='avatar size-XL l' src='../static/h-ui/images/ucnter/avatar-default.jpg'>";
			note+="<dl style='margin-left:80px; color:#fff'>";
			note+="<dt><span class='f-18'>"+student.getName()+"</span>	</dt>	</dl> 	</div>";			
			note+="<div class='pd-20'><table class='table'><tbody>";		
			note+="<tr><th class='text-r' width='80'>学号：</th><td>"+student.getStudentNumber()+"	</td></tr>";				
			note+="<tr><th class='text-r'>性别：</th><td>"+student.getSex()+"</td></tr>";		
			note+="<tr><th class='text-r'>院系：</th><td>"+student.getDepartment()+"</td></tr>";	
			//note+="<tr><th class='text-r'>年级：</th><td>"+student.getS_grade()+"</td></tr>";	
			note+="<tr><th class='text-r'>班级：</th><td>"+student.getS_class()+"</td></tr>";	
			note+="<tr><th class='text-r'>电话：</th><td>"+student.getTelnum()+"</td></tr>";	
			//note+="<tr><th class='text-r'>能力：</th><td><a href='../Student/Ability.jsp'	>"+"点击查看学生能力"+"</a></td></tr>";	
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
	 * 功能：获取学生选课信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void get_appliedcoursename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String note="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}
		int sid=(int)session.getAttribute("sessionKey");
		ConnDB  conn = new ConnDB();
		String sql="SELECT EXPCOURSE.CID AS COURSEID,TITLE FROM EXPCOURSE,EXPAPPLICATION WHERE EXPCOURSE.CID=EXPAPPLICATION.CID "
				+ "AND EASTATE=1 AND SID="+sid;
		ResultSet rs=conn.executeQuery(sql);
		try {
			while(rs.next()){
				note+="<option value ='"+rs.getInt("COURSEID")+"'>"+rs.getString("TITLE")+"</option>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
		}
		request.setAttribute("returnValue", note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面
	}		
	/**
	 * 功能：学生匹配
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	/*private void match(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String note="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// 重定向页面
			return;
		}		
		int sid=(int)session.getAttribute("sessionKey");
		ArrayList<Student> students=studentDao.match(sid);
		int count=0;
		DecimalFormat df = new DecimalFormat("#.00");
		for(Student s:students){
			if(count>=10) break;
			double[] w = new double [6];
			w=s.getW();
			double o=w[0]*s.getLeadership()
					+w[1]*s.getTeamwork()
					+w[2]*s.getApprehension()
					+w[3]*s.getPractice()
					+w[4]*s.getAnalysis()
					+w[5]*s.getInnovation();
			note+="<tr class='text-c' >";
			note+="<th>"+(++count)+"</th>";
			note+="<th>"+s.getName()+"</th>";
			note+="<th>"+s.getSex()+"</th>";
			note+="<th>"+s.getS_grade()+"</th>";
			note+="<th>"+s.getDepartment()+"</th>";
			note+="<th>"+df.format(o)+"</th>";
			note+="<th>"+s.getTelnum()+"</th>	</tr>";
		}	
		request.setAttribute("returnValue", note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面
	}*/
		
}
