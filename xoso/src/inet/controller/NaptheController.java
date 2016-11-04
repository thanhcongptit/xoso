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

import inet.bean.CapsoOnline;
import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.bean.Member;
import inet.constant.Constants;

/**
 *
 * @author 24h
 */
public class NaptheController extends BaseController {
    
    private static HashMap<String,List<Lottery>> hLotteryMB=null;
    private static HashMap<String,Lotterys> hLotterys=null;
    private static HashMap<String,List<String>> hLotteryDauDuoi=null;
    private static HashMap<String,String> hNumSize=null;
    private static String sDDMMYYYY=null;
    public NaptheController() {
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
        
        mod.setViewName("/napthe");
        
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
