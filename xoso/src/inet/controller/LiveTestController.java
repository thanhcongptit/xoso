/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.Lottery;
import inet.model.LotteryResultDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class LiveTestController extends AbstractController {
    
    public LiveTestController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView mod=new ModelAndView("livetest");
        
        String g0=request.getParameter("g0");
        String g1=request.getParameter("g1");
        String g2=request.getParameter("g2");
        String g31=request.getParameter("g31");
        String g32=request.getParameter("g32");
        String g41=request.getParameter("g41");
        String g42=request.getParameter("g42");
        String g43=request.getParameter("g43");
        String g44=request.getParameter("g44");
        String g45=request.getParameter("g45");
        String g46=request.getParameter("g46");
        String g47=request.getParameter("g47");
        String g5=request.getParameter("g5");
        String g61=request.getParameter("g61");
        String g62=request.getParameter("g62");
        String g63=request.getParameter("g63");
        String g7=request.getParameter("g7");
        String g8=request.getParameter("g8");
        
        Lottery lottery=new Lottery();
        lottery.setCode("XSCM");
        lottery.setSpecial(g0);
        lottery.setFirst(g1);
        lottery.setSecond(g2);
        lottery.setThird(g31+"-"+g32);
        lottery.setFourth(g41+"-"+g42+"-"+g43+"-"+g44+"-"+g45+"-"+g46+"-"+g47);
        lottery.setFifth(g5);
        lottery.setSixth(g61+"-"+g62+"-"+g63);
        lottery.setSeventh(g7);
        lottery.setEighth(g8);
        
        LotteryResultDAO lotteryResultDAO=new LotteryResultDAO();
        lotteryResultDAO.insert(lottery);
        
        return mod;
    }
    
}
