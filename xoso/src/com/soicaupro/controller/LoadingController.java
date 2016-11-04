/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.controller;

import com.controller.BaseController;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author conglt
 */
public class LoadingController extends BaseController{
     private static HashMap<String, Object> hMap = null;
    private static String sDDMMYYYY = null;

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
        
         String datetime1 = request.getParameter("datetime1");
         String date_begin = request.getParameter("date_begin");
         String date_end = request.getParameter("date_end");
         String miss = request.getParameter("miss");
         
         System.out.println("loading..." + datetime1);
         mod.addObject("datetime1", datetime1);
         mod.addObject("date_begin", date_begin);
         mod.addObject("date_end", date_end);
         mod.addObject("miss", miss);
         
        mod.setViewName("/thongkebacnho/loading");
        return mod;
    }
}
