/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.util.List;

/**
 *
 * @author hanhlm
 */
public class XSVuiChotSoJson {
    private List<XSVuiChotSo> betting_info;

    public List<XSVuiChotSo> getBetting_info() {
        return betting_info;
    }

    public void setBetting_info(List<XSVuiChotSo> betting_info) {
        this.betting_info = betting_info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }
    private int status;
    private String error_desc;
    private String loi_lo;

    public String getLoi_lo() {
        return loi_lo;
    }

    public void setLoi_lo(String loi_lo) {
        this.loi_lo = loi_lo;
    }
    private int total_page;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }
}
