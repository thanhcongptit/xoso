<%-- 
    Document   : thongketansuatloto
    Created on : Nov 3, 2015, 10:10:46 AM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.servletContext.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />        
        <link href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.8.2.js" ></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-1.9.0.custom.min.js" ></script>
        <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
        <script>
            $(function () {
                $('#tungay').datepicker({
                    inline: true,
                    showOtherMonths: true,
                    dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                });
            <c:if test="${capso==null}">checkAll();</c:if>
                });

                function checkAll() {
                    $('input:checkbox').prop('checked', true);
                }

                function checkNotAll() {
                    $('input:checkbox').prop('checked', false);
                }

            </script>
            <style type="text/css">
                .tansuatloto_table{
                    border-right: 1px solid #ccc;
                    border-bottom: 1px solid #ccc;
                    border-top: 1px solid #ccc;
                    border-left: 1px solid #ccc;
                    text-align: center;
                    vertical-align: middle;                
                }

                .tansuatloto_td{
                    border-right: 1px solid #ccc;
                    border-bottom: 1px solid #ccc;      
                    padding-left: 5px;
                    padding-right: 5px;
                }
                .tansuatloto_table th, .tansuatloto_table td{
                    padding: 0;
                }
            </style>
            <title>Thong ke tan suat loto</title>
        </head>
        <body style="background: #fff">
            <table width="100%" style="padding-left: 15px;padding-top: 10px;font-size: 15px;line-height:18px;font-weight: bold">
                <tr><td>Hướng dẫn sử dụng Thống Kê tần suất cặp loto</td></tr>
                <tr>
                    <td >
                        Để xem thống kê tần suất loto bạn chọn biên ngày muốn xem, sau đó chọn khoảng ngày muốn xem đến biên ngày và cuối cùng chọn tỉnh muốn xem rồi click Xem kết quả. Hệ thông sẽ thống kê tần suất loto trong khoảng ngày đó.
                        Bạn muốn xem con loto ở hàng nào thì bạn click vào hàng đó, lập tức hàng bạn muốn xem sẽ bôi da cam, tiện cho việc theo dõi.
                    </td>
                </tr>
                <tr>
                    <td>
                        <form method="post" name="frm">
                            Biên độ ngày:
                            <input type="text" name="tungay" id="tungay" value="${tungay}" class="boxLich"/>
                        &nbsp;
                        Khoảng ngày muốn xem:
                        <input type="number" name="biendo" id="biendo" value="${biendo}" style="height: 24px"/>
                        <br/><br/>
                        <span onclick="checkAll();" style="cursor: pointer">Chọn tất cả</span>   |    <span onclick="checkNotAll();" style="cursor: pointer">Xóa tất cả</span>
                        <br/>
                        <c:if test="${capso==null}">
                            <c:forEach items="${capLoto}" var="i" varStatus="status">                        
                                <input type="checkbox" name="capso" value="${i}" id="capso"/>
                                <span style="color: red">${i}</span>
                                <c:if test="${status.count%10==0}"><br/></c:if>                            
                            </c:forEach>
                        </c:if>
                        <c:if test="${capso!=null}">
                            <c:forEach items="${capLoto}" var="i" varStatus="status">
                                <c:set var="chk" value=""/>
                                <c:forEach items="${capso}" var="cs">
                                    <c:if test="${cs==i}"><c:set var="chk" value="checked"/></c:if>
                                </c:forEach> 
                                <input type="checkbox" name="capso" value="${i}" id="capso" ${chk}/>
                                <span style="color: red">${i}</span>
                                <c:if test="${status.count%10==0}"><br/></c:if>                                                                                       
                            </c:forEach>
                        </c:if>
                        <br/>
                        <input type="radio" name="optView" value="0" <c:if test="${optView=='0'}">checked</c:if> onchange="submit()"/>Chiều dọc
                        <input type="radio" name="optView" value="1" <c:if test="${optView=='1'}">checked</c:if> onchange="submit()"/>Chiều ngang
                            <br/>
                            <select name="code">
                            <c:forEach items="${listLotteryCompany}" var="company">
                                <c:if test="${code==company.code}"><c:set var="tinh" value="${company.company}"/></c:if>
                                <option value="${company.code}" <c:if test="${code==company.code}">selected</c:if> >${company.company}</option>
                            </c:forEach>
                        </select>
                        <input type="submit"  value="Xem kết quả" style="height:24px;"/>
                    </form>
                </td>
            </tr>
            <c:if test="${capso != null && listLoto != null && listTansuat != null}">
                <c:set var="rate" value="${100/(fn:length(capso)+1)}"/> 
                <tr>
                    <td>Thống kê tần suất loto của <span style="color: red"> xổ số ${tinh} trong ${biendo} </span> ngày gần nhất:</td>
                </tr>
                <!-- Xem theo chiều dọc -->
                <c:if test="${optView=='0'}">
                    <tr>
                        <td style="padding-top: 10px">
                            <table  width="100%" class="tansuatloto_table" cellspacing="0" cellpadding="0">
                                <tr style="height: 30px">
                                    <td width="${rate}%" class="tansuatloto_td">Tổng số lần về</td>
                                    <c:forEach items="${listLoto}" var="loto">
                                        <c:forEach items="${capso}" var="cs">
                                            <c:if test="${loto.loto==cs}">
                                                <td width="${rate}%" class="tansuatloto_td">&nbsp;${loto.solanxuathien}&nbsp;</td>
                                            </c:if>                                        
                                        </c:forEach>
                                    </c:forEach>
                                </tr>
                                <tr style="height: 30px;background-color: #d5edf8;">
                                    <td width="${rate}%" class="tansuatloto_td">Ngày/Cặp số</td>
                                    <c:forEach items="${capso}" var="cs">
                                        <td width="${rate}%" class="tansuatloto_td">&nbsp;${cs}&nbsp;</td>
                                    </c:forEach>
                                </tr>
                                <c:forEach items="${listTansuat}" var="tansuats">
                                    <tr style="height: 30px">                                                     
                                        <c:forEach items="${tansuats}" var="tansuat" varStatus="status">
                                            <c:if test="${status.index==0}">
                                                <td width="${rate}%" class="tansuatloto_td" >&nbsp;${tansuat.openDate}&nbsp;</td>
                                            </c:if>                                    
                                            <c:forEach items="${capso}" var="cs">
                                                <c:if test="${tansuat.capso==cs}">                       
                                                    <c:if test="${tansuat.solanve>0}"><td width="${rate}%" class="tansuatloto_td">${tansuat.solanve}</td></c:if>
                                                    <c:if test="${tansuat.solanve==0}"><td width="${rate}%" class="tansuatloto_td" style="background: gray">&nbsp;</td></c:if>
                                                </c:if>                                        
                                            </c:forEach>
                                        </c:forEach>           
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </c:if>

                <!-- Xem theo chiều ngang -->
                <c:if test="${optView=='1'}">
                    <tr>                    
                        <td style="padding-top: 10px">
                            <!-- chieu doc--->
                            <table class="tansuatloto_table" cellspacing="0" cellpadding="0">
                                <tr>    
                                    <td>
                                        <table  width="100%" class="tansuatloto_table" cellspacing="0" cellpadding="0">
                                            <c:forEach items="${capso}"  var="cs" varStatus="status">
                                                <c:if test="${status.index==0}">
                                                    <tr style="height: 30px">
                                                        <td width="${rate}%" class="tansuatloto_td" >Capso/<br/>Ngay</td>
                                                    </tr>
                                                </c:if>                                    
                                                <tr style="height: 30px">    
                                                    <td width="${rate}%" class="tansuatloto_td">                                                
                                                        ${cs}
                                                    </td>                                            
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                    <c:forEach items="${listTansuat}" var="tansuats">                                    
                                        <td>
                                            <table  width="100%" class="tansuatloto_table" cellspacing="0" cellpadding="0">
                                                <c:forEach items="${tansuats}" var="tansuat" varStatus="status">
                                                    <c:if test="${tansuat!=null}">
                                                        <c:if test="${status.index==0}">
                                                            <tr style="height: 30px">
                                                                <td width="${rate}%" class="tansuatloto_td" >&nbsp;${fn:substring(tansuat.openDate, 0, 5)}&nbsp;</td>
                                                            </tr>
                                                        </c:if>                                    
                                                        <tr style="height: 30px">    
                                                            <c:if test="${tansuat.solanve>0}"><td width="${rate}%" class="tansuatloto_td" <c:if test="${tansuat.special}">style="color: red"</c:if>>${tansuat.solanve}</td></c:if>
                                                            <c:if test="${tansuat.solanve==0}"><td width="${rate}%" class="tansuatloto_td" style="background: gray">&nbsp;</td></c:if>
                                                            </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </table>
                                        </td>                                 
                                    </c:forEach>
                                    <td width="150px">
                                        <table  width="100%" class="tansuatloto_table" cellspacing="0" cellpadding="0">
                                            <c:forEach items="${listLoto}" var="loto" varStatus="status">
                                                <c:if test="${status.index==0}">
                                                    <tr style="height: 30px">
                                                        <td  class="tansuatloto_td" >Tổng</td>
                                                    </tr>
                                                </c:if>                                    
                                                <tr style="height: 30px">    
                                                    <td width="150px" class="tansuatloto_td">
                                                        ${loto.solanxuathien}
                                                    </td>                                            
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:if>
            </c:if>
        </table>
    </body>
</html>
