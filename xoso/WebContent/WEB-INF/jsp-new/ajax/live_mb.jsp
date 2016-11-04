<%-- 
    Document   : live
    Created on : Sep 4, 2015, 4:25:59 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- ket qua xoso truc tiep mien bac123 ${listLotteryMB!=null}--> 
                <c:if test="${listLotteryMB!=null}">
                    
                <div class="box_kqxs box_cc">                    
                   <div id="kqxsmb">
                       <div class="result-header">
                           <h2 ><a>KẾT QUẢ XỔ SỐ MIỀN BẮC XSMB</a> NGÀY ${openDate}</h2>                           
                       </div>
                   
                    <div class="box_so">
                        <c:forEach items="${listLotteryMB}" var="lottery">
                       <div class="box_so_left">  
                         <table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#c9c9c9">
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
                       </c:forEach>
                       <div class="boxseo bogoc2" style="display: none">
                           <a href="javascript:loadSMS('XSMB CHO','8185')">Chờ kết quả :<span class="red"> XSMB CHO</span> gửi <span class="red">8185</span></a>
                           <br/>
                           <a href="javascript:loadSMS('CAU','8585')">Số đẹp hôm nay :<span class="red"> CAU</span> gửi <span class="red">8585</span></a>
                         </div>                        
                       <div class="box_so_right dd-loto-widget">
                          <c:if test="${listDuoiMB!=null}"> 
                          <table cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                             <tbody>
                                <tr class="bg_head">
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đầu</h3></td>
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đuôi</h3></td>
                                </tr>
                                <c:set var="dau" value="0"/>
                                <c:forEach items="${listDuoiMB}" var="duoi">
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_4 web_bg_xam">${dau}</td>
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
                                   <td class="text_rightmb web_XS_4 web_bg_Loto"><h3>Đầu</h3></td>
                                   <td class="web_XS_3 web_bg_Loto"><h3>Đuôi</h3></td>                                   
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
                
