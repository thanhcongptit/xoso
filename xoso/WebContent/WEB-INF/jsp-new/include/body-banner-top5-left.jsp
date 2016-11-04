<%-- 
    Document   : body-banner-top5-left
    Created on : 14-Jan-2016, 09:16:04
    Author     : 24h
--%>

<%@page import="inet.listener.BannerCache"%>
<%@page import="inet.bean.Banner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="banner5" style="text-align: center;">
    <%
        if(request.getRequestURI().endsWith("/index.jsp")){
            Banner banner = BannerCache.findBanner("index", Banner.POSITION_LEFT);
            if(banner != null) out.print(banner.getCode());
        }else{
            Banner banner = BannerCache.findBanner("other", Banner.POSITION_LEFT);
            if(banner != null) out.print(banner.getCode());
        }
    %>
</div>