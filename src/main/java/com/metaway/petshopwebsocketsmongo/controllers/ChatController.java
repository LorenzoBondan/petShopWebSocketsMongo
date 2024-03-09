package com.metaway.petshopwebsocketsmongo.controllers;

import com.metaway.petshopwebsocketsmongo.entities.ComponentEvent;
import com.metaway.petshopwebsocketsmongo.events.CreateComponentEventPublisher;
import com.metaway.petshopwebsocketsmongo.repositories.ComponentEventRepository;
import com.metaway.petshopwebsocketsmongo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private ComponentEventRepository componentEventRepository;

    @Autowired
    private CreateComponentEventPublisher publisher;

    @Autowired
    private AuthService authService;

    @MessageMapping("/chat.componentClicked")
    @SendTo("/topic/public")
    public ComponentEvent componentClicked(@Payload ComponentEvent componentEvent){
        componentEvent.setMoment(LocalDateTime.now().minusHours(3)); // MongoDB stores times in UTC by default, and converts any local time representations into this form.
        componentEvent.setUsername("user"/*authService.authenticatedUsername()*/);
        publisher.publishEvent(componentEvent); // evento de salvar assíncrono
        return componentEvent;
    }
}
