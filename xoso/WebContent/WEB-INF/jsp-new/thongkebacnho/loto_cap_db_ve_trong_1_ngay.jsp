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
<meta name="description" content="soicaupro.com – thống kê xổ số, thống kê bạc nhớ, phân tích xổ số, loto chính xác">
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
	String special = (String) request.getAttribute("dacBiet");
	String date = (String) request.getAttribute("date");
	String numberDate = (String)request.getAttribute("soNgay");
	String typeSub =  (String) request.getAttribute("type_sub");
        
        if (CommonUtil.isEmptyString(date)) {
		date = CommonUtil.convertDateToString1(new Date());
	}

	if (CommonUtil.isEmptyString(numberDate)) {
		numberDate = "45";
	} else {
		int number = 0;
		try {
			number = Integer.parseInt(numberDate);
		} catch (Exception e) {
			numberDate = "50";
		}
		
		if (number > 50 || number <= 0) {
			numberDate = "50";
		}
	}
        
	List<ScreenOneDayDTO> results =  (List<ScreenOneDayDTO>) request.getAttribute("results");
	List<SpecialAwardDTO> awards = (List<SpecialAwardDTO> ) request.getAttribute("awards");

%>

<body>

    <jsp:include page="../thongkebacnho/header_thongkebacnho.jsp"></jsp:include>
        <div id="fb-root"></div>
	<div id="tkv-wrapper" class="fix-clear">
		<!-- Bắt đầu phần thân -->

		<section class="sc-section">
			<div class="tkv-content">
				<h1 class="sc-tt">(Soi cầu PRO, Lotto cặp về trong 1 ngày theo đặc biệt)</h1>
			</div>
		</section>

		<section class="sc-section">
			<div class="sc-grp-columb input-prepend">
				<form method="POST" action="${pageContext.servletContext.contextPath}/loto_cap_db_ve_trong_1ngay.htm"
					accept-charset="UTF-8" class="" name="frm" autocomplete="off">
					<input name="_token" type="hidden"
						value="FliiCwtWxloRDGOEX1YgnmIIX9Bp9eWxhQJml2mn"> <label
						for="date" class="">Thống kê đến ngày </label> <input type="text"
						name="date" id="date" value="<%=date%>" class=""> &nbsp
					Trong <input type="text" name="soNgay" id="soNgay"
						value="<%=numberDate%>" style="width: 50px"> &nbsp lần đặc
					biệt về <select name="dacBiet" class="form-control" id="dacBiet">
						<%
							int temp = 85;
							if (!CommonUtil.isEmptyString(special)) {
								temp = Integer.parseInt(special);
							}

							for (int i = 0; i < 100; i++) {
								if (i < 10) {
						%>
						<option value="0<%=i%>" <%if (temp == i) {%> selected=<%}%>>0<%=i%></option>



						<%
							} else {
						%>
						<option value="<%=i%>" <%if (temp == i) {%> selected=<%}%>><%=i%></option>
						<%
							}
						%>
						<%
							}
						%>

					</select> &nbsp gần đây &nbsp <select name="type_sub" class="form-control"
						id="type_sub">
						<%
							int temp1 = 1;
							if (!CommonUtil.isEmptyString(typeSub)) {
								temp1 = Integer.parseInt(typeSub);
							}

							for (int i = 1; i <= 3; i++) {
						%>
						<option value="<%=i%>" <%if (temp1 == i) {%> selected=<%}%>>Ngày
							thứ
							<%=i%></option>
						<%
							}
						%>

					</select> &nbsp

					<button type="submit" class="btn-primary">Tra kết quả</button>
				</form>
			</div>
		</section>

		<%
			if (!CommonUtil.isEmptyString(special)) {
		%>

		<section class="sc-section">

			<center>
				<div class="tb-tsc">
					<p>
					<center>
						Thống kê từ 01/01/2005 đến nay trong
						<%=numberDate%>
						lần ĐB về
						<%=special%>
						gần đây, 10 cặp loto về nhiều nhất trong Ngày thứ
						<%=typeSub%>
						sau đó:
					</center>
					</p>
					<br /> <br />

					<table class="sc-table-tsc">
						<colgroup>
							<col width="140px">
							<col width="140px">
							<col width="140px">
							<col width="140px">
							<col width="140px">
						</colgroup>
						<thead>
							<tr>
								<th style="background-color: #FF9900;">Loto</th>
								<th style="background-color: #99FF99;">Số lần về</th>
								<th style="background-color: #ffcc99;">Ngày 1</th>
								<th style="background-color: #FFCC99;">Ngày 2</th>
								<th style="background-color: #FFCC99;">Ngày 3</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (results != null && results.size() > 0) {
										for (ScreenOneDayDTO screenOneDayDTO : results) {
							%>
							<tr>
								<td style="background-color: #FFCC66"><b><%=screenOneDayDTO.getCouple()[0]%>,
										<%=screenOneDayDTO.getCouple()[1]%></b></td>
								<td style="background-color: #CCFFCC"><%=screenOneDayDTO.getSize()%></td>
								<%
									if (typeSub.equalsIgnoreCase("1")) {
								%>
								<td><%=screenOneDayDTO.getSize()%></td>
								<%
									} else {
								%>
								<td></td>
								<%
									}
								%>

								<%
									if (typeSub.equalsIgnoreCase("2")) {
								%>
								<td><%=screenOneDayDTO.getSize()%></td>
								<%
									} else {
								%>
								<td></td>
								<%
									}
								%>

								<%
									if (typeSub.equalsIgnoreCase("3")) {
								%>
								<td><%=screenOneDayDTO.getSize()%></td>
								<%
									} else {
								%>
								<td></td>
								<%
									}
								%>
							</tr>
							<%
								}
									}
							%>
						</tbody>
					</table>

				</div>
			</center>


			<center>
				<br /> <br />
				<p>
				<center>
					Bảng thống kê chi tiết về trong ngày thứ
					<%=typeSub%>
				</center>
				</p>
				<br />
				<p>
					<%
						if (results != null && results.size() > 0) {
								for (int i = 0; i < results.size(); i++) {
									ScreenOneDayDTO dto = results.get(i);
					%>
					<input type="checkbox" name="loto_show" class="loto_show"
						value="<%=i%>" style="margin: 0" <%if (i < 3) {%> checked <%}%>>
					<%=dto.getCouple()[0]%>,
					<%=dto.getCouple()[1]%>
					&nbsp&nbsp&nbsp
					<%
						}
							}
					%>

				</p>
				<br />
				<div class="detail_loto" style="overflow: hidden; width: 1060px">
					<center>
						<div class="date_detail" style="width: 260px; float: left">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="120px">
									<col width="80px">
									<col width="60px">
								</colgroup>
								<tbody>
									<tr>
										<td style="background-color: #FF99FF; padding: 16px 0 15px 0">Ngày</td>
										<td style="background-color: #FF9900; padding: 16px 0 15px 0">Đặc
											biệt</td>
										<td style="background-color: #FFCC99; padding: 16px 0 15px 0">Sau
											đó</td>
									</tr>
									<%
										for (SpecialAwardDTO award : awards) {
									%>
									<tr>
										<td style="background-color: #FFCCFF;"><%=CommonUtil.convertDateToString2(award.getOpenDate())%></td>
										<td style="background-color: #FFCC66;"><b><%=award.getSpecialAward()%></b></td>
										<td>-----</td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>

						<div class="loto0_detail" style="width: 240px; float: left;">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(0).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(0).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(0).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>
								</tbody>
							</table>
						</div>
						<div class="loto1_detail" style="width: 280px; float: left;">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(1).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(1).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(1).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>
								</tbody>
							</table>
						</div>
						<div class="loto2_detail" style="width: 280px; float: left;">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(2).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(2).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(2).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>
								</tbody>
							</table>
						</div>
						<div class="loto3_detail"
							style="width: 280px; float: left; display: none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(3).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(3).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(3).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>

								</tbody>
							</table>
						</div>

						<div class="loto4_detail"
							style="width: 280px; float: left; display: none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(4).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(4).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(4).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>

								</tbody>
							</table>
						</div>

						<div class="loto5_detail"
							style="width: 280px; float: left; display: none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(5).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(5).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(5).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>

								</tbody>
							</table>
						</div>
						<div class="loto6_detail"
							style="width: 280px; float: left; display: none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(6).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(6).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(6).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>

								</tbody>
							</table>
						</div>
						<div class="loto7_detail"
							style="width: 280px; float: left; display: none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(7).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(7).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(7).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>

								</tbody>
							</table>
						</div>
						<div class="loto8_detail"
							style="width: 280px; float: left; display: none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {

												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(8).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(8).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(8).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>

								</tbody>
							</table>
						</div>
						<div class="loto9_detail"
							style="width: 280px; float: left; display: none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="3"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
									%>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (typeSub.equalsIgnoreCase("1")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(9).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("2")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(9).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

										<%
											if (typeSub.equalsIgnoreCase("3")) {
										%>
										<td><%=CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
									results.get(9).getOneDayDTOs(), Integer.parseInt(typeSub))%></td>
										<%
											} else {
										%>
										<td>&nbsp</td>
										<%
											}
										%>

									</tr>

									<%
										}
											}
									%>
								</tbody>
							</table>
						</div>
					</center>
				</div>
	</div>
	</center>
	</section>
	<%
		}
	%>

	</div>
	<!-- Hết phần thân -->
        <jsp:include page="../include/footer.jsp"></jsp:include>
	<script src="js/jquery-1.11.1.js"></script>

	<script src="js/bootstrap-datepicker.js"></script>
	</script>
	<!-- Jquery Scroll Animated -->

	<script type="text/javascript">
		$(function() {
			$('.loto_show').click(function() {
				//alert('aaa');
				var num = 0;
				var width = 260;
				$('input[name="loto_show"]').each(function() {
					var id = this.value;

					if ($(this).is(':checked')) {
						$('.loto' + id + '_detail').css('display', 'block');
						num = num + 1;
					} else {
						$('.loto' + id + '_detail').css('display', 'none');
					}
					//console.log(this.value);
				});
				width = width + (280 * num);
				$('.detail_loto').css('width', width + 'px');
			})

		})
	</script>
	<script type="text/javascript">
		$(function() {
			$('#date').datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				todayHighlight : true,
				language : 'vi'
			});

		})
	</script>

	<link media="all" type="text/css" rel="stylesheet"
		href="css/bootstrap-datepicker3.standalone.css">

	</div>

</body>
</html>