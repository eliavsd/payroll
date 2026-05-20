package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeTaxTest {

    private IncomeTax incomeTax;

    @BeforeEach
    void setUp() {
        incomeTax = new IncomeTax();
        ReflectionTestUtils.invokeMethod(incomeTax, "init");
    }

    private TaxDTO createTaxDTO(double taxPoints) {
        TaxDTO dto = new TaxDTO();
        dto.setTaxName("income");
        dto.setTaxPoints(taxPoints);
        return dto;
    }

    @Test
    void zeroIncome() {
        double tax = incomeTax.calculateTax(0, createTaxDTO(0));
        assertEquals(0.0, tax);
    }

    @Test
    void incomeInFirstBracketOnly() {
        double income = 5000.0;
        double tax = incomeTax.calculateTax(income, createTaxDTO(0));
        assertEquals(Math.round(income * 0.1 * 100.0) / 100.0, tax);
    }

    @Test
    void incomeSpanningTwoBrackets() {
        double income = 7000.0;
        double expectedFirstBracket = (6330.0 - 0.01) * 0.1;
        double expectedSecondBracket = (income - 6330.0) * 0.14;
        double expected = Math.round((expectedFirstBracket + expectedSecondBracket) * 100.0) / 100.0;
        double tax = incomeTax.calculateTax(income, createTaxDTO(0));
        assertEquals(expected, tax);
    }

    @Test
    void taxPointsReduceTax() {
        double income = 10000.0;
        double taxWithoutPoints = incomeTax.calculateTax(income, createTaxDTO(0));
        double taxWith225Points = incomeTax.calculateTax(income, createTaxDTO(2.25));
        assertEquals(taxWithoutPoints - (2.25 * TaxConstants.TAX_POINT_VALUE), taxWith225Points);
    }

    @Test
    void taxPointsCanMakeTaxNegative() {
        double income = 1000.0;
        double tax = incomeTax.calculateTax(income, createTaxDTO(2.25));
        assertTrue(tax < 0, "Tax points can cause negative result before service-level clamping");
    }

    @Test
    void highIncomeSpansAllBrackets() {
        double income = 60000.0;
        double tax = incomeTax.calculateTax(income, createTaxDTO(0));
        assertTrue(tax > 0);
        double taxLower = incomeTax.calculateTax(50000.0, createTaxDTO(0));
        assertTrue(tax > taxLower, "Higher income should produce higher tax");
    }
}
