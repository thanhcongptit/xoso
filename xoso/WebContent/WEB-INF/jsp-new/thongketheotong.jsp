<%-- 
    Document   : thongketheotong
    Created on : Feb 28, 2016, 3:52:43 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                        THỐNG KÊ THEO CÁC TỔNG
                                    </h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="post">
                                        <div class="tc space">
                                            <p class="sred space"></p>
                                            <p class="sgreen space">
                                                Chọn tổng cần thống kê: 
                                                <select name="tong">
                                                    <option value="0" <c:if test="${tong == 0}">selected</c:if>>Tổng 0</option>
                                                    <option value="1" <c:if test="${tong == 1}">selected</c:if>>Tổng 1</option>
                                                    <option value="2" <c:if test="${tong == 2}">selected</c:if>>Tổng 2</option>
                                                    <option value="3" <c:if test="${tong == 3}">selected</c:if>>Tổng 3</option>
                                                    <option value="4" <c:if test="${tong == 4}">selected</c:if>>Tổng 4</option>
                                                    <option value="5" <c:if test="${tong == 5}">selected</c:if>>Tổng 5</option>
                                                    <option value="6" <c:if test="${tong == 6}">selected</c:if>>Tổng 6</option>
                                                    <option value="7" <c:if test="${tong == 7}">selected</c:if>>Tổng 7</option>
                                                    <option value="8" <c:if test="${tong == 8}">selected</c:if>>Tổng 8</option>
                                                    <option value="9" <c:if test="${tong == 9}">selected</c:if>>Tổng 9</option>
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
                                        <div class="sgreen cl space2">Thống kê lần quay trước:</div>
                                        <table class="bot space2" cellspacing="0" cellpadding="0" width="100%" border="1" style="border-color: #e8e8e8">
                                            <thead>
                                                <tr>
                                                    <th class="bor tc" width="40%">Tổng</th>
                                                    <th class="bor tc" width="60%">Các bộ số về hôm trước</th>
                                                </tr>
                                            </thead><tbody><tr>
                                                    <td class="bor tc">Tổng: 0</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong0Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 1</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong1Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 2</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong2Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 3</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong3Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 4</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong4Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 5</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong5Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 6</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong6Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 7</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong7Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 8</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong8Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="bor tc">Tổng: 9</td>
                                                    <td class="bor tc">
                                                        <c:forEach items="${listTong9Loto}" var="loto">
                                                            ${loto.loto},
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
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
