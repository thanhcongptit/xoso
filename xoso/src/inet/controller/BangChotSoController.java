/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.controller;

import inet.bean.BangChotSo;
import inet.bean.CapsoOnline;
import inet.model.CapsoOnlineDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class BangChotSoController extends BaseController {
    
    public BangChotSoController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod=super.handleRequestInternal(request, response);
        
        // seo
        String slogan="Lịch mở thưởng xổ số";
        String title="Bảng chốt số hôm nay";
        String keywords="lich mo thuong xo so, lich mo thuong xo so mien bac, lich mo thuong xo so mien trung, lich mo thuong xo so mien nam";
        String description="Lịch mở thưởng kết quả xổ số miền Bắc, miền Trung, Miền Nam. Thống kê kết quả xổ số ba miền nhanh và chính xác."; 
        
        CapsoOnlineDAO capsoOnlineDAO = new CapsoOnlineDAO();
        List<CapsoOnline> listCapso = capsoOnlineDAO.findBangChotSo();
        List<BangChotSo> listBangChotSo = new ArrayList<>();
        BangChotSo bangChotSo = null;
        BigDecimal memberIdCurrent = null;
        int i = 0;
        if(listCapso != null) for(CapsoOnline capsoOnline : listCapso){
            i++;
            if(memberIdCurrent == null || capsoOnline.getMemberId().compareTo(memberIdCurrent) != 0){                
                if(memberIdCurrent != null && capsoOnline.getMemberId().compareTo(memberIdCurrent) != 0){
                    listBangChotSo.add(bangChotSo);
                }
                bangChotSo = new BangChotSo();
            }
            if(bangChotSo.getMemberId() == null) bangChotSo.setMemberId(capsoOnline.getMemberId());
            if(bangChotSo.getMemberName() == null || "".equals(bangChotSo.getMemberName())) bangChotSo.setMemberName(capsoOnline.getMemberName());
            if(bangChotSo.getNgaychot() == null) bangChotSo.setNgaychot(capsoOnline.getGenDate());
            bangChotSo.setListLoto(bangChotSo.getListLoto()+" "+capsoOnline.getCapso());
            if(i == listCapso.size()){
                listBangChotSo.add(bangChotSo);
            }
            memberIdCurrent = capsoOnline.getMemberId();
        }
        
        mod.addObject("listBangChotSo", listBangChotSo);
        mod.addObject("slogan", slogan);
        mod.addObject("title", title);
        mod.addObject("keywords", keywords);
        mod.addObject("description", description);
        
        mod.setViewName("/bang-chot-so");
        
        return mod;
    }
    
}
