/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.domain;

import java.sql.Date;

/**
 *
 * @author conglt
 */

public class SpecialAwardDTO {
    private Date openDate;
    private String specialAward;

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getSpecialAward() {
        return specialAward;
    }

    public void setSpecialAward(String specialAward) {
        this.specialAward = specialAward;
    } 
}
