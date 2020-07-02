package org.eshames.payroll.payrollbackend.model.dto;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.IncomeDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.InputDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.ResultDTO;
import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.eshames.payroll.payrollbackend.model.taxes.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncomeCalculator {

    @Autowired
    ApplicationContext applicationContext;

    public ResultDTO GrossToNet(InputDTO inputDTO) {
        IncomeDTO incomeDTO = inputDTO.getIncomeDTO();
        TaxDTO taxDTO = inputDTO.getTaxDTO();
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
}
