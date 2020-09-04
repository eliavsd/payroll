import {Component, OnInit} from '@angular/core';
import {PayrollService} from '../service/payroll.service';
import {PayrollDTO} from '../dto/payroll-dto';
import {PayBasis} from '../enum/pay-basis.enum';
import {ResultDTO} from '../dto/result-dto';
import {MenuItem, SelectItem} from 'primeng';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-salary-form',
  templateUrl: './salary-form.component.html',
  styleUrls: ['./salary-form.component.scss']
})


export class SalaryFormComponent implements OnInit {

  navItems: MenuItem[];
  activeNavIndex: number = 0;
  payrollDTO: PayrollDTO;
  incomeTaxCredits: number;
  result: ResultDTO;

  payBasisOptions: SelectItem[];


  constructor(private payrollService: PayrollService) {


    this.payrollDTO = payrollService.payrollInfo;
    this.payrollDTO.incomeDTO.payBasis = PayBasis.MONTHLY;
  }

  ngOnInit() {
    this.navItems = [
      {label: 'Income'},
      {label: 'Taxes'},
      {label: 'Savings'}
    ]
  }

  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
  }

  onSubmit() {

    this.payrollService.grossToNet().subscribe(data => {
        console.log(data);
        return data;
      }
    );
    this.payrollDTO = new PayrollDTO();
  }

}
