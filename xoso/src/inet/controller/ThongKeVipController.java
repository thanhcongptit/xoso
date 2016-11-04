/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import inet.bean.Lottery;
import inet.bean.Member;
import inet.bean.ThongKeVip;
import inet.constant.Constants;
import inet.util.StringPro;
import inet.util.Today;

/**
 *
 * @author hanhlm
 */
public class ThongKeVipController extends BaseController {
    private static HashMap<String,List<ThongKeVip>> hListThongKeVip=null;
    private static String sDDMMYYYY=null;
    private static long loadTimeVip=0;
    public ThongKeVipController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        
        if(hListThongKeVip==null){hListThongKeVip=new HashMap<String, List<ThongKeVip>>();}
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hListThongKeVip.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String day=request.getParameter("d");
        if(day==null||"".equals(day)){
            day=ddmmyyyy;
        }else{day=day.replace("-", "/");}
        
        String key=day.replace("/", "");
        
        if(ddmmyyyy.equals(day)){
            Today today=new Today();
            if(today.getHour()==16&&today.getMinute()>38){key=key+today.getHour()+today.getMinute();}
            if(today.getHour()==17&&today.getMinute()>38){key=key+today.getHour()+today.getMinute();}
            if(today.getHour()==18&&today.getMinute()>38){key=key+today.getHour()+today.getMinute();}                        
        }
        
        
        // seo
        String slogan="Thống kê xổ số vip ";
        String title="Thống kê miền bắc";
        String keywords="du doan xsmb xsmt xsmn, du doan ket qua xo so mien bac mien trung mien nam, du doan xo so mien bac mien trung mien nam, soi cau xo so mien bac mien trung mien nam";
        String description="SOI CAU - DU DOAN KET QUA XO SO MIEN BAC - XSMB MIEN TRUNG - XSMT MIEN NAM - XSMN. Phân tích và dự đoán kết quả xổ số miền bắc miền trung miền nam ngày hôm nay chính xác nhất";
        
        mod.addObject("ddmmyyyy", day);
        mod.addObject("today", ddmmyyyy);
        
        if(ddmmyyyy.equals(day)){
            title=title+" hôm nay";
        }else{title=title+" ngày "+day;}
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
                
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/du");}
        else{mod.setViewName("/mobile/du");}
        return mod;
    }
    
    
    private List<ThongKeVip> getThongKeVipOfCode(String code,List<ThongKeVip> listthongKeVip){
        List<ThongKeVip> listThongKeVip=null;
        if(listthongKeVip==null||listthongKeVip.isEmpty()){return null;}
        if(code==null||"".equals(code)){return null;}
        if("MB".equalsIgnoreCase(code)||"MT".equalsIgnoreCase(code)||"MN".equalsIgnoreCase(code)){            
            for(int i=0;i<listthongKeVip.size();i++){
                if(listThongKeVip==null){listThongKeVip=new ArrayList<ThongKeVip>();}
                if(listthongKeVip.get(i).getRegion().equals(code)){
                    listThongKeVip.add(listthongKeVip.get(i));
                }
            }
        }else{
            for(int i=0;i<listthongKeVip.size();i++){
                if(listThongKeVip==null){listThongKeVip=new ArrayList<ThongKeVip>();}
                if(listthongKeVip.get(i).getCode().equals(code)){
                    listThongKeVip.add(listthongKeVip.get(i));
                }
            }
        }
        
        return listThongKeVip;
    }
    
    private ThongKeVip checkResultThongKeVip(ThongKeVip thongKeVip,Lottery lottery){
                
        String start="<strong class=\"do\">";
        String end="</strong>";
        String sVip="";
        if(lottery.getSpecial()!=null&&!"".equals(lottery.getSpecial())&&lottery.getSpecial().length()>4){
            String special=lottery.getSpecial().substring(3,5);
            if(thongKeVip.getDau()!=null&&special.startsWith(thongKeVip.getDau())){
                thongKeVip.setDau(start+thongKeVip.getDau()+end);                
            }
            
            if(thongKeVip.getDuoi()!=null&&special.endsWith(thongKeVip.getDuoi())){
                thongKeVip.setDuoi(start+thongKeVip.getDuoi()+end);
            }
            
            StringPro sp=new StringPro();
            String str=sp.sub2RightString(lottery.getSpecial(),lottery.getFirst(), lottery.getSecond(),lottery.getThird(), lottery.getFourth(), lottery.getFifth(), lottery.getSixth(), lottery.getSeventh(), lottery.getEighth());
            if(thongKeVip.getVip()!=null&&thongKeVip.getVip().contains("-")){
                String[] arrVip=thongKeVip.getVip().split("-");
                for(int i=0;i<arrVip.length;i++){
                    if(str.contains(arrVip[i])){
                        sVip=start+arrVip[i]+end;
                        thongKeVip.setVip(thongKeVip.getVip().replaceAll(arrVip[i], sVip));
                    }
                }
            }
            
            if(thongKeVip.getXien()!=null&&thongKeVip.getXien().contains("-")){
                String strLoXien=thongKeVip.getXien().replaceAll("\\(", "").replaceAll("\\)","-");
                String[] arrLoXien=strLoXien.split("-");
                for(int i=0;i<arrLoXien.length;i++){
                    if(str.contains(arrLoXien[i])){
                        sVip=start+arrLoXien[i]+end;
                        thongKeVip.setXien(thongKeVip.getXien().replaceAll(arrLoXien[i], sVip));
                    }
                }
            }
            
        }
        
        
        
        return thongKeVip;
    }
}
