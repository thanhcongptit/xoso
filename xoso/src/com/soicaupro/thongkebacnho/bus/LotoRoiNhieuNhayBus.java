package com.soicaupro.thongkebacnho.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.Constant;
import com.soicaupro.thongkebacnho.dao.LotoTheoGiaiDao;
import com.soicaupro.thongkebacnho.domain.AwardDTO;
import com.soicaupro.thongkebacnho.domain.AwardDetailDTO;
import com.soicaupro.thongkebacnho.domain.LotoNhieuNhayBachThuDetailDTO;
import com.soicaupro.thongkebacnho.domain.LotoNhieuNhayDTO;
import com.soicaupro.thongkebacnho.domain.LotoNhieuNhieuNhayBachThuDTO;
import com.soicaupro.thongkebacnho.domain.OpenDateDTO;
import com.soicaupro.thongkebacnho.domain.ResultOneDayDTO;
import com.soicaupro.thongkebacnho.domain.ScreenThreeDaySpecialDTO;

public class LotoRoiNhieuNhayBus {
	public LotoNhieuNhayDTO getListDateNhieuNhay(int indexLoto, String toDate, int sonhay, int times) {
		
		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		LotoNhieuNhayDTO lotoNhieuNhayDTO = new LotoNhieuNhayDTO();
		List<OpenDateDTO> openDateDTOs = getListDateNhieuNhay(indexLoto, sonhay, times, cacgiai);
		List<ScreenThreeDaySpecialDTO> result = getListResultThreeDay(openDateDTOs, indexLoto, cacgiai);

		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getCouple()[0] + "," + result.get(i).getCouple()[1] + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoNhieuNhayDTO.setResult(result);
		openDateDTOs = getListAward(openDateDTOs, result);
		lotoNhieuNhayDTO.setOpenDates(openDateDTOs);
		return lotoNhieuNhayDTO;
	}

	private List<ScreenThreeDaySpecialDTO> getListResultThreeDay(List<OpenDateDTO> openDateDTOs, int indexLoto,
			AwardDetailDTO[][] cacgiai) {
		List<ScreenThreeDaySpecialDTO> daySpecialDTOs = new ArrayList<ScreenThreeDaySpecialDTO>();

		for (int i = 0; i < 50; i++) {
			ScreenThreeDaySpecialDTO screenThreeDaySpecialDTO = new ScreenThreeDaySpecialDTO();
			screenThreeDaySpecialDTO.setCouple(Constant.coupleLoto[i]);
			List<ResultOneDayDTO> list1 = getList(openDateDTOs, 1, Constant.coupleLoto[i], cacgiai);
			List<ResultOneDayDTO> list2 = getList(openDateDTOs, 2, Constant.coupleLoto[i], cacgiai);
			List<ResultOneDayDTO> list3 = getList(openDateDTOs, 3, Constant.coupleLoto[i], cacgiai);

			screenThreeDaySpecialDTO.setListDay1(list1);
			screenThreeDaySpecialDTO.setListDay2(list2);
			screenThreeDaySpecialDTO.setListDay3(list3);
			screenThreeDaySpecialDTO.setSize(list1.size() + list2.size() + list3.size());
			daySpecialDTOs.add(screenThreeDaySpecialDTO);
		}
		Collections.sort(daySpecialDTOs);
		List<ScreenThreeDaySpecialDTO> results = daySpecialDTOs.subList(0, 10);
		return results;
	}

	private List<ResultOneDayDTO> getList(List<OpenDateDTO> openDateDTOs, int i, String loto[],
			AwardDetailDTO[][] cacgiai) {
		List<ResultOneDayDTO> list = new ArrayList<ResultOneDayDTO>();

		for (OpenDateDTO openDateDTO : openDateDTOs) {

			List<String> s = new ArrayList<String>();

			int index = openDateDTO.getIndex();
			int temp = index - i;
			if (temp >= 0) {

				for (int k = 0; k < 27; k++) {
					if (cacgiai[temp][k].getValue().equals(loto[0])) {
						s.add(loto[0]);
					} else if (cacgiai[temp][k].getValue().equals(loto[1])) {
						s.add(loto[1]);
					}

				}

				if (s != null & s.size() > 0) {
					ResultOneDayDTO resultOneDayDTO = new ResultOneDayDTO();
					String rs = CommonUtil.getStringFromList(s);
					resultOneDayDTO.setOpen_date(cacgiai[temp][0].getOpen_date());
					resultOneDayDTO.setResult(rs);
					list.add(resultOneDayDTO);
				}
			}
		}

		return list;
	}

	private List<OpenDateDTO> getListDateNhieuNhay(int indexLoto, int sonhay, int times, AwardDetailDTO[][] cacgiai) {
		List<OpenDateDTO> dateList = new ArrayList<OpenDateDTO>();

		int total = 0;
		int i = 0;

		while (total < times && i < cacgiai.length) {
			if (checkCondition(cacgiai, indexLoto, i, sonhay)) {
				total++;
				OpenDateDTO openDateDTO = new OpenDateDTO();
				openDateDTO.setIndex(i);
				openDateDTO.setOpenDate(cacgiai[i][0].getOpen_date());
				openDateDTO.setValue(getResultLotoByDay(cacgiai, indexLoto, i));

				dateList.add(openDateDTO);
			}

			i++;
		}

		return dateList;
	}

	private String getResultLotoByDay(AwardDetailDTO[][] cacgiai, int indexLoto, int i) {
		String loto[] = Constant.coupleLoto[indexLoto];
		List<String> rs = new ArrayList<String>();

		for (int k = 0; k < 27; k++) {
			if (loto[0].equalsIgnoreCase(cacgiai[i][k].getValue())) {
				rs.add(loto[0]);
			} else if (loto[1].equalsIgnoreCase(cacgiai[i][k].getValue())) {
				rs.add(loto[1]);
			}

		}

		return CommonUtil.getStringFromList(rs);
	}

	private boolean checkCondition(AwardDetailDTO[][] cacgiai, int indexLoto, int i, int sonhay) {
		int total = 0;
		String loto[] = Constant.coupleLoto[indexLoto];

		for (int k = 0; k < 27; k++) {
			if (loto[0].equalsIgnoreCase(cacgiai[i][k].getValue())
					|| loto[1].equalsIgnoreCase(cacgiai[i][k].getValue())) {
				total++;

				if (total == sonhay) {
					return true;
				}
			}

		}
		return false;
	}

	private List<OpenDateDTO> getListAward(List<OpenDateDTO> awards, List<ScreenThreeDaySpecialDTO> results) {

		List<OpenDateDTO> rs = new ArrayList<>();

		for (OpenDateDTO awardDTO : awards) {
			if (!checkExitAwardLoto(results, awardDTO)) {
				rs.add(awardDTO);
			}
		}

		return rs;
	}

	private boolean checkExitAwardLoto(List<ScreenThreeDaySpecialDTO> results, OpenDateDTO awardDTO) {
		boolean rs = true;

		for (ScreenThreeDaySpecialDTO dTO : results) {
			String s1 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay1(), 1);
			String s2 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay2(), 2);
			String s3 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay3(), 3);

			if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2) || !CommonUtil.isEmptyString(s3)) {

				return false;
			}
		}

		return rs;
	}
	
	//----------------
	public LotoNhieuNhieuNhayBachThuDTO getLotoTheoGiaiBachThuDTO(int indexLoto, String toDate, int sonhay, int times) {
		
		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 6);
		toDate = CommonUtil.convertDateToString1(date);
		
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		LotoNhieuNhieuNhayBachThuDTO lotoNhieuNhayDTO = new LotoNhieuNhieuNhayBachThuDTO();
		List<OpenDateDTO> openDateDTOs = getListDateNhieuNhay(indexLoto, sonhay, times, cacgiai);
		List<LotoNhieuNhayBachThuDetailDTO> result = getListResultBachThu(openDateDTOs, indexLoto, cacgiai);

		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getLoto() + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoNhieuNhayDTO.setResults(result);
		openDateDTOs = getListAward1(openDateDTOs, result);
		lotoNhieuNhayDTO.setOpenDates(openDateDTOs);
		return lotoNhieuNhayDTO;
	}

	private List<LotoNhieuNhayBachThuDetailDTO> getListResultBachThu(List<OpenDateDTO> openDateDTOs, int indexLoto,
			AwardDetailDTO[][] cacgiai) {
		List<LotoNhieuNhayBachThuDetailDTO> daySpecialDTOs = new ArrayList<LotoNhieuNhayBachThuDetailDTO>();

		for (int i = 0; i < 100; i++) {
			LotoNhieuNhayBachThuDetailDTO screenThreeDaySpecialDTO = new LotoNhieuNhayBachThuDetailDTO();
			String loto = "";
			if(i<10) {
				loto = "0" + i;
			} else {
				loto = "" + i;
			}
			
			String []lotos = new String[] {loto, loto};
			screenThreeDaySpecialDTO.setLoto(loto);
			List<ResultOneDayDTO> list1 = getList(openDateDTOs, 1, lotos, cacgiai);
			List<ResultOneDayDTO> list2 = getList(openDateDTOs, 2, lotos, cacgiai);
			List<ResultOneDayDTO> list3 = getList(openDateDTOs, 3, lotos, cacgiai);
			List<ResultOneDayDTO> list4 = getList(openDateDTOs, 4, lotos, cacgiai);
			List<ResultOneDayDTO> list5 = getList(openDateDTOs, 5, lotos, cacgiai);

			screenThreeDaySpecialDTO.setListDay1(list1);
			screenThreeDaySpecialDTO.setListDay2(list2);
			screenThreeDaySpecialDTO.setListDay3(list3);
			screenThreeDaySpecialDTO.setListDay4(list4);
			screenThreeDaySpecialDTO.setListDay5(list5);
			screenThreeDaySpecialDTO.setSize(list1.size() + list2.size() + list3.size() + list4.size() + list5.size());
			daySpecialDTOs.add(screenThreeDaySpecialDTO);
		}
		Collections.sort(daySpecialDTOs);
		List<LotoNhieuNhayBachThuDetailDTO> results = daySpecialDTOs.subList(0, 10);
		return results;
	}
	
	private List<OpenDateDTO> getListAward1(List<OpenDateDTO> awards, List<LotoNhieuNhayBachThuDetailDTO> results) {

		List<OpenDateDTO> rs = new ArrayList<>();

		for (OpenDateDTO awardDTO : awards) {
			if (!checkExitAwardLoto1(results, awardDTO)) {
				rs.add(awardDTO);
			}
		}

		return rs;
	}

	private boolean checkExitAwardLoto1(List<LotoNhieuNhayBachThuDetailDTO> results, OpenDateDTO awardDTO) {
		boolean rs = true;

		for (LotoNhieuNhayBachThuDetailDTO dTO : results) {
			String s1 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay1(), 1);
			String s2 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay2(), 2);
			String s3 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay3(), 3);
			String s4 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay3(), 4);
			String s5 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay3(), 5);

			if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2) || !CommonUtil.isEmptyString(s3)
					|| !CommonUtil.isEmptyString(s4) || !CommonUtil.isEmptyString(s5)) {

				return false;
			}
		}

		return rs;
	}
	
	public static void main(String a[]) {
		new LotoRoiNhieuNhayBus().getLotoTheoGiaiBachThuDTO(45, "25-03-2016", 2, 30);
	}
}
