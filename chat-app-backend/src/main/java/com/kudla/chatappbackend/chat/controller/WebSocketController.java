package com.kudla.chatappbackend.chat.controller;

import com.kudla.chatappbackend.chat.model.dto.WebSocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chat")
    public WebSocketMessage sendMessage(@Payload WebSocketMessage webSocketChatMessage) {
        return webSocketChatMessage;
    }
}
