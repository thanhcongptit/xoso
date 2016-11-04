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
public class XSVuiChotSoResult {
    private String betNumber;
    private int betType;
    private long betCoin;
    private long receiveCoin;

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }

    public int getBetType() {
        return betType;
    }

    public void setBetType(int betType) {
        this.betType = betType;
    }

    public long getBetCoin() {
        return betCoin;
    }

    public void setBetCoin(long betCoin) {
        this.betCoin = betCoin;
    }

    public long getReceiveCoin() {
        return receiveCoin;
    }

    public void setReceiveCoin(long receiveCoin) {
        this.receiveCoin = receiveCoin;
    }
    
}
