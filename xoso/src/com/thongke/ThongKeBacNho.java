/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thongke;

import com.bean.LotoCam;
import com.bean.LotoCap;
import com.bean.LotoOfDacBiet;
import com.bean.LotteryResult;
import com.dao.LotteryResultDAO;
import com.utils.LotoUtils;
import com.utils.StringConvert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author HanhDung
 */
public class ThongKeBacNho {

    public List<LotoOfDacBiet> findLotoBachThuOfDacBiet(String date, int maxlanve, String lotodb) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResultAsc("XSTD", "2009-07-01", date);

        List<LotoOfDacBiet> list = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            String loto = "";
            list = new ArrayList<>();
            LotoOfDacBiet lotoOfDacBiet = null;
            LotteryResult lotteryResult = null;
            int dem = 0;
            String resultLoto = "";
            LotoCap lotoCap = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                loto = StringConvert.subRight(lotteryResult.getSpecial(), 2);
                if (loto.equals(lotodb)) {
                    lotoOfDacBiet = new LotoOfDacBiet();
                    lotoOfDacBiet.setSpecial(lotteryResult.getSpecial());
                    lotoOfDacBiet.setNgayve(lotteryResult.getOpen_date());
                    if (i + 1 < listLottery.size()) {
                        List<LotoCap> listLotoCap1 = new ArrayList<>();
                        resultLoto = StringConvert.lotoResult(listLottery.get(i + 1));
                        for (int j = 0; j < 100; j++) {
                            lotoCap = new LotoCap();
                            if (j < 10) {
                                lotoCap.setCap("0" + j);
                            } else {
                                lotoCap.setCap("" + j);
                            }

                            if (resultLoto.indexOf(lotoCap.getCap()) > -1) {
                                listLotoCap1.add(lotoCap);
                            }
                        }
                        lotoOfDacBiet.setListLotoCap1(listLotoCap1);
                    }

                    if (i + 2 < listLottery.size()) {
                        List<LotoCap> listLotoCap2 = new ArrayList<>();
                        resultLoto = StringConvert.lotoResult(listLottery.get(i + 2));
                        for (int j = 0; j < 100; j++) {
                            lotoCap = new LotoCap();
                            if (j < 10) {
                                lotoCap.setCap("0" + j);
                            } else {
                                lotoCap.setCap("" + j);
                            }
                            if (resultLoto.contains(lotoCap.getCap())) {
                                listLotoCap2.add(lotoCap);
                            }
                        }
                        lotoOfDacBiet.setListLotoCap2(listLotoCap2);
                    }

                    if (i + 3 < listLottery.size()) {
                        List<LotoCap> listLotoCap3 = new ArrayList<>();
                        resultLoto = StringConvert.lotoResult(listLottery.get(i + 3));
                        for (int j = 0; j < 100; j++) {
                            lotoCap = new LotoCap();
                            if (j < 10) {
                                lotoCap.setCap("0" + j);
                            } else {
                                lotoCap.setCap("" + j);
                            }
                            if (resultLoto.contains(lotoCap.getCap())) {
                                listLotoCap3.add(lotoCap);
                            }
                        }
                        lotoOfDacBiet.setListLotoCap3(listLotoCap3);
                    }

                    list.add(lotoOfDacBiet);

                    dem++;
                    if (dem == maxlanve) {
                        break;
                    }
                }
            }

        }

        return list;
    }

    public List<LotoCap> countLotoBachThuCap(List<LotoOfDacBiet> list) {

        LotoCap lotoCap1 = null;
        List<LotoCap> listLotoCap1 = null;
        for (int k = 0; k < 100; k++) {
            lotoCap1 = new LotoCap();
            if (k < 10) {
                lotoCap1.setCap("0" + k);
            } else {
                lotoCap1.setCap("" + k);
            }

            if (list != null && !list.isEmpty()) {
                LotoOfDacBiet lotoOfDacBiet = null;
                List<LotoCap> listLotoCap = null;
                LotoCap lotoCap = null;
                for (int i = 0; i < list.size(); i++) {
                    lotoOfDacBiet = list.get(i);
                    listLotoCap = lotoOfDacBiet.getListLotoCap1();
                    if(listLotoCap != null) for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve1(lotoCap1.getSolanve1() + 1);
                        }
                    }
                    listLotoCap = lotoOfDacBiet.getListLotoCap2();
                    if(listLotoCap != null) for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve2(lotoCap1.getSolanve2() + 1);
                        }
                    }
                    listLotoCap = lotoOfDacBiet.getListLotoCap3();
                    if(listLotoCap != null) for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve3(lotoCap1.getSolanve3() + 1);
                        }
                    }
                }
            }
            if (listLotoCap1 == null) {
                listLotoCap1 = new ArrayList<>();
            }
            listLotoCap1.add(lotoCap1);
        }

        return listLotoCap1;
    }

    public List<LotoCap> sapxepBachThu(List<LotoCap> list, int order) {
        //0= tu be den lon
        //1=tu lon den be
        LotoCap lotoCap = null;
        if (order == 0) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getSolanve1() + list.get(i).getSolanve2() + list.get(i).getSolanve3() > list.get(j).getSolanve1() + list.get(j).getSolanve2() + list.get(j).getSolanve3()) {
                        lotoCap = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, lotoCap);
                    }
                }
            }
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getSolanve1() + list.get(i).getSolanve2() + list.get(i).getSolanve3() < list.get(j).getSolanve1() + list.get(j).getSolanve2() + list.get(j).getSolanve3()) {
                        lotoCap = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, lotoCap);
                    }
                }
            }
        }

        return list;
    }

    public List<LotoOfDacBiet> findLotoOfDacBiet(String date, int maxlanve, String lotodb, int step) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResultAsc("XSTD", "2009-07-01", date);

        List<LotoOfDacBiet> list = null;

        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        if (listLottery != null && !listLottery.isEmpty()) {
            String loto = "";
            list = new ArrayList<>();
            LotoOfDacBiet lotoOfDacBiet = null;
            LotteryResult lotteryResult = null;
            int dem = 0;
            String resultLoto = "";
            LotoCap lotoCap = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                loto = StringConvert.subRight(lotteryResult.getSpecial(), 2);
                if (loto.equals(lotodb)) {
                    lotoOfDacBiet = new LotoOfDacBiet();
                    lotoOfDacBiet.setSpecial(lotteryResult.getSpecial());
                    lotoOfDacBiet.setNgayve(lotteryResult.getOpen_date());
                    List<LotoCap> listLotoCap = new ArrayList<>();
                    if (i + step < listLottery.size()) {
                        resultLoto = StringConvert.lotoResult(listLottery.get(i + step));
                        for (int j = 0; j < capLoto.length; j++) {
                            lotoCap = new LotoCap();
                            lotoCap.setCap(capLoto[j]);
                            lotoCap.setLoto1("no");
                            for (StringTokenizer stringTokenizer = new StringTokenizer(capLoto[j], "-"); stringTokenizer.hasMoreTokens();) {
                                String token = stringTokenizer.nextToken();
                                while (resultLoto.indexOf(token) > -1) {
                                    if (lotoCap.getLoto1().equals("no")) {
                                        lotoCap.setLoto1(token);
                                    } else {
                                        lotoCap.setLoto1(lotoCap.getLoto1() + "," + token);
                                    }
                                    if (resultLoto.indexOf(token) == 0) {
                                        resultLoto = resultLoto.substring(resultLoto.indexOf(token) + 2, resultLoto.length());
                                    } else if (resultLoto.indexOf(token) + 2 == resultLoto.length()) {
                                        resultLoto = resultLoto.substring(0, resultLoto.indexOf(token));
                                    } else {
                                        resultLoto = resultLoto.substring(0, resultLoto.indexOf(token)) + resultLoto.substring(resultLoto.indexOf(token) + 2, resultLoto.length());
                                    }
                                }
                            }
                            listLotoCap.add(lotoCap);
                        }

                        lotoOfDacBiet.setListLotoCap1(listLotoCap);
                    }

                    list.add(lotoOfDacBiet);

                    dem++;
                    if (dem == maxlanve) {
                        break;
                    }
                }
            }

        }

        return list;
    }

    public List<LotoCap> countLotoCap(List<LotoOfDacBiet> list) {
        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        LotoCap lotoCap1 = null;
        List<LotoCap> listLotoCap1 = null;
        for (int k = 0; k < capLoto.length; k++) {
            lotoCap1 = new LotoCap();
            lotoCap1.setCap(capLoto[k]);
            if (list != null && !list.isEmpty()) {
                LotoOfDacBiet lotoOfDacBiet = null;
                List<LotoCap> listLotoCap = null;
                LotoCap lotoCap = null;
                for (int i = 0; i < list.size(); i++) {
                    lotoOfDacBiet = list.get(i);
                    listLotoCap = lotoOfDacBiet.getListLotoCap1();
                    if (listLotoCap != null && !listLotoCap.isEmpty()) {
                        for (int j = 0; j < listLotoCap.size(); j++) {
                            lotoCap = listLotoCap.get(j);
                            if (!lotoCap.getLoto1().equals("no")) {
                                if (lotoCap.getCap().equals(capLoto[k])) {
                                    lotoCap1.setSolanve1(lotoCap1.getSolanve1() + 1);
                                }
                            }
                        }
                    }

                }
            }
            if (listLotoCap1 == null) {
                listLotoCap1 = new ArrayList<>();
            }
            listLotoCap1.add(lotoCap1);
        }

        return listLotoCap1;
    }

    public List<LotoCap> countLotoCam(List<LotoCam> list, boolean isBachThu) {

        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        LotoCap lotoCap1 = null;
        List<LotoCap> listLotoCap1 = null;
        for (int k = 0; k < capLoto.length; k++) {
            if (isBachThu) {
                for (StringTokenizer stringTokenizer = new StringTokenizer(capLoto[k], "-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    lotoCap1 = new LotoCap();
                    lotoCap1.setCap(token);

                    if (list != null && !list.isEmpty()) {
                        LotoCam lotoCam = null;
                        List<LotoCap> listLotoCap = null;
                        LotoCap lotoCap = null;
                        for (int i = 0; i < list.size(); i++) {
                            lotoCam = list.get(i);
                            listLotoCap = lotoCam.getListLotoCap1();
                            for (int j = 0; j < listLotoCap.size(); j++) {
                                lotoCap = listLotoCap.get(j);
                                if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                                    lotoCap1.setSolanve1(lotoCap1.getSolanve1() + 1);
                                }
                            }
                            listLotoCap = lotoCam.getListLotoCap2();
                            for (int j = 0; j < listLotoCap.size(); j++) {
                                lotoCap = listLotoCap.get(j);
                                if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                                    lotoCap1.setSolanve2(lotoCap1.getSolanve2() + 1);
                                }
                            }
                            listLotoCap = lotoCam.getListLotoCap3();
                            for (int j = 0; j < listLotoCap.size(); j++) {
                                lotoCap = listLotoCap.get(j);
                                if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                                    lotoCap1.setSolanve3(lotoCap1.getSolanve3() + 1);
                                }
                            }
                        }
                    }
                }
            } else {
                lotoCap1 = new LotoCap();
                lotoCap1.setCap(capLoto[k]);

                if (list != null && !list.isEmpty()) {
                    LotoCam lotoCam = null;
                    List<LotoCap> listLotoCap = null;
                    LotoCap lotoCap = null;
                    for (int i = 0; i < list.size(); i++) {
                        lotoCam = list.get(i);
                        listLotoCap = lotoCam.getListLotoCap1();
                        for (int j = 0; j < listLotoCap.size(); j++) {
                            lotoCap = listLotoCap.get(j);
                            if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                                lotoCap1.setSolanve1(lotoCap1.getSolanve1() + 1);
                            }
                        }
                        listLotoCap = lotoCam.getListLotoCap2();
                        for (int j = 0; j < listLotoCap.size(); j++) {
                            lotoCap = listLotoCap.get(j);
                            if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                                lotoCap1.setSolanve2(lotoCap1.getSolanve2() + 1);
                            }
                        }
                        listLotoCap = lotoCam.getListLotoCap3();
                        for (int j = 0; j < listLotoCap.size(); j++) {
                            lotoCap = listLotoCap.get(j);
                            if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                                lotoCap1.setSolanve3(lotoCap1.getSolanve3() + 1);
                            }
                        }
                    }
                }
            }

            if (listLotoCap1 == null) {
                listLotoCap1 = new ArrayList<>();
            }
            listLotoCap1.add(lotoCap1);
        }

        return listLotoCap1;
    }

    public List<LotoCap> sapxep(List<LotoCap> list, int order) {
        //0= tu be den lon
        //1=tu lon den be
        LotoCap lotoCap = null;
        if (order == 0) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getSolanve1() > list.get(j).getSolanve1()) {
                        lotoCap = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, lotoCap);
                    }
                }
            }
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getSolanve1() < list.get(j).getSolanve1()) {
                        lotoCap = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, lotoCap);
                    }
                }
            }
        }

        return list;
    }

    public List<LotoCam> findLotoCam(String date, int maxlanve, String loto, boolean opt, boolean isBachThu) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResultAsc("XSTD", "2009-07-01", date);
        List<LotoCam> listLotoCam = null;
        List<LotoCam> listLotoCam1 = null;
        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        if (listLottery != null && !listLottery.isEmpty()) {
            listLotoCam = new ArrayList<>();
            String lotoResult = "";
            int countDauOrDuoi = 0;
            LotoCam lotoCam = null;
            LotteryResult lotteryResult = null;
            int dem = 0;
            LotoCap lotoCap = null;
            String strLoto = "";
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.lotoResult(lotteryResult);
                countDauOrDuoi = LotoUtils.countDauDuoi(lotoResult, loto, opt);
                if (countDauOrDuoi == 0) {
                    lotoCam = new LotoCam();
                    lotoCam.setLoto(loto);
                    for (int j = 0; j < 10; j++) {
                        if (!loto.equals("" + j)) {
                            countDauOrDuoi = LotoUtils.countDauDuoi(lotoResult, "" + j, opt);
                            if (countDauOrDuoi == 0) {
                                lotoCam.setLoto(lotoCam.getLoto() + j);
                            }
                        }
                    }
                    lotoCam.setNgayve(lotteryResult.getOpen_date());

                    if (i + 1 < listLottery.size()) {
                        List<LotoCap> listLotoCap1 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 1));

                        for (int j = 0; j < capLoto.length; j++) {
                            if (!isBachThu) {
                                lotoCap = new LotoCap();
                                strLoto = capLoto[j].split("-")[0];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap.setCap(capLoto[j]);
                                    lotoCap.setLoto1(strLoto);
                                }

                                strLoto = capLoto[j].split("-")[1];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap.setCap(capLoto[j]);
                                    lotoCap.setLoto1(strLoto);

                                }

                                if (lotoCap.getCap() != null && !"".equals(lotoCap.getCap())) {
                                    listLotoCap1.add(lotoCap);
                                    lotoCam.setListLotoCap1(listLotoCap1);
                                }
                            } else {
                                strLoto = capLoto[j].split("-")[0];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(strLoto);
                                    lotoCap.setLoto1(strLoto);
                                    listLotoCap1.add(lotoCap);
                                    lotoCam.setListLotoCap1(listLotoCap1);
                                }

                                strLoto = capLoto[j].split("-")[1];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(strLoto);
                                    lotoCap.setLoto1(strLoto);
                                    listLotoCap1.add(lotoCap);
                                    lotoCam.setListLotoCap1(listLotoCap1);
                                }
                            }

                        }

                    }

                    if (i + 2 < listLottery.size()) {
                        List<LotoCap> listLotoCap2 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 2));

                        for (int j = 0; j < capLoto.length; j++) {
                            if (!isBachThu) {
                                lotoCap = new LotoCap();
                                strLoto = capLoto[j].split("-")[0];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap.setCap(capLoto[j]);
                                    lotoCap.setLoto1(strLoto);
                                }

                                strLoto = capLoto[j].split("-")[1];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap.setCap(capLoto[j]);
                                    lotoCap.setLoto1(strLoto);

                                }

                                if (lotoCap.getCap() != null && !"".equals(lotoCap.getCap())) {
                                    listLotoCap2.add(lotoCap);
                                    lotoCam.setListLotoCap2(listLotoCap2);
                                }
                            } else {
                                strLoto = capLoto[j].split("-")[0];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(strLoto);
                                    lotoCap.setLoto1(strLoto);
                                    listLotoCap2.add(lotoCap);
                                    lotoCam.setListLotoCap2(listLotoCap2);
                                }

                                strLoto = capLoto[j].split("-")[1];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(strLoto);
                                    lotoCap.setLoto1(strLoto);
                                    listLotoCap2.add(lotoCap);
                                    lotoCam.setListLotoCap2(listLotoCap2);
                                }
                            }
                        }
                    }

                    if (i + 3 < listLottery.size()) {
                        List<LotoCap> listLotoCap3 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 3));

                        for (int j = 0; j < capLoto.length; j++) {
                            if (!isBachThu) {
                                lotoCap = new LotoCap();
                                strLoto = capLoto[j].split("-")[0];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap.setCap(capLoto[j]);
                                    lotoCap.setLoto1(strLoto);
                                }

                                strLoto = capLoto[j].split("-")[1];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap.setCap(capLoto[j]);
                                    lotoCap.setLoto1(strLoto);

                                }

                                if (lotoCap.getCap() != null && !"".equals(lotoCap.getCap())) {
                                    listLotoCap3.add(lotoCap);
                                    lotoCam.setListLotoCap3(listLotoCap3);
                                }
                            } else {
                                strLoto = capLoto[j].split("-")[0];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(strLoto);
                                    lotoCap.setLoto1(strLoto);
                                    listLotoCap3.add(lotoCap);
                                    lotoCam.setListLotoCap3(listLotoCap3);
                                }

                                strLoto = capLoto[j].split("-")[1];
                                if (lotoResult.indexOf(strLoto) > -1) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(strLoto);
                                    lotoCap.setLoto1(strLoto);
                                    listLotoCap3.add(lotoCap);
                                    lotoCam.setListLotoCap3(listLotoCap3);
                                }
                            }
                        }
                    }

                    listLotoCam.add(lotoCam);
//                   dem++;
//                   if(dem==maxlanve){break;}
                }
            }

            listLotoCam1 = new ArrayList<>();

            for (int i = listLotoCam.size() - 1; i >= 0; i--) {
                dem++;
                listLotoCam1.add(listLotoCam.get(i));
                if (dem == maxlanve) {
                    break;
                }
            }
        }
        return listLotoCam1;
    }

    public List<LotoCam> findLotoCapRoi(String date, int maxlanve, String loto, int nhay) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResultAsc("XSTD", "2009-07-01", date);
        List<LotoCam> listLotoCam = null;
        List<LotoCam> listLotoCam1 = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            listLotoCam = new ArrayList<>();
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            int result = 0;
            LotoCap lotoCap = null;
            LotoCam lotoCam = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.lotoResult(lotteryResult);
                result = LotoUtils.countLotoResult(lotoResult, loto);
                if (result >= nhay) {
                    lotoCam = new LotoCam();
                    lotoCam.setLoto(loto);
                    lotoCam.setNgayve(lotteryResult.getOpen_date());
                    lotoCap = null;
                    if (i + 1 < listLottery.size()) {
                        List<LotoCap> listLotoCap1 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 1));
                        for (StringTokenizer stringTokenizer = new StringTokenizer(loto, "-"); stringTokenizer.hasMoreTokens();) {
                            String token = stringTokenizer.nextToken();
                            result = LotoUtils.countLotoResult(lotoResult, token, false);
                            if (result > 0) {
                                if (lotoCap == null) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(loto);
                                }
                                for (int j = 0; j < result; j++) {
                                    if (lotoCap.getLoto1() == null) {
                                        lotoCap.setLoto1(token);
                                    } else {
                                        lotoCap.setLoto1(lotoCap.getLoto1() + "," + token);
                                    }
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap1.add(lotoCap);
                        }
                        lotoCam.setListLotoCap1(listLotoCap1);
                    }

                    lotoCap = null;
                    if (i + 2 < listLottery.size()) {
                        List<LotoCap> listLotoCap2 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 2));
                        for (StringTokenizer stringTokenizer = new StringTokenizer(loto, "-"); stringTokenizer.hasMoreTokens();) {
                            String token = stringTokenizer.nextToken();
                            result = LotoUtils.countLotoResult(lotoResult, token, false);
                            if (result > 0) {
                                if (lotoCap == null) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(loto);
                                }
                                for (int j = 0; j < result; j++) {
                                    if (lotoCap.getLoto1() == null) {
                                        lotoCap.setLoto1(token);
                                    } else {
                                        lotoCap.setLoto1(lotoCap.getLoto1() + "," + token);
                                    }
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap2.add(lotoCap);
                        }
                        lotoCam.setListLotoCap2(listLotoCap2);
                    }

                    lotoCap = null;
                    if (i + 3 < listLottery.size()) {
                        List<LotoCap> listLotoCap3 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 3));
                        for (StringTokenizer stringTokenizer = new StringTokenizer(loto, "-"); stringTokenizer.hasMoreTokens();) {
                            String token = stringTokenizer.nextToken();
                            result = LotoUtils.countLotoResult(lotoResult, token, false);
                            if (result > 0) {
                                if (lotoCap == null) {
                                    lotoCap = new LotoCap();
                                    lotoCap.setCap(loto);
                                }
                                for (int j = 0; j < result; j++) {
                                    if (lotoCap.getLoto1() == null) {
                                        lotoCap.setLoto1(token);
                                    } else {
                                        lotoCap.setLoto1(lotoCap.getLoto1() + "," + token);
                                    }
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap3.add(lotoCap);
                        }
                        lotoCam.setListLotoCap3(listLotoCap3);
                    }
                    listLotoCam.add(lotoCam);
                }
            }

            listLotoCam1 = new ArrayList<>();
            int dem = 0;
            for (int i = listLotoCam.size() - 1; i >= 0; i--) {
                dem++;
                listLotoCam1.add(listLotoCam.get(i));
                if (dem == maxlanve) {
                    break;
                }
            }
        }

        return listLotoCam1;
    }

    public List<LotoCam> findLotoRoi(String date, int maxlanve, String loto, int nhay) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResultAsc("XSTD", "2009-07-01", date);
        List<LotoCam> listLotoCam = null;
        List<LotoCam> listLotoCam1 = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            listLotoCam = new ArrayList<>();
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            int result = 0;
            LotoCap lotoCap = null;
            LotoCam lotoCam = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.lotoResult(lotteryResult);
                result = LotoUtils.countLotoResult(lotoResult, loto, false);
                if (result >= nhay) {
                    lotoCam = new LotoCam();
                    lotoCam.setLoto(loto);
                    lotoCam.setNgayve(lotteryResult.getOpen_date());
                    lotoCap = null;
                    if (i + 1 < listLottery.size()) {
                        List<LotoCap> listLotoCap1 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 1));
                        result = LotoUtils.countLotoResult(lotoResult, loto, false);
                        if (result > 0) {
                            if (lotoCap == null) {
                                lotoCap = new LotoCap();
                                lotoCap.setCap(loto);
                            }
                            for (int j = 0; j < result; j++) {
                                if (lotoCap.getLoto1() == null) {
                                    lotoCap.setLoto1(loto);
                                } else {
                                    lotoCap.setLoto1(lotoCap.getLoto1() + "," + loto);
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap1.add(lotoCap);
                        }
                        lotoCam.setListLotoCap1(listLotoCap1);
                    }

                    lotoCap = null;
                    if (i + 2 < listLottery.size()) {
                        List<LotoCap> listLotoCap2 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 2));
                        result = LotoUtils.countLotoResult(lotoResult, loto, false);
                        if (result > 0) {
                            if (lotoCap == null) {
                                lotoCap = new LotoCap();
                                lotoCap.setCap(loto);
                            }
                            for (int j = 0; j < result; j++) {
                                if (lotoCap.getLoto1() == null) {
                                    lotoCap.setLoto1(loto);
                                } else {
                                    lotoCap.setLoto1(lotoCap.getLoto1() + "," + loto);
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap2.add(lotoCap);
                        }
                        lotoCam.setListLotoCap2(listLotoCap2);
                    }

                    lotoCap = null;
                    if (i + 3 < listLottery.size()) {
                        List<LotoCap> listLotoCap3 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 3));
                        result = LotoUtils.countLotoResult(lotoResult, loto, false);
                        if (result > 0) {
                            if (lotoCap == null) {
                                lotoCap = new LotoCap();
                                lotoCap.setCap(loto);
                            }
                            for (int j = 0; j < result; j++) {
                                if (lotoCap.getLoto1() == null) {
                                    lotoCap.setLoto1(loto);
                                } else {
                                    lotoCap.setLoto1(lotoCap.getLoto1() + "," + loto);
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap3.add(lotoCap);
                        }
                        lotoCam.setListLotoCap3(listLotoCap3);
                    }

                    lotoCap = null;
                    if (i + 4 < listLottery.size()) {
                        List<LotoCap> listLotoCap4 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 4));
                        result = LotoUtils.countLotoResult(lotoResult, loto, false);
                        if (result > 0) {
                            if (lotoCap == null) {
                                lotoCap = new LotoCap();
                                lotoCap.setCap(loto);
                            }
                            for (int j = 0; j < result; j++) {
                                if (lotoCap.getLoto1() == null) {
                                    lotoCap.setLoto1(loto);
                                } else {
                                    lotoCap.setLoto1(lotoCap.getLoto1() + "," + loto);
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap4.add(lotoCap);
                        }
                        lotoCam.setListLotoCap4(listLotoCap4);
                    }

                    lotoCap = null;
                    if (i + 5 < listLottery.size()) {
                        List<LotoCap> listLotoCap5 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 5));
                        result = LotoUtils.countLotoResult(lotoResult, loto, false);
                        if (result > 0) {
                            if (lotoCap == null) {
                                lotoCap = new LotoCap();
                                lotoCap.setCap(loto);
                            }
                            for (int j = 0; j < result; j++) {
                                if (lotoCap.getLoto1() == null) {
                                    lotoCap.setLoto1(loto);
                                } else {
                                    lotoCap.setLoto1(lotoCap.getLoto1() + "," + loto);
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap5.add(lotoCap);
                        }
                        lotoCam.setListLotoCap5(listLotoCap5);
                    }

                    lotoCap = null;
                    if (i + 6 < listLottery.size()) {
                        List<LotoCap> listLotoCap6 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 6));
                        result = LotoUtils.countLotoResult(lotoResult, loto, false);
                        if (result > 0) {
                            if (lotoCap == null) {
                                lotoCap = new LotoCap();
                                lotoCap.setCap(loto);
                            }
                            for (int j = 0; j < result; j++) {
                                if (lotoCap.getLoto1() == null) {
                                    lotoCap.setLoto1(loto);
                                } else {
                                    lotoCap.setLoto1(lotoCap.getLoto1() + "," + loto);
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap6.add(lotoCap);
                        }
                        lotoCam.setListLotoCap6(listLotoCap6);
                    }

                    lotoCap = null;
                    if (i + 7 < listLottery.size()) {
                        List<LotoCap> listLotoCap7 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 7));
                        result = LotoUtils.countLotoResult(lotoResult, loto, false);
                        if (result > 0) {
                            if (lotoCap == null) {
                                lotoCap = new LotoCap();
                                lotoCap.setCap(loto);
                            }
                            for (int j = 0; j < result; j++) {
                                if (lotoCap.getLoto1() == null) {
                                    lotoCap.setLoto1(loto);
                                } else {
                                    lotoCap.setLoto1(lotoCap.getLoto1() + "," + loto);
                                }
                            }
                        }

                        if (lotoCap != null) {
                            listLotoCap7.add(lotoCap);
                        }
                        lotoCam.setListLotoCap7(listLotoCap7);
                    }

                    listLotoCam.add(lotoCam);
                }
            }

            listLotoCam1 = new ArrayList<>();
            int dem = 0;
            for (int i = listLotoCam.size() - 1; i >= 0; i--) {
                dem++;
                listLotoCam1.add(listLotoCam.get(i));
                if (dem == maxlanve) {
                    break;
                }
            }
        }

        return listLotoCam1;
    }

    public List<LotoCap> countLotoCapRoi(List<LotoCam> list, String loto) {

        LotoCap lotoCap1 = null;
        List<LotoCap> listLotoCap1 = null;

        if (list != null && !list.isEmpty()) {
            lotoCap1 = new LotoCap();
            lotoCap1.setCap(loto);
            LotoCam lotoCam = null;
            List<LotoCap> listLotoCap = null;
            LotoCap lotoCap = null;
            for (int i = 0; i < list.size(); i++) {
                lotoCam = list.get(i);
                listLotoCap = lotoCam.getListLotoCap1();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve1(lotoCap1.getSolanve1() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap2();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve2(lotoCap1.getSolanve2() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap3();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve3(lotoCap1.getSolanve3() + 1);
                        }
                    }
                }
            }

            if (listLotoCap1 == null) {
                listLotoCap1 = new ArrayList<>();
            }
            listLotoCap1.add(lotoCap1);
        }

        return listLotoCap1;
    }

    public List<LotoCap> countLotoRoi(List<LotoCam> list, String loto) {

        LotoCap lotoCap1 = null;
        List<LotoCap> listLotoCap1 = null;

        if (list != null && !list.isEmpty()) {
            lotoCap1 = new LotoCap();
            lotoCap1.setCap(loto);
            LotoCam lotoCam = null;
            List<LotoCap> listLotoCap = null;
            LotoCap lotoCap = null;
            for (int i = 0; i < list.size(); i++) {
                lotoCam = list.get(i);
                listLotoCap = lotoCam.getListLotoCap1();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve1(lotoCap1.getSolanve1() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap2();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve2(lotoCap1.getSolanve2() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap3();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve3(lotoCap1.getSolanve3() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap4();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve4(lotoCap1.getSolanve4() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap5();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve5(lotoCap1.getSolanve5() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap6();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve6(lotoCap1.getSolanve6() + 1);
                        }
                    }
                }

                listLotoCap = lotoCam.getListLotoCap7();
                if (listLotoCap != null && !listLotoCap.isEmpty()) {
                    for (int j = 0; j < listLotoCap.size(); j++) {
                        lotoCap = listLotoCap.get(j);
                        if (lotoCap.getCap().equals(lotoCap1.getCap())) {
                            lotoCap1.setSolanve7(lotoCap1.getSolanve7() + 1);
                        }
                    }
                }
            }

            if (listLotoCap1 == null) {
                listLotoCap1 = new ArrayList<>();
            }
            listLotoCap1.add(lotoCap1);
        }

        return listLotoCap1;
    }

    public List<LotoCam> findLotoVeTheoLoto3Ngay(String date, int maxlanve, String loto, int nhay) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResultAsc("XSTD", "2009-07-01", date);
        List<LotoCam> listLotoCam = null;
        List<LotoCam> listLotoCam1 = null;

        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        if (listLottery != null && !listLottery.isEmpty()) {
            listLotoCam = new ArrayList<>();
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            int result = 0;
            LotoCap lotoCap = null;
            LotoCam lotoCam = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.lotoResult(lotteryResult);
                result = LotoUtils.countLotoResult(lotoResult, loto);
                if (result >= nhay) {
                    lotoCam = new LotoCam();
                    lotoCam.setLoto(loto);
                    lotoCam.setNgayve(lotteryResult.getOpen_date());
                    
                    for (StringTokenizer stringTokenizer = new StringTokenizer(loto,"-"); stringTokenizer.hasMoreTokens();) {
                        String token = stringTokenizer.nextToken();
                        result = LotoUtils.countLotoResult(lotoResult, token, false);
                        if(result>0){
                            for (int j = 0; j < result; j++) {
                                if(lotoCam.getLoto().equals(loto)){lotoCam.setLoto(token);}
                                else{lotoCam.setLoto(lotoCam.getLoto()+","+token);}
                            }
                        }
                    }
                    
                    lotoCap = null;
                    if (i + 1 < listLottery.size()) {
                        List<LotoCap> listLotoCap1 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 1));
                        for (int k = 0; k < capLoto.length; k++) {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(capLoto[k], "-"); stringTokenizer.hasMoreTokens();) {
                                String token = stringTokenizer.nextToken();
                                result = LotoUtils.countLotoResult(lotoResult, token, false);
                                if (result > 0) {
                                    if (lotoCap == null) {
                                        lotoCap = new LotoCap();
                                        lotoCap.setCap(capLoto[k]);
                                    }
                                    for (int j = 0; j < result; j++) {
                                        if (lotoCap.getLoto1() == null) {
                                            lotoCap.setLoto1(token);
                                        } else {
                                            lotoCap.setLoto1(lotoCap.getLoto1() + "," + token);
                                        }
                                    }
                                }
                            }

                            if (lotoCap != null) {
                                listLotoCap1.add(lotoCap);
                            }
                        }
                        lotoCam.setListLotoCap1(listLotoCap1);
                    }

                    lotoCap = null;
                    if (i + 2 < listLottery.size()) {
                        List<LotoCap> listLotoCap2 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 2));
                        for (int k = 0; k < capLoto.length; k++) {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(capLoto[k], "-"); stringTokenizer.hasMoreTokens();) {
                                String token = stringTokenizer.nextToken();
                                result = LotoUtils.countLotoResult(lotoResult, token, false);
                                if (result > 0) {
                                    if (lotoCap == null) {
                                        lotoCap = new LotoCap();
                                        lotoCap.setCap(capLoto[k]);
                                    }
                                    for (int j = 0; j < result; j++) {
                                        if (lotoCap.getLoto1() == null) {
                                            lotoCap.setLoto1(token);
                                        } else {
                                            lotoCap.setLoto1(lotoCap.getLoto1() + "," + token);
                                        }
                                    }
                                }
                            }

                            if (lotoCap != null) {
                                listLotoCap2.add(lotoCap);
                            }
                        }
                        lotoCam.setListLotoCap2(listLotoCap2);
                    }

                    lotoCap = null;
                    if (i + 3 < listLottery.size()) {
                        List<LotoCap> listLotoCap3 = new ArrayList<>();
                        lotoResult = StringConvert.lotoResult(listLottery.get(i + 3));
                        for (int k = 0; k < capLoto.length; k++) {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(capLoto[k], "-"); stringTokenizer.hasMoreTokens();) {
                                String token = stringTokenizer.nextToken();
                                result = LotoUtils.countLotoResult(lotoResult, token, false);
                                if (result > 0) {
                                    if (lotoCap == null) {
                                        lotoCap = new LotoCap();
                                        lotoCap.setCap(capLoto[k]);
                                    }
                                    for (int j = 0; j < result; j++) {
                                        if (lotoCap.getLoto1() == null) {
                                            lotoCap.setLoto1(token);
                                        } else {
                                            lotoCap.setLoto1(lotoCap.getLoto1() + "," + token);
                                        }
                                    }
                                }
                            }

                            if (lotoCap != null) {
                                listLotoCap3.add(lotoCap);
                            }
                        }
                        lotoCam.setListLotoCap3(listLotoCap3);
                    }
                    listLotoCam.add(lotoCam);
                }
            }

            listLotoCam1 = new ArrayList<>();
            int dem = 0;
            for (int i = listLotoCam.size() - 1; i >= 0; i--) {
                dem++;
                listLotoCam1.add(listLotoCam.get(i));
                if (dem == maxlanve) {
                    break;
                }
            }
        }

        return listLotoCam1;
    }

    public static void main(String[] args) {
//        String str="10-20-30-40-30-20-50";
//        String token="20";
//        while(str.indexOf(token)>-1){            
//            if(str.indexOf(token)==0){
//                str=str.substring(str.indexOf(token)+2,str.length());
//                System.out.println("str=="+str);
//            }else if(str.indexOf(token)+2==str.length()){
//                str=str.substring(0,str.indexOf(token));
//                System.out.println("str=="+str);
//            }else{
//                str=str.substring(0,str.indexOf(token))+str.substring(str.indexOf(token)+2,str.length());
//                System.out.println("str=="+str);
//            }
//
//        } 

        ThongKeBacNho thongKeBacNho = new ThongKeBacNho();
        List<LotoCam> list = thongKeBacNho.findLotoVeTheoLoto3Ngay("2016-02-16", 30, "68-86", 2);
        if (list != null && !list.isEmpty()) {
            List<LotoCap> listLotoCap = null;
            LotoCap lotoCap = null;
            System.out.println("dodai===" + list.size());
//            listLotoCap = thongKeBacNho.countLotoRoi(list, "00");
//            for (Iterator<LotoCap> it = listLotoCap.iterator(); it.hasNext();) {
//                LotoCap lotoCap1 = it.next();
//                System.out.println(lotoCap1.getCap() + "====" + lotoCap1.getSolanve1() + "==" + lotoCap1.getSolanve2() + "==" + lotoCap1.getSolanve3() + "==" + lotoCap1.getSolanve4() + "==" + lotoCap1.getSolanve5() + "==" + lotoCap1.getSolanve6() + "==" + lotoCap1.getSolanve7());
//            }
            for (Iterator<LotoCam> it = list.iterator(); it.hasNext();) {
                LotoCam lotoCam = it.next();
                System.out.println(lotoCam.getLoto() + "====" + lotoCam.getNgayve());
//                listLotoCap = lotoCam.getListLotoCap1();
//                if (listLotoCap != null && !listLotoCap.isEmpty()) {
//                    for (int j = 0; j < listLotoCap.size(); j++) {
//                        lotoCap = listLotoCap.get(j);
//                        System.out.println(lotoCam.getNgayve() + "==caploto===" + lotoCap.getCap() + "===" + lotoCap.getLoto1() + "==" + lotoCap.getSolanve1());
//                    }
//                }
            }
        }
//        if(list!=null&&!list.isEmpty()){
//            LotoOfDacBiet lotoOfDacBiet=null;
//            List<LotoCap> listLotoCap=null;
//            LotoCap lotoCap=null;
//            for(int i=0;i<list.size();i++){
//                lotoOfDacBiet=list.get(i);
//                System.out.println("dd=="+lotoOfDacBiet.getNgayve()+"=="+lotoOfDacBiet.getSpecial());
//                listLotoCap=lotoOfDacBiet.getListLotoCap1();
//                for(int j=0;j<listLotoCap.size();j++){
//                    lotoCap=listLotoCap.get(j);
//                    //if(!lotoCap.getLoto().equals("no")){
//                        System.out.println("caploto==="+lotoCap.getCap()+"==="+lotoCap.getLoto1()+"=="+lotoCap.getSolanve1());
//                    //}
//                    
//                }
//            }
//        }
    }
}
