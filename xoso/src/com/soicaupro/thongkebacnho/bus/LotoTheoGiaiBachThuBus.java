/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho.bus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.dao.LotoTheoGiaiDao;
import com.soicaupro.thongkebacnho.domain.AwardDTO;
import com.soicaupro.thongkebacnho.domain.AwardDetailDTO;
import com.soicaupro.thongkebacnho.domain.LotoTheoGiaiBachThuDTO;
import com.soicaupro.thongkebacnho.domain.LotoTheoGiaiDetailDTO;

/**
 *
 * @author conglt
 */
public class LotoTheoGiaiBachThuBus {

	public List<LotoTheoGiaiBachThuDTO> getListLotoTheoGiaiDTOs(String fromDate, String toDate, String dataDate,
			int missTimes) {

		System.out.println("fromDate: " + fromDate + "--toDate: " + toDate + "--DataDate: " + dataDate + "--missTimes: "
				+ missTimes );
		List<LotoTheoGiaiBachThuDTO> giaiDTOs = new ArrayList<LotoTheoGiaiBachThuDTO>();

		Date date = CommonUtil.convertStringToDate(toDate);
		Date dateAward = new Date(date.getTime());
		date = CommonUtil.addDate2(date, 6);
		toDate = CommonUtil.convertDateToString1(date);

		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward(dataDate, toDate);
		AwardDetailDTO[][] cacgiai = getCacgiai(awardDTOs);
		System.out.println("size: " + awardDTOs.size());
		System.out.println("-----");

		Date from_Date = CommonUtil.convertStringToDate(fromDate);
		AwardDetailDTO[][] cacGiaiDate = new AwardDetailDTO[4][27];
		int index = 0;

		while (!dateAward.before(from_Date) && index < 4) {
			cacGiaiDate[index] = getAwardDetailDTOInDate(dateAward, cacgiai);
			index++;
			dateAward = CommonUtil.addDate2(dateAward, -1);
		}

/*		System.out.println("------");
		for (int i = 0; i < index; i++) {
			for (int j = 0; j < 27; j++) {
				System.out.print("--" + cacGiaiDate[i][j].getPosition() + ":" + cacGiaiDate[i][j].getValue());
			}
			System.out.println("------");
		}
		System.out.println("start: " + new Date());
*/
		for (int i = index - 1; i >= 0; i--) {
			for (int j = 0; j < 27; j++) {

				if(cacGiaiDate[i] != null) {
					List<java.sql.Date> dates = getListDate(cacgiai, j, cacGiaiDate[i][j].getValue(),
							cacGiaiDate[i][j].getOpen_date());

					for (int k = 0; k < 100; k++) {
						LotoTheoGiaiBachThuDTO lotoTheoGiaiDTO = getLotoGiaiDTO(cacgiai, cacGiaiDate, i, j, "" + k,
								missTimes, dates);

						if (lotoTheoGiaiDTO != null) {
							giaiDTOs.add(lotoTheoGiaiDTO);
						}
					}
				}
			}
		}

/*		System.out.println(giaiDTOs.size());
		for (int i = 0; i < giaiDTOs.size(); i++) {
			System.out.println("------------");
			System.out.println((i + 1) + "-" + giaiDTOs.get(i).getNameAward() + "--value: " + giaiDTOs.get(i).getValue()
					+ "--loto: " + giaiDTOs.get(i).getLoto() + "--"
					+ CommonUtil.convertDateToString(giaiDTOs.get(i).getOpenDate()) + "--" + giaiDTOs.get(i).getMiss());

			for (int j = 0; j < giaiDTOs.get(i).getList10().size(); j++) {
				System.out.println("lan" + (j + 1) + ": " + giaiDTOs.get(i).getList10().get(j).getRs() 
						+ " -- day: " + giaiDTOs.get(i).getList10().get(j).getColorDay());
			}

			System.out.println("");
			System.out.println("rs: " + giaiDTOs.get(i).getRs());
			System.out.println("N1: " + giaiDTOs.get(i).getN1() + "-N2: " + giaiDTOs.get(i).getN2() + "-N3: "
					+ giaiDTOs.get(i).getN3() + "-N4: " + giaiDTOs.get(i).getN4() + "-N5: "
							+ giaiDTOs.get(i).getN5());
			System.out.println("----------");
		}
		System.out.println("end: " + new Date());*/
		return giaiDTOs;
	}

	private AwardDetailDTO[] getAwardDetailDTOInDate(Date openDate, AwardDetailDTO[][] cacGiai) {
		for (int i = 0; i < cacGiai.length; i++) {
			java.sql.Date temp = cacGiai[i][0].getOpen_date();

			if (CommonUtil.convertDateToString3(openDate).equalsIgnoreCase(CommonUtil.convertDateToString(temp))) {
				return cacGiai[i];
			}
		}

		return null;
	}

	public static void main(String a[]) {
		LotoTheoGiaiBachThuBus bus = new LotoTheoGiaiBachThuBus();
		bus.getListLotoTheoGiaiDTOs("11-03-2016", "13-03-2016", "01-01-2005", 1);
	}

	private AwardDetailDTO[][] getCacgiai(List<AwardDTO> awardDTOs) {
		AwardDetailDTO[][] awardDetailDTOs = new AwardDetailDTO[awardDTOs.size()][27];

		for (int i = 0; i < awardDTOs.size(); i++) {
			AwardDTO awardDTO = awardDTOs.get(i);

			String special = awardDTO.getSpecial();
			String first = awardDTO.getFirst();
			String second = awardDTO.getSecond();
			String third = awardDTO.getThird();
			String fourth = awardDTO.getFourth();
			String fifth = awardDTO.getFifth();
			String sixth = awardDTO.getSixth();
			String seventh = awardDTO.getSeventh();
			java.sql.Date open_date = awardDTO.getOpenDate();

			AwardDetailDTO awardDetailDTO = new AwardDetailDTO();
			awardDetailDTO.setOpen_date(open_date);
			awardDetailDTO.setPosition("GDB");
			awardDetailDTO.setValue(special.substring(special.length() - 2, special.length()));
			awardDetailDTOs[i][0] = awardDetailDTO;

			AwardDetailDTO awardDetailDTO1 = new AwardDetailDTO();
			awardDetailDTO1.setOpen_date(open_date);
			awardDetailDTO1.setPosition("G1");
			awardDetailDTO1.setValue(first.substring(first.length() - 2, first.length()));
			awardDetailDTOs[i][1] = awardDetailDTO1;

			int index = 2;
			AwardDetailDTO giai2[] = getListAwardDetail(second, 2, open_date);

			for (int k = 0; k < giai2.length; k++) {
				awardDetailDTOs[i][index + k] = giai2[k];
			}
			index = index + giai2.length;

			AwardDetailDTO giai3[] = getListAwardDetail(third, 3, open_date);
			for (int k = 0; k < giai3.length; k++) {
				awardDetailDTOs[i][index + k] = giai3[k];
			}
			index = index + giai3.length;

			AwardDetailDTO giai4[] = getListAwardDetail(fourth, 4, open_date);
			for (int k = 0; k < giai4.length; k++) {
				awardDetailDTOs[i][index + k] = giai4[k];
			}
			index = index + giai4.length;

			AwardDetailDTO giai5[] = getListAwardDetail(fifth, 5, open_date);
			for (int k = 0; k < giai5.length; k++) {
				awardDetailDTOs[i][index + k] = giai5[k];
			}
			index = index + giai5.length;

			AwardDetailDTO giai6[] = getListAwardDetail(sixth, 6, open_date);
			for (int k = 0; k < giai6.length; k++) {
				awardDetailDTOs[i][index + k] = giai6[k];
			}
			index = index + giai6.length;

			AwardDetailDTO giai7[] = getListAwardDetail(seventh, 7, open_date);
			for (int k = 0; k < giai7.length; k++) {
				awardDetailDTOs[i][index + k] = giai7[k];
			}
		}

		return awardDetailDTOs;
	}

	private AwardDetailDTO[] getListAwardDetail(String award, int indexGiai, java.sql.Date open_date) {
		String awards[] = award.split("-");
		AwardDetailDTO awardDetailDTO[] = new AwardDetailDTO[awards.length];

		for (int i = 0; i < awards.length; i++) {
			AwardDetailDTO addto = new AwardDetailDTO();
			addto.setOpen_date(open_date);
			addto.setValue(awards[i].substring(awards[i].length() - 2, awards[i].length()));
			addto.setPosition("G" + indexGiai + "." + (i + 1));
			awardDetailDTO[i] = addto;
		}

		return awardDetailDTO;
	}

	private LotoTheoGiaiBachThuDTO getLotoGiaiDTO(AwardDetailDTO[][] cacgiai, AwardDetailDTO[][] cacGiaiDate, int i,
			int j, String loto, int missTimes, List<java.sql.Date> dates) {
		LotoTheoGiaiBachThuDTO lotoTheoGiaiDTO = new LotoTheoGiaiBachThuDTO();
		int miss = 0;
		int ngay1 = 0;
		int ngay2 = 0;
		int ngay3 = 0;
		int ngay4 = 0;
		int ngay5 = 0;

		for (int k = 0; k < dates.size(); k++) {
			if (miss > missTimes) {
				return null;
			}

			java.sql.Date temp = dates.get(k);
			int index = getIndex(cacgiai, temp);

			if (!checkCondition(cacgiai, index - 1, loto) && !checkCondition(cacgiai, index - 2, loto)
					&& !checkCondition(cacgiai, index - 3, loto) && !checkCondition(cacgiai, index - 4, loto)
					&& !checkCondition(cacgiai, index - 5, loto)) {
				miss = miss + 1;
			} else if (checkCondition(cacgiai, index - 1, loto)) {
				ngay1++;
			} else if (checkCondition(cacgiai, index - 2, loto)) {
				ngay2++;
			} else if (checkCondition(cacgiai, index - 3, loto)) {
				ngay3++;
			} else if (checkCondition(cacgiai, index - 4, loto)) {
				ngay4++;
			} else {
				ngay5++;
			}
		}

		if (miss > missTimes) {
			return null;
		}

		java.sql.Date dateAward = cacGiaiDate[i][j].getOpen_date();
		lotoTheoGiaiDTO.setMiss("" + miss);
		lotoTheoGiaiDTO.setN1(ngay1 + "");
		lotoTheoGiaiDTO.setN2(ngay2 + "");
		lotoTheoGiaiDTO.setN3(ngay3 + "");
		lotoTheoGiaiDTO.setN4(ngay4 + "");
		lotoTheoGiaiDTO.setN5(ngay5 + "");
		lotoTheoGiaiDTO.setLoto(loto);
		lotoTheoGiaiDTO.setTimes(dates.size() + "");
		lotoTheoGiaiDTO.setValue(cacGiaiDate[i][j].getValue());
		lotoTheoGiaiDTO.setNameAward(cacGiaiDate[i][j].getPosition());
		lotoTheoGiaiDTO.setOpenDate(dateAward);
		List<LotoTheoGiaiDetailDTO> detailDTOs = get10LotoDetail(cacgiai, loto, dates);
		lotoTheoGiaiDTO.setList10(detailDTOs);
		String rs = getCurrentResult(cacgiai, loto, dateAward);
		lotoTheoGiaiDTO.setRs(rs);
		return lotoTheoGiaiDTO;
	}

	private int getIndex(AwardDetailDTO[][] cacgiai, java.sql.Date dateAward) {
		for (int i = 0; i < cacgiai.length; i++) {
			if (cacgiai[i][0].getOpen_date().getTime() == dateAward.getTime()) {
				return i;
			}
		}

		return -1;
	}

	private String getCurrentResult(AwardDetailDTO[][] cacgiai, String loto, java.sql.Date dateAward) {
		String s = "";
		int index = getIndex(cacgiai, dateAward);

		List<String> s1 = getDetail(cacgiai, loto, index - 1);

		if (s1 != null && s1.size() > 0) {
			s = "Về " + getStringFromList(s1) + " ngày 1";
			return s;
		}

		List<String> s2 = getDetail(cacgiai, loto, index - 2);

		if (s2 != null && s2.size() > 0) {
			s = "Về " + getStringFromList(s2) + " ngày 2";
			return s;
		}


		List<String> s3 = getDetail(cacgiai, loto, index - 3);

		if (s3 != null && s3.size() > 0) {
			s = "Về " + getStringFromList(s3) + " ngày 3";
			return s;
		}
		
		List<String> s4 = getDetail(cacgiai, loto, index - 4);

		if (s4 != null && s4.size() > 0) {
			s = "Về " + getStringFromList(s4) + " ngày 4";
			return s;
		}
		
		List<String> s5 = getDetail(cacgiai, loto, index - 5);

		if (s5 != null && s5.size() > 0) {
			s = "Về " + getStringFromList(s5) + " ngày 5";
			return s;
		}

		Date currentDate = new Date();
		java.sql.Date current = CommonUtil.convertDateToDate(currentDate);

		java.sql.Date date3 = CommonUtil.addDate3(dateAward, 5);
		if (!date3.before(current)) {
			s = "?";
			return s;
		} else {
			s = "Miss";
		}

		return s;
	}

	private String getStringFromList(List<String> finalList) {

		StringBuilder rs = new StringBuilder("");

		for (int k = 0; k < finalList.size(); k++) {
			if (k < finalList.size() - 1) {
				rs.append(finalList.get(k)).append(",");
			} else {
				rs.append(finalList.get(k));
			}
		}

		return rs.toString();
	}

	private List<LotoTheoGiaiDetailDTO> get10LotoDetail(AwardDetailDTO[][] cacgiai, String loto,
			List<java.sql.Date> dates) {

		List<LotoTheoGiaiDetailDTO> lotoTheoGiaiDetailDTOs = new ArrayList<>();
		int index = 0;

		while (index < 10 && index < dates.size()) {

			LotoTheoGiaiDetailDTO lotoTheoGiaiDetailDTO = new LotoTheoGiaiDetailDTO();
			String day = "";
			String rs;

			java.sql.Date temp = dates.get(index);
			int i = getIndex(cacgiai, temp);

			List<String> s1 = getDetail(cacgiai, loto, i - 1);
			List<String> s2 = getDetail(cacgiai, loto, i - 2);
			List<String> s3 = getDetail(cacgiai, loto, i - 3);
			List<String> s4 = getDetail(cacgiai, loto, i - 4);
			List<String> s5 = getDetail(cacgiai, loto, i - 5);

			if (s1 != null && s1.size() > 0) {
				day = "1";
				rs = getStringFromList(s1);
			} else if (s2 != null && s2.size() > 0) {
				day = "2";
				rs = getStringFromList(s2);
			} else if (s3 != null && s3.size() > 0) {
				day = "3";
				rs = getStringFromList(s3);
			} else if (s4 != null && s4.size() > 0) {
				day = "4";
				rs = getStringFromList(s4);
			} else {
				
				rs = getStringFromList(s5);
				if(!CommonUtil.isEmptyString(rs)) {
					day = "5";
				} 
			}

//			if (s2 != null && s2.size() > 0) {
//				finalList.addAll(s2);
//			}
//
//			if (s3 != null && s3.size() > 0) {
//				finalList.addAll(s3);
//			}
//			
//			if (s4 != null && s4.size() > 0) {
//				finalList.addAll(s4);
//			}
//			
//			if (s5 != null && s5.size() > 0) {
//				finalList.addAll(s5);
//			}

			lotoTheoGiaiDetailDTO.setColorDay(day);
			lotoTheoGiaiDetailDTO.setRs(rs);
			lotoTheoGiaiDetailDTOs.add(lotoTheoGiaiDetailDTO);
			index++;
		}

		return lotoTheoGiaiDetailDTOs;

	}

	private List<String> getDetail(AwardDetailDTO[][] cacgiai, String loto, int i) {

		List<String> s = new ArrayList<String>();

		if (i >= 0) {
			for (int k = 0; k < 27; k++) {
				if (cacgiai[i][k].getValue().equals(loto)) {
					s.add(loto);
				}
			}
		}

		return s;
	}

	private boolean checkCondition(AwardDetailDTO[][] cacgiai, int i, String loto) {

		if (i >= 0) {
			for (int k = 0; k < 27; k++) {
				if (cacgiai[i][k].getValue().equals(loto)) {
					return true;
				}
			}
		}

		return false;
	}

	private List<java.sql.Date> getListDate(AwardDetailDTO[][] cacgiai, int j, String value, java.sql.Date dateAward) {
		List<java.sql.Date> dates = new ArrayList<>();

		for (int i = 0; i < cacgiai.length; i++) {
			if (value.equalsIgnoreCase(cacgiai[i][j].getValue()) && cacgiai[i][j].getOpen_date().before(dateAward)) {
				dates.add(cacgiai[i][j].getOpen_date());
			}
		}

		return dates;
	}
}
