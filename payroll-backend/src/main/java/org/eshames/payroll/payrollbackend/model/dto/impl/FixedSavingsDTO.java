package org.eshames.payroll.payrollbackend.model.dto.impl;

import org.eshames.payroll.payrollbackend.model.dto.SavingsDTO;

public class FixedSavingsDTO implements SavingsDTO {
    @Override
    public float calculateSavings(float savingsFactor, float grossIncome) {
        return grossIncome - savingsFactor;
    }
}
