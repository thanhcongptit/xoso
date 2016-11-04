/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.listener;

import inet.util.DateUtil;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import xs.parsing.ParsingXS;
import xs.parsing.ParsingXSMN;
import xs.parsing.ParsingXSMT;

/**
 *
 * @author 24h
 */
public class CrawlerData implements ServletContextListener, Runnable{

    //stop 
    private boolean running = false;
    //private boolean running = true;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Thread CrawlerData Starting...");
        //new Thread(this).start();
        System.out.println("Thread CrawlerData Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop running Thread CrawlerData");
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Date date = new Date();
                String ddMMyyyy = DateUtil.date2String(date, "dd-MM-yyyy");
                System.out.println("Crawler Data ==> " + ddMMyyyy);
                //ParsingXSMT.antrom("http://xoso.wap.vn/ket-qua-xo-so-mien-trung-xsmt-ngay-"+ddMMyyyy+".html");
                ParsingXSMT.antrom("http://xoso.wap.vn/ket-qua-xo-so-mien-trung-xsmt.html");                
                //ParsingXSMN.antrom("http://xoso.wap.vn/ket-qua-xo-so-mien-nam-xsmn-ngay-"+ddMMyyyy+".html");
                ParsingXSMN.antrom("http://xoso.wap.vn/ket-qua-xo-so-mien-nam-xsmn.html");                
                //ParsingXS.antrom("http://xoso.wap.vn/xsmb-ket-qua-xo-so-mien-bac-xstd-ngay-"+ddMMyyyy+".html");
                ParsingXS.antrom("http://xoso.wap.vn/xsmb-ket-qua-xo-so-mien-bac-xstd.html");
                try {
                    Thread.sleep(1000 * 60 * 10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CrawlerData.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(CrawlerData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
