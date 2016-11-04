<%-- 
    Document   : index
    Created on : 14-Jan-2016, 08:22:23
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header.jsp" %>        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/thong-ke.css">
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
                        <%@include file="include/body-calendar-left.jsp" %>
                        <%@include file="include/body-lichmothuong-left.jsp" %>
                        <%@include file="include/body-xs3mien-left.jsp" %>
                        <%@include file="include/body-thongkecoban-left.jsp" %>
                        <%@include file="include/body-banner-top5-left.jsp" %>                      
                    </div>					
                    <div id="content-center">
                        <div id="content-tructiep">
                            <table style="width: 100%">						
                                <tr>
                                    <td colspan="2">
                                        <div class="thong-ke">
                                            <div class="boxlist">
                                                    <div id="kqxsmb" class="cat_news">
                                                        <h3 style="border-bottom: 1px solid #aeaeae;margin-bottom: 20px">
                                                            Đăng ký tài khoản mới                         
                                                        </h3>
                                                        <div>
                                                            <div style="color: red">${msg}</div>
                                                            <c:if test="${success eq null}">
                                                                <form method="post">
                                                                    <table>
                                                                        <tr>
                                                                            <td>Tên đăng nhập:</td>
                                                                            <td><input name="username" value="${username}"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Mật khẩu:</td>
                                                                            <td><input type="password" name="password"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Nhập lại Mật khẩu:</td>
                                                                            <td><input type="password" name="rePassword"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Email:</td>
                                                                            <td><input type="text" name="email" value="${email}"/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>Nhập lại Email:</td>
                                                                            <td><input type="text" name="reEmail" ${reEmail}/></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td></td>
                                                                            <td>
                                                                                <input type="hidden" name="action" value="register"/>
                                                                                <button type="submit" class="btnGreen">Đăng ký</button>                                                                            
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </form>
                                                            </c:if>
                                                            <c:if test="${success ne null}">
                                                                <p style="color: green">Bạn đã đăng ký tài khoản thành công! Click <a href="<%= request.getContextPath() %>/trang-chu.html">vào đây</a> để trở lại trang chủ!</p>
                                                            </c:if>        
                                                        </div>
                                                    </div>
                                            </div>
                                        </div>								
                                    </td>
                                </tr>						
                            </table>					
                        </div>
                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
