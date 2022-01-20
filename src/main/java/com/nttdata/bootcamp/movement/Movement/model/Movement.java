package com.nttdata.bootcamp.movement.Movement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Document(collection = "movement")
public class Movement {

    @Id
    private String id = UUID.randomUUID().toString();
    private Date created;
    private Float amount;
    private Float commissionAmount;
    private Float totalAmount;
    private String productId;
    private String customerId;
}