/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.bean.LotteryCompany;
import com.dao.LotteryResultDAO;
import com.utils.DateProc;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author HanhDung
 */
public class BaseController extends AbstractController {
   
    protected static String today=null;
    private static List<LotteryCompany> listLotteryCompany=null;
    public BaseController() {
    }
    
    protected void loadBase(){
        today= DateProc.TimestampYYYYMMDD(DateProc.createTimestamp());
        if(listLotteryCompany==null){
            LotteryResultDAO lotteryResultDAO=new LotteryResultDAO();
            listLotteryCompany=lotteryResultDAO.findLotteryCompany();
        }
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=new ModelAndView();
        
        loadBase();
        
        mod.addObject("listLotteryCompany", listLotteryCompany);
        return mod;
    }
}