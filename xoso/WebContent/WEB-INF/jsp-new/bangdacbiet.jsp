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
                        <table width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ BẢNG ĐẶC BIỆT TUẦN XỔ SỐ MIỀN BẮC</h2></td>
                            </tr>                            
                            <tr>
                                <td style="text-align: center;padding-top: 5px">
                                    <form method="post">
                                        Từ ngày
                                        <input type="text" id="tungay" name="tungay" class="boxLich" value="${tungay}"/>
                                        Đến ngày
                                        <input type="text" id="denngay" name="denngay" class="boxLich" value="${denngay}"/>
                                        <br/><br/>
                                        Hiển thị:
                                        <input type="radio" name="opt" value="0" <c:if test="${opt=='0'}">checked</c:if>/>Thông thường
                                        <input type="radio" name="opt" value="1" <c:if test="${opt=='1'}">checked</c:if>/>2 số cuối
                                        <input type="radio" name="opt" value="2" <c:if test="${opt=='2'}">checked</c:if>/>Đầu
                                        <input type="radio" name="opt" value="3" <c:if test="${opt=='3'}">checked</c:if>/>Đít
                                        <input type="radio" name="opt" value="4" <c:if test="${opt=='4'}">checked</c:if>/>Tổng
                                        <br/><br/>
                                        <input type="submit" value="Xem kết quả"/>
                                    </form>
                                </td>
                            </tr>                            
                            <c:if test="${list!=null}">
                                <tr>
                                    <td style="padding-top: 5px">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0">
                                            <tr style="background-color: #d5edf8;height: 30px">
                                                <td  class="bol">Thứ hai</td>
                                                <td  class="bol">Thứ ba</td>
                                                <td  class="bol">Thứ tư</td>
                                                <td  class="bol">Thứ năm</td>
                                                <td  class="bol">Thứ sáu</td>
                                                <td  class="bol">Thứ bẩy</td>
                                                <td  class="bol">Chủ Nhật</td>
                                            </tr>
                                            <c:forEach items="${list}" var="dacbiet" varStatus="status">
                                                <fmt:parseNumber type="number" var="thu" value="8"/>
                                                <c:if test="${dacbiet.thu!='cn'}">
                                                    <fmt:parseNumber type="number" var="thu" value="${dacbiet.thu}"/>
                                                </c:if>
                                                <c:if test="${status.index==0||dacbiet.thu=='2'}">
                                                <tr style="text-align: center">
                                                </c:if>    
                                                    <c:if test="${dacbiet.thu=='2'}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">${dacbiet.special}</p>
                                                            <p style="font-size: 10px;">${dacbiet.ngayve}</p>
                                                            <p style="font-size: 12px;color: red">
                                                                <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                                <c:if test="${opt=='1'}">${dacbiet.dau}${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='2'}">${dacbiet.dau}</c:if>
                                                                <c:if test="${opt=='3'}">${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='4'}">${dacbiet.tong}</c:if>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${dacbiet.thu!='2'&&thu>2&&status.index==0}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">&nbsp;</p>
                                                            <p style="font-size: 10px;">&nbsp;</p>
                                                            <p style="font-size: 12px;color: red">
                                                                &nbsp;
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                        
                                                    <c:if test="${dacbiet.thu=='3'}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">${dacbiet.special}</p>
                                                            <p style="font-size: 10px;">${dacbiet.ngayve}</p>
                                                            <p style="font-size: 12px;color: red">
                                                                <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                                <c:if test="${opt=='1'}">${dacbiet.dau}${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='2'}">${dacbiet.dau}</c:if>
                                                                <c:if test="${opt=='3'}">${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='4'}">${dacbiet.tong}</c:if>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${dacbiet.thu!='3'&&thu>3&&status.index==0}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">&nbsp;</p>
                                                            <p style="font-size: 10px;">&nbsp;</p>
                                                            <p style="font-size: 12px;color: red">
                                                                &nbsp;
                                                            </p>
                                                        </td>
                                                    </c:if>    
                                                    <c:if test="${dacbiet.thu=='4'}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">${dacbiet.special}</p>
                                                            <p style="font-size: 10px;">${dacbiet.ngayve}</p>
                                                            <p style="font-size: 12px;color: red">
                                                                <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                                <c:if test="${opt=='1'}">${dacbiet.dau}${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='2'}">${dacbiet.dau}</c:if>
                                                                <c:if test="${opt=='3'}">${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='4'}">${dacbiet.tong}</c:if>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${dacbiet.thu!='4'&&thu>4&&status.index==0}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">&nbsp;</p>
                                                            <p style="font-size: 10px;">&nbsp;</p>
                                                            <p style="font-size: 12px;color: red">
                                                                &nbsp;
                                                            </p>
                                                        </td>
                                                    </c:if>    
                                                        
                                                    <c:if test="${dacbiet.thu=='5'}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">${dacbiet.special}</p>
                                                            <p style="font-size: 10px;">${dacbiet.ngayve}</p>
                                                            <p style="font-size: 12px;color: red">
                                                                <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                                <c:if test="${opt=='1'}">${dacbiet.dau}${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='2'}">${dacbiet.dau}</c:if>
                                                                <c:if test="${opt=='3'}">${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='4'}">${dacbiet.tong}</c:if>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${dacbiet.thu!='5'&&thu>5&&status.index==0}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">&nbsp;</p>
                                                            <p style="font-size: 10px;">&nbsp;</p>
                                                            <p style="font-size: 12px;color: red">
                                                                &nbsp;
                                                            </p>
                                                        </td>
                                                    </c:if>    
                                                    
                                                    <c:if test="${dacbiet.thu=='6'}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">${dacbiet.special}</p>
                                                            <p style="font-size: 10px;">${dacbiet.ngayve}</p>
                                                            <p style="font-size: 12px;color: red">
                                                                <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                                <c:if test="${opt=='1'}">${dacbiet.dau}${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='2'}">${dacbiet.dau}</c:if>
                                                                <c:if test="${opt=='3'}">${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='4'}">${dacbiet.tong}</c:if>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${dacbiet.thu!='6'&&thu>6&&status.index==0}">
                                                        <td  class="bol">
                                                            <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                            <p style="font-size: 14px;font-weight: bold">&nbsp;</p>
                                                            <p style="font-size: 10px;">&nbsp;</p>
                                                            <p style="font-size: 12px;color: red">
                                                                &nbsp;
                                                            </p>
                                                        </td>
                                                    </c:if>    
                                                        
                                                    <c:if test="${dacbiet.thu=='7'}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">${dacbiet.special}</p>
                                                            <p style="font-size: 10px;">${dacbiet.ngayve}</p>
                                                            <p style="font-size: 12px;color: red">
                                                                <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                                <c:if test="${opt=='1'}">${dacbiet.dau}${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='2'}">${dacbiet.dau}</c:if>
                                                                <c:if test="${opt=='3'}">${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='4'}">${dacbiet.tong}</c:if>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${dacbiet.thu!='7'&&thu>7&&status.index==0}">
                                                        <td  class="bol">                                                            
                                                            <p style="font-size: 14px;font-weight: bold">&nbsp;</p>
                                                            <p style="font-size: 10px;">&nbsp;</p>
                                                            <p style="font-size: 12px;color: red">
                                                                &nbsp;
                                                            </p>
                                                        </td>
                                                    </c:if>    
                                                    
                                                    <c:if test="${dacbiet.thu=='cn'}">
                                                        <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">${dacbiet.special}</p>
                                                            <p style="font-size: 10px;">${dacbiet.ngayve}</p>
                                                            <p style="font-size: 12px;color: red">
                                                                <c:if test="${opt==null||opt=='0'}">&nbsp;</c:if>
                                                                <c:if test="${opt=='1'}">${dacbiet.dau}${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='2'}">${dacbiet.dau}</c:if>
                                                                <c:if test="${opt=='3'}">${dacbiet.duoi}</c:if>
                                                                <c:if test="${opt=='4'}">${dacbiet.tong}</c:if>
                                                            </p>
                                                        </td>
                                                    </c:if>    
                                                    <c:if test="${dacbiet.ngayTet > 0}">
                                                        <c:forEach begin="1" end="${dacbiet.ngayTet}">                                                            
                                                            <c:choose>
                                                                <c:when test="${thu eq 8}">
                                                                    <fmt:parseNumber type="number" var="thu" value="2"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <fmt:parseNumber type="number" var="thu" value="${thu + 1}"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            
                                                            <c:if test="${thu eq 2}">
                                                                <tr style="text-align: center">
                                                            </c:if> 
                                                            <td  class="bol">
                                                                <p style="font-size: 14px;font-weight: bold">&nbsp;Nghỉ tết</p>
                                                                <p style="font-size: 10px;">&nbsp;</p>
                                                                <p style="font-size: 12px;color: red">
                                                                    &nbsp;
                                                                </p>
                                                            </td>
                                                            
                                                            <c:if test="${thu eq 8}">
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>                                                        
                                                    </c:if>
                                                <c:if test="${dacbiet.thu=='cn'}">
                                                    </tr>
                                                </c:if>    
                                                
                                            </c:forEach>
                                            <c:if test="${thu<8}">
                                                <c:forEach begin="${thu}" end="7">
                                                    <td  class="bol">
                                                            <p style="font-size: 14px;font-weight: bold">&nbsp;</p>
                                                            <p style="font-size: 10px;">&nbsp;</p>
                                                            <p style="font-size: 12px;color: red">
                                                                &nbsp;
                                                            </p>
                                                        </td>
                                                </c:forEach>
                                                  </tr>      
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
        </div>
    </body>
</html>
