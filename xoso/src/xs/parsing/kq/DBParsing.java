/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing.kq;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import inet.bean.LotteryResult;
import inet.model.LotteryResultDAO;

/**
 *
 * @author thangdm
 */
public class DBParsing {
    public static void main(String[] args) throws IOException {
        String url = "http://noithatphoxinh.net/testxs.html";
        System.out.println("url="+url);
        Document doc = Jsoup.connect(url)
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(3000)
        .post();
        
        Elements tds = doc.select("table tr td");
        System.out.println("trs size="+tds.size());
        String text = "";
        LotteryResult lottery = null;
        List<LotteryResult> listLottery = new ArrayList<LotteryResult>();
        for(Element td : tds){
            text = td.text();
            if(text != null && !"".equals(text.trim()) && text.split(" ").length == 2){
                lottery = new LotteryResult();
                lottery.setOpenDate(parseDate(text));
                lottery.setSpecial(text.split(" ")[0]);
                System.out.println("lottery.setSpecial="+lottery.getSpecial());
                lottery.setCode("XSTD");
                lottery.setSymbol("TD");
                lottery.setPrice(new BigDecimal(5000));
                lottery.setStatus(1);
                lottery.setUserName("quyetnv");
                listLottery.add(lottery);
            }
        }
        for(LotteryResult lr : listLottery){
            lottery = lr;
            if(lottery != null){
                LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
                //kiem tra da co trong DB chua
                //khong co moi insert
                LotteryResult tmp = null;
                tmp = lotteryResultDAO.findLRByCodeInDay("XSTD", lottery.getOpenDate());
                if(tmp == null){
                    System.out.println("create="+lotteryResultDAO.createWithOpenDate(lottery));
                }else{
                    boolean checkUpdate = false;
                    //giai dac biet
                    if(tmp.getSpecial() == null) tmp.setSpecial("");
                    if(lottery.getSpecial() != null && !"".equals(lottery.getSpecial()) && 
                            lottery.getSpecial().length() > tmp.getSpecial().length() ){
                        tmp.setSpecial(lottery.getSpecial());
                        checkUpdate = true;
                    }
                    //giai nhat
                    if(tmp.getFirst() == null) tmp.setFirst("");
                    if(lottery.getFirst() != null && !"".equals(lottery.getFirst()) && 
                            lottery.getFirst().length() > tmp.getFirst().length() ){
                        tmp.setFirst(lottery.getFirst());
                        checkUpdate = true;
                    }
                    //giai nhi
                    if(tmp.getSecond()== null) tmp.setSecond("");
                    if(lottery.getSecond() != null && !"".equals(lottery.getSecond()) && 
                            lottery.getSecond().length() > tmp.getSecond().length() ){
                        tmp.setSecond(lottery.getSecond());
                        checkUpdate = true;
                    }
                    //giai 3
                    if(tmp.getThird()== null) tmp.setThird("");
                    if(lottery.getThird() != null && !"".equals(lottery.getThird()) && 
                            lottery.getThird().length() > tmp.getThird().length() ){
                        tmp.setThird(lottery.getThird());
                        checkUpdate = true;
                    }
                    //giai 4
                    if(tmp.getFourth() == null) tmp.setFourth("");
                    if(lottery.getFourth() != null && !"".equals(lottery.getFourth()) && 
                            lottery.getFourth().length() > tmp.getFourth().length() ){
                        tmp.setFourth(lottery.getFourth());
                        checkUpdate = true;
                    }
                    //giai 5
                    if(tmp.getFifth()== null) tmp.setFifth("");
                    if(lottery.getFifth() != null && !"".equals(lottery.getFifth()) && 
                            lottery.getFifth().length() > tmp.getFifth().length() ){
                        tmp.setFifth(lottery.getFifth());
                        checkUpdate = true;
                    }
                    //giai 6
                    if(tmp.getSixth()== null) tmp.setSixth("");
                    if(lottery.getSixth() != null && !"".equals(lottery.getSixth()) && 
                            lottery.getSixth().length() > tmp.getSixth().length() ){
                        tmp.setSixth(lottery.getSixth());
                        checkUpdate = true;
                    }
                    //giai 7
                    if(tmp.getSeventh() == null) tmp.setSeventh("");
                    if(lottery.getSeventh() != null && !"".equals(lottery.getSeventh()) && 
                            lottery.getSeventh().length() > tmp.getSeventh().length() ){
                        tmp.setSeventh(lottery.getSeventh());
                        checkUpdate = true;
                    }
                    //giai 8
                    if(tmp.getEighth()== null) tmp.setEighth("");
                    if(lottery.getEighth() != null && !"".equals(lottery.getEighth()) && 
                            lottery.getEighth().length() > tmp.getEighth().length() ){
                        tmp.setEighth(lottery.getEighth());
                        checkUpdate = true;
                    }
                    if(checkUpdate) System.out.println("update="+lotteryResultDAO.updateLiveXS(tmp));
                    else System.out.println("Khong can update, du lieu van nhu cu");
                }
            }
        }
    }
    
    public static String parseDate(String title){
        String[] split = title.split(" ");
        if(split != null && split.length > 0) return split[split.length-1];
        return "";
    }
}
