package com.pismo.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotBlank(message = "Document number must not be empty")
    @Size(min = 11, max = 11, message = "Document number must be exactly 11 digits")
    @Column(nullable = false, unique = true)
    private String documentNumber;
}
