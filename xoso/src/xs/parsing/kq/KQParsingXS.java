/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing.kq;

import java.io.IOException;
import java.math.BigDecimal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import inet.bean.LotteryResult;
import inet.model.LotteryResultDAO;

/**
 *
 * @author 24h
 */
public class KQParsingXS {
    public static void main(String[] args) throws IOException {
        //String url = "http://ketqua.net/xo-so-mien-bac.php";
        String url = "http://noithatphoxinh.net/testxs.html";
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
        
        Elements elements = doc.select("div#ketqua_mb table");
        Elements listTr = null;
        LotteryResult lottery = null;
        int i = 0;
        for(Element table : elements){
            listTr = table.select("tr");
            lottery = new LotteryResult();
            for(Element tr : listTr){
                i++;
                //System.out.println(tr.text());
                //System.out.println("=========================");
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
                }else if(i == 5 || i == 6){
                    //giai 3
                    if(i == 5){
                        lottery.setThird(getListKQ(tr.text()));
                    }else{
                        lottery.setThird(lottery.getThird() + "-" + getListKQ(tr.text()));
                    }
                }else if(i == 7){
                    //giai 4
                    lottery.setFourth(getListKQ(tr.text()));
                }else if(i == 8 || i == 9){
                    //giai 5
                    if(i == 8){
                        lottery.setFifth(getListKQ(tr.text()));
                    }else{
                        lottery.setFifth(lottery.getFifth()+ "-" + getListKQ(tr.text()));
                    }
                }else if(i == 10){
                    //giai 6
                    lottery.setSixth(getListKQ(tr.text()));
                }else if(i == 11){
                    //giai 7
                    lottery.setSeventh(getListKQ(tr.text()));
                }
            }
            lottery.setCode("XSTD");
            lottery.setSymbol("TD");
            lottery.setPrice(new BigDecimal(5000));
            lottery.setStatus(1);
            lottery.setUserName("quyetnv");
            break;
        }
        
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
