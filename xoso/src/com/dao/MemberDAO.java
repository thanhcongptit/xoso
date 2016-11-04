/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

/**
 *
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import com.bean.Member;
import inet.util.Md5;
import inet.util.MobileFormat;

public class MemberDAO {

    private Logger logger;
    private DBPoolX poolX = null;

    public MemberDAO() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Member> getAll() {
        List<Member> list = new ArrayList<Member>();
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        ResultSet rs = null;
        try {
            conn = poolX.getConnection();
            strSQL = "SELECT id, fullname, email, mobile, status, gen_date, "
                    + "google_id, facebook_id, password FROM member";
            preStmt = conn.prepareStatement(strSQL);
            rs = preStmt.executeQuery();
            int i = 1;
            Member result = null;
            while (rs.next()) {
                i = 1;
                result = new Member();
                result.setId(rs.getBigDecimal(i++));
                result.setFullname(rs.getString(i++));
                result.setEmail(rs.getString(i++));
                result.setMobile(rs.getString(i++));
                result.setStatus(rs.getInt(i++));
                result.setGenDate(rs.getTimestamp(i++));
                result.setGoogleId(rs.getString(i++));
                result.setFacebookId(rs.getString(i++));
                result.setPassword(rs.getString(i++));

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

    public boolean insertRow(String fullname, String email, String mobile, 
            int status, String googleId, String facebookId, String password) {
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = null;
        try {
            conn = poolX.getConnection();
            sql = "INSERT INTO member(fullname, email, mobile, status, gen_date, "
                    + "google_id, facebook_id, password) VALUES(?, ?, ?, ?, now(), ?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, fullname);
            pstm.setString(i++, email);
            pstm.setString(i++, mobile);
            pstm.setInt(i++, status);
            pstm.setString(i++, googleId);
            pstm.setString(i++, facebookId);
            pstm.setString(i++, Md5.Hash(password));

            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error("insertRow: Error executing SQL " + sql + ">>>" + e.toString());
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, pstm);
        }
        return false;
    }

    public boolean isExistEmail(String email) {
        if(email == null){
            return false;
        }else{
            email = email.trim();
        }
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        ResultSet rs = null;
        try {
            conn = poolX.getConnection();
            strSQL = "SELECT count(*) FROM member WHERE upper(email) like upper(?)";
            preStmt = conn.prepareStatement(strSQL);
            int i = 1;
            preStmt.setString(i++, email);
            rs = preStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("isExistEmail: Error executing >>>" + e.toString());
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return false;
    }
    
    public boolean isExistMobile(String mobile) {
        if(mobile == null){
            return false;
        }else{
            mobile = MobileFormat.formatInternational(mobile.trim());
        }
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        ResultSet rs = null;
        try {
            conn = poolX.getConnection();
            strSQL = "SELECT count(*) FROM member WHERE upper(mobile) like upper(?)";
            preStmt = conn.prepareStatement(strSQL);
            int i = 1;
            preStmt.setString(i++, mobile);
            rs = preStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("isExistMobile: Error executing >>>" + e.toString());
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        return false;
    }
}
