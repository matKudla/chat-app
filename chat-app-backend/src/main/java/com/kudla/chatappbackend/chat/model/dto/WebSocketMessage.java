package com.kudla.chatappbackend.chat.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class WebSocketMessage {
    private String type;
    private String content;
    private String sender;
}
