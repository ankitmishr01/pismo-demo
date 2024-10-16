package com.pismo.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionModel {

    private Long transactionId;

    private Long account;

    private Long operationType;

    private Double amount;

    private LocalDateTime timestamp;
}

