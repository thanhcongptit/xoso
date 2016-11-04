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
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ CẦU LOTO</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">
                                    <form method="post">
                                    Từ ngày
                                    <input type="text" id="tungay" name="tungay" class="boxLich" value="${tungay}"/>
                                    Số ngày cầu chạy: 
                                    <input type="number" id="tungay" name="cau" value="${cau}"/>
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
                                <tr style="background-color: #d5edf8;height: 30px"><td style="padding-top: 15px">BẠN ĐANG XEM TOÀN BỘ CẦU CÓ SỐ NGÀY CẦU CHẠY LÀ ${cau} NGÀY</td></tr>
                                <tr style="height: 30px">
                                    <td >
                                        Chú ý: Để xem vị trí của một cầu bạn chỉ cần di chuột lên link của cầu là thấy.
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 25px">
                                        <table style="font-weight: bold;font-size: 15px;" width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <c:set var="dau" value="0"/>
                                            <c:forEach items="${listCauLoto}" var="cauloto" varStatus="status">
                                                <c:if test="${status.index%10==0}">
                                                <tr style="height: 30px">
                                                    <td  class="bol" style="height:30px;width: 50px">Đầu ${dau}</td>                                                
                                                </c:if>                                                
                                                    <td  class="bol" style="height:30px;width: 50px">                                                        
                                                        <c:if test="${cauloto.cau>=icau}">                                                            
                                                            <a href="${pageContext.servletContext.contextPath}/detailcau.htm?code=${code}&d=${tungay}&l=${cauloto.loto}&v1=${cauloto.vitri1}&v2=${cauloto.vitri2}&c=${cau}" target="_blank">${cauloto.loto}</a>
                                                        </c:if>
                                                        <c:if test="${cauloto.cau<icau}">&nbsp;</c:if>
                                                    </td>
                                                <c:if test="${status.count%10==0}">    
                                                </tr>                                  
                                                <c:set var="dau" value="${dau+1}"/>
                                                </c:if>
                                            </c:forEach>                                            
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
