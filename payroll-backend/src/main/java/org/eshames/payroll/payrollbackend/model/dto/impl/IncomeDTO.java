package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.Getter;
import lombok.Setter;
import org.eshames.payroll.payrollbackend.model.PayBasis;
import org.eshames.payroll.payrollbackend.model.dto.PayrollDTO;

@Getter
@Setter
public class IncomeDTO implements PayrollDTO {

    private float baseSalary;
    private float globalOvertime;
    private PayBasis payBasis;
    private float travelCopay;
    private float otherCopay;
}
