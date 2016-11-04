/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.controller;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.Member;
import inet.bean.ThongKeVip;
import inet.constant.Constants;
import inet.request.LotteryRequest;
import inet.request.ThongKeVipRequest;
import inet.util.DatePro;
import inet.util.DateUtil;
import inet.util.StringPro;
import inet.util.Today;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.dao.DuDoanXoSoDao;

/**
 *
 * @author 24h
 */
public class DuController extends BaseController {

	private static HashMap<String, List<ThongKeVip>> hListThongKeVip = null;
	private static String sDDMMYYYY = null;
	private static long loadTimeVip = 0;

	public DuController() {
	}

	@Override
	protected void loadBase() {
		super.loadBase(); // To change body of generated methods, choose Tools |
							// Templates.

		if (hListThongKeVip == null) {
			hListThongKeVip = new HashMap<String, List<ThongKeVip>>();
		}
		if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
			hListThongKeVip.clear();
			sDDMMYYYY = ddmmyyyy;
		}
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mod = super.handleRequestInternal(request, response);

		String day = request.getParameter("d");
		String code = request.getParameter("c");
		if (day == null || "".equals(day)) {
			day = ddmmyyyy;
		} else {
			day = day.replace("-", "/");
		}

		String key = day.replace("/", "");

		if (ddmmyyyy.equals(day)) {
			Today today = new Today();
			if (today.getHour() == 16 && today.getMinute() > 38) {
				key = key + today.getHour() + today.getMinute();
			}
			if (today.getHour() == 17 && today.getMinute() > 38) {
				key = key + today.getHour() + today.getMinute();
			}
			if (today.getHour() == 18 && today.getMinute() > 38) {
				key = key + today.getHour() + today.getMinute();
			}
		}

		List<ThongKeVip> listthongKeVip = null;
		ThongKeVip thongKeVip = null;

		long timeCurrent = System.currentTimeMillis();
		if (hListThongKeVip.containsKey(key) && timeCurrent - loadTimeVip < (5 * 60 * 1000)) {
			listthongKeVip = hListThongKeVip.get(key);
		} else {
			key = day.replace("/", "");
			ThongKeVipRequest thongKeVipRequest = new ThongKeVipRequest();
			List<List<LotteryCompany>> listCompanyOpen = null;
			if (ddmmyyyy.equals(day)) {
				listCompanyOpen = listCompanyOpenToday;
			} else {
				String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(day);
				listCompanyOpen = getCompanyOpenDay(dayOfWeek);
			}

			if (listCompanyOpen != null && !listCompanyOpen.isEmpty()) {
				for (int i = 0; i < listCompanyOpen.size(); i++) {
					List<LotteryCompany> listComp = listCompanyOpen.get(i);
					if (listComp != null && !listComp.isEmpty()) {
						for (int j = 0; j < listCompanyOpen.get(i).size(); j++) {
							thongKeVip = thongKeVipRequest
									.parserThongKeVip(listCompanyOpen.get(i).get(j).getCode().replace("XS", ""), day);
							if (thongKeVip != null) {
								thongKeVip.setCompany(listCompanyOpen.get(i).get(j).getCompany().toUpperCase());
								thongKeVip.setRegion(listCompanyOpen.get(i).get(j).getRegion());
								if (listthongKeVip == null) {
									listthongKeVip = new ArrayList<ThongKeVip>();
								}
								listthongKeVip.add(thongKeVip);
							}
						}
					}
				}

				if (listthongKeVip != null && !listthongKeVip.isEmpty()) {
					LotteryRequest lotteryRequest = new LotteryRequest();
					List<Lottery> listLottery = null;
					for (int i = 0; i < listthongKeVip.size(); i++) {
						listLottery = lotteryRequest.parserLotteryResult("XS" + listthongKeVip.get(i).getCode(), day,
								day);
						if (listLottery != null && !listLottery.isEmpty()) {
							listthongKeVip.set(i, checkResultThongKeVip(listthongKeVip.get(i), listLottery.get(0)));
						}
					}
					hListThongKeVip.put(key, listthongKeVip);
				}
			}
		}

		mod.addObject("listthongKeVip", listthongKeVip);

		// seo
		String slogan = "Thống kê xổ số vip ";
		String title = "Thống kê miền bắc";
		String keywords = "du doan xsmb xsmt xsmn, du doan ket qua xo so mien bac mien trung mien nam, du doan xo so mien bac mien trung mien nam, soi cau xo so mien bac mien trung mien nam";
		String description = "SOI CAU - DU DOAN KET QUA XO SO MIEN BAC - XSMB MIEN TRUNG - XSMT MIEN NAM - XSMN. Phân tích và dự đoán kết quả xổ số miền bắc miền trung miền nam ngày hôm nay chính xác nhất";

		if (code != null && !"".equals(code)) {
			code = code.toUpperCase();
			code = code.replace("XS", "");
			List<ThongKeVip> listthongKeVipOfCode = getThongKeVipOfCode(code, listthongKeVip);

			if ("MB".equals(code)) {
				slogan = slogan + " miền bắc";
				title = "Soi Cầu - Dự đoán kết quả xổ số miền bắc - xsmb";
				keywords = "du doan xo so miền bắc, du doan ket qua xo so mien bac, du doan xsmb";
				description = "SOI CAU - DU DOAN KET QUA XO SO MIEN BAC - xsmb. Phân tích và dự đoán kết quả xổ số miền bắc ngày hôm nay chính xác nhất";
			} else if ("MT".equals(code)) {
				slogan = slogan + " miền trung";
				title = "Soi cầu - dự đoán kết quả xổ số miền trung - XSMT";
				keywords = "du doan xsmt, du doan ket qua xo so mien trung, du doan xo so mien trung";
				description = "SOI CAU - DU DOAN KET QUA XO SO MIEN TRUNG - XSMT. Phân tích và dự đoán kết quả xổ số miền trung ngày hôm nay chính xác nhất";
			} else if ("MN".equals(code)) {
				slogan = slogan + " miền nam";
				title = "Soi cầu - dự đoán kết quả xổ số miền nam - XSMN";
				description = "SOI CAU - DU DOAN KET QUA XO SO MIEN NAM - XSMN. Phân tích và dự đoán kết quả xổ số miền nam ngày hôm nay chính xác nhất";
				keywords = "du doan xsmn, du doan ket qua xo so mien nam, du doan xo so mien nam, soi cau xo so mien nam";
			} else {
				if (listthongKeVipOfCode != null && !listthongKeVipOfCode.isEmpty()) {
					slogan = slogan + listthongKeVipOfCode.get(0).getCompany();
					title = title + listthongKeVipOfCode.get(0).getCompany() + " - XS" + code;
					keywords = "du doan xo so " + listthongKeVipOfCode.get(0).getCompany() + " du doan ket qua xo so "
							+ listthongKeVipOfCode.get(0).getCompany() + ", du doan XS" + code;
					description = "SOI CAU - DU DOAN KET QUA XO SO " + listthongKeVipOfCode.get(0).getCompany()
							+ " - XS" + code + ". Phân tích và dự đoán kết quả xổ số "
							+ listthongKeVipOfCode.get(0).getCompany() + " ngày hôm nay chính xác nhất";
				}
			}

			mod.addObject("listthongKeVipOfCode", listthongKeVipOfCode);
			mod.addObject("code", code);
		}

		mod.addObject("ddmmyyyy", day);
		mod.addObject("today", ddmmyyyy);

		if (ddmmyyyy.equals(day)) {
			title = title + " hôm nay";
		} else {
			title = title + " ngày " + day;
		}

		mod.addObject("slogan", slogan);
		mod.addObject("title", title);
		mod.addObject("keywords", keywords);
		mod.addObject("description", description);

		Member memberLogin = null;
		if (request.getSession() != null) {
			memberLogin = request.getSession().getAttribute(Constants.LOGIN_SESSION) != null
					? (Member) request.getSession().getAttribute(Constants.LOGIN_SESSION) : null;
		}

		mod.addObject("memberLogin", memberLogin);

		String message1 = null;
		String message2 = null;
		DuDoanXoSoDao doanXoSoDao = new DuDoanXoSoDao();
		String openDate = DateUtil.date2String(new Date(), "dd-MM-yyyy");

		if (memberLogin != null) {
			
			
			message1 = doanXoSoDao.getDaySoByDate(openDate, 1, Integer.parseInt(memberLogin.getId() + ""));
			message2 = doanXoSoDao.getDaySoByDate(openDate, 2, Integer.parseInt(memberLogin.getId() + ""));

		} else {
			Date date = new Date();
			String sHHmmss = DateUtil.date2String(date, "HHmmss");
			int HHmmss = Integer.parseInt(sHHmmss);
			if (HHmmss >= 185500 && HHmmss < 235900) {
				message1 = doanXoSoDao.getDaySoByDateWithoutMoney(openDate, 1);
				message2 = doanXoSoDao.getDaySoByDateWithoutMoney(openDate, 2);
			}

			
		}
		
		mod.addObject("message1", message1);
		mod.addObject("message2", message2);
		mod.setViewName("/du");
		return mod;
	}

	private List<ThongKeVip> getThongKeVipOfCode(String code, List<ThongKeVip> listthongKeVip) {
		List<ThongKeVip> listThongKeVip = null;
		if (listthongKeVip == null || listthongKeVip.isEmpty()) {
			return null;
		}
		if (code == null || "".equals(code)) {
			return null;
		}
		if ("MB".equalsIgnoreCase(code) || "MT".equalsIgnoreCase(code) || "MN".equalsIgnoreCase(code)) {
			for (int i = 0; i < listthongKeVip.size(); i++) {
				if (listThongKeVip == null) {
					listThongKeVip = new ArrayList<ThongKeVip>();
				}
				if (listthongKeVip.get(i).getRegion().equals(code)) {
					listThongKeVip.add(listthongKeVip.get(i));
				}
			}
		} else {
			for (int i = 0; i < listthongKeVip.size(); i++) {
				if (listThongKeVip == null) {
					listThongKeVip = new ArrayList<ThongKeVip>();
				}
				if (listthongKeVip.get(i).getCode().equals(code)) {
					listThongKeVip.add(listthongKeVip.get(i));
				}
			}
		}

		return listThongKeVip;
	}

	private ThongKeVip checkResultThongKeVip(ThongKeVip thongKeVip, Lottery lottery) {

		String start = "<strong class=\"do\">";
		String end = "</strong>";
		String sVip = "";
		if (lottery.getSpecial() != null && !"".equals(lottery.getSpecial()) && lottery.getSpecial().length() > 4) {
			String special = lottery.getSpecial().substring(3, 5);
			if (thongKeVip.getDau() != null && special.startsWith(thongKeVip.getDau())) {
				thongKeVip.setDau(start + thongKeVip.getDau() + end);
			}

			if (thongKeVip.getDuoi() != null && special.endsWith(thongKeVip.getDuoi())) {
				thongKeVip.setDuoi(start + thongKeVip.getDuoi() + end);
			}

			StringPro sp = new StringPro();
			String str = sp.sub2RightString(lottery.getSpecial(), lottery.getFirst(), lottery.getSecond(),
					lottery.getThird(), lottery.getFourth(), lottery.getFifth(), lottery.getSixth(),
					lottery.getSeventh(), lottery.getEighth());
			if (thongKeVip.getVip() != null && thongKeVip.getVip().contains("-")) {
				String[] arrVip = thongKeVip.getVip().split("-");
				for (int i = 0; i < arrVip.length; i++) {
					if (str.contains(arrVip[i])) {
						sVip = start + arrVip[i] + end;
						thongKeVip.setVip(thongKeVip.getVip().replaceAll(arrVip[i], sVip));
					}
				}
			}

			if (thongKeVip.getXien() != null && thongKeVip.getXien().contains("-")) {
				String strLoXien = thongKeVip.getXien().replaceAll("\\(", "").replaceAll("\\)", "-");
				String[] arrLoXien = strLoXien.split("-");
				for (int i = 0; i < arrLoXien.length; i++) {
					if (str.contains(arrLoXien[i])) {
						sVip = start + arrLoXien[i] + end;
						thongKeVip.setXien(thongKeVip.getXien().replaceAll(arrLoXien[i], sVip));
					}
				}
			}

		}

		return thongKeVip;
	}

}
