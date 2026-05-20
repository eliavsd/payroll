package org.eshames.payroll.payrollbackend.service;

import org.eshames.payroll.payrollbackend.model.dto.impl.*;
import org.eshames.payroll.payrollbackend.model.savings.PensionInsuranceSavings;
import org.eshames.payroll.payrollbackend.model.taxes.IncomeTax;
import org.eshames.payroll.payrollbackend.model.taxes.NationalInsuranceTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PayrollServiceTest {

    private PayrollService payrollService;
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        payrollService = new PayrollService();
        applicationContext = mock(ApplicationContext.class);
        ReflectionTestUtils.setField(payrollService, "applicationContext", applicationContext);
    }

    private SavingsDTO createSavingsDTO(String name, double factor) {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsName(name);
        dto.setSavingsFactor(factor);
        return dto;
    }

    @Test
    void calculateSavingDelegatesToBean() {
        PensionInsuranceSavings pensionBean = new PensionInsuranceSavings();
        when(applicationContext.getBean("pension_savings")).thenReturn(pensionBean);

        SavingsDTO savingsDTO = createSavingsDTO("pension", 0.06);
        double result = payrollService.calculateSaving(10000.0, savingsDTO);

        assertEquals(600.0, result);
        verify(applicationContext).getBean("pension_savings");
    }

    @Test
    void calculateSavingWithZeroIncome() {
        PensionInsuranceSavings pensionBean = new PensionInsuranceSavings();
        when(applicationContext.getBean("pension_savings")).thenReturn(pensionBean);

        SavingsDTO savingsDTO = createSavingsDTO("pension", 0.06);
        double result = payrollService.calculateSaving(0, savingsDTO);

        assertEquals(0.0, result);
    }
}
