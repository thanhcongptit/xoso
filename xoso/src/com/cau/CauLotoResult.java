/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cau;

import com.bean.CauLoto;
import com.bean.LotteryResult;
import com.dao.LotteryResultDAO;
import com.utils.LotoUtils;
import com.utils.StringConvert;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HanhDung
 */
public class CauLotoResult {

    public List<CauLoto> findLichSuCau(String code, String sDate, String eDate, int v1, int v2, boolean dao) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLoteryResult = lotteryResultDAO.findLotteryResultAsc(code, sDate, eDate);

        String resultStart = "";
        String result = "";
        String lotoResult = "";
        String loto = "";
        CauLoto cauLoto = null;
        int dodaicau = 0;
        List<CauLoto> listCauLoto = new ArrayList<>();

        for (int i = 0; i < listLoteryResult.size() - 1; i++) {
            resultStart = StringConvert.stringConcat(listLoteryResult.get(i));
            loto = resultStart.substring(v1, v1 + 1) + resultStart.substring(v2, v2 + 1);
            for (int j = i + 1; j < listLoteryResult.size(); j++) {
                lotoResult = StringConvert.lotoResult(listLoteryResult.get(j));
                if (cauLoto == null) {
                    cauLoto = new CauLoto();
                }
                if (LotoUtils.findLotoResult(lotoResult, loto, 1, dao)) {
                    dodaicau++;
                    cauLoto.setNgayve(cauLoto.getNgayve() + listLoteryResult.get(j).getOpen_date() + "-");
                    result = StringConvert.stringConcat(listLoteryResult.get(j));
                    loto = result.substring(v1, v1 + 1) + result.substring(v2, v2 + 1);
                } else {
                    if (dodaicau > 1) {
                        cauLoto.setCau(dodaicau);
                        cauLoto.setLoto(loto);
                        cauLoto.setVitri1(v1);
                        cauLoto.setVitri2(v2);
                        if (cauLoto.getNgayve() != null && !"".equals(cauLoto.getNgayve())) {
                            cauLoto.setNgayve(cauLoto.getNgayve().substring(0, cauLoto.getNgayve().length() - 1));
                        }

                        listCauLoto.add(cauLoto);
                    }
                    cauLoto = null;
                    dodaicau = 0;
                    i = j;
                    resultStart = StringConvert.stringConcat(listLoteryResult.get(i));
                    loto = resultStart.substring(v1, v1 + 1) + resultStart.substring(v2, v2 + 1);
                }
            }
        }

        return listCauLoto;
    }

    public List<CauLoto> findCauLoto(String code, String sDate, String eDate, int cau, int solanve, boolean dao) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLoteryResult = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<CauLoto> listCauLoto = new ArrayList<>();

        if (listLoteryResult.size() > 0) {
            String resultStart = StringConvert.stringConcat(listLoteryResult.get(0));
            String result = "";
            String lotoResult = "";
            String loto = "";
            String capLoto = "";
            CauLoto cauLoto = null;
            int dodaicau = 0;

            for (int i = 0; i < resultStart.length(); i++) {
                for (int j = 0; j < resultStart.length(); j++) {
                    if (i != j) {
                        dodaicau = 0;
                        result = resultStart;
                        capLoto = result.substring(i, i + 1) + result.substring(j, j + 1);
                        for (int k = 0; k < listLoteryResult.size() - 1; k++) {
                            lotoResult = StringConvert.lotoResult(listLoteryResult.get(k));
                            result = StringConvert.stringConcat(listLoteryResult.get(k + 1));
                            if (i >= 0 && j >= 0 && i + 1 < result.length() && j + 1 < result.length()) {
                                loto = result.substring(i, i + 1) + result.substring(j, j + 1);
                            }
                            if (LotoUtils.findLotoResult(lotoResult, loto, solanve, dao)) {
                                dodaicau++;
                            } else {
                                break;
                            }

                        }

                        //kiem tra do dai cua cau
                        if (dodaicau >= cau) {
                            cauLoto = new CauLoto();
                            cauLoto.setCau(dodaicau);
                            cauLoto.setLoto(capLoto);
                            cauLoto.setVitri1(i);
                            cauLoto.setVitri2(j);

                            listCauLoto.add(cauLoto);
                        }
                    }

                }
            }
        }

        return listCauLoto;
    }

    public List<CauLoto> findCauLotoLoai(String code, String sDate, String eDate, int cau, boolean dao) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLoteryResult = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<CauLoto> listCauLoto = new ArrayList<>();
        if (listLoteryResult.size() > 0) {
            String resultStart = StringConvert.stringConcat(listLoteryResult.get(0));
            String result = "";
            String lotoResult = "";
            String loto = "";
            String capLoto = "";
            CauLoto cauLoto = null;
            int dodaicau = 0;

            for (int i = 0; i < resultStart.length(); i++) {
                for (int j = 0; j < resultStart.length(); j++) {
                    if (i != j) {
                        dodaicau = 0;
                        result = resultStart;
                        capLoto = result.substring(i, i + 1) + result.substring(j, j + 1);
                        for (int k = 0; k < listLoteryResult.size() - 1; k++) {
                            lotoResult = StringConvert.lotoResult(listLoteryResult.get(k));
                            result = StringConvert.stringConcat(listLoteryResult.get(k + 1));
                            loto = result.substring(i, i + 1) + result.substring(j, j + 1);

                            if (LotoUtils.findNotLotoResult(lotoResult, loto, dao)) {
                                dodaicau++;
                            } else {
                                break;
                            }

                        }

                        //kiem tra do dai cua cau
                        if (dodaicau >= cau) {
                            cauLoto = new CauLoto();
                            cauLoto.setCau(dodaicau);
                            cauLoto.setLoto(capLoto);
                            cauLoto.setVitri1(i);
                            cauLoto.setVitri2(j);

                            listCauLoto.add(cauLoto);
                        }
                    }

                }
            }
        }

        return listCauLoto;
    }

    public List<CauLoto> findCauLotoDacbiet(String code, String sDate, String eDate, int cau, boolean cham) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLoteryResult = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<CauLoto> listCauLoto = new ArrayList<>();
        if (listLoteryResult.size() > 0) {
            String resultStart = StringConvert.stringConcat(listLoteryResult.get(0));
            String result = "";
            String lotoResult = "";
            String loto = "";
            String capLoto = "";
            CauLoto cauLoto = null;
            int dodaicau = 0;

            for (int i = 0; i < resultStart.length(); i++) {
                for (int j = 0; j < resultStart.length(); j++) {
                    if (i != j) {
                        dodaicau = 0;
                        result = resultStart;
                        capLoto = result.substring(i, i + 1) + result.substring(j, j + 1);
                        for (int k = 0; k < listLoteryResult.size() - 1; k++) {
                            lotoResult = StringConvert.subRight(listLoteryResult.get(k).getSpecial(), 2);
                            result = StringConvert.stringConcat(listLoteryResult.get(k + 1));
                            loto = result.substring(i, i + 1) + result.substring(j, j + 1);

                            if (LotoUtils.findLotoResultDacbiet(lotoResult, loto, cham)) {
                                dodaicau++;
                            } else {
                                break;
                            }

                        }

                        //kiem tra do dai cua cau
                        if (dodaicau >= cau) {
                            cauLoto = new CauLoto();
                            cauLoto.setCau(dodaicau);
                            cauLoto.setLoto(capLoto);
                            cauLoto.setVitri1(i);
                            cauLoto.setVitri2(j);

                            listCauLoto.add(cauLoto);
                        }
                    }

                }
            }
        }

        return listCauLoto;
    }

    public static void main(String[] args) {
        CauLotoResult cauLotoResult = new CauLotoResult();
        List<CauLoto> list = cauLotoResult.findCauLoto("XSTD", "2015-01-15", "2016-01-15", 3, 1, true);
        int max = 0;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (max < list.get(i).getCau()) {
                    max = list.get(i).getCau();
                }
            }

            System.out.println("max=========" + max);
        }

    }
}
