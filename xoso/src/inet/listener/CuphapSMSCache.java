/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.listener;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bean.SmsDTO;

import inet.model.SmsDao;

/**
 *
 * @author conglt
 */
public class CuphapSMSCache implements ServletContextListener, Runnable {

    public static HashMap<String, List<SmsDTO>> hmBanner = new HashMap<>();
    private boolean running = true;

    public static void clearCache() {
        if (!hmBanner.isEmpty()) {
            hmBanner.clear();
        }
    }

    public static List<SmsDTO> getCuPhap() {
        String cuphap = "cuphap";
        
        if (hmBanner.get(cuphap) != null) {
            return hmBanner.get(cuphap);
        } else {
            SmsDao smsDao = new SmsDao();
            List<SmsDTO> smsDTOs = smsDao.findAllCuphap();
            
            if (smsDTOs != null) {
                hmBanner.put(cuphap, smsDTOs);
                return smsDTOs;
            }
        }
        return null;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Thread BannerCache Starting...");
        new Thread(this).start();
        System.out.println("Thread BannerCache Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop running Thread BannerCache");
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            clearCache();
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException ex) {
                Logger.getLogger(CuphapSMSCache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
