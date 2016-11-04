/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing;

import inet.bean.Lottery;
import inet.bean.LotteryResult;
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
public class ParsingXSMN_bk {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://xoso.wap.vn/ket-qua-xo-so-mien-nam-xsmn-ngay-17-01-2016.html")
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(3000)
        .post();
        Elements elements = doc.select("#kqxsmb");        
        Elements listTinh = null;
        Elements listKQNgang = null;
        LotteryResult ve1 = null;
        LotteryResult ve2 = null;
        LotteryResult ve3 = null;
        String openDate = null;
        List<LotteryResult> listLottery = new ArrayList<>();
        int i = 0;
        String tmp1, tmp2, tmp3;
        HashMap<Integer, LotteryResult> hmLottery = new HashMap<>();
        for(Element element : elements){
            if(!element.select(".result-header").isEmpty()){
                ve1 = new LotteryResult();
                ve2 = new LotteryResult();
                ve3 = new LotteryResult();
                openDate = parseDate(element.select(".result-header").get(0).text());
                
                ve1.setOpenDate(openDate);
                ve2.setOpenDate(openDate);
                ve3.setOpenDate(openDate);
                
                //tieu de tinh
                listTinh = element.select("table .xsmb_ngang_1 .xsmn_3");                     
                if(listTinh.size() == 3){
                    ve1.setCode(hmTenTinhMaTinh.get(listTinh.get(0).text()));
                    ve1.setSymbol(ve1.getCode().replace("XS", ""));
                    ve2.setCode(hmTenTinhMaTinh.get(listTinh.get(1).text()));
                    ve2.setSymbol(ve2.getCode().replace("XS", ""));
                    ve3.setCode(hmTenTinhMaTinh.get(listTinh.get(2).text()));
                    ve3.setSymbol(ve3.getCode().replace("XS", ""));
                    
                    ve1.setPrice(new BigDecimal(5000));
                    ve1.setStatus(1);
                    ve1.setUserName("quyetnv");
                    
                    ve2.setPrice(new BigDecimal(5000));
                    ve2.setStatus(1);
                    ve2.setUserName("quyetnv");
                    
                    ve3.setPrice(new BigDecimal(5000));
                    ve3.setStatus(1);
                    ve3.setUserName("quyetnv");
                }
                
                listKQNgang = element.select("table .xsmn_ngang");
                i = 0;
                for(Element kqNgang : listKQNgang){
                    //System.out.println("size="+kqNgang.select(".xsmn_3").size());
                    i++;
                    if(kqNgang.select(".xsmn_3").size() == 3){
                        tmp1 = kqNgang.select("td.xsmn_3").get(0).text().replace(" ", "-");
                        tmp2 = kqNgang.select("td.xsmn_3").get(1).text().replace(" ", "-");
                        tmp3 = kqNgang.select("td.xsmn_3").get(2).text().replace(" ", "-");
                        if(i==1){
                            ve1.setEighth(tmp1);
                            ve2.setEighth(tmp2);
                            ve3.setEighth(tmp3);
                        }else if(i==2){
                            ve1.setSeventh(tmp1);
                            ve2.setSeventh(tmp2);
                            ve3.setSeventh(tmp3);
                        }else if(i==3){
                            ve1.setSixth(tmp1);
                            ve2.setSixth(tmp2);
                            ve3.setSixth(tmp3);
                        }else if(i==4){
                            ve1.setFifth(tmp1);
                            ve2.setFifth(tmp2);
                            ve3.setFifth(tmp3);
                        }else if(i==5){
                            ve1.setFourth(tmp1);
                            ve2.setFourth(tmp2);
                            ve3.setFourth(tmp3);
                        }else if(i==6){
                            ve1.setThird(tmp1);
                            ve2.setThird(tmp2);
                            ve3.setThird(tmp3);
                        }else if(i==7){
                            ve1.setSecond(tmp1);
                            ve2.setSecond(tmp2);
                            ve3.setSecond(tmp3);
                        }else if(i==8){
                            ve1.setFirst(tmp1);
                            ve2.setFirst(tmp2);
                            ve3.setFirst(tmp3);
                        }else if(i==9){
                            ve1.setSpecial(tmp1);
                            ve2.setSpecial(tmp2);
                            ve3.setSpecial(tmp3);
                        }             
                    }
                }
                
                listLottery.add(ve1);
                listLottery.add(ve2);
                listLottery.add(ve3);
            }
        }
        
        System.out.println("list-size="+listLottery.size());
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        //kiem tra da co trong DB chua
        //khong co moi insert
        Lottery tmp = null;
        for(LotteryResult lotteryResult : listLottery){
            tmp = lotteryResultDAO.findResultByCodeInDay(lotteryResult.getCode(), lotteryResult.getOpenDate());
            if(tmp == null){
                System.out.println("create="+lotteryResultDAO.createWithOpenDate(lotteryResult));
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
        hmMaTinhTenTinh.put("XSBDH", "Bình Định ");
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
}
