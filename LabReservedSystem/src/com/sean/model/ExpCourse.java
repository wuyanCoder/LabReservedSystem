/**
 * @Title: ExpCourse.java
 * @Package com.sean.model
 * @Description: TODO(ÊµÑé¿ÎÀà)
 * @author wsl,sean
 * @date 2018.9.15
 * @version V1.0
 */
package com.sean.model;
public class ExpCourse {
	private int cid=0;
	private String title="";
	private String content="";
	private String expTime="";
	private int expStatus=0;
	private String publicTime="";
	private int tid=0;
	private int erid=0;
	private int aid=0;
	private String abilityFocus="000000";
	private String tname="";
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	public int getExpStatus() {
		return expStatus;
	}
	public void setExpStatus(int expStatus) {
		this.expStatus = expStatus;
	}
	public String getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getErid() {
		return erid;
	}
	public void setErid(int erid) {
		this.erid = erid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAbilityFocus() {
		return abilityFocus;
	}
	public void setAbilityFocus(String abilityFocus) {
		this.abilityFocus = abilityFocus;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
}