<%-- 
    Document   : body-list-news
    Created on : 14-Jan-2016, 09:02:53
    Author     : 24h
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="list-news">
    <div id="header">Bản tin xổ số</div>
    <c:if test="${listLotteryNews!=null}">
        <c:forEach items="${listLotteryNews}" var="news">
            <article style="display: block;">
                <div class="cover">
                    <a href="<%=request.getContextPath()%>/tin-tuc/${news.url}-${news.id}.html">							
                        <img src="${news.imageUrl}">
                    </a>
                </div>
                <div class="linktitle">
                    <a href="<%=request.getContextPath()%>/tin-tuc/${news.url}-${news.id}.html">
                        ${news.title}                        
                        <c:forTokens items="${news.datePost}" var="date" delims="," begin="0" end="0">
                            <c:if test="${date=='Hôm nay'}">
                                <img src="<%=request.getContextPath()%>/resources/images/new.gif">
                            </c:if>
                        </c:forTokens>
                    </a>
                </div>
                <time datetime="2015-07-30 14:20+0700" pubdate="">
                    <c:forTokens items="${news.datePost}" var="date" delims="," begin="0" end="0">
                        ${date}
                    </c:forTokens>                                
                </time>
            </article>
            <div class="clear"></div>
        </c:forEach>
    </c:if>
    <p class="btnMore"><a href="<%=request.getContextPath()%>/tin-tuc.html" title="" style="color: #086404">Xem thêm</a></p>
</div>
