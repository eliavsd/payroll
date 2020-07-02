package org.eshames.payroll.payrollbackend.model.taxes;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("insurance_tax")
public final class NationalInsuranceTax implements Tax {

    // Tax brackets - national insurance
    private static final List<Integer> INSURANCE_BRACKETS = Arrays.asList(0, 6331, 44020);
    private static final List<Float> INSURANCE_RATES = Arrays.asList(0.035f, 0.12f, 0f);
    @Override
    public float calculateTax(float taxableIncome) {
        return 0;
    }
}
