package com.lewei.model;

/**
 * 产品排序模型
 * @author djzhao
 *@time 2015年9月25日
 */
public class Order {
	
	/** 主键*/
	private int TPLPOID;
	
	/** 外键（关联TPLineTable）*/
	private int TPLineID;
	
	/** 产品顺序*/
	private String Orders;
	
	/** 状态（默认1）*/
	private int Status;

	public int getTPLPOID() {
		return TPLPOID;
	}

	public void setTPLPOID(int tPLPOID) {
		TPLPOID = tPLPOID;
	}

	public int getTPLineID() {
		return TPLineID;
	}

	public void setTPLineID(int tPLineID) {
		TPLineID = tPLineID;
	}

	public String getOrders() {
		return Orders;
	}

	public void setOrders(String orders) {
		Orders = orders;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
