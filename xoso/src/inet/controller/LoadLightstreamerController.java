/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.LotteryCompany;
import inet.request.LotteryRequest;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.WapTool;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class LoadLightstreamerController extends AbstractController {
    
    private static List<LotteryCompany> listLotteryCompanyMT=new ArrayList<LotteryCompany>();
    private static List<LotteryCompany> listLotteryCompanyMN=new ArrayList<LotteryCompany>();
    private static List<LotteryCompany> listCompany=null;
    private static String sDDMMYYYY="";
    
    public LoadLightstreamerController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=new ModelAndView(); 
        
        String ddmmyyyy=DateProc.getDateString(DateProc.createTimestamp());
        if(!ddmmyyyy.equals(sDDMMYYYY)){
            // lay lich mo thuong                        
            sDDMMYYYY=ddmmyyyy;
            if(listCompany==null||listCompany.isEmpty()){
                LotteryRequest lotteryRequest=new LotteryRequest();
                listCompany=lotteryRequest.parserLotteryCompany();            
            }
            if(listCompany!=null&&!listCompany.isEmpty()){                                     
                listLotteryCompanyMT=getCompanyDayOfWeek("MT");
                listLotteryCompanyMN=getCompanyDayOfWeek("MN");                                
                System.out.println("==========load lich mo thuong========="+listLotteryCompanyMN.size());
            }
        }
                
        mod.addObject("numSizeOpenMN", listLotteryCompanyMN.size());
        mod.addObject("numSizeOpenMT", listLotteryCompanyMT.size());
        mod.addObject("listLotteryCompanyMN", listLotteryCompanyMN);
        mod.addObject("listLotteryCompanyMT", listLotteryCompanyMT);
        mod.addObject("ddmmyyyy", ddmmyyyy);
        
        try{
            boolean isMobile = WapTool.isMobileDevice(request);
            String strMobile="pc";
            if(isMobile){
                strMobile="mobile";
            }
            mod.addObject("strMobile", strMobile);
        }catch(Exception e){}
        mod.setViewName("/ajax/live_lightstreamer");
        
        return mod;
    }
    
    
    private List<LotteryCompany> getCompanyDayOfWeek(String region){
        LotteryCompany lotteryCompany=null;
        List<LotteryCompany> listComp=null;
        String dayOfWeek=DatePro.getDateOfWeekDDMMYYYY(sDDMMYYYY);
        for(int i=0;i<listCompany.size();i++){            
            lotteryCompany=listCompany.get(i);
            if(lotteryCompany.getOpendate().toLowerCase().contains(dayOfWeek.toLowerCase())&&lotteryCompany.getRegion().equals(region)){
                if(listComp==null){listComp=new ArrayList<LotteryCompany>();}
                listComp.add(lotteryCompany);
            }                                              
        }
        
        return listComp;
    }
}
