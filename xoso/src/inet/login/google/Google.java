/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.login.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Designer Nguyễn
 */
public final class Google implements Serializable {

//    public static final String CLIENT_ID = "927284132517-7pgg3n84o3q4uauo52b9u0tp6bm7epsl.apps.googleusercontent.com";
//    public static final String CLIENT_SECRET = "MgvAVZSJlOiyknQ8LDoGasbh";
    
    public static final String CLIENT_ID = "404146534912-jv0r3se0d7qh3lvf0bps45i3tfqukho1.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "2YuBzcc6gyVguJAFe01Nz1i6";

    //user can modify two fields bellow
    private String scope = "https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email";
    private String redirectUrl;

    private final Iterable<String> SCOPE_ARR = Arrays.asList(scope.split(";"));
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final String LOGOUT_URL = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // end google authentication constants
    private String stateToken;

    private final GoogleAuthorizationCodeFlow flow;

    /**
     * Constructor initializes the Google Authorization Code Flow with CLIENT
     * ID, SECRET, and SCOPE
     * @param redirectUrl
     */
    public Google(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        this.redirectUrl = "http://soicaupro.com/xoso/login/google/index.htm";
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE_ARR).build();
        generateStateToken();
    }

    /**
     * Builds a login URL based on client ID, secret, callback URI, and scope
     * @return 
     */
    public String getLoginUrl() {
        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        return url.setRedirectUri(this.redirectUrl).setState(stateToken).build();
    }

    public String getLogoutUrl(String logoutCallbackUrl) {
        if (!"".equals(logoutCallbackUrl)) {
            return LOGOUT_URL + "?continue=" + logoutCallbackUrl;
        } else {
            return LOGOUT_URL;
        }
    }

    private void generateStateToken() {
        SecureRandom sr1 = new SecureRandom();
        stateToken = "google;" + sr1.nextInt();
    }

    public String getStateToken() {
        return stateToken;
    }

    public GoogleUser parseGoogleResponse(HttpServletRequest req, String code) throws IOException, GoogleException {
        final String authCode = code;
        GoogleUser ggUser = null;
        if (authCode != null && !"".equals(authCode)) {
            final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(this.redirectUrl).execute();
            final Credential credential = flow.createAndStoreCredential(response, null);
            final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
            // Make an authenticated request
            final GenericUrl url = new GenericUrl(USER_INFO_URL);
            final HttpRequest request = requestFactory.buildGetRequest(url);
            request.getHeaders().setContentType("application/json");
            final String jsonIdentity = request.execute().parseAsString();
            Gson gson = new Gson();
            ggUser = gson.fromJson(jsonIdentity, GoogleUser.class);
        } else {
            throw new GoogleException("Authcode is null");
        }
        return ggUser;

    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
