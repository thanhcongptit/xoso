<%-- 
    Document   : js-lib
    Created on : Nov 17, 2014, 2:28:17 PM
    Author     : Designer Nguyễn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    var contextPath = "<%= request.getContextPath() %>";
</script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type='text/javascript' src='<%= request.getContextPath() %>/resources/js/inputUtil.js'></script>
<script type='text/javascript' src='<%= request.getContextPath() %>/resources/js/filter_input.js'></script>
<script type='text/javascript' src='<%= request.getContextPath() %>/resources/js/friendly_url.js'></script>
<script type='text/javascript' src='<%= request.getContextPath() %>/resources/js/tinybox2.js'></script>
<script type='text/javascript' src='<%= request.getContextPath() %>/resources/js/jquery.price_format.2.0.js'></script>
<script language="javascript" src="<%= request.getContextPath()%>/resources/js/calendar.js"></script>
<link rel='stylesheet' href='<%= request.getContextPath()%>/resources/style/calendar_orange.css' title='calendar'/>
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/style/jquery-ui.css">
<script src="<%= request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/edit_order.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/add_product_news.js"></script>
<script src="<%= request.getContextPath()%>/resources/js/add_product_post.js"></script>



