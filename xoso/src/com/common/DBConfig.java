package com.common;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.utils.GenericResourceLoader;


public class DBConfig {
    public static String db_driver_sql ="com.microsoft.jdbc.sqlserver.SQLServerDriver";
    public static String db_url_sql    ="jdbc:microsoft:sqlserver://192.168.1.200:1433;DatabaseName=INFOCONTENT;useUnicode=true;characterEncoding=UTF-8";
    public static String db_user_sql   ="tekciz_infocontent"; //tekciz
    public static String db_pass_sql   ="tekciz";//tekcizpass
	
    public static String db_driver_Service   = "oracle.jdbc.driver.OracleDriver";
    public static String db_url_Service      = "jdbc:oracle:thin:@10.0.0.99:1521:TEST";
    public static String db_user_Service     = "phuongnt";
    public static String db_pass_Service     = "phuongnt";
    public static int db_connection     	 = 2;
    
    public static String db_url_voice      = "jdbc:oracle:thin:@10.0.0.99:1521:TEST";
    public static String db_user_voice     = "phuongnt";
    public static String db_pass_voice     = "phuongnt";
    
    public static String db_driver_voice91 ="com.mysql.jdbc.Driver";
    public static String db_url_voice91    ="jdbc:mysql://localhost/tekciz?useUnicode=true&characterEncoding=utf8"; //jdbc:mysql://localhost/tekciz?useUnicode=true&characterEncoding=utf8
    public static String db_user_voice91   ="tekciz"; //tekciz
    public static String db_pass_voice91   ="tekciz";//tekcizpass
    
    public static String db_driver_voice198 ="com.mysql.jdbc.Driver";
    public static String db_url_voice198    ="jdbc:mysql://183.91.2.127/tekciz?useUnicode=true&characterEncoding=utf8"; //jdbc:mysql://192.168.1.14/tekciz?useUnicode=true&characterEncoding=utf8
    public static String db_user_voice198   ="tekadmin"; //cskh
    public static String db_pass_voice198   ="tekciz";//cskh
    
    public static String db_driver_voice99 ="com.mysql.jdbc.Driver";
    public static String db_url_voice99    ="jdbc:mysql://115.84.178.99/tekciz?useUnicode=true&characterEncoding=utf8"; //jdbc:mysql://localhost/tekciz?useUnicode=true&characterEncoding=utf8
    public static String db_user_voice99   ="admin"; //tekciz
    public static String db_pass_voice99   ="admin123456";//tekcizpass
    
    public static String db_url_dbsms      = "jdbc:oracle:thin:@10.0.0.99:1521:TEST";
    public static String db_user_dbsms     = "phuongnt";
    public static String db_pass_dbsms     = "phuongnt";
    
    public static String fileName = "conf/config.conf";
    public static boolean isXSChoEnabled = true;
    
    /**
     * Contains the parameters and default values for this gateway
     * such as system id, password, default npi, and ton of sender...
     */
    private static Properties properties = new Properties();
    
    public static String getProperties(String key, String defaultValue) throws IOException {
        return properties.getProperty(key, defaultValue);
    }
    /**
     * Loads configuration parameters from the file with the given name.
     * Sets private variable to the loaded values.
     */
    public static void loadProperties(String fileName) throws IOException {
        System.out.println("Reading configuration file " + fileName + "...");
        InputStream in = GenericResourceLoader.loadResource(fileName);
        properties.load(in);
        in.close();
        System.out.println("Setting parameters...");

        //========================================
      
        int n = getIntProperty("xsChoEnabled", 1);
        if (n == 1) isXSChoEnabled = true;
        else isXSChoEnabled = false;
        System.out.println("isXSChoEnabled = " + n);        		
    }

    // Gets a property and converts it into integer.
    static int getIntProperty(String propName, int defaultValue) {
        return Integer.parseInt(properties.getProperty(propName,
            Integer.toString(defaultValue)));
    }
    
    static {
 		try{
 			loadProperties(fileName);	
 		}catch(IOException e){
 			e.printStackTrace();
 		}
    }

    public static void main(String[] args) {
    	try{
    		System.out.println(DBConfig.getProperties("_vnm_url", ""));
    	}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
