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
            #wapper {width: 100%;}
            #content-center {margin-left: 0px}
        </style>
    </head>
    <body>		
        <%@include file="include/body-header.jsp" %>
        <div id="wapper">
            <div id="main_container">
                <div id="content">
                    <%--@include file="include/lotoonline.jsp" --%>
                    <%--@include file="include/right.jsp"--%>				
                    <%--@include file="include/left.jsp" --%>				
                    <div id="content-center"> 
                        <table width="100%" border="1" style="border-color: #aaa;padding-top: 30px" cellspacing="0" cellpadding="0">
                            <tr>
                                <td style="padding-top: 30px"><h2 class="h1title" style="text-align: center">THỐNG KÊ CHU KÌ DÀN LOTO</h2></td>
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
                                        gần đây
                                        <select  name="ganday">
                                            <option value="1" <c:if test="${ganday=='1'}">selected</c:if>>Ngày  thứ 1</option>
                                            <option value="2" <c:if test="${ganday=='2'}">selected</c:if>>Ngày  thứ 2</option>
                                            <option value="3" <c:if test="${ganday=='3'}">selected</c:if>>Ngày  thứ 3</option>
                                        </select>
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
                                                <td width="20%" class="bol">${lotocap.solanve1}</td>
                                                <td width="20%" class="bol">
                                                    <c:if test="${ganday=='1'}">
                                                    ${lotocap.solanve1}    
                                                    </c:if>
                                                </td>
                                                <td width="20%" class="bol">
                                                    <c:if test="${ganday=='2'}">
                                                    ${lotocap.solanve1}    
                                                    </c:if>
                                                </td>
                                                <td width="20%" class="bol">
                                                    <c:if test="${ganday=='3'}">
                                                    ${lotocap.solanve1}    
                                                    </c:if>
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                <td width="8.7%" class="bol" >
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
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="0">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="0">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="0" end="0">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="1" end="1">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="1" end="1">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="1" end="1">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="2" end="2">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="2" end="2">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="2" end="2">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="3" end="3">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="3" end="3">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="3" end="3">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="4" end="4">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="4" end="4">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="4" end="4">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="5" end="5">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="5" end="5">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="5" end="5">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="6" end="6">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="6" end="6">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="6" end="6">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="7" end="7">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="7" end="7">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="7" end="7">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="8" end="8">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="8" end="8">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="8" end="8">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='1'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="9" end="9">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='2'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="9" end="9">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
                                                            </c:forEach>                                                            
                                                        </c:forEach>
                                                        </c:if>
                                                    </td>
                                                    <td width="2.9%" class="bol" >
                                                        <c:if test="${ganday=='3'}">
                                                        <c:forEach items="${dacbiet.listLotoCap1}" var="cap1">
                                                            <c:forEach items="${listLotoCap}" var="lotocap" begin="9" end="9">
                                                                <c:if test="${cap1.cap==lotocap.cap&&cap1.loto1!='no'}">${cap1.loto1}</c:if>
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
                <%--<%@include file="include/footer.jsp" %>--%>
            </div>
    </body>
</html>
