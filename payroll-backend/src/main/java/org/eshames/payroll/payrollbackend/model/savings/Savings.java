package org.eshames.payroll.payrollbackend.model.savings;

import org.eshames.payroll.payrollbackend.model.dto.impl.SavingsDTO;

public interface Savings {
    double calculateSavings(double grossIncome, SavingsDTO savingsDTO);

    double getExemptionFactor();
}
