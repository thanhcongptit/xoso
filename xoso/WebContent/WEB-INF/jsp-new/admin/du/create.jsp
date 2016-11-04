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
                if("create".equals(action)){
                    String dau = RequestUtil.getString(request, "dau", "");
                    String duoi = RequestUtil.getString(request, "duoi", "");
                    String vip = RequestUtil.getString(request, "vip", "");
                    String xien = RequestUtil.getString(request, "xien", "");
                    String veNhieu = RequestUtil.getString(request, "veNhieu", "");
                    String veIt = RequestUtil.getString(request, "veIt", "");
                    genDate = RequestUtil.getString(request, "genDate", "");
                    
                    ThongKeVipDAO thongKeVipDAO = new ThongKeVipDAO();    
                    if(thongKeVipDAO.findThongKeVip("TD", genDate) != null){
                        msg = "<span class='error'>Dự đoán vào ngày này đã được tạo!</span>";
                    }else{
                        ThongKeVip thongKeVipCreate = new ThongKeVip();
                        thongKeVipCreate.setDau(dau);
                        thongKeVipCreate.setDuoi(duoi);
                        thongKeVipCreate.setVip(vip);
                        thongKeVipCreate.setXien(xien);
                        thongKeVipCreate.setVenhieu(veNhieu);
                        thongKeVipCreate.setVeit(veIt);
                        thongKeVipCreate.setGen_date(genDate);
                        thongKeVipCreate.setCode("TD");
                        if(thongKeVipDAO.create(thongKeVipCreate)){
                            msg = "<span class='ok'>Tạo dự đoán thành công!</span>";
                        }else{
                            msg = "<span class='error'>Tạo dự đoán thất bại!</span>";
                        }
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
                    <h1>Thêm mới dự đoán miền Bắc</h1>
                    <div class="form1">
                        <div><%= msg %></div>
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>Đầu</td>
                                    <td>
                                        <input name="dau" maxlength="1"/>
                                        VD: 2
                                    </td>
                                </tr>
                                <tr>
                                    <td>Đuôi</td>
                                    <td>
                                        <input name="duoi" maxlength="1"/>
                                        VD: 8
                                    </td>
                                </tr>
                                <tr>
                                    <td>Vip</td>
                                    <td>
                                        <input name="vip"/>
                                        VD: 12-21
                                    </td>
                                </tr>
                                <tr>
                                    <td>Xiên</td>
                                    <td>
                                        <input name="xien"/>
                                        VD: (34-56)(40-44)(24-89)(73-96)
                                    </td>
                                </tr>
                                <tr>
                                    <td>Về nhiều</td>
                                    <td>
                                        <input name="veNhieu"/>
                                        VD: 12-34
                                    </td>
                                </tr>
                                <tr>
                                    <td>Về ít</td>
                                    <td>
                                        <input name="veIt"/>
                                        VD: 12-34
                                    </td>
                                </tr>
                                <tr>
                                    <td>Ngày</td>
                                    <td><input name="genDate" value="<%= genDate %>"/></td>
                                </tr>
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="create"/>
                                        <button type="submit" class="btnCMS">Tạo mới</button>
                                    </td>
                                    <td><a href="<%= request.getContextPath() %>/admin/du/index.htm" class="btnCMS">Trở về</a></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>