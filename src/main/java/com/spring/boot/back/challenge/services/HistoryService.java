package com.spring.boot.back.challenge.services;

import com.spring.boot.back.challenge.entities.HistoryEntity;
import com.spring.boot.back.challenge.model.HistoryRecord;
import com.spring.boot.back.challenge.repositories.HistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveHistoryAsync(String endpoint, String params, String response, String error) {
        executor.execute(() -> {
            HistoryEntity entity = new HistoryEntity(endpoint, params, response, error, LocalDateTime.now());
            historyRepository.save(entity);
        });
    }

    public Page<HistoryRecord> getAllHistory(Pageable pageable) {
        return historyRepository.findAll(pageable).map(HistoryRecord::fromEntity);
    }
}
