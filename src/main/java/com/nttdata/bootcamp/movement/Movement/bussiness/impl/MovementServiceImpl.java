package com.nttdata.bootcamp.movement.Movement.bussiness.impl;

import com.nttdata.bootcamp.movement.Movement.bussiness.MovementService;
import com.nttdata.bootcamp.movement.Movement.model.Movement;
import com.nttdata.bootcamp.movement.Movement.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public Mono<Movement> create(Movement movement) {
        movement.setCreated(new Date());
        movement.setTotalAmount(movement.getAmount());
        return movementRepository.save(movement);
    }

    @Override
    public Flux<Movement> findAll() {
        return movementRepository.findAll();
    }

    @Override
    public Mono<Movement> findById(String movementId) {
        return movementRepository.findById(movementId);
    }

    @Override
    public Mono<Movement> update(Movement movement) {
        return movementRepository.findById(movement.getId())
                .flatMap(movementDB -> {
                    return movementRepository.save(movement);
                })
                .switchIfEmpty(Mono.empty());
    }

}