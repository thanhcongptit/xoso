package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import inet.bean.CapsoOnline;
import inet.bean.Member;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.DateUtil;
import inet.util.Logger;

public class LotoOnlineDao {

	private Logger logger;
	private DBPoolX poolX = null;

	public LotoOnlineDao() {
		logger = new Logger(this.getClass().getName());
		try {
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleInsertLotoOnline() {
		List<Member> members = getAllMember();
		Random rand = new Random();

		if (members != null && members.size() > 0) {
			for (Member member : members) {
				
				int loop = rand.nextInt(3) + 1;
				
				for(int i = 0; i<loop; i++) {
					CapsoOnline capsoOnline = new CapsoOnline();
					capsoOnline.setMemberId(member.getId());
					capsoOnline.setCurrentMoney(member.getMoney());
	
					int capso = rand.nextInt(100);
					int sodiem = rand.nextInt(25);
					
					if(capso < 10) {
						capsoOnline.setCapso("0" + capso);
					} else {
						capsoOnline.setCapso(String.valueOf(capso));
					}
					capsoOnline.setSodiem(sodiem);
	
					insertLotoOnline(capsoOnline);
				}
			}
		}
	}

	public void insertLotoOnline(CapsoOnline capsoOnline) {
		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = null;
		try {
			conn = poolX.getConnection();
			sql = "INSERT INTO capso_online(capso, sodiem, ngaychoi, gen_date, member_id, current_money"
					+ ") VALUES(?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			int i = 1;
			pstm.setString(i++, capsoOnline.getCapso());
			pstm.setInt(i++, capsoOnline.getSodiem());
			pstm.setString(i++, DateUtil.date2String(new Date(), "yyyy-MM-dd") + " 00:00:00");
			pstm.setString(i++, DateUtil.date2String(new Date(), "yyyy-MM-dd hh:mm:ss"));
			pstm.setBigDecimal(i++, capsoOnline.getMemberId());
			pstm.setBigDecimal(i++, capsoOnline.getCurrentMoney());

			pstm.executeUpdate();
		} catch (Exception e) {
			logger.error("insertRow: Error executing SQL " + sql + ">>>" + e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, pstm);
		}
	}

	public List<Member> getAllMember() {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement preStmt = null;
		String strSQL = null;
		ResultSet rs = null;
		try {
			conn = poolX.getConnection();
			strSQL = "SELECT id, money FROM member";
			preStmt = conn.prepareStatement(strSQL);
			rs = preStmt.executeQuery();
			int i = 1;
			Member result = null;
			while (rs.next()) {
				i = 1;
				result = new Member();
				result.setId(rs.getBigDecimal(i++));
				result.setMoney(rs.getBigDecimal(i++));
				list.add(result);
			}
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			logger.error("getAll: Error executing >>>" + e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, preStmt, rs);
		}
		return null;
	}
}
