<%-- 
    Document   : body-calendar-left
    Created on : 14-Jan-2016, 09:13:16
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        $('#datepicker').datepicker({
            inline: true,
            showOtherMonths: true,
            dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']                                    
        });
    });
</script>
<!DOCTYPE html>
<div id="calendar">
    <h2 class="h1title1"><a>Kết quả xổ số ngày</a></h2>
    <div id="mydate" gldp-id="mydate"></div>
    <div gldp-el="mydate" style="width:212px; height:200px;">
    </div>
</div>