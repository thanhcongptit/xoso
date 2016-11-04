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
public class LotoTheoGiai_3Ngay_RS_Controller extends BaseController{
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
        
        String datetime = request.getParameter("datetime");
        String date_begin = request.getParameter("date_begin");
        String date_end = request.getParameter("date_end");
        String miss = request.getParameter("miss");

        mod.addObject("datetime", datetime);
        mod.addObject("date_begin", date_begin);
        mod.addObject("date_end", date_end);
        mod.addObject("miss", miss);

        mod.setViewName("thongkebacnho/loto_theogiai_3ngay");
        
        return mod;
    }
}
