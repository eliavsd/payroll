package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NationalInsuranceTaxTest {

    private NationalInsuranceTax nationalInsuranceTax;

    @BeforeEach
    void setUp() throws Exception {
        nationalInsuranceTax = new NationalInsuranceTax();
        Method init = NationalInsuranceTax.class.getDeclaredMethod("init");
        init.setAccessible(true);
        init.invoke(nationalInsuranceTax);
    }

    private TaxDTO taxDTO() {
        TaxDTO dto = new TaxDTO();
        dto.setTaxName("insurance");
        dto.setTaxPoints(0);
        return dto;
    }

    @Test
    void zeroIncome_returnsZero() {
        assertEquals(0.0, nationalInsuranceTax.calculateTax(0, taxDTO()), 0.01);
    }

    @Test
    void incomeInFirstBracket() {
        double income = 5000.0;
        double expected = income * 0.035;
        assertEquals(expected, nationalInsuranceTax.calculateTax(income, taxDTO()), 0.01);
    }

    @Test
    void incomeInSecondBracket() {
        double income = 10000.0;
        double firstBracket = (6331.0 - 0.01) * 0.035;
        double secondBracket = (income - 6331.0) * 0.12;
        double expected = firstBracket + secondBracket;
        assertEquals(expected, nationalInsuranceTax.calculateTax(income, taxDTO()), 0.01);
    }

    @Test
    void incomeAboveCeiling() {
        double income = 50000.0;
        double firstBracket = (6331.0 - 0.01) * 0.035;
        double secondBracket = (44020.0 - 0.01 - 6331.0) * 0.12;
        double expected = firstBracket + secondBracket;
        assertEquals(expected, nationalInsuranceTax.calculateTax(income, taxDTO()), 0.01);
    }

    @Test
    void incomeAtExactBracketBoundary() {
        double income = 6331.0;
        double expected = (6331.0 - 0.01) * 0.035;
        assertEquals(expected, nationalInsuranceTax.calculateTax(income, taxDTO()), 0.01);
    }

    @Test
    void taxPointsDoNotAffectResult() {
        TaxDTO dtoWithPoints = new TaxDTO();
        dtoWithPoints.setTaxName("insurance");
        dtoWithPoints.setTaxPoints(5);
        double withPoints = nationalInsuranceTax.calculateTax(10000, dtoWithPoints);
        double withoutPoints = nationalInsuranceTax.calculateTax(10000, taxDTO());
        assertEquals(withoutPoints, withPoints, 0.01);
    }
}
