<%-- 
    Document   : thongke_mienbac
    Created on : Aug 28, 2015, 10:54:53 AM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    function onChage(){
        var week=$('#week').val();
        var code=$('#code').val();
        loadThongKeMienBac(week,code);
    }
</script>
                   <div id="kqxsmb">
                      <!-- 
                      <h2 class="h3title">Thống kê kết quả xổ số ${code}
                          <input type="hidden" id="code" value="${code}"/>
                          <select class="select" id="week" onchange="onChage();">
                              <option <c:if test="${week=='5'}">selected=""</c:if> value="5">5</option> 
                            <option <c:if test="${week=='10'}">selected=""</c:if> value="10">10</option>
                            <option <c:if test="${week=='15'}">selected=""</c:if> value="15">15</option>
                         </select>
                         tuần
                      </h2>
                      -->
                      <div class="box_so">
                          <div class="box_tkkqxs">
                          <div class="tc_title">10 cặp số về nhiều nhất:</div>
                         <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                            <tbody>
                               <tr class="LOTO_ngang_1">
                                    <td class="tke_1"><p>Cặp số</p></td>
                                    <td class="tke_2"><p>Xuất hiện</p></td>
                               </tr>                               
                               <c:if test="${listCapSo!=null}">
                                   <c:forEach items="${listCapSo}" var="capso" begin="0" end="4">
                                        <tr class="LOTO_ngang_1">
                                            <td class="tke_1">${capso.capso}</td>
                                            <td class="tke_2">${capso.solanxuathien} lần</td>                                            
                                         </tr>
                                    </c:forEach>                                  
                               </c:if>
                            </tbody>          
                         </table>
                          <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                              <tbody>
                               <tr class="LOTO_ngang_1">
                                    <td class="tke_1"><p>Cặp số</p></td>
                                    <td class="tke_2"><p>Xuất hiện</p></td>
                               </tr>                               
                               <c:if test="${listCapSo!=null}">
                                   <c:forEach items="${listCapSo}" var="capso" begin="5" end="9">
                                        <tr class="LOTO_ngang_1">
                                            <td class="tke_1">${capso.capso}</td>
                                            <td class="tke_2">${capso.solanxuathien} lần</td>                                            
                                         </tr>
                                    </c:forEach>                                  
                               </c:if>          
                              </tbody>         
                          </table>   
                          <div class="clearfix"></div>
                          </div>
                          <div class="box_tkkqxs">
                          <div class="tc_title">10 cặp số về ít nhất:</div>
                         <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                            <tbody>
                               <tr class="LOTO_ngang_1">
                                    <td class="tke_1"><p>Cặp số</p></td>
                                    <td class="tke_2"><p>Xuất hiện</p></td>
                               </tr>                               
                               <c:if test="${listCapSo!=null}">
                                   <c:forEach items="${listCapSo}" var="capso" begin="95" end="99">
                                        <tr class="LOTO_ngang_1">
                                            <td class="tke_1">${capso.capso}</td>
                                            <td class="tke_2">${capso.solanxuathien} lần</td>                                            
                                         </tr>
                                    </c:forEach>                                  
                               </c:if>
                            </tbody>          
                         </table>
                          <table width="100%" cellspacing="1" cellpadding="1" border="0" bgcolor="#dedede">
                              <tbody>
                               <tr class="LOTO_ngang_1">
                                    <td class="tke_1"><p>Cặp số</p></td>
                                    <td class="tke_2"><p>Xuất hiện</p></td>
                               </tr>                               
                               <c:if test="${listCapSo!=null}">
                                   <c:forEach items="${listCapSo}" var="capso" begin="90" end="94">
                                        <tr class="LOTO_ngang_1">
                                            <td class="tke_1">${capso.capso}</td>
                                            <td class="tke_2">${capso.solanxuathien} lần</td>                                            
                                         </tr>
                                    </c:forEach>                                  
                               </c:if>          
                              </tbody>         
                          </table>   
                          <div class="clearfix"></div>
                          </div>
                         </div>
                   </div>
