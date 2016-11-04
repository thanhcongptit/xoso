/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import inet.bean.ThongKeVip;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class ThongKeVipDAO {
    private Logger logger = null;
    private DBPoolX poolX = null;

    public ThongKeVipDAO() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public ThongKeVip findThongKeVip(String code,String ddmmyyyy){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ThongKeVip thongKeVip=null;
        String sql="";
        
        try{            
            conn=poolX.getConnection();            
            sql=" select dau,duoi,vip,xien,ve_nhieu,ve_it,DATE_FORMAT(gen_date, '%d/%m/%Y'),code" +
                " from lottery_tkvip where upper(code) = upper(?) and DATE_FORMAT(gen_date, '%d/%m/%Y')=?";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, ddmmyyyy);
            rs=ps.executeQuery();
            if(rs.next()){
                thongKeVip=new ThongKeVip();
                thongKeVip.setDau(rs.getString(1));
                thongKeVip.setDuoi(rs.getString(2));
                thongKeVip.setVip(rs.getString(3));
                thongKeVip.setXien(rs.getString(4));
                thongKeVip.setVenhieu(rs.getString(5));
                thongKeVip.setVeit(rs.getString(6));
                thongKeVip.setGen_date(rs.getString(7));                
                thongKeVip.setCode(rs.getString(8));                
            }
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi exp "+e.toString());
            System.out.println("loi exp "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return thongKeVip;
    }
    
    public ThongKeVip findThongKeVip4Update(String code,String ddmmyyyy, BigDecimal otherId){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ThongKeVip thongKeVip=null;
        String sql="";
        
        try{            
            conn=poolX.getConnection();            
            sql=" select dau,duoi,vip,xien,ve_nhieu,ve_it,DATE_FORMAT(gen_date, '%d/%m/%Y'),code" +
                " from lottery_tkvip "
              + " where upper(code) = upper(?) and DATE_FORMAT(gen_date, '%d/%m/%Y')=?"
                    + " and id != ?";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, ddmmyyyy);
            ps.setBigDecimal(3, otherId);
            rs=ps.executeQuery();
            if(rs.next()){
                thongKeVip=new ThongKeVip();
                thongKeVip.setDau(rs.getString(1));
                thongKeVip.setDuoi(rs.getString(2));
                thongKeVip.setVip(rs.getString(3));
                thongKeVip.setXien(rs.getString(4));
                thongKeVip.setVenhieu(rs.getString(5));
                thongKeVip.setVeit(rs.getString(6));
                thongKeVip.setGen_date(rs.getString(7));                
                thongKeVip.setCode(rs.getString(8));                
            }
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi exp "+e.toString());
            System.out.println("loi exp "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return thongKeVip;
    }
    
    public List<ThongKeVip> findThongKeVip(String code,int numrow){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ThongKeVip thongKeVip=null;
        String sql="";
        List<ThongKeVip> list=null;
//        sdate=sdate+" 00:00:00";
//        edate=edate+" 23:59:59";
        try{
            conn=poolX.getConnection();
            sql=" select dau,duoi,vip,xien,ve_nhieu,ve_it,DATE_FORMAT(gen_date, '%d/%m/%Y'),code "
              + " from (select dau,duoi,vip,xien,ve_nhieu,ve_it,gen_date ,code"
              + "       from lottery_tkvip where upper(code) = ? "
              + "       order by gen_date desc) "
              + " order by gen_date desc"
              + " limit 0,?";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setInt(2, numrow);
            rs=ps.executeQuery();
            while(rs.next()){
                
                if(list==null){list=new ArrayList<ThongKeVip>();}
                
                thongKeVip=new ThongKeVip();
                thongKeVip.setDau(rs.getString(1));
                thongKeVip.setDuoi(rs.getString(2));
                thongKeVip.setVip(rs.getString(3));
                thongKeVip.setXien(rs.getString(4));
                thongKeVip.setVenhieu(rs.getString(5));
                thongKeVip.setVeit(rs.getString(6));
                thongKeVip.setGen_date(rs.getString(7));                
                thongKeVip.setCode(rs.getString(8));                
                
                list.add(thongKeVip);
            }
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi exp "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return list;
    }
    
    public List<ThongKeVip> findThongKeVip(String code, String startDate, String endDate, int currentPage, 
                                     int rowsPerPage){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ThongKeVip thongKeVip=null;
        String sql="";
        if(startDate != null && !"".equals(startDate)){
            startDate = startDate + " 00:00:00";
        }
        if(endDate != null && !"".equals(endDate)){
            endDate = endDate + " 23:59:59";
        }
        int startRow = (currentPage - 1) * rowsPerPage;
        List<ThongKeVip> list = null;
        
        try{            
            conn=poolX.getConnection();       
            String sqlBonus = "";
            if(startDate != null && !"".equals(startDate)){
                sqlBonus += " and gen_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s')";
            }
            if(endDate != null && !"".equals(endDate)){
                sqlBonus += " and gen_date <= str_to_date(?, '%d/%m/%Y %H:%i:%s')";
            }
            sql=" select dau,duoi,vip,xien,ve_nhieu,ve_it,DATE_FORMAT(gen_date, '%d/%m/%Y'),code,id" +
                " from lottery_tkvip "
              + " where upper(code) = upper(?) " + sqlBonus
              + " order by gen_date desc"
              + " limit ?,?";
            
            ps=conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, code);
            if(startDate != null && !"".equals(startDate)){
                ps.setString(i++, startDate);
            }
            if(endDate != null && !"".equals(endDate)){
                ps.setString(i++, endDate);
            }
            ps.setInt(i++, startRow);
            ps.setInt(i++, rowsPerPage);
            
            rs=ps.executeQuery();
            while(rs.next()){
                if(list == null) list = new ArrayList<>();
                thongKeVip=new ThongKeVip();
                thongKeVip.setDau(rs.getString(1));
                thongKeVip.setDuoi(rs.getString(2));
                thongKeVip.setVip(rs.getString(3));
                thongKeVip.setXien(rs.getString(4));
                thongKeVip.setVenhieu(rs.getString(5));
                thongKeVip.setVeit(rs.getString(6));
                thongKeVip.setGen_date(rs.getString(7));                
                thongKeVip.setCode(rs.getString(8));    
                thongKeVip.setId(rs.getBigDecimal(9));    
                list.add(thongKeVip);
            }
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi exp "+e.toString());
            System.out.println("loi exp "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return list;
    }
    
    public int countThongKeVip(String code, String startDate, String endDate){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="";
        if(startDate != null && !"".equals(startDate)){
            startDate = startDate + " 00:00:00";
        }
        if(endDate != null && !"".equals(endDate)){
            endDate = endDate + " 23:59:59";
        }
        
        try{            
            conn=poolX.getConnection();       
            String sqlBonus = "";
            if(startDate != null && !"".equals(startDate)){
                sqlBonus += " and gen_date >= str_to_date(?, '%d/%m/%Y %H:%i:%s')";
            }
            if(endDate != null && !"".equals(endDate)){
                sqlBonus += " and gen_date <= str_to_date(?, '%d/%m/%Y %H:%i:%s')";
            }
            sql=" select count(id)" +
                " from lottery_tkvip "
              + " where upper(code) = upper(?) " + sqlBonus;
            
            ps=conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, code);
            if(startDate != null && !"".equals(startDate)){
                ps.setString(i++, startDate);
            }
            if(endDate != null && !"".equals(endDate)){
                ps.setString(i++, endDate);
            }
            
            rs=ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi exp "+e.toString());
            System.out.println("loi exp "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        return 0;
    }
    
    public boolean delete(BigDecimal id){
        Connection conn=null;
        PreparedStatement ps=null;
        
        try{            
            conn=poolX.getConnection();            
            String sql=" delete from lottery_tkvip "
              + "        where id = ?";
            
            ps=conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean create(ThongKeVip thongKeVip){
        
        Connection conn=null;
        PreparedStatement ps=null;
        
        try{            
            conn=poolX.getConnection();            
            String sql=" insert into lottery_tkvip(dau,duoi,vip,xien,ve_nhieu,ve_it,"
                    + "                            gen_date,code)" +
                      "  values(?, ?, ?, ?, ?, ?, str_to_date(?, '%d/%m/%Y'), ?)";
            
            ps=conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, thongKeVip.getDau());
            ps.setString(i++, thongKeVip.getDuoi());
            ps.setString(i++, thongKeVip.getVip());
            ps.setString(i++, thongKeVip.getXien());
            ps.setString(i++, thongKeVip.getVenhieu());
            ps.setString(i++, thongKeVip.getVeit());
            ps.setString(i++, thongKeVip.getGen_date());
            ps.setString(i++, thongKeVip.getCode());
            
            return ps.executeUpdate() == 1;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean update(ThongKeVip thongKeVip){
        
        Connection conn=null;
        PreparedStatement ps=null;
        
        try{            
            conn=poolX.getConnection();            
            String sql=" update lottery_tkvip "
                    + "  set dau = ?, duoi = ?, vip = ?, xien = ?,"
                    + "      ve_nhieu = ?, ve_it = ?, gen_date = STR_TO_DATE(?, '%d/%m/%Y')"
                    + "  where id = ?";
            
            ps=conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, thongKeVip.getDau());
            ps.setString(i++, thongKeVip.getDuoi());
            ps.setString(i++, thongKeVip.getVip());
            ps.setString(i++, thongKeVip.getXien());
            ps.setString(i++, thongKeVip.getVenhieu());
            ps.setString(i++, thongKeVip.getVeit());
            ps.setString(i++, thongKeVip.getGen_date());
            ps.setBigDecimal(i++, thongKeVip.getId());
            
            return ps.executeUpdate() == 1;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public ThongKeVip findById(BigDecimal id){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ThongKeVip thongKeVip=null;
        String sql="";
        
        try{            
            conn=poolX.getConnection();            
            sql=" select dau,duoi,vip,xien,ve_nhieu,ve_it,DATE_FORMAT(gen_date, '%d/%m/%Y'),code" +
                " from lottery_tkvip "
              + " where id = ?";
            
            ps=conn.prepareStatement(sql);
            ps.setBigDecimal(1, id);
            rs=ps.executeQuery();
            if(rs.next()){
                thongKeVip=new ThongKeVip();
                thongKeVip.setDau(rs.getString(1));
                thongKeVip.setDuoi(rs.getString(2));
                thongKeVip.setVip(rs.getString(3));
                thongKeVip.setXien(rs.getString(4));
                thongKeVip.setVenhieu(rs.getString(5));
                thongKeVip.setVeit(rs.getString(6));
                thongKeVip.setGen_date(rs.getString(7));                
                thongKeVip.setCode(rs.getString(8));                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return thongKeVip;
    }
}
