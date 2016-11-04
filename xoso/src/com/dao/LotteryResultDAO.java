/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.LotteryCompany;
import com.bean.LotteryResult;
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
 * @author HanhDung private String open_date; private String special; private
 * String first; private String second; private String third; private String
 * fourth; private String fifth; private String sixth; private String seventh;
 * private String eighth; private String create_date;
 */
public class LotteryResultDAO {

    Logger logger = null;
    private DBPoolX poolX = null;

    public LotteryResultDAO() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LotteryCompany> findLotteryCompany() {
        List<LotteryCompany> list = null;

        LotteryCompany lotteryCompany = null;
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            sql = "select code,name,open_days,website,region from lottery_company";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            list = new ArrayList<>();
            int index = 1;
            while (rs.next()) {
                index = 1;
                lotteryCompany = new LotteryCompany();
                lotteryCompany.setCode(rs.getString(index++));
                lotteryCompany.setCompany(rs.getString(index++));
                lotteryCompany.setOpendate(rs.getString(index++));
                lotteryCompany.setCompanyLink(rs.getString(index++));
                lotteryCompany.setRegion(rs.getString(index++));

                list.add(lotteryCompany);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return list;
    }

    public List<LotteryResult> findLotteryResult(String code, String sDate, String eDate) {

        List<LotteryResult> list = null;
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            sql = "select code,symbol,price,date_format(open_date,'%d/%m/%Y'),special,first,second,third,fourth,fifth,"
                    + "sixth,seventh,eighth,date_format(create_date,'%d/%m/%Y') from lottery_result "
                    + "where code=? and date(open_date) between date(?) and date(?) order by open_date desc";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, sDate);
            ps.setString(3, eDate);

            rs = ps.executeQuery();
            list = new ArrayList<>();
            LotteryResult lotteryResult = null;
            int index = 1;
            while (rs.next()) {
                index = 1;
                lotteryResult = new LotteryResult();
                lotteryResult.setCode(rs.getString(index++));
                lotteryResult.setSymbol(rs.getString(index++));
                lotteryResult.setPrice(rs.getString(index++));
                lotteryResult.setOpen_date(rs.getString(index++));
                lotteryResult.setSpecial(rs.getString(index++));
                lotteryResult.setFirst(rs.getString(index++));
                lotteryResult.setSecond(rs.getString(index++));
                lotteryResult.setThird(rs.getString(index++));
                lotteryResult.setFourth(rs.getString(index++));
                lotteryResult.setFifth(rs.getString(index++));
                lotteryResult.setSixth(rs.getString(index++));
                lotteryResult.setSeventh(rs.getString(index++));
                lotteryResult.setEighth(rs.getString(index++));
                lotteryResult.setCreate_date(rs.getString(index++));

                list.add(lotteryResult);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return list;
    }

    public List<LotteryResult> findLotteryResultAsc(String code, String sDate, String eDate) {

        List<LotteryResult> list = null;
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            sql = "select code,symbol,price,date_format(open_date,'%d/%m/%Y'),special,first,second,third,fourth,fifth,"
                    + "sixth,seventh,eighth,date_format(create_date,'%d/%m/%Y') from lottery_result "
                    + "where code=? and date(open_date) between date(?) and date(?) order by open_date asc";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, sDate);
            ps.setString(3, eDate);

            rs = ps.executeQuery();
            list = new ArrayList<>();
            LotteryResult lotteryResult = null;
            int index = 1;
            while (rs.next()) {
                index = 1;
                lotteryResult = new LotteryResult();
                lotteryResult.setCode(rs.getString(index++));
                lotteryResult.setSymbol(rs.getString(index++));
                lotteryResult.setPrice(rs.getString(index++));
                lotteryResult.setOpen_date(rs.getString(index++));
                lotteryResult.setSpecial(rs.getString(index++));
                lotteryResult.setFirst(rs.getString(index++));
                lotteryResult.setSecond(rs.getString(index++));
                lotteryResult.setThird(rs.getString(index++));
                lotteryResult.setFourth(rs.getString(index++));
                lotteryResult.setFifth(rs.getString(index++));
                lotteryResult.setSixth(rs.getString(index++));
                lotteryResult.setSeventh(rs.getString(index++));
                lotteryResult.setEighth(rs.getString(index++));
                lotteryResult.setCreate_date(rs.getString(index++));

                list.add(lotteryResult);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return list;
    }

    public LotteryResult findLatestLotteryResult(String code) {
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        LotteryResult lotteryResult = null;
        try {
            sql = "select code,symbol,price,date_format(open_date,'%d/%m/%Y'),special,first,second,third,fourth,fifth,"
                    + "sixth,seventh,eighth,date_format(create_date,'%d/%m/%Y') from lottery_result "
                    + "where code=? order by open_date desc limit 1";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);

            rs = ps.executeQuery();
            int index = 1;
            if (rs.next()) {
                index = 1;
                lotteryResult = new LotteryResult();
                lotteryResult.setCode(rs.getString(index++));
                lotteryResult.setSymbol(rs.getString(index++));
                lotteryResult.setPrice(rs.getString(index++));
                lotteryResult.setOpen_date(rs.getString(index++));
                lotteryResult.setSpecial(rs.getString(index++));
                lotteryResult.setFirst(rs.getString(index++));
                lotteryResult.setSecond(rs.getString(index++));
                lotteryResult.setThird(rs.getString(index++));
                lotteryResult.setFourth(rs.getString(index++));
                lotteryResult.setFifth(rs.getString(index++));
                lotteryResult.setSixth(rs.getString(index++));
                lotteryResult.setSeventh(rs.getString(index++));
                lotteryResult.setEighth(rs.getString(index++));
                lotteryResult.setCreate_date(rs.getString(index++));
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return lotteryResult;
    }

    public static void main(String[] args) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        //List<LotteryResult> list=lotteryResultDAO.findLotteryResult("XSTD", "2016-01-04", "2016-01-04");
        List<LotteryCompany> list = lotteryResultDAO.findLotteryCompany();
        if (!list.isEmpty()) {
            System.out.println(list.get(0).getCompany());
        }
    }
}
