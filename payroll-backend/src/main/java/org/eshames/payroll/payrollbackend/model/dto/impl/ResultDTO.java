package org.eshames.payroll.payrollbackend.model.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultDTO {
    private float grossForSavings;
    private float totalGrossIncome;
    private float totalGrossForTax;
    private float incomeTax;
    private float insuranceTax;
    private float savings;
}
