<%-- 
    Document   : index
    Created on : 14-Jan-2016, 08:22:23
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header.jsp" %>        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/thong-ke.css">
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
                        <%@include file="include/body-calendar-left.jsp" %>
                        <%@include file="include/body-lichmothuong-left.jsp" %>
                        <%@include file="include/body-xs3mien-left.jsp" %>
                        <%@include file="include/body-thongkecoban-left.jsp" %>
                        <%@include file="include/body-banner-top5-left.jsp" %>                      
                    </div>					
                    <div id="content-center">
                        <div id="content-tructiep">
                            <table style="width: 100%">						
                                <tr>
                                    <td colspan="2">
                                        <div class="thong-ke">
                                            <div class="boxlist" id="box_tinxoso">
                                                <div class="cat_news">
                                                    <h1 class="titleh1">Tin tức xổ số</h1>
                                                    <c:if test="${listNews!=null}">
                                                        <c:forEach items="${listNews}" var="news" >
                                                            <div class="news_item">
                                                                <a href="<%= request.getContextPath() %>/tin-tuc/${news.url}-${news.id}.html">                           
                                                                    <img class="thumb12 lft" src="${news.imageUrl}">
                                                                </a>
                                                                <a href="<%= request.getContextPath() %>/tin-tuc/${news.url}-${news.id}.html"><h6>${news.title}</h6></a>
                                                                <span class="newsTime">
                                                                    <c:forTokens items="${news.datePost}" var="date" delims="," begin="0" end="0">
                                                                        ${date}
                                                                    </c:forTokens> 
                                                                </span>
                                                                <p>${news.description}</p>
                                                                <div class="clear"></div>
                                                            </div>
                                                        </c:forEach>
                                                        <div style="text-align: center;padding-bottom: 20px;padding-top: 50px">                                
                                                            <c:set var="end" value="10"/>
                                                            <c:if test="${totalPage-iPage>10}"><c:set var="end" value="${iPage+10}"/></c:if>
                                                            <c:if test="${totalPage-iPage<=10}"><c:set var="end" value="${totalPage-iPage}"/></c:if>
                                                            <c:if test="${end < 0}">
                                                                <c:set var="end" value="1"/>
                                                            </c:if>
                                                            <c:if test="${iPage==1}"><a href="<%= request.getContextPath() %>/tin-tuc/${iPage}.html">Quay lại</a> </c:if>
                                                            <c:if test="${iPage>1}"><a href="<%= request.getContextPath() %>/tin-tuc/${iPage-1}.html">Quay lại</a> </c:if>
                                                            <c:forEach var="i" begin="1" end="${totalPage}">
                                                                <a href="<%= request.getContextPath() %>/tin-tuc/${i}.html" <c:if test="${i==iPage}">style="color: #ff0000"</c:if>>${i}</a>, 
                                                            </c:forEach>
                                                            <c:if test="${iPage==totalPage}"><a href="<%= request.getContextPath() %>/tin-tuc/${iPage}.html">Tiếp</a> </c:if>
                                                            <c:if test="${iPage<totalPage}"><a href="<%= request.getContextPath() %>/tin-tuc/${iPage+1}.html">Tiếp</a> </c:if>
                                                            </div>    
                                                    </c:if>
                                                </div>                     
                                            </div>
                                        </div>								
                                    </td>
                                </tr>						
                            </table>					
                        </div>
                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
