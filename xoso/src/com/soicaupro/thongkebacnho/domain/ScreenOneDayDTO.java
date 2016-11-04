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
public class ScreenOneDayDTO implements Comparable<ScreenOneDayDTO> {
    private String[] couple;
    private int size; 
    private List<ResultOneDayDTO> oneDayDTOs;

    public String[] getCouple() {
        return couple;
    }

    public void setCouple(String[] couple) {
        this.couple = couple;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ResultOneDayDTO> getOneDayDTOs() {
        return oneDayDTOs;
    }

    public void setOneDayDTOs(List<ResultOneDayDTO> oneDayDTOs) {
        this.oneDayDTOs = oneDayDTOs;
    }

    @Override
    public int compareTo(ScreenOneDayDTO o) {
        int compareQuantity = ((ScreenOneDayDTO) o).getSize(); 
		
		//ascending order
	return compareQuantity - this.size;
    }
}
