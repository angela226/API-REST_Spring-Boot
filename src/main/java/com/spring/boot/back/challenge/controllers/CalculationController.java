package com.spring.boot.back.challenge.controllers;

import com.spring.boot.back.challenge.model.CalculationRequest;
import com.spring.boot.back.challenge.model.CalculationResponse;
import com.spring.boot.back.challenge.model.HistoryRecord;
import com.spring.boot.back.challenge.services.CalculationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculate")
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        CalculationResponse response = calculationService.calculateSumWithPercentage(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<HistoryRecord>> getHistory(Pageable pageable) {
        return ResponseEntity.ok(calculationService.getAllHistory(pageable));
    }
}
