package com.challenge.backend.service;

import com.challenge.backend.domain.dto.ReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportService {
    List<ReportDTO> getReportByDateAndClient(Date initDate, Date endDate, String clientId);
}
