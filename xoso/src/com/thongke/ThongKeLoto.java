/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thongke;

import com.bean.DauDuoi;
import com.bean.Loto;
import com.bean.LotteryResult;
import com.bean.TanSuat;
import com.dao.LotteryResultDAO;
import com.utils.LotoUtils;
import static com.utils.LotoUtils.isLoto;
import com.utils.StringConvert;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class ThongKeLoto {

    public List<Loto> findLoto(String code, String sDate, String eDate) {

        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuat(code, sDate, eDate);

        if (list == null || list.isEmpty()) {
            return null;
        }

        int ganmax = 0;
        int chuave = 0;
        int tonglanve = 0;
        int songayve = 0;

        String startGanMax = "";
        String endGanMax = "";
        TanSuat tanSuat = null;
        Loto loto = null;
        List<Loto> listLoto = new ArrayList<>();
        List<TanSuat> listTanSuat = null;
        for (int i = 0; i < 100; i++) {
            ganmax = 0;
            chuave = 0;
            tonglanve = 0;
            songayve = 0;

            loto = new Loto();
            if (i < 10) {
                loto.setLoto("0" + i);
            } else {
                loto.setLoto("" + i);
            }
            for (int t = 0; t < list.size(); t++) {
                listTanSuat = list.get(t);
                for (int j = 0; j < listTanSuat.size(); j++) {
                    tanSuat = listTanSuat.get(j);
                    if (tanSuat.getCapso().equals(loto.getLoto())) {
                        if (tanSuat.getSolanve() > 0) {
                            if (loto.getNgaychuave() == 0) {
                                if (chuave == 0) {
                                    loto.setNgaychuave(-1);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                } else {
                                    loto.setNgaychuave(chuave);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                }
                            }
                            tonglanve = tonglanve + tanSuat.getSolanve();

                            if (ganmax < chuave) {
                                ganmax = chuave;
                                loto.setNgayketthucganmax(endGanMax);
                                loto.setNgaybatdauganmax(startGanMax);
                            }
                            chuave = 0;
                            songayve++;
                        } else {
                            if (chuave == 0) {
                                endGanMax = tanSuat.getOpenDate();
                            }
                            startGanMax = tanSuat.getOpenDate();
                            chuave++;
                        }
                    }
                }
            }

            if (ganmax < chuave) {
                ganmax = chuave;
            }
            if (loto.getNgaychuave() == 0) {
                loto.setNgaychuave(ganmax);
            }
            if (loto.getNgaychuave() == -1) {
                loto.setNgaychuave(0);
            }

            loto.setSolanxuathien(tonglanve);
            loto.setGanmax(ganmax);
            loto.setSongayve(songayve);

            listLoto.add(loto);
        }
        return listLoto;
    }

    public List<Loto> findLoto(String danLoto, String code, String sDate, String eDate) {
        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuat(code, sDate, eDate);

        if (list == null || list.isEmpty()) {
            return null;
        }

        int ganmax = 0;
        int chuave = 0;
        int tonglanve = 0;
        String startGanMax = "";
        String endGanMax = "";
        TanSuat tanSuat = null;
        Loto loto = null;
        List<Loto> listLoto = new ArrayList<>();
        List<TanSuat> listTanSuat = null;
        for (int i = 0; i < 100; i++) {
            ganmax = 0;
            chuave = 0;
            tonglanve = 0;

            loto = new Loto();
            if (i < 10) {
                loto.setLoto("0" + i);
            } else {
                loto.setLoto("" + i);
            }
            if (danLoto != null && !danLoto.contains(loto.getLoto())) {
                continue;
            }
            for (int t = 0; t < list.size(); t++) {
                listTanSuat = list.get(t);
                for (int j = 0; j < listTanSuat.size(); j++) {
                    tanSuat = listTanSuat.get(j);
                    if (tanSuat.getCapso().equals(loto.getLoto())) {
                        if (tanSuat.getSolanve() > 0) {
                            if (loto.getNgaychuave() == 0) {
                                if (chuave == 0) {
                                    loto.setNgaychuave(-1);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                } else {
                                    loto.setNgaychuave(chuave);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                }
                            }
                            tonglanve = tonglanve + tanSuat.getSolanve();

                            if (ganmax < chuave) {
                                ganmax = chuave;
                                loto.setNgayketthucganmax(endGanMax);
                                loto.setNgaybatdauganmax(startGanMax);
                            }
                            chuave = 0;
                        } else {
                            if (chuave == 0) {
                                endGanMax = tanSuat.getOpenDate();
                            }
                            startGanMax = tanSuat.getOpenDate();
                            chuave++;
                        }
                    }
                }
            }

            if (ganmax < chuave) {
                ganmax = chuave;
            }
            if (loto.getNgaychuave() == 0) {
                loto.setNgaychuave(ganmax);
            }
            if (loto.getNgaychuave() == -1) {
                loto.setNgaychuave(0);
            }

            loto.setSolanxuathien(tonglanve);
            loto.setGanmax(ganmax);

            listLoto.add(loto);
        }
        return listLoto;
    }

    public List<Loto> findLotoTheoThu(String danLoto, String thu, String code, String sDate, String eDate) {
        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuatTheoThu(code, thu, sDate, eDate);

        if (list == null || list.isEmpty()) {
            return null;
        }

        int ganmax = 0;
        int chuave = 0;
        int tonglanve = 0;
        String startGanMax = "";
        String endGanMax = "";
        TanSuat tanSuat = null;
        Loto loto = null;
        List<Loto> listLoto = new ArrayList<>();
        List<TanSuat> listTanSuat = null;
        for (int i = 0; i < 100; i++) {
            ganmax = 0;
            chuave = 0;
            tonglanve = 0;

            loto = new Loto();
            if (i < 10) {
                loto.setLoto("0" + i);
            } else {
                loto.setLoto("" + i);
            }
            if (danLoto != null && !danLoto.contains(loto.getLoto())) {
                continue;
            }
            for (int t = 0; t < list.size(); t++) {
                listTanSuat = list.get(t);
                for (int j = 0; j < listTanSuat.size(); j++) {
                    tanSuat = listTanSuat.get(j);
                    if (tanSuat.getCapso().equals(loto.getLoto())) {
                        if (tanSuat.getSolanve() > 0) {
                            if (loto.getNgaychuave() == 0) {
                                if (chuave == 0) {
                                    loto.setNgaychuave(-1);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                } else {
                                    loto.setNgaychuave(chuave);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                }
                            }
                            tonglanve = tonglanve + tanSuat.getSolanve();

                            if (ganmax < chuave) {
                                ganmax = chuave;
                                loto.setNgayketthucganmax(endGanMax);
                                loto.setNgaybatdauganmax(startGanMax);
                            }
                            chuave = 0;
                        } else {
                            if (chuave == 0) {
                                endGanMax = tanSuat.getOpenDate();
                            }
                            startGanMax = tanSuat.getOpenDate();
                            chuave++;
                        }
                    }
                }
            }

            if (ganmax < chuave) {
                ganmax = chuave;
            }
            if (loto.getNgaychuave() == 0) {
                loto.setNgaychuave(ganmax);
            }
            if (loto.getNgaychuave() == -1) {
                loto.setNgaychuave(0);
            }

            loto.setSolanxuathien(tonglanve);
            loto.setGanmax(ganmax);

            listLoto.add(loto);
        }
        return listLoto;
    }

    public List<Loto> findLotoCap(String code, String sDate, String eDate) {

        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuatTheoCap(code, sDate, eDate);

        if (list == null || list.isEmpty()) {
            return null;
        }

        int ganmax = 0;
        int chuave = 0;
        int tonglanve = 0;
        String startGanMax = "";
        String endGanMax = "";
        TanSuat tanSuat = null;
        Loto loto = null;
        List<Loto> listLoto = new ArrayList<>();
        List<TanSuat> listTanSuat = null;
        for (int i = 0; i < capLoto.length; i++) {
            ganmax = 0;
            chuave = 0;
            tonglanve = 0;

            loto = new Loto();
            loto.setLoto(capLoto[i]);

            for (int t = 0; t < list.size(); t++) {
                listTanSuat = list.get(t);
                for (int j = 0; j < listTanSuat.size(); j++) {
                    tanSuat = listTanSuat.get(j);
                    if (tanSuat.getCapso().equals(loto.getLoto())) {
                        if (tanSuat.getSolanve() > 0) {
                            if (loto.getNgaychuave() == 0) {
                                if (chuave == 0) {
                                    loto.setNgaychuave(-1);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                } else {
                                    loto.setNgaychuave(chuave);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                }
                            }

                            tonglanve = tonglanve + tanSuat.getSolanve();

                            if (ganmax < chuave) {
                                ganmax = chuave;
                                loto.setNgayketthucganmax(endGanMax);
                                loto.setNgaybatdauganmax(startGanMax);
                            }
                            chuave = 0;
                        } else {
                            if (chuave == 0) {
                                endGanMax = tanSuat.getOpenDate();
                            }
                            startGanMax = tanSuat.getOpenDate();
                            chuave++;
                        }
                    }
                }
            }

            if (ganmax < chuave) {
                ganmax = chuave;
            }
            if (loto.getNgaychuave() == 0) {
                loto.setNgaychuave(ganmax);
            }
            if (loto.getNgaychuave() == -1) {
                loto.setNgaychuave(0);
            }

            loto.setSolanxuathien(tonglanve);
            loto.setGanmax(ganmax);

            listLoto.add(loto);
        }
        return listLoto;
    }

    public List<Loto> findLotoDan(String code, String sDate, String eDate, String dan) {

        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuatTheoDan(code, sDate, eDate, dan);

        if (list == null || list.isEmpty()) {
            return null;
        }

        int ganmax = 0;
        int chuave = 0;
        int tonglanve = 0;
        String startGanMax = "";
        String endGanMax = "";
        TanSuat tanSuat = null;
        Loto loto = null;
        List<Loto> listLoto = new ArrayList<>();
        List<TanSuat> listTanSuat = null;

        loto = new Loto();
        loto.setLoto(dan);

        for (int t = 0; t < list.size(); t++) {
            listTanSuat = list.get(t);
            for (int j = 0; j < listTanSuat.size(); j++) {
                tanSuat = listTanSuat.get(j);
                if (tanSuat.getSolanve() > 0) {
                    if (loto.getNgaychuave() == 0) {
                        if (chuave == 0) {
                            loto.setNgaychuave(-1);
                            loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                        } else {
                            loto.setNgaychuave(chuave);
                            loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                        }
                    }

                    tonglanve = tonglanve + tanSuat.getSolanve();

                    if (ganmax < chuave) {
                        ganmax = chuave;
                        loto.setNgayketthucganmax(endGanMax);
                        loto.setNgaybatdauganmax(startGanMax);
                    }
                    chuave = 0;
                } else {
                    if (chuave == 0) {
                        endGanMax = tanSuat.getOpenDate();
                    }
                    startGanMax = tanSuat.getOpenDate();
                    chuave++;
                }
            }
        }

        if (ganmax < chuave) {
            ganmax = chuave;
        }
        if (loto.getNgaychuave() == 0) {
            loto.setNgaychuave(ganmax);
        }
        if (loto.getNgaychuave() == -1) {
            loto.setNgaychuave(0);
        }

        loto.setSolanxuathien(tonglanve);
        loto.setGanmax(ganmax);

        listLoto.add(loto);
        return listLoto;
    }

    public List<List<DauDuoi>> findDauDuoi(String code, String sDate, String eDate, boolean isDauDuoi) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);

        List<List<DauDuoi>> list = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            DauDuoi dauDuoi = null;
            List<DauDuoi> listDauduoi = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.lotoResult(lotteryResult);
                listDauduoi = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    dauDuoi = new DauDuoi();
                    dauDuoi.setNum("" + j);
                    dauDuoi.setNgay(lotteryResult.getOpen_date());
                    dauDuoi.setLanve(LotoUtils.countDauDuoi(lotoResult, dauDuoi.getNum(), isDauDuoi));
                    listDauduoi.add(dauDuoi);
                }

                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(listDauduoi);
            }
        }

        return list;
    }

    public static String ngayVeGanNhat(String loto, List<LotteryResult> resultList) {
        if (!isLoto(loto) || resultList == null || resultList.isEmpty()) {
            return null;
        }
        int size = resultList.size();
        LotteryResult tmpResult = null;
        String lotoResult = null;
        for (int i = 0; i < size; i++) {
            tmpResult = resultList.get(i);
            lotoResult = StringConvert.lotoResult(tmpResult);
            if (lotoResult.contains(loto)) {
                return tmpResult.getOpen_date();
            }
        }
        return null;
    }

    public static void main(String[] args) {

        ThongKeLoto thongKeLoto = new ThongKeLoto();
        List<Loto> listLoto = thongKeLoto.findLotoDan("XSTD", "2015-02-27", "2016-02-27", "00,01");
        System.out.println("listLoto>>>>>>>>" + listLoto.get(0).getLoto() + "===" + listLoto.get(0).getGanmax() + "===" + listLoto.get(0).getNgaybatdauganmax() + "==" + listLoto.get(0).getNgayketthucganmax());
    }
}
