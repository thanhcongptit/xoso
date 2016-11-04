/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.utils.LotoUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HanhDung
 */
public class GhepXienController extends BaseController {
    
    public GhepXienController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String danloto=request.getParameter("danloto");
        String opt=request.getParameter("opt");
        if(danloto!=null&&!"".equals(danloto)){
            List<String> list=LotoUtils.ghepXien(danloto, Integer.parseInt(opt));
            mod.addObject("list", list);
            mod.addObject("danloto", danloto);
            mod.addObject("opt", opt);
            mod.addObject("title", "GHÉP LÔ XIÊN 2, XIÊN 3, XIÊN 4 MỘT CÁCH TỰ ĐỘNG");
        }
        
        return mod;
    }
        
}