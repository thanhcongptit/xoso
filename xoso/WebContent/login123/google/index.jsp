<%-- 
    Document   : index
    Created on : Dec 9, 2014, 5:13:32 PM
    Author     : Designer Nguyễn
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="inet.constant.Constants"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.login.google.GoogleUser"%>
<%@page import="inet.login.google.Google"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="userExtDao" class="inet.model.MemberDAO" scope="session"/>
<%
    //login google
    String redirectUrl = Constants.GOOGLE_REDIRECT_URL;
    Google google = new Google(redirectUrl);
    //GoogleUser ggUser = google.parseGoogleResponse(request);
     GoogleUser ggUser = google.parseGoogleResponse(request, "");
    if (ggUser != null) {
        System.out.print("ggUser.getName()= " + ggUser.getName());
        System.out.print("ggUser.getId()= " + ggUser.getId());
        System.out.print("ggUser.getEmail()= " + ggUser.getEmail());
    } else {
        System.out.println("Có lỗi trong khi nhận thông tin từ Google, bạn vui lòng thử lại!");
    }
    if (ggUser == null) {
        response.sendRedirect(request.getContextPath() + "/");
    } else {
        Member userExtOnl = null;
        if (session != null) {
            userExtOnl = session.getAttribute(Constants.LOGIN_SESSION) != null ? (Member) session.getAttribute(Constants.LOGIN_SESSION) : null;
        }
        if (userExtOnl == null) {
            String googleId = null;
            try {
                googleId = ggUser.getId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (googleId != null) {
                userExtOnl = userExtDao.getRowByGoogleId(googleId);
                if (userExtOnl == null) {
                    userExtOnl = new Member();
                    userExtOnl.setFullname(ggUser.getName());
                    userExtOnl.setEmail(ggUser.getEmail());
                    userExtOnl.setGoogleId(googleId);
                    userExtOnl.setStatus(Member.STATUS_ACTIVE);
                    if (userExtDao.create(userExtOnl)) {
                        userExtOnl = userExtDao.getRowByGoogleId(googleId);
                    }
                }
                session.setAttribute(Constants.LOGIN_SESSION, userExtOnl);
                session.setMaxInactiveInterval(24 * 60 * 60);//Set session 1 ngay

                response.sendRedirect(request.getContextPath() + "/trang-chu.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
%>