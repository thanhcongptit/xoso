<%-- 
    Document   : body-header
    Created on : 14-Jan-2016, 08:50:07
    Author     : 24h
--%>
<%@page import="com.dao.AdvertisingSiteDao"%>
<%@page import="com.bean.AdvertisingSite"%>
<%@page import="java.util.List"%>
<%@page import="inet.listener.BannerCache"%>
<%@page import="inet.bean.Banner"%>
<%@page import="inet.login.google.Google"%>
<%@page import="inet.constant.Constants"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.model.MemberDAO"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="com.javapapers.java.social.facebook.FBConnection"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
	$(document).ready(function() {
		$(".enter").keyup(function(e) {
			if (e.keyCode === 13) {
				$("#frmLogin").submit();
			}
		});
	});
</script>
<%
	String actionHeader = RequestUtil.getString(request, "action", "");
	String msgHeader = "";
	if ("login".equals(actionHeader)) {
		String username = RequestUtil.getString(request, "username", "");
		String password = RequestUtil.getString(request, "password", "");
		if (!"".equals(username) && !"".equals(password)) {
			MemberDAO memberDAO = new MemberDAO();
			Member member = memberDAO.login(username, password);
			if (member == null) {
				msgHeader = "Thông tin tài khoản không chính xác!";
			} else if (member.getStatus() == Member.STATUS_BLOCK) {
				msgHeader = "Tài khoản của bạn đã bị khóa!";
			} else {
				session.setAttribute(Constants.LOGIN_SESSION, member);
				response.sendRedirect(request.getContextPath() + "/");
			}
		}
	}

	List<AdvertisingSite> advertisingSites = new AdvertisingSiteDao().getAvertisingSite();
%>


<div id="header" html="header" class="abcde-header">
	<div class="container">
		<div class="top_header">
			<a class="logo" href="<%=request.getContextPath()%>/"></a>
			<div class="banner">
				<%
					if (request.getRequestURI().endsWith("/index.jsp")) {
						Banner banner = BannerCache.findBanner("index", Banner.POSITION_HEADER);
						if (banner != null)
							out.print(banner.getCode());
					} else {
						Banner banner = BannerCache.findBanner("other", Banner.POSITION_HEADER);
						if (banner != null)
							out.print(banner.getCode());
					}
				%>
			</div>
			<div class="login">
				<form method="post" id="frmLogin">
					<table>
						<c:if test="${memberLogin ne null}">
							<tr>
								<td colspan="2" style="color: #117e0c;">Hi <b><a
										style="color: #117e0c; text-decoration: underline"
										href="${pageContext.servletContext.contextPath}/profile.htm">${memberLogin.fullname}</a></b>,
									<b>${memberLogin.xu} xu</b></td>
							</tr>
							<tr>
							<td><a href="${pageContext.servletContext.contextPath}/nap-the.htm"
								class="btn btn-danger funbtn">Nạp tiền</a>
							
							<a href="<%=request.getContextPath()%>/login/logout.htm"
								class="btn btn-success funbtn">Thoát</a></td>
							</tr>
						</c:if>
						<c:if test="${memberLogin eq null}">
							<tr>
								<%
									if ("".equals(msgHeader)) {
								%>
								<td colspan="2" style="color: #117e0c;"><b>Đăng nhập để
										chém gió nào thánh:</b></td>
								<%
									} else {
								%>
								<td colspan="2" style="color: red;"><b><%=msgHeader%></b></td>
								<%
									}
								%>
							</tr>
							<tr>
								<td style="width: 75px;"><b>Tài khoản</b></td>
								<td><input name="username" class="enter1" /> <input
									type="hidden" name="action" value="login" /></td>
							</tr>
							<tr>
								<td><b>Mật khẩu</b></td>
								<td><input name="password" class="enter" type="password" /></td>
							</tr>
							<%
								FBConnection fbConnection = new FBConnection();
									String redirectUrl = Constants.GOOGLE_REDIRECT_URL;
									Google google = new Google(redirectUrl);
									String loginGoogle = google.getLoginUrl();
									//out.print("loginGoogle="+loginGoogle);
							%>
							<tr>
								<td colspan="2"><b> Đăng nhập <a
										href="<%=fbConnection.getFBAuthUrl()%>"> Facebook </a> - <a
										href="<%=loginGoogle%>"> G+ </a>
								</b></td>
							</tr>
							<tr>
								<td colspan="2"><b>Nếu chưa có tài khoản đăng ký <a
										href="<%=request.getContextPath()%>/dang-ky.htm">Tại Đây</a></b></td>
							</tr>
						</c:if>
					</table>
				</form>
			</div>
		</div>
		<div class="menu">
			<ul>
				<li class="active"><a
					href="${pageContext.servletContext.contextPath}/" class="home"><span
						class="icon"></span></a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/ket-qua-xo-so-mien-bac-xstd.html">Sổ
						kết quả</a></li>

				<%
					for (AdvertisingSite advertisingSite : advertisingSites) {
						if (advertisingSite.getIndex() == 1) {
				%>

				<li><span class="link"><a
						href="<%=advertisingSite.getLink()%>"><%=advertisingSite.getTitle()%></a></span></li>
				<%
					}
					}
				%>

				<li><a
					href="${pageContext.servletContext.contextPath}/caulotodacbiet.htm">Soi
						cầu</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/thongkechuky.htm">Thống
						kê cơ bản</a></li>
				<%
					for (AdvertisingSite advertisingSite : advertisingSites) {
						if (advertisingSite.getIndex() == 2) {
				%>

				<li><span class="link"><a
						href="<%=advertisingSite.getLink()%>"><%=advertisingSite.getTitle()%></a></span></li>
				<%
					}
					}
				%>

				<li><a
					href="${pageContext.servletContext.contextPath}/tkbn.htm">Thống
						kê bạc nhớ</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/du-doan-ket-qua-xo-so.html">Dự
						đoán xổ số</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/tin-tuc.html">Tin
						xổ số</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/loto-online.htm">Loto
						online</a></li>
				<li><a
					href="${pageContext.servletContext.contextPath}/lien-he.html">Liên
						hệ</a></li>
				<!-- conglt add link quang cao-->
				<%
					for (AdvertisingSite advertisingSite : advertisingSites) {
						if (advertisingSite.getIndex() == 3) {
				%>

				<li><span class="link"><a
						href="<%=advertisingSite.getLink()%>"><%=advertisingSite.getTitle()%></a></span></li>
				<%
					}
					}
				%>
			</ul>
		</div>
	</div>
</div>
<div id="fb-root"></div>
<script>
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.5&appId=1493643337628401";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>