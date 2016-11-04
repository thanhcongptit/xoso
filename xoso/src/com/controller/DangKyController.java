/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.MemberDAO;
import inet.util.RequestUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
public class DangKyController extends BaseController {

    private final MemberDAO memberDAO = new MemberDAO();
    
    public DangKyController(){}    
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        String message = "";
        String action = RequestUtil.getString(request, "action", "");
        String fullname = RequestUtil.getString(request, "fullname", "");
        String email = RequestUtil.getString(request, "email", "");
        String mobile = RequestUtil.getString(request, "mobile", "");
        String password = RequestUtil.getString(request, "password", "");
        
        if("reg".equals(action)){
            if(memberDAO.insertRow(fullname, email, mobile, 1, null, null, password)){
                
            }else{
                message = "Lỗi đăng ký. Vui lòng thử lại sau";
            }
        }
        
        mod.addObject("message", message);
        return mod;
    }
}
