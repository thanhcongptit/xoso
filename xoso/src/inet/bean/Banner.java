/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author 24h
 */
public class Banner implements Serializable{
    
    private BigDecimal id;
    private String page;
    private String code;
    private int position;
    private Timestamp genDate;
    private Timestamp lastUpdated;
    private String desc;
    
    public static final int POSITION_HEADER = 0;
    public static final int POSITION_TOP_1 = 1;
    public static final int POSITION_TOP_2 = 2;
    public static final int POSITION_CONTENT = 3;
    public static final int POSITION_LEFT = 4;
    public static final int POSITION_RIGHT = 5;
    public static final int POSITION_BOTTOM = 6;

    public Banner() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Timestamp getGenDate() {
        return genDate;
    }

    public void setGenDate(Timestamp genDate) {
        this.genDate = genDate;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public String getPositionName(){
        switch (position){
            case POSITION_HEADER: return "Banner Header";
            case POSITION_TOP_1: return "Banner Top 1";
            case POSITION_TOP_2: return "Banner Top 2";
            case POSITION_CONTENT: return "Banner Content";
            case POSITION_LEFT: return "Banner Left";
            case POSITION_RIGHT: return "Banner Right";
            case POSITION_BOTTOM: return "Neo Footer";
        }
        return "";
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
