/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.util.HttpURLRequest;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class OleController extends AbstractController {
    private static HashMap<String,String> hOle=new HashMap<String, String>();
    private static long timeLoad=0;
    
    public OleController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=new ModelAndView("/ajax/ole");
        String font=request.getParameter("f");
        long currentTime=System.currentTimeMillis();
        try{
            String string="";
            //System.out.println("currentTime-timeLoad=="+(currentTime-timeLoad));
            if(currentTime-timeLoad>(60*60*1000)){
                timeLoad=currentTime;
                string=HttpURLRequest.sendGet("http://ole.vn/widgets/Codenews?all=true&width=1&limit=5&color=5&font="+font);
                if(string!=null&&!"".equals(string)){
                    string=string.replace("document.write('", "").replace("');", "");
                    hOle.put("ole", string);
                }
            }else if(!hOle.containsKey("ole")){                
                string=HttpURLRequest.sendGet("http://ole.vn/widgets/Codenews?all=true&width=1&limit=5&color=5&font="+font);
                if(string!=null&&!"".equals(string)){
                    string=string.replace("document.write('", "").replace("');", "");
                    hOle.put("ole", string);
                }
            }else{
                string=hOle.get("ole");
            }
            
            mod.addObject("string", string);
            
        }catch(Exception e){}
        return mod;
    }
    
}
