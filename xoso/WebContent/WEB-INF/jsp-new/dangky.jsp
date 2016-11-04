<%-- 
    Document   : dangky
    Created on : Jan 24, 2016, 11:06:56 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header.jsp" %>
        <script type="text/javascript">
            function checkInput(){
                if(frmReg.fullname.value == ''){
                    alert('Vui long nhap ho ten');
                    return false;
                }else if(frmReg.email.value == ''){
                    alert('Vui long nhap email');
                    return false;
                }else if(frmReg.mobile.value == ''){
                    alert('Vui long nhap mobile');
                    return false;
                }else if(frmReg.password.value == ''){
                    alert('Vui long nhap mat khau');
                    return false;
                }
                return true;
            }
        </script>
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
                        <table width="100%" border="1" style="border-color: #e8e8e8" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    <h2 class="h1title" style="text-align: center">
                                        Đăng ký tài khoản
                                    </h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="post" name="frmReg" onsubmit="return checkInput();">
                                        <input name="action" type="hidden" value="reg" />
                                        <div style="color: red;">${message}</div>
                                        <div class="tc space">
                                            <table width="100%" border="0">
                                                <tr height="30px">
                                                    <td>Họ tên (*)</td>
                                                    <td>
                                                        <input type="text" name="fullname" />
                                                    </td>
                                                </tr>
                                                <tr height="30px">
                                                    <td>Email (*)</td>
                                                    <td>
                                                        <input type="text" name="email" />
                                                    </td>
                                                </tr>
                                                <tr height="30px">
                                                    <td>Mobile (*)</td>
                                                    <td>
                                                        <input type="text" name="mobile" />
                                                    </td>
                                                </tr>
                                                <tr height="30px">
                                                    <td>Mật khẩu (*)</td>
                                                    <td>
                                                        <input type="text" name="password" />
                                                    </td>
                                                </tr>
                                                <tr height="30px">
                                                    <td colspan="2">
                                                        <input type="submit" value="Đăng ký" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
