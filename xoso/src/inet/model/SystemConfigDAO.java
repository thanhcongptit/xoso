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
import java.util.HashMap;

/**
 *
 * @author 24h
 */
public class SystemConfigDAO {
    
    private DBPoolX poolX = null;
    Logger logger = new Logger(this.getClass().getName());

    public SystemConfigDAO() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public String findByKey(String key){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String sql = "select value "
                    + "   from system_config"
                    + "   where upper(key_) = upper(?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, key);
            rs = ps.executeQuery();
            if(rs.next()) return rs.getString(1);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return null;
    }
    
    public boolean update(String key, String value){
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update system_config"
                    + "   set value = ?"
                    + "   where upper(key_) = upper(?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, value);
            ps.setString(2, key);
            return ps.executeUpdate() == 1;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        return false;
    }
    
    public HashMap<String, String> loadConfig(){
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, String> hmSystemConfig = null;
        
        try{
            String sql = "select key_, value "
                    + "   from system_config";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                if(hmSystemConfig == null) hmSystemConfig = new HashMap<>();
                hmSystemConfig.put(rs.getString(1), rs.getString(2));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return hmSystemConfig;
    }
}
