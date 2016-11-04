<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.listener.SystemConfigCache"%>
<%@page import="inet.model.SystemConfigDAO"%>
<%@page import="inet.bean.LienHe"%>
<%@page import="inet.model.LienHeDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="inet.bean.Banner"%>
<%@page import="inet.model.BannerDAO"%>
<%@page import="inet.bean.ThongKeVip"%>
<%@page import="inet.model.ThongKeVipDAO"%>
<%@page import="inet.bean.News"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.model.MemberDAO"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Cấu hình</title>
        <%@include file="../include/css-lib.jsp" %>
        <%@include file="../include/js-lib.jsp" %>
        <style>
            a.tabs{
                padding: 5px;
                color: blue;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../include/header.jsp" %>
            <script>
                function updateStatus(status,id){                    
                    $("#updateForm input[name=action]").val("updateStatus");
                    $("#updateForm input[name=id]").val(id);
                    $("#updateForm input[name=status]").val(status);
                    $("#updateForm").submit();
                }
                function deleteTKV(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa dự đoán này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
                function show(trang){                    
                    $("#updateForm input[name=show]").val(trang);
                    $("#updateForm").submit();
                }
            </script>
            <%
                String action = RequestUtil.getString(request, "action", "");
                String msg = "";
                String trang = RequestUtil.getString(request, "trang", SystemConfigCache.FACEBOOK_CONFIG);
                if("update".equals(action)){                    
                    String value = RequestUtil.getString(request, "value", "");
                    SystemConfigDAO systemConfigDAO = new SystemConfigDAO();
                    if(systemConfigDAO.update(trang, value)){
                        msg = "<span style='color:green'>Cập nhật thành công!</span>";
                        SystemConfigCache.hmSystemConfig.put(trang, value);
                    }else msg = "Cập nhật thất bại!";
                }                
            %>
            <%
                SystemConfigDAO systemConfigDAO = new SystemConfigDAO();
                String value = systemConfigDAO.findByKey(trang);
            %>
            <form style="display: none" id="updateForm" method="get">
                <input type="hidden" name="show"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Quản lý Cấu hình</h1>
                    <div class="form1">
                        <div style="color:red"><%= msg %></div>
                        <div style="margin-bottom:10px">
                            <a class="tabs" href="<%= request.getContextPath() %>/admin/cau-hinh/index.htm">Facebook</a>|<a class="tabs" href="<%= request.getContextPath() %>/admin/cau-hinh/index.htm?trang=footer">Footer</a>
                        </div>
                        <%
                            if("facebook".equals(trang)){
                        %>                        
                        (Điền link page facebook)
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>
                                        <textarea cols="70" rows="10" style="border: solid 1px #0b9444;min-width: 143px;padding: 5px;" name="value"><%= value %></textarea>
                                    </td>
                                </tr>                                
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="update"/>
                                        <button type="submit" class="btnCMS">Cập nhật</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <% }else if("footer".equals(trang)){
                            %>
                                <form method="post">
                                    <table class="table-auto">
                                        <tr>
                                            <td>
                                                <textarea cols="70" rows="10" style="border: solid 1px #0b9444;min-width: 143px;padding: 5px;" name="value"><%= value %></textarea>
                                            </td>
                                        </tr>                                
                                        <tr>                                    
                                            <td>
                                                <input type="hidden" name="action" value="update"/>
                                                <button type="submit" class="btnCMS">Cập nhật</button>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            <%   
                        }%>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>