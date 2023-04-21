package com.quangduong.SE114backend.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/message/{id}")
    @SendTo("/chatroom/{id}")
    public Message sendMessage(@Payload Message message) {
        return message;
    }

    record Message(String message, String email, String avatar) {
    }

}
