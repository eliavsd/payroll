import { Component, OnInit } from '@angular/core';
import {PayBasis} from '../../enum/pay-basis.enum';
import {PayrollDTO} from '../../dto/payroll-dto';
import {PayrollService} from '../../service/payroll.service';
import {SelectItem} from 'primeng';

@Component({
  selector: 'app-income-form',
  templateUrl: './income-form.component.html',
  styleUrls: ['./income-form.component.scss']
})
export class IncomeFormComponent implements OnInit {

  payrollDTO: PayrollDTO;
  payBasisOptions: SelectItem[];

  constructor(payrollService: PayrollService) {
    this.payrollDTO = payrollService.payrollInfo;
    this.payrollDTO.incomeDTO.payBasis = PayBasis.MONTHLY;
    this.payBasisOptions = Object.keys(PayBasis).map(key => ({label: PayBasis[key], value: key}));
  }

  onNext() {

  }

  ngOnInit(): void {
  }

}
