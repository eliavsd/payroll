package org.eshames.payroll.payrollbackend.model.savings;

import org.springframework.stereotype.Component;

@Component("percentage_savings")
public class PercentageSavings implements Savings {

    @Override
    public float calculateSavings(float savingsFactor, float grossIncome) {
        return grossIncome * savingsFactor;
    }
}
