/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.listener.XSLive;
import inet.util.WapTool;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class BuildXSLiveController extends AbstractController {
    
    public BuildXSLiveController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         ModelAndView mod=new ModelAndView();
        
        String region=request.getParameter("r");
        
        if(region==null||"".equals(region)||"all".equalsIgnoreCase(region)){
            mod.setViewName("/ajax/live");
        }else if("mb".equalsIgnoreCase(region)){mod.setViewName("/ajax/live_mb");}
        else if("mt".equalsIgnoreCase(region)){mod.setViewName("/ajax/live_mt");}
        else if("mn".equalsIgnoreCase(region)){mod.setViewName("/ajax/live_mn");}
        else{
            mod.setViewName("/ajax/live_tinh");
        }
         
        List<Lottery> listLotteryMB=XSLive.listLotteryMB;
        List<String> listDuoiMB=XSLive.listDuoiMB;
        List<String> listDauMB=XSLive.listDauMB;
        
        Lotterys listLotteryMT=XSLive.listLotteryMT;
        List<String> listDuoiMT=XSLive.listDuoiMT;
        int numSizeMT=XSLive.numSizeMT;
        
        Lotterys listLotteryMN=XSLive.listLotteryMN;
        List<String> listDuoiMN=XSLive.listDuoiMN;
        int numSizeMN=XSLive.numSizeMN;
        
        //System.out.println("region=========>>>>>>"+region);
        
        Lottery lottery=null;        
        List<String> listDuoi=null;
        List<String> listDau=null;
        if(XSLive.hLottery!=null&&XSLive.hLottery.containsKey(region)){
            lottery=XSLive.hLottery.get(region);
            listDuoi=XSLive.hDauDuoi.get(region+"_duoi");
            listDau=XSLive.hDauDuoi.get(region+"_dau");
        }
        
//        if(listDauDuoi!=null){
//            System.out.println("region=============>>>>>>>>>>>>"+region);
//            for(int i=0;i<listDauDuoi.size();i++){
//                System.out.println("listDauDuoi.get(i)=============>>>>>>>>>>>>"+listDauDuoi.get(i));
//            }
//            
//        }
        
//        if(listLotteryMT!=null){
//            System.out.println("listLotteryMT==>>"+numSizeMT);
//        }
        
        mod.addObject("openDate", XSLive.sDDMMYYYY);
        System.out.println("===================listLotteryMB="+listLotteryMB);
        mod.addObject("listLotteryMB", listLotteryMB);
        mod.addObject("listDuoiMB", listDuoiMB);
        mod.addObject("listDauMB", listDauMB);
        
        mod.addObject("listLotteryMT", listLotteryMT);
        mod.addObject("listDuoiMT", listDuoiMT);
        mod.addObject("numSizeMT", numSizeMT);
        
        mod.addObject("listLotteryMN", listLotteryMN);
        mod.addObject("listDuoiMN", listDuoiMN);
        mod.addObject("numSizeMN", numSizeMN);
        
        mod.addObject("lottery", lottery);
        mod.addObject("listDuoi", listDuoi);
        mod.addObject("listDau", listDau);
        
        try{
            boolean isMobile = WapTool.isMobileDevice(request);
            String strMobile="pc";
            if(isMobile){
                strMobile="mobile";
            }
            mod.addObject("strMobile", strMobile);
        }catch(Exception e){}
        
        return mod;
    }
    
}
