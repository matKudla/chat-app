package com.kudla.chatappbackend.chat.listener;

import com.kudla.chatappbackend.chat.model.dto.WebSocketMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
@AllArgsConstructor
public class WebSocketChatEventListener {

    private static final String SIMP_USER = "simpUser";
    private static final String LEAVE = "Leave";
    private static final String CONNECT = "Connect";
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        System.out.println("Received a new web socket connection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        handleWebSocketEvent(CONNECT, headerAccessor);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        System.out.println("Disconnected" + event.toString());
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        handleWebSocketEvent(LEAVE, headerAccessor);
    }

    private void handleWebSocketEvent(String eventType, StompHeaderAccessor headerAccessor) {

        String username = ((UsernamePasswordAuthenticationToken) Objects.requireNonNull(headerAccessor.getHeader(SIMP_USER))).getName();
        if (username != null) {
            WebSocketMessage chatMessage = new WebSocketMessage();
            chatMessage.setType(eventType);
            chatMessage.setSender(username);
            messagingTemplate.convertAndSend("/topic/chat", chatMessage);
        }
    }
}
