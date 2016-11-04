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
public class FilterMobile {
    private String isMobile;

    public String getIsMobile() {
        return isMobile;
    }
   
    public String getCommand() {
        return command;
    }

    
    private String command;

    public FilterMobile(String ugent,String httpAccept) {
        checkMobile(ugent, httpAccept);
    }
    
    
    
    
    private void checkMobile(String ugent,String httpAccept){
        UAgentInfo detector = new UAgentInfo(ugent, httpAccept);
        command = "sms:number?body=syntax";
        if ((detector.detectAndroid()) || (detector.detectAndroidPhone())) {
          isMobile = "MOBILE";
        }
        else if ((detector.detectIos()) || (detector.detectIpad()) || (detector.detectIphone()) || 
          (detector
          .detectIphoneOrIpod()) || (detector.detectIpod())) {
          command = "sms:number;body=syntax";
          isMobile = "MOBILE";
        }
        else if ((detector.detectWindowsMobile()) || (detector.detectWindowsPhone()) || 
          (detector
          .detectWindowsPhone7()) || (detector.detectWindowsPhone8())) {
          isMobile = "MOBILE";
        }
        else if (detector.detectMobileQuick()) {
          isMobile = "MOBILE";
        }
        else {
          isMobile = "PC";
          command="Soan tin";
        }
    }
}
