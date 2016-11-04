<%-- 
    Document   : index
    Created on : 14-Jan-2016, 08:22:23
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/thong-ke.css">
        <style>
            #option{
                border: 1px solid #d0d0d0;
                padding: 5px;
                background: white;
                font-size: 14px;
                line-height: 30px;
            }
        </style>
        <link href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
        <script>
            $(function() {
                $('#biendongay').datepicker({
                    inline: true,
                    showOtherMonths: true,
                    dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                });
            });                        
            $(document).ready(function(){
                var listOption = $("#companySl option.skq");
                var url;
                for(var i=0; i < listOption.size(); i++){
                    url = make_friendly_link(listOption.eq(i).text());
                    listOption.eq(i).attr("url", url);
                }
                
                $("input#clickSKQ").click(function(){
                    var options = $("#companySl option:selected");
                    var option = options.eq(0);
                    var url = '${pageContext.servletContext.contextPath}';
                    
//                    if(!url) {
//                        url = "xoso";
//                    }
                    url += '/ket-qua-xo-so-' + option.attr("url") + '-' + option.attr("value").toLowerCase() + '.html';
                    var biendongay = $("div#option input#biendongay").val();
                    var khoangNgay = $("div#option input[name=khoangNgay]").val();
                    url += '?d='+biendongay+'&kn='+khoangNgay;
                    window.location.href = url;
                    //alert(url);
                });
            });
        </script>
        <script>
            $(function() {
                loadThongKeMienBac('5', '${code}');
            });

            function loadThongKeMienBac(week, code) {
                $('#thongkemb').load("${pageContext.servletContext.contextPath}/ajax/thongke_mienbac.htm?week=" + week + "&code=" + code, function(responseTxt, statusTxt, xhr) {
                    if (statusTxt === "success") {
                        $("#thongkemb").html();
                    } else {
                        //loi
                        //alert("Error:112 " + xhr.status + ": " + xhr.statusText);
                    }
                });
            }
        </script>
        <script>
            console.log('nao chay di nao ${isOpenDate}');
            <c:set var="kv" value="MB"/>
            <c:if test="${listLotteryCompanyMT!=null}">
                <c:forEach items="${listLotteryCompanyMT}" var="company" varStatus="status">
                    <c:if test="${company.code==code}"><c:set var="kv" value="MT"/></c:if>
                </c:forEach>
            </c:if>
            <c:if test="${listLotteryCompanyMN!=null}">
                <c:forEach items="${listLotteryCompanyMN}" var="company" varStatus="status">
                    <c:if test="${company.code==code}"><c:set var="kv" value="MN"/></c:if>
                </c:forEach>
            </c:if>
            var kv = "${kv}";
            $(function() {
                console.log('nao chay di nao');
                loadKQNew();
                checkTimeLive();
                var t = setInterval(function() {
                    checkTimeLive();
                }, 5000);
            });


            function checkTimeLive() {
                currHHMMSS = getCurrHHMMSS();
                console.log('currHHMMSS==>>>>>' + currHHMMSS);
                if (currHHMMSS >= 161000 && currHHMMSS < 163800 && kv === "MN") {
                    // tuong thuat truc tiep mien nam
                    loadXSLive('${code}', 'tructiep');
                } else if (currHHMMSS >= 171000 && currHHMMSS < 173800 && kv === "MT") {
                    // tuong thuat truc tiep mien trung
                    loadXSLive('${code}', 'tructiep');
                } else if (currHHMMSS >= 181000 && currHHMMSS < 183800 && kv === "MB") {
                    //tuong thuat truc tiep mien bac
                    loadXSLive('${code}', 'tructiep');
                }
                console.log('checkTimeLive');
                return false;
            }

            function loadAjax(url, id) {
                $.ajax({
                    url: url,
                    dataType: 'text',
                    cache: false
                }).done(function(sResponse) {
                    console.log(sResponse);
                    document.getElementById(id).innerHTML = sResponse;
                });
            }

            function loadKQNew() {
                currHHMMSS = getCurrHHMMSS();
                var loadNew = '${loadNew}';
                if (currHHMMSS >= 163800 && currHHMMSS < 191100) {
                    if (loadNew === 'yes') {
                        loadXSLive('${code}', 'tructiep');
                    }
                }
            }

            function loadXSLive(region, id) {
                loadAjax("${pageContext.servletContext.contextPath}/ajax/live.htm?r=" + region, id);
            }
        </script>
        <style>
            .box_so_left{
                width: 400px;
                float: left;
                margin-right: 6px;
            }
            .dit-loto{
                display: none;
            }
            .dd-loto-widget table {
                width: 200px;
            }
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
                        <div id="content-tructiep">
                            <table style="width: 100%">						
                                <tr>
                                    <td colspan="2">
                                        <div class="thong-ke">
                                            <div class="boxlist" id="box_lmthn" style="display: none">
                                                <h2 class="h1title2"><a href="#">Mở thưởng hôm nay - ngày ${today}</a></h2>
                                                <table border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td class="region-column">
                                                            <div class="subcate">
                                                                <ul>
                                                                    <li><a href="${strUrl}/dien-toan.html"> Điện toán </a></li>
                                                                    <li><a href="${strUrl}/than-tai.html">Thần tài</a></li>
                                                                    <li><a href="${strUrl}/xsmb-ket-qua-xo-so-mien-bac-xstd.html">Truyền thống</a></li>
                                                                        <c:if test="${totalRowCompanyOpen>3}">
                                                                            <c:forEach begin="3" end="${totalRowCompanyOpen-1}">
                                                                            <li><a style="background:none;">&nbsp;</a></li>
                                                                            </c:forEach> 
                                                                        </c:if>
                                                                </ul>
                                                            </div>
                                                        </td>
                                                        <td class="region-column">
                                                            <div class="subcate">
                                                                <ul>
                                                                    <c:if test="${listLotteryCompanyMT!=null}">
                                                                        <c:set var="count"/>
                                                                        <c:forEach items="${listLotteryCompanyMT}" var="company" varStatus="status">
                                                                            <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>
                                                                            <c:if test="${company.code=='XSTTH'}">
                                                                                <c:set var="link" value="ket-qua-xo-so-thua-thien-hue-${company.codeLowerCase}.html"/>
                                                                                <li><a href="${strUrl}/${link}" title="xổ số ${company.company}">Thừa Thiên Huế</a></li>
                                                                                </c:if>
                                                                                <c:if test="${company.code=='XSBDH'}">
                                                                                    <c:set var="link" value="ket-qua-xo-so-binh-dinh-${company.codeLowerCase}.html"/>
                                                                                <li><a href="${strUrl}/${link}" title="xổ số ${company.company}">${company.company}</a></li>
                                                                                </c:if>    
                                                                                <c:if test="${company.code!='XSTTH'&&company.code!='XSBDH'}">                                          
                                                                                <li><a href="${strUrl}/${link}" title="xổ số ${company.company}">${company.company}</a></li>
                                                                                </c:if>
                                                                                <c:set var="count" value="${status.count}"/>   
                                                                            </c:forEach>                                   
                                                                            <c:if test="${count<totalRowCompanyOpen}">
                                                                                <c:forEach begin="${count}" end="${totalRowCompanyOpen-1}">
                                                                                <li><a style="background:none;">&nbsp;</a></li>
                                                                                </c:forEach> 
                                                                            </c:if>   
                                                                        </c:if>                                   
                                                                </ul>
                                                            </div>
                                                        </td>
                                                        <td class="region-column">
                                                            <div class="subcate">
                                                                <ul>
                                                                    <c:if test="${listLotteryCompanyMN!=null}">
                                                                        <c:forEach items="${listLotteryCompanyMN}" var="company" varStatus="status">
                                                                            <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>                                       
                                                                            <li><a href="${strUrl}/${link}" title="xổ số ${company.company}">${company.company}</a></li>
                                                                                <c:set var="count" value="${status.count}"/>
                                                                            </c:forEach>                                      
                                                                            <c:if test="${count<totalRowCompanyOpen}">
                                                                                <c:forEach begin="${count}" end="${totalRowCompanyOpen}">
                                                                                <li><a>&nbsp;</a></li>
                                                                                </c:forEach> 
                                                                            </c:if>    
                                                                        </c:if>                                  
                                                                </ul>                               
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div id="option">
                                                <form>
                                                    Biên độ ngày muốn xem: <input id="biendongay" name="bienDoNgay" value="${ddmmyyyy}"/><br/>
                                                    Khoảng ngày muốn xem: <input name="khoangNgay" maxlength="2" value="${kn}"/><br/>
                                                    Chọn tỉnh: <select id="companySl" name="tinh">
                                                                    <option value="xstd" url="mien-bac">Truyền thống</option>
                                                                    <c:forEach items="${listCompany}" var="company">
                                                                        <c:if test="${company.company != 'Miền Bắc'}">
                                                                                <option class="skq" value="${company.code}" <c:out value="${company.code == code ? 'selected': ''}"/>>${company.company}</option>                                                                                
                                                                        </c:if>
                                                                    </c:forEach>
                                                               </select>
                                                    <input type="button" id="clickSKQ" value="Xem kết quả"/>
                                                </form>
                                            </div>
                                            <div id="tructiep"></div>
                                            <div class="box_kqxs box_cc">
                                                <div id="kqxsmb" style="display: none">
                                                    <div class="result-header">
                                                        <h2>THỐNG KÊ KẾT QUẢ ĐẶC BIỆT</h2>
                                                    </div>
                                                    <div class="box_so tkdb1">
                                                        <table width="100%" cellspacing="0" cellpadding="0" border="0">
                                                            <tbody>
                                                                <tr>
                                                                    <th>Thứ</th>
                                                                    <th>Ngày</th>
                                                                    <th>Đặc Biệt</th>
                                                                    <th>Giải 8</th>
                                                                </tr>                               
                                                                <c:if test="${listDacBietMN!=null}">
                                                                    <c:set var="class1" value=""/>
                                                                    <c:forEach items="${listDacBietMN}" var="db" begin="0" end="6" varStatus="status">
                                                                        <c:if test="${status.index%2>0}"><c:set var="class" value="bg_gray"/></c:if>
                                                                        <c:if test="${status.index%2==0}"><c:set var="class" value=""/></c:if>    
                                                                        <tr class="${class1}">
                                                                            <td>${db.thu}</td>
                                                                            <td>${db.ddmmyyyy}</td>
                                                                            <td class="so_kq">${db.db1}</td>
                                                                            <td class="so_kq"><span class="red">${db.g81}</span></td>
                                                                        </tr>         
                                                                    </c:forEach>
                                                                </c:if>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- ket qua xo so tinh-->
                                            <c:if test="${listLottery!=null && code ne 'XSTD'}">
                                                <c:set var="index" value="0"/>
                                                <c:forEach items="${listLottery}" var="lottery" begin="0" end="0">
                                                    <div class="box_kqxs box_cc" id="ketquatinh">
                                                        <div id="kqxsmb">
                                                            <div class="result-header">
                                                                <h2 ><a href="" title="kết quả xổ số ${lottery.province}">KẾT QUẢ XỔ SỐ ${lottery.province}</a> Ngày ${lottery.openDate}</h2>                               
                                                            </div>                      
                                                            <div class="box_so">
                                                                <div class="box_so_left">  
                                                                    <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#c9c9c9">
                                                                        <tbody>                                
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải tám</td>
                                                                                <td colspan="12" class="web_XS_2 chukq"><span class="do">${lottery.eighth}</span></td>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải bảy</td>
                                                                                <td colspan="12" class="web_XS_2 chukq">${lottery.seventh}</td>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải sáu</td>
                                                                                <c:forTokens items="${lottery.sixth}" delims="-" var="sixth">
                                                                                    <td colspan="4" class="web_XS_2 chukq">${sixth}</td>    
                                                                                </c:forTokens>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td  class="web_XS_1 chugiai">Giải năm</td>
                                                                                <c:forTokens items="${lottery.fifth}" delims="-" var="fifth" >
                                                                                    <td colspan="12" class="web_XS_2 chukq">${fifth}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>

                                                                            <tr class="web_bg_Trang">
                                                                                <td rowspan="2" class="web_XS_1 chugiai">Giải Tư</td>
                                                                                <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="0" end="2">
                                                                                    <td colspan="4" class="web_XS_2 chukq">${fourth}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="3" end="6" >
                                                                                    <td colspan="3" class="web_XS_2 chukq">${fourth}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải ba</td>
                                                                                <c:forTokens items="${lottery.third}" delims="-" var="third" >
                                                                                    <td colspan="6" class="web_XS_2 chukq">${third}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>

                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải nhì</td>
                                                                                <c:forTokens items="${lottery.second}" delims="-" var="second"  >
                                                                                    <td colspan="12" class="web_XS_2 chukq">${second}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải nhất</td>
                                                                                <c:forTokens items="${lottery.first}" delims="-" var="first"  >
                                                                                    <td colspan="12" class="web_XS_2 chukq">${first}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Đặc biệt</td>
                                                                                <c:forTokens items="${lottery.special}" delims="-" var="special"  >
                                                                                    <td colspan="12" class="web_XS_2 chukq"><span class="do">${special}</span></td>    
                                                                                    </c:forTokens>                                  
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>                                             
                                                                <div class="boxseo bogoc2" style="display: none">
                                                                    Chờ kết quả :<span class="red"> XS${f:replace(code, "XS", "")} CHO</span> gửi <span class="red">8185</span>
                                                                    <c:if test="${lotteryCompany.region=='MB'}">
                                                                        <br/>
                                                                        Số đẹp hôm nay :<span class="red"> CAU</span> gửi <span class="red">8585</span>
                                                                    </c:if>
                                                                    <c:if test="${lotteryCompany.region!='MB'}">
                                                                        <br/>
                                                                        Lấy bộ số thần tài :<span class="red"> CAU ${f:replace(code, "XS", "")}</span> gửi <span class="red">8585</span>
                                                                    </c:if>    
                                                                </div>
                                                                <div class="box_so_right dd-loto-widget">
                                                                    <c:if test="${listLotteryDuoi!=null}">
                                                                        <c:forEach items="${listLotteryDuoi}" var="listDuoi" begin="${index}" end="${index}">
                                                                            <table cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                                                <tbody>
                                                                                    <tr class="bg_head">
                                                                                        <td class="web_XS_3 web_bg_Loto"><h3>Đầu</h3></td>
                                                                                        <td class="web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                                    </tr>
                                                                                    <c:forEach items="${listDuoi}" var="duoi" varStatus="dau">
                                                                                        <tr class="web_bg_Trang_1">
                                                                                            <td class="web_XS_3 web_bg_xam">${dau.index}</td>
                                                                                            <td class="web_XS_4 web_bg_xam">${duoi}</td>                                   
                                                                                        </tr>                       
                                                                                    </c:forEach>                                                                 
                                                                                </tbody>
                                                                            </table>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <c:if test="${listLotteryDau!=null}">
                                                                        <c:forEach items="${listLotteryDau}" var="listDau" begin="${index}" end="${index}">
                                                                            <table class="dit-loto" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                                                <tbody>
                                                                                    <tr class="bg_head">
                                                                                        <td class="text_rightmb web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                                        <td class="web_XS_3 web_bg_Loto"><h3>Đuôi</h3></td>                                   
                                                                                    </tr>
                                                                                    <c:forEach items="${listDau}" var="dau" varStatus="duoi">
                                                                                        <tr class="web_bg_Trang_1">
                                                                                            <td class="text_rightmb web_XS_4 web_bg_xam">${dau}</td>                                   
                                                                                            <td class="web_XS_3 web_bg_xam">${duoi.index}</td>
                                                                                        </tr>                       
                                                                                    </c:forEach>                                
                                                                                </tbody>
                                                                            </table>     
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <div class="clearfix"></div>


                                                                </div>
                                                            </div>   
                                                        </div>
                                                    </div> 
                                                    <div class="clearfix"></div>
                                                    <c:set var="index" value="${index+1}"/>          
                                                </c:forEach>
                                            </c:if>
                                            <div class="box_kqxs box_cc" style="display: none">
                                                <div>
                                                    <div class="result-header">
                                                        <h2 >Thống kê kết quả xổ số ${lotteryCompany.company}
                                                            <input type="hidden" id="code" value="${code}"/>
                                                            <select class="select" id="week" onchange="onChage();">
                                                                <option <c:if test="${week=='5'}">selected=""</c:if> value="5">5</option> 
                                                                <option <c:if test="${week=='10'}">selected=""</c:if> value="10">10</option>
                                                                <option <c:if test="${week=='15'}">selected=""</c:if> value="15">15</option>
                                                                </select>
                                                                tuần
                                                            </h2>
                                                        </div>                         
                                                        <div  id="thongkemb"></div>
                                                    </div>                         
                                                </div>
                                            <c:if test="${listLottery!=null && code ne 'XSTD'}">                                                
                                                <c:set var="index" value="1"/>
                                                <c:forEach items="${listLottery}" var="lottery" begin="1">
                                                    <div class="box_kqxs box_cc">
                                                        <div id="kqxsmb">
                                                            <div class="result-header">
                                                                <h2 ><a href="" title="kết quả xổ số ${lottery.province}">KẾT QUẢ XỔ SỐ ${lottery.province}</a> Ngày ${lottery.openDate}</h2>                               
                                                            </div>                      
                                                            <div class="box_so">
                                                                <div class="box_so_left">  
                                                                    <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#c9c9c9">
                                                                        <tbody>                                
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải tám</td>
                                                                                <td colspan="12" class="web_XS_2 chukq"><span class="do">${lottery.eighth}</span></td>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải bảy</td>
                                                                                <td colspan="12" class="web_XS_2 chukq">${lottery.seventh}</td>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải sáu</td>
                                                                                <c:forTokens items="${lottery.sixth}" delims="-" var="sixth">
                                                                                    <td colspan="4" class="web_XS_2 chukq">${sixth}</td>    
                                                                                </c:forTokens>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td  class="web_XS_1 chugiai">Giải năm</td>
                                                                                <c:forTokens items="${lottery.fifth}" delims="-" var="fifth" >
                                                                                    <td colspan="12" class="web_XS_2 chukq">${fifth}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>

                                                                            <tr class="web_bg_Trang">
                                                                                <td rowspan="2" class="web_XS_1 chugiai">Giải Tư</td>
                                                                                <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="0" end="2">
                                                                                    <td colspan="4" class="web_XS_2 chukq">${fourth}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" begin="3" end="6" >
                                                                                    <td colspan="3" class="web_XS_2 chukq">${fourth}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải ba</td>
                                                                                <c:forTokens items="${lottery.third}" delims="-" var="third" >
                                                                                    <td colspan="6" class="web_XS_2 chukq">${third}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>

                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải nhì</td>
                                                                                <c:forTokens items="${lottery.second}" delims="-" var="second"  >
                                                                                    <td colspan="12" class="web_XS_2 chukq">${second}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải nhất</td>
                                                                                <c:forTokens items="${lottery.first}" delims="-" var="first"  >
                                                                                    <td colspan="12" class="web_XS_2 chukq">${first}</td>    
                                                                                </c:forTokens>                                  
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Đặc biệt</td>
                                                                                <c:forTokens items="${lottery.special}" delims="-" var="special"  >
                                                                                    <td colspan="12" class="web_XS_2 chukq"><span class="do">${special}</span></td>    
                                                                                    </c:forTokens>                                  
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>                                             
                                                                <div class="boxseo bogoc2" style="display: none">
                                                                    Chờ kết quả :<span class="red"> XS${f:replace(code, "XS", "")} CHO</span> gửi <span class="red">8185</span>
                                                                    <c:if test="${lotteryCompany.region=='MB'}">
                                                                        <br/>
                                                                        Số đẹp hôm nay :<span class="red"> CAU</span> gửi <span class="red">8585</span>
                                                                    </c:if>
                                                                    <c:if test="${lotteryCompany.region!='MB'}">
                                                                        <br/>
                                                                        Lấy bộ số thần tài :<span class="red"> CAU ${f:replace(code, "XS", "")}</span> gửi <span class="red">8585</span>
                                                                    </c:if>
                                                                </div>
                                                                <div class="box_so_right dd-loto-widget">
                                                                    <c:if test="${listLotteryDuoi!=null}">
                                                                        <c:forEach items="${listLotteryDuoi}" var="listDuoi" begin="${index}" end="${index}">
                                                                            <table cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                                                <tbody>
                                                                                    <tr class="bg_head">
                                                                                        <td class="web_XS_3 web_bg_Loto"><h3>Đầu</h3></td>
                                                                                        <td class="web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                                    </tr>
                                                                                    <c:forEach items="${listDuoi}" var="duoi" varStatus="dau">
                                                                                        <tr class="web_bg_Trang_1">
                                                                                            <td class="web_XS_3 web_bg_xam">${dau.index}</td>
                                                                                            <td class="web_XS_4 web_bg_xam">${duoi}</td>                                   
                                                                                        </tr>                       
                                                                                    </c:forEach>                                                                 
                                                                                </tbody>
                                                                            </table>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <c:if test="${listLotteryDau!=null}">
                                                                        <c:forEach items="${listLotteryDau}" var="listDau" begin="${index}" end="${index}">
                                                                            <table class="dit-loto" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                                                <tbody>
                                                                                    <tr class="bg_head">
                                                                                        <td class="text_rightmb web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                                        <td class="web_XS_3 web_bg_Loto"><h3>Đuôi</h3></td>                                   
                                                                                    </tr>
                                                                                    <c:forEach items="${listDau}" var="dau" varStatus="duoi">
                                                                                        <tr class="web_bg_Trang_1">
                                                                                            <td class="text_rightmb web_XS_4 web_bg_xam">${dau}</td>                                   
                                                                                            <td class="web_XS_3 web_bg_xam">${duoi.index}</td>
                                                                                        </tr>                       
                                                                                    </c:forEach>                                
                                                                                </tbody>
                                                                            </table>     
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <div class="clearfix"></div>


                                                                </div>
                                                            </div>   
                                                        </div>
                                                    </div>         
                                                    <c:set var="index" value="${index+1}"/>          
                                                </c:forEach>
                                            </c:if>
                                            <!--xo so mien bac-->
                                            <c:if test="${listLottery!=null && code eq 'XSTD'}">
                                                <c:set var="index" value="0"/>
                                                <c:forEach items="${listLottery}" var="lottery" begin="0">
                                                    <div class="box_kqxs box_cc" id="ketquatinh">
                                                        <div id="kqxsmb">
                                                            <div class="result-header">
                                                                <h2 ><a href="" title="kết quả xổ số ${lottery.province}">KẾT QUẢ XỔ SỐ ${lottery.province}</a> Ngày ${lottery.openDate}</h2>                               
                                                            </div>                      
                                                            <div class="box_so">
                                                                <div class="box_so_left">  
                                                                    <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                                        <tbody>                               
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Đặc biệt</td>
                                                                                <td colspan="12" class="web_XS_2 chukq"><span class="do">${lottery.special}</span></td>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải nhất</td>
                                                                                <td colspan="12" class="web_XS_2 chukq">${lottery.first}</td>
                                                                            </tr>
                                                                            <tr class="web_bg_Trang">
                                                                                <td class="web_XS_1 chugiai">Giải nhì</td>
                                                                        <c:forTokens items="${lottery.second}" delims="-" var="second">
                                                                            <td colspan="6" class="web_XS_2 chukq">${second}</td>    
                                                                        </c:forTokens>
                                                                        </tr>
                                                                        <tr class="web_bg_Trang">
                                                                            <td rowspan="2" class="web_XS_1 chugiai">Giải ba</td>
                                                                        <c:forTokens items="${lottery.third}" delims="-" var="third" begin="0" end="2">
                                                                            <td colspan="4" class="web_XS_2 chukq">${third}</td>    
                                                                        </c:forTokens>                                  
                                                                        </tr>
                                                                        <tr class="web_bg_Trang">
                                                                        <c:forTokens items="${lottery.third}" delims="-" var="third" begin="3" end="5">
                                                                            <td colspan="4" class="web_XS_2 chukq">${third}</td>    
                                                                        </c:forTokens>
                                                                        </tr>
                                                                        <tr class="web_bg_Trang">
                                                                            <td class="web_XS_1 chugiai">Giải tư</td>
                                                                        <c:forTokens items="${lottery.fourth}" delims="-" var="fourth" >
                                                                            <td colspan="3" class="web_XS_2 chukq">${fourth}</td>    
                                                                        </c:forTokens>                                  
                                                                        </tr>
                                                                        <tr class="web_bg_Trang">
                                                                            <td rowspan="2" class="web_XS_1 chugiai">Giải năm</td>
                                                                        <c:forTokens items="${lottery.fifth}" delims="-" var="fifth" begin="0" end="2" >
                                                                            <td colspan="4" class="web_XS_2 chukq">${fifth}</td>    
                                                                        </c:forTokens>                                  
                                                                        </tr>
                                                                        <tr class="web_bg_Trang">
                                                                        <c:forTokens items="${lottery.fifth}" delims="-" var="fifth" begin="3" end="5" >
                                                                            <td colspan="4" class="web_XS_2 chukq">${fifth}</td>    
                                                                        </c:forTokens>                                  
                                                                        </tr>
                                                                        <tr class="web_bg_Trang">
                                                                            <td class="web_XS_1 chugiai">Giải sáu</td>
                                                                        <c:forTokens items="${lottery.sixth}" delims="-" var="sixth"  >
                                                                            <td colspan="4" class="web_XS_2 chukq">${sixth}</td>    
                                                                        </c:forTokens>                                  
                                                                        </tr>
                                                                        <tr class="web_bg_Trang">
                                                                            <td class="web_XS_1 chugiai">Giải bảy</td>
                                                                        <c:forTokens items="${lottery.seventh}" delims="-" var="seventh"  >
                                                                            <td colspan="3" class="web_XS_2 chukq">${seventh}</td>    
                                                                        </c:forTokens>                                  
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>                                             
                                                                <div class="boxseo bogoc2" style="display: none">
                                                                    Chờ kết quả :<span class="red"> XS${f:replace(code, "XS", "")} CHO</span> gửi <span class="red">8185</span>
                                                                    <c:if test="${lotteryCompany.region=='MB'}">
                                                                        <br/>
                                                                        Số đẹp hôm nay :<span class="red"> CAU</span> gửi <span class="red">8585</span>
                                                                    </c:if>
                                                                    <c:if test="${lotteryCompany.region!='MB'}">
                                                                        <br/>
                                                                        Lấy bộ số thần tài :<span class="red"> CAU ${f:replace(code, "XS", "")}</span> gửi <span class="red">8585</span>
                                                                    </c:if>    
                                                                </div>
                                                                <div class="box_so_right dd-loto-widget">
                                                                    <c:if test="${listLotteryDuoi!=null}">
                                                                        <c:forEach items="${listLotteryDuoi}" var="listDuoi" begin="${index}" end="${index}">
                                                                            <table cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                                                <tbody>
                                                                                    <tr class="bg_head">
                                                                                        <td class="web_XS_3 web_bg_Loto"><h3>Đầu</h3></td>
                                                                                        <td class="web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                                    </tr>
                                                                                    <c:forEach items="${listDuoi}" var="duoi" varStatus="dau">
                                                                                        <tr class="web_bg_Trang_1">
                                                                                            <td class="web_XS_3 web_bg_xam">${dau.index}</td>
                                                                                            <td class="web_XS_4 web_bg_xam">${duoi}</td>                                   
                                                                                        </tr>                       
                                                                                    </c:forEach>                                                                 
                                                                                </tbody>
                                                                            </table>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <c:if test="${listLotteryDau!=null}">
                                                                        <c:forEach items="${listLotteryDau}" var="listDau" begin="${index}" end="${index}">
                                                                            <table class="dit-loto" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                                                <tbody>
                                                                                    <tr class="bg_head">
                                                                                        <td class="text_rightmb web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                                        <td class="web_XS_3 web_bg_Loto"><h3>Đuôi</h3></td>                                   
                                                                                    </tr>
                                                                                    <c:forEach items="${listDau}" var="dau" varStatus="duoi">
                                                                                        <tr class="web_bg_Trang_1">
                                                                                            <td class="text_rightmb web_XS_4 web_bg_xam">${dau}</td>                                   
                                                                                            <td class="web_XS_3 web_bg_xam">${duoi.index}</td>
                                                                                        </tr>                       
                                                                                    </c:forEach>                                
                                                                                </tbody>
                                                                            </table>     
                                                                        </c:forEach>
                                                                    </c:if>
                                                                    <div class="clearfix"></div>


                                                                </div>
                                                            </div>   
                                                        </div>
                                                    </div>         
                                                    <c:set var="index" value="${index+1}"/>          
                                                </c:forEach>
                                            </c:if>                    
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
