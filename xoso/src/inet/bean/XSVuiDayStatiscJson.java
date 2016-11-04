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
public class XSVuiDayStatiscJson {
    private int status;
    private List<XSVuiDayStatisc> betting_list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<XSVuiDayStatisc> getBetting_list() {
        return betting_list;
    }

    public void setBetting_list(List<XSVuiDayStatisc> betting_list) {
        this.betting_list = betting_list;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }
    private String error_desc;
}
