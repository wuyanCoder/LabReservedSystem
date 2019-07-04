/**
* @Title: ExpCourseServlet.java
* @Package com.sean.servlet
* @Description: TODO(ʵ��γ̲����漰����Servlet����)
* @author wsl��sean
* @date 2018.9.20
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sean.dao.ExpCourseDao;
import com.sean.dao.NotificationDao;
import com.sean.model.ExpCourse;
import com.sean.model.Notification;
import com.sean.tools.ConnDB;
import com.sean.tools.MyPagination;
/**
 * Servlet implementation class ExpCourseServlet
 */
public class ExpCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExpCourseDao expCourseDao=null;
    private ArrayList<ExpCourse>  expCourses=null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
        expCourseDao=new ExpCourseDao();
        expCourses=new ArrayList<ExpCourse>();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if ("show".equals(action)) {
			try {
				show(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
		else if ("find_course".equals(action)) {
			try {
				find_course(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
		else if ("apply_course".equals(action)) {
			try {
				apply_course(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
		else if ("insert_newcourse".equals(action)) {
			try {
				insert_newcourse(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 		
		else if("display".equals(action)){
			try {
				display(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}		
		else if("display_detail".equals(action)){
			try {
				display_detail(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}				
	}
	/**
	 * ���ܣ���ҳչʾ�γ���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		String cur_page=request.getParameter("cur_page");
		int page=Integer.parseInt(cur_page);

		expCourses=expCourseDao.show();
		String note="";
		if(expCourses.size()==0){
			note+="<li>�����κ�ʵ��γ���Ϣ</li>";
		}
		else{
			MyPagination<ExpCourse> pagination=new MyPagination<>();
			ArrayList<ExpCourse> mynew=new ArrayList<>();
			mynew=(ArrayList<ExpCourse>) pagination.getInitPage(expCourses, page,1);
			for(ExpCourse e:mynew){
				note+="<li><a href='"+"expCourse.jsp?cid="+e.getCid()+"'>"+e.getTitle()+"</a>����ʱ�䣺"+e.getPublicTime()+"</li>";
			}
			if(mynew.size()<expCourses.size()){
				note+="<li><a href='"+"expCourseShow.jsp'>"+"��˲鿴����......"+"</li>";
			}
		}
		request.setAttribute("returnValue", note);// ������ʾ��Ϣ
		request.getRequestDispatcher("showMessage.jsp").forward(request,
		response);// �ض���ҳ��
	}
	/**
	 * ���ܣ�����ʵ��γ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void find_course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		ConnDB conn=new ConnDB(); 
		String department=request.getParameter("department");
		String from_date=request.getParameter("from_date");
		String to_date=request.getParameter("to_date");
		String keyword=request.getParameter("keyword");
		HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
		if(session==null){
			request.getRequestDispatcher("ErrorServlet?action=login").forward(request,response);// �ض���ҳ��
			return;
		}
		String note="";
		int rowCount=0;
		String sql = "SELECT CID,TITLE,SUPERORDINATE,TNAME,ERADDRESS,to_char(EXPTIME,'yyyy-mm-dd hh:mm:ss')  AS NEWDATE,ERTELNUM,EXPSTATUS "
				+ "FROM EXPCOURSE,EXPROOM,TEACHER WHERE EXPCOURSE.ERID=EXPROOM.ERID "
				+ "AND EXPCOURSE.TID=TEACHER.TID"
				+ " AND SUPERORDINATE='"+department+"'"+" AND EXPSTATUS=1  AND to_char(EXPTIME,'yyyy-mm-dd') BETWEEN '"
				+ from_date+"' AND '"+to_date+"' AND TITLE LIKE '%"+keyword+"%'";
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
		try {
			note+="<table class='table table-border table-bordered table-bg table-hover table-sort table-responsive'>"
					+"<thead><tr class='text-c'>"
					+ "<th width='25'></th>"
					+ "<th width='50'>ID</th>"
					+ "<th>����</th>"
					+ "<th width='100'>����Ժϵ</th>"
					+ "<th width='60'>ָ����ʦ</th>"
					+ "<th width='80'>ʵ���ҵ�ַ</th>"
					+ "<th width='100'>ʵ���ҵ绰</th>"
					+ "<th width='140'>ʵ��ʱ��</th>"
					+ "</tr></thead>	<tbody>";	
			while(rs.next()){
				note+="<tr class='text-c'>";
				String interior_sql="select eastate from expapplication where sid='"+session.getAttribute("sessionKey").toString()+"' and cid='"+rs.getInt("CID")+"'";		
				ResultSet interior_rs = conn.executeQuery(interior_sql);// ִ��SQL���
				int interior_count=0;
				while(interior_rs.next()){
					if(interior_rs.getInt("eastate")!=-1)
						interior_count++;
				}
				if(interior_count!=0){
					note+="<td>"+"<input type='checkbox' disabled='disabled' name='selected' value='"+rs.getInt("CID")+"'>"+"</td>";
				}
			else 		note+="<td>"+"<input type='checkbox' name='selected' value='"+rs.getInt("CID")+"'>"+"</td>";
				note+="<td>"+rs.getInt("CID")+"</td>"
				+"<td class='text-l'><u style='cursor:pointer' class='text-primary' onClick='article_edit('�鿴','article-zhang.jsp','10001')' title='�鿴'>"+rs.getString("TITLE")+"</u></td>"
				+"<td>"+rs.getString("SUPERORDINATE")+"</td>"
				+"<td>"+rs.getString("TNAME")+"</td>"
				+"<td>"+rs.getString("ERADDRESS")+"</td>"
				+"<td>"+rs.getString("ERTELNUM")+"</td>"
				+"<td>"+rs.getString("NEWDATE")+"</td>";
				note+="</tr>";
				rowCount++;
				}
				note+="</tbody></table>";		
				if(rowCount==0){
					note+="���޲�ѯ��ʵ��γ���Ϣ!"+"0";
				}
				else note+=rowCount+"";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn.close();// �ر����ݿ�����
			}		
			request.setAttribute("returnValue", note);// ������ʾ��Ϣ
			request.getRequestDispatcher("showMessage.jsp").forward(request,
			response);// �ض���ҳ��	
		}	
	/**
	 * ���ܣ�ԤԼʵ��γ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
		public void apply_course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			ConnDB conn=new ConnDB(); 
			HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
			String cids=request.getParameter("cid");
			String c_val[] = cids.split(",");// ��ȡ�ַ�������ø���checkBox��ֵ
			String note="";
			int sid= Integer.parseInt(session.getAttribute("sessionKey").toString());
			//System.out.println("sid="+sid);
			int success_count=0;
			for(int i=1;i<c_val.length;i++){
				int rs_count=0;
				String sql="select * from expapplication where cid="+c_val[i]+" and sid="+sid;
				ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
				while(rs.next())	{
					rs_count++;
				}				
				if(rs_count==0){
					sql="insert into expapplication values("+sid+","+c_val[i]+",to_date('"+(new java.text.SimpleDateFormat("yyyy-MM-dd,HH:mm:ss")).format(new Date())+"','yyyy-mm-dd,hh24:mi:ss'),0,'-1','','','','','','')";
					System.out.println(sql);
					if(conn.executeUpdate(sql)==1)
							success_count++;		
				}
			}
			note+="���ι�����"+(c_val.length-1)+"�ڿγ̣��ɹ�"+success_count+"�ڣ�ʧ��"+(c_val.length-1-success_count)+"�ڿγ̡���ȴ���ʦ��ˡ�";
			request.setAttribute("returnValue", note);// ������ʾ��Ϣ
			request.getRequestDispatcher("showMessage.jsp").forward(request,
			response);// �ض���ҳ��				
		}
		/**
		 * ���ܣ������¿γ�
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void insert_newcourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			HttpSession session = request.getSession();// ��ȡHttpSession�Ķ���
			String note="";
			ExpCourse expCourse=new ExpCourse();
			String title=request.getParameter("title");
			String exptime=request.getParameter("exptime");
			String erId=request.getParameter("erId");
			String content=request.getParameter("content");
			int aid = Integer.parseInt(request.getParameter("aid"));
			//String ability=request.getParameter("ability");
			
			int tid=Integer.parseInt(session.getAttribute("sessionKey").toString());
			expCourse.setTitle(title);
			expCourse.setExpTime(exptime);
			expCourse.setErid(Integer.parseInt(erId));
			expCourse.setContent(content);
			expCourse.setExpStatus(0);
			expCourse.setTid(tid);
			expCourse.setAid(aid);
			//expCourse.setAbilityFocus(ability);			
			if(expCourseDao.insert(expCourse)==1){
				note+="�ύ�ɹ�����ȴ�����Ա��ˡ�";
			}
			else{
				note+="�ύʧ�ܣ�";
			}
			request.setAttribute("returnValue", note);// ������ʾ��Ϣ
			request.getRequestDispatcher("showMessage.jsp").forward(request,
			response);// �ض���ҳ��	
		}
		/**
		 * ���ܣ�չʾ�γ���Ϣ�鿴����ҳ
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			// TODO Auto-generated method stub
			String cur_page=request.getParameter("cur_page");
			int page=Integer.parseInt(cur_page);
			
			expCourses = expCourseDao.show();
			String note="";
			if(expCourses.size()==0){
				note+="<li>�����κι�����Ϣ</li>";
			}
			else{
				MyPagination<ExpCourse> pagination=new MyPagination<>();
				ArrayList<ExpCourse> mynew=new ArrayList<>();
				mynew=(ArrayList<ExpCourse>) pagination.getInitPage(expCourses, page,8);
				for(ExpCourse n:mynew){
					note+="<li><a href='"+"expCourse.jsp?cid="+n.getCid()+"'>"+n.getTitle()+"</a>����ʱ�䣺"+n.getPublicTime()+"</li>";
				}				
				note+="<ul class='pagination'>  <li><a href='expCourseShow.jsp?cur_page="+((page-1)==0?page:page-1)+"'>&laquo;</a></li>";
				for(int i=1;i<=pagination.getMaxPage();i++)
				{
					note+=" <li><a href='expCourseShow.jsp?cur_page="+i+"'>"+i+"</a></li> ";
				}
				note+=	 " <li><a href='expCourseShow.jsp?cur_page="+(page==pagination.getMaxPage()?page:page+1)+"'>&raquo;</a></li></ul>";		
				}
			request.setAttribute("returnValue", note);// ������ʾ��Ϣ
			request.getRequestDispatcher("showMessage.jsp").forward(request,
			response);// �ض���ҳ��				
			}
		/**
		 * ���ܣ�չʾ�γ���Ϣ
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void display_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			// TODO Auto-generated method stub
				String s_cid=request.getParameter("cid");
				System.out.println(s_cid);
				int cid=Integer.parseInt(s_cid);
				ExpCourse notification=new ExpCourse();
				notification=expCourseDao.display_detail(cid);
				String note="";
			    note+="<p align='center' style='font-size:26px'><strong>"+notification.getTitle()+"</strong></p>";
			    note+="	 <p align='center' style='font-size:15px'> �ڿν�ʦ��"+notification.getTname()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ʱ�䣺"+notification.getPublicTime();
			    note+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ʵ��ʱ�䣺"+notification.getExpTime()+"</p>";
			    note+="<div class='content' ><div class='note_detail'><ul id='notify'>";
			    note+="<li>"+notification.getContent()+"</li>";
			    note+="</ul>	</div></div>";		 	 		 		
				request.setAttribute("returnValue", note);// ������ʾ��Ϣ
				request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// �ض���ҳ��	
			}
}