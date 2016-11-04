/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vig.controller.thongke;

import com.bean.Loto;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class ThongKeLotoGanController extends BaseController {
    
    private static String sDDMMYYYY=null;
    private static HashMap<String,Object> hMap=null;
    
    public ThongKeLotoGanController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hMap==null){hMap=new HashMap<String, Object>();}
        if(!today.equals(sDDMMYYYY)){hMap.clear();}
        sDDMMYYYY=today;
    }
    
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String tungay=request.getParameter("tungay");
        if(tungay==null||"".equals(tungay)){tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today),-365)));}
        else{tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));}
        
        String denngay=request.getParameter("denngay");
        if(denngay==null||"".equals(denngay)){denngay=today;}
        denngay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
        
        String biendo=request.getParameter("biendo");
        if(biendo==null||"".equals(biendo)){biendo="10";}
        
        String key=tungay+denngay;
        
        List<Loto> listLoto=null;
        if(hMap.containsKey(key)){
            listLoto=(List<Loto>)hMap.get(key);
        }else{
            ThongKeLoto thongKeLoto=new ThongKeLoto();
            listLoto=thongKeLoto.findLoto("XSTD", tungay, denngay);
            if(listLoto!=null&&!listLoto.isEmpty()){hMap.put(key, listLoto);}
        }
        
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay",DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("biendo", biendo);
        mod.addObject("listLoto", listLoto);
        
        return mod;
                
    }
    
    
}
