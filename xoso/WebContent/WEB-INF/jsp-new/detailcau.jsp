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
                        <table width="100%" border="1" style="border-color: #aaa;" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">CHI TIẾT KẾT QUẢ CẦU</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 25px">
                                    Chi tiết kết quả thống kê cầu ${cau} ngày của Xổ số Truyền Thống cặp số về nhiều ${loto}
                                    <br/><br/>
                                    Vị trí số ghép lên cầu >> Vị trí 1: ${vitri1}, Vị trí 2: ${vitri2}
                                </td>
                            </tr>
                            <c:if test="${listCaulotoDetail!=null}">   
                                <c:forEach items="${listCaulotoDetail}" var="caulotodetail">
                                <tr>
                                    <td style="padding-top: 25px;font-size: 13px;font-weight: bold;">
                                        <p style="width: 615px;word-wrap: break-word;">${caulotodetail.loto}</p></td>
                                </tr>    
                                <tr>
                                    <td style="padding-top: 25px">
                                        <c:if test="${code=='XSTD'}">
                                        <div class="box_kqxs box_cc" id="ketquatinh">    
                                            <table width="100%" class="bot" cellspacing="0" cellpadding="0" style="text-align: center;font-weight: bold;font-size: 16px;">
                                                <tr style="height: 30px">                                                
                                                    <td class="bol"  colspan="13">
                                                        <div class="result-header">
                                                            <h2>Xo so Mien Bắc Ngày ${caulotodetail.lotteryResult.open_date}</h2>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <td class="bol web_XS_1 chugiai" >Đặc biệt</td>
                                                    <td class="bol web_XS_2 chukq"  colspan="12">${caulotodetail.lotteryResult.special}</td>
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <td class="bol web_XS_1 chugiai" >Giải nhất</td>
                                                    <td class="bol web_XS_2 chukq"  colspan="12">${caulotodetail.lotteryResult.first}</td>
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <td class="bol web_XS_1 chugiai" >Giải nhì</td>
                                                    <c:forTokens items="${caulotodetail.lotteryResult.second}" delims="-" var="second">
                                                    <td class="bol web_XS_2 chukq"  colspan="6">${second}</td>    
                                                    </c:forTokens>                                                
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                     <td class="bol web_XS_1 chugiai"  rowspan="2">Giải ba</td>
                                                    <c:forTokens items="${caulotodetail.lotteryResult.third}" delims="-" var="third" begin="0" end="2">
                                                    <td class="bol web_XS_2 chukq"  colspan="4">${third}</td>    
                                                    </c:forTokens>                                                
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <c:forTokens items="${caulotodetail.lotteryResult.third}" delims="-" var="third" begin="3" end="5">
                                                    <td class="bol web_XS_2 chukq"  colspan="4">${third}</td>    
                                                    </c:forTokens>
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <td class="bol web_XS_1 chugiai" >Giải tư</td>
                                                    <c:forTokens items="${caulotodetail.lotteryResult.fourth}" delims="-" var="fourth">
                                                    <td class="bol web_XS_2 chukq"  colspan="3">${fourth}</td>    
                                                    </c:forTokens>                                                
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <td class="bol web_XS_1 chugiai"  rowspan="2">Giải năm</td>
                                                    <c:forTokens items="${caulotodetail.lotteryResult.fifth}" delims="-" var="fifth" begin="0" end="2">
                                                    <td class="bol web_XS_2 chukq"  colspan="4">${fifth}</td>    
                                                    </c:forTokens>                                                
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <c:forTokens items="${caulotodetail.lotteryResult.fifth}" delims="-" var="fifth" begin="3" end="5">
                                                    <td class="bol web_XS_2 chukq"  colspan="4">${fifth}</td>    
                                                    </c:forTokens>
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <td class="bol web_XS_1 chugiai" >Giải sáu</td>
                                                    <c:forTokens items="${caulotodetail.lotteryResult.sixth}" delims="-" var="sixth">
                                                    <td class="bol web_XS_2 chukq"  colspan="4">${sixth}</td>
                                                    </c:forTokens>                                              
                                                </tr>
                                                <tr style="height: 30px" class="web_bg_Trang">
                                                    <td class="bol web_XS_1 chugiai" >Giải bảy</td>
                                                    <c:forTokens items="${caulotodetail.lotteryResult.seventh}" delims="-" var="seventh">
                                                    <td class="bol web_XS_2 chukq"  colspan="3">${seventh}</td>    
                                                    </c:forTokens>                                                
                                                </tr>
                                            </table>
                                        </div>
                                        </c:if>
                                        <c:if test="${code!='XSTD'}">
                                        <table width="100%" class="bot" cellspacing="0" cellpadding="0" style="font-weight: bold;font-size: 16px;">
                                            <tr style="height: 30px">                                                
                                                <td class="bol"  colspan="13">
                                                    Xo so  Ngày ${caulotodetail.lotteryResult.open_date}
                                                </td>
                                            </tr>
                                            <tr style="height: 30px">
                                                <td class="bol" >Đặc biệt</td>
                                                <td class="bol"  colspan="12">${caulotodetail.lotteryResult.special}</td>
                                            </tr>
                                            <tr style="height: 30px">
                                                <td class="bol" >Giải nhất</td>
                                                <td class="bol"  colspan="12">${caulotodetail.lotteryResult.first}</td>
                                            </tr>
                                            <tr style="height: 30px">
                                                <td class="bol" >Giải nhì</td>
                                                <td class="bol"  colspan="12">${caulotodetail.lotteryResult.second}</td>    
                                            </tr>
                                            <tr style="height: 30px">
                                                 <td class="bol"  >Giải ba</td>
                                                <c:forTokens items="${caulotodetail.lotteryResult.third}" delims="-" var="third">
                                                <td class="bol"  colspan="6">${third}</td>    
                                                </c:forTokens>                                                
                                            </tr>                                            
                                            <tr style="height: 30px">
                                                <td class="bol"  rowspan="2">Giải tư</td>
                                                <c:forTokens items="${caulotodetail.lotteryResult.fourth}" delims="-" var="fourth" begin="0" end="2">
                                                <td class="bol"  colspan="4">${fourth}</td>    
                                                </c:forTokens>                                                
                                            </tr>
                                            <tr style="height: 30px">
                                                <c:forTokens items="${caulotodetail.lotteryResult.fourth}" delims="-" var="fourth" begin="3" end="6">
                                                <td class="bol"  colspan="3">${fourth}</td>    
                                                </c:forTokens>                                                
                                            </tr>
                                            <tr style="height: 30px">
                                                <td class="bol"  >Giải năm</td>
                                                <td class="bol"  colspan="12">${caulotodetail.lotteryResult.fifth}</td>    
                                            </tr>                                            
                                            <tr style="height: 30px">
                                                <td class="bol" >Giải sáu</td>
                                                <c:forTokens items="${caulotodetail.lotteryResult.sixth}" delims="-" var="sixth">
                                                <td class="bol"  colspan="4">${sixth}</td>
                                                </c:forTokens>                                              
                                            </tr>
                                            <tr style="height: 30px">
                                                <td class="bol" >Giải bảy</td>
                                                <td class="bol"  colspan="12">${caulotodetail.lotteryResult.seventh}</td>    
                                            </tr>
                                            <tr style="height: 30px">
                                                <td class="bol" >Giải tám</td>
                                                <td class="bol"  colspan="12">${caulotodetail.lotteryResult.eighth}</td>    
                                            </tr>
                                        </table>
                                        </c:if>
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
