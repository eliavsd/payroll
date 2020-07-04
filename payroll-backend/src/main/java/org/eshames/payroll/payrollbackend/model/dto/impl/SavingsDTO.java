package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.Getter;
import lombok.Setter;
import org.eshames.payroll.payrollbackend.model.dto.PayrollDTO;
import org.eshames.payroll.payrollbackend.model.savings.SavingsType;

@Getter
@Setter
public class SavingsDTO implements PayrollDTO {
    private String savingsName;
    private SavingsType savingsType = SavingsType.PERCENTAGE;
    private double savingsFactor;
}
