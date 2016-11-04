/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.DacBietMN;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.LotterySpecial;
import inet.request.LotteryRequest;
import inet.request.ThongKeRequest;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.RequestUtil;
import inet.util.StringPro;
import inet.util.Today;
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
public class KQXSTinhController extends BaseController {
    private static HashMap<String,List<LotterySpecial>> hLotterySpecial=null;
    private static HashMap<String,List<Lottery>> hLottery=null;
    private static HashMap<String,List<List<String>>> hLotteryDauDuoi=null;
    private static HashMap<String,LotteryCompany> hRegoin=null;
    private static List<DacBietMN> listDacBietMN=null;
    private static String sDDMMYYYY=null;
    public KQXSTinhController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotterySpecial==null){hLotterySpecial=new HashMap<String, List<LotterySpecial>>();}
        if(hLottery==null){hLottery=new HashMap<String, List<Lottery>>();}
        if(hLotteryDauDuoi==null){hLotteryDauDuoi=new HashMap<String, List<List<String>>>();}
        if(hRegoin==null){hRegoin=new HashMap<String, LotteryCompany>();}
        if(listDacBietMN==null){listDacBietMN=new ArrayList<DacBietMN>();}
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLotterySpecial.clear();
            hLottery.clear();
            hLotteryDauDuoi.clear();
            hRegoin.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        String code=request.getParameter("c");
        String day=request.getParameter("d");
        int kn = RequestUtil.getInt(request, "kn", 30);
        if(kn > 99) kn = 99;
        
        String sDay="";
        if(code==null||"".equals(code)){code="XSHCM";}
        else{code=code.toUpperCase();}
        if(day==null){
            day=ddmmyyyy;
            sDay=checkTimeOpen(listLotteryCompanyMT,listLotteryCompanyMN, code);
        }else{sDay=day.replace("-", "/");}
        
        //System.out.println("code====>>>"+code);
        
        // check uri
        if(!checkUri(code, canonical)){
            try{
                if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/404");}
                else{mod.setViewName("/mobile/404");}
                return mod;
            }catch(Exception e){}
        }
        
        //lay ket qua xo so mien bac cua 7 ngay
        List<Lottery> listLottery=null;
        List<List<String>> listLotteryDuoi=null;
        List<List<String>> listLotteryDau=null;
        LotteryCompany lotteryCompany=null;
        String key=sDay.replace("/", "")+"_"+code+"_"+kn;
        // thong ke giai dac biet mien bac
        List<LotterySpecial> listLotterySpecial=null;
        if(hLotterySpecial.containsKey(key)){            
            listLotterySpecial=hLotterySpecial.get(key);
        }else{            
            ThongKeRequest thongKeRequest=new ThongKeRequest();
            String sdate=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(sDay), -kn);
            listLotterySpecial=thongKeRequest.parserThongKeTheoThu(code, sdate, sDay);
            if(listLotterySpecial!=null&&!listLotterySpecial.isEmpty()){
                hLotterySpecial.put(key, listLotterySpecial);
            }
        }
        
        if(hLottery.containsKey(key)){
            listLottery=hLottery.get(key);
            if(hLotteryDauDuoi.containsKey(key+"_duoi")){
                listLotteryDuoi=hLotteryDauDuoi.get(key+"_duoi");
                listLotteryDau=hLotteryDauDuoi.get(key+"_dau");
            }            
            
            if(hRegoin.containsKey(key)){
                lotteryCompany=hRegoin.get(key);
            }
            if(!"XSTD".equalsIgnoreCase(code)){listDacBietMN=getDacBietMN(listLottery);}
        }else{
            String endDate=sDay;
            String startDate=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(sDay), -kn);
            LotteryRequest lotteryRequest=new LotteryRequest();
            listLottery=lotteryRequest.parserLotteryResult(code,startDate,endDate);            
            if(!"XSTD".equalsIgnoreCase(code)){listDacBietMN=getDacBietMN(listLottery);}
            if(listLottery!=null&&!listLottery.isEmpty()){
                hLottery.put(key, listLottery);                
                List<String> listDuoiMB=null;
                List<String> listDauMB=null;
                for(int i=0;i<listLottery.size();i++){
                    listDuoiMB=StringPro.filterDauOrDuoi(listLottery.get(i), true);
                    listDauMB=StringPro.filterDauOrDuoi(listLottery.get(i), false);
                    if(listLotteryDuoi==null){listLotteryDuoi=new ArrayList<List<String>>();}
                    if(listLotteryDau==null){listLotteryDau=new ArrayList<List<String>>();}
                    listLotteryDuoi.add(listDuoiMB);            
                    listLotteryDau.add(listDauMB);
                }
                
                if(listLotteryDuoi!=null&&!listLotteryDuoi.isEmpty()){
                    hLotteryDauDuoi.put(key+"_duoi", listLotteryDuoi);
                    hLotteryDauDuoi.put(key+"_dau", listLotteryDau);
                }
                
                //code thuoc khu vuc nao
                lotteryCompany=findLotteryCompanyOfCode(code);
                if(lotteryCompany!=null){
                    hRegoin.put(key, lotteryCompany);
                }
            }
        }
        String loadNew="no";
        if(lotteryCompany!=null){mod.addObject("regoin", lotteryCompany.getRegion());} 
        if(listLottery!=null&&!listLottery.isEmpty()){
            if(!ddmmyyyy.equals(listLottery.get(0).getOpenDate())){loadNew="yes";}
        }
        mod.addObject("listLotterySpecial", listLotterySpecial);
        mod.addObject("listLottery", listLottery);
        mod.addObject("listLotteryDuoi", listLotteryDuoi);
        mod.addObject("listLotteryDau", listLotteryDau);
        mod.addObject("lotteryCompany", lotteryCompany);
        mod.addObject("listDacBietMN", listDacBietMN);
        mod.addObject("ddmmyyyy", day);
        mod.addObject("code", code);
        mod.addObject("loadNew", loadNew);
        mod.addObject("kn", kn);
        
        // seo
        if(lotteryCompany!=null){
            String slogan=lotteryCompany.getCodeLowerCase()+" - kết quả xổ số "+lotteryCompany.getCompany().toLowerCase()+" - "+lotteryCompany.getCodeReverseLowerCase().toLowerCase();
            String title=lotteryCompany.getCodeLowerCase()+" - Ket qua xo so "+ UTF8Tool.coDau2KoDau(lotteryCompany.getCompany())+" - "+lotteryCompany.getCodeReverseLowerCase()+" trực tiếp nhanh vl";        
            String keywords=lotteryCompany.getCode()+",xo so "+UTF8Tool.coDau2KoDau(lotteryCompany.getCompany())+",ket qua xo so "+UTF8Tool.coDau2KoDau(lotteryCompany.getCompany())+",xổ số "+lotteryCompany.getCompany();
            String description=lotteryCompany.getCode()+"- XỔ SỐ "+lotteryCompany.getCompany()+" - "+lotteryCompany.getCodeReverseLowerCase()+" HÔM NAY - Xem kết quả xố số "+lotteryCompany.getCompany()+" trực tiếp tại trường quay ddmmyyyy nhanh và chính xác nhất";
            if(!ddmmyyyy.equals(day)){
                title=title+" ngày "+day;
                description=description.replace("ddmmyyyy", "ngày "+day);
            }else{
                description=description.replace("ddmmyyyy", "ngày hôm nay");
            }


            mod.addObject("slogan", slogan);
            mod.addObject("title", title);
            mod.addObject("keywords", keywords);
            mod.addObject("description", description);
        }
        
        //check mo thuong
        boolean isOpenDate=false;
        Today today=new Today();
        if(today.getHour()==16&&today.getMinute()<38){isOpenDate=checkLotteryOpen(listLotteryCompanyMN, code);}
        else if(today.getHour()==16&&today.getMinute()>38){isOpenDate=checkLotteryOpen(listLotteryCompanyMN, code);}        
        else if(today.getHour()==17&&today.getMinute()<38){
            isOpenDate=checkLotteryOpen(listLotteryCompanyMT, code);
            if(!isOpenDate){isOpenDate=checkLotteryOpen(listLotteryCompanyMN, code);}
        }else if(today.getHour()==17&&today.getMinute()>38){
            isOpenDate=checkLotteryOpen(listLotteryCompanyMT, code);
            if(!isOpenDate){isOpenDate=checkLotteryOpen(listLotteryCompanyMN, code);}
        }
                
        mod.addObject("isOpenDate", isOpenDate);
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/kqxs_tinh");}
        else{mod.setViewName("/mobile/kqxs_tinh");}
        return mod;
        
    }
    
    
    private boolean checkLotteryOpen(List<LotteryCompany> list,String code){
        if(list==null||list.isEmpty()||code==null||"".equals(code)){return false;}
        
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCode().equalsIgnoreCase(code)){return true;}
        }
        
        return false;
    }
    
    
    
    private boolean checkUri(String code,String uri){
        boolean result=true;
        if(uri.contains("ket-qua-xo-so-thua-thien-hue-xstth")){return result;}
        LotteryCompany lotteryCompany=findLotteryCompanyOfCode(code);        
        if(lotteryCompany==null||!uri.contains("ket-qua-xo-so-"+lotteryCompany.getCompanyLink())){result=false;}
        
        return result;
    }
    
    private List<DacBietMN> getDacBietMN(List<Lottery> list){
        List<DacBietMN> listdacBietMN=null;
        if(list==null||list.isEmpty()){return listdacBietMN;}
        Lottery lottery=null;
        DacBietMN dacBietMN=null;
        for(int i=0;i<list.size();i++){
            lottery=list.get(i);
            if(listdacBietMN==null){listdacBietMN=new ArrayList<DacBietMN>();}
            dacBietMN=new DacBietMN();
            dacBietMN.setDdmmyyyy(lottery.getOpenDate().substring(0,lottery.getOpenDate().length()-5));
            dacBietMN.setThu(DatePro.getDateOfWeekDDMMYYYY(lottery.getOpenDate()));
            if(dacBietMN.getThu().equalsIgnoreCase("cn")){dacBietMN.setThu("Chủ nhật");}
            else{dacBietMN.setThu("Thứ "+dacBietMN.getThu());}
            
            if(lottery.getSpecial()!=null){
                dacBietMN.setDb1(lottery.getSpecial().substring(0,lottery.getSpecial().length()-2)+"<span class=\"red\">"+lottery.getSpecial().substring(lottery.getSpecial().length()-2,lottery.getSpecial().length())+"</span>");
            }
            
            if(lottery.getEighth()!=null){
                dacBietMN.setG81(lottery.getEighth());
            }
            
            
            listdacBietMN.add(dacBietMN);
        }
        
        return listdacBietMN;
    }
    
    private String checkTimeOpen(List<LotteryCompany> list,List<LotteryCompany> listMN,String code){
        String result=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);;
        Today today=new Today();        
        if(list==null||list.isEmpty()||code==null||"".equals(code)){return result;}
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCode().equalsIgnoreCase(code)){
                if(list.get(i).getRegion().equals("MT")){
                    if(today.getHour()>17||(today.getHour()==17&&today.getMinute()>38)){return ddmmyyyy;}
                }
            }
        }
        
        if(listMN==null||listMN.isEmpty()||code==null||"".equals(code)){return result;}
        for(int i=0;i<listMN.size();i++){
            if(listMN.get(i).getCode().equalsIgnoreCase(code)){
                if(listMN.get(i).getRegion().equals("MN")){
                    if(today.getHour()>16||(today.getHour()==16&&today.getMinute()>38)){return ddmmyyyy;}                    
                }
            }
        }
        
        return result;
    }
}
