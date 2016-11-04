/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.bean;

import java.math.BigDecimal;

/**
 *
 * @author hanm
 */
public class XSVuiTopUser {
    private BigDecimal userId;
    private String mobile;
    private BigDecimal win;
    private BigDecimal total;
    private BigDecimal percentWin;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getWin() {
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPercentWin() {
        return percentWin;
    }

    public void setPercentWin(BigDecimal percentWin) {
        this.percentWin = percentWin;
    }
}
