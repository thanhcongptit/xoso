<%@page import="inet.util.DateUtil"%>
<%@page import="com.soicaupro.thongkebacnho.bus.LotoTheoBo4BachthuBus"%>
<%@page import="com.soicaupro.thongkebacnho.bus.LotoTheoBo3BachthuBus"%>
<%@page import="com.soicaupro.thongkebacnho.bus.LotoTheoBo2BachthuBus"%>
<%@page import="com.soicaupro.thongkebacnho.bus.LotoTheoGiaiBachThuBus"%>
<%@page import="com.soicaupro.thongkebacnho.domain.LotoTheoGiaiBachThuDTO"%>
<%@page import="com.soicaupro.thongkebacnho.domain.LotoTheoGiaiDetailDTO"%>
<%@page import="com.soicaupro.thongkebacnho.bus.LotoTheoGiaiBus"%>
<%@page import="com.soicaupro.thongkebacnho.domain.LotoTheoGiaiDTO"%>
<%@page import="com.soicaupro.thongkebacnho.bus.ScreenBachthuThreeDayBus"%>
<%@page import="com.soicaupro.thongkebacnho.domain.ScreenBachthuSpecialDTO"%>
<%@page import="com.soicaupro.thongkebacnho.bus.ScreenThreeDayBus"%>
<%@page import="com.soicaupro.thongkebacnho.dao.CoupleLotoADayDao"%>
<%@page import="java.util.*"%>
<%@page import="com.soicaupro.thongkebacnho.domain.ResultOneDayDTO"%>
<%@page import="com.soicaupro.thongkebacnho.Constant"%>
<%@page import="com.soicaupro.thongkebacnho.domain.ScreenOneDayDTO"%>
<%@page import="com.soicaupro.thongkebacnho.domain.ScreenThreeDaySpecialDTO"%>
<%@page import="com.soicaupro.thongkebacnho.dao.SpecialAwardDao"%>
<%@page import="com.soicaupro.thongkebacnho.dao.SpecialAwardDao"%>
<%@page import="com.soicaupro.thongkebacnho.domain.SpecialAwardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>
            Lotto về theo bộ
        </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="ket qua xo so, du doan ket qua xo so, thong ke vip, dự đoán xổ số, du doan xo so mien bac, thong ke bac nho, thong ke xo so mien bac">
        <meta name="description" content="soicaupro.com – thống kê xổ số, thống kê bạc nhớ, phân tích xổ số, loto chính xác">
        <link media="all" type="text/css" rel="stylesheet" href="css/bootstrap.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/animate.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/style_4.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/responsive.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/css-reset.css">

        <link rel="shortcut icon" href="assets/images/favicon.ico">
        <script src="js/jquery-1.11.1.js"></script>

        <script src="js/bootstrap.min.js"></script>
        <link media="all" type="text/css" rel="stylesheet"
              href="css/main.css">
     <%
         String datetime = (String)request.getAttribute("datetime");
         String date_end = (String)request.getAttribute("date_end");
         String miss = (String) request.getAttribute("miss");
         Date currentDate = DateUtil.toDate(DateUtil.date2String
            (new Date(), Constant.FORMAT_DATETIME1), Constant.FORMAT_DATETIME1);
         
         /* if(CommonUtil.isEmptyString(datetime)) {
        	 datetime = "01-01-2014";
         } */ 
          /* else {
        	 Date dataDate = CommonUtil.convertStringToDate(datetime);
        	 if(dataDate.before(CommonUtil.convertStringToDate("01-01-2014"))) {
        		 datetime = "01-01-2014";
        	 }
         } */
         
         
         if(CommonUtil.isEmptyString(date_end)) {
             date_end = CommonUtil.addDate1(currentDate, 0);
         } else {
             Date endDate = CommonUtil.convertStringToDate(date_end);
             if(!endDate.before(currentDate)) {
               if(CommonUtil.checkDate()) {
                     date_end = CommonUtil.addDate1(currentDate, -1);
                } else {
                     date_end = CommonUtil.addDate1(currentDate, 0);
                }
             } 
         }
         
         Date endDate = CommonUtil.convertStringToDate(date_end);
         
         System.out.println("datetime: " + datetime);
         System.out.println("date_end: " + date_end);
         System.out.println("miss: " + miss);
         
         List<LotoTheoGiaiBachThuDTO> results = null;
         
         if(!CommonUtil.isEmptyString(datetime)) {
                //results = new LotoTheoBo4BachthuBus().getListLotoBo4BachThu(date_end, "01-01-2005", 0);
                results = CommonUtil.getValueByKeyBachThu(date_end);
                if(results == null || results.size() == 0) {
                     results = new LotoTheoBo4BachthuBus().getListLotoBo4BachThu(date_end, "01-01-2005", 0);
                     
                     if(results != null && results.size() > 0) {
                         CommonUtil.putValueBachThu(date_end, results);
                     }
                }
         } 
         
     %>   

    <body>
        <div class = "body_container">
        <jsp:include page="../thongkebacnho/header_thongkebacnho.jsp"></jsp:include>

        <div id="tkv-wrapper" class="fix-clear"><!-- Bắt đầu phần thân -->

            <section class="sc-section">
                <div class="tkv-content">
                    <h1 class="sc-tt">(Soi cầu PRO, Loto về theo bộ 4 loto về cùng nhau)</h1>
                </div>
            </section>

            <section class="sc-section"> 
                <div class="sc-grp-columb input-prepend">
                    <form method="POST" action="${pageContext.servletContext.contextPath}/bo4_bachthu_rs.htm" accept-charset="UTF-8" class="frm" name="frm" autocomplete="off">
                        Thống kê ngày <input type="text" name="date_end" id="date_end" value="<%=date_end %>" class="form-control"> 
                        &nbsp Giới hạn Miss <=
                        <select name="miss" class="form-control" id="miss">
							 
                            <option value="0" selected="selected">0</option>     
                          </select>
                            
                        lần &nbsp
<!--                        <select name="missing" class="form-control" id="missing">
                            <option value="0"  selected>0</option><option value="1"  >1</option><option value="2"  >2</option><option value="3"  >3</option>            	<option value="4">All</option>
                        </select>
                        lần &nbsp-->
                        <button type="button" class="btn-primary submit-form">Tra kết quả</button>
                    </form><br />
                    <p style="color:red">
                    	- Chỉ lọc kết quả những bộ 4 lotto về cùng nhau >= 20 <br/> 
						- Dữ liệu được thống kê từ 01/01/2005 <br/>
                        Chú ý : Mỗi lần chỉ xem được tối đa 1 ngày .

                    </p>
                </div>           
            </section>
               
            <div id="loading" style="display:none">
                <center>
                    <img src='images/loading.gif' style="width:64px" alt="thong ke vip"><br />
                    <p style="color:red">Đang tải dữ liệu, xin bạn vui lòng chờ trong giây lát ...</p>
                </center>
            </div>   
                        
            <section class="sc-section detail_result">
                <center>
                    <table class="sc-table-tsc">

                        <tr style="background-color:#FFCC99;height:30px;white-space:nowrap;">
                            <td rowspan="2" style="background-color:#FF99FF;width:45px;">TT</td>
                            <td rowspan="2" style="background-color:#FF99FF;width:90px;">Ngày thống kê</td>
                            <td rowspan="2" scope="col" style="width:45px;">Bộ loto</td>
                            <td rowspan="2" scope="col" style="width:45px;">S.lần</td>
                            <td rowspan="2" scope="col" style="width:60px;">Loto</td>
                            <td rowspan="2" scope="col" style="width:30px;">Miss</td>
                            <td colspan="5" >Trong đó số lần</td>

                            <td colspan="10" >Trong 10 lần gần đây nhất</td>
                            <td rowspan="2" scope="col" style="background-color:#FFCC66;width:150px;">Kết quả</td>
                        </tr>
                        <tr>
                            <td scope="col" style="background-color:#FFCC66;width:30px;">N1</td>
                            <td scope="col" style="background-color:#99CC00;width:30px;">N2</td>
                            <td scope="col" style="background-color:#99FF66;width:30px;">N3</td>
                            <td scope="col" style="background-color:#FFCCFF;width:30px;">N4</td>
                            <td scope="col" style="background-color:#99FF66;width:30px;">N5</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L1</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L2</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L3</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L4</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L5</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L6</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L7</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L8</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L9</td>
                            <td scope="col" style="background-color:#33CCFF;width:30px;">L10</td>
                        </tr>
                        
                        <%if(results != null) { 
                        	for(int i=0; i< results.size(); i++) {
                        		LotoTheoGiaiBachThuDTO lotoTheoGiaiDTO = results.get(i);
                        		List<LotoTheoGiaiDetailDTO> detailDTOs = lotoTheoGiaiDTO.getList10();
                        %>		
                        		<tr>
                        			<td style="background-color:#FFCCFF;"><%=(i+1) %></td>
                        			<td style="background-color:#FFCCFF;"><%=CommonUtil.convertDateToString2(lotoTheoGiaiDTO.getOpenDate()) %></td>
                        			<td><b> <%=lotoTheoGiaiDTO.getValue() %> </b></td>
                        			<td> <%=lotoTheoGiaiDTO.getTimes() %> </td>
                        			<td><b><%=lotoTheoGiaiDTO.getLoto()%></b></td>
                        			<td><%=lotoTheoGiaiDTO.getMiss() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN1() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN2() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN3() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN4() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN5() %> </td>
                        			
                        			<% for(LotoTheoGiaiDetailDTO detailDTO: detailDTOs) {%>
                        				<td style="<% if(detailDTO.getColorDay().equals("1")) {%> background-color:#FFCC66; <%}
                        					else if(detailDTO.getColorDay().equals("2")) {%>background-color:#99CC00 <% } 
                        					else if(detailDTO.getColorDay().equals("3")) {%>background-color:#99FF66 <% }
                        					else if(detailDTO.getColorDay().equals("4")) {%>background-color:#FFCCFF <% }
                        					else if(detailDTO.getColorDay().equals("5")) {%> background-color:#33CCFF <% }%>"><%=detailDTO.getRs() %></td>
                        			<%} %>
                        			<td><p style="color:red;"><b><%=lotoTheoGiaiDTO.getRs() %></b></p></td>
                        		</tr>
                        		
                        		<% if(i+1 < (results.size())) {
                        				if(results.get(i+1).getOpenDate().after(lotoTheoGiaiDTO.getOpenDate())) {%>
                        				<tr> <td colspan="22">&nbsp;</td> </tr>
                        		<%} }%>
                        
                        <%} }%>
                        
                    </table>
                    <style>
                        .sc-table-tsc td{padding: 0 3px;}
                    </style>
                    <br />
                    <br />

                    <table id="" cellspacing="5" rules="rows" border="1" style="border-style:Double;">
                        <tbody><tr>
                                <td align="left" style="width:110px;">Về</td>
                                <td align="left">Kết quả 2 số cuối của giải, tức là loto 2 số;</td>
                                <td style="width:50px;"></td>
                                <td align="left" style="width:110px;">S.lần</td>
                                <td align="left">Số lần xuất hiện giải tương ứng với kết quả;</td>
                            </tr><tr>
                                <td align="left">Loto</td>
                                <td align="left">Loto thống kê được thỏa mãn điều kiện đặt ra;</td>
                                <td style="width:20px;"></td>
                                <td align="left">Miss</td>
                                <td align="left">Số lần trượt loto tương ứng với kết quả của giải;</td>
                            </tr><tr>
                                <td align="left">N1, N2, N3, N4, N5</td>
                                <td align="left">Số lần về Ngày 1, Ngày 2, Ngày 3, Ngày 4, Ngày 5 của loto;</td>
                                <td style="width:20px;"></td>
                                <td align="left">L1,...L10</td>
                                <td align="left">Lịch sử 10 lần gần đây nhất của loto theo giải;</td>
                            </tr><tr>
                                <td align="left">Kết quả</td>
                                <td align="left">Win loto thống kê Ngày 1, Ngày 2, Ngày 3, Ngày 4, Ngày 5;</td>
                                <td style="width:20px;"></td>
                                <td align="left">Màu L1,...L10</td>
                                <td align="center"><span style="display:inline-block;background-color:#FFCC66;width:80px;">Về ngày 1</span>
                                    <span style="display:inline-block;width:10px;"></span>
                                    <span style="display:inline-block;background-color:#99CC00;width:80px;">Về ngày 2</span>
                                    <span style="display:inline-block;width:10px;"></span>
                                    <span style="display:inline-block;background-color:#99FF66;width:80px;">Về ngày 3</span>
                                    <span style="display:inline-block;width:10px;"></span>
                                    <span style="display:inline-block;background-color:#FFCCFF;width:80px;">Về ngày 4</span>
                                    <span style="display:inline-block;width:10px;"></span>
                                    <span style="display:inline-block;background-color:#33CCFF;width:80px;">Về ngày 5</span>
                                </td>
                            </tr>
                        </tbody></table>
                </center>
            </section>

        </div><!-- Hết phần thân -->
        <jsp:include page="../include/footer.jsp"></jsp:include>
        </div>
        <div id = "container1"> </div>
        <script src="js/jquery-1.11.1.js"></script>

        <script src="js/bootstrap-datepicker.js"></script>
    </script>        <!-- Jquery Scroll Animated -->

  <script type="text/javascript">
        $(function () {
           /*  $('.submit-form').click(function () {
                $('.detail_result').empty();
                $('#loading').show();
                $.ajax({
                    url: $('.frm').attr('action'),
                    type: "POST",
                    data: $('.frm').serialize(),
                }).done(function (data) {
                    $('#loading').hide();
                    $('.detail_result').html(data);
                });
            }) */
             $('.submit-form').click(function () {
                console.log("data: " + $('.frm').serialize());
                console.log("url: " + "${pageContext.servletContext.contextPath}/bo4_bachthu_rs.htm"); 
                $('.detail_result').empty();
                $('#loading').show();
                $.ajax({
                    url: $('.frm').attr('action'),
                    type: "POST",
                    data: $('.frm').serialize(),
                }).done(function (data) {
                    $('#loading').hide();
                    $('.body_container').empty();
                    $('#container1').html(data);
                }).always(function () {
                     window.setTimeout('fail', 300);
                 });
            })
        
            $('#date_end,#date_begin,#datetime').datepicker({
                autoclose: true,
                format: "dd-mm-yyyy",
                todayHighlight: true,
                language: 'vi'
            });
           /*  $("#miss option[value='3']").prop('selected', true);
            $("#missing option[value='0']").prop('selected', true); */
        })
    </script> 
    <script type="text/javascript">
        $(function () {
            $('#date').datepicker({
                autoclose: true,
                format: "dd-mm-yyyy",
                todayHighlight: true,
                language: 'vi'
            });

        })
    </script>	

    <link media="all" type="text/css" rel="stylesheet" href="css/bootstrap-datepicker3.standalone.css">

</div>

</body>
</html>