/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.controller;

import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.request.LotteryRequest;
import inet.util.DateProc;
import inet.util.StringPro;
import inet.util.Today;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 24h
 */
public class SoKetQuaController extends BaseController{
    private static HashMap<String,List<Lottery>> hLotteryMB=null;
    private static HashMap<String,Lotterys> hLotterys=null;
    private static HashMap<String,List<String>> hLotteryDauDuoi=null;
    private static HashMap<String,String> hNumSize=null;
    private static String sDDMMYYYY=null;

    public SoKetQuaController() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotteryMB==null){hLotteryMB=new HashMap<String, List<Lottery>>();}
        if(hLotterys==null){hLotterys=new HashMap<String, Lotterys>();}
        if(hLotteryDauDuoi==null){hLotteryDauDuoi=new HashMap<String, List<String>>();} 
        if(hNumSize==null){hNumSize=new HashMap<String, String>();} 
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLotteryMB.clear();
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
        
        LotteryRequest  lotteryRequest=new LotteryRequest();
        //lay ket qua xoso mien bac
        String day=request.getParameter("d");
        String dayMB="";
        String dayMT="";
        String dayMN="";
        if(day==null||"".equals(day)){
            Today today=new Today();            
            if(today.getHour()<18||(today.getHour()==18&&today.getMinute()<=38)){
                dayMB=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy),-1);
            }else if(today.getHour()>18||(today.getHour()==18&&today.getMinute()>38)){
                dayMB=ddmmyyyy;
            }
            
            if(today.getHour()<17||(today.getHour()==17&&today.getMinute()<=38)){
                dayMT=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy),-1);
            }else if(today.getHour()>17||(today.getHour()==17&&today.getMinute()>38)){
                dayMT=ddmmyyyy;
            }
            
            if(today.getHour()<16||(today.getHour()==16&&today.getMinute()<=38)){
                dayMN=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy),-1);
            }else if(today.getHour()>16||(today.getHour()==16&&today.getMinute()>38)){                
                dayMN=ddmmyyyy;
            }
            
            day=ddmmyyyy;
        }else{            
            day=day.replace("-", "/");
            if(ddmmyyyy.equals(day)){day=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy),-1);}
            dayMB=day;
            dayMT=day;
            dayMN=day;
        }
        
        List<Lottery> listLotteryMB=null;       
        List<String> listLotteryDuoiMB=null;
        List<String> listLotteryDauMB=null;
        String key="MB_"+dayMB.replace("/", "");
        if(hLotteryMB.containsKey(key)){
            listLotteryMB=hLotteryMB.get(key);
            listLotteryDuoiMB=hLotteryDauDuoi.get(key);
            //ban mobile lay dau
            //if("mobile".equals(strMobile)){
                listLotteryDauMB=hLotteryDauDuoi.get(key+"_dau");
            //}
        }else{
            listLotteryMB=lotteryRequest.parserLotteryResultOfRegion(dayMB, "MB");
            if(listLotteryMB!=null&&!listLotteryMB.isEmpty()){
                listLotteryDuoiMB=StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
                hLotteryMB.put(key, listLotteryMB);    
                hLotteryDauDuoi.put(key, listLotteryDuoiMB);
                
                //ban mobile lay dau
                //if("mobile".equals(strMobile)){
                    listLotteryDauMB=StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
                    hLotteryDauDuoi.put(key+"_dau", listLotteryDauMB);
                //}
            }
        }
        
        Lotterys lotterysMT=null;
        List<String> listLotteryDuoiMT=null;
        int numSizeMT=0;
        key="MT_"+dayMT.replace("/", "");        
        if(hLotterys.containsKey(key)){
            lotterysMT=hLotterys.get(key);
            listLotteryDuoiMT=hLotteryDauDuoi.get(key);            
            try{
                numSizeMT=Integer.parseInt(hNumSize.get(key));
            }catch(Exception e){}
            
        }else{
            List<Lottery> listLotteryMT=lotteryRequest.parserLotteryResultOfRegion(dayMT, "MT");
            if(listLotteryMT!=null&&!listLotteryMT.isEmpty()){
                lotterysMT=findLotterys(listLotteryMT, dayMT);
                listLotteryDuoiMT=findDuoi(listLotteryMT);
                numSizeMT=listLotteryMT.size();
                try{
                    hNumSize.put(key, String.valueOf(numSizeMT));
                }catch(Exception e){}                
                hLotterys.put(key, lotterysMT);
                hLotteryDauDuoi.put(key, listLotteryDuoiMT);
                
            }
        }
        
        Lotterys lotterysMN=null;
        List<String> listLotteryDuoiMN=null;
        int numSizeMN=0;
        key="MN_"+dayMN.replace("/", "");
        if(hLotterys.containsKey(key)){
            lotterysMN=hLotterys.get(key);
            listLotteryDuoiMN=hLotteryDauDuoi.get(key);    
            try{
                numSizeMN=Integer.parseInt(hNumSize.get(key));
            }catch(Exception e){}
        }else{
            List<Lottery> listLotteryMN=lotteryRequest.parserLotteryResultOfRegion(dayMN, "MN");
            if(listLotteryMN!=null&&!listLotteryMN.isEmpty()){
                lotterysMN=findLotterys(listLotteryMN, dayMN);
                listLotteryDuoiMN=findDuoi(listLotteryMN);
                numSizeMN=listLotteryMN.size();
                try{
                    hNumSize.put(key, String.valueOf(numSizeMN));
                }catch(Exception e){} 
                hLotterys.put(key, lotterysMN);
                hLotteryDauDuoi.put(key, listLotteryDuoiMN);
            }
        }
        
                
        
        mod.addObject("listLotteryMB", listLotteryMB);
        mod.addObject("lotterysMT", lotterysMT);
        mod.addObject("lotterysMN", lotterysMN);
        
        mod.addObject("listLotteryDuoiMB", listLotteryDuoiMB);
        mod.addObject("listLotteryDuoiMT", listLotteryDuoiMT);
        mod.addObject("listLotteryDuoiMN", listLotteryDuoiMN);
        
        mod.addObject("listLotteryDauMB", listLotteryDauMB);
        
        mod.addObject("numSizeMT", numSizeMT);
        mod.addObject("numSizeMN", numSizeMN);
                                
        mod.addObject("ddmmyyyy", ddmmyyyy);
        
        // seo
        String slogan="Xổ số - kết quả xổ số";
        String title="Kết quả xổ số Miền Bắc";
        String keywords="ket qua xo so,so xo,xo so mien bac,xổ số miền bắc,xstd,xsmb,xo so mien trung,kqxs,xo so mien nam,xo so,xsmt,xsmn,kết quả xổ số";
        String description="xstd - Trực tiếp kết quả xổ số 3 miền bắc,trung,nam nhanh nhất.Phân tích kqxs,thống kê các cặp xo so may mắn trong ngày ";                
            
        if(canonical!=null&&canonical.contains("kqxs-ket-qua-xo-so-ngay")){
            title="Xổ số - kết quả xổ số - KQXS Trực tiếp nhanh vãi ngày "+day.replace("/", "-");
        }else if(canonical!=null&&canonical.contains("kqxs-ket-qua-xo-so")){
            title="Xổ số - kết quả xổ số - KQXS Trực tiếp nhanh vãi";
            keywords="ket qua xo so, xo so, kqxs, truc tiep xo so, xo so truc tiep";
            description="XO SO - KET QUA XO SO - KQXS. Trực tiếp kết quả xổ số ngày hôm nay thứ 2 thứ 3 thứ 4 thứ 5 thứ 6 thứ 7 chủ nhật tại trường quay chính xác và nhanh nhất"       ;
        }
        
        
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
                
        mod.setViewName("/soketqua");
        
        return mod;
    }
    
}
