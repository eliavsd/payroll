package org.eshames.payroll.payrollbackend.model.constants;

import java.util.Arrays;
import java.util.List;

public final class TaxConstants {
    public static final int TAX_POINT_VALUE = 216;
    public static final int EDU_FUND_MAX_EXEMPTION = 15712;

    // Tax brackets - income tax
    public static final List<Integer> INCOME_BRACKETS = Arrays.asList(0, 6331, 44020);
    public static final List<Float> INCOME_RATES = Arrays.asList(0.035f, 0.12f, 0f);
    // Tax brackets - national insurance
    public static final List<Integer> INSURANCE_BRACKETS = Arrays.asList(0, 6330, 9080, 14580, 20260, 42160, 54300);
    public static final List<Float> INSURANCE_RATES = Arrays.asList(0.1f, 0.14f, 0.2f, 0.31f, 0.35f, 0.47f, 0.5f);
}
