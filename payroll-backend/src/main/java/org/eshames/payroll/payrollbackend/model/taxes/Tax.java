package org.eshames.payroll.payrollbackend.model.taxes;

public interface Tax {
    float calculateTax(float taxableIncome);
}
