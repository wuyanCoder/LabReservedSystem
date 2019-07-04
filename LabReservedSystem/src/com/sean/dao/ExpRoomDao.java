/**
* @Title: ExpRoomDao.java
* @Package com.sean.dao
* @Description: TODO(ʵ���ҵ����ݿ����)
* @author sean
* @date 2018.9.1
* @version V1.0
*/
package com.sean.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.sean.model.ExpRoom;
import com.sean.tools.ConnDB;
public class ExpRoomDao {
	private ConnDB conn = null;
	public ExpRoomDao() {
		conn = new ConnDB();
	}
	//�¼Ӵ���

	//ʵ������Ϣ����
			public int insert(ExpRoom expRoom) {	
				String sql = "INSERT INTO EXPROOM(ERID,ERADDRESS,ERTELNUM,ERSTATE,SUPERORDINATE) "
						+ "VALUES(S_S_LABSYS.Nextval,'"+expRoom.getEraddress()+"','"
									+expRoom.getErtelnum()+"','"+expRoom.getErstate()+"','"+expRoom.getSuperordinate()+"')";
				System.out.println(sql);
				 return conn.executeUpdate(sql);// ִ��SQL���
				 
				
			}
			//ʵ������Ϣ�޸�
			public int update(ExpRoom expRoom) {
				String sql = "update EXPROOM set ERADDRESS='"+expRoom.getEraddress()+"',ERTELNUM='"+expRoom.getErtelnum()
				+"',ERSTATE='"+expRoom.getErstate()
				+"',SUPERORDINATE='"+expRoom.getSuperordinate()+"' where ERID='"+expRoom.getErid()+"'";
				System.out.println(sql);
			
				return conn.executeUpdate(sql);// ִ��SQL���
			}
			//ʵ������Ϣɾ��
			public int delete(int ERID) { 
				String sql = "DELETE FROM EXPROOM WHERE ERID="+ERID;
				 return conn.executeUpdate(sql);// ִ��SQL���
			}
			//����id��ѯʵ������Ϣ
			public ExpRoom getByEId(int ERID){
				ExpRoom expRoom =new ExpRoom();
				String sql = "SELECT * FROM EXPROOM WHERE ERID="+ERID;
				ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
				try {
					while(rs.next()){
						expRoom.setErid(rs.getInt("ERID"));
						
						expRoom.setEraddress(rs.getString("ERADDRESS"));
						expRoom.setErtelnum(rs.getString("ERTELNUM"));
						expRoom.setErstate(rs.getInt("ERSTATE"));
						expRoom.setSuperordinate(rs.getString("SUPERORDINATE"));
						
						
						}
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						conn.close();// �ر����ݿ�����
					}
				return expRoom;
				
			}	
			//��ѯ����ʵ������Ϣ
			public ArrayList<ExpRoom> getExpRoomList(){
				ArrayList<ExpRoom> expRooms=new ArrayList<ExpRoom>();
				String sql="SELECT * FROM EXPROOM";

				ResultSet rs = conn.executeQuery(sql);// ִ��SQL���	
				try {
					while(rs.next()){
						ExpRoom expRoom =new ExpRoom();
						expRoom.setErid(rs.getInt("ERID"));
						
						expRoom.setEraddress(rs.getString("ERADDRESS"));
						expRoom.setErtelnum(rs.getString("ERTELNUM"));
						expRoom.setErstate(rs.getInt("ERSTATE"));
						expRoom.setSuperordinate(rs.getString("SUPERORDINATE"));
						expRooms.add(expRoom);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					conn.close();// �ر����ݿ�����
				}
				return expRooms;
			}
		
			//ʵ������Ϣ��ѯ��ʾ
			public ExpRoom show(String eraddress){
				ExpRoom expRoom=new ExpRoom();
				
				String sql = "SELECT * FROM EXPROOM WHERE ERADDRESS='"+eraddress+"'";
				
				ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
				int count=0;
				try {
					while(rs.next()){
						expRoom.setErid(rs.getInt("ERID"));
						
						expRoom.setEraddress(rs.getString("ERADDRESS"));
						expRoom.setErtelnum(rs.getString("ERTELNUM"));
						expRoom.setErstate(rs.getInt("ERSTATE"));
						expRoom.setSuperordinate(rs.getString("SUPERORDINATE"));
		     
						count++;
						}
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						conn.close();// �ر����ݿ�����
					}
				if(count==0) expRoom=null;
				return expRoom;
				
			}	
	
	//ԭ������
	public ArrayList<ExpRoom> roomstae_lite(){
		ArrayList<ExpRoom> expRooms=new ArrayList<ExpRoom>();
		String sql = "SELECT * FROM EXPROOM";
		ResultSet rs = conn.executeQuery(sql);// ִ��SQL���
		try {
			while(rs.next()){
				ExpRoom expRoom=new ExpRoom();
				expRoom.setErid(rs.getInt("ERID"));
				expRoom.setEraddress(rs.getString("ERADDRESS"));
				expRoom.setErtelnum(rs.getString("ERTELNUM"));
				expRoom.setErstate(rs.getInt("ERSTATE"));
				expRoom.setSuperordinate(rs.getString("SUPERORDINATE"));
				expRooms.add(expRoom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();// �ر����ݿ�����
		}
		return expRooms;
	}
}