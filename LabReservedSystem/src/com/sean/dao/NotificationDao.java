/**
* @Title: NotificationDao.java
* @Package com.sean.dao
* @Description: TODO(通知的数据库操作)
* @author sean，wsl
* @date 2018.9.15
* @version V1.0
*/
package com.sean.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.sean.tools.ConnDB;
import com.sean.model.Notification;
import com.sean.model.Student;
import com.sean.model.Teacher;
public class NotificationDao {
	private ConnDB conn = null;
	public NotificationDao() {
		conn = new ConnDB();
	}
	
	
	
	//实验室公告信息增加
		public int insert(Notification notification) {

			String sql = "INSERT INTO NOTIFICATION(NID,TITLE,CONTENT,PUBLICTIME,PUBLISHER) "
					+ "VALUES(S_S_LABSYS.Nextval,'"+notification.getTitle()+"','"
								+notification.getContent()+"',to_date('"+notification.getPublictime()+"','yyyy-mm-dd hh24:mi:ss'),'"+notification.getPublisher()+"')";
			System.out.println(sql);
			 return conn.executeUpdate(sql);// 执行SQL语句
			 
			
		}
		//实验室公告信息删除
				public int delete(int ID) {
					String sql = "DELETE FROM NOTIFICATION WHERE NID="+ID;
					 return conn.executeUpdate(sql);// 执行SQL语句
				}
		//根据id查询公告信息
				public Notification getById(int ID){
					Notification notification =new Notification();
					String sql = "SELECT * FROM NOTIFICATION WHERE NID="+ID;
					ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
					try {
						while(rs.next()){
							notification.setNid(rs.getInt("NID"));
							notification.setTitle(rs.getString("TITLE"));
							notification.setContent(rs.getString("CONTENT"));
							
							
							}
						}catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							conn.close();// 关闭数据库连接
						}
					return notification;
					
				}	
			
			
				//查询所有公告信息
				public ArrayList<Notification> getNotificationList(){
					ArrayList<Notification> notifications=new ArrayList<Notification>();
					String sql="SELECT * FROM NOTIFICATION";
					
					
					ResultSet rs = conn.executeQuery(sql);// 执行SQL语句	
					try {
						while(rs.next()){
							Notification notification =new Notification();
							notification.setNid(rs.getInt("NID"));
							notification.setTitle(rs.getString("TITLE"));
							notification.setContent(rs.getString("CONTENT"));
							notifications.add(notification);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						conn.close();// 关闭数据库连接
					}
					return notifications;
				}
			

	
	public ArrayList<Notification> show() {
		
		ArrayList<Notification> notifications=new ArrayList<Notification>();
		String sql = "SELECT * FROM NOTIFICATION";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		try {
			while(rs.next()){
				Notification notification=new Notification();
				notification.setNid(rs.getInt("NID"));
				notification.setTitle(rs.getString("TITLE"));
				notification.setContent(rs.getString("CONTENT"));
				notification.setPublictime(rs.getString("PUBLICTIME"));
				notification.setPublisher(rs.getString("PUBLISHER"));
				notifications.add(notification);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return notifications;
		
	}	
		public Notification display_detail(int nid) {		
		String sql = "SELECT NID,TITLE,CONTENT,to_char(PUBLICTIME,'yyyy-mm-dd hh24:mi:ss') AS MYPUBLICTIME,PUBLISHER FROM NOTIFICATION WHERE NID="+nid;
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
		Notification notification=new Notification();
		try {
			while(rs.next()){
				notification.setNid(rs.getInt("NID"));
				notification.setTitle(rs.getString("TITLE"));
				notification.setContent(rs.getString("CONTENT"));
				notification.setPublictime(rs.getString("MYPUBLICTIME"));
				notification.setPublisher(rs.getString("PUBLISHER"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// 关闭数据库连接
		}
		return notification;		
	}
}