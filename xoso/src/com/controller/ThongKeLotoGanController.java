/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.Loto;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import inet.util.DateUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class ThongKeLotoGanController extends BaseController {

    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public ThongKeLotoGanController() {
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
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -365)));
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

        String denngay = request.getParameter("denngay");
        if (denngay == null || "".equals(denngay)) {
            denngay = today;
        } else {
            Date fromDate = DateUtil.toDate(denngay, "dd/MM/yyyy");

            if (fromDate.after(DateUtil.toDate(currentDate, "dd/MM/yyyy"))) {
                denngay = currentDate;
            }
            
            if (fromDate.before(DateUtil.toDate("01/01/2005", "dd/MM/yyyy"))) {
                denngay = currentDate;
            }
        }
        denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));

        String biendo = request.getParameter("biendo");
        if (biendo == null || "".equals(biendo)) {
            biendo = "10";
        }
        
        int bien = Integer.parseInt(biendo);
        if(bien < 1 || bien > 1000) {
            biendo = "10";
        }

        String key = tungay + denngay;

        List<Loto> listLoto = null;
        if (hMap.containsKey(key)) {
            listLoto = (List<Loto>) hMap.get(key);
        } else {
            ThongKeLoto thongKeLoto = new ThongKeLoto();
            listLoto = thongKeLoto.findLoto("XSTD", tungay, denngay);
            if (listLoto != null && !listLoto.isEmpty()) {
                hMap.put(key, listLoto);
            }
        }

        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("biendo", biendo);
        mod.addObject("listLoto", listLoto);
        mod.addObject("title", "THỐNG KÊ LOTO GAN");

        return mod;

    }

}
