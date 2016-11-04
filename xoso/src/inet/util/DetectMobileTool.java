package inet.util;

import javax.servlet.http.HttpServletRequest;

public class DetectMobileTool {
	public static boolean isMobileDevice(HttpServletRequest request){
	    String userAgent = request.getHeader("User-Agent");
	    String httpAccept = request.getHeader("Accept");

	    UAgentInfo detector = new UAgentInfo(userAgent, httpAccept);
	    if (detector.detectMobileQuick()) {
	        return true;
	    }
	    return false;
	}
}
