<%-- 
    Document   : live
    Created on : Sep 4, 2015, 4:25:59 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(function (){
        currHHMMSS = getCurrHHMMSS();
        if (currHHMMSS >= 161000 && currHHMMSS < 163800) {
            // tuong thuat truc tiep mien nam
            $('#livemn').show();
        }else if (currHHMMSS >= 171000 && currHHMMSS < 173800) {
            // tuong thuat truc tiep mien trung
            $('#livemt').show();
        }else if (currHHMMSS >= 181000&& currHHMMSS < 183800) {
            //tuong thuat truc tiep mien bac
            $('#livemb').show();            
        }
        
        
//         if (currHHMMSS > 163800) {
//            // tuong thuat truc tiep mien nam
//            $('#livemn').show();
//        }
//        if (currHHMMSS >173800) {
//            // tuong thuat truc tiep mien trung
//             $('#livemt').show();
//        }
//        if (currHHMMSS > 183800) {
//            //tuong thuat truc tiep mien bac
//            $('#livemb').show();
//        }
        
        loadlightstreamer();
    });
    
    function StringBreaker(limit) {
        this.limit = limit;
        this.patternS = "(";
        for (var i = 0; i < limit; i++) {
            this.patternS += "\\S";
        }
        this.pattern = new RegExp(this.patternS + ")", "g");
        // see http://www.quirksmode.org/oddsandends/wbr.html
        this.repl = "$1&#8203;"
    }
    
    function loadlightstreamer(){
        StringBreaker.prototype = {
            // insert soft breaks in long words
            breakString: function (source) {
                var target = source.replace(this.pattern, this.repl);
                target = target.replace(/</g, "&lt;");
                return target;
            }
        };
        var pathpage = window.location.pathname;
        var counter = 0;
        for(var i = 0; i < pathpage.length; i++) {
            if(pathpage[i] == "/") {
                counter++;
            }
        }
        console.log("count==>>"+pathpage+"==>>counter==>>"+counter);
        var ls_client = "http://static.xoso.wap.vn/version_2/js/lsClient.js";
        if(counter > 1) {
            ls_client = "http://static.xoso.wap.vn/version_2/js/lsClient.js";
        }
        console.log("ls_client="+ls_client);
        require([ls_client, "StaticGrid", "Subscription"], function (lsClient, StaticGrid, Subscription) {
            var sb = new StringBreaker(35);
            //  create the DynaGrid
            var rtGrid = new StaticGrid("kqxs", true);
            rtGrid.setAutoCleanBehavior(true, false);
            rtGrid.setHtmlInterpretationEnabled(true);
            rtGrid.addListener({
                onVisualUpdate: function (key, info, domNode) {
                    if (info == null) {
                        return;
                        ""
                    }
                    info.setHotToColdTime(6000);
                    info.setHotTime(1000);
                    info.setAttribute("red", "#003c3a", "color");
                    //info.setCellStyle("message", "red", "red", "XSTD00")
                    // format value
                    var value = info.getChangedFieldValue("message");
                    if (value != null) {
                        value = sb.breakString(value);
                        // info.setCellValue("message", value);
                        domNode.innerHTML = value;
                    }
                }

            });

            //  create the Subscription; item and field names will be extracted from the grid
            var rtSubscription = new Subscription("MERGE", rtGrid.extractItemList(), rtGrid.extractFieldList());
            rtSubscription.setDataAdapter("KQXS_ADAPTER");
            rtSubscription.setRequestedSnapshot("yes");

            rtSubscription.addListener(rtGrid);
            lsClient.subscribe(rtSubscription);
        });
    }
</script>
<!-- ket qua xoso truc tiep mien bac--> 
                
                    
                    <div class="box_kqxs box_cc" id="livemb" style="display: none">                    
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2 ><a>KẾT QUẢ XỔ SỐ MIỀN BẮC XSMB</a> NGÀY ${ddmmyyyy}</h2>
                       </div>                      
                    <div class="box_so">
                       <div class="box_so_left">  
                         <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#c9c9c9">
                            <tbody>                               
                               <tr class="web_bg_Trang">
                                  <td class="web_XS_1 chugiai">Đặc biệt</td>
                                  <td colspan="12" class="web_XS_2 chukq">
                                      <div data-source="lightstreamer"  data-grid="kqxs" data-item="XSTD00" data-field="message" data-replica="XSTD00"></div>
                                  </td>
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td class="web_XS_1 chugiai">Giải nhất</td>
                                  <td colspan="12" class="web_XS_2 chukq">
                                      <div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD10" data-field="message"></div>
                                  </td>
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td class="web_XS_1 chugiai">Giải nhì</td>                                  
                                  <td colspan="6" class="web_XS_2 chukq">
                                      <div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD20" data-field="message"></div>
                                  </td>
                                  <td colspan="6" class="web_XS_2 chukq">
                                      <div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD21" data-field="message"></div>
                                  </td>
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td rowspan="2" class="web_XS_1 chugiai">Giải ba</td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD30" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD31" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD32" data-field="message"></div></td>                                                                  
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD33" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD34" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD35" data-field="message"></div></td>                                                                  
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td class="web_XS_1 chugiai">Giải tư</td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD40" data-field="message"></div></td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD41" data-field="message"></div></td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD42" data-field="message"></div></td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD43" data-field="message"></div></td>                                                                    
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td rowspan="2" class="web_XS_1 chugiai">Giải năm</td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD50" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD51" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD52" data-field="message"></div></td>                                                                    
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD53" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD54" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD55" data-field="message"></div></td>                                                                                                      
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td class="web_XS_1 chugiai">Giải sáu</td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD60" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD61" data-field="message"></div></td>
                                  <td colspan="4" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD62" data-field="message"></div></td>                                                                   
                               </tr>
                               <tr class="web_bg_Trang">
                                  <td class="web_XS_1 chugiai">Giải bảy</td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD70" data-field="message"></div></td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD71" data-field="message"></div></td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD72" data-field="message"></div></td>
                                  <td colspan="3" class="web_XS_2 chukq"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD73" data-field="message"></div></td>                                                                    
                               </tr>
                            </tbody>
                         </table>
                       </div>        
                        <div class="boxseo bogoc2">Kết quả <span class="red"> Xổ số Miền Bắc </span> siêu tốc:<span class="red"> DK XSMB</span> gửi <span class="red">8985</span>
                         </div>
                       <div class="box_so_right">
                          <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#c9c9c9">
                             <tbody>
                                <tr class="bg_head">
                                   <td class="web_XS_3 web_bg_Loto"><h3>Đầu</h3></td>
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đuôi</h3></td>
                                </tr>                                
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">0</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD0" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">1</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD1" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">2</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD2" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">3</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD3" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">4</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD4" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">5</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD5" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">6</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD6" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">7</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD7" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">8</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD8" data-field="message"></div></td>
                                </tr>
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">9</td>
                                   <td class="web_XS_4 web_bg_xam"><div data-source="lightstreamer" data-grid="kqxs" data-item="XSTD9" data-field="message"></div></td>
                                </tr>                                
                            </tbody>
                         </table>
                       </div>
                       <div class="clearfix"></div>
                         
                      </div>
                   </div>
                </div>
                <!--
                <div class="adv box_cc" style="height:90px;"></div>
                -->
                <!-- ket qua xo so mien trung-->
                <c:if test="${numSizeOpenMT>0}">
                    <c:set var="width"  value="26%"/>
                    <c:if test="${numSizeOpenMT>3}"><c:set var="width"  value="20%"/></c:if>                    
                  <div class="box_kqxs box_cc" id="livemt" style="display: none">
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2 ><a>KẾT QUẢ XỔ SỐ MIỀN TRUNG XSMT </a> NGÀY ${ddmmyyyy}</h2>                           
                       </div>
                      
                    <div class="box_so">
                       <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#c9c9c9">
                          <tbody>
                            <tr class="xsmb_ngang_1">
                             <td class="xsmn_1 do"></td>                             
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                 <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>
                                <c:if test="${company.code=='XSTD'}">
                                    <c:set var="link" value="xsmb-${link}"/>
                                </c:if>
                                <c:if test="${company.code=='XSTTH'}">
                                    <c:set var="link" value="ket-qua-xo-so-thua-thien-hue-${company.codeLowerCase}.html"/>
                                </c:if>
                                 <td style="width:${width};text-align: center" class="xsmn_${numSizeOpenMT} fon12">
                                     <p class="do" style="text-align: center"><a href="${link}" title="xổ số ${company.company}" >${company.company}</a></p>
                                </td>                                
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                             <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G8</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải tám</c:if>                                 
                             </td>
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}80" data-field="message"></div>
                                 </td>
                             </c:forEach>                            
                          </tr>
                          <tr class="xsmn_ngang">
                             <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G7</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải bảy</c:if>                                 
                             </td>
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                             <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                 <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}70" data-field="message"></div>
                             </td>    
                             </c:forEach>                                                
                          </tr>
                          <tr class="xsmn_ngang">
                             <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G6</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải sáu</c:if>
                             </td>
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}60" data-field="message"></div>
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}61" data-field="message"></div>
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}62" data-field="message"></div>
                                 </td>    
                             </c:forEach>                              
                          </tr>
                          <tr class="xsmn_ngang">
                             <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G5</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải năm</c:if>
                             </td>
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}50" data-field="message"></div>
                                 </td>
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G4</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải tư</c:if>
                              </td>
                              <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                  <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}40" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}41" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}42" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}43" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}44" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}45" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}46" data-field="message"></div>
                                  </td>   
                              </c:forEach>                              
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G3</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải ba</c:if>
                              </td>
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                 <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}30" data-field="message"></div>
                                 <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}31" data-field="message"></div>
                                 </td>    
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G2</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải nhì</c:if>
                              </td>
                              <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                  <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                      <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}20" data-field="message"></div>
                                  </td>
                              </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">G1</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải nhất</c:if>
                              </td>
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}10" data-field="message"></div>
                                 </td>
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                             <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">ĐB</c:if>
                                 <c:if test="${strMobile=='pc'}">Đặc biệt</c:if>                                 
                             </td>
                             <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}00" data-field="message"></div>
                                 </td>
                             </c:forEach>                             
                          </tr>
                       </tbody>
                    </table>
                       <div class="clr"></div>
                         <div class="boxseo bogoc2">
                             Kết quả <span class="red"> XSMT </span> siêu tốc:<span class="red"> DK XSMT</span> gửi <span class="red">8985</span>
                             <br/>Cầu loto đẹp nhất: <span class="red"> CAU </span> gửi <span class="red">8785</span>
                         </div>
                      </div>
                   </div>
                   <c:if test="${numSizeOpenMT>0}">
                <div class="box_kqxs box_cc">
                   <div id="kqxsmb">                      
                      <div class="box_so">
                         <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#c9c9c9">
                            <tbody>
                               <tr class="bg_head">
                                  <td class="xsmn_1 xsmntit"><h3>Đầu</h3></td>                                  
                                    <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                        <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>
                                        <c:if test="${company.code=='XSTD'}">
                                            <c:set var="link" value="xsmb-${link}"/>
                                        </c:if>
                                        <c:if test="${company.code=='XSTTH'}">
                                            <c:set var="link" value="ket-qua-xo-so-thua-thien-hue-${company.codeLowerCase}.html"/>
                                        </c:if>
                                        <td style="width:${width}" class="xsmn_${numSizeOpenMT} fon12">
                                            <h3 class="h3title"><a href="${link}" title="Xổ số ${company.company}">${company.company}</a></h3>
                                       </td>
                                    </c:forEach>
                               </tr>
                               
                               <c:forEach begin="0" end="9" var="dau">
                               <tr class="xsmn_tke_ngang">
                                  <td class="web_XS_3 web_bg_xam"><a>${dau}</a></td>
                                  <c:forEach items="${listLotteryCompanyMT}"  var="company">
                                  <td style="width:${width}" class="xsmn_${numSizeOpenMT}">
                                      <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}${dau}" data-field="message"></div>
                                  </td>    
                                  </c:forEach>                                  
                               </tr>                                 
                               </c:forEach>                              
                            </tbody>
                         </table>
                      </div>
                   </div>
                </div>    
                </c:if>    
                </div>
                </c:if>                
                <!-- ket qua xo so mien nam-->
                <c:if test="${numSizeOpenMN>0}">
                    <c:set var="width"  value="26%"/>
                    <c:if test="${numSizeOpenMN>3}"><c:set var="width"  value="20%"/></c:if>                    
                  <div class="box_kqxs box_cc" id="livemn" style="display: none">
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2 ><a>KẾT QUẢ XỔ SỐ MIỀN NAM XSMN</a> NGÀY ${ddmmyyyy}</h2>
                       </div>
                      
                    <div class="box_so">
                       <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#c9c9c9">
                          <tbody>
                            <tr class="xsmb_ngang_1">
                             <td class="xsmn_1 do"></td>                             
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                 <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>
                                <c:if test="${company.code=='XSTD'}">
                                    <c:set var="link" value="xsmb-${link}"/>
                                </c:if>
                                <c:if test="${company.code=='XSTTH'}">
                                    <c:set var="link" value="ket-qua-xo-so-thua-thien-hue-${company.codeLowerCase}.html"/>
                                </c:if>
                                 <td style="width:${width};text-align: center" class="xsmn_${numSizeOpenMN} fon12">
                                     <p class="do" style="text-align: center"><a href="${link}" title="Xổ số ${company.company}" > ${company.company}</a></p>
                                </td>                                
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G8</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải tám</c:if> 
                              </td>
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}80" data-field="message"></div>
                                 </td>
                             </c:forEach>                            
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G7</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải bảy</c:if> 
                              </td>
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                             <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                 <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}70" data-field="message"></div>
                             </td>    
                             </c:forEach>                                                
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G6</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải sáu</c:if> 
                              </td>
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}60" data-field="message"></div>
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}61" data-field="message"></div>
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}62" data-field="message"></div>
                                 </td>    
                             </c:forEach>                              
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G5</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải năm</c:if> 
                              </td>
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}50" data-field="message"></div>
                                 </td>
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang"><td class="xsmn_1">
                              <c:if test="${strMobile!='pc'}">G4</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải tư</c:if> 
                              </td>
                              <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                  <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}40" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}41" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}42" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}43" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}44" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}45" data-field="message"></div>
                                    <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}46" data-field="message"></div>
                                  </td>   
                              </c:forEach>                              
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G3</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải ba</c:if> 
                              </td>
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                 <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}30" data-field="message"></div>
                                 <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}31" data-field="message"></div>
                                 </td>    
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang"><td class="xsmn_1">
                              <c:if test="${strMobile!='pc'}">G2</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải nhì</c:if> 
                              </td>
                              <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                  <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                      <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}20" data-field="message"></div>
                                  </td>
                              </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G1</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải nhất</c:if> 
                              </td>
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}10" data-field="message"></div>
                                 </td>
                             </c:forEach>                             
                          </tr>
                          <tr class="xsmn_ngang">
                             <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">ĐB</c:if>
                                 <c:if test="${strMobile=='pc'}">Đặc biệt</c:if>                                  
                             </td>
                             <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                 <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon16">
                                     <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}00" data-field="message"></div>
                                 </td>
                             </c:forEach>                             
                          </tr>
                       </tbody>
                    </table>
                       <div class="clr"></div>
                         <div class="boxseo bogoc2">
                             Kết quả <span class="red"> XSMN </span> siêu tốc:<span class="red"> DK XSMN</span> gửi <span class="red">8985</span>
                             <br/>Cầu loto đẹp nhất: <span class="red"> CAU </span> gửi <span class="red">8785</span>
                         </div>
                      </div>
                   </div>
                   <c:if test="${numSizeOpenMN>0}">
                <div class="box_kqxs box_cc">
                   <div id="kqxsmb">                      
                      <div class="box_so">
                         <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#c9c9c9">
                            <tbody>
                               <tr class="bg_head">
                                  <td class="xsmn_1 xsmntit"><h3>Đầu</h3></td>                                  
                                    <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                        <c:set var="link" value="ket-qua-xo-so-${company.companyLink}-${company.codeLowerCase}.html"/>
                                        <c:if test="${company.code=='XSTD'}">
                                            <c:set var="link" value="xsmb-${link}"/>
                                        </c:if>
                                        <c:if test="${company.code=='XSTTH'}">
                                            <c:set var="link" value="ket-qua-xo-so-thua-thien-hue-${company.codeLowerCase}.html"/>
                                        </c:if>
                                        <td style="width:${width}" class="xsmn_${numSizeOpenMN} fon12">
                                            <h3 class="h3title"><a href="${link}" title="Xổ số ${company.company}">${company.company}</a></h3>
                                       </td>
                                    </c:forEach>
                               </tr>
                               
                               <c:forEach begin="0" end="9" var="dau">
                               <tr class="xsmn_tke_ngang">
                                  <td class="web_XS_3 web_bg_xam"><a>${dau}</a></td>
                                  <c:forEach items="${listLotteryCompanyMN}"  var="company">
                                  <td style="width:${width}" class="xsmn_${numSizeOpenMN}">
                                      <div data-source="lightstreamer" data-grid="kqxs" data-item="${company.code}${dau}" data-field="message"></div>
                                  </td>    
                                  </c:forEach>                                  
                               </tr>                                 
                               </c:forEach>                              
                            </tbody>
                         </table>
                      </div>
                   </div>
                </div>    
                </c:if>    
                </div>
                </c:if>
                      
                <!--
                <div class="adv box_cc" style="height:90px;"></div>
                -->
                
