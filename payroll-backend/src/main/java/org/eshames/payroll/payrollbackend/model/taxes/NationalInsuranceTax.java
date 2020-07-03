package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("insurance_tax")
public final class NationalInsuranceTax implements Tax {

    // Tax brackets - national insurance
    private static final List<Integer> INSURANCE_BRACKETS = Arrays.asList(0, 6331, 44020);
    private static final List<Float> INSURANCE_RATES = Arrays.asList(0.035f, 0.12f, 0f);
    @Override
    public float calculateTax(float taxableIncome, TaxDTO taxDTO) {
        List<Integer> relevantBrackets = INSURANCE_BRACKETS.stream()
                .filter(insuranceBracket -> insuranceBracket <= taxableIncome)
                .collect(Collectors.toList());
        return 0;
    }
}
