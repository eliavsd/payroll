import {Component, OnInit} from '@angular/core';
import {PayrollDTO} from '../../dto/payroll-dto';
import {PayrollService} from '../../service/payroll.service';
import {SavingsDTO} from '../../dto/savings-dto';
import {SavingsType} from '../../enum/savings-type.enum';
import {SelectItem} from 'primeng';
import {NavigationService} from '../../service/navigation.service';

@Component({
  selector: 'app-savings-form',
  templateUrl: './savings-form.component.html',
  styleUrls: ['./savings-form.component.scss']
})
export class SavingsFormComponent implements OnInit {

  payrollDTO: PayrollDTO;
  navigationService: NavigationService;
  savingsTypes: SelectItem[];
  pensionOption = SavingsType.PERCENTAGE;
  pensionFactor;
  edufundOption = SavingsType.PERCENTAGE;
  edufundFactor;

  constructor(navigationService: NavigationService, payrollService: PayrollService) {
    this.navigationService = navigationService;
    this.payrollDTO = payrollService.payrollInfo;
    this.savingsTypes = Object.keys(SavingsType).map(key => ({label: SavingsType[key], value: key}));
  }

  onNext() {

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
  }

  ngOnInit(): void {
    this.navigationService.currentIndex = 2;
  }

}
