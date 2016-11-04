/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.request.LotteryRequest;
import inet.util.StringPro;
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
public class DoVeController extends BaseController {
    private static HashMap<String,List<Lottery>> hLottery=null;
    private static HashMap<String,List<List<String>>> hLotteryDauDuoi=null;
    private static HashMap<String,LotteryCompany> hRegoin=null;
    private static String sDDMMYYYY=null;
    public DoVeController() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hLottery==null){hLottery=new HashMap<String, List<Lottery>>();}
        if(hLotteryDauDuoi==null){hLotteryDauDuoi=new HashMap<String, List<List<String>>>();}
        if(hRegoin==null){hRegoin=new HashMap<String, LotteryCompany>();}
        if(sDDMMYYYY==null||!ddmmyyyy.equals(sDDMMYYYY)){
            hLottery.clear();
            hLotteryDauDuoi.clear();
            hRegoin.clear();
            sDDMMYYYY=ddmmyyyy;
        }
    }
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String code=request.getParameter("c");
        String day=request.getParameter("d");
        String so=request.getParameter("s");
        
        if(code==null||"".equals(code)){code="XSHCM";}
        else{code=code.toUpperCase();}
        
        if(day==null){day=ddmmyyyy;}
        else{day=day.replace("-", "/");}
        
        if(so==null||"".equals(so)){so="12";}
        
        // check uri
//        if(!checkUri(code, canonical)){
//            try{
//                if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/404");}
//                else{mod.setViewName("/mobile/404");}
//                return mod;
//            }catch(Exception e){}
//        }
        
        //lay ket qua xo so mien bac cua 7 ngay
        List<Lottery> listLottery=null;
        List<List<String>> listLotteryDuoi=null;        
        List<List<String>> listLotteryDau=null;
        LotteryCompany lotteryCompany=null;
        String key=day.replace("/", "")+"_"+code;
        if(hLottery.containsKey(key)){
            listLottery=hLottery.get(key);
            if(hLotteryDauDuoi.containsKey(key+"_duoi")){
                listLotteryDuoi=hLotteryDauDuoi.get(key+"_duoi");
                listLotteryDau=hLotteryDauDuoi.get(key+"_dau");
            }            
                       
            if(hRegoin.containsKey(key)){
                lotteryCompany=hRegoin.get(key);
            }
        }else{
            String endDate=day;
            String startDate=day;
            LotteryRequest lotteryRequest=new LotteryRequest();
            listLottery=lotteryRequest.parserLotteryResult(code,startDate,endDate);
            if(listLottery!=null&&!listLottery.isEmpty()){
                hLottery.put(key, listLottery);                
                List<String> listDuoiMB=null;
                List<String> listDauMB=null;
                for(int i=0;i<listLottery.size();i++){
                    listDuoiMB=StringPro.filterDauOrDuoi(listLottery.get(i), true);
                    listDauMB=StringPro.filterDauOrDuoi(listLottery.get(i), false);
                    if(listLotteryDuoi==null){listLotteryDuoi=new ArrayList<List<String>>();}
                    if(listLotteryDau==null){listLotteryDau=new ArrayList<List<String>>();}
                    listLotteryDuoi.add(listDuoiMB);        
                    listLotteryDau.add(listDauMB);
                }
                
                if(listLotteryDuoi!=null&&!listLotteryDuoi.isEmpty()){
                    hLotteryDauDuoi.put(key+"_duoi", listLotteryDuoi);
                    hLotteryDauDuoi.put(key+"_dau", listLotteryDau);
                }
                
                //code thuoc khu vuc nao
                lotteryCompany=findLotteryCompanyOfCode(code);
                if(lotteryCompany!=null){
                    hRegoin.put(key, lotteryCompany);
                }
            }
        }
        
        if(listLottery!=null&&!listLottery.isEmpty()){
            int total=checkVeSo(listLottery.get(0), so);
            mod.addObject("total", total);
        }
                
        mod.addObject("listLottery", listLottery);
        mod.addObject("listLotteryDuoi", listLotteryDuoi);
        mod.addObject("listLotteryDau", listLotteryDau);
        mod.addObject("lotteryCompany", lotteryCompany);
        mod.addObject("ddmmyyyy", day);
        mod.addObject("code", code);
        mod.addObject("so", so);
        
        // seo
        
        String slogan="Dò vé số - Tra cứu kết quả xổ số";
        String title="Dò vé số - Tra cứu kết quả xổ số";        
        String keywords="do ve so, tra cuu ket qua xo so";
        String description="Dò vé số - Tra cứu kết quả xổ số. Giúp bạn kiểm tra con số mình đánh có về không một cách nhanh nhất";
        
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/dove");}
        else{mod.setViewName("/mobile/dove");}
        return mod;
    }
    private boolean checkUri(String code,String uri){
        boolean result=true;
        if(uri==null){return result;}
        if(uri.contains("ket-qua-xo-so-thua-thien-hue-xstth")){return result;}
        LotteryCompany lotteryCompany=findLotteryCompanyOfCode(code);        
        if(lotteryCompany==null||!uri.contains("ket-qua-xo-so-"+lotteryCompany.getCompanyLink())){result=false;}
        
        return result;
    }
    
    private int checkVeSo(Lottery lottery,String so){
        int result=0;        
        if(lottery==null){return result;}
        
        if(lottery.getSpecial()!=null&&lottery.getSpecial().endsWith(so)){result++;}
        if(lottery.getFirst()!=null){
            if(!lottery.getFirst().contains("-")){
                if(lottery.getFirst().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFirst(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        if(lottery.getSecond()!=null){
            if(!lottery.getSecond().contains("-")){
                if(lottery.getSecond().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSecond(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        if(lottery.getThird()!=null){
            if(!lottery.getThird().contains("-")){
                if(lottery.getThird().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        if(lottery.getFourth()!=null){
            if(!lottery.getFourth().contains("-")){
                if(lottery.getFourth().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFourth(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        if(lottery.getFifth()!=null){
            if(!lottery.getFifth().contains("-")){
                if(lottery.getFifth().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        
        if(lottery.getSixth()!=null){
            if(!lottery.getSixth().contains("-")){
                if(lottery.getSixth().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSixth(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        if(lottery.getSeventh()!=null){
            if(!lottery.getSeventh().contains("-")){
                if(lottery.getSeventh().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSeventh(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        if(lottery.getEighth()!=null&&!"".equals(lottery.getEighth())){
            if(!lottery.getEighth().contains("-")){
                if(lottery.getEighth().endsWith(so)){result++;}
            }else{
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getEighth(),"-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if(token.endsWith(so)){result++;}
                }
            }
        }
        
        return result;
    }
}
