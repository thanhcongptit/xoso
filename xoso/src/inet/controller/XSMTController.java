/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.DacBietMN;
import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.request.LotteryRequest;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.Today;
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
public class XSMTController extends BaseController {    
    private static HashMap<String,List<Lotterys>> hLotterys=null;
    private static HashMap<String,List<List<String>>> hLotteryDauDuoi=null;
    private static List<DacBietMN> listDacBietMN=null;
    private static List<String> hNumSize=null;
    private static String sDDMMYYYY=null;
    public XSMTController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotterys==null){hLotterys=new HashMap<String, List<Lotterys>>();}
        if(hLotteryDauDuoi==null){hLotteryDauDuoi=new HashMap<String,List<List<String>>>();}
        if(hNumSize==null){hNumSize=new ArrayList<String>();} 
        if(listDacBietMN==null){listDacBietMN=new ArrayList<DacBietMN>();}
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
                
        String day=request.getParameter("d");
        String dayMT="";
        //lay ket qua xo so mien rung
        if(day==null||"".equals(day)){
            day=ddmmyyyy;
            Today today=new Today(); 
            if(today.getHour()<17||(today.getHour()==17&&today.getMinute()<=38)){
                dayMT=ddmmyyyy;
            }else if(today.getHour()>17||(today.getHour()==17&&today.getMinute()>38)){
                dayMT=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy),1);
            }
        }else{dayMT=day.replace("-", "/");}
        
        String edate=dayMT;
        String sdate=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(edate),-8);
        
        String key="MT_"+edate.replace("/", "");
        Lotterys lotterys=null;
        List<Lotterys> listLotterys=null;
        List<List<String>> listLotteryDuoi=null;
        List<List<String>> listLotteryDau=null;

        if(hLotterys.containsKey(key)){
            listLotterys=hLotterys.get(key);
            if(hLotteryDauDuoi.containsKey(key+"_duoi")){listLotteryDuoi=hLotteryDauDuoi.get(key+"_duoi");}
            if(hLotteryDauDuoi.containsKey(key+"_dau")){listLotteryDau=hLotteryDauDuoi.get(key+"_dau");}
        }else{
            LotteryRequest lotteryRequest=new LotteryRequest();
            List<Lottery> listLottery=lotteryRequest.parserLotteryResultRegion(sdate,edate,"MT");
            if(listLottery!=null&&!listLottery.isEmpty()){
                listLotterys=findListLotterys(listLottery,key);
                if(hLotteryDauDuoi.containsKey(key+"_duoi")){listLotteryDuoi=hLotteryDauDuoi.get(key+"_duoi");}
                if(hLotteryDauDuoi.containsKey(key+"_dau")){listLotteryDau=hLotteryDauDuoi.get(key+"_dau");}                                
                if(listLotterys!=null){hLotterys.put(key, listLotterys);}                
            }
        }
        
        String loadNew="no";
        if(listLotterys!=null&&!listLotterys.isEmpty()){
            if(!ddmmyyyy.equals(listLotterys.get(0).getOpenDate())){loadNew="yes";}
        }
        
        mod.addObject("listLotterys", listLotterys);
        mod.addObject("listLotteryDuoi", listLotteryDuoi);
        mod.addObject("listDacBietMN", listDacBietMN);
        mod.addObject("hNumSize", hNumSize);
        mod.addObject("ddmmyyyy", day);
        mod.addObject("loadNew", loadNew);
        
        // seo
        String slogan="Xsmt – kết quả xổ số miền trung - sxmt";
        String title="Kết quả xổ số Miền Trung";        
        String keywords="xo so,xsmt,so xo,xo so mien trung,kqxs,ket qua xo so mien trung,xổ số miền trung";
        String description="XSMT - Xo so mien trung - SXMT HÔM NAY - Tường thuật trực tiếp kết quả xổ số miền trung nhanh nhất,chính xác nhất ,thống kê các cặp số đẹp";
        if(!ddmmyyyy.equals(day)){
            title=title+" ngày "+day;
            description=description+" ngày "+day;
        }
        
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/xsmt");}
        else{mod.setViewName("/mobile/xsmt");}
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
        DacBietMN dacBietMN=null;
        String dmy="";
        for(int i=0;i<list.size();i++){
            lottery=list.get(i);
            if(!"".equals(dmy)&&!dmy.equals(lottery.getOpenDate())){
                lotterys=findLotterys(listLottery, dmy);
//                dacBietMN=getDacBietMN(listLottery);
//                listDacBietMN.add(dacBietMN);
                hNumSize.add(""+listLottery.size());
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
//        
        return listLotterys;
    }
    
    private DacBietMN getDacBietMN(List<Lottery> list){
        DacBietMN dacBietMN=null;
        if(list==null||list.isEmpty()){return dacBietMN;}
        Lottery lottery=null;
        for(int i=0;i<list.size();i++){
            lottery=list.get(i);
            if(dacBietMN==null){dacBietMN=new DacBietMN();}
            if(i==0){
                dacBietMN.setThu(DatePro.getDateOfWeekDDMMYYYY(lottery.getOpenDate()));
                if(dacBietMN.getThu().equalsIgnoreCase("cn")){dacBietMN.setThu("Chủ nhật");}
                else{dacBietMN.setThu("Thứ "+dacBietMN.getThu());}
                dacBietMN.setDb1(lottery.getSpecial().substring(lottery.getSpecial().length()-2,lottery.getSpecial().length()));
                dacBietMN.setG81(lottery.getEighth());
            }else if(i==1){
                dacBietMN.setDb2(lottery.getSpecial().substring(lottery.getSpecial().length()-2,lottery.getSpecial().length()));
                dacBietMN.setG82(lottery.getEighth());
            }else if(i==2){
                dacBietMN.setDb3(lottery.getSpecial().substring(lottery.getSpecial().length()-2,lottery.getSpecial().length()));
                dacBietMN.setG83(lottery.getEighth());
            }else if(i==3){
                dacBietMN.setDb4(lottery.getSpecial().substring(lottery.getSpecial().length()-2,lottery.getSpecial().length()));
                dacBietMN.setG84(lottery.getEighth());
            }            
        }
        
        return dacBietMN;
    }
}
