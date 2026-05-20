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

    private SavingsDTO savingsDTO(double factor) {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsName("pension");
        dto.setSavingsFactor(factor);
        return dto;
    }

    @Test
    void calculateSavings_standardFactor() {
        double income = 10000.0;
        double factor = 0.0625;
        assertEquals(income * factor, pensionSavings.calculateSavings(income, savingsDTO(factor)), 0.01);
    }

    @Test
    void calculateSavings_zeroFactor() {
        assertEquals(0.0, pensionSavings.calculateSavings(10000, savingsDTO(0)), 0.01);
    }

    @Test
    void calculateSavings_zeroIncome() {
        assertEquals(0.0, pensionSavings.calculateSavings(0, savingsDTO(0.0625)), 0.01);
    }

    @Test
    void calculateSavings_highIncome() {
        double income = 50000.0;
        double factor = 0.0625;
        assertEquals(income * factor, pensionSavings.calculateSavings(income, savingsDTO(factor)), 0.01);
    }

    @Test
    void getExemptionFactor_returns035() {
        assertEquals(0.35, pensionSavings.getExemptionFactor(), 0.001);
    }

    @Test
    void getMaxExemption_returns616() {
        assertEquals(616, pensionSavings.getMaxExemption(), 0.01);
    }
}
