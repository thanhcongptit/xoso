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
public class XSVuiUserJson {
    private XSVuiUser user_info;

    public XSVuiUser getUser_info() {
        return user_info;
    }

    public void setUser_info(XSVuiUser user_info) {
        this.user_info = user_info;
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
    
    private List<XSVuiUser> user_list;

    public List<XSVuiUser> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<XSVuiUser> user_list) {
        this.user_list = user_list;
    }
    private int total_page;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }
}
