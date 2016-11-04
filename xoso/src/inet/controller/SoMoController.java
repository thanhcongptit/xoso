/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.SoMo;
import inet.request.SoMoRequest;
import inet.util.UTF8Tool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class SoMoController extends BaseController {
    private static List<SoMo> listSoMo=null;
    private static String sDDMMYYYY=null;
    
    public SoMoController() {
    }
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        request.setCharacterEncoding("UTF-8");
        
        String page=request.getParameter("p");
        if(page==null||"".equals(page)){page="1";}
        int iPage=1;
        
        String dream=request.getParameter("dream");      
       // System.out.println("dream==========>>>>>>"+dream);
        String number=request.getParameter("number");
                
        
        try{
            iPage=Integer.parseInt(page);
        }catch(Exception e){}
        
        int row=50;
        int start=(iPage-1)*row;
        int end=iPage*row;
        
        int totalPage=0;
        int totalRow=0;
        if(listSoMo==null||listSoMo.isEmpty()){
            SoMoRequest soMoRequest=new SoMoRequest();
            listSoMo=soMoRequest.parserSoMo();                        
        }                
        
        List<SoMo> list=null;
        
        if(dream!=null&&dream.length()==1){
            list=searchSoMo(dream);
        }else{
            list=searchSoMo(dream,number);
        }
        
        
        if(list!=null&&!list.isEmpty()){
            totalRow=list.size();
            if(totalRow%row>0){totalPage=(totalRow/row)+1;}
            else{totalPage=totalRow/row;}
        }
        mod.addObject("totalPage", totalPage);
        mod.addObject("start", start);
        mod.addObject("end", end);
        mod.addObject("dream", dream);
        mod.addObject("number", number);
        mod.addObject("page", iPage);
        mod.addObject("listSoMo", list);
        
        
        // seo
        String slogan="Sổ mơ";
        String title="Sổ mơ - Giải đáp giấc mơ - Ket qua xo so";
        String keywords="";
        String description="";                
                            
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        if("pc".equalsIgnoreCase(strMobile)){mod.setViewName("/somo");}
        else{mod.setViewName("/mobile/somo");}
        return mod;
    }
    
    
    private List<SoMo> searchSoMo(String dream,String number){
        
        List<SoMo> list=null;
        SoMo soMo=null;
        if(listSoMo==null||listSoMo.isEmpty()){return list;}
        if((dream==null||"".equals(dream))&&(number==null||"".equals(number))){return listSoMo;}        
        String strDream="no";
        if(dream!=null&&!"".equals(dream)){
            strDream=UTF8Tool.coDau2KoDau(dream).toLowerCase();            
        }
        
        //System.out.println("strDream====>>>"+strDream);
        
        for(int i=0;i<listSoMo.size();i++){            
            soMo=listSoMo.get(i);
            if(!"no".equals(strDream)){
                //System.out.println("soMo.getName()===>>"+soMo.getName()+"==="+soMo.getName_vn());
                if(strDream.equalsIgnoreCase(UTF8Tool.coDau2KoDau(soMo.getName().toLowerCase()))||strDream.contains(UTF8Tool.coDau2KoDau(soMo.getName().toLowerCase()))||UTF8Tool.coDau2KoDau(soMo.getName()).toLowerCase().contains(strDream)){
                    if(list==null){list=new ArrayList<SoMo>();}
                    list.add(soMo);
                }
            }
            
            if(number!=null&&!"".equals(number)){
                if((number.equalsIgnoreCase(soMo.getNumbers())||number.contains(soMo.getNumbers())||soMo.getNumbers().contains(number))){                    
                    if(list==null){list=new ArrayList<SoMo>();}
                    list.add(soMo);
                }
            }
        }
        
        return list;
    }
    
    private List<SoMo> searchSoMo(String dream){
        
        List<SoMo> list=null;
        SoMo soMo=null;
        if(listSoMo==null||listSoMo.isEmpty()){return list;}
        if((dream==null||"".equals(dream))){return listSoMo;}        
        String strDream="no";
        if(dream!=null&&!"".equals(dream)){
            strDream=UTF8Tool.coDau2KoDau(dream);            
        }
                
        for(int i=0;i<listSoMo.size();i++){            
            soMo=listSoMo.get(i);
            if(!"no".equals(strDream)){
                if(soMo.getName().startsWith(strDream.toUpperCase())||UTF8Tool.coDau2KoDau(soMo.getName_vn()).startsWith(strDream.toUpperCase())){
                    if(list==null){list=new ArrayList<SoMo>();}
                    list.add(soMo);
                }
            }                        
        }
        
        return list;
    }
}
