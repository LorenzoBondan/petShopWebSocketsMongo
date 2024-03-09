package com.metaway.petshopwebsocketsmongo.events;

import com.metaway.petshopwebsocketsmongo.entities.ComponentEvent;
import org.springframework.context.ApplicationEvent;

public class CreateComponentEventEvent extends ApplicationEvent {

    private ComponentEvent componentEvent;

    public CreateComponentEventEvent(Object source, ComponentEvent componentEvent) {
        super(source);
        this.componentEvent = componentEvent;
    }

    public ComponentEvent getComponentEvent() {
        return componentEvent;
    }

    public void setComponentEvent(ComponentEvent componentEvent) {
        this.componentEvent = componentEvent;
    }
}
