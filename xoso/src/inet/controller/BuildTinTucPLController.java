/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.LotteryNews;
import inet.bean.News;
import inet.request.LotteryRequest;
import inet.util.Contant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class BuildTinTucPLController extends AbstractController {
    private static List<News> sListLottery=null;
    private static int sTotalRow=0;
    private static long sLoadTime=0;
    
    public BuildTinTucPLController() {
        if(sListLottery==null){sListLottery=new ArrayList<News>();}
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=new ModelAndView("/ajax/tinphapluat");
        String page=request.getParameter("p");
        int iPage=1;
        if(page!=null&&!"".equals(page)){
            try{
                iPage=Integer.parseInt(page);
            }catch(Exception e){}
        }
        int totalRow=15*iPage;
        long timeCurrent=System.currentTimeMillis();
        List<News> listNews=null;
        if(totalRow<=sTotalRow&&timeCurrent-sLoadTime <(5*60*1000)){
            listNews=sListLottery;
        }else{
            LotteryRequest lotteryRequest=new LotteryRequest();
            listNews=lotteryRequest.parserLotteryNews(Contant.SITE_PHAP_LUAT, "1", ""+totalRow);
            if(listNews!=null&&!listNews.isEmpty()){
                sListLottery=listNews;
                if(listNews.size()<totalRow){
                    totalRow=listNews.size();
                    if(totalRow/15>0){iPage=(totalRow/15);}
                    else{iPage=1;}
                }
                sTotalRow=totalRow;
                sLoadTime=timeCurrent;
            }
        }
        
        
        mod.addObject("listNews", listNews);
        mod.addObject("iPage", iPage);
        
        return mod;
    }
    
}
