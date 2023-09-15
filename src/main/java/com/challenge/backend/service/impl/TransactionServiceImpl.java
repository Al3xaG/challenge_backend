package com.challenge.backend.service.impl;

import com.challenge.backend.domain.dto.TransactionDTO;
import com.challenge.backend.domain.entities.Account;
import com.challenge.backend.domain.entities.Transaction;
import com.challenge.backend.exceptions.NotFoundException;
import com.challenge.backend.exceptions.TransactionException;
import com.challenge.backend.repository.AccountRepository;
import com.challenge.backend.repository.TransactionRepository;
import com.challenge.backend.service.TransactionService;
import com.challenge.backend.util.Constant;
import com.challenge.backend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Double balance = validateTransactionType(transactionDTO);
        if( balance == null){
            throw new TransactionException("Cuenta del cliente no existe");
        }
        transactionDTO.setBalance(balance);
        transactionDTO.setDate(new Date());
        Transaction transaction = transactionRepository.save(convertDTOtoEntity(transactionDTO));
        return convertEntityToDTO(transaction);
    }

    @Override
    public TransactionDTO getTransaction(String id) {
        Optional<Transaction> optional = transactionRepository.findById(Long.valueOf(id));
        if(!optional.isPresent()){
            return null;
        }
        return convertEntityToDTO(optional.get());
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        Optional<Transaction> optional = transactionRepository.findById(Long.valueOf(transactionDTO.getId()));
        if (!optional.isPresent()){
            throw  new NotFoundException("Transaction no existe");
        }
        Transaction transaction = transactionRepository.save(convertDTOtoEntity(transactionDTO));
        return convertEntityToDTO(transaction);
    }

    @Override
    public Boolean deleteTransaction(String id) {
        Optional<Transaction> optional = transactionRepository.findById(Long.valueOf(id));
        if (!optional.isPresent()){
            throw  new NotFoundException("Transaction no existe");
        }
        transactionRepository.deleteById(Long.valueOf(id));
        return true;
    }

    private Double validateTransactionType(TransactionDTO transactionDTO){
        Account account = accountRepository.findById(transactionDTO.getAccount().getId()).orElse(new Account());
        Double balance = Double.parseDouble("0.0");
        if(account.getId() != null) {
            switch (transactionDTO.getTransactionType()) {
                case "DEPOSIT":
                    balance = account.getInitialBalance() + transactionDTO.getAmount();
                    account.setInitialBalance(balance);
                    break;
                case "DEBIT":
                    if(account.getInitialBalance().equals(0.0) || transactionDTO.getAmount() > account.getInitialBalance()){
                        throw new TransactionException("Saldo no disponible");
                    }

                    if(!validateDailyAmount(transactionDTO)) {
                        throw new TransactionException("Limite diario superado");
                    }
                    balance = account.getInitialBalance() - transactionDTO.getAmount();
                    account.setInitialBalance(balance);
                    break;
            }
            accountRepository.save(account);
            return balance;
        }
        return null;
    }

    private Boolean validateDailyAmount(TransactionDTO transactionDTO){
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = dateNow.format(formatter);
        Double dailyAmount = transactionRepository.findAllTransactionByTransactionType(dateString, transactionDTO.getTransactionType(), transactionDTO.getAccount().getId());
        if(dailyAmount > Constant.dailyAmount || (dailyAmount + transactionDTO.getAmount()) > Constant.dailyAmount){
            return false;
        }
        return true;
    }

    private Transaction convertDTOtoEntity(TransactionDTO transactionDTO){
        return Mapper.modelMapper().map(transactionDTO, Transaction.class);
    }

    private TransactionDTO convertEntityToDTO(Transaction transaction){
        return Mapper.modelMapper().map(transaction, TransactionDTO.class);
    }
}
