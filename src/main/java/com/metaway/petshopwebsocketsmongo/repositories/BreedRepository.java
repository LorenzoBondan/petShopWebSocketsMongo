package com.metaway.petshopwebsocketsmongo.repositories;

import com.metaway.petshopwebsocketsmongo.entities.Breed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BreedRepository extends MongoRepository<Breed, Long> {
}
