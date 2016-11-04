<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

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
        <title>Quản lý thành viên</title>
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
                function deleteMember(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa thành viên này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
            </script>
            <%
                String action = RequestUtil.getString(request, "action", "");                
                Member member = null;
                String msg = "";
                BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                if("update".equals(action) && id != null){
                    Integer money = RequestUtil.getInt(request, "money", null);
                    if(money == null){
                        msg = "Vui lòng nhập số xu là số!";
                    }else if(money < 0){
                        msg = "Vui lòng nhập số xu lớn hơn 0!";
                    }else{
                        MemberDAO memberDAO = new MemberDAO();
                        if(memberDAO.updateMoney(id, money)){
                            msg = "<span style='color:green'>Thay đổi xu thành công</span>";
                        }else{
                            msg = "Thay đổi xu không thành công!";
                        }
                    }
                }
                if(id != null){
                    MemberDAO memberDAO = new MemberDAO();
                    member = memberDAO.getRowById(id);                    
                }
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Thay đổi xu</h1>
                    <%
                        if(member != null){
                    %>
                    <div style="color: red"><%= msg %></div>
                    <div class="form1">
                        <form method="POST">
                            <table class="table-auto">
                                <tr>
                                    <td>Số xu hiện tại</td>
                                    <td>
                                        <%= member.getMoney() %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Số xu thay đổi</td>
                                    <td>
                                        <input name="money"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>    
                                        <input name="action" value="update" type="hidden"/>
                                        <button type="submit" class="btnCMS">Thay đổi</button>
                                    </td>
                                    <td>
                                        <a class="btnCMS" href="<%= request.getContextPath() %>/admin/member/index.htm">Trở về</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <%
                        }else out.print("Thành viên này không tồn tại!");
                    %>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>