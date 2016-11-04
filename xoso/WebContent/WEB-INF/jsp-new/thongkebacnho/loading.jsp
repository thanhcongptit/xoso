<%@page import="com.soicaupro.thongkebacnho.bus.LotoTheoDacBietBus"%>
<%@page import="com.soicaupro.thongkebacnho.domain.LotoDacBiet1NgayDTO"%>
<%@page import="com.soicaupro.thongkebacnho.dao.CoupleLotoADayDao"%>
<%@page import="java.util.*"%>
<%@page import="com.soicaupro.thongkebacnho.domain.ResultOneDayDTO"%>
<%@page import="com.soicaupro.thongkebacnho.Constant"%>
<%@page import="com.soicaupro.thongkebacnho.domain.ScreenOneDayDTO"%>
<%@page import="com.soicaupro.thongkebacnho.dao.SpecialAwardDao"%>
<%@page import="com.soicaupro.thongkebacnho.dao.SpecialAwardDao"%>
<%@page import="com.soicaupro.thongkebacnho.domain.SpecialAwardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Lotto về theo đặc biệt</title>
<link rel="shortcut icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAABklBMVEUAAAD////l4dzm493k4Nvm493m493m493m493V2NiLxNrm493a1dLj39rm493b3dq81t7Q19eazN/W0c7Y1NDL297LxMPi4t2Vy9/M297c19Pm493E2d7k493m493J296z1N6Du9K21d7I2t6t0t7m493Tzcvm493NxsVpudlmttdlveBbs9h6u9bg4d1Tstrg4d2XxNam0N4lqeElquErq+AsrOEsrOIvq94vrOAwrOAxrOAxreAyrd8yreAzreA0rd41rt41ruA2ruA4r+A5r+A6rdw9seFAsuFBsuFCsN5CsuBGst9IteNPt+NQuORcvudiut9mwuhowuhzwN96wN2Fzu2LyuSS1O+Wyt+W1e6m2/Gnzt+v3/Ow3/KyytW+2uW/5fXA5fXB2uXG6PbL6vfO6/fPzc3VzszY0c/Z0tDa8Prc1dPf2NXf8vrg2dfh2tji29ji3Nnj9PvtHyTtLTLuLjPvPUHzam30eXz1iIr1+/73lpn3/P74paf5tLb6w8X7/f79/v/+8PD+//////8O3ccoAAAAM3RSTlMAAAEBAgIGDBESFxkhLjAxMjk/R0pMXWZrgICHlpianKKjvMTF0dbd6e/x8vT29/r6+/4dYH0dAAAA10lEQVQY00XIy0rDQBiA0W/+SSY3Go21BPG2EMGi4Cv44j6IRSkFUaO1xqRNJrWTuHDhWR4FkO5HUd825QZQYPLJZTxydfP0UoCHPp1mVwBOJg8LNMd5fg2wac4/6RoJb9UFVHXrZWpsDpGsOIoh7QngxM+NRGigVxrwvC4SYQs0gQNWvYjsqOasjdgOXgexmkwPiR/qyGPx5r6etT0QVbk9wc4K9/NeatyZNcN3vfxorVs+DprWejdpEsbJyMzmWxQQjNM7pYb7crUDBYAfBkO3BkD9zb9ft/1SXawC1lwAAAAASUVORK5CYII=" type="image/x-icon"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="ket qua xo so, du doan ket qua xo so, thong ke vip, dự đoán xổ số, du doan xo so mien bac, thong ke bac nho, thong ke xo so mien bac">
<meta name="description"
	content="Thongkevip.com - Hệ thống cung cấp các chức năng thống kê kết quả xổ số, lotto chính xác, hiệu quả cao">
<link media="all" type="text/css" rel="stylesheet"
	href="css/bootstrap.css">

<link media="all" type="text/css" rel="stylesheet"
	href="css/animate.css">

<link media="all" type="text/css" rel="stylesheet"
	href="css/main.css">

<link media="all" type="text/css" rel="stylesheet"
	href="css/style_4.css">

<link media="all" type="text/css" rel="stylesheet"
	href="css/responsive.css">

<link media="all" type="text/css" rel="stylesheet"
	href="css/css-reset.css">

<link rel="shortcut icon" href="assets/images/favicon.ico">
<script src="js/jquery-1.11.1.js"></script>

<script src="js/bootstrap.min.js"></script>


<%
	String datetime1 = (String) request.getAttribute("datetime1");
        String date_begin = (String) request.getAttribute("date_begin");
        String date_end = (String) request.getAttribute("date_end");
        String miss =  (String) request.getAttribute("miss");
        
        StringBuilder parram = new StringBuilder();
        parram.append("datetime1=").append(datetime1);
        parram.append("&date_begin=").append(date_begin);
        parram.append("&date_end=").append(date_end);
        parram.append("&miss=").append(miss);
        
        String s = request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath() + "/bo3_loto_rs.htm?" 
                + parram.toString();
        System.out.println(s);
        
        String url = request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath() + "/bo3_loto_rs.htm";
        
        request.getServletContext().getRequestDispatcher(url).forward(request, response);

%>

<body>

    <jsp:include page="../thongkebacnho/header_thongkebacnho.jsp"></jsp:include>
        <div id="fb-root"></div>
	<div id="tkv-wrapper" class="fix-clear">
		<!-- Bắt đầu phần thân -->

		<section class="sc-section">
			<div class="tkv-content">
				<h1 class="sc-tt">(Soi cầu PRO, đang tải dữ liệu......)</h1>
			</div>
		</section>


		<section class="sc-section">

			<center>
				<div class="tb-tsc">
					<p>
					<center>
						Do dữ liệu rất nhiều cần được phân tích, các bạn vui lòng chờ trong giây lát!
					</center>
					</p>
					<br /> <br />
	

				</div>
	</div>
	</center>
	</section>
         <>       
	</div>
	<!-- Hết phần thân -->
        <jsp:include page="../include/footer.jsp"></jsp:include>

</body>
</html>