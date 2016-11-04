/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.domain.AwardDTO;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;

/**
 *
 * @author conglt
 */
public class LotoTheoGiaiDao {

    private DBPoolX poolX = null;

    public LotoTheoGiaiDao() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AwardDTO> getListAward(String fromDate, String toDate) {
        List<AwardDTO> awardDTOs = new ArrayList<>();

//        List<AwardDTO> awardDTOsByKey = CommonUtil.getListAwardDTOByKey(fromDate + toDate);
//
//        if (awardDTOsByKey != null && awardDTOsByKey.size() > 0) {
//            return awardDTOsByKey;
//        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String temp[] = toDate.split("-");
        String date1 = temp[2].trim() + "-" + temp[1].trim() + "-" + temp[0].trim();

        String temp1[] = fromDate.split("-");
        String date2 = temp1[2].trim() + "-" + temp1[1].trim() + "-" + temp1[0].trim();

        try {
            connection = poolX.getConnection();
            statement = null;

            String sql = "Select DISTINCT open_date, special, first, second, third, fourth, fifth, sixth, seventh  FROM lottery_result WHERE "
                    + " code = 'XSTD' and symbol = 'TD' and special != '' AND seventh !='' AND sixth != '' AND fifth != '' "
                    + " AND fourth != '' AND third != '' AND second !='' AND first != '' "
                    + " AND open_date <='" + date1 + "'" + " AND open_date >='" + date2 + "'"
                    + " order by open_date desc ";
            System.out.println("sql: " + sql);
            statement = connection.prepareCall(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                String special = rs.getString("special");
                String first = rs.getString("first");
                String second = rs.getString("second");
                String third = rs.getString("third");
                String fourth = rs.getString("fourth");
                String fifth = rs.getString("fifth");
                String sixth = rs.getString("sixth");
                String seventh = rs.getString("seventh");
                Date openDate = rs.getDate("open_date");

                AwardDTO awardDTO = new AwardDTO(special, first, second, third, fourth, fifth, sixth, seventh, openDate);
                awardDTOs.add(awardDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(connection, statement, rs);
        }

//        if(awardDTOs.size() > 0) {
//            CommonUtil.putListAwardDTOByKey(fromDate+toDate, awardDTOs);
//        }
        
        System.out.println("size: " + awardDTOs.size());
        return awardDTOs;
    }

}
