/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing;

import inet.bean.Lottery;
import inet.bean.LotteryResult;
import inet.listener.XSCache;
import inet.model.LotteryResultDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author 24h
 */
public class ParsingXS {
    public static void main(String[] args) throws IOException {
        String url = "http://xoso.wap.vn/xsmb-ket-qua-xo-so-mien-bac-xstd-ngay-03-12-2015.html";
        System.out.println("url="+url);
        Document doc = Jsoup.connect(url)
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(3000)
        .post();
        Elements elements = doc.select("#kqxsmb .box_so_left");
        String titleDate = null;
        String dateS = null; //format dd/MM/yyyy
        Elements listTr = null;
        int i = 0;
        LotteryResult lottery = null;
        List<LotteryResult> listLottery = new ArrayList<LotteryResult>();
        for(Element element : elements){
            i = 0;
            lottery = new LotteryResult();
            titleDate = element.parent().previousElementSibling().text();
            dateS = parseDate(titleDate);
            lottery.setOpenDate(dateS);
            listTr = element.select("tr");
            for(Element tr : listTr){
                i++;
                if(i == 1){
                    //giai DB
                    lottery.setSpecial(getListKQ(tr.select("td.chukq")));
                }else if(i==2){
                    //giai 1
                    lottery.setFirst(getListKQ(tr.select("td.chukq")));
                }else if(i==3){
                    //giai 2
                    lottery.setSecond(getListKQ(tr.select("td.chukq")));
                }else if(i==4 || i==5){
                    //giai 3
                    if(i==4){
                        lottery.setThird(getListKQ(tr.select("td.chukq")));
                    }else{
                        lottery.setThird(lottery.getThird() + "-" + getListKQ(tr.select("td.chukq")));
                    }
                }else if(i==6){
                    //giai 4
                    lottery.setFourth(getListKQ(tr.select("td.chukq")));
                }else if(i==7 || i==8){
                    //giai 5
                    if(i==7){
                        lottery.setFifth(getListKQ(tr.select("td.chukq")));
                    }else{
                        lottery.setFifth(lottery.getFifth()+ "-" + getListKQ(tr.select("td.chukq")));
                    }
                }else if(i==9){
                    //giai 6
                    lottery.setSixth(getListKQ(tr.select("td.chukq")));
                }else if(i==10){
                    //giai 7
                    lottery.setSeventh(getListKQ(tr.select("td.chukq")));
                }
            }
            lottery.setCode("XSTD");
            lottery.setSymbol("TD");
            lottery.setPrice(new BigDecimal(5000));
            lottery.setStatus(1);
            lottery.setUserName("quyetnv");
            listLottery.add(lottery);
        }
        System.out.println("size="+listLottery.size());
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        //kiem tra da co trong DB chua
        //khong co moi insert
        LotteryResult tmp = null;
        for(LotteryResult lotteryResult : listLottery){
            //tmp = lotteryResultDAO.findResultByCodeInDay("XSTD", lotteryResult.getOpenDate());
            tmp = XSCache.getLotteryByCodeAndOpenDate("XSTD", lotteryResult.getOpenDate());
            if(tmp == null){
                boolean rs = lotteryResultDAO.createWithOpenDate(lotteryResult);
                if(rs) XSCache.clearCache();
                System.out.println("create="+rs);
            }
        }
        
        
    }
    
    public static void antrom(String rootUrl) throws IOException{
        String url = rootUrl;
        System.out.println("url="+url);
        Document doc = Jsoup.connect(url)
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(3000)
        .post();
        Elements elements = doc.select("#kqxsmb .box_so_left");
        String titleDate = null;
        String dateS = null; //format dd/MM/yyyy
        Elements listTr = null;
        int i = 0;
        LotteryResult lottery = null;
        List<LotteryResult> listLottery = new ArrayList<LotteryResult>();
        for(Element element : elements){
            i = 0;
            lottery = new LotteryResult();
            titleDate = element.parent().previousElementSibling().text();
            dateS = parseDate(titleDate);
            lottery.setOpenDate(dateS);
            listTr = element.select("tr");
            for(Element tr : listTr){
                i++;
                if(i == 1){
                    //giai DB
                    lottery.setSpecial(getListKQ(tr.select("td.chukq")));
                    System.out.println("dacbiet="+getListKQ(tr.select("td.chukq")));
                }else if(i==2){
                    //giai 1
                    lottery.setFirst(getListKQ(tr.select("td.chukq")));
                }else if(i==3){
                    //giai 2
                    lottery.setSecond(getListKQ(tr.select("td.chukq")));
                }else if(i==4 || i==5){
                    //giai 3
                    if(i==4){
                        lottery.setThird(getListKQ(tr.select("td.chukq")));
                    }else{
                        lottery.setThird(lottery.getThird() + "-" + getListKQ(tr.select("td.chukq")));
                    }
                }else if(i==6){
                    //giai 4
                    lottery.setFourth(getListKQ(tr.select("td.chukq")));
                }else if(i==7 || i==8){
                    //giai 5
                    if(i==7){
                        lottery.setFifth(getListKQ(tr.select("td.chukq")));
                    }else{
                        lottery.setFifth(lottery.getFifth()+ "-" + getListKQ(tr.select("td.chukq")));
                    }
                }else if(i==9){
                    //giai 6
                    lottery.setSixth(getListKQ(tr.select("td.chukq")));
                }else if(i==10){
                    //giai 7
                    lottery.setSeventh(getListKQ(tr.select("td.chukq")));
                }
            }
            lottery.setCode("XSTD");
            lottery.setSymbol("TD");
            lottery.setPrice(new BigDecimal(5000));
            lottery.setStatus(1);
            lottery.setUserName("xsw");
            listLottery.add(lottery);
        }
        System.out.println("size="+listLottery.size());
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        //kiem tra da co trong DB chua
        //khong co moi insert
        LotteryResult tmp = null;
        for(LotteryResult lotteryResult : listLottery){
            //tmp = lotteryResultDAO.findResultByCodeInDay("XSTD", lotteryResult.getOpenDate());
            tmp = XSCache.getLotteryByCodeAndOpenDate("XSTD", lotteryResult.getOpenDate());
            System.out.println("Kq ton tai: "+ (tmp!=null));
            if(tmp == null){
                boolean rs = lotteryResultDAO.createWithOpenDate(lotteryResult);
                if(rs) XSCache.clearCache();
                System.out.println("create="+rs);
            }
        }
    }
    
    public static String parseDate(String title){
        String[] split = title.split(" ");
        if(split != null && split.length > 0) return split[split.length-1];
        return "";
    }
    public static String getListKQ(Elements elements){
        String rs = "";
        int i = 0;
        for(Element element : elements){
            if(i == 0){
                rs += element.text().trim();
            }else{
                rs += "-" + element.text().trim();
            }
            i++;
        }
        return rs;
    }
}
