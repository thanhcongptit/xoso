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
public class ScreenTwoDaySpecialDTO implements Comparable<ScreenTwoDaySpecialDTO>{
    private String[] couple;
    private int size; 
    private List<ResultOneDayDTO> listDay1;
    private List<ResultOneDayDTO> listDay2;

    /**
     * @return the couple
     */
    public String[] getCouple() {
        return couple;
    }

    /**
     * @param couple the couple to set
     */
    public void setCouple(String[] couple) {
        this.couple = couple;
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
    
    @Override
    public int compareTo(ScreenTwoDaySpecialDTO o) {
        int compareQuantity = ((ScreenTwoDaySpecialDTO) o).getSize(); 
		
		//ascending order
	return compareQuantity - this.size;
    }
}
