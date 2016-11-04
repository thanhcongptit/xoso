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
                                                        Loto Online                        
                                                    </h3>
                                                    <c:if test="${memberLogin eq null}">
                                                        Bạn vui lòng đăng nhập để sử dụng chức năng này!
                                                    </c:if>
                                                    <c:if test="${memberLogin ne null}">
                                                        <div>
                                                            <div style="color: red">${msg}</div>
                                                            <div>
                                                                <form id="frmGhilo" method="post">
                                                                    Ngày: <input readonly="" id="ngayghi" onchange="$('input[name=action]').val('search');$('form#frmGhilo').submit()" name="ngayghi" style="width: 100px" value="${ddmmyyyy}"/> 
                                                                    Cặp số: <input name="capso" style="width: 100px"/> 
                                                                    Số điểm: <input name="sodiem" style="width: 100px"/> 
                                                                    <input name="action" value="ghilo" type="hidden"/>
                                                                    <button type="submit" class="btnGreen">Ghi</button>
                                                                </form>
                                                            </div>
                                                        </div>
                                                        <div style="text-align: right;margin-top: 10px;font-weight: bold">Tài khoản hiện có: <span style="color: green">${memberLogin.money}</span> k</div>
                                                        <div id="betarea" style="border: 1px solid rgb(172, 209, 247);">
                                                            <div style="background:#D2E7FB; padding:5px">
                                                                <a name="betplace"></a> 
                                                                <div style="float:left; font-weight:bold">Ghi lô ngày <b id="ngaydisplay">${ddmmyyyy}</b></div>
                                                                <div style="float:right; font-size:13px; font-weight:bold">
                                                                    Điểm:&nbsp;<span id="tongdiem" style="padding:3px; background:#616A89; color:white">${diem}&nbsp;</span>
                                                                    Chi:&nbsp;<span id="tongchi" style="padding:3px; background:#616A89; color:white">${chi}&nbsp;k</span>
                                                                    Nhận:&nbsp;<span id="tongnhan" style="padding:3px; background:#0052CC; color:white">${sNhan}</span>
                                                                    Lãi-lỗ:&nbsp;${sLailo}
                                                                </div>
                                                                <div style="clear:both"></div>
                                                            </div>
                                                            <div id="betcontainer" style="padding:5px; position:relative">
                                                                <c:if test="${listCapso ne null}">
                                                                    <c:forEach items="${listCapso}" var="capso">
                                                                        <div id="bet_${capso.capso}" class="bet">
                                                                            <span class="betnum">${capso.capso}</span><br>
                                                                            <span class="bet_value" id="betvalue_${capso.capso}">${capso.sodiem}</span>&nbsp;điểm
                                                                            <c:if test="${capso.nhay ne null && capso.nhay > 0}">
                                                                                <div class="trung-nhay">${capso.nhay}</div>
                                                                            </c:if>                                                                            
                                                                        </div>
                                                                    </c:forEach>
                                                                </c:if>
                                                                <div style="clear:both"></div>
                                                            </div>
                                                        </div>
                                                        <div style="margin-top: 10px;cursor: pointer"><a class="aHover" onclick="$('#aboutloonline').toggle();">(*) Cách tính thưởng Loto:</a></div>
                                                        <div id="aboutloonline" style="margin: 5px 0px; display: block;">
                                                            <span style="display:inline-block; color:#414141; padding:5px; background:#F8F8F8; border:#DADADA 1px solid">
                                                                <div>- Bạn phải chi 23k cho 1 điểm lô được ghi.</div>
                                                                <div>- Bạn nhận được 80k cho mỗi điểm đã ghi nếu trúng 1 nháy, và nhận được n lần 80k nếu trúng n nháy.</div>
                                                            </span>
                                                        </div>
                                                        <div style="margin-top:10px; border:#BFC9D2 1px solid; float:left">
                                                            <div style="background:#C7CFD8; padding:5px; font-weight:bold">Thống kê quá trình chơi</div>
                                                            <div id="lichsu">
                                                                <c:if test="${listHistoryOnline ne null}">
                                                                    <c:forEach items="${listHistoryOnline}" var="history">
                                                                        <table class="lichsu" cellspacing="1" cellpadding="5">
                                                                            <tbody>
                                                                                <tr>
                                                                                    <th>Ngày</th>
                                                                                    <th>Lô</th>
                                                                                    <th>Điểm</th>
                                                                                    <th>Nháy</th>
                                                                                    <th>Thắng/thua</th>
                                                                                </tr>
                                                                                <c:forEach items="${history.listCapso}" var="capso" varStatus="i">
                                                                                    <tr>
                                                                                        <c:if test="${i.index == 0}">
                                                                                            <td valign="top" rowspan="${fn:length(history.listCapso)}">
                                                                                                <a href="#betplace"><fmt:formatDate pattern="dd-MM-yyyy" value="${history.ngaychoi}"/></a>
                                                                                            </td>
                                                                                        </c:if>                                                                                        
                                                                                        <td>
                                                                                            <a href="#" class="betnum_ls">${capso.capso}</a>
                                                                                        </td>
                                                                                        <td>${capso.sodiem}</td>
                                                                                        <td>${capso.nhay}</td>
                                                                                        <c:set var="thangThua" value="${80 * capso.sodiem * capso.nhay - 23 * capso.sodiem}"/>
                                                                                        <td class="${thangThua >= 0 ? "pos" : "neg"}">${thangThua}</td>
                                                                                    </tr>
                                                                                </c:forEach>                                                                                                                                                                
                                                                                <tr class="lichsu_lastrow">
                                                                                    <td colspan="2">
                                                                                        <span style="font-weight:normal; color:#274B69">Tài&nbsp;khoản:</span>&nbsp;
                                                                                        <span class="pos">${history.tienHientai + history.tongThangthua}</span>
                                                                                    </td><td>${history.tongDiem}</td>
                                                                                    <td>${history.tongNhay}</td>
                                                                                    <td class="pos">${history.tongThangthua}</td>
                                                                                </tr>
                                                                            </tbody>
                                                                        </table>    
                                                                    </c:forEach>
                                                                </c:if>                                                                
                                                            </div>                                                        
                                                        </div>
                                                        <div style="clear:both"></div>
                                                    </c:if>
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
