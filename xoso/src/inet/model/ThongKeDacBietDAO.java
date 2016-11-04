/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import inet.bean.CapSo;
import inet.bean.Lottery;
import inet.util.DatePro;
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
public class ThongKeDacBietDAO {

    private LotteryResultDAO lotteryResultDAO = null;
    private final HashMap<String, List<CapSo>> hMapCapso = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMax = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapCapsoChanLe = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxChanLe = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapHangChuc = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxHangChuc = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapHangDonVi = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxHangDonVi = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapTong = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxTong = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapHieu = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxHieu = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapGiaiSo = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxGiaiSo = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapCham = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxCham = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapChiaHetCho3 = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxChiaHetCho3 = new HashMap<String, String>();

    private final HashMap<String, List<CapSo>> hMapKep = new HashMap<String, List<CapSo>>();
    private final HashMap<String, String> hMapGanMaxKep = new HashMap<String, String>();

    private List<CapSo> danhsachCapsoDacBiet;

    private String ngayVeGanNhat = "no";
    private String dayOfWeek = "no";
    private String special = "no";

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

    public ThongKeDacBietDAO() {
        lotteryResultDAO = new LotteryResultDAO();
    }

    private void loadCapso(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapCapso == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCapso.put(keyNew, list);
                loadGanMax(keyNew);
            }
        } else if (!hMapCapso.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCapso.clear();
                hMapGanMax.clear();
                hMapCapso.put(keyNew, list);
                loadGanMax(keyNew);
            }
        }
    }

    private void loadChanLe(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapCapso == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCapso.put(keyNew, list);
                loadGanMaxChanLe(keyNew);
            }
        } else if (!hMapCapso.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCapso.clear();
                hMapGanMax.clear();
                hMapCapso.put(keyNew, list);
                loadGanMaxChanLe(keyNew);
            }
        }
    }

    private void loadCapsoChiaHetCho3(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapChiaHetCho3 == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapChiaHetCho3.put(keyNew, list);
                loadGanMax(keyNew);
            }
        } else if (!hMapCapso.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapChiaHetCho3.clear();
                hMapGanMaxChiaHetCho3.clear();
                hMapChiaHetCho3.put(keyNew, list);
                loadGanMaxChiaHetCho3(keyNew);
            }
        }
    }

    private void loadCapsoKep(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapKep == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapKep.put(keyNew, list);
                loadGanMaxKep(keyNew);
            }
        } else if (!hMapKep.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapKep.clear();
                hMapGanMaxKep.clear();
                hMapKep.put(keyNew, list);
                loadGanMaxKep(keyNew);
            }
        }
    }

    private void loadTheoCapsoChanLe(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapCapsoChanLe == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCapsoChanLe.put(keyNew, list);
                loadGanMaxTheoChanLe(keyNew);
            }
        } else if (!hMapCapsoChanLe.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCapsoChanLe.clear();
                hMapGanMaxChanLe.clear();
                hMapCapsoChanLe.put(keyNew, list);
                loadGanMaxTheoChanLe(keyNew);
            }
        }
    }

    private void loadGiaiSo(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapGiaiSo == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapGiaiSo.put(keyNew, list);
                loadGanMaxGiaiSo(keyNew);
            }
        } else if (!hMapCapsoChanLe.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapGiaiSo.clear();
                hMapGanMaxGiaiSo.clear();
                hMapGiaiSo.put(keyNew, list);
                loadGanMaxGiaiSo(keyNew);
            }
        }
    }

    private void loadHangChuc(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapHangChuc == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapHangChuc.put(keyNew, list);
                loadGanMaxHangChuc(keyNew);
            }
        } else if (!hMapHangChuc.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapHangChuc.clear();
                hMapGanMaxHangChuc.clear();
                hMapHangChuc.put(keyNew, list);
                loadGanMaxHangChuc(keyNew);
            }
        }
    }

    private void loadHangDonVi(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapHangDonVi == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapHangDonVi.put(keyNew, list);
                loadGanMaxHangChuc(keyNew);
            }
        } else if (!hMapHangDonVi.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapHangDonVi.clear();
                hMapGanMaxHangDonVi.clear();
                hMapHangDonVi.put(keyNew, list);
                loadGanMaxHangDonVi(keyNew);
            }
        }
    }

    private void loadTong(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapTong == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapTong.put(keyNew, list);
                loadGanMaxTong(keyNew);
            }
        } else if (!hMapTong.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapTong.clear();
                hMapGanMaxTong.clear();
                hMapTong.put(keyNew, list);
                loadGanMaxTong(keyNew);
            }
        }
    }

    private void loadHieu(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapHieu == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapHieu.put(keyNew, list);
                loadGanMaxTong(keyNew);
            }
        } else if (!hMapHieu.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapHieu.clear();
                hMapGanMaxHieu.clear();
                hMapHieu.put(keyNew, list);
                loadGanMaxHieu(keyNew);
            }
        }
    }

    private void loadCham(String code) {
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String keyNew = code + endDate;
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -(50 * 7));
        List<Lottery> listLotteryResult = null;
        List<CapSo> list = null;
        if (hMapCham == null) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCham.put(keyNew, list);
                loadGanMaxTong(keyNew);
            }
        } else if (!hMapCham.containsKey(keyNew)) {
            listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);
            list = timCapsoDacBiet(listLotteryResult);
            if (list != null && !list.isEmpty()) {
                hMapCham.clear();
                hMapGanMaxCham.clear();
                hMapCham.put(keyNew, list);
                loadGanMaxCham(keyNew);
            }
        }
    }

    private void loadGanMaxTheoChanLe(String key) {
        List<CapSo> list = hMapCapsoChanLe.get(key);

        hMapGanMaxChanLe.put("CL", timGanChanLe("CL", list) + "");
        hMapGanMaxChanLe.put("CC", timGanChanLe("CC", list) + "");
        hMapGanMaxChanLe.put("LC", timGanChanLe("LC", list) + "");
        hMapGanMaxChanLe.put("LL", timGanChanLe("LL", list) + "");

    }

    private void loadGanMaxGiaiSo(String key) {
        List<CapSo> list = hMapGiaiSo.get(key);

        hMapGanMaxGiaiSo.put("00-24", timGanGiaiSo(0, 24, list) + "");
        hMapGanMaxGiaiSo.put("25-49", timGanGiaiSo(25, 49, list) + "");
        hMapGanMaxGiaiSo.put("50 –74", timGanGiaiSo(50, 74, list) + "");
        hMapGanMaxGiaiSo.put("75-99", timGanGiaiSo(75, 99, list) + "");

    }

    private void loadGanMax(String key) {
        List<CapSo> list = hMapCapso.get(key);
        //HashMap<String,String> hCapso=danhsachCapso(list);
        HashMap<String, String> hCapso = danhsachCapso0099();
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
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

    private void loadGanMaxChanLe(String key) {
        List<CapSo> list = hMapCapso.get(key);
        HashMap<String, String> hCapso = danhsachCapso(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
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

    private void loadGanMaxChiaHetCho3(String key) {
        List<CapSo> list = hMapChiaHetCho3.get(key);
        HashMap<String, String> hCapso = danhsachChiaHetCho3(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
            String capso = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                capso = (String) i.next();
                if ("".equals(capso)) {
                    continue;
                }
                hMapGanMaxChiaHetCho3.put(capso, timGan(capso, list) + "");
            }
        }
    }

    private void loadGanMaxKep(String key) {
        List<CapSo> list = hMapKep.get(key);
        HashMap<String, String> hCapso = danhsachKep(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
            String capso = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                capso = (String) i.next();
                if ("".equals(capso)) {
                    continue;
                }
                hMapGanMaxKep.put(capso, timGan(capso, list) + "");
            }
        }
    }

    private void loadGanMaxHangChuc(String key) {
        List<CapSo> list = hMapHangChuc.get(key);
        HashMap<String, String> hCapso = danhsachHangChuc(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
            String hangchuc = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                hangchuc = (String) i.next();
                if ("".equals(hangchuc)) {
                    continue;
                }
                hMapGanMaxHangChuc.put(hangchuc, timGanHangChuc(hangchuc, list) + "");
            }
        }
    }

    private void loadGanMaxHangDonVi(String key) {
        List<CapSo> list = hMapHangDonVi.get(key);
        HashMap<String, String> hCapso = danhsachHangDonVi(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
            String hangdonvi = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                hangdonvi = (String) i.next();
                if ("".equals(hangdonvi)) {
                    continue;
                }
                hMapGanMaxHangDonVi.put(hangdonvi, timGanHangDonVi(hangdonvi, list) + "");
            }
        }
    }

    private void loadGanMaxTong(String key) {
        List<CapSo> list = hMapTong.get(key);
        HashMap<String, String> hCapso = danhsachTong(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
            String tong = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                tong = (String) i.next();
                if ("".equals(tong)) {
                    continue;
                }
                hMapGanMaxTong.put(tong, timGanHangDonVi(tong, list) + "");
            }
        }
    }

    private void loadGanMaxHieu(String key) {
        List<CapSo> list = hMapHieu.get(key);
        HashMap<String, String> hCapso = danhsachTong(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
            String hieu = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                hieu = (String) i.next();
                if ("".equals(hieu)) {
                    continue;
                }
                hMapGanMaxHieu.put(hieu, timGanHieu(hieu, list) + "");
            }
        }
    }

    private void loadGanMaxCham(String key) {
        List<CapSo> list = hMapCham.get(key);
        HashMap<String, String> hCapso = danhsachCham(list);
        if (hCapso != null && !hCapso.isEmpty()) {
            Collection<String> cResult = hCapso.values();
            String cham = "";
            for (Iterator i = cResult.iterator(); i.hasNext();) {
                cham = (String) i.next();
                if ("".equals(cham)) {
                    continue;
                }
                hMapGanMaxCham.put(cham, timGanCham(cham, list) + "");
            }
        }
    }

    public void thongkeCapso(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapso(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        //HashMap<String,String> hCapso=danhsachCapso(danhsachCapsoDacBiet);
        HashMap<String, String> hCapso = danhsachCapso0099();
        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
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

    public void thongkeTheoThu(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        //danhsachCapsoDacBiet=timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachTheoThu(listLotteryResult);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;

            for (int i = 2; i < 8; i++) {
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setDayOfWeek("" + i);
                capSo.setCapso(hCapso.get("" + i));
                danhsachCapso.add(capSo);
            }
            capSo = new CapSo();
            capSo.setDayOfWeek("cn");
            capSo.setCapso(hCapso.get("cn"));
            danhsachCapso.add(capSo);
        }
    }

    public void thongkeNgayNayThangTruoc(String code, int numOfWeek) {

        if (numOfWeek < 12) {
            numOfWeek = 12;
        }
        if (numOfWeek > 48) {
            numOfWeek = 48;
        }
        Lottery lotteryResult = null;
        List<Lottery> listLotteryResult = null;
        Today today = new Today();
        String ddmmyy = "";
        for (int i = 0; i <= numOfWeek; i++) {
            ddmmyy = DateProc.Timestamp2DDMMYYYY(DateProc.String2Timestamp(today.getDay() + "/" + (today.getMonth() - i) + "/" + today.getYear()));
            lotteryResult = lotteryResultDAO.findResultByCodeInDay(code, ddmmyy);
            if (lotteryResult != null) {
                if (listLotteryResult == null) {
                    listLotteryResult = new ArrayList<Lottery>();
                }
                listLotteryResult.add(lotteryResult);
            }

        }

        danhsachCapso = timCapsoDacBiet(listLotteryResult);

    }

    public void thongkeNgayNayNamTruoc(String code, int numOfWeek) {

        if (numOfWeek < 1) {
            numOfWeek = 1;
        }
        if (numOfWeek > 6) {
            numOfWeek = 6;
        }
        Lottery lotteryResult = null;
        List<Lottery> listLotteryResult = null;
        Today today = new Today();
        String ddmmyy = "";
        for (int i = 0; i <= numOfWeek; i++) {
            ddmmyy = DateProc.Timestamp2DDMMYYYY(DateProc.String2Timestamp(today.getDay() + "/" + today.getMonth() + "/" + (today.getYear() - i)));
            //System.out.println("ddmmyy====>>"+ddmmyy);
            lotteryResult = lotteryResultDAO.findResultByCodeInDay(code, ddmmyy);
            if (lotteryResult != null) {
                if (listLotteryResult == null) {
                    listLotteryResult = new ArrayList<Lottery>();
                }
                listLotteryResult.add(lotteryResult);
            }

        }

        danhsachCapso = timCapsoDacBiet(listLotteryResult);

    }

    public void thongkeCapsoChiaHetCho3(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapsoChiaHetCho3(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachChiaHetCho3(danhsachCapsoDacBiet);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
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
                if (hMapGanMaxChiaHetCho3.containsKey(strCapso)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMaxChiaHetCho3.get(strCapso)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDayOfWeek(dayOfWeek);
                if ("cn".equalsIgnoreCase(capSo.getDayOfWeek())) {
                    capSo.setNumDayOfWeek(8);
                } else {
                    capSo.setNumDayOfWeek(Integer.parseInt(dayOfWeek));
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);

                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeCapsoKep(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCapsoKep(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachKep(danhsachCapsoDacBiet);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
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
                if (hMapGanMaxKep.containsKey(strCapso)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMaxKep.get(strCapso)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }

                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeHangChuc(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadHangChuc(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachHangChuc(danhsachCapsoDacBiet);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
            String hangchuc = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                hangchuc = (String) it.next();
                if ("".equals(hangchuc)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(hangchuc);
                capSo.setSongaychuave(timGanHangChuc(hangchuc, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathienHangChuc(hangchuc, danhsachCapsoDacBiet));
                if (hMapGanMaxHangChuc.containsKey(hangchuc)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMaxHangChuc.get(hangchuc)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeHangDonVi(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadHangDonVi(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachHangDonVi(danhsachCapsoDacBiet);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
            String hangdonvi = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                hangdonvi = (String) it.next();
                if ("".equals(hangdonvi)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(hangdonvi);
                capSo.setSongaychuave(timGanHangDonVi(hangdonvi, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathienHangDonVi(hangdonvi, danhsachCapsoDacBiet));
                if (hMapGanMaxHangDonVi.containsKey(hangdonvi)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMaxHangDonVi.get(hangdonvi)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeTong(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadTong(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachTong(danhsachCapsoDacBiet);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
            String tong = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                tong = (String) it.next();
                if ("".equals(tong)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(tong);
                capSo.setSongaychuave(timGanTong(tong, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathienTong(tong, danhsachCapsoDacBiet));
                if (hMapGanMaxTong.containsKey(tong)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMaxTong.get(tong)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeHieu(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadHieu(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachHieu(danhsachCapsoDacBiet);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
            String hieu = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                hieu = (String) it.next();
                if ("".equals(hieu)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(hieu);
                capSo.setSongaychuave(timGanHieu(hieu, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathienHieu(hieu, danhsachCapsoDacBiet));
                if (hMapGanMaxHieu.containsKey(hieu)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMaxHieu.get(hieu)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeCham(String code, int numOfWeek) {

        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadCham(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachCham(danhsachCapsoDacBiet);

        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
            String cham = "";
            for (Iterator it = cResult.iterator(); it.hasNext();) {
                cham = (String) it.next();
                if ("".equals(cham)) {
                    continue;
                }
                if (danhsachCapso == null) {
                    danhsachCapso = new ArrayList<CapSo>();
                }
                capSo = new CapSo();
                capSo.setCapso(cham);
                capSo.setSongaychuave(timGanCham(cham, danhsachCapsoDacBiet));
                capSo.setSolanxuathien(solanxuathienCham(cham, danhsachCapsoDacBiet));
                if (hMapGanMaxCham.containsKey(cham)) {
                    capSo.setGancucdai(Integer.valueOf(hMapGanMaxCham.get(cham)));
                } else {
                    capSo.setGancucdai(capSo.getSongaychuave());
                }
                capSo.setDdmmyyyy(ngayVeGanNhat);
                danhsachCapso.add(capSo);
            }
        }

    }

    public void thongkeChanle(String code, int numOfWeek) {
        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadChanLe(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);
        HashMap<String, String> hCapso = danhsachCapso(danhsachCapsoDacBiet);
        if (hCapso != null && !hCapso.isEmpty()) {
            CapSo capSo = null;
            Collection<String> cResult = hCapso.values();
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
                capSo.setDayOfWeek(dayOfWeek);
                if ("cn".equalsIgnoreCase(capSo.getDayOfWeek())) {
                    capSo.setNumDayOfWeek(8);
                } else {
                    capSo.setNumDayOfWeek(Integer.parseInt(dayOfWeek));
                }
                capSo.setChanle(chanle(strCapso));
                if (hangChuc(strCapso).equals(hangDonVi(strCapso))) {
                    capSo.setKep("kep");
                } else {
                    capSo.setKep("no");
                }
                danhsachCapso.add(capSo);
            }
        }
    }

    public void thongkeTheoCapChanle(String code, int numOfWeek) {
        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadTheoCapsoChanLe(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);

        danhsachCapso = new ArrayList<CapSo>();
        CapSo capSo = null;
        capSo = new CapSo();
        capSo.setCapso("CL");
        capSo.setSongaychuave(timGanChanLe("CL", danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienChanle("CL", danhsachCapsoDacBiet));
        if (hMapGanMaxChanLe.containsKey("CL")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxChanLe.get("CL")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        danhsachCapso.add(capSo);

        capSo = new CapSo();
        capSo.setCapso("CC");
        capSo.setSongaychuave(timGanChanLe("CC", danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienChanle("CC", danhsachCapsoDacBiet));
        if (hMapGanMaxChanLe.containsKey("CC")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxChanLe.get("CC")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        danhsachCapso.add(capSo);

        capSo = new CapSo();
        capSo.setCapso("LC");
        capSo.setSongaychuave(timGanChanLe("LC", danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienChanle("LC", danhsachCapsoDacBiet));
        if (hMapGanMaxChanLe.containsKey("LC")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxChanLe.get("LC")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        danhsachCapso.add(capSo);

        capSo = new CapSo();
        capSo.setCapso("LL");
        capSo.setSongaychuave(timGanChanLe("LL", danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienChanle("LL", danhsachCapsoDacBiet));
        if (hMapGanMaxChanLe.containsKey("LL")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxChanLe.get("LL")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        danhsachCapso.add(capSo);
    }

    public void thongkeGiaiSo(String code, int numOfWeek) {
        if (numOfWeek < 10) {
            numOfWeek = 10;
        }
        if (numOfWeek > 50) {
            numOfWeek = 50;
        }
        int numOfDay = numOfWeek * 7;
        String endDate = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
        String startDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(endDate), -numOfDay);
        List<Lottery> listLotteryResult = lotteryResultDAO.findResultByCodeInTime(code, startDate, endDate);

        loadGiaiSo(code);

        danhsachCapsoDacBiet = timCapsoDacBiet(listLotteryResult);

        danhsachCapso = new ArrayList<CapSo>();
        CapSo capSo = null;
        capSo = new CapSo();
        capSo.setCapso("00-24");
        capSo.setSongaychuave(timGanGiaiSo(0, 24, danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienGiaiSo(0, 24, danhsachCapsoDacBiet));
        if (hMapGanMaxGiaiSo.containsKey("00-24")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxGiaiSo.get("00-24")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        capSo.setDdmmyyyy(ngayVeGanNhat);
        capSo.setSpecial(special);
        danhsachCapso.add(capSo);

        capSo = new CapSo();
        capSo.setCapso("25-49");
        capSo.setSongaychuave(timGanGiaiSo(25, 49, danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienGiaiSo(25, 49, danhsachCapsoDacBiet));
        if (hMapGanMaxGiaiSo.containsKey("25-49")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxGiaiSo.get("25-49")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        capSo.setDdmmyyyy(ngayVeGanNhat);
        capSo.setSpecial(special);
        danhsachCapso.add(capSo);

        capSo = new CapSo();
        capSo.setCapso("50-74");
        capSo.setSongaychuave(timGanGiaiSo(50, 74, danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienGiaiSo(50, 74, danhsachCapsoDacBiet));
        if (hMapGanMaxGiaiSo.containsKey("50-74")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxGiaiSo.get("50-74")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        capSo.setDdmmyyyy(ngayVeGanNhat);
        capSo.setSpecial(special);
        danhsachCapso.add(capSo);

        capSo = new CapSo();
        capSo.setCapso("75-99");
        capSo.setSongaychuave(timGanGiaiSo(75, 99, danhsachCapsoDacBiet));
        capSo.setSolanxuathien(solanxuathienGiaiSo(75, 99, danhsachCapsoDacBiet));
        if (hMapGanMaxGiaiSo.containsKey("75-99")) {
            capSo.setGancucdai(Integer.valueOf(hMapGanMaxGiaiSo.get("75-99")));
        } else {
            capSo.setGancucdai(capSo.getSongaychuave());
        }
        capSo.setDdmmyyyy(ngayVeGanNhat);
        capSo.setSpecial(special);
        danhsachCapso.add(capSo);
    }

    private List<CapSo> timCapsoDacBiet(List<Lottery> list) {
        List<CapSo> listCapso = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CapSo capSo = null;
        Lottery lotteryResult = null;
        for (Lottery list1 : list) {
            if (listCapso == null) {
                listCapso = new ArrayList<CapSo>();
            }
            lotteryResult = list1;
            capSo = new CapSo();
            capSo.setSpecial(lotteryResult.getSpecial());
            capSo.setCapso(StringPro.subRight(lotteryResult.getSpecial(), 2));
            capSo.setDdmmyyyy(lotteryResult.getOpenDate());
            capSo.setDayOfWeek(DatePro.getDateOfWeekDDMMYYYY(lotteryResult.getOpenDate()));
            capSo.setChanle(chanle(capSo.getCapso()));
            listCapso.add(capSo);
        }

        return listCapso;
    }

    private String chanle(String capso) {
        String hangchuc = hangChuc(capso);
        String hangdonvi = hangDonVi(capso);
        String result = "";
        if (Integer.parseInt(hangchuc) % 2 == 0) {
            result = "C";
        } else {
            result = "L";
        }

        if (Integer.parseInt(hangdonvi) % 2 == 0) {
            result = result + "C";
        } else {
            result = result + "L";
        }
        return result;
    }

    private String hangChuc(String capso) {
        String hangchuc = capso.substring(0, 1);
        return hangchuc;
    }

    private String hangDonVi(String capso) {
        String hangdonvi = capso.substring(1, 2);
        return hangdonvi;
    }

    private String tongCapSo(String capso, boolean bot10) {
        int hangchuc = Integer.parseInt(hangChuc(capso));
        int hangdonvi = Integer.parseInt(hangDonVi(capso));
        int result = hangchuc + hangdonvi;
        if (bot10) {
            if (result >= 10) {
                result = result - 10;
            }
        }
        return String.valueOf(result);
    }

    private String hieuCapSo(String capso) {
        int hangchuc = Integer.parseInt(hangChuc(capso));
        int hangdonvi = Integer.parseInt(hangDonVi(capso));
        int result = Math.abs(hangchuc - hangdonvi);
        return String.valueOf(result);
    }

    private String chiaHetCho3(String capso) {
        int result = Integer.parseInt(tongCapSo(capso, false));
        if (result % 3 == 0) {
            return capso;
        }
        return null;
    }

    private String kep(String capso) {
        String hangchuc = hangChuc(capso);
        String hangdonvi = hangDonVi(capso);
        if (hangchuc.equals(hangdonvi)) {
            return capso;
        }
        return null;
    }

    private int timGanChanLe(String chanle, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!capSo.getChanle().equals(chanle)) {
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

    private int timGanGiaiSo(int start, int end, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        int so = 0;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            so = Integer.valueOf(capSo.getCapso());
            if (so < start || so > end) {
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

    private int timGan(String capso, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (CapSo list1 : list) {
            capSo = list1;
            if (!capSo.getCapso().equals(capso)) {
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

    private int timGanHangChuc(String hangchuc, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!capSo.getCapso().startsWith(hangchuc)) {
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

    private int timGanHangDonVi(String hangdonvi, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!capSo.getCapso().endsWith(hangdonvi)) {
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

    private int timGanTong(String tong, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!tong.equals(tongCapSo(capSo.getCapso(), true))) {
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

    private int timGanHieu(String hieu, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!hieu.equals(hieuCapSo(capSo.getCapso()))) {
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

    private int timGanCham(String cham, List<CapSo> list) {
        int result = 0;
        int dem = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!capSo.getCapso().contains(cham)) {
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

    private int solanxuathienHangChuc(String hangchuc, List<CapSo> list) {
        ngayVeGanNhat = "no";
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (capSo.getCapso().startsWith(hangchuc)) {
                result++;
                if ("no".equals(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                }
            }
        }
        return result;
    }

    private int solanxuathienHangDonVi(String hangdonvi, List<CapSo> list) {
        ngayVeGanNhat = "no";
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (capSo.getCapso().endsWith(hangdonvi)) {
                result++;
                if ("no".equals(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                }
            }
        }
        return result;
    }

    private int solanxuathienTong(String tong, List<CapSo> list) {
        ngayVeGanNhat = "no";
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (tong.equals(tongCapSo(capSo.getCapso(), true))) {
                result++;
                if ("no".equals(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                }
            }
        }
        return result;
    }

    private int solanxuathienHieu(String hieu, List<CapSo> list) {
        ngayVeGanNhat = "no";
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (CapSo list1 : list) {
            capSo = list1;
            if (hieu.equals(hieuCapSo(capSo.getCapso()))) {
                result++;
                if ("no".equals(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                }
            }
        }
        return result;
    }

    private int solanxuathienChanle(String chanle, List<CapSo> list) {
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (capSo.getChanle().equals(chanle)) {
                result++;
            }
        }
        return result;
    }

    private int solanxuathienGiaiSo(int start, int end, List<CapSo> list) {
        ngayVeGanNhat = "no";
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        int so = 0;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            so = Integer.valueOf(capSo.getCapso());
            if (start <= so && so <= end) {
                result++;
                if ("no".equals(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                    special = capSo.getSpecial();
                }
            }
        }
        return result;
    }

    private int solanxuathien(String capso, List<CapSo> list) {
        int result = 0;
        ngayVeGanNhat = "no";
        dayOfWeek = "no";
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (capSo.getCapso().equals(capso)) {
                result++;
                if ("no".equalsIgnoreCase(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                    dayOfWeek = capSo.getDayOfWeek();

                }
            }

        }
        return result;
    }

    private int solanxuathienCham(String cham, List<CapSo> list) {
        ngayVeGanNhat = "no";
        int result = 0;
        if (list == null || list.isEmpty()) {
            return result;
        }
        CapSo capSo = null;
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (capSo.getCapso().contains(cham)) {
                result++;
                if ("no".equals(ngayVeGanNhat)) {
                    ngayVeGanNhat = capSo.getDdmmyyyy();
                }
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachCapso(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            if (!result.containsKey(list.get(i).getCapso())) {
                result.put(list.get(i).getCapso(), list.get(i).getCapso());
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachCapso0099() {

        HashMap<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                result.put("0" + i, "0" + i);
            } else {
                result.put("" + i, "" + i);
            }

        }
        return result;
    }

    private HashMap<String, String> danhsachTheoThu(List<Lottery> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        Lottery lotteryResult = null;
        String strSpecial = "";
        for (int i = 0; i < list.size(); i++) {
            lotteryResult = list.get(i);
            String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(lotteryResult.getOpenDate());
            if (!result.containsKey(dayOfWeek)) {
                result.put(dayOfWeek, lotteryResult.getSpecial());
            } else {
                strSpecial = result.get(dayOfWeek);
                strSpecial = strSpecial + "-" + lotteryResult.getSpecial();
                result.put(dayOfWeek, strSpecial);
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachHangChuc(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        String hangchuc = "";
        for (int i = 0; i < list.size(); i++) {
            hangchuc = hangChuc(list.get(i).getCapso());
            if (!result.containsKey(hangchuc)) {
                result.put(hangchuc, hangchuc);
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachHangDonVi(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        String hangdonvi = "";
        for (int i = 0; i < list.size(); i++) {
            hangdonvi = hangDonVi(list.get(i).getCapso());
            if (!result.containsKey(hangdonvi)) {
                result.put(hangdonvi, hangdonvi);
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachTong(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        String tong = "";
        for (int i = 0; i < list.size(); i++) {
            tong = tongCapSo(list.get(i).getCapso(), true);
            if (!result.containsKey(tong)) {
                result.put(tong, tong);
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachHieu(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        String hieu = "";
        for (int i = 0; i < list.size(); i++) {
            hieu = hieuCapSo(list.get(i).getCapso());
            if (!result.containsKey(hieu)) {
                result.put(hieu, hieu);
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachCham(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        String cham = "";
        for (int i = 0; i < list.size(); i++) {
            cham = hangChuc(list.get(i).getCapso());
            if (!result.containsKey(cham)) {
                result.put(cham, cham);
            }

            cham = hangDonVi(list.get(i).getCapso());
            if (!result.containsKey(cham)) {
                result.put(cham, cham);
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachChiaHetCho3(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        String chiahetcho3 = "";
        for (CapSo list1 : list) {
            chiahetcho3 = chiaHetCho3(list1.getCapso());
            if (chiahetcho3 != null && !result.containsKey(chiahetcho3)) {
                result.put(chiahetcho3, chiahetcho3);
            }
        }
        return result;
    }

    private HashMap<String, String> danhsachKep(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> result = new HashMap<String, String>();
        String kep = "";
        for (int i = 0; i < list.size(); i++) {
            kep = kep(list.get(i).getCapso());
            if (kep != null && !result.containsKey(kep)) {
                result.put(kep, kep);
            }
        }
        return result;
    }

    public static void main(String[] arg) {
//        Today today=new Today();
//        System.out.println(DateProc.Timestamp2DDMMYYYY(DateProc.String2Timestamp(today.getDay()+"/"+(today.getMonth()+1)+"/"+today.getYear())));
        ThongKeDacBietDAO thongKeDAO = new ThongKeDacBietDAO();
        thongKeDAO.thongkeTong("XSTD", 50);
        List<CapSo> dsDacBiet = thongKeDAO.getDanhsachCapso();
        if (dsDacBiet != null) {
            CapSo capSo = null;
            for (int i = 0; i < dsDacBiet.size(); i++) {
                capSo = dsDacBiet.get(i);
                System.out.println(capSo.getDdmmyyyy() + "=====" + capSo.getCapso());
            }
        } else {
            System.out.println("null");
        }

        thongKeDAO.thongkeHangDonVi("XSTD", 50);
        List<CapSo> dsDacBietdonvi = thongKeDAO.getDanhsachCapso();
        if (dsDacBietdonvi != null) {
            CapSo capSo = null;
            for (CapSo dsDacBietdonvi1 : dsDacBietdonvi) {
                capSo = dsDacBietdonvi1;
                System.out.println(capSo.getCapso() + "***********" + capSo.getDdmmyyyy());
            }
        } else {
            System.out.println("null");
        }

    }

}
