package com.lewei.model;

/**
 * 产线计划模型
 * 
 * @author djzhao
 * @time 2015年9月25日
 */
public class TPPlan {

	/** 生产计划表主键 */
	private int TPPlanID;

	/** 总产量（默认设定为100） */
	private int TotalNum;

	/** 产线名 */
	private String TPLineName;

	/** 班次，默认为0，0为早班，1为中班，2为晚班 */
	private int Ranger;

	/** 创建时间 */
	private String CreateTme;

	/** 创建人 */
	private String CreatePeople;

	/** 状态列（默认状态设置为1） */
	private int Status;

	public int getTPPlanID() {
		return TPPlanID;
	}

	public void setTPPlanID(int tPPlanID) {
		TPPlanID = tPPlanID;
	}

	public int getTotalNum() {
		return TotalNum;
	}

	public void setTotalNum(int totalNum) {
		TotalNum = totalNum;
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

	public String getCreateTme() {
		return CreateTme;
	}

	public void setCreateTme(String createTme) {
		CreateTme = createTme;
	}

	public String getCreatePeople() {
		return CreatePeople;
	}

	public void setCreatePeople(String createPeople) {
		CreatePeople = createPeople;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
