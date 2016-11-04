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
public class PhantichLotoJson {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PhantichLoto> getList() {
        return list;
    }

    public void setList(List<PhantichLoto> list) {
        this.list = list;
    }
    private List<PhantichLoto> list;
}
