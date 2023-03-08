package com.likelion.ChatWebSocket.dto;


import com.likelion.ChatWebSocket.enumf.MessageType;
import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    public MessageType type;

    // constructors, getters and setters
}