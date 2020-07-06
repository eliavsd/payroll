package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;

import java.util.List;

public abstract class Tax {
    protected List<Double> brackets;
    protected List<Double> rates;
    
    
    public double calculateTax(double taxableIncome, TaxDTO taxDTO) {
        double totalTax = 0;
        for (int i = 0; i < brackets.size() - 1; i++) {
            double lowerLimit = brackets.get(i);
            double upperLimit = brackets.get(i + 1) - 0.01;
            double incomeInBracket = (taxableIncome >= upperLimit)
                    ? upperLimit - lowerLimit
                    : Math.max(0, taxableIncome - lowerLimit);
            if (incomeInBracket == 0)
                break;
            double taxForBracket = incomeInBracket * rates.get(i);
            totalTax += taxForBracket;
        }
        return (double) Math.round(totalTax * 100) / 100;
    }
}
