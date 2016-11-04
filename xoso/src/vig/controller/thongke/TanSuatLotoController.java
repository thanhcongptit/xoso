/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vig.controller.thongke;

import com.bean.Loto;
import com.bean.TanSuat;
import com.thongke.BangTanSuat;
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
public class TanSuatLotoController extends BaseController {
    
    private static String sDDMMYYYY=null;
    private static HashMap<String,Object> hMap=null;
    
    public TanSuatLotoController() {
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
        
        String[] capso=request.getParameterValues("capso");
                
        String biendo=request.getParameter("biendo");
        if(biendo==null||"".equals(biendo)){biendo="99";}
        
        String optView=request.getParameter("optView");
        if(optView==null||"".equals(optView)){optView="0";}
        
        String code=request.getParameter("code");
        if(code==null||"".equals(code)){code="XSTD";}
        
        String denngay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(tungay),-(Integer.parseInt(biendo)))));
        
        tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        
        String key=tungay+denngay+code;
        
        List<Loto> listLoto=null;
        List<List<TanSuat>> listTansuat=null;
        
        if(hMap.containsKey(key+"tansuat")){            
            listTansuat=(List<List<TanSuat>>)hMap.get(key+"tansuat");
        }else{
            BangTanSuat bangTanSuat=new BangTanSuat();
            listTansuat=bangTanSuat.findTanSuat(code, denngay,tungay);
            if(listTansuat!=null&&!listTansuat.isEmpty()){hMap.put(key+"tansuat", listTansuat);}
        }
        
        if(hMap.containsKey(key+"loto")){            
            listLoto=(List<Loto>)hMap.get(key+"loto");
        }else{
            ThongKeLoto thongKeLoto=new ThongKeLoto();
            listLoto=thongKeLoto.findLoto(code, denngay, tungay);
            if(listLoto!=null&&!listLoto.isEmpty()){hMap.put(key+"loto", listLoto);}
        }
        
        
        mod.addObject("optView", optView);
        mod.addObject("biendo", biendo);
        mod.addObject("capso", capso);
        mod.addObject("tungay",DateProcExt.convertYMDtoMDY(tungay) );
        mod.addObject("listLoto", listLoto);
        mod.addObject("listTansuat", listTansuat);
        mod.addObject("code", code);
        return mod;
    }
    
}
