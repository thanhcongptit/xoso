<%-- 
    Document   : tinxoso
    Created on : Sep 12, 2015, 11:02:54 AM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script>
    function loadTinXoSo(p){
        $('#box_tinxoso').load("/ajax/tinxoso.htm?p="+p,function(responseTxt,statusTxt,xhr){
            if(statusTxt==="success") {                    
                 $("#box_tinxoso").html(); 
              }else{
                  //loi
                  //alert("Error:112 "+xhr.status+": "+xhr.statusText);
              }
         });
    }
</script>
    <div class="cat_news">
                         <h1 class="titleh1">Tin tức xổ số</h1>
                        <c:if test="${listNews!=null}">
                            <c:forEach items="${listNews}" var="news" >
                                <div class="news_item">
                                <a href="${strUrl}/tin-tuc/${news.url}-${news.id}.html">                           
                                   <img class="thumb12 lft" src="http://cdn.xoso.wap.vn/hinh-anh-tin-tuc/${news.id}.gif">
                                </a>
                                <a href="${strUrl}/tin-tuc/${news.url}-${news.id}.html"><h6>${news.title}</h6></a>
                                <span class="newsTime">
                                    <c:forTokens items="${news.datePost}" var="date" delims="," begin="0" end="0">
                                        ${date}
                                    </c:forTokens> 
                                </span>
                                <p>${news.title}</p>
                                <div class="clear"></div>
                             </div>
                            </c:forEach>
                            <div style="text-align: center;padding-bottom: 20px;padding-top: 50px">
                                <c:set var="end" value="10"/>
                                <c:if test="${totalPage-iPage>10}"><c:set var="end" value="${iPage+10}"/></c:if>
                                <c:if test="${totalPage-iPage<=10}"><c:set var="end" value="${totalPage-iPage}"/></c:if>
                                
                                <c:if test="${iPage==1}"><a href="${strUrl}/tin-tuc/${iPage}.html">Quay lại</a> </c:if>
                                <c:if test="${iPage>1}"><a href="${strUrl}/tin-tuc/${iPage-1}.html">Quay lại</a> </c:if>
                                <c:forEach  var="i" begin="${iPage}" end="${end}">
                                      <a href="${strUrl}/tin-tuc/${i}.html" <c:if test="${i==iPage}">style="color: #ff0000"</c:if>>${i}</a>,
                                  </c:forEach>
                                <c:if test="${iPage==totalPage}"><a href="${strUrl}/tin-tuc/${iPage}.html">Tiếp</a> </c:if>
                                <c:if test="${iPage<totalPage}"><a href="${strUrl}/tin-tuc/${iPage+1}.html">Tiếp</a> </c:if>
                              </div>    
                        </c:if>
                     </div>

