package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoDacBiet3NgayDTO {
	private List<ScreenThreeDaySpecialDTO> results;
	private List<SpecialAwardDTO> awards;
	public List<ScreenThreeDaySpecialDTO> getResults() {
		return results;
	}
	public void setResults(List<ScreenThreeDaySpecialDTO> results) {
		this.results = results;
	}
	public List<SpecialAwardDTO> getAwards() {
		return awards;
	}
	public void setAwards(List<SpecialAwardDTO> awards) {
		this.awards = awards;
	}
	
	
}
