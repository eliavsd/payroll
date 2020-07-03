package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.Getter;
import lombok.Setter;
import org.eshames.payroll.payrollbackend.model.dto.PayrollDTO;
import org.eshames.payroll.payrollbackend.model.savings.Savings;

@Getter
@Setter
public class InputDTO implements PayrollDTO {
    private IncomeDTO incomeDTO;
    private TaxDTO taxDTO;
    private Savings savings;
}
