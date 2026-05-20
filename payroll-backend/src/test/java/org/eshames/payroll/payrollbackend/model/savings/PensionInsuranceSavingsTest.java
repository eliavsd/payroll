package org.eshames.payroll.payrollbackend.model.savings;

import org.eshames.payroll.payrollbackend.model.dto.impl.SavingsDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PensionInsuranceSavingsTest {

    private final PensionInsuranceSavings pensionSavings = new PensionInsuranceSavings();

    @Test
    void calculateSavingsReturnsCorrectAmount() {
        SavingsDTO dto = new SavingsDTO();
        dto.setSavingsFactor(0.0625);

        double result = pensionSavings.calculateSavings(10000, dto);

        assertEquals(625.0, result, 0.001);
    }
}
