<%-- 
    Document   : thongketansuatboso
    Created on : Feb 27, 2016, 4:24:29 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/thong-ke.css" />
        <link href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css" rel="stylesheet" type="text/css" />
        <!--<script src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>-->
        <script>
            $(function () {
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
                        <table width="100%" border="1" style="border-color: #e8e8e8" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    <h2 class="h1title" style="text-align: center">
                                        Thống kê tần suất của các bộ số
                                    </h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="post">
                                        <div class="tc space">
                                            <p class="sred space"></p>
                                            <p class="sgreen space">Chọn tỉnh - Thành phố: 
                                                <select name="company">
                                                    <c:forEach items="${listLotteryCompany}" var="company">
                                                        <c:if test="${code == company.code}">
                                                            <c:set var="tinh" value="${company.company}"/>
                                                        </c:if>
                                                        <option value="${company.code}" <c:if test="${code==company.code}">selected</c:if> >
                                                            ${company.company}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </p>
                                            <p class="sgreen space">
                                                Chọn khoảng thời gian:
                                                <select name="timeSelect">
                                                    <option value="500" <c:if test="${timeSelect == 500}">selected</c:if>>500 ngày</option>
                                                    <option value="450" <c:if test="${timeSelect == 450}">selected</c:if>>450 ngày</option>
                                                    <option value="400" <c:if test="${timeSelect == 400}">selected</c:if>>400 ngày</option>
                                                    <option value="365" <c:if test="${timeSelect == 365}">selected</c:if>>365 ngày</option>
                                                    <option value="300" <c:if test="${timeSelect == 300}">selected</c:if>>300 ngày</option>
                                                    <option value="240" <c:if test="${timeSelect == 240}">selected</c:if>>240 ngày</option>
                                                    <option value="120" <c:if test="${timeSelect == 120}">selected</c:if>>120 ngày</option>
                                                    <option value="30" <c:if test="${timeSelect == 30}">selected</c:if>>30 ngày</option>
                                                    <option value="20" <c:if test="${timeSelect == 20}">selected</c:if>>20 ngày</option>
                                                    <option value="10" <c:if test="${timeSelect == 10}">selected</c:if>>10 ngày</option>
                                                    </select>
                                                </p>
                                                <p class="sgreen space">
                                                    Loại giải: 
                                                    <input type="radio" <c:if test="${type == 0}">checked</c:if> name="type" value="0" />Tất cả giải
                                                <input type="radio" <c:if test="${type == 1}">checked</c:if> name="type" value="1" />Giải đặc biệt
                                                </p>
                                                <p class="space">
                                                    <input type="hidden" value="view" name="action"/>
                                                    <input type="submit" class="button" value="Xem kết quả" name="cmdView" />
                                                </p>
                                            </div>

                                        <c:if test="${listLoto != null}">
                                            <table class="bot space2" cellspacing="0" cellpadding="0" width="100%" border="1" style="border-color: #e8e8e8">
                                                <thead>
                                                    <tr>
                                                        <th class="bor tc" width="10%">Bộ số</th>
                                                        <th class="bor tc" width="30%">Tổng số ngày về</th>
                                                        <th class="bor tc" width="30%">Tổng số lần về</th>
                                                        <th class="bor tc" width="30%">Tỉ lệ (lần/ngày)</th>
                                                    </tr>
                                                </thead>
                                                <c:forEach items="${listLoto}" var="loto">
                                                    <tr>
                                                        <td class="bor tc">${loto.loto}</td>
                                                        <td class="bor tc">
                                                            ${loto.songayve}  
                                                            (<fmt:formatNumber type="number"
                                                                               value="${loto.songayve * 100/timeSelect}" 
                                                                               maxFractionDigits="1" />%)
                                                        </td>
                                                        <td class="bor tc">
                                                            ${loto.solanxuathien} 
                                                            (<fmt:formatNumber type="number"
                                                                               value="${loto.solanxuathien * 100/(timeSelect * 27)}" 
                                                                               maxFractionDigits="1" />%)
                                                        </td>
                                                        <td class="bor tc">
                                                            <fmt:formatNumber type="number"
                                                                               value="${loto.solanxuathien * 100/loto.songayve}" 
                                                                               maxFractionDigits="1" />%
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </c:if>
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
