import {Component, OnInit} from '@angular/core';
import {PayrollService} from '../service/payroll.service';
import {PayrollDTO} from '../dto/payroll-dto';
import {PayBasis} from '../enum/pay-basis.enum';
import {ResultDTO} from '../dto/result-dto';
import {MenuItem, SelectItem} from 'primeng';
import {RouterOutlet} from '@angular/router';
import {NavigationService} from '../service/navigation.service';

@Component({
  selector: 'app-salary-form',
  templateUrl: './salary-form.component.html',
  styleUrls: ['./salary-form.component.scss']
})


export class SalaryFormComponent implements OnInit {

  navItems: MenuItem[];
  navigationService: NavigationService;
  payrollDTO: PayrollDTO;
  incomeTaxCredits: number;
  result: ResultDTO;
  payrollService: PayrollService;
  payBasisOptions: SelectItem[];


  constructor(navigationService: NavigationService, payrollService: PayrollService) {
    this.navigationService = navigationService;
    this.payrollService = payrollService;
    this.payrollDTO = payrollService.payrollInfo;
  }

  ngOnInit() {
    this.navItems = [
      {label: 'Income', routerLink: 'income'},
      {label: 'Taxes', routerLink: 'taxes'},
      {label: 'Savings', routerLink: 'savings'}
    ]
  }

/*  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
  }*/

  onSubmit() {

    this.payrollService.grossToNet().subscribe(data => {
        console.log(data);
        return data;
      }
    );
    this.payrollDTO = new PayrollDTO();
  }

}
