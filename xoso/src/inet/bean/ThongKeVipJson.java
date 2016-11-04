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
public class ThongKeVipJson {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ThongKeVip getThongKeVip() {
        return thongKeVip;
    }

    public void setThongKeVip(ThongKeVip thongKeVip) {
        this.thongKeVip = thongKeVip;
    }

    public List<ThongKeVip> getList() {
        return list;
    }

    public void setList(List<ThongKeVip> list) {
        this.list = list;
    }
    private ThongKeVip thongKeVip;
    private List<ThongKeVip> list;
}
