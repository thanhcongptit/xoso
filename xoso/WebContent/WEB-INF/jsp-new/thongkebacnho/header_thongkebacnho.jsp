<%-- 
    Document   : header_thongkebacnho
    Created on : Apr 23, 2016, 11:16:20 AM
    Author     : conglt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script>
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

        ga('create', 'UA-73152547-1', 'auto');
        ga('send', 'pageview');

    </script>
    <body>
        <div id="header" html="header" class="abcde-header">
            <div class="container">
                <div class="top_header">
                    <a class="logo" href="<%=request.getContextPath()%>/"></a>

                </div>
                <div class="menu">
                    <ul>
                        <li class="active"><a href="${pageContext.servletContext.contextPath}/" class="home"><span class="icon"></span></a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/ket-qua-xo-so-mien-bac-xstd.html">Sổ kết quả</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/caulotodacbiet.htm">Soi cầu</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/thongkechuky.htm">Thống kê cơ bản</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/tkbn.htm">Thống kê bạc nhớ</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/du-doan-ket-qua-xo-so.html">Dự đoán xổ số</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/tin-tuc.html">Tin xổ số</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/loto-online.htm">Loto online</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/lien-he.html">Liên hệ</a></li>
                    </ul>
                </div>
            </div>		
        </div>
        <jsp:include page="../thongkebacnho/body-banner-top1.jsp" ></jsp:include>            
    </body>
</html>
