/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vig.controller.thongke;

import com.utils.LotoUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HanhDung
 */
public class GhepXienController extends BaseController {
    
    public GhepXienController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        String danloto=request.getParameter("danloto");
        String opt=request.getParameter("opt");
        if(danloto!=null&&!"".equals(danloto)){
            List<String> list=LotoUtils.ghepXien(danloto, Integer.parseInt(opt));
            mod.addObject("list", list);
            mod.addObject("danloto", danloto);
            mod.addObject("opt", opt);
        }
        
        return mod;
    }
        
}