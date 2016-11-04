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
                        <table width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ LOTO GAN</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">
                                    <form method="post">
                                    Từ ngày
                                    <input type="text" id="tungay" name="tungay" class="boxLich" value="${tungay}"/>
                                    Đến ngày
                                    <input type="text" id="denngay" name="denngay" class="boxLich" value="${denngay}"/>
                                    Biên độ
                                    <input type="number" name="biendo" value="${biendo}" style="line-height:24px;width: 50px"/>
                                    <input type="submit" value="Xem kết quả" style="height:24px;"/>
                                    </form>
                                </td>
                            </tr>                           
                            <c:if test="${listLoto!=null}">
                                <tr><td style="padding-top: 25px">Các cặp số loto chưa ra theo biên độ <span style="color: red"> ${biendo}</span> ngày trở lên:</td></tr>
                                <fmt:parseNumber type="number" var="ibiendo" value="${biendo}"/>
                                <c:forEach items="${listLoto}" var="loto"> 
                                <tr>                                                                           
                                    <c:if test="${loto.ngaychuave>=ibiendo}">
                                        <td style="padding-top: 5px">Cặp số <span style="color: red">${loto.loto}</span> ra ngày ${loto.ngayxuathiengannhat} đến ngày ${denngay} vẫn chưa ra là <span style="color: red">${loto.ngaychuave}</span> ngày</td>
                                    </c:if>                                                                                                                
                                </tr>    
                                </c:forEach>                                
                                <tr>
                                    <td style="padding-top: 15px">
                                        <table style="font-weight: bold;font-size: 15px" width="100%" class="bot" cellspacing="0" cellpadding="0">                                            
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="10%" class="bol">Cặp số</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="0" end="19">                                                
                                                <td width="4.5%" class="bol">${loto.loto}</td>                                                
                                                </c:forEach>                                                
                                            </tr>                 
                                            <tr style="height: 30px">
                                                <td width="10%" class="bol" >Ngày gan cực đại</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="0" end="19">                                                
                                                <td width="4.5%" class="bol">${loto.ganmax}</td>                                                
                                                </c:forEach>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 15px">
                                        <table style="font-weight: bold;font-size: 15px" width="100%" class="bot" cellspacing="0" cellpadding="0">                                            
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="10%" class="bol">Cặp số</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="20" end="39">                                                
                                                <td width="4.5%" class="bol">${loto.loto}</td>                                                
                                                </c:forEach>                                                
                                            </tr>                 
                                            <tr style="height: 30px">
                                                <td width="10%" class="bol" >Ngày gan cực đại</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="20" end="39">                                                
                                                <td width="4.5%" class="bol">${loto.ganmax}</td>                                                
                                                </c:forEach>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 15px">
                                        <table style="font-weight: bold;font-size: 15px" width="100%" class="bot" cellspacing="0" cellpadding="0">                                            
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="10%" class="bol">Cặp số</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="40" end="59">                                                
                                                <td width="4.5%" class="bol">${loto.loto}</td>                                                
                                                </c:forEach>                                                
                                            </tr>                 
                                            <tr style="height: 30px">
                                                <td width="10%" class="bol" >Ngày gan cực đại</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="40" end="59">                                                
                                                <td width="4.5%" class="bol">${loto.ganmax}</td>                                                
                                                </c:forEach>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 15px">
                                        <table style="font-weight: bold;font-size: 15px" width="100%" class="bot" cellspacing="0" cellpadding="0">                                            
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="10%" class="bol">Cặp số</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="60" end="79">                                                
                                                <td width="4.5%" class="bol">${loto.loto}</td>                                                
                                                </c:forEach>                                                
                                            </tr>                 
                                            <tr style="height: 30px">
                                                <td width="10%" class="bol" >Ngày gan cực đại</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="60" end="79">                                                
                                                <td width="4.5%" class="bol">${loto.ganmax}</td>                                                
                                                </c:forEach>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 15px">
                                        <table style="font-weight: bold;font-size: 15px" width="100%" class="bot" cellspacing="0" cellpadding="0">                                            
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="10%" class="bol">Cặp số</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="80" end="99">                                                
                                                <td width="4.5%" class="bol">${loto.loto}</td>                                                
                                                </c:forEach>                                                
                                            </tr>                 
                                            <tr style="height: 30px">
                                                <td width="10%" class="bol" >Ngày gan cực đại</td>
                                                <c:forEach items="${listLoto}" var="loto" begin="80" end="99">                                                
                                                <td width="4.5%" class="bol">${loto.ganmax}</td>                                                
                                                </c:forEach>
                                            </tr>
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
