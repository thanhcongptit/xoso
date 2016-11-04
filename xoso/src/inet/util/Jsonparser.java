/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import com.google.gson.Gson;

/**
 *
 * @author iNET
 */
public class Jsonparser {    
   
    public static JsonString parser(String string){
        try{
            Gson gson=new Gson();        
            if(string.contains("packagename")){
                JsonString jsonString=(JsonString)gson.fromJson(string, JsonString.class);
                return jsonString;
            }                        
        }catch(ClassCastException e){
            
        }
        return null;
    }
}
