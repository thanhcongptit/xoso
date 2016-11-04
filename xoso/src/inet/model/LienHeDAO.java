/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 24h
 */
public class LienHeDAO {
    
    private DBPoolX poolX = null;
    Logger logger = new Logger(this.getClass().getName());

    public LienHeDAO() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public String getLienHe(){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String sql = "select lien_he from lien_he";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) return rs.getString(1);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return "";
    }       
    
    public boolean update(String lienHe){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update lien_he set lien_he = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, lienHe);
            
            return ps.executeUpdate() == 1;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    } 
}
