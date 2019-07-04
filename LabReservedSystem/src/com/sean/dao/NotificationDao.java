/**
* @Title: NotificationDao.java
* @Package com.sean.dao
* @Description: TODO(֪ͨ�����ݿ����)
* @author sean��wsl
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
	
	
	
	//ʵ���ҹ�����Ϣ����
		public int insert(Notification notification) {

			String sql = "INSERT INTO NOTIFICATION(NID,TITLE,CONTENT,PUBLICTIME,PUBLISHER) "
					+ "VALUES(S_S_LABSYS.Nextval,'"+notification.getTitle()+"','"
								+notification.getContent()+"',to_date('"+notification.getPublictime()+"','yyyy-mm-dd hh24:mi:ss'),'"+notification.getPublisher()+"')";
			System.out.println(sql);
			 return conn.executeUpdate(sql);// ִ��SQL���
			 
			
		}
		//ʵ���ҹ�����Ϣɾ��
				public int delete(int ID) {
					String sql = "DELETE FROM NOTIFICATION WHERE NID="+ID;
					 return conn.executeUpdate(sql);// ִ��SQL���
				}
		//����id��ѯ������Ϣ
				public Notification getById(int ID){
					Notification notification =new Notification();
					String sql = "SELECT * FROM NOTIFICATION WHERE NID="+ID;
					ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
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
							conn.close();// �ر����ݿ�����
						}
					return notification;
					
				}	
			
			
				//��ѯ���й�����Ϣ
				public ArrayList<Notification> getNotificationList(){
					ArrayList<Notification> notifications=new ArrayList<Notification>();
					String sql="SELECT * FROM NOTIFICATION";
					
					
					ResultSet rs = conn.executeQuery(sql);// ִ��SQL���	
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
						conn.close();// �ر����ݿ�����
					}
					return notifications;
				}
			

	
	public ArrayList<Notification> show() {
		
		ArrayList<Notification> notifications=new ArrayList<Notification>();
		String sql = "SELECT * FROM NOTIFICATION";
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
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
			conn.close();// �ر����ݿ�����
		}
		return notifications;
		
	}	
		public Notification display_detail(int nid) {		
		String sql = "SELECT NID,TITLE,CONTENT,to_char(PUBLICTIME,'yyyy-mm-dd hh24:mi:ss') AS MYPUBLICTIME,PUBLISHER FROM NOTIFICATION WHERE NID="+nid;
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
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
			conn.close();// �ر����ݿ�����
		}
		return notification;		
	}
}