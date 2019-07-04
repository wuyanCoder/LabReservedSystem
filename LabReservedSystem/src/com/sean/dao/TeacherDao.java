/**
* @Title: TeacherDao.java
* @Package com.sean.dao
* @Description: TODO(��ʦ�����ݿ����)
* @author sean��wsl
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
	
	
	//��ʦ��Ϣ����
	public int insert(Teacher teacher) {	
		String sql = "INSERT INTO TEACHER(TID,TNUMBER,TPASSWORD,TNAME,TSEX,TPHONENUMBER,TDEPARTMENT) "
				+ "VALUES(S_S_LABSYS.Nextval,'"+teacher.getTeacherNumber()+"','"
							+teacher.getPassword()+"','"+teacher.getName()+"','"+teacher.getSex()+"','"
				            +teacher.getPhoneNumber()+"','"+teacher.getDepartment()+"')";
		System.out.println(sql);
		 return conn.executeUpdate(sql);// ִ��SQL���
		 
		
	}
	//��ʦ��Ϣ�޸�
	public int update(Teacher teacher) {
		String sql = "update TEACHER set TNUMBER='"+teacher.getTeacherNumber()+"',TPASSWORD='"+teacher.getPassword()+"',"
				+ "TNAME='"+teacher.getName()+"',TSEX='"+teacher.getSex()+"',TPHONENUMBER='"+teacher.getPhoneNumber()+"',"
						+ "TDEPARTMENT='"+teacher.getDepartment()+"' where TID='"+teacher.getID()+"'";
		 return conn.executeUpdate(sql);// ִ��SQL���
	}

	//��ʦ��Ϣɾ��
	public int delete(int ID) {
		String sql = "DELETE FROM TEACHER WHERE TID="+ID;
		 return conn.executeUpdate(sql);// ִ��SQL���
	}
	
	//����id��ѯ��ʦ��Ϣ
	public Teacher getById(int ID){
		Teacher tea =new Teacher();
		String sql = "SELECT * FROM TEACHER WHERE TID="+ID;
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
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
				conn.close();// �ر����ݿ�����
			}
		return tea;
		
	}	
	
	
	//��ѯ���н�ʦ��Ϣtea
	public ArrayList<Teacher> getTeaList(){
		ArrayList<Teacher> teachers=new ArrayList<Teacher>();
		String sql="SELECT * FROM TEACHER";
		
		
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���	
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
			conn.close();// �ر����ݿ�����
		}
		return teachers;
	}
	//��ʦ��Ϣ��ѯ��ʾ
	public Teacher show(String teacherNumber){
		Teacher teacher=new Teacher();
		
		String sql = "SELECT * FROM TEACHER WHERE TNUMBER='"+teacherNumber+"'";
		
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
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
				conn.close();// �ر����ݿ�����
			}
		if(count==0) teacher=null;
		return teacher;
		
	}	
	
	// ��֤�û��ķ���������ֵΪ1��ʾ��¼�ɹ��������ʾ��¼ʧ��
	public int login(Teacher teacher) {
		int flag = 0;
		String sql = "SELECT * FROM TEACHER WHERE TNUMBER='"
				+ teacher.getTeacherNumber() + "'";	
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
		try {
			if (rs.next()) {
				String pwd = teacher.getPassword();// ��ȡ�û����������
				int tid = rs.getInt("TID");// ��ȡ��һ�е�����
				String truepwd=rs.getString("TPASSWORD");//��ȡ����������
				if (pwd.equals(truepwd)) {
					flag = tid;
					rs.last(); // ��λ�����һ����¼
					int rowSum = rs.getRow();// ��ȡ��¼����
					rs.first();// ��λ����һ����¼
					if (rowSum != 1) {
						flag = 0;
					}	
					teacher.setID(tid);
					teacher.setName(rs.getString("TNAME"));//��ȡ��ʦ����
					teacher.setSex(rs.getString("TSEX"));//��ȡ��ʦ�Ա�
					teacher.setPhoneNumber(rs.getString("TPHONENUMBER"));//��ȡ��ʦ�绰����
					teacher.setDepartment(rs.getString("TDEPARTMENT"));//��ȡ��ʦ����ѧԺ
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
			ex.printStackTrace();// ����쳣��Ϣ
			flag = 0;
		} finally {
			conn.close();// �ر����ݿ�����
		}
		return flag;
	}
	public int changePassword(int tid,String old_password,String new_password){
		int flag=0;
		String sql = "SELECT * FROM TEACHER WHERE TID='"
				+ tid+ "'";
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
		try {
			if (rs.next()) {
				String truepwd=rs.getString("TPASSWORD");//��ȡ����������
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
						ex.printStackTrace();// ����쳣��Ϣ
						flag = 0;
			} finally {
						conn.close();// �ر����ݿ�����
			}
		return flag;
		}
	public void ip_date(int tid,HttpServletRequest request) throws IOException{
		String ip = IPv6Test.getIpAddr(request);
		String date = DateTest.Date();
		String sql = "SELECT * FROM TEACHER WHERE TID='"
				+ tid+ "'";
		conn.executeQuery(sql);// ִ��SQL���
		try {	
			
					sql="UPDATE TEACHER SET TLASTIP='"+ip+"',TLASTDATE=TO_DATE('"+date+"' ,'YYYY/MM/DD HH24:MI:SS') WHERE TID='"+tid+"'";
					//System.out.println(sql);
					conn.executeUpdate(sql);
 
			} finally {
						conn.close();// �ر����ݿ�����
			}
		}
}