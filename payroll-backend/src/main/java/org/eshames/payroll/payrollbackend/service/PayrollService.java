package org.eshames.payroll.payrollbackend.service;

import org.eshames.payroll.payrollbackend.model.dto.impl.IncomeDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.InputDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.ResultDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.eshames.payroll.payrollbackend.model.savings.Savings;
import org.eshames.payroll.payrollbackend.model.taxes.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {
    @Autowired
    ApplicationContext applicationContext;

    public ResultDTO grossToNet(InputDTO grossIncome) {
        IncomeDTO incomeDTO = grossIncome.getIncomeDTO();
        TaxDTO taxDTO = grossIncome.getTaxDTO();
        float incomeForSavings = incomeDTO.getBaseSalary() + incomeDTO.getGlobalOvertime();
        float totalGrossIncome = incomeForSavings;
        float totalGrossForTax = incomeForSavings;
        float totalIncomeTax = calculateTax(totalGrossForTax, "income");
        float insuranceTax = calculateTax(totalGrossForTax, "insurance");
        float savings = 0;
        totalIncomeTax -= taxDTO.getTaxPoints();
        return new ResultDTO(incomeForSavings, totalGrossIncome, totalGrossForTax, totalIncomeTax, insuranceTax, savings);
    }



    public float calculateTax(float taxableIncome, String taxName) {
        Tax tax = (Tax) applicationContext.getBean(taxName + "_tax");
        return tax.calculateTax(taxableIncome);
    }

    public float calculateSavings(float savingsIncome, float savingsFactor, String savingsName) {
        Savings savings = (Savings) applicationContext.getBean(savingsName + "_savings");
        return savings.calculateSavings(savingsFactor, savingsIncome);
    }
}
