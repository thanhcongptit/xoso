/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.Member;
import inet.constant.Constants;
import inet.model.MemberDAO;
import inet.util.RequestUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class MemberProfileController extends BaseController {
    
    public MemberProfileController() {
    } 
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        //change pass
        String action = RequestUtil.getString(request, "action", "");
        String msg = "";
        if("changePass".equals(action)){
            String oldPass = RequestUtil.getString(request, "oldPass", "");
            String newPass = RequestUtil.getString(request, "newPass", "");
            String reNewPass = RequestUtil.getString(request, "reNewPass", "");
            if("".equals(oldPass)){
                msg = "<span class='error'>Vui lòng điền mật khẩu cũ!</span>";
            }else if("".equals(newPass)){
                msg = "<span class='error'>Vui lòng điền mật khẩu mới!</span>";
            }else if(!newPass.equals(reNewPass)){
                msg = "<span class='error'>Xác nhận mật khẩu không chính xác!</span>";
            }else{
                Member memberCurrent = null;
                if (request.getSession() != null) {
                    memberCurrent = request.getSession().getAttribute(Constants.LOGIN_SESSION) != null ? (Member) request.getSession().getAttribute(Constants.LOGIN_SESSION) : null;
                }
                if(memberCurrent == null){
                    msg = "<span class='error'>Bạn chưa đăng nhập!</span>";
                }else{
                    MemberDAO memberDAO = new MemberDAO();
                    if(memberDAO.login(memberCurrent.getUsername(), oldPass) == null){
                        msg = "<span class='error'>Mật khẩu cũ không chính xác!</span>";
                    }else{
                        if(memberDAO.changePass(memberCurrent.getId(), newPass)){
                            msg = "<span class='ok'>Thay đổi mật khẩu thành công!</span>";
                        }else{
                            msg = "<span class='error'>Thay đổi mật khẩu thất bại!</span>";
                        }
                    }
                }
            }
        }
        
        // seo
        String p =  RequestUtil.getString(request, "p", "ttc");
        
        mod.addObject("p", p);        
        mod.addObject("msg", msg);        
        mod.addObject("Thông tin tài khoản");
        mod.setViewName("/profile");
        
        return mod;
    }
    
}
