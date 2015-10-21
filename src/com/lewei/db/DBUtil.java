package com.lewei.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	// 威特
	private static final String URL = "jdbc:mysql://172.21.2.216:3306/warning_light";
	
	// 本机
//	private static final String URL = "jdbc:mysql://127.0.0.1:3306/warning_light";

	private static final String USER = "root";

	private static final String PASSWORD = "";

	private static Connection conn = null;

	static {
		// 1.加载驱动（反射机制）
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得数据库连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}

	public static void main(String[] args) throws Exception {

		// 3.通过数据库的连接操作数据库（增删改查）
		Statement statement = conn.createStatement();
		// 4.通过查询返回结果
		ResultSet rs = statement.executeQuery("select * from tpplantable");
		// 5.循环去除 rs 中的结果
		while (rs.next()) {
			System.out.println(rs.getObject(3));
		}

	}

}
