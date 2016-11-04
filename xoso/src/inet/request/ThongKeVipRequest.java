/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.request;

import com.google.gson.Gson;
import inet.bean.ThongKeVip;
import inet.bean.ThongKeVipJson;
import inet.model.ThongKeVipDAO;
import inet.util.Contant;
import inet.util.HttpURLRequest;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class ThongKeVipRequest {
    
    private List<ThongKeVip> order(List<ThongKeVip> list){        
        if(list==null||list.isEmpty()){return list;}
        ThongKeVip thongKeVip1=null;
        ThongKeVip thongKeVip2=null;
        String[] arrStr=null;
        int so1=0;
        int so2=0;
        for(int i=0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size();j++){
                thongKeVip1=list.get(i);
                thongKeVip2=list.get(j);
                if(thongKeVip1.getGen_date()==null||"".equals(thongKeVip1.getGen_date())){break;}
                else{
                    arrStr=thongKeVip1.getGen_date().split("/");
                    so1=Integer.parseInt(arrStr[2]+arrStr[1]+arrStr[0]);
                    arrStr=thongKeVip2.getGen_date().split("/");
                    so2=Integer.parseInt(arrStr[2]+arrStr[1]+arrStr[0]);

                    if(so2>so1){
                        list.set(i, thongKeVip2);
                        list.set(j, thongKeVip1);
                    }
                }
            }
        }
        
        return list;
    }
    
    ////////////////////////////////////////////////////////
    public ThongKeVip parserThongKeVip(String code,String ddmmyyyy){
        try{
//            Gson gson=new Gson();        
//            String url=Contant.URL_THONG_KE_VIP;
//            url=url.replace("code", code).replace("ddmmyyyy", ddmmyyyy);
//            String string=HttpURLRequest.sendGet(url);
//            if(string.contains("thongKeVip")){
//                ThongKeVipJson thongKeVipJson=(ThongKeVipJson)gson.fromJson(string, ThongKeVipJson.class);                
//                return thongKeVipJson.getThongKeVip();
//            }
            
            ThongKeVipDAO thongKeVipDAO=new ThongKeVipDAO();
            ThongKeVip thongKeVip=thongKeVipDAO.findThongKeVip(code, ddmmyyyy);
            
            return thongKeVip;
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeVipRequest=======>>parserThongKeVip=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<ThongKeVip> parserThongKeVips(String code,String numrow){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_VIPS;
            url=url.replace("code", code).replace("numrow", numrow);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("list")){
                ThongKeVipJson thongKeVipJson=(ThongKeVipJson)gson.fromJson(string, ThongKeVipJson.class);                
                return order(thongKeVipJson.getList());
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeVipRequest=======>>parserThongKeVips=======>>"+e.toString());        
        }                
        return null;
    }
}
