/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.DacBiet;
import static com.controller.BaseController.today;
import com.thongke.ThongKeDacBiet;
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
 * @author HanhDung
 */
public class BangDacBietController extends BaseController {

    private static String sDDMMYYYY = null;
    private static HashMap<String, Object> hMap = null;

    public BangDacBietController() {
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

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        String tungay = request.getParameter("tungay");
        String currentDate = DateUtil.date2String(new Date(), "dd/MM/yyyy");

        if (tungay == null || "".equals(tungay)) {
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -30)));
        } else {
            Date fromDate = DateUtil.toDate(tungay, "dd/MM/yyyy");

            if (fromDate.before(DateUtil.toDate("01/01/2005", "dd/MM/yyyy"))) {
                tungay = "01/01/2015";
            }

            if (fromDate.after(DateUtil.toDate(currentDate, "dd/MM/yyyy"))) {
                tungay = "01/01/2015";
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

        String opt = request.getParameter("opt");
        if (opt == null || "".equals(opt)) {
            opt = "0";
        }

        String key = tungay + denngay;
        List<DacBiet> list = null;
        if (hMap.containsKey(key)) {
            list = (List<DacBiet>) hMap.get(key);
        } else {
            ThongKeDacBiet thongKeDacBiet = new ThongKeDacBiet();
            list = thongKeDacBiet.findBangDacBiet("XSTD", tungay, denngay);
            if (list != null && !list.isEmpty()) {
                hMap.put(key, list);
            }
        }
        DacBiet dacBiet = null;
        DacBiet dacBietOld = null;
        int ngayDB, ngayDBOld;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    dacBiet = list.get(i);
                    dacBietOld = list.get(i - 1);
                    ngayDB = Integer.parseInt(DateUtil.date2String(DateUtil.toDate(dacBiet.getNgayve(), "dd/MM/yyyy"), "yyyyMMdd"));
                    ngayDBOld = Integer.parseInt(DateUtil.date2String(DateUtil.toDate(dacBietOld.getNgayve(), "dd/MM/yyyy"), "yyyyMMdd"));
                    if ((ngayDB - ngayDBOld < 10) && (ngayDB - ngayDBOld > 1)) {
                        dacBietOld.setNgayTet(ngayDB - ngayDBOld - 1);
                    }
                }
            }
        }

        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("list", list);
        mod.addObject("opt", opt);
        mod.addObject("title", "THỐNG KÊ BẢNG ĐẶC BIỆT TUẦN XỔ SỐ MIỀN BẮC");
        return mod;
    }
}
