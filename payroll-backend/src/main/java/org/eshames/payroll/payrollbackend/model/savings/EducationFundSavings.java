package org.eshames.payroll.payrollbackend.model.savings;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.SavingsDTO;
import org.springframework.stereotype.Component;

@Component("edufund_savings")
public class EducationFundSavings implements Savings {
    @Override
    public double calculateSavings(double grossIncome, SavingsDTO savingsDTO) {
        return Math.min(grossIncome * savingsDTO.getSavingsFactor(),
                TaxConstants.EDU_FUND_MAX_EXEMPTION * savingsDTO.getSavingsFactor());
    }

    @Override
    public double getExemptionFactor() {
        return 1;
    }
}
