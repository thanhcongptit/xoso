/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import com.bean.SmsDTO;
import inet.bean.News;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author conglt
 */
public class SmsDao {

    private DBPoolX poolX = null;

    public SmsDao() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
        }
    }

    public List<SmsDTO> findAllSmsContent(String openedDate) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        StringBuilder strSQL = new StringBuilder();
        List<SmsDTO> smsDTOs = new ArrayList<SmsDTO>();
        
        try {
            conn = poolX.getConnection();
            strSQL.append(" SELECT *");
            strSQL.append(" FROM sms_content");
            strSQL.append(" WHERE openDate = ?");
            preStmt = conn.prepareStatement(strSQL.toString());
            preStmt.setString(1, openedDate);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                SmsDTO smsDTO = new SmsDTO();
                smsDTO.setId(rs.getString("id"));
                smsDTO.setContent(rs.getString("content"));
                smsDTO.setType(rs.getString("type"));
                smsDTO.setCreatedDate(rs.getString("createdDate"));
                smsDTO.setOpenDate(rs.getString("openDate"));
                smsDTO.setShortCode(rs.getString("shortCode"));
                smsDTOs.add(smsDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        
        return smsDTOs;
    }
    
    public boolean create(SmsDTO smsDTO) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "INSERT INTO sms_content (type, content, createdDate, openDate, shortCode) "
                    + "   VALUES (?,?,?,?,?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, smsDTO.getType());
            ps.setString(2, smsDTO.getContent());
            ps.setString(3, smsDTO.getCreatedDate());
            ps.setString(4, smsDTO.getOpenDate());
            ps.setString(5, smsDTO.getShortCode());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean delete(int id, String table) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "delete from " + table
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    
    public List<SmsDTO> findAllCuphap() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        StringBuilder strSQL = new StringBuilder();
        List<SmsDTO> smsDTOs = new ArrayList<SmsDTO>();
        
        try {
            conn = poolX.getConnection();
            strSQL.append(" SELECT *");
            strSQL.append(" FROM cuphap");
            preStmt = conn.prepareStatement(strSQL.toString());
            rs = preStmt.executeQuery();
            while (rs.next()) {
                SmsDTO smsDTO = new SmsDTO();
                smsDTO.setId(rs.getString("id"));
                smsDTO.setContent(rs.getString("content"));
                smsDTO.setType(rs.getString("mo"));
                smsDTO.setShortCode(rs.getString("shortCode"));
                smsDTOs.add(smsDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, preStmt, rs);
        }
        
        return smsDTOs;
    }
    
    public boolean createCuphap(SmsDTO smsDTO) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "INSERT INTO cuphap (mo, shortCode, content) "
                    + "   VALUES (?,?,?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, smsDTO.getType());
            ps.setString(2, smsDTO.getShortCode());
            ps.setString(3, smsDTO.getContent());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
}
