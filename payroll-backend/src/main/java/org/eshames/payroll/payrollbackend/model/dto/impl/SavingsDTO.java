package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.Getter;
import lombok.Setter;
import org.eshames.payroll.payrollbackend.model.dto.PayrollDTO;

@Getter
@Setter
public class SavingsDTO implements PayrollDTO {
    private String savingsType;
    private double savingsFactor;
}
