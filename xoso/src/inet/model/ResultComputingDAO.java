package inet.model;

import inet.bean.DienToanThanTai;
import inet.bean.LotteryComputing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import inet.bean.ResultComputing;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.DateProc;
import inet.util.Logger;
import java.util.List;

public class ResultComputingDAO {

    private Logger logger = null;
    private DBPoolX poolX = null;

    public ResultComputingDAO() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TUYENTT - 28/09/2011 Check phone is existed in database or no.
     *
     * @param phone
     * @return true if phone is existed or false if phone is not existed .
     */
    public LotteryComputing getRow(int id) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ResultComputing resultComputing = null;
        LotteryComputing lotteryComputing = new LotteryComputing();
        String strSQL = null;

        try {
            strSQL = "SELECT * FROM lottery_result_computing WHERE id = ?";
            conn = poolX.getConnection();
            psmt = conn.prepareStatement(strSQL);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if (rs.next()) {
                resultComputing = new ResultComputing();
                resultComputing.setId(rs.getInt("id"));
                resultComputing.setCode(rs.getString("code"));
                resultComputing.setDt123First(rs.getString("dt123_first"));
                resultComputing.setDt123Second(rs.getString("dt123_second"));
                resultComputing.setDt123Third(rs.getString("dt123_third"));
                resultComputing.setDt636First(rs.getString("dt636_first"));
                resultComputing.setDt636Second(rs.getString("dt636_second"));
                resultComputing.setDt636Third(rs.getString("dt636_third"));
                resultComputing.setDt636Fourth(rs.getString("dt636_fourth"));
                resultComputing.setDt636Fifth(rs.getString("dt636_fifth"));
                resultComputing.setDt636Sixth(rs.getString("dt636_sixth"));
                resultComputing.setThanTai(rs.getString("than_tai"));
                resultComputing.setOpenDate(rs.getTimestamp("open_date"));
                resultComputing.setLastUpdate(rs.getTimestamp("last_update"));

                lotteryComputing.setDt123(resultComputing.getDt123First() + "-" + resultComputing.getDt123Second() + "-" + resultComputing.getDt123Third());
                lotteryComputing.setDt636(resultComputing.getDt636First() + "-" + resultComputing.getDt636Second() + "-" + resultComputing.getDt636Third() + "-" + resultComputing.getDt636Fourth() + "-" + resultComputing.getDt636Fifth() + "-" + resultComputing.getDt636Sixth());
                lotteryComputing.setXstt(resultComputing.getThanTai());
            }
        } catch (Exception e) {
            logger.error("getRow: SQLException Error executing " + strSQL + ">>>" + e.toString());
            poolX.releaseConnection(conn, psmt, rs);
        } finally {
            poolX.releaseConnection(conn, psmt, rs);
        }
        return lotteryComputing;
    }

    public LotteryComputing getRowByCodeOnDate(String code, String ddmmyyyy) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ResultComputing resultComputing = null;
        String strSQL = null;
        LotteryComputing lotteryComputing = null;
        try {
            strSQL = "SELECT * FROM lottery_result_computing "
                    + "WHERE UPPER(code) = UPPER(?) "
                    + " AND DATE_FORMAT(open_date, '%d/%m/%Y') = ? ";
            conn = poolX.getConnection();
            psmt = conn.prepareStatement(strSQL);
            psmt.setString(1, code);
            psmt.setString(2, ddmmyyyy);
            rs = psmt.executeQuery();

            if (rs.next()) {
                resultComputing = new ResultComputing();
                resultComputing.setId(rs.getInt("id"));
                resultComputing.setCode(rs.getString("code"));
                resultComputing.setDt123First(rs.getString("dt123_first"));
                resultComputing.setDt123Second(rs.getString("dt123_second"));
                resultComputing.setDt123Third(rs.getString("dt123_third"));
                resultComputing.setDt636First(rs.getString("dt636_first"));
                resultComputing.setDt636Second(rs.getString("dt636_second"));
                resultComputing.setDt636Third(rs.getString("dt636_third"));
                resultComputing.setDt636Fourth(rs.getString("dt636_fourth"));
                resultComputing.setDt636Fifth(rs.getString("dt636_fifth"));
                resultComputing.setDt636Sixth(rs.getString("dt636_sixth"));
                resultComputing.setThanTai(rs.getString("than_tai"));
                resultComputing.setOpenDate(rs.getTimestamp("open_date"));
                resultComputing.setLastUpdate(rs.getTimestamp("last_update"));

                lotteryComputing = new LotteryComputing();
                lotteryComputing.setDt123(resultComputing.getDt123First() + "-" + resultComputing.getDt123Second() + "-" + resultComputing.getDt123Third());
                lotteryComputing.setDt636(resultComputing.getDt636First() + "-" + resultComputing.getDt636Second() + "-" + resultComputing.getDt636Third() + "-" + resultComputing.getDt636Fourth() + "-" + resultComputing.getDt636Fifth() + "-" + resultComputing.getDt636Sixth());
                lotteryComputing.setXstt(resultComputing.getThanTai());

            }
        } catch (Exception e) {
            logger.error("getRowByCodeOnDate: SQLException Error executing " + strSQL + ">>>" + e.toString());
            poolX.releaseConnection(conn, psmt, rs);
        } finally {
            poolX.releaseConnection(conn, psmt, rs);
        }
        return lotteryComputing;
    }

    public LotteryComputing findResultOnDate(String ddmmyyyy) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ResultComputing resultComputing = null;
        String strSQL = null;
        LotteryComputing lotteryComputing = null;
        try {
            strSQL = "SELECT * FROM lottery_result_computing "
                    + "WHERE DATE_FORMAT(open_date, '%d/%m/%Y') = ? ";
            conn = poolX.getConnection();
            psmt = conn.prepareStatement(strSQL);
            psmt.setString(1, ddmmyyyy);
            rs = psmt.executeQuery();
            lotteryComputing = new LotteryComputing();
            while (rs.next()) {
                resultComputing = new ResultComputing();
                resultComputing.setId(rs.getInt("id"));
                resultComputing.setCode(rs.getString("code"));
                resultComputing.setDt123First(rs.getString("dt123_first"));
                resultComputing.setDt123Second(rs.getString("dt123_second"));
                resultComputing.setDt123Third(rs.getString("dt123_third"));
                resultComputing.setDt636First(rs.getString("dt636_first"));
                resultComputing.setDt636Second(rs.getString("dt636_second"));
                resultComputing.setDt636Third(rs.getString("dt636_third"));
                resultComputing.setDt636Fourth(rs.getString("dt636_fourth"));
                resultComputing.setDt636Fifth(rs.getString("dt636_fifth"));
                resultComputing.setDt636Sixth(rs.getString("dt636_sixth"));
                resultComputing.setThanTai(rs.getString("than_tai"));
                resultComputing.setOpenDate(rs.getTimestamp("open_date"));
                resultComputing.setLastUpdate(rs.getTimestamp("last_update"));

                if ("XSTTI".equalsIgnoreCase(resultComputing.getCode())) {
                    lotteryComputing.setXstt(resultComputing.getThanTai());
                } else if ("XSDT123".equalsIgnoreCase(resultComputing.getCode())) {
                    lotteryComputing.setDt123(resultComputing.getDt123First() + "-" + resultComputing.getDt123Second() + "-" + resultComputing.getDt123Third());
                } else if ("XSDT636".equalsIgnoreCase(resultComputing.getCode())) {
                    lotteryComputing.setDt636(resultComputing.getDt636First() + "-" + resultComputing.getDt636Second() + "-" + resultComputing.getDt636Third() + "-" + resultComputing.getDt636Fourth() + "-" + resultComputing.getDt636Fifth() + "-" + resultComputing.getDt636Sixth());
                }
            }
        } catch (Exception e) {
            logger.error("findResultOnDate: SQLException Error executing " + strSQL + ">>>" + e.toString());
            System.out.println("findResultOnDate: SQLException Error executing " + strSQL + ">>>" + e.toString());
            poolX.releaseConnection(conn, psmt, rs);
        } finally {
            poolX.releaseConnection(conn, psmt, rs);
        }
        return lotteryComputing;
    }

    public List<DienToanThanTai> findResultByCodeOnDate(String code, String startDate, String endDate) {
        if (startDate == null || startDate.length() != 10 || endDate == null || endDate.length() != 10) {
            return null;
        }
        startDate = startDate + "00:00:00";
        endDate = endDate + "23:59:59";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ResultComputing resultComputing = null;
        String strSQL = null;
        String result = "";
        List<DienToanThanTai> vResult = null;
        DienToanThanTai computing = null;
        try {
            strSQL = "SELECT * FROM lottery_result_computing where code = ? and "
                    + " open_date >= STR_TO_DATE(?, '%d/%m/%Y %H:%i:%s') "
                    + " and open_date <= STR_TO_DATE(?, '%d/%m/%Y %H:%i:%s') "
                    + " order by open_date desc ";
            conn = poolX.getConnection();
            psmt = conn.prepareStatement(strSQL);
            psmt.setString(1, code);
            psmt.setString(2, startDate);
            psmt.setString(3, endDate);
            rs = psmt.executeQuery();
            vResult = new Vector<DienToanThanTai>();
            while (rs.next()) {
                resultComputing = new ResultComputing();
                resultComputing.setId(rs.getInt("id"));
                resultComputing.setCode(rs.getString("code"));
                resultComputing.setDt123First(rs.getString("dt123_first"));
                resultComputing.setDt123Second(rs.getString("dt123_second"));
                resultComputing.setDt123Third(rs.getString("dt123_third"));
                resultComputing.setDt636First(rs.getString("dt636_first"));
                resultComputing.setDt636Second(rs.getString("dt636_second"));
                resultComputing.setDt636Third(rs.getString("dt636_third"));
                resultComputing.setDt636Fourth(rs.getString("dt636_fourth"));
                resultComputing.setDt636Fifth(rs.getString("dt636_fifth"));
                resultComputing.setDt636Sixth(rs.getString("dt636_sixth"));
                resultComputing.setThanTai(rs.getString("than_tai"));
                resultComputing.setOpenDate(rs.getTimestamp("open_date"));
                resultComputing.setLastUpdate(rs.getTimestamp("last_update"));

                computing = new DienToanThanTai();
                computing.setDate(DateProc.Timestamp2DDMMYYYY(resultComputing.getOpenDate()));
                if ("XSTTI".equalsIgnoreCase(code)) {
                    computing.setResult(resultComputing.getThanTai());
                } else if ("XSDT123".equalsIgnoreCase(code)) {
                    computing.setResult(resultComputing.getDt123First() + "-" + resultComputing.getDt123Second() + "-" + resultComputing.getDt123Third());
                } else if ("XSDT636".equalsIgnoreCase(code)) {
                    computing.setResult(resultComputing.getDt636First() + "-" + resultComputing.getDt636Second() + "-" + resultComputing.getDt636Third() + "-" + resultComputing.getDt636Fourth() + "-" + resultComputing.getDt636Fifth() + "-" + resultComputing.getDt636Sixth());
                }

                vResult.add(computing);
            }
        } catch (Exception e) {
            logger.error("findResultByCodeOnDate: SQLException Error executing " + strSQL + ">>>" + e.toString());
            System.out.println("findResultByCodeOnDate: SQLException Error executing " + strSQL + ">>>" + e.toString());
            poolX.releaseConnection(conn, psmt, rs);
        } finally {
            poolX.releaseConnection(conn, psmt, rs);
        }
        return vResult;
    }

    public List<DienToanThanTai> findResultByCodeOnDate(String code) {

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ResultComputing resultComputing = null;
        String strSQL = null;
        String result = "";
        List<DienToanThanTai> vResult = null;
        DienToanThanTai computing = null;
        try {
            strSQL = "SELECT  * FROM lottery_result_computing where code = ?"
                    + " order by open_date desc ";
            conn = poolX.getConnection();
            psmt = conn.prepareStatement(strSQL);
            psmt.setString(1, code);

            rs = psmt.executeQuery();
            vResult = new Vector<DienToanThanTai>();
            if (rs.next()) {
                resultComputing = new ResultComputing();
                resultComputing.setId(rs.getInt("id"));
                resultComputing.setCode(rs.getString("code"));
                resultComputing.setDt123First(rs.getString("dt123_first"));
                resultComputing.setDt123Second(rs.getString("dt123_second"));
                resultComputing.setDt123Third(rs.getString("dt123_third"));
                resultComputing.setDt636First(rs.getString("dt636_first"));
                resultComputing.setDt636Second(rs.getString("dt636_second"));
                resultComputing.setDt636Third(rs.getString("dt636_third"));
                resultComputing.setDt636Fourth(rs.getString("dt636_fourth"));
                resultComputing.setDt636Fifth(rs.getString("dt636_fifth"));
                resultComputing.setDt636Sixth(rs.getString("dt636_sixth"));
                resultComputing.setThanTai(rs.getString("than_tai"));
                resultComputing.setOpenDate(rs.getTimestamp("open_date"));
                resultComputing.setLastUpdate(rs.getTimestamp("last_update"));

                computing = new DienToanThanTai();
                computing.setDate(DateProc.Timestamp2DDMMYYYY(resultComputing.getOpenDate()));
                if ("XSTTI".equalsIgnoreCase(code)) {
                    computing.setResult(resultComputing.getThanTai());
                } else if ("XSDT123".equalsIgnoreCase(code)) {
                    computing.setResult(resultComputing.getDt123First() + "-" + resultComputing.getDt123Second() + "-" + resultComputing.getDt123Third());
                } else if ("XSDT636".equalsIgnoreCase(code)) {
                    computing.setResult(resultComputing.getDt636First() + "-" + resultComputing.getDt636Second() + "-" + resultComputing.getDt636Third() + "-" + resultComputing.getDt636Fourth() + "-" + resultComputing.getDt636Fifth() + "-" + resultComputing.getDt636Sixth());
                }

                vResult.add(computing);
            }
        } catch (Exception e) {
            logger.error("findResultByCodeOnDate: SQLException Error executing " + strSQL + ">>>" + e.toString());
            System.out.println("findResultByCodeOnDate: SQLException Error executing " + strSQL + ">>>" + e.toString());
            poolX.releaseConnection(conn, psmt, rs);
        } finally {
            poolX.releaseConnection(conn, psmt, rs);
        }
        return vResult;
    }

}
