package com.challenge.backend.service;

import com.challenge.backend.domain.dto.AccountDTO;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccount(String id);
    AccountDTO updateAccount(AccountDTO accountDTO);
    Boolean deleteAccount(String id);
}
