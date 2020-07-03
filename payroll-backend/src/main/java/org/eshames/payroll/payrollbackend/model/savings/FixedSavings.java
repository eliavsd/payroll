package org.eshames.payroll.payrollbackend.model.savings;

import org.springframework.stereotype.Component;

@Component("fixed_savings")
public class FixedSavings implements Savings {
    @Override
    public float calculateSavings(float savingsFactor, float grossIncome) {
        return grossIncome - savingsFactor;
    }
}
