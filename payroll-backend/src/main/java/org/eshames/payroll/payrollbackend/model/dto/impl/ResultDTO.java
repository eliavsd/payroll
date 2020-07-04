package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultDTO {
    private double grossForSavings;
    private double totalGrossIncome;
    private double totalGrossForTax;
    private double incomeTax;
    private double insuranceTax;
    private double pensionSavings;
    private double edufundSavings;
}
