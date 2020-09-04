import { Component, OnInit } from '@angular/core';
import {PayBasis} from '../../enum/pay-basis.enum';
import {PayrollDTO} from '../../dto/payroll-dto';
import {PayrollService} from '../../service/payroll.service';
import {SelectItem} from 'primeng';
import {NavigationService} from '../../service/navigation.service';

@Component({
  selector: 'app-income-form',
  templateUrl: './income-form.component.html',
  styleUrls: ['./income-form.component.scss']
})
export class IncomeFormComponent implements OnInit {

  payrollDTO: PayrollDTO;
  payBasisOptions: SelectItem[];
  navigationService: NavigationService;

  constructor(navigationService: NavigationService, payrollService: PayrollService) {
    this.navigationService = navigationService;
    this.payrollDTO = payrollService.payrollInfo;
    this.payrollDTO.incomeDTO.payBasis = PayBasis.MONTHLY;
    this.payBasisOptions = Object.keys(PayBasis).map(key => ({label: PayBasis[key], value: key}));
  }

  onNext() {
  }

  ngOnInit(): void {
    this.navigationService.currentIndex = 0;
  }

}
