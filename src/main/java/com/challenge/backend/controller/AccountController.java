package com.challenge.backend.controller;

import com.challenge.backend.domain.dto.AccountDTO;
import com.challenge.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") String id){
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PutMapping
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(accountService.updateAccount(accountDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> updateCliente(@PathVariable String id){
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
}
