/**
 * @Title: Administrator.java
 * @Package com.sean.model
 * @Description: TODO(����Ա��)
 * @author wsl,sean
 * @date 2018.9.15
 * @version V1.0
 */
package com.sean.model;
//AID,ANUMBER,APASSWORD,ANAME,ASEX,ADEPARTMENT,AEMAIL,APHONE
public class Administrator {
	private int ID=0;
	private String adminNumber="";
	private String password="";
	private String name="";
	private String sex="";
	private String phoneNumber="";
	private String email="";
	private String department="";
	private String lastdate="";
	private String lastip="::";
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
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
	
}