package com.example.aismschatbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class WebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private ChatService chatService = new ChatService();
    private long startTime;

    public WebSocketHandler() throws ParserConfigurationException {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, TransformerException {
        String msg = message.getPayload();
        System.out.println(msg);
        if(msg.equals("START")) {
            startTime = System.currentTimeMillis();
            chatService.insertParticipantStr(msg);
            String botStr = chatService.chatSession.multisentenceRespond(msg);
            chatService.insertBotStr(botStr);
            session.sendMessage(new TextMessage(botStr));
        }
        else if(msg.equals("END")) chatService.dumpChatSession(startTime, System.currentTimeMillis());
        else {
            chatService.insertParticipantStr(msg);
            String botStr = chatService.chatSession.multisentenceRespond(msg);
            chatService.insertBotStr(botStr);
            session.sendMessage(new TextMessage(botStr));
        }
    }
}
