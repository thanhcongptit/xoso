/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.request;

import com.google.gson.Gson;
import inet.bean.SoMo;
import inet.bean.SoMoJson;
import inet.model.SoMoDAO;
import inet.util.Contant;
import inet.util.HttpURLRequest;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class SoMoRequest {
    public List<SoMo> parserSoMo(){
        try{
//            Gson gson=new Gson();        
//            String url=Contant.URL_SO_MO;            
//            String string=HttpURLRequest.sendGet(url);
//            if(string.contains("list")){
//                SoMoJson soMoJson=(SoMoJson)gson.fromJson(string, SoMoJson.class);                
//                return soMoJson.getList();
//            }
            
            SoMoDAO soMoDAO=new SoMoDAO();
            List<SoMo> list=soMoDAO.findAll();
            return list;
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======SoMoRequest=======>>parserSoMo=======>>"+e.toString());        
        }                
        return null;
    }
}
