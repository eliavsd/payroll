package org.eshames.payroll.payrollbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayBasis {
    HOURLY("Hourly"),
    MONTHLY("Monthly");

    private final String fullForm;
}
