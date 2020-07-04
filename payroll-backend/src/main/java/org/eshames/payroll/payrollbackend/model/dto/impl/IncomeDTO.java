package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.Getter;
import lombok.Setter;
import org.eshames.payroll.payrollbackend.model.PayBasis;
import org.eshames.payroll.payrollbackend.model.dto.PayrollDTO;

@Getter
@Setter
public class IncomeDTO implements PayrollDTO {

    private double baseSalary;
    private double globalOvertime;
    private PayBasis payBasis;
    private double travelCopay;
    private double otherCopay;
}
