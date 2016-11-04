/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

/**
 *
 * @author iNET
 */
public class PackageInfo {
    private String packagename;
    private String status;

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLast_time_unsubscribe() {
        return last_time_unsubscribe;
    }

    public void setLast_time_unsubscribe(String last_time_unsubscribe) {
        this.last_time_unsubscribe = last_time_unsubscribe;
    }

    public String getLast_time_renew() {
        return last_time_renew;
    }

    public void setLast_time_renew(String last_time_renew) {
        this.last_time_renew = last_time_renew;
    }
    private String last_time_unsubscribe;
    private String last_time_renew;
}
