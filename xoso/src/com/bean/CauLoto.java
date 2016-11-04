/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author HanhDung
 */
public class CauLoto {
    private String loto;

    public String getLoto() {
        return loto;
    }

    public void setLoto(String loto) {
        this.loto = loto;
    }

    public int getCau() {
        return cau;
    }

    public void setCau(int cau) {
        this.cau = cau;
    }

    public int getVitri1() {
        return vitri1;
    }

    public void setVitri1(int vitri1) {
        this.vitri1 = vitri1;
    }

    public int getVitri2() {
        return vitri2;
    }

    public void setVitri2(int vitri2) {
        this.vitri2 = vitri2;
    }
    private int cau;
    private int vitri1;
    private int vitri2;
    private String ngayve="";

    public String getNgayve() {
        return ngayve;
    }

    public void setNgayve(String ngayve) {
        this.ngayve = ngayve;
    }
}
