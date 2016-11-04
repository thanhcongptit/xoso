<%-- 
    Document   : content-topchoinhieutrongngay
    Created on : 14-Jan-2016, 09:22:29
    Author     : 24h
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="topchoinhieutrongngay">
    <h3 class="titleTop3">Top 10 loto chơi nhiều</h3>
    <div class="show-list-left">
        <ul>
            <c:if test="${hmTop10ChoiNhieu ne null}">
                <c:forEach items="${hmTop10ChoiNhieu}" var="loto">
                    <li><a <c:if test="${not loto.isTrue}"> style="color: #FF3322" </c:if> ><b>${loto.key}:</b> ${loto.value} lượt</a></li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
</div>
