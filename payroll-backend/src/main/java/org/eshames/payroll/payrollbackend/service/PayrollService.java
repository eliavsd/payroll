package org.eshames.payroll.payrollbackend.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import javax0.license3j.License;
import javax0.license3j.io.LicenseReader;
import lombok.extern.slf4j.Slf4j;
import org.eshames.payroll.payrollbackend.constant.LicensePublicKey;
import org.eshames.payroll.payrollbackend.exception.LicenseException;
import org.eshames.payroll.payrollbackend.model.dto.impl.*;
import org.eshames.payroll.payrollbackend.model.savings.Savings;
import org.eshames.payroll.payrollbackend.model.taxes.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PayrollService {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MeterRegistry meterRegistry;

    public ResultDTO grossToNet(InputDTO grossIncome) throws Exception {
        Timer.Sample sample = Timer.start(meterRegistry);
        Thread.sleep(5000);
        Timer.Sample sample1 = Timer.start(meterRegistry);
        Thread.sleep(5000);
        sample.stop(meterRegistry.timer("timer1"));
        sample1.stop(meterRegistry.timer("timer2"));
        doLicenseCheck();
        log.info("Received request JSON: {}", grossIncome.toString());
        IncomeDTO incomeDTO = grossIncome.getIncomeDTO();
        List<TaxDTO> taxes = grossIncome.getTaxes();
        List<SavingsDTO> savings = grossIncome.getSavings();
        double incomeForSavings = incomeDTO.getBaseSalary() + incomeDTO.getGlobalOvertime();
        double totalGrossIncome = incomeForSavings + incomeDTO.getTravelCopay() + incomeDTO.getOtherCopay();

        Map<String, Double> savingsResults = calculateSavings(incomeForSavings, savings);
        // I've stopped generifying here, because I want the BL to be clearer
        double pensionSavings = savingsResults.getOrDefault("pension", 0.0);
        // Now take the income tax that would be paid if we didn't deduct the pension from the gross and apply a 35%
        // discount on it before adding it as income tax
        Map<String, Double> taxResults = calculateTaxes(totalGrossIncome, taxes);
        double incomeTax = taxResults.getOrDefault("income", 0.0);
        // Deduct the insurance exemption from income tax only - 35% from all taxes owed up to 616ils of money put
        // aside. Any remaining savings is subject to normal income tax
        Savings pensionSavingsBean = (Savings) applicationContext.getBean("pension_savings");
        incomeTax -= Math.min(pensionSavingsBean.getExemptionFactor() * pensionSavings,
                pensionSavingsBean.getExemptionFactor() * pensionSavingsBean.getMaxExemption());
        taxResults.put("income", Math.max(incomeTax, 0));
        return new ResultDTO(incomeForSavings, totalGrossIncome, totalGrossIncome, taxResults, savingsResults);
    }

    private Map<String, Double> calculateTaxes(double taxableIncome, List<TaxDTO> taxes) {
        Map<String, Double> taxResults = new HashMap<>();

        for (TaxDTO tax: taxes) {
            taxResults.put(tax.getTaxName(), calculateTax(taxableIncome, tax));
        }
        return taxResults;
    }

    private Map<String, Double> calculateSavings(double savingsIncome, List<SavingsDTO> savings) {
        Map<String, Double> savingsResults = new HashMap<>();
        for (SavingsDTO saving: savings) {
            savingsResults.put(saving.getSavingsName(), calculateSaving(savingsIncome, saving));
        }
        return savingsResults;
    }

    private double calculateTax(double taxableIncome, TaxDTO taxDTO) {
        Tax tax = (Tax) applicationContext.getBean(taxDTO.getTaxName() + "_tax");
        return tax.calculateTax(taxableIncome, taxDTO);
    }

    public double calculateSaving(double savingsIncome, SavingsDTO savingsDTO) {
        Savings savings = (Savings) applicationContext.getBean(savingsDTO.getSavingsName() + "_savings");
        return savings.calculateSavings(savingsIncome, savingsDTO);
    }

    public void doLicenseCheck(){
        License license;
        try(LicenseReader licenseReader = new LicenseReader(getClass().getResource("/payroll.lic").getPath())){
            license = licenseReader.read();
            log.info("License found.\nProduct: {}\nFirst Name: {}\nLast Name: {}",
                    license.get("productName").getString(),
                    license.get("firstName").getString(),
                    license.get("lastName").getString());
            if(license.isOK(LicensePublicKey.key)){
                log.info("License check OK");
                log.info("Full license info: {}", license.getFeatures().toString());
            }
            else {
                throw new LicenseException("License failed signature check");
            }
        }
        catch (IOException e){
            log.error("Error reading license file: {}", e.toString());
        }
    }
}
