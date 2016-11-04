/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.listener;

import com.google.gson.Gson;
import com.soicaupro.thongkebacnho.CommonUtil;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.Lotterys;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.FileTool;
import inet.util.StringPro;
import inet.util.Today;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author hanhlm
 */
public class XSLive implements ServletContextListener, Runnable {

    private boolean running = true;
    private static List<LotteryCompany> listCompany = null;

    public static HashMap<String, Lottery> hLottery = null;
    public static HashMap<String, List<String>> hDauDuoi = null;

    public static List<Lottery> listLotteryMB = null;
    public static List<String> listDuoiMB = null;
    public static List<String> listDauMB = null;

    public static Lotterys listLotteryMT = null;
    public static List<String> listDuoiMT = null;
    public static int numSizeMT = 0;

    public static Lotterys listLotteryMN = null;
    public static List<String> listDuoiMN = null;
    public static int numSizeMN = 0;

    public static String sDDMMYYYY = null;

    private static String newsFolder = null;
    private static String realNewsFolder = null;

    private static boolean buildMB = false;
    private static boolean buildMT = false;
    private static boolean buildMN = false;

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {

        ServletContext c = contextEvent.getServletContext();
        if (c != null) {
            if (c.getInitParameter("buffer_folder") != null) {
                newsFolder = c.getInitParameter("buffer_folder");
                realNewsFolder = c.getRealPath("/") + "/" + c.getInitParameter("buffer_folder");
            } else {
                newsFolder = "buffer";
                realNewsFolder = c.getRealPath("/") + "/" + newsFolder;
            }
            if(!realNewsFolder.contains("123")) realNewsFolder += "123";
        }

        if (listCompany == null || listCompany.isEmpty()) {
            LotteryRequest lotteryRequest = new LotteryRequest();
            listCompany = lotteryRequest.parserLotteryCompany();
        }

        new Thread(this).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop running Live XS");
        running = false;
    }

    private void buildBufferHtmlXSMN(String mobile) {
        String html = "";
        if (listLotteryMN != null) {
            String width = "26%";
            if (numSizeMN > 3) {
                width = "20%";
            }

            html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
            html += "<div class=\"box_kqxs box_cc\">";
            html += "<div id=\"kqxsmb\">";
            html += "<div class=\"result-header\">";
            html += "<h2><a href=\"/ket-qua-xo-so-mien-nam-xsmn.html\">KẾT QUẢ XỔ SỐ MIỀN NAM XSMN</a> Ngày " + listLotteryMN.getOpenDate() + "</h2>";
            html += "</div>";

            html += "<div class=\"box_so\">";
            html += "<table width=\"100%\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" bgcolor=\"#dedede\">";
            html += "<tbody>";
            html += "<tr class=\"xsmb_ngang_1\">";
            html += "<td class=\"xsmn_1 do\"></td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + ";text-align: center\" class=\"xsmn_" + numSizeMN + " fon12\">";
                html += "<p class=\"do\" style=\"text-align: center\"><a>" + token + "</a></p>";
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G8</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải tám</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getEighth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">";
                html += "<span class=\"do\">" + token + "</span>";
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G7</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải bảy</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSeventh(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G6</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải sáu</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSixth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G5</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải năm</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getFifth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G4</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải tư</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getFourth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G3</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải ba</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getThird(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G2</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải nhì</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSecond(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G1</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải nhất</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getFirst(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">DB</td>";
            } else {
                html += "<td class=\"xsmn_1\">Đặc biệt</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSpecial(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon16\">";
                html += "<span class=\"do fon16\">" + token + "</span>";
                html += "</td>";
            }
            html += "</tr>";

            html += "</tbody>";
            html += "</table>";
            html += "<div class=\"clr\"></div>";
            html += "<div class=\"boxseo bogoc2\" style='display:none'>";
            html += "Chờ kết quả :<span class=\"red\"> XS&lt;Mã Tỉnh&gt; CHO</span> gửi <span class=\"red\">8185</span>";
            if ("mobile".equals(mobile)) {
                html = "<a href=\"javascript:loadSMS('XS<MaTinh> CHO','8185')\">" + html + "</a>";
            }
            html += "<br/>Lấy bộ số thần tài: <span class=\"red\"> CAU </span> &lt;Mã Tỉnh&gt; gửi <span class=\"red\">8585</span>";
            if ("mobile".equals(mobile)) {
                html = "<a href=\"javascript:loadSMS('CAU MaTinh','8585')\">" + html + "</a>";
            }
            html += "</div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";

            html += "<div class=\"box_kqxs box_cc\">";
            html += "<div id=\"kqxsmb\">";
            html += "<div class=\"box_so\">";
            html += "<table width=\"100%\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" bgcolor=\"#dedede\">";
            html += "<tbody>";

            html += "<tr class=\"bg_head\">";
            html += "<td class=\"xsmn_1 xsmntit\"><h3>Đầu</h3></td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + " fon12\">";
                html += "<h3 class=\"h3title\"><a>" + token + "</a></h3>";
                html += "</td>";
            }
            html += "</tr>";

            for (int j = 0; j < listDuoiMN.size(); j++) {
                html += "<tr class=\"xsmn_tke_ngang\">";
                html += "<td class=\"web_XS_3 web_bg_xam\"><a>" + j + "</a></td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(listDuoiMN.get(j), "+"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMN + "\">" + token + "</td>";
                }
                html += "</tr>";
            }

            html += "</tbody>";
            html += "</table>";
            html += "</div>";
            html += "</div>";
            html += "</div>";

            //lotteryResultMN=html;
        }

        if (!"mobile".equals(mobile)) {
            FileTool.saveToFile(html, realNewsFolder + "/xsmn.html");
        } else {
            FileTool.saveToFile(html, realNewsFolder + "/mobile/xsmn.html");
        }
    }

    private void buildBufferHtmlXSMT(String mobile) {
        String html = "";
        System.out.println("kqmt 1: " + new Gson().toJson(listLotteryMT) );
        if (listLotteryMT != null) {
            String width = "26%";
            if (numSizeMT > 3) {
                width = "20%";
            }

            html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
            html += "<div class=\"box_kqxs box_cc\">";
            html += "<div id=\"kqxsmb\">";
            html += "<div class=\"result-header\">";
            html += "<h2><a href=\"/ket-qua-xo-so-mien-trung-xsmt.html\">KẾT QUẢ XỔ SỐ MIỀN TRUNG XSMT</a> Ngày " + listLotteryMT.getOpenDate() + "</h2>";
            html += "</div>";

            html += "<div class=\"box_so\">";
            html += "<table width=\"100%\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" bgcolor=\"#dedede\">";
            html += "<tbody>";
            html += "<tr class=\"xsmb_ngang_1\">";
            html += "<td class=\"xsmn_1 do\"></td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + ";text-align: center\" class=\"xsmn_" + numSizeMT + " fon12\">";
                html += "<p class=\"do\" style=\"text-align: center\"><a>" + token + "</a></p>";
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G8</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải tám</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getEighth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">";
                html += "<span class=\"do\">" + token + "</span>";
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G7</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải bảy</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSeventh(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G6</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải sáu</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSixth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G5</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải năm</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getFifth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G4</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải tư</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getFourth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G3</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải ba</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getThird(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G2</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải nhì</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSecond(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">G1</td>";
            } else {
                html += "<td class=\"xsmn_1\">Giải nhất</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getFirst(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"xsmn_ngang\">";
            if ("mobile".equals(mobile)) {
                html += "<td class=\"xsmn_1\">DB</td>";
            } else {
                html += "<td class=\"xsmn_1\">Đặc biệt</td>";
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSpecial(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon16\">";
                html += "<span class=\"do fon16\">" + token + "</span>";
                html += "</td>";
            }
            html += "</tr>";

            html += "</tbody>";
            html += "</table>";
            html += "<div class=\"clr\"></div>";
            html += "<div class=\"boxseo bogoc2\" style='display:none'>";
            html += "Chờ kết quả :<span class=\"red\"> XS&lt;Mã Tỉnh&gt; CHO</span> gửi <span class=\"red\">8185</span>";
            if ("mobile".equals(mobile)) {
                html = "<a href=\"javascript:loadSMS('XS<MaTinh> CHO','8185')\">" + html + "</a>";
            }
            html += "<br/>Lấy bộ số thần tài: <span class=\"red\"> CAU </span> &lt;Mã Tỉnh&gt; gửi <span class=\"red\">8585</span>";
            if ("mobile".equals(mobile)) {
                html = "<a href=\"javascript:loadSMS('CAU MaTinh','8585')\">" + html + "</a>";
            }
            html += "</div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";

            html += "<div class=\"box_kqxs box_cc\">";
            html += "<div id=\"kqxsmb\">";
            html += "<div class=\"box_so\">";
            html += "<table width=\"100%\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" bgcolor=\"#dedede\">";
            html += "<tbody>";

            html += "<tr class=\"bg_head\">";
            html += "<td class=\"xsmn_1 xsmntit\"><h3>Đầu</h3></td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + " fon12\">";
                html += "<h3 class=\"h3title\"><a>" + token + "</a></h3>";
                html += "</td>";
            }
            html += "</tr>";

            for (int j = 0; j < listDuoiMT.size(); j++) {
                html += "<tr class=\"xsmn_tke_ngang\">";
                html += "<td class=\"web_XS_3 web_bg_xam\"><a>" + j + "</a></td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(listDuoiMT.get(j), "+"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td style=\"width:" + width + "\" class=\"xsmn_" + numSizeMT + "\">" + token + "</td>";
                }
                html += "</tr>";
            }

            html += "</tbody>";
            html += "</table>";
            html += "</div>";
            html += "</div>";
            html += "</div>";

            //lotteryResultMT=html;
        }

        if (!"mobile".equals(mobile)) {
            FileTool.saveToFile(html, realNewsFolder + "/xsmt.html");
        } else {
            FileTool.saveToFile(html, realNewsFolder + "/mobile/xsmt.html");
        }
    }

    private void buildBufferHtmlXSMB() {
        String html = "";
        if (listLotteryMB != null && !listLotteryMB.isEmpty()) {
            Lottery lottery = listLotteryMB.get(0);
            html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
            html += "<div class=\"box_kqxs box_cc\">";
            html += "<div id=\"kqxsmb\">";
            html += "<div class=\"result-header\">";
            html += "<h2 ><a href=\"/xsmb-ket-qua-xo-so-mien-bac-xstd.html\">KẾT QUẢ XỔ SỐ MIỀN BẮC XSMB<a/> Ngày " + lottery.getOpenDate() + "</h2>";
            html += "</div>";
            html += "<div class=\"box_so\">";
            html += "<div class=\"box_so_left\">";
            html += "<table width=\"100%\" cellspacing=\"1\" cellpadding=\"0\" border=\"0\" bgcolor=\"#dedede\">";
            html += "<tbody>";
            html += "<tr class=\"web_bg_Trang\">";
            html += "<td class=\"web_XS_1 chugiai\">Đặc biệt</td>";
            html += "<td colspan=\"12\" class=\"web_XS_2 chukq\"><span class=\"do\">";
            html += lottery.getSpecial();
            html += "</span></td>";
            html += "</tr>";
            html += "<tr class=\"web_bg_Trang\">";
            html += "<td class=\"web_XS_1 chugiai\">Giải nhất</td>";
            html += "<td colspan=\"12\" class=\"web_XS_2 chukq\">";
            html += lottery.getFirst();
            html += "</td>";
            html += "</tr>";
            html += "<tr class=\"web_bg_Trang\">";
            html += "<td class=\"web_XS_1 chugiai\">Giải nhì</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td colspan=\"6\" class=\"web_XS_2 chukq\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"web_bg_Trang\">";
            html += "<td rowspan=\"2\" class=\"web_XS_1 chugiai\">Giải ba</td>";
            int i = 0;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                if (i > 2) {
                    break;
                }
                String token = stringTokenizer.nextToken();
                html += "<td colspan=\"4\" class=\"web_XS_2 chukq\">" + token + "</td>";
            }
            html += "</tr>";

            i = 0;
            html += "<tr class=\"web_bg_Trang\">";
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (i > 2) {
                    html += "<td colspan=\"4\" class=\"web_XS_2 chukq\">" + token + "</td>";
                }
            }
            html += "</tr>";

            html += "<tr class=\"web_bg_Trang\">";
            html += "<td class=\"web_XS_1 chugiai\">Giải tư</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td colspan=\"3\" class=\"web_XS_2 chukq\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"web_bg_Trang\">";
            html += "<td rowspan=\"2\" class=\"web_XS_1 chugiai\">Giải năm</td>";
            i = 0;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                if (i > 2) {
                    break;
                }
                String token = stringTokenizer.nextToken();
                html += "<td colspan=\"4\" class=\"web_XS_2 chukq\">" + token + "</td>";
            }
            html += "</tr>";

            i = 0;
            html += "<tr class=\"web_bg_Trang\">";
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (i > 2) {
                    html += "<td colspan=\"4\" class=\"web_XS_2 chukq\">" + token + "</td>";
                }
            }
            html += "</tr>";

            html += "<tr class=\"web_bg_Trang\">";
            html += "<td class=\"web_XS_1 chugiai\">Giải sáu</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td colspan=\"4\" class=\"web_XS_2 chukq\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"web_bg_Trang\">";
            html += "<td class=\"web_XS_1 chugiai\">Giải bảy</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td colspan=\"3\" class=\"web_XS_2 chukq\">" + token + "</td>";
            }
            html += "</tr>";

            html += "</tbody>";
            html += "</table>";
            html += "</div>";
            html += "<div class=\"boxseo bogoc2\" style='display:none'><a href=\"javascript:loadSMS('XSMB CHO','8185')\">Chờ kết quả:<span class=\"red\"> XSMB CHO</span> gửi <span class=\"red\">8185</span></a>";
            html += "<br/><a href=\"javascript:loadSMS('CAU','8585')\">Số đẹp hôm nay :<span class=\"red\"> CAU</span> gửi <span class=\"red\">8585</span></a>";
            html += "</div>";

            html += "<div class=\"box_so_right dd-loto-widget\">";
            html += "<table cellspacing=\"1\" cellpadding=\"0\" border=\"0\" bgcolor=\"#dedede\">";
            html += "<tbody>";
            html += "<tr class=\"bg_head\">";
            html += "<td class=\"web_XS_3 web_bg_Loto\"><h3>Đầu</h3></td>";
            html += "<td class=\"web_XS_4 web_bg_Loto\"><h3>Đuôi</h3></td>";
            html += "</tr>";

            for (int j = 0; j < listDuoiMB.size(); j++) {
                html += "<tr class=\"web_bg_Trang_1\">";
                html += "<td class=\"web_XS_3 web_bg_xam\">" + j + "</td>";
                html += "<td class=\"web_XS_4 web_bg_xam\">" + listDuoiMB.get(j) + "</td>";
                html += "</tr>";
            }

            html += "</tbody>";
            html += "</table>";

            html += "<table class=\"dit-loto\" cellspacing=\"1\" cellpadding=\"0\" border=\"0\" bgcolor=\"#dedede\">";
            html += "<tbody>";
            html += "<tr class=\"bg_head\">";
            html += "<td class=\"text_rightmb web_XS_4 web_bg_Loto\"><h3>Loto</h3></td>";
            html += "<td class=\"web_XS_3 web_bg_Loto\"><h3>Đuôi</h3></td>";
            html += "</tr>";

            for (int j = 0; j < listDauMB.size(); j++) {
                html += "<tr class=\"web_bg_Trang_1\">";
                html += "<td class=\"text_rightmb web_XS_4 web_bg_xam\">" + listDauMB.get(j) + "</td>";
                html += "<td class=\"web_XS_3 web_bg_xam\">" + j + "</td>";
                html += "</tr>";
            }

            html += "</tbody>";
            html += "</table>";

            html += "<div class=\"clearfix\"></div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
            
            FileTool.saveToFile(html, realNewsFolder + "/xsmb.html");

        }
    }

    public void run() {

        Today today = null;
        while (running) {
            today = new Today();
            try {
                String ddmmyyyy = DateProc.getDateString(DateProc.createTimestamp());
                if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
                    synchronized (this) {
                        listLotteryMB = null;
                        listDuoiMB = null;

                        listLotteryMT = null;
                        listDuoiMT = null;

                        listLotteryMN = null;
                        listDuoiMN = null;

                        hLottery = null;
                        hDauDuoi = null;

                        buildMB = false;
                        buildMT = false;
                        buildMN = false;
                    }

                    sDDMMYYYY = ddmmyyyy;

                }

                //build html buffer
                if ((today.getHour() == 18 && today.getMinute() > 38) || (today.getHour() > 18)) {
                    if (!buildMB && listLotteryMB != null && !listLotteryMB.isEmpty()) {
                        if (listLotteryMB.get(0).getSpecial() != null && !listLotteryMB.get(0).getSpecial().equals("")) {
                            buildBufferHtmlXSMB();
                            buildMB = true;
                        }
                    }
                }

                if ((today.getHour() == 17 && today.getMinute() > 38) || (today.getHour() > 17)) {
                    if (!buildMT && listLotteryMT != null && listLotteryMT.getSpecial() != null && listLotteryMT.getSpecial().contains("+")) {
                        buildBufferHtmlXSMT("pc");
                        buildBufferHtmlXSMT("mobile");
                        buildMT = true;
                    }
                }

                if ((today.getHour() == 16 && today.getMinute() > 38) || (today.getHour() > 16)) {
                    if (!buildMN && listLotteryMN != null && listLotteryMN.getSpecial() != null && listLotteryMN.getSpecial().contains("+")) {
                        buildBufferHtmlXSMN("pc");
                        buildBufferHtmlXSMN("mobile");
                        buildMN = true;
                    }
                }

                if (today.getHour() == 16 && today.getMinute() <= 45) {
                    // lay ket qua xo so mien nam                    
                    builKQXS("MN");
                    buildBufferHtmlXSMN("pc");
                    buildBufferHtmlXSMN("mobile");
                    Thread.sleep(5000);
                } else if (today.getHour() == 17 && today.getMinute() <= 45) {
                    // lay ket qua xo so mien nam
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    builKQXS("MT");
                    buildBufferHtmlXSMT("pc");
                    buildBufferHtmlXSMT("mobile");
                    Thread.sleep(5000);
                } else if (today.getHour() == 18 && today.getMinute() <= 45) {
                    // lay ket qua xo so mien nam
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    if (listLotteryMT == null) {
                        builKQXS("MT");
                    }
                    builKQXS("MB");
                    buildBufferHtmlXSMB();
                    Thread.sleep(5000);
                } else if (today.getHour() == 16 && today.getMinute() > 45) {
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    Thread.sleep(1000 * 60 * 5);
                } else if (today.getHour() == 17 && today.getMinute() > 45) {
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    if (listLotteryMT == null) {
                        builKQXS("MT");
                    }
                    Thread.sleep(1000 * 60 * 5);
                } else if ((today.getHour() == 18 && today.getMinute() > 45) || today.getHour() > 18) {
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    if (listLotteryMT == null) {
                        builKQXS("MT");
                    }
                    if (listLotteryMB == null) {
                        builKQXS("MB");
                    }
                    Thread.sleep(1000 * 60 * 5);
                } else {
                    Thread.sleep(1000 * 60 * 5);
                }

            } catch (Exception ex) {
                System.out.println("XSLive==========>>>>>>>>>>>" + ex.toString());
                ex.printStackTrace();
            }

        }

    }

    LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();

    private void builKQXS(String region) {
        if (hLottery == null) {
            hLottery = new HashMap<String, Lottery>();
            hDauDuoi = new HashMap<String, List<String>>();
        }
        List<Lottery> listLottery = lotteryResultDAO.findLotteryNewestRegion(region, sDDMMYYYY);

        listLottery = checkLottery(listLottery);
        // ket qua xo so mien bac
        if ("MB".equals(region)) {
            if (listLottery != null && !listLottery.isEmpty()) {
                synchronized (this) {
                    
                    if(listLottery.size() > 1) {
                        listLottery = listLottery.subList(0, 0);
                    }
                    
                    listLotteryMB = listLottery;
                    listDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
                    listDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
                }
            } else {
                listLottery = new ArrayList<Lottery>();
                listLottery.add(addLotteryNull("Mien Bac", "XSTD"));
                listLotteryMB = listLottery;
                listDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
                listDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
            }
        } else if ("MT".equals(region)) {
            // ket qua xo so mien trung
            if (listLottery != null && !listLottery.isEmpty()) {
                synchronized (this) {
                    List<Lottery> listMT = getListLoterry(listLottery);
//                    listLotteryMT = findLotterys(listLottery, sDDMMYYYY);
//                    listDuoiMT = findDuoi(listLottery);
//                    numSizeMT = listLottery.size();

                    listLotteryMT = findLotterys(listMT, sDDMMYYYY);
                    listDuoiMT = findDuoi(listMT);
                    numSizeMT = listMT.size();
                }
            } else {
                List<LotteryCompany> listComp = getCompanyDayOfWeek("MT");
                if (listComp != null && !listComp.isEmpty()) {
                    for (int i = 0; i < listComp.size(); i++) {
                        if (listLottery == null) {
                            listLottery = new ArrayList<Lottery>();
                        }
                        listLottery.add(addLotteryNull(listComp.get(i).getCompany(), listComp.get(i).getCode()));
                    }
                    listLotteryMT = findLotterys(listLottery, sDDMMYYYY);
                    listDuoiMT = findDuoi(listLottery);
                    numSizeMT = listLottery.size();
                }

            }

        } else if ("MN".equals(region)) {
            // ket qua xo so mien nam 
            if (listLottery != null && !listLottery.isEmpty()) {
                synchronized (this) {
                    List<Lottery> listMN = getListLoterry(listLottery);
                    
//                    listLotteryMN = findLotterys(listLottery, sDDMMYYYY);
//                    listDuoiMN = findDuoi(listLottery);
//                    numSizeMN = listLottery.size();
                      listLotteryMN = findLotterys(listMN, sDDMMYYYY);
                      listDuoiMN = findDuoi(listMN);
                      numSizeMN = listMN.size();
                        
                }
            } else {
                List<LotteryCompany> listComp = getCompanyDayOfWeek("MN");
                if (listComp != null && !listComp.isEmpty()) {
                    for (int i = 0; i < listComp.size(); i++) {
                        if (listLottery == null) {
                            listLottery = new ArrayList<Lottery>();
                        }
                        listLottery.add(addLotteryNull(listComp.get(i).getCompany(), listComp.get(i).getCode()));
                    }
                    listLotteryMN = findLotterys(listLottery, sDDMMYYYY);
                    listDuoiMN = findDuoi(listLottery);
                    numSizeMN = listLottery.size();
                }

            }

        }
    }

    private Lotterys findLotterys(List<Lottery> list, String ddmmyyyy) {
        Lotterys lotterys = null;
        LotteryCompany lotteryCompany = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                hLottery.put(list.get(i).getCode(), list.get(i));
                hDauDuoi.put(list.get(i).getCode() + "_duoi", StringPro.filterDauOrDuoi(list.get(i), true));
                hDauDuoi.put(list.get(i).getCode() + "_dau", StringPro.filterDauOrDuoi(list.get(i), false));
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

    private List<String> findDuoi(List<Lottery> list) {
        List<String> listDuoi = null;
        List<String> listDuoi1 = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                listDuoi1 = StringPro.filterDauOrDuoi(list.get(i), true);
                if (listDuoi == null) {
                    listDuoi = listDuoi1;
                } else {
                    for (int j = 0; j < 10; j++) {
                        listDuoi.set(j, listDuoi.get(j) + "+" + listDuoi1.get(j));
                    }
                }
            }
        }
        return listDuoi;
    }

    private LotteryCompany findLotteryCompanyOfCode(String code) {
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

    private Lottery addLotteryNull(String tinh, String code) {
        Lottery lottery = new Lottery();
        String linkImageLoading = "<img src=\"http://static.xoso.wap.vn/images/xo_so_truc_tiep.gif\">";
        if ("XSTD".equals(code)) {
            lottery.setCode(code);
            lottery.setProvince(tinh);
            lottery.setOpenDate(sDDMMYYYY);
            lottery.setSpecial(linkImageLoading);
            lottery.setFirst(linkImageLoading);
            lottery.setSecond(linkImageLoading + "-" + linkImageLoading);
            lottery.setThird(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setFourth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setFifth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setSeventh(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
        } else {
            lottery.setCode(code);
            lottery.setProvince(tinh);
            lottery.setOpenDate(sDDMMYYYY);
            lottery.setSpecial(linkImageLoading);
            lottery.setFirst(linkImageLoading);
            lottery.setSecond(linkImageLoading);
            lottery.setThird(linkImageLoading + "-" + linkImageLoading);
            lottery.setFourth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setFifth(linkImageLoading);
            lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setSeventh(linkImageLoading);
            lottery.setEighth(linkImageLoading);
        }

        return lottery;
    }

    private List<LotteryCompany> getCompanyDayOfWeek(String region) {
        LotteryCompany lotteryCompany = null;
        List<LotteryCompany> listComp = null;
        String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(sDDMMYYYY);
        for (int i = 0; i < listCompany.size(); i++) {
            lotteryCompany = listCompany.get(i);
            if (lotteryCompany.getOpendate().contains(dayOfWeek) && lotteryCompany.getRegion().equals(region)) {
                if (listComp == null) {
                    listComp = new ArrayList<LotteryCompany>();
                }
                listComp.add(lotteryCompany);
            }
        }

        return listComp;
    }

    private List<Lottery> checkLottery(List<Lottery> listLottery) {
        if (listLottery == null || listLottery.isEmpty()) {
            return null;
        }
        if (!sDDMMYYYY.equals(listLottery.get(0).getOpenDate())) {
            return null;
        }
        Lottery lottery = null;
        for (int i = 0; i < listLottery.size(); i++) {
            lottery = listLottery.get(i);
            lottery.setSpecial(checkAddLoading(lottery.getSpecial()));
            lottery.setFirst(checkAddLoading(lottery.getFirst()));
            lottery.setSecond(checkAddLoading(lottery.getSecond()));
            lottery.setThird(checkAddLoading(lottery.getThird()));
            lottery.setFourth(checkAddLoading(lottery.getFourth()));
            //System.out.println("lottery.setFourth====>>>>"+lottery.getFourth());
            lottery.setFifth(checkAddLoading(lottery.getFifth()));
            lottery.setSixth(checkAddLoading(lottery.getSixth()));
            lottery.setSeventh(checkAddLoading(lottery.getSeventh()));
            lottery.setEighth(checkAddLoading(lottery.getEighth()));

            listLottery.set(i, lottery);
        }

        return listLottery;
    }

    private List<Lottery> checkAddLoadingMB(List<Lottery> listLottery) {
        if (listLottery == null || listLottery.isEmpty()) {
            return null;
        }
        if (!sDDMMYYYY.equals(listLottery.get(0).getOpenDate())) {
            return null;
        }
        Lottery lottery = null;
        String linkImageLoading = "<img src=\"http://static.xoso.wap.vn/images/xo_so_truc_tiep.gif\">";
        for (int i = 0; i < listLottery.size(); i++) {
            lottery = listLottery.get(i);
            if (lottery.getSpecial() == null || "".equals(lottery.getSpecial())) {
                lottery.setSpecial(linkImageLoading);
            }
            if (lottery.getFirst() == null || "".equals(lottery.getFirst())) {
                lottery.setFirst(linkImageLoading);
            }
            if (lottery.getSecond() == null || "".equals(lottery.getSecond())) {
                lottery.setSecond(linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getSecond().contains("-")) {
                lottery.setSecond(lottery.getSecond() + "-" + linkImageLoading);
            }
            if (lottery.getThird() == null || "".equals(lottery.getThird())) {
                lottery.setThird(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getThird().contains("-")) {
                lottery.setThird(lottery.getThird() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getThird().split("-");
                if (arrStr.length < 6) {
                    for (int j = 0; j < (6 - arrStr.length); j++) {
                        lottery.setThird(lottery.getThird() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getFourth() == null || "".equals(lottery.getFourth())) {
                lottery.setFourth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getFourth().contains("-")) {
                lottery.setFourth(lottery.getFourth() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getFourth().split("-");
                if (arrStr.length < 4) {
                    for (int j = 0; j < (4 - arrStr.length); j++) {
                        lottery.setFourth(lottery.getFourth() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getFifth() == null || "".equals(lottery.getFifth())) {
                lottery.setFifth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getFifth().contains("-")) {
                lottery.setFifth(lottery.getFifth() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getFifth().split("-");
                if (arrStr.length < 6) {
                    for (int j = 0; j < (6 - arrStr.length); j++) {
                        lottery.setFifth(lottery.getFifth() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getSixth() == null || "".equals(lottery.getSixth())) {
                lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getSixth().contains("-")) {
                lottery.setSixth(lottery.getSixth() + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getSixth().split("-");
                if (arrStr.length < 3) {
                    for (int j = 0; j < (3 - arrStr.length); j++) {
                        lottery.setSixth(lottery.getSixth() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getSeventh() == null || "".equals(lottery.getSeventh())) {
                lottery.setSeventh(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getSeventh().contains("-")) {
                lottery.setSeventh(lottery.getSeventh() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getSeventh().split("-");
                if (arrStr.length < 4) {
                    for (int j = 0; j < (4 - arrStr.length); j++) {
                        lottery.setSeventh(lottery.getSeventh() + "-" + linkImageLoading);
                    }
                }
            }

            listLottery.set(i, lottery);
        }

        return listLottery;
    }

    private static String checkAddLoading(String str) {
        String linkImageLoading = "<img src=\"http://static.xoso.wap.vn/images/xo_so_truc_tiep.gif\">";
        String result = "";
        if (str == null || "".equals(str)) {
            return linkImageLoading;
        }

        String[] arrStr = str.split("-");
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                count++;
            }
        }

        if (arrStr.length == 0) {
            for (int i = 0; i < count; i++) {
                result = result + "-" + linkImageLoading;
            }
        } else {
            for (int i = 0; i < arrStr.length; i++) {
                result = result + "-" + arrStr[i];
            }

            int addCount = count - (arrStr.length - 1);
            for (int i = 0; i < addCount; i++) {
                result = result + "-" + linkImageLoading;
            }
        }
        result = result.replaceFirst("-", "");
        return result;
    }

    public static void main(String[] args) {

        // System.out.println(checkAddLoading(null));
//        String giai="1----";
//        String[]arr=giai.split("-");
//        if(arr.length<6){
//            for(int i=0;i<6-arr.length;i++){
//                giai=giai+"-**";
//            }
//        }
//        System.out.println(giai);
        //System.out.println(checkAddLoading("9999-8888"));
    }

    private List<String> filterDauOrDuoi(Lottery lottery, boolean isDauOrDuoi) {
        List result = new ArrayList();
        String str = "";
        String str1 = "";
        String str2 = "";
        String strDacbiet = "";
        int dauDacbiet = 0;

        if (isDauOrDuoi) {
            for (int i = 0; i < 10; i++) {
                str1 = "";
                str2 = "";

                if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
                    str = subRight(lottery.getSpecial(), 2);
                    if (str.startsWith(i + "")) {
                        str1 = str.substring(1, 2) + " ";
                        strDacbiet = str.substring(1, 2);
                        dauDacbiet = i;
                    }

                }

                if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
                    str = subRight(lottery.getFirst(), 2);
                    if (str.startsWith(i + "")) {
                        str2 = str.substring(1, 2);

                        str1 = str1 + str2 + " ";
                    }

                }

                String[] arrlottery = null;
                if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
                    arrlottery = lottery.getSecond().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
                    arrlottery = lottery.getThird().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
                    arrlottery = lottery.getFourth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
                    arrlottery = lottery.getFifth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
                    arrlottery = lottery.getSixth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
                    arrlottery = lottery.getSeventh().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
                    arrlottery = lottery.getEighth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }
                    }
                }

                if (!"".equals(str1)) {
                    result.add(str1.substring(0, str1.length() - 1));
                } else {
                    result.add("&nbsp;");
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                str1 = "";
                str2 = "";

                if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
                    str = subRight(lottery.getSpecial(), 2);
                    if (str.endsWith(i + "")) {
                        str1 = str.substring(0, 1) + " ";
                        strDacbiet = str.substring(0, 1);
                        dauDacbiet = i;
                    }

                }

                if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
                    str = subRight(lottery.getFirst(), 2);
                    if (str.endsWith(i + "")) {
                        str2 = str.substring(0, 1);

                        str1 = str1 + str2 + " ";
                    }

                }

                String[] arrlottery = null;
                if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
                    arrlottery = lottery.getSecond().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
                    arrlottery = lottery.getThird().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
                    arrlottery = lottery.getFourth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
                    arrlottery = lottery.getFifth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
                    arrlottery = lottery.getSixth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
                    arrlottery = lottery.getSeventh().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
                    arrlottery = lottery.getEighth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }
                    }
                }

                if (!"".equals(str1)) {
                    result.add(str1.substring(0, str1.length() - 1));
                } else {
                    result.add("&nbsp;");
                }
            }

        }

        return shortAscOrDesc(result, true, strDacbiet, dauDacbiet);
    }

    private List<String> shortAscOrDesc(List<String> list, boolean ascOrDesc, String strDacbiet, int dauDacbiet) {
        List list1 = null;
        if ((list == null) || (list.isEmpty())) {
            return null;
        }
        String string = "";
        String str = "";
        String[] arrStr = null;
        int tg1 = 0;
        int tg2 = 0;
        if (ascOrDesc) {
            for (int i = 0; i < list.size(); i++) {
                string = (String) list.get(i);
                if (!string.contains(" ")) {
                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    list1.add(string);
                } else {
                    arrStr = string.split(" ");
                    for (int j = 0; j < arrStr.length - 1; j++) {
                        for (int k = j + 1; k < arrStr.length; k++) {
                            tg1 = Integer.parseInt(arrStr[j]);
                            tg2 = Integer.parseInt(arrStr[k]);
                            if (tg1 > tg2) {
                                arrStr[j] = ("" + tg2);
                                arrStr[k] = ("" + tg1);
                            }
                        }
                    }

                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    str = "";
                    for (int j = 0; j < arrStr.length; j++) {
                        str = str + arrStr[j] + " ";
                    }
                    list1.add(str.substring(0, str.length() - 1));
                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                string = (String) list.get(i);
                if (!string.contains(" ")) {
                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    list1.add(string);
                } else {
                    arrStr = string.split(" ");
                    for (int j = 0; j < arrStr.length - 1; j++) {
                        for (int k = j + 1; k < arrStr.length; k++) {
                            tg1 = Integer.parseInt(arrStr[j]);
                            tg2 = Integer.parseInt(arrStr[k]);
                            if (tg1 < tg2) {
                                arrStr[j] = ("" + tg2);
                                arrStr[k] = ("" + tg1);
                            }
                        }
                    }

                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    str = "";
                    for (int j = 0; j < arrStr.length; j++) {
                        str = str + arrStr[j] + " ";
                    }
                    list1.add(str.substring(0, str.length() - 1));
                }
            }
        }

        if (list1 != null) {
            for (int i = 0; i < list1.size(); i++) {
                if (i == dauDacbiet) {
                    str = strDacbiet;
                    str = "<span style=\"color: #FF3322\">" + str + "</span>";
                    list1.set(i, ((String) list1.get(i)).replaceFirst(strDacbiet, str));
                    break;
                }
            }
        }

        return list1;
    }

    private String subRight(String temp, int i) {
        if (temp == null) {
            return "";
        }
        String result = "";
        result = temp.substring(temp.length() - i, temp.length());
        return result;
    }

    private List<Lottery> getListLoterry(List<Lottery> listLottery) {
        List<Lottery> rs = new ArrayList<>();
        if(listLottery != null && listLottery.size() > 0) {
            
            for(Lottery lottery: listLottery) {
                if(checkInListLoterry(rs, lottery)) {
                    rs.add(lottery);
                } else {
                    int check = checkDataLoterry(lottery, rs);
                    if( check != -1) {
                        rs.set(check, lottery);
                    }
                }
            }
        }
        
        
        return rs;
    }

    private boolean checkInListLoterry(List<Lottery> rs, Lottery item) {
        if(rs != null && rs.size() > 0) {
            
            for(Lottery lottery: rs) {
                if(lottery.getCode().equalsIgnoreCase(item.getCode())) {
                    return false;
                } 
            }
        }
        
        return true;
    }

    private int checkDataLoterry(Lottery item, List<Lottery> rs) {
        int check = -1;
        
        if(rs != null && rs.size() > 0) {
            
            for(int i=0; i< rs.size(); i++) {
                Lottery lottery = rs.get(i);
                
                if(lottery.getCode().equalsIgnoreCase(item.getCode())) {
                    int totalLoterry = getNullInAward(lottery);
                    int totalItem= getNullInAward(item);
                    
                    if(totalItem < totalLoterry) {
                        return i;
                    }
                }
            }
        }
        
        return check;
    }
    
    private int getNullInAward(Lottery lottery) {
        int total = 0;
        
        if(CommonUtil.isEmptyString(lottery.getSpecial())) {
            total++;
        }
        
        if(CommonUtil.isEmptyString(lottery.getFifth())) {
            total++;
        }
        if(CommonUtil.isEmptyString(lottery.getSecond())) {
            total++;
        }
        if(CommonUtil.isEmptyString(lottery.getThird())) {
            total++;
        }
        if(CommonUtil.isEmptyString(lottery.getFourth())) {
            total++;
        }
        if(CommonUtil.isEmptyString(lottery.getFifth())) {
            total++;
        }
        if(CommonUtil.isEmptyString(lottery.getSixth())) {
            total++;
        }
        if(CommonUtil.isEmptyString(lottery.getSeventh())) {
            total++;
        }
        if(CommonUtil.isEmptyString(lottery.getEighth())) {
            total++;
        }
        
        return total;
    }
}
