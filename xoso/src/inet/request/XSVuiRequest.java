/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.request;

import com.google.gson.Gson;
import inet.bean.Base;
import inet.bean.TopBetting;
import inet.bean.TopBettingJson;
import inet.bean.XSVuiChotSo;
import inet.bean.XSVuiChotSoJson;
import inet.bean.XSVuiFollow;
import inet.bean.XSVuiFollowJson;
import inet.bean.XSVuiHistory;
import inet.bean.XSVuiHistoryJson;
import inet.bean.XSVuiTopUser;
import inet.bean.XSVuiTopUserJson;
import inet.bean.XSVuiUser;
import inet.bean.XSVuiUserJson;
import inet.util.Base64;
import inet.util.Contant;
import inet.util.HttpURLRequest;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class XSVuiRequest {
    private int total_page;
    private String loi_lo;

    public String getLoi_lo() {
        return loi_lo;
    }
    
    public int getTotal_page() {
        return total_page;
    }
    public XSVuiUser parserLogin(String mobile,String username,String password){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_LOGIN;
            url=url.replace("username", username).replace("mobile", mobile);
            url=url.replace("password", password);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("password")){                
                XSVuiUserJson userJson=(XSVuiUserJson)gson.fromJson(string, XSVuiUserJson.class);                
                return userJson.getUser_info();
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserLogin=======>>"+e.toString());        
        }                
        return null;
    }
    
    public void parserBetting(String mobile,String username,String userid,String type,String so1,String so2
                                ,String so3,String coin1,String coin2,String coin3){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_BETTING;
            url=url.replace("mobile", mobile).replace("username", username).replace("userid", userid);            
            url=url.replace("type", type).replace("so1", so1);            
            url=url.replace("so2", so2).replace("so3", so3).replace("coin1", coin1);
            url=url.replace("coin2", coin2).replace("coin3", coin3);            
            String string=HttpURLRequest.sendGet(url);
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserBetting=======>>"+e.toString());        
        }                
        
    }
    
    public List<XSVuiChotSo> parserGetBetting(String mobile,String username,String userid,String type,String sday,String eday){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_GET_BETTING;
            url=url.replace("mobile", mobile).replace("username", username).replace("userid", userid);            
            url=url.replace("type", type).replace("sday", sday).replace("eday", eday);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("betting_info")){
                XSVuiChotSoJson chotSoJson=(XSVuiChotSoJson)gson.fromJson(string, XSVuiChotSoJson.class);
                loi_lo=chotSoJson.getLoi_lo();
                return chotSoJson.getBetting_info();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserGetBetting=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiChotSo> parserGetBettingAll(String type,String mobile,String sday,String eday,String page,String pagesize){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_GET_BETTING_ALL;
            url=url.replace("&LIMIT=", "").replace("limit", "").replace("&DAY", "").replace("=day", "");
            url=url.replace("type", type).replace("mobile", mobile).replace("sday", sday).replace("eday", eday).replace("pagesize", pagesize).replace("page", page);                                 
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("betting_info")){
                XSVuiChotSoJson chotSoJson=(XSVuiChotSoJson)gson.fromJson(string, XSVuiChotSoJson.class);
                loi_lo=chotSoJson.getLoi_lo();
                total_page=chotSoJson.getTotal_page();
                return chotSoJson.getBetting_info();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserGetBettingAll=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiChotSo> parserGetBettingAll(String type,String day,String limit){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_GET_BETTING_ALL;            
            url=url.replace("type", type).replace("day", day).replace("limit", limit);                                 
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("betting_info")){
                XSVuiChotSoJson chotSoJson=(XSVuiChotSoJson)gson.fromJson(string, XSVuiChotSoJson.class);
                return chotSoJson.getBetting_info();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserGetBettingAll=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public String parserChangePasswd(String mobile,String passwdOld,String passwdNew){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_CHANGEPASSWD;
            url=url.replace("mobile", mobile);            
            url=url.replace("oldpass", passwdOld).replace("newpass", passwdNew);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("error_desc")){
                Base b=(Base)gson.fromJson(string, Base.class);
                return b.getError_desc();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserChangePasswd=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public String parserChangeUser(String mobile,String username){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_CHANGEUSER;
            url=url.replace("mobile", mobile);            
            url=url.replace("username", username);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("error_desc")){
                Base b=(Base)gson.fromJson(string, Base.class);
                return b.getError_desc();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserChangeUser=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<TopBetting> parserTopBetting(String type,String day,String limit){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCBETTING;            
            url=url.replace("type", type).replace("day", day).replace("limit", limit);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("betting_list")){
                TopBettingJson topBettingJson=(TopBettingJson)gson.fromJson(string, TopBettingJson.class);
                return topBettingJson.getBetting_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserDayStatiscBetting=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiUser> parserSTATISCLUCKY(String limit){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCLUCKY;            
            url=url.replace("limit", limit);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiUserJson userJson=(XSVuiUserJson)gson.fromJson(string, XSVuiUserJson.class);
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserSTATISCLUCKY=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiUser> parserSTATISCLUCKY(String pagesize,String page){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCLUCKY;            
            url=url.replace("pagesize", pagesize).replace("page", page);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiUserJson userJson=(XSVuiUserJson)gson.fromJson(string, XSVuiUserJson.class);
                total_page=userJson.getTotal_page();
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserSTATISCLUCKY=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiTopUser> parserTopUser(String limit){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCTOPUSER;            
            url=url.replace("limit", limit);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiTopUserJson userJson=(XSVuiTopUserJson)gson.fromJson(string, XSVuiTopUserJson.class);
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserTopUser=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiTopUser> parserTopUser(String pagesize,String page){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCTOPUSER;            
            url=url.replace("pagesize", pagesize).replace("page", page);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiTopUserJson userJson=(XSVuiTopUserJson)gson.fromJson(string, XSVuiTopUserJson.class);
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserTopUser=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiTopUser> parserTopUserDay(String limit,String sday,String eday){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCTOPUSERDAY;            
            url=url.replace("limit", limit).replace("sday", sday).replace("eday", eday);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiTopUserJson userJson=(XSVuiTopUserJson)gson.fromJson(string, XSVuiTopUserJson.class);
                total_page=userJson.getTotal_page();
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserTopUserDay=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiUser> parserSTATISCLUCKYDAY(String limit,String sday,String eday){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCLUCKYDAY;            
            url=url.replace("limit", limit).replace("sday", sday).replace("eday", eday);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiUserJson userJson=(XSVuiUserJson)gson.fromJson(string, XSVuiUserJson.class);
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserSTATISCLUCKYDAY=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiUser> parserSTATISCLUCKYDAY(String pagesize,String sday,String eday,String page){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCLUCKYDAY;            
            url=url.replace("pagesize", pagesize).replace("sday", sday).replace("eday", eday).replace("page", page);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiUserJson userJson=(XSVuiUserJson)gson.fromJson(string, XSVuiUserJson.class);
                total_page=userJson.getTotal_page();
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserSTATISCLUCKYDAY=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiTopUser> parserTopUserDay(String pagesize,String sday,String eday,String page){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_STATISCTOPUSERDAY;            
            url=url.replace("pagesize", pagesize).replace("sday", sday).replace("eday", eday).replace("page", page);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("user_list")){
                XSVuiTopUserJson userJson=(XSVuiTopUserJson)gson.fromJson(string, XSVuiTopUserJson.class);
                total_page=userJson.getTotal_page();
                return userJson.getUser_list();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserTopUserDay=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public int parserFollow(String mobile1,String mobile2){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_FOLLOW;            
            url=url.replace("mobile1", mobile1).replace("mobile2", mobile2);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("status")){
                Base userJson=(Base)gson.fromJson(string, Base.class);
                
                return userJson.getStatus();
            }            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserFollow=======>>"+e.toString());        
        }                
        
        return 1;
    }
    
    public List<XSVuiFollow> parserFollowUserId(String userId){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_FOLLOWUSERID;            
            url=url.replace("userid", userId);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("list")){
                XSVuiFollowJson followJson =(XSVuiFollowJson)gson.fromJson(string, XSVuiFollowJson.class);
                return followJson.getList();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserFollowUserId=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiFollow> parserFollowedId(String userId){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_FOLLOWEDID;            
            url=url.replace("userid", userId);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("list")){
                XSVuiFollowJson followJson =(XSVuiFollowJson)gson.fromJson(string, XSVuiFollowJson.class);
                return followJson.getList();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserFollowedId=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public List<XSVuiHistory> parserHistory(String mobile,String sday){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_HISTORY;            
            url=url.replace("mobile", mobile).replace("sday", sday).replace("eday", sday);            
                     
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("trans_info")){
                XSVuiHistoryJson historyJson =(XSVuiHistoryJson)gson.fromJson(string, XSVuiHistoryJson.class);
                return historyJson.getTrans_info();
            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserHistory=======>>"+e.toString());        
        }                
        
        return null;
    }
    
    public void parserAddCoin(String mobile,String type,String coin,String content,String contentid,String fuser,String feduser){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_XO_SO_VUI_ADDCOIN;            
            url=url.replace("mobile", mobile).replace("type", type).replace("coin",coin);            
            url=url.replace("contentid", contentid).replace("content", content.replaceAll(" ", "%20"));         
            url=url.replace("fuser", fuser).replace("feduser", feduser);                           
            String string=HttpURLRequest.sendGet(url);            
//            if(string.contains("trans_info")){
//               
//            }
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======XSVuiRequest=======>>parserAddCoin=======>>"+e.toString());        
        }                
        
    }
}
