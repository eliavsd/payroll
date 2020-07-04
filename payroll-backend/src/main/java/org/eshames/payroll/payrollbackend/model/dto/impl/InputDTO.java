package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.Getter;
import lombok.Setter;
import org.eshames.payroll.payrollbackend.model.dto.PayrollDTO;

import java.util.List;

@Getter
@Setter
public class InputDTO implements PayrollDTO {
    private IncomeDTO incomeDTO;
    private List<TaxDTO> taxes;
    private List<SavingsDTO> savings;
}
