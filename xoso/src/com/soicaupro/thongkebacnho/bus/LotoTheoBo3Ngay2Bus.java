package com.soicaupro.thongkebacnho.bus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.Constant;
import com.soicaupro.thongkebacnho.dao.LotoTheoGiaiDao;
import com.soicaupro.thongkebacnho.domain.AwardDTO;
import com.soicaupro.thongkebacnho.domain.AwardDetailDTO;
import com.soicaupro.thongkebacnho.domain.LotoTheoGiaiDTO;
import com.soicaupro.thongkebacnho.domain.LotoTheoGiaiDetailDTO;

public class LotoTheoBo3Ngay2Bus {
	
	private List<LotoTheoGiaiDTO> lotoTheoBos = new ArrayList<LotoTheoGiaiDTO>();

	public List<LotoTheoGiaiDTO> getListLotoBo3_2Ngay(String fromDate, String toDate, String dataDate, int miss) {

		Date date = CommonUtil.convertStringToDate(toDate);
		Date dateAward = new Date(date.getTime());
		date = CommonUtil.addDate2(date, 3);
		toDate = CommonUtil.convertDateToString1(date);

		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward(dataDate, toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		System.out.println("size: " + awardDTOs.size());
		System.out.println("-----");

		Date from_Date = CommonUtil.convertStringToDate(fromDate);
		AwardDetailDTO[][] cacGiaiDate = new AwardDetailDTO[4][27];
		int index = 0;

		while (!dateAward.before(from_Date) && index < 4) {
			AwardDetailDTO[] temp = getAwardDetailDTOInDate(dateAward, cacgiai);

			if (temp != null) {
				cacGiaiDate[index] = temp;
				index++;
			}

			dateAward = CommonUtil.addDate2(dateAward, -1);
		}

		for (int i = index -1; i >= 0; i--) {
			int length = getLengthCacGiai(cacGiaiDate[i]);
			tr(1, length, 3, cacgiai, cacGiaiDate[i]);
		}

		System.out.println("size: " + lotoTheoBos.size());
		return lotoTheoBos;
	}

	private int getLengthCacGiai(AwardDetailDTO[] awardDetailDTOs) {
		int total = 0;

		for (int i = 0; i < 27; i++) {
			if (awardDetailDTOs[i] != null) {
				total++;
			}
		}

		return total;
	}

	private AwardDetailDTO[] getAwardDetailDTOInDate(Date openDate, AwardDetailDTO[][] cacGiai) {
		for (int i = 0; i < cacGiai.length; i++) {
			java.sql.Date temp = cacGiai[i][0].getOpen_date();

			if (CommonUtil.convertDateToString3(openDate).equalsIgnoreCase(CommonUtil.convertDateToString(temp))) {
				return getGiaiRiengBiet(cacGiai, i);
			}
		}

		return null;
	}

	private AwardDetailDTO[] getGiaiRiengBiet(AwardDetailDTO[][] cacGiai, int i) {
		AwardDetailDTO[] rs = new AwardDetailDTO[27];
		int index = 0;

		for (int j = 0; j < 27; j++) {
			if (isNotInList(cacGiai[i][j], rs, index)) {
				rs[index] = cacGiai[i][j];
				index++;
			}
		}

		return rs;
	}

	private boolean isNotInList(AwardDetailDTO awardDetailDTO, AwardDetailDTO[] rs, int index) {

		for (int i = 0; i < index; i++) {
			if (awardDetailDTO.getValue().equalsIgnoreCase(rs[i].getValue())) {
				return false;
			}
		}
		return true;
	}

	
	public int[] a = new int[5];

	/**
	 * --------------------------------------------------------
	 */
	public void result(int n, int k, AwardDetailDTO[][] cacgiai, AwardDetailDTO[] cacgiaiDate) {
		//System.out.println("\nTo hop thu " + ++dem + " : ");
//		for(int t = 1; t <= k; t++) {
//			if(t<k)
//				System.out.print(a[t] + ",");
//			else
//				System.out.print(a[t]);
//			
//		}
		
		if(k == 3) {
			AwardDetailDTO giai1 = cacgiaiDate[a[1]-1];
			AwardDetailDTO giai2 = cacgiaiDate[a[2]-1];
			AwardDetailDTO giai3 = cacgiaiDate[a[3]-1];
			
			System.out.println(giai1.getValue()+ "-"+ giai2.getValue() + "-" + giai3.getValue());
			
			List<java.sql.Date> dates = getListDate(cacgiai, giai1.getValue(), giai2.getValue(), giai3.getValue(), giai1.getOpen_date());
			
			if(dates != null && dates.size() >= 20) {
				
				for (int t = 0; t < 50; t++) {
					LotoTheoGiaiDTO lotoTheoGiaiDTO = getLotoGiaiDTO(cacgiai,
							giai1.getValue(), giai2.getValue(), giai3.getValue(), dates, giai1.getOpen_date(), Constant.coupleLoto[t]);

					if (lotoTheoGiaiDTO != null) {
						lotoTheoBos.add(lotoTheoGiaiDTO);
					}
				}
			}
		}
	}
	
	private LotoTheoGiaiDTO getLotoGiaiDTO(AwardDetailDTO[][] cacgiai,
			String giai1, String giai2, String giai3, List<java.sql.Date> dates, java.sql.Date openDate, String loto[]) {
		LotoTheoGiaiDTO lotoTheoGiaiDTO = new LotoTheoGiaiDTO();
		int miss = 0;
		int ngay1 = 0;
		int ngay2 = 0;

		for (int k = 0; k < dates.size(); k++) {
			if (miss > 0) {
				return null;
			}

			java.sql.Date temp = dates.get(k);
			int index = getIndex(cacgiai, temp);

			if (!checkCondition(cacgiai, index - 1, loto) && !checkCondition(cacgiai, index - 2, loto)) {
					
				miss = miss + 1;
			} else if (checkCondition(cacgiai, index - 1, loto)) {
				ngay1++;
			} else {
				ngay2++;
			} 
		}

		if (miss > 0) {
			return null;
		}

		lotoTheoGiaiDTO.setMiss("" + miss);
		lotoTheoGiaiDTO.setN1(ngay1 + "");
		lotoTheoGiaiDTO.setN2(ngay2 + "");
		lotoTheoGiaiDTO.setN3("0");
		lotoTheoGiaiDTO.setLoto(loto);
		lotoTheoGiaiDTO.setTimes(dates.size() + "");
		lotoTheoGiaiDTO.setValue(giai1 + "," + giai2 + "," + giai3);
		lotoTheoGiaiDTO.setOpenDate(openDate);
		List<LotoTheoGiaiDetailDTO> detailDTOs = get10LotoDetail(cacgiai, loto, dates);
		lotoTheoGiaiDTO.setList10(detailDTOs);
		String rs = getCurrentResult(cacgiai, loto, openDate);
		lotoTheoGiaiDTO.setRs(rs);
		return lotoTheoGiaiDTO;
	}
	
	private String getCurrentResult(AwardDetailDTO[][] cacgiai, String[] loto, java.sql.Date dateAward) {
			
		String s = "";
		int index = getIndex(cacgiai, dateAward);

		List<String> s1 = getDetail(cacgiai, loto, index-1);

		if (s1 != null && s1.size() > 0) {
			s = "Về " + CommonUtil.getStringFromList(s1) + " ngày 1";
			return s;
		}

		List<String> s2 = getDetail(cacgiai, loto, index-2);

		if (s2 != null && s2.size() > 0) {
			s = "Về " + CommonUtil.getStringFromList(s2) + " ngày 2";
			return s;
		}

		

		Date currentDate = new Date();
		java.sql.Date current = CommonUtil.convertDateToDate(currentDate);

		java.sql.Date date3 = CommonUtil.addDate3(dateAward, 2);
		if (!date3.before(current)) {
			s = "?";
			return s;
		} else {
			s = "Miss";
		}

		return s;
	}
	
	private List<LotoTheoGiaiDetailDTO> get10LotoDetail(AwardDetailDTO[][] cacgiai, String[] loto,
			List<java.sql.Date> dates) {

		List<LotoTheoGiaiDetailDTO> lotoTheoGiaiDetailDTOs = new ArrayList<>();
		int index = 0;

		while (index < 10 && index < dates.size()) {

			LotoTheoGiaiDetailDTO lotoTheoGiaiDetailDTO = new LotoTheoGiaiDetailDTO();
			String day = "";
			String rs = "";
			
			java.sql.Date temp = dates.get(index);
			int i = getIndex(cacgiai, temp);

			List<String> s1 = getDetail(cacgiai, loto, i-1);
			List<String> s2 = getDetail(cacgiai, loto, i-2);


			if (s1 != null && s1.size() > 0) {
				day = "1";
				rs = CommonUtil.getStringFromList(s1);
			} else {
				day = "2";
				rs = CommonUtil.getStringFromList(s2);
			} 


			lotoTheoGiaiDetailDTO.setColorDay(day);
			lotoTheoGiaiDetailDTO.setRs(rs);
			lotoTheoGiaiDetailDTOs.add(lotoTheoGiaiDetailDTO);
			index++;
		}

		return lotoTheoGiaiDetailDTOs;

	}
	
	private List<String> getDetail(AwardDetailDTO[][] cacgiai, String[] loto, int i) {

		List<String> s = new ArrayList<String>();

		if (i >= 0) {
			for (int k = 0; k < 27; k++) {
				if (cacgiai[i][k].getValue().equals(loto[0])) {
					s.add(loto[0]);
				} else if (cacgiai[i][k].getValue().equals(loto[1])) {
					s.add(loto[1]);
				}
			}
		}

		return s;
	}
	
	private boolean checkCondition(AwardDetailDTO[][] cacgiai, int i, String[] loto) {

		if (i >= 0) {
			for (int k = 0; k < 27; k++) {
				if (cacgiai[i][k].getValue().equals(loto[0]) || cacgiai[i][k].getValue().equals(loto[1])) {
					return true;
				}
			}
		}

		return false;
	}
	
	private int getIndex(AwardDetailDTO[][] cacgiai, java.sql.Date dateAward) {
		for (int i = 0; i < cacgiai.length; i++) {
			if (cacgiai[i][0].getOpen_date().getTime() == dateAward.getTime()) {
				return i;
			}
		}

		return -1;
	}
	
	private List<java.sql.Date> getListDate(AwardDetailDTO[][] cacgiai, String loto1, String loto2, String loto3, java.sql.Date dateAward) {
		List<java.sql.Date> dates = new ArrayList<>();

		for (int i = 0; i < cacgiai.length; i++) {
			if (cacgiai[i][0].getOpen_date().before(dateAward)) {
				if(checkLotoInAward(cacgiai[i], loto1) && checkLotoInAward(cacgiai[i], loto2) && checkLotoInAward(cacgiai[i], loto3)) {
					dates.add(cacgiai[i][0].getOpen_date());
				}
			}
		}

		return dates;
	}

	private boolean checkLotoInAward(AwardDetailDTO[] awards, String loto) {
		for(int i=0;i < 27; i++) {
			if(loto.equals(awards[i].getValue())) {
				return true;
			}
		}
		
		return false;
	}
	/**
	 * --------------------------------------------------------
	 * n: length
	 * k: tohop
	 */
	public List<LotoTheoGiaiDTO> tr(int i, int n, int k, AwardDetailDTO[][] cacgiai, AwardDetailDTO[] cacgiaiDate) {
		
		List<LotoTheoGiaiDTO> lotoTheoGiaiDTOs = new ArrayList<LotoTheoGiaiDTO>();
		
		for(int j = a[i - 1] + 1; j <= n - k + i; j++) {
			a[i] = j;
			if(i == k) {
				result(n, k, cacgiai, cacgiaiDate);
				//System.out.println();
			} else {
				tr(i + 1, n, k, cacgiai, cacgiaiDate);
			}
		}
		
		return lotoTheoGiaiDTOs;
	}
}
