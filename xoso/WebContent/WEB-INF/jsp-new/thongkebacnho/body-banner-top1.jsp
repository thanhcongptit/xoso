<%-- 
    Document   : body-banner-top1
    Created on : 14-Jan-2016, 08:55:49
    Author     : 24h
--%>

<%@page import="inet.listener.BannerCache"%>
<%@page import="inet.bean.Banner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="banner-top1">
    <div class="banner">
        <%
            if(request.getRequestURI().endsWith("/index.jsp")){
                Banner banner = BannerCache.findBanner("index", Banner.POSITION_TOP_1);
                if(banner != null) out.print(banner.getCode());
            }else{
                Banner banner = BannerCache.findBanner("other", Banner.POSITION_TOP_1);
                if(banner != null) out.print(banner.getCode());
            }
        %>
    </div>
    <!--
    <div class="banner">
        <%
            if(request.getRequestURI().endsWith("/index.jsp")){
                Banner banner = BannerCache.findBanner("index", Banner.POSITION_TOP_2);
                if(banner != null) out.print(banner.getCode());
            }else{
                Banner banner = BannerCache.findBanner("other", Banner.POSITION_TOP_2);
                if(banner != null) out.print(banner.getCode());
            }
        %>
    </div>
    -->
</div>
