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
		// 3.ͨ�����ݿ�����Ӳ������ݿ⣨��ɾ�Ĳ飩
		Statement statement = conn.createStatement();
		// 4.ͨ����ѯ���ؽ��
		ResultSet rs = statement.executeQuery("select * from tpplantable");
		// 5.ѭ��ȥ�� rs �еĽ��
		while (rs.next()) {
			System.out.println(rs.getObject(3));
		}
	}

	/**
	 * �������ݵ��� tpplan ������������ֵ
	 * 
	 * @param tpplan
	 * @return
	 * @throws Exception
	 */
	public int insertToTpplan(TPPlan tpplan) throws Exception {
		// �������
		Connection conn = DBUtil.getConn();
		// ������ ΪԤִ�����õ�ռλ��
		String sql = "insert into tpplan(TotalNum,TPLineName,Ranger,CreateTime,CreatePeople,Status) values(?,?,?,now(),?,?)";
		// Ԥִ��sql
		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS�����ز��������
		// ��ռλ����ֵ
		pstmt.setInt(1, tpplan.getTotalNum());
		pstmt.setString(2, tpplan.getTPLineName());
		pstmt.setInt(3, tpplan.getRanger());
		pstmt.setString(4, tpplan.getCreatePeople());
		pstmt.setInt(5, tpplan.getStatus());
		// ִ��
		pstmt.executeUpdate();
		// ��ȡ��������
		ResultSet rs = pstmt.getGeneratedKeys();
		int autoInckey = -1;
		if (rs.next()) {
			// ȡ������ID
			autoInckey = rs.getInt(1);
		}
		return autoInckey;
	}

	/**
	 * �������ݵ��� tpline ������������ֵ
	 * 
	 * @param tpline
	 * @return
	 * @throws SQLException
	 */
	public int insertToTpline(TPLine tpline) throws SQLException {
		// �������
		Connection conn = DBUtil.getConn();
		// ������ ΪԤִ�����õ�ռλ��
		String sql = "insert into tpline(TPPlanID,TPLineName,Ranger,RealNum,RestTime,LostTime,StartTime,EndTime,Status,ChangeTime) values(?,?,?,?,?,?,now(),?,?,?)";
		// Ԥִ��sql
		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS�����ز��������
		// ��ռλ����ֵ
		pstmt.setInt(1, tpline.getTPPlanID());
		pstmt.setString(2, tpline.getTPLineName());
		pstmt.setInt(3, tpline.getRanger());
		pstmt.setInt(4, tpline.getRealNum());
		pstmt.setString(5, tpline.getRestTime());
		pstmt.setString(6, tpline.getLostTime());
		pstmt.setString(7, tpline.getEndTime());
		pstmt.setInt(8, tpline.getStatus());
		pstmt.setString(9, tpline.getChangeTime());
		// ִ��
		pstmt.executeUpdate();
		// ��ȡ��������
		ResultSet rs = pstmt.getGeneratedKeys();
		int autoInckey = -1;
		if (rs.next()) {
			// ȡ������ID
			autoInckey = rs.getInt(1);
		}
		return autoInckey;
	}

	/**
	 * �������ݵ��� tplprod ������������ֵ
	 * 
	 * @param p
	 * @return
	 * @throws SQLException
	 */
	public int insertToTplprod(TPLProd p) throws SQLException {

		// �������
		Connection conn = DBUtil.getConn();
		// ������ ΪԤִ�����õ�ռλ��
		String sql = "insert into tplprod(TPLineID,ProdName,Takt,Num,Status) values(?,?,?,?,?)";
		// Ԥִ��sql
		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS�����ز��������
		// ��ռλ����ֵ
		pstmt.setInt(1, p.getTPLineID());
		pstmt.setString(2, p.getProdName());
		pstmt.setDouble(3, p.getTakt());
		pstmt.setInt(4, p.getNum());
		pstmt.setInt(5, p.getStatus());
		// ִ��
		pstmt.executeUpdate();
		// ��ȡ��������
		ResultSet rs = pstmt.getGeneratedKeys();
		int autoInckey = -1;
		if (rs.next()) {
			// ȡ������ID
			autoInckey = rs.getInt(1);
		}
		return autoInckey;
	}

	public List<String> getProductNames() throws SQLException {
		List<String> list = new ArrayList<String>();
		// �������
		Connection conn = DBUtil.getConn();
		// ͨ�����ݿ�����Ӳ������ݿ�
		Statement statement = conn.createStatement();
		// ͨ����ѯ���ؽ��
		ResultSet rs = statement.executeQuery("select ProName from product");
		// 5.ѭ��ȡ�� rs �еĽ��
		while (rs.next()) {
			// ͨ���ֶ���ȡ��
			list.add(rs.getString("ProName")); // ���ȡ��rs.getString(1);
		}
		return list;
	}

	public int getTplineIDByName(String name) throws SQLException {
		// �������
				Connection conn = DBUtil.getConn();
				// ������ ΪԤִ�����õ�ռλ��
				String sql = "select TPLineID from tpline where TPLineName = ?";
				// Ԥִ��sql
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS�����ز��������
				// ��ռλ����ֵ
				pstmt.setString(1, name);
				// ִ��
				ResultSet rs = pstmt.executeQuery();
				int autoInckey = -1;
				if (rs.next()) {
					// ȡ������ID
					autoInckey = rs.getInt(1);
				}
				return autoInckey;
	}

}
