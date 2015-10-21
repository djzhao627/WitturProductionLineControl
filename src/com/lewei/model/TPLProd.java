package com.lewei.model;

/**
 * 产线产品模型
 * @author djzhao
 *@time 2015年9月25日
 */
public class TPLProd {

	/** 主键 */
	private int TPLProdID;

	/** 关联产线表（TPLineTable） */
	private int TPLineID;

	/** 产品名称 */
	private String ProdName;

	/** 产品生产所需节拍（默认设置10） */
	private double Takt;
	
	/** 产品数量（默认设置1）*/
	private int Num;

	/** 状态（默认1） */
	private int Status;

	public int getTPLProdID() {
		return TPLProdID;
	}

	public void setTPLProdID(int tPLProd) {
		TPLProdID = tPLProd;
	}

	public int getTPLineID() {
		return TPLineID;
	}

	public void setTPLineID(int tPLineID) {
		TPLineID = tPLineID;
	}

	public String getProdName() {
		return ProdName;
	}

	public void setProdName(String prodName) {
		ProdName = prodName;
	}

	public double getTakt() {
		return Takt;
	}

	public void setTakt(double takt) {
		Takt = takt;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
