package org.eshames.payroll.payrollbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingsDTO implements PayrollDTO {
    private String savingsType;
    private float savingsFactor;
}
