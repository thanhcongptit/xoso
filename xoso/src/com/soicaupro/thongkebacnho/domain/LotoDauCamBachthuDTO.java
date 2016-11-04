package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoDauCamBachthuDTO {
	private List<OpenDateDTO> openDateDTOs;
	public List<OpenDateDTO> getOpenDateDTOs() {
		return openDateDTOs;
	}
	public void setOpenDateDTOs(List<OpenDateDTO> openDateDTOs) {
		this.openDateDTOs = openDateDTOs;
	}
	public List<ScreenBachthuSpecialDTO> getResults() {
		return results;
	}
	public void setResults(List<ScreenBachthuSpecialDTO> results) {
		this.results = results;
	}
	private List<ScreenBachthuSpecialDTO> results;
}
