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
public class TanSuatLotoCapsoController extends BaseController {

    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public TanSuatLotoCapsoController() {
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
                tungay = currentDate;
            }
            
            if (fromDate.after(DateUtil.toDate(currentDate, "dd/MM/yyyy"))) {
                tungay = currentDate;
            }
            
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        }

        String[] capso = request.getParameterValues("capso");

        String biendo = request.getParameter("biendo");
        if (biendo == null || "".equals(biendo)) {
            biendo = "99";
        }
        
        int biendovalue = Integer.parseInt(biendo);
        if(biendovalue < 0 && biendovalue < 1000) {
            biendo = "99";
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
            listTansuat = bangTanSuat.findTanSuatTheoCap("XSTD", denngay, tungay);
            if (listTansuat != null && !listTansuat.isEmpty()) {
                hMap.put(key + "tansuat", listTansuat);
            }
        }

        if (hMap.containsKey(key + "loto")) {
            listLoto = (List<Loto>) hMap.get(key + "loto");
        } else {
            ThongKeLoto thongKeLoto = new ThongKeLoto();
            listLoto = thongKeLoto.findLotoCap("XSTD", denngay, tungay);
            if (listLoto != null && !listLoto.isEmpty()) {
                hMap.put(key + "loto", listLoto);
            }
        }

        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        mod.addObject("optView", optView);
        mod.addObject("biendo", biendo);
        mod.addObject("capso", capso);
        mod.addObject("code", code);
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("listTansuat", listTansuat);
        mod.addObject("listLoto", listLoto);
        mod.addObject("capLoto", capLoto);

        return mod;
    }

}
