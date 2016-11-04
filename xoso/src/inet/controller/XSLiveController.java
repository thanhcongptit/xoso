/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class XSLiveController extends BaseController {

    public XSLiveController() {
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        // seo
        String slogan = "Kết quả xổ số trực tiếp";
        String title = "Trực tiếp kết quả xổ số hôm nay";
        String keywords = "Xo so truc tiep, truc tiep xo so, ket qua xo so truc tiep";
        String description = "XO SO TRUC TIEP - Trực tiếp kết quả xổ số ba Miền Bắc, Trung, Nam hôm nay nhanh và chính xác nhất";

        if (canonical.contains("xsmb-mien-bac.html")) {
            title = "XSMB Trực tiếp - Trực tiếp kết quả xổ số miền bắc hôm nay";
            description = "XSMB - SXMB - XSTD - TRỰC TIẾP. Trực tiếp kết quả xổ số Miền Bắc hôm nay tại trường quay nhanh nhất";
            keywords = "xsmb truc tiep, truc tiep xsmb, truc tiep ket qua xo so mien bac";
            slogan = "Trực tiếp xổ số miền Bắc - xsmb";
        } else if (canonical.contains("xsmn-mien-nam.html")) {
            title = "XSMN TRỰC TIẾP - Trực tiếp kết quả xổ số Miền Nam hôm nay";
            description = "XSMN - SXMN TRỰC TIẾP. Trực tiếp kết quả xổ số Miền Nam hôm nay";
            keywords = "XSMN truc tiep, truc tiep xsmn, xsmn hom nay";
            slogan = "Trực tiếp xổ số miền Nam - xsmn";
        } else if (canonical.contains("xsmt-mien-trung.html")) {
            title = "XSMT TRỰC TIẾP - Trực tiếp kết quả xổ số Miền Trung hôm nay";
            description = "XSMT - SXMT TRỰC TIẾP. Trực tiếp kết quả xổ số Miền Trung hôm nay";
            keywords = "xsmt truc tiep, truc tiep xsmt, xsmt hom nay";
            slogan = "Trực tiếp xổ số miền Trung - xsmt";
        }

        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);

        if ("pc".equalsIgnoreCase(strMobile)) {
            mod.setViewName("/xslive");
            
        } else {
            mod.setViewName("/mobile/xslive");
        }
        return mod;
    }

}
