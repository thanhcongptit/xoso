<%-- 
    Document   : live
    Created on : Sep 4, 2015, 4:25:59 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                <!-- ket qua xo so mien nam-->
                <c:if test="${listLotteryMN!=null&&numSizeMN>0}">
                    <c:set var="width"  value="26%"/>
                    <c:if test="${numSizeMN>3}"><c:set var="width"  value="20%"/></c:if>                    
                  <div class="box_kqxs box_cc">
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2><a>KẾT QUẢ XỔ SỐ MIỀN NAM XSMN</a> NGÀY ${listLotteryMN.openDate}</h2>
                       </div>                      
                    <div class="box_so">
                       <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#c9c9c9">
                          <tbody>
                            <tr class="xsmb_ngang_1">
                             <td class="xsmn_1 do"></td>
                             <c:set var="index" value="0"/>
                             <c:forTokens items="${listLotteryMN.province}" delims="+" var="province">
                                 <td style="width:${width} ;text-align: center" class="xsmn_${numSizeMN} fon12">
                                     <p class="do" style="text-align: center">${province}</p>
                                </td>
                              <c:set var="index" value="${index+1}"/>   
                             </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G8</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải tám</c:if>  
                              </td>
                             <c:forTokens items="${listLotteryMN.eighth}" delims="+" var="eighth">
                                 <td style="width:${width}" class="xsmn_${numSizeMN} fon16">
                                     <span class="do">${eighth}</span>
                                 </td>
                             </c:forTokens>                            
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G7</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải bảy</c:if> 
                              </td>
                             <c:forTokens items="${listLotteryMN.seventh}" delims="+" var="seventh">
                             <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${seventh}</td>    
                             </c:forTokens>                                                          
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G6</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải sáu</c:if> 
                              </td>
                             <c:forTokens items="${listLotteryMN.sixth}" delims="+" var="sixth">
                                 <td style="width:${width}" class="xsmn_${numSizeMN} fon16">
                                 <c:forTokens items="${sixth}" delims="-" var="six">
                                     ${six}<br/>
                                 </c:forTokens>
                                 </td>    
                             </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G5</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải năm</c:if>
                              </td>
                             <c:forTokens items="${listLotteryMN.fifth}" delims="+" var="fifth">
                                 <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${fifth}</td>
                             </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang"><td class="xsmn_1">
                              <c:if test="${strMobile!='pc'}">G4</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải tư</c:if>
                              </td>
                              <c:forTokens items="${listLotteryMN.fourth}" delims="+" var="fourth">
                                  <td style="width:${width}" class="xsmn_${numSizeMN} fon16">
                                  <c:forTokens items="${fourth}" delims="-" var="fo">
                                      ${fo}<br/>
                                  </c:forTokens>
                                   </td>   
                              </c:forTokens>                              
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G3</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải ba</c:if>
                              </td>
                             <c:forTokens items="${listLotteryMN.third}" delims="+" var="third">
                                 <td style="width:${width}" class="xsmn_${numSizeMN} fon16">
                                 <c:forTokens items="${third}" delims="-" var="th">
                                     ${th}<br/>
                                 </c:forTokens>
                                 </td>    
                             </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang"><td class="xsmn_1">
                              <c:if test="${strMobile!='pc'}">G2</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải nhì</c:if>
                              </td>
                              <c:forTokens items="${listLotteryMN.second}" delims="+" var="second">
                                  <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${second}</td>
                              </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G1</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải nhất</c:if>  
                              </td>
                             <c:forTokens items="${listLotteryMN.first}" delims="+" var="first">
                                 <td style="width:${width}" class="xsmn_${numSizeMN} fon16">${first}</td>
                             </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">ĐB</c:if>
                                 <c:if test="${strMobile=='pc'}">Đặc biệt</c:if>
                              </td>
                             <c:forTokens items="${listLotteryMN.special}" delims="+" var="special">
                                 <td style="width:${width}" class="xsmn_${numSizeMN} fon16"><span class="do fon16">${special}</span></td>
                             </c:forTokens>                             
                          </tr>
                       </tbody>
                    </table>
                       <div class="clr"></div>
                         <div class="boxseo bogoc2" style="display: none">
                         <c:if test="${strMobile!='pc'}"><a href="javascript:loadSMS('XS<MaTinh> CHO','8185')"></c:if>
                             Chờ kết quả :<span class="red"> XS&lt;Mã Tỉnh&gt; CHO</span> gửi <span class="red">8185</span>
                            <c:if test="${strMobile!='pc'}"></a></c:if>
                             <br/>
                            <c:if test="${strMobile!='pc'}"><a href="javascript:loadSMS('CAU MaTinh','8585')"></c:if>
                             Lấy bộ số thần tài: <span class="red"> CAU </span>&lt;Mã Tỉnh&gt; gửi <span class="red">8585</span>
                            <c:if test="${strMobile!='pc'}"></a></c:if> 
                         </div>
                      </div>
                   </div>
                </div>
                </c:if>
                <c:if test="${listDuoiMN!=null}">
                <div class="box_kqxs box_cc">
                   <div id="kqxsmb">                      
                      <div class="box_so">
                         <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#c9c9c9">
                            <tbody>
                               <tr class="bg_head">
                                  <td class="xsmn_1 xsmntit"><h3>Đầu</h3></td>
                                  <c:set var="index" value="0"/>
                                    <c:forTokens items="${listLotteryMN.province}" delims="+" var="province">
                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon12">
                                            <h3 class="h3title">${province}</h3>
                                       </td>
                                     <c:set var="index" value="${index+1}"/>   
                                    </c:forTokens>
                               </tr>
                               <c:set var="dau" value="0"/>
                               <c:forEach items="${listDuoiMN}" var="duois">
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
