/**
* @Title: AdministratorDao.java
* @Package com.sean.dao
* @Description: TODO(管理员的数据库操作)
* @author 张芳菲
* @date 2018.11.1
* @version V1.0
*/
package com.sean.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sean.model.Administrator;
import com.sean.model.Student;
import com.sean.tools.ConnDB;
import com.sean.tools.DateTest;
import com.sean.tools.IPv6Test;

public class AdministratorDao {
	private ConnDB conn = null;
	private Administrator return_administrator=null;
	public AdministratorDao() {
		conn = new ConnDB();
		 return_administrator=new Administrator();
	}
	

	
	//登录
 public int login(Administrator administrator){
	 int flag=0;
	 String sql = "SELECT * FROM ADMINISTRATOR WHERE ANUMBER='"
				+ administrator.getAdminNumber() + "'";	
	 //System.out.println(sql);
		ResultSet rs = conn.executeQuery(sql);
		try{
		if (rs.next()) {
			String pwd = administrator.getPassword();
			int aid = rs.getInt("AID");
			String truepwd=rs.getString("APASSWORD");
			if (pwd.equals(truepwd)) {
				flag = aid;
				rs.last(); 
				int rowSum = rs.getRow();
				rs.first();
				if (rowSum != 1) {
					flag = 0;
				}	

				administrator.setID(aid);
				administrator.setName(rs.getString("ANAME"));
				administrator.setSex(rs.getString("ASEX"));
				administrator.setPhoneNumber(rs.getString("APHONE"));
				administrator.setEmail(rs.getString("AEMAIL"));
				administrator.setDepartment(rs.getString("ADEPARTMENT"));
				administrator.setLastip(rs.getString("ALASTIP"));
				administrator.setLastdate(rs.getString("ALASTDATE"));
				
			} else {
				flag = 0;
			}
		} else {
			flag = 0;
		}
	} catch (SQLException ex) {
		ex.printStackTrace();
		flag = 0;
	} finally {
		conn.close();
	}
	return flag;
 }
 
	//修改密码
	public int changePassword(int aid,String old_password,String new_password){
		int flag=0;
		String sql = "SELECT * FROM ADMINISTRATOR WHERE AID='"
				+ aid+ "'";
		ResultSet rs = conn.executeQuery(sql);
		try {
			if (rs.next()) {
				String truepwd=rs.getString("APASSWORD");
				if (old_password.equals(truepwd)) {
					sql="UPDATE ADMINISTRATOR SET APASSWORD='"+new_password+"' WHERE AID='"+aid+"'";
					if(conn.executeUpdate(sql)==1){
						flag=1;
					}
					else{
						flag=0;
					}
				}
			 }
			}catch (SQLException ex) {
						ex.printStackTrace();
						flag = 0;
			} finally {
						conn.close();
			}
		return flag;
		}
	//IP地址更新
	public void ip_date(int aid,HttpServletRequest request) throws IOException{
		String ip = IPv6Test.getIpAddr(request);
		String date = DateTest.Date();
		String sql = "SELECT * FROM ADMINISTRATOR WHERE AID='"
				+ aid+ "'";
		conn.executeQuery(sql);// 执行SQL语句
		try {	
			
					sql="UPDATE ADMINISTRATOR SET ALASTIP='"+ip+"',ALASTDATE=TO_DATE('"+date+"' ,'YYYY/MM/DD HH24:MI:SS') WHERE AID='"+aid+"'";
				//	System.out.println(sql);
					
					conn.executeUpdate(sql);
 
			} finally {
			
						conn.close();// 关闭数据库连接
			}
		}

	/**
	 * 获得管理员列表
	 * @return
	 */
	public List<Administrator> admin_lite() {

		List<Administrator> admins=new ArrayList<Administrator>();
		String sql = "SELECT * FROM ADMINISTRATOR";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next()){
				Administrator administrator=new Administrator();

				administrator.setID(rs.getInt("AID"));
				administrator.setName(rs.getString("ANAME"));
				administrator.setSex(rs.getString("ASEX"));
				administrator.setPhoneNumber(rs.getString("APHONE"));
				administrator.setEmail(rs.getString("AEMAIL"));
				administrator.setDepartment(rs.getString("ADEPARTMENT"));
				administrator.setLastip(rs.getString("ALASTIP"));
				administrator.setLastdate(rs.getString("ALASTDATE"));
				admins.add(administrator);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return admins;
	}
}
