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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.domain.DauCamDTO;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;

/**
 *
 * @author conglt
 */
public class DauCamDao {
	
	Logger logger = null;
	private DBPoolX poolX = null;

    public DauCamDao() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DauCamDTO> getListCamDTO(int count, String date, String daucam) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String temp[] = date.split("-");
        String open_date = temp[2].trim() + "-" + temp[1].trim() + "-" + temp[0].trim();
        List<DauCamDTO> dauCamDTOs = new ArrayList<>();

        try {
            connection = poolX.getConnection();
            statement = null;

            String sql = "Select DISTINCT open_date, special, first, second, third, fourth, fifth, sixth, seventh  FROM lottery_result WHERE "
                    + " code = 'XSTD' and symbol = 'TD' and special != '' AND open_date <='" + open_date + "'"
                    + " order by open_date desc ";
            statement = connection.prepareCall(sql);
            rs = statement.executeQuery();

            int index = 0;
            while (rs.next() && index < count) {
                String special = rs.getString("special");
                if (String.valueOf(special.charAt(special.length() - 2)).equals(daucam)) {
                    continue;
                }

                String first = rs.getString("first");
                if (String.valueOf(first.charAt(first.length() - 2)).equals(daucam)) {
                    continue;
                }

                String second = rs.getString("second");
                if (!checkDauCam(second, daucam)) {
                    continue;
                }

                String third = rs.getString("third");
                if (!checkDauCam(third, daucam)) {
                    continue;
                }

                String fourth = rs.getString("fourth");
                if (!checkDauCam(fourth, daucam)) {
                    continue;
                }

                String fifth = rs.getString("fifth");
                if (!checkDauCam(fifth, daucam)) {
                    continue;
                }

                String sixth = rs.getString("sixth");
                if (!checkDauCam(sixth, daucam)) {
                    continue;
                }

                String seventh = rs.getString("seventh");
                if (!checkDauCam(seventh, daucam)) {
                    continue;
                }

                Date openDate = rs.getDate("open_date");

                DauCamDTO dauCamDTO = new DauCamDTO();
                dauCamDTO.setOpenDate(openDate);
                dauCamDTOs.add(dauCamDTO);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(connection, statement, rs);
        }

        return dauCamDTOs;
    }

    private static boolean checkDauCam(String award, String daucam) {
        String s[] = award.split("-");
        boolean rs = true;

        for (String t : s) {
            if (String.valueOf(t.charAt(t.length() - 2)).equalsIgnoreCase(daucam)) {
                return false;
            }
        }
        return rs;
    }

    //----
    public List<DauCamDTO> getListDuoiCamDTO(int count, String date, String duoicam) {
        List<DauCamDTO> resultCamDTOs = new ArrayList<>();
        String temp[] = date.split("-");
        String openDate = temp[2].trim() + "-" + temp[1].trim() + "-" + temp[0].trim();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT open_date");
        sql.append(" FROM lottery_result");
        sql.append(" WHERE open_date <='").append(openDate).append("'");
        sql.append("     AND (special not like '%").append(duoicam).append("'");
        sql.append("     AND  first not like '%").append(duoicam).append("'");

        sql.append("     AND  second not like '%").append(duoicam).append("'");
        sql.append("     AND  second not like '%").append(duoicam).append("-%'");

        sql.append("     AND  third not like '%").append(duoicam).append("'");
        sql.append("     AND  third not like '%").append(duoicam).append("-%'");

        sql.append("     AND  fourth not like '%").append(duoicam).append("'");
        sql.append("     AND  fourth not like '%").append(duoicam).append("-%'");

        sql.append("     AND  fifth not like '%").append(duoicam).append("'");
        sql.append("     AND  fifth not like '%").append(duoicam).append("-%'");

        sql.append("     AND  sixth not like '%").append(duoicam).append("'");
        sql.append("     AND  sixth not like '%").append(duoicam).append("-%'");

        sql.append("     AND  seventh not like '%").append(duoicam).append("'");
        sql.append("     AND  seventh not like '%").append(duoicam).append("-%'");
        sql.append(" )");

        sql.append(" AND code = 'XSTD' and symbol = 'TD' and special != '' order by open_date desc");
        sql.append(" LIMIT ").append(count);

        System.out.println("sql: " + sql.toString());
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = poolX.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());

            while (rs.next()) {

                Date open_date = rs.getDate("open_date");
                DauCamDTO camDTO = new DauCamDTO();
                camDTO.setOpenDate(open_date);
                resultCamDTOs.add(camDTO);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(connection, null, statement, rs);
        }

        return resultCamDTOs;
    }

    public List<DauCamDTO> getListTongCamDTO(int count, String date, String tongcam) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String temp[] = date.split("-");
        String open_date = temp[2].trim() + "-" + temp[1].trim() + "-" + temp[0].trim();
        List<DauCamDTO> dauCamDTOs = new ArrayList<>();

        try {
            connection = poolX.getConnection();
            statement = null;

            String sql = "Select DISTINCT open_date, special, first, second, third, fourth, fifth, sixth, seventh  FROM lottery_result WHERE "
                    + " code = 'XSTD' and symbol = 'TD' and special != '' AND open_date <='" + open_date + "'"
                    + " order by open_date desc ";
            statement = connection.prepareCall(sql);
            rs = statement.executeQuery();

            int index = 0;
            while (rs.next() && index < count) {
                String special = rs.getString("special");
                int si1 = Integer.parseInt("" + special.charAt(special.length() - 1));
                int si2 = Integer.parseInt("" + special.charAt(special.length() - 2));
                if (Integer.parseInt(tongcam) == (si1 + si2) || (si1+si2 -10) == Integer.parseInt(tongcam)) {
                    continue;
                }

                String first = rs.getString("first");
                int fi1 = Integer.parseInt("" + first.charAt(first.length() - 1));
                int fi2 = Integer.parseInt("" + first.charAt(first.length() - 2));
                if (Integer.parseInt(tongcam) == (fi1 + fi2) || (fi1+fi2 -10) == Integer.parseInt(tongcam)) {
                    continue;
                }

                String second = rs.getString("second");
                if (!checkTongCam(second, tongcam)) {
                    continue;
                }

                String third = rs.getString("third");
                if (!checkTongCam(third, tongcam)) {
                    continue;
                }

                String fourth = rs.getString("fourth");
                if (!checkTongCam(fourth, tongcam)) {
                    continue;
                }

                String fifth = rs.getString("fifth");
                if (!checkTongCam(fifth, tongcam)) {
                    continue;
                }

                String sixth = rs.getString("sixth");
                if (!checkTongCam(sixth, tongcam)) {
                    continue;
                }

                String seventh = rs.getString("seventh");
                if (!checkTongCam(seventh, tongcam)) {
                    continue;
                }

                Date openDate = rs.getDate("open_date");

                DauCamDTO dauCamDTO = new DauCamDTO();
                dauCamDTO.setOpenDate(openDate);
                dauCamDTOs.add(dauCamDTO);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolX.releaseConnection(connection, statement, rs);
        }

        return dauCamDTOs;
    }

    private static boolean checkTongCam(String award, String tongcam) {
        String s[] = award.split("-");
        boolean rs = true;

        for (String t : s) {
            int fi1 = Integer.parseInt("" + t.charAt(t.length() - 1));
            int fi2 = Integer.parseInt("" + t.charAt(t.length() - 2));
            if (Integer.parseInt(tongcam) == (fi1+fi2) || (fi1+fi2 -10) == Integer.parseInt(tongcam)) {
                return false;
            }
        }
        return rs;
    }
}
