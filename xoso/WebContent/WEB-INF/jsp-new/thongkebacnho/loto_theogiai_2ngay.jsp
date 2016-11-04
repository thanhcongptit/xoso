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
            Lotto về theo giải
        </title>
        <link rel="shortcut icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAABklBMVEUAAAD////l4dzm493k4Nvm493m493m493m493V2NiLxNrm493a1dLj39rm493b3dq81t7Q19eazN/W0c7Y1NDL297LxMPi4t2Vy9/M297c19Pm493E2d7k493m493J296z1N6Du9K21d7I2t6t0t7m493Tzcvm493NxsVpudlmttdlveBbs9h6u9bg4d1Tstrg4d2XxNam0N4lqeElquErq+AsrOEsrOIvq94vrOAwrOAxrOAxreAyrd8yreAzreA0rd41rt41ruA2ruA4r+A5r+A6rdw9seFAsuFBsuFCsN5CsuBGst9IteNPt+NQuORcvudiut9mwuhowuhzwN96wN2Fzu2LyuSS1O+Wyt+W1e6m2/Gnzt+v3/Ow3/KyytW+2uW/5fXA5fXB2uXG6PbL6vfO6/fPzc3VzszY0c/Z0tDa8Prc1dPf2NXf8vrg2dfh2tji29ji3Nnj9PvtHyTtLTLuLjPvPUHzam30eXz1iIr1+/73lpn3/P74paf5tLb6w8X7/f79/v/+8PD+//////8O3ccoAAAAM3RSTlMAAAEBAgIGDBESFxkhLjAxMjk/R0pMXWZrgICHlpianKKjvMTF0dbd6e/x8vT29/r6+/4dYH0dAAAA10lEQVQY00XIy0rDQBiA0W/+SSY3Go21BPG2EMGi4Cv44j6IRSkFUaO1xqRNJrWTuHDhWR4FkO5HUd825QZQYPLJZTxydfP0UoCHPp1mVwBOJg8LNMd5fg2wac4/6RoJb9UFVHXrZWpsDpGsOIoh7QngxM+NRGigVxrwvC4SYQs0gQNWvYjsqOasjdgOXgexmkwPiR/qyGPx5r6etT0QVbk9wc4K9/NeatyZNcN3vfxorVs+DprWejdpEsbJyMzmWxQQjNM7pYb7crUDBYAfBkO3BkD9zb9ft/1SXawC1lwAAAAASUVORK5CYII=" type="image/x-icon"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="ket qua xo so, du doan ket qua xo so, thong ke vip, dự đoán xổ số, du doan xo so mien bac, thong ke bac nho, thong ke xo so mien bac">
        <meta name="description" content="soicaupro.com  - Hệ thống cung cấp các chức năng thống kê kết quả xổ số, lotto chính xác, hiệu quả cao">
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
         String datetime = (String) request.getAttribute("datetime");
         String date_begin =  (String) request.getAttribute("date_begin");
         String date_end = (String) request.getAttribute("date_end");
         String miss = (String) request.getAttribute("miss");
         Date currentDate = new Date();
         
         if(CommonUtil.isEmptyString(datetime)) {
        	 datetime = "";
         }
         
         if(CommonUtil.isEmptyString(miss)) {
             miss = "3";
         }
         
         if(CommonUtil.isEmptyString(date_begin)) {
             date_begin = CommonUtil.addDate1(currentDate, -4);
         } 
         
         if(CommonUtil.isEmptyString(date_end)) {
             date_end = CommonUtil.addDate1(currentDate, -1);
         } else {
             Date endDate = CommonUtil.convertStringToDate(date_end);
             if(!endDate.before(currentDate)) {
                 date_end = CommonUtil.addDate1(currentDate, -1);
             }
         }
         
         Date endDate = CommonUtil.convertStringToDate(date_end);
         Date beginDate = CommonUtil.convertStringToDate(date_begin);
         
         if(beginDate.after(endDate) || endDate.after(CommonUtil.addDate2(beginDate, 3))) {
             date_begin = CommonUtil.addDate1(endDate, -3);
         }
         
         System.out.println("datetime: " + datetime);
         System.out.println("date_begin: " + date_begin);
         System.out.println("date_end: " + date_end);
         System.out.println("miss: " + miss);
         
         List<LotoTheoGiaiDTO> results = null;
         
         if(!CommonUtil.isEmptyString(datetime)) {
        	 results = new LotoTheoGiaiBus().getListLotoTheoGiaiDTOs
        			 (date_begin, date_end, datetime, Integer.parseInt(miss), 2);
         }
         
     %>   

    <body>

        <jsp:include page="../thongkebacnho/header_thongkebacnho.jsp"></jsp:include>
        <div id="tkv-wrapper" class="fix-clear"><!-- Bắt đầu phần thân -->

            <section class="sc-section">
                <div class="tkv-content">
                    <h1 class="sc-tt">(Soi cầu PRO, Lotto cặp về trong 2 ngày theo giải)</h1>
                </div>
            </section>

            <section class="sc-section"> 
                <div class="sc-grp-columb input-prepend">
                    <form method="POST" action="${pageContext.servletContext.contextPath}/theogiai_rs_loto_2_ngay.htm" accept-charset="UTF-8" class="frm" name="frm" autocomplete="off">
                        Thống kê dữ liệu từ <input type="text" name="datetime" id="datetime" <%if(CommonUtil.isEmptyString(datetime)) { %> value="01-01-2005"<% } else {%> value="<%=datetime %>"<%} %> class="form-control" style="width:100px">
                        Thống kê từ ngày <input type="text" name="date_begin" id="date_begin" value="<%=date_begin %>" class="form-control"> 
                        đến ngày <input type="text" name="date_end" id="date_end" value="<%=date_end %>" class="form-control"> 
                        <br /> Giới hạn Miss <=
                        <select name="miss" class="form-control" id="miss">
							<%for(int i=0; i<4; i++) {%>
							 
                            <option value="<%=i%>" <%if(miss.equals(""+i)) { %>  selected <%} %> ><%=i%></option>     
                          	<% }%>
                          </select>
                            
                        lần &nbsp
<!--                        <select name="missing" class="form-control" id="missing">
                            <option value="0"  selected>0</option><option value="1"  >1</option><option value="2"  >2</option><option value="3"  >3</option>            	<option value="4">All</option>
                        </select>
                        lần &nbsp-->
                        <button type="submit" class="btn-primary submit-form">Tra kết quả</button>
                    </form><br />
                    <p style="color:red">
                        Chú ý : Mỗi lần chỉ xem được tối đa 4 ngày .

                    </p>
                </div>           
            </section>

            <section class="sc-section detail_result">
                <center>
                    <table class="sc-table-tsc">

                        <tr style="background-color:#FFCC99;height:30px;white-space:nowrap;">
                            <td rowspan="2" style="background-color:#FF99FF;width:45px;">TT</td>
                            <td rowspan="2" style="background-color:#FF99FF;width:90px;">Ngày thống kê</td>
                            <td rowspan="2" scope="col" style="background-color:#0066FF;width:45px;"><span style="color:#FFF">Giải</span></td>
                            <td rowspan="2" scope="col" style="width:45px;">Về</td>
                            <td rowspan="2" scope="col" style="width:45px;">S.lần</td>
                            <td rowspan="2" scope="col" style="width:60px;">Loto</td>
                            <td rowspan="2" scope="col" style="width:30px;">Miss</td>
                            <td colspan="3" >Trong đó số lần</td>

                            <td colspan="10" >Trong 10 lần gần đây nhất</td>
                            <td rowspan="2" scope="col" style="background-color:#FFCC66;width:150px;">Kết quả</td>
                        </tr>
                        <tr>
                            <td scope="col" style="background-color:#FFCC66;width:30px;">N1</td>
                            <td scope="col" style="background-color:#99CC00;width:30px;">N2</td>
                            <td scope="col" style="background-color:#99FF66;width:30px;">N3</td>
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
                        		LotoTheoGiaiDTO lotoTheoGiaiDTO = results.get(i);
                        		List<LotoTheoGiaiDetailDTO> detailDTOs = lotoTheoGiaiDTO.getList10();
                        		
                        		if(i==0) {%>
                        			<tr> <td colspan="21">&nbsp;</td> </tr>
                        		
                        		<%}  %>
                        		<tr>
                        			<td style="background-color:#FFCCFF;"><%=(i+1) %></td>
                        			<td style="background-color:#FFCCFF;"><%=CommonUtil.convertDateToString2(lotoTheoGiaiDTO.getOpenDate()) %></td>
                        			<td style="background-color:#0066FF;"><span style="color:#FFF"><%=lotoTheoGiaiDTO.getNameAward() %></span></td>
                        			<td> <%=lotoTheoGiaiDTO.getValue() %> </td>
                        			<td> <%=lotoTheoGiaiDTO.getTimes() %> </td>
                        			<td><b><%=lotoTheoGiaiDTO.getLoto()[0]%>,<%=lotoTheoGiaiDTO.getLoto()[1]%></b></td>
                        			<td><%=lotoTheoGiaiDTO.getMiss() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN1() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN2() %> </td>
                        			<td><%=lotoTheoGiaiDTO.getN3() %> </td>
                        			
                        			<% for(LotoTheoGiaiDetailDTO detailDTO: detailDTOs) {%>
                        				<td style="<% if(detailDTO.getColorDay().equals("1")) {%> background-color:#FFCC66; <%}
                        					else if(detailDTO.getColorDay().equals("2")) {%>background-color:#99CC00 <% }%>"><%=detailDTO.getRs() %></td>
                        			<%} %>
                        			<td><p style="color:red;"><b><%=lotoTheoGiaiDTO.getRs() %></b></p></td>
                        		</tr>
                        		
                        		<% if(i+1 < (results.size())) {
                        				if(results.get(i+1).getOpenDate().after(lotoTheoGiaiDTO.getOpenDate())) {%>
                        				<tr> <td colspan="21">&nbsp;</td> </tr>
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