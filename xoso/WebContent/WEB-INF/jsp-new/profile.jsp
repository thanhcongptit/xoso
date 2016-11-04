<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            .error{color:red}
            .ok{color:green}
            #content-center{font-size: 14px;}
            .bold{font-weight: bold}
            .menua{color: blue}
            #ttc,#dmk,#tkl{margin-top: 10px;}
            .btnDMK{
                    font-size: 12px;
                    background-color: #157ee8;
                    color: white;
                    font-weight: bold;
                    font-family: arial;
                    border: #157ee8 1px solid;
                    padding: 2px;
                    cursor: pointer;
                    cursor: hand;
                    border-radius: 3px;
                    -moz-border-radius: 3px;
                    -webkit-border-radius: 3px;
            }
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
                    <div id="content-center"> 
                        <table width="100%" border="1" style="border-color: #aaa;background: white;" cellspacing="0" cellpadding="0">
                            <tr>
                                <td style="padding: 0px;"><h2 class="h1title" style="text-align: center">Thông tin tài khoản</h2></td>
                            </tr>
                            <c:if test="${memberLogin ne null}">
                                <tr>
                                    <td style="padding: 10px;text-align: left">
                                        <a class="menua <c:if test="${p eq 'ttc'}"><c:out value="bold"/></c:if>" href="${pageContext.servletContext.contextPath}/profile.htm">Thông tin chung</a> |
                                        <c:if test="${(memberLogin.facebookId eq null || memberLogin.facebookId eq '') && (memberLogin.googleId eq null || memberLogin.googleId eq '')}">
                                            <a class="menua <c:if test="${p eq 'dmk'}"><c:out value="bold"/></c:if>" href="${pageContext.servletContext.contextPath}/profile.htm?p=dmk">Đổi mật khẩu</a> |
                                        </c:if>
                                        <a class="menua <c:if test="${p eq 'tkl'}"><c:out value="bold"/></c:if>" href="${pageContext.servletContext.contextPath}/profile.htm?p=tkl">Tài khoản Lotto</a>
                                        <c:if test="${p eq 'ttc'}">
                                            <div id="ttc">
                                                <table>
                                                    <tr>
                                                        <td class="bold">Facebook Id:</td>
                                                        <td>${memberLogin.facebookId}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bold">Google Id:</td>
                                                        <td>${memberLogin.googleId}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bold">Tên truy cập:</td>
                                                        <td>${memberLogin.username}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bold">Họ tên:</td>
                                                        <td>${memberLogin.fullname}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bold">Email:</td>
                                                        <td>${memberLogin.email}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bold">Ngày tham gia:</td>
                                                        <td>${memberLogin.genDate}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </c:if>
                                        <c:if test="${p eq 'dmk' && (memberLogin.facebookId eq null || memberLogin.facebookId eq '') && (memberLogin.googleId eq null || memberLogin.googleId eq '')}">
                                            <div id="dmk">
                                                <div>${msg}</div>
                                                <form method="post">
                                                    <table>
                                                        <tr>
                                                            <td>Mật khẩu cũ</td>
                                                            <td><input type="password" name="oldPass"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Mật khẩu mới</td>
                                                            <td><input type="password" name="newPass"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Nhập lại mật khẩu mới</td>
                                                            <td><input type="password" name="reNewPass"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td>
                                                                <input type="hidden" name="action" value="changePass"/>
                                                                <button class="btnDMK" type="submit">Đổi mật khẩu</button>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form>
                                            </div>
                                        </c:if>
                                        <c:if test="${p eq 'tkl'}">
                                            <div id="tkl">
                                                <b>Tài khoản của bạn là: <span style="color: #117e0c;">${memberLogin.money}</span> k</b>
                                            </div>
                                        </c:if>
                                    </td>
                                </tr> 
                            </c:if>
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
