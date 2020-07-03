package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component("income_tax")
public class IncomeTax extends Tax {

    @PostConstruct
    private void init() {
        // rate[i] applies to all income within brackets[i] and brackets[i+1]
        brackets = Arrays.asList(0.0, 6330.0, 9080.0, 14580.0, 20260.0, 42160.0, 54300.0, Double.MAX_VALUE);
        rates = Arrays.asList(0.1, 0.14, 0.2, 0.31, 0.35, 0.47, 0.5);
    }

    @Override
    public double calculateTax(int taxableIncome, TaxDTO taxDTO) {
        double taxBeforeDeduction = super.calculateTax(taxableIncome, taxDTO);
        return Math.ceil(taxBeforeDeduction - (taxDTO.getTaxPoints() * TaxConstants.TAX_POINT_VALUE));
    }
}
