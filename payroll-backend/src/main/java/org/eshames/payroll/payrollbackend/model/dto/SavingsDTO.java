package org.eshames.payroll.payrollbackend.model.dto;

public interface SavingsDTO {
    float calculateSavings(float savingsFactor, float grossIncome);
}
