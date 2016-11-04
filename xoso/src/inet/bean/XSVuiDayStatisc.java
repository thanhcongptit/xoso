/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.bean;

/**
 *
 * @author hanm
 */
import java.math.BigDecimal;
import java.sql.Timestamp;

public class XSVuiDayStatisc {

    private BigDecimal id;
    private BigDecimal userId;
    private String statiscDay;
    private BigDecimal totalBet;
    private BigDecimal totalReceive;
    private BigDecimal totalEarning;
    private Timestamp genDate;

    public XSVuiDayStatisc() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getStatiscDay() {
        return statiscDay;
    }

    public void setStatiscDay(String statiscDay) {
        this.statiscDay = statiscDay;
    }

    public BigDecimal getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(BigDecimal totalBet) {
        this.totalBet = totalBet;
    }

    public BigDecimal getTotalReceive() {
        return totalReceive;
    }

    public void setTotalReceive(BigDecimal totalReceive) {
        this.totalReceive = totalReceive;
    }

    public BigDecimal getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(BigDecimal totalEarning) {
        this.totalEarning = totalEarning;
    }

    public Timestamp getGenDate() {
        return genDate;
    }

    public void setGenDate(Timestamp genDate) {
        this.genDate = genDate;
    }
}
