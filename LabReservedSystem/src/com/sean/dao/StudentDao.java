/**
* @Title: StudentDao.java
* @Package com.sean.dao
* @Description: TODO(学生的数据库操作)
* @author sean，wsl
* @date 2018.9.15
* @version V1.0
*/
package com.sean.dao;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import com.sean.model.ExpApplication;
import com.sean.model.Notification;
import com.sean.model.Student;
import com.sean.model.Teacher;
import com.sean.tools.ConnDB;
import com.sean.tools.DateTest;
import com.sean.tools.GetIPv6Address;
import com.sean.tools.IPv6Test;
import oracle.net.aso.s;
public class StudentDao {
	private ConnDB conn = null;
	private double weight_func(double x){
		return Math.log(200*1.0/x-1);
	}
	public StudentDao() {
		conn = new ConnDB();
	}
	
	//学生信息增加
		public int insert(Student student) {	
			String sql = "INSERT INTO STUDENT(SID,SNUMBER,SPASSWORD,SNAME,SSEX,SDEPARTMENT,SCLASS,STELNUM) "
					+ "VALUES(S_S_LABSYS.Nextval,'"+student.getStudentNumber()+"','"
								+student.getPassword()+"','"+student.getName()+"','"+student.getSex()+"','"
					            +student.getDepartment()+"','"+student.getS_class()+"','"+student.getTelnum()+"')";
			System.out.println(sql);
			 return conn.executeUpdate(sql);// 执行SQL语句
			 
			
		}
		//学生信息修改
		public int update(Student student) {
			String sql = "update STUDENT set SNUMBER='"+student.getStudentNumber()+"',SPASSWORD='"+student.getPassword()+"',"
					+ "SNAME='"+student.getName()+"',SSEX='"+student.getSex()+"',SDEPARTMENT='"+student.getDepartment()+"',"
							+ "SCLASS='"+student.getS_class()+"',STELNUM='"+student.getTelnum()+"' where SID='"+student.getID()+"'";
			System.out.println(sql);
		
			return conn.executeUpdate(sql);// 执行SQL语句
		}
		//学生信息删除
		public int delete(int ID) {
			String sql = "DELETE FROM STUDENT WHERE SID="+ID;
			 return conn.executeUpdate(sql);// 执行SQL语句
		}
		
		//根据id查询学生信息
		public Student getById(int ID){
			Student student =new Student();
			String sql = "SELECT * FROM STUDENT WHERE SID="+ID;
			ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
			try {
				while(rs.next()){
					student.setID(rs.getInt("SID"));
					student.setStudentNumber(rs.getString("SNUMBER"));
					student.setPassword(rs.getString("SPASSWORD"));
					student.setName(rs.getString("SNAME"));
					student.setSex(rs.getString("SSEX"));
					student.setDepartment(rs.getString("SDEPARTMENT"));
					student.setS_class(rs.getString("SCLASS"));
					student.setTelnum(rs.getString("STELNUM"));
					
					}
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					conn.close();// 关闭数据库连接
				}
			return student;
			
		}	
	
	
		//查询所有学生信息
		public ArrayList<Student> getStudentList(){
			ArrayList<Student> students=new ArrayList<Student>();
			String sql="SELECT * FROM STUDENT";
			
			
			ResultSet rs = conn.executeQuery(sql);// 执行SQL语句	
			try {
				while(rs.next()){
					Student student =new Student();
					student.setID(rs.getInt("SID"));
					student.setStudentNumber(rs.getString("SNUMBER"));
					student.setPassword(rs.getString("SPASSWORD"));
					student.setName(rs.getString("SNAME"));
					student.setSex(rs.getString("SSEX"));
					student.setDepartment(rs.getString("SDEPARTMENT"));
					student.setS_class(rs.getString("SCLASS"));
					student.setTelnum(rs.getString("STELNUM"));
					students.add(student);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn.close();// 关闭数据库连接
			}
			return students;
		}
	

	
	
	// 验证用户的方法，返回值为1表示登录成功，否则表示登录失败
	public int login(Student student) {
		int flag = 0;
		String sql = "SELECT * FROM STUDENT WHERE SNUMBER='"
				+ student.getStudentNumber() + "'";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			if (rs.next()) {
				String pwd = student.getPassword();// 获取用户输入的密码
				int sid = rs.getInt("SID");// 获取第一列的数据
				String truepwd=rs.getString("SPASSWORD");//获取真正的密码
				if (pwd.equals(truepwd)) {
					flag = sid;
					rs.last(); // 定位到最后一条记录
					int rowSum = rs.getRow();// 获取记录总数
					rs.first();// 定位到第一条记录
					if (rowSum != 1) {
						flag = 0;
					}
					student.setID(sid);
					student.setName(rs.getString("SNAME"));//获取学生姓名
					student.setSex(rs.getString("SSEX"));//获取学生性别
					student.setDepartment(rs.getString("SDEPARTMENT"));//获取学生所在学院
					student.setS_class(rs.getString("SCLASS"));//获取学生所在班级
//					student.setS_grade(rs.getInt("SGRADE"));//获取学生所在年级
					student.setTelnum(rs.getString("STELNUM"));//获取学生联系方式
//					student.setLeadership(rs.getDouble("LEADERSHIP"));
//					student.setTeamwork(rs.getDouble("TEAMWORK"));
//					student.setApprehension(rs.getDouble("APPREHENSION"));
//					student.setPractice(rs.getDouble("PRACTICE"));
//					student.setAnalysis(rs.getDouble("ANALYSIS"));
//					student.setInnovation(rs.getDouble("INNOVATION"));
					student.setLastip(rs.getString("SLASTIP"));
					student.setLastdate(rs.getString("SLASTDATE"));
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
	public int changePassword(int sid,String old_password,String new_password){
		int flag=0;
		String sql = "SELECT * FROM STUDENT WHERE SID='"
				+ sid+ "'";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			if (rs.next()) {
				String truepwd=rs.getString("SPASSWORD");//获取真正的密码
				if (old_password.equals(truepwd)) {
					sql="UPDATE STUDENT SET SPASSWORD='"+new_password+"' WHERE SID='"+sid+"'";
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
	public ArrayList<ExpApplication> get_applied(int sid){
		ArrayList<ExpApplication> expApplications=new ArrayList<ExpApplication>();
		String sql = "SELECT * FROM expapplication WHERE SID='"+sid+"'";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next()){
				ExpApplication expApplication=new ExpApplication();
				expApplication.setSid(rs.getInt("SID"));
				expApplication.setCid(rs.getInt("CID"));
				expApplication.setEaTime(rs.getString("EATIME"));
				expApplication.setEaState(rs.getInt("EASTATE"));
				expApplication.setEaResult(rs.getString("EARESULT"));
				expApplications.add(expApplication);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return expApplications;
		
	}
	public Student show(String studentNumber){
		Student student =new Student();
		String sql = "SELECT * FROM STUDENT WHERE SNUMBER='"+studentNumber+"'";
		
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		int count=0;
		try {
			while(rs.next()){
				student.setID(rs.getInt("SID"));
				student.setStudentNumber(rs.getString("SNUMBER"));
				student.setName(rs.getString("SNAME"));
				student.setSex(rs.getString("SSEX"));
				student.setDepartment(rs.getString("SDepartment"));
				student.setS_class(rs.getString("SCLASS"));
				student.setTelnum(rs.getString("STELNUM"));//获取学生联系方式
//				student.setS_grade(rs.getInt("SGRADE"));
//			
//				student.setLeadership(rs.getDouble("LEADERSHIP"));
//				student.setTeamwork(rs.getDouble("TEAMWORK"));
//				student.setApprehension(rs.getDouble("APPREHENSION"));
//				student.setPractice(rs.getDouble("PRACTICE"));
//				student.setAnalysis(rs.getDouble("ANALYSIS"));
//				student.setInnovation(rs.getDouble("INNOVATION"));
				count++;
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn.close();// 关闭数据库连接
			}
		if(count==0) student=null;
		return student;
		
	}	
	/*public ArrayList<Student> match(int sid){
		double w[]=new double [6];
		ArrayList<Student> students=new ArrayList<Student>();
		String sql="SELECT * FROM STUDENT WHERE SID="+sid;
		ResultSet self_rs = conn.executeQuery(sql);// 执行SQL语句
		
		try {
			while(self_rs.next()){
				w[0]=weight_func(self_rs.getDouble("LEADERSHIP"));
				w[1]=weight_func(self_rs.getDouble("TEAMWORK"));
				w[2]=weight_func(self_rs.getDouble("APPREHENSION"));
				w[3]=weight_func(self_rs.getDouble("PRACTICE"));
				w[4]=weight_func(self_rs.getDouble("ANALYSIS"));
				w[5]=weight_func(self_rs.getDouble("INNOVATION"));
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		sql = "SELECT * FROM STUDENT WHERE SID!="+sid;
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句	
		try {
			while(rs.next()){
				Student student =new Student();
				student.setID(rs.getInt("SID"));
				student.setStudentNumber(rs.getString("SNUMBER"));
				student.setName(rs.getString("SNAME"));
				student.setSex(rs.getString("SSEX"));
				student.setDepartment(rs.getString("SDepartment"));
				student.setS_class(rs.getString("SCLASS"));
//				student.setS_grade(rs.getInt("SGRADE"));
//				student.setLeadership(rs.getDouble("LEADERSHIP"));
//				student.setTeamwork(rs.getDouble("TEAMWORK"));
//				student.setApprehension(rs.getDouble("APPREHENSION"));
//				student.setPractice(rs.getDouble("PRACTICE"));
//				student.setAnalysis(rs.getDouble("ANALYSIS"));
//				student.setInnovation(rs.getDouble("INNOVATION"));
				student.setTelnum(rs.getString("STELNUM"));//获取学生联系方式
//				student.setW(w);
				students.add(student);
			}
//			 Collections.sort(students);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return students;
	}	*/
	public void ip_date(int sid,HttpServletRequest request) throws IOException{
		String ip = IPv6Test.getIpAddr(request);
		String date = DateTest.Date();
		String sql = "SELECT * FROM STUDENT WHERE SID='"
				+ sid+ "'";
		conn.executeQuery(sql);// 执行SQL语句
		try {	
			
					sql="UPDATE STUDENT SET SLASTIP='"+ip+"',SLASTDATE=TO_DATE('"+date+"' ,'YYYY/MM/DD HH24:MI:SS') WHERE SID='"+sid+"'";
					//System.out.println(sql);
					conn.executeUpdate(sql);
 
			} finally {
						conn.close();// 关闭数据库连接
			}
		}
}