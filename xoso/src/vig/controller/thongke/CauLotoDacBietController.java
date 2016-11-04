/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vig.controller.thongke;

import com.bean.CauLoto;
import com.cau.CauLotoResult;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
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
public class CauLotoDacBietController extends BaseController {
    private static String sRegion="";
    private static String sDDMMYYYY=null;
    private static HashMap<String,Object> hMap=null;
    
    public CauLotoDacBietController() {
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
        
        String denngay=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(tungay),-30);
        
        tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        denngay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
        
        String cau=request.getParameter("cau");
        if(cau==null||"".equals(cau)){cau="4";}
        
        int iCau=Integer.parseInt(cau);
        
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
        
        String cham=request.getParameter("cham");
        if(cham==null||"".equals(cham)){cham="0";}
        
        String key=tungay+denngay+cau+code+cham+region;        
        
        List<CauLoto> listCauLoto=null;
        
        if(hMap.containsKey(key)){
            listCauLoto=(List<CauLoto>)hMap.get(key);
        }else{
            CauLotoResult cauLotoResult=new CauLotoResult();
            if("0".equals(cham)){
                listCauLoto=cauLotoResult.findCauLotoDacbiet(code, denngay, tungay, iCau, true);
            }else{
                listCauLoto=cauLotoResult.findCauLotoDacbiet(code, denngay, tungay, iCau, false);
            }
            
            if(listCauLoto!=null&&!listCauLoto.isEmpty()){
                listCauLoto=fillterCauLoto(listCauLoto);
                hMap.put(key, listCauLoto);
            }
        }
        
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("listCauLoto", listCauLoto);
        mod.addObject("cau", cau);
        mod.addObject("icau", iCau);
        mod.addObject("code", code);
        mod.addObject("cham", cham);
        mod.addObject("region", region);
        return mod;
    }
    
    
    private List<CauLoto> initCauLoto(){
        List<CauLoto> list=null;
        CauLoto cauLoto=null;
        for (int i = 0; i < 100; i++) {
            cauLoto=new CauLoto();
            cauLoto.setCau(0);
            cauLoto.setVitri1(0);
            cauLoto.setVitri2(0);
            if(i<10){cauLoto.setLoto("0"+i);}
            else{cauLoto.setLoto(""+i);}
            if(list==null){list=new ArrayList<CauLoto>();}
            list.add(cauLoto);
        }
        
        return list;
    }
    
    private List<CauLoto> fillterCauLoto(List<CauLoto> listCauLoto){
        List<CauLoto> result=initCauLoto();        
        for (int i=0;i<result.size();i++) {
            for(int j=0;j<listCauLoto.size();j++){
                if(result.get(i).getLoto().equals(listCauLoto.get(j).getLoto())){
                    result.set(i, listCauLoto.get(j));
                    break;
                }
            }
        }
        
        return result;
    }
}
