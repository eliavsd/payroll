package org.eshames.payroll.payrollbackend.model.savings;

import org.eshames.payroll.payrollbackend.model.dto.impl.SavingsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PensionInsuranceSavingsTest {

    private PensionInsuranceSavings pensionSavings;

    @BeforeEach
    void setUp() {
        pensionSavings = new PensionInsuranceSavings();
    }

    private SavingsDTO createSavingsDTO(double factor) {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsName("pension");
        dto.setSavingsFactor(factor);
        return dto;
    }

    @Test
    void calculateSavingsWithTypicalFactor() {
        double grossIncome = 10000.0;
        double factor = 0.06;
        double savings = pensionSavings.calculateSavings(grossIncome, createSavingsDTO(factor));
        assertEquals(600.0, savings);
    }

    @Test
    void calculateSavingsWithZeroFactor() {
        double savings = pensionSavings.calculateSavings(10000.0, createSavingsDTO(0));
        assertEquals(0.0, savings);
    }

    @Test
    void calculateSavingsWithZeroIncome() {
        double savings = pensionSavings.calculateSavings(0, createSavingsDTO(0.06));
        assertEquals(0.0, savings);
    }

    @Test
    void exemptionFactorIs035() {
        assertEquals(0.35, pensionSavings.getExemptionFactor());
    }

    @Test
    void maxExemptionIs616() {
        assertEquals(616, pensionSavings.getMaxExemption());
    }
}
