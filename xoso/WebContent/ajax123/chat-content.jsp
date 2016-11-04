<%-- 
    Document   : chat-content
    Created on : 24-Jan-2016, 22:53:09
    Author     : 24h
--%>

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
            <span class="chatid id2" rel=""><%= chatContent.getUsername() %>:</span> 
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
    }
%>
