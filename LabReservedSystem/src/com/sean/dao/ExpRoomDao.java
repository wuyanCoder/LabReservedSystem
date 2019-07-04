/**
* @Title: ExpRoomDao.java
* @Package com.sean.dao
* @Description: TODO(实验室的数据库操作)
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
	//新加代码

	//实验室信息增加
			public int insert(ExpRoom expRoom) {	
				String sql = "INSERT INTO EXPROOM(ERID,ERADDRESS,ERTELNUM,ERSTATE,SUPERORDINATE) "
						+ "VALUES(S_S_LABSYS.Nextval,'"+expRoom.getEraddress()+"','"
									+expRoom.getErtelnum()+"','"+expRoom.getErstate()+"','"+expRoom.getSuperordinate()+"')";
				System.out.println(sql);
				 return conn.executeUpdate(sql);// 执行SQL语句
				 
				
			}
			//实验室信息修改
			public int update(ExpRoom expRoom) {
				String sql = "update EXPROOM set ERADDRESS='"+expRoom.getEraddress()+"',ERTELNUM='"+expRoom.getErtelnum()
				+"',ERSTATE='"+expRoom.getErstate()
				+"',SUPERORDINATE='"+expRoom.getSuperordinate()+"' where ERID='"+expRoom.getErid()+"'";
				System.out.println(sql);
			
				return conn.executeUpdate(sql);// 执行SQL语句
			}
			//实验室信息删除
			public int delete(int ERID) { 
				String sql = "DELETE FROM EXPROOM WHERE ERID="+ERID;
				 return conn.executeUpdate(sql);// 执行SQL语句
			}
			//根据id查询实验室信息
			public ExpRoom getByEId(int ERID){
				ExpRoom expRoom =new ExpRoom();
				String sql = "SELECT * FROM EXPROOM WHERE ERID="+ERID;
				ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
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
						conn.close();// 关闭数据库连接
					}
				return expRoom;
				
			}	
			//查询所有实验室信息
			public ArrayList<ExpRoom> getExpRoomList(){
				ArrayList<ExpRoom> expRooms=new ArrayList<ExpRoom>();
				String sql="SELECT * FROM EXPROOM";

				ResultSet rs = conn.executeQuery(sql);// 执行SQL语句	
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
					conn.close();// 关闭数据库连接
				}
				return expRooms;
			}
		
			//实验室信息查询显示
			public ExpRoom show(String eraddress){
				ExpRoom expRoom=new ExpRoom();
				
				String sql = "SELECT * FROM EXPROOM WHERE ERADDRESS='"+eraddress+"'";
				
				ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
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
						conn.close();// 关闭数据库连接
					}
				if(count==0) expRoom=null;
				return expRoom;
				
			}	
	
	//原来代码
	public ArrayList<ExpRoom> roomstae_lite(){
		ArrayList<ExpRoom> expRooms=new ArrayList<ExpRoom>();
		String sql = "SELECT * FROM EXPROOM";
		ResultSet rs = conn.executeQuery(sql);// 执行SQL语句
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
			conn.close();// 关闭数据库连接
		}
		return expRooms;
	}
}