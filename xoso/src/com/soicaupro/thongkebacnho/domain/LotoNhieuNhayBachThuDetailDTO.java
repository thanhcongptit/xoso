package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoNhieuNhayBachThuDetailDTO implements Comparable<LotoNhieuNhayBachThuDetailDTO> {
	private String loto;
	private int size;
	private List<ResultOneDayDTO> listDay1;
	private List<ResultOneDayDTO> listDay2;
	private List<ResultOneDayDTO> listDay3;
	private List<ResultOneDayDTO> listDay4;
	private List<ResultOneDayDTO> listDay5;

	public List<ResultOneDayDTO> getListDay4() {
		return listDay4;
	}

	public void setListDay4(List<ResultOneDayDTO> listDay4) {
		this.listDay4 = listDay4;
	}

	public List<ResultOneDayDTO> getListDay5() {
		return listDay5;
	}

	public void setListDay5(List<ResultOneDayDTO> listDay5) {
		this.listDay5 = listDay5;
	}

	public String getLoto() {
		return loto;
	}

	public void setLoto(String loto) {
		this.loto = loto;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<ResultOneDayDTO> getListDay1() {
		return listDay1;
	}

	public void setListDay1(List<ResultOneDayDTO> listDay1) {
		this.listDay1 = listDay1;
	}

	public List<ResultOneDayDTO> getListDay2() {
		return listDay2;
	}

	public void setListDay2(List<ResultOneDayDTO> listDay2) {
		this.listDay2 = listDay2;
	}

	public List<ResultOneDayDTO> getListDay3() {
		return listDay3;
	}

	public void setListDay3(List<ResultOneDayDTO> listDay3) {
		this.listDay3 = listDay3;
	}

	@Override
	public int compareTo(LotoNhieuNhayBachThuDetailDTO o) {
		int compareQuantity = ((LotoNhieuNhayBachThuDetailDTO) o).getSize();
		return compareQuantity - this.size;
	}
}
