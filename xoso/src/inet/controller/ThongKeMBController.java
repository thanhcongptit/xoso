/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.CapSo;
import inet.request.ThongKeRequest;
import inet.util.DateProc;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class ThongKeMBController extends AbstractController {
    private static String sDDMMYYYY=null;
    private static HashMap<String,List<CapSo>> hListCapSo=null;
    public ThongKeMBController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=new ModelAndView("/ajax/thongke_mienbac");
        
        String ddmmyyyy=DateProc.getDateString(DateProc.createTimestamp());      
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            if(hListCapSo==null){hListCapSo=new HashMap<String, List<CapSo>>();}
            hListCapSo.clear();    
        }
        
        List<CapSo> listCapSo=null;
        String week=request.getParameter("week");
        String code=request.getParameter("code");
        if(week==null||"".equals(week)){week="5";}            
        if(code==null||"".equals(code)){code="XSTD";}
        String key=code+"_"+week;
        if(hListCapSo.containsKey(key)){
            listCapSo=hListCapSo.get(key);
        }else{
            ThongKeRequest thongKeRequest=new ThongKeRequest();
            listCapSo=thongKeRequest.parserThongKeLotoCapSo(code, week,"lan_xuat_hien"); 
            if(listCapSo!=null&&!listCapSo.isEmpty()){hListCapSo.put(key, listCapSo);} 
        }
        
        sDDMMYYYY=ddmmyyyy;
                
        mod.addObject("listCapSo", listCapSo);
        mod.addObject("week", week);
        mod.addObject("code", code);
        mod.addObject("title", "Thống kê kết quả Miền Bắc");
        return mod;
        
    }
    
}
