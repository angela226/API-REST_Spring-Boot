package com.spring.boot.back.challenge.services;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PercentageService {

    private final Cache<String, Double> cache;

    public PercentageService() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    public double getPercentage() {
        Double cached = cache.getIfPresent("percentage");
        if (cached != null) {
            return cached;
        }
        try {
            double externalPercentage = 10.0;
            cache.put("percentage", externalPercentage);
            return externalPercentage;
        } catch (Exception e) {
            if (cached != null) {
                return cached;
            }
            throw new RuntimeException("Error obteniendo porcentaje y no hay caché disponible");
        }
    }
}