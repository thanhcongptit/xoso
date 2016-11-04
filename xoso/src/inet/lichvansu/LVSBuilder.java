/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.lichvansu;

import inet.bean.LichVanSu;
import inet.db.lichvansu.LuniSolarCalendar;
import inet.db.lichvansu.TuVi;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class LVSBuilder {
    public LichVanSu buildContentLVS(String ddmmyyyy){
        
        Today now = Today.getInstance();
        int day = now.getDay();
        int month = now.getMonth();
        int year = now.getYear();
        int[] am = LuniSolarCalendar.Solar2Lunar(day, month, year);
        int ngayAL = am[0];
        int thangAL = am[1];
        int namAL = am[2];
        int leap = am[3];
        String[] canChi = LuniSolarCalendar.CanChi(ngayAL, thangAL, namAL,leap);
        int[] amHnay = LuniSolarCalendar.Solar2Lunar(now.getDay(), now.getMonth(), now.getYear());					
        String[] canChiHnay = LuniSolarCalendar.CanChi(amHnay[0], amHnay[1], amHnay[2],amHnay[3]);
        String ngayChiHnay = canChiHnay[1];
        String ngayCan = canChi[0];
        String ngayChi = canChi[1];
        String thangCan = canChi[2];
        String thangChi = canChi[3];
        String namCan = canChi[4];
        String namChi = canChi[5];
        String[] gioHD = TuVi.gio_Hoang_Dao_Wap(ngayChi);
        String[] gioHacDao = TuVi.gio_Hac_Dao_Wap(ngayChi);
        String[] huongXuatHanh = TuVi.huong_Xuat_Hanh(ngayCan);	
        
        LichVanSu lichVanSu=new LichVanSu();
        lichVanSu.setDayOfWeek(LuniSolarCalendar.getThu(day, month, year));
        lichVanSu.setDdmmyyyy(ddmmyyyy);
        String amlich=am[0]<10?(" 0"+am[0]):" "+am[0]+"/";
        amlich=amlich+(am[1]<10?("0"+am[1]):am[1])+"/";
        amlich=amlich+am[2];
        lichVanSu.setDdmmyyyyAl(amlich);
        lichVanSu.setNgayCan(ngayCan);
        lichVanSu.setNgayChi(ngayChi);
        lichVanSu.setThangCan(thangCan);
        lichVanSu.setThangChi(thangChi);
        lichVanSu.setNamCan(namCan);
        lichVanSu.setNamChi(namChi);
        lichVanSu.setGioHoangDao1(gioHD[0]);
        lichVanSu.setGioHoangDao2(gioHD[1]);
        lichVanSu.setGioHoangDao3(gioHD[2]);
        lichVanSu.setGioHoangDao4(gioHD[3]);
        lichVanSu.setGioHoangDao5(gioHD[4]);
        lichVanSu.setGioHoangDao6(gioHD[5]);
        
        lichVanSu.setGioHacDao1(gioHacDao[0]);
        lichVanSu.setGioHacDao2(gioHacDao[1]);
        lichVanSu.setGioHacDao3(gioHacDao[2]);
        lichVanSu.setGioHacDao4(gioHacDao[3]);
        lichVanSu.setGioHacDao5(gioHacDao[4]);
        lichVanSu.setGioHacDao6(gioHacDao[5]);
        
        lichVanSu.setHuongXuatHanh1(huongXuatHanh[0]);
        if(!huongXuatHanh[1].trim().equals(huongXuatHanh[0].trim())){
            lichVanSu.setHuongXuatHanh2(huongXuatHanh[1]);
        }
        
        return lichVanSu;
    }
}
