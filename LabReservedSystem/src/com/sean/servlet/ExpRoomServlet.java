/**
* @Title: ExpRoomServlet.java
* @Package com.sean.servlet
* @Description: TODO(实验室信息Servlet)
* @author sean
* @date 2018.9.1
* @version V1.0
*/
package com.sean.servlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sean.dao.ExpRoomDao;
import com.sean.model.ExpRoom;

import com.sean.tools.ConnDB;
public class ExpRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExpRoomDao exproomDao=null;
    private ArrayList<ExpRoom> expRooms=null;

    public ExpRoomServlet() {
        super();
        exproomDao=new ExpRoomDao();
        expRooms=new ArrayList<ExpRoom>();
        // TODO Auto-generated constructor stub
  
    }	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if ("roomstate_lite".equals(action)) {
			try {

				roomstate_lite(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
		else if ("address_for_option".equals(action)) {
			try {
				address_for_option(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else if ("show".equals(action)) {
			try {
				show(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
		}
	/********
	 * 功能：实验室状态显示
	 */
	public void roomstate_lite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		expRooms=exproomDao.roomstae_lite();
		String note="";
		if(expRooms.size()==0){
			note+="暂无任何实验室信息";
		}
		else{
			note+="<ul class='ul_zj'>";
			for(ExpRoom n:expRooms){
				if(n.getErstate()==1){
					note+=" <li><img src='images/电脑开机.png' width='180' height='180'><span>"+n.getEraddress()+"</span> </li>";
				}
				else{
					note+="<li><img src='images/电脑关机.png' width='180' height='180'><span>"+n.getEraddress()+"</span> </li>";
				}			
		}
		note+="</ul>";
		}
		request.setAttribute("returnValue", note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
		response);// 重定向页面	
		}
	/********
	 * 功能：实验室选择
	 */
	public void address_for_option(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String note="<option value='null' selected='selected'>请选择...</option>";
		expRooms=exproomDao.roomstae_lite();
		for(ExpRoom n:expRooms){
			if(n.getErstate()==1){
				note+="<option value='"+n.getErid()+"'>"+n.getEraddress()+"</option>";
			}
			else {
				note+="<option value='"+n.getErid()+"' disabled='disabled'>"+n.getEraddress()+"</option>";
			}
		}
		request.setAttribute("returnValue", note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
		response);// 重定向页面	
	}
	/**
	 * 功能：显示实验室信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ExpRoom expRoom=new ExpRoom();
		String eraddress=request.getParameter("eraddress");// 获取HttpSession的对象	
		expRoom=exproomDao.show(eraddress);
	    session.setAttribute("eraddress", eraddress);//直接保存用户实例
		String note="";
		if(expRoom!=null){
			note+="<div class='cl pd-20' style=' background-color:#5bacb6'>";
			note+="<img class='avatar size-XL l' src='../static/h-ui/images/ucnter/avatar-default.jpg'>";
			note+="<dl style='margin-left:80px; color:#fff'>";
			note+="<dt><span class='f-18'>"+expRoom.getEraddress()+"</span>	</dt>	</dl> 	</div>";			
			note+="<div class='pd-20'><table class='table'><tbody>";		
							
			note+="<tr><th class='text-r'>电话：</th><td>"+expRoom.getErtelnum()+"</td></tr>";		
			note+="<tr><th class='text-r'>状态：</th><td>"+expRoom.getErstate()+"</td></tr>";
			note+="<tr><th class='text-r'>院系：</th><td>"+expRoom.getSuperordinate()+"</td></tr>";	
			note+="</tbody></table></div>";
		}
		else{
			note+="暂无此人信息";
		}
		request.setAttribute("returnValue", note);// 保存提示信息
		request.getRequestDispatcher("showMessage.jsp").forward(request,
				response);// 重定向页面
	}
}