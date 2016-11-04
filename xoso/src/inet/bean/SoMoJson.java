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
public class SoMoJson {
    private int status;
    private List<SoMo> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SoMo> getList() {
        return list;
    }

    public void setList(List<SoMo> list) {
        this.list = list;
    }
}
