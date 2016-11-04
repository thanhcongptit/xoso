/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.domain.SpecialAwardDTO;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;

/**
 *
 * @author conglt
 */
public class SpecialAwardDao {
	
	private DBPoolX poolX = null;

    public SpecialAwardDao() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SpecialAwardDTO> getListSpecialAward(int topRecord, String special, String opendate) {
        
        List<SpecialAwardDTO> awards = new ArrayList<SpecialAwardDTO>();
        String temp[] = opendate.split("-");
        String open = temp[2].trim() + "-" + temp[1].trim() + "-" + temp[0].trim();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = poolX.getConnection();
            statement = null;
            
            String sql = "Select DISTINCT open_date, special FROM lottery_result WHERE special like '%" + special
                        + "' AND code = 'XSTD' and symbol = 'TD' and special != '' AND open_date <= '" + open +"' order by open_date desc "
                        + "LIMIT " + topRecord;
            
            System.out.println("sql:" + sql);
            statement = connection.prepareCall(sql);
            rs = statement.executeQuery();
            
            while(rs.next()) {
                SpecialAwardDTO specialAward = new SpecialAwardDTO();
                specialAward.setOpenDate(rs.getDate("open_date"));
                specialAward.setSpecialAward(rs.getString("special"));
                awards.add(specialAward);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(connection, statement, null, rs);
        }

        return awards;
    }

}
