<%-- 
    Document   : content-topdaigia
    Created on : 14-Jan-2016, 09:21:58
    Author     : 24h
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${listTopDaiGia ne null}">
    <div id="topdaigia">
        <h3 class="titleTop3">Top 6 Đại Gia</h3>
        <div class="show-list-left">
            <ul>            
                <c:forEach items="${listTopDaiGia}" var="daigia" varStatus="i">
                    <li><a><b style="cursor: pointer" onclick="openChat(${daigia.id}, '${daigia.username}')">${i.index+1}. ${daigia.fullname}:</b> ${daigia.money} xu</a></li>    
                </c:forEach>                
            </ul>
        </div>
    </div>
</c:if>
