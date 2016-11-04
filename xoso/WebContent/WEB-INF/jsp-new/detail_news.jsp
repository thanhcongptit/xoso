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
                                            <c:if test="${lotteryNews!=null}">
                                                <div class="boxlist">
                                                    <div id="kqxsmb" class="cat_news">
                                                        <h3 style="border-bottom: 1px solid #aeaeae;margin-bottom: 20px">
                                                            ${lotteryNews.title}                           
                                                        </h3>
                                                        <c:forTokens items="${lotteryNews.datePost}" delims="," var="d" varStatus="status">
                                                            <c:if test="${status.index==0}">${d}&nbsp;</c:if>                               
                                                            <c:if test="${status.index==1}">
                                                                ${f:substring(d, 0, 2)}:${f:substring(d, 2, 4)}
                                                            </c:if>
                                                        </c:forTokens>
                                                        - Xem(${lotteryNews.viewCounter})<br/>
                                                        <div style="margin-top: 10px;line-height: 20px">
                                                            ${lotteryNews.content}
                                                        </div>                      

                                                        <div class="news_list">
                                                            <h3>Tin khác</h3>
                                                            <c:if test="${listNews!=null}">                            
                                                                <ul>
                                                                    <c:forEach items="${listNews}" var="news" begin="0" end="4">
                                                                        <c:if test="${news.id!=lotteryNews.id}">
                                                                            <li>
                                                                                <a href="<%=request.getContextPath()%>/tin-tuc/${news.url}-${news.id}.html">${news.title}
                                                                                    <p> 
                                                                                        <c:forTokens items="${news.datePost}" var="date" delims="," begin="0" end="0">
                                                                                            (${date})
                                                                                        </c:forTokens>
                                                                                    </p>     
                                                                            </li>
                                                                        </c:if>                                    
                                                                    </c:forEach>                           
                                                                </ul>
                                                            </c:if>

                                                        </div> 
                                                    </div>                     
                                                </div>                                       
                                            </c:if>
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
