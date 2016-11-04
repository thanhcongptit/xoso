<%-- 
    Document   : live
    Created on : Sep 4, 2015, 4:25:59 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
            <c:if test="${lottery!=null}">
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
                        <div class="adv box_cc" style="height:60px;"><img src="/images/pc/468x60-oew-web-banner.jpg"></div>                                                                               
                         <div class="box_so_right dd-loto-widget">
                          <c:if test="${listDuoi!=null}"> 
                          <table cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                             <tbody>
                                <tr class="bg_head">
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đầu</h3></td>
                                   <td class="web_XS_4 web_bg_Loto"><h3>Đuôi</h3></td>
                                </tr>
                                <c:set var="dau" value="0"/>
                                <c:forEach items="${listDuoi}" var="duoi">
                                <tr class="web_bg_Trang_1">
                                   <td class="web_XS_3 web_bg_xam">${dau}</td>
                                   <td class="web_XS_4 web_bg_xam">${duoi}</td>
                                </tr>                       
                                    <c:set var="dau" value="${dau+1}"/>    
                                </c:forEach>                                
                            </tbody>
                         </table>
                         </c:if>                           
                          <c:if test="${listDau!=null}">  
                         <table class="dit-loto" cellspacing="1" cellpadding="0" border="0" bgcolor="#dedede">
                             <tbody>
                                <tr class="bg_head">
                                   <td class="text_rightmb web_XS_4 web_bg_Loto"><h3>Đầu</h3></td>
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
                         </c:if>
                         <div class="clearfix"></div>  
                       </div>
                   </div>   
                   </div>
                </div>
            </c:if>
