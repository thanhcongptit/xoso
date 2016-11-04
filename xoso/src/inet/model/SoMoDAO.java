/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import inet.bean.SoMo;
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
 * @author hanhlm
 */
public class SoMoDAO {

    private Logger logger = null;
    private DBPoolX poolX = null;

    public SoMoDAO() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SoMo> findAll() {
        List<SoMo> result = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        SoMo soMo = null;
        try {
            conn = poolX.getConnection();
            sql = "select name,name_vn,numbers from lottery_dream order by id asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<SoMo>();
                }
                soMo = new SoMo();

                soMo.setName(rs.getString(1));
                soMo.setName_vn(rs.getString(2));
                soMo.setNumbers(rs.getString(3));

                result.add(soMo);
            }
        } catch (SQLException e) {
            logger.error("loi sql " + e.toString());
        } catch (Exception e) {
            logger.error("loi ngoai le " + e.toString());
        } finally {
            poolX.releaseConnection(conn, ps, rs);
        }

        return result;
    }

}
