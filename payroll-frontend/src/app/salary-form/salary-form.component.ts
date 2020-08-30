import {Component, Input, OnInit, Output} from '@angular/core';
import {PayrollService} from '../service/payroll.service';
import {PayrollDTO} from '../dto/payroll-dto';
import {PayBasis} from '../enum/pay-basis.enum';
import {ResultDTO} from '../dto/result-dto';
import {SelectItem} from 'primeng';
import {TaxDTO} from '../dto/tax-dto';
import {SavingsType} from '../enum/savings-type.enum';
import {SavingsDTO} from '../dto/savings-dto';

@Component({
  selector: 'app-salary-form',
  templateUrl: './salary-form.component.html',
  styleUrls: ['./salary-form.component.scss']
})


export class SalaryFormComponent implements OnInit {

  @Input()
  payrollDTO: PayrollDTO;
  @Input()
  incomeTaxCredits: number;
  @Output()
  result: ResultDTO;

  payBasisOptions: SelectItem[];
  savingsTypes: SelectItem[];
  pensionOption = SavingsType.PERCENTAGE;
  pensionFactor;
  edufundOption = SavingsType.PERCENTAGE;
  edufundFactor;

  constructor(private payrollService: PayrollService) {
    this.payBasisOptions = Object.keys(PayBasis).map(key => ({label: PayBasis[key], value: key}));
    this.savingsTypes = Object.keys(SavingsType).map(key => ({label: SavingsType[key], value: key}));
    this.payrollDTO = new PayrollDTO();
    this.payrollDTO.incomeDTO.payBasis = PayBasis.MONTHLY;
  }

  ngOnInit() {
  }

  onSubmit() {
    // Add taxes
    let incomeTax = new TaxDTO();
    incomeTax.taxName = "income";
    incomeTax.taxPoints = this.incomeTaxCredits;

    let insuranceTax = new TaxDTO();
    insuranceTax.taxName = "insurance";

    this.payrollDTO.taxes.push(incomeTax);
    this.payrollDTO.taxes.push(insuranceTax);

    // Add savings
    let pension = new SavingsDTO();
    pension.savingsName = "pension";
    if (this.pensionOption !== null) {
      pension.savingsType = this.pensionOption;
    }
    pension.savingsFactor = this.pensionFactor;
    if (pension.savingsType === SavingsType.PERCENTAGE) {
      pension.savingsFactor /= 100;
    }

    let edufund = new SavingsDTO();
    edufund.savingsName = "edufund";
    if (this.edufundOption !== null) {
      edufund.savingsType = this.edufundOption;
    }
    edufund.savingsFactor = this.edufundFactor;
    if (edufund.savingsType === SavingsType.PERCENTAGE) {
      edufund.savingsFactor /= 100;
    }

    this.payrollDTO.savings.push(pension);
    this.payrollDTO.savings.push(edufund);

    this.payrollService.grossToNet(this.payrollDTO).subscribe(data => {
        console.log(data);
        return data;
      }
    );
    this.payrollDTO = new PayrollDTO();
  }

  onChange(ev) {
    console.log(this.payrollDTO.incomeDTO.payBasis);
  }

}
