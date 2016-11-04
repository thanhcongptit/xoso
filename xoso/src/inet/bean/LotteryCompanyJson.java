/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.util.List;

/**
 *
 * @author iNET
 */
public class LotteryCompanyJson {
    private String status;
    private List<LotteryCompany> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LotteryCompany> getList() {
        return list;
    }

    public void setList(List<LotteryCompany> list) {
        this.list = list;
    }
}
