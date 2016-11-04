/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.Loto;
import static com.controller.BaseController.today;
import com.thongke.ThongKeDacBiet;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.xshuy.DateProcExt;
import inet.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HanhDung
 */
public class ThongKeChuKyDanDacBietController extends BaseController {

    private static HashMap<String, Object> hMap = null;
    private static String sDDMMYYYY = null;

    public ThongKeChuKyDanDacBietController() {
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

        String danloto = request.getParameter("danloto");

        List<Loto> listLoto = null;
        if (danloto != null && !"".equals(danloto)) {
            String key = tungay + denngay + danloto;
            if (hMap.containsKey(key)) {
                listLoto = (List<Loto>) hMap.get(danloto);
            } else {
                ThongKeDacBiet thongKeDacBiet = new ThongKeDacBiet();
                listLoto = thongKeDacBiet.findLotoDanDacBiet("XSTD", tungay, denngay, danloto);
                if (listLoto != null && !listLoto.isEmpty()) {
                    hMap.put(key, listLoto);
                }

            }
        }

        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("danloto", danloto);
        mod.addObject("listLoto", listLoto);
        mod.addObject("title", "THỐNG KÊ CHU KÌ DÀN ĐẶC BIỆT");

        return mod;
    }
}
