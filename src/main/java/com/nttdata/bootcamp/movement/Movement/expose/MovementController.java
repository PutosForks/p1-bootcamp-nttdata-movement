package com.nttdata.bootcamp.movement.Movement.expose;

import com.nttdata.bootcamp.movement.Movement.model.Movement;
import com.nttdata.bootcamp.movement.Movement.bussiness.MovementService;
import com.nttdata.bootcamp.movement.Movement.model.dto.MovementProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
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

    //Delete
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Movement>> delete(@PathVariable("id") String id) {
      log.info("delete>>>>>");
      return movementService.remove(id)
              .flatMap(benefit -> Mono.just(ResponseEntity.ok(benefit)))
              .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    //Movement -> Benefit
    @GetMapping("/bemefit/{id}")
    public Flux<Movement> findByBenefitId(@PathVariable("id") String id) {
      log.info("Movement -> Benefit>>>>>");
      return movementService.findByBenefitId(id);
    }

  //Movement -> Benefit
  @GetMapping("/product/{id}")
  public Mono<ResponseEntity<Flux<MovementProductDto>>> findByProductId(@PathVariable("id") String id, @RequestParam("commission") Boolean commission) {
    log.info("Movement -> Product>>>>>");
    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(movementService.findByProductId(id, commission)));
  }

  //Create
  @PostMapping("/sale-bootcoin")
  public Mono<ResponseEntity<Mono<Movement>>> saleBootCoin(@RequestBody Movement movement){
    log.info("create sale Bootcoin>>>>>");
    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(movementService.create(movement)));
  }
}
