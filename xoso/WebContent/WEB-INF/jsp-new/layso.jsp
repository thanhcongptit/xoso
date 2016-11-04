<%-- 
    Document   : index
    Created on : 14-Jan-2016, 08:22:23
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="include/header-tk.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/thong-ke.css">
<link
	href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
<script>
	$(function() {
		$('#ngayghi').datepicker({
			inline : true,
			showOtherMonths : true,
			dayNamesMin : [ 'CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7' ]
		});
	});
</script>

<style type="text/css">

.box-list {
	margin: auto;
}

.item {
	width: 100%;
	float: left;
	background-color: #F2F2F2;
}

 .item .photo {
	max-width: 120px;
	float: left;
	padding: 5px;
}

.item .photo img {
	max-width: 120px;
	width: 100%;
}

 .item  .text-right {
	margin-left: 140px;
	font-size: 13px;
	color: #222;
	font-weight: 600;
	text-align: right;
	line-height: 18px;
	font-family: Verdana, Geneva, sans-serif;
	margin-right: 10px;
}

 .item  .text-right ul {
	width: 100%;
	float: left;
	margin: 0px;
	padding: 0px;
	margin-top: 10px;
	padding-bottom: 10px;
}

.item .text-right ul li {
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
</head>
<body>
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
					<%@include file="include/body-lichmothuong-left.jsp"%>
					<%@include file="include/body-xs3mien-left.jsp"%>
					<%@include file="include/body-thongkecoban-left.jsp"%>
					<%@include file="include/body-banner-top5-left.jsp"%>
				</div>
				<div id="content-center">
					<div id="content-tructiep">
						<table style="width: 100%">
							<tr>
								<td colspan="2">
									<div class="thong-ke">
										<div class="boxlist">
											<div id="kqxsmb" class="cat_news">
												<h3
													style="border-bottom: 1px solid #aeaeae; margin-bottom: 20px">
													Dự đoán xổ số</h3>
												<c:if test="${memberLogin eq null}">
                                                        Bạn vui lòng đăng nhập để sử dụng chức năng này!
                                                    </c:if>
												<c:if test="${memberLogin ne null}">
													
													<c:if test="${not isDaySo}">
														 Chưa có dữ liệu
													</c:if>
												
													<c:if test="${isDaySo}">
															
															<c:if test="${not isXu}">
														 		Bạn không đủ Xu. <a
										style="color: #117e0c; text-decoration: underline"
										href="${pageContext.servletContext.contextPath}/nap-the.htm">Nạp thẻ đi</a>
															</c:if>
													        
													        <c:if test="${isXu}">
														 		
															    <c:if test="${vitri == 1}"> 
																	<div class="item">
																		<div class="photo">
																			<img
																				src="${pageContext.servletContext.contextPath}/resources/card/giacat.jpg">
																		</div>
																		<div class="text-right">
																			<ul>
																				<li>Thầy đang chốt số cầu Miền Bắc hôm nay là: <span style="color: red;">${dayso}</span></li>
																				
																			</ul>
				
																		</div>
																	</div>
																</c:if>
																
																<c:if test="${vitri == 2}">
																	<div class="item">
																		<div class="photo">
																			<img
																				src="${pageContext.servletContext.contextPath}/resources/card/dieuthuyen.jpg">
																		</div>
																		<div class="text-right">
																			<ul>
																				<li>Em chốt Song Thủ Lô Miền Bắc hôm nay là: <span style="color: red;"> ${dayso}</span></li>
																				
																			</ul>
				
																		</div>
																	</div>
																</c:if>
															 </c:if>
													</c:if>
													<div style="clear: both"></div>
												</c:if>
											</div>
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
