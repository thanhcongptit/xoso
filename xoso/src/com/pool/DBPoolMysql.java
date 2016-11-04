package com.pool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

import com.common.DBConfig;
import static com.pool.DBPoolMysql.MAX_CONNECTIONS;
import static com.pool.DBPoolMysql.build;
import static com.pool.DBPoolMysql.makeDBConnection;
import static com.pool.DBPoolMysql.putConnection;
import static com.pool.DBPoolMysql.release;
import static com.pool.DBPoolMysql.releaseConnection;
import static com.pool.DBPoolMysql.size;
import com.utils.Logger;


// This file is packaged in every .jar and .war modul.
// Therefore, the class is loaded for each modul.
// In other word, How many moduls, how many DBPool are created.
// Make pool connect to oracle db, user smsgw
public class DBPoolMysql {
    private static Logger logger = new Logger("DBPool");

    private static LinkedList pool = new LinkedList();
    public final static int MAX_CONNECTIONS = 10;
    public final static int INI_CONNECTIONS = 2;

    static { //Make connections
        build(DBConfig.db_connection);
    }

    public static void build(int number) {
        logger.log("Establishing " + number + " connections...");
        Connection conn = null;
        release();
        for (int i = 0; i < number; i++) {
            try {
                conn = makeDBConnection();
            } catch (SQLException ex) {
                logger.log("Error: " + ex.getMessage());
                logger.log("Hix,Khong noi dc voi database roi !");
            }
            if (conn != null) {
                pool.addLast(conn);
            }
        }
        logger.log("Number of connection: " + size());
    }

    public static Connection getConnection() {
        Connection conn = null;
        try{
	        synchronized (pool) {
	            conn = (java.sql.Connection) pool.removeFirst();
	        }
	        if (conn == null) {
	        	conn = makeDBConnection();
	        }
	        try {
	            conn.setAutoCommit(true);
	        } catch (Exception ex) {}
	        
        } catch(Exception e){            
            logger.error("Method getConnection(): Error executing >>>" + e.toString());
            try {
            	logger.log("Make connection again.");
				conn = makeDBConnection();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
			}
			logger.log(""+conn);
        }
        return conn;
    }

    public static void putConnection(java.sql.Connection conn) {
        try { // Ignore closed connection
            if (conn == null || conn.isClosed()) {
                logger.log("putConnection: conn is null or closed: " + conn);
                return;
            }
            if (pool.size() >= MAX_CONNECTIONS) {
                conn.close();
                return;
            }
        } catch (SQLException ex) {}

        synchronized (pool) {
            pool.addLast(conn);
            pool.notify();
        }
    }

    // Remove and close all connections in pool
    public static void release() {
        logger.log("Closing connections in pool...");
        synchronized (pool) {
            for (Iterator it = pool.iterator(); it.hasNext(); ) {
                Connection conn = (Connection) it.next();
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    logger.error(
                        "release: Cannot close connection! (maybe closed?)");
                }
            }
            pool.clear();
        }
        logger.log("Release connection OK");
    }

    public static int size() {
        synchronized (pool) {
            return pool.size();
        }
    }

    public static boolean isEmpty() {
        synchronized (pool) {
            return pool.isEmpty();
        }
    }

    public void finalize() {
        release();
    }

    public static boolean isClosed() {
        boolean closed = false;
        synchronized (pool) {
            for (Iterator it = pool.iterator(); it.hasNext(); ) {
                try {
                    Connection conn = (Connection) it.next();
                    if (conn == null || conn.isClosed()) {
                        closed = true;
                    }
                }
                catch (SQLException e) {
                    closed = true;
                }
            }
        }
        return closed;
    }
    //--------------------------------------------------------------------------
    public static Connection makeDBConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(DBConfig.getProperties("_vnm_driver", ""));
            System.out.println("DBConfig.db_url_sql="+DBConfig.getProperties("_vnm_url", ""));
            System.out.println("DBConfig.db_user_sql="+DBConfig.getProperties("_vnm_user", ""));
            conn = DriverManager.getConnection(
            	DBConfig.getProperties("_vnm_url", ""),
            	DBConfig.getProperties("_vnm_user", ""),
                DBConfig.getProperties("_vnm_passwd", ""));
            
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }catch (Exception e) {
			// TODO: handle exception
		}
        return conn;
    }

    public static void main(String args[]) {
        
		System.out.println(DBPoolMysql.getConnection());
       	
	}
    
    public static void load() {}
    
    public static void releaseConnection(Connection conn, PreparedStatement preStmt) {
        //try {if (conn != null) {conn.close(); } } catch (SQLException e) { }
        putConnection(conn);
        try {
            if (preStmt != null) {
                preStmt.close();
            }
        } catch (SQLException e) {}
    }

    public static void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        releaseConnection(conn, preStmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {}
    }

    public static void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        releaseConnection(conn, preStmt, rs);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {}
    }
}
