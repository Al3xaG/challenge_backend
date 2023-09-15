package com.challenge.backend.domain.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDTO {
    private Date date;
    private String client;
    private String numberAccount;
    private String typeAccount;
    private Double initialBalance;
    private Boolean status;
    private Double amount;
    private Double availableBalance;
}
