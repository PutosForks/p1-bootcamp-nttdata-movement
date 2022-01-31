package com.nttdata.bootcamp.movement.Movement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class BenefitDto {
  private String id;
  private String customerId;
  private String productId;
  private String productType;
  private Integer restMovements;
  private Float totalAmount;
  private Float commission;
  private Float totalCommission;
  private LocalDate dateAction;
  private Float totalCredit;
  private Float restCredit;
  private String createdBy;
  private LocalDate createdAt;
  private Boolean active;

}
