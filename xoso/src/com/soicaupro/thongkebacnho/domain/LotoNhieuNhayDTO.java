package com.soicaupro.thongkebacnho.domain;

import java.util.List;

public class LotoNhieuNhayDTO {
	private List<OpenDateDTO> openDates;
	private List<ScreenThreeDaySpecialDTO> result;

	public List<ScreenThreeDaySpecialDTO> getResult() {
		return result;
	}

	public void setResult(List<ScreenThreeDaySpecialDTO> result) {
		this.result = result;
	}

	public List<OpenDateDTO> getOpenDates() {
		return openDates;
	}

	public void setOpenDates(List<OpenDateDTO> openDates) {
		this.openDates = openDates;
	}
}
