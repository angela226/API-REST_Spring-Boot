package com.spring.boot.back.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationRequest {

    private double num1;
    private double num2;


    @Override
    public String toString() {
        return "CalculationRequest{num1=" + num1 + ", num2=" + num2 + '}';
    }
}

