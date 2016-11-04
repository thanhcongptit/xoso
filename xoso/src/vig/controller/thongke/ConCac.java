/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vig.controller.thongke;

import com.bean.Loto;
import com.thongke.ThongKeLoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import static vig.controller.thongke.BaseController.today;

/**
 *
 * @author 24h
 */
public class ConCac extends BaseController{
    
    private static HashMap<String,Object> hMap=null;
    private static String sDDMMYYYY=null;

    public ConCac() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if(hMap==null){hMap=new HashMap<String, Object>();}
        if(!today.equals(sDDMMYYYY)){hMap.clear();}
        sDDMMYYYY=today;
    }
    
    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        
        
        //mod.setViewName("/tk/thongkechuky");
        return mod;
    }
    
    private List<Loto> findDanLoto(List<Loto> list,String dan){
        List<Loto> result=null;
        for (StringTokenizer stringTokenizer = new StringTokenizer(dan,","); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            for (int i = 0; i < list.size(); i++) {
                if(token.equals(list.get(i).getLoto())){
                    if(result==null){result=new ArrayList<Loto>();}
                    result.add(list.get(i));
                }
            }
        }
        
        return  result;
    }
}
