package com.challenge.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private ClientDTO client;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String status;
    private List<TransactionDTO> transactions;
}
