package com.lewei.model;

/**
 * 产线模型
 * 
 * @author djzhao
 * @time 2015年9月25日
 */
public class TPLine {

	/** 主键 */
	private int TPLineID;

	/** 外键，关联生产计划表（TPPlanTable） */
	private int TPPlanID;

	/** 产线名称 */
	private String TPLineName;

	/** 班次（0为早班，1为中班，2为晚班） */
	private int Ranger;

	/** 计划产量 */
	private int PlanNum;

	/** 实际产量 */
	private int RealNum;

	/** 中间休息时间（格式如：10：00-10：10;12：30-13：00）中间用 ';' 区分不同时间段 */
	private String RestTime;

	/** 换班时间 */
	private String ChangeTime;

	/** 损失的时间 */
	private String LostTime;

	/** 工站位，不可为空（格式如：1,2,3,4,5） */
	private String WorkStations;

	/** 产线开始时间 */
	private String StartTime;

	/** 产线结束时间 */
	private String EndTime;

	/** 产线状态，默认1 */
	private int Status;

	public int getTPLineID() {
		return TPLineID;
	}

	public void setTPLineID(int tPLineID) {
		TPLineID = tPLineID;
	}

	public int getTPPlanID() {
		return TPPlanID;
	}

	public void setTPPlanID(int tPPlanID) {
		TPPlanID = tPPlanID;
	}

	public String getTPLineName() {
		return TPLineName;
	}

	public void setTPLineName(String tPLineName) {
		TPLineName = tPLineName;
	}

	public int getRanger() {
		return Ranger;
	}

	public void setRanger(int ranger) {
		Ranger = ranger;
	}

	public int getPlanNum() {
		return PlanNum;
	}

	public void setPlanNum(int planNum) {
		PlanNum = planNum;
	}

	public int getRealNum() {
		return RealNum;
	}

	public void setRealNum(int realNum) {
		RealNum = realNum;
	}

	public String getRestTime() {
		return RestTime;
	}

	public void setRestTime(String restTime) {
		RestTime = restTime;
	}

	public String getChangeTime() {
		return ChangeTime;
	}

	public void setChangeTime(String changeTime) {
		ChangeTime = changeTime;
	}

	public String getLostTime() {
		return LostTime;
	}

	public void setLostTime(String lostTime) {
		LostTime = lostTime;
	}

	public String getWorkStations() {
		return WorkStations;
	}

	public void setWorkStations(String workStations) {
		WorkStations = workStations;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
