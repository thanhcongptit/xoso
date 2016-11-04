/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.listener;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import inet.model.SystemConfigDAO;

/**
 *
 * @author 24h
 */
public class SystemConfigCache implements ServletContextListener{
    
    public static final String FACEBOOK_CONFIG = "facebook";
    public static final String FOOTER_CONFIG = "footer";
     
    public static HashMap<String, String> hmSystemConfig = new HashMap<String, String>();
    private boolean running = true;
    
    public static void clearCache(){
        if(!hmSystemConfig.isEmpty()) hmSystemConfig.clear();
    }
    
    public static String findConfig(String key){
        if(hmSystemConfig.get(key) != null){
            return hmSystemConfig.get(key);
        }else{
            SystemConfigDAO configDAO = new SystemConfigDAO();
            String value = configDAO.findByKey(key);
            if(value != null){
                hmSystemConfig.put(key, value);
                return value;
            }
        }
        return null;
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Thread SystemConfigCache Loading...");
        SystemConfigDAO configDAO = new SystemConfigDAO();
        hmSystemConfig = configDAO.loadConfig();
        if(hmSystemConfig == null) System.out.println("SystemConfigCache: Khong load duoc cau hinh nao");
        else System.out.println("SystemConfigCache: load duoc "+hmSystemConfig.size()+" cau hinh");
        System.out.println("Thread SystemConfigCache Loaded");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("SystemConfigCache destroyed");
    }
    
}
