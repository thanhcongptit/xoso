/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.Loto;
import com.bean.TanSuat;
import static com.controller.BaseController.today;
import com.thongke.BangTanSuat;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import inet.util.DateUtil;
import java.util.ArrayList;
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
public class TanSuatLotoController extends BaseController {

    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public TanSuatLotoController() {
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

        String[] capso = request.getParameterValues("capso");

        String biendo = request.getParameter("biendo");
        if (biendo == null || "".equals(biendo)) {
            biendo = "30";
        }
        
        int bien = Integer.parseInt(biendo);
        if(bien < 0 || bien > 100) {
            biendo = "30";
        }

        String optView = request.getParameter("optView");
        if (optView == null || "".equals(optView)) {
            optView = "0";
        }

        String code = request.getParameter("code");
        if (code == null || "".equals(code)) {
            code = "XSTD";
        }

        String denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(tungay), -(Integer.parseInt(biendo)))));

        tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));

        String key = tungay + denngay + code;

        List<Loto> listLoto = null;
        List<List<TanSuat>> listTansuat = null;

        if (hMap.containsKey(key + "tansuat")) {
            listTansuat = (List<List<TanSuat>>) hMap.get(key + "tansuat");
        } else {
            BangTanSuat bangTanSuat = new BangTanSuat();
            listTansuat = bangTanSuat.findTanSuat(code, denngay, tungay);
            if (listTansuat != null && !listTansuat.isEmpty()) {
                hMap.put(key + "tansuat", listTansuat);
            }
        }

        if (hMap.containsKey(key + "loto")) {
            listLoto = (List<Loto>) hMap.get(key + "loto");
        } else {
            ThongKeLoto thongKeLoto = new ThongKeLoto();
            listLoto = thongKeLoto.findLoto(code, denngay, tungay);
            if (listLoto != null && !listLoto.isEmpty()) {
                hMap.put(key + "loto", listLoto);
            }
        }
        
        List<List<TanSuat>> listTansuatResult = null;
        List<Loto> listLotoResult = null;
        if (capso != null && capso.length < 100) {
            listTansuatResult = fillterOfCapso(listTansuat, capso);
            listLotoResult = fillterLoto(listLoto, capso);
        } else {
            listTansuatResult = listTansuat;
            listLotoResult = listLoto;
        }

        mod.addObject("optView", optView);
        mod.addObject("biendo", biendo);
        mod.addObject("capso", capso);
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("listLoto", listLoto);
        mod.addObject("listTansuat", listTansuat);
        mod.addObject("code", code);
        return mod;
    }

    private List<List<TanSuat>> fillterOfCapso(List<List<TanSuat>> list, String[] capso) {
        List<List<TanSuat>> result = new ArrayList<List<TanSuat>>();
        List<TanSuat> listTanSuat = null;
        List<TanSuat> listTanSuatResult = null;
        TanSuat tanSuat = null;
        for (int i = 0; i < list.size(); i++) {
            listTanSuat = list.get(i);
            listTanSuatResult = new ArrayList<TanSuat>();
            for (int j = 0; j < listTanSuat.size(); j++) {
                tanSuat = listTanSuat.get(j);
                for (int k = 0; k < capso.length; k++) {
                    if (!tanSuat.getCapso().equals(capso[k])) {
                        listTanSuatResult.add(null);
                    } else {
                        listTanSuatResult.add(tanSuat);
                    }
                }
            }
            result.add(listTanSuatResult);
        }

        return result;
    }

    private List<Loto> fillterLoto(List<Loto> list, String[] capso) {
        List<Loto> result = new ArrayList<Loto>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < capso.length; j++) {
                if (list.get(i).getLoto().equals(capso[j])) {
                    result.add(list.get(i));
                }
            }
        }

        return result;
    }

}
