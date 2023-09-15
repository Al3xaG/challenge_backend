package com.challenge.backend.service.impl;

import com.challenge.backend.domain.dto.ReportDTO;
import com.challenge.backend.repository.TransactionRepository;
import com.challenge.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final TransactionRepository transactionRepository;
    @Override
    public List<ReportDTO> getReportByDateAndClient(Date initDate, Date endDate, String clientId) {
        List<Object[]> objects = transactionRepository.findAllTransactionByDate(initDate, endDate, Long.valueOf(clientId));
        List<ReportDTO> reportDTOS = new ArrayList<>();
        objects.forEach(object -> {
            reportDTOS.add(ReportDTO.builder()
                    .date((Date)object[0])
                    .client((String) object[1])
                    .numberAccount((String) object[2])
                    .typeAccount((String) object[3])
                    .initialBalance((Double) object[5] - (Double) object[6])
                    .status((Boolean) object[4])
                    .amount((Double) object[6])
                    .availableBalance((Double) object[5])
                    .build());
        });

        return reportDTOS;
    }
}
