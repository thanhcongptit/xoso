/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HanhDung
 */
public class LotoCapVeTrongNgayController extends BaseController {
    
    private static HashMap<String,Object> hMap=null;
    private static String sDDMMYYYY=null;
    
    public LotoCapVeTrongNgayController() {
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
        mod.setViewName("thongkebacnho/loto_cap_db_ve_trong_1_ngay");
        return mod;
    }
        
}