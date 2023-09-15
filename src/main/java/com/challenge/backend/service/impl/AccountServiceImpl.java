package com.challenge.backend.service.impl;

import com.challenge.backend.domain.dto.AccountDTO;
import com.challenge.backend.domain.dto.ClientDTO;
import com.challenge.backend.domain.entities.Account;
import com.challenge.backend.domain.entities.Client;
import com.challenge.backend.exceptions.NotFoundException;
import com.challenge.backend.repository.AccountRepository;
import com.challenge.backend.service.AccountService;
import com.challenge.backend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = accountRepository.save(convertDTOtoEntity(accountDTO));
        return convertEntityToDTO(account);
    }

    @Override
    public AccountDTO getAccount(String id) {
        Optional<Account> account = accountRepository.findById(Long.valueOf(id));
        if(!account.isEmpty()) {
            Account account1= account.get();
            account1.setClient(Mapper.modelMapper().map(account1.getClient(), Client.class));
            return convertEntityToDTO(account1);
        }
        return null;
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        Optional<Account> optional = accountRepository.findById(Long.valueOf(accountDTO.getId()));
        if (!optional.isPresent()){
            throw  new NotFoundException("Cuenta no existe");
        }
        Account account = accountRepository.save(convertDTOtoEntity(accountDTO));
        return convertEntityToDTO(account);
    }

    @Override
    public Boolean deleteAccount(String id) {
        Optional<Account> optional = accountRepository.findById(Long.valueOf(id));
        if (!optional.isPresent()){
            throw  new NotFoundException("Cuenta no existe");
        }
        accountRepository.deleteById(Long.valueOf(id));
        return true;
    }

    private Account convertDTOtoEntity(AccountDTO accountDTO) {
        Client client = Mapper.modelMapper().map(accountDTO.getClient(), Client.class);
        Account account = Mapper.modelMapper().map(accountDTO, Account.class);
        account.setClient(client);
        return account;
    }

    private AccountDTO convertEntityToDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClient(Mapper.modelMapper().map(account.getClient(), ClientDTO.class));
        return Mapper.modelMapper().map(account, AccountDTO.class);
    }
}
