package com.nttdata.bootcamp.movement.Movement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
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
    private String detail;
    private Float amount;
    @Field(name = "commission_amount")
    private Float commissionAmount;
    @Field(name = "total_amount")
    private Float totalAmount;
    @Field(name = "benefit_id")
    private String benefitId;
    @Field(name = "created_at")
    private LocalDate createdAt;
    @Field(name = "payment_method")
    private String paymentMethod;
    @Field(name = "transaction_type")
    private String transactionType;
    @Field(name = "total_boot_coin")
    private Float totalBootCoin;
    private String status;

}