package inet.util;

import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Pattern;

import inet.util.StringTool;
import inet.util.UAgentInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WapTool {

    /**
     * @param args
     */
    public static void main(String[] args) {
		// TODO Auto-generated method stub
        ///System.out.println(!false);
//		System.out.println(getIpFromXForwardedFor("127.0.0.1, 192.168.0.100, 195.189.143.147"));	
        String F5IPPattern = "^10.*||^113.185.[1-31].[0-24]";
        String WAPGWIPPattern = "^172.16.30.[11-12]|^113.185.0.16";

        System.out.println("10.1.10.2".matches(F5IPPattern));
        System.out.println("113.185.2.21".matches(F5IPPattern));

    }

    public static boolean isOnOperaMini(HttpServletRequest request) {
        String ua = ((HttpServletRequest) request).getHeader("User-Agent");
        ua = (ua == null) ? "" : ua.toLowerCase();
        String remoteHost = request.getRemoteHost();
        remoteHost = (remoteHost == null) ? "" : remoteHost.toLowerCase();
        if (ua.indexOf("opera/9.80") != -1) {
            return true;
        } else if (remoteHost.indexOf("opera") != -1) {
            return true;
        }
        return false;
    }

    public static String getRealIpAddressWithOperaMini(HttpServletRequest request) {
        if (isOnOperaMini(request)) {
//			System.out.println("WapTool - isOnOperaMini = "+isOnOperaMini(request));
//			System.out.println("");
//	    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//	    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//	    	Enumeration headerNames = request.getHeaderNames();
//	        	while(headerNames.hasMoreElements()) {
//	          		String headerName = (String)headerNames.nextElement();
//	          		System.out.println(headerName + ": " + request.getHeader(headerName));
//	        	}
//	    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//	    	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

            String HTTP_X_FORWARDED_FOR_VALUE = request.getHeader("HTTP_X_FORWARDED_FOR");
            if (HTTP_X_FORWARDED_FOR_VALUE != null) {
                HTTP_X_FORWARDED_FOR_VALUE = HTTP_X_FORWARDED_FOR_VALUE.trim();
                if (isIpV4(HTTP_X_FORWARDED_FOR_VALUE)) {
                    return HTTP_X_FORWARDED_FOR_VALUE;
                }
            }
            String ipFromXForwardedFor = getIpFromXForwardedFor(request.getHeader("X-Forwarded-For"));
            if (ipFromXForwardedFor != null && !"".equalsIgnoreCase(ipFromXForwardedFor)) {
                return ipFromXForwardedFor;
            }
        } else {
			//Update mô hình mới Viettel 2012-02-27
            //Xử dụng parameter "VX-Forwarded-For" trong http header
            String valueOfVX_Forwarded_For = request.getHeader("VX-Forwarded-For");
            if (valueOfVX_Forwarded_For != null) {
                valueOfVX_Forwarded_For = valueOfVX_Forwarded_For.trim();
                if (isIpV4(valueOfVX_Forwarded_For)) {
                    System.out.println("WapTool - ip from VX-Forwarded-For = " + valueOfVX_Forwarded_For);
                    return valueOfVX_Forwarded_For;
                }
            }
        }

        //Default
        System.out.println("WapTool - ip from RemoteAddr()");
        return request.getRemoteAddr();
    }

    public static String getIpFromXForwardedFor(String strXForwardedFor) {
        if (strXForwardedFor == null) {
            return null;
        }
        System.out.println("inet.util.my.WapTool.getIpFromXForwardedFor(" + strXForwardedFor + ")");
        Vector vIp = (Vector) StringTool.parseString(strXForwardedFor, ",");
        if (vIp != null && vIp.size() > 0 && isIpV4(vIp.get(vIp.size() - 1).toString())) {
            return vIp.get(vIp.size() - 1).toString();
        }
        return null;
    }

    public static boolean isIpV4(String addressIp) {
        if (addressIp == null) {
            return false;
        }
        boolean result = false;
        Vector v = (Vector) StringTool.parseStringEx(addressIp);
        int number = 0;

        if (v.size() == 4) {
            for (int i = 0; i < 4; i++) {
                try {
                    number = Integer.parseInt(v.get(i).toString());
                    if (i == 0 && number > 0 & number <= 255) {
                        result = true;
                    } else if (number >= 0 && number <= 255) {
                        result = true;
                    } else {
                        result = false;
                    }
                } catch (Exception ex) {
                    result = false;
                    break;
                }
            }
        }
        if (!result) {
            System.out.println("WapTool - isIpV4 = " + result);
        }

        return result;
    }

    public static boolean isMobileDevice(HttpServletRequest request) {
        //HttpSession session=request.getSession();
        Boolean isMobile = null;
                //(Boolean) session.getAttribute("IS_MOBILE");
        if (isMobile == null) {
            String userAgent = request.getHeader("User-Agent");
            String httpAccept = request.getHeader("Accept");
            UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);
            isMobile = detector.detectAndroid() || detector.detectAndroidPhone() || detector.detectIos() || detector.detectIpad()
                    || detector.detectIphone() || detector.detectIphoneOrIpod() || detector.detectIpod() || detector.detectWindowsMobile()
                    || detector.detectWindowsPhone() || detector.detectWindowsPhone7() || detector.detectWindowsPhone8();
        }
        //session.setAttribute("IS_MOBILE", isMobile);
        return isMobile;
    }

    public static String getCommand(HttpServletRequest request){
        HttpSession session=request.getSession();
        String isCommand = (String) session.getAttribute("IS_COMMAND");
        if (isCommand == null||"".equals(isCommand)) {
            String userAgent = request.getHeader("User-Agent");
            String httpAccept = request.getHeader("Accept");
            UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);
            if(detector.detectAndroid() || detector.detectAndroidPhone()||detector.detectWindowsMobile()
                    || detector.detectWindowsPhone() || detector.detectWindowsPhone7() || detector.detectWindowsPhone8()){
                    
                isCommand="sms:number?body=syntax";
            }else if(detector.detectIos() || detector.detectIpad()
                    || detector.detectIphone() || detector.detectIphoneOrIpod() || detector.detectIpod()){
                isCommand="sms:number;body=syntax";
            }
        }
        session.setAttribute("IS_COMMAND", isCommand);
        return isCommand;
    }
    
    public static int isMobileOSDevice(HttpServletRequest request) {
        String ua = ((HttpServletRequest) request).getHeader("User-Agent");
        String accept = ((HttpServletRequest) request).getHeader("Accept");
        String xwp = ((HttpServletRequest) request).getHeader("x-wap-profile");
        //System.out.println("ua=" + ua);
        return isMobileOSDevice(ua, accept, xwp);
    }

    public static boolean isMobileDevice(String userAgent, String accept, String xWapProfile) {
        boolean result = false;
        String ua = (userAgent == null) ? "" : userAgent.toLowerCase();
        String a = (accept == null) ? "" : accept.toLowerCase();
        if (ua.matches(".*mobile.*")) {
            return true;
        }
        return //!ua.matches(".*(chrome/|mozilla/).*")
                //&& 
                (ua.matches(".*(ipod|iphone).*")
                || ua.matches(".*android.*")
                || ua.matches(".*opera mini.*")
                || ua.matches(".*blackberry.*")
                || ua.matches(".*(palm|hiptop|avantgo|plucker|xiino|blazer|elaine).*")
                || ua.matches(".*windows ce; (ppc;|smartphone;|iemobile).*")
                || a.matches(".*text/vnd\\.wap\\.wml.*")
                || a.matches(".*application/vnd\\.wap\\.xhtml\\+xml.*")
                || (xWapProfile != null)
                //ThangDT
                || ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")
                || (ua.length() >= 4 && ua.substring(0, 4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-"))
                || ua.matches(".*(iphone|nokia|samsung|htc|ericsson|siemens|sonyericsson|apple|alcatel|benq|blackberry|t-mobile|google|hyundai|i-mate|kyocera|mitsubishi|motorola|sonim|palm|panasonic|pantech|philips|sagem|sanyo|sharp).*"))
                && (!ua.contains("chrome")
                || (ua.contains("chrome") && (ua.matches(".*(ipod|iphone).*") || ua.matches(".*android.*"))));
    }

    public static int isMobileOSDevice(String userAgent, String accept, String xWapProfile) {

        String ua = (userAgent == null) ? "" : userAgent.toLowerCase();
        String a = (accept == null) ? "" : accept.toLowerCase();

        if (ua.matches(".*(ipod|iphone).*")) {
            return 1;
        } else if (ua.matches(".*android.*")) {
            return 2;
        } else {
            return 0;
        }
    }

    //ThangDT 29/02/2012
    public static String getViettelSessionid(HttpServletRequest request) {
        System.out.println("VSessionid=" + request.getHeader("VSessionid"));
        if (request.getHeader("VSessionid") != null && !"".equalsIgnoreCase(request.getHeader("VSessionid"))) {
            return request.getHeader("VSessionid");
        } else {
            System.out.println("Khong lay dc session Id");
            return "1";
        }
    }

}
