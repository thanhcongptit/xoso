<%-- 
    Document   : thongketheongay
    Created on : Feb 28, 2016, 10:36:36 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>
        <style>
            .tc {
                text-align: center;
            }
            .f1b {
                font-size: larger;
                font-weight: bold;
                vertical-align: middle;
            }
            .info {
                background: #d5edf8;
                color: #205791;
                border-color: #92cae4;
                line-height: 20px;
            }
            .alert, .notice, .success, .info {
                padding: 2px 5px 2px 5px;
                margin-bottom: 5px;
                border: 1px solid #ddd;
            }
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
                        <table width="100%" border="1" style="border-color: #e8e8e8" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    <h2 class="h1title" style="text-align: center">
                                        Thống kê theo ngày của các bộ số
                                    </h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form method="post">
                                        <div class="tc space">
                                            <p class="sred space"></p>
                                            <p class="sred space">
                                                Hôm nay là ${thu}
                                                ngày ${ngay} tháng ${thang} năm ${nam}
                                            </p>
                                            <p class="sgreen space">
                                                Bạn thống kê tất cả các ${thu} trong: 
                                                <select style="width: 100px;" name="week">
                                                    <option value="4" <c:if test="${week == 4}">selected</c:if>>4 tuần</option>
                                                    <option value="8" <c:if test="${week == 8}">selected</c:if>>8 tuần</option>
                                                    <option value="12" <c:if test="${week == 12}">selected</c:if>>12 tuần</option>
                                                    <option value="24" <c:if test="${week == 24}">selected</c:if>>24 tuần</option>
                                                    <option value="36" <c:if test="${week == 36}">selected</c:if>>36 tuần</option>
                                                    <option value="48" <c:if test="${week == 48}">selected</c:if>>48 tuần</option>
                                                    <option value="60" <c:if test="${week == 60}">selected</c:if>>60 tuần</option>
                                                    <option value="66" <c:if test="${week == 66}">selected</c:if>>66 tuần</option>
                                                    <option value="72" <c:if test="${week == 72}">selected</c:if>>72 tuần</option>
                                                    <option value="80" <c:if test="${week == 80}">selected</c:if>>80 tuần</option>
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
                                            <p class="space">
                                                <input type="hidden" value="view" name="action"/>
                                                <input type="submit" class="button" value="Xem kết quả" name="cmdView" />
                                            </p>
                                        </div>
                                        <c:if test="${nhieuList != null}">
                                            <h4><span class="sred">27 bộ số xuất hiện nhiều nhất vào ${thu} trong ${week} tuần:</span></h4>
                                            <div class="info tc f1b">
                                                <c:forEach items="${nhieuList}" var="loto">
                                                    ${loto.loto} (${loto.solanxuathien} lần);
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                        <c:if test="${itList != null}">
                                            <br/>
                                            <h4><span class="sred">10 bộ số xuất hiện ít nhất vào ${thu} trong ${week} tuần:</span></h4>
                                            <div class="info tc f1b">
                                                <c:forEach items="${itList}" var="loto">
                                                    ${loto.loto} (${loto.solanxuathien} lần);
                                                </c:forEach>
                                            </div>
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
