package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoDacBiet1NgayDTO {
	List<ScreenOneDayDTO> results;
	List<SpecialAwardDTO> awards;
	public List<ScreenOneDayDTO> getResults() {
		return results;
	}
	public void setResults(List<ScreenOneDayDTO> results) {
		this.results = results;
	}
	public List<SpecialAwardDTO> getAwards() {
		return awards;
	}
	public void setAwards(List<SpecialAwardDTO> awards) {
		this.awards = awards;
	}
}
