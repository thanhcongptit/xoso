/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.Member;
import inet.constant.Constants;
import inet.model.MemberDAO;
import inet.util.EmailValidator;
import inet.util.Md5;
import inet.util.RequestUtil;
import inet.util.StringTool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 24h
 */
public class DangkyController extends BaseController {

    public DangkyController() {
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
        
        String action = RequestUtil.getString(request, "action", "");
        String username = "";        
        String email = "";
        String reEmail = "";
        String msg = "";
        if("register".equals(action)){
            username = RequestUtil.getString(request, "username", "");
            String password = RequestUtil.getString(request, "password", "");
            String rePassword = RequestUtil.getString(request, "rePassword", "");
            email = RequestUtil.getString(request, "email", "");
            reEmail = RequestUtil.getString(request, "reEmail", "");
            
            if("".equals(username)){
                msg = "Vui lòng điền tên đăng nhập!";
            }else if("".equals(password)){
                msg = "Vui lòng điền mật khẩu!";
            }else if(!password.equals(rePassword)){
                msg = "Xác nhận mật khẩu không chính xác!";
            }else if("".equals(email)){
                msg = "Vui lòng điền email!";
            }else if("".equals(reEmail)){
                msg = "Xác nhận email không chính xác!";
            }else if(!EmailValidator.validate(email)){
                msg = "Email không đúng định dạng!";
            }else{
                MemberDAO memberDAO = new MemberDAO();
                //kiem tra xem ten dang nhap ton tai chua
                //kiem tra xem email ton tai chua
                if(memberDAO.checkExistUsername(username)) msg = "Tên đăng nhập này đã có người sử dụng!";
                else if(memberDAO.checkExistEmail(email)) msg = "Email đã tồn tại trong hệ thống!";
                else{
                    //on roi thi insert
                    Member member = new Member();
                    member.setUsername(username);
                    member.setPassword(Md5.Hash(password));
                    member.setEmail(email);
                    member.setStatus(Member.STATUS_ACTIVE);
                    member.setFullname(username);
                    if(memberDAO.create(member)){
                        member = memberDAO.getRowByUsername(username);
                        request.getSession().setAttribute(Constants.LOGIN_SESSION, member);
                        mod.addObject("success", true);
                    }else msg = "Tạo tài khoản không thành công!";
                }                                
            }
        }
        
        mod.addObject("title", "Đăng ký");
        mod.addObject("username", username);
        mod.addObject("email", email);
        mod.addObject("reEmail", reEmail);
        mod.addObject("msg", msg);
        
        mod.setViewName("/dang-ky");
        return mod;
    }
}
