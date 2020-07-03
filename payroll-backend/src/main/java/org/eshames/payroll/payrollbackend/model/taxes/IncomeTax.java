package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("income_tax")
public class IncomeTax implements Tax {

    // Tax brackets - income tax
    private static final List<Double> INCOME_BRACKETS = Arrays.asList(0.0, 6330.0, 9080.0, 14580.0,
            20260.0, 42160.0, 54300.0, Double.MAX_VALUE);
    private static final List<Double> INCOME_RATES = Arrays.asList(0.1, 0.14, 0.2, 0.31, 0.35, 0.47, 0.5);
    @Override
    public double calculateTax(int taxableIncome, TaxDTO taxDTO) {
        double totalTax = 0;
        for (int i = 0; i < INCOME_BRACKETS.size() - 1; i++) {
            double lowerLimit = INCOME_BRACKETS.get(i);
            double upperLimit = INCOME_BRACKETS.get(i + 1) - 0.01;
            double incomeInBracket = (taxableIncome >= upperLimit)
                    ? upperLimit - lowerLimit
                    : Math.max(0, taxableIncome - lowerLimit);
            if (incomeInBracket == 0)
                break;
            double taxForBracket = incomeInBracket * INCOME_RATES.get(i);
            totalTax += taxForBracket;
        }
        double taxAfterDeductions = totalTax - (taxDTO.getTaxPoints() * TaxConstants.TAX_POINT_VALUE);
        return Math.ceil(taxAfterDeductions);
    }
}
