/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.CauLoto;
import com.bean.LichSuCau;
import com.cau.CauLotoResult;
import static com.controller.BaseController.today;
import com.soicaupro.thongkebacnho.CommonUtil;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import inet.util.DateUtil;
import java.util.ArrayList;
import java.util.Collection;
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
public class LichSuCauLotoController extends BaseController {

    private static String sRegion = "";
    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public LichSuCauLotoController() {
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

        String denngay = request.getParameter("denngay");
        if (denngay == null || "".equals(denngay)) {
            denngay = today;
        } else {
            Date fromDate = DateUtil.toDate(denngay, "dd/MM/yyyy");
            String currentDate = DateUtil.date2String(new Date(), "dd/MM/yyyy");

            if (fromDate.after(DateUtil.toDate(currentDate, "dd/MM/yyyy"))) {
                denngay = currentDate;
            }
        }

        String tungay = request.getParameter("tungay");
        if (tungay == null || "".equals(tungay)) {
            tungay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(denngay), -365);
        } else {
            Date fromDate = DateUtil.toDate(tungay, "dd/MM/yyyy");

            if (fromDate.before(DateUtil.toDate("01/01/2005", "dd/MM/yyyy"))) {
                tungay = "01/01/2005";
            }
        }

        tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));

        String bachthu = request.getParameter("bachthu");
        if (bachthu == null || "".equals(bachthu)) {
            bachthu = "0";
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

        String vitri1 = request.getParameter("vitri1");
        String vitri2 = request.getParameter("vitri2");

        String key = tungay + denngay + bachthu + code + vitri1 + vitri2;

        List<CauLoto> listCauLoto = null;
        Collection<LichSuCau> vLichsucau = null;

        if (vitri1 != null && !"".equals(vitri1) && vitri2 != null && !"".equals(vitri2)) {

            int vi1 = Integer.parseInt(vitri1);
            int vi2 = Integer.parseInt(vitri2);

            if (vi1 < 1) {
                vitri1 = "1";
            }

            if (vi1 > 30) {
                vitri1 = "27";
            }

            if (vi2 < 1) {
                vitri2 = "27";
            }

            if (vi2 > 30) {
                vitri2 = "27";
            }

            if (vi1 > vi2) {
                vitri1 = "1";
            }

            if (hMap.containsKey(key)) {
                listCauLoto = (List<CauLoto>) hMap.get(key);
                vLichsucau = (Collection<LichSuCau>) hMap.get(key + "count");
            } else {
                CauLotoResult cauLotoResult = new CauLotoResult();
                if ("0".equals(bachthu)) {
                    listCauLoto = cauLotoResult.findLichSuCau(code, tungay, denngay, Integer.parseInt(vitri1), Integer.parseInt(vitri2), true);
                } else {
                    listCauLoto = cauLotoResult.findLichSuCau(code, tungay, denngay, Integer.parseInt(vitri1), Integer.parseInt(vitri2), false);
                }

                if (listCauLoto != null && !listCauLoto.isEmpty()) {
                    vLichsucau = countLichSuCau(listCauLoto);
                    hMap.put(key, listCauLoto);
                    hMap.put(key + "count", vLichsucau);
                }
            }
        }

        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("listCauLoto", listCauLoto);
        mod.addObject("vLichsucau", vLichsucau);
        mod.addObject("vitri1", vitri1);
        mod.addObject("vitri2", vitri2);
        mod.addObject("code", code);
        mod.addObject("region", region);
        mod.addObject("bachthu", bachthu);
        mod.addObject("title", "KIỂM TRA LỊCH SỬ CẦU LOTO");
        return mod;
    }

    private Collection<LichSuCau> countLichSuCau(List<CauLoto> list) {
        HashMap<String, LichSuCau> hLichSuCau = new HashMap<String, LichSuCau>();
        LichSuCau lichSuCau = null;
        for (CauLoto cauLoto : list) {
            if (hLichSuCau.containsKey("" + cauLoto.getCau())) {
                lichSuCau = hLichSuCau.get("" + cauLoto.getCau());
                lichSuCau.setTongcau(lichSuCau.getTongcau() + 1);
            } else {
                lichSuCau = new LichSuCau();
                lichSuCau.setDodai("" + cauLoto.getCau());
                lichSuCau.setTongcau(1);
                hLichSuCau.put("" + cauLoto.getCau(), lichSuCau);
            }
        }

        Collection<LichSuCau> result = hLichSuCau.values();

        return result;
    }
}
