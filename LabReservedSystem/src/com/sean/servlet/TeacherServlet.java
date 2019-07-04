/**
* @Title: TeacherServlet.java
* @Package com.sean.servlet
* @Description: TODO����ʦģ���漰����Servlet������
* @author wsl��sean
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
	 * ���ܣ��û���½
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Teacher teacher=new Teacher(); 
		teacher.setTeacherNumber(request.getParameter("teacherNumber")) ;// ��ȡ�������û���
		teacher.setPassword(request.getParameter("password")); // ��ȡ����������
		int r=teacherDao.login(teacher);
		if (r > 0) {// ���û���¼�ɹ�ʱ
			HttpSession session = request.getSession();
		//	session.setAttribute("teacherNumber", teacher.getTeacherNumber());// �����û���
		//	session.setAttribute("sid", r);// �����û�ID
			session.setAttribute("sessionKey", r);// ����sessionKey
			session.setAttribute("teacher", teacher);//ֱ�ӱ����û�ʵ��			
			session.setAttribute("last_ip", teacher.getLastip());
			session.setAttribute("last_date", teacher.getLastdate());
			teacherDao.ip_date(teacher.getID(),request);			
			request.setAttribute("returnValue", "��¼�ɹ���");// ������ʾ��Ϣ		
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// �ض���ҳ��
		} else {// ���û���¼���ɹ�ʱ
			request.setAttribute("returnValue", "��������û���������������������룡");
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// �ض���ҳ��
		}
	}
	/**
	 * ���ܣ��û��˳�
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		session.invalidate();// ����session
		request.getRequestDispatcher("/index.jsp").forward(request,
				response);// �ض���ҳ��
	}
	private void change_password(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
			return;
		}
		int tid=(int)session.getAttribute("sessionKey");
		String old_password=request.getParameter("old_password");
		String new_password=request.getParameter("new_password");
		if(teacherDao.changePassword(tid,old_password,new_password)==1){
			session.invalidate();// ����session
			request.setAttribute("returnValue", "�޸ĳɹ���");// ������ʾ��Ϣ
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// �ض���ҳ��			
		}
		else{
			request.setAttribute("returnValue", "ԭ��������޸�ʧ�ܣ�");// ������ʾ��Ϣ
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// �ض���ҳ��
		}	
	}	
	
	/**
	 * ���ܣ���ʾ��ʦ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Teacher teacher=new Teacher();
		String teacherNumber= request.getParameter("teacherNumber");// ��ȡHttpSession�Ķ���		
		teacher=teacherDao.show(teacherNumber);
		session.setAttribute("teacher", teacher);//ֱ�ӱ����û�ʵ��
		String note="";
		System.out.print("teacher");
		if(teacher!=null){
			note+="<div class='cl pd-20' style=' background-color:#5bacb6'>";
			note+="<img class='avatar size-XL l' src='../static/h-ui/images/ucnter/avatar-default.jpg'>";
			note+="<dl style='margin-left:80px; color:#fff'>";
			note+="<dt><span class='f-18'>"+teacher.getName()+"</span>	</dt>	</dl> 	</div>";			
			note+="<div class='pd-20'><table class='table'><tbody>";		
			note+="<tr><th class='text-r' width='80'>ѧ�ţ�</th><td>"+teacher.getTeacherNumber()+"	</td></tr>";				
			note+="<tr><th class='text-r'>�Ա�</th><td>"+teacher.getSex()+"</td></tr>";		
			note+="<tr><th class='text-r'>�绰��</th><td>"+teacher.getPhoneNumber()+"</td></tr>";
			note+="<tr><th class='text-r'>Ժϵ��</th><td>"+teacher.getDepartment()+"</td></tr>";	
			note+="</tbody></table></div>";
		}
		else{
			note+="���޴�����Ϣ";
		}
		request.setAttribute("returnValue", note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��
	}
	/**
	 * ���ܣ���ѯ������γ�
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ask_applied(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String cur_page=request.getParameter("cur_page");
		int page=Integer.parseInt(cur_page);
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		int course_count=0;
		String mes="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
			return;
		}
		
		int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
		ConnDB  conn = new ConnDB();
		String sql="SELECT CID,TITLE,ERADDRESS,to_char(EXPTIME,'yyyy-mm-dd hh:mm:ss')  AS NEWDATE,EXPSTATUS FROM EXPCOURSE,EXPROOM "
				+ "WHERE  EXPCOURSE.ERID=EXPROOM.ERID AND TID="+tid;
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
		try {
			while(rs.next()){
				mes+="<tr class='text-c'>";
				mes+="<td>"+rs.getInt("CID")+"</td>";
				mes+="<td class='text-l'><u style='cursor:pointer' class='text-primar'>"+rs.getString("TITLE")+"</u></td>";
				mes+="<td>"+rs.getString("ERADDRESS")+"</td>";
				mes+="<td>"+rs.getString("NEWDATE")+"</td>";
				switch(rs.getInt("EXPSTATUS")){
				case 1:mes+="<td class='td-status'><span class='label label-success radius'>ͬ�⿪��</span></td>";break;
				case 0:mes+="<td class='td-status'><span class='label label-warning radius'>������</span></td>";break;
				case -1:mes+="<td class='td-status'><span class='label label-failed radius'>δ�ܿ���</span></td>";break;
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
		request.setAttribute("returnValue",mes);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��			
	}	
	/**
	 * ���ܣ���ѯѧ���������
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void check_student_application(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cur_page=request.getParameter("cur_page");
		int page=Integer.parseInt(cur_page);
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		int course_count=0;
		String mes="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
			return;
		}
		int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
		ConnDB  conn = new ConnDB();
		
		String sql="SELECT TITLE,TNAME,SNUMBER,SNAME,to_char(EATIME,'yyyy-mm-dd hh:mm:ss')  AS NEWTIME,EASTATE,EXPCOURSE.CID AS COURSEID,STUDENT.SID AS STUDENTID FROM EXPAPPLICATION,TEACHER,STUDENT,EXPCOURSE "
				+ "WHERE TEACHER.TID="+tid
				+ " AND EXPAPPLICATION.CID=EXPCOURSE.CID AND EXPAPPLICATION.SID=STUDENT.SID AND EXPCOURSE.TID=TEACHER.TID";
		System.out.println(sql);
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
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
				case 1:mes+="<td class='td-status'><span class='label label-success radius'>���ͨ��</span></td>";break;
				case 0:mes+="<td class='td-status'><span class='label label-warning radius'>�����</span></td>";break;
				case -1:mes+="<td class='td-status'><span class='label label-failed radius'>���ʧ��</span></td>";break;
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
		request.setAttribute("returnValue",mes);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��			
	}		
	/**
	 * ���ܣ�ͬ��ѧ������
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
		String sc_val[] = sc.split(",");// ��ȡ�ַ�������ø���checkBox��ֵ
		int success_count=0;
		for(int i=1;i<sc_val.length;i++){
			boolean flag=true;
			int sid,cid;	
			String params[]=sc_val[i].split(":");
			sid=Integer.parseInt(params[0]);
			cid=Integer.parseInt(params[1]);		
			String sql="SELECT * FROM EXPAPPLICATION WHERE SID="+sid+" AND CID="+cid;
			ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
			try {
				while(rs.next())	{
					if(Integer.parseInt(rs.getString("EASTATE"))==1){
						note+="���������Ѿ�ͬ������룬�����ٴβ���\n";
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
		note+="���ι����ͬ����"+(sc_val.length-1)+"�ڿγ̣��ɹ�"+success_count+"�ڣ�ʧ��"+(sc_val.length-1-success_count)+"�ڿγ̡�";
		request.setAttribute("returnValue",note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��			
	}	
	/**
	 * ���ܣ��ܾ�ѧ������
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
		String sc_val[] = sc.split(",");// ��ȡ�ַ�������ø���checkBox��ֵ
		int success_count=0;
		for(int i=1;i<sc_val.length;i++){
			boolean flag=true;
			int sid,cid;	
			String params[]=sc_val[i].split(":");
			sid=Integer.parseInt(params[0]);
			cid=Integer.parseInt(params[1]);				
			String sql="SELECT * FROM EXPAPPLICATION WHERE SID="+sid+" AND CID="+cid;
			ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
			try {
				while(rs.next())	{
					if(Integer.parseInt(rs.getString("EASTATE"))==-1){
						note+="���������Ѿ��ܾ������룬�����ٴβ���\n";
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
		note+="���ι���˾ܾ���"+(sc_val.length-1)+"�ڿγ̣��ɹ�"+success_count+"�ڣ�ʧ��"+(sc_val.length-1-success_count)+"�ڿγ̡�";
		request.setAttribute("returnValue",note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��			
	}	
	/**
	 * ���ܣ��鿴ѧ��ʵ������
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void check_expcourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String note="";
		ConnDB conn=new ConnDB(); 
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
			return;
		}
		int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
		//System.out.println(tid);
		String sql="SELECT EXPCOURSE.CID AS COURSEID,TITLE,SNAME,SNUMBER,CONTENT,EARESULT,STUDENT.SID FROM EXPCOURSE,STUDENT,EXPAPPLICATION WHERE TID="+tid
						+ " AND EXPCOURSE.CID=EXPAPPLICATION.CID AND EXPAPPLICATION.SID=STUDENT.SID";
		//System.out.println(sql);
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
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
					note+="<td>--��</td>";
					else note+="<td>"+rs.getString("EARESULT")+"��</td>";
				//	note+="<td><a title='�༭' href='javascript:;' onclick='edit('score.jsp','360','460')' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a></td></tr>";
					note+="<td><a  href='javascript:;' onclick='edit(&quot;score.jsp?cid="+rs.getInt("COURSEID")+"&sid="+rs.getInt("SID")+"&quot;,&quot;360&quot;,&quot;460&quot;)' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a></td>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
		}
		request.setAttribute("returnValue",note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��		
	}
	/**
	 * ���ܣ��޸�ѧ��ʵ��ɼ�������
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
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
			return ;
		}
		String sql="UPDATE EXPAPPLICATION SET earesult="+score+",leadership="+leadership+", "
				+ "teamwork="+teamwork+",apprehension="+apprehension+",practice="+practice+","
						+ "analysis="+analysis+",innovation="+innovation+" WHERE SID="+sid+" AND CID="+cid;
		//System.out.println(sql);
		if(conn.executeUpdate(sql)==1)
		{
			note="ʵ�����ֳɹ�";
		}
		else
		{
			note="ʵ�����ֳɹ�";
		}
		request.setAttribute("returnValue",note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��		
	}		
}
