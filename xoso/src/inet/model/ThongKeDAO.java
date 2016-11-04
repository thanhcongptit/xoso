/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import inet.bean.ThongKeNhanh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author MY COMPUTER
 */
public class ThongKeDAO {

    private LotteryResultDAO lotteryResultDAO = null;
    private static final List<String> totalBoSoList = new ArrayList<>();
    private static final HashMap<String, HashMap<String, ThongKeNhanh>> thongKeNhanhMap = new HashMap<>();
    private static boolean isLoadingThongKeNhanh = false;

    private ThongKeDAO() {
        lotteryResultDAO = new LotteryResultDAO();
        String key = null;
        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                key = "0" + i;
            } else {
                key = "" + i;
            }
            totalBoSoList.add(key);
        }
    }

    public static String buildKey(List<String> boSoList, String code, String startDate, 
            String endDate, int loaiGiai){
        String key = "";
        for (String boSo : boSoList) {
            key += boSo;
        }
        key += "_" + code + "_" + startDate + "_" + endDate + "_" + loaiGiai;
        return key;
    }
    private static HashMap<String, ThongKeNhanh> loadThongKeNhanh(List<String> boSoList, 
            String code, String startDate, String endDate, int loaiGiai) {
        if(boSoList == null || boSoList.isEmpty()){
            boSoList = totalBoSoList;
        }
        isLoadingThongKeNhanh = true;
        //Xu ly
        isLoadingThongKeNhanh = false;
        return null;
    }
    public static HashMap<String, ThongKeNhanh> getThongKeNhanh(List<String> boSoList, 
            String code, String startDate, String endDate, int loaiGiai) {
        if(boSoList == null || boSoList.isEmpty()){
            boSoList = totalBoSoList;
        }
        String key = buildKey(boSoList, code, startDate, endDate, loaiGiai);
        if(thongKeNhanhMap != null && thongKeNhanhMap.containsKey(key)){
            return thongKeNhanhMap.get(key);
        }
        
        return null;
    }
}
