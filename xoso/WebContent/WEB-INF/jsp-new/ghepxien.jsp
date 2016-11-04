<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>
        <style>
            #content-center{font-size: 14px;}                
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
                        <table width="100%" border="1" style="border-color: #aaa;font-weight: bold;font-size: 15px;" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">GHÉP LÔ XIÊN 2, XIÊN 3, XIÊN 4 MỘT CÁCH TỰ ĐỘNG</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">Nhập dàn số, tối đã 10 con: (các cặp số cách nhau bởi dấu phẩy. VD: 92,06,39)</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;padding-top: 5px">
                                    <form method="post">
                                        <input type="text" name="danloto" value="${danloto}" maxlength="29"/>
                                        <select name="opt">
                                            <option value="2" <c:if test="${opt=='2'}">selected</c:if>>Xiên 2</option>
                                            <option value="3" <c:if test="${opt=='3'}">selected</c:if>>Xiên 3</option>
                                            <option value="4" <c:if test="${opt=='4'}">selected</c:if>>Xiên 4</option>
                                        </select>
                                        <input type="submit" value="Xem kết quả"/>
                                    </form>
                                </td>
                            </tr>                            
                            <c:if test="${list!=null}">
                                <tr>
                                    <td style="padding-top: 5px">Các cặp xiên ${opt}</td>
                                </tr>
                                <c:forEach items="${list}" var="xien">
                                <tr>                                    
                                    <td style="line-height: 20px">${xien}</td>                                
                                </tr>
                                </c:forEach>
                            </c:if>
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
    </body>
</html>
