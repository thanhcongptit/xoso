<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <%@include file="include/header.jsp" %>
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
                        <table width="100%" border="1" style="border-color: #aaa;padding-top: 30px" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ BẠCH THỦ VỀ TRONG 3 NGÀY</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;text-align: center">Nhập bộ số cần xem chu kì</td>
                            </tr>
                            <tr>
                                <td style="text-align: center;padding-top: 5px">
                                    <form method="post">
                                        Thống kê đến ngày
                                        <input type="text" name="tungay" id="tungay" value="${tungay}" class="boxLich"/>
                                        Trong
                                        <input type="text" name="trong" value="${trong}" size="5px"/>                                        
                                        lần đặc biệt về
                                        <input type="text" name="special" value="${special}" size="5px"/>                                        
                                        Sắp xếp theo
                                        <select name="order">
                                            <option value="1" <c:if test="${order=='1'}">selected</c:if>>Về nhiều</option>
                                            <option value="0" <c:if test="${order=='0'}">selected</c:if>>Miss</option>
                                        </select>
                                        <input type="submit" value="Xem kết quả"/>
                                    </form>
                                </td>
                            </tr>                            
                            <c:if test="${listOfDacBiet!=null}">
                                <tr>
                                    <td style="padding-top: 10px">
                                        Thống kê từ 01/07/2009 đến này trong ${trong} lần ĐB về ${special} gần đây, 10 cặp loto về nhiều nhất trong Ngày thứ ${ganday} sau đó:
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 50px">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="20%" class="bol">Loto</td>
                                                <td width="20%" class="bol">Số lần về</td>
                                                <td width="20%" class="bol">Ngày 1</td>
                                                <td width="20%" class="bol">Ngày 2</td>
                                                <td width="20%" class="bol">Ngày 3</td>
                                            </tr>
                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="9">
                                            <tr >
                                                <td width="20%" class="bol">${lotocap.cap}</td>
                                                <td width="20%" class="bol">
                                                    <fmt:parseNumber type="number" value="${lotocap.solanve1+lotocap.solanve2+lotocap.solanve3}" var="tong"/>
                                                    ${tong}
                                                </td>
                                                <td width="20%" class="bol">
                                                    ${lotocap.solanve1}    
                                                </td>
                                                <td width="20%" class="bol">
                                                    ${lotocap.solanve2}    
                                                </td>
                                                <td width="20%" class="bol">
                                                    ${lotocap.solanve3}    
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 10px">Bảng thống kê chi tiết về trong ngày thứ ${ganday}</td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 10px">
                                        <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="9">
                                            <input name="${lotocap.cap}" type="checkbox" value="${lotocap.cap}"/>
                                            ${lotocap.cap}&nbsp;
                                        </c:forEach>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td style="padding-top: 20px">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td width="7.5%" class="bol" rowspan="2">Ngày</td>
                                                <td width="3.5%" class="bol" rowspan="2">Đặc biệt</td>
                                                <td width="2%" class="bol" rowspan="2">Sau đó</td>
                                                <td width="87%" class="bol" >
                                                    <table width="100%"  cellspacing="0" cellpadding="1">
                                                        <tr>
                                                            <td colspan="3" class="bol">Số lần về cụ thể từng ngày như sau</td>                                                            
                                                        </tr>
                                                        <tr>
                                                            <td width="33%" class="bol">Ngày 1</td>
                                                            <td width="33%" class="bol">Ngày 2</td>
                                                            <td width="33%" class="bol">Ngày 3</td>
                                                        </tr>
                                                    </table>
                                                </td>                                                
                                                
                                            </tr>                                            
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <c:forEach items="${listOfDacBiet}" var="dacbiet">
                                                <tr>
                                                    <td width="7.5%" class="bol" >${dacbiet.ngayve}</td>
                                                    <td width="3.5%" class="bol" >${dacbiet.special}</td>
                                                    <td width="2%" class="bol" >---</td>
                                                    <td width="29%" class="bol" >
                                                        <c:if test="${dacbiet.listLotoCap1!=null}">
                                                            <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                                <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="9">
                                                                    <c:if test="${cap1.cap==lotocap.cap}">${cap1.cap}</c:if>
                                                                </c:forEach>                                                            
                                                            </c:forEach>
                                                        </c:if>                                                        
                                                    </td>
                                                    <td width="29%" class="bol" >
                                                        
                                                        <c:if test="${dacbiet.listLotoCap2!=null}">
                                                            <c:forEach items="${dacbiet.listLotoCap2}" var="cap2">
                                                                <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="9">
                                                                    <c:if test="${cap2.cap==lotocap.cap}">${cap2.cap}</c:if>
                                                                </c:forEach>                                                            
                                                            </c:forEach>                                                            
                                                        </c:if>
                                                    </td>
                                                    <td width="29%" class="bol" >
                                                        
                                                        <c:if test="${dacbiet.listLotoCap3!=null}">
                                                            <c:forEach items="${dacbiet.listLotoCap3}" var="cap3">
                                                                <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="9">
                                                                    <c:if test="${cap3.cap==lotocap.cap}">${cap3.cap}</c:if>
                                                                </c:forEach>                                                            
                                                            </c:forEach>
                                                        </c:if>
                                                    </td>
                                                                                                        
                                                </tr>
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
    </body>
</html>
