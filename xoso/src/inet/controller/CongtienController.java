/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.servlet.ModelAndView;

import com.common.BaokimSCard;
import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.Member;
import inet.constant.Constants;
import inet.model.MemberDAO;

/**
 *
 * @author 24h
 */
public class CongtienController extends BaseController {
    
    public CongtienController() {
    }
    
    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        mod.setViewName("/congtien");
        //kiem tra dang nhap
        Member memberLogin = null;
        
        if (request.getSession() != null) {
            memberLogin = request.getSession().getAttribute(Constants.LOGIN_SESSION) != null ? (Member) request.getSession().getAttribute(Constants.LOGIN_SESSION) : null;
        }
        
        if(memberLogin != null) {
        	BaokimSCard bksc = new BaokimSCard();
        	String selectMethod = request.getParameter("select_method");
        	String txtSoSeri = request.getParameter("SoSeri");
        	String txtSoPin = request.getParameter("SoPin");
        	
        	if(CommonUtil.isEmptyString(selectMethod) 
        			|| CommonUtil.isEmptyString(txtSoSeri)
        			|| CommonUtil.isEmptyString(txtSoPin)) {
        		mod.setViewName("/napthe");
        		return mod;
        	}
        		
    		String[] rs = bksc.send(selectMethod, txtSoSeri, txtSoPin);

    		String responseCode = rs[0];
    		String responseBody = rs[1];

    		// Xử lý dữ liệu trả về dạng json
    		try {
    			JSONParser parser = new JSONParser();
    			Object obj;
    			obj = parser.parse(responseBody);

    			JSONObject jsonObject = (JSONObject) obj;
    			
    			if (!responseCode.equals("200")) {
    				// TODO: xử lý khi Bảo kim trả về lỗi
    				System.out.println("response code: " + responseCode);
    				System.out.println("transaction_id: " + jsonObject.get("transaction_id"));
    				System.out.println("error message: " + jsonObject.get("errorMessage"));
    				String message = jsonObject.get("errorMessage") + "";
    				
    				mod.addObject("response", false);
    				mod.addObject("message", message);
    				
    			} else {
    				// TODO: xử lý khi Bảo kim trả về thành công
    				System.out.println("response code: " + responseCode);
    				System.out.println("transaction_id: " + jsonObject.get("transaction_id"));
    				System.out.println("amount: " + jsonObject.get("amount"));
    				
    				int amount = Integer.parseInt(jsonObject.get("amount") + "");
    				String message = "";
    				int xu = 0;
    				message = "Bạn vừa được cộng " +amount+ " xu vào tài khoản";
    				mod.addObject("response", true);
    				mod.addObject("message", message);
    				
    				xu = memberLogin.getXu() + amount;
    				new MemberDAO().updateXu(memberLogin.getId(), xu);
    				memberLogin.setXu(xu);
    				request.getSession().setAttribute(Constants.LOGIN_SESSION, memberLogin);
    				
    			}
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
        return mod;
    }

}
