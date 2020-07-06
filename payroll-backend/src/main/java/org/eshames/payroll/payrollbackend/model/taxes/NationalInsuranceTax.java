package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component("insurance_tax")
public class NationalInsuranceTax extends Tax {

    @PostConstruct
    private void init() {
        brackets = Arrays.asList(0.0, 6331.0, 44020.0, Double.MAX_VALUE);
        rates = Arrays.asList(0.035, 0.12, 0.0);
    }
    @Override
    public double calculateTax(double taxableIncome, TaxDTO taxDTO) {
        return super.calculateTax(taxableIncome, taxDTO);
    }
}
