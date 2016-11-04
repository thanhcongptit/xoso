/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 24h
 */
public class ChatUtil {

    public static List<ChatContent> listChatContent = new ArrayList<ChatContent>();

    public ChatUtil() {
    }

    //userIdClick-List<memberId>: toi co nhung nguoi nay goi
    public static HashMap<String, List<String>> hmPingChat = new HashMap<String, List<String>>();
    
    //userIdClick-memberId or memberId-userIdClick / List<ChatContent>
    public static HashMap<String, List<ChatContent>> hmChatGroup = new HashMap<String, List<ChatContent>>();
    
    public static List<ChatContent> getListChatGroupByChatId(String nguoiNoiId, String nguoiNgheId){
        String chatId = nguoiNoiId+"-"+nguoiNgheId;
        if(hmChatGroup.get(chatId) != null) return hmChatGroup.get(chatId);
        else{
            chatId = nguoiNgheId+"-"+nguoiNoiId;
            if(hmChatGroup.get(chatId) != null) return hmChatGroup.get(chatId);
        }
        return null;
    }
    
    public static HashMap<String, String> hmName = new HashMap<String, String>();
    
    
    public static void addMsgChatGroup(String username, String message, String nguoiNoiId, String nguoiNgheId){
        hmName.put(nguoiNoiId, username);
        if(hmPingChat.get(nguoiNgheId) != null){
            List<String> listNguoiNoi = hmPingChat.get(nguoiNgheId);
            boolean checkAdd = true;
            for(String nguoiNoi : listNguoiNoi){
                if(nguoiNoi.equals(nguoiNoiId)) {
                    checkAdd = false;
                    break;
                }
            }
            if(checkAdd){
                listNguoiNoi.add(nguoiNoiId);
            }
        }else{
            List<String> listNguoiNoi = new ArrayList<>();
            listNguoiNoi.add(nguoiNoiId);
            hmPingChat.put(nguoiNgheId, listNguoiNoi);
        }
        String chatId = nguoiNoiId+"-"+nguoiNgheId;
        if(hmChatGroup.get(chatId) != null){
            List<ChatContent> listChat = hmChatGroup.get(chatId);
            ChatContent chatContent = new ChatContent();
            chatContent.setUsername(username);
            for(Map.Entry<String, String> emotion : hmEmotion.entrySet()){
                message = message.replace(emotion.getKey(), "<img src='"+emotion.getValue()+"'/>");
            }
            chatContent.setMessage(message);
            chatContent.setUserId(nguoiNoiId);
            listChat.add(chatContent);
        }else{
            List<ChatContent> listChat = new ArrayList<ChatContent>();
            ChatContent chatContent = new ChatContent();
            chatContent.setUsername(username);
            for(Map.Entry<String, String> emotion : hmEmotion.entrySet()){
                message = message.replace(emotion.getKey(), "<img src='"+emotion.getValue()+"'/>");
            }
            chatContent.setMessage(message);
            chatContent.setUserId(nguoiNoiId);
            listChat.add(chatContent);
            hmChatGroup.put(chatId, listChat);
        }
        
        chatId = nguoiNgheId+"-"+nguoiNoiId;
        if(hmChatGroup.get(chatId) != null){
            List<ChatContent> listChat = hmChatGroup.get(chatId);
            ChatContent chatContent = new ChatContent();
            chatContent.setUsername(username);
            for(Map.Entry<String, String> emotion : hmEmotion.entrySet()){
                message = message.replace(emotion.getKey(), "<img src='"+emotion.getValue()+"'/>");
            }
            chatContent.setMessage(message);
            chatContent.setUserId(nguoiNoiId);
            listChat.add(chatContent);
        }else{
            List<ChatContent> listChat = new ArrayList<ChatContent>();
            ChatContent chatContent = new ChatContent();
            chatContent.setUsername(username);
            for(Map.Entry<String, String> emotion : hmEmotion.entrySet()){
                message = message.replace(emotion.getKey(), "<img src='"+emotion.getValue()+"'/>");
            }
            chatContent.setMessage(message);
            chatContent.setUserId(nguoiNoiId);
            listChat.add(chatContent);
            hmChatGroup.put(chatId, listChat);
        }
    }
    
    public static void addMsg(String username, String message, String userId) {
        ChatContent chatContent = new ChatContent();
        chatContent.setUsername(username);
        for(Map.Entry<String, String> emotion : hmEmotion.entrySet()){
            message = message.replace(emotion.getKey(), "<img src='"+emotion.getValue()+"'/>");
        }
        chatContent.setMessage(message);
        chatContent.setUserId(userId);
        listChatContent.add(chatContent);
    }

    public static LinkedHashMap<String, String> hmEmotion = new LinkedHashMap<String, String>();

    static {        
        hmEmotion.put(";;)", "http://rongbachkim.com/images/emoticons/5.gif");
        hmEmotion.put(">:D<", "http://rongbachkim.com/images/emoticons/6.gif");
        hmEmotion.put(":-/", "http://rongbachkim.com/images/emoticons/7.gif");        
        hmEmotion.put(":'>", "http://rongbachkim.com/images/emoticons/9.gif");
        hmEmotion.put(":P", "http://rongbachkim.com/images/emoticons/10.gif");
        hmEmotion.put(":-*", "http://rongbachkim.com/images/emoticons/11.gif");
        hmEmotion.put("=((", "http://rongbachkim.com/images/emoticons/12.gif");
        hmEmotion.put(":-O", "http://rongbachkim.com/images/emoticons/13.gif");        
        hmEmotion.put("B-)", "http://rongbachkim.com/images/emoticons/16.gif");
        hmEmotion.put(":-S", "http://rongbachkim.com/images/emoticons/17.gif");
        hmEmotion.put("#:-S", "http://rongbachkim.com/images/emoticons/18.gif");
        hmEmotion.put(">:)", "http://rongbachkim.com/images/emoticons/19.gif");
        hmEmotion.put(":((", "http://rongbachkim.com/images/emoticons/20.gif");
        hmEmotion.put(":))", "http://rongbachkim.com/images/emoticons/21.gif");
        hmEmotion.put(":|", "http://rongbachkim.com/images/emoticons/22.gif");
        hmEmotion.put("=))", "http://rongbachkim.com/images/emoticons/24.gif");
        hmEmotion.put(":-c", "http://rongbachkim.com/images/emoticons/101.gif");
        hmEmotion.put("~X(", "http://rongbachkim.com/images/emoticons/102.gif");
        hmEmotion.put(":-h", "http://rongbachkim.com/images/emoticons/103.gif");
        hmEmotion.put(":-t", "http://rongbachkim.com/images/emoticons/104.gif");
        hmEmotion.put("8->", "http://rongbachkim.com/images/emoticons/105.gif");
        hmEmotion.put("I-)", "http://rongbachkim.com/images/emoticons/28.gif");
        hmEmotion.put("8-|", "http://rongbachkim.com/images/emoticons/29.gif");
        hmEmotion.put("[-(", "http://rongbachkim.com/images/emoticons/33.gif");
        hmEmotion.put("8-}", "http://rongbachkim.com/images/emoticons/35.gif");
        hmEmotion.put("(:|", "http://rongbachkim.com/images/emoticons/37.gif");
        hmEmotion.put("=P~", "http://rongbachkim.com/images/emoticons/38.gif");
        hmEmotion.put(":-?", "http://rongbachkim.com/images/emoticons/39.gif");
        hmEmotion.put("#-o", "http://rongbachkim.com/images/emoticons/40.gif");
        hmEmotion.put("=D>", "http://rongbachkim.com/images/emoticons/41.gif");
        hmEmotion.put(":-SS", "http://rongbachkim.com/images/emoticons/42.gif");
        hmEmotion.put("@-)", "http://rongbachkim.com/images/emoticons/43.gif");
        hmEmotion.put(":^o", "http://rongbachkim.com/images/emoticons/44.gif");
        hmEmotion.put(":-w", "http://rongbachkim.com/images/emoticons/45.gif");
        hmEmotion.put(":-<", "http://rongbachkim.com/images/emoticons/46.gif");
        hmEmotion.put(">:P", "http://rongbachkim.com/images/emoticons/47.gif");
        hmEmotion.put("X_X", "http://rongbachkim.com/images/emoticons/109.gif");
        hmEmotion.put(":!!", "http://rongbachkim.com/images/emoticons/110.gif");
        hmEmotion.put(":m/", "http://rongbachkim.com/images/emoticons/111.gif");
        hmEmotion.put(":-q", "http://rongbachkim.com/images/emoticons/112.gif");
        hmEmotion.put(":-bd", "http://rongbachkim.com/images/emoticons/113.gif");
        hmEmotion.put("^#(^", "http://rongbachkim.com/images/emoticons/114.gif");
        hmEmotion.put(":-??", "http://rongbachkim.com/images/emoticons/106.gif");
        hmEmotion.put("%-(", "http://rongbachkim.com/images/emoticons/107.gif");
        hmEmotion.put(":@)", "http://rongbachkim.com/images/emoticons/49.gif");
        hmEmotion.put("3:-O", "http://rongbachkim.com/images/emoticons/50.gif");
        hmEmotion.put(":(|)", "http://rongbachkim.com/images/emoticons/51.gif");
        hmEmotion.put("~:>", "http://rongbachkim.com/images/emoticons/52.gif");
        hmEmotion.put("@};-", "http://rongbachkim.com/images/emoticons/53.gif");
        hmEmotion.put("~O)", "http://rongbachkim.com/images/emoticons/57.gif");
        hmEmotion.put("*-:)", "http://rongbachkim.com/images/emoticons/58.gif");
        hmEmotion.put("8-X", "http://rongbachkim.com/images/emoticons/59.gif");
        hmEmotion.put("[-O<", "http://rongbachkim.com/images/emoticons/63.gif");
        hmEmotion.put("$-)", "http://rongbachkim.com/images/emoticons/64.gif");
        hmEmotion.put(":-'", "http://rongbachkim.com/images/emoticons/65.gif");
        hmEmotion.put("b-(", "http://rongbachkim.com/images/emoticons/66.gif");
        hmEmotion.put(":)>-", "http://rongbachkim.com/images/emoticons/67.gif");
        hmEmotion.put("[-X", "http://rongbachkim.com/images/emoticons/68.gif");
        hmEmotion.put("\\:D/", "http://rongbachkim.com/images/emoticons/69.gif");
        hmEmotion.put(">:/", "http://rongbachkim.com/images/emoticons/70.gif");
        hmEmotion.put(";))", "http://rongbachkim.com/images/emoticons/71.gif");
        hmEmotion.put("^:)^", "http://rongbachkim.com/images/emoticons/77.gif");
        hmEmotion.put(":-j", "http://rongbachkim.com/images/emoticons/78.gif");
        hmEmotion.put(":-$", "http://rongbachkim.com/images/emoticons/32.gif");
        hmEmotion.put("(*)", "http://rongbachkim.com/images/emoticons/79.gif");
        hmEmotion.put(":)", "http://rongbachkim.com/images/emoticons/1.gif");
        hmEmotion.put(":(", "http://rongbachkim.com/images/emoticons/2.gif");
        hmEmotion.put(";)", "http://rongbachkim.com/images/emoticons/3.gif");
        hmEmotion.put(":D", "http://rongbachkim.com/images/emoticons/4.gif");
        hmEmotion.put("X(", "http://rongbachkim.com/images/emoticons/14.gif");
        hmEmotion.put(":>", "http://rongbachkim.com/images/emoticons/15.gif");
        hmEmotion.put(":x", "http://rongbachkim.com/images/emoticons/8.gif");
    }
}
