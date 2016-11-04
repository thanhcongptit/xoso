/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.listener;

import inet.bean.CapsoOnline;
import inet.bean.Lottery;
import inet.model.CapsoOnlineDAO;
import inet.model.LotteryResultDAO;
import inet.util.DateUtil;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class TraoThuongThread implements ServletContextListener, Runnable{

    private boolean running = true;
    private LotteryResultDAO lotteryResultDAO = null;
    private Lottery lottery = null;
    private Date date = null;
    private int intDate = 0;
    private HashMap<String, Integer> hmKetqua = null;
    private int countTraoThuong = 0;
    private List<CapsoOnline> listBangChotSo = null;
    private CapsoOnlineDAO capsoOnlineDAO = null;
    private int sonhay = 0;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Thread TraoThuongThread Starting...");
        new Thread(this).start();
        System.out.println("Thread TraoThuongThread Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop running TraoThuongThread CrawlerData");
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            date = new Date();
            intDate = Integer.parseInt(DateUtil.date2String(date, "HHmmss"));
            if(190000 < intDate && intDate < 191000){
                lotteryResultDAO = new LotteryResultDAO();
                lottery = lotteryResultDAO.find4TraThuong();
                if(lottery != null){
                    hmKetqua = parseKetqua(lottery);
                    if(!hmKetqua.isEmpty()){
                        if(countTraoThuong < 3){
                            capsoOnlineDAO = new CapsoOnlineDAO();
                            listBangChotSo = capsoOnlineDAO.findBangTraoThuong();
                            if(listBangChotSo != null){
                                for(CapsoOnline capsoOnline : listBangChotSo){
                                    if(hmKetqua.get(capsoOnline.getCapso()) != null){
                                        sonhay = hmKetqua.get(capsoOnline.getCapso());
                                    }else{
                                        sonhay = 0;
                                    }
                                    capsoOnlineDAO.traothuong(capsoOnline, sonhay);
                                }
                                countTraoThuong++;
                            }
                        }
                    }
                }
            }else{
                countTraoThuong = 0;
            }
            try {
                Thread.sleep(1000 * 60 * 5);
            } catch (InterruptedException ex) {
                Logger.getLogger(TraoThuongThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static HashMap<String, Integer> parseKetqua(Lottery lottery){
        HashMap<String, Integer> hmKetqua = new HashMap<>();
        String tmp = "";
        String giai = "";
        String[] split = null;
        for(int i=0;i<8;i++){
            switch (i){
                case 0: giai = lottery.getSpecial();break;
                case 1: giai = lottery.getFirst();break;
                case 2: giai = lottery.getSecond();break;
                case 3: giai = lottery.getThird();break;
                case 4: giai = lottery.getFourth();break;
                case 5: giai = lottery.getFifth();break;
                case 6: giai = lottery.getSixth();break;
                case 7: giai = lottery.getSeventh();break;
            }
            split = giai.split("-");
            for(String s : split){
                s = s.trim();
                tmp = s.substring(s.length()-2, s.length());
                if(hmKetqua.get(tmp) != null){
                    hmKetqua.put(tmp, hmKetqua.get(tmp) + 1);
                }else{
                    hmKetqua.put(tmp, 1);
                }
            }
        }
        
        return hmKetqua;
    }
    
    public static void main(String[] args) {    
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        Lottery lottery = lotteryResultDAO.find4TraThuong();
        HashMap<String, Integer> hmKetqua = parseKetqua(lottery);
    }
}
