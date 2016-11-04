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
                $('#denngay').datepicker({
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
                    <div id="content-center"> 
                        <table width="100%" border="1"  style="border-color: #aaa;font-weight: bold;font-size: 15px;" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ CHU KÌ DÀN LOTO</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">Nhập bộ số cần xem chu kì</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;padding-top: 5px">
                                    <form method="post">
                                        Từ ngày
                                        <input type="text" name="tungay" id="tungay" value="${tungay}" class="boxLich"/>
                                        Đến ngày
                                        <input type="text" name="denngay" id="denngay" value="${denngay}" class="boxLich"/>
                                        <br/><br/>
                                        <input type="text" name="danloto" value="${danloto}"/>                                        
                                        <input type="submit" value="Xem kết quả"/>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center;padding-top: 5px">Bạn có thể nhập nhiều bộ số (tối đa 10 bộ số, các bộ số cách nhau bởi dấu , ví dụ: 23,54,56,65)</td>
                            </tr>
                            <c:if test="${listLoto!=null}">
                                <fmt:parseNumber var="max" value="0" type="number"/>
                                <c:set var="lotomax" value=""/>
                                <c:forEach items="${listLoto}" var="loto">
                                    <c:if test="${loto.ganmax > max}">
                                        <fmt:parseNumber var="max" value="${loto.ganmax}" type="number"/>
                                        <c:set var="lotomax" value="${loto}"/>
                                    </c:if>                                    
                                </c:forEach>
                                <tr>
                                    <td style="padding-top: 5px;line-height: 20px">Dàn số: <span style="color: red"> ${danloto}</span></td>
                                </tr>
                                <tr>
                                    <td style="line-height: 20px">Ngưỡng cực đại xuất hiện dàn số là: <span style="color: red">${max}</span> ngày tính cả ngày về (${lotomax.ngaybatdauganmax} đến ngày ${lotomax.ngayketthucganmax})</td>
                                </tr>                                
                            </c:if>
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
    </body>
</html>
