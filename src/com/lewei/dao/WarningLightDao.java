package com.lewei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lewei.db.DBUtil;
import com.lewei.model.TPLProd;
import com.lewei.model.TPLine;
import com.lewei.model.TPPlan;

public class WarningLightDao {

	private PreparedStatement pstmt;

	public void Query() throws Exception {
		Connection conn = DBUtil.getConn();
		// 3.通过数据库的连接操作数据库（增删改查）
		Statement statement = conn.createStatement();
		// 4.通过查询返回结果
		ResultSet rs = statement.executeQuery("select * from tpplantable");
		// 5.循环去除 rs 中的结果
		while (rs.next()) {
			System.out.println(rs.getObject(3));
		}
	}

	/**
	 * 插入数据到表 tpplan ，并返回主键值
	 * 
	 * @param tpplan
	 * @return
	 * @throws Exception
	 */
	public int insertToTpplan(TPPlan tpplan) throws Exception {
		// 获得连接
		Connection conn = DBUtil.getConn();
		// ‘？’ 为预执行所用的占位符
		String sql = "insert into tpplan(TotalNum,TPLineName,Ranger,CreateTime,CreatePeople,Status) values(?,?,?,now(),?,?)";
		// 预执行sql
		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS：返回插入的主键
		// 给占位符赋值
		pstmt.setInt(1, tpplan.getTotalNum());
		pstmt.setString(2, tpplan.getTPLineName());
		pstmt.setInt(3, tpplan.getRanger());
		pstmt.setString(4, tpplan.getCreatePeople());
		pstmt.setInt(5, tpplan.getStatus());
		// 执行
		pstmt.executeUpdate();
		// 获取返回主键
		ResultSet rs = pstmt.getGeneratedKeys();
		int autoInckey = -1;
		if (rs.next()) {
			// 取得主键ID
			autoInckey = rs.getInt(1);
		}
		return autoInckey;
	}

	/**
	 * 插入数据到表 tpline ，并返回主键值
	 * 
	 * @param tpline
	 * @return
	 * @throws SQLException
	 */
	public int insertToTpline(TPLine tpline) throws SQLException {
		// 获得连接
		Connection conn = DBUtil.getConn();
		// ‘？’ 为预执行所用的占位符
		String sql = "insert into tpline(TPPlanID,TPLineName,Ranger,RealNum,RestTime,LostTime,StartTime,EndTime,Status,ChangeTime) values(?,?,?,?,?,?,now(),?,?,?)";
		// 预执行sql
		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS：返回插入的主键
		// 给占位符赋值
		pstmt.setInt(1, tpline.getTPPlanID());
		pstmt.setString(2, tpline.getTPLineName());
		pstmt.setInt(3, tpline.getRanger());
		pstmt.setInt(4, tpline.getRealNum());
		pstmt.setString(5, tpline.getRestTime());
		pstmt.setString(6, tpline.getLostTime());
		pstmt.setString(7, tpline.getEndTime());
		pstmt.setInt(8, tpline.getStatus());
		pstmt.setString(9, tpline.getChangeTime());
		// 执行
		pstmt.executeUpdate();
		// 获取返回主键
		ResultSet rs = pstmt.getGeneratedKeys();
		int autoInckey = -1;
		if (rs.next()) {
			// 取得主键ID
			autoInckey = rs.getInt(1);
		}
		return autoInckey;
	}

	/**
	 * 插入数据到表 tplprod ，并返回主键值
	 * 
	 * @param p
	 * @return
	 * @throws SQLException
	 */
	public int insertToTplprod(TPLProd p) throws SQLException {

		// 获得连接
		Connection conn = DBUtil.getConn();
		// ‘？’ 为预执行所用的占位符
		String sql = "insert into tplprod(TPLineID,ProdName,Takt,Num,Status) values(?,?,?,?,?)";
		// 预执行sql
		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS：返回插入的主键
		// 给占位符赋值
		pstmt.setInt(1, p.getTPLineID());
		pstmt.setString(2, p.getProdName());
		pstmt.setDouble(3, p.getTakt());
		pstmt.setInt(4, p.getNum());
		pstmt.setInt(5, p.getStatus());
		// 执行
		pstmt.executeUpdate();
		// 获取返回主键
		ResultSet rs = pstmt.getGeneratedKeys();
		int autoInckey = -1;
		if (rs.next()) {
			// 取得主键ID
			autoInckey = rs.getInt(1);
		}
		return autoInckey;
	}

	public List<String> getProductNames() throws SQLException {
		List<String> list = new ArrayList<String>();
		// 获得链接
		Connection conn = DBUtil.getConn();
		// 通过数据库的连接操作数据库
		Statement statement = conn.createStatement();
		// 通过查询返回结果
		ResultSet rs = statement.executeQuery("select ProName from product");
		// 5.循环取出 rs 中的结果
		while (rs.next()) {
			// 通过字段名取出
			list.add(rs.getString("ProName")); // 序号取出rs.getString(1);
		}
		return list;
	}

	public int getTplineIDByName(String name) throws SQLException {
		// 获得连接
				Connection conn = DBUtil.getConn();
				// ‘？’ 为预执行所用的占位符
				String sql = "select TPLineID from tpline where TPLineName = ?";
				// 预执行sql
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS：返回插入的主键
				// 给占位符赋值
				pstmt.setString(1, name);
				// 执行
				ResultSet rs = pstmt.executeQuery();
				int autoInckey = -1;
				if (rs.next()) {
					// 取得主键ID
					autoInckey = rs.getInt(1);
				}
				return autoInckey;
	}

}
