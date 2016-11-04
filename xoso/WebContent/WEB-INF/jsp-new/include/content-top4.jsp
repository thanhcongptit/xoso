<%-- 
    Document   : content
    Created on : 14-Jan-2016, 09:23:25
    Author     : 24h
--%>

<%@page import="inet.listener.BannerCache"%>
<%@page import="inet.bean.Banner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="banner-top4">
    <%
        if(request.getRequestURI().endsWith("/index.jsp")){
            Banner banner = BannerCache.findBanner("index", Banner.POSITION_CONTENT);
            if(banner != null) out.print(banner.getCode());
        }else{
            Banner banner = BannerCache.findBanner("other", Banner.POSITION_CONTENT);
            if(banner != null) out.print(banner.getCode());
        }
    %>
</div>
