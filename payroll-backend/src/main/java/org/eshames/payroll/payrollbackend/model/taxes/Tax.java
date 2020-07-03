package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;

public interface Tax {
    double calculateTax(int taxableIncome, TaxDTO taxDTO);
}
