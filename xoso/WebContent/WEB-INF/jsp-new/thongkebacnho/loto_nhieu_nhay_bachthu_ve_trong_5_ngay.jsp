<%@page import="com.soicaupro.thongkebacnho.domain.LotoNhieuNhayBachThuDetailDTO"%>
<%@page import="com.soicaupro.thongkebacnho.domain.LotoNhieuNhieuNhayBachThuDTO"%>
<%@page import="com.soicaupro.thongkebacnho.domain.LotoNhieuNhayDTO"%>
<%@page import="com.soicaupro.thongkebacnho.bus.LotoRoiNhieuNhayBus"%>
<%@page import="com.soicaupro.thongkebacnho.domain.OpenDateDTO"%>
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
<title>Lotto về theo loto nhiều nháy</title>
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
	href="css/style_4.css">

<link media="all" type="text/css" rel="stylesheet"
	href="css/responsive.css">

<link media="all" type="text/css" rel="stylesheet"
	href="css/css-reset.css">

<link rel="shortcut icon" href="assets/images/favicon.ico">
<script src="js/jquery-1.11.1.js"></script>

<script src="js/bootstrap.min.js"></script>
<link media="all" type="text/css" rel="stylesheet"
	href="css/main.css">

<%
	String indexLoto = (String) request.getAttribute("indexLoto");
        String date = (String) request.getAttribute("date");
        String numberDate = (String) request.getAttribute("soNgay");
        String soNhay = (String) request.getAttribute("soNhay");
	List<LotoNhieuNhayBachThuDetailDTO> results = null;
	List<OpenDateDTO> awards = null;

	if (CommonUtil.isEmptyString(date)) {
		date = CommonUtil.convertDateToString1(new Date());
	}

	if (CommonUtil.isEmptyString(numberDate)) {
		numberDate = "30";
	} else {
		int number = 0;
		try {
			number = Integer.parseInt(numberDate);
		} catch (Exception e) {
			numberDate = "30";
		}
		if (number > 30 || number <= 0)
			numberDate = "30";
	}

	if (CommonUtil.isEmptyString(soNhay)) {
		soNhay = "2";
	}

	if (!CommonUtil.isEmptyString(indexLoto)) {
		LotoRoiNhieuNhayBus nhieuNhayBus = new LotoRoiNhieuNhayBus();
		LotoNhieuNhieuNhayBachThuDTO lotoNhieuNhayDTO = nhieuNhayBus.getLotoTheoGiaiBachThuDTO(Integer.parseInt(indexLoto), date,
				Integer.parseInt(soNhay), Integer.parseInt(numberDate));

		awards = lotoNhieuNhayDTO.getOpenDates();
		results = lotoNhieuNhayDTO.getResults();
	}
%>

<body>
        <jsp:include page="../thongkebacnho/header_thongkebacnho.jsp"></jsp:include>

	<div id="tkv-wrapper" class="fix-clear">
		<!-- Bắt đầu phần thân -->

		<section class="sc-section">
			<div class="tkv-content">
                            <h1 class="sc-tt">(Soi cầu PRO, bạch thủ về theo loto về nhiều nháy)</h1>
                         </div>
		</section>
		<section class="sc-section">
			<h2 class="sc-tt2">Loto rơi sau khi về nhiều nháy</h2>
		</section>

		<section class="sc-section">
			<div class="sc-grp-columb input-prepend">
				<form method="POST" action="${pageContext.servletContext.contextPath}/nhieu_nhay_bachthu_rs.htm"
					accept-charset="UTF-8" class="" name="frm" autocomplete="off">
					<input name="_token" type="hidden"
						value="FliiCwtWxloRDGOEX1YgnmIIX9Bp9eWxhQJml2mn"> <label
						for="date" class="">Thống kê đến ngày </label> <input type="text"
						name="date" id="date" value="<%=date%> " class=""> &nbsp
					Trong <input type="text" name="soNgay" id="soNgay"
						value="<%=numberDate%>" style="width: 50px"> &nbsp lần
					&nbsp Loto <select name="indexLoto" class="form-control"
						id="dacBiet">
						<%
							int temp = 45;
							if (!CommonUtil.isEmptyString(indexLoto)) {
								temp = Integer.parseInt(indexLoto);
							}

							for (int i = 0; i < 50; i++) {
						%>
						<option value="<%=i%>" <%if (temp == i) {%> selected=<%}%>><%=Constant.coupleLoto[i][0]%>,<%=Constant.coupleLoto[i][1]%></option>



						<%
							}
						%>

					</select> &nbsp về từ &nbsp <select name="soNhay" class="form-control"
						id="dacBiet">
						<%
							for (int i = 1; i <= 4; i++) {
						%>
						<option value="<%=i%>" <%if (soNhay.equals(String.valueOf(i))) {%>
							selected=<%}%>><%=i%></option>
						<%
							}
						%>
					</select> &nbsp nháy gần đây &nbsp
					<button type="submit" class="btn-primary">Tra kết quả</button>
				</form>
			</div>
		</section>

		<%
			if (!CommonUtil.isEmptyString(indexLoto)) {
				int index = Integer.parseInt(indexLoto);
		%>

		<section class="sc-section">

			<center>
				<div class="tb-tsc">
					<p>
					<center>
						Thống kê trong
						<%=numberDate%>
						lần loto
						<%=Constant.coupleLoto[index][0]%>,<%=Constant.coupleLoto[index][1]%>
						về từ
						<%=soNhay%>
						nháy gần đây, 10 bạch thủ về đều nhất:
					</center>
					</p>
					<br /> <br />

					<table class="sc-table-tsc">
						<colgroup>
							<col width="100px">
							<col width="100px">
							<col width="123px">
							<col width="123px">
							<col width="123px">
							<col width="123px">
							<col width="123px">
						</colgroup>
						<thead>
							<tr>
								<th style="background-color: #FF9900;">Loto</th>
								<th style="background-color: #99FF99;">Số lần về</th>
								<th style="background-color: #ffcc99;">Ngày 1</th>
								<th style="background-color: #FFCC99;">Ngày 2</th>
								<th style="background-color: #FFCC99;">Ngày 3</th>
								<th style="background-color: #ffcc99;">Ngày 4</th>
								<th style="background-color: #FFCC99;">Ngày 5</th>
							</tr>
						</thead>
						<tbody>
							<%for(LotoNhieuNhayBachThuDetailDTO detailDTO: results) { %>
							<tr>
								<td style="background-color: #FFCC66"><b><%=detailDTO.getLoto() %></b></td>
								<td style="background-color: #CCFFCC"><%=detailDTO.getSize() %></td>
								<td><%=detailDTO.getListDay1().size() %></td>
								<td><%=detailDTO.getListDay2().size() %></td>
								<td><%=detailDTO.getListDay3().size() %></td>
								<td><%=detailDTO.getListDay4().size() %></td>
								<td><%=detailDTO.getListDay5().size() %></td>
							</tr>
							
							<%} %>

						</tbody>
					</table>

				</div>
			</center>


			<center>
				<br /> <br />
				<p>
				<center>Bảng thống kê chi tiết về trong 5 ngày</center>
				</p>
				<br />
				<p>
					<%
						if (results != null && results.size() > 0) {
								for (int i = 0; i < results.size(); i++) {
									LotoNhieuNhayBachThuDetailDTO dto = results.get(i);
					%>
					<input type="checkbox" name="loto_show" class="loto_show"
						value="<%=i%>" style="margin: 0" <%if (i < 3) {%> checked <%}%>>
					<%=dto.getLoto()%>
					&nbsp&nbsp&nbsp
					<%
						}
							}
					%>

				</p>
				<br />
				<div class="detail_loto" style="overflow: hidden; width: 1600px; float: left;">
					<center>
						<div class="date_detail" style="width: 300px; float: left">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="120px">
									<col width="120px">
									<col width="60px">
								</colgroup>
								<tbody>
									<tr>
										<td style="background-color: #FF99FF; padding: 16px 0 15px 0">Ngày</td>
										<td style="background-color: #FF9900; padding: 16px 0 15px 0">Loto</td>
										<td style="background-color: #FFCC99; padding: 16px 0 15px 0">Sau
											đó</td>
									</tr>
									<%
										for (OpenDateDTO award : awards) {
									%>
									<tr>
										<td style="background-color: #FFCCFF;"><%=CommonUtil.convertDateToString2(award.getOpenDate())%></td>
										<td style="background-color: #FFCC66;"><b><%=award.getValue()%></b></td>
										<td>-----</td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>

						<div class="loto0_detail" style="width: 400px; float: left;">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color:#FF9933;">Ngày 4</td>
                           				<td style="background-color:#FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>
									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(0).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(0).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(0).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(0).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(0).getListDay5(), 5);
									%>

									<tr>

										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
						<div class="loto1_detail" style="width: 440px; float: left;">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(1).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(1).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(1).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(1).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(1).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
						<div class="loto2_detail" style="width: 440px; float: left;">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(2).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(2).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(2).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(2).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(2).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
						<div class="loto3_detail" style="width: 440px; float: left; display:none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(3).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(3).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(3).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(3).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(3).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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

						<div class="loto4_detail" style="width: 440px; float: left; display:none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(4).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(4).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(4).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(4).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(4).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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

						<div class="loto5_detail" style="width: 440px; float: left; display:none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(5).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(5).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(5).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(5).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(5).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
						
						<div class="loto6_detail" style="width: 440px; float: left; display:none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(6).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(6).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(6).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(6).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(6).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
						
						<div class="loto7_detail" style="width: 440px; float: left; display:none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(7).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(7).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(7).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(7).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(7).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
						
						<div class="loto8_detail" style="width: 440px; float: left; display:none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(8).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(8).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(8).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(8).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(8).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
						
						<div class="loto9_detail" style="width: 440px; float: left;display:none">
							<table class="sc-table-tsc">
								<colgroup>
									<col width="40px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
									<col width="80px">
								</colgroup>
								<thead>
									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<td colspan="5"
											style="background-color: #CCFFCC; font-size: 11px">Số
											lần về cụ thể từng ngày như sau</td>
									</tr>
									<tr>
										<td ROWSPAN="2" style="background-color: #66CCFF;">----</td>
										<td style="background-color: #99FF66;">Ngày 1</td>
										<td style="background-color: #FF9933;">Ngày 2</td>
										<td style="background-color: #FF6699;">Ngày 3</td>
										<td style="background-color: #FF6699;">Ngày 4</td>
										<td style="background-color: #FF6699;">Ngày 5</td>
									</tr>

								</thead>
								<tbody>

									<%
										if (awards != null && awards.size() > 0) {
												for (int i = 0; i < awards.size(); i++) {
													String s1 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(9).getListDay1(), 1);
													String s2 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(9).getListDay2(), 2);
													String s3 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(9).getListDay3(), 3);
													String s4 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(9).getListDay4(), 4);
													String s5 = CommonUtil.getValueLotoSpecial(awards.get(i).getOpenDate(),
															results.get(9).getListDay5(), 5);
									%>

									<tr>
										<td style="background-color: #66CCFF;">----</td>
										<%
											if (!CommonUtil.isEmptyString(s1) || !CommonUtil.isEmptyString(s2)
																|| !CommonUtil.isEmptyString(s3)
																|| !CommonUtil.isEmptyString(s4)
																|| !CommonUtil.isEmptyString(s5)) {
										%>

										<td><%=s1%></td>
										<td><%=s2%></td>
										<td><%=s3%></td>
										<td><%=s4%></td>
										<td><%=s5%></td>
										<%
											} else {
										%>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
										<td style="background-color: #FFCCFF;">&nbsp</td>
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
        <jsp:include page="../include/footer.jsp"></jsp:include>
	<!-- Hết phần thân -->
	<script src="js/jquery-1.11.1.js"></script>

	<script src="js/bootstrap-datepicker.js"></script>
	</script>
	<!-- Jquery Scroll Animated -->

	<script type="text/javascript">
	$(function(){
        $('.loto_show').click(function(){
            //alert('aaa');
            var num = 0;
            var width = 300;
            $('input[name="loto_show"]').each(function() {
                var id = this.value;
                
                if($(this).is(':checked')) {
                    $('.loto'+id+'_detail').css('display','block');
                    num = num + 1;
                }else{
                    $('.loto'+id+'_detail').css('display','none');
                }
               //console.log(this.value);
            });
            width = width + (450 * num);
            $('.detail_loto').css('width', width+'px');
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