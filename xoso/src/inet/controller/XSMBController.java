/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.Lottery;
import inet.bean.LotterySpecial;
import inet.request.LotteryRequest;
import inet.request.ThongKeRequest;
import inet.util.DateProc;
import inet.util.StringPro;
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
public class XSMBController extends BaseController {
    private static HashMap<String,List<LotterySpecial>> hLotterySpecial=null;
    private static HashMap<String,List<Lottery>> hLotteryMB=null;
    private static HashMap<String,List<List<String>>> hLotteryDauDuoiMB=null;
    private static String sDDMMYYYY=null;
    public XSMBController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotterySpecial==null){hLotterySpecial=new HashMap<String, List<LotterySpecial>>();}
        if(hLotteryMB==null){hLotteryMB=new HashMap<String, List<Lottery>>();}
        if(hLotteryDauDuoiMB==null){hLotteryDauDuoiMB=new HashMap<String, List<List<String>>>();}
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLotterySpecial.clear();
            hLotteryMB.clear();
            hLotteryDauDuoiMB.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String day=request.getParameter("d");
        if(day==null||"".equals(day)){
            day=ddmmyyyy;
            Today today=new Today(); 
            if(today.getHour()<18||(today.getHour()==18&&today.getMinute()<=38)){
                day=ddmmyyyy;
            }else if(today.getHour()>18||(today.getHour()==18&&today.getMinute()>38)){
                day=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy),1);
            }
        }else{day=day.replace("-", "/");}
        // thong ke giai dac biet mien bac
        List<LotterySpecial> listLotterySpecial=null;
        String key=day.replace("/", "");
        if(hLotterySpecial.containsKey(key)){            
            listLotterySpecial=hLotterySpecial.get(key);
        }else{
            ThongKeRequest thongKeRequest=new ThongKeRequest();
            String sdate=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(day), -63);
            listLotterySpecial=thongKeRequest.parserThongKeTheoThu("XSTD", sdate, day);
            if(listLotterySpecial!=null&&!listLotterySpecial.isEmpty()){
                hLotterySpecial.put(key, listLotterySpecial);
            }
        }
        
        //lay ket qua xo so mien bac cua 7 ngay
        List<Lottery> listLotteryMB=null;
        List<List<String>> listLotteryDuoiMB=null;
        List<List<String>> listLotteryDauMB=null;
        if(hLotteryMB.containsKey(key)){
            listLotteryMB=hLotteryMB.get(key);
            if(hLotteryDauDuoiMB.containsKey(key+"_duoi")){
                listLotteryDuoiMB=hLotteryDauDuoiMB.get(key+"_duoi");
            }            
            //ban mobile lay dau
            //if("mobile".equals(strMobile)&&hLotteryDauDuoiMB.containsKey(key+"_dau")){
                listLotteryDauMB=hLotteryDauDuoiMB.get(key+"_dau");
            //}            
        }else{
            String endDate=day;
            String startDate=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(day), -7);
            LotteryRequest lotteryRequest=new LotteryRequest();
            listLotteryMB=lotteryRequest.parserLotteryResult("XSTD",startDate,endDate);
            if(listLotteryMB!=null&&!listLotteryMB.isEmpty()){
                hLotteryMB.put(key, listLotteryMB);                
                List<String> listDuoiMB=null;
                for(int i=0;i<listLotteryMB.size();i++){
                    listDuoiMB=StringPro.filterDauOrDuoi(listLotteryMB.get(i), true);
                    if(listLotteryDuoiMB==null){listLotteryDuoiMB=new ArrayList<List<String>>();}
                    listLotteryDuoiMB.add(listDuoiMB);
                    //ban mobile lay dau
                    //if("mobile".equals(strMobile)){
                        List<String> listDauMB=null;
                        listDauMB=StringPro.filterDauOrDuoi(listLotteryMB.get(i), false);
                        if(listLotteryDauMB==null){listLotteryDauMB=new ArrayList<List<String>>();}
                        listLotteryDauMB.add(listDauMB);
                    //}
                }
                
                if(listLotteryDuoiMB!=null&&!listLotteryDuoiMB.isEmpty()){
                    hLotteryDauDuoiMB.put(key+"_duoi", listLotteryDuoiMB);
                }
                
                if(listLotteryDauMB!=null&&!listLotteryDauMB.isEmpty()){
                    hLotteryDauDuoiMB.put(key+"_dau", listLotteryDauMB);
                }
            }
        }
        
        String loadNew="no";
        if(listLotteryMB!=null&&!listLotteryMB.isEmpty()){
            if(!ddmmyyyy.equals(listLotteryMB.get(0).getOpenDate())){loadNew="yes";}
        }
        mod.addObject("loadNew", loadNew);
        mod.addObject("listLotterySpecial", listLotterySpecial);
        mod.addObject("listLotteryMB", listLotteryMB);
        mod.addObject("listLotteryDuoiMB", listLotteryDuoiMB);
        mod.addObject("listLotteryDauMB", listLotteryDauMB);
        mod.addObject("ddmmyyyy", day);
        
        // seo
        String slogan="Xsmb – sxmb – kết quả xổ số miền bắc - xstd";        
        String title="Kết quả xổ số Miền Bắc - SXMB trực tiếp";
        if(!ddmmyyyy.equals(day)){title=title+" ngày "+day;}
        String keywords="xsmb,xo so mien bac,xstd,sxmb,kqxsmb,ket qua xo so mien bac,xsmb truc tiep,du doan xsmb";
        String description="XSMB - XSTD -SXMB - KQXSMB - Xo So Mien Bac Hôm Nay.Tường thuật trực tiếp kết quả Xổ Số Miền Bắc nhanh nhất,chính xác nhất từ trường quay .";
        
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/xsmb");}
        else{mod.setViewName("/mobile/xsmb");}
        return mod;
    }
    
}
