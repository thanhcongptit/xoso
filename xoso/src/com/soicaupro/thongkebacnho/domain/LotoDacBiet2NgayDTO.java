package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoDacBiet2NgayDTO {
	private List<ScreenTwoDaySpecialDTO> results;
	private List<SpecialAwardDTO> awards;
	public List<ScreenTwoDaySpecialDTO> getResults() {
		return results;
	}
	public void setResults(List<ScreenTwoDaySpecialDTO> results) {
		this.results = results;
	}
	public List<SpecialAwardDTO> getAwards() {
		return awards;
	}
	public void setAwards(List<SpecialAwardDTO> awards) {
		this.awards = awards;
	}
	
}
