/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vig.controller.thongke;

import com.bean.Loto;
import com.thongke.ThongKeDacBiet;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HanhDung
 */
public class ThongKeChuKyDanDacBietController extends BaseController {

    private static HashMap<String, Object> hMap = null;
    private static String sDDMMYYYY = null;

    public ThongKeChuKyDanDacBietController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if (hMap == null) {
            hMap = new HashMap<String, Object>();
        }
        if (!today.equals(sDDMMYYYY)) {
            hMap.clear();
        }
        sDDMMYYYY = today;
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        String tungay=request.getParameter("tungay");
        if(tungay==null||"".equals(tungay)){tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today),-365)));}
        else{tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));}
        
        
        
        String denngay=request.getParameter("denngay");
        if(denngay==null||"".equals(denngay)){denngay=today;}
        denngay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
        
        String danloto = request.getParameter("danloto");

                
        
        List<Loto> listLoto = null;
        if (danloto != null && !"".equals(danloto)) {
            String key=tungay+denngay+danloto;
            if (hMap.containsKey(key)) {
                listLoto = (List<Loto>) hMap.get(danloto);
            } else {
                ThongKeDacBiet thongKeDacBiet=new ThongKeDacBiet();
                listLoto = thongKeDacBiet.findLotoDanDacBiet("XSTD", tungay, denngay,danloto);                           
                if (listLoto != null && !listLoto.isEmpty()) {
                    hMap.put(key, listLoto);
                }
                
                
            }
        }
        
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay",DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("danloto", danloto);
        mod.addObject("listLoto", listLoto);

        return mod;
    }   
}
