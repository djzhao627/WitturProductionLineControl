package com.lewei.model;

/**
 * 控制按钮实体模型
 * @author djzhao
 *@time 2015年9月25日
 */
public class LightControl {
	
	/** 工站编号*/
	private String WorkStation;
	
	/** 按键色（例如：red,blue,green 或者 red,none,green）*/
	private String Color;
	
	/** 时间记录*/
	private String Time;
	
	/** 状态（默认1）*/
	private int Status;

	public String getWorkStation() {
		return WorkStation;
	}

	public void setWorkStation(String workStation) {
		WorkStation = workStation;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
