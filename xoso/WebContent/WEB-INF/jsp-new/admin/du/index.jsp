<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

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
        <title>Quản lý dự đoán miền Bắc</title>
        <%@include file="../include/css-lib.jsp" %>
        <%@include file="../include/js-lib.jsp" %>
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
            </script>
            <%
                String action = RequestUtil.getString(request, "action", "");
                if("updateStatus".equals(action)){
                    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                    int status = RequestUtil.getInt(request, "status", 0);
                    NewsDAO newsDAO = new NewsDAO();
                    if(newsDAO.updateStatus(status, id)){
                        %>
                        <script>alert("Cập nhật thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Cập nhật thất bại!");</script>
                        <%
                    }
                }else if("delete".equals(action)){
                    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                    ThongKeVipDAO thongKeVipDAO = new ThongKeVipDAO();
                    if(thongKeVipDAO.delete(id)){
                        %>
                        <script>alert("Xóa thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Xóa thất bại!");</script>
                        <%
                    }
                }
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Quản lý dự đoán miền Bắc</h1>
                    <div style="margin-bottom: 5px;">
                        <a class="btnCMS" href="<%= request.getContextPath() %>/admin/du/create.htm">Thêm mới</a>
                    </div>
                    <div class="form1">
                        <%
                            int rowsPerPage = 10;
                            int currentPage = 1;
                            int rowNumber = 0;
                            int totalPage = 0;
                            String startDate = "";
                            String endDate = "";
                            
                            ThongKeVipDAO thongKeVipDAO = new ThongKeVipDAO();
                            currentPage = RequestUtil.getInt(request, "p", 1);
                            startDate = RequestUtil.getString(request, "startDate", "");
                            endDate = RequestUtil.getString(request, "endDate", "");
                            
                            rowNumber = thongKeVipDAO.countThongKeVip("TD",startDate, endDate);
                            totalPage = (int) Math.ceil((double) rowNumber / rowsPerPage);
                            List<ThongKeVip> listThongKeVip = thongKeVipDAO.findThongKeVip("TD", startDate, endDate, currentPage, rowsPerPage);
                        %>
                        <%
                            ThongKeVip thongKeVip = null;
                            if(listThongKeVip != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Đầu</td>
                                <td>Đuôi</td>
                                <td>Vip</td>
                                <td>Xiên</td>
                                <td>Về nhiều</td>                                
                                <td>Về ít</td>
                                <td>Ngày</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j<listThongKeVip.size(); j++){
                                thongKeVip = listThongKeVip.get(j);
                            %>
                            <tr>
                                <td><%= (currentPage - 1) * rowsPerPage + j + 1%></td>
                                <td><%= thongKeVip.getDau() %></td>
                                <td><%= thongKeVip.getDuoi() %></td>
                                <td><%= thongKeVip.getVip() %></td>
                                <td><%= thongKeVip.getXien() %></td>
                                <td><%= thongKeVip.getVenhieu()%></td>                                
                                <td><%= thongKeVip.getVeit()%></td>
                                <td><%= thongKeVip.getGen_date()%></td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/admin/du/update.htm?id=<%= thongKeVip.getId() %>"> Sửa </a> | 
                                    <a onclick="deleteTKV(<%= thongKeVip.getId()%>)">Xóa</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <%
                            }
                        %>
                        <div class="navigation">
                            <%
                                if (totalPage > 0) {
                            %>
                            <form method="get" id="frmNavigation">
                                Chuyển trang: <select name="p" use="inputUtil" idFormSubmit="frmNavigation">
                                    <%
                                        for (int i = 0; i < totalPage; i++) {
                                    %>
                                    <option value="<%= i + 1%>" <%= currentPage == (i + 1) ? "selected" : ""%>><%= i + 1%></option>
                                    <%
                                        }
                                    %>                                        
                                </select>
                            </form>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>