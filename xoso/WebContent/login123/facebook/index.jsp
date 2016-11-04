<%-- 
    Document   : index
    Created on : Dec 9, 2014, 3:42:27 PM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.bean.Member"%>
<%@page import="inet.model.MemberDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.constant.Constants"%>
<%@page import="java.util.Map"%>
<%@page import="com.javapapers.java.social.facebook.FBGraph"%>
<%@page import="com.javapapers.java.social.facebook.FBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    long serialVersionUID = 1L;
    String code="";
    code = request.getParameter("code");
    if (code == null || code.equals("")) {
        response.sendRedirect(request.getContextPath());
    }else{
        MemberDAO memberDAO = new MemberDAO();
        Member member = null;
        if (session != null) {
            member = session.getAttribute(Constants.LOGIN_SESSION) != null ? (Member) session.getAttribute(Constants.LOGIN_SESSION) : null;
        }
        FBConnection fbConnection = new FBConnection();
        String accessToken = fbConnection.getAccessToken(code);

        FBGraph fbGraph = new FBGraph(accessToken);
        String graph = fbGraph.getFBGraph();
        Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
        if(member == null){
            String facebookId = fbProfileData.get("id");
            if(facebookId != null){
                member = memberDAO.getRowByFacebookId(facebookId);                
                if (member == null) {
                    member = new Member();
                    member.setFullname(fbProfileData.get("name"));
                    member.setEmail(fbProfileData.get("email"));
                    member.setFacebookId(facebookId);
                    member.setStatus(Member.STATUS_ACTIVE);
                    if (memberDAO.create(member)) {
                        member = memberDAO.getRowByFacebookId(facebookId);
                    }
                }
                session.setAttribute(Constants.LOGIN_SESSION, member);
                session.setMaxInactiveInterval(24 * 60 * 60);//Set session 1 ngay

                response.sendRedirect(request.getContextPath() + "/trang-chu.html");
                return;
            }else{
                response.sendRedirect(request.getContextPath());
                return;
            }
        }else{
            response.sendRedirect(request.getContextPath());
            return;
        }
    }
%>
