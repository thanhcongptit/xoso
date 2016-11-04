/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.thongkebacnho;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.soicaupro.thongkebacnho.domain.AwardDTO;
import com.soicaupro.thongkebacnho.domain.AwardDetailDTO;
import com.soicaupro.thongkebacnho.domain.LotoTheoGiaiBachThuDTO;
import com.soicaupro.thongkebacnho.domain.LotoTheoGiaiDTO;
import com.soicaupro.thongkebacnho.domain.ResultOneDayDTO;

import inet.bean.Lottery;
import inet.util.DateUtil;

/**
 *
 * @author conglt
 */
public class CommonUtil {

    private static HashMap<String, List<LotoTheoGiaiDTO>> bo4_lotoRs = new HashMap<>();
    private static HashMap<String, List<LotoTheoGiaiBachThuDTO>> bo4_bachThuRs = new HashMap<>();
    private static HashMap<String, List<AwardDTO>> awards = new HashMap<>();
    
    public static List<AwardDTO> getListAwardDTOByKey(String key) {
        if(awards != null) {
            return awards.get(key);
        }
        
        return null;
    }
    
    public static void putListAwardDTOByKey(String key, List<AwardDTO> awardDTOs) {
        if(awards == null) {
            awards = new HashMap<>();
        }
        
        awards.put(key, awardDTOs);
    }
    
    public static boolean checkDate() {
        java.util.Date date = new java.util.Date();
        String dateNow = DateUtil.date2String(new java.util.Date(), "yyyy-MM-dd");
        java.util.Date test = DateUtil.toDate(dateNow + " 18:45", "yyyy-MM-dd HH:mm");
        if(date.before(test)) {
            System.out.println("test 1");
            return true;
        } else {
            System.out.println("test 0");
            return false;
        }
    }
    public static List<LotoTheoGiaiDTO> getValueByKey(String openDate) {
        if(bo4_lotoRs != null) {
            String date_request = DateUtil.date2String(new java.util.Date(), "dd/MM/yyyy");
            return bo4_lotoRs.get(openDate + "-" + date_request);
        }
        
        return null;
    }
    
    public static void putValue(String openDate, List<LotoTheoGiaiDTO> results) {
        if(bo4_lotoRs == null) {
            bo4_lotoRs = new HashMap<>();
        }
        String date_request = DateUtil.date2String(new java.util.Date(), "dd/MM/yyyy");
        bo4_lotoRs.put(openDate+"-"+date_request, results);
    }
    
     public static List<LotoTheoGiaiBachThuDTO> getValueByKeyBachThu(String openDate) {
        String date_request = DateUtil.date2String(new java.util.Date(), "dd/MM/yyyy");
        if(bo4_bachThuRs != null) {
            return bo4_bachThuRs.get(openDate+ "-"+ date_request);
        }
        
        return null;
    }
    
    public static void putValueBachThu(String openDate, List<LotoTheoGiaiBachThuDTO> results) {
        if(bo4_bachThuRs == null) {
            bo4_bachThuRs = new HashMap<>();
        }
        String date_request = DateUtil.date2String(new java.util.Date(), "dd/MM/yyyy");
        bo4_bachThuRs.put(openDate + "-" + date_request, results);
    }
    
    public static String addDate(Date date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME);
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.
        c.add(Calendar.DATE, day);
        return sdf.format(c.getTime());
    }

    public static String addDate1(java.util.Date date, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME1);
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.
        c.add(Calendar.DATE, day);
        return sdf.format(c.getTime());
    }

    public static java.util.Date addDate2(java.util.Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    public static java.sql.Date addDate3(java.sql.Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.
        c.add(Calendar.DATE, day);
        return new Date(c.getTime().getTime());
    }

    public static String getStringRS(String[] loto, String special, String first, String second,
            String third, String fourth, String fifth, String sixth, String seventh) {
        StringBuilder rs = new StringBuilder();

        rs.append(getString(special, loto));
        rs.append(getString(first, loto));
        rs.append(getString(second, loto));
        rs.append(getString(third, loto));
        rs.append(getString(fourth, loto));
        rs.append(getString(fifth, loto));
        rs.append(getString(sixth, loto));
        rs.append(getString(seventh, loto));

        String result = rs.toString();
        return result.substring(0, result.length() - 1);
    }

    private static String getString(String award, String loto[]) {
        StringBuilder sb = new StringBuilder();
        String[] awards = award.split("-");

        for (String a : awards) {
            if (a.endsWith(loto[0])) {
                sb.append(loto[0]).append(",");
            }

            if (a.endsWith(loto[1])) {
                sb.append(loto[1]).append(",");
            }
        }
        return sb.toString();
    }

    public static String getStringLotoRS(String loto, String special, String first, String second,
            String third, String fourth, String fifth, String sixth, String seventh) {
        StringBuilder rs = new StringBuilder();

        rs.append(getStringLotoRS(special, loto));
        rs.append(getStringLotoRS(first, loto));
        rs.append(getStringLotoRS(second, loto));
        rs.append(getStringLotoRS(third, loto));
        rs.append(getStringLotoRS(fourth, loto));
        rs.append(getStringLotoRS(fifth, loto));
        rs.append(getStringLotoRS(sixth, loto));
        rs.append(getStringLotoRS(seventh, loto));

        String result = rs.toString();
        return result.substring(0, result.length() - 1);
    }

    private static String getStringLotoRS(String award, String loto) {
        StringBuilder sb = new StringBuilder();
        String[] awards = award.split("-");

        for (String a : awards) {
            if (a.endsWith(loto)) {
                sb.append(loto).append(",");
            }

        }
        return sb.toString();
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME);
        return sdf.format(date);
    }

    public static String convertDateToString2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME2);
        return sdf.format(date);
    }

    public static String convertDateToString1(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME1);
        return sdf.format(date);
    }

    public static String convertDateToString3(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME);
        return sdf.format(date);
    }

    public static boolean isEmptyString(String s) {
        if (null == s || s.trim().equals("")) {
            return true;
        }

        return false;
    }

    public static String getValueLotoSpecial(Date date, List<ResultOneDayDTO> screenOneDays, int day) {

        for (ResultOneDayDTO dayDTO : screenOneDays) {

            if (addDate(date, day).equalsIgnoreCase(CommonUtil.convertDateToString(dayDTO.getOpen_date()))) {
                return dayDTO.getResult();
            }
        }

        return "";
    }

    public static String[] getListByTongCam(String tongcam) {
        String rs[] = new String[10];

        int tong = Integer.parseInt(tongcam);
        int tong1 = tong + 10;
        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if ((i + j) == tong || (i + j) == tong1) {
                    rs[index] = i + "" + j;
                    index++;
                }
            }
        }

        return rs;
    }

    public static java.util.Date convertStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME1);
        try {
            return sdf.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Date convertDateToDate(java.util.Date currentDate) {
        String s = convertDateToString3(currentDate);
        Date rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATETIME);
        try {
            rs = new Date(sdf.parse(s).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return rs;
    }

    public static AwardDetailDTO[][] getCacgiai(List<AwardDTO> awardDTOs) {
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

    private static AwardDetailDTO[] getListAwardDetail(String award, int indexGiai, java.sql.Date open_date) {
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

    public static String getStringFromList(List<String> finalList) {

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

    public static int getIndex(AwardDetailDTO[][] cacgiai, java.sql.Date dateAward) {
        for (int i = 0; i < cacgiai.length; i++) {
            if (cacgiai[i][0].getOpen_date().getTime() == dateAward.getTime()) {
                return i;
            }
        }

        return -1;
    }

    public static String getStringFromArray(String[] finalList) {

        StringBuilder rs = new StringBuilder("");

        for (int k = 0; k < finalList.length; k++) {
            if (k < finalList.length - 1) {
                rs.append(finalList[k]).append("-");
            } else {
                rs.append(finalList[k]);
            }
        }

        return rs.toString();
    }

    public static boolean checkTop10InResult(Lottery item, String value) {
        if(item.getSpecial().trim().endsWith(value)) {
            return true;
        }
        
        if(item.getFirst().trim().endsWith(value)) {
            return true;
        }
        
        String seconds[] = item.getSecond().split("-");
        String third[] = item.getThird().split("-");
        String fourth[] = item.getFourth().split("-");
        String fifth[] = item.getFifth().split("-");
        String sixth[] = item.getSixth().split("-");
        String seventh[] = item.getSeventh().split("-");
        
        if(checkInAwards(seconds, value)) {
            return true;
        }
        
        if(checkInAwards(third, value)) {
            return true;
        }
        
        if(checkInAwards(fourth, value)) {
            return true;
        }
        
        if(checkInAwards(fifth, value)) {
            return true;
        }
        
        if(checkInAwards(sixth, value)) {
            return true;
        }
       
        return checkInAwards(seventh, value);
    }

    private static boolean checkInAwards(String[] awards, String value) {
        for(String s: awards) {
            if(s.trim().endsWith(value)) {
                return true;
            }
        }
        
        return false;
    }

}
