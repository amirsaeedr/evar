package ir.iust.computer.ood.evar.controller;


import ir.iust.computer.ood.evar.model.Chat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatContoroller {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Chat register(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chat.getSender());
        return chat;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Chat sendMessage(@Payload Chat chat) {
        return chat;
    }
}
