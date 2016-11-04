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
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ ĐẦU ĐUÔI LOTO</h2></td>
                            </tr>
                            
                            <tr>
                                <td style="text-align: center;padding-top: 5px">
                                    <form method="post">
                                        Biên độ ngày
                                        <input type="text" id="tungay" name="tungay" class="boxLich" value="${tungay}"/>
                                        Khoảng ngày muốn xem
                                        <input type="number" name="biendo" value="${biendo}" style="height: 24px"/>
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
                                                <td class="bol" colspan="11">1.THỐNG KÊ ĐẦU LOTO CỦA XỔ SỐ ${tinh}</td>
                                            </tr>
                                            <tr style="background-color: #e0be7e;height: 30px">
                                                <td class="bol" colspan="11">Thống kê trong ${biendo} ngày gần đây nhất.</td>
                                            </tr>
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol">Ngày/Đầu</td>
                                                <c:forEach begin="0" end="9" var="i"><td class="bol">${i}</td></c:forEach>
                                            </tr>
                                            <c:forEach items="${listDau}" var="daus">
                                                <tr style="height: 30px">
                                                 <c:forEach items="${daus}" var="dau" varStatus="status"> 
                                                     <c:if test="${status.index==0}"><td class="bol">${dau.ngay}</td></c:if>                                                        
                                                        <td class="bol">${dau.lanve} lần</td>                                                        
                                                </c:forEach>  
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
                                                <td class="bol" colspan="11">1.THỐNG KÊ ĐUÔI LOTO CỦA XỔ SỐ MIỀN BẮC</td>
                                            </tr>
                                            <tr style="background-color: #e0be7e;height: 30px">
                                                <td class="bol" colspan="11">Thống kê trong ${biendo} ngày gần đây nhất.</td>
                                            </tr>
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td class="bol">Ngày/Đầu</td>
                                                <c:forEach begin="0" end="9" var="i"><td class="bol">${i}</td></c:forEach>
                                            </tr>
                                            <c:forEach items="${listDuoi}" var="duois">
                                                <tr style="height: 30px">
                                                 <c:forEach items="${duois}" var="duoi" varStatus="status"> 
                                                     <c:if test="${status.index==0}"><td class="bol">${duoi.ngay}</td></c:if>                                                        
                                                        <td class="bol">${duoi.lanve} lần</td>                                                        
                                                </c:forEach>  
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
