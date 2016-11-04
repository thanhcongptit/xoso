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

public class XSVuiChotSo {

    private BigDecimal id;
    private BigDecimal userId;
    private String betNumber;
    private int numberOrder;
    private int betType;
    private String betDay;
    private BigDecimal betCoin;
    private String genDate;
    private int result;
    private Timestamp resultDate;
    private BigDecimal earning;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public XSVuiChotSo() {
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

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getBetType() {
        return betType;
    }

    public void setBetType(int betType) {
        this.betType = betType;
    }

    public String getBetDay() {
        return betDay;
    }

    public void setBetDay(String betDay) {
        this.betDay = betDay;
    }

    public BigDecimal getBetCoin() {
        return betCoin;
    }

    public void setBetCoin(BigDecimal betCoin) {
        this.betCoin = betCoin;
    }

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Timestamp getResultDate() {
        return resultDate;
    }

    public void setResultDate(Timestamp resultDate) {
        this.resultDate = resultDate;
    }

    public BigDecimal getEarning() {
        return earning;
    }

    public void setEarning(BigDecimal earning) {
        this.earning = earning;
    }
}
