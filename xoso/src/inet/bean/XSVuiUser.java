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
import java.util.List;

public class XSVuiUser {

    private BigDecimal id;
    private String mobile;
    private String username;
    private String password;
    private int displayType;
    private int status;
    private Timestamp genDate;
    private Timestamp lastUpdate;
    private Timestamp lastLogin;
    private BigDecimal leftCoin;
    private BigDecimal lastCoin;
    private BigDecimal tempCoin;
    private Timestamp lastTrans;
    private String hashCode;
    private int follow;
    private int followed;
    private BigDecimal percentLo;
    private BigDecimal percentDe;
    private BigDecimal maxDayProfit;
    private BigDecimal maxDayCoin;
    private List<XSVuiMaxDayResult> maxDayResultList;
    private int rank;

    public XSVuiUser() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getGenDate() {
        return genDate;
    }

    public void setGenDate(Timestamp genDate) {
        this.genDate = genDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public BigDecimal getLeftCoin() {
        return leftCoin;
    }

    public void setLeftCoin(BigDecimal leftCoin) {
        this.leftCoin = leftCoin;
    }

    public BigDecimal getLastCoin() {
        return lastCoin;
    }

    public void setLastCoin(BigDecimal lastCoin) {
        this.lastCoin = lastCoin;
    }

    public BigDecimal getTempCoin() {
        return tempCoin;
    }

    public void setTempCoin(BigDecimal tempCoin) {
        this.tempCoin = tempCoin;
    }

    public Timestamp getLastTrans() {
        return lastTrans;
    }

    public void setLastTrans(Timestamp lastTrans) {
        this.lastTrans = lastTrans;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    public BigDecimal getPercentLo() {
        return percentLo;
    }

    public void setPercentLo(BigDecimal percentLo) {
        this.percentLo = percentLo;
    }

    public BigDecimal getPercentDe() {
        return percentDe;
    }

    public void setPercentDe(BigDecimal percentDe) {
        this.percentDe = percentDe;
    }

    public BigDecimal getMaxDayProfit() {
        return maxDayProfit;
    }

    public void setMaxDayProfit(BigDecimal maxDayProfit) {
        this.maxDayProfit = maxDayProfit;
    }

    public BigDecimal getMaxDayCoin() {
        return maxDayCoin;
    }

    public void setMaxDayCoin(BigDecimal maxDayCoin) {
        this.maxDayCoin = maxDayCoin;
    }

    public List<XSVuiMaxDayResult> getMaxDayResultList() {
        return maxDayResultList;
    }

    public void setMaxDayResultList(List<XSVuiMaxDayResult> maxDayResultList) {
        this.maxDayResultList = maxDayResultList;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
}
