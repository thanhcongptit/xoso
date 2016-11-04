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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class DetailNewsController extends BaseController {
    private static HashMap<String,News> hLotteryNews=null;
    private static List<News> listNews=null;
    private static String sDDMMYYYY=null;
    public DetailNewsController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLotteryNews==null){hLotteryNews=new HashMap<String, News>();}
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLotteryNews.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String id=request.getParameter("id");
        String sid=request.getParameter("sid");
        String key=sid+"_"+id+"_"+ddmmyyyy.replace("/", "");
        
        News lotteryNews=null;
//        if(hLotteryNews.containsKey(key)){
//            lotteryNews=hLotteryNews.get(key);
//        }else{
            LotteryRequest  lotteryRequest=new LotteryRequest();
            listNews=lotteryRequest.parserLotteryNews(Contant.SITE_ID, "1", "15");
            lotteryNews=lotteryRequest.parserLotteryNewsDetail(sid, id);
            if(lotteryNews!=null){
                lotteryNews.setContent(lotteryNews.getContent().replaceAll("/userfiles/image/", "http://img.xoso.wap.vn/userfiles/image/"));
                hLotteryNews.put(key, lotteryNews);
                
            }
        //}
        
        mod.addObject("lotteryNews", lotteryNews);
        mod.addObject("listNews", listNews);                
        
        if(lotteryNews!=null){
            // seo
            String slogan="Bản tin xổ số wap";
            String title=lotteryNews.getTitle();
            String keywords=lotteryNews.getTag();
            String description=lotteryNews.getDescription();                
            
            mod.addObject("slogan", slogan);
            mod.addObject("title", title);
            mod.addObject("keywords", keywords);
            mod.addObject("description", description);
        }
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/detail_news");}
        else{mod.setViewName("/mobile/detail_news");}
        return mod;
    }
    
}
