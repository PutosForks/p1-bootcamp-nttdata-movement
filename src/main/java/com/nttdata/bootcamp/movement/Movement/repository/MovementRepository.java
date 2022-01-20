package com.nttdata.bootcamp.movement.Movement.repository;

import com.nttdata.bootcamp.movement.Movement.model.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {
}
