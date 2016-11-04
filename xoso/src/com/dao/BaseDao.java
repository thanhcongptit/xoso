/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import com.utils.Logger;
import java.sql.Connection;

/**
 *
 * @author HanhDung
 */
public class BaseDao {
    protected Logger logger=null;
    protected Connection conn=null;
    public BaseDao() {
        logger=new Logger(this.getClass().getName());
        try{
            //conn=DBPoolMysql.getConnection();
            conn = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY).getConnection();
        }catch(Exception e){
            logger.error("loi ket noi "+e.toString());
        }
    }
    
}
