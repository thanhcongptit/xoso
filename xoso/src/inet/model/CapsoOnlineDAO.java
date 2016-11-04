/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import inet.bean.CapsoOnline;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.Logger;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author 24h
 */
public class CapsoOnlineDAO {

    private DBPoolX poolX = null;
    Logger logger = new Logger(this.getClass().getName());

    public CapsoOnlineDAO() {
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public boolean create(CapsoOnline capsoOnline) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO capso_online(capso, sodiem, ngaychoi, gen_date, "
                    + "                            member_id, current_money) "
                    + "   VALUES (?, ?, str_to_date(?, '%d/%m/%Y %H:%i:%s'), now(), ?, ?)";
            conn = poolX.getConnection();
            //kiem tra xem cap so nay da choi trong ngay hom nay chua
            CapsoOnline capsoOnlineTmp = findByDate(conn, capsoOnline.getCapso(), capsoOnline.getNgaychoi(),
                    capsoOnline.getMemberId());
            if (capsoOnlineTmp != null) {
                //co roi thi update
                return update(conn, capsoOnline.getSodiem(), capsoOnlineTmp.getId());
            } else {
                //chua co thi insert
                ps = conn.prepareStatement(sql);
                int i = 1;
                ps.setString(i++, capsoOnline.getCapso());
                ps.setInt(i++, capsoOnline.getSodiem());
                ps.setString(i++, DateProc.getDateString(capsoOnline.getNgaychoi()) + " 00:00:00");
                ps.setBigDecimal(i++, capsoOnline.getMemberId());
                ps.setBigDecimal(i++, capsoOnline.getCurrentMoney());

                return ps.executeUpdate() == 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps);
        }

        return false;
    }

    public CapsoOnline findByDate(Connection conn, String capso, Timestamp ngaychoi, BigDecimal memberId) {

        CapsoOnline capsoOnline = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT id, capso, sodiem, ngaychoi, gen_date, nhay, thang_thua, member_id, "
                    + "          current_money "
                    + "   FROM capso_online"
                    + "   WHERE capso = ? and DATE_FORMAT(ngaychoi,'%d/%m/%Y') = DATE_FORMAT(?,'%d/%m/%Y')"
                    + "         and member_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, capso);
            ps.setTimestamp(2, ngaychoi);
            ps.setBigDecimal(3, memberId);
            rs = ps.executeQuery();
            if (rs.next()) {
                capsoOnline = new CapsoOnline();
                int i = 1;
                capsoOnline.setId(rs.getBigDecimal(i++));
                capsoOnline.setCapso(rs.getString(i++));
                capsoOnline.setSodiem(rs.getInt(i++));
                capsoOnline.setNgaychoi(rs.getTimestamp(i++));
                capsoOnline.setGenDate(rs.getTimestamp(i++));
                capsoOnline.setNhay(rs.getBigDecimal(i++));
                capsoOnline.setThangThua(rs.getBigDecimal(i++));
                capsoOnline.setMemberId(rs.getBigDecimal(i++));
                capsoOnline.setCurrentMoney(rs.getBigDecimal(i++));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(null, ps, rs);
        }

        return capsoOnline;
    }

    public boolean update(Connection conn, int sodiem, BigDecimal id) {

        PreparedStatement ps = null;

        try {
            String sql = "UPDATE capso_online "
                    + "   SET sodiem = ? "
                    + "   WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sodiem);
            ps.setBigDecimal(2, id);
            return ps.executeUpdate() == 1;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(null, ps);
        }

        return false;
    }

    public List<CapsoOnline> findListByDate(Timestamp ngaychoi, BigDecimal memberId) {

        CapsoOnline capsoOnline = null;
        List<CapsoOnline> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            String sql = "SELECT id, capso, sodiem, ngaychoi, gen_date, nhay, thang_thua, member_id, "
                    + "          current_money "
                    + "   FROM capso_online"
                    + "   WHERE DATE_FORMAT(ngaychoi,'%d/%m/%Y') = DATE_FORMAT(?,'%d/%m/%Y')"
                    + "         and member_id = ?";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, ngaychoi);
            ps.setBigDecimal(2, memberId);
            rs = ps.executeQuery();
            int i = 1;
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<CapsoOnline>();
                }
                capsoOnline = new CapsoOnline();
                i = 1;
                capsoOnline.setId(rs.getBigDecimal(i++));
                capsoOnline.setCapso(rs.getString(i++));
                capsoOnline.setSodiem(rs.getInt(i++));
                capsoOnline.setNgaychoi(rs.getTimestamp(i++));
                capsoOnline.setGenDate(rs.getTimestamp(i++));
                capsoOnline.setNhay(rs.getBigDecimal(i++));
                capsoOnline.setThangThua(rs.getBigDecimal(i++));
                capsoOnline.setMemberId(rs.getBigDecimal(i++));
                capsoOnline.setCurrentMoney(rs.getBigDecimal(i++));

                list.add(capsoOnline);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return list;
    }

    public List<CapsoOnline> findListByMemberHistory(BigDecimal memberId) {

        CapsoOnline capsoOnline = null;
        List<CapsoOnline> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            String sql = "SELECT id, capso, sodiem, ngaychoi, gen_date, nhay, thang_thua, member_id, "
                    + "          current_money "
                    + "   FROM capso_online"
                    + "   WHERE member_id = ? and nhay is not null"
                    + "   ORDER BY ngaychoi desc";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, memberId);
            rs = ps.executeQuery();
            int i = 1;
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<CapsoOnline>();
                }
                capsoOnline = new CapsoOnline();
                i = 1;
                capsoOnline.setId(rs.getBigDecimal(i++));
                capsoOnline.setCapso(rs.getString(i++));
                capsoOnline.setSodiem(rs.getInt(i++));
                capsoOnline.setNgaychoi(rs.getTimestamp(i++));
                capsoOnline.setGenDate(rs.getTimestamp(i++));
                capsoOnline.setNhay(rs.getBigDecimal(i++));
                capsoOnline.setThangThua(rs.getBigDecimal(i++));
                capsoOnline.setMemberId(rs.getBigDecimal(i++));
                capsoOnline.setCurrentMoney(rs.getBigDecimal(i++));

                list.add(capsoOnline);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return list;
    }

    public int findSoDiemDaChoi(BigDecimal memberId, Timestamp ngaychoi) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            String sql = "SELECT sum(sodiem) "
                    + "   FROM capso_online"
                    + "   WHERE member_id = ? and DATE_FORMAT(ngaychoi,'%d/%m/%Y') = DATE_FORMAT(?,'%d/%m/%Y')";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, memberId);
            ps.setTimestamp(2, ngaychoi);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return 0;
    }

    public HashMap<String, String> getTop10ChoiNhieu() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, String> hmTopChoiNhieu = null;

        try {
            String sql = "select count(capso) as luotchoi, capso, ngaychoi, gen_date "
                    + "   from capso_online"
                    + "   where DATE_FORMAT(ngaychoi,'%d/%m/%Y') = DATE_FORMAT(now(),'%d/%m/%Y')"
                    + "   group by capso"
                    + "   order by luotchoi desc"
                    + "   limit 0,10";

            System.out.println("cap so: " + sql);
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            String capso = "";
            while (rs.next()) {
                if (hmTopChoiNhieu == null) {
                    hmTopChoiNhieu = new HashMap<>();
                }
                if (rs.getString(2).trim().length() == 1) {
                    capso = "0" + rs.getString(2).trim();
                } else {
                    capso = rs.getString(2).trim();
                }
                hmTopChoiNhieu.put(capso, rs.getString(1));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return hmTopChoiNhieu;
    }

    public List<CapsoOnline> findBangChotSo() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CapsoOnline> list = null;

        try {
            String sql = "select id, capso, sodiem, ngaychoi, gen_date, nhay, thang_thua, member_id, "
                    + "          current_money, (select fullname from member where id = capso_online.member_id)  "
                    + "   from capso_online"
                    + "   where DATE_FORMAT(ngaychoi,'%d/%m/%Y') = DATE_FORMAT(now(),'%d/%m/%Y')"
                    + "   order by gen_date desc, member_id";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            CapsoOnline capsoOnline = null;
            int i = 1;
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<CapsoOnline>();
                }
                capsoOnline = new CapsoOnline();
                i = 1;
                capsoOnline.setId(rs.getBigDecimal(i++));
                capsoOnline.setCapso(rs.getString(i++));
                capsoOnline.setSodiem(rs.getInt(i++));
                capsoOnline.setNgaychoi(rs.getTimestamp(i++));
                capsoOnline.setGenDate(rs.getTimestamp(i++));
                capsoOnline.setNhay(rs.getBigDecimal(i++));
                capsoOnline.setThangThua(rs.getBigDecimal(i++));
                capsoOnline.setMemberId(rs.getBigDecimal(i++));
                capsoOnline.setCurrentMoney(rs.getBigDecimal(i++));
                capsoOnline.setMemberName(rs.getString(i++));

                list.add(capsoOnline);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return list;
    }

    public List<CapsoOnline> findBangTraoThuong() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CapsoOnline> list = null;

        try {
            String sql = "select id, capso, sodiem, ngaychoi, gen_date, nhay, thang_thua, member_id, "
                    + "          current_money, (select fullname from member where id = capso_online.member_id)  "
                    + "   from capso_online"
                    + "   where DATE_FORMAT(ngaychoi,'%d/%m/%Y') = DATE_FORMAT(now(),'%d/%m/%Y') and nhay is null"
                    + "   order by gen_date desc, member_id";
            conn = poolX.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            CapsoOnline capsoOnline = null;
            int i = 1;
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<CapsoOnline>();
                }
                capsoOnline = new CapsoOnline();
                i = 1;
                capsoOnline.setId(rs.getBigDecimal(i++));
                capsoOnline.setCapso(rs.getString(i++));
                capsoOnline.setSodiem(rs.getInt(i++));
                capsoOnline.setNgaychoi(rs.getTimestamp(i++));
                capsoOnline.setGenDate(rs.getTimestamp(i++));
                capsoOnline.setNhay(rs.getBigDecimal(i++));
                capsoOnline.setThangThua(rs.getBigDecimal(i++));
                capsoOnline.setMemberId(rs.getBigDecimal(i++));
                capsoOnline.setCurrentMoney(rs.getBigDecimal(i++));
                capsoOnline.setMemberName(rs.getString(i++));

                list.add(capsoOnline);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return list;
    }

    public boolean traothuong(CapsoOnline capsoOnline, int sonhay) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "update capso_online"
                    + "   set nhay = ?"
                    + "   where id = ?";
            conn = poolX.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sonhay);
            ps.setBigDecimal(2, capsoOnline.getId());

            if (ps.executeUpdate() == 1) {
                int sotien = 0;
                sotien = capsoOnline.getSodiem() * 23 * -1;
                if (sonhay > 0) {
                    sotien = capsoOnline.getSodiem() * 80 * sonhay + sotien;
                }
                sql = " update member"
                        + " set money = money + ?"
                        + " where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, sotien);
                ps.setBigDecimal(2, capsoOnline.getMemberId());

                if (ps.executeUpdate() == 1) {
                    conn.commit();
                    return true;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            poolX.releaseConnection(conn, ps);
        }

        return false;
    }
}
