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
                if("updateStatus".equals(action)){
                    BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                    int status = RequestUtil.getInt(request, "status", 0);
                    MemberDAO memberDAO = new MemberDAO();
                    if(memberDAO.updateStatus(id, status)){
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
                    MemberDAO memberDAO = new MemberDAO();
                    if(memberDAO.delete(id)){
                        %>
                        <script>alert("Xóa thành viên thành công!");</script>
                        <%
                    }else{
                        %>
                        <script>alert("Xóa thành viên thất bại!");</script>
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
                    <h1>Quản lý thành viên</h1>
                    <div class="form1">
                        <%
                            int rowsPerPage = 10;
                            int currentPage = 1;
                            int rowNumber = 0;
                            int totalPage = 0;
                            MemberDAO memberDAO = new MemberDAO();
                            currentPage = RequestUtil.getInt(request, "p", 1);
                            String username = RequestUtil.getString(request, "username", "");
                            String fullname = RequestUtil.getString(request, "fullname", "");
                            String email = RequestUtil.getString(request, "email", "");
                            rowNumber = memberDAO.count(username, fullname, email);
                            totalPage = (int) Math.ceil((double) rowNumber / rowsPerPage);
                            List<Member> listMember = memberDAO.find(username, fullname, email, currentPage, rowsPerPage);
                        %>
                        <%
                            Member member = null;
                            if(listMember != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Username</td>
                                <td>Facebook Id</td>
                                <td>Google Id</td>
                                <td>Họ tên</td>
                                <td>Email</td>
                                <td>Trạng thái</td>                                
                                <td>Ngày tham gia</td>
                                <td>Số xu</td>
                                <td>Hành động</td>
                            </tr>
                            <%
                                for(int j=0; j<listMember.size(); j++){
                                member = listMember.get(j);
                            %>
                            <tr>
                                <td><%= (currentPage - 1) * rowsPerPage + j + 1%></td>
                                <td style="text-align: left"><%= member.getUsername() != null ? member.getUsername() : ""%></td>
                                <td style="text-align: left"><%= member.getFacebookId() != null ? member.getFacebookId() : ""%></td>
                                <td style="text-align: left"><%= member.getGoogleId() != null ? member.getGoogleId() : ""%></td>
                                <td style="text-align: left"><%= member.getFullname() != null ? member.getFullname() : ""%></td>
                                <td style="text-align: left"><%= member.getEmail() != null ? member.getEmail() : ""%></td>
                                <td><%= member.getStatus() == 1 ? "Hoạt động" : "Khóa"%></td>                                
                                <td><%= member.getGenDate()%></td>
                                <td><%= member.getMoney()%></td>
                                <td>
                                    <%
                                        if(member.getStatus() == 1){
                                    %>
                                        <a onclick="updateStatus(0,<%= member.getId() %>)">Khóa</a>
                                    <%
                                        }else{
                                    %>
                                        <a onclick="updateStatus(1,<%= member.getId() %>)">Mở khóa</a>
                                    <%
                                        }
                                    %>
                                    | <a href="<%= request.getContextPath() %>/admin/member/update.htm?id=<%= member.getId() %>">Sửa</a>
                                    | <a onclick="deleteMember(<%= member.getId() %>)">Xóa</a>
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