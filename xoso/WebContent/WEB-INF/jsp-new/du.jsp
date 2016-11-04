<%-- 
    Document   : index
    Created on : 14-Jan-2016, 08:22:23
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="include/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/thong-ke.css">
</head>

<style>
:before, :after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

html {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.box-list {
	margin: auto;
}

.box-list .item {
	width: 100%;
	float: left;
	background-color: #F2F2F2;
}

.box-list .item .photo {
	max-width: 120px;
	float: left;
	padding: 5px;
}

.box-list .item .photo img {
	max-width: 120px;
	width: 100%;
}

.box-list .item  .text-right {
	margin-left: 140px;
	font-size: 13px;
	color: #222;
	font-weight: 600;
	text-align: right;
	line-height: 18px;
	font-family: Verdana, Geneva, sans-serif;
	margin-right: 10px;
}

.box-list .item  .text-right ul {
	width: 100%;
	float: left;
	margin: 0px;
	padding: 0px;
	margin-top: 10px;
	padding-bottom: 10px;
}

.box-list .item  .text-right ul li {
	width: 100%;
	float: left;
	margin: 0px;
	padding-top: 4px;
	padding-bottom: 4px;
	list-style: none;
}

.button {
	padding: 10px 15px;
	text-align: center;
	background: #f0ad4e;
	border-color: #eea236;
	font-size: 13px;
	text-align: center;
	color: #fff;
	font-family: Verdana, Geneva, sans-serif;
	font-weight: bold;
	border: 0px;
	cursor: pointer;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
}
</style>
<body>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.5&appId=510396495798330";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		
		function checkLaySo(id){
              $("#updateForm input[name=id]").val(id);
              $("#updateForm").submit();
        }
		
	</script>
	
	<%@include file="include/body-header.jsp"%>
	<div id="wapper">
		<div id="main_container">
			<%@include file="include/body-banner-top1.jsp"%>
			<div id="content">
				<%@include file="include/body-loto-online.jsp"%>
				<div id="sidebar-right">
					<%@include file="include/body-list-news.jsp"%>
					<%@include file="include/body-tkbacnho-right.jsp"%>
					<%@include file="include/body-thongkenhanh-right.jsp"%>
					<%@include file="include/body-banner-top5-right.jsp"%>
				</div>
				<div id="sidebar-left">
					<%@include file="include/body-calendar-left.jsp"%>
					<%@include file="include/body-lichmothuong-left.jsp"%>
					<%@include file="include/body-xs3mien-left.jsp"%>
					<%@include file="include/body-thongkecoban-left.jsp"%>
					<%@include file="include/body-banner-top5-left.jsp"%>
				</div>

				<form style="display: none" id="updateForm" method="POST" action="${pageContext.servletContext.contextPath}/layso.htm">
					<input type="hidden" name="id" /> 
						
				</form>
				
				<div id="content-center">
					<div id="content-tructiep">
						<table style="width: 100%">
							<tr>
								<td colspan="2" style="padding: 0px">
									<div class="thong-ke">
										<div class="box-list">
											<div class="item">
												<div class="photo">
													<img
														src="${pageContext.servletContext.contextPath}/resources/card/giacat.jpg">
												</div>
												<div class="text-right">
													<ul>
														<li>Thầy đang gieo quẻ chốt số cầu Miền Bắc</li>
														<li>
															<c:if test="${message1 eq null}">
																<input class="button" type="button"
																value="Lấy số chỉ 3k xu" onclick="checkLaySo(1)" />
															</c:if>	
															
															<c:if test="${message1 ne null}">
																<span style="color: red;">Con nên chơi ${message1}</span>
															</c:if>
														</li>
													</ul>

												</div>
											</div>
											<div class="item">
												<div class="photo">
													<img
														src="${pageContext.servletContext.contextPath}/resources/card/dieuthuyen.jpg">
												</div>
												<div class="text-right">
													<ul>
														<li>Em đang tìm Song Thủ Lô Miền Bắc</li>
														<li>
															<c:if test="${message2 eq null}">
																<input class="button" type="button"
																value="Lấy số chỉ 3k xu" onclick="checkLaySo(2)" />
															</c:if>	
															
															<c:if test="${message2 ne null}">
																<span style="color: red;">Anh nên chơi ${message2}</span>
															</c:if>
														</li>
													</ul>

												</div>
											</div>
										</div>

										<div class="fb-comments" style="width: 100%"
											data-href="http://soicaupro.com/du-doan-ket-qua-xo-so.html"
											data-numposts="5"></div>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<%@include file="include/footer.jsp"%>
		</div>
	</div>
</body>
</html>
