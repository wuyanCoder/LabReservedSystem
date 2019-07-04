/**
* @Title: AdministratorServlet.java
* @Package com.sean.servlet
* @Description: TODO(����Ա����Servlet)
* @author �ŷ���
* @date 2018.11.1
* @version V1.0
*/
package com.sean.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sean.dao.AdministratorDao;
import com.sean.dao.StudentDao;
import com.sean.model.*;
import com.sean.tools.ConnDB;

/**
 * Servlet implementation class Administrator
 */
@WebServlet("/AdministratorServlet")
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private AdministratorDao administratorDao = null;
    public AdministratorServlet() {
        super();
        administratorDao=new AdministratorDao();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		if("login".equals(action)){
			
			login(request,response);
		}
//		else if("show".equals(action)){//ѧ����Ϣ�Ĳ���
//			exit(request,response);
//		}
		else if("exit".equals(action)){
			exit(request,response);
		}
		else if("changlogin".equals(action)){
			exit(request,response);
		}
		else if("change_password".equals(action)){
			change_password(request,response);
		}
		else if("insert_teacher".equals(action)){
			try {
				insert_teacher(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if("check_expCourse_application".equals(action)){
			check_expCourse_application(request,response);
		}
		else if("consent_application".equals(action)){
			consent_application(request,response);
		}
		else if("deny_application".equals(action)){
			deny_application(request,response);
		}
        else if("admin_for_option".equals(action)){
            admin_for_option(request,response);
        }
		
	}

    /********
     * ���ܣ�����Աѡ��
     */
    private void admin_for_option(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String note="<option value='null' selected='selected'>��ѡ��...</option>";
        List<Administrator> admins=administratorDao.admin_lite();
        for(Administrator n:admins){

            note+="<option value='"+n.getID()+"'>"+n.getName()+"</option>";

        }
        request.setAttribute("returnValue", note);// ������ʾ��Ϣ
        request.getRequestDispatcher("showMessage.jsp").forward(request,
                response);// �ض���ҳ��
    }


	/**
	 * ���ܣ�ͬ����ʦ����
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
			int cid;
			String params[]=sc_val[i].split(":");
			cid=Integer.parseInt(params[0]);
			String sql="SELECT * FROM EXPCOURSE WHERE CID="+cid;
			ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
			try {
				while(rs.next())	{
					if(Integer.parseInt(rs.getString("EXPSTATUS"))==1){
						note+="���������Ѿ�ͬ������룬�����ٴβ���\n";
						flag=false;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag==false) continue;
			sql="UPDATE EXPCOURSE SET EXPSTATUS=1 WHERE CID="+cid;

			if(conn.executeUpdate(sql)==1)
				success_count++;
		}
		note+="���ι����ͬ����"+(sc_val.length-1)+"�ڿγ̣��ɹ�"+success_count+"�ڣ�ʧ��"+(sc_val.length-1-success_count)+"�ڿγ̡�";
		request.setAttribute("returnValue",note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��
	}
	/**
	 * ���ܣ��ܾ���ʦ����
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
			int cid;
			String params[]=sc_val[i].split(":");
			cid=Integer.parseInt(params[0]);
			String sql="SELECT * FROM EXPCOURSE WHERE CID="+cid;
			ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
			try {
				while(rs.next())	{
					if(Integer.parseInt(rs.getString("EXPSTATUS"))==-1){
						note+="���������Ѿ��ܾ������룬�����ٴβ���\n";
						flag=false;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag==false) continue;
			sql="UPDATE EXPCOURSE SET EXPSTATUS=-1 WHERE CID="+cid;

			System.out.println(sql);
			if(conn.executeUpdate(sql)==1)
				success_count++;
		}
		note+="���ι���˾ܾ���"+(sc_val.length-1)+"�ڿγ̣��ɹ�"+success_count+"�ڣ�ʧ��"+(sc_val.length-1-success_count)+"�ڿγ̡�";
		request.setAttribute("returnValue",note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��
	}

	/**
	 * ��ѯ��ʦ�ύ��ʵ��γ�
	 * @param request
	 * @param response
	 */
	public void check_expCourse_application(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		String cur_page=request.getParameter("cur_page");
		int page=Integer.parseInt(cur_page);
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		int course_count=0;
		String mes="";
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
			return;
		}
		int aid=Integer.parseInt(session.getAttribute("sessionKey").toString());
		ConnDB conn = new ConnDB();

		String sql="SELECT CID,ec.TITLE TITLE,t.TNAME TNAME,er.ERADDRESS ERADDRESS,to_char(EC.EXPTIME,'yyyy-mm-dd hh:mm:ss') EXPTIME," +
				"EC.EXPSTATUS EXPSTATUS FROM EXPCOURSE ec LEFT JOIN TEACHER t ON ec.TID=t.TID LEFT JOIN EXPROOM er ON er.ERID=ec.ERID WHERE EC.AID="+aid;
		System.out.println(sql);
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
		try {
			while(rs.next()){

				mes+="<tr class='text-c'>";
				mes+="<td><input type='checkbox' value='"+rs.getInt("CID")+"' name='selected' ></td>";
				mes+="<td class='text-l'><u style='cursor:pointer' class='text-primar'>"+rs.getString("TITLE")+"</u></td>";
				mes+="<td>"+rs.getString("TNAME")+"</td>";
				mes+="<td>"+rs.getString("ERADDRESS")+"</td>";
				mes+="<td>"+rs.getString("EXPTIME")+"</td>";
				switch(rs.getInt("EXPSTATUS")){
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
	
	//��ʦ��Ϣ������
	
			public void insert_teacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
				HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
				
				Teacher teacher=new Teacher();
				String teacherNumber=request.getParameter("teacherNumber");
				String password=request.getParameter("password");
				String name=request.getParameter("name");
				String sex=request.getParameter("sex");
				String phoneNumber=request.getParameter("phoneNumber");
				String department=request.getParameter("department");
						
						
				//int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
				
				teacher.setTeacherNumber(teacherNumber);
				teacher.setPassword(password);
				teacher.setName(name);
				teacher.setSex(sex);
				teacher.setPhoneNumber(phoneNumber);
				teacher.setDepartment(department);
				
				
				//request.setAttribute("returnValue", note);// ������ʾ��Ϣ
				request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��	
			}
			
			
			
		private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println(1);
			Administrator administrator=new Administrator();
			administrator.setAdminNumber(request.getParameter("adminNumber")) ;
			administrator.setPassword(request.getParameter("password")); 
			int a=administratorDao.login(administrator);
			if (a > 0) {
				HttpSession session = request.getSession();
		
				session.setAttribute("sessionKey", a);// ����sessionKey
				session.setAttribute("administrator", administrator);//ֱ�ӱ����û�ʵ��			
				session.setAttribute("lastip", administrator.getLastip());
				session.setAttribute("lastdate", administrator.getLastdate());
				administratorDao.ip_date(administrator.getID(),request);			
				request.setAttribute("returnValue", "��¼�ɹ���");// ������ʾ��Ϣ		
				request.getRequestDispatcher("showMessage.jsp").forward(request,
						response);// �ض���ҳ��
				System.out.println(administrator.getLastip());
				System.out.println(administrator.getLastdate());
			} else {// ���û���¼���ɹ�ʱ
				request.setAttribute("returnValue", "��������û���������������������룡");
				request.getRequestDispatcher("showMessage.jsp").forward(request,
						response);// �ض���ҳ��
			}
		}
		
		
		
		//ѧ����Ϣ�Ĳ���
		/*
		private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			Student student=new Student();
			String studentNumber= request.getParameter("studentNumber");// ��ȡHttpSession�Ķ���		
			student=studentDao.show(studentNumber);
			session.setAttribute("student", student);//ֱ�ӱ����û�ʵ��
			String note="";
			if(student!=null){
				note+="<div class='cl pd-20' style=' background-color:#5bacb6'>";
				note+="<img class='avatar size-XL l' src='../static/h-ui/images/ucnter/avatar-default.jpg'>";
				note+="<dl style='margin-left:80px; color:#fff'>";
				note+="<dt><span class='f-18'>"+student.getName()+"</span>	</dt>	</dl> 	</div>";			
				note+="<div class='pd-20'><table class='table'><tbody>";		
				note+="<tr><th class='text-r' width='80'>ѧ�ţ�</th><td>"+student.getStudentNumber()+"	</td></tr>";				
				note+="<tr><th class='text-r'>�Ա�</th><td>"+student.getSex()+"</td></tr>";		
				note+="<tr><th class='text-r'>Ժϵ��</th><td>"+student.getDepartment()+"</td></tr>";	
				//note+="<tr><th class='text-r'>�꼶��</th><td>"+student.getS_grade()+"</td></tr>";	
				note+="<tr><th class='text-r'>�༶��</th><td>"+student.getS_class()+"</td></tr>";	
				note+="<tr><th class='text-r'>�绰��</th><td>"+student.getTelnum()+"</td></tr>";	
				//note+="<tr><th class='text-r'>������</th><td><a href='../Student/Ability.jsp'	>"+"����鿴ѧ������"+"</a></td></tr>";	
				note+="</tbody></table></div>";
			}
			else{
				note+="���޴�����Ϣ";
			}
			request.setAttribute("returnValue", note);// ������ʾ��Ϣ
			request.getRequestDispatcher("showMessage.jsp").forward(request,
					response);// �ض���ҳ��
		}
		*/
		//����Աע�����ص�������
		private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
			session.invalidate();// ����session
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);// �ض���ҳ��
		}
		//����Ա�л���¼�˺�
//	private void changlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			
//			HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
//			session.invalidate();// ����session
//			request.getRequestDispatcher("../Administrator/adminLogin.jsp").forward(request,
//					response);// �ض���ҳ��
//		}

		private void change_password(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
			if(session==null){
				request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
				return;
			}
			int aid=(int)session.getAttribute("sessionKey");
			String old_password=request.getParameter("old_password");
			String new_password=request.getParameter("new_password");
			if(administratorDao.changePassword(aid,old_password,new_password)==1){
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
		
		
		
		
	
	}


