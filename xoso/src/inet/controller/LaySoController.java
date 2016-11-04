/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.dao.DuDoanXoSoDao;

import inet.bean.CapsoOnline;
import inet.bean.DuDoanXoso;
import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.bean.Member;
import inet.constant.Constants;
import inet.model.MemberDAO;
import inet.util.DateUtil;

/**
 *
 * @author 24h
 */
public class LaySoController extends BaseController {
    
    private static HashMap<String,List<Lottery>> hLotteryMB=null;
    private static HashMap<String,Lotterys> hLotterys=null;
    private static HashMap<String,List<String>> hLotteryDauDuoi=null;
    private static HashMap<String,String> hNumSize=null;
    private static String sDDMMYYYY=null;
    public LaySoController() {
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
        mod.setViewName("/layso");
        //kiem tra dang nhap
        Member memberLogin = null;
        
        if (request.getSession() != null) {
            memberLogin = request.getSession().getAttribute(Constants.LOGIN_SESSION) != null ? (Member) request.getSession().getAttribute(Constants.LOGIN_SESSION) : null;
        }
        
        if(memberLogin == null) {
        	return mod;
        } 
        
        String id = request.getParameter("id");
        System.out.println("id: " +id);
        DuDoanXoso doanXoso = new DuDoanXoSoDao().getDuDoanXoSoByDate
        		(DateUtil.date2String(new Date(), "dd-MM-yyyy"), Integer.parseInt(id));
        
        if(doanXoso != null) {
        	
        	int restXu = memberLogin.getXu() - doanXoso.getXu();
        	mod.addObject("isDaySo", true);
        	
        	if(restXu >= 0) {
        		mod.addObject("isXu", true);
        		try{
        			new MemberDAO().updateXu(memberLogin.getId(), restXu);
        			memberLogin.setXu(restXu);
        		} catch(Exception e) {
        			e.printStackTrace();
        		}
        		
        		mod.addObject("dayso", doanXoso.getDayso());
        		mod.addObject("vitri", doanXoso.getVitri());
        		request.getSession().setAttribute(Constants.LOGIN_SESSION, memberLogin);
        		new DuDoanXoSoDao().createLaySo(Integer.parseInt(memberLogin.getId() + ""), 
        				DateUtil.date2String(new Date(), "dd-MM-yyyy"), doanXoso.getVitri(), doanXoso.getDayso());
        		
        	} else {
        		mod.addObject("isXu", false);
        	}
        } else {
        	mod.addObject("isDaySo", false);
        }
        
        
        
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
