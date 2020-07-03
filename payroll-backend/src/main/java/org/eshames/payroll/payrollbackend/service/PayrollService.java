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
        int incomeForSavings = incomeDTO.getBaseSalary() + incomeDTO.getGlobalOvertime();
        int totalGrossIncome = incomeForSavings + incomeDTO.getTravelCopay() + incomeDTO.getOtherCopay();
        double totalIncomeTax = calculateTax(totalGrossIncome, taxDTO, "income");
        double insuranceTax = calculateTax(totalGrossIncome, taxDTO, "insurance");
        float savings = 0;
        return new ResultDTO(incomeForSavings, totalGrossIncome, totalGrossIncome, totalIncomeTax, insuranceTax, savings);
    }



    public double calculateTax(int taxableIncome, TaxDTO taxDTO, String taxName) {
        Tax tax = (Tax) applicationContext.getBean(taxName + "_tax");
        return tax.calculateTax(taxableIncome, taxDTO);
    }

    public double calculateSavings(float savingsIncome, float savingsFactor, String savingsName) {
        Savings savings = (Savings) applicationContext.getBean(savingsName + "_savings");
        return savings.calculateSavings(savingsFactor, savingsIncome);
    }
}
