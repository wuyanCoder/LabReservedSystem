/**
 * @Title: ExpEquipment.java
 * @Package com.sean.model
 * @Description: TODO(实验设备类)
 * @author sean
 * @date 2018.9.15
 * @version V1.0
 */
package com.sean.model;
public class ExpEquipment {
	private int eqid=0;
	private String eqname="";
	private int eqstate=0;
	private int erid=0;	
	public int getEqid() {
		return eqid;
	}
	public void setEqid(int eqid) {
		this.eqid = eqid;
	}
	public String getEqname() {
		return eqname;
	}
	public void setEqname(String eqname) {
		this.eqname = eqname;
	}
	public int getEqstate() {
		return eqstate;
	}
	public void setEqstate(int eqstate) {
		this.eqstate = eqstate;
	}
	public int getErid() {
		return erid;
	}
	public void setErid(int erid) {
		this.erid = erid;
	}	
}