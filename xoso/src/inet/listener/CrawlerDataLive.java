/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.listener;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.Constant;
import inet.bean.LotteryCompany;
import inet.bean.LotteryResult;
import inet.model.LotteryResultDAO;
import inet.util.DateUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import xs.parsing.kqvn.KQVNParsingXS;
import xs.parsing.kqvn.KQVNParsingXSMN;

/**
 *
 * @author 24h
 */
public class CrawlerDataLive implements ServletContextListener, Runnable{

    private boolean running = true;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Thread CrawlerDataLive Starting...");
        new Thread(this).start();
        System.out.println("Thread CrawlerDataLive Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop running Thread CrawlerDataLive");
        running = false;
    }

    @Override
    public void run() {
        boolean live = false;
        while (running) {
            try {
                Date date = new Date();
                String sHHmmss = DateUtil.date2String(date, "HHmmss");
                int HHmmss = Integer.parseInt(sHHmmss);
                live = false;
                if(HHmmss >= 161000 && HHmmss < 164300){
                    //xo so mien nam
                    //KQParsingXSMN.antrom("http://ketqua.net/xo-so-mien-nam.php");
                    if(!live){
                        List<LotteryCompany> listCompany = XSCache.getListCompanyToday("MN");
                        if(listCompany != null){
                            LotteryResult lotteryLive = null;
                            LotteryResult tmp = null;
                            LotteryResult lr = null;
                            LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
                            for(LotteryCompany lotteryCompany : listCompany){
                                tmp = null;                                
                                lotteryLive = new LotteryResult();
                                lotteryLive.setCode(lotteryCompany.getCode());
                                lotteryLive.setOpenDate(DateUtil.date2String(new Date(), "dd/MM/yyyy"));
                                lotteryLive.setSymbol(lotteryCompany.getCode().replace("XS", ""));
                                lotteryLive.setUserName("thread");
                                
                                tmp = XSCache.getLotteryByCodeAndOpenDate(lotteryLive.getCode(), lotteryLive.getOpenDate());
                                if(tmp == null && !XSCache.checkInsert(lotteryLive.getCode(), lotteryLive.getOpenDate())){
                                     lr = lotteryResultDAO.createWithOpenDate77(lotteryLive);
                                     if(lr != null){
                                         XSCache.addLottery(lr);
                                         XSCache.saveInsert(lotteryLive.getCode(), lotteryLive.getOpenDate());
                                     }
                                }
                            }
                        }
                    }
                    KQVNParsingXSMN.antrom("http://ketqua.vn/truc-tiep-xo-so-mien-nam");
                    live = true;
                } else if (HHmmss > 165300 && HHmmss < 169800){
                    LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
                    lotteryResultDAO.deleteXSMB_Error();
                    
                    String openDate = DateUtil.date2String(new Date(), Constant.FORMAT_DATETIME);
                    
                    if(!CommonUtil.isEmptyString(openDate)) {
                        lotteryResultDAO.deleteDulicateRecordsLoterry(openDate);
                    }
                    live = true;
                    
                } else if(HHmmss >= 171000 && HHmmss < 173800){
                    //xo so mien trung
                    //KQParsingXSMN.antrom("http://ketqua.net/xo-so-mien-trung.php");
                    if(!live){
                        List<LotteryCompany> listCompany = XSCache.getListCompanyToday("MT");
                        if(listCompany != null){
                            LotteryResult lotteryLive = null;
                            LotteryResult tmp = null;
                            LotteryResult lr = null;
                            LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
                            for(LotteryCompany lotteryCompany : listCompany){
                                tmp = null;                                
                                lotteryLive = new LotteryResult();
                                lotteryLive.setCode(lotteryCompany.getCode());
                                lotteryLive.setOpenDate(DateUtil.date2String(new Date(), "dd/MM/yyyy"));
                                lotteryLive.setSymbol(lotteryCompany.getCode().replace("XS", ""));
                                lotteryLive.setUserName("thread");
                                
                                tmp = XSCache.getLotteryByCodeAndOpenDate(lotteryLive.getCode(), lotteryLive.getOpenDate());
                                if(tmp == null && !XSCache.checkInsert(lotteryLive.getCode(), lotteryLive.getOpenDate())){
                                     lr = lotteryResultDAO.createWithOpenDate77(lotteryLive);
                                     if(lr != null){
                                         XSCache.addLottery(lr);
                                         XSCache.saveInsert(lotteryLive.getCode(), lotteryLive.getOpenDate());
                                     }
                                }
                            }
                        }
                    }
                    KQVNParsingXSMN.antrom("http://ketqua.vn/truc-tiep-xo-so-mien-trung");
                    live = true;
                }else if (HHmmss > 174100 && HHmmss < 178000){
                    LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
                    lotteryResultDAO.deleteXSMB_Error();
                    
                    String openDate = DateUtil.date2String(new Date(), Constant.FORMAT_DATETIME);
                    
                    if(!CommonUtil.isEmptyString(openDate)) {
                        lotteryResultDAO.deleteDulicateRecordsLoterry(openDate);
                    }
                    
                } else if(HHmmss >= 181000 && HHmmss < 183800){
                    //xo so mien bac
                    //KQParsingXS.antrom("http://ketqua.net/xo-so-mien-bac.php");
                    //KQParsingXS.antrom("http://noithatphoxinh.net/testxs.html");
                    KQVNParsingXS.antrom("http://ketqua.vn/truc-tiep-xo-so-mien-bac");
                    live = true;
                } else if (HHmmss >= 188800 && HHmmss < 192000) {
                    LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
                    try{
                        lotteryResultDAO.deleteXSMB_Error();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    
                    String openDate = DateUtil.date2String(new Date(), Constant.FORMAT_DATETIME);
                    
                    if(!CommonUtil.isEmptyString(openDate)) {
                        lotteryResultDAO.deleteDulicateRecords(openDate);
                    }
                    live = true;
                } 
                try {
                    if(live){
                        Thread.sleep(1000 * 5);
                    }else{
                        Thread.sleep(1000 * 10);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(CrawlerDataLive.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(CrawlerDataLive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
