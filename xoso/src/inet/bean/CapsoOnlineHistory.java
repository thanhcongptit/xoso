/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thangdm
 */
public class CapsoOnlineHistory {
    
    private Timestamp ngaychoi;
    private int tongDiem;
    private int tongNhay;
    private int tongThangthua;
    private int tienHientai;
    private List<CapsoOnline> listCapso = new ArrayList<CapsoOnline>();

    public CapsoOnlineHistory() {
    }

    public Timestamp getNgaychoi() {
        return ngaychoi;
    }

    public void setNgaychoi(Timestamp ngaychoi) {
        this.ngaychoi = ngaychoi;
    }

    public int getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(int tongDiem) {
        this.tongDiem = tongDiem;
    }

    public int getTongNhay() {
        return tongNhay;
    }

    public void setTongNhay(int tongNhay) {
        this.tongNhay = tongNhay;
    }

    public int getTongThangthua() {
        return tongThangthua;
    }

    public void setTongThangthua(int tongThangthua) {
        this.tongThangthua = tongThangthua;
    }

    public List<CapsoOnline> getListCapso() {
        return listCapso;
    }

    public void setListCapso(List<CapsoOnline> listCapso) {
        this.listCapso = listCapso;
    }

    public int getTienHientai() {
        return tienHientai;
    }

    public void setTienHientai(int tienHientai) {
        this.tienHientai = tienHientai;
    }
    
    
}
