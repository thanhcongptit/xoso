<%-- 
    Document   : index
    Created on : 14-Jan-2016, 08:22:23
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/thong-ke.css">
        <link href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
        <script>
                    $(function() {
                    $('#ngayghi').datepicker({
                    inline: true,
                            showOtherMonths: true,
                            dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                    });
                    });        </script>
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
                        <div id="content-tructiep">
                            <table style="width: 100%">						
                                <tr>
                                    <td colspan="2">
                                        <div class="thong-ke">
                                        <div class="boxlist">
                                            <div id="kqxsmb" class="cat_news">
                                                <h3 style="border-bottom: 1px solid #aeaeae;margin-bottom: 20px">
                                                    Bảng chốt số                     
                                                </h3>
                                                <div class="chotlisttbl">
                                                    <div class="chotlisthead">Bảng chốt số ngày ${ddmmyyyy} <span class="chotcount"> (${fn:length(listBangChotSo)})</span></div>
                                                    <c:if test="${listBangChotSo ne null}">
                                                        <div style="padding: 2px;max-height: 300px;overflow: auto">
                                                            <c:forEach items="${listBangChotSo}" var="bangChotSo">
                                                                <div class="chotline chothight" rel="265453" uid="59337">
                                                                    <span class="chotname tip" id="chotuser_59337">${bangChotSo.memberName}</span> 
                                                                    <span class="chottime" title="Chốt lúc 18:26:12 hôm qua">(Chốt: ${bangChotSo.ngaychotHienthi})</span>
                                                                    <div class="clear"></div> 
                                                                    <span class="chotline_lo">Lô:</span>&nbsp;${bangChotSo.listLoto}
                                                                </div>
                                                            </c:forEach>
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
