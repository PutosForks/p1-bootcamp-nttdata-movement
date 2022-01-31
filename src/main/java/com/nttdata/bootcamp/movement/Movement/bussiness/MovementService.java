package com.nttdata.bootcamp.movement.Movement.bussiness;


import com.nttdata.bootcamp.movement.Movement.model.Movement;
import com.nttdata.bootcamp.movement.Movement.model.dto.MovementProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {
    Mono<Movement> create(Movement movement);
    Flux<Movement> findAll();
    Mono<Movement> findById(String movementId);
    Mono<Movement> update(Movement movement);
    Mono<Movement> remove(String movementId);
    Flux<Movement> findByBenefitId(String benefitId);
    Flux<MovementProductDto> findByProductId (String productId , Boolean commission);

}
