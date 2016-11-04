package com.soicaupro.thongkebacnho.domain;

import java.sql.Date;
import java.util.List;

public class LotoTheoGiaiBachThuDTO {
	private Date openDate;
    private String value;
    private String times;
	private String nameAward;
    private String loto;
    private String miss;
    private String n1;
    private String n2;
    private String n3;
    private String n4;
    private String n5;
    List<LotoTheoGiaiDetailDTO> list10;
    private String rs;
    
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNameAward() {
		return nameAward;
	}
	public void setNameAward(String nameAward) {
		this.nameAward = nameAward;
	}
	public String getLoto() {
		return loto;
	}
	public void setLoto(String loto) {
		this.loto = loto;
	}
	public String getMiss() {
		return miss;
	}
	public void setMiss(String miss) {
		this.miss = miss;
	}
	public String getN1() {
		return n1;
	}
	public void setN1(String n1) {
		this.n1 = n1;
	}
	public String getN2() {
		return n2;
	}
	public void setN2(String n2) {
		this.n2 = n2;
	}
	public String getN3() {
		return n3;
	}
	public void setN3(String n3) {
		this.n3 = n3;
	}
	public String getN4() {
		return n4;
	}
	public void setN4(String n4) {
		this.n4 = n4;
	}
	public String getN5() {
		return n5;
	}
	public void setN5(String n5) {
		this.n5 = n5;
	}
	public List<LotoTheoGiaiDetailDTO> getList10() {
		return list10;
	}
	public void setList10(List<LotoTheoGiaiDetailDTO> list10) {
		this.list10 = list10;
	}
	public String getRs() {
		return rs;
	}
	public void setRs(String rs) {
		this.rs = rs;
	}
}
