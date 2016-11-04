/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.parsing.kq;

import inet.bean.LotteryResult;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static xs.parsing.ParsingXS.getListKQ;

/**
 *
 * @author thangdm
 */
public class ParsingXS {
    public static void main(String[] args) throws IOException {
        String url = "http://ketqua.net/xo-so-mien-bac.php";
        System.out.println("url="+url);
        Document doc = Jsoup.connect(url)
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(3000)
        .post();
        Elements elements = doc.select("div#ketqua_mb table");
        Elements listTr = null;
        int i = 0;
        LotteryResult lottery = null;
        if(elements != null) for(Element element : elements){
            listTr = element.select("tr");
            for(Element tr : listTr){
                lottery = new LotteryResult();
                System.out.println(tr.text());
                System.out.println("========================");
                i++;
                if(i == 1){
                    //lay ngay quay thuong
                    System.out.println("Ngay: "+parseDate(tr.text()));
                    lottery.setOpenDate(parseDate(tr.text()));
                }else if(i == 2){
                    //giai dac biet
                    lottery.setSpecial(getListKQ(tr.text()));
                    System.out.println("dacbiet="+getListKQ(lottery.getSpecial()));
                }else if(i==3){
                    //giai nhat
                    lottery.setFirst(getListKQ(tr.text()));
                }else if(i==4){
                    //giai nhi
                    lottery.setSecond(getListKQ(tr.text()));
                }else if(i==5 || i== 6){
                    //giai ba
                    if(i==5){
                        lottery.setThird(getListKQ(tr.text()));
                    }else{
                        lottery.setThird(lottery.getThird() + "-" + getListKQ(tr.text()));
                    }
                }else if(i == 7){
                    //giai tu
                    lottery.setFourth(getListKQ(tr.text()));
                }else if(i == 8 || i == 9){
                    //giai nam
                    if(i==8){
                        lottery.setFifth(getListKQ(tr.text()));
                    }else{
                        lottery.setFifth(lottery.getThird() + "-" + getListKQ(tr.text()));
                    }
                }else if(i == 10){
                    //giai sau
                    lottery.setSixth(getListKQ(tr.text()));
                }else if(i == 11){
                    //giai bay
                    lottery.setSeventh(getListKQ(tr.text()));
                }
            }
            break;
        }
        System.out.println("size="+elements.size());
    }
    
    public static String parseDate(String title){
        String[] split = title.split(" ");
        if(split != null && split.length > 0) return split[split.length-1];
        return "";
    }
    
    public static String getListKQ(String text){
        String[] split = text.split(" ");
        String rs = "";
        int i = 0;
        if(split != null && split.length > 0) for(String s : split){
            if(isNumber(s)){
                if(i==0){
                    rs += s.trim();
                }else{
                    rs += "-"+ s.trim();
                }
                i++;
            }
        }
        return rs;
    }
    
    public static boolean isNumber(String text){
        try{
            int i = Integer.parseInt(text);
            return true;
        }catch(Exception ex){}
        return false;
    }
}
