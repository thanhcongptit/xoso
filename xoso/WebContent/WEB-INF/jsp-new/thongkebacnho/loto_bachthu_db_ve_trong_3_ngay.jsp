<%@page import="com.soicaupro.thongkebacnho.bus.LotoTheoDacBietBus"%>
<%@page import="com.soicaupro.thongkebacnho.bus.ScreenBachthuThreeDayBus"%>
<%@page import="com.soicaupro.thongkebacnho.domain.ScreenBachthuSpecialDTO"%>
<%@page import="com.soicaupro.thongkebacnho.bus.ScreenThreeDayBus"%>
<%@page import="com.soicaupro.thongkebacnho.dao.CoupleLotoADayDao"%>
<%@page import="java.util.*"%>
<%@page import="com.soicaupro.thongkebacnho.domain.LotoDacBietBachThuDTO"%>
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
        <title>
            Lotto về theo đặc biệt
        </title>
        <link rel="shortcut icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAABklBMVEUAAAD////l4dzm493k4Nvm493m493m493m493V2NiLxNrm493a1dLj39rm493b3dq81t7Q19eazN/W0c7Y1NDL297LxMPi4t2Vy9/M297c19Pm493E2d7k493m493J296z1N6Du9K21d7I2t6t0t7m493Tzcvm493NxsVpudlmttdlveBbs9h6u9bg4d1Tstrg4d2XxNam0N4lqeElquErq+AsrOEsrOIvq94vrOAwrOAxrOAxreAyrd8yreAzreA0rd41rt41ruA2ruA4r+A5r+A6rdw9seFAsuFBsuFCsN5CsuBGst9IteNPt+NQuORcvudiut9mwuhowuhzwN96wN2Fzu2LyuSS1O+Wyt+W1e6m2/Gnzt+v3/Ow3/KyytW+2uW/5fXA5fXB2uXG6PbL6vfO6/fPzc3VzszY0c/Z0tDa8Prc1dPf2NXf8vrg2dfh2tji29ji3Nnj9PvtHyTtLTLuLjPvPUHzam30eXz1iIr1+/73lpn3/P74paf5tLb6w8X7/f79/v/+8PD+//////8O3ccoAAAAM3RSTlMAAAEBAgIGDBESFxkhLjAxMjk/R0pMXWZrgICHlpianKKjvMTF0dbd6e/x8vT29/r6+/4dYH0dAAAA10lEQVQY00XIy0rDQBiA0W/+SSY3Go21BPG2EMGi4Cv44j6IRSkFUaO1xqRNJrWTuHDhWR4FkO5HUd825QZQYPLJZTxydfP0UoCHPp1mVwBOJg8LNMd5fg2wac4/6RoJb9UFVHXrZWpsDpGsOIoh7QngxM+NRGigVxrwvC4SYQs0gQNWvYjsqOasjdgOXgexmkwPiR/qyGPx5r6etT0QVbk9wc4K9/NeatyZNcN3vfxorVs+DprWejdpEsbJyMzmWxQQjNM7pYb7crUDBYAfBkO3BkD9zb9ft/1SXawC1lwAAAAASUVORK5CYII=" type="image/x-icon"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="ket qua xo so, du doan ket qua xo so, thong ke vip, dự đoán xổ số, du doan xo so mien bac, thong ke bac nho, thong ke xo so mien bac">
        <meta name="description" content="soicaupro.com – thống kê xổ số, thống kê bạc nhớ, phân tích xổ số, loto chính xác">
        <link media="all" type="text/css" rel="stylesheet" href="css/bootstrap.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/animate.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/style_4.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/responsive.css">

        <link media="all" type="text/css" rel="stylesheet" href="css/css-reset.css">

        <link rel="shortcut icon" href="assets/images/favicon.ico">
        <script src="js/jquery-1.11.1.js"></script>

        <script src="js/bootstrap.min.js"></script>
        <link media="all" type="text/css" rel="stylesheet"
	href="css/main.css">

        <%
            String special = (String) request.getAttribute("dacBiet");
            String date = (String) request.getAttribute("date");
            String numberDate = (String) request.getAttribute("soNgay");
            List<ScreenBachthuSpecialDTO> results = (List<ScreenBachthuSpecialDTO>) request.getAttribute("results");
            List<SpecialAwardDTO> awards = (List<SpecialAwardDTO>) request.getAttribute("awards");

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

        %>

    <body>
        <jsp:include page="../thongkebacnho/header_thongkebacnho.jsp"></jsp:include>
      
            
            <section class="sc-section">
                <div class="tkv-content">
                    <h1 class="sc-tt">(Soi cầu PRO, Bạch thủ về trong 3 ngày theo đặc biệt)</h1>
                </div>
            </section>

            <section class="sc-section"> 
                <div class="sc-grp-columb input-prepend">
                    <form method="POST" action="${pageContext.servletContext.contextPath}/loto_bachthu_db_rs_ve_trong_3ngay.htm" accept-charset="UTF-8" class="" name="frm" autocomplete="off"><input name="_token" type="hidden" value="FliiCwtWxloRDGOEX1YgnmIIX9Bp9eWxhQJml2mn">
                        <label for="date" class="">Thống kê đến ngày </label>
                        <input type="text" name="date" id="date" value="<%=date%> "class="">
                        &nbsp Trong
                        <input type="text" name="soNgay" id="soNgay" value="<%=numberDate%>" style="width:50px">
                        &nbsp lần đặc biệt về 
                        <select name="dacBiet" class="form-control" id="dacBiet">
                            <%
                                int temp = 85;
                                if (!CommonUtil.isEmptyString(special)) {
                                    temp = Integer.parseInt(special);
                                }

                                for (int i = 0; i < 100; i++) {
                                    if (i < 10) {

                            %>
                            <option value="0<%=i%>" <%if (temp == i) {%> selected= <%}%> >0<%=i%></option>



                            <%
                            } else {
                            %>      
                            <option value="<%=i%>" <%if (temp == i) {%> selected= <%}%> ><%=i%></option>
                            <%
                                }
                            %>
                            <%
                                }
                            %>

                        </select>
                        &nbsp gần đây &nbsp

                        <button type="submit" class="btn-primary">Tra kết quả</button>
                    </form>
                </div>           
            </section>

            <%if (!CommonUtil.isEmptyString(special)) {%>

            <section class="sc-section">

                <center>
                    <div class="tb-tsc">
                        <p><center>Thống kê từ 01/01/2005 đến nay trong <%=numberDate%> lần ĐB về <%=special%> gần đây, 10 loto bạch thủ về nhiều nhất trong 3 ngày sau đó:</center></p>
                        <br />
                        <br />

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
                                    <th style="background-color:#FF9900;">Loto</th>
                                    <th style="background-color:#99FF99;">Số lần về</th>
                                    <th style="background-color:#ffcc99;">
                                        Ngày 1
                                    </th>
                                    <th style="background-color:#FFCC99;">Ngày 2</th>
                                    <th style="background-color:#FFCC99;">Ngày 3</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (results != null && results.size() > 0) {
                                        for (ScreenBachthuSpecialDTO screenOneDayDTO : results) {%>
                                <tr>
                                    <td style="background-color:#FFCC66"><b><%= screenOneDayDTO.getLoto() %></b></td>
                                    <td style="background-color:#CCFFCC"><%= screenOneDayDTO.getSize()%></td>
                                    <td> <%= screenOneDayDTO.getListDay1().size()%> </td>
                                    <td> <%= screenOneDayDTO.getListDay2().size()%> </td>
                                    <td> <%= screenOneDayDTO.getListDay3().size()%> </td>
                                </tr>
                                <%}
                                    }%>
                            </tbody>
                        </table>

                    </div>
                </center>


                <center>
                    <br />
                    <br />
                    <p><center>Bảng thống kê chi tiết về trong 3 ngày </center></p>
                    <br />
                    <p>
                        <%
                            if (results != null && results.size() > 0) {
                                for (int i = 0; i < results.size(); i++) {
                                    ScreenBachthuSpecialDTO dto = results.get(i);
                        %> 
                        <input type="checkbox" name="loto_show" class="loto_show" value="<%=i%>" style="margin:0" <%if (i < 3) {%> checked <%}%> > <%= dto.getLoto() %> &nbsp&nbsp&nbsp
                        <%}
                            }%>

                    </p>
                    <br />
                    <div class="detail_loto" style="overflow: hidden;width:1060px">
                        <center>
                            <div class="date_detail" style="width:260px;float:left">
                                <table class="sc-table-tsc">
                                    <colgroup>
                                        <col width="120px">
                                        <col width="80px">
                                        <col width="60px">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <td style="background-color:#FF99FF;padding:16px 0 15px 0">Ngày</td>
                                            <td style="background-color:#FF9900;padding:16px 0 15px 0">Đặc biệt</td>
                                            <td style="background-color:#FFCC99;padding:16px 0 15px 0">Sau đó</td>
                                        </tr>
                                        <%for (SpecialAwardDTO award : awards) {%>
                                        <tr>
                                            <td style="background-color:#FFCCFF;"><%= CommonUtil.convertDateToString2(award.getOpenDate())%></td>
                                            <td style="background-color:#FFCC66;"><b><%=award.getSpecialAward()%></b></td>
                                            <td>-----</td>
                                        </tr>   
                                        <%}%>
                                    </tbody>                    
                                </table>
                            </div>

                            <div class="loto_detail" style="width:780px;float:left;">
                                <table class="sc-table-tsc">
                                    <colgroup>
                                        <col width="260px">
                                        <col width="260px">
                                        <col width="260px">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <td colspan="3" style="background-color:#CCFFCC;">Số lần về cụ thể từng ngày như sau</td>
                                        </tr>
                                        <tr>
                                            <td style="background-color:#99FF66;">Ngày 1</td>
                                            <td style="background-color:#FF9933;">Ngày 2</td>
                                            <td style="background-color:#FF6699;">Ngày 3</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            if(awards != null && awards.size() > 0) {
                                            
                                                for(SpecialAwardDTO award : awards) {
                                        %>
                                                <tr>
                                                    <%
                                                        String r1t1 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(0).getListDay1(), 1);
                                                        String r1t2 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(1).getListDay1(), 1);
                                                        String r1t3 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(2).getListDay1(), 1);
                                                        String r1t4 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(3).getListDay1(), 1);
                                                        String r1t5 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(4).getListDay1(), 1);
                                                        String r1t6 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(5).getListDay1(), 1);
                                                        String r1t7 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(6).getListDay1(), 1);
                                                        String r1t8 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(7).getListDay1(), 1);
                                                        String r1t9 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(8).getListDay1(), 1);
                                                        String r1t10 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(9).getListDay1(), 1);
                                                        
                                                        String r2t1 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(0).getListDay2(), 2);
                                                        String r2t2 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(1).getListDay2(), 2);
                                                        String r2t3 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(2).getListDay2(), 2);
                                                        String r2t4 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(3).getListDay2(), 2);
                                                        String r2t5 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(4).getListDay2(), 2);
                                                        String r2t6 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(5).getListDay2(), 2);
                                                        String r2t7 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(6).getListDay2(), 2);
                                                        String r2t8 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(7).getListDay2(), 2);
                                                        String r2t9 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(8).getListDay2(), 2);
                                                        String r2t10 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(9).getListDay2(), 2);
                                                       
                                                        String r3t1 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(0).getListDay3(), 3);
                                                        String r3t2 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(1).getListDay3(), 3);
                                                        String r3t3 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(2).getListDay3(), 3);
                                                        String r3t4 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(3).getListDay3(), 3);
                                                        String r3t5 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(4).getListDay3(), 3);
                                                        String r3t6 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(5).getListDay3(), 3);
                                                        String r3t7 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(6).getListDay3(), 3);
                                                        String r3t8 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(7).getListDay3(), 3);
                                                        String r3t9 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(8).getListDay3(), 3);
                                                        String r3t10 = CommonUtil.getValueLotoSpecial(award.getOpenDate(), results.get(9).getListDay3(), 3);
                                                    %>
                                                    <td>
                                                        &nbsp
                                                        <span class="loto0_detail" style=""><%=r1t1%><%if(!CommonUtil.isEmptyString(r1t1)) {%>,<%}%></span>
                                                        <span class="loto1_detail" style=""><%=r1t2%><%if(!CommonUtil.isEmptyString(r1t2)) {%>,<%}%></span>
                                                        <span class="loto2_detail" style=""><%=r1t3%><%if(!CommonUtil.isEmptyString(r1t3)) {%>,<%}%></span>
                                                        <span class="loto3_detail" style="display:none"><%=r1t4%><%if(!CommonUtil.isEmptyString(r1t4)) {%>,<%}%></span>
                                                        <span class="loto4_detail" style="display:none"><%=r1t5%><%if(!CommonUtil.isEmptyString(r1t5)) {%>,<%}%></span>
                                                        <span class="loto5_detail" style="display:none"><%=r1t6%><%if(!CommonUtil.isEmptyString(r1t6)) {%>,<%}%></span>
                                                        <span class="loto6_detail" style="display:none"><%=r1t7%><%if(!CommonUtil.isEmptyString(r1t7)) {%>,<%}%></span>
                                                        <span class="loto7_detail" style="display:none"><%=r1t8%><%if(!CommonUtil.isEmptyString(r1t8)) {%>,<%}%></span>
                                                        <span class="loto8_detail" style="display:none"><%=r1t9%><%if(!CommonUtil.isEmptyString(r1t9)) {%>,<%}%></span>
                                                        <span class="loto9_detail" style="display:none"><%=r1t10%></span>
                                                        &nbsp
                                                    </td>
                                                    
                                                    <td>
                                                        &nbsp
                                                        <span class="loto0_detail" style=""><%=r2t1%> <%if(!CommonUtil.isEmptyString(r2t1)) {%>,<%}%></span>
                                                        <span class="loto1_detail" style=""><%=r2t2%> <%if(!CommonUtil.isEmptyString(r2t2)) {%>,<%}%></span>
                                                        <span class="loto2_detail" style=""><%=r2t3%> <%if(!CommonUtil.isEmptyString(r2t3)) {%>,<%}%></span>
                                                        <span class="loto3_detail" style="display:none"><%=r2t4%> <%if(!CommonUtil.isEmptyString(r2t4)) {%>,<%}%></span>
                                                        <span class="loto4_detail" style="display:none"><%=r2t5%> <%if(!CommonUtil.isEmptyString(r2t5)) {%>,<%}%></span>
                                                        <span class="loto5_detail" style="display:none"><%=r2t6%> <%if(!CommonUtil.isEmptyString(r2t6)) {%>,<%}%></span>
                                                        <span class="loto6_detail" style="display:none"><%=r2t7%> <%if(!CommonUtil.isEmptyString(r2t7)) {%>,<%}%></span>
                                                        <span class="loto7_detail" style="display:none"><%=r2t8%> <%if(!CommonUtil.isEmptyString(r2t8)) {%>,<%}%></span>
                                                        <span class="loto8_detail" style="display:none"><%=r2t9%> <%if(!CommonUtil.isEmptyString(r2t9)) {%>,<%}%></span>
                                                        <span class="loto9_detail" style="display:none"><%=r2t10%></span>
                                                        &nbsp
                                                    </td>
                                                    
                                                    <td>
                                                        &nbsp
                                                        <span class="loto0_detail" style=""><%=r3t1%><%if(!CommonUtil.isEmptyString(r3t1)) {%>,<%}%></span>
                                                        <span class="loto1_detail" style=""><%=r3t2%><%if(!CommonUtil.isEmptyString(r3t2)) {%>,<%}%></span>
                                                        <span class="loto2_detail" style=""><%=r3t3%><%if(!CommonUtil.isEmptyString(r3t3)) {%>,<%}%></span>
                                                        <span class="loto3_detail" style="display:none"><%=r3t4%><%if(!CommonUtil.isEmptyString(r3t4)) {%>,<%}%></span>
                                                        <span class="loto4_detail" style="display:none"><%=r3t5%><%if(!CommonUtil.isEmptyString(r3t5)) {%>,<%}%></span>
                                                        <span class="loto5_detail" style="display:none"><%=r3t6%><%if(!CommonUtil.isEmptyString(r3t6)) {%>,<%}%></span>
                                                        <span class="loto6_detail" style="display:none"><%=r3t7%><%if(!CommonUtil.isEmptyString(r3t7)) {%>,<%}%></span>
                                                        <span class="loto7_detail" style="display:none"><%=r3t8%><%if(!CommonUtil.isEmptyString(r3t8)) {%>,<%}%></span>
                                                        <span class="loto8_detail" style="display:none"><%=r3t9%><%if(!CommonUtil.isEmptyString(r3t9)) {%>,<%}%></span>
                                                        <span class="loto9_detail" style="display:none"><%=r3t10%></span>
                                                        &nbsp
                                                    </td>
                                                </tr>
                                        
                                        <%}}%>
                                    </tbody>
                                </table> 
                        </center>
                    </div>  

                    </div>
                </center>
            </section>
            <%}%>

        </div><!-- Hết phần thân -->
        <jsp:include page="../include/footer.jsp"></jsp:include>
        <script src="js/jquery-1.11.1.js"></script>

        <script src="js/bootstrap-datepicker.js"></script>
    </script>        <!-- Jquery Scroll Animated -->

    <script type="text/javascript">
        $(function(){
            $('.loto_show').click(function(){
                //alert('aaa');
                var num = 0;
                $('input[name="loto_show"]').each(function() {
                    var id = this.value;
                    
                    if($(this).is(':checked')) {
                        $('.loto'+id+'_detail').css('display','inline');
                        num = num + 1;
                    }else{
                        $('.loto'+id+'_detail').css('display','none');
                    }
                   //console.log(this.value);
                });
            })
            
        })
    </script>  
    <script type="text/javascript">
        $(function () {
            $('#date').datepicker({
                autoclose: true,
                format: "dd-mm-yyyy",
                todayHighlight: true,
                language: 'vi'
            });

        })
    </script>	

    <link media="all" type="text/css" rel="stylesheet" href="css/bootstrap-datepicker3.standalone.css">

</div>

</body>
</html>