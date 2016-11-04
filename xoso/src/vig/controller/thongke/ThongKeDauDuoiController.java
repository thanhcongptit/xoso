/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vig.controller.thongke;

import com.bean.DauDuoi;
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
public class ThongKeDauDuoiController extends BaseController {
    
    private static String sDDMMYYYY=null;
    private static HashMap<String,Object> hMap=null;
    
    public ThongKeDauDuoiController() {
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
        if(tungay==null||"".equals(tungay)){tungay=today;}                        
                
        String biendo=request.getParameter("biendo");
        if(biendo==null||"".equals(biendo)){biendo="20";}  
        
        String code=request.getParameter("code");
        if(code==null||"".equals(code)){code="XSTD";}
        
        String denngay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(tungay),-(Integer.parseInt(biendo)))));
        tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));        
        
        String key=tungay+denngay+code;
        
        List<List<DauDuoi>> listDau=null;
        List<List<DauDuoi>> listDuoi=null;
        
        if(hMap.containsKey(key+"dau")){
            listDau=(List<List<DauDuoi>>)hMap.get(key+"dau");
        }else{
            ThongKeLoto thongKeLoto=new ThongKeLoto();            
            listDau=thongKeLoto.findDauDuoi(code, denngay, tungay, true);
            if(listDau!=null&&!listDau.isEmpty()){hMap.put(key+"dau", listDau);}
        }
        
        if(hMap.containsKey(key+"duoi")){
            listDuoi=(List<List<DauDuoi>>)hMap.get(key+"duoi");
        }else{
            ThongKeLoto thongKeLoto=new ThongKeLoto();            
            listDuoi=thongKeLoto.findDauDuoi(code, denngay, tungay, false);
            if(listDau!=null&&!listDau.isEmpty()){hMap.put(key+"duoi", listDuoi);}
        }
        
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("biendo", biendo);
        mod.addObject("listDau", listDau);
        mod.addObject("listDuoi", listDuoi);
        mod.addObject("code", code);
        
        return mod;
    }
    
    
    public static void main(String[] args) {
        String b=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp("31/12/2015"),-(Integer.parseInt("20")));
        System.out.println("bb=="+b);
        String a =DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp("31/12/2015")); 
        System.out.println(a);
    }
}
