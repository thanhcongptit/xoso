<%-- 
    Document   : index
    Created on : 14-Jan-2016, 08:22:23
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="include/header.jsp" %>        
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/trang-chu.css">
        <script>
            var liveMN = false;
            var loadTime = false;
            var audioElement = document.createElement('audio');
            function playMusic(){
                audioElement.setAttribute('src', 'resources/music/facebook_sound.mp3');
                audioElement.setAttribute('autoplay', 'autoplay');
                //audioElement.load()

                $.get();

                audioElement.addEventListener("load", function() {
                    audioElement.play();
                }, true);
            }
            $(function() {
                sapxep();
                console.log('nao chay di nao');
                countDownMN();
                countDownMT();
                countDownMB();
                checkTimeLive();
                checkLoadTime();

                var t = setInterval(function() {
                    checkTimeLive();
                    checkLoadTime();
                }, 5000);

            });


            function checkLoadTime() {
                console.log('checkLoadTime');

                if (liveMN) {
                    if (!loadTime) {
                        var secondmn = $('#mnsecond').html();
                        console.log("==>>" + secondmn);
                        if (secondmn !== '00') {
                            loadTime = true;
                            showTime('#timemn');
                            hidenKQ('#kqmiennam');
                            hidenKQ('#d2miennam');
                        }
                    }
                    loadXSLive('MN');
                }
            }

            function checkTimeLive() {

                var mnhour = $('#mnhour').html();
                var minumn = $('#mnminu').html();
                var secondmn = $('#mnsecond').html();

                if (mnhour === '00' && minumn === '00' && secondmn === '00') {
                    hidenKQ('#timemn');
                }

                var hourmt = $('#mthour').html();
                var minumt = $('#mtminu').html();
                var secondmt = $('#mtsecond').html();

                if (hourmt === '00' && minumt === '00' && secondmt === '00') {
                    hidenKQ('#timemt');
                }

                var hourmb = $('#mbhour').html();
                var minumb = $('#mbminu').html();
                var secondmb = $('#mbsecond').html();

                if (hourmb === '00' && minumb === '00' && secondmb === '00') {
                    hidenKQ('#timemb');

                }
                currHHMMSS = getCurrHHMMSS();
                console.log('currHHMMSS==>>>>>' + currHHMMSS);
                if (currHHMMSS >= 161000 && currHHMMSS < 163800) {
                    if (mnhour === '00' && minumn === '00' && secondmn === '00') {
                        hidenKQ('#kqmiennam');
                        hidenKQ('#d2miennam');
                    }
                    liveMN = true;
                } else {
                    liveMN = false;
                    loadTime = false;
                    hidenKQ('#timemb');
                    hidenKQ('#timemt');
                    hidenKQ('#timemn');
                }
                console.log('checkTimeLive');
                return false;
            }

            function loadAjax(url, id) {
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'text',
                    cache: false                    
                }).done(function(sResponse) {
                    console.log(sResponse);                    
//                    if( $("#"+id).html().trim().length !== sResponse.trim().length ){
//                        playMusic();
//                    }
                    document.getElementById(id).innerHTML = sResponse;
                });
            }


            function loadXSLive(region) {
                var temp = '${pageContext.servletContext.contextPath}';
                
                if(!temp) {
                    temp = '/xoso'
                }
                console.log('temp: ' + temp);
                
                loadAjax(temp + "/ajax/live.htm?r=" + region, 'tructiep');
            }

            function hidenKQ(id) {
                $(id).hide();
            }
            function showTime(id) {
                $(id).show();
            }

            function sapxep() {
                currHHMMSS = getCurrHHMMSS();
                console.log("currHHMMSS==========>>>>>>>>>>" + currHHMMSS);

                var adv = "<script async src=\"$$pagead2.googlesyndication.compageadXXjsXXadsbygoogle.jsXX\"><XXscript>";
                adv = adv + "<ins class=adsbygoogle style=display:inline-block;width:336px;height:280px data-ad-client=ca-pub-2360743497940079 data-ad-slot=7087078346></ins>";
                adv = adv + "<script>(adsbygoogle = window.adsbygoogle || []).push({});<XXscript>";
                
                adv = adv.replace('$$', '//');
                adv = adv.replace(/XX/g, '/');
                
                adv = "";

                console.log(adv);
                if (currHHMMSS > 163800 && currHHMMSS < 191100) {
                    $('#kqmiennam').hide();
                    $('#d2miennam').hide();
                    loadAjax('${pageContext.servletContext.contextPath}/ajax123/xsmn.html', 'kqmn_new');

                    if (currHHMMSS > 163800 && currHHMMSS < 173800) {
                        $('#adv2').html(adv);
                    }
                }


                if (currHHMMSS > 173800 && currHHMMSS < 191100) {
                    $('#kqmientrung').hide();
                    $('#d2mientrung').hide();
                    loadAjax('${pageContext.servletContext.contextPath}/ajax123/xsmt.html', 'kqmt_new');

                    if (currHHMMSS > 173800 && currHHMMSS < 183800) {
                        $('#adv3').html(adv);
                    }

                }

                if (currHHMMSS > 183800 && currHHMMSS < 191100) {
                    $('#kqmienbac').hide();
                    loadAjax('${pageContext.servletContext.contextPath}/ajax123/xsmb.html', 'kqmb_new');

                    if (currHHMMSS > 183800 && currHHMMSS < 191100) {
                        $('#adv4').html(adv);
                    }
                }

                if (currHHMMSS < 161000 || currHHMMSS > 191100) {
                    $('#adv1').html(adv);
                }

                if (currHHMMSS >= 161000 && currHHMMSS < 163800) {
                    $('#adv0').html(adv);
                } else if (currHHMMSS >= 171000 && currHHMMSS < 173800) {
                    $('#adv0').html(adv);
                } else if (currHHMMSS >= 181000 && currHHMMSS < 183800) {
                    $('#adv0').html(adv);
                }
            }
        </script>
        <script>
            $(document).ready(function(){
               $(function() {
                    $( "#dialog" ).dialog();
               }); 
            });            
        </script>
        <style>
            .thanh-like-share {    
                margin-bottom: 10px;
                text-align: center;
            }
            .thanh-like-share .button {
                float: left;
                margin: 10px 10px 10px 0;
            }
            #content-center #content-tructiep td#tructiep {padding-top: 0px}
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
                        <%@include file="include/body-calendar-left.jsp" %>
                        <%@include file="include/body-lichmothuong-left.jsp" %>
                        <%@include file="include/body-xs3mien-left.jsp" %>
                        <%@include file="include/body-thongkecoban-left.jsp" %>
                        <%@include file="include/body-banner-top5-left.jsp" %>                      
                    </div>					
                    <div id="content-center">
                        <%@include file="include/content-tructiep3mien.jsp" %>   
                        <div id="content-tructiep">
                            <table>
                                <tr>
                                    <td id="tructiep"> 
                                        <div>                
                                            <div class="thanh-like-share">
                                                <div class="button">
                                                    <div class="fb-like" data-href="http://soicaupro.com/trang-chu.html" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
                                                </div>
                                                <div class="button">
                                                    <script src="https://apis.google.com/js/platform.js" async defer>
                                                        {lang: 'vi'}
                                                    </script>
                                                    <div class="g-plus" data-action="share" data-annotation="bubble"></div>
                                                </div>
                                                <div class="button" style="margin-top: 9px;">
                                                    <g:plusone data-size="medium"></g:plusone>
                                                </div>                                                
                                            </div>
                                            <div class="clearfix"></div>
                                            <h2>Trực tiếp Kết Quả Xổ Số</h2>
                                            <h3>Kết quả tự động cập nhật khi đến giờ không cần refresh hoặc F5</h3>
                                        </div>
                                        <div class="box_kqxs box_cc" id="timemb" style="display: none">
                                            <div class="box_so_tg" >
                                                <div class="tttt-wait red">TRỰC TIẾP KẾT QUẢ XỔ SỐ MIỀN BẮC</div> 
                                                <div class="ttxs">
                                                    <div class="tgtt" id="mbhour">00</div>
                                                    <div class="tgtt" id="mbminu">00</div>
                                                    <div class="tgtt" id="mbsecond">00</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="box_kqxs box_cc" id="timemt" style="display: none">
                                            <div class="box_so_tg">
                                                <div class="tttt-wait red">TRỰC TIẾP KẾT QUẢ XỔ SỐ MIỀN TRUNG</div> 
                                                <div class="ttxs" >
                                                    <div class="tgtt" id="mthour">00</div>
                                                    <div class="tgtt" id="mtminu">00</div>
                                                    <div class="tgtt" id="mtsecond">00</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="box_kqxs box_cc" id="timemn" style="display: none">
                                            <div class="box_so_tg">
                                                <div class="tttt-wait red">TRỰC TIẾP KẾT QUẢ XỔ SỐ MIỀN NAM</div> 
                                                <div class="ttxs" >
                                                    <div class="tgtt" id="mnhour">00</div>
                                                    <div class="tgtt" id="mnminu">00</div>
                                                    <div class="tgtt" id="mnsecond">00</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tructiep"></div>
                                        <div  id="adv0" style="text-align: center"></div>
                                        <div id="kqmb_new" class="box_kqxs box_cc"></div>
                                        <div class="adv box_cc" id="adv4" style="text-align: center"></div>
                                        <div id="kqmt_new" class="box_kqxs box_cc"></div>
                                        <div id="d2mt_new" class="box_kqxs box_cc"></div>
                                        <div id="adv3" style="text-align: center"></div>
                                        <div id="kqmn_new" class="box_kqxs box_cc"></div>
                                        <div id="d2mn_new" class="box_kqxs box_cc"></div>                 
                                        <div id="adv2" style="text-align: center"></div>
                                        <!-- ket qua xo so mien bac-->
                                <%--<c:if test="${listLotteryMB!=null}">--%>
                                <c:if test="${1<0}">
                                    <c:forEach items="${listLotteryMB}" var="lottery">
                                        <div class="box_kqxs box_cc" id="kqmienbac">
                                            <div id="kqxsmb">
                                                <div class="result-header">
                                                    <h2 ><a href="${pageContext.servletContext.contextPath}/ket-qua-xo-so-mien-bac-xstd.html" title="kết quả xố số miền bắc">KẾT QUẢ XỔ SỐ MIỀN BẮC XSMB</a> Ngày ${lottery.openDate}</h2>
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
                                                    <div class="boxseo bogoc2" style="display: none;">
                                                        Chờ kết quả :<span class="red"> XSMB CHO</span> gửi <span class="red">8185</span>
                                                        <br/>
                                                        Số đẹp hôm nay :<span class="red"> CAU</span> gửi <span class="red">8585</span>
                                                    </div>                       
                                                    <div class="box_so_right dd-loto-widget">
                                                        <table cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                            <tbody>
                                                                <tr class="bg_head">
                                                                    <td class="web_XS_3 web_bg_Loto"><h3>Đầu</h3></td>
                                                                    <td class="web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                </tr>
                                                            <c:forEach items="${listLotteryDuoiMB}" var="duoi" varStatus="dau">
                                                                <tr class="web_bg_Trang_1">
                                                                    <td class="web_XS_3 web_bg_xam">${dau.index}</td>
                                                                    <td class="web_XS_4 web_bg_xam">${duoi}</td>                                   
                                                                </tr>                       
                                                            </c:forEach>                                                                 
                                                            </tbody>
                                                        </table>
                                                        <table class="dit-loto" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                            <tbody>
                                                                <tr class="bg_head">
                                                                    <td class="text_rightmb web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                                                    <td class="web_XS_3 web_bg_Loto"><h3>Đuôi</h3></td>                                   
                                                                </tr>
                                                            <c:forEach items="${listLotteryDauMB}" var="dau" varStatus="duoi">
                                                                <tr class="web_bg_Trang_1">                                   
                                                                    <td class="text_rightmb web_XS_4 web_bg_xam">${dau}</td>                                   
                                                                    <td class="web_XS_3 web_bg_xam">${duoi.index}</td>
                                                                </tr>                       
                                                            </c:forEach>                                
                                                            </tbody>
                                                        </table>                           
                                                        <div class="clearfix"></div>
                                                    </div>                                                                     
                                                </div>
                                            </div>
                                        </div>                                        
                                    </c:forEach>
                                    <div class="adv box_cc" id="adv1"></div>
                                </c:if>


                                <!-- ket qua xo so mien trung-->
                                <%--<c:if test="${lotterysMT!=null&&numSizeMT>0}">--%>
                                <c:if test="${1<0}">
                                    <c:set var="width"  value="26%"/>
                                    <c:if test="${numSizeMT>3}"><c:set var="width"  value="20%"/></c:if>                    
                                    <div class="box_kqxs box_cc" id="kqmientrung">
                                        <div id="kqxsmb">
                                            <div class="result-header">                           
                                                <h2 ><a href="${strUrl}/ket-qua-xo-so-mien-trung-xsmt.html" title="kết quả xổ số miền trung">KẾT QUẢ XỔ SỐ MIỀN TRUNG XSMT</a> Ngày ${lotterysMT.openDate}</h2>                       
                                            </div>                       
                                            <div class="box_so">
                                                <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                    <tbody>
                                                        <tr class="xsmb_ngang_1">
                                                            <td class="xsmn_1 do"></td>
                                                    <c:set var="index" value="0"/>
                                                    <c:set var="link" value=""/>
                                                    <c:forTokens items="${lotterysMT.province}" delims="+" var="province">
                                                        <c:if test="${listCompany!=null}">
                                                            <c:forEach items="${listCompany}" var="company">
                                                                <c:if test="${province==company.company}">
                                                                    <c:if test="${company.codeLowerCase=='xstth'}">
                                                                        <c:set var="link" value="ket-qua-xo-so-thua-thien-hue-${company.codeLowerCase}.html"/>
                                                                    </c:if>
                                                                    <c:if test="${company.codeLowerCase=='xsbdh'}">
                                                                        <c:set var="link" value="ket-qua-xo-so-binh-dinh-${company.codeLowerCase}.html"/>
                                                                    </c:if>
                                                                    <c:if test="${company.codeLowerCase!='xstth'&&company.codeLowerCase!='xsbdh'}"><c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/></c:if>                                             
                                                                </c:if> 
                                                            </c:forEach>
                                                        </c:if>                                 
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon12">
                                                            <p><a href="/${link}" title="Xổ số ${province}">${province}</a></p>
                                                        </td>
                                                        <c:set var="index" value="${index+1}"/>   
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải tám</td>
                                                    <c:forTokens items="${lotterysMT.eighth}" delims="+" var="eighth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16"><span class="do">${eighth}</span></td>
                                                    </c:forTokens>                            
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải bảy</td>
                                                    <c:forTokens items="${lotterysMT.seventh}" delims="+" var="seventh">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${seventh}</td>    
                                                    </c:forTokens>                                                          
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải sáu</td>
                                                    <c:forTokens items="${lotterysMT.sixth}" delims="+" var="sixth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16">
                                                        <c:forTokens items="${sixth}" delims="-" var="six">
                                                            ${six}<br/>
                                                        </c:forTokens>
                                                        </td>    
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải năm</td>
                                                    <c:forTokens items="${lotterysMT.fifth}" delims="+" var="fifth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${fifth}</td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang"><td class="xsmn_1">Giải tư</td>
                                                    <c:forTokens items="${lotterysMT.fourth}" delims="+" var="fourth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16">
                                                        <c:forTokens items="${fourth}" delims="-" var="fo">
                                                            ${fo}<br/>
                                                        </c:forTokens>
                                                        </td>   
                                                    </c:forTokens>                              
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải ba</td>
                                                    <c:forTokens items="${lotterysMT.third}" delims="+" var="third">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16">
                                                        <c:forTokens items="${third}" delims="-" var="th">
                                                            ${th}<br/>
                                                        </c:forTokens>
                                                        </td>    
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang"><td class="xsmn_1">Giải nhì</td>
                                                    <c:forTokens items="${lotterysMT.second}" delims="+" var="second">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${second}</td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải nhất</td>
                                                    <c:forTokens items="${lotterysMT.first}" delims="+" var="first">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${first}</td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Đặc biệt</td>
                                                    <c:forTokens items="${lotterysMT.special}" delims="+" var="special">
                                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon16"><span class="do fon16">${special}</span></td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div class="clr"></div>
                                                <div class="boxseo bogoc2" style="display: none">
                                                    Chờ kết quả :<span class="red"> XS&lt;Mã Tỉnh&gt; CHO</span> gửi <span class="red">8185</span>
                                                    <br/>
                                                    Lấy bộ số thần tài :<span class="red"> CAU </span>&lt;Mã Tỉnh&gt; gửi <span class="red">8585</span>
                                                </div>                         
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <%--<c:if test="${listLotteryDuoiMT!=null}">--%>
                                <c:if test="${1<0}">
                                    <div class="box_kqxs box_cc" id="d2mientrung">
                                        <div id="kqxsmb">                      
                                            <div class="box_so">
                                                <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                                                    <tbody>
                                                        <tr class="bg_head">
                                                            <td class="xsmn_1 xsmntit"><h3>Đầu</h3></td>
                                                    <c:set var="index" value="0"/>
                                                    <c:forTokens items="${lotterysMT.province}" delims="+" var="province">
                                                        <c:if test="${listCompany!=null}">
                                                            <c:forEach items="${listCompany}" var="company">
                                                                <c:if test="${province==company.company}">
                                                                    <c:if test="${company.codeLowerCase=='xstth'}">
                                                                        <c:set var="link" value="ket-qua-xo-so-thua-thien-hue-${company.codeLowerCase}.html"/>
                                                                    </c:if>
                                                                    <c:if test="${company.codeLowerCase!='xstth'}"><c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/></c:if>                                             
                                                                </c:if> 
                                                            </c:forEach>
                                                        </c:if>
                                                        <td style="width:${width}" class="xsmn_${numSizeMT}">
                                                            <h3 class="h3title"><a href="${strUrl}/${link}" title="Xổ số ${province}">${province}</a></h3>
                                                        </td>
                                                        <c:set var="index" value="${index+1}"/>   
                                                    </c:forTokens>
                                                    </tr>
                                                    <c:set var="dau" value="0"/>
                                                    <c:forEach items="${listLotteryDuoiMT}" var="duois">
                                                        <tr class="xsmn_tke_ngang">
                                                            <td class="web_XS_3 web_bg_xam"><a>${dau}</a></td>
                                                        <c:forTokens items="${duois}" delims="+" var="duoi">
                                                            <td style="width:${width}" class="xsmn_${numSizeMT}">${duoi}</td>    
                                                        </c:forTokens>                                  
                                                        </tr>  
                                                        <c:set var="dau" value="${dau+1}"/>
                                                    </c:forEach>                              
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>    
                                </c:if>


                                <!-- ket qua xo so mien nam-->
                                <c:if test="${lotterysMN!=null&&numSizeMN>0}">
                                <%--<c:if test="${1<0}">--%>
                                    <c:set var="width"  value="26%"/>
                                    <c:if test="${numSizeMN>3}"><c:set var="width"  value="20%"/></c:if>                    
                                    <div class="box_kqxs box_cc" id="kqmiennam">
                                        <div id="kqxsmb">
                                            <div class="result-header">                           
                                                <h2 ><a href="${strUrl}/ket-qua-xo-so-an-giang-xsag.html" title="kết quả xổ số miền nam">KẾT QUẢ XỔ SỐ MIỀN NAM XSMN</a> Ngày ${lotterysMN.openDate}</h2>                                  
                                            </div>                        
                                            <div class="box_so">
                                                <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                                                    <tbody>
                                                        <tr class="xsmb_ngang_1">
                                                            <td class="xsmn_1 do"></td>
                                                    <c:set var="index" value="0"/>
                                                    <c:set var="link" value=""/>
                                                    <c:forTokens items="${lotterysMN.province}" delims="+" var="province">
                                                        <c:if test="${listCompany!=null}">
                                                            <c:forEach items="${listCompany}" var="company">
                                                                <c:if test="${province==company.company}">
                                                                    <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>
                                                                </c:if> 
                                                            </c:forEach>
                                                        </c:if>
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon12">
                                                            <p><a href="/${link}" title="Xổ số ${province}">${province}</a></p>
                                                        </td>
                                                        <c:set var="index" value="${index+1}"/>   
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải tám</td>
                                                    <c:forTokens items="${lotterysMN.eighth}" delims="+" var="eighth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16"><span class="do"> ${eighth}</span></td>
                                                    </c:forTokens>                            
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải bảy</td>
                                                    <c:forTokens items="${lotterysMN.seventh}" delims="+" var="seventh">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${seventh}</td>    
                                                    </c:forTokens>                                                          
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải sáu</td>
                                                    <c:forTokens items="${lotterysMN.sixth}" delims="+" var="sixth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16">
                                                        <c:forTokens items="${sixth}" delims="-" var="six">
                                                            ${six}<br/>
                                                        </c:forTokens>
                                                        </td>    
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải năm</td>
                                                    <c:forTokens items="${lotterysMN.fifth}" delims="+" var="fifth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${fifth}</td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang"><td class="xsmn_1">Giải tư</td>
                                                    <c:forTokens items="${lotterysMN.fourth}" delims="+" var="fourth">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16">
                                                        <c:forTokens items="${fourth}" delims="-" var="fo">
                                                            ${fo}<br/>
                                                        </c:forTokens>
                                                        </td>   
                                                    </c:forTokens>                              
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải ba</td>
                                                    <c:forTokens items="${lotterysMN.third}" delims="+" var="third">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16">
                                                        <c:forTokens items="${third}" delims="-" var="th">
                                                            ${th}<br/>
                                                        </c:forTokens>
                                                        </td>    
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang"><td class="xsmn_1">Giải nhì</td>
                                                    <c:forTokens items="${lotterysMN.second}" delims="+" var="second">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${second}</td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Giải nhất</td>
                                                    <c:forTokens items="${lotterysMN.first}" delims="+" var="first">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${first}</td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    <tr class="xsmn_ngang">
                                                        <td class="xsmn_1">Đặc biệt</td>
                                                    <c:forTokens items="${lotterysMN.special}" delims="+" var="special">
                                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon16"><span class="do fon16">${special}</span></td>
                                                    </c:forTokens>                             
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div class="clr"></div>
                                                <div class="boxseo bogoc2" style="display: none">
                                                    Chờ kết quả :<span class="red"> XS&lt;Mã Tỉnh&gt; CHO</span> gửi <span class="red">8185</span>
                                                    <br/>
                                                    Lấy bộ số thần tài :<span class="red"> CAU </span>&lt;Mã Tỉnh&gt; gửi <span class="red">8585</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${listLotteryDuoiMN!=null}">
                                <%--<c:if test="${1<0}">--%>
                                    <div class="box_kqxs box_cc" id="d2miennam">
                                        <div id="kqxsmb">                      
                                            <div class="box_so">
                                                <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                                                    <tbody>
                                                        <tr class="bg_head">
                                                            <td class="xsmn_1 xsmntit"><h3>Đầu</h3></td>
                                                    <c:set var="index" value="0"/>
                                                    <c:forTokens items="${lotterysMN.province}" delims="+" var="province">
                                                        <c:if test="${listCompany!=null}">
                                                            <c:forEach items="${listCompany}" var="company">
                                                                <c:if test="${province==company.company}">
                                                                    <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>
                                                                </c:if> 
                                                            </c:forEach>
                                                        </c:if>
                                                        <td style="width:${width}" class="xsmn_${numSizeMN}">
                                                            <h3 class="h3title"><a href="${strUrl}/${link}" title="Xổ số ${province}">${province}</a></h3>
                                                        </td>
                                                        <c:set var="index" value="${index+1}"/>   
                                                    </c:forTokens>
                                                    </tr>
                                                    <c:set var="dau" value="0"/>
                                                    <c:forEach items="${listLotteryDuoiMN}" var="duois">
                                                        <tr class="xsmn_tke_ngang">
                                                            <td class="web_XS_3 web_bg_xam"><a>${dau}</a></td>
                                                        <c:forTokens items="${duois}" delims="+" var="duoi">
                                                            <td style="width:${width}" class="xsmn_${numSizeMN}">${duoi}</td>    
                                                        </c:forTokens>                                  
                                                        </tr>  
                                                        <c:set var="dau" value="${dau+1}"/>
                                                    </c:forEach>                              
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>    
                                </c:if>
                                </td>
                                <td id="thaoluan">								
                                    <%--<%@include file="include/content-chatchit.jsp" %>--%>
                                    <%@include file="include/content-topdaigia.jsp" %>
                                    <%@include file="include/content-topchoinhieutrongngay.jsp" %>
                                </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <%@include file="include/content-top4.jsp" %>
                                        <%-- <%@include file="include/content-top5.jsp" %> --%>
                                        
                                        <div id="thongkenhanh">
                                            <%@include file="include/content-tkranhieu30ngay.jsp" %>
                                            <%@include file="include/content-tkrait30ngay.jsp" %>
                                            <%@include file="include/content-tkbacnhongaycuoi.jsp" %>
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
