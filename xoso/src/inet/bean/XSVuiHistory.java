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

public class XSVuiHistory {

    private BigDecimal id;
    private BigDecimal userId;
    private BigDecimal transCoin;
    private BigDecimal leftCoin;
    private String transContent;
    private int transType;
    private Timestamp transDate;
    private int status;
    private String errorDesc;
    private BigDecimal contentId;
    private BigDecimal followUserId;
    private BigDecimal followedUserId;
    private String transDateStr;

    public XSVuiHistory() {
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

    public BigDecimal getTransCoin() {
        return transCoin;
    }

    public void setTransCoin(BigDecimal transCoin) {
        this.transCoin = transCoin;
    }

    public BigDecimal getLeftCoin() {
        return leftCoin;
    }

    public void setLeftCoin(BigDecimal leftCoin) {
        this.leftCoin = leftCoin;
    }

    public String getTransContent() {
        return transContent;
    }

    public void setTransContent(String transContent) {
        this.transContent = transContent;
    }

    public int getTransType() {
        return transType;
    }

    public void setTransType(int transType) {
        this.transType = transType;
    }

    public Timestamp getTransDate() {
        return transDate;
    }

    public void setTransDate(Timestamp transDate) {
        this.transDate = transDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public BigDecimal getContentId() {
        return contentId;
    }

    public void setContentId(BigDecimal contentId) {
        this.contentId = contentId;
    }

    public BigDecimal getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(BigDecimal followUserId) {
        this.followUserId = followUserId;
    }

    public BigDecimal getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(BigDecimal followedUserId) {
        this.followedUserId = followedUserId;
    }

    public String getTransDateStr() {
        return transDateStr;
    }

    public void setTransDateStr(String transDateStr) {
        this.transDateStr = transDateStr;
    }
}
