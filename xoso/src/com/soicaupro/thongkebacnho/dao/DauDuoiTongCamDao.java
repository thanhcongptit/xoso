/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.domain.DauCamDTO;
import com.soicaupro.thongkebacnho.domain.ResultOneDayDTO;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;

/**
 *
 * @author conglt
 */
public class DauDuoiTongCamDao {
	
	Logger logger = null;
	private DBPoolX poolX = null;

    public DauDuoiTongCamDao() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<ResultOneDayDTO> getCoupleLoto(List<DauCamDTO> awards, String[] couple, int day) {
        
        List<ResultOneDayDTO> resultOneDayDTOs = new ArrayList<>();
        
        if (awards != null && awards.size() > 0) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT DISTINCT special, first, second, third, fourth, fifth, sixth, seventh, open_date");
            sql.append(" FROM lottery_result");
            sql.append(" WHERE (");
            for (int i = 0; i < awards.size() - 1; i++) {
                Date date = awards.get(i).getOpenDate();
                sql.append(" open_date = '").append(CommonUtil.addDate(date, day)).append("' OR ");
            }

            Date date = awards.get(awards.size() - 1).getOpenDate();
            sql.append(" open_date = '").append(CommonUtil.addDate(date, day)).append("' )");

            sql.append(" AND ("
                    + "special like '%").append(couple[0]).append("'").append(" OR special like '%").append(couple[1]).append("'");
            sql.append("     OR  first like '%").append(couple[0]).append("'").append(" OR first like '%").append(couple[1]).append("'");
            
            sql.append("     OR  second like '%").append(couple[0]).append("'").append(" OR second like '%").append(couple[1]).append("'");
            sql.append("     OR  second like '%").append(couple[0]).append("-%'").append(" OR second like '%").append(couple[1]).append("-%'");

            sql.append("     OR  third like '%").append(couple[0]).append("'").append(" OR third like '%").append(couple[1]).append("'");
            sql.append("     OR  third like '%").append(couple[0]).append("-%'").append(" OR third like '%").append(couple[1]).append("-%'");

            sql.append("     OR  fourth like '%").append(couple[0]).append("'").append(" OR fourth like '%").append(couple[1]).append("'");
            sql.append("     OR  fourth like '%").append(couple[0]).append("-%'").append(" OR fourth like '%").append(couple[1]).append("-%'");

            sql.append("     OR  fifth like '%").append(couple[0]).append("'").append(" OR fifth like '%").append(couple[1]).append("'");
            sql.append("     OR  fifth like '%").append(couple[0]).append("-%'").append(" OR fifth like '%").append(couple[1]).append("-%'");

            sql.append("     OR  sixth like '%").append(couple[0]).append("'").append(" OR sixth like '%").append(couple[1]).append("'");
            sql.append("     OR  sixth like '%").append(couple[0]).append("-%'").append(" OR sixth like '%").append(couple[1]).append("-%'");

            sql.append("     OR  seventh like '%").append(couple[0]).append("'").append(" OR seventh like '%").append(couple[1]).append("'");
            sql.append("     OR  seventh like '%").append(couple[0]).append("-%'").append(" OR seventh like '%").append(couple[1]).append("-%'");
            sql.append(" )");

            sql.append(" AND code = 'XSTD' and symbol = 'TD' and special != '' order by open_date desc ");

            System.out.println("sql: " + sql.toString());
            
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            
            try {
                connection = poolX.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql.toString());
                
                while(rs.next()) {
                    String specail = rs.getString("special");
                    String first = rs.getString("first");
                    String second = rs.getString("second");
                    String third = rs.getString("third");
                    String fourth = rs.getString("fourth");
                    String fifth = rs.getString("fifth");
                    String sixth = rs.getString("sixth");
                    String senventh = rs.getString("seventh");
                    Date open_date = rs.getDate("open_date");
                    
                    String result = CommonUtil.getStringRS(couple, specail, 
                            first, second, third, fourth, fifth, sixth, senventh);
                    
                    ResultOneDayDTO resultOneDayDTO = new ResultOneDayDTO();
                    resultOneDayDTO.setOpen_date(open_date);
                    resultOneDayDTO.setResult(result);
                    
                    resultOneDayDTOs.add(resultOneDayDTO);
                    
                }
                
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                poolX.releaseConnection(connection, null, statement, rs);
            }
            
        }
        return resultOneDayDTOs;
    }
    
    public List<ResultOneDayDTO> getLoto(List<DauCamDTO> awards, String loto, int day) {
        
        List<ResultOneDayDTO> resultOneDayDTOs = new ArrayList<>();
        
        if (awards != null && awards.size() > 0) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT DISTINCT special, first, second, third, fourth, fifth, sixth, seventh, open_date");
            sql.append(" FROM lottery_result");
            sql.append(" WHERE (");
            for (int i = 0; i < awards.size() - 1; i++) {
                Date date = awards.get(i).getOpenDate();
                sql.append(" open_date = '").append(CommonUtil.addDate(date, day)).append("' OR ");
            }

            Date date = awards.get(awards.size() - 1).getOpenDate();
            sql.append(" open_date = '").append(CommonUtil.addDate(date, day)).append("' )");

            sql.append(" AND (special like '%").append(loto).append("'");     
            sql.append("     OR  first like '%").append(loto).append("'");
            
            sql.append("     OR  second like '%").append(loto).append("'");
            sql.append("     OR  second like '%").append(loto).append("-%'");

            sql.append("     OR  third like '%").append(loto).append("'");
            sql.append("     OR  third like '%").append(loto).append("-%'");

            sql.append("     OR  fourth like '%").append(loto).append("'");
            sql.append("     OR  fourth like '%").append(loto).append("-%'");

            sql.append("     OR  fifth like '%").append(loto).append("'");
            sql.append("     OR  fifth like '%").append(loto).append("-%'");

            sql.append("     OR  sixth like '%").append(loto).append("'");
            sql.append("     OR  sixth like '%").append(loto).append("-%'");

            sql.append("     OR  seventh like '%").append(loto).append("'");
            sql.append("     OR  seventh like '%").append(loto).append("-%'");
            sql.append(" )");

            sql.append(" AND code = 'XSTD' and symbol = 'TD' and special != '' order by open_date desc ");

            //System.out.println("sql: " + sql.toString());
            
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            
            try {
                connection = poolX.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql.toString());
                
                while(rs.next()) {
                    String specail = rs.getString("special");
                    String first = rs.getString("first");
                    String second = rs.getString("second");
                    String third = rs.getString("third");
                    String fourth = rs.getString("fourth");
                    String fifth = rs.getString("fifth");
                    String sixth = rs.getString("sixth");
                    String senventh = rs.getString("seventh");
                    Date open_date = rs.getDate("open_date");
                    
                    String result = CommonUtil.getStringLotoRS(loto, specail, 
                            first, second, third, fourth, fifth, sixth, senventh);
                    
                    ResultOneDayDTO resultOneDayDTO = new ResultOneDayDTO();
                    resultOneDayDTO.setOpen_date(open_date);
                    resultOneDayDTO.setResult(result);
                    
                    resultOneDayDTOs.add(resultOneDayDTO);
                    
                }
                
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                poolX.releaseConnection(connection, null, statement, rs);
            }
            
        }
        return resultOneDayDTOs;
    }
}
