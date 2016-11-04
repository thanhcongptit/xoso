<%-- 
    Document   : live
    Created on : Sep 4, 2015, 4:25:59 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ket qua xoso truc tiep mien bac--> 
                <c:if test="${listLotteryMB!=null}">
                    
                <div class="box_kqxs box_cc">                    
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2 >KẾT QUẢ XỔ SỐ MIỀN BẮC XSMB </h2>
                           <h3> NGÀY ${ddmmyyyy}</h3>
                       </div>                      
                    <div class="box_so">
                        <c:forEach items="${listLotteryMB}" var="lottery">
                       <div class="box_so_left">  
                         <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                            <tbody>
                               <tr class="web_bg_Trang">
                                  <td class="name_tbl_ketquaxs" colspan="11"></td>
                               </tr>
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
                       </c:forEach>
                        <div class="boxseo bogoc2" style="display: none">
                           <c:if test="${strMobile!='pc'}"><a href="javascript:loadSMS('XSMB CHO','8185')"></c:if>Chờ kết quả :<span class="red"> XSMB CHO</span> gửi <span class="red">8185</span>
                           <c:if test="${strMobile!='pc'}"></a></c:if>
                           <br/>
                           <c:if test="${strMobile!='pc'}"><a href="javascript:loadSMS('CAU','8585')"></c:if>
                           Số đẹp hôm nay :<span class="red"> CAU</span> gửi <span class="red">8585</span>
                           <c:if test="${strMobile!='pc'}"></a></c:if>
                         </div>                                                
                       <div class="box_so_right dd-loto-widget">
                          <c:if test="${listDuoiMB!=null}"> 
                          <table cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                             <tbody>
                                <tr class="web_bg_Trang">
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đầu</h3></td>
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đuôi</h3></td>
                                </tr>
                                <c:set var="dau" value="0"/>
                                <c:forEach items="${listDuoiMB}" var="duoi">
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">${dau}</td>
                                   <td class="web_XS_4 web_bg_xam">${duoi}</td>
                                </tr>                       
                                    <c:set var="dau" value="${dau+1}"/>    
                                </c:forEach>                                
                            </tbody>
                         </table>
                         </c:if>                           
                          <c:if test="${listDauMB!=null}">  
                         <table class="dit-loto" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                             <tbody>
                                <tr class="bg_head">
                                   <td class="text_rightmb web_XS_4 web_bg_Loto"><h3>Loto</h3></td>
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đuôi</h3></td>                                   
                                </tr>
                                <c:forEach items="${listDauMB}" var="dau" varStatus="duoi">
                                <tr class="web_bg_Trang_1">                                   
                                   <td class="text_rightmb web_XS_4 web_bg_xam">${dau}</td>                                   
                                   <td class="web_XS_3 web_bg_xam">${duoi.index}</td>
                                </tr>                       
                                </c:forEach>                                
                            </tbody>
                         </table>
                         </c:if>
                         <div class="clearfix"></div>  
                       </div>                                                                        
                      </div>
                   </div>
                </div>
                </c:if>                
                <!--
                <div class="adv box_cc" style="height:90px;"></div>
                -->
                <!-- ket qua xo so mien trung-->
                <c:if test="${listLotteryMT!=null&&numSizeMT>0}">
                    <c:set var="width"  value="26%"/>
                    <c:if test="${numSizeMT>3}"><c:set var="width"  value="20%"/></c:if>                    
                  <div class="box_kqxs box_cc">
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2 >KẾT QUẢ XỔ SỐ MIỀN TRUNG XSMT </h2>
                           <h3>NGÀY ${listLotteryMT.openDate}</h3>
                       </div>
                      
                    <div class="box_so">
                       <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                          <tbody>
                            <tr class="xsmb_ngang_1">
                             <td class="xsmn_1 do"></td>
                             <c:set var="index" value="0"/>
                             <c:forTokens items="${listLotteryMT.province}" delims="+" var="province">
                                 <td style="width:${width};text-align: center"  class="xsmn_${numSizeMT} fon12">
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
                             <c:forTokens items="${listLotteryMT.eighth}" delims="+" var="eighth">
                                 <td style="width:${width}" class="xsmn_${numSizeMT} fon16"><span class="do">${eighth}</span></td>
                             </c:forTokens>                            
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G7</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải bảy</c:if>
                              </td>
                             <c:forTokens items="${listLotteryMT.seventh}" delims="+" var="seventh">
                             <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${seventh}</td>    
                             </c:forTokens>                                                          
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G6</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải sáu</c:if>
                              </td>
                             <c:forTokens items="${listLotteryMT.sixth}" delims="+" var="sixth">
                                 <td style="width:${width}" class="xsmn_${numSizeMT} fon16">
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
                             <c:forTokens items="${listLotteryMT.fifth}" delims="+" var="fifth">
                                 <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${fifth}</td>
                             </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang"><td class="xsmn_1">
                              <c:if test="${strMobile!='pc'}">G4</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải tư</c:if>
                              </td>
                              <c:forTokens items="${listLotteryMT.fourth}" delims="+" var="fourth">
                                  <td style="width:${width}" class="xsmn_${numSizeMT} fon16">
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
                             <c:forTokens items="${listLotteryMT.third}" delims="+" var="third">
                                 <td style="width:${width}" class="xsmn_${numSizeMT} fon16">
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
                              <c:forTokens items="${listLotteryMT.second}" delims="+" var="second">
                                  <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${second}</td>
                              </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang">
                              <td class="xsmn_1">
                                  <c:if test="${strMobile!='pc'}">G1</c:if>
                                 <c:if test="${strMobile=='pc'}">Giải nhất</c:if>
                              </td>
                             <c:forTokens items="${listLotteryMT.first}" delims="+" var="first">
                                 <td style="width:${width}" class="xsmn_${numSizeMT} fon16">${first}</td>
                             </c:forTokens>                             
                          </tr>
                          <tr class="xsmn_ngang">
                             <td class="xsmn_1">
                                 <c:if test="${strMobile!='pc'}">ĐB</c:if>
                                 <c:if test="${strMobile=='pc'}">Đặc biệt</c:if>                                 
                             </td>
                             <c:forTokens items="${listLotteryMT.special}" delims="+" var="special">
                                 <td style="width:${width}" class="xsmn_${numSizeMT} fon16"><span class="do fon16">${special}</span></td>
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
                <c:if test="${listDuoiMT!=null}">
                <div class="box_kqxs box_cc">
                   <div id="kqxsmb">                      
                      <div class="box_so">
                         <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                            <tbody>
                               <tr class="loto_ngang">
                                  <td class="xsmn_1 xsmntit"><h3>Đầu</h3></td>
                                  <c:set var="index" value="0"/>
                                    <c:forTokens items="${listLotteryMT.province}" delims="+" var="province">
                                        <td style="width:${width}" class="xsmn_${numSizeMT} fon12">
                                           <p><a title="Xổ số ${province}">${province}</a></p>
                                       </td>
                                     <c:set var="index" value="${index+1}"/>   
                                    </c:forTokens>
                               </tr>
                               <c:set var="dau" value="0"/>
                               <c:forEach items="${listDuoiMT}" var="duois">
                               <tr class="xsmn_tke_ngang">
                                  <td class="xsmn_1"><a>${dau}</a></td>
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
                <c:if test="${listLotteryMN!=null&&numSizeMN>0}">
                    <c:set var="width"  value="26%"/>
                    <c:if test="${numSizeMN>3}"><c:set var="width"  value="20%"/></c:if>                    
                  <div class="box_kqxs box_cc">
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2>KẾT QUẢ XỔ SỐ MIỀN NAM XSMN </h2>
                           <h3>NGÀY ${listLotteryMN.openDate}</h3>
                       </div>                      
                    <div class="box_so">
                       <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                          <tbody>
                            <tr class="xsmb_ngang_1">
                             <td class="xsmn_1 do"></td>
                             <c:set var="index" value="0"/>
                             <c:forTokens items="${listLotteryMN.province}" delims="+" var="province">
                                 <td style="width:${width};text-align: center" class="xsmn_${numSizeMN} fon12">
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
                         <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                            <tbody>
                               <tr class="loto_ngang">
                                  <td class="xsmn_1 xsmntit"><h3>Đầu</h3></td>
                                  <c:set var="index" value="0"/>
                                    <c:forTokens items="${listLotteryMN.province}" delims="+" var="province">
                                        <td style="width:${width}" class="xsmn_${numSizeMN} fon12">
                                            <p class="do">${province}</a></p>
                                       </td>
                                     <c:set var="index" value="${index+1}"/>   
                                    </c:forTokens>
                               </tr>
                               <c:set var="dau" value="0"/>
                               <c:forEach items="${listDuoiMN}" var="duois">
                               <tr class="xsmn_tke_ngang">
                                  <td class="xsmn_1"><a>${dau}</a></td>
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
                <!--
                <div class="adv box_cc" style="height:90px;"></div>
                -->
                
