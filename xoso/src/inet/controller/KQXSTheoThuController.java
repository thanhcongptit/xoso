/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.request.LotteryRequest;
import inet.util.UTF8Tool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class KQXSTheoThuController extends BaseController {
    private static HashMap<String,List<Lotterys>> hLotterys=null;
    private static HashMap<String,List<List<String>>> hLotteryDauDuoi=null;
    private static HashMap<String,String> hNumSize=null;
    private static String sDDMMYYYY=null;
    public KQXSTheoThuController() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotterys==null){hLotterys=new HashMap<String, List<Lotterys>>();}
        if(hLotteryDauDuoi==null){hLotteryDauDuoi=new HashMap<String, List<List<String>>>();}
        if(hNumSize==null){hNumSize=new HashMap<String, String>();}
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLotterys.clear();
            hLotteryDauDuoi.clear();
            hNumSize.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        
        String dow=request.getParameter("d");
        String code=request.getParameter("c");
        if(dow==null||"".equals(dow)){dow="cn";}
        if(code==null||"".equals(code)){code="MB";}
        else{code=code.replace("xs", "").toUpperCase();}
        
        String key=code+"_"+dow;
        Lotterys lotterys=null;
        List<Lotterys> listLotterys=null;
        List<List<String>> listLotteryDuoi=null;
        List<List<String>> listLotteryDau=null;
        int numSize=0;
        if(hLotterys.containsKey(key)){
            listLotterys=hLotterys.get(key);
            if(hLotteryDauDuoi.containsKey(key+"_duoi")){listLotteryDuoi=hLotteryDauDuoi.get(key+"_duoi");}
            if(hLotteryDauDuoi.containsKey(key+"_dau")){listLotteryDau=hLotteryDauDuoi.get(key+"_dau");}
            if(hNumSize.containsKey(key)){numSize=Integer.parseInt(hNumSize.get(key));}
        }else{
            LotteryRequest lotteryRequest=new LotteryRequest();
            List<Lottery> listLottery=lotteryRequest.parserLotteryResultDayOfWeek(code,dow,"8");
            if(listLottery!=null&&!listLottery.isEmpty()){
                listLotterys=findListLotterys(listLottery,key);
                if(hLotteryDauDuoi.containsKey(key+"_duoi")){listLotteryDuoi=hLotteryDauDuoi.get(key+"_duoi");}
                if(hLotteryDauDuoi.containsKey(key+"_dau")){listLotteryDau=hLotteryDauDuoi.get(key+"_dau");}
                numSize=listLottery.size()/7;
                try{
                    hNumSize.put(key, String.valueOf(numSize));
                }catch(Exception e){} 
                
                if(listLotterys!=null){hLotterys.put(key, listLotterys);}                
            }
        }
        
        //System.out.println("listLotteryDau==========>>>>>"+listLotteryDau.size());
        
        String loadNew="no";
        if(listLotterys!=null&&!listLotterys.isEmpty()){
            if(!ddmmyyyy.equals(listLotterys.get(0).getOpenDate())){loadNew="yes";}
        }
        
        mod.addObject("listLotterys", listLotterys);
        mod.addObject("listLotteryDuoi", listLotteryDuoi);
        mod.addObject("listLotteryDau", listLotteryDau);
        mod.addObject("numSize", numSize);
        mod.addObject("code", code);
        mod.addObject("dow", dow);
        
        // seo
        String slogan="Xổ số - kết quả xổ số";
        
        if("MB".equals(code)){
            if(dow.equalsIgnoreCase("cn")){slogan="XSMB - SXMB - XSTD - Kết quả xổ số miền Bắc chủ nhật";}
            else{slogan="XSMB - SXMB - XSTD - Kết quả xổ số miền Bắc thứ "+dow;}            
        }else if("MT".equals(code)){
            if(dow.equalsIgnoreCase("cn")){slogan="XSMT - SXMT - Kết quả xổ số miền Trung chủ nhật";}
            else{slogan="XSMT - SXMT - Kết quả xổ số miền Trung thứ "+dow;}            
        }else if("MN".equals(code)){
            if(dow.equalsIgnoreCase("cn")){slogan="XSMN - SXMN - Kết quả xổ số miền Nam chủ nhật";}
            else{slogan="XSMN - SXMN - Kết quả xổ số miền Nam thứ"+dow;}            
        }
        
        String title="";
        String keywords="";
        String description="";
        String strThu="Thứ ";
        if("cn".equalsIgnoreCase(dow)){strThu="Chủ nhật";}
        else{strThu=strThu+dow;}
        if("MB".equalsIgnoreCase(code)){
            title="XSMB – SXMB - XSTD "+strThu+" – Trực Tiếp kết quả xổ số Miền Bắc hôm nay ";
            keywords="xsmb thu "+UTF8Tool.coDau2KoDau(strThu)+", xsmb hom nay, truc tiep xsmb "+UTF8Tool.coDau2KoDau(strThu);
            description="XSMB - SXMB - XSTD "+strThu+". Trực tiếp kết quả xổ số Miền Bắc  "+strThu+" tại trường quay nhanh và chính xác nhất";
        }else if("MN".equalsIgnoreCase(code)){
            title="XSMN – SXMN "+strThu+" – Trực Tiếp kết quả xổ số Miền Nam hôm nay ";
            keywords="xsmn thu "+UTF8Tool.coDau2KoDau(strThu)+", xsmn hom nay, truc tiep xsmn "+UTF8Tool.coDau2KoDau(strThu);
            description="XSMN - SXMN "+strThu+". Trực tiếp kết quả xổ số Miền Nam  "+strThu+" tại trường quay nhanh và chính xác nhất";
        }else if("MT".equalsIgnoreCase(code)){
            title="XSMT – SXMT "+strThu+" – Trực Tiếp kết quả xổ số Miền Trung hôm nay ";
            keywords="xsmt thu "+UTF8Tool.coDau2KoDau(strThu)+", xsmt hom nay, truc tiep xsmt "+UTF8Tool.coDau2KoDau(strThu);
            description="XSMT - SXMT "+strThu+". Trực tiếp kết quả xổ số Miền Trung  "+strThu+" tại trường quay nhanh và chính xác nhất";
        }
                        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/kqxs_theo_thu");}
        else{mod.setViewName("/mobile/kqxs_theo_thu");}
        
        return mod;
    }

    
    private List<Lotterys> findListLotterys(List<Lottery> list,String key) {
        
        if(list==null||list.isEmpty()){return null;}
        List<Lotterys> listLotterys=null;
        Lotterys lotterys=null;
        Lottery lottery=null;
        List<Lottery> listLottery=new ArrayList<Lottery>();
        List<String> listLotteryDuoi=null;
        List<List<String>> list2LotteryDuoi=null;
        List<String> listLotteryDau=null;
        List<List<String>> list2LotteryDau=null;
        String dmy="";
        for(int i=0;i<list.size();i++){
            lottery=list.get(i);
            if(!"".equals(dmy)&&!dmy.equals(lottery.getOpenDate())){
                lotterys=findLotterys(listLottery, dmy);                
                if(listLotterys==null){listLotterys=new ArrayList<Lotterys>();}
                if(lotterys!=null){
                    listLotteryDuoi=findDuoi(listLottery);
                    if(listLotteryDuoi!=null){
                        if(list2LotteryDuoi==null){list2LotteryDuoi=new ArrayList<List<String>>();}
                        list2LotteryDuoi.add(listLotteryDuoi);
                    }
                    listLotteryDau=findDau(listLottery);
                    if(listLotteryDau!=null){
                        if(list2LotteryDau==null){list2LotteryDau=new ArrayList<List<String>>();}
                        list2LotteryDau.add(listLotteryDau);
                    }
                    listLotterys.add(lotterys);
                    listLottery.clear();
                }
                listLottery.add(lottery);
            }else{
                listLottery.add(lottery);
            }
                        
            dmy=lottery.getOpenDate();
        }
        if(list2LotteryDuoi!=null){hLotteryDauDuoi.put(key+"_duoi", list2LotteryDuoi);}
        if(list2LotteryDau!=null){hLotteryDauDuoi.put(key+"_dau", list2LotteryDau);}
        
        return listLotterys;
    }
    
    
}
