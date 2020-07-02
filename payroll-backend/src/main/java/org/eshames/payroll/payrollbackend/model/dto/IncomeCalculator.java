package org.eshames.payroll.payrollbackend.model.dto;

import org.eshames.payroll.payrollbackend.model.dto.impl.IncomeDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.ResultDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;

public class IncomeCalculator {

    private static ResultDTO GrossToNet(IncomeDTO incomeDTO, TaxDTO taxDTO) {
        float incomeForSavings = incomeDTO.getBaseSalary();
        float totalGrossIncome = incomeForSavings;
        float totalGrossForTax = totalGrossIncome;
        float totalIncomeTax = 0;
        float insuranceTax = 0;
        float savings = 0;
        totalIncomeTax -= taxDTO.getTaxPoints();
        return new ResultDTO(incomeForSavings, totalGrossIncome, totalGrossForTax, totalIncomeTax, insuranceTax, savings);
    }
}
