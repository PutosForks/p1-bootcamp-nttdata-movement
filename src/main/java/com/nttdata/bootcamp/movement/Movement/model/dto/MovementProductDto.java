package com.nttdata.bootcamp.movement.Movement.model.dto;

import com.nttdata.bootcamp.movement.Movement.model.Movement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MovementProductDto {
  private ProductDto product;
  private List<Movement> movements = new ArrayList<>();

  public void addMovement(Movement movement) {
    this.movements.add(movement);
  }
}
