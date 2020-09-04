import { Component, OnInit } from '@angular/core';
import {PayrollDTO} from '../../dto/payroll-dto';
import {PayrollService} from '../../service/payroll.service';
import {TaxDTO} from '../../dto/tax-dto';
import {NavigationService} from '../../service/navigation.service';

@Component({
  selector: 'app-tax-form',
  templateUrl: './tax-form.component.html',
  styleUrls: ['./tax-form.component.scss']
})
export class TaxFormComponent implements OnInit {

  incomeTaxCredits: number;
  payrollDTO: PayrollDTO;
  navigationService: NavigationService;

  constructor(navigationService: NavigationService, payrollService: PayrollService) {
    this.navigationService = navigationService;
    this.payrollDTO = payrollService.payrollInfo;
  }

  onNext() {
    // Add taxes
    let incomeTax = new TaxDTO();
    incomeTax.taxName = "income";
    incomeTax.taxPoints = this.incomeTaxCredits;

    let insuranceTax = new TaxDTO();
    insuranceTax.taxName = "insurance";

    this.payrollDTO.taxes = [incomeTax, insuranceTax];
  }

  ngOnInit(): void {
    this.navigationService.currentIndex = 1;
  }

}
