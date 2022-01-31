package com.nttdata.bootcamp.movement.Movement.bussiness.helper;

import com.nttdata.bootcamp.movement.Movement.model.dto.BenefitDto;
import com.nttdata.bootcamp.movement.Movement.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebClientHelper {

  @Autowired
  private WebClient webClientBenefit;

  @Autowired
  private WebClient webClientProduct;

  public Mono<BenefitDto> findBenefit(String id) {
    return webClientBenefit.get()
            .uri(uriBuilder -> uriBuilder.pathSegment(id).build())
            .retrieve()
            .bodyToMono(BenefitDto.class);
  }

  public Flux<BenefitDto> findBenefitByProduct(String id) {

    return webClientBenefit.get()
            .uri("/product/".concat(id))
            .retrieve()
            .bodyToFlux(BenefitDto.class);
  }

  public Mono<BenefitDto> updateBenefit(BenefitDto benefitDto) {
    return webClientBenefit.put()
            .body(Mono.just(benefitDto), BenefitDto.class)
            .retrieve()
            .bodyToMono(BenefitDto.class);
  }

  public Mono<ProductDto> finddProduct(String id) {
    return webClientProduct.get()
            .uri(uriBuilder -> uriBuilder.pathSegment(id).build())
            .retrieve()
            .bodyToMono(ProductDto.class);
  }

}
