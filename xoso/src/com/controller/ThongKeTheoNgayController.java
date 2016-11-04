/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.Loto;
import static com.controller.BaseController.today;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.utils.LotoUtils;
import inet.util.RequestUtil;
import inet.util.Today;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
public class ThongKeTheoNgayController extends BaseController {

    private static HashMap<String, Object> hMapRaNhieu = null;
    private static HashMap<String, Object> hMapRaIt = null;

    public ThongKeTheoNgayController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase();

        if (hMapRaNhieu == null) {
            hMapRaNhieu = new HashMap<>();
        }
        if(hMapRaIt == null){
            hMapRaIt = new HashMap<>();
        }
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        Today now = new Today();
        String thu = now.getDayOfWeek();
        String ngay = now.getDay() >= 10 ? "" + now.getDay() : "0" + now.getDay();
        String thang = now.getMonth() >= 10 ? "" + now.getMonth() : "0" + now.getMonth();
        String nam = "" + now.getYear();
        int week = RequestUtil.getInt(request, "week", 4);
        String code = RequestUtil.getString(request, "company", "XSTD");
        int songay = week * 7;
        String tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -songay)));
        String denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(today));
        String action = RequestUtil.getString(request, "action", null);
        
        //Nhieu
        List<Loto> ranhieuLotoList = null;
        //It
        List<Loto> raItLotoList = null;
        if ("view".equals(action)) {
            String key = ngay + thang + nam + code + week;
            if (hMapRaNhieu.containsKey(key) && hMapRaIt.containsKey(key)) {
                ranhieuLotoList = (List<Loto>) hMapRaNhieu.get(key);
                raItLotoList = (List<Loto>) hMapRaIt.get(key);
            } else {
                ThongKeLoto thongKeLoto = new ThongKeLoto();
                String danlotoTK = LotoUtils.getAllLoto(",");
                List<Loto> listLoto = thongKeLoto.findLotoTheoThu(danlotoTK, thu, code, tungay, denngay);
                //Nhieu
                ranhieuLotoList = new ArrayList<>();
                //It
                raItLotoList = new ArrayList<>();
                if (listLoto != null && listLoto.size() > 0) {
                    Loto[] arrayMaxLoto = listLoto.toArray(new Loto[listLoto.size()]);
                    arrayMaxLoto = bubbleSort(arrayMaxLoto, listLoto.size(), false);
                    for (int i = 0; i < 27; i++) {
                        ranhieuLotoList.add(arrayMaxLoto[i]);
                    }

                    Loto[] arrayMinLoto = listLoto.toArray(new Loto[listLoto.size()]);
                    arrayMinLoto = bubbleSort(arrayMinLoto, listLoto.size(), true);
                    for (int i = 0; i < 10; i++) {
                        raItLotoList.add(arrayMinLoto[i]);
                    }
                    hMapRaNhieu.put(key, ranhieuLotoList);
                    hMapRaIt.put(key, raItLotoList);
                }
            }
        }

        mod.addObject("thu", thu);
        mod.addObject("ngay", ngay);
        mod.addObject("thang", thang);
        mod.addObject("nam", nam);
        mod.addObject("week", week);
        mod.addObject("code", code);
        mod.addObject("nhieuList", ranhieuLotoList);
        mod.addObject("itList", raItLotoList);
        mod.addObject("title", "Thống kê theo ngày của các bộ số");
        return mod;
    }

    private Loto[] bubbleSort(Loto arrLoto[], int size, boolean isAsc) {
        Loto tmp = null;
        for (int i = 0; i < size - 1; i++) {
            for (int j = size - 1; j > i; j--) {
                if (isAsc && (arrLoto[j].getSolanxuathien() < arrLoto[j - 1].getSolanxuathien())) {
                    tmp = arrLoto[j - 1];
                    arrLoto[j - 1] = arrLoto[j];
                    arrLoto[j] = tmp;
                } else if (!isAsc && (arrLoto[j].getSolanxuathien() > arrLoto[j - 1].getSolanxuathien())) {
                    tmp = arrLoto[j - 1];
                    arrLoto[j - 1] = arrLoto[j];
                    arrLoto[j] = tmp;
                }
            }
        }
        return arrLoto;
    }
}
