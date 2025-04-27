package com.spring.boot.back.challenge.model;

import com.spring.boot.back.challenge.entities.HistoryEntity;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRecord {

    private String endpoint;
    private String parameters;
    private String response;
    private String error;
    private LocalDateTime timestamp;

    public static HistoryRecord fromEntity(HistoryEntity historyEntity) {
        return new HistoryRecord(
                historyEntity.getEndpoint(),
                historyEntity.getParameters(),
                historyEntity.getResponse(),
                historyEntity.getError(),
                historyEntity.getTimestamp()
        );
    }
}
