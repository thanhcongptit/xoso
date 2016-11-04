/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.Loto;
import com.bean.LotteryResult;
import static com.controller.BaseController.today;
import com.dao.LotteryResultDAO;
import com.thongke.ThongKeLoto;
import com.utils.DateProc;
import com.utils.LotoUtils;
import com.utils.StringConvert;
import static com.utils.StringConvert.subRight;
import com.xshuy.DateProcExt;
import inet.util.RequestUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
public class ThongKeTheoTongController extends BaseController {

    private static HashMap<String, Object> hMap = null;
    private static HashMap<String, Object> hLantruocMap = null;

    public ThongKeTheoTongController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase();
        if (hMap == null) {
            hMap = new HashMap<>();
        }
        if (hLantruocMap == null) {
            hLantruocMap = new HashMap<>();
        }
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        int tong = RequestUtil.getInt(request, "tong", 0);
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
        List<Loto> listTong0Loto = new ArrayList<>();
        List<Loto> listTong1Loto = new ArrayList<>();
        List<Loto> listTong2Loto = new ArrayList<>();
        List<Loto> listTong3Loto = new ArrayList<>();
        List<Loto> listTong4Loto = new ArrayList<>();
        List<Loto> listTong5Loto = new ArrayList<>();
        List<Loto> listTong6Loto = new ArrayList<>();
        List<Loto> listTong7Loto = new ArrayList<>();
        List<Loto> listTong8Loto = new ArrayList<>();
        List<Loto> listTong9Loto = new ArrayList<>();
        if ("view".equals(action)) {
            //List loto
            String key = tungay + denngay + code + tong + type;
            if (hMap.containsKey(key)) {
                listLoto = (List<Loto>) hMap.get(key);
            } else {
                ThongKeLoto thongKeLoto = new ThongKeLoto();
                String danlotoTK = LotoUtils.getAllLotoByTong(tong, ",");
                listLoto = thongKeLoto.findLoto(danlotoTK, code, tungay, denngay);
                if (listLoto != null && !listLoto.isEmpty()) {
                    hMap.put(key, listLoto);
                }
            }
        }
        //List theo tong
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        LotteryResult latestLottery = lotteryResultDAO.findLatestLotteryResult(code);
        if (latestLottery != null) {
            String lotoResult = StringConvert.lotoResult(latestLottery);
            Loto tmpLoto = null;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                tmpLoto = new Loto();
                tmpLoto.setLoto(token);
                if (LotoUtils.isTong(token, 0)) {
                    listTong0Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 1)) {
                    listTong1Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 2)) {
                    listTong2Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 3)) {
                    listTong3Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 4)) {
                    listTong4Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 5)) {
                    listTong5Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 6)) {
                    listTong6Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 7)) {
                    listTong7Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 8)) {
                    listTong8Loto.add(tmpLoto);
                } else if (LotoUtils.isTong(token, 9)) {
                    listTong9Loto.add(tmpLoto);
                }
            }
        }

        mod.addObject("tong", tong);
        mod.addObject("code", code);
        mod.addObject("type", type);
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("denngay", DateProcExt.convertYMDtoMDY(denngay));
        mod.addObject("listLoto", listLoto);
        mod.addObject("listTong0Loto", listTong0Loto);
        mod.addObject("listTong1Loto", listTong1Loto);
        mod.addObject("listTong2Loto", listTong2Loto);
        mod.addObject("listTong3Loto", listTong3Loto);
        mod.addObject("listTong4Loto", listTong4Loto);
        mod.addObject("listTong5Loto", listTong5Loto);
        mod.addObject("listTong6Loto", listTong6Loto);
        mod.addObject("listTong7Loto", listTong7Loto);
        mod.addObject("listTong8Loto", listTong8Loto);
        mod.addObject("listTong9Loto", listTong9Loto);
        mod.addObject("title", "THỐNG KÊ THEO CÁC TỔNG");
        return mod;
    }
}
