/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vig.controller.thongke;

import com.bean.CauLotoDetail;
import com.bean.LotteryResult;
import com.dao.LotteryResultDAO;
import com.utils.DateProc;
import com.utils.LotoUtils;
import com.utils.StringConvert;
import com.xshuy.DateProcExt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class ChiTietCauLoaiController extends BaseController {
    
    private static String sDDMMYYYY=null;
    private static HashMap<String,Object> hMap=null;
    
    public ChiTietCauLoaiController() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hMap==null){hMap=new HashMap<String, Object>();}
        if(!today.equals(sDDMMYYYY)){hMap.clear();}
        sDDMMYYYY=today;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        
        String cau=request.getParameter("c");
        String loto=request.getParameter("l");
        String vitri1=request.getParameter("v1");
        String vitri2=request.getParameter("v2");        
        String tungay=request.getParameter("d");
        String code=request.getParameter("code");
        
        List<CauLotoDetail> listCaulotoDetail=null;
        
        if(cau!=null&&!"".equals(cau)&&loto!=null&&!"".equals(loto)
                &&vitri1!=null&&!"".equals(vitri1)&&vitri2!=null&&!"".equals(vitri2)&&tungay!=null&&!"".equals(tungay)){
            String key=cau+loto+vitri1+vitri2+tungay;
            
            
            if(hMap.containsKey(key)){
                listCaulotoDetail=(List<CauLotoDetail>)hMap.get(key);
            }else{
                String denngay=DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(tungay),-60);
        
                tungay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(tungay));
                denngay=DateProc.TimestampYYYYMMDD(DateProc.String2Timestamp(denngay));
                
                String keyLotteryResult=tungay+denngay;
                
                List<LotteryResult> listLotteryResult=null;
                if(hMap.containsKey(key)){
                    listLotteryResult=(List<LotteryResult>)hMap.get(keyLotteryResult);
                }else{
                    LotteryResultDAO lotteryResultDAO=new LotteryResultDAO();
                    listLotteryResult=lotteryResultDAO.findLotteryResult(code, denngay, tungay);
                    if(listLotteryResult!=null&&!listLotteryResult.isEmpty()){hMap.put(keyLotteryResult, listLotteryResult);}
                }
                
                if(listLotteryResult!=null&&!listLotteryResult.isEmpty()){
                    listCaulotoDetail=crawLotteryResult(listLotteryResult, vitri1, vitri2, cau,code);
                    if(listCaulotoDetail!=null&&!listCaulotoDetail.isEmpty()){
                        hMap.put(key, listCaulotoDetail);
                    }
                }
                
            }
        }
        
        mod.addObject("vitri1", vitri1);
        mod.addObject("vitri2", vitri2);
        mod.addObject("cau", cau);
        mod.addObject("loto", loto);
        mod.addObject("tungay", DateProcExt.convertYMDtoMDY(tungay));
        mod.addObject("listCaulotoDetail", listCaulotoDetail);
        mod.addObject("code", code);
        return mod;
    }
    
    private List<CauLotoDetail> crawLotteryResult(List<LotteryResult> list, String v1,String v2,String cau,String code){
        
        List<CauLotoDetail> resultDetail=null;
        
        LotteryResult lotteryResult=null;
        LotteryResult lottery1=null;
                
        int iCau=Integer.parseInt(cau)+1;
        int iV1=Integer.parseInt(v1);
        int iV2=Integer.parseInt(v2);
        int start=0;
        
        String str="";
        String result="";
        String loto="";
        String caploto="";
        String caplotodao="";
        
        CauLotoDetail cauLotoDetail=null;
        
        for (int i = 0; i < iCau; i++) {
                                                
                lottery1 = new LotteryResult();
                start = 0;
                lotteryResult = list.get(i);
                if(i<iCau){
                    loto=StringConvert.stringConcat(list.get(i+1));
                    caploto=loto.substring(iV1,iV1+1);
                    caploto=caploto+loto.substring(iV2,iV2+1);
                }
                
                
                lottery1.setOpen_date(lotteryResult.getOpen_date());
                // giai dac biet

                str = lotteryResult.getSpecial();
                if("XSTD".equalsIgnoreCase(code)){
                    result = checkAddString(iV1, start, 5, str, 1);
                }else{
                    result = checkAddString(iV1, start, 6, str, 1);
                }
                
                if (result != null) {
                    lottery1.setSpecial(result);
                } else {
                    lottery1.setSpecial(lotteryResult.getSpecial());
                }

                str = lottery1.getSpecial();
                if("XSTD".equalsIgnoreCase(code)){
                    result = checkAddString(iV2, start, 5, str, 2);
                }else{
                    result = checkAddString(iV2, start, 6, str, 2);
                }
                
                if (result != null) {
                    lottery1.setSpecial(result);
                }
                if("XSTD".equalsIgnoreCase(code)){
                    start = start + 5;
                }else{
                    start = start + 6;
                }
                

                // giai nhat                
                str = lotteryResult.getFirst();
                result = checkAddString(iV1, start, start + 5, str, 1);
                
                if (result != null) {
                    lottery1.setFirst(result);
                } else {
                    lottery1.setFirst(lotteryResult.getFirst());
                }
                
                str = lotteryResult.getFirst();
                result = checkAddString(iV2, start, start + 5, str, 2);
                
                if (result != null) {
                    lottery1.setFirst(result);
                }

                start = start + 5;
                
                

                //giai nhi
                int next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();                    
                    result = checkAddString(iV1, start, start + 5, str, 1);
                    if (result != null) {
                        lottery1.setSecond(lotteryResult.getSecond().replace(str, result));
                    } else if (lottery1.getSecond() == null) {
                        lottery1.setSecond(lotteryResult.getSecond());
                    }
                    start = start + 5;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 5, str, 2);
                    if (result != null) {
                        lottery1.setSecond(lottery1.getSecond().replace(str, result));
                    }
                    start = start + 5;
                }

                //giai ba
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getThird(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 5, str, 1);
                    if (result != null) {
                        lottery1.setThird(lotteryResult.getThird().replace(str, result));
                    } else if (lottery1.getThird() == null) {
                        lottery1.setThird(lotteryResult.getThird());
                    }
                    start = start + 5;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getThird(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 5, str, 2);
                    if (result != null) {
                        lottery1.setThird(lottery1.getThird().replace(str, result));
                    }
                    start = start + 5;
                }

                //giai tu            
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    if("XSTD".equalsIgnoreCase(code)){
                        result = checkAddString(iV1, start, start + 4, str, 1);
                    }else{
                        result = checkAddString(iV1, start, start + 5, str, 1);
                    }
                    
                    if (result != null) {
                        lottery1.setFourth(lotteryResult.getFourth().replace(str, result));
                    } else if (lottery1.getFourth() == null) {
                        lottery1.setFourth(lotteryResult.getFourth());
                    }
                    
                    if("XSTD".equalsIgnoreCase(code)){
                        start = start + 4;
                    }else{
                        start = start + 5;
                    }
                    
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    if("XSTD".equalsIgnoreCase(code)){
                        result = checkAddString(iV2, start, start + 4, str, 2);
                    }else{
                        result = checkAddString(iV2, start, start + 5, str, 2);
                    }
                    
                    if (result != null) {
                        lottery1.setFourth(lottery1.getFourth().replace(str, result));
                    }
                    if("XSTD".equalsIgnoreCase(code)){
                        start = start + 4;
                    }else{
                        start = start + 5;
                    }
                    
                }

                //giai nam   
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFifth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 4, str, 1);
                    if (result != null) {
                        lottery1.setFifth(lotteryResult.getFifth().replace(str, result));
                    } else if (lottery1.getFifth() == null) {
                        lottery1.setFifth(lotteryResult.getFifth());
                    }
                    start = start + 4;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getFifth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 4, str, 2);
                    if (result != null) {
                        lottery1.setFifth(lottery1.getFifth().replace(str, result));
                    }
                    start = start + 4;
                }

                //giai sau     
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    if("XSTD".equalsIgnoreCase(code)){
                        result = checkAddString(iV1, start, start + 3, str, 1);
                    }else{
                        result = checkAddString(iV1, start, start + 4, str, 1);
                    }
                    
                    if (result != null) {
                        lottery1.setSixth(lotteryResult.getSixth().replace(str, result));
                    } else if (lottery1.getSixth() == null) {
                        lottery1.setSixth(lotteryResult.getSixth());
                    }
                    
                    if("XSTD".equalsIgnoreCase(code)){
                        start = start + 3;
                    }else{
                        start = start + 4;
                    }
                    
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    if("XSTD".equalsIgnoreCase(code)){
                        result = checkAddString(iV2, start, start + 3, str, 2);
                    }else{
                        result = checkAddString(iV2, start, start + 4, str, 2);
                    }
                    
                    if (result != null) {
                        lottery1.setSixth(lottery1.getSixth().replace(str, result));
                    }
                    if("XSTD".equalsIgnoreCase(code)){
                        start = start + 3;
                    }else{
                        start = start + 4;
                    }
                }

                //giai bay            
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    if("XSTD".equalsIgnoreCase(code)){
                        result = checkAddString(iV1, start, start + 2, str, 1);
                    }else{
                        result = checkAddString(iV1, start, start + 3, str, 1);
                    }
                    
                    if (result != null) {
                        lottery1.setSeventh(lotteryResult.getSeventh().replace(str, result));
                    } else if (lottery1.getSeventh() == null) {
                        lottery1.setSeventh(lotteryResult.getSeventh());
                    }
                    
                    if("XSTD".equalsIgnoreCase(code)){
                        start = start + 2;
                    }else{
                        start = start + 3;
                    }
                    
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    if("XSTD".equalsIgnoreCase(code)){
                        result = checkAddString(iV2, start, start + 2, str, 2);
                    }else{
                        result = checkAddString(iV2, start, start + 3, str, 2);
                    }
                    
                    if (result != null) {
                        lottery1.setSeventh(lottery1.getSeventh().replace(str, result));
                    }
                    if("XSTD".equalsIgnoreCase(code)){
                        start = start + 2;
                    }else{
                        start = start + 3;
                    }
                }
                
                
                if(lotteryResult.getEighth()!=null&&!"".equals(lotteryResult.getEighth())){
                    next=start;
                    
                    str = lotteryResult.getEighth();
                    result = checkAddString(iV1, start, start + 2, str, 1);
                    if (result != null) {
                        lottery1.setEighth(result);
                    } else {
                        lottery1.setEighth(lotteryResult.getEighth());
                    }
                    
                    start=next;
                    str = lottery1.getEighth();
                    result = checkAddString(iV2, start, start + 2, str, 2);
                    if (result != null) {
                        lottery1.setEighth(result);
                    }
                }
                        
                
                cauLotoDetail=new CauLotoDetail();
                cauLotoDetail.setLotteryResult(lottery1);
                if(!"".equals(loto)){
                    cauLotoDetail.setLoto(StringConvert.lotoResult(lotteryResult).replaceAll("-", ",&nbsp;").replace(caploto, "<span style=\"color: red\">"+caploto+"</span>"));
                }else{
                    cauLotoDetail.setLoto(StringConvert.lotoResult(lotteryResult).replaceAll("-", ",&nbsp;"));
                }
                
                if(resultDetail==null){resultDetail=new ArrayList<CauLotoDetail>();}
                resultDetail.add(cauLotoDetail);
            }
        
        
        return resultDetail;
        
    }
    
    private List<CauLotoDetail> crawLotteryResult1(List<LotteryResult> list, String v1,String v2,String cau){
        
        List<CauLotoDetail> resultDetail=null;
        
        LotteryResult lotteryResult=null;
        LotteryResult lottery1=null;
                
        int iCau=Integer.parseInt(cau);
        int iV1=Integer.parseInt(v1);
        int iV2=Integer.parseInt(v2);
        int start=0;
        
        String str="";
        String result="";
        
        CauLotoDetail cauLotoDetail=null;
        
        for (int i = 0; i < iCau; i++) {
                lottery1 = new LotteryResult();
                start = 0;
                lotteryResult = list.get(i);

                lottery1.setOpen_date(lotteryResult.getOpen_date());
                // giai dac biet

                str = lotteryResult.getSpecial();
                result = checkAddString(iV1, start, 5, str, 1);
                if (result != null) {
                    lottery1.setSpecial(result);
                } else {
                    lottery1.setSpecial(lotteryResult.getSpecial());
                }

                str = lottery1.getSpecial();
                result = checkAddString(iV2, start, 5, str, 2);
                if (result != null) {
                    lottery1.setSpecial(result);
                }
                start = start + 5;

                // giai nhat                
                str = lotteryResult.getFirst();
                result = checkAddString(iV1, start, start + 5, str, 1);
                if (result != null) {
                    lottery1.setFirst(result);
                } else {
                    lottery1.setFirst(lotteryResult.getFirst());
                }

                str = lotteryResult.getFirst();
                result = checkAddString(iV2, start, start + 5, str, 2);
                if (result != null) {
                    lottery1.setFirst(result);
                }

                start = start + 5;

                //giai nhi
                int next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 5, str, 1);
                    if (result != null) {
                        lottery1.setSecond(lotteryResult.getSecond().replace(str, result));
                    } else if (lottery1.getSecond() == null) {
                        lottery1.setSecond(lotteryResult.getSecond());
                    }
                    start = start + 5;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 5, str, 2);
                    if (result != null) {
                        lottery1.setSecond(lottery1.getSecond().replace(str, result));
                    }
                    start = start + 5;
                }

                //giai ba
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getThird(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 5, str, 1);
                    if (result != null) {
                        lottery1.setThird(lotteryResult.getThird().replace(str, result));
                    } else if (lottery1.getThird() == null) {
                        lottery1.setThird(lotteryResult.getThird());
                    }
                    start = start + 5;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getThird(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 5, str, 2);
                    if (result != null) {
                        lottery1.setThird(lottery1.getThird().replace(str, result));
                    }
                    start = start + 5;
                }

                //giai tu            
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 4, str, 1);
                    if (result != null) {
                        lottery1.setFourth(lotteryResult.getFourth().replace(str, result));
                    } else if (lottery1.getFourth() == null) {
                        lottery1.setFourth(lotteryResult.getFourth());
                    }
                    start = start + 4;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 4, str, 2);
                    if (result != null) {
                        lottery1.setFourth(lottery1.getFourth().replace(str, result));
                    }
                    start = start + 4;
                }

                //giai nam   
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFifth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 4, str, 1);
                    if (result != null) {
                        lottery1.setFifth(lotteryResult.getFifth().replace(str, result));
                    } else if (lottery1.getFifth() == null) {
                        lottery1.setFifth(lotteryResult.getFifth());
                    }
                    start = start + 4;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getFifth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 4, str, 2);
                    if (result != null) {
                        lottery1.setFifth(lottery1.getFifth().replace(str, result));
                    }
                    start = start + 4;
                }

                //giai sau     
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 3, str, 1);
                    if (result != null) {
                        lottery1.setSixth(lotteryResult.getSixth().replace(str, result));
                    } else if (lottery1.getSixth() == null) {
                        lottery1.setSixth(lotteryResult.getSixth());
                    }
                    start = start + 3;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 3, str, 2);
                    if (result != null) {
                        lottery1.setSixth(lottery1.getSixth().replace(str, result));
                    }
                    start = start + 3;
                }

                //giai bay            
                next = start;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV1, start, start + 2, str, 1);
                    if (result != null) {
                        lottery1.setSeventh(lotteryResult.getSeventh().replace(str, result));
                    } else if (lottery1.getSeventh() == null) {
                        lottery1.setSeventh(lotteryResult.getSeventh());
                    }
                    start = start + 2;
                }

                start = next;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                    str = stringTokenizer.nextToken();
                    result = checkAddString(iV2, start, start + 2, str, 2);
                    if (result != null) {
                        lottery1.setSeventh(lottery1.getSeventh().replace(str, result));
                    }
                    start = start + 2;
                }
                
                cauLotoDetail=new CauLotoDetail();
                cauLotoDetail.setLotteryResult(lottery1);
                cauLotoDetail.setLoto(StringConvert.lotoResult(lotteryResult).replaceAll("-", ",&nbsp;"));
                if(resultDetail==null){resultDetail=new ArrayList<CauLotoDetail>();}
                resultDetail.add(cauLotoDetail);
            }
        
        
        return resultDetail;
        
    }
    
    
    private String checkAddString(int vitri, int start, int end, String str, int iVitri) {
        String temp1 = "<span style=\"color: red\">";
        String result = null;
        if (-1 < str.indexOf("<")) {
            end = end - temp1.length();
        }
        if (vitri >= start && vitri < end) {
            result = addString(str, vitri - start, 0, iVitri);
        }

        return result;
    }

    private String addString(String str, int i, int s, int iVitri) {
        String temp1 = "<span style=\"color: red\">";
        String temp2 = "</span>";
        if (-1 < str.indexOf("<") && str.indexOf("<") <= i) {
            i = i + temp1.length();
        }
        String start = "";
        String center = "";
        String end = "";
        String result = str;
        String so1="";
        String so2="";
        for (int j = 0; j < str.length(); j++) {
            if (j == i) {
                if (j > 0) {
                    start = str.substring(0, j);
                }
                center = str.substring(j, j + 1);
                if (iVitri == 1) {
                    so1 = center;
                } else {
                    so2 = center;
                }
                if (j < str.length() - 1) {
                    end = str.substring(j + 1, str.length());
                }
                result = start + temp1 + center + temp2 + end;
            }
        }

        return result;
    }
}
