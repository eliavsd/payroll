package org.eshames.payroll.payrollbackend.model.savings;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.SavingsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EducationFundSavingsTest {

    private EducationFundSavings educationFundSavings;

    @BeforeEach
    void setUp() {
        educationFundSavings = new EducationFundSavings();
    }

    private SavingsDTO createSavingsDTO(double factor) {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsName("edufund");
        dto.setSavingsFactor(factor);
        return dto;
    }

    @Test
    void calculateSavingsBelowCap() {
        double grossIncome = 10000.0;
        double factor = 0.075;
        double savings = educationFundSavings.calculateSavings(grossIncome, createSavingsDTO(factor));
        assertEquals(grossIncome * factor, savings);
    }

    @Test
    void calculateSavingsAtCap() {
        double factor = 0.075;
        double incomeAboveCap = (TaxConstants.EDU_FUND_MAX_EXEMPTION + 10000);
        double savings = educationFundSavings.calculateSavings(incomeAboveCap, createSavingsDTO(factor));
        assertEquals(TaxConstants.EDU_FUND_MAX_EXEMPTION * factor, savings);
    }

    @Test
    void calculateSavingsWithZeroFactor() {
        double savings = educationFundSavings.calculateSavings(10000.0, createSavingsDTO(0));
        assertEquals(0.0, savings);
    }

    @Test
    void calculateSavingsWithZeroIncome() {
        double savings = educationFundSavings.calculateSavings(0, createSavingsDTO(0.075));
        assertEquals(0.0, savings);
    }

    @Test
    void exemptionFactorIsZero() {
        assertEquals(0, educationFundSavings.getExemptionFactor());
    }

    @Test
    void maxExemptionIsZero() {
        assertEquals(0, educationFundSavings.getMaxExemption());
    }
}
