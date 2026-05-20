package org.eshames.payroll.payrollbackend.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.eshames.payroll.payrollbackend.model.dto.impl.*;
import org.eshames.payroll.payrollbackend.model.savings.EducationFundSavings;
import org.eshames.payroll.payrollbackend.model.savings.PensionInsuranceSavings;
import org.eshames.payroll.payrollbackend.model.savings.Savings;
import org.eshames.payroll.payrollbackend.model.taxes.IncomeTax;
import org.eshames.payroll.payrollbackend.model.taxes.NationalInsuranceTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PayrollServiceTest {

    @InjectMocks
    private PayrollService payrollService;

    @Mock
    private ApplicationContext applicationContext;

    @Spy
    private MeterRegistry meterRegistry = new SimpleMeterRegistry();

    @BeforeEach
    void setUp() {
    }

    private SavingsDTO pensionSavingsDTO(double factor) {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsName("pension");
        dto.setSavingsFactor(factor);
        return dto;
    }

    private SavingsDTO edufundSavingsDTO(double factor) {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsName("edufund");
        dto.setSavingsFactor(factor);
        return dto;
    }

    @Test
    void calculateSaving_pension_returnsCorrectAmount() {
        PensionInsuranceSavings pensionSavings = new PensionInsuranceSavings();
        when(applicationContext.getBean("pension_savings")).thenReturn(pensionSavings);

        double result = payrollService.calculateSaving(10000, pensionSavingsDTO(0.0625));
        assertEquals(625.0, result, 0.01);
    }

    @Test
    void calculateSaving_edufund_returnsCorrectAmount() {
        EducationFundSavings eduFundSavings = new EducationFundSavings();
        when(applicationContext.getBean("edufund_savings")).thenReturn(eduFundSavings);

        double result = payrollService.calculateSaving(10000, edufundSavingsDTO(0.075));
        assertEquals(750.0, result, 0.01);
    }

    @Test
    void calculateSaving_edufund_cappedAtMaxExemption() {
        EducationFundSavings eduFundSavings = new EducationFundSavings();
        when(applicationContext.getBean("edufund_savings")).thenReturn(eduFundSavings);

        double result = payrollService.calculateSaving(50000, edufundSavingsDTO(0.075));
        assertEquals(15712 * 0.075, result, 0.01);
    }

    @Test
    void calculateSaving_zeroIncome() {
        PensionInsuranceSavings pensionSavings = new PensionInsuranceSavings();
        when(applicationContext.getBean("pension_savings")).thenReturn(pensionSavings);

        double result = payrollService.calculateSaving(0, pensionSavingsDTO(0.0625));
        assertEquals(0.0, result, 0.01);
    }
}
