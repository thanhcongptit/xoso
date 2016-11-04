<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                        <table style="font-weight: bold;font-size: 15px;" width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">KIỂM TRA LỊCH SỬ CẦU LOTO</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">
                                    <form method="post">
                                    Từ ngày
                                    <input type="text" id="tungay" name="tungay" class="boxLich" value="${tungay}"/>
                                    Đến ngày
                                    <input type="text" id="tungay" name="tungay" class="boxLich" value="${denngay}"/>
                                    <br/><br/>
                                    Vị trí 1: 
                                    <input type="number" id="tungay" name="vitri1" value="${vitri1}"/>
                                    Vị trí 2: 
                                    <input type="number" id="tungay" name="vitri2" value="${vitri2}"/>
                                    <br/><br/>
                                    <input type="checkbox" name="bachthu" value="1" <c:if test="${bachthu=='1'}">checked</c:if>/>
                                    Bạch thủ
                                    <br/><br/>
                                    Miền bắc
                                    <input type="radio" name="region" value="MB" <c:if test="${region=='MB'}">checked</c:if> onclick="submit();"/>
                                    Miền trung
                                    <input type="radio" name="region" value="MT" <c:if test="${region=='MT'}">checked</c:if> onclick="submit();"/>
                                    Miền nam
                                    <input type="radio" name="region" value="MN" <c:if test="${region=='MN'}">checked</c:if> onclick="submit();"/>
                                    
                                    <br/><br/>
                                    <select name="code">
                                    <c:forEach items="${listLotteryCompany}" var="company">
                                        <c:if test="${company.region==region}">
                                            <option value="${company.code}" <c:if test="${code==company.code}">selected</c:if> >${company.company}</option>
                                        </c:if>                                        
                                    </c:forEach>
                                    </select>
                                    <br/><br/>
                                    <input type="submit" value="Xem kết quả" style="height:24px;"/>                                    
                                    </form>
                                </td>
                            </tr>                                                         
                            <c:if test="${listCauLoto!=null}">
                                <tr style="background-color: #d5edf8;height: 30px"><td style="padding-top: 15px">THÔNG KÊ LỊCH SỬ CẦU CHẠY Ở >> VỊ TRÍ 1: ${vitri1} - VỊ TRÍ 2: ${vitri2}</td></tr>                                
                                <c:forEach items="${vLichsucau}" var="lichsu">
                                    <tr>
                                        <td style="padding-top: 25px">
                                            Số cầu dài ${lichsu.dodai} ngày là: ${lichsu.tongcau}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr style="background-color: #d5edf8;height: 30px"><td style="padding-top: 15px">DƯỚI ĐÂY LÀ CHI TIẾT LỊCH SỬ CẦU CHẠY Ở >> VỊ TRÍ 1: ${vitri1} - VỊ TRÍ 2: ${vitri2}</td></tr>                                
                                <c:forEach items="${listCauLoto}" var="cauloto">
                                    <tr>
                                        <td style="padding-top: 25px">
                                            ${cauloto.ngayve}&nbsp; 
                                            <c:forTokens items="${cauloto.ngayve}" delims="-"  var="ngay">
                                                <c:set var="ngaybatdau" value="${ngay}"/>
                                            </c:forTokens>
                                            <a target="_blank" href="${pageContext.servletContext.contextPath}/detailcau.htm?code=${code}&d=${ngaybatdau}&l=${cauloto.loto}&v1=${cauloto.vitri1}&v2=${cauloto.vitri2}&c=${cauloto.cau}">
                                                Xem chi tiết
                                            </a>
                                        </td>
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
