package com.wittur.www;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.lewei.db.DBUtil;

public class LightChange {

	public static void main(String[] args) throws SQLException, InterruptedException {
		Connection conn = DBUtil.getConn();
		Statement statement = conn.createStatement();
//		for(int i = 1; i < 20; i ++){
//			for(int j = 1; j < 7; j ++){
//			statement.executeUpdate("INSERT INTO t_alarmdata (bid,keyid,ttime,yn) VALUES("+i+","+j+",NOW(),0)");
//			Thread.sleep(100);
//			}
//		}
//		System.out.println("SUCCESS!");
		statement.executeUpdate("INSERT INTO t_alarmdata (bid,keyid,ttime,yn) VALUES(12,3,NOW(),1)");
	}
}
