/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.Loto;
import static com.controller.BaseController.today;
import com.thongke.ThongKeDacBiet;
import com.utils.DateProc;
import com.utils.StringConvert;
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
 * @author HanhDung
 */
public class TongHopChuKyDacBietController extends BaseController {

    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public TongHopChuKyDacBietController() {
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

            if (fromDate.before(DateUtil.toDate("01/07/2009", "dd/MM/yyyy"))) {
                tungay = currentDate;
            }
            
            if (fromDate.after(DateUtil.toDate(currentDate, "dd/MM/yyyy"))) {
                tungay = currentDate;
            }
            
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        }

        String denngay = "2009-07-01";
        tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));

        String code = request.getParameter("code");
        if (code == null || "".equals(code)) {
            code = "XSTD";
        }

        String key = tungay + code;
        List<Loto> listDau = null;
        List<Loto> listDuoi = null;
        List<Loto> listTong = null;
        ThongKeDacBiet thongKeDacBiet = new ThongKeDacBiet();
        if (hMap.containsKey(key + "dau")) {
            listDau = (List<Loto>) hMap.get(key + "dau");
        } else {
            listDau = thongKeDacBiet.findLotoDacbiet(code, denngay, tungay, 0);
            if (listDau != null && !listDau.isEmpty()) {
                hMap.put(key + "dau", listDau);
            }
        }

        if (hMap.containsKey(key + "duoi")) {
            listDuoi = (List<Loto>) hMap.get(key + "duoi");
        } else {
            listDuoi = thongKeDacBiet.findLotoDacbiet(code, denngay, tungay, 1);
            if (listDuoi != null && !listDuoi.isEmpty()) {
                hMap.put(key + "duoi", listDuoi);
            }
        }

        if (hMap.containsKey(key + "tong")) {
            listTong = (List<Loto>) hMap.get(key + "tong");
        } else {
            listTong = thongKeDacBiet.findLotoDacbiet(code, denngay, tungay, 2);
            if (listTong != null && !listTong.isEmpty()) {
                hMap.put(key + "tong", listTong);
            }
        }

        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("listDau", listDau);
        mod.addObject("listDuoi", listDuoi);
        mod.addObject("listTong", listTong);
        mod.addObject("code", code);
        mod.addObject("title", "TỔNG HỢP CHU KÌ ĐẶC BIỆT");
        return mod;

    }
}
