/**
* @Title: ExpCourseDao.java
* @Package com.sean.dao
* @Description: TODO(实验课程的数据库操作)
* @author sean
* @date 2018.9.1
* @version V1.0
*/
package com.sean.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.sean.model.ExpCourse;
import com.sean.model.Notification;
import com.sean.tools.ConnDB;
public class ExpCourseDao {
	private ConnDB conn = null;
	public ExpCourseDao() {
		conn = new ConnDB();
	}
	public ArrayList<ExpCourse> show() {	
		ArrayList<ExpCourse> expCourses=new ArrayList<ExpCourse>();
		String sql = "SELECT * FROM EXPCOURSE";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next()){
				if(rs.getInt("EXPSTATUS")==0){
					continue;
				}
				if (rs.getInt("EXPSTATUS")==-1)
				{
					continue;
				}
				ExpCourse expCourse=new ExpCourse();
				expCourse.setCid(rs.getInt("CID"));
				expCourse.setTitle(rs.getString("TITLE"));
				expCourse.setContent(rs.getString("CONTENT"));
				expCourse.setExpTime(rs.getString("EXPTIME"));
				if(rs.getInt("EXPSTATUS")==0){
					expCourse.setExpStatus(0);
				}
				else if (rs.getInt("EXPSTATUS")==-1)
				{
					expCourse.setExpStatus(-1);
				}
				else {
					expCourse.setExpStatus(1);
				}
				expCourse.setPublicTime(rs.getString("PUBLICTIME"));
				//待续。。。。。。
				expCourses.add(expCourse);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return expCourses;		
	}
	public int insert(ExpCourse e) {	
		String sql = "INSERT INTO EXPCOURSE(CID,TITLE,CONTENT,EXPTIME,EXPSTATUS,TID,ERID,AID) "
				+ "VALUES(S_S_LABSYS.Nextval,'"+e.getTitle()+"','"+e.getContent()+"',to_date('"+e.getExpTime()+"','yyyy-mm-dd hh24:mi:ss'),0,"
						+e.getTid()+","+e.getErid()+","+e.getAid()+")";
		System.out.println(sql);
		 return conn.executeUpdate(sql);// 执行SQL语句
	}
	public ExpCourse display_detail(int cid) {	
		String sql = "SELECT CID,TITLE,CONTENT,to_char(PUBLICTIME,'yyyy-mm-dd hh24:mi:ss') AS MYPUBLICTIME,to_char(EXPTIME,'yyyy-mm-dd hh24:mi:ss') AS MYEXPTIME,TNAME FROM EXPCOURSE,TEACHER WHERE CID="+cid +" AND EXPCOURSE.TID=TEACHER.TID";	
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		ExpCourse excourse=new ExpCourse();
		try {
			while(rs.next()){
				excourse.setCid(rs.getInt("CID"));
				excourse.setTitle(rs.getString("TITLE"));
				excourse.setContent(rs.getString("CONTENT"));
				excourse.setPublicTime(rs.getString("MYPUBLICTIME"));
				excourse.setExpTime(rs.getString("MYEXPTIME"));
				excourse.setTname(rs.getString("TNAME"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return excourse;	
	}
}