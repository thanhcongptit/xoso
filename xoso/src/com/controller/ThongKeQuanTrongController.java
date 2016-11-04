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
import java.util.HashMap;
import inet.util.RequestUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
public class ThongKeQuanTrongController extends BaseController {

    private static HashMap<String, Object> hMap = null;

    public ThongKeQuanTrongController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase();
        if (hMap == null) {
            hMap = new HashMap<>();
        }
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        int statiscType = RequestUtil.getInt(request, "statiscType", 1);
        String code = RequestUtil.getString(request, "company", "XSTD");
        String tungay = request.getParameter("tungay");
        if (tungay == null || "".equals(tungay)) {
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -30)));
        } else {
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        }
        String denngay = request.getParameter("denngay");
        if (denngay == null || "".equals(denngay)) {
            denngay = today;
        }
        denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
        String action = RequestUtil.getString(request, "action", null);
        List<Loto> listLoto = null;
        List<Loto> ranhieuLotoList = null;
        List<Loto> khong10ngayList = null;

        if ("view".equals(action)) {
            String key = tungay + denngay + code + statiscType;
            if (hMap.containsKey(key)) {
                listLoto = (List<Loto>) hMap.get(key);
            } else {
                ThongKeLoto thongKeLoto = new ThongKeLoto();
                String danlotoTK = LotoUtils.getAllLoto(",");
                listLoto = thongKeLoto.findLoto(danlotoTK, code, tungay, denngay);
                ranhieuLotoList = new ArrayList<>();
                khong10ngayList = new ArrayList<>();

                if (listLoto != null && listLoto.size() > 0) {
                    if (statiscType == 1) {
                        Loto[] arrayMaxLoto = listLoto.toArray(new Loto[listLoto.size()]);
                        arrayMaxLoto = bubbleSort(arrayMaxLoto, listLoto.size(), false);
                        for (int i = 0; i < 27; i++) {
                            ranhieuLotoList.add(arrayMaxLoto[i]);
                        }
                        listLoto = ranhieuLotoList;
                    } else if (statiscType == 2) {
                        Loto[] array10Ngay = listLoto.toArray(new Loto[listLoto.size()]);
                        array10Ngay = sortNgayKhongVe(array10Ngay, listLoto.size());
                        for (int i = 0; i < 10; i++) {
                            khong10ngayList.add(array10Ngay[i]);
                        }
                        listLoto = khong10ngayList;
                    } else if (statiscType == 3) {
                        Loto[] array10Ngay = listLoto.toArray(new Loto[listLoto.size()]);
                        array10Ngay = sortNgayKhongVe(array10Ngay, listLoto.size());
                        for (int i = 0; i < 10; i++) {
                            khong10ngayList.add(array10Ngay[i]);
                        }
                        listLoto = khong10ngayList;
                    }
                }
            }
        }

        mod.addObject("statiscType", statiscType);
        mod.addObject("listLoto", listLoto);
        mod.addObject("code", code);
        mod.addObject("title", "THỐNG KÊ QUAN TRỌNG");
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

    private Loto[] sortNgayKhongVe(Loto arrLoto[], int size) {
        Loto tmp = null;
        for (int i = 0; i < size - 1; i++) {
            for (int j = size - 1; j > i; j--) {
                if (arrLoto[j].getNgaychuave() > arrLoto[j - 1].getNgaychuave()) {
                    tmp = arrLoto[j - 1];
                    arrLoto[j - 1] = arrLoto[j];
                    arrLoto[j] = tmp;
                }
            }
        }
        return arrLoto;
    }
}
