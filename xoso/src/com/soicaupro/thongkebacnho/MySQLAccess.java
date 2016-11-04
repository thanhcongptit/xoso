//
//package com.soicaupro.thongkebacnho;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//
//
///**
// *
// * @author conglt
// */
//public class MySQLAccess {
//
//    public static Connection getConnection() throws Exception {
//        
//        Connection connection = null;
//        
//        try {
//            // This will load the MySQL driver, each DB has its own driver
//            Class.forName("com.mysql.jdbc.Driver");
//            // Setup the connection with the DB
////            connection = DriverManager
////                    .getConnection("jdbc:mysql://localhost:3306/xoso?"
////                            + "user=root&password=");
//            
//            connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/xoso?"
//                            + "user=root&password=123qweasd@qwe");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } 
//       
//        return connection;
//    }
//
//    // You need to close the resultSet
//    public static void close(ResultSet resultSet, Statement statement, PreparedStatement preparedStatement, Connection connect) {
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//
//            if (statement != null) {
//                statement.close();
//            }
//            
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//
//            if (connect != null) {
//                connect.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    
//    public static void main(String args[]) throws Exception {
//        System.out.println("ttaas");
//        Connection con = getConnection();
//        if(con == null)
//        System.out.println("aaaa");
//        else 
//            System.out.println("test ok");
//    }
//}
