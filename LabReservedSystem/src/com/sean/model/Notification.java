/**
 * @Title: Notification.java
 * @Package com.sean.model
 * @Description: TODO(Í¨ÖªÀà)
 * @author sean
 * @date 2018.9.15
 * @version V1.0
 */
package com.sean.model;
public class Notification {
	private int nid=0;
	private String title="";
	private String content="";
	private String publictime="";
	private String publisher="";
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
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
	public String getPublictime() {
		return publictime;
	}
	public void setPublictime(String publictime) {
		this.publictime = publictime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}