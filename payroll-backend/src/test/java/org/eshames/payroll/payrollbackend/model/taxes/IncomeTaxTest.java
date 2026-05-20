package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.constants.TaxConstants;
import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeTaxTest {

    private IncomeTax incomeTax;

    @BeforeEach
    void setUp() throws Exception {
        incomeTax = new IncomeTax();
        Method init = IncomeTax.class.getDeclaredMethod("init");
        init.setAccessible(true);
        init.invoke(incomeTax);
    }

    private TaxDTO taxDTO(double taxPoints) {
        TaxDTO dto = new TaxDTO();
        dto.setTaxName("income");
        dto.setTaxPoints(taxPoints);
        return dto;
    }

    @Test
    void zeroIncome_zeroTaxPoints_returnsZero() {
        double result = incomeTax.calculateTax(0, taxDTO(0));
        assertEquals(0.0, result, 0.01);
    }

    @Test
    void incomeInFirstBracket_noTaxPoints() {
        double income = 5000.0;
        double expected = income * 0.1;
        assertEquals(expected, incomeTax.calculateTax(income, taxDTO(0)), 0.01);
    }

    @Test
    void incomeInSecondBracket_noTaxPoints() {
        double income = 8000.0;
        double firstBracket = (6330.0 - 0.01) * 0.1;
        double secondBracket = (income - 6330.0) * 0.14;
        double expected = firstBracket + secondBracket;
        assertEquals(expected, incomeTax.calculateTax(income, taxDTO(0)), 0.01);
    }

    @Test
    void taxPointsDeduction() {
        double income = 10000.0;
        double taxWithoutPoints = incomeTax.calculateTax(income, taxDTO(0));
        double taxWith2Points = incomeTax.calculateTax(income, taxDTO(2));
        assertEquals(taxWithoutPoints - 2 * TaxConstants.TAX_POINT_VALUE, taxWith2Points, 0.01);
    }

    @Test
    void taxPointsCanMakeTaxNegative() {
        double income = 1000.0;
        double result = incomeTax.calculateTax(income, taxDTO(2.25));
        assertTrue(result < 0);
    }

    @Test
    void incomeSpanningMultipleBrackets() {
        double income = 15000.0;
        double b1 = (6330.0 - 0.01) * 0.1;
        double b2 = (9080.0 - 0.01 - 6330.0) * 0.14;
        double b3 = (income - 14580.0) * 0.31;
        double b3Full = (14580.0 - 0.01 - 9080.0) * 0.2;
        double expected = b1 + b2 + b3Full + b3;
        assertEquals(expected, incomeTax.calculateTax(income, taxDTO(0)), 1.0);
    }

    @Test
    void highIncome_allBrackets() {
        double income = 60000.0;
        double result = incomeTax.calculateTax(income, taxDTO(0));
        assertTrue(result > 0);
        double resultWithPoints = incomeTax.calculateTax(income, taxDTO(2.25));
        assertEquals(result - 2.25 * TaxConstants.TAX_POINT_VALUE, resultWithPoints, 0.01);
    }
}
