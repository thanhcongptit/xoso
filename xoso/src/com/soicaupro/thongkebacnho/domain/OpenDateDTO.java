package com.soicaupro.thongkebacnho.domain;

import java.sql.Date;

public class OpenDateDTO {
	private int index;
	private Date openDate;
	private String value;

    public OpenDateDTO() {
    }
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	
}
