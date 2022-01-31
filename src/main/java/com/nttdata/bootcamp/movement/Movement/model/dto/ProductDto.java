package com.nttdata.bootcamp.movement.Movement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ProductDto {

  private String id;
  private String name;
  private String createdBy;
  private String productType;
  private Float commission;
  private Integer maxMovements;
  private Integer dateAction;
  private Boolean active;

}
