/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.CapsoOnline;
import inet.bean.CapsoOnlineHistory;
import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.bean.Member;
import inet.constant.Constants;
import inet.model.CapsoOnlineDAO;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.DateUtil;
import inet.util.RequestUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 24h
 */
public class LotoOnlineController extends BaseController {
    
    private static HashMap<String,List<Lottery>> hLotteryMB=null;
    private static HashMap<String,Lotterys> hLotterys=null;
    private static HashMap<String,List<String>> hLotteryDauDuoi=null;
    private static HashMap<String,String> hNumSize=null;
    private static String sDDMMYYYY=null;
    public LotoOnlineController() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotteryMB==null){hLotteryMB=new HashMap<String, List<Lottery>>();}
        if(hLotterys==null){hLotterys=new HashMap<String, Lotterys>();}
        if(hLotteryDauDuoi==null){hLotteryDauDuoi=new HashMap<String, List<String>>();} 
        if(hNumSize==null){hNumSize=new HashMap<String, String>();} 
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLotteryMB.clear();
            hLotterys.clear();
            hLotteryDauDuoi.clear();
            hNumSize.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        //kiem tra dang nhap
        Member memberLogin = null;
        if (request.getSession() != null) {
            memberLogin = request.getSession().getAttribute(Constants.LOGIN_SESSION) != null ? (Member) request.getSession().getAttribute(Constants.LOGIN_SESSION) : null;
        }
        
        CapsoOnlineDAO capsoOnlineDAO = new CapsoOnlineDAO();
        String action = RequestUtil.getString(request, "action", "");
        String msg = "";
        String sNhan = "--";
        String sLailo = "<span id='thangthua' style='padding:3px; background:#616A89; color:white'>--</span>";
        if("ghilo".equals(action)){
            //kiem tra da ghi du 2000 diem/ngay chua?
            String ngayghi = RequestUtil.getString(request, "ngayghi", "");
            Date currentDate = new Date();
            if(DateProc.String2Timestamp(ngayghi).compareTo(currentDate) < 0){
                msg = "Ngày này đã hết thời hạn ghi loto!";
            }else if(DateProc.String2Timestamp(ngayghi).compareTo(currentDate) == 0){
                String dateStr = DateUtil.date2String(currentDate, "dd/MM/yyyy") + " 18:00";
                Date dateEnd = DateUtil.toDate(dateStr, "dd/MM/yyyy HH:mm");
                if(currentDate.compareTo(dateEnd) > 0){
                    msg = "Ngày này đã hết thời hạn ghi loto!";
                }
            }
            
            //lay ra so diem da choi ngay ghi
            int tongDiemDaChoi = capsoOnlineDAO.findSoDiemDaChoi(memberLogin.getId(), DateProc.String2Timestamp(ngayghi));
            int sodiem = RequestUtil.getInt(request, "sodiem", 1);
            if(sodiem + tongDiemDaChoi > 2000) msg = "Mỗi ngày bạn chỉ được ghi tối đa 2000 điểm!";
            
            if("".equals(msg)){
                String capso = RequestUtil.getString(request, "capso", "");

                //kiem tra cap so co hop le khong
                List<CapsoOnline> listCapso = parseCapsoOnline(capso, sodiem);
                if(!listCapso.isEmpty()){                
                    for(CapsoOnline capsoOnline : listCapso){
                        capsoOnline.setMemberId(memberLogin.getId());
                        capsoOnline.setCurrentMoney(memberLogin.getMoney());
                        capsoOnline.setNgaychoi(DateProc.String2Timestamp(ngayghi));
                        capsoOnlineDAO.create(capsoOnline);
                    }
                }
                ddmmyyyy = ngayghi;
            }
        }else if("search".equals(action)){
            String ngayghi = RequestUtil.getString(request, "ngayghi", "");
            ddmmyyyy = ngayghi;
        }
        
        int diem = 0;
        int chi = 0;
        int nhan = 0;
        int lailo = 0;
        boolean checkShowNhay = false;
        List<CapsoOnline> listCapso = null;
        List<CapsoOnline> listCapsoHistory = null;
        List<CapsoOnlineHistory> listHistoryOnline = null;
        if(memberLogin != null){
            listCapso = capsoOnlineDAO.findListByDate(DateProc.String2Timestamp(ddmmyyyy), memberLogin.getId());
            listCapsoHistory = capsoOnlineDAO.findListByMemberHistory(memberLogin.getId());
            //chuyen list nay vao dang report history
            if(listCapsoHistory != null){
                listHistoryOnline = new ArrayList<CapsoOnlineHistory>();
                CapsoOnlineHistory history = null;
                int i = 0;
                for(CapsoOnline csTmp : listCapsoHistory){
                    if(history == null || history.getNgaychoi().compareTo(csTmp.getNgaychoi()) != 0){
                        if(history != null && !history.getListCapso().isEmpty()) listHistoryOnline.add(history);
                        history = new CapsoOnlineHistory();
                        history.setNgaychoi(csTmp.getNgaychoi());
                        history.setTienHientai(csTmp.getCurrentMoney().intValue());                        
                    }
                    //cap so nay vao list
                    history.getListCapso().add(csTmp);
                    //tinh tong diem
                    history.setTongDiem(history.getTongDiem() + csTmp.getSodiem());
                    //tinh tong nhay
                    history.setTongNhay(history.getTongNhay() + csTmp.getNhay().intValue());
                    //tinh thang thua
                    //= tong thang thua hien tai + (80*sodiem*sonhay - 23*sodiem)
                    history.setTongThangthua(history.getTongThangthua() + (80*csTmp.getSodiem()*csTmp.getNhay().intValue() - 23 * csTmp.getSodiem()));                                        
                    i++;
                    if(i == listCapsoHistory.size()) listHistoryOnline.add(history);
                }
                System.out.println("listHistoryOnline="+listHistoryOnline.size());
            }
        }
        if(listCapso != null){            
            for(CapsoOnline capsoOnline : listCapso){
                diem += capsoOnline.getSodiem();
                if(capsoOnline.getNhay() != null){
                    nhan += 80 * capsoOnline.getNhay().intValue() * capsoOnline.getSodiem();
                    checkShowNhay = true;
                }
            }
            chi = 23 * diem;
        }
        lailo = nhan - chi;
        if(checkShowNhay){
            sNhan = nhan + " k";
            if(lailo < 0){
                sLailo = "<span id='thangthua' style='padding:3px; background:red; color:white'>"+lailo+" k</span>";
            }else{
                sLailo = "<span id='thangthua' style='padding:3px; background:green; color:white'>"+lailo+" k</span>";
            }
        }
        
        mod.addObject("diem", diem);
        mod.addObject("chi", chi);
        
        mod.addObject("sNhan", sNhan);
        mod.addObject("sLailo", sLailo);
        mod.addObject("title", "Loto Online");
        mod.addObject("msg", msg);
        mod.addObject("ddmmyyyy", ddmmyyyy);
        mod.addObject("listCapso", listCapso);
        mod.addObject("listCapsoHistory", listCapsoHistory);
        mod.addObject("listHistoryOnline", listHistoryOnline);
        mod.setViewName("/loto-online");
        
        return mod;
    }
    
    public List<CapsoOnline> parseCapsoOnline(String capso, int sodiem){
        List<CapsoOnline> list = new ArrayList<CapsoOnline>();
        
        if(capso.contains(",")){
            String[] split = capso.split(",");
            Integer so = null;
            CapsoOnline capsoOnline = null;
            for(String s : split){
                s = s.trim();
                if(s.length() == 2){
                    so = parseInt(capso);
                    if(so != null){
                        capsoOnline = new CapsoOnline();
                        if(so.compareTo(new Integer(10)) < 0 ){
                            capsoOnline.setCapso("0"+so);
                        }else{
                            capsoOnline.setCapso(so+"");
                        }
                        capsoOnline.setSodiem(sodiem);
                        list.add(capsoOnline);
                    }
                }
            }
        }else{
            capso = capso.trim();
            if(capso.length() == 2){
                Integer so = parseInt(capso);
                if(so != null){
                    CapsoOnline capsoOnline = new CapsoOnline();
                    if(so.compareTo(new Integer(10)) < 0 ){
                        capsoOnline.setCapso("0"+so);
                    }else{
                        capsoOnline.setCapso(so+"");
                    }
                    capsoOnline.setSodiem(sodiem);
                    list.add(capsoOnline);
                }
            }
        }
        
        return list;
    }
    
    public Integer parseInt(String source){
        Integer rs = null;
        try{
            rs = Integer.parseInt(source);
        }catch(Exception ex){}
        return rs;
    }
}
