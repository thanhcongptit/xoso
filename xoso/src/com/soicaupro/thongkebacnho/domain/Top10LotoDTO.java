/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.domain;

/**
 *
 * @author conglt
 */
public class Top10LotoDTO {
    private String key;
    private String value;
    private boolean isTrue;

    public Top10LotoDTO(String key, String value) {
        this.key = key;
        this.value = value;
        isTrue = true;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the isTrue
     */
    public boolean isIsTrue() {
        return isTrue;
    }

    /**
     * @param isTrue the isTrue to set
     */
    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }
}
