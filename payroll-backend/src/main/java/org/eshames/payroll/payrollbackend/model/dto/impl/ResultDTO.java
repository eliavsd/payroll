package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ResultDTO {
    private double totalSavingsEligibleIncome;
    private double totalGrossIncome;
    private double totalTaxableIncome;
    private Map<String, Double> taxResults;
    private Map<String, Double> savingsResults;
}
