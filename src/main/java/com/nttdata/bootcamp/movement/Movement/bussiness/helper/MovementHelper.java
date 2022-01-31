package com.nttdata.bootcamp.movement.Movement.bussiness.helper;

import com.nttdata.bootcamp.movement.Movement.model.Movement;
import com.nttdata.bootcamp.movement.Movement.model.dto.BenefitDto;
import com.nttdata.bootcamp.movement.Movement.model.dto.MovementProductDto;
import com.nttdata.bootcamp.movement.Movement.model.dto.ProductDto;
import com.nttdata.bootcamp.movement.Movement.utils.ConstantBenefit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovementHelper {

  @Autowired
  private WebClientHelper webClientHelper;

  public Mono<Movement> createObjectMovement(Movement movement, BenefitDto benefitDto){
    Mono<Movement> objMovement= Mono.just(new Movement());
    movement.setCreatedAt(LocalDate.now());
    movement.setTotalAmount(movement.getAmount());
    if(benefitDto!=null &&  benefitDto.getActive()){
      if(benefitDto.getProductType().equals(ConstantBenefit.PASSIVES.name())){
        objMovement = availablePassive(movement, benefitDto);
      }
      if(benefitDto.getProductType().equals(ConstantBenefit.ASSETS.name())){
        objMovement = availableAssets(movement, benefitDto);
      }
    }
    return objMovement;
  }

  public Mono<Movement> availablePassive(Movement movement, BenefitDto benefitDto){
    Mono<Movement> objMovement = Mono.empty();
    if((benefitDto.getTotalAmount()!=null && (benefitDto.getTotalAmount()+movement.getAmount()>=0))){
      if((benefitDto.getRestMovements()==null || benefitDto.getRestMovements()>0) &&
              (benefitDto.getDateAction()== null || (benefitDto.getDateAction()!=null && benefitDto.getDateAction().getMonth()==LocalDate.now().getMonth()))) {
        objMovement = Mono.just(movement);
          benefitDto.setTotalAmount(benefitDto.getTotalAmount()+movement.getAmount());
      }
      if(benefitDto.getRestMovements()!=null){
        if(benefitDto.getRestMovements()>0) {
          benefitDto.setRestMovements(benefitDto.getRestMovements() - 1);
        }
      }
      webClientHelper.updateBenefit(benefitDto).subscribe();
    }
    return objMovement;
  }

  public Mono<Movement> availableAssets (Movement movement, BenefitDto benefitDto){
    Mono<Movement> objMovement = Mono.empty();
    if(benefitDto.getRestCredit()!=null && (benefitDto.getRestCredit()+movement.getAmount()>=0) &&
            (benefitDto.getRestCredit()+ movement.getAmount()<= benefitDto.getTotalCredit())){
      objMovement = Mono.just(movement);
      benefitDto.setRestCredit(benefitDto.getRestCredit()+ movement.getAmount());
      webClientHelper.updateBenefit(benefitDto).subscribe();
    }
    return objMovement;
  }

  public Mono<MovementProductDto> createMovementProductDto(ProductDto productDto, List<Movement> objMovement){

    Mono<MovementProductDto> objMovementProduct= Mono.just(new MovementProductDto());

    return objMovementProduct.flatMap(object->{
      object.setProduct(productDto);
      objMovement.stream().forEach(element->{
        System.out.println(element.getId());
        object.addMovement(element);
      });
      return Mono.just(object);
    });

  }

}
