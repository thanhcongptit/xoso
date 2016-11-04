/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vig.controller.thongke;

import com.bean.CauLoto;
import com.bean.LichSuCau;
import com.cau.CauLotoResult;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class LichSuCauLotoController extends BaseController {
    private static String sRegion="";
    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public LichSuCauLotoController() {
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

        String denngay = request.getParameter("denngay");
        if (denngay == null || "".equals(denngay)) {
            denngay = today;
        }

        String tungay = request.getParameter("tungay");
        if (tungay == null || "".equals(tungay)) {
            tungay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(denngay), -365);
        }

        tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));

        String bachthu = request.getParameter("bachthu");
        if (bachthu == null || "".equals(bachthu)) {
            bachthu = "0";
        }

        String region=request.getParameter("region");
        if(region==null||"".equals(region)){region="MB";}
        
        String code=request.getParameter("code");
        if(code==null||"".equals(code)){
            if("MB".equals(region)){code="XSTD";}
            else if("MT".equals(region)){code="XSBDH";}
            else if("MN".equals(region)){code="XSAG";}
        }else{
            if(!region.equals(sRegion)){
                if("MB".equals(region)){code="XSTD";}
                else if("MT".equals(region)){code="XSBDH";}
                else if("MN".equals(region)){code="XSAG";}
            }
        }

        String vitri1 = request.getParameter("vitri1");
        String vitri2 = request.getParameter("vitri2");

        String key = tungay + denngay + bachthu + code + vitri1 + vitri2;

        List<CauLoto> listCauLoto = null;
        Collection<LichSuCau> vLichsucau=null;
        
        if (vitri1 != null && !"".equals(vitri1) && vitri2 != null && !"".equals(vitri2)) {
            if (hMap.containsKey(key)) {
                listCauLoto = (List<CauLoto>) hMap.get(key);
                vLichsucau=(Collection<LichSuCau>)hMap.get(key+"count");
            } else {
                CauLotoResult cauLotoResult = new CauLotoResult();
                if ("0".equals(bachthu)) {
                    listCauLoto = cauLotoResult.findLichSuCau(code, tungay, denngay, Integer.parseInt(vitri1), Integer.parseInt(vitri2), true);
                } else {
                    listCauLoto = cauLotoResult.findLichSuCau(code, tungay, denngay, Integer.parseInt(vitri1), Integer.parseInt(vitri2), false);
                }

                if (listCauLoto != null && !listCauLoto.isEmpty()) {
                    vLichsucau=countLichSuCau(listCauLoto);                    
                    hMap.put(key, listCauLoto);
                    hMap.put(key+"count", vLichsucau);
                }
            }
        }

        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("listCauLoto", listCauLoto);
        mod.addObject("vLichsucau", vLichsucau);
        mod.addObject("vitri1", vitri1);
        mod.addObject("vitri2", vitri2);
        mod.addObject("code", code);
        mod.addObject("region", region);
        mod.addObject("bachthu", bachthu);
        return mod;
    }
    
    
    private Collection<LichSuCau> countLichSuCau(List<CauLoto> list){
        HashMap<String,LichSuCau> hLichSuCau=new HashMap<String, LichSuCau>();
        LichSuCau lichSuCau=null;
        for(CauLoto cauLoto:list){
            if(hLichSuCau.containsKey(""+cauLoto.getCau())){
                lichSuCau=hLichSuCau.get(""+cauLoto.getCau());
                lichSuCau.setTongcau(lichSuCau.getTongcau()+1);
            }else{
                lichSuCau=new LichSuCau();
                lichSuCau.setDodai(""+cauLoto.getCau());
                lichSuCau.setTongcau(1);
                hLichSuCau.put(""+cauLoto.getCau(), lichSuCau);
            }
        }
        
        Collection<LichSuCau> result=hLichSuCau.values();
        
        return result;
    }
}
