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
													Nạp thẻ</h3>
												<c:if test="${memberLogin eq null}">
                                                        Bạn vui lòng đăng nhập để sử dụng chức năng này!
                                                    </c:if>
												<c:if test="${memberLogin ne null}">
													<div class="row">
														<div class="col-md-6 col-md-offset-3 logindialog">
															<form class="cd-form" action="${pageContext.servletContext.contextPath}/congtien.htm" method="post">
																<table align="center" class="table">
																	<tbody>
																	
																		<tr>
																			<td colspan="3">
																				<table>
																					<tbody>
																						
																						<tr>
																							<td
																								style="padding-left: 5px; padding-top: 5px; width: 80px"
																								align="center"><label for="92"><img
																									src="${pageContext.servletContext.contextPath}/resources/card/10k.png"></label>
																							</td>
																							<td
																								style="padding-left: 5px; padding-top: 5px; width: 80px"
																								align="center"><label for="93"><img
																									src="${pageContext.servletContext.contextPath}/resources/card/20k.png"></label></td>
																							<td
																								style="padding-top: 5px; padding-left: 5px; width: 80px"
																								align="center"><label for="107"><img
																									src="${pageContext.servletContext.contextPath}/resources/card/50k.png"
																									width="80px" height="25"></label></td>
																							<td
																								style="padding-top: 5px; padding-left: 5px; width: 80px"
																								align="center"><label for="121"><img
																									width="80px" height="25"
																									src="${pageContext.servletContext.contextPath}/resources/card/100k.png"></label>
																							</td>
																							<td
																								style="padding-top: 5px; padding-left: 5px; width: 80px"
																								align="center"><label for="120"><img
																									width="80px" height="25"
																									src="${pageContext.servletContext.contextPath}/resources/card/200k.png"></label></td>
																							<td
																								style="padding-top: 5px; padding-left: 5px; width: 80px"
																								align="center"><label for="120"><img
																									width="80px" height="25"
																									src="${pageContext.servletContext.contextPath}/resources/card/500k.png"></label></td>
																						</tr>
																						<tr>
																							<td align="center" style="padding: 10px 0;">
																								
																								10k xu
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								20k xu
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								 50k xu
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								100k xu
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								 200k xu
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								 500k xu
																							</td>
																						</tr>
																					</tbody>
																				</table>
																			</td>
																		</tr>
																	
																		<tr>
																			<td colspan="3">
																				<table>
																					<tbody>
																						
																						<tr>
																							<td
																								style="padding-left: 5px; padding-top: 5px; width: 130px"
																								align="center"><label for="92"><img
																									src="${pageContext.servletContext.contextPath}/resources/card/mobifone.jpg"></label>
																							</td>
																							<td
																								style="padding-left: 5px; padding-top: 5px; width: 130px"
																								align="center"><label for="93"><img
																									src="${pageContext.servletContext.contextPath}/resources/card/vinaphone.jpg"></label></td>
																							<td
																								style="padding-top: 5px; padding-left: 5px; width: 130px"
																								align="center"><label for="107"><img
																									src="${pageContext.servletContext.contextPath}/resources/card/viettel.jpg"
																									width="110" height="35"></label></td>
																							<td
																								style="padding-top: 5px; padding-left: 5px; width: 130px"
																								align="center"><label for="121"><img
																									width="100" height="35"
																									src="${pageContext.servletContext.contextPath}/resources/card/vtc.jpg"></label>
																							</td>
																							<td
																								style="padding-top: 5px; padding-left: 5px; width: 130px"
																								align="center"><label for="120"><img
																									width="100" height="35"
																									src="${pageContext.servletContext.contextPath}/resources/card/gate.jpg"></label></td>
																						</tr>
																						<tr>
																							<td align="center" style="padding: 10px 0;">
																								<input type="radio" name="select_method"
																								checked="true" value="MOBI" id="92">&nbsp;
																								MobiPhone
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								<input type="radio" name="select_method"
																								value="VINA" id="93">&nbsp; VinaPhone
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								<input type="radio" name="select_method"
																								value="VIETEL" id="107">&nbsp; Viettel
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								<input type="radio" id="121" value="VTC"
																								name="select_method">&nbsp; VCOIN
																							</td>
																							<td align="center" style="padding: 10px 0;">
																								<input type="radio" id="120" value="GATE"
																								name="select_method">&nbsp; Gate
																							</td>
																						</tr>
																					</tbody>
																				</table>
																			</td>
																		</tr>
																		
																		<tr>
																			<td align="right" style="padding: 15px">Số Seri
																				:</td>
																			<td colspan="2"><input type="text" required="required"
																				class="form-control" id="txtSoSeri" name="SoSeri"></td>
																		</tr>
																		<tr>
																			<td align="right" style="padding: 15px">Mã số
																				thẻ :</td>
																			<td colspan="2"><input type="text" id="txtSoPin" required="required"
																				class="form-control" name="SoPin"></td>
																		</tr>
																		<tr>
																			<td colspan="3" align="center"
																				style="padding-bottom: 10px; padding-right: 10px">
																				<input type="submit" name="submit" class="btn btn-danger homebtn value="Nạp Thẻ"
																				class="btn btn-primary">
																			</td>
																		</tr>
																	</tbody>
																</table>
															</form>
														</div>
														
													
													</div>
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
