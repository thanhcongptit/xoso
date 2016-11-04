<%-- 
    Document   : index
    Created on : Nov 24, 2014, 10:08:16 AM
    Author     : iNET
--%>

<%@page import="inet.constant.Constants"%>
<%@page import="inet.login.google.Google"%>
<%@page import="com.javapapers.java.social.facebook.FBConnection"%>
<%@page import="inet.util.RequestUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        FBConnection fbConnection = new FBConnection();
        String redirectUrl = Constants.GOOGLE_REDIRECT_URL;        
        Google google = new Google(redirectUrl);
        String loginGoogle = google.getLoginUrl();
%>
<style>
    dd {
        margin-left: 0px;
    }
</style>
<div class="content">
    <h2 class="textHeading">Đăng Nhập</h2>
    <div class="plugin">
        <a href="<%=fbConnection.getFBAuthUrl()%>" class="fbLogin" tabindex="10"><span>Đăng nhập bằng Facebook</span></a>
        <dd style="margin-top: 4px;">
            <a href="<%= loginGoogle %>">
                <span data-gapiattached="true" class="googleLogin GoogleLogin JsOnly" tabindex="10" 
                      data-client-id="572333712218-17emelrtbjqlumrd326fbnrjb2ma4kh5.apps.googleusercontent.com" 
                      data-redirect-url="register/google?code=__CODE__&amp;csrf=Rx3ggbFSxkuJaIis">
                    <span>Đăng nhập bằng Google</span>
                </span>
            </a>
        </dd>
        <dd style="margin-top: 4px;">
            <a href="<%= request.getContextPath() %>/dang-nhap/id-inet">
                <span data-gapiattached="true" class="inetLogin GoogleLogin JsOnly" tabindex="10" 
                      data-client-id="572333712218-17emelrtbjqlumrd326fbnrjb2ma4kh5.apps.googleusercontent.com" 
                      data-redirect-url="register/google?code=__CODE__&amp;csrf=Rx3ggbFSxkuJaIis">
                    <span>Đăng nhập bằng iNET ID</span>
                </span>
            </a>
        </dd>
    </div>
</div>