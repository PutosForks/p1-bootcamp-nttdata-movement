package com.nttdata.bootcamp.movement.Movement.expose;

import com.nttdata.bootcamp.movement.Movement.model.Movement;
import com.nttdata.bootcamp.movement.Movement.bussiness.MovementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("api/v1/movement")
public class MovementController {

    @Autowired
    private MovementService movementService;

    //list
    @GetMapping()
    public Mono<ResponseEntity<Flux<Movement>>> findAll() {
        log.info("findAll>>>>>");
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movementService.findAll()));
    }

    //Create
    @PostMapping()
    public Mono<ResponseEntity<Mono<Movement>>> create(@RequestBody Movement movement){
        log.info("create>>>>>");
        System.out.println(movement.getProductId().concat(" ").concat(movement.getCustomerId()));
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movementService.create(movement)));
    }

    //Detail
    @GetMapping("/{id}")
    public Mono<Movement> show(@PathVariable("id") String id) {
        log.info("byId>>>>>");
        System.out.println(id);
        return movementService.findById(id);
    }

    //Edit
    @PutMapping("")
    public Mono<ResponseEntity<Movement>> update(@RequestBody Movement movement) {
        log.info("update>>>>>");
        return movementService.update(movement)
                .flatMap(movementUpdate -> Mono.just(ResponseEntity.ok(movementUpdate)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
