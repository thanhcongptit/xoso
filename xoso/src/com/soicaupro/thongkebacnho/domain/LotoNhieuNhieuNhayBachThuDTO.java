package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoNhieuNhieuNhayBachThuDTO {
	private List<OpenDateDTO> openDates;
	private List<LotoNhieuNhayBachThuDetailDTO> results;
	public List<OpenDateDTO> getOpenDates() {
		return openDates;
	}
	public void setOpenDates(List<OpenDateDTO> openDates) {
		this.openDates = openDates;
	}
	public List<LotoNhieuNhayBachThuDetailDTO> getResults() {
		return results;
	}
	public void setResults(List<LotoNhieuNhayBachThuDetailDTO> results) {
		this.results = results;
	}
}
