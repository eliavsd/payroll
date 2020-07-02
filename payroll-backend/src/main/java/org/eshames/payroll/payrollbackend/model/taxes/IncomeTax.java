package org.eshames.payroll.payrollbackend.model.taxes;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("income_tax")
public final class IncomeTax implements Tax {

    // Tax brackets - income tax
    private static final List<Integer> INCOME_BRACKETS = Arrays.asList(0, 6330, 9080, 14580, 20260, 42160, 54300);
    private static final List<Float> INCOME_RATES = Arrays.asList(0.1f, 0.14f, 0.2f, 0.31f, 0.35f, 0.47f, 0.5f);
    @Override
    public float calculateTax(float taxableIncome) {
        return 0;
    }
}
