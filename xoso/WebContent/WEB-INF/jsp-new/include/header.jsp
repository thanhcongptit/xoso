<%-- 
    Document   : header
    Created on : 14-Jan-2016, 08:45:36
    Author     : 24h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.8.2.js" ></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-1.9.0.custom.min.js" ></script>
<!--<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/scrollbar.js" ></script>-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-tabs-rotate.js" ></script>
<link href="${pageContext.servletContext.contextPath}/resources/glDatePicker-2.0/styles/glDatePicker.default.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath}/resources/glDatePicker-2.0/glDatePicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/xsthantai.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/friendly_url.js"></script>
<link rel="shortcut icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAABklBMVEUAAAD////l4dzm493k4Nvm493m493m493m493V2NiLxNrm493a1dLj39rm493b3dq81t7Q19eazN/W0c7Y1NDL297LxMPi4t2Vy9/M297c19Pm493E2d7k493m493J296z1N6Du9K21d7I2t6t0t7m493Tzcvm493NxsVpudlmttdlveBbs9h6u9bg4d1Tstrg4d2XxNam0N4lqeElquErq+AsrOEsrOIvq94vrOAwrOAxrOAxreAyrd8yreAzreA0rd41rt41ruA2ruA4r+A5r+A6rdw9seFAsuFBsuFCsN5CsuBGst9IteNPt+NQuORcvudiut9mwuhowuhzwN96wN2Fzu2LyuSS1O+Wyt+W1e6m2/Gnzt+v3/Ow3/KyytW+2uW/5fXA5fXB2uXG6PbL6vfO6/fPzc3VzszY0c/Z0tDa8Prc1dPf2NXf8vrg2dfh2tji29ji3Nnj9PvtHyTtLTLuLjPvPUHzam30eXz1iIr1+/73lpn3/P74paf5tLb6w8X7/f79/v/+8PD+//////8O3ccoAAAAM3RSTlMAAAEBAgIGDBESFxkhLjAxMjk/R0pMXWZrgICHlpianKKjvMTF0dbd6e/x8vT29/r6+/4dYH0dAAAA10lEQVQY00XIy0rDQBiA0W/+SSY3Go21BPG2EMGi4Cv44j6IRSkFUaO1xqRNJrWTuHDhWR4FkO5HUd825QZQYPLJZTxydfP0UoCHPp1mVwBOJg8LNMd5fg2wac4/6RoJb9UFVHXrZWpsDpGsOIoh7QngxM+NRGigVxrwvC4SYQs0gQNWvYjsqOasjdgOXgexmkwPiR/qyGPx5r6etT0QVbk9wc4K9/NeatyZNcN3vfxorVs+DprWejdpEsbJyMzmWxQQjNM7pYb7crUDBYAfBkO3BkD9zb9ft/1SXawC1lwAAAAASUVORK5CYII=" type="image/x-icon"/>
<meta name="keywords" content="ket qua xo so,kqxs,ketquaxoso,soi cau,thong ke vip,truc tiep ket qua xo so,du doan xo so,loto ve theo dac biet,loto ve theo giai,soi lo de,soi cau lo de mien bac,soi cau mien bac,soi cau mien nam,soi cau mien trung,dien dan soi cau,dien dan chem gio,soi cau mb, soi cau xs,soi cau lo de mien phi,soi cau lo de hang ngay"/>
<meta name="description" content="Trực tiếp kết quả xổ số 3 miền bắc,trung,nam nhanh nhất.Phân tích kqxs,thống kê các cặp xo so may mắn trong ngày "/>

<!-- bo sung quang cao conglt-->
<!--<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-7048567027610873",
    enable_page_level_ads: true
  });
</script>-->
<!-- end bo sung quang cao conglt-->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-73152547-1', 'auto');
  ga('send', 'pageview');

</script>
<script type="text/javascript">    
    $(document).ready(function() {
        $("#featured").tabs({fx: {opacity: "toggle"}}).tabs("rotate", 5000, true);
    });
    $(window).load(function() {
        var ddmmyyyy = '${ddmmyyyy}';
        var dd = ddmmyyyy.substring(0, 2);
        var mm = parseInt(ddmmyyyy.substring(3, 5)) - 1;
        var yyyy = ddmmyyyy.substring(6, 10);
        $('#mydate').glDatePicker({
            showAlways: true,
            selectedDate: new Date(yyyy, mm, dd),
            onClick: function(target, cell, date, data) {
                var string = '';
                if (eval(date.getDate()) < 10) {
                    string = "0" + date.getDate() + "-";
                }
                else {
                    string = date.getDate() + "-";
                }
                if (eval(date.getMonth() + 1) < 10) {
                    string = string + "0" + (date.getMonth() + 1) + "-";
                }
                else {
                    string = string + (date.getMonth() + 1) + "-";
                }
                string = string + date.getFullYear();
                var url = document.URL;
                if (url.indexOf("/tin-tuc/") > 0 || url.indexOf("-chu-nhat") > 0 || url.indexOf("-thu-") > 0
                        || url.indexOf("xo-so-truc-tiep") > 0 || url.indexOf("/tin-tuc") > 0) {
                    url = "/kqxs-ket-qua-xo-so-ngay-" + string + ".html";
                } else {
                    if (url.indexOf(".html") > 0) {
                        url = url.replace(".html", "");
                        if (url.indexOf("-ngay-") > 0) {
                            url = url.substring(0, url.length - 10);
                            url = url + string + ".html";
                        } else {
                            url = url + "-ngay-" + string + ".html";
                        }
                    } else {
                        url = url + "kqxs-ket-qua-xo-so-ngay-" + string + ".html";
                    }
                }
                if (eval(yyyy) === eval(date.getFullYear())) {
                    if (eval(mm) === eval(date.getMonth())) {
                        if (eval(dd) >= eval(date.getDate())) {
                            window.location.href = url;
                        }
                    } else if (eval(mm) > eval(date.getMonth())) {
                        window.location.href = url;
                    }
                }
            }
        });
    });
</script>

<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1073478392724488',
      xfbml      : true,
      version    : 'v2.6'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>

<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/css2/jquery-ui.css">