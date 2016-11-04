/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author HanhDung
 */
public class DacBiet {
    private String special;
    private int ngayTet = 0;

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getNgayve() {
        return ngayve;
    }

    public void setNgayve(String ngayve) {
        this.ngayve = ngayve;
    }

    public String getDau() {        
        return dau;
    }

    public void setDau(String dau) {
        this.dau = dau;
    }

    public String getDuoi() {
        return duoi;
    }

    public void setDuoi(String duoi) {
        this.duoi = duoi;
    }
    private String ngayve;
    private String dau;
    private String duoi;
    private String thu;

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }
    
    private int tong;

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }

    public int getNgayTet() {
        return ngayTet;
    }

    public void setNgayTet(int ngayTet) {
        this.ngayTet = ngayTet;
    }
    
    
}
