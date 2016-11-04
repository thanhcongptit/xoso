/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.controller;

import com.controller.BaseController;
import com.soicaupro.thongkebacnho.CommonUtil;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author conglt
 */
public class LotoNhieuNhay_Bachthu_RS_Controller extends BaseController{
    private static String sDDMMYYYY=null;
    private static HashMap<String,Object> hMap=null;
    
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
        
        String indexLoto = request.getParameter("indexLoto");
        String date = request.getParameter("date");
        String numberDate = request.getParameter("soNgay");
        String soNhay = request.getParameter("soNhay");

        mod.addObject("indexLoto", indexLoto);
        mod.addObject("date", date);
        mod.addObject("soNgay", numberDate);
        mod.addObject("soNhay", soNhay);

        mod.setViewName("thongkebacnho/loto_nhieu_nhay_bachthu_ve_trong_5_ngay");
        
        return mod;
    }
}
