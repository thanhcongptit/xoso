<%-- 
    Document   : content-chatchit
    Created on : 14-Jan-2016, 09:20:21
    Author     : 24h
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    <c:if test="${memberLogin ne null}">
        var audioElement = document.createElement('audio');
        var checkPlayMusic = true;
        function playMusic(){
            if(checkPlayMusic){
                audioElement.setAttribute('src', 'resources/music/facebook_sound.mp3');
                audioElement.setAttribute('autoplay', 'autoplay');
                //audioElement.load()

                $.get();

                audioElement.addEventListener("load", function() {
                    audioElement.play();
                }, true);
            }
        }
        var memberId = ${memberLogin.id};
        var listChatCurrent = "";
        function openChat(userIdClick, usernameClick){
            //alert((userIdClick !== memberId)+"userIdClick="+userIdClick+",memberId="+memberId);
            if(userIdClick !== memberId){
                //check dialog da duoc mo chua
                if($("div#chat-dialog-content-"+userIdClick).size() === 0){
                    createChatDialog(userIdClick, usernameClick);
                    if($("span#chathandler_"+userIdClick).size() === 0){
                        createBoxBottom(userIdClick, usernameClick);
                    }
                }
            }
        }
        function sendMsgGroup(username, msg, memberId, userIdClick){
            $.ajax({
                type: 'POST',
                url: '${pageContext.servletContext.contextPath}/chat_content.htm',
                data: {
                    action: 'addMsgGroup',
                    nguoiNoiId: memberId,
                    nguoiNgheId: userIdClick,
                    username: username,
                    msg: msg
                },
                success: function(rs) {
                    $("#editor input#input-"+userIdClick).val("");
                }
            });
        }
        function createChatDialog(userIdClick, usernameClick){
            var chatDialogContentHTML = "<div id='chat-dialog-content-"+userIdClick+"' class='chat-dialog-content'></div>";
            chatDialogContentHTML += "<div id='wapper-emotion'>";
            chatDialogContentHTML += "<a class='icon_toolbar emotion' id='emotion-"+userIdClick+"'><img src='http://rongbachkim.com/images/emoticons/1.gif'/></a>";
            chatDialogContentHTML += "<div class='allicon_holder' id='allicon_holder-"+userIdClick+"'>";
            <c:forEach items="${hmEmotion}" var="emotion">
                chatDialogContentHTML += "<a inp='input-"+userIdClick+"' class='icon_bar_item' alt=\"${emotion.key}\"><img src='${emotion.value}' alt=\"${emotion.key}\"/></a>";
            </c:forEach>
            chatDialogContentHTML += "</div>";
            chatDialogContentHTML += "</div>";
            chatDialogContentHTML += "<div id='editor'><input id='input-"+userIdClick+"' style='width:280px' class='inline'/><button class='btnGui inline' id='sendMsg-"+userIdClick+"'>Gửi</button></div>";
            $("div#chatchit").append("<div style='background: rgb(245, 245, 245);width: 325px;' id='dialog-"+userIdClick+"' title='"+usernameClick+"'>"+chatDialogContentHTML+"</div>");
            $("div#dialog-"+userIdClick).dialog({
                close: function(event, ui){
                    $(this).dialog('destroy').remove();
                }
            });
            $("#emotion-"+userIdClick).click(function(){
                $(this).next().toggle();
            });
            $("#allicon_holder-"+userIdClick+" .icon_bar_item").click(function(){
                var inp = $(this).attr("inp");
                var val = $("#editor input#"+inp).val();                
                $("#editor input#"+inp).val(val+$(this).attr("alt"));
                $(".allicon_holder").hide();
            });
            $("#sendMsg-"+userIdClick).click(function(){
                var username = '${memberLogin.fullname}';
                var msg = $("input#input-"+userIdClick).val();
                sendMsgGroup(username, msg, memberId, userIdClick);
            });
            $("input#input-"+userIdClick).keyup(function(e){
                if(e.keyCode === 13){
                    var username = '${memberLogin.fullname}';
                    var msg = $("input#input-"+userIdClick).val();
                    sendMsgGroup(username, msg, memberId, userIdClick);
                }
            });
        }
        function removeChatGroupByUserIdClick(userIdClick){
            $.ajax({
                type: 'POST',
                url: '${pageContext.servletContext.contextPath}/chat_content.htm',
                data: {
                    action: 'removeChatGroupByUserIdClick',
                    userIdClick: userIdClick,
                    memberId: memberId
                },
                success: function(rs) {
                }
            });
        }
        function createBoxBottom(userIdClick, usernameClick){            
            var box = "<span class='ppchathandler' id='chathandler_"+userIdClick+"'>"+usernameClick+"<a id='aclick-"+userIdClick+"' class='closebutton_round'>&nbsp;</a></span>";
            $("#chat_handler_holder").append(box);
            playMusic();
            $("#aclick-"+userIdClick).click(function(){
                removeChatGroupByUserIdClick(userIdClick);
                $("span#chathandler_"+userIdClick).remove();
                $("div#dialog-"+userIdClick).dialog('destroy').remove();                
            });
            $("span#chathandler_"+userIdClick).click(function(){
                if($("div#chat-dialog-content-"+userIdClick).size() === 0){
                    createChatDialog(userIdClick, usernameClick);                    
                }else{                    
                    $("div#dialog-"+userIdClick).dialog('open');
                    //alert(1);
                }
                $("span#chathandler_"+userIdClick).css("background","#b4d0ef");
                checkPlayMusic = false;
                setTimeout(function(){
                    checkPlayMusic = true;
                },1000);
            });
        }        
        function writeChatContent(userIdClick, element){
            $.ajax({
                type: 'POST',
                url: '${pageContext.servletContext.contextPath}/chat_content.htm',
                data: {
                    action: 'getContentChatGroup',
                    userIdClick: userIdClick,
                    memberId: memberId
                },
                success: function(rs) {
                    if(element.html() !== rs.trim()){
                        element.html(rs.trim());                        
                        element.scrollTop(element[0].scrollHeight);
                        playMusic();
                    }
                }
            });
        }
        function loadDialogContent(){
            var divContent = $(".chat-dialog-content");
            var userIdClick;
            for(var i=0; i<divContent.size(); i++){                
                userIdClick = parseInt(divContent.eq(i).attr("id").replace("chat-dialog-content-","")); 
                writeChatContent(userIdClick, divContent.eq(i));
            }
        }
        function getListChatCurrent(){
            $.ajax({
                type: 'POST',
                url: '${pageContext.servletContext.contextPath}/chat_content.htm',
                data: {
                    action: 'getListChatCurrent',
                    memberId: memberId
                },
                success: function(rs) {
                    listChatCurrent = rs.trim();
                }
            });
        }
        function checkPingChat(){
            getListChatCurrent();
            var split = listChatCurrent.split("-");            
            var idName;
            var userIdClick;
            var userClickName;
            for(var ci=0;ci<split.length;ci++){
                idName = split[ci];
                if(idName.trim() !== ""){
                    userIdClick = idName.split(",")[0];
                    userClickName = idName.split(",")[1];
                    if($("span#chathandler_"+userIdClick).size() === 0){
                        createBoxBottom(userIdClick, userClickName);
                        $("span#chathandler_"+userIdClick).css("background","#e77003");
                    }
                }
            }
        }
        setInterval(function() {
            loadDialogContent();
            checkPingChat();
        }, 500);        
    </c:if>
    $(document).ready(function() {
        setInterval(function() {
            $.ajax({
                type: 'POST',
                url: '${pageContext.servletContext.contextPath}/chat_content.htm',
                data: {
                    action: 'getContent'
                },
                success: function(rs) {
                    var contentLength = $("#chat-content").html().trim().length;
                    var rsLength = rs.trim().length;
                    if($("#chat-content").html() !== rs.trim() && contentLength + 3 < rsLength){
                        $("#chat-content").html(rs.trim());                        
                        $('#chat-content').scrollTop($('#chat-content')[0].scrollHeight);
                    }
                }
            });
        }, 500);
        function sendMsg(){
            $.ajax({
                type: 'POST',
                url: '${pageContext.servletContext.contextPath}/chat_content.htm',
                data: {
                    action: 'addMsg',
                    userId: '${memberLogin.id}',
                    username: '${memberLogin.fullname}',
                    msg: $("#editor input#input-root").val()
                },
                success: function(rs) {
                    $("#editor input#input-root").val("");
                }
            });
        }
        $("#sendMsg").click(function() {
            sendMsg();
        });
        $("#editor input").keyup(function(e){
            if(e.keyCode === 13){
                sendMsg();
            }
        });
        $("#emotion").click(function(){
            //$(".allicon_holder").toggle();
            $(this).next().toggle();
        });        
        $("#allicon_holder-root .icon_bar_item").click(function(){
            var inp = $(this).attr("inp");
            var val = $("#editor input#"+inp).val();            
            $("#editor input#"+inp).val(val+$(this).attr("alt"));
            $(".allicon_holder").hide();
        });
    });
</script>
<div id="chatchit">
    <h2>Khu Thảo Luận </h2>
    <div id="chat-content">
<!--        <div id="chatmsg_23032545" class="chatmsgline" rel="23032545">
            <span class="chatid id2 " rel="87113">Cần Một Cái Tên:</span> 
            <span class="chatmsg" rel="1453648947" title="Gửi lúc 22:22:27 hôm nay">Ai an thi an ai nuoi thi nuoi lam b.u.o.i sao</span>
        </div>-->
    </div>
    <c:if test="${memberLogin ne null}">
        <div id="wapper-emotion">
            <a class="icon_toolbar" id="emotion"><img src="http://rongbachkim.com/images/emoticons/1.gif"/></a>
            <div class="allicon_holder" id="allicon_holder-root">
                <c:forEach items="${hmEmotion}" var="emotion">
                    <a inp="input-root" class="icon_bar_item" alt="${emotion.key}"><img src="${emotion.value}" alt="${emotion.key}"/></a>
                </c:forEach>                
            </div>
	</div>
        <div id="editor">        
            <input id="input-root" class="inline"/>
            <button class="btnGui inline" id="sendMsg">Gửi</button>
        </div>
    </c:if>
    <c:if test="${memberLogin eq null}">
        <div id="editor" style="margin-left: 5px"> 
            Bạn cần đăng nhập để chat cùng các thành viên khác.
        </div>
    </c:if>
</div>
<div id="chat_handler_holder">
    <!--<span class="ppchathandler" id="chathandler_104733_112916">LinhUt<a href="#" class="closebutton_round">&nbsp;</a></span>-->
</div>
