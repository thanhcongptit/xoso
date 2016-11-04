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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
public class ThongKeNhanhController extends BaseController {
    private static HashMap<String, Object> hMap = null;
    
    public ThongKeNhanhController() {}

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
        String danloto = RequestUtil.getString(request, "danloto", "");
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
        if("view".equals(action)){
            String key = tungay + denngay + code + danloto + type;
            if (hMap.containsKey(key)) {
                listLoto = (List<Loto>) hMap.get(key);
            } else {
                ThongKeLoto thongKeLoto = new ThongKeLoto();
                String danlotoTK = danloto;
                if("".equals(danlotoTK)){
                    danlotoTK = LotoUtils.getAllLoto(",");
                }
                System.out.println("code = " + code);
                System.out.println("tungay = " + tungay);
                System.out.println("denngay = " + denngay);
                System.out.println("danlotoTK = " + danlotoTK);
                
                listLoto = thongKeLoto.findLoto(danlotoTK, code, tungay, denngay);                           
                if (listLoto != null && !listLoto.isEmpty()) {
                    hMap.put(key, listLoto);
                }
            }
        }
        
        mod.addObject("code", code);
        mod.addObject("type", type);
        mod.addObject("title", "THỐNG KÊ NHANH");
        mod.addObject("danloto", danloto);
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("listLoto", listLoto);
        return mod;
    }
}
