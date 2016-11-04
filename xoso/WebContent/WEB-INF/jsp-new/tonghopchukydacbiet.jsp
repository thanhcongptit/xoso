<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <div id="content-center"> 
                        <table width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">TỔNG HỢP CHU KÌ ĐẶC BIỆT</h2></td>
                            </tr>
                            
                            <tr>
                                <td style="text-align: center;padding-top: 5px">
                                    <form method="post">
                                        Biên độ ngày
                                        <input type="text" id="tungay" name="tungay" class="boxLich" value="${tungay}"/>  
                                        <br/><br/>
                                        <select name="code">
                                        <c:forEach items="${listLotteryCompany}" var="company">
                                            <c:if test="${code==company.code}"><c:set var="tinh" value="${company.company}"/></c:if>
                                             <option value="${company.code}" <c:if test="${code==company.code}">selected</c:if> >${company.company}</option>
                                        </c:forEach>
                                        </select>
                                        <input type="submit" value="Xem kết quả"/>
                                    </form>
                                </td>
                            </tr>
                            
                            <c:if test="${listDau!=null}">
                                <tr>
                                    <td style="padding-top: 5px">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol" colspan="11">1. CHU KỲ ĐẦU</td>
                                            </tr>
                                            <tr style="background-color: #e0be7e;height: 30px">
                                                <td class="bol" colspan="11">Thống kê chu kỳ gần nhất của các số đầu trong 2 số cuối của giải đặc biệt của xổ số ${tinh} tính đến ngày ${tungay}</td>
                                            </tr>
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol">Chu kỳ đầu</td>
                                                <td class="bol">Max đại</td>
                                            </tr>
                                            <c:forEach items="${listDau}" var="loto">
                                                <tr style="height: 30px">
                                                     <td class="bol">
                                                         Đầu ${loto.loto} ra ngày ${loto.ngayxuathiengannhat} đến nay vẫn chưa ra là ${loto.ngaychuave} ngày
                                                     </td>
                                                     <td class="bol">${loto.ganmax} ngày</td>                                                        
                                                 </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${listDuoi!=null}">
                                <tr>
                                    <td style="padding-top: 5px">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol" colspan="11">2. CHU KỲ ĐUÔI</td>
                                            </tr>
                                            <tr style="background-color: #e0be7e;height: 30px">
                                                <td class="bol" colspan="11">Thống kê chu kỳ gần nhất của các số đuôi trong 2 số cuối của giải đặc biệt của xổ số Miền Bắc tính đến ngày ${tungay}</td>
                                            </tr>
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol">Chu kỳ đuôi</td>
                                                <td class="bol">Max đại</td>
                                            </tr>
                                            <c:forEach items="${listDuoi}" var="loto">
                                                <tr style="height: 30px">
                                                     <td class="bol">
                                                         Đầu ${loto.loto} ra ngày ${loto.ngayxuathiengannhat} đến nay vẫn chưa ra là ${loto.ngaychuave} ngày
                                                     </td>
                                                     <td class="bol">${loto.ganmax} ngày</td>                                                        
                                                 </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>    
                            <c:if test="${listTong!=null}">
                                <tr>
                                    <td style="padding-top: 5px">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol" colspan="11">3. CHU KỲ TỔNG</td>
                                            </tr>
                                            <tr style="background-color: #e0be7e;height: 30px">
                                                <td class="bol" colspan="11">Thống kê chu kỳ gần nhất của các số tổng trong 2 số cuối của giải đặc biệt của xổ số Miền Bắc tính đến ngày ${tungay}</td>
                                            </tr>
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol">Chu kỳ tổng</td>
                                                <td class="bol">Max đại</td>
                                            </tr>
                                            <c:forEach items="${listTong}" var="loto">
                                                <tr style="height: 30px">
                                                     <td class="bol">
                                                         Đầu ${loto.loto} ra ngày ${loto.ngayxuathiengannhat} đến nay vẫn chưa ra là ${loto.ngaychuave} ngày
                                                     </td>
                                                     <td class="bol">${loto.ganmax} ngày</td>                                                        
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
