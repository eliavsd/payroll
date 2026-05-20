package org.eshames.payroll.payrollbackend.model.taxes;

import org.eshames.payroll.payrollbackend.model.dto.impl.TaxDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NationalInsuranceTaxTest {

    private NationalInsuranceTax nationalInsuranceTax;

    @BeforeEach
    void setUp() {
        nationalInsuranceTax = new NationalInsuranceTax();
        ReflectionTestUtils.invokeMethod(nationalInsuranceTax, "init");
    }

    private TaxDTO createTaxDTO() {
        TaxDTO dto = new TaxDTO();
        dto.setTaxName("insurance");
        dto.setTaxPoints(0);
        return dto;
    }

    @Test
    void zeroIncome() {
        double tax = nationalInsuranceTax.calculateTax(0, createTaxDTO());
        assertEquals(0.0, tax);
    }

    @Test
    void incomeInFirstBracketOnly() {
        double income = 5000.0;
        double tax = nationalInsuranceTax.calculateTax(income, createTaxDTO());
        assertEquals(Math.round(income * 0.035 * 100.0) / 100.0, tax);
    }

    @Test
    void incomeSpanningTwoBrackets() {
        double income = 10000.0;
        double firstBracket = (6331.0 - 0.01) * 0.035;
        double secondBracket = (income - 6331.0) * 0.12;
        double expected = Math.round((firstBracket + secondBracket) * 100.0) / 100.0;
        double tax = nationalInsuranceTax.calculateTax(income, createTaxDTO());
        assertEquals(expected, tax);
    }

    @Test
    void incomeAboveMaxBracket() {
        double income = 50000.0;
        double firstBracket = (6331.0 - 0.01) * 0.035;
        double secondBracket = (44020.0 - 0.01 - 6331.0) * 0.12;
        double expected = Math.round((firstBracket + secondBracket) * 100.0) / 100.0;
        double tax = nationalInsuranceTax.calculateTax(income, createTaxDTO());
        assertEquals(expected, tax, "Income above 44020 is taxed at 0%");
    }

    @Test
    void higherIncomeProducesHigherOrEqualTax() {
        double taxLow = nationalInsuranceTax.calculateTax(5000, createTaxDTO());
        double taxMid = nationalInsuranceTax.calculateTax(20000, createTaxDTO());
        double taxHigh = nationalInsuranceTax.calculateTax(50000, createTaxDTO());
        assertTrue(taxMid > taxLow);
        assertEquals(taxHigh, nationalInsuranceTax.calculateTax(60000, createTaxDTO()),
                "Tax should plateau above max bracket");
    }
}
