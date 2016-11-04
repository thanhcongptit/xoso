/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.Loto;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.utils.LotoUtils;
import inet.util.RequestUtil;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
public class ThongKeTanSuatBoSoController extends BaseController {
    private static HashMap<String, Object> hMap = null;
    
    public ThongKeTanSuatBoSoController() {}

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
        String code = RequestUtil.getString(request, "company", "XSTD");
        int timeSelect = RequestUtil.getInt(request, "timeSelect", 500);
        String tungay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -timeSelect)));
        String denngay = DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(today));
        int type = RequestUtil.getInt(request, "type", 0);
        String action = RequestUtil.getString(request, "action", null);
        
        List<Loto> listLoto = null;
        if("view".equals(action)){
            String key = tungay + denngay + code + timeSelect + type;
            if (hMap.containsKey(key)) {
                listLoto = (List<Loto>) hMap.get(key);
            } else {
                ThongKeLoto thongKeLoto = new ThongKeLoto();
                System.out.println("code = " + code);
                System.out.println("tungay = " + tungay);
                System.out.println("denngay = " + denngay);
                
                listLoto = thongKeLoto.findLoto(code, tungay, denngay);                           
                if (listLoto != null && !listLoto.isEmpty()) {
                    hMap.put(key, listLoto);
                }
            }
        }
        mod.addObject("code", code);
        mod.addObject("timeSelect", timeSelect);
        mod.addObject("type", type);
        mod.addObject("listLoto", listLoto);
        mod.addObject("Thống kê tần suất của các bộ số");
        return mod;
    }
}
