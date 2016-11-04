/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.controller;

import com.controller.BaseController;
import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.bus.LotoTheoDacBietBus;
import com.soicaupro.thongkebacnho.bus.ScreenBachthuThreeDayBus;
import com.soicaupro.thongkebacnho.domain.LotoDacBietBachThuDTO;
import com.soicaupro.thongkebacnho.domain.ScreenBachthuSpecialDTO;
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
public class Loto3NgayDB_BachThuController extends BaseController{
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
	String typeSub = request.getParameter("type_sub");
	List<ScreenBachthuSpecialDTO> results = null;
        List<SpecialAwardDTO> awards = null;

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

	if (!CommonUtil.isEmptyString(special)) {
            	
            	LotoDacBietBachThuDTO lotoBachthuDTO = new LotoTheoDacBietBus().
            			getLotoDacBietBachThu3Ngay("01-01-2005", date, numberDate, special);
            	
                awards = lotoBachthuDTO.getAwards();
                results = lotoBachthuDTO.getResults();
                awards = new ScreenBachthuThreeDayBus().getListAward(awards, results);
            }
       
        mod.addObject("dacBiet", special);
        mod.addObject("date", date);
        mod.addObject("soNgay", numberDate);
        mod.addObject("type_sub", typeSub);
        mod.addObject("awards", awards);
        mod.addObject("results", results);
        mod.setViewName("thongkebacnho/loto_bachthu_db_ve_trong_3_ngay");
        
        return mod;
    }
}
