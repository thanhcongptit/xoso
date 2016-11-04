/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import inet.util.UTF8Tool;
import java.math.BigDecimal;

/**
 *
 * @author hanhlm
 */
public class ThongKeVip {
    private BigDecimal id;
    private String dau;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getXien() {
        return xien;
    }

    public void setXien(String xien) {
        this.xien = xien;
    }

    public String getVenhieu() {
        return venhieu;
    }

    public void setVenhieu(String venhieu) {
        this.venhieu = venhieu;
    }

    public String getVeit() {
        return veit;
    }

    public void setVeit(String veit) {
        this.veit = veit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGen_date() {
        return gen_date;
    }

    public void setGen_date(String gen_date) {
        this.gen_date = gen_date;
    }
    private String duoi;
    private String vip;
    private String xien;
    private String venhieu;
    private String veit;
    private String code;
    private String gen_date;
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    private String region;
    
    private String link;

    public String getLink() {
        
        if(company!=null&&code!=null){
            link=UTF8Tool.coDau2KoDau(company).replace(" ", "-")+"-xs"+code;
            link=UTF8Tool.coDau2KoDau(link.toLowerCase());
        }
        
        return link;
    }
}
