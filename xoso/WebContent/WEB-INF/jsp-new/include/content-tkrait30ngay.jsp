<%-- 
    Document   : content-tkrait30ngay
    Created on : 14-Jan-2016, 09:26:04
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div id="tkrait30ngay">
    <h2 class="h1title">Thống kê loto ra ít trong 30 ngày</h2>
    <c:if test="${min30LotoList != null}">
        <table border="0" cellspacing="0" cellpadding="0" class="bot" style="display: inline-table;">
            <thead>
                <tr>
                    <th class="bol f1 tt1"><h3>Cặp</h3></th>
                    <th class="bor f1 tt2"><h3>Số lần</h3></th>
                    <th class="bol f1 tt1"><h3>Cặp</h3></th>
                    <th class="bor f1 tt2"><h3>Số lần</h3></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${min30LotoList}" var="loto" varStatus="i">
                    <c:if test="${i.index % 2 == 0}">  
                        <tr>
                            <td class="bol tt1 f1b db">${loto.loto}</td>
                            <td class="bor tt2 f1b tleft">${loto.solanxuathien}</td>
                    </c:if>
                    <c:if test="${i.count % 2 == 0}">
                            <td class="bol tt1 f1b db">${loto.loto}</td>
                            <td class="bor tt2 f1b tleft">${loto.solanxuathien}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
