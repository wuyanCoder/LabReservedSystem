/**
 * @Title: ExpApplication.java
 * @Package com.sean.model
 * @Description: TODO(ʵ���״̬��)
 * @author wsl,sean
 * @date 2018.9.15
 * @version V1.0
 */
package com.sean.model;
public class ExpApplication {
	private int sid=0;
	private int cid=0;
	private String eaTime ="";
	private int eaState =-1;
	private String eaResult="";
	private double leadership=0;
	private double teamwork=0;
	private double apprehension=0;
	private double practice=0;
	private double analysis=0;
	private double innovation=0;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getEaTime() {
		return eaTime;
	}
	public void setEaTime(String eaTime) {
		this.eaTime = eaTime;
	}
	public int getEaState() {
		return eaState;
	}
	public void setEaState(int eaState) {
		this.eaState = eaState;
	}
	public String getEaResult() {
		return eaResult;
	}
	public void setEaResult(String eaResult) {
		this.eaResult = eaResult;
	}
	/**
	 * @return leadership
	 */
	public double getLeadership() {
		return leadership;
	}
	/**
	 * @param leadership Ҫ���õ� leadership
	 */
	public void setLeadership(double leadership) {
		this.leadership = leadership;
	}
	/**
	 * @return teamwork
	 */
	public double getTeamwork() {
		return teamwork;
	}
	/**
	 * @param teamwork Ҫ���õ� teamwork
	 */
	public void setTeamwork(double teamwork) {
		this.teamwork = teamwork;
	}
	/**
	 * @return apprehension
	 */
	public double getApprehension() {
		return apprehension;
	}
	/**
	 * @param apprehension Ҫ���õ� apprehension
	 */
	public void setApprehension(double apprehension) {
		this.apprehension = apprehension;
	}
	/**
	 * @return practice
	 */
	public double getPractice() {
		return practice;
	}
	/**
	 * @param practice Ҫ���õ� practice
	 */
	public void setPractice(double practice) {
		this.practice = practice;
	}
	/**
	 * @return analysis
	 */
	public double getAnalysis() {
		return analysis;
	}
	/**
	 * @param analysis Ҫ���õ� analysis
	 */
	public void setAnalysis(double analysis) {
		this.analysis = analysis;
	}
	/**
	 * @return innovation
	 */
	public double getInnovation() {
		return innovation;
	}
	/**
	 * @param innovation Ҫ���õ� innovation
	 */
	public void setInnovation(double innovation) {
		this.innovation = innovation;
	}
}