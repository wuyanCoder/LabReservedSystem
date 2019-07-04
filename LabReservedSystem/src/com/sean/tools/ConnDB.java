package com.sean.tools;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/** 
* @ClassName: ConnDB 
* @Description: TODO(���ݿ���ز���) 
* @author sean��wsl 
* @email:364672554@qq.com,616272735@qq.com
* @date 2017��12��6�� ����7:39:28 
*  
*/
public class ConnDB {
	public Connection conn=null;				//����Connection�����ʵ��
	public Statement stmt=null;	               //����Statement�����ʵ��
	public ResultSet rs=null;               //����ResultSet�����ʵ��
	private static String propFileName="connDB.properties"; //ָ����Դ�ļ������λ��
	private static Properties prop=new Properties();   			//������ʵ����Properties�����ʵ��
	private static String dbClassName="oracle.jdbc.driver.OracleDriver";//���岢�������ݿ������ı���
	private static String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl";
	private static String jdbcIpv6Url = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = orcl.lan)))";

//	private static String jdbcIpv6Url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=orcl)))";
	private static String dbUsername="C##WSL";
	private static String dbPassword="1234";
	public ConnDB(){
		try{
			InputStream in=getClass().getResourceAsStream(propFileName);
			prop.load(in);//ͨ���������������propeties�ļ�
			dbClassName=prop.getProperty("DB_CLASS_NAME",dbClassName);
			dbUrl=prop.getProperty("DB_URL",jdbcIpv6Url);
			dbUsername=prop.getProperty("DB_USERNAME",dbUsername);
			dbPassword=prop.getProperty("DB_PASSWORD",dbPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/** 
	* @Title: getConnection 
	* @Description: TODO(�������ݿ�) 
	* @param @return   conn
	* @return Connection    �������� 
	* @throws 
	*/
	public static Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName(dbClassName).newInstance();//�������ݿ�����
			conn=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);//�������ݿ�����
		}catch(Exception e){
			e.printStackTrace();
		}
		if(conn==null){
			System.err.println("���棺������ݿ�����ʧ��.\r\n �������ͣ�"+dbClassName+"\r\n ����λ�ã�"+dbUrl);
		}
		return conn;
	}
	/** 
	* @Title: executeQuery 
	* @Description: TODO(��ѯ���ݿ����) 
	* @param  sql
	* @return ResultSet    �������� 
	* @throws 
	*/
	public ResultSet executeQuery(String sql){
		try {		
			conn=getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;	
	}
	/** 
	* @Title: executeUpdate 
	* @Description: TODO(�������ݿ����) 
	* @param sql 
	* @return int  �������� 
	* @throws 
	*/
	public int executeUpdate(String sql){
		int result=0;
		try {	
			conn=getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			result=0;
		}
		return result;
	}
	public void close(){
		try{
			if(rs!=null)
			{
				rs.close();
			}
			if(stmt!=null)
			{
				stmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
}