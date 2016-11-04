/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.controller;

import com.controller.BaseController;
import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.bus.LotoTheoDacBietBus;
import com.soicaupro.thongkebacnho.domain.LotoDacBiet2NgayDTO;
import com.soicaupro.thongkebacnho.domain.ScreenTwoDaySpecialDTO;
import com.soicaupro.thongkebacnho.domain.SpecialAwardDTO;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author conglt
 */
public class DauCamLotoRSController extends BaseController{
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
        
        String special = request.getParameter("dacBiet");
	String date = request.getParameter("date");
	String numberDate = request.getParameter("soNgay");
	String typeSub = request.getParameter("typeSub");

	if (CommonUtil.isEmptyString(date)) {
		date = CommonUtil.convertDateToString1(new Date());
	}

	if (CommonUtil.isEmptyString(numberDate)) {
		numberDate = "45";
	} else {
		int number = 0;
		try {
			number = Integer.parseInt(numberDate);
		} catch (Exception e) {
			numberDate = "50";
		}
		
		if (number > 50 || number <= 0) {
			numberDate = "50";
		}
	}
       
        mod.addObject("dacBiet", special);
        mod.addObject("date", date);
        mod.addObject("soNgay", numberDate);
        mod.addObject("typeSub", typeSub);

        mod.setViewName("thongkebacnho/loto_ve_trong_3_ngay_daucam");
        
        return mod;
    }
}
