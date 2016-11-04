<%-- 
    Document   : daucam
    Created on : Apr 23, 2016, 2:47:28 PM
    Author     : conglt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>
        <link href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
        <script>
            $(function() {
                $('#tungay').datepicker({
                    inline: true,
                    showOtherMonths: true,
                    dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                });
                                
            });
        </script>
        <style>
            #content-center{font-size: 14px;}                
        </style>
    </head>
    <body>		
        <%@include file="include/body-header.jsp" %>
        <div id="wapper">
            <div id="main_container">
                <%@include file="include/body-banner-top1.jsp" %>
                <div id="content">
                    <%@include file="include/body-loto-online.jsp" %>
                    <div id="sidebar-right">										
                        <%@include file="include/body-list-news.jsp" %>
                        <%@include file="include/body-tkbacnho-right.jsp" %>
                        <%@include file="include/body-thongkenhanh-right.jsp" %>
                        <%@include file="include/body-banner-top5-right.jsp" %>
                    </div>				
                    <div id="sidebar-left">
                        <%@include file="include/body-lichmothuong-left.jsp" %>
                        <%@include file="include/body-xs3mien-left.jsp" %>
                        <%@include file="include/body-thongkecoban-left.jsp" %>
                        <%@include file="include/body-banner-top5-left.jsp" %>                      
                    </div>					
                    <div id="content-center" style="text-align: center"> 
                        <table width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ BẠC NHỚ</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Lotto về theo đuôi câm</h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/duoi_cam_loto.htm">Lotto về theo đuôi câm</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/duoi_cam_chinh.htm">Lotto chính về theo đuôi câm</a>
                                    </div>
                              

                                </td>
                            </tr>                                                         
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
    </body>
</html>
