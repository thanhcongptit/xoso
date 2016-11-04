<%-- 
    Document   : content
    Created on : 14-Jan-2016, 09:23:25
    Author     : 24h
--%>

<%@page import="java.util.List"%>
<%@page import="inet.listener.CuphapSMSCache"%>
<%@page import="com.bean.SmsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="sms-content">
    <%
        List<SmsDTO> cuphaps = CuphapSMSCache.getCuPhap();
        
        if(cuphaps != null && cuphaps.size() > 0) {
            for(SmsDTO cuphap : cuphaps) {
                out.print("<b>Soạn theo cú pháp <span class='short_code'>" + cuphap.getType() + "</span>"
                        + " gửi <span class='short_code'>" + cuphap.getShortCode() + " </span> để " 
                        + cuphap.getContent() + "<b> <br/>");
            }
        }
    %>
</div>
