package org.eshames.payroll.payrollbackend.model.savings;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.SavingsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EducationFundSavingsTest {

    private EducationFundSavings eduFundSavings;

    @BeforeEach
    void setUp() {
        eduFundSavings = new EducationFundSavings();
    }

    private SavingsDTO savingsDTO(double factor) {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsName("edufund");
        dto.setSavingsFactor(factor);
        return dto;
    }

    @Test
    void calculateSavings_belowMaxExemption() {
        double income = 10000.0;
        double factor = 0.075;
        double expected = income * factor;
        assertEquals(expected, eduFundSavings.calculateSavings(income, savingsDTO(factor)), 0.01);
    }

    @Test
    void calculateSavings_aboveMaxExemption() {
        double income = 50000.0;
        double factor = 0.075;
        double maxBasedOnExemption = TaxConstants.EDU_FUND_MAX_EXEMPTION * factor;
        double incomeBased = income * factor;
        double expected = Math.min(incomeBased, maxBasedOnExemption);
        assertEquals(expected, eduFundSavings.calculateSavings(income, savingsDTO(factor)), 0.01);
    }

    @Test
    void calculateSavings_exactlyAtMaxExemption() {
        double factor = 0.075;
        double income = TaxConstants.EDU_FUND_MAX_EXEMPTION;
        double expected = income * factor;
        assertEquals(expected, eduFundSavings.calculateSavings(income, savingsDTO(factor)), 0.01);
    }

    @Test
    void calculateSavings_zeroIncome() {
        assertEquals(0.0, eduFundSavings.calculateSavings(0, savingsDTO(0.075)), 0.01);
    }

    @Test
    void calculateSavings_zeroFactor() {
        assertEquals(0.0, eduFundSavings.calculateSavings(10000, savingsDTO(0)), 0.01);
    }

    @Test
    void getExemptionFactor_returnsZero() {
        assertEquals(0.0, eduFundSavings.getExemptionFactor(), 0.001);
    }

    @Test
    void getMaxExemption_returnsZero() {
        assertEquals(0.0, eduFundSavings.getMaxExemption(), 0.01);
    }
}
