/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.bean.CauLoto;
import com.cau.CauLotoResult;
import com.utils.DateProc;
import com.xshuy.DateProcExt;

import inet.util.DateUtil;

/**
 *
 * @author hanhlm
 */
public class CauLotoLoaiController extends BaseController {

    private static String sRegion = "";
    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public CauLotoLoaiController() {
    }

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

        String currentDate = DateUtil.date2String(new Date(), "dd/MM/yyyy");
        String tungay = request.getParameter("tungay");
        if (tungay == null || "".equals(tungay)) {
            tungay = today;
        } else {
            Date fromDate = DateUtil.toDate(tungay, "dd/MM/yyyy");

            if (fromDate.before(DateUtil.toDate("01/01/2005", "dd/MM/yyyy"))) {
                tungay = "01/01/2005";
            }
            
            if (fromDate.after(DateUtil.toDate(currentDate, "dd/MM/yyyy"))) {
                tungay = "01/01/2005";
            }
            
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        }

        String denngay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(tungay), -60);

        tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));

        String cau = request.getParameter("cau");
        if (cau == null || "".equals(cau)) {
            cau = "4";
        }

        int iCau = Integer.parseInt(cau);

        if(iCau < 1 || iCau > 10) {
            cau = "4";
            iCau = 4;
        }
        
        String region = request.getParameter("region");
        if (region == null || "".equals(region)) {
            region = "MB";
        }

        String code = request.getParameter("code");
        if (code == null || "".equals(code)) {
            if ("MB".equals(region)) {
                code = "XSTD";
            } else if ("MT".equals(region)) {
                code = "XSBDH";
            } else if ("MN".equals(region)) {
                code = "XSAG";
            }
        } else if (!region.equals(sRegion)) {
            if ("MB".equals(region)) {
                code = "XSTD";
            } else if ("MT".equals(region)) {
                code = "XSBDH";
            } else if ("MN".equals(region)) {
                code = "XSAG";
            }
        }

        String key = tungay + denngay + cau;

        List<CauLoto> listCauLoto = null;

        if (hMap.containsKey(key)) {
            listCauLoto = (List<CauLoto>) hMap.get(key);
        } else {
            CauLotoResult cauLotoResult = new CauLotoResult();
            listCauLoto = cauLotoResult.findCauLotoLoai(code, denngay, tungay, iCau, true);
            if (listCauLoto != null && !listCauLoto.isEmpty()) {
                listCauLoto = fillterCauLoto(listCauLoto);
                hMap.put(key, listCauLoto);
            }
        }

        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("listCauLoto", listCauLoto);
        mod.addObject("cau", cau);
        mod.addObject("icau", iCau);
        mod.addObject("code", code);
        mod.addObject("region", region);
        mod.addObject("title", "THỐNG KÊ CẦU LOTO KHÔNG VỀ");
        return mod;
    }

    private List<CauLoto> initCauLoto() {
        List<CauLoto> list = null;
        CauLoto cauLoto = null;
        for (int i = 0; i < 100; i++) {
            cauLoto = new CauLoto();
            cauLoto.setCau(0);
            cauLoto.setVitri1(0);
            cauLoto.setVitri2(0);
            if (i < 10) {
                cauLoto.setLoto("0" + i);
            } else {
                cauLoto.setLoto("" + i);
            }
            if (list == null) {
                list = new ArrayList<CauLoto>();
            }
            list.add(cauLoto);
        }

        return list;
    }

    private List<CauLoto> fillterCauLoto(List<CauLoto> listCauLoto) {
        List<CauLoto> result = initCauLoto();
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < listCauLoto.size(); j++) {
                if (result.get(i).getLoto().equals(listCauLoto.get(j).getLoto())) {
                    result.set(i, listCauLoto.get(j));
                    break;
                }
            }
        }

        return result;
    }
}
