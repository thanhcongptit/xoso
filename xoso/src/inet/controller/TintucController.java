/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.LotteryNews;
import inet.bean.News;
import inet.model.NewsDAO;
import inet.request.LotteryRequest;
import inet.util.Contant;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */

public class TintucController extends BaseController{
    
    private static List<News> sListLottery=null;
    private static int totalPage=0;
    private static int sTotalRow=0;
    private static long sLoadTime=0;
    public TintucController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(sListLottery==null){sListLottery=new ArrayList<News>();}        
    }
    
    
            
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);                        
        
        String page=request.getParameter("p");        
        int iPage=1;
        if(page!=null&&!"".equals(page)){
            try{
                iPage=Integer.parseInt(page);
            }catch(Exception e){}
        }
        int totalRow=10*iPage;
        long timeCurrent=System.currentTimeMillis();
        List<News> listNews=null;
       
        LotteryRequest lotteryRequest=new LotteryRequest();
        listNews=lotteryRequest.parserLotteryNews(Contant.SITE_ID, ""+iPage, "10");
        if(listNews!=null&&!listNews.isEmpty()){
            sListLottery=listNews;                
            sTotalRow=totalRow;
            sLoadTime=timeCurrent;
            NewsDAO newsDAO=new NewsDAO();
            int countRow=newsDAO.count();
            if(countRow%10>0){
                totalPage=(countRow/10)+1;
            }else{
                totalPage=(countRow/10);
            }
        }
        
        
        //System.out.println("page====================="+iPage);
        mod.addObject("listNews", listNews);
        mod.addObject("iPage", iPage);
        mod.addObject("totalPage", totalPage);
        
        // seo
        String slogan="Bản tin xổ số wap";
        String title="Tin tức xổ số";
        String keywords="ket qua xo so,so xo,xo so mien bac,xổ số miền bắc,xstd,xsmb,xo so mien trung,kqxs,xo so mien nam,xo so,xsmt,xsmn,kết quả xổ số";
        String description="tin tuc xo so, tin tuc, tin tuc 24h, tin tuc hot";                
                                    
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/tintuc");}
        else{mod.setViewName("/mobile/tintuc");}
        
        return mod;
    }
    
}
