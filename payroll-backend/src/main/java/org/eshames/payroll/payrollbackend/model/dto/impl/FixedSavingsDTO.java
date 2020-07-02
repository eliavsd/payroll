package org.eshames.payroll.payrollbackend.model.dto.impl;

import org.eshames.payroll.payrollbackend.model.dto.SavingsDTO;
import org.springframework.stereotype.Component;

@Component("savings_fixed")
public class FixedSavingsDTO implements SavingsDTO {
    @Override
    public float calculateSavings(float savingsFactor, float grossIncome) {
        return grossIncome - savingsFactor;
    }
}
