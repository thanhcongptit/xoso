/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.domain.Top10LotoDTO;
import inet.bean.LichVanSu;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.Lotterys;
import inet.bean.Member;
import inet.bean.News;
import inet.bean.XSVuiChotSo;
import inet.bean.XSVuiTopUser;
import inet.constant.Constants;
import inet.lichvansu.LVSBuilder;
import inet.model.CapsoOnlineDAO;
import inet.model.LotteryResultDAO;
import inet.model.MemberDAO;
import inet.request.LotteryRequest;
import inet.util.ChatUtil;
import inet.util.Contant;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.StringPro;
import inet.util.WapTool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class BaseController extends AbstractController {
//    private final String strUrl="http://localhost:8084/xoso";

    private final String strUrl = "http://108.61.183.88/xoso";
    protected String canonical = "";
    private LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
    private static List<LotteryCompany> listCompany = null;
    private static HashMap<String, List<LotteryCompany>> hListCompanyDayOfWeek = null;
    protected static List<List<LotteryCompany>> listCompanyOpenToday = null;
    protected static List<LotteryCompany> listLotteryCompanyMT = new ArrayList<LotteryCompany>();
    protected static List<LotteryCompany> listLotteryCompanyMN = new ArrayList<LotteryCompany>();
    private static int totalRowCompanyOpen = 3;
    private static List<List<LotteryCompany>> listCompanyOpenPrv = null;
    private static List<News> listLotteryNews = null;
    private static final List<News> listLotteryPhapLuat = null;
    private static final List<XSVuiTopUser> listTopUser = null;
    private static final List<XSVuiTopUser> listTopUserMonth = null;
    private static final List<XSVuiTopUser> listTopUserWeek = null;
    private static final List<XSVuiChotSo> listBettingAll = null;
    private static LichVanSu lichVanSu = null;
    protected String strMobile = "pc";
    protected String ddmmyyyy = "";
    private String dayOfWeek = "";
    private String prvDayOfWeek = "";
    private String prvDay = "";
    private long loadTimeNews = 0;
    private Member memberLogin = null;

    public BaseController() {
        if (listCompany == null || listCompany.isEmpty()) {
            LotteryRequest lotteryRequest = new LotteryRequest();
            listCompany = lotteryRequest.parserLotteryCompany();
            if (listCompany != null && !listCompany.isEmpty()) {
                getCompanyDayOfWeek();
            }
        }
    }

    protected void loadBase() {
        // lay ngay thang hien tai       
        String today = DateProc.getDateString(DateProc.createTimestamp());
        ddmmyyyy = DateProc.getDateString(DateProc.createTimestamp());
        if ("".equals(prvDay)) {
            prvDay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
        }
        long timeCurrent = System.currentTimeMillis();

        if (listCompany == null || listCompany.isEmpty()) {
            LotteryRequest lotteryRequest = new LotteryRequest();
            listCompany = lotteryRequest.parserLotteryCompany();
            if (listCompany != null && !listCompany.isEmpty()) {
                getCompanyDayOfWeek();
            }
        }

        //lay cac tinh mo thuong ngay hom nay        
        if (!today.equals(prvDay)) {
            
            prvDay = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
            dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(today);
            prvDayOfWeek = DatePro.getDateOfWeekDDMMYYYY(prvDay);

            //System.out.println("Hom nay "+dayOfWeek+"==>>Hom qua "+prvDayOfWeek);
            if (listCompanyOpenPrv == null) {
                listCompanyOpenPrv = new ArrayList<List<LotteryCompany>>();
            }
            listCompanyOpenPrv.clear();
            listCompanyOpenPrv = getCompanyOpenDay(prvDayOfWeek);

            if (listCompanyOpenToday == null) {
                listCompanyOpenToday = new ArrayList<List<LotteryCompany>>();
            }
            listCompanyOpenToday.clear();
            listCompanyOpenToday = getCompanyOpenDay(dayOfWeek);

            // lay lich mo thuong
            if (listCompanyOpenToday != null && !listCompanyOpenToday.isEmpty()) {
                List<LotteryCompany> list = null;
                listLotteryCompanyMT.clear();
                listLotteryCompanyMN.clear();
                for (int i = 0; i < listCompanyOpenToday.size(); i++) {
                    list = listCompanyOpenToday.get(i);
                    for (int j = 0; j < list.size(); j++) {
                        if ("MN".equals(list.get(j).getRegion())) {
                            listLotteryCompanyMN.add(list.get(j));
                        } else if ("MT".equals(list.get(j).getRegion())) {
                            listLotteryCompanyMT.add(list.get(j));
                        }
                    }
                }
            }
        }

        // lay phan tin tuc
        loadTimeNews = 0;
        if (listLotteryNews == null || !ddmmyyyy.equals(prvDay)) {
            LotteryRequest lotteryRequest = new LotteryRequest();
            listLotteryNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "1", Contant.MAX_NEWS);
            loadTimeNews = System.currentTimeMillis();

        } else if (timeCurrent - loadTimeNews > (60 * 60 * 1000)) {
            LotteryRequest lotteryRequest = new LotteryRequest();
            listLotteryNews = lotteryRequest.parserLotteryNews(Contant.SITE_ID, "1", Contant.MAX_NEWS);
            loadTimeNews = timeCurrent;
        }

        // van su lanh
        if (lichVanSu == null) {
            LVSBuilder lVSBuilder = new LVSBuilder();
            lichVanSu = lVSBuilder.buildContentLVS(ddmmyyyy);
        }
        prvDay = today;
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        ModelAndView mod = new ModelAndView();
        canonical = (String) request.getAttribute("javax.servlet.forward.request_uri");
        if (canonical != null) {
            if (checkToUpcase(canonical)) {
                try {
                    canonical = canonical.toLowerCase();
                    response.sendRedirect(canonical);
                } catch (Exception e) {
                }
            }
        }
        //kiem tra user-agent
        getUserAgent(request);

        //loadbase
        loadBase();

        //kiem tra dang nhap
        if (request.getSession() != null) {
            memberLogin = request.getSession().getAttribute(Constants.LOGIN_SESSION) != null ? (Member) request.getSession().getAttribute(Constants.LOGIN_SESSION) : null;
        }

        if (listLotteryCompanyMN != null && listLotteryCompanyMT != null) {
            if (listLotteryCompanyMN.size() > listLotteryCompanyMT.size()) {
                totalRowCompanyOpen = listLotteryCompanyMN.size();
            } else {
                totalRowCompanyOpen = listLotteryCompanyMT.size();
            }
        }

        MemberDAO memberDAO = new MemberDAO();
        List<Member> listTopDaiGia = memberDAO.findTopMoney(6);

        CapsoOnlineDAO capsoOnlineDAO = new CapsoOnlineDAO();
        HashMap<String, String> hmTop10ChoiNhieu1 = capsoOnlineDAO.getTop10ChoiNhieu();

        Lottery lottery = lotteryResultDAO.findResultByCodeInDay("XSTD",
                DateUtil.date2String(new Date(), "dd/MM/yyyy"));
        List<Top10LotoDTO> hmTop10ChoiNhieu = sortByValues(hmTop10ChoiNhieu1, lottery);

        mod.addObject("hmTop10ChoiNhieu", hmTop10ChoiNhieu);
        mod.addObject("hmEmotion", ChatUtil.hmEmotion);
        mod.addObject("listCompany", listCompany);
        mod.addObject("listCompanyOpenToday", listCompanyOpenToday);
        mod.addObject("listLotteryCompanyMT", listLotteryCompanyMT);
        mod.addObject("listLotteryCompanyMN", listLotteryCompanyMN);
        mod.addObject("totalRowCompanyOpen", totalRowCompanyOpen);
        mod.addObject("listCompanyOpenPrv", listCompanyOpenPrv);
        mod.addObject("listLotteryNews", listLotteryNews);
        mod.addObject("listLotteryPhapLuat", listLotteryPhapLuat);
        mod.addObject("listTopUser", listTopUser);
        mod.addObject("listTopUserMonth", listTopUserMonth);
        mod.addObject("listTopUserWeek", listTopUserWeek);
        mod.addObject("listBettingAll", listBettingAll);
        mod.addObject("today", ddmmyyyy);
        mod.addObject("ddmmyyyy", ddmmyyyy);
        mod.addObject("lichVanSu", lichVanSu);
        mod.addObject("canonical", canonical);
        mod.addObject("dayOfWeek", dayOfWeek);
        mod.addObject("strUrl", strUrl);
        mod.addObject("memberLogin", memberLogin);
        mod.addObject("listTopDaiGia", listTopDaiGia);
        return mod;
    }

    private List<Top10LotoDTO> sortByValues(HashMap map, Lottery item) {
        List list = null;
        if (map != null) {
            list = new LinkedList(map.entrySet());
            // Defined Custom Comparator here
            Collections.sort(list, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return ((Comparable) ((Map.Entry) (o2)).getValue())
                            .compareTo(((Map.Entry) (o1)).getValue());
                }
            });
        }

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
//        HashMap sortedHashMap = new LinkedHashMap();
//        for (Iterator it = list.iterator(); it.hasNext();) {
//            Map.Entry entry = (Map.Entry) it.next();
//            sortedHashMap.put(entry.getKey(), entry.getValue());
//        }
//        return sortedHashMap;
        List<Top10LotoDTO> results = new ArrayList<Top10LotoDTO>();
        if (list != null) {
            for (Iterator it = list.iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                Top10LotoDTO top10LotoDTO = new Top10LotoDTO((String) entry.getKey(), (String) entry.getValue());
                if (item != null) {
                    if (CommonUtil.checkTop10InResult(item, top10LotoDTO.getKey())) {
                        top10LotoDTO.setIsTrue(false);
                    } else {
                        top10LotoDTO.setIsTrue(true);
                    }
                }

                results.add(top10LotoDTO);
            }
        }

        return results;
    }

    protected List<List<LotteryCompany>> getCompanyOpenDay(String day) {
        List<List<LotteryCompany>> listComp = null;
        if (hListCompanyDayOfWeek != null) {
            listComp = new ArrayList<List<LotteryCompany>>();
            String key = day + "_MB";
            listComp.add(hListCompanyDayOfWeek.get(key));

            key = day + "_MT";
            listComp.add(hListCompanyDayOfWeek.get(key));

            key = day + "_MN";
            listComp.add(hListCompanyDayOfWeek.get(key));
        }

        return listComp;
    }

    private void getCompanyDayOfWeek() {
        String key = "";
        if (hListCompanyDayOfWeek == null) {
            hListCompanyDayOfWeek = new HashMap<String, List<LotteryCompany>>();
        }
        LotteryCompany lotteryCompany = null;
        List<LotteryCompany> listComp = null;
        for (int i = 0; i < listCompany.size(); i++) {
            lotteryCompany = listCompany.get(i);
            if (lotteryCompany.getOpendate().contains("2")) {
                key = "2_" + lotteryCompany.getRegion();
                if (hListCompanyDayOfWeek.containsKey(key)) {
                    listComp = hListCompanyDayOfWeek.get(key);
                    listComp.add(lotteryCompany);
                } else {
                    listComp = new ArrayList<LotteryCompany>();
                    listComp.add(lotteryCompany);
                }
                hListCompanyDayOfWeek.put(key, listComp);
            }
            if (lotteryCompany.getOpendate().contains("3")) {
                key = "3_" + lotteryCompany.getRegion();
                if (hListCompanyDayOfWeek.containsKey(key)) {
                    listComp = hListCompanyDayOfWeek.get(key);
                    listComp.add(lotteryCompany);
                } else {
                    listComp = new ArrayList<LotteryCompany>();
                    listComp.add(lotteryCompany);
                }
                hListCompanyDayOfWeek.put(key, listComp);
            }
            if (lotteryCompany.getOpendate().contains("4")) {
                key = "4_" + lotteryCompany.getRegion();
                if (hListCompanyDayOfWeek.containsKey(key)) {
                    listComp = hListCompanyDayOfWeek.get(key);
                    listComp.add(lotteryCompany);
                } else {
                    listComp = new ArrayList<LotteryCompany>();
                    listComp.add(lotteryCompany);
                }
                hListCompanyDayOfWeek.put(key, listComp);
            }
            if (lotteryCompany.getOpendate().contains("5")) {
                key = "5_" + lotteryCompany.getRegion();
                if (hListCompanyDayOfWeek.containsKey(key)) {
                    listComp = hListCompanyDayOfWeek.get(key);
                    listComp.add(lotteryCompany);
                } else {
                    listComp = new ArrayList<LotteryCompany>();
                    listComp.add(lotteryCompany);
                }
                hListCompanyDayOfWeek.put(key, listComp);
            }
            if (lotteryCompany.getOpendate().contains("6")) {
                key = "6_" + lotteryCompany.getRegion();
                if (hListCompanyDayOfWeek.containsKey(key)) {
                    listComp = hListCompanyDayOfWeek.get(key);
                    listComp.add(lotteryCompany);
                } else {
                    listComp = new ArrayList<LotteryCompany>();
                    listComp.add(lotteryCompany);
                }
                hListCompanyDayOfWeek.put(key, listComp);
            }
            if (lotteryCompany.getOpendate().contains("7")) {
                key = "7_" + lotteryCompany.getRegion();
                if (hListCompanyDayOfWeek.containsKey(key)) {
                    listComp = hListCompanyDayOfWeek.get(key);
                    listComp.add(lotteryCompany);
                } else {
                    listComp = new ArrayList<LotteryCompany>();
                    listComp.add(lotteryCompany);
                }
                hListCompanyDayOfWeek.put(key, listComp);
            }
            if (lotteryCompany.getOpendate().contains("CN")) {
                key = "CN_" + lotteryCompany.getRegion();
                if (hListCompanyDayOfWeek.containsKey(key)) {
                    listComp = hListCompanyDayOfWeek.get(key);
                    listComp.add(lotteryCompany);
                } else {
                    listComp = new ArrayList<LotteryCompany>();
                    listComp.add(lotteryCompany);
                }
                hListCompanyDayOfWeek.put(key, listComp);
            }

        }
    }

    private synchronized void getUserAgent(HttpServletRequest request) {
        try {
            synchronized (strMobile) {
                boolean isMobile = WapTool.isMobileDevice(request);
                if (isMobile) {
                    //strMobile="mobile";
                    strMobile = "pc";
                } else {
                    strMobile = "pc";
                }
            }

        } catch (Exception e) {
        }

    }

    protected Lotterys findLotterys(List<Lottery> list, String ddmmyyyy) {
        Lotterys lotterys = null;
        LotteryCompany lotteryCompany = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (lotterys == null) {
                    lotterys = new Lotterys();
                    lotterys.setOpenDate(ddmmyyyy);
                    lotterys.setProvince(list.get(i).getProvince());
                    lotterys.setSpecial(list.get(i).getSpecial());
                    lotterys.setFirst(list.get(i).getFirst());
                    lotterys.setSecond(list.get(i).getSecond());
                    lotterys.setThird(list.get(i).getThird());
                    lotterys.setFourth(list.get(i).getFourth());
                    lotterys.setFifth(list.get(i).getFifth());
                    lotterys.setSixth(list.get(i).getSixth());
                    lotterys.setSeventh(list.get(i).getSeventh());
                    lotterys.setEighth(list.get(i).getEighth());
                    lotteryCompany = findLotteryCompanyOfCode(list.get(i).getCode());
                    lotterys.setLink(lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-" + lotteryCompany.getCompanyLink() + "-" + lotteryCompany.getCodeReverseLowerCase() + ".html");
                } else {
                    lotterys.setProvince(lotterys.getProvince() + "+" + list.get(i).getProvince());
                    lotterys.setSpecial(lotterys.getSpecial() + "+" + list.get(i).getSpecial());
                    lotterys.setFirst(lotterys.getFirst() + "+" + list.get(i).getFirst());
                    lotterys.setSecond(lotterys.getSecond() + "+" + list.get(i).getSecond());
                    lotterys.setThird(lotterys.getThird() + "+" + list.get(i).getThird());
                    lotterys.setFourth(lotterys.getFourth() + "+" + list.get(i).getFourth());
                    lotterys.setFifth(lotterys.getFifth() + "+" + list.get(i).getFifth());
                    lotterys.setSixth(lotterys.getSixth() + "+" + list.get(i).getSixth());
                    lotterys.setSeventh(lotterys.getSeventh() + "+" + list.get(i).getSeventh());
                    lotterys.setEighth(lotterys.getEighth() + "+" + list.get(i).getEighth());
                    lotteryCompany = findLotteryCompanyOfCode(list.get(i).getCode());
                    lotterys.setLink(lotterys.getLink() + "+" + lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-" + lotteryCompany.getCompanyLink() + "-" + lotteryCompany.getCodeReverseLowerCase() + ".html");
                }
            }

        }

        return lotterys;
    }

    protected List<String> findDuoi(List<Lottery> list) {
        List<String> listDuoi = null;
        List<String> listDuoi1 = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (listDuoi == null) {
                    listDuoi = StringPro.filterDauOrDuoi(list.get(i), true);
                } else {
                    listDuoi1 = StringPro.filterDauOrDuoi(list.get(i), true);
                    for (int j = 0; j < 10; j++) {
                        listDuoi.set(j, listDuoi.get(j) + "+" + listDuoi1.get(j));
                    }
                }
            }
        }
        return listDuoi;
    }

    protected List<String> findDau(List<Lottery> list) {
        List<String> listDuoi = null;
        List<String> listDuoi1 = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (listDuoi == null) {
                    listDuoi = StringPro.filterDauOrDuoi(list.get(i), false);
                } else {
                    listDuoi1 = StringPro.filterDauOrDuoi(list.get(i), false);
                    for (int j = 0; j < 10; j++) {
                        listDuoi.set(j, listDuoi.get(j) + "+" + listDuoi1.get(j));
                    }
                }
            }
        }
        return listDuoi;
    }

    protected LotteryCompany findLotteryCompanyOfCode(String code) {
        LotteryCompany lotteryCompany = null;
        if (listCompany == null || listCompany.isEmpty()) {
            return lotteryCompany;
        }
        for (int i = 0; i < listCompany.size(); i++) {
            if (code.equalsIgnoreCase(listCompany.get(i).getCode())) {
                lotteryCompany = listCompany.get(i);
            }
        }

        return lotteryCompany;
    }

    private boolean checkToUpcase(String str) {
        boolean result = false;
        String[] arr = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
        for (int i = 0; i < arr.length; i++) {
            if (str.contains(arr[i])) {
                result = true;
                break;
            }
        }

        return result;
    }
}
