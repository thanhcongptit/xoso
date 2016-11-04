<%-- 
    Document   : thongketonghop
    Created on : Feb 28, 2016, 9:07:58 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                                        Thống kê tổng hợp
                                    </h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="post">
                                        <div class="tc space">
                                            <p class="sred space"></p>
                                            <p class="sgreen space">
                                                Chọn kiểu thống kê: 
                                                <select name="statiscType">
                                                    <option value="1" <c:if test="${statiscType == 1}">selected</c:if>>Thống kê tổng chẵn</option>
                                                    <option value="2" <c:if test="${statiscType == 2}">selected</c:if>>Thống kê tổng lẻ</option>
                                                    <option value="3" <c:if test="${statiscType == 3}">selected</c:if>>Thống kê bộ chẵn chẵn</option>
                                                    <option value="4" <c:if test="${statiscType == 4}">selected</c:if>>Thống kê bộ lẻ lẻ</option>
                                                    <option value="5" <c:if test="${statiscType == 5}">selected</c:if>>Thống kê bộ chẵn lẻ</option>
                                                    <option value="6" <c:if test="${statiscType == 6}">selected</c:if>>Thống kê bộ lẻ chẵn</option>
                                                    <option value="7" <c:if test="${statiscType == 7}">selected</c:if>>Thống kê bộ kép</option>
                                                    <option value="8" <c:if test="${statiscType == 8}">selected</c:if>>Thống kê bộ sát kép</option>
                                                    <option value="9" <c:if test="${statiscType == 9}">selected</c:if>>Thống kê theo đầu số</option>
                                                    <option value="10" <c:if test="${statiscType == 10}">selected</c:if>>Thống kê theo đít số</option>
                                                    <option value="11" <c:if test="${statiscType == 11}">selected</c:if>>Thống kê 15 số về nhiều nhất</option>
                                                    <option value="12" <c:if test="${statiscType == 12}">selected</c:if>>Thống kê 15 số về ít nhất</option>
                                                </select>
                                            </p>
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
                                                Từ ngày
                                                <input type="text" name="tungay" id="tungay" value="${tungay}" class="boxLich"/>
                                                Đến ngày
                                                <input type="text" name="denngay" id="denngay" value="${denngay}" class="boxLich"/>
                                            </p>
                                            <p class="sgreen space">
                                                Loại giải: 
                                                <input type="radio" <c:if test="${type == 0}">checked</c:if> name="type" value="0" />Tất cả giải
                                                <input type="radio" <c:if test="${type == 1}">checked</c:if> name="type" value="1">Giải đặc biệt
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
                                                        <th class="bor tc" width="30%">Ngày về gần nhất</th>
                                                        <th class="bor tc" width="30%">Tổng số lần xuất hiện</th>
                                                        <th class="bor tc" width="30%">Số ngày chưa về</th>
                                                    </tr>
                                                </thead>
                                                <c:forEach items="${listLoto}" var="loto">
                                                    <tr>
                                                        <td class="bor tc">${loto.loto}</td>
                                                        <td class="bor tc">${loto.ngayxuathiengannhat}</td>
                                                        <td class="bor tc">${loto.solanxuathien}</td>
                                                        <td class="bor tc">${loto.ngaychuave}</td>
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

