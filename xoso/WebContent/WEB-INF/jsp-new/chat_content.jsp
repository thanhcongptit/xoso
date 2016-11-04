<%-- 
    Document   : chat-content
    Created on : 24-Jan-2016, 22:53:09
    Author     : 24h
--%>

<%@page import="java.util.List"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="inet.util.ChatContent"%>
<%@page import="inet.util.ChatUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String action = request.getParameter("action");
    if("getContent".equals(action)){
%>
<%
    ChatContent chatContent = null;
    for(int ci=0; ci<ChatUtil.listChatContent.size(); ci++){
        chatContent = ChatUtil.listChatContent.get(ci);
        %>
        <div id="" class="chatmsgline" rel="">
            <span class="chatid id2" onclick="openChat(<%= chatContent.getUserId() %>, '<%= chatContent.getUsername() %>')" userId="<%= chatContent.getUserId() %>" rel=""><%= chatContent.getUsername() %>:</span> 
            <span class="chatmsg" rel="" title=""><%= chatContent.getMessage() %></span>
        </div>
        <%
    }
%>
<%
    }else if("addMsg".equals(action)){
        String username = request.getParameter("username");
        String msg = request.getParameter("msg");
        String userId = request.getParameter("userId");
        if(username != null && !"".equals(username) & msg != null && !"".equals(msg)){
            ChatUtil.addMsg(username, msg, userId);
        }
    }else if("getContentChatGroup".equals(action)){
        String userIdClick = RequestUtil.getString(request, "userIdClick", "");
        String memberId = RequestUtil.getString(request, "memberId", "");
        List<ChatContent> listChatContent = ChatUtil.getListChatGroupByChatId(memberId, userIdClick);
        ChatContent chatContent = null;
        if(listChatContent != null && !listChatContent.isEmpty()) for(int ci=0; ci<listChatContent.size(); ci++){
            chatContent = listChatContent.get(ci);
            %>
            <div id="" class="chatmsgline" rel="">
                <span class="chatid id2" rel=""><%= chatContent.getUsername() %>:</span> 
                <span class="chatmsg" rel="" title=""><%= chatContent.getMessage() %></span>
            </div>
            <%
        }
    }else if("addMsgGroup".equals(action)){
        String nguoiNoiId = RequestUtil.getString(request, "nguoiNoiId", "");
        String nguoiNgheId = RequestUtil.getString(request, "nguoiNgheId", "");
        String username = RequestUtil.getString(request, "username", "");
        String msg = RequestUtil.getString(request, "msg", "");
        if(!"".equals(nguoiNoiId) && !"".equals(nguoiNgheId) && !"".equals(username) && !"".equals(msg)){
            ChatUtil.addMsgChatGroup(username, msg, nguoiNoiId, nguoiNgheId);
        }
    }else if("getListChatCurrent".equals(action)){
        String memberId = RequestUtil.getString(request, "memberId", "");
        if(!"".equals(memberId)){
            if(ChatUtil.hmPingChat.get(memberId) != null){
                List<String> userIdClicks = ChatUtil.hmPingChat.get(memberId);
                for(String userIdClick : userIdClicks){
                    out.print(userIdClick+","+ChatUtil.hmName.get(userIdClick)+"-");
                }
            }
        }
    }else if("removeChatGroupByUserIdClick".equals(action)){
        String userIdClick = RequestUtil.getString(request, "userIdClick", "");
        String memberId = RequestUtil.getString(request, "memberId", "");
        if(!"".equals(memberId) && ChatUtil.hmPingChat.get(memberId) != null){
            List<String> userIdClicks = ChatUtil.hmPingChat.get(memberId);
            userIdClicks.remove(userIdClick);
        }
    }
%>
