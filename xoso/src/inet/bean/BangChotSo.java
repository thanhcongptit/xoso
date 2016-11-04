/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import inet.util.DateUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author 24h
 */
public class BangChotSo {
    
    private BigDecimal memberId;
    private String memberName;
    private Timestamp ngaychot;
    private String listLoto;
    private String ngaychotHienthi;

    public BangChotSo() {
        listLoto = "";
    }

    public BigDecimal getMemberId() {
        return memberId;
    }

    public void setMemberId(BigDecimal memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Timestamp getNgaychot() {
        return ngaychot;
    }

    public void setNgaychot(Timestamp ngaychot) {
        this.ngaychot = ngaychot;
    }

    public String getListLoto() {
        return listLoto;
    }

    public void setListLoto(String listLoto) {
        this.listLoto = listLoto;
    }

    public String getNgaychotHienthi() {
        if(ngaychot != null){
            return DateUtil.timestamp2String(ngaychot, "HH:mm:ss dd/MM/yyyy");
        }
        return "";
    }

    public void setNgaychotHienthi(String ngaychotHienthi) {
        this.ngaychotHienthi = ngaychotHienthi;
    }
    
}
