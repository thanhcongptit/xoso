/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.LotoCap;
import com.bean.LotoOfDacBiet;
import com.thongke.ThongKeBacNho;
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
public class LotoBachThuVeTrong3NgayController extends BaseController {
    
    private static HashMap<String,Object> hMap=null;
    private static String sDDMMYYYY=null;
    
    public LotoBachThuVeTrong3NgayController() {
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
        if(tungay==null||"".equals(tungay)){tungay="20-01-2016";}
        tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        
        String trong=request.getParameter("trong");
        if(trong==null||"".equals(trong)){trong="45";}
        
        String special=request.getParameter("special");
        if(special==null||"".equals(special)){special="85";}
                
        
        String order=request.getParameter("order");
        if(order==null||"".equals(order)){order="1";}
        
        List<LotoOfDacBiet> listOfDacBiet=null;
        List<LotoCap> listLotoCap=null;
        
        String key=tungay+trong+special+order;
        if(hMap.containsKey(key)){
            listOfDacBiet=(List<LotoOfDacBiet>)hMap.get(key);
            listLotoCap=(List<LotoCap>)hMap.get(key+"capso");
        }else{
            ThongKeBacNho thongKeBacNho=new ThongKeBacNho();
            int maxlanve=Integer.parseInt(trong);
//            int step=Integer.parseInt(ganday);
            listOfDacBiet=thongKeBacNho.findLotoBachThuOfDacBiet(tungay, maxlanve, special);
            if(listOfDacBiet!=null&&!listOfDacBiet.isEmpty()){
                int iOrder=Integer.parseInt(order);
                listLotoCap=thongKeBacNho.countLotoBachThuCap(listOfDacBiet);
                listLotoCap=thongKeBacNho.sapxepBachThu(listLotoCap, iOrder);
                hMap.put(key, listOfDacBiet);
                hMap.put(key+"capso", listLotoCap);
            }
        }
        
        
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("trong", trong);
        mod.addObject("special", special);
        mod.addObject("order", order);
        mod.addObject("listOfDacBiet", listOfDacBiet);
        mod.addObject("listLotoCap", listLotoCap);
        
        return mod;
    }
        
}