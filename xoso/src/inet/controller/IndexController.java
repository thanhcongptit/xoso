/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import com.bean.Loto;
import com.thongke.ThongKeLoto;
import com.utils.LotoUtils;
import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.request.LotteryRequest;
import inet.util.DateProc;
import inet.util.StringPro;
import inet.util.Today;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class IndexController extends BaseController {

    private static HashMap<String, List<Lottery>> hLotteryMB = null;
    private static HashMap<String, Lotterys> hLotterys = null;
    private static HashMap<String, List<String>> hLotteryDauDuoi = null;
    private static HashMap<String, String> hNumSize = null;
    private static HashMap<String, Object> hLotoMax30Days = null;

    private static String sDDMMYYYY = null;

    public IndexController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("index init");
        if (hLotteryMB == null) {
            hLotteryMB = new HashMap<String, List<Lottery>>();
        }
        if (hLotterys == null) {
            hLotterys = new HashMap<String, Lotterys>();
        }
        if (hLotteryDauDuoi == null) {
            hLotteryDauDuoi = new HashMap<String, List<String>>();
        }
        if (hNumSize == null) {
            hNumSize = new HashMap<String, String>();
        }
        if (hLotoMax30Days == null) {
            hLotoMax30Days = new HashMap<>();
        }
        if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
            hLotteryMB.clear();
            hLotterys.clear();
            hLotteryDauDuoi.clear();
            hNumSize.clear();
            hLotoMax30Days.clear();
            sDDMMYYYY = ddmmyyyy;
        }
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        System.out.println("index body");
        LotteryRequest lotteryRequest = new LotteryRequest();
        //lay ket qua xoso mien bac
        String day = request.getParameter("d");
        String dayMB = "";
        String dayMT = "";
        String dayMN = "";
        if (day == null || "".equals(day)) {
            Today today = new Today();
            if (today.getHour() < 18 || (today.getHour() == 18 && today.getMinute() <= 38)) {
                dayMB = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            } else if (today.getHour() > 18 || (today.getHour() == 18 && today.getMinute() > 38)) {
                dayMB = ddmmyyyy;
            }

            if (today.getHour() < 17 || (today.getHour() == 17 && today.getMinute() <= 38)) {
                dayMT = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            } else if (today.getHour() > 17 || (today.getHour() == 17 && today.getMinute() > 38)) {
                dayMT = ddmmyyyy;
            }

            if (today.getHour() < 16 || (today.getHour() == 16 && today.getMinute() <= 38)) {
                dayMN = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            } else if (today.getHour() > 16 || (today.getHour() == 16 && today.getMinute() > 38)) {
                dayMN = ddmmyyyy;
            }

            day = ddmmyyyy;
        } else {
            day = day.replace("-", "/");
            if (ddmmyyyy.equals(day)) {
                day = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -1);
            }
            dayMB = day;
            dayMT = day;
            dayMN = day;
        }

        List<Lottery> listLotteryMB = null;
        List<String> listLotteryDuoiMB = null;
        List<String> listLotteryDauMB = null;
        String key = "MB_" + dayMB.replace("/", "");
        if (hLotteryMB.containsKey(key)) {
            listLotteryMB = hLotteryMB.get(key);
            listLotteryDuoiMB = hLotteryDauDuoi.get(key);
            //ban mobile lay dau
            //if("mobile".equals(strMobile)){
            listLotteryDauMB = hLotteryDauDuoi.get(key + "_dau");
            //}
        } else {
            listLotteryMB = lotteryRequest.parserLotteryResultOfRegion(dayMB, "MB");
            
            if(listLotteryMB != null && listLotteryMB.size() > 1) {
                listLotteryMB = listLotteryMB.subList(0, 0);
            }
            if (listLotteryMB != null && !listLotteryMB.isEmpty()) {
                listLotteryDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
                hLotteryMB.put(key, listLotteryMB);
                hLotteryDauDuoi.put(key, listLotteryDuoiMB);

                //ban mobile lay dau
                //if("mobile".equals(strMobile)){
                listLotteryDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
                hLotteryDauDuoi.put(key + "_dau", listLotteryDauMB);
                //}
            }
        }

        Lotterys lotterysMT = null;
        List<String> listLotteryDuoiMT = null;
        int numSizeMT = 0;
        key = "MT_" + dayMT.replace("/", "");
        if (hLotterys.containsKey(key)) {
            lotterysMT = hLotterys.get(key);
            listLotteryDuoiMT = hLotteryDauDuoi.get(key);
            try {
                numSizeMT = Integer.parseInt(hNumSize.get(key));
            } catch (Exception e) {
            }

        } else {
            List<Lottery> listLotteryMT = lotteryRequest.parserLotteryResultOfRegion(dayMT, "MT");
            if (listLotteryMT != null && !listLotteryMT.isEmpty()) {
                lotterysMT = findLotterys(listLotteryMT, dayMT);
                listLotteryDuoiMT = findDuoi(listLotteryMT);
                numSizeMT = listLotteryMT.size();
                try {
                    hNumSize.put(key, String.valueOf(numSizeMT));
                } catch (Exception e) {
                }
                hLotterys.put(key, lotterysMT);
                hLotteryDauDuoi.put(key, listLotteryDuoiMT);

            }
        }

        Lotterys lotterysMN = null;
        List<String> listLotteryDuoiMN = null;
        int numSizeMN = 0;
        key = "MN_" + dayMN.replace("/", "");
        if (hLotterys.containsKey(key)) {
            lotterysMN = hLotterys.get(key);
            listLotteryDuoiMN = hLotteryDauDuoi.get(key);
            try {
                numSizeMN = Integer.parseInt(hNumSize.get(key));
            } catch (Exception e) {
            }
        } else {
            List<Lottery> listLotteryMN = lotteryRequest.parserLotteryResultOfRegion(dayMN, "MN");
            if (listLotteryMN != null && !listLotteryMN.isEmpty()) {
                lotterysMN = findLotterys(listLotteryMN, dayMN);
                listLotteryDuoiMN = findDuoi(listLotteryMN);
                numSizeMN = listLotteryMN.size();
                try {
                    hNumSize.put(key, String.valueOf(numSizeMN));
                } catch (Exception e) {
                }
                hLotterys.put(key, lotterysMN);
                hLotteryDauDuoi.put(key, listLotteryDuoiMN);
            }
        }

        //30 so ra nhieu va it
        String tungay = com.utils.DateProc.TimestampYYYYMMDD(com.utils.DateProc.String2Timestamp(com.utils.DateProc.TimestampPlusDay2DDMMYYYY(com.utils.DateProc.String2Timestamp(day), -30)));
        String denngay = com.utils.DateProc.TimestampYYYYMMDD(com.utils.DateProc.String2Timestamp(day));
        String danlotoTK = LotoUtils.getAllLoto(",");
        ThongKeLoto thongKeLoto = new ThongKeLoto();
        List<Loto> listLoto = thongKeLoto.findLoto(danlotoTK, "XSTD", tungay, denngay);
        //Nhieu
        List<Loto> max30LotoList = new ArrayList<>();
        //It
        List<Loto> min30LotoList = new ArrayList<>();
        if (listLoto != null && listLoto.size() > 0) {
            Loto[] arrayMaxLoto = listLoto.toArray(new Loto[listLoto.size()]);
            arrayMaxLoto = bubbleSort(arrayMaxLoto, listLoto.size(), false);
            for (int i = 0; i < 30; i++) {
                max30LotoList.add(arrayMaxLoto[i]);
            }
            
            Loto[] arrayMinLoto = listLoto.toArray(new Loto[listLoto.size()]);
            arrayMinLoto = bubbleSort(arrayMinLoto, listLoto.size(), true);
            for (int i = 0; i < 30; i++) {
                min30LotoList.add(arrayMinLoto[i]);
            }
        }
        mod.addObject("listLotteryMB", listLotteryMB);
        mod.addObject("lotterysMT", lotterysMT);
        mod.addObject("lotterysMN", lotterysMN);

        mod.addObject("listLotteryDuoiMB", listLotteryDuoiMB);
        mod.addObject("listLotteryDuoiMT", listLotteryDuoiMT);
        mod.addObject("listLotteryDuoiMN", listLotteryDuoiMN);

        mod.addObject("listLotteryDauMB", listLotteryDauMB);

        mod.addObject("numSizeMT", numSizeMT);
        mod.addObject("numSizeMN", numSizeMN);

        mod.addObject("max30LotoList", max30LotoList);
        mod.addObject("min30LotoList", min30LotoList);
        
        mod.addObject("ddmmyyyy", ddmmyyyy);

        // seo
        String slogan = "Xổ số - kết quả xổ số";
        String title = "Xổ số - Kết quả xổ số trực tiếp ngay tại trường quay ";
        String keywords = "ket qua xo so,so xo,xo so mien bac,xổ số miền bắc,xstd,xsmb,xo so mien trung,kqxs,xo so mien nam,xo so,xsmt,xsmn,kết quả xổ số";
        String description = "xstd - Trực tiếp kết quả xổ số 3 miền bắc,trung,nam nhanh nhất.Phân tích kqxs,thống kê các cặp xo so may mắn trong ngày ";

        if (canonical != null && canonical.contains("kqxs-ket-qua-xo-so-ngay")) {
            title = "Xổ số - kết quả xổ số - KQXS Trực tiếp nhanh vãi ngày " + day.replace("/", "-");
        } else if (canonical != null && canonical.contains("kqxs-ket-qua-xo-so")) {
            title = "Xổ số - kết quả xổ số - KQXS Trực tiếp nhanh vãi";
            keywords = "ket qua xo so, xo so, kqxs, truc tiep xo so, xo so truc tiep";
            description = "XO SO - KET QUA XO SO - KQXS. Trực tiếp kết quả xổ số ngày hôm nay thứ 2 thứ 3 thứ 4 thứ 5 thứ 6 thứ 7 chủ nhật tại trường quay chính xác và nhanh nhất";
        }

        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);

        if ("pc".equalsIgnoreCase(strMobile)) {
            mod.setViewName("/index");
        } else {
            mod.setViewName("/mobile/index");
        }

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
