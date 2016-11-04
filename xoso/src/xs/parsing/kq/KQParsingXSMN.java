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
import inet.util.XosoUtil;

/**
 *
 * @author 24h
 */
public class KQParsingXSMN {
    public static void main(String[] args) throws IOException {
        //String url = "http://ketqua.net/xo-so-mien-bac.php";
        String url = "http://ketqua.net/xo-so-mien-nam.php";
        antrom(url);
    }
    
    public static void antrom(String url) throws IOException{        
        System.out.println("url="+url);
        Document doc = Jsoup.connect(url)
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(3000)
        .post();
        
        Elements elements = doc.select("div[id^=ketqua_]");
        Elements listTr = null;
        LotteryResult lottery = null;
        int i = 0;
        List<LotteryResult> listLottery = new ArrayList<LotteryResult>();
        for(Element div : elements){
            listTr = div.select("table").first().select("tr");
            lottery = new LotteryResult();
            i = 0;
            for(Element tr : listTr){
                i++;
                if(i == 1){
                    //get date
                    lottery.setOpenDate(parseDate(tr.text()));
                    System.out.println("open date="+lottery.getOpenDate());
                }else if(i == 2){
                    //giai DB
                    lottery.setSpecial(getListKQ(tr.text()));
                    System.out.println("lottery.setSpecial="+lottery.getSpecial());
                }else if(i == 3){
                    //giai nhat
                    lottery.setFirst(getListKQ(tr.text()));
                }else if(i == 4){
                    //giai nhi
                    lottery.setSecond(getListKQ(tr.text()));
                }else if(i == 5){
                    //giai 3
                    lottery.setThird(getListKQ(tr.text()));                    
                }else if(i == 6 || i == 7){
                    //giai 4
                    if(i == 6){
                        lottery.setFourth(getListKQ(tr.text()));
                    }else{
                        lottery.setFourth(lottery.getFourth()+ "-" + getListKQ(tr.text()));
                    }
                }else if(i == 8){
                    //giai 5
                    lottery.setFifth(getListKQ(tr.text()));
                }else if(i == 9){
                    //giai 6
                    lottery.setSixth(getListKQ(tr.text()));
                }else if(i == 10){
                    //giai 7
                    lottery.setSeventh(getListKQ(tr.text()));
                }else if(i == 11){
                    //giai 8
                    lottery.setEighth(getListKQ(tr.text()));
                }
            }
            String codeKQ = div.attr("id").replace("ketqua_", "");
            String codeMy = XosoUtil.hmXosoCode.get(codeKQ);
            lottery.setCode("XS"+codeMy);
            lottery.setSymbol(codeMy);
            lottery.setPrice(new BigDecimal(5000));
            lottery.setStatus(1);
            lottery.setUserName("quyetnv");
            listLottery.add(lottery);
        }
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        if(!listLottery.isEmpty()) for(LotteryResult lotteryLive : listLottery){                
                //kiem tra da co trong DB chua
                //khong co moi insert
                LotteryResult tmp = null;
                tmp = lotteryResultDAO.findLRByCodeInDay(lotteryLive.getCode(), lotteryLive.getOpenDate());
                if(tmp == null){
                    System.out.println("create="+lotteryResultDAO.createWithOpenDate(lotteryLive));
                }else{
                    boolean checkUpdate = false;
                    //giai dac biet
                    if(tmp.getSpecial() == null) tmp.setSpecial("");
                    if(lotteryLive.getSpecial() != null && !"".equals(lotteryLive.getSpecial()) && 
                            lotteryLive.getSpecial().length() > tmp.getSpecial().length() ){
                        tmp.setSpecial(lotteryLive.getSpecial());
                        checkUpdate = true;
                    }
                    //giai nhat
                    if(tmp.getFirst() == null) tmp.setFirst("");
                    if(lotteryLive.getFirst() != null && !"".equals(lotteryLive.getFirst()) && 
                            lotteryLive.getFirst().length() > tmp.getFirst().length() ){
                        tmp.setFirst(lotteryLive.getFirst());
                        checkUpdate = true;
                    }
                    //giai nhi
                    if(tmp.getSecond()== null) tmp.setSecond("");
                    if(lotteryLive.getSecond() != null && !"".equals(lotteryLive.getSecond()) && 
                            lotteryLive.getSecond().length() > tmp.getSecond().length() ){
                        tmp.setSecond(lotteryLive.getSecond());
                        checkUpdate = true;
                    }
                    //giai 3
                    if(tmp.getThird()== null) tmp.setThird("");
                    if(lotteryLive.getThird() != null && !"".equals(lotteryLive.getThird()) && 
                            lotteryLive.getThird().length() > tmp.getThird().length() ){
                        tmp.setThird(lotteryLive.getThird());
                        checkUpdate = true;
                    }
                    //giai 4
                    if(tmp.getFourth() == null) tmp.setFourth("");
                    if(lotteryLive.getFourth() != null && !"".equals(lotteryLive.getFourth()) && 
                            lotteryLive.getFourth().length() > tmp.getFourth().length() ){
                        tmp.setFourth(lotteryLive.getFourth());
                        checkUpdate = true;
                    }
                    //giai 5
                    if(tmp.getFifth()== null) tmp.setFifth("");
                    if(lotteryLive.getFifth() != null && !"".equals(lotteryLive.getFifth()) && 
                            lotteryLive.getFifth().length() > tmp.getFifth().length() ){
                        tmp.setFifth(lotteryLive.getFifth());
                        checkUpdate = true;
                    }
                    //giai 6
                    if(tmp.getSixth()== null) tmp.setSixth("");
                    if(lotteryLive.getSixth() != null && !"".equals(lotteryLive.getSixth()) && 
                            lotteryLive.getSixth().length() > tmp.getSixth().length() ){
                        tmp.setSixth(lotteryLive.getSixth());
                        checkUpdate = true;
                    }
                    //giai 7
                    if(tmp.getSeventh() == null) tmp.setSeventh("");
                    if(lotteryLive.getSeventh() != null && !"".equals(lotteryLive.getSeventh()) && 
                            lotteryLive.getSeventh().length() > tmp.getSeventh().length() ){
                        tmp.setSeventh(lotteryLive.getSeventh());
                        checkUpdate = true;
                    }
                    //giai 8
                    if(tmp.getEighth()== null) tmp.setEighth("");
                    if(lotteryLive.getEighth() != null && !"".equals(lotteryLive.getEighth()) && 
                            lotteryLive.getEighth().length() > tmp.getEighth().length() ){
                        tmp.setEighth(lotteryLive.getEighth());
                        checkUpdate = true;
                    }
                    if(checkUpdate) System.out.println("update="+lotteryResultDAO.updateLiveXS(tmp));
                    else System.out.println("Khong can update, du lieu van nhu cu");
                }
        }
    }
    
    public static String getListKQ(String source){
        String[] split = source.split(" ");
        String rs = "";
        int i = 0;
        if(split != null && split.length > 0) for(String s : split){
            if(isNumber(s)){
                if(i == 0){
                    rs += s.trim();
                }else{
                    rs += "-" + s.trim();
                }
                i++;
            }
        }
        return rs;
    }
    
    public static boolean isNumber(String source){
        try{
            int i = Integer.parseInt(source);
            return true;
        }catch(Exception ex){}
        return false;
    }
    
    public static String parseDate(String title){
        String[] split = title.split(" ");
        if(split != null && split.length > 0) return split[split.length-1];
        return "";
    }
}
