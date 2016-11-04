<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>
        <link href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
        <script>
            $(function() {
                $('#tungay').datepicker({
                    inline: true,
                    showOtherMonths: true,
                    dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                });
                
                $('#denngay').datepicker({
                    inline: true,
                    showOtherMonths: true,
                    dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                });
            });
        </script>
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
                        <table width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ TẦN SỐ NHỊP</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">
                                    <form method="post">
                                    Từ ngày
                                    <input type="text" id="tungay" name="tungay" class="boxLich" value="${tungay}"/>
                                    Đến ngày
                                    <input type="text" id="denngay" name="denngay" class="boxLich" value="${denngay}"/>
                                    <br/><br/>
                                    Cặp số khảo sát
<!--                                    <input type="number" name="capso" value="${capso}" style="line-height:24px;width: 50px" maxlength="2"/>-->
                                    <select name="capso" style="line-height:24px;width: 50px">
                                        <c:forEach var="i" begin="0" end="99">
                                            <c:choose>
                                                <c:when test="${i < 10}">
                                                    <c:set var="index" value="0${i}"/>
                                                </c:when>    
                                                <c:otherwise>
                                                    <c:set var="index" value="${i}"/>
                                                </c:otherwise>
                                            </c:choose>
                                                <option value="${index}" <c:if test="${index eq capso}">selected</c:if>> ${index}</option>
                                        </c:forEach>
                                    </select>
                                    Theo thứ: 
                                    <select name="dayofweek" style="height: 30px">
                                        <option value="all" <c:if test="${dayofweek=='all'}">selected</c:if>>Tất cả</option>
                                        <option value="2" <c:if test="${dayofweek=='2'}">selected</c:if>>Thứ 2</option>
                                        <option value="3" <c:if test="${dayofweek=='3'}">selected</c:if>>Thứ 3</option>                    
                                        <option value="4" <c:if test="${dayofweek=='4'}">selected</c:if>>Thứ 4</option>
                                        <option value="5" <c:if test="${dayofweek=='5'}">selected</c:if>>Thứ 5</option>
                                        <option value="6" <c:if test="${dayofweek=='6'}">selected</c:if>>Thứ 6</option>
                                        <option value="7" <c:if test="${dayofweek=='7'}">selected</c:if>>Thứ 7</option>
                                        <option value="cn" <c:if test="${dayofweek=='cn'}">selected</c:if>>Chủ nhật</option>
                                    </select>
                                    <input type="submit" value="Xem kết quả" style="height:24px;"/>
                                    </form>
                                </td>
                            </tr>                                                         
                            <c:if test="${listLoto!=null}">
                                <tr><td style="padding-top: 15px">Thống kê tần số nhịp xuất hiện cho cặp số: <span style="color: red"> ${capso}</span></td></tr>
                                <tr>
                                    <td>
                                        <c:if test="${dayofweek=='all'}">
                                            Tổng số lần xuất hiện của cặp số <span style="color: red"> ${capso}</span> vào tất cả các thứ là: ${fn:length(listLoto)} lần
                                        </c:if>
                                        <c:if test="${dayofweek!='all'}">
                                            <c:set var="dem" value="0"/>
                                            <c:forEach items="${listLoto}" var="loto">
                                                <c:if test="${loto.thuve==dayofweek}"><c:set var="dem" value="${dem+1}"/></c:if>
                                            </c:forEach> 
                                            Tổng số lần xuất hiện của cặp số <span style="color: red"> ${capso}</span> vào thứ ${dayofweek} là: ${dem} lần
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 25px">
                                        <table style="font-weight: bold;font-size: 15px;" width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="20%" class="bol">Ngày</td>
                                                <td width="10%" class="bol">Thứ</td>
                                                <td width="45%" class="bol">Về ở giải</td>
                                                <td width="25%" class="bol">Số nhịp xuất hiện</td>
                                            </tr>
                                            <c:if test="${dayofweek=='all'}">
                                            <c:forEach items="${listLoto}" var="loto">                                                
                                                <tr style="height: 30px">
                                                    <td width="20%" class="bol">${loto.ngayxuathiengannhat}</td>
                                                    <td width="10%" class="bol">${loto.thuve}</td>
                                                    <td width="45%" class="bol">${loto.giaive}</td>
                                                    <td width="25%" class="bol">${loto.ngaychuave}</td>
                                                </tr>                                                                                               
                                            </c:forEach>
                                            </c:if>
                                            <c:if test="${dayofweek!='all'}">
                                            <c:forEach items="${listLoto}" var="loto">    
                                                <c:if test="${loto.thuve==dayofweek}">
                                                <tr style="height: 30px">
                                                    <td width="20%" class="bol">${loto.ngayxuathiengannhat}</td>
                                                    <td width="10%" class="bol">${loto.thuve}</td>
                                                    <td width="45%" class="bol">${loto.giaive}</td>
                                                    <td width="25%" class="bol">${loto.ngaychuave}</td>
                                                </tr>
                                                </c:if>                                                                                                                                               
                                            </c:forEach>
                                            </c:if>    
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
    </body>
</html>
