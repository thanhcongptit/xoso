<%-- 
    Document   : thongkenhanh
    Created on : Jan 23, 2016, 10:21:39 AM
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
                                        THỐNG KÊ NHANH
                                    </h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="post">
                                        <div class="tc space">
                                            <p class="sred space"></p>
                                            <p class="sgreen space">
                                                Các bộ số thống kê: 
                                                <input type="text" name="danloto" value="${danloto}"/>
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
                                            <div class="alert space2">
                                                Các bạn nhập các bộ số thống kê vào ô textbox sau đó nút xem kết quả.<br>
                                                Chú ý các bạn có thể nhập nhiều bộ số khác nhau, các bộ số cách nhau bởi dấu phẩy.<br>
                                                Ví dụ: 00,12,34,...<br>
                                                Lưu ý nếu các bạn không nhập bộ số thống kê, hệ thống mặc định thống kê toàn bộ.
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
