/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.util.List;

/**
 *
 * @author iNET
 */
public class JsonString {
    private String errorid;
    private String totalpackage;
    private String errordesc;
    private List<PackageInfo> packageInfos;

    public List<PackageInfo> getPackageInfos() {
        return packageInfos;
    }

    public void setPackageInfos(List<PackageInfo> packageInfos) {
        this.packageInfos = packageInfos;
    }
    
    public String getErrorid() {
        return errorid;
    }

    public void setErrorid(String errorid) {
        this.errorid = errorid;
    }

    public String getTotalpackage() {
        return totalpackage;
    }

    public void setTotalpackage(String totalpackage) {
        this.totalpackage = totalpackage;
    }

    public String getErrordesc() {
        return errordesc;
    }

    public void setErrordesc(String errordesc) {
        this.errordesc = errordesc;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    private String mobile;
}
