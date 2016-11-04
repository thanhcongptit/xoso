/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import inet.bean.CapSo;
import inet.bean.Lottery;
import inet.util.DateProc;
import inet.util.StringPro;
import inet.util.Today;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author iNET
 */
public class ThongKeLotoDAO {

    private LotteryResultDAO lotteryResultDAO = null;
    private final HashMap<String, String> hMapCapso = new HashMap<String, String>();
    private final HashMap<String, String> hMapGanMax = new HashMap<String, String>();
    private List<CapSo> danhsachCapsoDacBiet;

    private String ngayVeGanNhat = "no";

    public List<CapSo> getDanhsachCapsoDacBiet() {
        return danhsachCapsoDacBiet;
    }

    public void setDanhsachCapsoDacBiet(List<CapSo> danhsachCapsoDacBiet) {
        this.danhsachCapsoDacBiet = danhsachCapsoDacBiet;
    }

    public List<CapSo> getDanhsachCapso() {
        return danhsachCapso;
    }

    public void setDanhsachCapso(List<CapSo> danhsachCapso) {
        this.danhsachCapso = danhsachCapso;
    }
    private List<CapSo> danhsachCapso;

    public ThongKeLotoDAO() {
        lotteryResultDAO = new LotteryResultDAO();
    }

    private void loadCapso(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;

        listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
        list = timCapso(listLotteryResult);
        if (list != null && !list.isEmpty()) {
            danhSachCapso();
            loadGanMax(list);
        }
    }

    private void loadCapso(String capso, String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;

        listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
        list = timCapso(listLotteryResult);
        if (list != null && !list.isEmpty()) {
            danhSachCapso(capso);
            loadGanMax(list);
        }
    }
    
    private void loadCapso(String capso, String code, String startDate, String endDate) {
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;

        listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
        list = timCapso(listLotteryResult);
        if (list != null && !list.isEmpty()) {
            danhSachCapso(capso);
            loadGanMax(list);
        }
    }

    private void loadCapsoLoroi(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;

        listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
        list = timCapsoLoroi(listLotteryResult);
        if (list != null && !list.isEmpty()) {
            danhSachCapsoDacBiet(listLotteryResult);
            loadGanLoroiMax(list);
        }
    }

    private void loadCapsoDao(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;

        listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
        list = timCapso(listLotteryResult);
        if (list != null && !list.isEmpty()) {
            danhSachCapsoDao();
            loadGanDaoMax(list);
        }
    }

    private void loadCapsoHangChuc(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;

        listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
        list = timCapsoHangChuc(listLotteryResult);
        if (list != null && !list.isEmpty()) {
            danhSachCapsoHangChuc();
            loadGanMax(list);
        }
    }

    private void loadCapsoDonVi(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;

        listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
        list = timCapsoDonVi(listLotteryResult);
        if (list != null && !list.isEmpty()) {
            danhSachCapsoHangChuc();
            loadGanMax(list);
        }
    }

    private void loadGanMax(List<CapSo> list) {
        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            Collection<String> cResult = hMapCapso.values();
            String capso = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                capso = (String) i.next();
                if ("".equals(capso)) {
                    continue;
                }
                hMapGanMax.put(capso, timGan(capso, list) + "");
            }
        }
    }

    private void loadGanLoroiMax(List<CapSo> list) {
        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            Collection<String> cResult = hMapCapso.values();
            String capso = "";
            int j = 0;
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                capso = (String) i.next();
                if ("".equals(capso)) {
                    continue;
                }
                hMapGanMax.put(capso, timGanLoroi(capso, list, j) + "");
                j++;
            }
        }
    }

    private void loadGanDaoMax(List<CapSo> list) {
        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            Collection<String> cResult = hMapCapso.values();
            String capso = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                capso = (String) i.next();
                if ("".equals(capso)) {
                    continue;
                }
                hMapGanMax.put(capso, timGanCapSoDao(capso, list) + "");
            }
        }
    }

    public void thongkeCapso(String code, int numOfWeek) {

        if (numOfWeek < 5) {
            numOfWeek = 5;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapso(code);

        danhsachCapsoDacBiet = timCapso(listLotteryResult);

        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hMapCapso.values();
            String strCapso = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                strCapso = (String) it.next();
                if ("".equals(strCapso)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(strCapso);
                capSo.setSongaychuave(timGan(strCapso, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathien(strCapso, danhsachCapsoDacBiet));
                if (hMapGanMax.containsKey(strCapso)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMax.get(strCapso)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }

                danhsachCapso.add(capSo);
            }
        }

    }

    //cap so cua ban
    public void thongkeCapso(String capso, String code, int numOfWeek) {

        if (numOfWeek < 5) {
            numOfWeek = 5;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapso(capso, code);

        danhsachCapsoDacBiet = timCapso(listLotteryResult);

        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hMapCapso.values();
            String strCapso = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                strCapso = (String) it.next();
                if ("".equals(strCapso)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(strCapso);
                capSo.setSongaychuave(timGan(strCapso, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathien(strCapso, danhsachCapsoDacBiet));
                if (hMapGanMax.containsKey(strCapso)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMax.get(strCapso)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }

                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeCapsoLoroi(String code, int numOfWeek) {

        if (numOfWeek < 5) {
            numOfWeek = 5;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapsoLoroi(code);

        danhsachCapsoDacBiet = timCapsoLoroi(listLotteryResult);
        danhSachCapsoDacBiet(listLotteryResult);

        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hMapCapso.values();
            String strCapso = "";
            int i = 0;
            int j = 0;
            int solanxuathien = 0;
            String vt = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                strCapso = (String) it.next();
                if ("".equals(strCapso)) {
                    continue;
                }
                vt = strCapso.split("_")[1];
                i = Integer.parseInt(vt);
                vt = strCapso.split("_")[2];
                j = Integer.parseInt(vt);
                strCapso = strCapso.split("_")[0];

                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                solanxuathien = solanxuathienLoroi(strCapso, danhsachCapsoDacBiet, i);
                if (solanxuathien > 0) {
                    capSo = new CapSo();
                    capSo.setCapso(strCapso);
                    capSo.setSongaychuave(timGanLoroi(strCapso, danhsachCapsoDacBiet, i));
                    capSo.setSolanxuathien(solanxuathien);
                    if (hMapGanMax.containsKey(strCapso)) {
                        capSo.setGancucdai(Integer.valueOf(hMapGanMax.get(strCapso)));
                    } else {
                        capSo.setGancucdai(capSo.getSongaychuave());
                    }
                    capSo.setLotteryResult1(listLotteryResult.get(j));
                    capSo.setLotteryResult2(listLotteryResult.get(j - 1));
                    danhsachCapso.add(capSo);
                }
                //i++;
            }
        }

    }

    public void thongkeCapsoDao(String code, int numOfWeek) {

        if (numOfWeek < 5) {
            numOfWeek = 5;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        Today today = new Today();
        if (today.getHour() < 19) {
            endDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -1);
        }
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapsoDao(code);

        danhsachCapsoDacBiet = timCapso(listLotteryResult);

        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hMapCapso.values();
            cResult = orderASC(cResult);
            String strCapso = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                strCapso = (String) it.next();
                if ("".equals(strCapso)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(strCapso);
                capSo.setSongaychuave(timGanCapSoDao(strCapso, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathienCapsoDao(strCapso, danhsachCapsoDacBiet));
                if (hMapGanMax.containsKey(strCapso)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMax.get(strCapso)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    private List<String> orderASC(Collection<String> cString) {

        String str1 = "";
        String str2 = "";
        int iStr1 = 0;
        int iStr2 = 0;
        List<String> list = null;
        for (Iterator it = cString.iterator(); it.hasNext();) {
            str1 = (String) it.next();
            if (str1 == null) {
                continue;
            }
            if (list == null) {
                list = new ArrayList<String>();
            }
            list.add(str1);
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                str1 = list.get(i);
                iStr1 = Integer.parseInt(str1);

                str2 = list.get(j);
                iStr2 = Integer.parseInt(str2);
                if (iStr1 > iStr2) {
                    list.set(i, str2);
                    list.set(j, str1);
                }
            }
        }

        return list;
    }

    public void thongkeCapsoHangChuc(String code, int numOfWeek) {

        if (numOfWeek < 5) {
            numOfWeek = 5;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapsoHangChuc(code);

        danhsachCapsoDacBiet = timCapsoHangChuc(listLotteryResult);

        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hMapCapso.values();
            String strCapso = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                strCapso = (String) it.next();
                if ("".equals(strCapso)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(strCapso);
                capSo.setSongaychuave(timGan(strCapso, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathien(strCapso, danhsachCapsoDacBiet));
                if (hMapGanMax.containsKey(strCapso)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMax.get(strCapso)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeCapsoDonVi(String code, int numOfWeek) {

        if (numOfWeek < 5) {
            numOfWeek = 5;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapsoDonVi(code);

        danhsachCapsoDacBiet = timCapsoDonVi(listLotteryResult);

        if (hMapCapso != null && !hMapCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hMapCapso.values();
            String strCapso = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                strCapso = (String) it.next();
                if ("".equals(strCapso)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(strCapso);
                capSo.setSongaychuave(timGan(strCapso, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathien(strCapso, danhsachCapsoDacBiet));
                if (hMapGanMax.containsKey(strCapso)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMax.get(strCapso)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    private int timGan(String capso, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!capSo.getCapso().contains(capso)) {
                dem++;
            } else {
                if (dem > result) {
                    result = dem;
                }
                dem = 0;
            }
        }

        if (dem > result) {
            result = dem;
        }

        return result;

    }

    private int timGanLoroi(String capso, List<CapSo> list, int vt) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = vt + 1; i < list.size(); i++) {
            capSo = list.get(i);
            if (!capSo.getCapso().contains(capso)) {
                dem++;
            } else {
                if (dem > result) {
                    result = dem;
                }
                dem = 0;
            }
        }

        if (dem > result) {
            result = dem;
        }

        return result;

    }

    private int timGanCapSoDao(String capso, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        //String dao=capso.substring(1,2)+capso.substring(0,1);&&!capSo.getCapso().contains(dao)
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!capSo.getCapso().contains(capso)) {
                dem++;
            } else {
                if (dem > result) {
                    result = dem;
                }
                dem = 0;
            }
        }
        if (dem > result) {
            result = dem;
        }
        return result;

    }

    private int solanxuathien(String capso, List<CapSo> list) {
        int result = 0;
        ngayVeGanNhat = "no";
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (capSo.getCapso().contains(capso)) {
                result++;
                if ("no".equals(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                }
            }
        }
        return result;
    }

    private int solanxuathienLoroi(String capso, List<CapSo> list, int vt) {
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        if (vt == list.size() - 1) {
            return result;
        }
        CapSo capSo = list.get(vt + 1);
        if (capSo.getCapso().contains(capso)) {
            result++;
        }
        return result;
    }

    private int solanxuathienCapsoDao(String capso, List<CapSo> list) {
        ngayVeGanNhat = "no";
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        //String dao=capso.substring(1,2)+capso.substring(0,1);||capSo.getCapso().contains(dao)
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (capSo.getCapso().contains(capso)) {
                result++;
                if ("no".equalsIgnoreCase(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                }
            }
        }
        return result;
    }

    private void danhSachCapso() {

        String key = "";
        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                key = "0" + i;
            } else {
                key = "" + i;
            }
            hMapCapso.put(key, key);
        }

    }

    private void danhSachCapso(String capso) {
        String key = "";
        if (capso.contains(",")) {
            String[] arrCapso = capso.split(",");
            for (int i = 0; i < arrCapso.length; i++) {
                key = arrCapso[i];
                hMapCapso.put(key, key);
            }
        } else {
            key = capso;
            hMapCapso.put(key, key);
        }

    }

    private void danhSachCapsoDacBiet(List<Lottery> list) {
        hMapCapso.clear();
        String key = "";
        Lottery lotteryResult = null;
        int j = 0;
        for (int i = list.size() - 1; i > -1; i--) {
            lotteryResult = list.get(i);
            key = StringPro.subRight(lotteryResult.getSpecial(), 2);
            hMapCapso.put(key, key + "_" + j + "_" + i);
            j++;
        }

    }

    private void danhSachCapsoDao() {

        String key = "";
        String dao = "";
        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                key = "0" + i;
            } else {
                key = "" + i;
            }
            //dao=key.substring(1,2)+key.substring(0,1);   &&!hMapCapso.containsKey(dao)         
            if (!hMapCapso.containsKey(key)) {
                hMapCapso.put(key, key);
            }
        }

    }

    private void danhSachCapsoHangChuc() {

        String key = "";
        for (int i = 0; i < 10; i++) {
            key = "" + i;
            hMapCapso.put(key, key);
        }

    }

    private List<CapSo> timCapso(List<Lottery> list) {
        String[] arrstr;
        List<CapSo> listCapso = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CapSo capSo = null;
        Lottery lotteryResult = null;
        String strCapso = "";
        for (int i = 0; i < list.size(); i++) {
            if (listCapso == null) {
                listCapso = new ArrayList<CapSo>();
            }
            lotteryResult = list.get(i);

            // giai dac biet
            strCapso = StringPro.subRight(lotteryResult.getSpecial(), 2);

            // giai nhat
            strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getFirst(), 2);

            // giai nhi
            if (lotteryResult.getSecond().contains("-")) {
                arrstr = lotteryResult.getSecond().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getSecond(), 2);
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getThird(), 2);
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getThird(), 2);
            }

            // giai tu
            if (lotteryResult.getFourth().contains("-")) {
                arrstr = lotteryResult.getFourth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getFourth(), 2);
            }

            // giai nam
            if (lotteryResult.getFifth().contains("-")) {
                arrstr = lotteryResult.getFifth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getFifth(), 2);
            }

            // giai sau
            if (lotteryResult.getSixth().contains("-")) {
                arrstr = lotteryResult.getSixth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getSixth(), 2);
            }

            // giai bay
            if (lotteryResult.getSeventh().contains("-")) {
                arrstr = lotteryResult.getSeventh().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getSeventh(), 2);
            }
            capSo = new CapSo();
            capSo.setCapso(strCapso);
            capSo.setDdmmyyyy(lotteryResult.getOpenDate());
            listCapso.add(capSo);
        }

        return listCapso;
    }

    private List<CapSo> timCapsoLoroi(List<Lottery> list) {
        String[] arrstr;
        List<CapSo> listCapso = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CapSo capSo = null;
        Lottery lotteryResult = null;
        String strCapso = "";
        for (int i = list.size() - 1; i > -1; i--) {
            if (listCapso == null) {
                listCapso = new ArrayList<CapSo>();
            }
            lotteryResult = list.get(i);

            // giai dac biet
            strCapso = StringPro.subRight(lotteryResult.getSpecial(), 2);
            // giai nhat
            strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getFirst(), 2);

            // giai nhi
            if (lotteryResult.getSecond().contains("-")) {
                arrstr = lotteryResult.getSecond().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getSecond(), 2);
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getThird(), 2);
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getThird(), 2);
            }

            // giai tu
            if (lotteryResult.getFourth().contains("-")) {
                arrstr = lotteryResult.getFourth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getFourth(), 2);
            }

            // giai nam
            if (lotteryResult.getFifth().contains("-")) {
                arrstr = lotteryResult.getFifth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getFifth(), 2);
            }

            // giai sau
            if (lotteryResult.getSixth().contains("-")) {
                arrstr = lotteryResult.getSixth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getSixth(), 2);
            }

            // giai bay
            if (lotteryResult.getSeventh().contains("-")) {
                arrstr = lotteryResult.getSeventh().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    strCapso = strCapso + "-" + StringPro.subRight(arrstr[j], 2);
                }
            } else {
                strCapso = strCapso + "-" + StringPro.subRight(lotteryResult.getSeventh(), 2);
            }
            capSo = new CapSo();
            capSo.setCapso(strCapso);
            capSo.setDdmmyyyy(lotteryResult.getOpenDate());
            listCapso.add(capSo);
        }

        return listCapso;
    }

    private List<CapSo> timCapsoHangChuc(List<Lottery> list) {
        String[] arrstr;
        List<CapSo> listCapso = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CapSo capSo = null;
        Lottery lotteryResult = null;
        String strCapso = "";
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (listCapso == null) {
                listCapso = new ArrayList<CapSo>();
            }
            lotteryResult = list.get(i);

            // giai dac biet
            str = StringPro.subRight(lotteryResult.getSpecial(), 2);
            str = str.substring(0, 1);
            strCapso = str;

            // giai nhat
            str = StringPro.subRight(lotteryResult.getFirst(), 2);
            str = str.substring(0, 1);
            strCapso = strCapso + "-" + str;

            // giai nhi
            if (lotteryResult.getSecond().contains("-")) {
                arrstr = lotteryResult.getSecond().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(0, 1);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getSecond(), 2);
                str = str.substring(0, 1);
                strCapso = strCapso + "-" + str;
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(0, 1);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getThird(), 2);
                str = str.substring(0, 1);
                strCapso = strCapso + "-" + str;
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(0, 1);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getThird(), 2);
                str = str.substring(0, 1);
                strCapso = strCapso + "-" + str;
            }

            // giai tu
            if (lotteryResult.getFourth().contains("-")) {
                arrstr = lotteryResult.getFourth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(0, 1);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getFourth(), 2);
                str = str.substring(0, 1);
                strCapso = strCapso + "-" + str;
            }

            // giai nam
            if (lotteryResult.getFifth().contains("-")) {
                arrstr = lotteryResult.getFifth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(0, 1);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getFifth(), 2);
                str = str.substring(0, 1);
                strCapso = strCapso + "-" + str;
            }

            // giai sau
            if (lotteryResult.getSixth().contains("-")) {
                arrstr = lotteryResult.getSixth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(0, 1);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getSixth(), 2);
                str = str.substring(0, 1);
                strCapso = strCapso + "-" + str;
            }

            // giai bay
            if (lotteryResult.getSeventh().contains("-")) {
                arrstr = lotteryResult.getSeventh().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(0, 1);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getSeventh(), 2);
                str = str.substring(0, 1);
                strCapso = strCapso + "-" + str;
            }
            capSo = new CapSo();
            capSo.setCapso(strCapso);
            capSo.setDdmmyyyy(lotteryResult.getOpenDate());
            listCapso.add(capSo);
        }

        return listCapso;
    }

    private List<CapSo> timCapsoDonVi(List<Lottery> list) {
        String[] arrstr;
        List<CapSo> listCapso = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CapSo capSo = null;
        Lottery lotteryResult = null;
        String strCapso = "";
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (listCapso == null) {
                listCapso = new ArrayList<CapSo>();
            }
            lotteryResult = list.get(i);

            // giai dac biet
            str = StringPro.subRight(lotteryResult.getSpecial(), 2);
            str = str.substring(1, 2);
            strCapso = str;

            // giai nhat
            str = StringPro.subRight(lotteryResult.getFirst(), 2);
            str = str.substring(1, 2);
            strCapso = strCapso + "-" + str;

            // giai nhi
            if (lotteryResult.getSecond().contains("-")) {
                arrstr = lotteryResult.getSecond().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(1, 2);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getSecond(), 2);
                str = str.substring(1, 2);
                strCapso = strCapso + "-" + str;
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(1, 2);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getThird(), 2);
                str = str.substring(1, 2);
                strCapso = strCapso + "-" + str;
            }

            // giai ba
            if (lotteryResult.getThird().contains("-")) {
                arrstr = lotteryResult.getThird().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(1, 2);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getThird(), 2);
                str = str.substring(1, 2);
                strCapso = strCapso + "-" + str;
            }

            // giai tu
            if (lotteryResult.getFourth().contains("-")) {
                arrstr = lotteryResult.getFourth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(1, 2);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getFourth(), 2);
                str = str.substring(1, 2);
                strCapso = strCapso + "-" + str;
            }

            // giai nam
            if (lotteryResult.getFifth().contains("-")) {
                arrstr = lotteryResult.getFifth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(1, 2);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getFifth(), 2);
                str = str.substring(1, 2);
                strCapso = strCapso + "-" + str;
            }

            // giai sau
            if (lotteryResult.getSixth().contains("-")) {
                arrstr = lotteryResult.getSixth().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(1, 2);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getSixth(), 2);
                str = str.substring(1, 2);
                strCapso = strCapso + "-" + str;
            }

            // giai bay
            if (lotteryResult.getSeventh().contains("-")) {
                arrstr = lotteryResult.getSeventh().split("-");
                for (int j = 0; j < arrstr.length; j++) {
                    str = StringPro.subRight(arrstr[j], 2);
                    str = str.substring(1, 2);
                    strCapso = strCapso + "-" + str;
                }
            } else {
                str = StringPro.subRight(lotteryResult.getSeventh(), 2);
                str = str.substring(1, 2);
                strCapso = strCapso + "-" + str;
            }
            capSo = new CapSo();
            capSo.setCapso(strCapso);
            capSo.setDdmmyyyy(lotteryResult.getOpenDate());
            listCapso.add(capSo);
        }

        return listCapso;
    }

    public static void main(String[] arg) {
//        Today today=new Today();
//        System.out.println(DateProc.Timestamp2DDMMYYYY(DateProc.String2Timestamp(today.getDay()+"/"+(today.getMonth()+1)+"/"+today.getYear())));
        ThongKeLotoDAO thongKeLotoDAO = new ThongKeLotoDAO();
        thongKeLotoDAO.thongkeCapsoLoroi("XSTD", 30);
        List<CapSo> dsDacBiet = thongKeLotoDAO.danhsachCapso;
        if (dsDacBiet != null) {
            CapSo capSo = null;
            for (int i = 0; i < dsDacBiet.size(); i++) {
                capSo = dsDacBiet.get(i);
                System.out.println(capSo.getCapso() + "==gan==" + capSo.getSongaychuave() + "==gan cuc dai==" + capSo.getGancucdai() + "==so lan xuat hien==" + capSo.getSolanxuathien());
                System.out.println("====" + capSo.getLotteryResult1().getSpecial() + "===" + capSo.getLotteryResult1().getOpenDate());
                System.out.println("====" + capSo.getLotteryResult2().getSpecial() + "===" + capSo.getLotteryResult2().getOpenDate());
            }
        }

    }
}
