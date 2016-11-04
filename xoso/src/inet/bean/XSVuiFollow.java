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

public class XSVuiFollow {

    private BigDecimal id;
    private BigDecimal userId;
    private BigDecimal followedUserId;
    private int status;
    private Timestamp genDate;
    private Timestamp lastUpdate;
    private BigDecimal totalPaidCoin;
    private BigDecimal totalReceiveCoin;
    private int user_rank;

    public int getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(int user_rank) {
        this.user_rank = user_rank;
    }
    public XSVuiFollow() {
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

    public BigDecimal getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(BigDecimal followedUserId) {
        this.followedUserId = followedUserId;
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

    public BigDecimal getTotalPaidCoin() {
        return totalPaidCoin;
    }

    public void setTotalPaidCoin(BigDecimal totalPaidCoin) {
        this.totalPaidCoin = totalPaidCoin;
    }

    public BigDecimal getTotalReceiveCoin() {
        return totalReceiveCoin;
    }

    public void setTotalReceiveCoin(BigDecimal totalReceiveCoin) {
        this.totalReceiveCoin = totalReceiveCoin;
    }
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate_follow() {
        return date_follow;
    }

    public void setDate_follow(String date_follow) {
        this.date_follow = date_follow;
    }
    private String date_follow;
}
