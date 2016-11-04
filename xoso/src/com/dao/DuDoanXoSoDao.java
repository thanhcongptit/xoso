package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import inet.bean.DuDoanXoso;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.DateUtil;
import inet.util.Logger;

public class DuDoanXoSoDao {
	Logger logger = null;
	private DBPoolX poolX = null;

	public DuDoanXoSoDao() {
		logger = new Logger(this.getClass().getName());
		try {
			poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<DuDoanXoso> getDuDoanXoSo() {
		List<DuDoanXoso> list = null;

		String sql = "select * from DuDoanXoso order by opendate desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = new ArrayList<>();

			while (rs.next()) {
				DuDoanXoso advertisingSite = new DuDoanXoso();
				advertisingSite.setId(rs.getInt("id"));
				advertisingSite.setDayso(rs.getString("dayso"));
				advertisingSite.setOpenDate(rs.getString("opendate"));
				advertisingSite.setXu(rs.getInt("xu"));
				advertisingSite.setVitri(rs.getInt("vitri"));
				list.add(advertisingSite);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return list;
	}

	public boolean deleteLink(int id) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String sql = "delete FROM  DuDoanXoso " + "  where id = ?";
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			return ps.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps);
		}

		return false;
	}
	
	public boolean create(DuDoanXoso advertisingSite){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "insert into DuDoanXoso(dayso, opendate, xu, vitri)"
                    + "   values(?, ?, ?, ?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, advertisingSite.getDayso());
            ps.setString(i++, DateUtil.date2String(new Date(), "dd-MM-yyyy"));
            ps.setInt(i++, advertisingSite.getXu());
            ps.setInt(i++, advertisingSite.getVitri());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
	
	
	public DuDoanXoso getDuDoanXoSoByDate(String openDate, int vitri) {

		String sql = "select * from DuDoanXoso WHERE opendate = ? and vitri = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openDate);
			ps.setInt(2, vitri);
			rs = ps.executeQuery();

			if (rs.next()) {
				DuDoanXoso advertisingSite = new DuDoanXoso();
				advertisingSite.setId(rs.getInt("id"));
				advertisingSite.setDayso(rs.getString("dayso"));
				advertisingSite.setOpenDate(rs.getString("opendate"));
				advertisingSite.setXu(rs.getInt("xu"));
				advertisingSite.setVitri(rs.getInt("vitri"));
				
				return advertisingSite;
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return null;
	}
	
	
	public String getDaySoByDate(String openDate, int vitri, int memberId) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select capso  from LaySo WHERE opendate = ? and vitri = ?");
		if(memberId != -1) {
			sql.append(" and memberId = ?");
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, openDate);
			ps.setInt(2, vitri);
			
			if(memberId != -1) {
				ps.setInt(3, memberId);
			}
			
			rs = ps.executeQuery();

			if (rs.next()) {
				String dayso = rs.getString(1);
				
				return dayso;
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return null;
	}
	
	public String getDaySoByDateWithoutMoney(String openDate, int vitri) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select dayso  from DuDoanXoso WHERE opendate = ? and vitri =?");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = poolX.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, openDate);
			ps.setInt(2, vitri);
			
			rs = ps.executeQuery();

			if (rs.next()) {
				String dayso = rs.getString(1);
				
				return dayso;
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.log(e.toString());
			e.printStackTrace();
		} finally {
			poolX.releaseConnection(conn, ps, rs);
		}

		return null;
	}
	
 public boolean createLaySo(int memberId, String openDate, int vitri, String capso){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "insert into LaySo(memberId, opendate, vitri, capso)"
                    + "   values(?, ?, ?, ?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, memberId);
            ps.setString(i++, DateUtil.date2String(new Date(), "dd-MM-yyyy"));
            ps.setInt(i++, vitri);
            ps.setString(i++, capso);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
}
