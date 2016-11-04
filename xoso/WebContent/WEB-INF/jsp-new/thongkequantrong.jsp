<%-- 
    Document   : thongkequantrong
    Created on : Feb 28, 2016, 4:58:24 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                                    THỐNG KÊ QUAN TRỌNG
                                </h2>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form method="post">
                                    <div class="tc space">
                                        <p class="sred space">Dữ liệu thống kê trong 1 tháng</p>
                                        <p class="sred space"></p>
                                        <p class="sgreen space">
                                            Chọn kiểu thống kê: 
                                            <select name="statiscType">
                                                <option value="1" <c:if test="${statiscType == 1}">selected</c:if>>27 bộ số xuất hiện nhiều nhất trong 30 lần quay</option>
                                                <option value="2" <c:if test="${statiscType == 2}">selected</c:if>>Các bộ số không xuất hiện từ 10 ngày trở lên</option>
                                                <option value="3" <c:if test="${statiscType == 3}">selected</c:if>>10 bộ số xuất hiện ít nhất trong 30 lần quay</option>
                                                <option value="4" <c:if test="${statiscType == 4}">selected</c:if>>Các bộ số xuất hiện liên tiếp</option>
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
</html>
