<%-- 
    Document   : body-loto-online
    Created on : 14-Jan-2016, 09:00:31
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="loto-online">
    <table>
        <tr>
            <td id="title">LOTO<br/>ONLINE</td>
            <td id="loto-content">
                <strong>* Tham gia chốt số online với mục đích giải trí, không liên quan đến tiền hay hiện vật</strong><br/>
                <strong>* Xu được cấp miễn phí, không có giá trị quy đổi thành tiền</strong><br/><br/>
                <strong style="color: red;font-size: 13px;">Bạn thích cặp số nào hôm nay:</strong><br/>
                <strong>Cặp số:</strong> <input class="loto-inp"/> <strong>Số điểm:</strong> <input class="loto-inp"/> <input type="submit" value="Ghi" onclick="window.location.href='${pageContext.servletContext.contextPath}/loto-online.htm'"/><br/>
                <strong>Xem chuyên gia và những người khác thích cặp số nào? <a href="<%= request.getContextPath() %>/bang-chot-so.htm" style="color: blue">Tại đây</a>
            </td>
        </tr>
    </table>					
</div>
