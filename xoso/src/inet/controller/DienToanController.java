/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.DienToanThanTai;
import inet.bean.Lottery;
import inet.request.LotteryRequest;
import inet.util.Contant;
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
 * @author hanhlm
 */
public class DienToanController extends BaseController {
    private static HashMap<String,List<Lottery>> hLotteryMB=null;
    private static HashMap<String,List<String>> hLotteryDauDuoi=null;
    private static List<DienToanThanTai> listDienToan123=null;
    private static List<DienToanThanTai> listDienToan636=null;
    private static String sDDMMYYYY=null;
    public DienToanController() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotteryMB==null){hLotteryMB=new HashMap<String, List<Lottery>>();}
        if(hLotteryDauDuoi==null){hLotteryDauDuoi=new HashMap<String, List<String>>();} 
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLotteryMB.clear();
            hLotteryDauDuoi.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String day=request.getParameter("d");
        
        LotteryRequest  lotteryRequest=new LotteryRequest();
        
        Today today=new Today();
        String dayMB="";
        if(today.getHour()<18||(today.getHour()==18&&today.getMinute()<=38)){
            dayMB=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy),-1);
        }else if(today.getHour()>18||(today.getHour()==18&&today.getMinute()>38)){
            dayMB=ddmmyyyy;
        }
        
        if(day!=null&&!"".equals(day)){dayMB=day.replaceAll("-", "/");}
        
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
            
            listDienToan123=lotteryRequest.parserDienToanThanTai(Contant.XSDT123, dayMB, dayMB);            
            listDienToan636=lotteryRequest.parserDienToanThanTai(Contant.XSDT636, dayMB, dayMB);            
            
        }
        if(listDienToan123!=null&&!listDienToan123.isEmpty()){
            mod.addObject("listDienToan123", listDienToan123.get(0).getResult());
            mod.addObject("openDate123", listDienToan123.get(0).getDate());
        }
        if(listDienToan636!=null&&!listDienToan636.isEmpty()){
            mod.addObject("listDienToan636", listDienToan636.get(0).getResult());
            mod.addObject("openDate636", listDienToan636.get(0).getDate());
        }
        
        mod.addObject("listLotteryMB", listLotteryMB);
        mod.addObject("listLotteryDauMB", listLotteryDauMB);
        mod.addObject("listLotteryDuoiMB", listLotteryDuoiMB);
        mod.addObject("dayMB", dayMB);
        
        
        // seo
        String slogan="XSDT - Kết quả xổ số điện toán";
        String title="Xổ số điện toán – kết quả xổ số diện toán";
        String keywords="xo so dien toan, ket qua xo so dien toan, xsdt";
        String description="XSDT - Xo so dien toan – Ket qua xo so dien toan 123, 6x36. Trực tiếp kết quả xổ số điện toán 123, 6x36 hôm nay tại trường quay nhanh và chính xác nhất";                
                                    
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
                
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/dientoan");}
        else{mod.setViewName("/mobile/dientoan");}
        
        return mod;
    }
    
}
