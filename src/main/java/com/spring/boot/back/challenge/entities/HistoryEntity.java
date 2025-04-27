package com.spring.boot.back.challenge.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endpoint;
    private String parameters;
    private String response;
    private String error;
    private LocalDateTime timestamp;

    public HistoryEntity(String endpoint, String parameters, String response, String error, LocalDateTime timestamp) {
        this.endpoint = endpoint;
        this.parameters = parameters;
        this.response = response;
        this.error = error;
        this.timestamp = timestamp;
    }
}
