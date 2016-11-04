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
public class XSVuiHistoryJson extends Base{
    private int total_page;
    private List<XSVuiHistory> trans_info;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<XSVuiHistory> getTrans_info() {
        return trans_info;
    }

    public void setTrans_info(List<XSVuiHistory> trans_info) {
        this.trans_info = trans_info;
    }
}
