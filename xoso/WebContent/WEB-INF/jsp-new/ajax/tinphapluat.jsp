<%-- 
    Document   : tinxoso
    Created on : Sep 12, 2015, 11:02:54 AM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script>
    function loadTinPhapLuat(p){
        $('#box_tinphapluat').load("/ajax/tinphapluat.htm?p="+p,function(responseTxt,statusTxt,xhr){
            if(statusTxt==="success") {                    
                 $("#box_tinphapluat").html(); 
              }else{
                  //loi
                  //alert("Error:112 "+xhr.status+": "+xhr.statusText);
              }
         });
    }
</script>

    <h1 class="titleh1">Tin tức pháp luật</h1>
<c:if test="${listNews!=null}">
    <c:forEach items="${listNews}" var="news" begin="${(iPage-1)*15}" end="${iPage*15}">
        <div class="news_item">
        <a href="/tin-tuc/${news.linkseo}-${news.siteId}-${news.id}.html">                           
           <img class="thumb12 lft" src="http://news.xoso.wap.vn/${news.thumbnail}">
        </a>
        <a href="/tin-tuc/${news.linkseo}-${news.siteId}-${news.id}.html"><h6>${news.title}</h6></a>
        <span class="newsTime">${news.approvalDate}</span>
        <p>${news.title}</p>
        <div class="clear"></div>
     </div>
    </c:forEach>
    <div style="text-align: center;padding-bottom: 20px;padding-top: 50px">
          <c:forEach var="i" begin="1" end="${iPage}">
              <a href="javascript:loadTinPhapLuat('${i}')" <c:if test="${i==iPage}">style="color: #ff0000"</c:if>>${i}</a>,
          </c:forEach>
              ...<a href="javascript:loadTinPhapLuat('${iPage+1}')">Tiếp</a>  
      </div>    
</c:if>