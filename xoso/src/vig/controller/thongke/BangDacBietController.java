/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vig.controller.thongke;

import com.bean.DacBiet;
import com.thongke.ThongKeDacBiet;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HanhDung
 */
public class BangDacBietController extends BaseController {
    private static String sDDMMYYYY=null;
    private static HashMap<String,Object> hMap=null;
    public BangDacBietController() {
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
        if(tungay==null||"".equals(tungay)){tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today),-30)));}
        else{tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));}
        
        String denngay=request.getParameter("denngay");
        if(denngay==null||"".equals(denngay)){denngay=today;}
        denngay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
        
        String opt=request.getParameter("opt");
        if(opt==null||"".equals(opt)){opt="0";}
        
        String key=tungay+denngay;
        List<DacBiet> list=null;
        if(hMap.containsKey(key)){
            list=(List<DacBiet>)hMap.get(key);
        }else{
            ThongKeDacBiet thongKeDacBiet=new ThongKeDacBiet();
            list=thongKeDacBiet.findBangDacBiet("XSTD", tungay, denngay);
            if(list!=null&&!list.isEmpty()){hMap.put(key, list);}
        }
        
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay",DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("list", list);
        mod.addObject("opt", opt);
        return mod;
    }
}