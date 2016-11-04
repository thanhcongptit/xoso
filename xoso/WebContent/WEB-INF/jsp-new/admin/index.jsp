<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SOICAUPRO.COM CMS</title>
        <%@include file="include/css-lib.jsp" %>
        <%@include file="include/js-lib.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="include/header.jsp" %>
            <div class="content">
                <div class="formWrapper">
                    <h1>WELCOME TO SOICAUPRO.COM CMS</h1>
                    
                    
                    <div class="form1"></div>
                </div><!--end form-->                
                <div class="ndung">
                    <div class="txtRight" style="margin-bottom: 10px">
                    
                    
                    </div>
                </div>
                <h2><a href="${pageContext.servletContext.contextPath}/admin/quan-ly-link-quangcao.htm">Quản lý link quảng cáo</a></h2>
                <h2><a href="${pageContext.servletContext.contextPath}/admin/quan-ly-du-doan-xoso.htm">Quản lý dự đoán xổ số</a></h2>
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="include/footer.jsp" %>
    </body>
</html>