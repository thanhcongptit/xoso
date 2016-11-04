/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.util.List;

/**
 *
 * @author HanhDung
 */
public class LotoOfDacBiet {
    private String ngayve;

    public String getNgayve() {
        return ngayve;
    }

    public void setNgayve(String ngayve) {
        this.ngayve = ngayve;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    private List<LotoCap> listLotoCap1;

    public List<LotoCap> getListLotoCap2() {
        return listLotoCap2;
    }

    public void setListLotoCap2(List<LotoCap> listLotoCap2) {
        this.listLotoCap2 = listLotoCap2;
    }

    public List<LotoCap> getListLotoCap3() {
        return listLotoCap3;
    }

    public void setListLotoCap3(List<LotoCap> listLotoCap3) {
        this.listLotoCap3 = listLotoCap3;
    }
    private List<LotoCap> listLotoCap2;
    private List<LotoCap> listLotoCap3;

    public List<LotoCap> getListLotoCap1() {
        return listLotoCap1;
    }

    public void setListLotoCap1(List<LotoCap> listLotoCap1) {
        this.listLotoCap1 = listLotoCap1;
    }

    
    private String special;
}
