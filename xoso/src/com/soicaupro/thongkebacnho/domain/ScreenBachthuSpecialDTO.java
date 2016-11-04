/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.domain;

import java.util.List;

/**
 *
 * @author conglt
 */
public class ScreenBachthuSpecialDTO implements Comparable<ScreenBachthuSpecialDTO>{
    private String loto;
    private int size; 
    private List<ResultOneDayDTO> listDay1;
    private List<ResultOneDayDTO> listDay2;
    private List<ResultOneDayDTO> listDay3;

    /**
     * @return the loto
     */
    public String getLoto() {
        return loto;
    }

    /**
     * @param loto the loto to set
     */
    public void setLoto(String loto) {
        this.loto = loto;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the listDay1
     */
    public List<ResultOneDayDTO> getListDay1() {
        return listDay1;
    }

    /**
     * @param listDay1 the listDay1 to set
     */
    public void setListDay1(List<ResultOneDayDTO> listDay1) {
        this.listDay1 = listDay1;
    }

    /**
     * @return the listDay2
     */
    public List<ResultOneDayDTO> getListDay2() {
        return listDay2;
    }

    /**
     * @param listDay2 the listDay2 to set
     */
    public void setListDay2(List<ResultOneDayDTO> listDay2) {
        this.listDay2 = listDay2;
    }

    /**
     * @return the listDay3
     */
    public List<ResultOneDayDTO> getListDay3() {
        return listDay3;
    }

    /**
     * @param listDay3 the listDay3 to set
     */
    public void setListDay3(List<ResultOneDayDTO> listDay3) {
        this.listDay3 = listDay3;
    }
    
    @Override
    public int compareTo(ScreenBachthuSpecialDTO o) {
        int compareQuantity = ((ScreenBachthuSpecialDTO) o).getSize(); 
	return compareQuantity - this.size;
    }
}
