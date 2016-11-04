<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <%@include file="include/header-tk.jsp" %>
        <link href="${pageContext.servletContext.contextPath}/resources/css/datepicker.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.ui.datepicker-fr.js"></script>
        <script>
            $(function() {
                $('#tungay').datepicker({
                    inline: true,
                    showOtherMonths: true,
                    dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7']
                });
                                
            });
        </script>
        <style>
            #content-center{font-size: 14px;}                
        </style>
    </head>
    <body>		
        <%@include file="include/body-header.jsp" %>
        <div id="wapper">
            <div id="main_container">
                <%@include file="include/body-banner-top1.jsp" %>
                <div id="content">
                    <%@include file="include/body-loto-online.jsp" %>
                    <div id="sidebar-right">										
                        <%@include file="include/body-list-news.jsp" %>
                        <%@include file="include/body-tkbacnho-right.jsp" %>
                        <%@include file="include/body-thongkenhanh-right.jsp" %>
                        <%@include file="include/body-banner-top5-right.jsp" %>
                    </div>				
                    <div id="sidebar-left">
                        <%@include file="include/body-lichmothuong-left.jsp" %>
                        <%@include file="include/body-xs3mien-left.jsp" %>
                        <%@include file="include/body-thongkecoban-left.jsp" %>
                        <%@include file="include/body-banner-top5-left.jsp" %>                      
                    </div>					
                    <div id="content-center" style="text-align: center"> 
                        <table width="100%" border="1" style="border-color: #aaa" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><h2 class="h1title" style="text-align: center">THỐNG KÊ BẠC NHỚ</h2></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 25px;text-align:">
                                    <h3 class="sc-tt4">Lotto về theo đặc biệt</h3>
                                    <div style="margin-top: 20px; margin-left: 120px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/lotocapvetrong1ngay.htm">Lotto cặp về trong 1 ngày</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/loto_bachthu_db_ve_trong_3ngay.htm">Bạch thủ về trong 3 ngày</a>
                                    </div>
                                    
                                    <div style="margin-top: 20px; margin-left: 120px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/loto_cap_db_ve_trong_2ngay.htm">Lotto cặp về trong 2 ngày</a>
                                    </div>  
                                    <div style="margin-top: 20px; margin-left: 120px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/loto_cap_db_ve_trong_3ngay.htm">Lotto cặp về trong 3 ngày</a>
                                    </div> 

                                </td>
                            </tr> 
                            
                      
                            <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Lotto về theo đầu câm</h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/dau_cam_loto.htm">Lotto về theo đầu câm</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/dau_cam_chinh.htm">Lotto chính về theo đầu câm</a>
                                    </div>
                              

                                </td>
                            </tr>  
                            
                             <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Lotto về theo đuôi câm</h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/duoi_cam_loto.htm">Lotto về theo đuôi câm</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/duoi_cam_chinh.htm">Lotto chính về theo đuôi câm</a>
                                    </div>
                              

                                </td>
                            </tr>
                            
                             <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Lotto về theo tổng câm</h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/tong_cam_loto.htm">Lotto về theo tổng câm</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/tong_cam_chinh.htm">Lotto chính về theo tổng câm</a>
                                    </div>
                              

                                </td>
                            </tr>
                            
                             <tr>
                                <td style="padding-top: 25px; text-align:  ">
                                    <h3 class="sc-tt4">Lotto về theo giải</h3>
                                    <div style="margin-top: 20px; margin-left: 120px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/theogiai_loto_2_ngay.htm">Lotto cặp về trong 2 ngày</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/theogiai_bachthu_5_ngay.htm">Bạch thủ về trong 5 ngày</a>
                                    </div>
                              
                                     <div style="margin-top: 20px; margin-left: 120px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/theogiai_loto_3_ngay.htm"">Lotto cặp về trong 3 ngày</a>
                                    </div>
                                </td>
                            </tr> 
                            
                            <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Loto về theo loto về nhiều nháy </h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/nhieu_nhay_loto.htm">Lotto cặp về trong 3 ngày</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/nhieu_nhay_bachthu.htm">Bạch thủ về trong 5 ngày</a>
                                    </div>
                                </td>
                            </tr> 
                            
                             <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Loto về theo bộ 2 loto về cùng nhau</h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/bo2_loto.htm">Loto cặp về trong 3 ngày</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/bo2_bachthu.htm">Bạch thủ về trong 5 ngày</a>
                                    </div>
                                </td>
                            </tr>   
                            
                             <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Loto về theo bộ 3 loto về cùng nhau</h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/bo3_loto.htm">Loto cặp về trong 3 ngày</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/bo3_bachthu.htm">Bạch thủ về trong 5 ngày</a>
                                    </div>
                                </td>
                            </tr> 
                            
                             <tr>
                                <td style="padding-top: 25px;text-align: center ">
                                    <h3 class="sc-tt4">Loto về theo bộ 4 loto về cùng nhau</h3>
                                    <div style="margin-top: 20px;">
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/bo4_loto.htm">Loto cặp về trong 3 ngày</a>
                                        <a class="tkbn-link" href="${pageContext.servletContext.contextPath}/bo4_bachthu.htm">Bạch thủ về trong 5 ngày</a>
                                    </div>
                                </td>
                            </tr> 
                        </table>


                    </div>
                </div>
                <%@include file="include/footer.jsp" %>
            </div>
    </body>
</html>
