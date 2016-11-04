<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>
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
                    <div id="content-center"> 
                        <table width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">CHU KÌ DÀI NHẤT BỘ SỐ KHÔNG RA</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">Nhập bộ số cần xem chu kì</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;padding-top: 5px">
                                    <form method="post">
                                        <input type="text" name="danloto" value="${danloto}"/><input type="submit" value="Xem kết quả"/>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;padding-top: 5px">Bạn có thể nhập nhiều bộ số (tối đa 10 bộ số, các bộ số cách nhau bởi dấu , ví dụ: 23,54,56,65)</td>
                            </tr>
                            <c:if test="${listLoto!=null}">
                                <tr>
                                    <td style="padding-top: 5px">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="10%" class="bol">Bộ số</td>
                                                <td width="25%" class="bol">Chu kỳ dài nhất</td>
                                                <td width="65%" class="bol">Khoảng thời gian của chu kỳ</td>
                                            </tr>
                                            <c:forEach items="${listLoto}" var="loto">
                                                <tr style="height: 30px">
                                                    <td class="bol">${loto.loto}</td>
                                                    <td class="bol">${loto.ganmax}</td>
                                                    <td class="bol">
                                                        Từ ngày ${loto.ngaybatdauganmax} đến ngày ${loto.ngayketthucganmax}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
    </body>
</html>
