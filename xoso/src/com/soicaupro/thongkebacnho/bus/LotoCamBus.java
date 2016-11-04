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
import com.soicaupro.thongkebacnho.domain.LotoDauCamBachthuDTO;
import com.soicaupro.thongkebacnho.domain.LotoDauCamDTO;
import com.soicaupro.thongkebacnho.domain.OpenDateDTO;
import com.soicaupro.thongkebacnho.domain.ResultOneDayDTO;
import com.soicaupro.thongkebacnho.domain.ScreenBachthuSpecialDTO;
import com.soicaupro.thongkebacnho.domain.ScreenThreeDaySpecialDTO;

public class LotoCamBus {
	/**
	 * Dau cam
	 * 
	 */
	
	public LotoDauCamDTO getLotoDauCam(String toDate, int times, String daucam) {
		LotoDauCamDTO lotoDauCamDTO = new LotoDauCamDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateDauCam(cacgiai, times, daucam);
		List<ScreenThreeDaySpecialDTO> result = getListResultThreeDay(openDateDTOs, cacgiai);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getCouple()[0] + "," + result.get(i).getCouple()[1] + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResult(result);
		openDateDTOs = getListAward(openDateDTOs, result);
		lotoDauCamDTO.setOpenDates(openDateDTOs);
		return lotoDauCamDTO;
	}

	private List<OpenDateDTO> getOpenDateDauCam(AwardDetailDTO[][] cacgiai, int times, String daucam) {
		
		List<OpenDateDTO> openDateDTOs = new ArrayList<OpenDateDTO>();
		
		int total = 0;
		int i = 0;
		
		while(i < cacgiai.length && total < times) {
			if (checkCondition(cacgiai, daucam, i)) {
				total++;
				OpenDateDTO openDateDTO = new OpenDateDTO();
				openDateDTO.setIndex(i);
				openDateDTO.setOpenDate(cacgiai[i][0].getOpen_date());
				openDateDTO.setValue(daucam);
				openDateDTOs.add(openDateDTO);
			}

			i++;
		}
		
		return openDateDTOs;
	}

	private boolean checkCondition(AwardDetailDTO[][] cacgiai, String daucam, int i) {
		
		for(int k = 0; k< 27; k++) {
			if(String.valueOf(cacgiai[i][k].getValue().charAt(0)).equals(daucam)) {
				return false;
			}
		}
		return true;
	}
	
	private List<ScreenThreeDaySpecialDTO> getListResultThreeDay(List<OpenDateDTO> openDateDTOs,
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
	
	//-----------------------------------------------------------------------------------
	
	public LotoDauCamBachthuDTO getLotoDauCamBachThu(String toDate, int times, String daucam) {
		LotoDauCamBachthuDTO lotoDauCamDTO = new LotoDauCamBachthuDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateDauCam(cacgiai, times, daucam);
		List<ScreenBachthuSpecialDTO> result = getListResultThreeDayBachthu(openDateDTOs, cacgiai);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getLoto() + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResults(result);
		openDateDTOs = getListAward1(openDateDTOs, result);
		lotoDauCamDTO.setOpenDateDTOs(openDateDTOs);
		return lotoDauCamDTO;
	}

	private List<ScreenBachthuSpecialDTO> getListResultThreeDayBachthu(List<OpenDateDTO> openDateDTOs,
			AwardDetailDTO[][] cacgiai) {
		List<ScreenBachthuSpecialDTO> daySpecialDTOs = new ArrayList<ScreenBachthuSpecialDTO>();

		for (int i = 0; i < 100; i++) {
			String loto = "";
			
			if(i<10) {
				loto = "0" + i;
			} else {
				loto = "" + i;
			}
			
			ScreenBachthuSpecialDTO screenThreeDaySpecialDTO = new ScreenBachthuSpecialDTO();
			screenThreeDaySpecialDTO.setLoto(loto);
			
			String []condition = new String[] {loto, loto};
			
			List<ResultOneDayDTO> list1 = getList(openDateDTOs, 1, condition, cacgiai);
			List<ResultOneDayDTO> list2 = getList(openDateDTOs, 2, condition, cacgiai);
			List<ResultOneDayDTO> list3 = getList(openDateDTOs, 3, condition, cacgiai);

			screenThreeDaySpecialDTO.setListDay1(list1);
			screenThreeDaySpecialDTO.setListDay2(list2);
			screenThreeDaySpecialDTO.setListDay3(list3);
			screenThreeDaySpecialDTO.setSize(list1.size() + list2.size() + list3.size());
			daySpecialDTOs.add(screenThreeDaySpecialDTO);
		}
		Collections.sort(daySpecialDTOs);
		List<ScreenBachthuSpecialDTO> results = daySpecialDTOs.subList(0, 10);
		return results;
	}
	
	private List<OpenDateDTO> getListAward1(List<OpenDateDTO> awards, List<ScreenBachthuSpecialDTO> results) {

		List<OpenDateDTO> rs = new ArrayList<>();

		for (OpenDateDTO awardDTO : awards) {
			if (!checkExitAwardLoto1(results, awardDTO)) {
				rs.add(awardDTO);
			}
		}

		return rs;
	}

	private boolean checkExitAwardLoto1(List<ScreenBachthuSpecialDTO> results, OpenDateDTO awardDTO) {
		boolean rs = true;

		for (ScreenBachthuSpecialDTO dTO : results) {
			String s1 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay1(), 1);
			String s2 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay2(), 2);
			String s3 = CommonUtil.getValueLotoSpecial(awardDTO.getOpenDate(), dTO.getListDay3(), 3);

			if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2) || !CommonUtil.isEmptyString(s3)) {

				return false;
			}
		}

		return rs;
	}
	
	
	/*
	 * * Tong Cam
	 * 
	 * */
	
	public LotoDauCamDTO getLotoTongCam(String toDate, int times, String tongcam) {
		LotoDauCamDTO lotoDauCamDTO = new LotoDauCamDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateTongCam(cacgiai, times, tongcam);
		
		for(OpenDateDTO dateDTO : openDateDTOs) {
			System.out.println(CommonUtil.convertDateToString(dateDTO.getOpenDate()));
		}
		
		List<ScreenThreeDaySpecialDTO> result = getListResultThreeDay(openDateDTOs, cacgiai);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getCouple()[0] + "," + result.get(i).getCouple()[1] + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResult(result);
		openDateDTOs = getListAward(openDateDTOs, result);
		lotoDauCamDTO.setOpenDates(openDateDTOs);
		return lotoDauCamDTO;
	}
	
	
	private List<OpenDateDTO> getOpenDateTongCam(AwardDetailDTO[][] cacgiai, int times, String tongcam) {
		
		List<OpenDateDTO> openDateDTOs = new ArrayList<OpenDateDTO>();
		
		int total = 0;
		int i = 0;
		
		while(i < cacgiai.length && total < times) {
			if (checkConditionTongCam(cacgiai, tongcam, i)) {
				total++;
				OpenDateDTO openDateDTO = new OpenDateDTO();
				openDateDTO.setIndex(i);
				openDateDTO.setOpenDate(cacgiai[i][0].getOpen_date());
				openDateDTO.setValue(tongcam);
				openDateDTOs.add(openDateDTO);
			}

			i++;
		}
		
		return openDateDTOs;
	}

	private boolean checkConditionTongCam(AwardDetailDTO[][] cacgiai, String tongcam, int i) {
		
		for(int k = 0; k< 27; k++) {
			String t1 = String.valueOf(cacgiai[i][k].getValue().charAt(0));
			String t2 = String.valueOf(cacgiai[i][k].getValue().charAt(1));
			
			int t = Integer.parseInt(t1) + Integer.parseInt(t2);
			
			if(t == Integer.parseInt(tongcam) || Integer.parseInt(tongcam) == (t-10)) {
				return false;
			}
		}
		return true;
	}
	
	
	//--------------------------
	
	public LotoDauCamBachthuDTO getLotoTongCamBachThu(String toDate, int times, String tongcam) {
		LotoDauCamBachthuDTO lotoDauCamDTO = new LotoDauCamBachthuDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateTongCam(cacgiai, times, tongcam);
		List<ScreenBachthuSpecialDTO> result = getListResultThreeDayBachthu(openDateDTOs, cacgiai);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getLoto() + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResults(result);
		openDateDTOs = getListAward1(openDateDTOs, result);
		lotoDauCamDTO.setOpenDateDTOs(openDateDTOs);
		return lotoDauCamDTO;
	}

	public static void main(String a[]) {
		new LotoCamBus().getLotoDuoiCam("20-03-2016", 45, "2");
	}
	
	
	/*
	 * * Duoi Cam
	 * 
	 * */
	
	public LotoDauCamDTO getLotoDuoiCam(String toDate, int times, String duoicam) {
		LotoDauCamDTO lotoDauCamDTO = new LotoDauCamDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateDuoiCam(cacgiai, times, duoicam);
		List<ScreenThreeDaySpecialDTO> result = getListResultThreeDay(openDateDTOs, cacgiai);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getCouple()[0] + "," + result.get(i).getCouple()[1] + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResult(result);
		openDateDTOs = getListAward(openDateDTOs, result);
		lotoDauCamDTO.setOpenDates(openDateDTOs);
		return lotoDauCamDTO;
	}
	
	
	private List<OpenDateDTO> getOpenDateDuoiCam(AwardDetailDTO[][] cacgiai, int times, String duoicam) {
		
		List<OpenDateDTO> openDateDTOs = new ArrayList<OpenDateDTO>();
		
		int total = 0;
		int i = 0;
		
		while(i < cacgiai.length && total < times) {
			if (checkConditionDuoiCam(cacgiai, duoicam, i)) {
				total++;
				OpenDateDTO openDateDTO = new OpenDateDTO();
				openDateDTO.setIndex(i);
				openDateDTO.setOpenDate(cacgiai[i][0].getOpen_date());
				openDateDTO.setValue(duoicam);
				openDateDTOs.add(openDateDTO);
			}

			i++;
		}
		
		return openDateDTOs;
	}

	private boolean checkConditionDuoiCam(AwardDetailDTO[][] cacgiai, String duoicam, int i) {
		
		for(int k = 0; k< 27; k++) {
			if(String.valueOf(cacgiai[i][k].getValue().charAt(1)).equals(duoicam)) {
				return false;
			}
		}
		return true;
	}
	
	
	//--------------------------
	
	public LotoDauCamBachthuDTO getLotoDuoiCamBachThu(String toDate, int times, String duoicam) {
		LotoDauCamBachthuDTO lotoDauCamDTO = new LotoDauCamBachthuDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateDuoiCam(cacgiai, times, duoicam);
		List<ScreenBachthuSpecialDTO> result = getListResultThreeDayBachthu(openDateDTOs, cacgiai);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getLoto() + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResults(result);
		openDateDTOs = getListAward1(openDateDTOs, result);
		lotoDauCamDTO.setOpenDateDTOs(openDateDTOs);
		return lotoDauCamDTO;
	}
	
	//////---------------------------
	
	public LotoDauCamBachthuDTO getLotoDauCamChinhBachThu(String toDate, int times, String daucam) {
		LotoDauCamBachthuDTO lotoDauCamDTO = new LotoDauCamBachthuDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateDauCam(cacgiai, times, daucam);
		List<ScreenBachthuSpecialDTO> result = getListResultDauCamChinhBachthu(openDateDTOs, cacgiai, daucam);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getLoto() + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResults(result);
		openDateDTOs = getListAward1(openDateDTOs, result);
		lotoDauCamDTO.setOpenDateDTOs(openDateDTOs);
		return lotoDauCamDTO;
	}
	
	private List<ScreenBachthuSpecialDTO> getListResultDauCamChinhBachthu(List<OpenDateDTO> openDateDTOs,
			AwardDetailDTO[][] cacgiai, String daucam) {
		List<ScreenBachthuSpecialDTO> daySpecialDTOs = new ArrayList<ScreenBachthuSpecialDTO>();

		for (int i = 0; i < 10; i++) {
			String loto = "0" + i;
			ScreenBachthuSpecialDTO screenThreeDaySpecialDTO = new ScreenBachthuSpecialDTO();
			screenThreeDaySpecialDTO.setLoto(loto);
			
			String []condition = new String[] {loto, loto};
			
			List<ResultOneDayDTO> list1 = getList(openDateDTOs, 1, condition, cacgiai);
			List<ResultOneDayDTO> list2 = getList(openDateDTOs, 2, condition, cacgiai);
			List<ResultOneDayDTO> list3 = getList(openDateDTOs, 3, condition, cacgiai);

			screenThreeDaySpecialDTO.setListDay1(list1);
			screenThreeDaySpecialDTO.setListDay2(list2);
			screenThreeDaySpecialDTO.setListDay3(list3);
			screenThreeDaySpecialDTO.setSize(list1.size() + list2.size() + list3.size());
			daySpecialDTOs.add(screenThreeDaySpecialDTO);
		}
		Collections.sort(daySpecialDTOs);
		return daySpecialDTOs;
	}
	
	//--------------------Duoi cam chinh bach thu-
	
	public LotoDauCamBachthuDTO getLotoDuoiCamChinhBachThu(String toDate, int times, String duoicam) {
		LotoDauCamBachthuDTO lotoDauCamDTO = new LotoDauCamBachthuDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateDuoiCam(cacgiai, times, duoicam);
		List<ScreenBachthuSpecialDTO> result = getListResultDuoiCamBachthu(openDateDTOs, cacgiai, duoicam);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getLoto() + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResults(result);
		openDateDTOs = getListAward1(openDateDTOs, result);
		lotoDauCamDTO.setOpenDateDTOs(openDateDTOs);
		return lotoDauCamDTO;
	}
	
	private List<ScreenBachthuSpecialDTO> getListResultDuoiCamBachthu(List<OpenDateDTO> openDateDTOs,
			AwardDetailDTO[][] cacgiai, String duoicam) {
		List<ScreenBachthuSpecialDTO> daySpecialDTOs = new ArrayList<ScreenBachthuSpecialDTO>();

		for (int i = 0; i < 10; i++) {
			String loto = i + "0";
			ScreenBachthuSpecialDTO screenThreeDaySpecialDTO = new ScreenBachthuSpecialDTO();
			screenThreeDaySpecialDTO.setLoto(loto);
			
			String []condition = new String[] {loto, loto};
			
			List<ResultOneDayDTO> list1 = getList(openDateDTOs, 1, condition, cacgiai);
			List<ResultOneDayDTO> list2 = getList(openDateDTOs, 2, condition, cacgiai);
			List<ResultOneDayDTO> list3 = getList(openDateDTOs, 3, condition, cacgiai);

			screenThreeDaySpecialDTO.setListDay1(list1);
			screenThreeDaySpecialDTO.setListDay2(list2);
			screenThreeDaySpecialDTO.setListDay3(list3);
			screenThreeDaySpecialDTO.setSize(list1.size() + list2.size() + list3.size());
			daySpecialDTOs.add(screenThreeDaySpecialDTO);
		}
		Collections.sort(daySpecialDTOs);
		return daySpecialDTOs;
	}
	
	//-----------------tong cam chinh bach thu
	public LotoDauCamBachthuDTO getLotoTongCamChinhBachThu(String toDate, int times, String tongcam) {
		LotoDauCamBachthuDTO lotoDauCamDTO = new LotoDauCamBachthuDTO();

		Date date = CommonUtil.convertStringToDate(toDate);
		date = CommonUtil.addDate2(date, 4);
		toDate = CommonUtil.convertDateToString1(date);
		List<AwardDTO> awardDTOs = new LotoTheoGiaiDao().getListAward("01-01-2005", toDate);
		AwardDetailDTO[][] cacgiai = CommonUtil.getCacgiai(awardDTOs);
		
		List<OpenDateDTO> openDateDTOs = getOpenDateTongCam(cacgiai, times, tongcam);
		List<ScreenBachthuSpecialDTO> result = getListResultTongCamBachthu(openDateDTOs, cacgiai, tongcam);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("loto: " + result.get(i).getLoto() + "--size: "
					+ result.get(i).getSize());
		}
		
		lotoDauCamDTO.setResults(result);
		openDateDTOs = getListAward1(openDateDTOs, result);
		lotoDauCamDTO.setOpenDateDTOs(openDateDTOs);
		return lotoDauCamDTO;
	}
	
	private List<ScreenBachthuSpecialDTO> getListResultTongCamBachthu(List<OpenDateDTO> openDateDTOs,
			AwardDetailDTO[][] cacgiai, String tongcam) {
		List<ScreenBachthuSpecialDTO> daySpecialDTOs = new ArrayList<ScreenBachthuSpecialDTO>();

		String temp[] = CommonUtil.getListByTongCam(tongcam);
		
		for (int i = 0; i < 10; i++) {
			String loto = temp[i];
			ScreenBachthuSpecialDTO screenThreeDaySpecialDTO = new ScreenBachthuSpecialDTO();
			screenThreeDaySpecialDTO.setLoto(loto);
			
			String []condition = new String[] {loto, loto};
			
			List<ResultOneDayDTO> list1 = getList(openDateDTOs, 1, condition, cacgiai);
			List<ResultOneDayDTO> list2 = getList(openDateDTOs, 2, condition, cacgiai);
			List<ResultOneDayDTO> list3 = getList(openDateDTOs, 3, condition, cacgiai);

			screenThreeDaySpecialDTO.setListDay1(list1);
			screenThreeDaySpecialDTO.setListDay2(list2);
			screenThreeDaySpecialDTO.setListDay3(list3);
			screenThreeDaySpecialDTO.setSize(list1.size() + list2.size() + list3.size());
			daySpecialDTOs.add(screenThreeDaySpecialDTO);
		}
		Collections.sort(daySpecialDTOs);
		return daySpecialDTOs;
	}

}
