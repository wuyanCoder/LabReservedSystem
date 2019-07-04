/**
* @Title: TeacherDao.java
* @Package com.sean.dao
* @Description: TODO(教师的数据库操作)
* @author sean，wsl
* @date 2018.9.1
* @version V1.0
*/
package com.sean.dao;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import com.sean.model.ExpCourse;
import com.sean.model.Student;
import com.sean.model.Teacher;
import com.sean.tools.ConnDB;
import com.sean.tools.DateTest;
import com.sean.tools.IPv6Test;
import oracle.net.aso.s;
public class TeacherDao {
	private ConnDB conn = null;

	private Teacher return_teacher=null;
	public TeacherDao() {
		conn = new ConnDB();
		 return_teacher=new Teacher();
	}
	
	
	//教师信息增加
	public int insert(Teacher teacher) {	
		String sql = "INSERT INTO TEACHER(TID,TNUMBER,TPASSWORD,TNAME,TSEX,TPHONENUMBER,TDEPARTMENT) "
				+ "VALUES(S_S_LABSYS.Nextval,'"+teacher.getTeacherNumber()+"','"
							+teacher.getPassword()+"','"+teacher.getName()+"','"+teacher.getSex()+"','"
				            +teacher.getPhoneNumber()+"','"+teacher.getDepartment()+"')";
		System.out.println(sql);
		 return conn.executeUpdate(sql);// 执行SQL语句
		 
		
	}
	//教师信息修改
	public int update(Teacher teacher) {
		String sql = "update TEACHER set TNUMBER='"+teacher.getTeacherNumber()+"',TPASSWORD='"+teacher.getPassword()+"',"
				+ "TNAME='"+teacher.getName()+"',TSEX='"+teacher.getSex()+"',TPHONENUMBER='"+teacher.getPhoneNumber()+"',"
						+ "TDEPARTMENT='"+teacher.getDepartment()+"' where TID='"+teacher.getID()+"'";
		 return conn.executeUpdate(sql);// 执行SQL语句
	}

	//教师信息删除
	public int delete(int ID) {
		String sql = "DELETE FROM TEACHER WHERE TID="+ID;
		 return conn.executeUpdate(sql);// 执行SQL语句
	}
	
	//根据id查询教师信息
	public Teacher getById(int ID){
		Teacher tea =new Teacher();
		String sql = "SELECT * FROM TEACHER WHERE TID="+ID;
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next()){
				tea.setID(rs.getInt("TID"));
				tea.setTeacherNumber(rs.getString("TNUMBER"));
				tea.setPassword(rs.getString("TPASSWORD"));
				tea.setName(rs.getString("TNAME"));
				tea.setSex(rs.getString("TSEX"));
				tea.setPhoneNumber(rs.getString("TPHONENUMBER"));
				tea.setDepartment(rs.getString("TDEPARTMENT"));
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn.close();// 关闭数据库连接
			}
		return tea;
		
	}	
	
	
	//查询所有教师信息tea
	public ArrayList<Teacher> getTeaList(){
		ArrayList<Teacher> teachers=new ArrayList<Teacher>();
		String sql="SELECT * FROM TEACHER";
		
		
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句	
		try {
			while(rs.next()){
				Teacher tea =new Teacher();
				tea.setID(rs.getInt("TID"));
				tea.setTeacherNumber(rs.getString("TNUMBER"));
				tea.setPassword(rs.getString("TPASSWORD"));
				tea.setName(rs.getString("TNAME"));
				tea.setSex(rs.getString("TSEX"));
				tea.setPhoneNumber(rs.getString("TPHONENUMBER"));
				tea.setDepartment(rs.getString("TDEPARTMENT"));
				
				teachers.add(tea);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return teachers;
	}
	//教师信息查询显示
	public Teacher show(String teacherNumber){
		Teacher teacher=new Teacher();
		
		String sql = "SELECT * FROM TEACHER WHERE TNUMBER='"+teacherNumber+"'";
		
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		int count=0;
		try {
			while(rs.next()){
				
				teacher.setID(rs.getInt("TID"));
				teacher.setTeacherNumber(rs.getString("TNUMBER"));
				teacher.setName(rs.getString("TNAME"));
				teacher.setSex(rs.getString("TSEX"));
				teacher.setPhoneNumber(rs.getString("TPHONENUMBER"));
				teacher.setDepartment(rs.getString("TDEPARTMENT"));
     
				count++;
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn.close();// 关闭数据库连接
			}
		if(count==0) teacher=null;
		return teacher;
		
	}	
	
	// 验证用户的方法，返回值为1表示登录成功，否则表示登录失败
	public int login(Teacher teacher) {
		int flag = 0;
		String sql = "SELECT * FROM TEACHER WHERE TNUMBER='"
				+ teacher.getTeacherNumber() + "'";	
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			if (rs.next()) {
				String pwd = teacher.getPassword();// 获取用户输入的密码
				int tid = rs.getInt("TID");// 获取第一列的数据
				String truepwd=rs.getString("TPASSWORD");//获取真正的密码
				if (pwd.equals(truepwd)) {
					flag = tid;
					rs.last(); // 定位到最后一条记录
					int rowSum = rs.getRow();// 获取记录总数
					rs.first();// 定位到第一条记录
					if (rowSum != 1) {
						flag = 0;
					}	
					teacher.setID(tid);
					teacher.setName(rs.getString("TNAME"));//获取教师姓名
					teacher.setSex(rs.getString("TSEX"));//获取教师性别
					teacher.setPhoneNumber(rs.getString("TPHONENUMBER"));//获取教师电话号码
					teacher.setDepartment(rs.getString("TDEPARTMENT"));//获取教师所在学院
					teacher.setLastip(rs.getString("TLASTIP"));
					teacher.setLastdate(rs.getString("TLASTDATE"));
					//System.out.println(teacher.getID()+teacher.getName());
				} else {
					flag = 0;
				}
			} else {
				flag = 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();// 输出异常信息
			flag = 0;
		} finally {
			conn.close();// 关闭数据库连接
		}
		return flag;
	}
	public int changePassword(int tid,String old_password,String new_password){
		int flag=0;
		String sql = "SELECT * FROM TEACHER WHERE TID='"
				+ tid+ "'";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			if (rs.next()) {
				String truepwd=rs.getString("TPASSWORD");//获取真正的密码
				if (old_password.equals(truepwd)) {
					sql="UPDATE TEACHER SET TPASSWORD='"+new_password+"' WHERE TID='"+tid+"'";
					if(conn.executeUpdate(sql)==1){
						flag=1;
					}
					else{
						flag=0;
					}
				}
			 }
			}catch (SQLException ex) {
						ex.printStackTrace();// 输出异常信息
						flag = 0;
			} finally {
						conn.close();// 关闭数据库连接
			}
		return flag;
		}
	public void ip_date(int tid,HttpServletRequest request) throws IOException{
		String ip = IPv6Test.getIpAddr(request);
		String date = DateTest.Date();
		String sql = "SELECT * FROM TEACHER WHERE TID='"
				+ tid+ "'";
		conn.executeQuery(sql);// 执行SQL语句
		try {	
			
					sql="UPDATE TEACHER SET TLASTIP='"+ip+"',TLASTDATE=TO_DATE('"+date+"' ,'YYYY/MM/DD HH24:MI:SS') WHERE TID='"+tid+"'";
					//System.out.println(sql);
					conn.executeUpdate(sql);
 
			} finally {
						conn.close();// 关闭数据库连接
			}
		}
}