<%-- 
    Document   : body-banner-top5-right
    Created on : 14-Jan-2016, 09:08:34
    Author     : 24h
--%>

<%@page import="inet.listener.SystemConfigCache"%>
<%@page import="inet.listener.BannerCache"%>
<%@page import="inet.bean.Banner"%>
<%@page import="inet.bean.Banner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="bannerTop5">
    <%
        if(request.getRequestURI().endsWith("/index.jsp")){
            Banner banner = BannerCache.findBanner("index", Banner.POSITION_RIGHT);
            if(banner != null) out.print(banner.getCode());
        }else{
            Banner banner = BannerCache.findBanner("other", Banner.POSITION_RIGHT);
            if(banner != null) out.print(banner.getCode());
        }
    %>
</div>
<div id="box-like-page" style="margin-top: 10px">
    <div class="fb-page" data-href="<%= SystemConfigCache.findConfig(SystemConfigCache.FACEBOOK_CONFIG) %>" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"></div>
</div>
