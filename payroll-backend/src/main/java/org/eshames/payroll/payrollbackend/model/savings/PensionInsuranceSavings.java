package org.eshames.payroll.payrollbackend.model.savings;

import org.eshames.payroll.payrollbackend.model.dto.impl.SavingsDTO;
import org.springframework.stereotype.Component;

@Component("pension_savings")
public class PensionInsuranceSavings implements Savings {

    @Override
    public double calculateSavings(double grossIncome, SavingsDTO savingsDTO) {
        return grossIncome * savingsDTO.getSavingsFactor();
    }

    @Override
    public double getExemptionFactor() {
        return 0.35;
    }

    @Override
    public double getMaxExemption() {
        return 616;
    }
}
