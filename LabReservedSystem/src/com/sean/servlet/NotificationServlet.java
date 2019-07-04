/**
* @Title: NotificationServlet.java
* @Package com.sean.servlet
* @Description: TODO(通知信息Servlet)
* @author wsl,sean
* @date 2018.9.15
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sean.dao.NotificationDao;
import com.sean.model.Notification;
import com.sean.tools.MyPagination;
/**
 * Servlet implementation class NotificationServlet
 */
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationDao notificationDao=null;
    private ArrayList<Notification> notifications=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
        notificationDao=new NotificationDao();
        notifications=new ArrayList<Notification>();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action = request.getParameter("action");
			//String nid=request.getParameter("nid");
			if ("show".equals(action)) {
				try {
					show(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
			} 
			else if("display".equals(action)){
				try {
					display(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
			}
			else if("display_detail".equals(action)){
				try {
					display_detail(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
			}
	}
		/********
		 * 功能：首页通知显示
		 */
		private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
			String cur_page=request.getParameter("cur_page");
			int page=Integer.parseInt(cur_page);
			notifications=notificationDao.show();
			String note="";
			if(notifications.size()==0){
				note+="<li>暂无任何公告信息</li>";
			}
			else{
				MyPagination<Notification> pagination=new MyPagination<>();
				ArrayList<Notification> mynew=new ArrayList<>();
				mynew=(ArrayList<Notification>) pagination.getInitPage(notifications, page,2);
				for(Notification n:mynew){
					note+="<li><a href='"+"notification.jsp?nid="+n.getNid()+"'>"+n.getTitle()+"</a>发布时间："+n.getPublictime()+"</li>";
				}
				if(mynew.size()<notifications.size()){
					note+="<li><a href='"+"notificationShow.jsp?cur_page=1'>"+"点此查看更多......"+"</li>";
				}
			}
			request.setAttribute("returnValue", note);// 保存提示信息
			request.getRequestDispatcher("showMessage.jsp").forward(request,
			response);// 重定向页面	
		}
		/********
		 * 功能：更多通知显示
		 */
		private void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			// TODO Auto-generated method stub
				String cur_page=request.getParameter("cur_page");				
				int page=Integer.parseInt(cur_page);
				notifications=notificationDao.show();
				String note="";
				if(notifications.size()==0){
					note+="<li>暂无任何公告信息</li>";
				}
				else{
					MyPagination<Notification> pagination=new MyPagination<>();
					ArrayList<Notification> mynew=new ArrayList<>();
					mynew=(ArrayList<Notification>) pagination.getInitPage(notifications, page,8);
					for(Notification n:mynew){
						note+="<li><a href='"+"notification.jsp?nid="+n.getNid()+"'>"+n.getTitle()+"</a>发布时间："+n.getPublictime()+"</li>";
					}
					
					note+="<ul class='pagination'>  <li><a href='notificationShow.jsp?cur_page="+((page-1)==0?page:page-1)+"'>&laquo;</a></li>";
					for(int i=1;i<=pagination.getMaxPage();i++)
					{
						note+=" <li><a href='notificationShow.jsp?cur_page="+i+"'>"+i+"</a></li> ";
					}
					note+=	 " <li><a href='notificationShow.jsp?cur_page="+(page==pagination.getMaxPage()?page:page+1)+"'>&raquo;</a></li></ul>";
			
					}
				request.setAttribute("returnValue", note);// 保存提示信息
				request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面	
			}
		/********
		 * 功能：通知内容显示
		 */
		private void display_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			// TODO Auto-generated method stub
				String s_nid=request.getParameter("nid");
				int nid=Integer.parseInt(s_nid);
				Notification notification=new Notification();
				notification=notificationDao.display_detail(nid);
				String note="";
			    note+="<p align='center' style='font-size:26px'><strong>"+notification.getTitle()+"</strong></p>";
			    note+="	 <p align='center' style='font-size:15px'> 发布人："+notification.getPublisher()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间："+notification.getPublictime()+"</p>";
			    note+="<div class='content' ><div class='note_detail'><ul id='notify'>";
			    note+="<li>"+notification.getContent()+"</li>";
			    note+="</ul>	</div></div>";
				request.setAttribute("returnValue", note);// 保存提示信息
				request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面	
			}
}