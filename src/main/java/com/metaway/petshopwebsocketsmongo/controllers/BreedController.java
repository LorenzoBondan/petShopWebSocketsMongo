package com.metaway.petshopwebsocketsmongo.controllers;

import com.metaway.petshopwebsocketsmongo.dto.BreedDTO;
import com.metaway.petshopwebsocketsmongo.entities.Breed;
import com.metaway.petshopwebsocketsmongo.entities.ComponentEvent;
import com.metaway.petshopwebsocketsmongo.events.CreateComponentEventPublisher;
import com.metaway.petshopwebsocketsmongo.services.AuthService;
import com.metaway.petshopwebsocketsmongo.services.BreedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/breeds")
public class BreedController {

    @Autowired
    private BreedService service;

    @Autowired
    private AuthService authService;

    @Autowired
    private CreateComponentEventPublisher publisher;


    @PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<BreedDTO>> findAllPaged(Pageable pageable){
        Page<BreedDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<BreedDTO> findById(@PathVariable Long id){
        BreedDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
    @PostMapping
    //@MessageMapping("/chat.componentClicked")
    //@SendTo("/topic/public")
    public ResponseEntity<BreedDTO> insert(@Valid @RequestBody BreedDTO dto/*, @Payload ComponentEvent component*/){
        /*
        ComponentEvent componentEvent = new ComponentEvent();
        componentEvent.setMoment(LocalDateTime.now().minusHours(3)); // MongoDB stores times in UTC by default, and converts any local time representations into this form.
        componentEvent.setUsername(authService.authenticatedUsername());
        componentEvent.setMessage(component.getMessage()); // <<--
        publisher.publishEvent(componentEvent);
        */

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BreedDTO> update(@PathVariable Long id, @Valid @RequestBody BreedDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BreedDTO> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
