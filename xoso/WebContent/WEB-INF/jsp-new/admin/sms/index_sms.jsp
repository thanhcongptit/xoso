<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="com.bean.SmsDTO"%>
<%@page import="inet.model.SmsDao"%>
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
        <title>Quản lý SMS</title>
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
                function deleteNews(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa tin này?');
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
                    int id = RequestUtil.getInt(request, "id", 0);
                    SmsDao smsDao = new SmsDao();
                    if(smsDao.delete(id, "sms_content")) {
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
                    <h1>Quản lý SMS</h1>
                    <div style="margin-bottom: 5px;">
                        <a class="btnCMS" href="<%= request.getContextPath() %>/admin/sms/create.htm">Thêm mới</a>
                    </div>
                    <div class="form1">
                        <%
                            
                            SmsDao smsDao = new SmsDao();
                            String openDate = DateUtil.date2String(new Date(), "dd/MM/yyyy");
                            List<SmsDTO> smsDTOs = smsDao.findAllSmsContent(openDate);
                            System.out.println("size : " + smsDTOs.size());
                        %>
                        <%
                            SmsDTO news = null;
                            if(smsDTOs != null){
                        %>
                        <table class="info" style="width: 100%">
                            <tr>
                                <td>STT</td>
                                <td>Cú pháp</td>
                                <td>Đầu số</td>
                                <td>Nội dung</td>
                                <td>Ngày</td>
                                <td>---</td>
                            </tr>
                            <%
                                for(int j=0; j<smsDTOs.size(); j++){
                                news = smsDTOs.get(j);
                            %>
                            <tr>
                                <td><%=j + 1%></td>
                                <td><%= news.getType() %></td>
                                <td><%= news.getShortCode() %></td>
                                <td><%= news.getContent() %></td>
                                <td><%= news.getOpenDate() %></td>
                                <td>
                                     <a onclick=" deleteNews(<%= news.getId()%>) "> Delete</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <%
                            }
                        %>
                       
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>