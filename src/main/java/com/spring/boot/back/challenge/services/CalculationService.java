package com.spring.boot.back.challenge.services;

import com.spring.boot.back.challenge.entities.HistoryEntity;
import com.spring.boot.back.challenge.model.CalculationRequest;
import com.spring.boot.back.challenge.model.CalculationResponse;
import com.spring.boot.back.challenge.model.HistoryRecord;
import com.spring.boot.back.challenge.repositories.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CalculationService {

    private final PercentageService percentageService;
    private final HistoryService historyService;
    private final HistoryRepository historyRepository;

    public CalculationResponse calculateSumWithPercentage(CalculationRequest request) {
        try {
            double percentage = percentageService.getPercentage();
            double result = (request.getNum1() + request.getNum2()) * (1 + percentage/100);
            historyService.saveHistoryAsync("/api/calculate", request.toString(), String.valueOf(result), null);
            return new CalculationResponse(result);
        } catch (Exception e) {
            historyService.saveHistoryAsync("/api/calculate", request.toString(), null, e.getMessage());
            throw e;
        }
    }

    public Page<HistoryRecord> getAllHistory(Pageable pageable) {
        return historyRepository.findAll(pageable).map(HistoryRecord::fromEntity);
    }
}