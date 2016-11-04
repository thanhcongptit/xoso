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
import com.xshuy.DateProcExt;
import inet.util.RequestUtil;
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
public class ThongKeTongHopController extends BaseController {

    private static HashMap<String, Object> hMap = null;

    public ThongKeTongHopController() {
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
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -365)));
        } else {
            tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
        }
        String denngay = request.getParameter("denngay");
        if (denngay == null || "".equals(denngay)) {
            denngay = today;
        }
        denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
        int type = RequestUtil.getInt(request, "type", 0);
        String action = RequestUtil.getString(request, "action", null);
        List<Loto> listLoto = null;

        if ("view".equals(action)) {
            String key = tungay + denngay + code + statiscType + type;
            if (hMap.containsKey(key)) {
                listLoto = (List<Loto>) hMap.get(key);
            } else {
                ThongKeLoto thongKeLoto = new ThongKeLoto();
                String danlotoTK = "";
                if (statiscType == 1) {
                    //Tổng chẵn
                    danlotoTK = LotoUtils.getAllLoto("TONGCHAN", ",");
                } else if (statiscType == 2) {
                    //Tổng lẻ
                    danlotoTK = LotoUtils.getAllLoto("TONGLE", ",");
                } else if (statiscType == 3) {
                    //Bộ chẵn chẵn
                    danlotoTK = LotoUtils.getAllLoto("BOCHANCHAN", ",");
                } else if (statiscType == 4) {
                    //Bộ lẻ lẻ
                    danlotoTK = LotoUtils.getAllLoto("BOLELE", ",");
                } else if (statiscType == 5) {
                    //Bộ chẵn lẻ
                    danlotoTK = LotoUtils.getAllLoto("BOCHANLE", ",");
                } else if (statiscType == 6) {
                    //Bộ lẻ chẵn
                    danlotoTK = LotoUtils.getAllLoto("BOLECHAN", ",");
                } else if (statiscType == 7) {
                    //Bộ kep
                    danlotoTK = LotoUtils.getAllLoto("BOKEP", ",");
                } else if (statiscType == 8) {
                    //Bộ sat kep
                    danlotoTK = LotoUtils.getAllLoto("BOSATKEP", ",");
                } else if (statiscType == 9) {
                    //Thong ke dau so
                    danlotoTK = LotoUtils.getAllLoto(",");
                } else if (statiscType == 10) {
                    //Thong ke dit so
                    danlotoTK = LotoUtils.getAllLoto(",");
                } else if (statiscType == 11) {
                    //15 so ve nhieu
                    danlotoTK = LotoUtils.getAllLoto(",");
                } else if (statiscType == 12) {
                    //15 so ve it
                    danlotoTK = LotoUtils.getAllLoto(",");
                }

                System.out.println("code = " + code);
                System.out.println("tungay = " + tungay);
                System.out.println("denngay = " + denngay);
                System.out.println("danlotoTK = " + danlotoTK);

                listLoto = thongKeLoto.findLoto(danlotoTK, code, tungay, denngay);
                if (statiscType == 9) {
                    //Thong ke dau so
                    danlotoTK = LotoUtils.getAllLoto(",");
                } else if (statiscType == 10) {
                    //Thong ke dit so
                    danlotoTK = LotoUtils.getAllLoto(",");
                } else if (statiscType == 11) {
                    //15 so ve nhieu
                    //Sap xep lai list va chi lay 15 gia tri dau
                    Loto [] arrayLoto = listLoto.toArray(new Loto[listLoto.size()]);
                    arrayLoto = bubbleSort(arrayLoto, listLoto.size(), false);
                    listLoto = new ArrayList<>();
                    for(int i = 0; i < 15; i++){
                        listLoto.add(arrayLoto[i]);
                    }
                } else if (statiscType == 12) {
                    //15 so ve it
                    //Sap xep lai list va chi lay 15 gia tri dau
                    Loto [] arrayLoto = listLoto.toArray(new Loto[listLoto.size()]);
                    arrayLoto = bubbleSort(arrayLoto, listLoto.size(), true);
                    listLoto = new ArrayList<>();
                    for(int i = 0; i < 15; i++){
                        listLoto.add(arrayLoto[i]);
                    }
                }
                if (listLoto != null && !listLoto.isEmpty()) {
                    hMap.put(key, listLoto);
                }
            }
        }

        mod.addObject("statiscType", statiscType);
        mod.addObject("code", code);
        mod.addObject("type", type);
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("listLoto", listLoto);
        mod.addObject("title", "Thống kê tổng hợp");
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
                } else if(!isAsc && (arrLoto[j].getSolanxuathien() > arrLoto[j - 1].getSolanxuathien())){
                    tmp = arrLoto[j - 1];
                    arrLoto[j - 1] = arrLoto[j];
                    arrLoto[j] = tmp;
                }
            }
        }
        return arrLoto;
    }
}
