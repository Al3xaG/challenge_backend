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
public class ClientDTO extends PersonDTO {
    private Long clientId;
    private String password;
    private Boolean status;
    private List<AccountDTO> accounts;
}
