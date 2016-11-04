<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.model.SmsDao"%>
<%@page import="java.util.UUID"%>
<%@page import="com.bean.SmsDTO"%>
<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@page import="inet.util.EntityDecoder"%>
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
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    List<SmsDTO> cuphap = (List<SmsDTO>) request.getAttribute("cuphap");
%>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý SMS</title>
        <%@include file="../include/css-lib.jsp" %>
        <%@include file="../include/js-lib.jsp" %>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/tinymce/tinymce.min.js"></script>
    </head>
    
    <script type="text/javascript">
        $(function () {
            $('#date').datepicker({
                autoclose: true,
                format: "yyyy-mm-dd",
                todayHighlight: true,
                language: 'vi'
            });
            

        })
    </script>
    <body>
        <div class="container">
            <%@include file="../include/header.jsp" %>
            <style>
                .error{color:red}
                .ok{color:green}
            </style>
            <script>
                tinymce.init({
                    selector: "textarea#content",
                    plugins: [
                        "advlist autolink autosave link image lists charmap print preview hr anchor pagebreak spellchecker",
                        "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
                        "table contextmenu directionality emoticons template textcolor paste fullpage textcolor colorpicker textpattern"
                    ],
                    toolbar1: "code | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | formatselect fontselect fontsizeselect ",
                    toolbar2: "bullist numlist | outdent indent | link unlink image media | table | removeformat | emoticons | forecolor backcolor | preview fullscreen ",
                    menubar: false,
                    toolbar_items_size: 'small',
                    style_formats: [
                        {title: 'Bold text', inline: 'b'},
                        {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
                        {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
                        {title: 'Example 1', inline: 'span', classes: 'example1'},
                        {title: 'Example 2', inline: 'span', classes: 'example2'},
                        {title: 'Table styles'},
                        {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
                    ],
                    templates: [
                        {title: 'Test template 1', content: 'Test 1'},
                        {title: 'Test template 2', content: 'Test 2'}
                    ]
                });
                $(document).ready(function () {
                setTimeout(function () {
                    $(".mce-i-code").html("HTML");
                    $(".mce-i-code").css("font-size", "10px");
                    $(".mce-i-code").css("font-weight", "bold");
                    $(".mce-i-code").parent().css("padding-right", "16px");
                }, 500);
            });
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
                String msg = "";
                String action = RequestUtil.getString(request, "action", "");
                String date = DateUtil.date2String(new Date(), "dd/MM/yyyy");
                if("create".equals(action)){
                    String type = RequestUtil.getString(request, "type", "");
                    String shortCode = RequestUtil.getString(request, "shortCode", "");
                    String content = RequestUtil.getString(request, "content", "");
                    //String content = RequestUtil.getString(request, "content", "");
                    String openDate = request.getParameter("openDate");
                    
                    if("".equals(content)){
                        msg = "Vui lòng điền tiêu đề!";
                    }else{
                        SmsDTO smsDTO = new SmsDTO();
                        smsDTO.setContent(content);
                        smsDTO.setCreatedDate(date);
                        
                        smsDTO.setOpenDate(openDate);
                        smsDTO.setShortCode(shortCode);
                        smsDTO.setType(type);
                        
                        SmsDao newsDAO = new SmsDao();
                        if(newsDAO.create(smsDTO)){
                            msg = "<span style='color:green'>Tạo tin SMS thành công!</span>";
                        }else{
                            msg = "Tạo tin SMS bài thất bại!";
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
                    <h1>Thêm mới SMS hôm nay</h1>
                    <div class="form1">
                        <div style="color: red"><%= msg %></div>
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>Cú pháp</td>
                                    <td>
                                        <select name="type">
                                            <%
                                                if(cuphap != null && cuphap.size() > 0) {
                                                    for(SmsDTO smsDTO: cuphap) {
                                                
                                            %>
                                            <option value="<%=smsDTO.getType() %>"><%=smsDTO.getType() %></option>
                                            <%}}%>
                                        </select>
                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td>Đầu số</td>
                                    <td>
                                        <select name="shortCode">
                                            <%
                                                if(cuphap != null && cuphap.size() > 0) {
                                                    for(SmsDTO smsDTO: cuphap) {
                                                
                                            %>
                                            <option value="<%=smsDTO.getShortCode()%>"><%=smsDTO.getShortCode()%></option>
                                            <%}}%>
                                        </select>    
                                    </td>
                                </tr>
                                <tr>
                                    <td>Nội dung</td>
                                    <td>
                                        <textarea style="border: solid 1px #0b9444;min-width: 143px;padding: 5px;" name="content" rows="5" cols="25"></textarea>
                                        (tối đa 200 ký tự)
                                    </td>
                                </tr>
                                <tr>
                                    <td> <label for="date" class="">Ngày : </label> </td>
                                        <td> <input class="input" type="text" name="openDate" id="date" value="<%=date%> "
                                        style="width: 70px"> </td>
                                </tr>
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="create"/>
                                        <button type="submit" class="btnCMS">Tạo mới</button>
                                    </td>
                                    <td><a href="<%= request.getContextPath() %>/admin/sms.htm" class="btnCMS">Trở về</a></td>
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