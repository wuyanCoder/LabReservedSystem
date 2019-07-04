/**
 * @Title: ExpRoom.java
 * @Package com.sean.model
 * @Description: TODO(ÊµÑéÊÒÀà)
 * @author sean
 * @date 2018.9.15
 * @version V1.0
 */
package com.sean.model;
public class ExpRoom {
	private int erid=0;	
	private String eraddress="";
	private String ertelnum="";
	private int erstate=0;
	private String superordinate="";
	public int getErid() {
		return erid;
	}
	public void setErid(int erid) {
		this.erid = erid;
	}
	public String getEraddress() {
		return eraddress;
	}
	public void setEraddress(String eraddress) {
		this.eraddress = eraddress;
	}
	public String getErtelnum() {
		return ertelnum;
	}
	public void setErtelnum(String ertelnum) {
		this.ertelnum = ertelnum;
	}
	public int getErstate() {
		return erstate;
	}
	public void setErstate(int erstate) {
		this.erstate = erstate;
	}
	public String getSuperordinate() {
		return superordinate;
	}
	public void setSuperordinate(String superordinate) {
		this.superordinate = superordinate;
	}
}