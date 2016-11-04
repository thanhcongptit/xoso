package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoDacBietBachThuDTO {
	private List<ScreenBachthuSpecialDTO> results;
	public List<ScreenBachthuSpecialDTO> getResults() {
		return results;
	}
	public void setResults(List<ScreenBachthuSpecialDTO> results) {
		this.results = results;	
	}
	public List<SpecialAwardDTO> getAwards() {
		return awards;
	}
	public void setAwards(List<SpecialAwardDTO> awards) {
		this.awards = awards;
	}
	private List<SpecialAwardDTO> awards;
}
