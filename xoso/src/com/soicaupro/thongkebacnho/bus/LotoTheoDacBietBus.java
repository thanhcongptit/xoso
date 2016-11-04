package com.soicaupro.thongkebacnho.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.soicaupro.thongkebacnho.Constant;
import com.soicaupro.thongkebacnho.dao.LotoTheoGiaiDao;
import com.soicaupro.thongkebacnho.dao.SpecialAwardDao;
import com.soicaupro.thongkebacnho.domain.AwardDTO;
import com.soicaupro.thongkebacnho.domain.AwardDetailDTO;
import com.soicaupro.thongkebacnho.domain.LotoDacBiet1NgayDTO;
import com.soicaupro.thongkebacnho.domain.LotoDacBiet2NgayDTO;
import com.soicaupro.thongkebacnho.domain.LotoDacBiet3NgayDTO;
import com.soicaupro.thongkebacnho.domain.LotoDacBietBachThuDTO;
import com.soicaupro.thongkebacnho.domain.ResultOneDayDTO;
import com.soicaupro.thongkebacnho.domain.ScreenBachthuSpecialDTO;
import com.soicaupro.thongkebacnho.domain.ScreenOneDayDTO;
import com.soicaupro.thongkebacnho.domain.ScreenThreeDaySpecialDTO;
import com.soicaupro.thongkebacnho.domain.ScreenTwoDaySpecialDTO;
import com.soicaupro.thongkebacnho.domain.SpecialAwardDTO;

public class LotoTheoDacBietBus {
	private SpecialAwardDao specialAwardDao = new SpecialAwardDao();
	
	public LotoDacBiet1NgayDTO getLotoDacBiet1Ngay(String dataDate, String toDate, 
			String numberDate, String special, String day) {
		LotoDacBiet1NgayDTO dacBiet1NgayDTO = new LotoDacBiet1NgayDTO();
		int total = Integer.parseInt(numberDate);
		List<SpecialAwardDTO> awards = specialAwardDao.getListSpecialAward(total, special, toDate);
		
		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward(dataDate, toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		System.out.println("size: " + awardDTOs.size());
		System.out.println("-----");
		
		
		List<ScreenOneDayDTO> screenOneDayDTOs = new ArrayList<ScreenOneDayDTO>();
		
		for(int i=0; i< 50; i++) {
			ScreenOneDayDTO screenOneDayDTO = new ScreenOneDayDTO();
			String loto[] = Constant.coupleLoto[i];
			List<ResultOneDayDTO> resultOneDayDTOs = getListResultOneDay(cacgiai, awards, day, loto);
			screenOneDayDTO.setCouple(loto);
			screenOneDayDTO.setSize(resultOneDayDTOs.size());
			screenOneDayDTO.setOneDayDTOs(resultOneDayDTOs);
			screenOneDayDTOs.add(screenOneDayDTO);
		}
		
		Collections.sort(screenOneDayDTOs);
		List<ScreenOneDayDTO> results = screenOneDayDTOs.subList(0, 10);
		
		dacBiet1NgayDTO.setAwards(awards);
		dacBiet1NgayDTO.setResults(results);;
		return dacBiet1NgayDTO;
	}

	private List<ResultOneDayDTO> getListResultOneDay(AwardDetailDTO[][] cacgiai, List<SpecialAwardDTO> awards,
			String dayDTO, String loto[]) {
		List<ResultOneDayDTO> dayDTOs = new ArrayList<ResultOneDayDTO>();
		
		for(SpecialAwardDTO specialAwardDTO: awards) {
			int index = CommonUtil.getIndex(cacgiai, specialAwardDTO.getOpenDate());
			int day = Integer.parseInt(dayDTO);
			int temp = index - day;
			
			if(temp>=0) {
				List<String> s = new ArrayList<String>();
				
				for(int j = 0; j< 27; j++) {
					if(cacgiai[temp][j].getValue().equals(loto[0])) {
						s.add(loto[0]);
					}
					
					if(cacgiai[temp][j].getValue().equals(loto[1])) {
						s.add(loto[1]);
					}
				}
				
				if(s!= null && s.size() > 0) {
					ResultOneDayDTO resultOneDayDTO = new ResultOneDayDTO();
					resultOneDayDTO.setOpen_date(cacgiai[temp][0].getOpen_date());
					resultOneDayDTO.setResult(CommonUtil.getStringFromList(s));
					dayDTOs.add(resultOneDayDTO);
				}
			}
		}
		
		return dayDTOs;
	}
	
	
	// ------------2 ngay--
	
	public LotoDacBiet2NgayDTO getLotoDacBiet2Ngay(String dataDate, String toDate, 
			String numberDate, String special) {
		LotoDacBiet2NgayDTO dacBiet2NgayDTO = new LotoDacBiet2NgayDTO();
		int total = Integer.parseInt(numberDate);
		List<SpecialAwardDTO> awards = specialAwardDao.getListSpecialAward(total, special, toDate);
		
		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward(dataDate, toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		System.out.println("size: " + awardDTOs.size());
		System.out.println("-----");
		
		
		List<ScreenTwoDaySpecialDTO> screenOneDayDTOs = new ArrayList<ScreenTwoDaySpecialDTO>();
		
		for(int i=0; i< 50; i++) {
			ScreenTwoDaySpecialDTO screenOneDayDTO = new ScreenTwoDaySpecialDTO();
			String loto[] = Constant.coupleLoto[i];
			List<ResultOneDayDTO> resultOneDayDTOs = getListResultOneDay(cacgiai, awards, "1", loto);
			List<ResultOneDayDTO> resultOneDayDTOs2 = getListResultOneDay(cacgiai, awards, "2", loto);
			screenOneDayDTO.setCouple(loto);
			screenOneDayDTO.setSize(resultOneDayDTOs.size() + resultOneDayDTOs2.size());
			screenOneDayDTO.setListDay1(resultOneDayDTOs);
			screenOneDayDTO.setListDay2(resultOneDayDTOs2);
			screenOneDayDTOs.add(screenOneDayDTO);
		}
		
		Collections.sort(screenOneDayDTOs);
		List<ScreenTwoDaySpecialDTO> results = screenOneDayDTOs.subList(0, 10);
		
		dacBiet2NgayDTO.setAwards(awards);
		dacBiet2NgayDTO.setResults(results);;
		return dacBiet2NgayDTO;
	}
	
	//-----------3 ngay
	
	public LotoDacBiet3NgayDTO getLotoDacBiet3Ngay(String dataDate, String toDate, 
			String numberDate, String special) {
		LotoDacBiet3NgayDTO dacBiet2NgayDTO = new LotoDacBiet3NgayDTO();
		int total = Integer.parseInt(numberDate);
		List<SpecialAwardDTO> awards = specialAwardDao.getListSpecialAward(total, special, toDate);
		
		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward(dataDate, toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		System.out.println("size: " + awardDTOs.size());
		System.out.println("-----");
		
		
		List<ScreenThreeDaySpecialDTO> screenOneDayDTOs = new ArrayList<ScreenThreeDaySpecialDTO>();
		
		for(int i=0; i< 50; i++) {
			ScreenThreeDaySpecialDTO screenOneDayDTO = new ScreenThreeDaySpecialDTO();
			String loto[] = Constant.coupleLoto[i];
			List<ResultOneDayDTO> resultOneDayDTOs = getListResultOneDay(cacgiai, awards, "1", loto);
			List<ResultOneDayDTO> resultOneDayDTOs2 = getListResultOneDay(cacgiai, awards, "2", loto);
			List<ResultOneDayDTO> resultOneDayDTOs3 = getListResultOneDay(cacgiai, awards, "3", loto);
			screenOneDayDTO.setCouple(loto);
			screenOneDayDTO.setSize(resultOneDayDTOs.size() + resultOneDayDTOs2.size() + resultOneDayDTOs3.size());
			screenOneDayDTO.setListDay1(resultOneDayDTOs);
			screenOneDayDTO.setListDay2(resultOneDayDTOs2);
			screenOneDayDTO.setListDay3(resultOneDayDTOs3);
			screenOneDayDTOs.add(screenOneDayDTO);
		}
		
		Collections.sort(screenOneDayDTOs);
		List<ScreenThreeDaySpecialDTO> results = screenOneDayDTOs.subList(0, 10);
		
		dacBiet2NgayDTO.setAwards(awards);
		dacBiet2NgayDTO.setResults(results);;
		return dacBiet2NgayDTO;
	}
	
	public LotoDacBietBachThuDTO getLotoDacBietBachThu3Ngay(String dataDate, String toDate, 
			String numberDate, String special) {
		LotoDacBietBachThuDTO dacBiet2NgayDTO = new LotoDacBietBachThuDTO();
		int total = Integer.parseInt(numberDate);
		List<SpecialAwardDTO> awards = specialAwardDao.getListSpecialAward(total, special, toDate);
		
		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward(dataDate, toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		System.out.println("size: " + awardDTOs.size());
		System.out.println("-----");
		
		
		List<ScreenBachthuSpecialDTO> screenOneDayDTOs = new ArrayList<ScreenBachthuSpecialDTO>();
		
		for(int i=0; i< 100; i++) {
			String loto = "";
			if(i < 10) {
				loto = "0" + i; 
			} else {
				loto = "" + i;
			}
			
			ScreenBachthuSpecialDTO screenOneDayDTO = new ScreenBachthuSpecialDTO();
			List<ResultOneDayDTO> resultOneDayDTOs = getListResultOneDay1(cacgiai, awards, "1", loto);
			List<ResultOneDayDTO> resultOneDayDTOs2 = getListResultOneDay1(cacgiai, awards, "2", loto);
			List<ResultOneDayDTO> resultOneDayDTOs3 = getListResultOneDay1(cacgiai, awards, "3", loto);
			screenOneDayDTO.setLoto(loto);
			screenOneDayDTO.setSize(resultOneDayDTOs.size() + resultOneDayDTOs2.size() + resultOneDayDTOs3.size());
			screenOneDayDTO.setListDay1(resultOneDayDTOs);
			screenOneDayDTO.setListDay2(resultOneDayDTOs2);
			screenOneDayDTO.setListDay3(resultOneDayDTOs3);
			screenOneDayDTOs.add(screenOneDayDTO);
		}
		
		Collections.sort(screenOneDayDTOs);
		List<ScreenBachthuSpecialDTO> results = screenOneDayDTOs.subList(0, 10);
		
		dacBiet2NgayDTO.setAwards(awards);
		dacBiet2NgayDTO.setResults(results);;
		return dacBiet2NgayDTO;
	}
	
	private List<ResultOneDayDTO> getListResultOneDay1(AwardDetailDTO[][] cacgiai, List<SpecialAwardDTO> awards,
			String dayDTO, String loto) {
		List<ResultOneDayDTO> dayDTOs = new ArrayList<ResultOneDayDTO>();
		
		for(SpecialAwardDTO specialAwardDTO: awards) {
			int index = CommonUtil.getIndex(cacgiai, specialAwardDTO.getOpenDate());
			int day = Integer.parseInt(dayDTO);
			int temp = index - day;
			
			if(temp>=0) {
				List<String> s = new ArrayList<String>();
				
				for(int j = 0; j< 27; j++) {
					if(cacgiai[temp][j].getValue().equals(loto)) {
						s.add(loto);
					}
					
				}
				
				if(s!= null && s.size() > 0) {
					ResultOneDayDTO resultOneDayDTO = new ResultOneDayDTO();
					resultOneDayDTO.setOpen_date(cacgiai[temp][0].getOpen_date());
					resultOneDayDTO.setResult(CommonUtil.getStringFromList(s));
					dayDTOs.add(resultOneDayDTO);
				}
			}
		}
		
		return dayDTOs;
	}
}
