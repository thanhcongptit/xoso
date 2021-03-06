/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing;

import inet.bean.LotteryResult;
import inet.listener.XSCache;
import inet.model.LotteryResultDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author 24h
 */
public class ParsingXSMT {
    public static void main(String[] args) throws IOException {
        String url = "http://xoso.wap.vn/ket-qua-xo-so-mien-trung-xsmt-ngay-01-01-2016.html";
        System.out.println("url="+url);
        Document doc = Jsoup.connect(url)
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(3000)
        .post();
        Elements elements = doc.select("#kqxsmb");        
        Elements listTinh = null;
        Elements listKQNgang = null;
        String openDate = null;
        List<LotteryResult> listLottery = new ArrayList<>();
        int i = 0;
        HashMap<Integer, LotteryResult> hmLottery = new HashMap<>();
        HashMap<Integer, String> hmTmp = new HashMap<>();
        int sizeNgang = 0;
        for(Element element : elements){
            if(!element.select(".result-header").isEmpty()){
                hmLottery = new HashMap<>();
                openDate = parseDate(element.select(".result-header").get(0).text());
                System.out.println("openDate="+openDate);
                //tieu de tinh
                sizeNgang = element.select("table .xsmb_ngang_1 td").size() - 1;   
                listTinh = element.select("table .xsmb_ngang_1 td"); 
                System.out.println("listTinh.size()="+listTinh.size());
                if(listTinh.size() > 0){
                    for(int j = 0; j < listTinh.size(); j++){
                        if(j==0) continue;
                        LotteryResult lr = new LotteryResult();
                        lr.setOpenDate(openDate);
                        lr.setCode(hmTenTinhMaTinh.get(listTinh.get(j).text()));
                        System.out.println("listTinh.get(j).text()="+listTinh.get(j).text());
                        lr.setSymbol(lr.getCode().replace("XS", ""));
                        
                        lr.setPrice(new BigDecimal(5000));
                        lr.setStatus(1);
                        lr.setUserName("quyetnv");
                        hmLottery.put(j-1, lr);
                    }
                }
                
                listKQNgang = element.select("table .xsmn_ngang");
                i = 0;
                for(Element kqNgang : listKQNgang){
                    //System.out.println("size="+kqNgang.select(".xsmn_3").size());
                    i++;
                    if(kqNgang.select("td").size() > 0){
                        for(int j = 0; j<kqNgang.select("td").size(); j++){
                            if(j==0) continue;
                            String tmp = new String();
                            tmp = kqNgang.select("td").get(j).text().replace(" ", "-");
                            hmTmp.put(j-1,tmp);
                        }
                        
                        if(i==1){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setEighth(hmTmp.get(j));
                            }
                        }else if(i==2){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSeventh(hmTmp.get(j));
                            }
                        }else if(i==3){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSixth(hmTmp.get(j));
                            }
                        }else if(i==4){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setFifth(hmTmp.get(j));
                            }
                        }else if(i==5){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setFourth(hmTmp.get(j));
                            }
                        }else if(i==6){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setThird(hmTmp.get(j));
                            }
                        }else if(i==7){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSecond(hmTmp.get(j));
                            }
                        }else if(i==8){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setFirst(hmTmp.get(j));
                            }
                        }else if(i==9){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSpecial(hmTmp.get(j));
                            }
                        }             
                    }
                }
                
                for(int j=0; j<hmLottery.size(); j++){
                    listLottery.add(hmLottery.get(j));
                }
            }
        }
        System.out.println("==============================");
        System.out.println("list-size="+listLottery.size());
        for(LotteryResult lotteryResult : listLottery){
            System.out.println(lotteryResult.getCode()+"/"+lotteryResult.getOpenDate()+"/lotter="+lotteryResult.getSpecial());
        }
        System.out.println("===============================");        
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        //kiem tra da co trong DB chua
        //khong co moi insert
        LotteryResult tmp = null;
        for(LotteryResult lotteryResult : listLottery){
            //tmp = lotteryResultDAO.findResultByCodeInDay(lotteryResult.getCode(), lotteryResult.getOpenDate());
            tmp = XSCache.getLotteryByCodeAndOpenDate(lotteryResult.getCode(), lotteryResult.getOpenDate());
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
    public static HashMap<String, String> hmMaTinhTenTinh = new HashMap<String, String>();
    static{
        hmMaTinhTenTinh.put("XSAG", "An Giang");
        hmMaTinhTenTinh.put("XSBD", "Bình Dương");
        hmMaTinhTenTinh.put("XSBP", "Bình Phước");
        hmMaTinhTenTinh.put("XSBTH", "Bình Thuận");
        hmMaTinhTenTinh.put("XSBDH", "Bình Định");
        hmMaTinhTenTinh.put("XSBL", "Bạc Liêu");
        hmMaTinhTenTinh.put("XSBTR", "Bến Tre");
        hmMaTinhTenTinh.put("XSCM", "Cà Mau");
        hmMaTinhTenTinh.put("XSCT", "Cần Thơ");
        hmMaTinhTenTinh.put("XSDLK", "DakLak");
        hmMaTinhTenTinh.put("XSGL", "Gia Lai");
        hmMaTinhTenTinh.put("XSHG", "Hậu Giang");
        hmMaTinhTenTinh.put("XSHCM", "Hồ Chí Minh");
        hmMaTinhTenTinh.put("XSKH", "Khánh Hòa");
        hmMaTinhTenTinh.put("XSKG", "Kiên Giang");
        hmMaTinhTenTinh.put("XSKT", "Kon Tum");
        hmMaTinhTenTinh.put("XSLA", "Long An");
        hmMaTinhTenTinh.put("XSTD", "Miền Bắc");
        hmMaTinhTenTinh.put("XSNT", "Ninh Thuận");
        hmMaTinhTenTinh.put("XSPY", "Phú Yên");
        hmMaTinhTenTinh.put("XSQB", "Quảng Bình");
        hmMaTinhTenTinh.put("XSQNM", "Quảng Nam");
        hmMaTinhTenTinh.put("XSQNI", "Quảng Ngãi");
        hmMaTinhTenTinh.put("XSQT", "Quảng Trị");
        hmMaTinhTenTinh.put("XSST", "Sóc Trăng");
        hmMaTinhTenTinh.put("XSTTH", "Thừa Thiên Huế");
        hmMaTinhTenTinh.put("XSTTH", "ThừaThiênHuế");
        hmMaTinhTenTinh.put("XSTG", "Tiền Giang");
        hmMaTinhTenTinh.put("XSTV", "Trà Vinh");
        hmMaTinhTenTinh.put("XSTN", "Tây Ninh");
        hmMaTinhTenTinh.put("XSVL", "Vĩnh Long");
        hmMaTinhTenTinh.put("XSVT", "Vũng Tàu");
        hmMaTinhTenTinh.put("XSDL", "Đà Lạt");
        hmMaTinhTenTinh.put("XSDNG", "Đà Nẵng");
        hmMaTinhTenTinh.put("XSDNO", "Đắc Nông");
        hmMaTinhTenTinh.put("XSDN", "Đồng Nai");
        hmMaTinhTenTinh.put("XSDT", "Đồng Tháp");
    }
    public static HashMap<String, String> hmTenTinhMaTinh = new HashMap<String, String>();
    static{
        for(Map.Entry<String, String> entry : hmMaTinhTenTinh.entrySet()){
            hmTenTinhMaTinh.put(entry.getValue(), entry.getKey());
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
        Elements elements = doc.select("#kqxsmb");        
        Elements listTinh = null;
        Elements listKQNgang = null;
        String openDate = null;
        List<LotteryResult> listLottery = new ArrayList<>();
        int i = 0;
        HashMap<Integer, LotteryResult> hmLottery = new HashMap<>();
        HashMap<Integer, String> hmTmp = new HashMap<>();
        int sizeNgang = 0;
        for(Element element : elements){
            if(!element.select(".result-header").isEmpty()){
                hmLottery = new HashMap<>();
                openDate = parseDate(element.select(".result-header").get(0).text());
                System.out.println("openDate="+openDate);
                //tieu de tinh
                sizeNgang = element.select("table .xsmb_ngang_1 td").size() - 1;   
                listTinh = element.select("table .xsmb_ngang_1 td"); 
                System.out.println("listTinh.size()="+listTinh.size());
                if(listTinh.size() > 0){
                    for(int j = 0; j < listTinh.size(); j++){
                        if(j==0) continue;
                        LotteryResult lr = new LotteryResult();
                        lr.setOpenDate(openDate);
                        lr.setCode(hmTenTinhMaTinh.get(listTinh.get(j).text()));
                        System.out.println("listTinh.get(j).text()="+listTinh.get(j).text());
                        lr.setSymbol(lr.getCode().replace("XS", ""));
                        
                        lr.setPrice(new BigDecimal(5000));
                        lr.setStatus(1);
                        lr.setUserName("xsw");
                        hmLottery.put(j-1, lr);
                    }
                }
                
                listKQNgang = element.select("table .xsmn_ngang");
                i = 0;
                for(Element kqNgang : listKQNgang){
                    //System.out.println("size="+kqNgang.select(".xsmn_3").size());
                    i++;
                    if(kqNgang.select("td").size() > 0){
                        for(int j = 0; j<kqNgang.select("td").size(); j++){
                            if(j==0) continue;
                            String tmp = new String();
                            tmp = kqNgang.select("td").get(j).text().replace(" ", "-");
                            hmTmp.put(j-1,tmp);
                        }
                        
                        if(i==1){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setEighth(hmTmp.get(j));
                            }
                        }else if(i==2){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSeventh(hmTmp.get(j));
                            }
                        }else if(i==3){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSixth(hmTmp.get(j));
                            }
                        }else if(i==4){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setFifth(hmTmp.get(j));
                            }
                        }else if(i==5){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setFourth(hmTmp.get(j));
                            }
                        }else if(i==6){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setThird(hmTmp.get(j));
                            }
                        }else if(i==7){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSecond(hmTmp.get(j));
                            }
                        }else if(i==8){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setFirst(hmTmp.get(j));
                            }
                        }else if(i==9){
                            for(int j=0; j<hmLottery.size(); j++){
                                hmLottery.get(j).setSpecial(hmTmp.get(j));
                            }
                        }             
                    }
                }
                
                for(int j=0; j<hmLottery.size(); j++){
                    listLottery.add(hmLottery.get(j));
                }
            }
        }
        System.out.println("==============================");
        System.out.println("list-size="+listLottery.size());
        for(LotteryResult lotteryResult : listLottery){
            System.out.println(lotteryResult.getCode()+"/"+lotteryResult.getOpenDate()+"/lotter="+lotteryResult.getSpecial());
        }
        System.out.println("===============================");        
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        //kiem tra da co trong DB chua
        //khong co moi insert
        LotteryResult tmp = null;
        for(LotteryResult lotteryResult : listLottery){
            //tmp = lotteryResultDAO.findResultByCodeInDay(lotteryResult.getCode(), lotteryResult.getOpenDate());
            tmp = XSCache.getLotteryByCodeAndOpenDate(lotteryResult.getCode(), lotteryResult.getOpenDate());
            if(tmp == null){
                boolean rs = lotteryResultDAO.createWithOpenDate(lotteryResult);
                if(rs) XSCache.clearCache();
                System.out.println("create="+rs);
            }
        }
    }
}
