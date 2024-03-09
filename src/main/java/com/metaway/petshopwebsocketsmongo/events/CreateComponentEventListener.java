package com.metaway.petshopwebsocketsmongo.events;

import com.metaway.petshopwebsocketsmongo.entities.ComponentEvent;
import com.metaway.petshopwebsocketsmongo.repositories.ComponentEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreateComponentEventListener {

    @Autowired
    private ComponentEventRepository repository;

    @EventListener
    public void handleComponentEventRegister(CreateComponentEventEvent event){
        ComponentEvent componentEvent = event.getComponentEvent();
        repository.save(componentEvent);
    }
}
