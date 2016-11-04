<%-- 
    Document   : body-footer
    Created on : 14-Jan-2016, 08:57:25
    Author     : 24h
--%>

<%@page import="com.dao.AdvertisingSiteDao"%>
<%@page import="com.bean.AdvertisingSite"%>
<%@page import="java.util.List"%>
<%@page import="inet.listener.SystemConfigCache"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="footer">
	<div class="copyright">
		<p><%=SystemConfigCache.findConfig(SystemConfigCache.FOOTER_CONFIG)%></p>
	</div>
	<%
		List<AdvertisingSite> advertisingSites1 = new AdvertisingSiteDao().getAvertisingSite();
	%>
	<div class="box_tag">
		<strong>Tags:</strong>
		<ul class="tags">
			
			<%for(AdvertisingSite advertisingSite : advertisingSites1) {
                	if(advertisingSite.getIndex() == 0) {
                %>
			<li><a href="<%=advertisingSite.getLink()%>"
				title="<%=advertisingSite.getTitle()%>" target="_blank"><%=advertisingSite.getTitle()%></a></li>
			<% }}%>	
			<div class="clearfix"></div>
			
		</ul>
	</div>
</div>

<!-- <div class="banner-bt">
				<img alt="Soi cau pro" src="https://giovangchotso.com/public/ads_upload/images/sodep102_com.gif" class="img-responsive">
</div> -->

<script type="text/javascript">
	$(window)
			.load(
					function() {
						var ddmmyyyy = '${ddmmyyyy}';
						var dd = ddmmyyyy.substring(0, 2);
						var mm = parseInt(ddmmyyyy.substring(3, 5)) - 1;
						var yyyy = ddmmyyyy.substring(6, 10);
						$('#mydate')
								.glDatePicker(
										{
											showAlways : true,
											selectedDate : new Date(yyyy, mm,
													dd),
											onClick : function(target, cell,
													date, data) {
												var string = '';
												if (eval(date.getDate()) < 10) {
													string = "0"
															+ date.getDate()
															+ "-";
												} else {
													string = date.getDate()
															+ "-";
												}

												if (eval(date.getMonth() + 1) < 10) {
													string = string
															+ "0"
															+ (date.getMonth() + 1)
															+ "-";
												} else {
													string = string
															+ (date.getMonth() + 1)
															+ "-";
												}

												string = string
														+ date.getFullYear();

												var url = document.URL;
												if (url.indexOf("/tin-tuc/") > 0
														|| url
																.indexOf("-chu-nhat") > 0
														|| url.indexOf("-thu-") > 0
														|| url
																.indexOf("xo-so-truc-tiep") > 0
														|| url
																.indexOf("/tin-tuc") > 0) {
													url = "/kqxs-ket-qua-xo-so-ngay-"
															+ string + ".html";
												} else {
													if (url.indexOf(".html") > 0) {
														url = url.replace(
																".html", "");
														if (url
																.indexOf("-ngay-") > 0) {
															url = url
																	.substring(
																			0,
																			url.length - 10);
															url = url + string
																	+ ".html";
														} else {
															url = url
																	+ "-ngay-"
																	+ string
																	+ ".html";
														}
													} else {
														url = url
																+ "kqxs-ket-qua-xo-so-ngay-"
																+ string
																+ ".html";
													}
												}
												if (eval(yyyy) === eval(date
														.getFullYear())) {
													if (eval(mm) === eval(date
															.getMonth())) {
														if (eval(dd) >= eval(date
																.getDate())) {
															window.location.href = url;
														}
													} else if (eval(mm) > eval(date
															.getMonth())) {
														window.location.href = url;
													}
												}

											}
										});
					});
</script>
