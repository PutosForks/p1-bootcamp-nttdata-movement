package com.nttdata.bootcamp.movement.Movement.bussiness.impl;

import com.nttdata.bootcamp.movement.Movement.bussiness.MovementService;
import com.nttdata.bootcamp.movement.Movement.bussiness.helper.MovementHelper;
import com.nttdata.bootcamp.movement.Movement.bussiness.helper.WebClientHelper;
import com.nttdata.bootcamp.movement.Movement.model.Movement;
import com.nttdata.bootcamp.movement.Movement.model.dto.BenefitDto;
import com.nttdata.bootcamp.movement.Movement.model.dto.MovementProductDto;
import com.nttdata.bootcamp.movement.Movement.model.dto.ProductDto;
import com.nttdata.bootcamp.movement.Movement.repository.MovementRepository;
import com.nttdata.bootcamp.movement.Movement.utils.ConstantMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private WebClientHelper webClientHelper;
    @Autowired
    private MovementHelper movementHelper;

    @Override
    public Mono<Movement> create(Movement movement) {
        Mono<BenefitDto> benefit = webClientHelper.findBenefit(movement.getBenefitId());
        Mono<Movement> objMovement =  benefit.flatMap(objBenefit->movementHelper.createObjectMovement(movement, objBenefit));
        return objMovement.flatMap(m->movementRepository.save(m));
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

    @Override
    public Mono<Movement> remove(String movementId) {
      return movementRepository
              .findById(movementId)
              .flatMap(p -> {
                p.setStatus(ConstantMovement.REMOVE.name());
                return movementRepository.save(p);
              });
    }
  @Override
  public Flux<Movement> findByBenefitId(String benefitId) {
    return movementRepository.findByBenefitId(benefitId);
  }

  @Override
  public Flux<MovementProductDto> findByProductId (String productId, Boolean commission){
    Mono<ProductDto> product = webClientHelper.finddProduct(productId);
    Flux<BenefitDto> benefit = webClientHelper.findBenefitByProduct(productId);
    Flux<Movement> movementslux =  benefit.flatMap(objBenefit->{
      return findByBenefitId(objBenefit.getId());
    });
    Mono<List<Movement>> objMovementList = movementslux.collectList();
    Mono<MovementProductDto> objMpD =  product.flatMap(objProduct->{
        Mono<MovementProductDto> prueba =  objMovementList.map(objMovement->{
          return movementHelper.createMovementProductDto(objProduct, objMovement);
        });
      return Mono.just(prueba);
    });

    return Flux.from(objMpD);

  }
}