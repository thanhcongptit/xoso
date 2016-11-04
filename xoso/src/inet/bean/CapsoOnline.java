/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author 24h
 */
public class CapsoOnline {
    
    private BigDecimal id;
    private String capso;
    private int sodiem;
    private Timestamp ngaychoi;
    private Timestamp genDate;
    private BigDecimal nhay;
    private BigDecimal thangThua;
    private BigDecimal memberId;
    private BigDecimal currentMoney;
    
    //bonus
    private String memberName;

    public CapsoOnline() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCapso() {
        return capso;
    }

    public void setCapso(String capso) {
        this.capso = capso;
    }

    public int getSodiem() {
        return sodiem;
    }

    public void setSodiem(int sodiem) {
        this.sodiem = sodiem;
    }

    public Timestamp getNgaychoi() {
        return ngaychoi;
    }

    public void setNgaychoi(Timestamp ngaychoi) {
        this.ngaychoi = ngaychoi;
    }

    public Timestamp getGenDate() {
        return genDate;
    }

    public void setGenDate(Timestamp genDate) {
        this.genDate = genDate;
    }

    public BigDecimal getNhay() {
        return nhay;
    }

    public void setNhay(BigDecimal nhay) {
        this.nhay = nhay;
    }

    public BigDecimal getThangThua() {
        return thangThua;
    }

    public void setThangThua(BigDecimal thangThua) {
        this.thangThua = thangThua;
    }

    public BigDecimal getMemberId() {
        return memberId;
    }

    public void setMemberId(BigDecimal memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(BigDecimal currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    
}
