package com.challenge.backend.service;

import com.challenge.backend.domain.dto.TransactionDTO;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    TransactionDTO getTransaction(String id);
    TransactionDTO updateTransaction(TransactionDTO transactionDTO);
    Boolean deleteTransaction(String id);
}
