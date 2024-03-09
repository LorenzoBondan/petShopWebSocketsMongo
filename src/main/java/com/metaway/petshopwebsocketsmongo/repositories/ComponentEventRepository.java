package com.metaway.petshopwebsocketsmongo.repositories;

import com.metaway.petshopwebsocketsmongo.entities.ComponentEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponentEventRepository extends MongoRepository<ComponentEvent, String> {
}
