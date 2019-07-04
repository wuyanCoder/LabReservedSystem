/**
 * @Title: Student.java
 * @Package com.sean.model
 * @Description: TODO(—ß…˙¿‡)
 * @author sean£¨wsl
 * @date 2018.9.15
 * @version V1.0
 */
package com.sean.model;
public class Student {
	private int ID=0;
	private String studentNumber="";
	private String password="";
	private String name="";
	private String sex="";
	private String department="";
	private String s_class="";
	private String telnum="";
	
	
	
//	private int s_grade=1;
//	private double leadership=0;
//	private double teamwork=0;
//	private double apprehension=0;
//	private double practice=0;
//	private double analysis=0;
//	private double innovation=0;
	private String lastdate="";
	private String lastip="::";
//	public Student(){
//		w=new double [6];
//	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getS_class() {
		return s_class;
	}
	public void setS_class(String s_class) {
		this.s_class = s_class;
	}
	
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
//	public double[] getW() {
//		return w;
//	}
//	public void setW(double[] w) {
//		this.w = w;
//	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
//	private double w[];
//	@Override
//	public int compareTo(Student o) {
//		// TODO Auto-generated method stub
//		double o1=w[0]*leadership+w[1]*teamwork+w[2]*apprehension+w[3]*practice+w[4]*analysis+w[5]* innovation;
//		double o2=w[0]*o.leadership+w[1]*o.teamwork+w[2]*o.apprehension+w[3]*o.practice+w[4]*o.analysis+w[5]* o.innovation;
//		if(o1<o2) return 1;
//		else if(o1==o2) return 0;
//		else if(o1>o2) return -1;
//		return 0;
//	}
}