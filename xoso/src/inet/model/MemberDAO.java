/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import inet.bean.Member;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import inet.util.Md5;

/**
 *
 * @author 24h
 */
public class MemberDAO {

    private DBPoolX poolX = null;
    Logger logger = new Logger(this.getClass().getName());

    public MemberDAO() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public Member getRowByFacebookId(String facebookId) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date, money "
                    + "   FROM member WHERE facebook_id = ? and status = 1";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, facebookId);
            rs = ps.executeQuery();
            if(rs.next()){
                member = new Member();
                int i = 1;
                member.setId(rs.getBigDecimal(i++));
                member.setFacebookId(rs.getString(i++));
                member.setGoogleId(rs.getString(i++));
                member.setFullname(rs.getString(i++));
                member.setEmail(rs.getString(i++));
                member.setAvatar(rs.getString(i++));
                member.setIntroduction(rs.getString(i++));
                member.setStatus(rs.getInt(i++));
                member.setUsername(rs.getString(i++));     
                member.setGenDate(rs.getTimestamp(i++));
                member.setMoney(rs.getBigDecimal(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return member;
    }

    public boolean create(Member member) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "INSERT INTO member(facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "                      status, username, password, gen_date)"
                    + "   VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, member.getFacebookId());
            ps.setString(i++, member.getGoogleId());
            ps.setString(i++, member.getFullname());
            ps.setString(i++, member.getEmail());
            ps.setString(i++, member.getAvatar());
            ps.setString(i++, member.getIntroduction());
            ps.setInt(i++, member.getStatus());
            ps.setString(i++, member.getUsername());
            ps.setString(i++, member.getPassword());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public Member getRowByUsername(String username) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date, money "
                    + "   FROM member WHERE upper(username) = upper(?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()){
                member = new Member();
                int i = 1;
                member.setId(rs.getBigDecimal(i++));
                member.setFacebookId(rs.getString(i++));
                member.setGoogleId(rs.getString(i++));
                member.setFullname(rs.getString(i++));
                member.setEmail(rs.getString(i++));
                member.setAvatar(rs.getString(i++));
                member.setIntroduction(rs.getString(i++));
                member.setStatus(rs.getInt(i++));
                member.setUsername(rs.getString(i++));     
                member.setGenDate(rs.getTimestamp(i++));
                member.setMoney(rs.getBigDecimal(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return member;
    }
    
    public Member getRowById(BigDecimal id) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date, money, xu "
                    + "   FROM member WHERE id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                member = new Member();
                int i = 1;
                member.setId(rs.getBigDecimal(i++));
                member.setFacebookId(rs.getString(i++));
                member.setGoogleId(rs.getString(i++));
                member.setFullname(rs.getString(i++));
                member.setEmail(rs.getString(i++));
                member.setAvatar(rs.getString(i++));
                member.setIntroduction(rs.getString(i++));
                member.setStatus(rs.getInt(i++));
                member.setUsername(rs.getString(i++));     
                member.setGenDate(rs.getTimestamp(i++));
                member.setMoney(rs.getBigDecimal(i++));
                member.setXu(rs.getInt(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return member;
    }
    
    public boolean checkExistUsername(String username) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date "
                    + "   FROM member WHERE upper(username) = upper(?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return false;
    }
    
    public boolean checkExistEmail(String email) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date "
                    + "   FROM member WHERE upper(email) = upper(?)";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return false;
    }
    
    public Member login(String username, String password) {
        
        if(username == null || "".equals(username) || password == null || "".equals(password)) return null;
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date, money, xu "
                    + "   FROM member WHERE upper(username) = upper(?) and password = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, Md5.Hash(password));
            rs = ps.executeQuery();
            if(rs.next()){
                member = new Member();
                int i = 1;
                member.setId(rs.getBigDecimal(i++));
                member.setFacebookId(rs.getString(i++));
                member.setGoogleId(rs.getString(i++));
                member.setFullname(rs.getString(i++));
                member.setEmail(rs.getString(i++));
                member.setAvatar(rs.getString(i++));
                member.setIntroduction(rs.getString(i++));
                member.setStatus(rs.getInt(i++));
                member.setUsername(rs.getString(i++));     
                member.setGenDate(rs.getTimestamp(i++));
                member.setMoney(rs.getBigDecimal(i++));
                member.setXu(rs.getInt(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return member;
    }
    public Member getRowByGoogleId(String googleId) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date, money, xu "
                    + "   FROM member WHERE google_id = ? and status = 1";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, googleId);
            rs = ps.executeQuery();
            if(rs.next()){
                member = new Member();
                int i = 1;
                member.setId(rs.getBigDecimal(i++));
                member.setFacebookId(rs.getString(i++));
                member.setGoogleId(rs.getString(i++));
                member.setFullname(rs.getString(i++));
                member.setEmail(rs.getString(i++));
                member.setAvatar(rs.getString(i++));
                member.setIntroduction(rs.getString(i++));
                member.setStatus(rs.getInt(i++));
                member.setUsername(rs.getString(i++));     
                member.setGenDate(rs.getTimestamp(i++));
                member.setMoney(rs.getBigDecimal(i++));
                member.setXu(rs.getInt(i++));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return member;
    }
    
    public List<Member> findTopMoney(int quantity) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        List<Member> list = null;
        
        try{
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date, money "
                    + "   FROM member "
                    + "   ORDER BY money desc"
                    + "   LIMIT 0,?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            rs = ps.executeQuery();
            while(rs.next()){
                if(list == null) list = new ArrayList<Member>();
                member = new Member();
                int i = 1;
                member.setId(rs.getBigDecimal(i++));
                member.setFacebookId(rs.getString(i++));
                member.setGoogleId(rs.getString(i++));
                member.setFullname(rs.getString(i++));
                member.setEmail(rs.getString(i++));
                member.setAvatar(rs.getString(i++));
                member.setIntroduction(rs.getString(i++));
                member.setStatus(rs.getInt(i++));
                member.setUsername(rs.getString(i++));     
                member.setGenDate(rs.getTimestamp(i++));
                member.setMoney(rs.getBigDecimal(i++));
                
                list.add(member);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return list;
    }
    
    public List<Member> find(String username, String fullname, String email, int currentPage,
                             int rowsPerPage) {
        
        int startRow = (currentPage - 1) * rowsPerPage;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        List<Member> list = null;
        
        try{
            String sqlBonus = "";
            Vector vParams = new Vector();
            if(username != null && !"".equals(username)){
                sqlBonus += " and upper(username) like ?";
                vParams.add("%"+username.toUpperCase()+"%");
            }
            if(fullname != null && !"".equals(fullname)){
                sqlBonus += " and upper(fullname) like ?";
                vParams.add("%"+fullname.toUpperCase()+"%");
            }
            if(email != null && !"".equals(email)){
                sqlBonus += " and upper(email) like ?";
                vParams.add("%"+email.toUpperCase()+"%");
            }
            String sql = "SELECT id, facebook_id, google_id, fullname, email, avatar, introduction, "
                    + "          status, username, gen_date, money "
                    + "   FROM member "
                    + "   WHERE 1 = 1" + sqlBonus
                    + "   ORDER BY gen_date desc"
                    + "   LIMIT ?,?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            for(Object o : vParams){
                ps.setString(i++, o.toString());
            }
            ps.setInt(i++, startRow);
            ps.setInt(i++, rowsPerPage);
            rs = ps.executeQuery();
            while(rs.next()){
                if(list == null) list = new ArrayList<Member>();
                member = new Member();
                i = 1;
                member.setId(rs.getBigDecimal(i++));
                member.setFacebookId(rs.getString(i++));
                member.setGoogleId(rs.getString(i++));
                member.setFullname(rs.getString(i++));
                member.setEmail(rs.getString(i++));
                member.setAvatar(rs.getString(i++));
                member.setIntroduction(rs.getString(i++));
                member.setStatus(rs.getInt(i++));
                member.setUsername(rs.getString(i++));     
                member.setGenDate(rs.getTimestamp(i++));
                member.setMoney(rs.getBigDecimal(i++));
                
                list.add(member);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return list;
    }
    
    public int count(String username, String fullname, String email) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            String sqlBonus = "";
            Vector vParams = new Vector();
            if(username != null && !"".equals(username)){
                sqlBonus += " and upper(username) like ?";
                vParams.add("%"+username.toUpperCase()+"%");
            }
            if(fullname != null && !"".equals(fullname)){
                sqlBonus += " and upper(fullname) like ?";
                vParams.add("%"+fullname.toUpperCase()+"%");
            }
            if(email != null && !"".equals(email)){
                sqlBonus += " and upper(email) like ?";
                vParams.add("%"+email.toUpperCase()+"%");
            }
            String sql = "SELECT count(id) "
                    + "   FROM member "
                    + "   WHERE 1 = 1" + sqlBonus;
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            for(Object o : vParams){
                ps.setString(i++, o.toString());
            }
            rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return 0;
    }
    
    public boolean updateStatus(BigDecimal id, int status) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update member "
                    + "   set status = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setBigDecimal(2, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        return false;
    }
    
    public boolean delete(BigDecimal id) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "delete from member "
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        return false;
    }
    
    public boolean changePass(BigDecimal id, String newPass) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update member "
                    + "   set password = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, Md5.Hash(newPass));
            ps.setBigDecimal(2, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        return false;
    }
    
    public boolean updateMoney(BigDecimal id, int money) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update member "
                    + "   set money = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, money);
            ps.setBigDecimal(2, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        return false;
    }
    
    public boolean updateXu(BigDecimal id, int xu) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            String sql = "update member "
                    + "   set xu = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, xu);
            ps.setBigDecimal(2, id);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        return false;
    }
}
