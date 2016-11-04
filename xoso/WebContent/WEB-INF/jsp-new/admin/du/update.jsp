<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
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
            <style>
                .error{color:red}
                .ok{color:green}
            </style>
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
                $(function() {
                    $( "input[name=genDate]" ).datepicker();
                 });
            </script>
            <%
                String genDate = DateUtil.date2String(new Date(), "dd/MM/yyyy");
                String msg = "";
                String action = RequestUtil.getString(request, "action", "");
                BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                
                if("update".equals(action) && id != null){
                    String dau = RequestUtil.getString(request, "dau", "");
                    String duoi = RequestUtil.getString(request, "duoi", "");
                    String vip = RequestUtil.getString(request, "vip", "");
                    String xien = RequestUtil.getString(request, "xien", "");
                    String veNhieu = RequestUtil.getString(request, "veNhieu", "");
                    String veIt = RequestUtil.getString(request, "veIt", "");
                    genDate = RequestUtil.getString(request, "genDate", "");
                    
                    ThongKeVipDAO thongKeVipDAO = new ThongKeVipDAO();    
                    if(thongKeVipDAO.findThongKeVip4Update("TD", genDate, id) != null){
                        msg = "<span class='error'>Dự đoán vào ngày này đã được tạo!</span>";
                    }else{
                        ThongKeVip thongKeVipUpdate = new ThongKeVip();
                        thongKeVipUpdate.setDau(dau);
                        thongKeVipUpdate.setDuoi(duoi);
                        thongKeVipUpdate.setVip(vip);
                        thongKeVipUpdate.setXien(xien);
                        thongKeVipUpdate.setVenhieu(veNhieu);
                        thongKeVipUpdate.setVeit(veIt);
                        thongKeVipUpdate.setGen_date(genDate);
                        thongKeVipUpdate.setCode("TD");
                        thongKeVipUpdate.setId(id);
                        if(thongKeVipDAO.update(thongKeVipUpdate)){
                            msg = "<span class='ok'>Cập nhật dự đoán thành công!</span>";
                        }else{
                            msg = "<span class='error'>Cập nhật dự đoán thất bại!</span>";
                        }
                    }
                }
                                
                ThongKeVip thongKeVipCurrent = null;
                if(id != null){
                    ThongKeVipDAO thongKeVipDAO = new ThongKeVipDAO();
                    thongKeVipCurrent = thongKeVipDAO.findById(id);
                }
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Sửa dự đoán miền Bắc</h1>
                    <div class="form1">
                        <div><%= msg %></div>
                        <%
                            if(thongKeVipCurrent != null){
                        %>
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>Đầu</td>
                                    <td>
                                        <input name="dau" maxlength="1" value="<%= thongKeVipCurrent.getDau() != null ? thongKeVipCurrent.getDau() : "" %>"/>
                                        VD: 2
                                    </td>
                                </tr>
                                <tr>
                                    <td>Đuôi</td>
                                    <td>
                                        <input name="duoi" maxlength="1" value="<%= thongKeVipCurrent.getDuoi() != null ? thongKeVipCurrent.getDuoi() : "" %>"/>
                                        VD: 8
                                    </td>
                                </tr>
                                <tr>
                                    <td>Vip</td>
                                    <td>
                                        <input name="vip" value="<%= thongKeVipCurrent.getVip() != null ? thongKeVipCurrent.getVip() : "" %>"/>
                                        VD: 12-21
                                    </td>
                                </tr>
                                <tr>
                                    <td>Xiên</td>
                                    <td>
                                        <input name="xien" value="<%= thongKeVipCurrent.getXien() != null ? thongKeVipCurrent.getXien() : ""%>"/>
                                        VD: (34-56)(40-44)(24-89)(73-96)
                                    </td>
                                </tr>
                                <tr>
                                    <td>Về nhiều</td>
                                    <td>
                                        <input name="veNhieu" value="<%= thongKeVipCurrent.getVenhieu() != null ? thongKeVipCurrent.getVenhieu() : "" %>"/>
                                        VD: 12-34
                                    </td>
                                </tr>
                                <tr>
                                    <td>Về ít</td>
                                    <td>
                                        <input name="veIt" value="<%= thongKeVipCurrent.getVeit() != null ? thongKeVipCurrent.getVeit() : "" %>"/>
                                        VD: 12-34
                                    </td>
                                </tr>
                                <tr>
                                    <td>Ngày</td>
                                    <td><input name="genDate" value="<%= thongKeVipCurrent.getGen_date() %>"/></td>
                                </tr>
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="update"/>
                                        <button type="submit" class="btnCMS">Cập nhật</button>
                                    </td>
                                    <td><a href="<%= request.getContextPath() %>/admin/du/index.htm" class="btnCMS">Trở về</a></td>
                                </tr>
                            </table>
                        </form>
                        <%
                            }else out.print("Dự đoán này không tồn tại!");
                        %>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>